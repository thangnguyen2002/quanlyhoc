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
public class GiangVienDTO extends TaiKhoanDTO {
    @JsonProperty("co_quan_cong_tac")
    private String coQuanCongTac;

    @JsonProperty("dia_chi")
    private String diaChi;

    @JsonProperty("email")
    private String email;

    @JsonProperty("ghi_chu")
    private String ghiChu;

    @JsonProperty("ma_linh_vuc")
    private Long maLinhVuc;

    @JsonProperty("ngay_sinh")
    private LocalDate ngaySinh;

    @JsonProperty("so_cmnd")
    private String soCmnd;

    @JsonProperty("so_dien_thoai")
    private String soDienThoai;

    @JsonProperty("ten_giang_vien")
    private String tenGiangVien;

    @JsonProperty("tinh_trang_cong_tac")
    private String tinhTrangCongTac;

    @JsonProperty("url_hinh_dai_dien")
    private String urlHinhDaiDien;

    @JsonProperty("gioi_tinh")
    private String gioiTinh;

    @JsonProperty("ma_tai_khoan")
    private Long maTaiKhoan;
}
