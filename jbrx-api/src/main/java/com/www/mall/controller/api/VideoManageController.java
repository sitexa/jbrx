package com.www.mall.controller.api;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.www.mall.common.base.BaseController;
import com.www.mall.common.bean.RC;
import com.www.mall.common.constants.HttpImgContants;
import com.www.mall.common.validator.VGroup;
import com.www.mall.common.validator.Validator;
import com.www.mall.user.dto.Comment;
import com.www.mall.user.dto.Thumb;
import com.www.mall.user.dto.Video;
import com.www.mall.user.interf.CommentService;
import com.www.mall.user.interf.ThumbService;
import com.www.mall.user.interf.VideoService;

import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/video/manage")
public class VideoManageController extends BaseController {

	@JbootrpcService
	VideoService videoService;

	@JbootrpcService
	CommentService commentService;

	@JbootrpcService
	ThumbService thumbService;

	/**
	 * 上传视频
	 */
	@Validator(bean = Video.class, group = VGroup.save)
	public void upload() {
		// 将json字符串转化成对象
		Video video = getBeanByJsonParam(Video.class);
		// 获取当前登录用户ID
		Long userId = getUserId();
		if (userId == null) {
			result(RC.REQUEST_FAIL, "参数错误，请先登录再去添加视频.");
			return;
		}
		video.setUserId(userId);
		video.setPath(HttpImgContants.IMG_URL + video.getPath());
		video.setImg(HttpImgContants.IMG_URL + video.getImg());
		result(videoService.saveVideo(video));
	}

	/**
	 * 分页查询视频
	 */
	public void queryPageByVideo() {
		long userId = 0;
		Page<Video> page = videoService.queryPageByVideo(pageNumber(), pageSize(), userId);
		result(page);
	}

	/**
	 * 分页查询我的视频
	 */
	public void queryPageByMyVideo() {
		// 获取当前登录用户ID
		Long userId = getUserId();
		if (userId == null) {
			result(RC.REQUEST_FAIL, "参数错误，请先登录再去查看自己上传的视频.");
			return;
		}
		Page<Video> page = new Page<Video>();
		// if (userId == null) {
		// page.setList(new ArrayList<Video>());
		// } else {
		page = videoService.queryPageByVideo(pageNumber(), pageSize(), userId);
		// }
		result(page);
	}

	/**
	 * 点击看视频的次数记录
	 */
	public void updateCountVideo() {
		// 当前视频主键
		Long id = getParamLong("id");
		if (id == null) {
			result(RC.REQUEST_FAIL, "参数错误.");
			return;
		}
		result(videoService.updateCount(id));
	}

	/**
	 * 用户点赞 ： type:1--视频点赞，2--评论点赞 ,userId:手机端唯一编号
	 */
	@Validator(bean = Thumb.class, group = VGroup.save)
	public void videoThumb() {
		Thumb thumb = getBeanByJsonParam(Thumb.class);
		result(thumbService.saveThumb(thumb));
	}

	/**
	 * 记录相关评论
	 */
	@Validator(bean = Comment.class, group = VGroup.save)
	public void addComment() {
		Comment comment = getBeanByJsonParam(Comment.class);
		// 获取当前登录用户ID
		Long userId = getUserId();
		if (userId == null) {
			result(RC.REQUEST_FAIL, "参数错误，请先登录再去评论.");
			return;
		}
		comment.setUserId(userId);
		comment.setIscomment(1);
		result(commentService.saveComment(comment));
	}

	/**
	 * 查询当前手机用户查询点赞总数并是否已点赞
	 */
	public void queryVideoThumb() {
		// 将json字符串转化成对象
		Video video = getBeanByJsonParam(Video.class);
		result(videoService.queryVideoThumb(video));
	}

	/**
	 * 推荐视频
	 */
	public void queryRecommendVideo() {
		Integer state = 1;
		result(videoService.queryRecommendVideo(pageNumber(), pageSize(), state));
	}

	/**
	 * 查询当前视频的评论
	 */
	public void queryPageCommentByVideoId() {
		// 将json字符串转化成对象
		Video video = getBeanByJsonParam(Video.class);
		result(commentService.queryPageCommentByVideoId(pageNumber(), pageSize(), video));
	}

	/**
	 * 是否评论过
	 */
	public void isComment() {
		// 获取当前登录用户ID
		Long userId = getUserId();
		if (userId == null) {
			result(RC.REQUEST_FAIL, "参数错误，请先登录再去评论.");
			return;
		}
		Long videoId = getParamLong("videoId");
		Response rs = commentService.queryByUserComment(userId,videoId);
		result(rs);
	}
}
