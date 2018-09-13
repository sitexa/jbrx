package com.www.mall.user.interf;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.www.mall.user.dto.Tactics;
import com.www.mall.user.vo.TacticsVo;

public interface TacticsService {
    public final static String service=TacticsService.class.getSimpleName();

    public Page<TacticsVo> getTacticsAppList(int pageNumber,int pageSize,Long userId,String tacticsName,String minRate,String topRisk);

    public Response followTactics(Long userId,Long tacticsId);

    public Response cancelFollowTactics(Long userId,Long tacticsId);

    public Page<TacticsVo> getUserTacticsList(int pageNumber,int pageSize,Long userId);

    public Page<TacticsVo> getTacticsListByPlatformId(int pageNumber,int pageSize,Long userId,Long platformId,String minRate,String topRisk);

    public Page<Tactics> getTacticsList(int pageNumber,int pageSize,Long platformId,String tacticsName);

    public Response insertTactics(Tactics tactics);

    public Response updateTactics(Tactics tactics);

    public Response isUsedTactics(Tactics tactics);

    public Response delTactics(Tactics tactics);
}
