package com.xgblack.cool.framework.common.tree;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.dromara.hutool.core.collection.CollUtil;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TreeUtil {

    /**
     * @ApiModelProperty(value = "类型-0父节点，1当前节点，2子节点", position = 3)
     * private Integer type;
     */
    private static final Integer PARENT_TYPE = 0;
    private static final Integer CURR_TYPE = 1;
    private static final Integer CHILD_TYPE = 2;

    /**
     * 查找当前节点和子节点的id列表
     *
     * @param list
     * @param rSupplier
     * @param currNodeId
     * @param <R>
     * @return
     */
    public static <R extends TreeNode> List<Long> getChildIds(List<R> list, Supplier<R> rSupplier, Long currNodeId) {
        Map<Long, R> treeMap = new HashMap<>(128);
        list.forEach(treeVo -> {
            Long pid = treeVo.getParentId();
            Long oid = treeVo.getId();
            // 处理当前组织
            R current = treeMap.computeIfAbsent(oid, (id) -> rSupplier.get());
            BeanUtils.copyProperties(treeVo, current, "children");

            // 处理父组织 绑定父级组织
            R parent = treeMap.computeIfAbsent(pid, (id) -> rSupplier.get());
            parent.addChild(current);
        });
        R currNode = treeMap.get(currNodeId);
        List<Long> result = Lists.newArrayList(currNodeId);
        if (currNode != null) {
            Set<R> repeatInfo = new HashSet<>();
            Queue<R> queue = new LinkedList<>();
            queue.addAll(currNode.getChildren());
            while (!queue.isEmpty()) {
                R currInfo = queue.poll();
                if (repeatInfo.contains(currInfo)) {
                    continue;
                }
                repeatInfo.add(currInfo);
                result.add(currInfo.getId());
                List<R> children = treeMap.get(currInfo.getId()).getChildren();
                if (!CollUtil.isEmpty(children)) {
                    queue.addAll(children);
                }
            }
        }
        return result;
    }

    /**
     * 根据list返回一个树
     *
     * @param list
     * @param rSupplier
     * @return
     */
    public static <R extends TreeNode> List<R> getChildInfos(List<R> list, Supplier<R> rSupplier) {
        Map<Long, R> treeMap = new HashMap<>(128);
        list.forEach(treeVo -> {
            Long pid = treeVo.getParentId();
            Long oid = treeVo.getId();
            // 处理当前组织
            R current = treeMap.computeIfAbsent(oid, (id) -> rSupplier.get());
            BeanUtils.copyProperties(treeVo, current, "children");

            // 处理父组织 绑定父级组织
            R parent = treeMap.computeIfAbsent(pid, (id) -> rSupplier.get());
            parent.addChild(current);
        });
        Long rootId = 0L;
        R node = treeMap.get(rootId);
        return node.getChildren();
    }

    /**
     * 根据list返回一个树，并设置父节点，当前节点和子节点
     *
     * @param list   原始列表对象（必须包含id,和 parentId）
     * @param currId 用户所属的id
     * @return
     */
    public static <R extends TreeNode> List<R> getChildInfosWithCurrId(List<R> list, Supplier<R> rSupplier,
                                                                       Long currId) {
        Map<Long, R> treeMap = new HashMap<>(128);
        list.forEach(treeVo -> {
            Long pid = treeVo.getParentId();
            Long oid = treeVo.getId();
            // 处理当前组织
            R current = treeMap.computeIfAbsent(oid, (id) -> rSupplier.get());
            BeanUtils.copyProperties(treeVo, current, "children");

            // 处理父组织 绑定父级组织
            R parent = treeMap.computeIfAbsent(pid, (id) -> rSupplier.get());
            parent.addChild(current);
        });

        //根节点为0L
        Long rootId = 0L;
        //标记父节点
        R curr = treeMap.get(currId);
        if (Objects.isNull(curr)) {
            return Lists.newArrayList();
        }
        List<R> currInfos = Lists.newArrayList(treeMap.get(currId));

        Set<R> repeatInfo = new HashSet<>();
        R rootTree = null;
        for (R root : currInfos) {
            Queue<R> queue = new LinkedList<>();
            queue.add(root);
            root.setType(CURR_TYPE);
            if (rootId.equals(root.getId())) {
                rootTree = root;
                break;
            }
            while (!queue.isEmpty()) {
                R currInfo = queue.poll();
                if (repeatInfo.contains(currInfo)) {
                    continue;
                }
                repeatInfo.add(currInfo);
                R parentNode = treeMap.get(currInfo.getParentId());
                if (Objects.nonNull(parentNode)) {
                    R cloneParentNode = rSupplier.get();
                    BeanUtils.copyProperties(parentNode, cloneParentNode);
                    cloneParentNode.setChildren(Lists.newArrayList(currInfo));
                    cloneParentNode.setType(PARENT_TYPE);
                    if (rootId.equals(cloneParentNode.getId())) {
                        rootTree = cloneParentNode;
                        break;
                    }
                    queue.add(cloneParentNode);

                } else {
                    rootTree = currInfo;
                }
            }
        }
        // 防止出现环的情况
        repeatInfo = new HashSet<>();
        for (R root : currInfos) {
            Queue<R> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                R currInfo = queue.poll();
                if (repeatInfo.contains(currInfo)) {
                    continue;
                }
                repeatInfo.add(currInfo);
                List<R> children = treeMap.get(currInfo.getId()).getChildren();
                if (!CollUtil.isEmpty(children)) {
                    children.forEach(child -> child.setType(CHILD_TYPE));
                    queue.addAll(children);
                }
            }
        }
        return rootTree.getChildren();
    }


    /**
     * 在当前树上根据名称搜索
     *
     * @param currDeptTrees
     * @param rSupplier
     * @param searchIds     根据名称在数据库中like查询节点列表
     * @param <R>
     * @return
     */
    public static <R extends TreeNode> List<R> getCurrDeptTreesWithSearch(List<R> currDeptTrees,
                                                                          Supplier<R> rSupplier, List<Long> searchIds) {
        Map<Long, R> treeMap = Maps.newHashMap();
        Set<R> repeatInfo = new HashSet<>();
        for (R root : currDeptTrees) {
            Queue<R> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                R currInfo = queue.poll();
                if (repeatInfo.contains(currInfo)) {
                    continue;
                }
                repeatInfo.add(currInfo);
                treeMap.put(currInfo.getId(), currInfo);
                List<R> children = currInfo.getChildren();
                if (!CollUtil.isEmpty(children)) {
                    queue.addAll(children);
                }
            }
        }
        List<R> childInfos1 = getChildInfos1(treeMap, rSupplier, searchIds);
        return childInfos1;
    }

    private static <R extends TreeNode> List<R> getChildInfos1(Map<Long, R> treeMap, Supplier<R> rSupplier,
                                                               List<Long> searchIds) {
        //标记父节点
        List<R> currInfos = treeMap.entrySet().stream().filter(entry -> searchIds.contains(entry.getKey()))
                .map(entry -> entry.getValue()).collect(Collectors.toList());
        Map<Long, R> newTreeMap = Maps.newHashMap();
        Set<R> rootNodes = Sets.newHashSet();
        for (R root : currInfos) {
            Set<R> repeatInfo = new HashSet<>();
            Queue<R> queue = new LinkedList<>();
            queue.add(root);
            //处理当前节点和子节点
            while (!queue.isEmpty()) {
                R currInfo = queue.poll();
                if (repeatInfo.contains(currInfo)) {
                    continue;
                }
                repeatInfo.add(currInfo);
                newTreeMap.put(currInfo.getId(), currInfo);
                List<R> children = currInfo.getChildren();
                if (!CollUtil.isEmpty(children)) {
                    queue.addAll(children);
                }
            }
            repeatInfo = new HashSet<>();
            queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                R currInfo = queue.poll();
                if (repeatInfo.contains(currInfo)) {
                    continue;
                }
                repeatInfo.add(currInfo);
                R parentNode = treeMap.get(currInfo.getParentId());
                if (Objects.nonNull(parentNode)) {
                    R cloneParentNode = null;
                    if (newTreeMap.containsKey(parentNode.getId())) {
                        cloneParentNode = newTreeMap.get(currInfo.getParentId());
                        List<R> children = cloneParentNode.getChildren();
                        List<Long> childIds = children.stream().map(t -> t.getId()).collect(Collectors.toList());
                        if (!childIds.contains(currInfo.getId())) {
                            cloneParentNode.addChild(currInfo);
                        }
                    } else {
                        cloneParentNode = rSupplier.get();
                        BeanUtils.copyProperties(parentNode, cloneParentNode);
                        cloneParentNode.setChildren(Lists.newArrayList(currInfo));
                        newTreeMap.put(cloneParentNode.getId(), cloneParentNode);
                    }
                    queue.add(cloneParentNode);
                } else {
                    rootNodes.add(currInfo);
                }
            }
        }

        return Lists.newArrayList(rootNodes);
    }

}
