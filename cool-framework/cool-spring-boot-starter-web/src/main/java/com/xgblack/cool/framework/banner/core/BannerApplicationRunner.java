package com.xgblack.cool.framework.banner.core;

import org.dromara.hutool.core.net.NetUtil;
import org.dromara.hutool.core.text.StrUtil;
import org.dromara.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.LinkedHashSet;
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
        Thread.ofVirtual()
                .name("banner-virtual-thread")
                .start(() -> {
                    // 延迟 1 秒，可以保证输出到结尾
                    ThreadUtil.sleep(1, TimeUnit.SECONDS);

                    LinkedHashSet<String> ipv4s = NetUtil.localIpv4s();
                    StringBuilder sb = new StringBuilder();
                    for (String ipv4 : ipv4s) {
                        sb.append(StrUtil.format("\t项目地址: \thttp://{}:{}\n", ipv4, port));
                    }

                    log.info("""

                            \t----------------------------------------------------------
                            {}
                            \t项目启动成功！
                            \t----------------------------------------------------------""", sb.toString());
                });

    }


}
