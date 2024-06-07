package com.quanlyhoc.quanlyhoc.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailFormDTO {
    @JsonProperty("nguoi_nhan")
    private String nguoiNhan;

    @JsonProperty("tieu_de")
    private String tieuDe;

    @JsonProperty("noi_dung")
    private String noiDung;
}