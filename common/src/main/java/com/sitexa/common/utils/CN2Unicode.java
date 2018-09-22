package com.sitexa.common.utils;

public class CN2Unicode {   
//	public static void main(String[] args) throws IOException { 
//		String strf = MessageFormat.format("我是{0},我来自{1},今年{2}岁", "中国人", "北京", "22");
//
//		List<String[]> list=CSVUtils.readCsv("C:\\i18n_field_zh_CN.properties", "UTF-8");
//		StringBuffer sb=new StringBuffer();
//		for (int i = 0; i < list.size(); i++) {
//			String string=list.get(i)[0];
//			if("".equals(string) || string.contains("\\u")|| string.startsWith("#")){
//				sb.append(string+"\n");
//				continue;
//			}
//			String[] kv=string.split("=");
//			boolean flag=isChinese(kv[1]);
//			if(flag){
//				String unicode=enUnicode(kv[1]);
//				string=kv[0]+"="+unicode;
////				System.out.println(string);
//			}
//			sb.append(string+"\n");
//				
//		}
//		System.out.println(sb);
//	
//	}
	
	public static String enUnicode(final String gbString) {   
		char[] utfBytes = gbString.toCharArray();   
		String unicodeBytes = "";   
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {   
			String hexB = Integer.toHexString(utfBytes[byteIndex]);   
			if (hexB.length() <= 2) {   
				hexB = "00" + hexB;   
			}   
			unicodeBytes = unicodeBytes + "\\u" + hexB;   
		}   
		return unicodeBytes;
	}   
	
	public static String deUnicode1(final String dataStr) {   
		int start = 0;   
		int end = 0;   
		final StringBuffer buffer = new StringBuffer();   
		while (start > -1) {   
			end = dataStr.indexOf("\\u", start + 2);   
			String charStr = "";   
			if (end == -1) {   
				charStr = dataStr.substring(start + 2, dataStr.length());   
			} else {   
				charStr = dataStr.substring(start + 2, end);   
			}   
			char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。   
			buffer.append(new Character(letter).toString());   
			start = end;   
		}   
		return buffer.toString();   
	}  
	
	/**
	 * 位运算转化 效率高
	 * @param theString
	 * @return
	 */
	public static String deUnicode(String theString) {      
		char aChar;      
		int len = theString.length();      
		StringBuffer outBuffer = new StringBuffer(len);      
		
		for (int x = 0; x < len;) {      
			aChar = theString.charAt(x++);      
			if (aChar == '\\') {      
				aChar = theString.charAt(x++);      
				if (aChar == 'u') {      
					// Read the xxxx      
					int value = 0;      
					for (int i = 0; i < 4; i++) {      
						aChar = theString.charAt(x++);      
						
						switch (aChar) {      
						case '0':      
						case '1':      
						case '2':      
						case '3':      
						case '4':      
						case '5':      
						case '6':      
						case '7':      
						case '8':      
						case '9':      
							value = (value << 4) + aChar - '0';      
							break;      
						case 'a':      
						case 'b':      
						case 'c':      
						case 'd':      
						case 'e':      
						case 'f':      
							value = (value << 4) + 10 + aChar - 'a';      
							break;      
						case 'A':      
						case 'B':      
						case 'C':      
						case 'D':      
						case 'E':      
						case 'F':      
							value = (value << 4) + 10 + aChar - 'A';      
							break;      
						default:      
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");      
						}      
					}      
					outBuffer.append((char) value);      
				} else {      
					if (aChar == 't')      
						aChar = '\t';      
					else if (aChar == 'r')      
						aChar = '\r';      
					else if (aChar == 'n')      
						aChar = '\n';      
					else if (aChar == 'f')      
						aChar = '\f';      
					outBuffer.append(aChar);      
				}      
			} else     
				outBuffer.append(aChar);      
		}      
		return outBuffer.toString();      
	} 
	
	 private static boolean isChinese(char c) {
	        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
	        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
	                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
	                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
	                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
	            return true;
	        }
	        return false;
	    }
	 
	    // 完整的判断中文汉字和符号
	    public static boolean isChinese(String strName) {
	        char[] ch = strName.toCharArray();
	        for (int i = 0; i < ch.length; i++) {
	            char c = ch[i];
	            if (isChinese(c)) {
	                return true;
	            }
	        }
	        return false;
	    }
	 
 }   