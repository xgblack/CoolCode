package com.xgblack.cool.module.system.gateway.database.dataobject;

import com.mybatisflex.annotation.Table;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 操作日志表
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "sys_operate_log")
public class OperateLogDO implements Serializable {

    private Long id;
    /**
     * 租户
     */
    private String tenant;

    /**
     * 保存的操作日志的类型，比如：订单类型、商品类型
     */
    private String type;
    /**
     * 日志的子类型，比如订单的C端日志，和订单的B端日志，type都是订单类型，但是子类型不一样
     */
    private String subType;

    /**
     * 日志绑定的业务标识
     */
    private String bizNo;

    /**
     * 操作人
     */
    private Long operator;

    /**
     * 日志内容
     */
    private String action;
    /**
     * 记录是否是操作失败的日志
     */
    private boolean fail;
    /**
     * 日志的创建时间
     */
    private LocalDateTime createTime;
    /**
     * 日志的额外信息
     *
     */
    private String extra;

    /**
     * 打印日志的代码信息，类名
     */
    private String className;

    /**
     * 打印日志的代码信息，方法名
     */
    private String methodName;

}
