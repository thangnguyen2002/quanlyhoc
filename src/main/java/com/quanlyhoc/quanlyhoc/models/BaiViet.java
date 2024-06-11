package com.quanlyhoc.quanlyhoc.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "baiviet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maBaiViet;

    @Column(name = "lan_cap_nhat_cuoi_cung")
    private LocalDate lanCapNhatCuoiCung;

    @Column(name = "ma_menu", length = 255)
    private String maMenu;

    @Column(name = "ngay_dang")
    private LocalDate ngayDang;

    @ManyToOne
    @JoinColumn(name = "nguoi_viet_bai")
    private NhanVien nhanVien;

    @Column(name = "noi_dung", length = 255)
    private String noiDung;

    @Column(name = "so_luong_truy_cap")
    private Integer soLuongTruyCap;

    @Column(name = "tieu_de", length = 255)
    private String tieuDe;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @Column(name = "url_hinh_anh_minh_hoa", length = 255)
    private String urlHinhAnhMinhHoa;

    @Column(name = "noi_dung_tom_tat", length = 255)
    private String noiDungTomTat;
}
