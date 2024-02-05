package com.xgblack.cool.address;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.resource.ResourceUtil;
import com.xgblack.cool.framework.address.core.Area;
import com.xgblack.cool.framework.address.core.utils.AreaUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.lionsoul.ip2region.xdb.Searcher;

/**
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */
@Slf4j
public class AddressTest {

    @Test
    @SneakyThrows
    public void testIpSearch(){
        //Area localArea = IPUtils.getArea("127.0.0.1");
        //log.info("localArea:{}", localArea);

        //中国|0|香港|0|电讯盈科
        //log.info("area = {}", IPUtils.getArea("119.236.164.50"));

        //中国|0|江苏省|无锡市|联通
        //国家|区域|省份|城市|ISP
        //log.info("area = {}", IPUtils.getArea("153.35.178.4"));

        //中国|0|北京|北京市|阿里云
        //log.info("area = {}", IPUtils.getArea("39.101.77.173"));

        byte[] bytes = ResourceUtil.readBytes("address/ip2region_test.xdb");
        Searcher SEARCHER = Searcher.newWithBuffer(bytes);

        //String search = SEARCHER.search("153.35.178.4");
        //log.info("search = {}", search);

        TimeInterval timer = DateUtil.timer(true);
        //String ip = "153.35.178.4";
        String ip = "1.0.171.8";

        String region = SEARCHER.search(ip);
        log.info("region: {}, ioCount: {}, took: {} μs", region, SEARCHER.getIOCount(), timer.interval());

    }


    @Test
    public void testArea(){
        Area area = AreaUtils.getArea(320200);
        log.info("id = {},name = {}", area.getId(), area.getName());
    }
}
