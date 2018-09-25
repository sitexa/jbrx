package com.sitexa.admin.utils;

import com.sitexa.common.bean.Menu;
import com.sitexa.common.bean.TreeModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TreeUtil {
    private static TreeUtil treeUtil=new TreeUtil();

    public TreeUtil(){}

    public static TreeUtil instance(){
        return treeUtil;
    }
    
    public List<TreeModel> buildMenuTree(List<Menu> menus) {
    	long tempParentId=0;
    	
        
        boolean change=false;
        Map<Long,TreeModel> map= new LinkedHashMap<Long,TreeModel>();
    	for (int i = 0; i < menus.size(); i++) {
    		Menu menu=menus.get(i);
    		long parentId=menu.getParentId();
    		if(tempParentId!=parentId){
    			tempParentId=parentId;
    			change=true;
    		}
    		long id=menu.getId();
    		TreeModel exitst=change?map.get(parentId):map.get(id);
    		if(exitst!=null){
    				exitst.addChild(menu);
    		}else{
    			exitst=new TreeModel();
    			exitst.setObject(menu);
    			map.put(id, exitst);
    		}
		}
    	
    	List<TreeModel> list=new ArrayList<TreeModel>(map.values());
    	return list;
    }    

}
