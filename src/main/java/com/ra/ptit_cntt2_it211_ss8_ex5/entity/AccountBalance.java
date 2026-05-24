package com.ra.ptit_cntt2_it211_ss8_ex5.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account_balances")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountBalance {
    @Id
    private String username;
    @Column(name = "cash_available")
    private Double cashAvailable;
}
