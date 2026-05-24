package com.ra.ptit_cntt2_it211_ss8_ex5.service;

import com.ra.ptit_cntt2_it211_ss8_ex5.entity.StockOrder;
import com.ra.ptit_cntt2_it211_ss8_ex5.exception.MarginViolationException;
import com.ra.ptit_cntt2_it211_ss8_ex5.repository.StockOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlaceOrderService {

    private final StockOrderRepository orderRepository;

    @Transactional
    public StockOrder placeOrder(String username, String stockCode, Integer quantity, Double price, String orderType) {
        // [Logic Lõi]: Kiểm tra biên độ giá lệch quá 7% so với giá tham chiếu 100.0
        double referencePrice = 100.0;
        double deviation = Math.abs(price - referencePrice) / referencePrice;

        if (deviation > 0.07) {
            throw new MarginViolationException("Đặt lệnh thất bại: Mức giá đặt mua vượt quá biên độ quy định 7% so với giá tham chiếu (100.0)!");
        }

        // Lưu lệnh vào Database
        StockOrder order = StockOrder.builder()
                .username(username)
                .stockCode(stockCode)
                .quantity(quantity)
                .price(price)
                .orderType(orderType)
                .build();

        System.out.println("====== LÕI CORE ENGINE: KHỚP LỆNH THÀNH CÔNG ======");
        return orderRepository.save(order);
    }
}
