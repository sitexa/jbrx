package com.www.mall.user.interf;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.www.mall.user.dto.Banner;

import java.util.List;

public interface BannerService {

    public static final String service = BannerService.class.getSimpleName();

    public Page<Banner> getBannersPage(int pageNumber,int pageSize,String bannerName);

    public Response insertBanner(Banner banner);

    public Response updateBanner(Banner banner);

    public Response delBanner(Banner banner);

    public List<Banner> getBannerInfo(int category);

    public Response updateBannerCount(Long id);
}
