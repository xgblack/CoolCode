package com.xgblack.cool.framework.common.pojo;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.paginate.Page;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 分页参数
 * @author xg BLACK
 * @date 2023/12/23 21:49
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class PageParam implements Serializable {
    private static final Integer PAGE_NUMBER = 1;
    private static final Integer PAGE_SIZE = FlexGlobalConfig.getDefaultConfig().getDefaultPageSize();

    /**
     * 每页条数 - 不分页
     *
     * 例如说，导出接口，可以设置 {@link #pageSize} 为 -1 不分页，查询所有数据。
     */
    //public static final Integer PAGE_SIZE_NONE = -1;

    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小值为 1")
    private Integer pageNumber = PAGE_NUMBER;

    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页条数最小值为 1")
    @Max(value = 100, message = "每页条数最大值为 100")
    private Integer pageSize = PAGE_SIZE;

    public <T> Page<T> buildPage() {
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = PAGE_NUMBER;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        // 页码 + 数量
        return Page.of(this.pageNumber, this.pageSize);
    }
}
