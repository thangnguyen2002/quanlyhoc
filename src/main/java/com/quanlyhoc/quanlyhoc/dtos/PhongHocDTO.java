package com.quanlyhoc.quanlyhoc.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhongHocDTO {
    @JsonProperty("ghi_chu")
    private String ghiChu;

    @JsonProperty("so_cho_ngoi")
    private Integer soChoNgoi;

    @JsonProperty("ten_phong_hoc")
    private String TenPhongHoc;
}