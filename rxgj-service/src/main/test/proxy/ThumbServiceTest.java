package proxy;

import org.junit.Test;

import com.www.mall.service.user.ThumbServiceImpl;
import com.www.mall.user.dto.Thumb;
import com.www.mall.user.interf.ThumbService;

import conf.BaseTest;

public class ThumbServiceTest  extends BaseTest{
	
	ThumbService thumbService;

	public ThumbServiceTest() {
		thumbService = new ThumbServiceImpl();
	}
	/**
	 * 查询总数
	 */
	@Test
	public void queryCountByVideoId() {
		Long id = 1L;
		Integer type = 2;
		result(thumbService.queryCountByVideoId(id,type,null));
	}

	/**
	 * 点赞
	 */
	@Test
	public void saveThumb() {
		Thumb t = new Thumb();
		t.setAId(1L);
		t.setType(2);
//		t.setUserId(2L);
		result(thumbService.saveThumb(t));
	}
}
