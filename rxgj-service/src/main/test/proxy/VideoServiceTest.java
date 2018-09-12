package proxy;

import org.junit.Test;

import com.www.mall.service.user.VideoServiceImpl;
import com.www.mall.user.dto.Video;
import com.www.mall.user.interf.VideoService;

import conf.BaseTest;

public class VideoServiceTest extends BaseTest {
	VideoService videoService;
	public VideoServiceTest() {
		videoService = new VideoServiceImpl();
	}
	
	@Test
	public void queryPageByVideo() {
		long userId = 0;
		result(videoService.queryPageByVideo(0, 10, userId));
	}
	/*8
	 * 添加
	 */
	@Test
	public void saveVideo() {
		Video v = new Video();
		v.setPath("tt");
		v.setTitle("ff");
		v.setUserId(2L);
		result(videoService.saveVideo(v));
	}
	/*8
	 * 观看次数
	 */
	@Test
	public void updateCount() {
		Long id = 1L;
		result(videoService.updateCount(id));
	}
	
	/*8
	 * 查询当前手机用户所看视频的点赞总数并且当前用户是否已点赞
	 */
	@Test
	public void queryVideoThumb() {
		Video v = new Video();
		v.setId(1L);
		v.setUserId(1L);
		result(videoService.queryVideoThumb(v));
	}
	
}
