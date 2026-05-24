package com.ra.ptit_cntt2_it211_ss8_ex5.aspect;

import com.ra.ptit_cntt2_it211_ss8_ex5.exception.MarketClosedException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.time.LocalTime;

@Aspect
@Component
@Order(2) // Chạy thứ hai
public class TradingTimeAspect {
    @Around("execution(* com.ra.ptit_cntt2_it211_ss8_ex5.service.PlaceOrderService.placeOrder(..))")
    public Object checkTradingTime(ProceedingJoinPoint joinPoint) throws Throwable {
        LocalTime now = LocalTime.now();
        LocalTime openTime = LocalTime.of(9, 0);
        LocalTime closeTime = LocalTime.of(15, 0);

        if (now.isBefore(openTime) || now.isAfter(closeTime)) {
            throw new MarketClosedException("Đặt lệnh thất bại: Sàn chứng khoán hiện đã đóng cửa (Thời gian giao dịch: 09:00 - 15:00)!");
        }
        
        System.out.println("[HỆ THỐNG ĐẶT LỆNH] VÒNG 2: Khung giờ giao dịch hợp lệ.");
        return joinPoint.proceed();
    }
}
