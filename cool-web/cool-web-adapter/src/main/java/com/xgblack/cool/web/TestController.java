package com.xgblack.cool.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    /*@GetMapping("detail")
    public StudentResp testResp() {
        //throw new RuntimeException("1122");
        return new StudentResp().setId(2L).setAge(18).setName("张三").setBirthday(LocalDateTime.now()).setDate(new Date()).setLocalDate(LocalDate.now());
    }*/

    /**
     * 新增
     * @param req
     */
    /*@PostMapping
    public void add(@RequestBody StudentReq req) {
        log.info("student req = {}", req);
    }*/

}
