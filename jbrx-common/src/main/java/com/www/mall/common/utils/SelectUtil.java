package com.www.mall.common.utils;
//package com.wxh.manager.utils;
//
//import java.util.ArrayList;
//import java.util.Dictionary;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.jfinal.plugin.activerecord.Model;
//
///**
// * Created by wangdongming on 16/9/3.
// */
//public class SelectUtil {
//
//        private static SelectUtil SelectUtil=new SelectUtil();
//        private static Map<String, List<Model>> map=new HashMap<String, List<Model>>();
//
//        public SelectUtil(){}
//
//        public static SelectUtil instance(){
//            return SelectUtil;
//        }
//
//        public static void build(String itemCode,String itemParam){
//            List<Model> list=map.get(itemCode);
//            if(list!=null){
//                map.remove(itemCode);
//            }
//            if(itemCode.indexOf(".")>0){
//                String[] table=itemCode.split("\\.");
//                List<CnCallArea> listDict= CnCallArea.dao.find("select * from "+table[0] +" where "+table[1]+"=?",itemParam);
//                list=new ArrayList<Model>();
//                for (int i = 0; i < listDict.size(); i++) {
//                    list.add(listDict.get(i));
//                }
//            }else{
//                List<Dictionary> listDict=Dictionary.dao.findByDictCode(itemCode);
//                list=new ArrayList<Model>();
//                for (int i = 0; i < listDict.size(); i++) {
//                    list.add(listDict.get(i));
//                }
//            }
//            map.put(itemCode,list);
//        }
//
//        public static List<Model> get(String itemCode,String itemParam){
//            if(map.get(itemParam)==null){
//                build(itemCode,itemParam);
//            }
//            return map.get(itemParam);
//        }
//
//        public synchronized static void clear(String itemCode){
//            map.remove(itemCode);
//        }
//
//}
