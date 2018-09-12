package com.www.mall.user.interf;

import com.gavin.model.Response;
import com.www.mall.user.dto.Thumb;

/**
 * ------------------------------
 * 服务
 * ------------------------------
 * @author wdm  @date 2018年01月28日
 * @version 1.0
 */
public interface ThumbService  {
	public static final String SERVICE = ThumbService.class.getSimpleName();
	public static final String SAVE = "saveThumb";
	public static final String QUERYBYVIDEOANDTYPE = "queryByVideoAndType";
	public static final String QUERYCOUNTBYVIDEOID = "queryCountByVideoId";


    /**
     * 保存数据
     * @param thumb
     * @return
     */
    public Response saveThumb(Thumb thumb);


    /**
     * 根据视频主键查询出对应的点赞数据
     * @param id
     * @return
     */
    public Integer queryCountByVideoId(Long id,Integer type,String userId);

    
}