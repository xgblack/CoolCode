package com.xgblack.cool.framework.common.pojo;

import cn.hutool.core.collection.CollUtil;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页结果
 * @author xg BLACK
 * @date 2023/12/23 21:53
 */

@Getter
@Setter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    private List<T> list;

    private Long total;


    public PageResult(Long total) {
        this.list = new ArrayList<>();
        this.total = total;
    }

    public static <T> PageResult<T> empty() {
        return new PageResult<>(0L);
    }

    public static <T> PageResult<T> empty(Long total) {
        return new PageResult<>(total);
    }

    public static <T> PageResult<T> of(List<T> list, Long total) {
        return new PageResult<>(list, total);
    }

    public static <T> PageResult<T> of(List<T> list) {
        return new PageResult<>(list, CollUtil.isEmpty(list) ? 0L : list.size());
    }


}
