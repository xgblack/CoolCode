package com.xgblack.cool.controller;

import com.xgblack.cool.controller.req.StudentReq;
import com.xgblack.cool.controller.resp.StudentResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 测试接口
 * @author xg black
 * @date 2023/12/20 16:53
 */
@Slf4j
@RequestMapping("test")
@RestController
public class TestController {

    /**
     * 详情
     * @return
     */
    @GetMapping("detail")
    public StudentResp testResp() {
        return new StudentResp().setId(2L).setAge(18).setName("张三").setBirthday(LocalDateTime.now()).setDate(new Date()).setLocalDate(LocalDate.now());
    }

    /**
     * 新增
     * @param req
     */
    @PostMapping
    public void add(@RequestBody StudentReq req) {
        log.info("student req = {}", req);
    }

}
