package com.www.mall.file.handler;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.gavin.business.DBTrans;
import com.gavin.model.Model;
import com.gavin.model.Response;
import com.www.mall.file.handler.store.StoreManagerProxy;
import com.www.mall.file.utils.FileHandlerUitls;

/**
 * ------------------------------
 * 文件流处理
 * ------------------------------
 * @author wdm（l311576@sina.com）  @date 2018年4月12日
 * @version 1.0
 */
public class StreamFileHanler extends FileHandler {
	private Logger log = Logger.getLogger(StreamFileHanler.class);

	@Override
	public Response handleUploadRequest(HttpServletRequest httpRequest) throws Exception {
		List<FileItem> fileItems = FileHandlerUitls.getFileItem(httpRequest);
		Response response=new Response();
		Model[] files=new Model[fileItems.size()];
		if (fileItems != null && fileItems.size()>0) {
			for (int i = 0; i < fileItems.size(); i++) {
				FileItem fileItem=fileItems.get(i);
				
				String strFileName = fileItem.getName();//
				strFileName=strFileName==null?"":strFileName;
				log.info("StreamFileHanler.handleUploadRequest strFileName="+strFileName);
				String strSuffix = FileHandlerUitls.getSuffix(strFileName);
				long lSize;
				Model cdoURIPath;
				if (fileItem.isInMemory()) {//isInMemory方法用来判断FileItem对象封装的数据内容是存储在内存中，还是存储在临时文件中，如果存储在内存中则返回true，否则返回false。
					byte[] data = fileItem.get();
					lSize = data.length;
					cdoURIPath = StoreManagerProxy.getInstance().save(data,strSuffix);
				} else {
					InputStream is = fileItem.getInputStream();
					lSize = is.available();
					cdoURIPath = StoreManagerProxy.getInstance().save(is, strSuffix);
				}
				
				response.set("strURIPath",cdoURIPath.getString("strURIPath"));
				response.set("strFileName",cdoURIPath.getString("strFileName"));
				response.set("strSourceFileName", strFileName);
				response.set("lSize", lSize);
				files[i]=new Model();
				files[i].set("strURIPath",cdoURIPath.getString("strURIPath"));
				files[i].set("strFileName",cdoURIPath.getString("strFileName"));
				files[i].set("strSourceFileName", strFileName);
				files[i].set("lSize", lSize);
				files[i].set("nFileCode", Math.abs((cdoURIPath.getString("strFileName")+"").hashCode()%DBTrans.get("FileTableCount",500)));
				
				log.info("StreamFileHanler.handleUploadRequest fileInfo="+response.toJson());
			}
		} else {
			if (getHandler() != null) {
				response = getHandler().handleUploadRequest(httpRequest);
			}
		}
		response.set("files", files);
		return response;
	}

}
