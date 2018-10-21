package com.merit.utils;

import com.merit.model.ResponseMessage;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * <p>方法 JsonUtil : <p>
 * <p>说明:前台界面通用JSON工具转换类</p>
 * <pre>
 * @author JackZhou
 * @date 2017/4/12 22:35
 * </pre>
 */
public class JsonUtil {
    /**
     * 对象转换成JSON字符串
     *
     * @param obj 需要转换的对象
     * @return 对象的string字符
     */
    public static String toJson(Object obj) {
        JSONObject jSONObject = JSONObject.fromObject(obj);
        return jSONObject.toString();
    }

    public static String toJson(Object[] obj) {
        JSONArray jSONObject = JSONArray.fromObject(obj);
        return jSONObject.toString();
    }

    /**
     * JSON字符串转换成对象
     *
     * @param jsonString 需要转换的字符串
     * @param type       需要转换的对象类型
     * @return 对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String jsonString, Class<T> type) {
        if (TextUtils.isEmpty(jsonString))
            return null;
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        return (T) JSONObject.toBean(jsonObject, type);
    }


    /***
     * JSON 转换为 List
     * @param jsonStr
     *         [{"age":12,"createTime":null,"id":"","name":"wxw","registerTime":null,"sex":1},{...}]
     * @param objectClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> json2List(String jsonStr, Class<T> objectClass){
        if (StringUtils.isNotBlank(jsonStr)) {
            JSONArray jsonArray = JSONArray.fromObject(jsonStr);
            List<T> list = (List<T>) JSONArray.toCollection(jsonArray, objectClass);
            return list;
        }
        return null;
    }

    /**
     * <p>方法:fromJson 把morphdynabean转为具体的vo对象 </p>
     * <ul>
     * <li> @param bean json的bean</li>
     * <li> @param type 对象</li>
     * <li>@return T  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/2 22:17  </li>
     * </ul>
     */
    public static <T> T fromJson(MorphDynaBean bean, Class<T> type) {
        Set<String> propsName = BeanUtil.getPropertiesName(type);
        try {
            T t = type.newInstance();
            for (String s : propsName) {
                if (null != bean.get(s))
                    BeanUtil.setProperty(t, s, bean.get(s));
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> jsonToList(String jsonStr, Class<T> type) {
        List<T> list = new ArrayList<T>();
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        for (Object obj : jsonArr) {
            if (obj instanceof JSONObject) {
                JSONObject _obj = (JSONObject) obj;
                list.add((T) JSONObject.toBean(_obj, type));
            }
        }
        return list;
    }

    /**
     * 将JSONArray对象转换成list集合
     *
     * @param jsonArr
     * @return
     */
    public static List<Object> jsonToList(JSONArray jsonArr) {
        List<Object> list = new ArrayList<Object>();
        for (Object obj : jsonArr) {
            if (obj instanceof JSONArray) {
                list.add(jsonToList((JSONArray) obj));
            } else if (obj instanceof JSONObject) {
                list.add(jsonToMap((JSONObject) obj));
            } else {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * 将json字符串转换成map对象
     *
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMap(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        return jsonToMap(obj);
    }

    /**
     * 将JSONObject转换成map对象
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> jsonToMap(JSONObject obj) {
        Set<?> set = obj.keySet();
        Map<String, Object> map = new HashMap<String, Object>(set.size());
        for (Object key : obj.keySet()) {
            Object value = obj.get(key);
            if (value instanceof JSONArray) {
                map.put(key.toString(), jsonToList((JSONArray) value));
            } else if (value instanceof JSONObject) {
                map.put(key.toString(), jsonToMap((JSONObject) value));
            } else {
                map.put(key.toString(), obj.get(key));
            }
        }
        return map;
    }

    /**
     * <p>方法:getResponse 接口返回responsemessage对象 </p>
     * <ul>
     * <li> @param result json返回数据</li>
     * <li>@return com.ourway.base.zk.models.ResponseMessage  </li>
     * <li>@author JackZhou </li>
     * <li>@date 2017/5/3 0:09  </li>
     * </ul>
     */
    public static ResponseMessage getResponseMsg(String result) {
        if (TextUtils.isEmpty(result))
            return null;
        JSONObject jsonobject = JSONObject.fromObject(result);
        ResponseMessage mess = new ResponseMessage();
        if (jsonobject.has("backCode"))
            mess.setBackCode(new Integer(jsonobject.get("backCode").toString()));
        if (jsonobject.has("errorMess"))
            mess.setErrorMess(jsonobject.get("errorMess").toString());
        Object value = jsonobject.get("bean");
        if (value instanceof JSONArray) {
            mess.setBean(jsonToList((JSONArray) value));
        } else if (value instanceof JSONObject) {
            mess.setBean(jsonToMap((JSONObject) value));
        } else {
            mess.setBean(value);
        }
        return mess;

    }


}
