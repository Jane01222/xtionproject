package com.xuanwu.xtion.utils;

import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {

	public static long toAscii(String str) {
		
		if(isNotBlank(str)) {
			StringBuilder builder = new StringBuilder();
			char[] cs = str.toCharArray();
			for (char c : cs) {
				builder.append((int)c);
			}
			return Long.parseLong(builder.toString());
		}
		
		return 0;
	}
	
}
