package com.ra.ptit_cntt2_it211_ss8_ex5.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class OrderRequestDTO {
    @NotBlank(message = "Mã chứng khoán không được để trống")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Mã chứng khoán phải viết hoa toàn bộ và có độ dài đúng 3 ký tự (Ví dụ: VNM, VCB)")
    private String stockCode;
    @NotNull(message = "Số lượng đặt lệnh không được để trống")
    @Min(value = 100, message = "Khối lượng đặt lệnh tối thiểu phải từ 100 cổ phiếu")
    @Digits(integer = 10, fraction = 0, message = "Khối lượng không hợp lệ")
    private Integer quantity;
    @NotNull(message = "Mức giá đặt mua không được để trống")
    @Positive(message = "Mức giá đặt mua phải lớn hơn 0")
    private Double price;
    @NotBlank(message = "Loại lệnh không được để trống")
    @Pattern(regexp = "^(BUY|SELL)$", message = "Loại lệnh bắt buộc phải là BUY hoặc SELL")
    private String orderType;
    public boolean isValidLotSize() {
        return this.quantity != null && this.quantity % 100 == 0;
    }
}
