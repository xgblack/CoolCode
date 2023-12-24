package com.xgblack.cool.module.system.gateway.database.dataobject;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.xgblack.cool.framework.mybatis.listener.DataInsertListener;
import com.xgblack.cool.framework.mybatis.listener.DataUpdateListener;
import com.xgblack.cool.framework.mybatis.type.StringListJsonTypeHandler;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author xg BLACK
 * @date 2023/12/23 19:30
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Table(value = "test_student", onInsert = DataInsertListener.class, onUpdate = DataUpdateListener.class)
public class StudentDO {
    @Id(keyType = KeyType.Auto)
    private Long id;

    private String name;

    private Integer age;

    private LocalDateTime birthday;

    @Column("local_date")
    private LocalDate localDate;

    private Date date;

    @Column(typeHandler = StringListJsonTypeHandler.class)
    private List<String> hobby;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
