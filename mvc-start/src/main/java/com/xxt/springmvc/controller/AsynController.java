package com.xxt.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 异步请求
 */
@Controller
public class AsynController {

    @Autowired
    private Map<String, DeferredResult<String>> asynResultMap;

    @Autowired
    private Map<String, ResponseBodyEmitter> emitterMap;

    @Autowired
    private Map<String, SseEmitter> sseEmitterMap;

    /*=======================deferred=================================================*/
    @ResponseBody
    @RequestMapping("/asyn/deferred/{id}")
    public DeferredResult<String> deferredReq(@PathVariable String id) {
        DeferredResult<String> result = new DeferredResult<>();
        asynResultMap.put(id, result);
        return result;
    }

    @ResponseBody
    @RequestMapping("/asyn/handleDeferred/{id}")
    public String handleDeferred(@PathVariable String id) {
        DeferredResult<String> result = asynResultMap.get(id);
        result.setResult("id:" + id + new String("完成".getBytes(), Charset.forName("gbk")));
        asynResultMap.remove(id);
        return "ok";
    }

    /*=======================Callable=================================================*/
    @RequestMapping("/asyn/call")
    @ResponseBody
    public Callable<String> call() {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + "调度完成");
                return "call ok";
            }
        };
    }


    /*=======================emiResponseBodyEmittertter=================================================*/
    @RequestMapping("/asyn/emitter/{id}")
    @ResponseBody
    public ResponseBodyEmitter handleEmitter(@PathVariable String id) {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        emitterMap.put(id, emitter);
        return emitter;
    }

    @RequestMapping("/asyn/emitter/{id}/push/{msg}")
    @ResponseBody
    public String pushMessage(@PathVariable String id, @PathVariable String msg) {
        ResponseBodyEmitter responseBodyEmitter = emitterMap.get(id);
        try {
            responseBodyEmitter.send(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "push ok";
    }

    @RequestMapping("/asyn/emitter/{id}/pushover")
    @ResponseBody
    public String pushOver(@PathVariable String id) {
        ResponseBodyEmitter responseBodyEmitter = emitterMap.get(id);
        responseBodyEmitter.complete();
        return "push ok";
    }


    /*=======================sse=================================================*/
    @RequestMapping("/asyn/sseEmitter/{id}")
    @ResponseBody
    public ResponseBodyEmitter sseHandleEmitter(@PathVariable String id) {
        SseEmitter sseEmitter = new SseEmitter();
        sseEmitterMap.put(id, sseEmitter);
        sseEmitter.onCompletion(() -> {
            System.out.println(id + "完成");
            sseEmitterMap.remove(sseEmitter);
        });
        return sseEmitter;
    }

    @RequestMapping("/asyn/sseEmitter/{id}/push/{msg}")
    @ResponseBody
    public String ssePushMessage(@PathVariable String id, @PathVariable String msg) {
        SseEmitter sseEmitter = sseEmitterMap.get(id);
        try {
            sseEmitter.send(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "push ok";
    }

    @RequestMapping("/asyn/sseEmitter/{id}/pushover")
    @ResponseBody
    public String ssePushOver(@PathVariable String id) {
        SseEmitter sseEmitter = sseEmitterMap.get(id);
        sseEmitter.complete();
        return "push ok";
    }

    /*=======================StreamingResponseBody =================================================*/

    /**
     * 利用StreamingResponseBody直接向response中输出流
     * @return
     */
    @GetMapping("/download")
    public ResponseEntity<StreamingResponseBody> handle() {
        StreamingResponseBody responseBody = new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream outputStream) throws IOException {
                FileInputStream fileInputStream = new FileInputStream("G:\\文档\\卢小红\\学习梳理.xlsx");
                byte[] bytes = new byte[fileInputStream.available()];
                fileInputStream.read(bytes);
                fileInputStream.close();
                outputStream.write(bytes);
            }
        };
        return ResponseEntity.ok().
                contentType(MediaType.parseMediaType("application/vnd.ms-excel")).
                header("Content-Disposition", "attachment;filename = test.xlsx").
                body(responseBody);

    }
}
