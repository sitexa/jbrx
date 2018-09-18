(function($){
	
	var httpClient = new HttpClient("handleTrans.cdo");
	$.extend({	
		handleTrans:function(parm,cdoResponse){
			
			var cdoRequest = new CDO();
			for(var field in parm){
				if(typeof(parm[field]) == "string") {
					cdoRequest.setStringValue(field,parm[field]);
				}
				else if(parm[field] instanceof CDO){
					cdoRequest.setCDOValue(field,parm[field]);
				}else if(typeof(parm[field]) == "object"){
					var argus = parm[field];
					for(var p in argus){
						if(typeof(argus[p]) == "string"){
							cdoRequest.setStringValue(p, argus[p]);
						}else if(argus[p] instanceof Array){
							
							cdoRequest.setStringArrayValue(p, argus[p]);
						};
					}
				}
			};
			
			var ret = httpClient.handleTrans(cdoRequest, cdoResponse);
			if(ret.nCode != 0) {
				alert("调用服务失败："+ret.getText());
			}
			return ret;
		},
		raiseTrans:function(parm){
			var cdoRequest = new CDO();
			for(var field in parm){
				if(typeof(parm[field]) == "string") {
					cdoRequest.setStringValue(field,parm[field]);
				}
				else if(parm[field] instanceof CDO){
					cdoRequest.setCDOValue(field,parm[field]);
				}
			};
			httpClient.raiseTrans(cdoRequest, false, null);

		},
		
		triggerCommand:function(config){
			
			$.fn.Render.init.initData(config);
			$.fn.Render.flowCtrl.triggerCommand();
		}
	});
	
	$.fn.extend({
		getExcel:function(config){
			if(!confirm("文件只能下载一次，\r\n 再次下载需要重复请求，\r\n 您确定要继续吗？")){
				return false;
			}
			var cdoResponse = new CDO();
			$.fn.Render.init.initData(config);
			$.fn.Render.flowCtrl.getExcel(cdoResponse);
			var url = cdoResponse.getStringValue("URL");
			var strFileName = cdoResponse.getStringValue("strFileName");
			window.open(url);
			
		}
	});
	
	$.fn.Render = {
		config:{
			data:{},
			cdoExportCfg:new CDO()
		},
		flowCtrl:{
			triggerCommand:function(){
				var ret2 = $.fn.Render.flowCtrl.isDownLoading();
				if(ret2.nCode != 0) {
					return false;
				};
				$.raiseTrans({strServiceName:"ExcelService",strTransName:"exportSheetExcel",cdoExportCfg:$.fn.Render.config.cdoExportCfg});
				alert("正在下载中。。。");
			},
			
			delTask:function(){
				var cdoResponse = new CDO();
				$.handleTrans({strServiceName:"ExcelService",strTransName:"deleteFinishTask",cdoExportCfg:$.fn.Render.config.cdoExportCfg},cdoResponse);
			},
			
			getExcel:function(cdoResponse){
				
				$.handleTrans({strServiceName:"ExcelService",strTransName:"getExcel",cdoExportCfg:$.fn.Render.config.cdoExportCfg},cdoResponse);
			},
			isDownLoading:function(){
				var cdoResponse = new CDO();
				$.fn.Render.config.data.strTrans = 
					$.fn.Render.config.data.strServiceName + "+" + $.fn.Render.config.data.strTransName;
				$.fn.Render.config.data.strCondition = "["+$.fn.Render.config.data.columns.join(", ")+"]";
				return $.handleTrans({conditions:$.fn.Render.config.data,strServiceName:"ExcelService",strTransName:"isDownLoading"},cdoResponse);
			}
		},
		init:{
			initData:function(config){
				if(!config.data){
					$.extend(true,$.fn.Render.config.data,config);
				}else{
					$.extend(true,$.fn.Render.config, config);
				}
				var cdoExportCfg = $.fn.Render.config.cdoExportCfg;
				
				cdoExportCfg.setStringValue("nRecordCount", "nRecordCount");
				
				var parms = $.fn.Render.config.data;
				for(var field in parms){
					if(typeof(parms[field]) == "string"){
						cdoExportCfg.setStringValue(field, parms[field]);
					}else if(parms[field] instanceof Array){
						cdoExportCfg.setStringArrayValue(field, parms[field]);
					}else if(parms[field] instanceof CDO){
						cdoExportCfg.setCDOValue(field,parms[field]);
					}
					;
				};
			}
		}
	};
	
})(jQuery);
