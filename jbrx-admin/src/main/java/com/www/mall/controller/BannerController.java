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
package com.www.mall.controller;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.gavin.model.RetCode;
import com.jfinal.aop.Before;
import com.www.mall.common.base.BaseController;
import com.www.mall.common.bean.AdminsVo;
import com.www.mall.interceptor.AuthInterceptor;
import com.www.mall.user.dto.Banner;
import com.www.mall.user.interf.BannerService;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/manage/banner")
public class BannerController extends BaseController {
    @JbootrpcService
    private BannerService bannerService;

    /**
     * 获取广告分页列表
     */
    @Before(AuthInterceptor.class)
    public void getBannersPage() {
        Page<Banner> page = bannerService.getBannersPage(pageNumber(), pageSize(),getParam("bannerName"));
        result(page);
    }
    /**
     * 新增广告信息
     */
    @Before(AuthInterceptor.class)
    public void insertBanner() {
        Banner banner = getBeanByJsonParam(Banner.class);
        Response response = bannerService.insertBanner(banner);
        result(response);
    }
    /**
     * 修改广告信息
     */
    @Before(AuthInterceptor.class)
    public void updateBanner() {
        Banner banner = getBeanByJsonParam(Banner.class);
        Response response = bannerService.updateBanner(banner);
        result(response);
    }

    /**
     * 删除广告信息
     */
    @Before(AuthInterceptor.class)
    public void delBanner() {
        Banner banner = getBeanByJsonParam(Banner.class);
        Response response = bannerService.delBanner(banner);
        result(response);
    }
}
