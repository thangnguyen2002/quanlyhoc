package com.quanlyhoc.quanlyhoc.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DangNhapResponse {
    @JsonProperty("message")
    private String message;

    @JsonProperty("token")
    private String token;

    private Long maTaiKhoan;

    private String tenTaiKhoan;

    private String vaiTro;
}
