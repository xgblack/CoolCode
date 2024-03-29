package com.xgblack.cool.module.system.web;


import com.mzt.logapi.starter.annotation.LogRecord;
import com.xgblack.cool.framework.common.pojo.dto.PageResult;
import com.xgblack.cool.module.system.api.StudentServiceI;
import com.xgblack.cool.module.system.dto.student.StudentAddCmd;
import com.xgblack.cool.module.system.dto.student.StudentEditCmd;
import com.xgblack.cool.module.system.dto.student.StudentPageQry;
import com.xgblack.cool.module.system.dto.student.clientobject.StudentCO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 测试接口
 * @author xg black
 * @date 2023/12/20 16:53
 */
@Slf4j
@RequestMapping("test")
@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceI studentService;

    //private final ResponseFactory responseFactory;

    /**
     * 测试String
     * @return
     */
    @GetMapping("string")
    public String testStr() {
        //return responseFactory.newSuccessInstance("only string response");
        //return Collections.singletonMap("resp", "resp is ok");

        return "resp is ok";
        //return 'a';

        //StudentCO studentCO = null;
        //CoolThrowable.dataFoundAssert(studentCO);
        //return studentCO.toString();
        //return CoolRespUtils.fail();
    }

    /**
     * 详情
     * @return
     */
    //@PreAuthorize("hasAuthority('SCOPE_Message')")
    @GetMapping("{id}")
    public StudentCO detail(@PathVariable Long id) {
        return studentService.getDetail(id);
    }

    /**
     * 分页查询
     * @param qry
     * @return
     */
    //@PreAuthorize("hasAuthority('SCOPE_profile')")
    @LogRecord(success = "查询学生分页,查询第{{#_ret.pageNumber}}页，共{{#_ret.totalPage}}页，{{#_ret.totalRow}}条", type = "student", bizNo = "")
    @GetMapping
    public PageResult<StudentCO> page(StudentPageQry qry) {
        return studentService.getPage(qry);
    }

    /**
     * 新增
     * @param cmd
     */
    @LogRecord(
            success = "新建了一个学生,姓名「{{#cmd.name}}」,年龄「{{#cmd.age}}」,创建结果:{{#_ret}}",
            type = "system", bizNo = "123")
    @PostMapping
    public void add(@RequestBody @Validated StudentAddCmd cmd) {
        studentService.save(cmd);
    }

    /**
     * 删除
     * @param id
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        studentService.remove(id);
    }

    /**
     * 修改
     * @param cmd
     */
    @PutMapping
    public boolean edit(@RequestBody @Validated StudentEditCmd cmd) {
        studentService.update(cmd);
        return true;
    }

}
