package com.www.mall.service.user;

import javax.inject.Singleton;
import com.gavin.business.DBTrans;
import com.gavin.model.Page;
import com.gavin.model.Request;
import com.gavin.model.Response;
import com.www.mall.common.base.BaseService;
import com.www.mall.common.shiro.principal.UserPrincipal;
import com.www.mall.user.dto.Video;
import com.www.mall.user.interf.CommentService;
import com.www.mall.user.interf.ThumbService;
import com.www.mall.user.interf.UserService;
import com.www.mall.user.interf.VideoService;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

/**
 * ------------------------------ Video服务 ------------------------------
 * 
 * @author wdm @date 2018年01月28日
 * @version 1.0
 */
@Bean
@Singleton
@JbootrpcService
public class VideoServiceImpl extends BaseService implements VideoService {

	@JbootrpcService
	ThumbService thumbService;

	@JbootrpcService
	CommentService commentService;
	
	@JbootrpcService
    private UserService usersService;

	/**
	 * 保存视频地址
	 */
	@Override
	public Response saveVideo(Video video) {
		video.setState(1);
		Request request = Request.build(SERVICE, SAVE).from(video).currentTime();
		Response response = DBTrans.execute(request);
		return response;
	}

	/**
	 * 记录视频的观看次数
	 */
	@Override
	public Response updateCount(Long id) {
		Request request = Request.build(SERVICE, UPDATECOUNT).set("id", id);
		Response response = DBTrans.execute(request);
		return response;
	}

	/**
	 * 分页查询所有视频的页面
	 */
	@Override
	public Page<Video> queryPageByVideo(int pageNumber, int pageSize, long userId) {
		Request request;
		if (userId == 0) {
			request = Request.build(SERVICE, QUERYPAGEBYVIDEO).page(pageNumber, pageSize);
		} else {
			request = Request.build(SERVICE, QUERYPAGEBYVIDEO).page(pageNumber, pageSize).set("userId", userId);
		}
		Page<Video> page = DBTrans.page(request, Video.class);
		if (page.getList().size() > 0) {
			for (Video video : page.getList()) {
				UserPrincipal u = usersService.queryUsersById(video.getUserId());
				if(u != null) {
					video.setUserName(u.getNickName());
					video.setUserPic(u.getPic());
				}
				// 对应的评论
				Integer commentCount = commentService.queryCommentByVideoId(video.getId());
				video.setCommentCount(commentCount);
			}
		}
		return page;
	}

	/**
	 * 查询当前手机用户所看视频的点赞总数并且当前用户是否已点赞
	 */
	@Override
	public Video queryVideoThumb(Video video) {
		// 获取对应的视频点赞数
		Integer videoCount = thumbService.queryCountByVideoId(video.getId(), 1, null);
		video.setVideoCount(videoCount);
		// 当前用户是否已点赞过
		Integer videoCounts = thumbService.queryCountByVideoId(video.getId(), 1, video.getDeviceId());
		if (videoCounts == 0) {
			video.setIsThumb(false); // 未点赞
		} else {
			video.setIsThumb(true); // 已点赞
		}
		return video;
	}

	/**
	 * 推荐视频以点击数为准则，从大到小
	 */
	@Override
	public Page<Video> queryRecommendVideo(int pageNumber, int pageSize, Integer state) {
		Request request;
		if (state == -1) {
			request = Request.build(SERVICE, QUERYRECOMMENDVIDEO).page(pageNumber, pageSize);
		} else {
			request = Request.build(SERVICE, QUERYRECOMMENDVIDEO).page(pageNumber, pageSize).set("state", state);
		}
		Page<Video> page = DBTrans.page(request, Video.class);
		if(page.getList().size() > 0) {
			for(Video v:page.getList()) {
				UserPrincipal user = usersService.queryUsersById(v.getUserId());
				if(user != null) {
					v.setUserName(user.getNickName());
					v.setUserPic(user.getPic());
				}
			}
		}
		return page;
	}

	/**
	 * 修改视频审核状态
	 * 
	 * @param state
	 * @return
	 */
	@Override
	public Response updateExamineVideo(Integer state, Long id) {
		Request request = Request.build(SERVICE, UPDATESTATE).set("state", state).set("id", id);
		Response res = DBTrans.execute(request);
		return res;

	}

}