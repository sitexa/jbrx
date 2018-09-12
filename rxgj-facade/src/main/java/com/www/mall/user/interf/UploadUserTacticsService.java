package com.www.mall.user.interf;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.www.mall.user.dto.UploadUserTactics;

public interface UploadUserTacticsService {
    public final static String service=UploadUserTacticsService.class.getSimpleName();
    public Response followUploadUserTactics(Long userId, Long tacticsId);
    public Page<UploadUserTactics> getUploadUserTacticsList(int pageNumber, int pageSize, Long userId, String tacticsName);
}

