package com.lantin.common.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * @author Lantin
 */
public class JsonUtils {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        // Date类型格式化
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // java8 新的时间类 格式化
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        //  注意 同一个时间类型不能使用两个格式化格式 后一个会覆盖前一个
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateUtils.FORMATTER_DATE_TIME));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateUtils.FORMATTER_DATE_TIME));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateUtils.FORMATTER_DATE));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateUtils.FORMATTER_DATE));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateUtils.FORMATTER_TIME));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateUtils.FORMATTER_TIME));
        //  自己写的时间模块注册进ObjectMapper
        mapper.registerModule(javaTimeModule);
    }


    public static String fastObj2json(Object obj)  {
        return JSON.toJSONString(obj);
    }

    public static <T> T fastJson2obj(String jsonStr, Class<T> clazz) throws Exception {
        return JSON.parseObject(jsonStr, clazz);
    }

    public static Map<String, Object> fastJson2map(String jsonStr) {
        return JSON.parseObject(jsonStr, Map.class);
    }

    public static <T> T fastMap2obj(Map<?, ?> map, Class<T> clazz) throws Exception {
        return JSON.parseObject(JSON.toJSONString(map), clazz);
    }

    public static String jacksonObj2json(Object obj) {
        String result = null;
        if (obj != null) {
            try {
                result = mapper.writeValueAsString(obj);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
        return result;
    }


    public static <T> T jacksonParseJson(String jsonStr, Class<T> clazz) {
        if (!StringUtils.hasText(jsonStr)) {
            return null;
        }
        T result;
        try {
            result = mapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return result;
    }

    public static <T> List<T> jacksonParseJsonArray(String jsonStr, Class<T> clazz) {
        if (!StringUtils.hasText(jsonStr)) {
            return new ArrayList<>();
        }

        List<T> result = new ArrayList<>();
        TypeFactory typeFactory = TypeFactory.defaultInstance();
        try {
            result = mapper.readValue(jsonStr, typeFactory.constructCollectionType(List.class, clazz));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    static String filePathDir = "D:\\data\\tmp\\export\\";


    public static void main(String[] args) {
        String fileName = "礼包导出23.txt";

        String absPath = filePathDir + fileName;

        File file = new File(absPath);
        System.out.println(file.exists());
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            }
        }
        File parentDir = file.getParentFile();
        String absolutePath = parentDir.getAbsolutePath();
        System.out.println(absolutePath);
        if (!parentDir.exists()) {
            boolean mkdirs = parentDir.mkdirs();
        }
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add(String.valueOf(i + 1));
        }
        ListIterator<String> stringListIterator = list.listIterator();
        StringBuilder sb = new StringBuilder();
        while (stringListIterator.hasNext()) {
            String next = stringListIterator.next();
            int i = stringListIterator.nextIndex();
            System.out.println("next index is" + i + "and next element is" + next);
            sb.append(next);
            if (i < list.size()) {
                sb.append("\r\n");
            }
        }

        String str = sb.toString();
        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
