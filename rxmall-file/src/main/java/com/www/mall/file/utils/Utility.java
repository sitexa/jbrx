package com.www.mall.file.utils;

import java.io.InputStream;
import java.io.OutputStream;

public class Utility {
	final public static void closeStream(InputStream stream){
		if(stream!=null){
			try{
				stream.close();
			}catch(Exception e){
			}
		}
	}
	
	final public static void closeStream(OutputStream stream){
		if(stream!=null){
			try{
				stream.close();
			}catch(Exception e){
			}
		}
	}
}
