package com.sitexa.common.utils;

import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * dom4j解析xml,xml转化成map集合
 */
public class XMLUtil {
	
	static Logger logger = LoggerFactory.getLogger(XMLUtil.class);

	/**
	 * 获取document文档
	 */
	private static Document  getDocument(String content){
		Document document = null;
		try {
			document = DocumentHelper.parseText(content);
		} catch (DocumentException e) {
			e.printStackTrace();
			logger.error("获取Document文档异常:xml="+content);
		}
		return document;
	}
	
	/**
	 *  获取根接点
	 * 
	 * */
	public static Element  getRootElement(String content){
		Document document=getDocument(content);
		return document.getRootElement();
	}
	
    /**
     * 通过xpath获取第一个元素
     * @param rootElement
     * @param xpath
     * @return
     */
    public static Node getNode4XpathFirst(Element rootElement,String xpath){
        Node node=rootElement.selectSingleNode(xpath);
        return node;
    }
	
    
	/**
	 * xml转换为map
	 * @param strXML
	 * @return
	 */
	public static Map<String, Object> xml2map(String strXML){
		Map<String, Object> map =new HashMap<String, Object>();
		strXML=((strXML+"").trim()).replaceAll("<null>", "null");
		strXML=strXML.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
		try {
			Document document = getDocument(strXML);
			map=dom2Map(document);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("解析xml文本异常,"+strXML);
		}
		logger.info("转map结果:"+map);
		return map;
	}
	
	/**
	 * 将xml封装到map集合中
	 * @param doc
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static Map<String, Object> dom2Map(Document doc){
		Map<String, Object> map = new HashMap<String, Object>();
		if(doc == null)
			return map;
		Element root = doc.getRootElement();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			//System.out.println(e.getName());
			List list = e.elements();
			if(list.size() > 0){
				map.put(e.getName(), dom2Map(e));
			}else
				map.put(e.getName(), e.getText());
		}
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Map dom2Map(Element e){
		Map	map = new HashMap();
//		Model cdo =new Model();
		List list = e.elements();
		if(list.size() > 0){
			for (int i = 0;i < list.size(); i++) {
				Element iter = (Element) list.get(i);
				List mapList = new ArrayList();
				if(iter.elements().size() > 0){
					Map m = dom2Map(iter);
					if(map.get(iter.getName()) != null){
						Object obj = map.get(iter.getName());
						if(!obj.getClass().getName().equals("java.util.ArrayList")){
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(m);
						}
						if(obj.getClass().getName().equals("java.util.ArrayList")){
							mapList = (List) obj;
							mapList.add(m);
						}
						map.put(iter.getName(), mapList);
					}else
						map.put(iter.getName(), m);
				}
				else{
					if(map.get(iter.getName()) != null){
						Object obj = map.get(iter.getName());
						if(!obj.getClass().getName().equals("java.util.ArrayList")){
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(iter.getText());
						}
						if(obj.getClass().getName().equals("java.util.ArrayList")){
							mapList = (List) obj;
							mapList.add(iter.getText());
						}
						map.put(iter.getName(), mapList);
					}else
						map.put(iter.getName(), iter.getText());
//						cdo.set(iter.getName()+"", iter.getText()+"");
				}
			}
		}else
			map.put(e.getName(), e.getText());
		return map;
	}
    
    
    
    
    
    
	public static void main(String args[]){
		
		StringBuffer sb=new StringBuffer();
//		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
//		sb.append("<verifymessage>");
//		sb.append("<messagehead>");
//		sb.append("<status>0</status>");
//		sb.append("<statusdesc>(00)Success</statusdesc>");
//		sb.append("<mid>888</mid>");
//		sb.append("<idtype>01</idtype>");
//		sb.append("<idnumber>140202198603036511</idnumber>");
//		sb.append("<idname>张三</idname>");
//		sb.append("</messagehead>");
//		sb.append("<messagebody>");
//		sb.append("<verifystatus>0</verifystatus>");
//		sb.append("<mac></mac>");
//		sb.append("</messagebody>");
//		sb.append("</verifymessage>");
		
//		Document doc=XMLUtil.getDocument(sb.toString());
//		Element rootElement=doc.getRootElement();
//		Node node1=getNode4XpathFirst(rootElement, "/verifymessage");
//		Node node2=getNode4XpathFirst(rootElement, "/verifymessage/messagehead");
//		
//		System.out.println(node1.getText());
//		System.out.println(node2.getText());
		sb.append("<xml><return_code><![CDATA[SUCCESS]]></return_code>");
		sb.append("<return_msg><![CDATA[OK]]></return_msg>");
		sb.append("<appid><![CDATA[wxb855f599a252941d]]></appid>");
		sb.append("<mch_id><![CDATA[1489166612]]></mch_id>");
		sb.append("<nonce_str><![CDATA[LkHvlSpIWbqOMq3Y]]></nonce_str>");
		sb.append("<sign><![CDATA[CA076C55AC6EB807A7E44CAA53E6D461]]></sign>");
		sb.append("<result_code><![CDATA[SUCCESS]]></result_code>");
		sb.append("<prepay_id><![CDATA[wx2018030217035930e34cf58e0508036295]]></prepay_id>");
		sb.append("<trade_type><![CDATA[APP]]></trade_type>");
		sb.append("</xml>");
		Map<String, Object> map=XMLUtil.xml2map(sb.toString());
		System.out.println(map);
		
	}

}
