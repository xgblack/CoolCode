package com.xgblack.cool.module.system.web;


import com.xgblack.cool.module.system.api.StudentServiceI;
import com.xgblack.cool.module.system.dto.student.StudentAddCmd;
import com.xgblack.cool.module.system.dto.student.clientobject.StudentCO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    /**
     * 测试String
     * @return
     */
    @GetMapping("string")
    public String testStr() {
        return "resp is ok";
    }

    /**
     * 详情
     * @return
     */
    @GetMapping("{id}")
    public StudentCO detail(@PathVariable Long id) {
        //throw new RuntimeException("1122");
        log.info("id = {}", id);
        return studentService.getDetail(id);
    }

    /**
     * 新增
     * @param cmd
     */
    @PostMapping
    public void add(@RequestBody StudentAddCmd cmd) {
        studentService.save(cmd);
    }

}
