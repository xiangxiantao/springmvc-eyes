package com.xxt.springmvc.controller;

import com.xxt.springmvc.event.PayCallbackEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

/**
 * 实现一个异步回调的支付接口
 */
@Controller
public class PaymentController implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @Autowired
    Map<String, SseEmitter> paySseEmitterMap;

    /**
     * 客户端调用支付
     *
     * @param id
     * @return
     */
    @RequestMapping("/payment/{id}")
    @ResponseBody
    public SseEmitter payment(@PathVariable String id) {
        //SseEmitter sseEmitter = new SseEmitter(3000L);
        SseEmitter sseEmitter = new SseEmitter();
        paySseEmitterMap.put(id, sseEmitter);
        //同时将该支付订单发送给微信
        //sendToWechant()
        return sseEmitter;

    }

    /**
     * 微信完成支付之后回调我们的接口通知我们
     * 我们通过事件机制将该支付完成事件发送给回调监听器
     * 监听器处理事件之后给正在等待的客户端请求发送结果
     *
     * @param id
     * @return
     */
    @RequestMapping("/wx/callback/{id}")
    @ResponseBody
    public String WechantCallback(@PathVariable String id) {
        SseEmitter sseEmitter = paySseEmitterMap.get(id);
        //发送通知给支付回调监听器
        PayCallbackEvent payCallbackEvent = new PayCallbackEvent(this, id, sseEmitter);
        publisher.publishEvent(payCallbackEvent);
        paySseEmitterMap.remove(id);
        return "weChant notify ok";
    }


    @EventListener
    public void onApplicationEvent(PayCallbackEvent event) {
        System.out.println("收到weChant支付处理完成回调通知事件：" + event.getPayId());
        SseEmitter sseEmitter = event.getSseEmitter();
        try {
            sseEmitter.send(event.getPayId() + "pay complate");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sseEmitter.complete();
    }
}
