package com.quanlyhoc.quanlyhoc.controllers;

import com.quanlyhoc.quanlyhoc.dtos.*;
import com.quanlyhoc.quanlyhoc.models.BaiViet;
import com.quanlyhoc.quanlyhoc.models.PhongHoc;
import com.quanlyhoc.quanlyhoc.models.TaiKhoan;
import com.quanlyhoc.quanlyhoc.responses.DangNhapResponse;
import com.quanlyhoc.quanlyhoc.responses.PhongHocListResponse;
import com.quanlyhoc.quanlyhoc.responses.TaiKhoanListResponse;
import com.quanlyhoc.quanlyhoc.services.interfaces.ITaiKhoanService;
import com.quanlyhoc.quanlyhoc.shared.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/taikhoan")
@RequiredArgsConstructor
public class TaiKhoanController {
    @Autowired
    private final ITaiKhoanService iTaiKhoanService;

    @Autowired
    private final JwtUtils jwtUtils;

    @PostMapping("/admin")
    public ResponseEntity<?> themTaiKhoanAdmin(
            @Valid @RequestBody TaiKhoanDTO taiKhoanDTO
    ) {
        try {
            TaiKhoan taiKhoan = iTaiKhoanService.themTaiKhoanAdmin(taiKhoanDTO);
            return new ResponseEntity<>(taiKhoan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/giangvien")
    public ResponseEntity<?> themTaiKhoanGiangVien(
            @Valid @RequestPart("taiKhoan_giangVienDTO") TaiKhoan_GiangVienDTO taiKhoan_giangVienDTO,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        try {
            TaiKhoan taiKhoan = iTaiKhoanService.themTaiKhoanGiangVien(taiKhoan_giangVienDTO, file);
            return new ResponseEntity<>(taiKhoan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/hocvien")
    public ResponseEntity<?> themTaiKhoanHocVien(
            @Valid @RequestPart("taiKhoan_hocVienDTO") TaiKhoan_HocVienDTO taiKhoan_hocVienDTO,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        try {
            TaiKhoan taiKhoan = iTaiKhoanService.themTaiKhoanHocVien(taiKhoan_hocVienDTO, file);
            return new ResponseEntity<>(taiKhoan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/dangnhap")
    public ResponseEntity<DangNhapResponse> login(
            @Valid @RequestBody DangNhapDTO dangNhapDTO,
            HttpServletRequest request) {
        // Kiểm tra thông tin đăng nhập và sinh token
        try {
            String token = iTaiKhoanService.dangNhap(
                    dangNhapDTO.getTenTaiKhoan(),
                    dangNhapDTO.getMatKhau());

            TaiKhoan userDetail = iTaiKhoanService.getUserDetailsFromToken(token);

            return new ResponseEntity<>(DangNhapResponse.builder()
                    .message("Đăng nhập thành công")
                    .token(jwtUtils.generateToken(dangNhapDTO.getTenTaiKhoan()))
                    .maTaiKhoan(userDetail.getMaTaiKhoan())
                    .tenTaiKhoan(userDetail.getTenTaiKhoan())
                    .vaiTro(userDetail.getVaiTro())
                    .build(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(DangNhapResponse.builder()
                    .message(e.getMessage())
                    .build(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/doimatkhau")
    public ResponseEntity<?> changePassword(
            @Valid @RequestBody DoiMatKhauDTO doiMatKhauDTO,
            @AuthenticationPrincipal UserDetails userDetails,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            if (userDetails == null) {
                return new ResponseEntity<>("User not authenticated", HttpStatus.UNAUTHORIZED);
            }

            String tenTaiKhoan = userDetails.getUsername();
            iTaiKhoanService.thayDoiMatKhau(tenTaiKhoan, doiMatKhauDTO.getMatKhauCu(), doiMatKhauDTO.getMatKhauMoi());

            // Invalidate the current token by logging out the user
            new SecurityContextLogoutHandler().logout(request, response, null);

            return new ResponseEntity<>("Đổi mật khẩu thành công", HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/dangxuat")
    public ResponseEntity<?> logout(
            HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return ResponseEntity.ok("Đăng xuất thành công.");
    }

    @PostMapping("/chitiet")
    public ResponseEntity<?> getUserDetails(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String extractedToken = authorizationHeader.substring(7);
            // Loại bỏ "Bearer " từ chuỗi token, lay tu vi tri 7 tro di
            TaiKhoan taiKhoan = iTaiKhoanService.getUserDetailsFromToken(extractedToken);
            return new ResponseEntity<>(taiKhoan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> suaTaiKhoan(
            @Valid @PathVariable("id") Long id,
            @Valid @RequestBody TaiKhoanDTO taiKhoanDTO) {
        try {
            TaiKhoan taiKhoan = iTaiKhoanService.suaTaiKhoan(id, taiKhoanDTO);
            return new ResponseEntity<>(taiKhoan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> xoaTaiKhoan(@Valid @PathVariable("id") Long id) {
        try {
            iTaiKhoanService.xoaTaiKhoan(id);
            return new ResponseEntity<>(String.format("Tài khoản với id = %d đã xoá thành công", id),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> timTaiKhoan(@RequestBody KeywordDTO keywordDTO) {
        try {
            List<TaiKhoan> taiKhoanList = iTaiKhoanService.timTaiKhoanTheoTen(keywordDTO.getKeyword());
            return new ResponseEntity<>(taiKhoanList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllTaiKhoan(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "maTaiKhoan") String sortBy
    ) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
            Page<TaiKhoan> taiKhoanPage = iTaiKhoanService.findAll(pageable);
            int totalPages = taiKhoanPage.getTotalPages();
            List<TaiKhoan> taiKhoans = taiKhoanPage.getContent();
            return new ResponseEntity<>(TaiKhoanListResponse.builder()
                    .taiKhoanList(taiKhoans)
                    .totalPages(totalPages)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaiKhoanById(@Valid @PathVariable("id") Long id) {
        try {
            TaiKhoan taiKhoan = iTaiKhoanService.findById(id);
            return new ResponseEntity<>(taiKhoan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
