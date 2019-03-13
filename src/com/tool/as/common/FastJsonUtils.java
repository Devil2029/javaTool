package com.tool.as.common;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Json相关util工具类
 */
public class FastJsonUtils {

	/**
	 * 默认的日期格式
	 */
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 字符串转化成对象
	 * 
	 * @param str
	 * @param type
	 * @return
	 * @return T
	 * @throws
	 */
	public static <T> T parseObject(String str, Class<T> type) {
		if (StrUtil.isEmpty(str)) {
			return null;
		}
		return JSON.parseObject(str, type);
	}

	/**
	 * 字符串转化成集合
	 * 
	 * @param str
	 * @param type
	 * @return
	 * @return List<T>
	 * @throws
	 */
	public static <T> List<T> parseArray(String str, Class<T> type) {
		return JSONArray.parseArray(str, type);
	}

	/**
	 * 对象序列化
	 * 
	 * @param obj
	 * @return
	 * @return String
	 * @throws
	 */
	public static String toJSONString(Object obj) {
		try {
			return JSON.toJSONString(obj,
					SerializerFeature.WriteNullStringAsEmpty,
					SerializerFeature.WriteNullListAsEmpty,
					SerializerFeature.WriteDateUseDateFormat,
					SerializerFeature.DisableCircularReferenceDetect);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 对象序列化-默认时间格式
	 * 
	 * @param obj
	 * @return
	 * @return String
	 * @throws
	 */
	public static String toJSONStringWithDateFormat(Object obj) {
		try {
			return JSON.toJSONStringWithDateFormat(obj, DEFAULT_DATE_PATTERN,
					SerializerFeature.WriteNullStringAsEmpty,
					SerializerFeature.WriteNullListAsEmpty,
					SerializerFeature.DisableCircularReferenceDetect);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 对象序列化-自定义时间格式
	 * 
	 * @param obj
	 * @param dateFormat
	 * @return
	 * @return String
	 * @throws
	 */
	public static String toJSONStringWithDateFormat(Object obj,
			String dateFormat) {
		try {
			return JSON.toJSONStringWithDateFormat(obj, dateFormat,
					SerializerFeature.WriteNullStringAsEmpty,
					SerializerFeature.DisableCircularReferenceDetect);
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", 1);
		map.put("b", new Date());
		System.out.println(toJSONStringWithDateFormat(map, "yyyy-MM-dd"));
	}
}
