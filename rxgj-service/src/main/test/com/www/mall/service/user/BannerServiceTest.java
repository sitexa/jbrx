package com.www.mall.service.user;

import com.gavin.model.Page;
import com.gavin.model.Response;
import com.gavin.model.RetCode;
import com.www.mall.common.utils.DateUtils;
import com.www.mall.user.dto.Banner;
import com.www.mall.user.interf.BannerService;
import com.xiaoleilu.hutool.date.DateUtil;
import conf.BaseTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BannerServiceTest extends BaseTest {
    BannerService service;

    public BannerServiceTest() {
        service = new BannerServiceImpl();
    }

    @Test
    public void testSave() {
        Banner banner = new Banner();
        banner.setArea("湖南长沙");
        banner.setAurl("/prod?id=1002");
        banner.setCategory("index");
        banner.setMurl("/image?id=1003");
        banner.setCreateUser(1001L);
        result(service.save(banner));
    }

    @Test
    public void testSaveWithDate() {

        String startDate = "2018-9-30 12:00:00";
        String endDate = "2018-10-20 11:00:00";

        Banner banner = new Banner();
        banner.setArea("湖南长沙");
        banner.setAurl("/prod?id=1005");
        banner.setCategory("index");
        banner.setMurl("/image?id=1005");
        banner.setCreateUser(1001L);

        banner.setStartDate(startDate);
        banner.setEndDate(endDate);

        result(service.save(banner));
    }

    @Test
    public void testFindById() {
        Banner banner = service.findById(1L);
        System.out.println("banner = " + banner.toString());
        result(banner);
    }

    @Test
    public void testDeleteById() {
        Response response = service.deleteById(17L);
        result(response);
    }

    @Test
    public void testUpdate() {

        Banner banner = service.findById(18L);

        Date dd = DateUtils.convertAsDate("2018-10-1");
        Date de = DateUtils.convertAsDate("2018-10-7");

        String sdate = DateUtils.date2String(dd);
        String edate = DateUtils.date2String(de);

        banner.setStartDate(sdate);
        banner.setEndDate(edate);
        result(service.update(banner));

        banner.setStartDate("");
        result(service.update(banner));

        banner.setEndDate("");
        result(service.update(banner));

        dd = DateUtils.convertAsDate("2018-10-20");
        sdate = DateUtils.date2String(dd);
        banner.setStartDate(sdate);
        result(service.update(banner));

    }

    @Test
    public void testUpdateCount() {

        result(service.updateCount(19L));

    }

    @Test
    public void testUpdateStatus() {
        int status = 1;
        Long updateUser = 1002L;
        result(service.updateStatus(18L, status, updateUser));
    }

    @Test
    public void testPaginate() {

        int page = 1;
        int pageSize = 3;

        Page<Banner> pages = service.paginate(page, pageSize);

        List<Banner> banners = pages.getList();
        for (Banner banner : banners) {
            System.out.println("banner.toString() = " + banner.toString());
        }

        result(pages);

    }

    /**
     * (int page, int pageSize, int status, String category, String area, String startDate, String endDate)
     */
    @Test
    public void testQuery() {

        Date startDay = DateUtils.addDay(new Date(), -10);
        String _startDay = DateUtils.date2String(startDay);
        Date endDay = DateUtils.addDay(new Date(), 30);
        String _endDay = DateUtils.date2String(endDay);

        System.out.println("_startDay = " + _startDay);
        System.out.println("_endDay = " + _endDay);

        Page<Banner> pages = service.queryBanner(1, 3, 0, "index", "湖南长沙", _startDay, _endDay);
        result(pages);
    }


    @Test
    public void testBulkUpdate() {

        String idsStr = "27,26,25";
        String statusStr = "1,1,0";

        List<Banner> list = new ArrayList<>();

        String[] ids = idsStr.split(",");
        String[] statuss = statusStr.split(",");
        if (ids.length != statuss.length) {

        } else {
            for (int i = 0; i < ids.length; i++) {
                Banner banner = new Banner();
                banner.setId(Long.valueOf(ids[i]));
                banner.setStatus(Integer.valueOf(statuss[i]));
                list.add(banner);
            }
        }

        Response response = service.updateStatusList(list, 1L);

        result(response);

    }


    @Test
    public void testBulkDelete() {

        long[] ids = new long[2];
        ids[0] = 27L;
        ids[1] = 26L;


        List<Banner> list = new ArrayList<>();

        Banner banner = new Banner();
        banner.setId(27L);
        list.add(banner);

        banner = new Banner();
        banner.setId(26L);
        list.add(banner);

        Response response = service.deleteByIdList(list);
        result(response);

    }
}
