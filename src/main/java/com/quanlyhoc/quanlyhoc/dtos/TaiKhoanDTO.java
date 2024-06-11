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

    @JsonProperty("ma_nhan_vien")
    private Long maNhanVien;

    @JsonProperty("mat_khau")
    private String matKhau;

    @JsonProperty("quyen_truy_cap")
    private String quyenTruyCap;

    @JsonProperty("ten_tai_khoan")
    private String tenTaiKhoan;
}