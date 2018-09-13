package proxy;

import org.junit.Test;

import com.www.mall.service.user.CommentServiceImpl;
import com.www.mall.user.dto.Comment;
import com.www.mall.user.interf.CommentService;

import conf.BaseTest;

public class CommentServiceTest  extends BaseTest{
	
	CommentService commentService;
	
	public CommentServiceTest() {
		commentService = new CommentServiceImpl();
	}
	
	@Test
	public void saveComment() {
		Comment c = new Comment();
		c.setUserId(1L);
		c.setContent("test");
		c.setState(1);
		c.setVideoId(1L);
		c.setIscomment(1);
		result(commentService.saveComment(c));
	}
	
	@Test
	public void queryPageCommentByVideoId() {
		
//		result(commentService.queryPageCommentByVideoId(0, 10, 15L));
	}
	
	@Test
	public void queryByUserComment() {
		result(commentService.queryByUserComment(2L, 15L));
	}
	
	
}
