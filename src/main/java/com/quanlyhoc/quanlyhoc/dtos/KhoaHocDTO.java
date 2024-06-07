package com.quanlyhoc.quanlyhoc.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KhoaHocDTO {
    @JsonProperty("hoc_phi")
    private Double hocPhi;

    @JsonProperty("ma_linh_vuc")
    private Long maLinhVuc;

    @JsonProperty("so_buoi")
    private Integer soBuoi;

    @JsonProperty("ten_khoa_hoc")
    private String tenKhoaHoc;

    @JsonProperty("ghi_chu")
    private String ghiChu;

    @JsonProperty("noi_dung_khoa_hoc")
    private String noiDungKhoaHoc;

    @JsonProperty("noi_dung_tom_tat_khoa_hoc")
    private String noiDungTomTatKhoaHoc;
}