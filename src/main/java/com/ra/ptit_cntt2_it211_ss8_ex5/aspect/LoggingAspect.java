package com.ra.ptit_cntt2_it211_ss8_ex5.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1) // Chạy đầu tiên
@Slf4j
public class LoggingAspect {
    @Around("execution(* com.ra.ptit_cntt2_it211_ss8_ex5.service.PlaceOrderService.placeOrder(..))")
    public Object logIncomingOrder(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[HỆ THỐNG ĐẶT LỆNH] VÒNG 1: Nhận request đặt lệnh thô từ Gateway. Đang chuẩn bị chuyển tiếp...");
        return joinPoint.proceed();
    }
}
