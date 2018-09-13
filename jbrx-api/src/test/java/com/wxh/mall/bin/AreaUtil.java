package com.wxh.mall.bin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaUtil {
	public static void main(String[] args){
		
		long start=System.currentTimeMillis();
		Map<String,List<String[]>> map=new HashMap<String,List<String[]>>();
		List<String[]> list=CSVUtils.readCsv("E://new.csv");
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
		System.out.println("耗时："+(System.currentTimeMillis()-start));
		String rootAreaCode="0";
		Area area=new Area(rootAreaCode, "根地区编码", 0,new ArrayList<Area>());
		setChild(map,rootAreaCode,area.getChild());//设置子地区
		System.out.println("耗时："+(System.currentTimeMillis()-start));
		
		print(area.getChild());
		System.out.println("count:"+count);
	}
	
	public static int count=0;
	public static void print(List<Area> lists){
		for (int i = 0; i < lists.size(); i++) {
			count++;
			Area areas=lists.get(i);
			System.out.println(areas.getCode()+"|"+areas.getName());
			if(areas.getChild()!=null){
				System.out.println("-----------------------------------------------------------------");
				print(areas.getChild());
			}
		}
		
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
