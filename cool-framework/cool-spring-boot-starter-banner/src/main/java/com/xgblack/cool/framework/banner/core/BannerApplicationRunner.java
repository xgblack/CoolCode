package com.xgblack.cool.framework.banner.core;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.concurrent.TimeUnit;

/**
 * 项目启动成功后，输出其他信息
 *
 * @author xg black
 */
@Slf4j
public class BannerApplicationRunner implements ApplicationRunner {

    @Value("${server.port}")
    private Integer port;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ThreadUtil.execute(() -> {
            // 延迟 1 秒，可以保证输出到结尾
            ThreadUtil.sleep(1, TimeUnit.SECONDS);
            log.info("""

                            \t----------------------------------------------------------
                            \t项目启动成功！
                            \t项目地址: \t{}\s
                            \t----------------------------------------------------------""",
                    "http://localhost:" + port
            );
        });
    }

    /*private static boolean isNotPresent(String className) {
        return !ClassUtils.isPresent(className, ClassUtils.getDefaultClassLoader());
    }*/

}
