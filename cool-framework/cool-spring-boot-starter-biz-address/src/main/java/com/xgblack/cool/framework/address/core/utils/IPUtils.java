package com.xgblack.cool.framework.address.core.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import com.xgblack.cool.framework.address.core.Area;
import com.xgblack.cool.framework.common.address.IPInfo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;
import java.util.List;

/**
 * IP 工具类
 * 基于 <a href="https://gitee.com/zhijiantianya/ip2region"/> 项目
 * 可以使用 https://gitee.com/nutz/nutzmore/tree/master/nutz-plugins-ip2region 优化
 *
 * @author <a href="https://www.xgblack.cn">xg black</a>
 */

@Slf4j
public class IPUtils {

    /**
     * 初始化 SEARCHER
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    private final static IPUtils INSTANCE = new IPUtils();

    /**
     * IP 查询器，启动加载到内存中
     */
    private static Searcher SEARCHER;

    private static final String DEFAULT_SEPARATOR = "|";
    /** 缺省值 */
    private static final String DEFAULT_NO_VALUE = "0";

    private static final String DEFAULT_LOCAL_VALUE = "内网";

    /**
     * 私有化构造
     */
    private IPUtils() {
        try {
            TimeInterval timer = DateUtil.timer();

            byte[] bytes = ResourceUtil.readBytes("address/ip2region.xdb");
            SEARCHER = Searcher.newWithBuffer(bytes);
            log.info("启动加载 IPUtils 成功，耗时 ({}) 毫秒", timer.interval());
        } catch (IOException e) {
            log.error("启动加载 IPUtils 失败", e);
        }
    }

    /**
     * 查询IP信息
     * @param ip ip地址
     * @return IP信息
     */
    @SneakyThrows
    public static IPInfo getIPInfo(String ip){
        String originalInfo = SEARCHER.search(ip.trim());
        if (StrUtil.isBlank(originalInfo)) {
            return null;
        }
        List<String> split = StrUtil.split(originalInfo, DEFAULT_SEPARATOR);
        //国家|区域|省份|城市|ISP    缺省为0
        //中国|0|江苏省|无锡市|联通
        String country = DEFAULT_NO_VALUE.equals(split.get(0)) ? null : split.get(0);
        String region = DEFAULT_NO_VALUE.equals(split.get(1)) ? null : split.get(1);
        String province = DEFAULT_NO_VALUE.equals(split.get(2)) ? null : split.get(2);
        String city = DEFAULT_NO_VALUE.equals(split.get(3)) ? null : split.get(3);
        String isp = DEFAULT_NO_VALUE.equals(split.get(4)) ? null : split.get(4);

        //判断是否是本地ip
        if (isp != null && StrUtil.containsIgnoreCase(isp, DEFAULT_LOCAL_VALUE)) {
            return new IPInfo().setLocal(true)
                    .setOriginalInfo(originalInfo)
                    .setIp(ip)
                    .setIsp(isp);
        }

        Area area = AreaUtils.queryArea(province, city, region);
        if (area == null) {
            String address = StrUtil.format("{}{}{}{}", country != null ? country + " " : "", province != null ? province + " " : "", city != null ? city + " " : "", region != null ? region : "");
            return new IPInfo().setLocal(false)
                    .setOriginalInfo(originalInfo)
                    .setIp(ip)
                    .setFormatterAddress(address)
                    .setIsp(isp);
        }

        return new IPInfo().setLocal(false)
                .setOriginalInfo(originalInfo)
                .setIp(ip)
                .setCode(area.getId())
                .setFormatterAddress(AreaUtils.format(area.getId()))
                .setIsp(isp);
    }



    /**
     * 查询 IP 对应的地区编号
     *
     * @param ip IP 地址，格式为 127.0.0.1
     * @return 地区id
     */
    @SneakyThrows
    public static Integer getAreaId(String ip){
        IPInfo ipInfo = getIPInfo(ip);
        return ipInfo != null ? ipInfo.getCode() : null;
    }

    /**
     * 查询 IP 对应的地区
     *
     * @param ip IP 地址，格式为 127.0.0.1
     * @return 地区
     */
    public static Area getArea(String ip) {
        return AreaUtils.getArea(getAreaId(ip));
    }

}
