package com.shui.shop.utils;

import java.util.UUID;

public class UUIDUtils {
	
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
