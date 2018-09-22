package com.www.mall.file.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.gavin.business.DBTrans;
import com.gavin.model.Model;
import com.gavin.model.Response;
import com.gavin.security.Base64;
import com.www.mall.file.handler.store.StoreManagerProxy;
import com.www.mall.file.utils.FileHandlerUitls;

/**
 * ------------------------------
 * H5（base64）文件处理
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年4月12日
 * @version 1.0
 */
public class H5FileHanler extends FileHandler {
	private Logger log = Logger.getLogger(H5FileHanler.class);
	@Override
	public Response handleUploadRequest(HttpServletRequest httpRequest)throws Exception  {
		String imageData = httpRequest.getParameter("imageData");// html5转过来的base64字符串；
		String imageName = httpRequest.getParameter("imageName")+"";

		log.info("收到上传请求 H5FileHanler.handleUploadRequest.............");
		Response response=new Response();
		Model[] files=new Model[1];
		if (imageData != null) {

			imageName=imageName==null?"default_www_file":imageName;
			log.info("H5FileHanler.handleUploadRequest imageData="+imageData);
			String strSuffix = FileHandlerUitls.getSuffix(imageName);
			if("".equals(strSuffix)){
				strSuffix=".jpg";
			}

			byte [] data=Base64.decode(imageData);
			long lSize=data.length;
			Response cdoURIPath=StoreManagerProxy.getInstance().save(data, strSuffix);
			response.set("strURIPath", cdoURIPath.getString("strURIPath"));
			response.set("strFileName", cdoURIPath.getString("strFileName"));
			response.set("strSourceFileName", imageName==null?"":imageName);
			response.set("lSize", lSize);
			files[0]=new Model();
			files[0].set("strURIPath",cdoURIPath.getString("strURIPath"));
			files[0].set("strFileName",cdoURIPath.getString("strFileName"));
			files[0].set("strSourceFileName", imageName==null?"":imageName);
			files[0].set("lSize", lSize);
			files[0].set("nFileCode", Math.abs((cdoURIPath.getString("strFileName")+"").hashCode()%DBTrans.get("FileTableCount",500)));
			
			log.info("H5FileHanler.handleUploadRequest param="+cdoURIPath.toJson());
			response.set("files", files);
		}else{
			if(getHandler()!=null){
				response=getHandler().handleUploadRequest(httpRequest);
			}
		}
		return response;
	}

}
