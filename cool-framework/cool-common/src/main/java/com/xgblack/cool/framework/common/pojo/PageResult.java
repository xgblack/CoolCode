package com.xgblack.cool.framework.common.pojo;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.paginate.Page;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
    /**
     * 当前页数据。
     */
    private List<T> records = Collections.emptyList();

    /**
     * 页码
     */
    private long pageNumber = 1;

    /**
     * 每页数据数量。
     */
    private long pageSize;

    /**
     * 总页数。
     */
    private long totalPage;

    /**
     * 总数据数量。
     */
    private long totalRow;



    public static <T> PageResult<T> empty() {
        return new PageResult<>(new ArrayList<>(), 1, FlexGlobalConfig.getDefaultConfig().getDefaultPageSize(), 0, 0);
    }

    public static <T> PageResult<T> of(Page<T> page) {
        return new PageResult<>(page.getRecords(), page.getPageNumber(), page.getPageSize(), page.getTotalPage(), page.getTotalRow());
    }


}
