package com.www.mall.controller.api;

import com.www.mall.common.base.BaseController;
import com.www.mall.common.bean.RC;
import com.www.mall.common.bean.Ret;
import com.www.mall.common.constans.Constans;
import com.www.mall.common.shiro.principal.User;
import com.www.mall.common.utils.MD5;
import com.www.mall.common.utils.StringUtils;
import com.www.mall.common.utils.WebServiceUtils;
import com.www.mall.common.utils.http.HttpRequest;
import com.www.mall.user.interf.UsersService;
import io.jboot.core.http.JbootHttp;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;
import io.jboot.web.handler.JbootHandler;

import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "app/lw")
public class LwController extends BaseController {
    @JbootrpcService
    private UsersService usersService;
    /**
     * 获取项目的登录token
     */
    public void hkLoginToken(){
        //获取接口地址
        String httpUrl=Constans.LW_HTTP_URL+"getToken?";
        Long platId=getParamToLong("platId");  //平台ID
        String login = getParam("login");   //登录账号
        String password = getParam("password");   //密钥

        if(platId==-1){
            result(RC.BUSINESS_FAIL, "平台ID不能为空.");
            return;
        }
        if(StringUtils.isBlank(login)){
            result(RC.BUSINESS_FAIL, "登录账号不能为空.");
            return;
        }
        if(StringUtils.isBlank(password)){
            result(RC.BUSINESS_FAIL, "密钥不能为空.");
            return;
        }
        httpUrl+="platId="+platId;
        //密钥：
        //测试账号：58 密码：a4321633
        //规则md5(md5(密钥+时间戳));
        //密码加密
        String getTime=String.valueOf(System.currentTimeMillis());
        //密钥+时间戳
        password+=getTime;
        password= MD5.getMD5(MD5.getMD5(password));
        httpUrl+="&sign="+password+"&timeStamp="+getTime+"&login="+login;
        //?sign=f20bfcfc68876001ff00a977f3b11f6e&timeStamp=1536568837&login=mt账号8&platId=平台ID
        //调用接口
        Ret br=WebServiceUtils.sendHttp(httpUrl,HttpRequest.METHOD_GET);
        if(br.getResult()==0) {
            //this.getSession().setAttribute(Constans.LW_LOGIN_TOKEN, br.getData());
            //this.getSession().setAttribute(Constans.LW_REGISTER_PLATID, platId);
            setJwtAttr(Constans.LW_LOGIN_TOKEN, br.getData());
            setJwtAttr(Constans.LW_REGISTER_PLATID, platId);
        }

        result(br);
    }

    /**
     * 用户注册到平台
     */
    public void registerUserToPlatform(){
        //获取接口地址
        String httpUrl=Constans.LW_HTTP_URL+"reg";
        //获取用户信息
        User user=usersService.queryUsersById(getUserId());
        if(user==null){
            result(RC.REQUEST_FAIL, "查询用户信息失败");
            return;
        }
        //平台ID
        //Long platformId=getParamToLong("platformId");
        //验证是否已经进行利万系统认证过了没
        if(getJwtPara(Constans.LW_LOGIN_TOKEN)==null){
            System.out.println("=====fff====="+getJwtPara(Constans.LW_LOGIN_TOKEN));
            //result(RC.BUSINESS_FAIL, "请先进行利万系统登录认证.");
            //return;
        }
        System.out.println("=========="+getJwtPara(Constans.LW_LOGIN_TOKEN));
        System.out.println("=========="+getJwtPara(Constans.LW_REGISTER_PLATID));

        //String token=this.getSession().getAttribute(Constans.LW_LOGIN_TOKEN).toString();
        String token=getParam("token");
        // httpUrl+="platId="+platformId;
        //设置参数
        Map<String, Object> params = new HashMap<String, Object>();
        //params.put("token",token); //token
        params.put("cardPic",user.getIdcardImg());//身份证照片
        params.put("bank",user.getBank()); //银行
        params.put("bankcardOwner",user.getBankcardOwner()); //持卡人
        params.put("branch",user.getBankch()); //支行
        params.put("bankNum",user.getBankNum()); //用户卡号
        params.put("userName",user.getRealName()); //用户名
        params.put("userPhone",user.getMobile()); //绑定手机
        params.put("userEmail",user.getEmail()); //邮箱
        params.put("userPassword",user.getPassword()); //密码
        params.put("cardNum",user.getIdcard()); //身份证

        //返回结果
        result(WebServiceUtils.sendHttp(httpUrl,params,HttpRequest.METHOD_GET,token));
    }

    /**
     * 用户是否是代理
     */
   public void userIsAgent(){
       Long userId = getUserId();
       if (userId == null) {
           result(RC.REQUEST_FAIL, "用户未登录，请先登录.");
           return;
       }
       //获取token

   }

    //获取代理用户今日分润


    //获取代理用户分润记录列表


    //获取团队中心统计数据


    //获取团队中心代理数据列表


    //获取团队中心会员数据列表


}
