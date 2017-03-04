package com.cong.shop.util;

import java.util.UUID;

/**
 * @author Administrator
 * 生成随机字符串的工具类
 */
public class UUIDUtils {
	
	/**
	 * 获得随机字符串
	 * @return uuid
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
