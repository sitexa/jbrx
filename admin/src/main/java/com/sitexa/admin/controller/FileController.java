package com.sitexa.admin.controller;

import com.sitexa.common.base.BaseController;
import io.jboot.web.controller.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RequestMapping(value =  "/manage/file")
public class FileController extends BaseController {

    public void uploadImage(){
        File file=getFile().getFile();
        BufferedImage image = null;
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 创建字节数组输入流
        ByteArrayInputStream bis = new ByteArrayInputStream(buffer);
        //图片文件夹名称
        String folderName = file.getName().split("\\.")[1];
        String path=getRequest().getSession().getServletContext().getRealPath("/upload");
        Map<String ,Object> map = new HashMap<String ,Object>();
        String picName = UUID.randomUUID()+"."+file.getName().split("\\.")[1];
        String picAddress = path+ picName;
        File targetFile = new File(path, picName);
        File aFile=new File(path);
        if(!aFile.exists()){
            aFile.mkdirs();
        }
        try {
            image=ImageIO.read(bis);
            ImageIO.write(image, folderName, targetFile);
        }catch (IOException e){
            e.printStackTrace();
        }
        map.put("folderName", folderName);
        map.put("picAddress", picAddress );
        map.put("picName", picName );
        map.put("hasUpload", true );

        result(map);
    }

    public void uploadFile(){
        File file=getFile().getFile();
        FileOutputStream outputStream = null;
        Map<String ,Object> map = new HashMap<String ,Object>();
        try {
            FileInputStream fis = new FileInputStream(file);
            //文件夹名称
            String folderName = file.getName().split("\\.")[1];
            String path=getRequest().getSession().getServletContext().getRealPath("/upload");
            String picName = UUID.randomUUID()+"."+file.getName().split("\\.")[1];
            String picAddress = path+ picName;
            File targetFile = new File(path, picName);
            File aFile=new File(path);
            if(!aFile.exists()){
                aFile.mkdirs();
            }
            byte temp[] = new byte[1024];
            int size = -1;
            outputStream = new FileOutputStream(targetFile);
            while ((size = fis.read(temp)) != -1) { // 每次读取1KB，直至读完
                outputStream.write(temp, 0, size);
            }
            map.put("folderName", folderName);
            map.put("picAddress", picAddress );
            map.put("picName", picName );
            map.put("hasUpload", true );
            fis.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        result(map);
    }
}
