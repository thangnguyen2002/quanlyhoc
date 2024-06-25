package com.quanlyhoc.quanlyhoc.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaiKhoan_HocVienDTO {
    @JsonProperty("ghi_chu")
    private String ghiChu;

    @JsonProperty("ten_tai_khoan")
    private String tenTaiKhoan;

    @JsonProperty("mat_khau")
    private String matKhau;

    @JsonProperty("vai_tro")
    private String vaiTro;

    @JsonProperty("dia_chi")
    private String diaChi;

    @JsonProperty("email")
    private String email;

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

}
