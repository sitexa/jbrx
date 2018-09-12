package com.www.mall.user.interf;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.www.mall.user.dto.Platform;
import com.www.mall.user.vo.PlatformVo;

import java.util.List;

public interface PlatformService {
    public final static String service=PlatformService.class.getSimpleName();

    public Page<PlatformVo> getPlatformAppList(int pageNumber, int pageSize, Long userId, String plateformName);

    public Response followPlatform(Long userId,Long platformId);

    public Response cancelFollowPlatform(Long userId,Long platformId);

    public Page<PlatformVo> getUserPlatformList(int pageNumber,int pageSize,Long userId);

    public Page<PlatformVo> getRecommendPlatformList(int pageNumber,int pageSize,Long userId);

    public Page<Platform> getPlatformList(int pageNumber,int pageSize,String platformName);

    public Response insertPlatform(Platform platform);

    public Response updatePlatform(Platform platform);

    public Response isUsedPlatform(Platform platform);

    public Response delPlatform(Platform platform);

    public List<Platform> getPlatformInfoList();
}
