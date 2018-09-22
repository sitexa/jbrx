package com.sitexa.service.impl;

import com.gavin.business.DBTrans;
import com.gavin.model.Page;
import com.gavin.model.Request;
import com.gavin.model.Response;
import com.sitexa.common.base.BaseService;
import com.sitexa.facade.interfaces.BannerService;
import com.sitexa.facade.vo.Banner;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

import javax.inject.Singleton;
import java.util.List;

@Bean
@Singleton
@JbootrpcService
public class BannerServiceImpl extends BaseService implements BannerService {
    @Override
    public Page<Banner> getBannersPage(int pageNumber, int pageSize, String bannerName) {
        Request request = Request.build(service, "getBannersPage").page(pageNumber, pageSize).set("bannerName", bannerName);
        Page<Banner> page = DBTrans.page(request, Banner.class);
        return page;
    }

    @Override
    public Response insertBanner(Banner banner) {
        Request request = Request.build(service, "insertBanner").from(banner).currentTime();
        Response response = DBTrans.execute(request);
        return response;
    }

    @Override
    public Response updateBanner(Banner banner) {
        Request request = Request.build(service, "updateBanner").from(banner).currentTime();
        Response response = DBTrans.execute(request);
        return response;
    }

    @Override
    public Response delBanner(Banner banner) {
        Request request = Request.build(service, "delBanner").from(banner).currentTime();
        Response response = DBTrans.execute(request);
        return response;
    }

    @Override
    public List<Banner> getBannerInfo(int category) {
        Request request = Request.build(service, "getBannerInfo").set("category", category);
        List<Banner> list = DBTrans.list(request, Banner.class);
        return list;
    }

    @Override
    public Response updateBannerCount(Long id) {
        Request request = Request.build(service, "updateBannerCount").set("id", id).currentTime();
        Response response = DBTrans.execute(request);
        return response;
    }
}
