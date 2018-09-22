
/**
url:请求地址
callBack:回调函数
useCacheUrl:是否采用缓存加载数据 true 采用缓存  false 不采用缓存 此参数主要用于采用版本控制的数据
*/
function AJAXInteraction(url, callBack,useCacheUrl) {
	var req = init();
    req.onreadystatechange = processRequest;
    if(url.indexOf("?")!=-1){
	    	url=url+"&";
	    }else{
	    	url=url+"?";
	    }
    if(!useCacheUrl){
	    url=url+"t="+new Date().getTime()+"&";
    }
    url=url+"isAjax=1";
    function init() {
       var xmlHttpRequest; 
        //  判断是否把XMLHttpRequest实现为一个本地javascript对象 
        if(window.XMLHttpRequest){ 
        	xmlHttpRequest = new XMLHttpRequest(); 
        }else if(window.ActiveXObject){  //  判断是否支持ActiveX控件 
	        try{ 
	          //  通过实例化ActiveXObject的一个新实例来创建XMLHttpRequest对象 
	            xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");  //  msxml3以上版本 
	        }catch(e){ 
	            try{ 
	              //  通过实例化ActiveXObject的一个新实例来创建XMLHttpRequest对象 
	              xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");  //  msxml3以下版本 
	              }catch(e){} 
	        } 
        } 
        if ( !xmlHttpRequest ) { 
          alert("创建Ajax对象失败，您将无法正常浏览网页"); 
        } 
        return xmlHttpRequest; 
    }
   
    function processRequest () {
      	if (callBack){ 
      		if (req.readyState == 4) {
				callBack(req); 
      		}	
      			
      		}
    }

    this.doGet = function() {
      req.open("GET", url, true);
      req.send(null);
    };
    
    this.doPost = function(data) {
      req.open("POST", url, true);
      req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
      req.send(data);
    };
}

/**
function makeRequest() {
  var ai = new AJAXInteraction("processme", function() { alert("Doing Post Process");});
  ai.doGet();
}
**/