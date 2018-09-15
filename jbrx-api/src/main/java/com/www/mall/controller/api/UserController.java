package com.www.mall.controller.api;

import com.gavin.model.Response;
import com.jfinal.aop.Before;
import com.www.mall.common.base.BaseController;
import com.www.mall.common.base.BaseImgUrl;
import com.www.mall.common.bean.RC;
import com.www.mall.common.bean.Ret;
import com.www.mall.common.bean.UserVo;
import com.www.mall.common.shiro.principal.UserPrincipal;
import com.www.mall.common.utils.RegexUtils;
import com.www.mall.common.utils.StringUtils;
import com.www.mall.common.validator.VGroup;
import com.www.mall.common.validator.Validator;
import com.www.mall.interceptor.AuthInterceptor;
import com.www.mall.message.dto.MessageRecord;
import com.www.mall.message.dto.TemplateType;
import com.www.mall.message.interf.MessageService;
import com.www.mall.user.dto.Register;
import com.www.mall.user.interf.UserService;
import com.xiaoleilu.hutool.util.RandomUtil;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.annotation.RequestMapping;
import org.apache.shiro.crypto.hash.Md5Hash;

@RequestMapping("/api/user")
public class UserController extends BaseController {
    @JbootrpcService
    private UserService usersService;
    @JbootrpcService
    private MessageService messageService;

    /**
     * 注册
     */
    @Validator(bean=Register.class,group=VGroup.save)
    public void register(){
        //1.获取注册信息
        Register register=getBeanByJsonParam(Register.class);

        //2.验证短信是否匹配
        Ret ret=messageService.verifyCode(register.getMessageId(), register.getVerifyCode());
        if(!ret.success()){
            result(ret);
            return;
        }

        //3.保存用户
        String password=register.getPassword();
        String salt=RandomUtil.simpleUUID();
        String saltPassword=new Md5Hash(password,salt,3).toString();
        UserPrincipal users=new UserPrincipal();
        users.setMobilePhone(register.getMobilePhone());
        users.setPassword(saltPassword);
        users.setSalt(salt);
        users.setNickName(register.getNickName());

        Response response=usersService.saveUsers(users);
        result(response);
    }

    /**
     * 注册-发送验证码
     */
    @Validator(bean=Register.class,group=VGroup.excutor)
    public void sendRegisterCode(){

        //1.获取注册信息
        Register users=getBeanByJsonParam(Register.class);
        String mobilePhone=users.getMobilePhone();

        //2.判断用户是否存在
        Response response =	usersService.queryUserNameExist(mobilePhone);
        if(response.fail()){
            result(RC.REQUEST_FAIL, "用户服务暂不可用,请稍后再试");
            return;
        }
        Long userCount=response.getLong("userCount");
        if(userCount!=null && userCount>0){
            result(RC.BUSINESS_FAIL, "当前手机号码已被注册");
            return;
        }

        //3.发送短信
        MessageRecord messageRecord=new MessageRecord();
        messageRecord.setServiceType(TemplateType.REGISTER.name());//这个一定要按业务填写，否则有问题
        messageRecord.setContent(TemplateType.REGISTER.name());
        messageRecord.setTitle("发送注册短信验证码");
        messageRecord.setMessageType(1);
        messageRecord.setReceiver(mobilePhone);
        messageRecord.setMessageSn(RandomUtil.simpleUUID());

        result(messageService.sendCode(messageRecord));
    }

    /**
     * 修改密码-需要登录
     */
    @Before(AuthInterceptor.class)
    public void updatePassword(){
        String password=getParam("password");
        String oldPassword=getParam("oldPassword");
        if(password==null){
            result(RC.PARAM_FAIL, "密码不能为空");
            return;
        }
        if(oldPassword==null){
            result(RC.PARAM_FAIL, "原密码不能为空");
            return;
        }

        UserPrincipal user=usersService.queryUsersById(getUserId());
        if(user==null){
            result(RC.REQUEST_FAIL, "查询用户信息失败");
            return;
        }
        String currentPassword=user.getPassword();
        String verfyPassword = new Md5Hash(oldPassword, user.getSalt(), 3).toString();
        if(!currentPassword.equals(verfyPassword)){
            result(RC.BUSINESS_FAIL, "原密码错误,请重新输入");
            return;
        }

        String newSalt=RandomUtil.simpleUUID();
        String newPassword = new Md5Hash(password, newSalt, 3).toString();
        result(usersService.updatePassword(user.getId(),newPassword,newSalt));
    }


    /**
     * 忘记密码重设密码
     */
    public void resetPassword(){
        String mobilePhone=getParam("mobilePhone");
        String password=getParam("password");
        String verifyCode=getParam("verifyCode");
        String messageId=getParam("messageId");
        if(password==null){
            result(RC.PARAM_FAIL, "密码不能为空");
            return;
        }
        if(verifyCode==null || verifyCode.length()!=6){
            result(RC.PARAM_FAIL, "请输入正确的短信验证码");
            return;
        }
        if(messageId==null){
            result(RC.PARAM_FAIL, "短信验证码失败");
            return;
        }
        Response response=usersService.queryUsersByUsersName(mobilePhone);
        if(response==null || response.fail()){
            result(RC.PARAM_FAIL, "查询用户信息失败，请重试");
            return;
        }

        //2.验证短信是否匹配
        Ret ret=messageService.verifyCode(messageId, verifyCode);
        if(!ret.success()){
            result(ret);
            return;
        }

        String newSalt=RandomUtil.simpleUUID();
        String newPassword = new Md5Hash(password, newSalt, 3).toString();
        result(usersService.updatePassword(response.getLong("id"),newPassword,newSalt));
    }

    /**
     * 未登陆忘记密码-发送短信
     */
    public void sendCodeByForgetPassword(){
        String mobilePhone=getParam("mobilePhone");
//		//1.获取注册信息
//		Register users=getBeanByJsonParam(Register.class);
//		String mobilePhone=users.getMobilePhone();
        if(mobilePhone==null || mobilePhone.length()!=11){
            result(RC.BUSINESS_FAIL, "请输入正确的手机号码");
            return;
        }

        //2.判断用户是否存在
        Response response =	usersService.queryUserNameExist(mobilePhone);
        if(response.fail()){
            result(RC.REQUEST_FAIL, "用户服务暂不可用,请稍后再试");
            return;
        }
        Long userCount=response.getLong("userCount");
        if(userCount==0){
            result(RC.BUSINESS_FAIL, "请输入正确的手机号码");
            return;
        }

        //3.发送短信
        MessageRecord messageRecord=new MessageRecord();
        messageRecord.setServiceType(TemplateType.UPDATE_PWD.name());//这个一定要按业务填写，否则有问题
        messageRecord.setContent(TemplateType.UPDATE_PWD.name());
        messageRecord.setTitle("发送忘记密码短信验证码");
        messageRecord.setMessageType(1);
        messageRecord.setReceiver(mobilePhone);
        messageRecord.setMessageSn(RandomUtil.simpleUUID());

        result(messageService.sendCode(messageRecord));
    }

    /**
     * 已登录忘记密码-发送短信
     */
    public void sendCodeByLoginUserForgetPassword(){

        UserVo user=getUser();
        String mobilePhone=null;
        if(user==null){
            mobilePhone=getParam("mobilePhone");
        }else{
            mobilePhone=user.getMobilePhone();
        }

        //3.发送短信
        MessageRecord messageRecord=new MessageRecord();
        messageRecord.setServiceType(TemplateType.UPDATE_PWD.name());//这个一定要按业务填写，否则有问题
        messageRecord.setContent(TemplateType.UPDATE_PWD.name());
        messageRecord.setTitle("发送忘记密码短信验证码");
        messageRecord.setMessageType(1);
        messageRecord.setReceiver(mobilePhone);
        messageRecord.setMessageSn(RandomUtil.simpleUUID());

        result(messageService.sendCode(messageRecord));
    }



    /**
     * 查询用户信息
     */
    public void getUserInfo(){
        Long userId = getUserId();
        if (userId == null) {
            result(RC.REQUEST_FAIL, "用户未登录，请先登录.");
            return;
        }
        UserPrincipal user=usersService.queryUsersById(userId);
        if(user==null){
            result(RC.REQUEST_FAIL, "查询用户信息失败");
            return;
        }
        result(Ret.resultFile(user,BaseImgUrl.fastUrl+BaseImgUrl.IMG_BASE));
    }

    /**
     * 修改银行信息
     */
    public void updateBankInfo(){
        Long userId = getUserId();
        if (userId == null) {
            result(RC.REQUEST_FAIL, "用户未登录，请先登录.");
            return;
        }
        String bank=getParam("bank");   //开户银行
        String bankch=getParam("bankch");   //支行信息
        String bankNum=getParam("bankNum");   //银行卡号
        String bankImg=getParam("bankImg");   //银行卡照片
        String bankImgBack=getParam("bankImgBack");   //银行卡照片背面
        String bankcardOwner=getParam("bankcardOwner");   //持卡人姓名
        String mobile=getParam("mobile");   //绑定手机号
        if(StringUtils.isBlank(bank)){
            result(RC.BUSINESS_FAIL, "开户银行不能为空.");
            return;
        }
        if(StringUtils.isBlank(bankch)){
            result(RC.BUSINESS_FAIL, "支行信息不能为空.");
            return;
        }
        if(StringUtils.isBlank(bankNum)){
            result(RC.BUSINESS_FAIL, "银行卡号不能为空.");
            return;
        }else if(!RegexUtils.checkBankCard(bankNum)){
            result(RC.BUSINESS_FAIL, "银行卡号不正确.");
            return;
        }
        if(StringUtils.isBlank(bankImg)){
            result(RC.BUSINESS_FAIL, "请上传银行卡正面图片");
            return;
        }
        if(StringUtils.isBlank(bankImgBack)){
            result(RC.BUSINESS_FAIL, "请上传银行卡背面图片");
            return;
        }
        if(StringUtils.isBlank(bankcardOwner)){
            result(RC.BUSINESS_FAIL, "持卡人姓名不能为空.");
            return;
        }
//        if(mobile==null || !RegexUtils.checkMobile(mobile)){
//            result(RC.BUSINESS_FAIL, "请输入正确的手机号码.");
//            return;
//        }
        UserPrincipal user=new UserPrincipal();
        user.setId(userId);
        user.setBank(bank);
        user.setBankch(bankch);
        user.setBankImg(bankImg);
        user.setBankNum(bankNum);
        user.setBankcardOwner(bankcardOwner);
       // user.setMobile(mobile);
        user.setBankImgBack(bankImgBack);
        result(usersService.updateBankInfo(user));
    }

    /**
     * 修改用户证件信息
     */
    public void updateUserIdcardInfo() {
        Long userId = getUserId();
        if (userId == null) {
            result(RC.REQUEST_FAIL, "用户未登录，请先登录.");
            return;
        }
        String realName = getParam("realName");   //真实姓名
        String idcard = getParam("idcard");   //证件号码
        String idcardImg = getParam("idcardImg");   //证件图片
        String idcardImgBack = getParam("idcardImgBack");   //证件图片背面
        //String country = getParam("country");   //国籍
       // Integer cardType = getParamInteger("cardType");   //证件类型
        if (StringUtils.isBlank(realName)) {
            result(RC.BUSINESS_FAIL, "姓名不能为空.");
            return;
        }
        if (StringUtils.isBlank(idcard)) {
            result(RC.BUSINESS_FAIL, "证件号码不能为空.");
            return;
        }
        if (StringUtils.isBlank(idcardImg)) {
            result(RC.BUSINESS_FAIL, "证件正面图片不能为空.");
            return;
        }
        if (StringUtils.isBlank(idcardImgBack)) {
            result(RC.BUSINESS_FAIL, "证件背面图片不能为空.");
            return;
        }


//        if (StringUtils.isBlank(country)) {
//            result(RC.BUSINESS_FAIL, "请选择国籍.");
//            return;
//        }
//        if (cardType== null || !(cardType>=0 && cardType<=3)) {
//            result(RC.BUSINESS_FAIL, "请选择证件类型.");
//            return;
//        }
        if(!RegexUtils.checkIdCard(idcard)){
            result(RC.BUSINESS_FAIL, "请输入正确的身份证号码.");
            return;
        }
        UserPrincipal user=new UserPrincipal();
        user.setId(userId);
        user.setRealName(realName);
        user.setIdcard(idcard);
        user.setIdcardImg(idcardImg);
        user.setIdcardImgBack(idcardImgBack);
//        user.setCountry(country);
//        user.setCardType(cardType);
        result(usersService.updateUserIdcardInfo(user));
    }


    public void updateUserAddress(){
        Long userId = getUserId();
        if (userId == null) {
            result(RC.REQUEST_FAIL, "用户未登录，请先登录.");
            return;
        }
        String pro = getParam("pro");   //省份
        String city = getParam("city");   //城市
        String area = getParam("area");   //区
        String addr = getParam("addr");   //详细地址
        if (StringUtils.isBlank(pro)) {
            result(RC.BUSINESS_FAIL, "省份不能为空.");
            return;
        }
        if (StringUtils.isBlank(city)) {
            result(RC.BUSINESS_FAIL, "城市不能为空.");
            return;
        }
        if (StringUtils.isBlank(area)) {
            result(RC.BUSINESS_FAIL, "区不能为空.");
            return;
        }
        if (StringUtils.isBlank(addr)) {
            result(RC.BUSINESS_FAIL, "详细地址不能为空.");
            return;
        }

        UserPrincipal user=new UserPrincipal();
        user.setId(userId);
        user.setPro(pro);
        user.setCity(city);
        user.setArea(area);
        user.setAddr(addr);
        result(usersService.updateUserAddress(user));
    }

    /**
     * 修改用户信息  公共修改非法
     */
    public void updateUserInfo() {
        Long userId = getUserId();
        if (userId == null) {
            result(RC.REQUEST_FAIL, "用户未登录，请先登录.");
            return;
        }
        String columnName = getParam("columnName");   //字段名称
        String columnValue = getParam("columnValue");   //字段值

        if (StringUtils.isBlank(columnName)) {
            result(RC.BUSINESS_FAIL, "参数columnName不能为空.");
            return;
        }
        //校验字段信息
        StringBuffer columnStr=new StringBuffer();
        String retMsg=getColumnStr(columnName,columnStr,columnValue);
        if("".equals(columnStr.toString())){
            result(RC.BUSINESS_FAIL, "非法参数名.");
            return;
        }
        if (StringUtils.isBlank(columnValue)) {
            result(RC.BUSINESS_FAIL, columnStr+"不能为空.");
            return;
        }
        if(StringUtils.isNotEmpty(retMsg)){
            result(RC.BUSINESS_FAIL, retMsg);
            return;
        }
        result(usersService.updateUserInfo(columnName,columnValue,columnStr.toString(),userId));
    }

    /**
     * 判断user 字段名称是否正确（需要和数据库user表里面的字段名称一致）
     * 校验参数的值是否满足条件
     * @param columnName
     * @param columnStr
     * @return
     */
    private String getColumnStr(String columnName,StringBuffer columnStr,String columnValue){
        String retMsg="";
        switch (columnName){
            case "pic":
                columnStr.append("用户头像");break;
            case "nickName":
                columnStr.append("昵称");break;
            case "realName":
                columnStr.append("真实姓名");break;
            case "sex":
                columnStr.append("性别");break;
            case "mobilePhone":
                columnStr.append("手机");
                if(!RegexUtils.checkMobile(columnValue)){
                    retMsg="请输入正确的手机号码.";
                }
                break;
            case "idcard":
                columnStr.append("证件号码");
                break;
            case "idcardImg":
                columnStr.append("证件照片");break;
            case "bank":
                columnStr.append("开户银行");break;
            case "bankch":
                columnStr.append("支行信息");break;
            case "bankNum":
                columnStr.append("银行卡号");
                if(!RegexUtils.checkBankCard(columnValue)){
                    retMsg="请输入正确的银行卡号.";
                }
                break;
            case "bankImg":
                columnStr.append("银行卡照片");break;
            case "bankcardOwner":
                columnStr.append("持卡人姓名");break;
            case "mobile":
                columnStr.append("绑定手机号");
                if(!RegexUtils.checkMobile(columnValue)){
                    retMsg="请输入正确的手机号码.";
                }
                break;
            case "country":
                columnStr.append("国籍");break;
            case "addr":
                columnStr.append("详细地址");break;
            case "email":
                columnStr.append("邮箱");
                if(!RegexUtils.checkEmail(columnValue)){
                    retMsg="请输入正确的邮箱账号.";
                }
                break;
            case "birthday":
                columnStr.append("生日");
                if(!RegexUtils.checkBirthday(columnValue)){
                    retMsg="请输入正确的日期.";
                }
                break;
            case "pro":
                columnStr.append("省");break;
            case "city":
                columnStr.append("市");break;
            case "area":
                columnStr.append("区");break;
            case "idcardIsA":
                columnStr.append("身份证是否认证");
                if(!RegexUtils.checkDigit(columnValue)){
                    retMsg="请输入正确认证值，正确的值为1或者2.";
                }
                if(!"1".equals(columnValue) || !"2".equals(columnValue)){
                    retMsg="正确的值为1或者2.";
                }
                break;
            case "bankIsA":
                columnStr.append("银行卡是否认证");
                if(!RegexUtils.checkDigit(columnValue) ){
                    retMsg="请输入正确认证值.";
                }
                if(!"1".equals(columnValue) || !"2".equals(columnValue)){
                    retMsg="正确的值为1或者2.";
                }
                break;
            default:
                columnStr.append("");
        }
        return retMsg;
    }

    /**
     * 删除银行卡信息
     */
    public void deleteBankInfo(){
        Long userId = getUserId();
        if (userId == null) {
            result(RC.REQUEST_FAIL, "用户未登录，请先登录.");
            return;
        }
        result(usersService.deleteBankInfo(userId));
    }

}
