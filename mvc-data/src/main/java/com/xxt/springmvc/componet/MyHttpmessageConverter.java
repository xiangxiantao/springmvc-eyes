package com.xxt.springmvc.componet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.xxt.springmvc.entity.Result;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.List;


public class MyHttpmessageConverter implements HttpMessageConverter {

    private static Gson gson;

    public MyHttpmessageConverter() {
        System.out.println("MyHttpmessageConverter init......");
        gson = new GsonBuilder()
                .serializeNulls()
                .enableComplexMapKeySerialization()
                .registerTypeAdapterFactory(new EmptyStringAdapterFactory())
                .setDateFormat("yyyy-MM-dd")
                .create();
    }

    @Override
    public boolean canRead(Class aClass, MediaType mediaType) {
        return true;
    }

    @Override
    public boolean canWrite(Class aClass, MediaType mediaType) {
        return true;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(MediaType.ALL);
    }

    @Override
    public Object read(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        System.out.println("MyHttpmessageConverter reading......");
        InputStream inputStream = httpInputMessage.getBody();
        InputStreamReader reader = new InputStreamReader(inputStream);
        char[] chars = new char[inputStream.available()/4];
        reader.read(chars);
        String s =new String(chars);
        return gson.fromJson(s,aClass);
    }

    @Override
    public void write(Object o, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        System.out.println("MyHttpmessageConverter writing......");
        OutputStream body = httpOutputMessage.getBody();
        if (o instanceof Result){
            body.write(gson.toJson(o).getBytes("utf-8"));
        }else {
            String jsonResult = gson.toJson(new Result(o));
            body.write(jsonResult.getBytes("utf-8"));
        }

    }

    public class EmptyStringAdapterFactory<T> implements TypeAdapterFactory {

        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>)type.getRawType();
            if (rawType == Long.class || rawType == long.class) {
                return (TypeAdapter<T>)new LongAdapter();
            } else if (rawType == Integer.class || rawType == int.class) {
                return (TypeAdapter<T>)new IntegerAdapter();
            }
            return null;
        }
    }

    public class LongAdapter extends TypeAdapter<Long> {

        @Override
        public Long read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return null;
            }
            String nextStr = reader.nextString();
            if (StringUtils.isEmpty(nextStr)) {
                //仅处理空白字符串
                return null;
            }
            //其他字符串如果转换失败仍然报错
            return Long.parseLong(nextStr);
        }

        @Override
        public void write(JsonWriter writer, Long value) throws IOException {
            writer.value(value);
        }
    }

    public class IntegerAdapter extends TypeAdapter<Integer> {

        @Override
        public Integer read(JsonReader reader) throws IOException {
            if (reader.peek() == JsonToken.NULL) {
                reader.nextNull();
                return null;
            }
            String nextStr = reader.nextString();
            if (StringUtils.isEmpty(nextStr)) {
                //仅处理空白字符串
                return null;
            }
            //其他字符串如果转换失败仍然报错
            return Integer.parseInt(nextStr);
        }

        @Override
        public void write(JsonWriter writer, Integer value) throws IOException {
            writer.value(value);
        }
    }
}
