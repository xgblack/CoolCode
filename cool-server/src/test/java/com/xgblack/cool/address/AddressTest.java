package com.xgblack.cool.address;

import com.xgblack.cool.framework.address.core.Area;
import com.xgblack.cool.framework.address.core.utils.AreaUtils;
import com.xgblack.cool.framework.address.core.utils.IPUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.date.StopWatch;
import org.dromara.hutool.core.io.resource.ResourceUtil;
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

        byte[] bytes = ResourceUtil.readBytes("address/ip2region.xdb");
        Searcher SEARCHER = Searcher.newWithBuffer(bytes);

        //String search = SEARCHER.search("153.35.178.4");
        //log.info("search = {}", search);

        StopWatch interval = StopWatch.of();
        //String ip = "153.35.178.4";
        //String ip = "1.0.171.8";
        //String ip = "127.0.0.1";
        String ip = "186.7.8.1";

        interval.start();
        String region = SEARCHER.search(ip);
        interval.stop();
        log.info("region: {}, ioCount: {}, took: {} μs", region, SEARCHER.getIOCount(), interval.getLastTaskTimeMillis());

    }

    @Test
    public void testIpInfo(){
        log.info("ipInfo = {}", IPUtils.getIPInfo("127.0.0.1"));
        log.info("ipInfo = {}", IPUtils.getIPInfo("153.35.178.4"));
        log.info("ipInfo = {}", IPUtils.getIPInfo("1.0.171.8"));
        log.info("ipInfo = {}", IPUtils.getIPInfo("39.101.77.173"));
        log.info("ipInfo = {}", IPUtils.getIPInfo("119.236.164.50"));
    }

    @Test
    public void testQueryArea(){
        //Area area = AreaUtils.queryArea("广东省", "深圳市", "龙华区");
        //Area area = AreaUtils.queryArea("江", "无", "新吴");
        //Area area = AreaUtils.queryArea(null, "无", "新吴");
        //Area area = AreaUtils.queryArea(null, null, "新吴");
        Area area = AreaUtils.queryArea("澳门", null, null);
        log.info("area = {}", area);
    }


    @Test
    public void testArea(){
        Area area = AreaUtils.getArea(320200);
        log.info("id = {},name = {}", area.getId(), area.getName());
    }
}
