
package com.sitexa.common.utils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;


/**
 * @ClassName: BlankUtil
 * @Description:逻辑、功能相关描述:验证对象空值，字符串零长，集合无元素
 */
public class BlankUtil {
    /**
     * Function:判断字符串是否为空或零长
     *
     * @param str 待检查的字符串变量
     * @return true  字符串为空或零长
     * false 字符串不为空且不是零长字符串
     */
    public static boolean isBlank(final String str) {
        return (str == null) || (str.trim().length() <= 0 || ("null").equals(str.toLowerCase()));
    }

    /**
     * Function:判断字符是否为空或空格
     *
     * @param cha 待检查的字符变量
     * @return true  字符为空或空格
     * false 字符不为空且不是空格
     */
    public static boolean isBlank(final Character cha) {
        return (cha == null) || cha.equals(' ');
    }

    /**
     * Function:判断对象是否为空
     *
     * @param obj 待检查的对象变量
     * @return true  对象为空
     * false 对象不为空
     */
    public static boolean isBlank(final Object obj) {
        return (obj == null);
    }

    /**
     * Function:判断对象数组是否为空或无元素
     *
     * @param objs 待检查的对象数组变量
     * @return true  对象数组为空或无元素
     * false 对象数组不为空且有元素
     */
    public static boolean isBlank(final Object[] objs) {
        return (objs == null) || (objs.length <= 0);
    }

    /**
     * Function:判断集合对象是否为空或无元素
     *
     * @param obj 待检查的集合对象变量
     * @return true  集合对象为空或无元素
     * false 集合对象不为空且有元素
     */
    public static boolean isBlank(final Collection<?> obj) {
        return (obj == null) || obj.isEmpty() || (obj.size() <= 0);
    }

    /**
     * Function:判断Set对象是否为空或无元素
     *
     * @param obj 待检查的Set对象变量
     * @return true  Set对象为空或无元素
     * false Set对象不为空且有元素
     */
    public static boolean isBlank(final Set<?> obj) {
        return (obj == null) || (obj.size() <= 0);
    }

    /**
     * Function:判断Serializable对象是否为空
     *
     * @param obj 待检查的Serializable对象变量
     * @return true  Serializable对象为空
     * false Serializable对象不为空
     */
    public static boolean isBlank(final Serializable obj) {
        return obj == null;
    }

    /**
     * Function:判断Map对象是否为空或无元素
     *
     * @param obj 待检查的Map对象变量
     * @return true  Map对象为空或无元素
     * false Map对象不为空且有元素APIData
     */
//    public static boolean isBlank(final Map<?, ?> obj) {
//        return (obj == null) || obj.isEmpty() || (obj.size() <= 0);
//    }
    public static boolean isBlank(final APIData obj) {
        return (obj == null) || obj.isEmpty() || (obj.size() <= 0);
    }

    public static boolean isNotBlank(final APIData obj) {
        return !isBlank(obj);
    }

    /**
     * Function:判断字符串是否为空或零长
     *
     * @param str 待检查的字符串变量
     * @return true  字符串不为空且不是零长字符串
     * false 字符串为空或零长
     */
    public static boolean isNotBlank(final String str) {
        return !isBlank(str);
    }

    /**
     * Function:判断字符是否为空或空格
     *
     * @param cha 待检查的字符变量
     * @return true  字符不为空且不是空格
     * false 字符为空或空格
     */
    public static boolean isNotBlank(final Character cha) {
        return !isBlank(cha);
    }

    /**
     * Function:判断对象是否为空
     *
     * @param obj 待检查的对象变量
     * @return true  对象不为空
     * false 对象为空
     */
    public static boolean isNotBlank(final Object obj) {
        return !isBlank(obj);
    }

    /**
     * Function:判断对象数组是否为空或无元素
     *
     * @param objs 待检查的对象数组变量
     * @return true  对象数组不为空且有元素
     * false 对象数组为空或无元素
     */
    public static boolean isNotBlank(final Object[] objs) {
        return !isBlank(objs);
    }

    /**
     * Function:判断集合对象是否为空或无元素
     *
     * @param obj 待检查的集合对象变量
     * @return true  集合对象不为空且有元素
     * false 集合对象为空或无元素
     */
    public static boolean isNotBlank(final Collection<?> obj) {
        return !isBlank(obj);
    }

    /**
     * Function:判断Set对象是否为空或无元素
     *
     * @param obj 待检查的Set对象变量
     * @return true  Set对象不为空且有元素
     * false Set对象为空或无元素
     */
    public static boolean isNotBlank(final Set<?> obj) {
        return !isBlank(obj);
    }

    /**
     * Function:判断Serializable对象是否为空
     *
     * @param obj 待检查的Serializable对象变量
     * @return true  Serializable对象不为空
     * false Serializable对象为空
     */
    public static boolean isNotBlank(final Serializable obj) {
        return !isBlank(obj);
    }

    /**
     * Function:判断Map对象是否为空或无元素
     * @param obj 待检查的Map对象变量
     * @return true  Map对象空且有元素
     *         false Map对象为空或无元素
     */
//    public static boolean isNotBlank(final Map<?, ?> obj) {
//        return !isBlank(obj);
//    }
}
