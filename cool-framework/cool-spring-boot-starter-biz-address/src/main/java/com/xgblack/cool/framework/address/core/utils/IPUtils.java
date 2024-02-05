package com.xgblack.cool.framework.address.core.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.resource.ResourceUtil;
import com.xgblack.cool.framework.address.core.Area;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;

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
     * 查询 IP 对应的地区编号
     *
     * @param ip IP 地址，格式为 127.0.0.1
     * @return 地区id
     */
    @SneakyThrows
    public static Integer getAreaId(String ip){
        return Integer.parseInt(SEARCHER.search(ip.trim()));
    }

    /**
     * 查询 IP 对应的地区编号
     *
     * @param ip IP 地址的时间戳，格式参考{@link Searcher#checkIP(String)} 的返回
     * @return 地区编号
     */
    @SneakyThrows
    public static Integer getAreaId(long ip) {
        return Integer.parseInt(SEARCHER.search(ip));
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

    /**
     * 查询 IP 对应的地区
     *
     * @param ip IP 地址的时间戳，格式参考{@link Searcher#checkIP(String)} 的返回
     * @return 地区
     */
    public static Area getArea(long ip) {
        return AreaUtils.getArea(getAreaId(ip));
    }

}
