package proxy;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.sitexa.facade.interfaces.BannerService;
import com.sitexa.facade.vo.Banner;
import com.sitexa.service.impl.BannerServiceImpl;
import conf.BaseTest;
import org.junit.Test;

import java.util.List;

public class BannerServerTest extends BaseTest {

/*    BannerService bannerService = new BannerServiceImpl();

    @Test
    public void insertBanner() {
        Banner banner = new Banner();

        banner.setCategory(1);
        banner.setName("banner");
        banner.setAurl("/action?id=1");
        banner.setMurl("/image?id=1");
        banner.setStartDate("2018-09-30");
        banner.setEndDate("2018-10-10");
        banner.setCreateUser(1L);

        Response response = bannerService.insertBanner(banner);
        result(response);
    }

    @Test
    public void getBannerPage(){
        Page<Banner> pages = bannerService.getBannersPage(0,10,"banner");
        result(pages);
    }

    @Test
    public void getBannerInfo(){
        List<Banner> banners = bannerService.getBannerInfo(1);
        result(banners);
    }*/
}
