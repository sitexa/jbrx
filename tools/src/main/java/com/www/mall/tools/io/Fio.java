package com.www.mall.tools.io;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdcardUtil;
import org.junit.Assert;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.Enumeration;
import java.util.Properties;

public class Fio {
    public static void main(String[] args) throws IOException {
        idcard();
    }


    public static void cpfile() {
        BufferedInputStream in = FileUtil.getInputStream("/home/xnpeng/zookeeper.out");
        BufferedOutputStream out = FileUtil.getOutputStream("/home/xnpeng/test2");
        long copySize = IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);
        System.out.println("copySize = " + copySize);
    }

    public static void fileType() {
        File file = FileUtil.file("/home/xnpeng/test2");
        String type = FileTypeUtil.getType(file);
        System.out.println("type = " + type);
    }

    public static void watch() {
        File file = FileUtil.file("/home/xnpeng/watcher");
        //这里只监听文件或目录的修改事件
        WatchMonitor watchMonitor = WatchMonitor.create(file, WatchMonitor.ENTRY_MODIFY);
        watchMonitor.setWatcher(new Watcher() {
            @Override
            public void onCreate(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("创建：{}-> {}", currentPath, obj);
            }

            @Override
            public void onModify(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("修改：{}-> {}", currentPath, obj);
            }

            @Override
            public void onDelete(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("删除：{}-> {}", currentPath, obj);
            }

            @Override
            public void onOverflow(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("Overflow：{}-> {}", currentPath, obj);
            }
        });

        //设置监听目录的最大深入，目录层级大于制定层级的变更将不被监听，默认只监听当前层级目录
        watchMonitor.setMaxDepth(5);
        //启动监听
        watchMonitor.start();
    }

    public static void classPath() throws IOException {
        ClassPathResource resource = new ClassPathResource("test.properties");
        Properties properties = new Properties();
        properties.load(resource.getStream());

        Console.log("Properties: {}", properties);

        Enumeration names = properties.propertyNames();
        while (names.hasMoreElements()) {
            Object o = names.nextElement();
            String p = properties.getProperty(o.toString());
            System.out.println(o + " = " + p);
        }
    }

    public static void readfile(){
        //默认UTF-8编码，可以在构造中传入第二个参数做为编码
        FileReader fileReader = new FileReader("/home/xnpeng/test2");
        String result = fileReader.readString();
        Console.log(result);
    }

    public static void writefile(){
        FileWriter writer = new FileWriter("/home/xnpeng/test2");
        writer.write("test");
    }

    public static void idcard(){
        String ID_18 = "321083197812162119";
        String ID_15 = "150102880730303";

//是否有效
        boolean valid = IdcardUtil.isValidCard(ID_18);
        boolean valid15 = IdcardUtil.isValidCard(ID_15);

//转换
        String convert15To18 = IdcardUtil.convert15To18(ID_15);
        Assert.assertEquals(convert15To18, "150102198807303035");

//年龄
        DateTime date = DateUtil.parse("2017-04-10");

        int age = IdcardUtil.getAgeByIdCard(ID_18, date);
        Assert.assertEquals(age, 38);

        int age2 = IdcardUtil.getAgeByIdCard(ID_15, date);
        Assert.assertEquals(age2, 28);

//生日
        String birth = IdcardUtil.getBirthByIdCard(ID_18);
        Assert.assertEquals(birth, "19781216");

        String birth2 = IdcardUtil.getBirthByIdCard(ID_15);
        Assert.assertEquals(birth2, "19880730");

//省份
        String province = IdcardUtil.getProvinceByIdCard(ID_18);
        Assert.assertEquals(province, "江苏");

        String province2 = IdcardUtil.getProvinceByIdCard(ID_15);
        Assert.assertEquals(province2, "内蒙古");
    }
}
