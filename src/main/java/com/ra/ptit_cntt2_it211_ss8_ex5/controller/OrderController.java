package com.ra.ptit_cntt2_it211_ss8_ex5.controller;

import com.ra.ptit_cntt2_it211_ss8_ex5.dto.OrderRequestDTO;
import com.ra.ptit_cntt2_it211_ss8_ex5.entity.StockOrder;
import com.ra.ptit_cntt2_it211_ss8_ex5.service.PlaceOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final PlaceOrderService placeOrderService;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(
            @RequestHeader("X-User") String username,
            @Valid @RequestBody OrderRequestDTO dto) {
        

        if (!dto.isValidLotSize()) {
            return ResponseEntity.badRequest().body(java.util.Map.of("error", "Khối lượng đặt lệnh không hợp lệ! Khối lượng phải là bội số của 100 (Ví dụ: 100, 200, 1500)."));
        }

        StockOrder order = placeOrderService.placeOrder(
                username, dto.getStockCode(), dto.getQuantity(), dto.getPrice(), dto.getOrderType()
        );
        return ResponseEntity.ok(order);
    }
}
