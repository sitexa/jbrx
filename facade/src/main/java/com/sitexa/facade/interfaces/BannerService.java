package com.sitexa.facade.interfaces;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.sitexa.facade.vo.Banner;

import java.util.List;

public interface BannerService {

    public static final String service = BannerService.class.getSimpleName();

    public Page<Banner> getBannersPage(int pageNumber, int pageSize, String bannerName);

    public Response insertBanner(Banner banner);

    public Response updateBanner(Banner banner);

    public Response delBanner(Banner banner);

    public List<Banner> getBannerInfo(int category);

    public Response updateBannerCount(Long id);
}
