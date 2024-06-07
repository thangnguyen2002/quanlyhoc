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
public class BaiVietDTO {
    @JsonProperty("lan_cap_nhat_cuoi_cung")
    private LocalDate lanCapNhatCuoiCung;

    @JsonProperty("ma_menu")
    private String maMenu;

    @JsonProperty("ngay_dang")
    private LocalDate ngayDang;

    @JsonProperty("nguoi_viet_bai")
    private String nguoiVietBai;

    @JsonProperty("noi_dung")
    private String noiDung;

    @JsonProperty("so_luong_truy_cap")
    private Integer soLuongTruyCap;

    @JsonProperty("tieu_de")
    private String tieuDe;

    @JsonProperty("trang_thai")
    private Boolean trangThai;

//    @JsonProperty("url_hinh_anh_minh_hoa")
//    private String urlHinhAnhMinhHoa;

    @JsonProperty("noi_dung_tom_tat")
    private String noiDungTomTat;
}