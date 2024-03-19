package com.xgblack.cool.module.system.web;

import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.api.PostServiceI;
import com.xgblack.cool.module.system.dto.company.post.PostAddCmd;
import com.xgblack.cool.module.system.dto.company.post.PostEditCmd;
import com.xgblack.cool.module.system.dto.company.post.PostPageQry;
import com.xgblack.cool.module.system.dto.company.post.clientobject.PostCO;
import com.xgblack.cool.module.system.dto.company.post.clientobject.PostSimpleCO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@RestController
@RequestMapping("/system/post")
@Validated
@RequiredArgsConstructor
public class PostController {

    private final PostServiceI postService;

    /**
     * 创建岗位
     * @param cmd
     */
    @PostMapping
    //@Operation(summary = "创建岗位")
    //@PreAuthorize("@ss.hasPermission('system:post:create')")
    public void add(@Valid @RequestBody PostAddCmd cmd) {
        postService.add(cmd);
    }

    /**
     * 修改岗位
     * @param cmd
     */
    @PutMapping
    //@Operation(summary = "修改岗位")
    //@PreAuthorize("@ss.hasPermission('system:post:update')")
    public void edit(@Valid @RequestBody PostEditCmd cmd) {
        postService.edit(cmd);
    }

    /**
     * 删除岗位
     * @param id
     */
    @DeleteMapping("{id}")
    //@Operation(summary = "删除岗位")
    //@PreAuthorize("@ss.hasPermission('system:post:delete')")
    public void remove(@PathVariable("id") Long id) {
        postService.remove(id);
    }

    /**
     * 获得岗位信息
     * @param id
     * @return
     */
    @GetMapping(value = "detail/{id}")
    //@Operation(summary = "获得岗位信息")
    //@Parameter(name = "id", description = "岗位编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('system:post:query')")
    public PostCO detail(@PathVariable("id") Long id) {
        return postService.detail(id);
    }

    /**
     * 获取岗位精简列表
     * @return
     */
    @GetMapping(value = {"/list-all-simple", "simple-list"})
    //@Operation(summary = "获取岗位全列表", description = "只包含被开启的岗位，主要用于前端的下拉选项")
    public List<PostSimpleCO> getSimpleList() {
        // 获得岗位列表，只要开启状态的
        return postService.getSimpleList();
    }

    /**
     * 获得岗位分页列表
     * @param qry
     * @return
     */
    @GetMapping("/page")
    //@Operation(summary = "获得岗位分页列表")
    //@PreAuthorize("@ss.hasPermission('system:post:query')")
    public PageResult<PostCO> page(@Validated PostPageQry qry) {
        return postService.page(qry);
    }

    /*@GetMapping("/export")
    @Operation(summary = "岗位管理")
    @PreAuthorize("@ss.hasPermission('system:post:export')")
    @OperateLog(type = EXPORT)
    public void export(HttpServletResponse response, @Validated PostPageReqVO reqVO) throws IOException {
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PostDO> list = postService.getPostPage(reqVO).getList();
        // 输出
        ExcelUtils.write(response, "岗位数据.xls", "岗位列表", PostRespVO.class,
                BeanUtils.toBean(list, PostRespVO.class));
    }*/

}
