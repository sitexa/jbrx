package com.wxh.mall.bin;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.fabric.xmlrpc.base.Array;
import com.www.mall.common.utils.CsvReader;
import com.www.mall.common.utils.CsvWriter;

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
//		List<String[]> list= readCsv("E://地区.csv");
//		 ArrayList<String[]> newlist = new ArrayList<>();
//		 for (int i = 0; i < list.size(); i++) {
//			 String[] strs= list.get(i);
//			 for (int j = 0; j < 4; j++) {
//				 System.out.print(strs[j]+"|");
//			}
//			String[] temp={strs[0],strs[1].replace("'", ""),strs[2],strs[4].replace("'", ""),strs[5].replace("'", "")}; 
//			newlist.add(temp);
//			 System.out.println();
//		 }
//		 writeCsv("E:/new.csv",newlist,true);
		
		Map<String,List<String[]>> map=new HashMap<String,List<String[]>>();
		List<String[]> list= readCsv("E://new.csv");
		for (int i = 0; i < list.size(); i++) {
			String[] strs= list.get(i);
			List<String[]> tempList=map.get(strs[2]);
			if(tempList!=null){
				tempList.add(strs);
			}else{
				tempList=new ArrayList<String[]>();
				tempList.add(strs);
				map.put(strs[2], tempList);
			}
			
		}
		
		Area area=new Area("0", "地区", 0,new ArrayList<Area>());
		List<Area> child=area.getChild();
		setChild(map,"0",child);
		
		
//		List<String[]> root=map.get("0");
//		for (int i = 0; i < root.size(); i++) {
//			String[] strs=root.get(i);
//			Area tempArea=new Area(strs[0], strs[1], strs[3], strs[4], 1, null);
//			child.add(tempArea);
//		}
//		
//		
//		for (int i = 0; i < list.size(); i++) {
//			 String[] strs= list.get(i);
//			 int size=child.size();
//			 if(size==0){
//				 Area temp=new Area(strs[0], strs[1], strs[3], strs[4], 1, null);
//				 
//			 }
//			 
//			 
//			 for (int j = 0; j < strs.length; j++) {
//				 System.out.print(strs[j]+"|");
//			}
			 System.out.println();
//		}
	}
	
	public static void setChild(Map<String,List<String[]>> map,String code,List<Area> child){
		List<String[]> root=map.get(code);
		for (int i = 0; i < root.size(); i++) {
			String[] strs=root.get(i);
			String parantCode=strs[0];
			List<String[]> tempChild=map.get(parantCode);
			List<Area> childchild=null;
			if(tempChild!=null){
				childchild=new ArrayList<Area>();
				setChild(map,parantCode,childchild);
			}
			
			Area tempArea=new Area(strs[0], strs[1], strs[3], strs[4], 1, childchild);
			child.add(tempArea);
		}
	}
	
}
