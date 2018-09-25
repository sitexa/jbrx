package com.sitexa.service.impl;

import javax.inject.Singleton;
import com.gavin.business.DBTrans;
import com.gavin.model.Page;
import com.gavin.model.Request;
import com.gavin.model.Response;

import com.sitexa.common.base.BaseService;
import com.sitexa.common.shiro.principal.UserPrincipal;
import com.sitexa.facade.interfaces.CommentService;
import com.sitexa.facade.interfaces.ThumbService;
import com.sitexa.facade.interfaces.UserService;
import com.sitexa.facade.vo.Comment;
import com.sitexa.facade.vo.Video;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.rpc.annotation.JbootrpcService;

@Bean
@Singleton
@JbootrpcService
public class CommentServiceImpl extends BaseService implements CommentService {
	
	@JbootrpcService
	ThumbService thumbService;
	
	@JbootrpcService
    private UserService usersService;

	/**
	 * 保存评论
	 */
	@Override
	public Response saveComment(Comment comment) {
		Request request=Request.build(SERVICE, SAVE).from(comment).currentTime();
		Response response=DBTrans.execute(request);
		return response;
	}

	/**
	 * 查询当前视频的评论总数
	 */
	@Override
	public Integer queryCommentByVideoId(Long id) {
		Request request=Request.build(SERVICE, QUERYCOMMENTBYVIDEOID).set("id", id);
		Response count = DBTrans.execute(request);
//		System.out.println(count.get("totalCount"));
		return Integer.parseInt(count.get("totalCount").toString());
	}
	
	/**
	 * 查询当前视频的评论，并显示评论的点赞数
	 */
	@Override
	public Page<Comment> queryPageCommentByVideoId(int pageNumber ,int pageSize, Video video) {
		Request request=Request.build(SERVICE, QUERYPAGECOMMENTBYVIDEOID).set("id", video.getId()).page(pageNumber, pageSize);
		Page<Comment> page = DBTrans.page(request, Comment.class);
		if(page.getList().size() > 0) {
			for(Comment c:page.getList()) {
				UserPrincipal u = usersService.queryUserById(c.getUserId());
				if(u != null) {
					c.setUserName(u.getNickName());
					c.setUserPic(u.getPic());
				}
				//此条评论的点赞总数
				Integer commentCount = thumbService.queryCountByVideoId(c.getId(), 2, null);
				c.setCommentThumb(commentCount);
				// 当前用户是否已点赞过
				Integer videoCounts = thumbService.queryCountByVideoId(c.getId(), 2, video.getDeviceId());
				if (videoCounts == 0) {
					c.setIsThumb(false); // 未点赞
				} else {
					c.setIsThumb(true); // 已点赞
				}
			}
		}
		return page;
	}
	/**
	 * 分页查询待审核的评论列表
	 */
	@Override
	public Page<Comment> queryPageCommentByExamine(int pageNumber, int pageSize, Long state) {
		Request request=Request.build(SERVICE, "queryPageCommentByExamine").set("state", state).page(pageNumber, pageSize);
		Page<Comment> page = DBTrans.page(request, Comment.class);
		return page;
	}
    /**
     * 修改审核评论状态
     */
	@Override
	public Response updateExamineComment(Integer state, Long id) {
		Request request=Request.build(SERVICE, "updateComment").set("state", state).set("id", id);
		return DBTrans.execute(request);
	}

	@Override
	public Response queryByUserComment(Long userId, Long videoId) {
		Request request=Request.build(SERVICE, "queryByUserComment").set("userId", userId).set("videoId", videoId);
		Response rs = DBTrans.execute(request);
		Comment c = rs.bean(Comment.class);
		if(c==null) {
			rs.data = false;
		}else {
			rs.data = true;
		}
		return rs;
	}
}