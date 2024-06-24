package com.quanlyhoc.quanlyhoc.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaiKhoanDTO {
    @JsonProperty("ghi_chu")
    private String ghiChu;

    @JsonProperty("ten_tai_khoan")
    private String tenTaiKhoan;

    @JsonProperty("mat_khau")
    private String matKhau;

    @JsonProperty("vai_tro")
    private String vaiTro;
}
