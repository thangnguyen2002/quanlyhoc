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
public class DoiMatKhauDTO {
    @JsonProperty("mat_khau_cu")
    @NotBlank(message = "Yêu cầu nhập Mật khẩu cũ")
    private String matKhauCu;

    @JsonProperty("mat_khau_moi")
    @NotBlank(message = "Yêu cầu nhập mật khẩu mới")
    private String matKhauMoi;
}
