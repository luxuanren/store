package com.store.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkUtil {
	public static List<Integer> getMarkList(String data) {
		List<Integer> list = new ArrayList<>();
		Matcher matcher = Pattern.compile("\\d+").matcher(data);
		
		while (matcher.find()){
			list.add(Integer.valueOf(matcher.group()));
		}
		return list;
	}
}
