package com.ra.ptit_cntt2_it211_ss8_ex5.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    
    @Column(name = "stock_code")
    private String stockCode;
    
    private Integer quantity;
    private Double price;
    
    @Column(name = "order_type")
    private String orderType; // BUY, SELL
}
