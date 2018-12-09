package com.elec.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CompareObject {
	
	public static <T> Map<String, String> compare(T obj1, T Obj2) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		Field[] fs = obj1.getClass().getDeclaredFields();
		for (Field f : fs) {
			f.setAccessible(true);
			Object v1 = f.get(obj1);
			Object v2 = f.get(Obj2);
			if (!equals(v1, v2)) {
				result.put(f.getName(), String.valueOf(equals(v1, v2)));

			}
		}
		return result;
	}

	public static boolean equals(Object obj1, Object obj2) {

		if (obj1 == obj2) {
			return true;
		}
		if (obj1 == null || obj2 == null) {
			return false;
		}
		return obj1.equals(obj2);
	}
}
