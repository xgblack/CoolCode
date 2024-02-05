package com.xgblack.cool.framework.address.core.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.util.StrUtil;
import com.xgblack.cool.framework.address.core.Area;
import com.xgblack.cool.framework.address.core.enums.AreaTypeEnum;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;

/**
 * 区域工具类
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
public class AreaUtils {

    /**
     * 初始化 SEARCHER
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    private final static AreaUtils INSTANCE = new AreaUtils();

    /**
     * Area 内存缓存，提升访问速度
     */
    private static Map<Integer, Area> areas;

    private AreaUtils() {
        TimeInterval timer = DateUtil.timer();

        areas = new HashMap<>();
        areas.put(Area.ID_GLOBAL, new Area(Area.ID_GLOBAL, "全球", 0, null, new ArrayList<>()));
        // 从 csv 中加载数据
        List<CsvRow> rows = CsvUtil.getReader().read(ResourceUtil.getUtf8Reader("address/area.csv")).getRows();
        rows.removeFirst(); // 删除 header
        for (CsvRow row : rows) {
            // 创建 Area 对象
            Area area = new Area(Integer.valueOf(row.get(0)), row.get(1), Convert.toInt(row.get(2)), null, new ArrayList<>());
            // 添加到 areas 中
            areas.put(area.getId(), area);
        }

        // 构建父子关系：因为 Area 中没有 parentId 字段，所以需要重复读取
        for (CsvRow row : rows) {
            Area area = areas.get(Convert.toInt(row.get(0))); // 自己
            Area parent = areas.get(Convert.toInt(row.get(3))); // 父
            Assert.isTrue(area != parent, "{}:父子节点相同", area.getName());
            area.setParent(parent);
            parent.getChildren().add(area);
        }

        log.info("启动加载 AreaUtils 成功，耗时 ({}) 毫秒", timer.interval());
    }

    /**
     * 获得指定编号对应的区域
     *
     * @param id 区域编号
     * @return 区域
     */
    public static Area getArea(Integer id) {
        return areas.get(id);
    }

    /**
     * 格式化区域
     *
     * @param id 区域编号
     * @return 格式化后的区域
     */
    public static String format(Integer id) {
        return format(id, " ");
    }

    /**
     * 格式化区域
     *
     * 例如说：
     *      1. id = “静安区”时：上海 上海市 静安区
     *      2. id = “上海市”时：上海 上海市
     *      3. id = “上海”时：上海
     *      4. id = “美国”时：美国
     * 当区域在中国时，默认不显示中国
     *
     * @param id 区域编号
     * @param separator 分隔符
     * @return 格式化后的区域
     */
    public static String format(Integer id, String separator) {
        // 获得区域
        Area area = areas.get(id);
        if (area == null) {
            return null;
        }

        // 格式化
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < AreaTypeEnum.values().length; i++) { // 避免死循环
            sb.insert(0, area.getName());
            // “递归”父节点
            area = area.getParent();
            if (area == null || Area.ID_GLOBAL.equals(area.getId()) || Area.ID_CHINA.equals(area.getId()) ){
                // 跳过父节点为中国的情况
                break;
            }
            sb.insert(0, separator);
        }
        return sb.toString();
    }

    /**
     * 获取指定类型的区域列表
     *
     * @param type 区域类型
     * @param func 转换函数
     * @param <T>  结果类型
     * @return 区域列表
     */
    public static <T> List<T> getByType(AreaTypeEnum type, Function<Area, T> func) {
        if (CollUtil.isEmpty(areas.values())) {
            return new ArrayList<>();
        }
        return areas.values().stream()
                .filter(area -> type.getType().equals(area.getType()))
                .map(func)
                .filter(Objects::nonNull)
                .toList();
    }

    /**
     * 根据区域编号、上级区域类型，获取上级区域编号
     *
     * @param id   区域编号
     * @param type 区域类型
     * @return 上级区域编号
     */
    public static Integer getParentIdByType(Integer id, @NonNull AreaTypeEnum type) {
        for (int i = 0; i < Byte.MAX_VALUE; i++) {
            Area area = AreaUtils.getArea(id);
            if (area == null) {
                return null;
            }
            // 情况一：匹配到，返回它
            if (type.getType().equals(area.getType())) {
                return area.getId();
            }
            // 情况二：找到根节点，返回空
            if (area.getParent() == null || area.getParent().getId() == null) {
                return null;
            }
            // 其它：继续向上查找
            id = area.getParent().getId();
        }
        return null;
    }

    /**
     * 根据省市区模糊搜索
     * @param province 省份
     * @param city 市
     * @param region 区/县
     * @return Area
     */
    public static Area queryArea(String province, String city, String region) {
        if (province == null && city == null && region == null) {
            return null;

        }

        if (province != null && city != null && region != null) {

            List<Area> provinceList = areas.values().parallelStream()
                    .filter(area -> AreaTypeEnum.PROVINCE.getType().equals(area.getType()) && StrUtil.contains(area.getName(), province))
                    .toList();
            if (CollUtil.isEmpty(provinceList)) {
                return null;
            }
            List<Area> cityList = provinceList.parallelStream()
                    .flatMap(area -> area.getChildren().stream())
                    .filter(area -> AreaTypeEnum.CITY.getType().equals(area.getType()) && StrUtil.contains(area.getName(), city))
                    .toList();
            if (CollUtil.isEmpty(cityList)) {
                return null;
            }
            return cityList.parallelStream()
                    .flatMap(area -> area.getChildren().stream())
                    .filter(area -> AreaTypeEnum.DISTRICT.getType().equals(area.getType()) && StrUtil.contains(area.getName(), region))
                    .findFirst().orElse(null);

        }

        if (province != null && city != null && region == null) {

            List<Area> provinceList = areas.values().parallelStream()
                    .filter(area -> AreaTypeEnum.PROVINCE.getType().equals(area.getType()) && StrUtil.contains(area.getName(), province))
                    .toList();
            if (CollUtil.isEmpty(provinceList)) {
                return null;
            }
            return provinceList.parallelStream()
                    .flatMap(area -> area.getChildren().stream())
                    .filter(area -> AreaTypeEnum.CITY.getType().equals(area.getType()) && StrUtil.contains(area.getName(), city))
                    .findFirst().orElse(null);

        }


        if (province != null && city == null && region != null) {

            List<Area> provinceList = areas.values().parallelStream()
                    .filter(area -> AreaTypeEnum.PROVINCE.getType().equals(area.getType()) && StrUtil.contains(area.getName(), province))
                    .toList();
            if (CollUtil.isEmpty(provinceList)) {
                return null;
            }
            return provinceList.parallelStream()
                    .flatMap(area -> area.getChildren().stream())
                    .flatMap(area -> area.getChildren().stream())
                    .filter(area -> AreaTypeEnum.DISTRICT.getType().equals(area.getType()) && StrUtil.contains(area.getName(), region))
                    .findFirst().orElse(null);

        }

        if (province != null && city == null && region == null) {

            return areas.values().parallelStream()
                    .filter(area -> AreaTypeEnum.PROVINCE.getType().equals(area.getType()) && StrUtil.contains(area.getName(), province))
                    .findFirst().orElse(null);

        }

        if (province == null && city != null && region != null) {

            List<Area> cityList = areas.values().parallelStream()
                    .flatMap(area -> area.getChildren().stream())
                    .filter(area -> AreaTypeEnum.CITY.getType().equals(area.getType()) && StrUtil.contains(area.getName(), city))
                    .toList();
            if (CollUtil.isEmpty(cityList)) {
                return null;
            }
            return cityList.parallelStream()
                    .flatMap(area -> area.getChildren().stream())
                    .filter(area -> AreaTypeEnum.DISTRICT.getType().equals(area.getType()) && StrUtil.contains(area.getName(), region))
                    .findFirst().orElse(null);

        }

        if (province == null && city != null && region == null) {

            return areas.values().parallelStream()
                    .flatMap(area -> area.getChildren().stream())
                    .filter(area -> AreaTypeEnum.CITY.getType().equals(area.getType()) && StrUtil.contains(area.getName(), city))
                    .findFirst().orElse(null);

        }

        if (province == null && city == null && region != null) {

            return areas.values().parallelStream()
                    .flatMap(area -> area.getChildren().stream())
                    .filter(area -> AreaTypeEnum.DISTRICT.getType().equals(area.getType()) && StrUtil.contains(area.getName(), region))
                    .findFirst().orElse(null);

        }
        return null;
    }
}
