package com.quanlyhoc.quanlyhoc.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HocVienDTO extends TaiKhoanDTO {
    @JsonProperty("dia_chi")
    private String diaChi;

    @JsonProperty("email")
    private String email;

    @JsonProperty("ghi_chu")
    private String ghiChu;

    @JsonProperty("so_cmnd")
    private String soCmnd;

    @JsonProperty("so_dien_thoai")
    private String soDienThoai;

    @JsonProperty("ten_hoc_vien")
    private String tenHocVien;

    @JsonProperty("url_hinh_dai_dien")
    private String urlHinhDaiDien;

    @JsonProperty("gioi_tinh")
    private String gioiTinh;

    @JsonProperty("ngay_sinh")
    private LocalDate ngaySinh;

    @JsonProperty("tinh_trang_hoc_tap")
    private String tinhTrangHocTap;

    @JsonProperty("ma_tai_khoan")
    private Long maTaiKhoan;
}
