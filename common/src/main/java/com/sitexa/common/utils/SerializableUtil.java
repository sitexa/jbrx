package com.sitexa.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * ------------------------------
 *  obj序列化工具
 * ------------------------------
 */
public class SerializableUtil {

    public static String serialize(Object obj) {
        if (null == obj) {
            return null;
        }
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            return Base64.encodeBase64String(bos.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("serialize obj error", e);
        }
    }
    public static Object deserialize(String objStr) {
        if (StringUtils.isBlank(objStr)) {
            return null;
        }
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decodeBase64(objStr));
            ObjectInputStream ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException("deserialize obj error", e);
        }
    }
    
}
