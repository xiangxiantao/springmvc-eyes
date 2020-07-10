package com.xxt.springmvc.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class PayCallbackEvent extends ApplicationEvent {

    private final String payId;

    private final SseEmitter sseEmitter;

    public String getPayId() {
        return payId;
    }

    public SseEmitter getSseEmitter() {
        return sseEmitter;
    }

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public PayCallbackEvent(Object source, String payId, SseEmitter sseEmitter) {
        super(source);
        this.sseEmitter = sseEmitter;
        this.payId = payId;
    }
}
