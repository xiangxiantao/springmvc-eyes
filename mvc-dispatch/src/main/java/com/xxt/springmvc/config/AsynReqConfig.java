package com.xxt.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 异步请求的配置
 */
@Configuration
public class AsynReqConfig {

    @Bean("asynResultMap")
    public Map<String, DeferredResult<String>> asynResultMap() {
        Map<String, DeferredResult<String>> resultMap = new ConcurrentHashMap<>();
        return resultMap;
    }

    @Bean("emitterMap")
    public Map<String, ResponseBodyEmitter> responseBodyEmitterMap() {
        Map<String, ResponseBodyEmitter> emitterMap = new ConcurrentHashMap<>();
        return emitterMap;
    }

    @Bean("sseEmitterMap")
    public Map<String, SseEmitter> sseEmitterMap() {
        Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();
        return sseEmitterMap;
    }

    @Bean("paySseEmitterMap")
    public Map<String, SseEmitter> paySseEmitterMap() {
        Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();
        return sseEmitterMap;
    }

}
