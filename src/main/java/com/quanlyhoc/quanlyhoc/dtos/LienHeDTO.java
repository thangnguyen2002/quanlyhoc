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
public class LienHeDTO {
    @JsonProperty("email")
    private String email;

    @JsonProperty("ho_ten")
    private String hoTen;

    @JsonProperty("ngay_lien_he")
    private LocalDate ngayLienHe;

    @JsonProperty("so_dien_thoai")
    private String soDienThoai;

    @JsonProperty("y_kien")
    private String yKien;

}