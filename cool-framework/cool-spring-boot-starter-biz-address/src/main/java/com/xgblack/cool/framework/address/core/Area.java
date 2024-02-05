package com.xgblack.cool.framework.address.core;

import com.xgblack.cool.framework.address.core.enums.AreaTypeEnum;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 区域节点，包括国家、省份、城市、地区等信息
 * <p>数据见 resources/address/area.csv 文件 ,
 * 来自项目<a href="https://github.com/modood/Administrative-divisions-of-China?tab=readme-ov-file">Administrative-divisions-of-China</a>
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Area {

    /**
     * 编号 - 全球，即根目录
     */
    public static final Integer ID_GLOBAL = 0;
    /**
     * 编号 - 中国
     */
    public static final Integer ID_CHINA = 1;

    /**
     * 编号
     */
    private Integer id;
    /**
     * 名字
     */
    private String name;
    /**
     * 类型
     *
     * 枚举 {@link AreaTypeEnum}
     */
    private Integer type;

    /**
     * 父节点
     */
    private Area parent;
    /**
     * 子节点
     */
    private List<Area> children;

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
