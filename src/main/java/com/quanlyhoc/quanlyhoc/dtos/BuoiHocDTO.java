package com.quanlyhoc.quanlyhoc.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuoiHocDTO {
    @JsonProperty("ten_buoi_hoc")
    private String tenBuoiHoc;

    @JsonProperty("noi_dung_buoi_hoc")
    private String noiDungBuoiHoc;

    @JsonProperty("ma_module")
    private Long maModule;
}
