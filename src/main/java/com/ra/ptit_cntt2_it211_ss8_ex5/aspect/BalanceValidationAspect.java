package com.ra.ptit_cntt2_it211_ss8_ex5.aspect;

import com.ra.ptit_cntt2_it211_ss8_ex5.entity.AccountBalance;
import com.ra.ptit_cntt2_it211_ss8_ex5.exception.InsufficientFundsException;
import com.ra.ptit_cntt2_it211_ss8_ex5.repository.AccountBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3) 
@RequiredArgsConstructor
public class BalanceValidationAspect {

    private final AccountBalanceRepository balanceRepository;

    @Around("execution(* com.ra.ptit_cntt2_it211_ss8_ex5.service.PlaceOrderService.placeOrder(..)) && args(username, stockCode, quantity, price, orderType)")
    public Object validateBalance(ProceedingJoinPoint joinPoint, String username, String stockCode, Integer quantity, Double price, String orderType) throws Throwable {
        
        if ("BUY".equalsIgnoreCase(orderType)) {
            double totalCost = quantity * price;
            AccountBalance balance = balanceRepository.findById(username)
                    .orElseThrow(() -> new InsufficientFundsException("Tài khoản nhà đầu tư không tồn tại trên hệ thống!"));

            if (balance.getCashAvailable() < totalCost) {
                throw new InsufficientFundsException(String.format(
                        "Đặt lệnh thất bại: Số dư tài khoản không đủ! Cần có: %,.2f VND nhưng hiện tại chỉ có: %,.2f VND", 
                        totalCost, balance.getCashAvailable()));
            }
        }
        
        System.out.println("[HỆ THỐNG ĐẶT LỆNH] VÒNG 3: Thẩm định số dư khả dụng thành công.");
        return joinPoint.proceed();
    }
}
