package com.cong.shop.util;

import java.util.UUID;

/**
 * @author Administrator
 * ��������ַ����Ĺ�����
 */
public class UUIDUtils {
	
	/**
	 * �������ַ���
	 * @return uuid
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
