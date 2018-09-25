package com.sitexa.api.controller;

import com.gavin.model.Page;
import com.sitexa.common.base.BaseController;
import com.sitexa.facade.interfaces.CommentService;
import com.sitexa.facade.vo.Comment;
import com.sitexa.facade.vo.Video;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/api/comment")
public class CommentController extends BaseController {

    @JbootrpcService
    CommentService commentService;

    public void getCommentByUserVideo() {
        Long videoId = getParamToLong("videoId");
        Video video = new Video();
        video.setId(videoId);
        Page<Comment> pages = commentService.queryPageCommentByVideoId(0, 10, video);
        result(pages);
    }
}
