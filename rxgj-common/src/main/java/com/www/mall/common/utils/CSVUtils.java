package com.www.mall.common.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    private static String charset = "UTF-8";

    /**
     * 读取csv文件
     * @throws Exception
     */
    public static List<String[]> readCsv(String path){
        List<String[]> csvList = new ArrayList<String[]>();
        try {
            CsvReader csv = new CsvReader(path, ',',Charset.forName(charset));
            //csv.readHeaders();// 跳过表头   如果需要表头的话，不要写这句。
            //逐行读入所有的数据
            while (csv.readRecord()) {
                csvList.add(csv.getValues());
//				csv.getRawRecord();
            }
            csv.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return csvList;
    }

    public static List<String[]> readCsv(InputStream inputStream){
        List<String[]> csvList = new ArrayList<String[]>();
        try {
            CsvReader csv = new CsvReader(inputStream, ',',Charset.forName(charset));
            //csv.readHeaders();// 跳过表头   如果需要表头的话，不要写这句。
            //逐行读入所有的数据
            while (csv.readRecord()) {
                csvList.add(csv.getValues());
//				csv.getRawRecord();
            }
            csv.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return csvList;
    }

    /**
     * 写入CSV文件
     */
    public static void writeCsv(String path,List<String[]> list,boolean isNotTrim) {
        try {
            CsvWriter wr = new CsvWriter(path, ',',Charset.forName(charset));
            for (String[] strArray : list) {
                wr.writeRecord(strArray, isNotTrim);//不保留前后空格
            }
            wr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){

        List<String[]> list= readCsv("C:\\Users\\rongxin\\Desktop\\new.csv");
        System.out.println(list);
    }

//	public static void setChild(Map<String,List<String[]>> map,String code,List<Area> child){
//		List<String[]> root=map.get(code);
//		for (int i = 0; i < root.size(); i++) {
//			String[] strs=root.get(i);
//			String parantCode=strs[0];
//			List<String[]> tempChild=map.get(parantCode);
//			List<Area> childchild=null;
//			if(tempChild!=null){
//				childchild=new ArrayList<Area>();
//				setChild(map,parantCode,childchild);
//			}
//
//			Area tempArea=new Area(strs[0], strs[1], strs[3], strs[4], 1, childchild);
//			child.add(tempArea);
//		}
//	}
}

