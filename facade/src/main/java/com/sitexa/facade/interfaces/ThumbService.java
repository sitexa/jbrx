package com.sitexa.facade.interfaces;

import com.gavin.model.Response;
import com.sitexa.facade.vo.Thumb;

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
    public Integer queryCountByVideoId(Long id, Integer type, String userId);

    
}