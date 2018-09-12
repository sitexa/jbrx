package com.www.mall.user.interf;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.www.mall.user.dto.Video;

/**
 * ------------------------------
 * 服务
 * ------------------------------
 * @author wdm  @date 2018年01月28日
 * @version 1.0
 */
public interface VideoService  {
	public static final String SERVICE = VideoService.class.getSimpleName();
	public static final String SAVE = "saveVideo";
	public static final String UPDATECOUNT = "updateCount";
	public static final String QUERYPAGEBYVIDEO = "queryPageByVideo";
	public static final String QUERYRECOMMENDVIDEO = "queryRecommendVideo"; 
	public static final String UPDATESTATE = "updateState";

    /**
     * 保存数据
     * @param video
     * @return
     */
    public Response saveVideo(Video video);

    /**
     * 记录视频点击次数
     * @param id
     * @return
     */
	public Response updateCount(Long id);

	/**
	 * 分页查询所有的视频
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Video> queryPageByVideo(int pageNumber, int pageSize,long userId);

	/**
	 * 查询当前手机用户查询点赞总数并是否已点赞
	 * @param video
	 * @return
	 */
	public Video queryVideoThumb(Video video);
	
	/**
	 * 推荐视频
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public Page<Video> queryRecommendVideo(int pageNumber, int pageSize, Integer state);

	/**
	 * 修改视频审核状态
	 * @param state
	 * @param id
	 * @return
	 */
	public Response updateExamineVideo(Integer state, Long id);
	

}