package com.sitexa.facade.interfaces;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.sitexa.facade.vo.Comment;
import com.sitexa.facade.vo.Video;

public interface CommentService {
    public static final String SERVICE = CommentService.class.getSimpleName();
    public static final String SAVE = "saveComment";
    public static final String QUERYCOMMENTBYVIDEOID = "queryCommentByVideoId";
    public static final String QUERYPAGECOMMENTBYVIDEOID = "queryPageCommentByVideoId";

    /**
     * 保存数据
     *
     * @param comment
     * @return
     */
    public Response saveComment(Comment comment);

    /**
     * 查询当前视频的总评论数
     *
     * @param id
     * @return
     */
    public Integer queryCommentByVideoId(Long id);

    /**
     * 查询当前视频的评论，并显示评论的点赞数
     *
     * @param video
     * @return
     */
    public Page<Comment> queryPageCommentByVideoId(int pageNumber, int pageSize, Video video);

    /**
     * 分页查询待审核的评论列表
     *
     * @param pageNumber
     * @param pageSize
     * @param state
     * @return
     */
    public Page<Comment> queryPageCommentByExamine(int pageNumber, int pageSize, Long state);

    /**
     * 修改审核评论状态
     *
     * @param state
     * @param id
     * @return
     */
    public Response updateExamineComment(Integer state, Long id);

    /**
     * 当前用户是否评论过
     *
     * @param userId
     * @param videoId
     * @return
     */
    public Response queryByUserComment(Long userId, Long videoId);

}