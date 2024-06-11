package com.quanlyhoc.quanlyhoc.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChucVuDTO {
    @JsonProperty("ten_chuc_vu")
    private String tenChucVu;

    @JsonProperty("trang_thai")
    private String trangThai;
}