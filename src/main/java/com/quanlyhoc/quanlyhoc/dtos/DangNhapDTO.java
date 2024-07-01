package com.quanlyhoc.quanlyhoc.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DangNhapDTO {
    @JsonProperty("ten_tai_khoan")
    @NotBlank(message = "Tên tài khoản không được để trống")
    private String tenTaiKhoan;

    @JsonProperty("mat_khau")
    @NotBlank(message = "Mật khẩu không được để trống")
    private String matKhau;
}
