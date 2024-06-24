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
public class ModuleDTO {
    @JsonProperty("ten_module")
    private String tenModule;

    @JsonProperty("mo_ta")
    private String moTa;

    @JsonProperty("ma_khoa_hoc")
    private Long maKhoaHoc;
}
