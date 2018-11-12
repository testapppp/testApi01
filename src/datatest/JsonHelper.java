package datatest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonHelper.class);

	private static final ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
		// 去掉默认的时间戳格式
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		// 设置为中国上海时区
		objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		// 设置输入:禁止把POJO中值为null的字段映射到json字符串中
		objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		// 空值不序列化
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		// 反序列化时，属性不存在的兼容处理
		objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// 序列化时，日期的统一格式
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 单引号处理
		objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
	}

	public static <T> T toObject(String json, Class<T> clazz) {
		if (json == null || json.length() == 0)
			return null;

		try {
			return objectMapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> String toJson(T entity) {
		if (entity == null)
			return null;

		try {
			return objectMapper.writeValueAsString(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> toMap(String json) {
		if (json == null || json.length() == 0)
			return new HashMap<K, V>();

		try {
			return objectMapper.readValue(json, Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new HashMap<K, V>();
	}

	public static <K, V> String toJson(Map<K, V> obj) {
		if (obj == null || obj.isEmpty())
			return "";

		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

	@SuppressWarnings("rawtypes")
	public static <T> T toObject(Map map, Class<T> clazz) {
		if (map == null || map.isEmpty())
			return null;

		return objectMapper.convertValue(map, clazz);
	}

	/**
	 * 将json array 字符串 转换为指定的集合类型
	 * @param jsonArrayStr - String
	 * @param clazz - Class<T>
	 * @return List<T>
	 */
	public static <T> List<T> toList(String jsonArrayStr, Class<T> clazz) {
		List<T> result = new ArrayList<T>();

		if (jsonArrayStr != null && jsonArrayStr.length() > 0) {
			try {
				List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {});

				for (Map<String, Object> map : list) {
					result.add(toObject(map, clazz));
				}

				return result;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	/**
	 * 将JSONArray转换为指定类型的集合
	 * @param array - JSONArray
	 * @param clazz - Class<T>
	 * @return List<T>
	 */
	public static <T> List<T> toList(JSONArray array , Class<T> clazz) {
		if(array == null || array.isEmpty()) return null;
		
		try {
			List<T> result = new ArrayList<T>(array.size());
			for(int i=0,size=array.size();i<size;i++) {
				result.add(array.getObject(i, clazz));
			}
			
			return result;
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		
		return null;
	}

	/**
	 * 将字符串转换成FastJson对象
	 * 
	 * @param json
	 *            - String
	 * @return JSONObject
	 */
	public static JSONObject toJsonObject(String json) {
		if (json == null || json.length() == 0)
			return new JSONObject(0);
		try {
			return JSONObject.parseObject(json);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return new JSONObject(0);
	}

	/**
	 * 将字符串转换成FastJson对象
	 * 
	 * @param json
	 *            - String
	 * @return JSONArray
	 */
	public static JSONArray toJsonArray(String json) {
		if (json == null || json.length() == 0)
			return new JSONArray(0);
		try {
			return JSONObject.parseArray(json);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return new JSONArray(0);
	}

}
