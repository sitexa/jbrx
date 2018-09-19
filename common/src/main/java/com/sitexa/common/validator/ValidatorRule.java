package com.sitexa.common.validator;

import java.util.ArrayList;
import java.util.List;

/**
 * ------------------------------
 * 验证数据规则
 * ------------------------------
 */
public class ValidatorRule {

	private List<VGroup> vGroups;
	private List<VType> vTypes;
	private String validatorField;
	private String message;
	
	/**
	 * 构建一个数据验证规则
	 * @param validatorField
	 * @param message
	 * @param vGroups
	 * @param vTypes
	 * @return
	 */
	public static ValidatorRule bulid(String validatorField,String message,List<VGroup> vGroups,List<VType> vTypes){
		return new ValidatorRule(vGroups,vTypes,validatorField,message);
	}
	
	/**
	 * 都需要验证的数据
	 * 如：save和update操作时都需要验证这个参数
	 * @param validatorGroups
	 * @return
	 */
	public static List<VGroup> group(VGroup... validatorGroups){
		List<VGroup> list=new ArrayList<VGroup>();
		for (int i = 0; i < validatorGroups.length; i++) {
			list.add(validatorGroups[i]);
		}
		return list;
	}
	
	/**
	 * 数据的验证类型
	 * @param validatorTypes
	 * @return
	 */
	public static List<VType> type(VType... validatorTypes){
		List<VType> list=new ArrayList<VType>();
		for (int i = 0; i < validatorTypes.length; i++) {
			list.add(validatorTypes[i]);
		}
		return list;
	}
	
	private ValidatorRule(){}
	private ValidatorRule(List<VGroup> vGroups, List<VType> vTypes, String validatorField,
			String message) {
		super();
		this.vGroups = vGroups;
		this.vTypes = vTypes;
		this.validatorField = validatorField;
		this.message = message;
	}

	public List<VGroup> getValidatorGroups() {
		return vGroups;
	}
	public void setValidatorGroups(List<VGroup> vGroups) {
		this.vGroups = vGroups;
	}
	public List<VType> getValidatorTypes() {
		return vTypes;
	}
	public void setValidatorTypes(List<VType> vTypes) {
		this.vTypes = vTypes;
	}
	public String getValidatorField() {
		return validatorField;
	}
	public void setValidatorField(String validatorField) {
		this.validatorField = validatorField;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
