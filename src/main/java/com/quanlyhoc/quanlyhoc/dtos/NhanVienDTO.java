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
public class NhanVienDTO {
    @JsonProperty("dia_chi")
    private String diaChi;

    @JsonProperty("email")
    private String email;

    @JsonProperty("ghi_chu")
    private String ghiChu;

    @JsonProperty("ma_chuc_vu")
    private Long maChucVu;

    @JsonProperty("ngay_sinh")
    private LocalDate ngaySinh;

    @JsonProperty("nguoi_nhap_thong_tin")
    private String nguoiNhapThongTin;

    @JsonProperty("so_cmnd")
    private String soCmnd;

    @JsonProperty("so_dien_thoai")
    private String soDienThoai;

    @JsonProperty("ten_nhan_vien")
    private String tenNhanVien;

    @JsonProperty("url_hinh_dai_dien")
    private String urlHinhDaiDien;

    @JsonProperty("gioi_tinh")
    private String gioiTinh;
}