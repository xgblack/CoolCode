package com.xgblack.cool.framework.mybatis.dataobject;

import com.mybatisflex.annotation.Column;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体对象
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Data
public abstract class BaseDO implements Serializable {

    /**
     * 创建者id TODO：优化自动填充
     */
    private Long creator;
    /**
     * 更新者id TODO：优化自动填充
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

    /**
     * 是否删除
     */
    @Column(isLogicDelete = true)
    private Boolean deleted;

}
