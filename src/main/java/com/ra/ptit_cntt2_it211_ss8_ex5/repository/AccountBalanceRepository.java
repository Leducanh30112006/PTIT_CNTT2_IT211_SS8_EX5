package com.ra.ptit_cntt2_it211_ss8_ex5.repository;

import com.ra.ptit_cntt2_it211_ss8_ex5.entity.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBalanceRepository extends JpaRepository<AccountBalance, String> {
}
