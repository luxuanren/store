package com.store.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartUtil {
	/**
	 * translate cart list string to list
	 * @param data the string of cartList which is get form database
	 * @return
	 */
	public static List<Integer> getList(String data) {
		List<Integer> list = new ArrayList<Integer>();
		String regex = "(\\d+):(\\d+)";
		Matcher matcher = Pattern.compile(regex).matcher(data);
		while (matcher.find()) {
			list.add(Integer.valueOf(matcher.group(1)));
		}
		return list;
	}
}
