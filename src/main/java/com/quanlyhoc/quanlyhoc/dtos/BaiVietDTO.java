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
    @JsonProperty("nguoi_viet_bai")
    private Long nguoiVietBai;

    @JsonProperty("noi_dung")
    private String noiDung;

    @JsonProperty("tieu_de")
    private String tieuDe;

    @JsonProperty("trang_thai")
    private Boolean trangThai;

    @JsonProperty("url_hinh_anh_minh_hoa")
    private String urlHinhAnhMinhHoa;

    @JsonProperty("noi_dung_tom_tat")
    private String noiDungTomTat;
}