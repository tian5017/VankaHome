package com.tianyulin.vankahome.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 * Json工具类
 */
public final class JsonUtil {
    /**
     * 把Java对象序列化成json字符串
     * @param obj 值对象
     * @return json字符串
     */
    public static String writeObject2JsonString(final Object obj) {
        final ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 把Java对象序列化输出到文件
     * @param resultFile son内容文件
     * @param value 值对象
     */
    public static void writeObject2File(File resultFile, final Object value) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(resultFile, value);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 把json字符串转换成指定的对象
     * @param json json字符串
     * @param valueType 对象Class类型
     * @return 值对象
     */
    public static <T> T readJsonString2Object(final String json, final Class<T> valueType) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, valueType);
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把json字符串转换成指定的对象
     *
     * @param json json字符串
     * @param valueTypeRef 对象类型参数
     * @return 值对象
     */
    public static <T> T readJsonString2Object(final String json, final TypeReference<T> valueTypeRef) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, valueTypeRef);
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 把json数据文件转换成指定类型对象
     *
     * @param jsonFile json数据文件
     * @param valueTypeRef 对象类型参数
     * @return 值对象
     */
    public static <T> T readJsonFile2Object(final File jsonFile, final TypeReference<T> valueTypeRef) {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonFile, valueTypeRef);
        } catch (JsonParseException e) {
            e.printStackTrace();
            return null;
        } catch (JsonMappingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
