package com.www.mall.controller;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.jfinal.aop.Before;
import com.www.mall.common.base.BaseController;
import com.www.mall.interceptor.AuthInterceptor;
import com.www.mall.user.dto.Comment;
import com.www.mall.user.dto.Video;
import com.www.mall.user.interf.CommentService;
import com.www.mall.user.interf.VideoService;

import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping(value = "/manage/video")
public class VideoManageController extends BaseController {

	@JbootrpcService
	VideoService videoService;

	@JbootrpcService
	CommentService commentService;

	/**
	 * 分页查询视频
	 */
	@Before(AuthInterceptor.class)
	public void examineVideo() {
		Integer state = getParamToInt("state");
		Page<Video> page = videoService.queryRecommendVideo(pageNumber(), pageSize(), state);
		result(page);
	}

	/**
	 * 修改视频审核状态
	 */
	@Before(AuthInterceptor.class)
	public void updateVideo() {
		Integer state = getParamToInt("state");
		Response page = null;
		// Long id = getParamToLong("id");
		String id = getParam("id");
		for (String str : id.split(",")) {
			page = videoService.updateExamineVideo(state, Long.parseLong(str));
		}
		result(page);
	}

	/**
	 * 分页查询待审核的评论列表
	 */
	@Before(AuthInterceptor.class)
	public void queryPageByComment() {
		Long state = getParamToLong("state");
		Page<Comment> page = commentService.queryPageCommentByExamine(pageNumber(), pageSize(), state);
		result(page);
	}

	/**
	 * 修改审核评论状态
	 */
	@Before(AuthInterceptor.class)
	public void updateComment() {
		Integer state = getParamToInt("state");
		Long id = getParamToLong("id");
		Response page = commentService.updateExamineComment(state, id);
		result(page);
	}

}
