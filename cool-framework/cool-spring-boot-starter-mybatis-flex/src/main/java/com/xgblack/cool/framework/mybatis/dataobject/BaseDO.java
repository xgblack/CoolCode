package com.xgblack.cool.framework.mybatis.dataobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体对象
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@Setter
@ToString
@FieldNameConstants
public abstract class BaseDO implements Serializable {

    /**
     * 创建者id
     */
    private Long creator;
    /**
     * 更新者id
     */
    private Long updater;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;

}
