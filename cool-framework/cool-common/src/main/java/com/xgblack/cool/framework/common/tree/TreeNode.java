package com.xgblack.cool.framework.common.tree;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Data
public class TreeNode<R extends TreeNode> {

    /** 当前节点id */
    private Long id;

    /** 父节点id */
    private Long parentId;

    /** 当前节点名称 */
    private String name;

    /**
     * 类型-0父节点，1当前节点，2子节点
     */
    private Integer type;

    private List<R> children = Lists.newArrayList();

    public void addChild(R node) {
        children.add(node);
    }

    public TreeNode() {
    }

    public TreeNode(Long id, Long parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TreeResponseResult{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", children.size()=" + children.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode<?> that = (TreeNode<?>) o;
        return Objects.equals(id, that.id) && Objects.equals(parentId, that.parentId) && Objects.equals(name, that.name) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parentId, name, type);
    }
}
