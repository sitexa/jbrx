/**
 * Banner设计。
 * 目的：显示在app头部的轮播图片，用以展示广告、推广、活动等内容，引导用户点击进入。
 * 规则：
 * 1）分栏目：【首页，栏目页】
 * 2）分地区：【省-市】
 * 3）展示时间：【startdate,enddate】
 * 4）是否有效：【status=0,1】
 * 5）媒介地址：mediaurl=/media?id=xxx&type=image；媒介类型：【image,video】；大小及尺寸：【建议宽高比：640：180，50K以内】
 * 6）动作地址：actionurl=/xxx?param1=x&param2=y&param3=z...，app内有效地址，如商品页面，活动页面等。不负责目标地址的访问权限。
 * 7）创建人，创建时间，修改时间，点击次数
 * 8）数据量有多张【1，10】；
 * 表结构：
 * CREATE TABLE `banner` (
 * `id` bigint(20) NOT NULL DEFAULT '0',
 * `category` varchar(45) NOT NULL DEFAULT '',
 * `area` varchar(200) DEFAULT NULL,
 * `murl` varchar(400) NOT NULL DEFAULT '0' COMMENT '媒介地址。mediaurl=/media?id=xxx&type=image/video',
 * `aurl` varchar(400) NOT NULL DEFAULT '0' COMMENT '动作地址：actionurl=/xxx?param1=x&param2=y&param3=z...，app内有效地址，如商品页面，活动页面等。不负责目标地址的访问权限。',
 * `startdate` datetime NOT NULL,
 * `enddate` datetime NOT NULL ,
 * `status` int(1) NOT NULL DEFAULT '0' COMMENT '是否有效。1-有效/启用；0-无效/禁用；',
 * `count` bigint(20) DEFAULT '0' COMMENT '点击次数',
 * `createuser` bigint(20) NOT NULL,
 * `createtime` datetime NOT NULL DEFAULT currentTime,
 * `updateuser` bigint(20) DEFAULT NULL,
 * `updatetime` datetime ,
 * PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='banner表';
 */
package com.sitexa.api.controller;

import com.gavin.model.Response;
import com.jfinal.aop.Before;
import com.sitexa.api.interceptor.AuthInterceptor;
import com.sitexa.common.base.BaseController;
import com.sitexa.facade.interfaces.BannerService;
import com.sitexa.facade.vo.Banner;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/app/banner")
public class BannerController extends BaseController {

    @JbootrpcService
    private BannerService bannerService;

    /**
     * 获取启动页或首页banner广告信息 1 启动页 2 首页banner
     */
    @Before(AuthInterceptor.class)
    public void getBannerInfo() {
        List<Banner> list = bannerService.getBannerInfo(getParamToInt("category"));
        result(list);
    }

    /**
     * 广告点击率
     */
    @Before(AuthInterceptor.class)
    public void updateBannerCount() {
        Response response = bannerService.updateBannerCount(getParamToLong("id"));
        result(response);
    }
}
