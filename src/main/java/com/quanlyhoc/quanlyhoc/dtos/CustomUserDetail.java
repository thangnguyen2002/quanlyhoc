//package com.quanlyhoc.quanlyhoc.dtos;
//
//import com.quanlyhoc.quanlyhoc.models.TaiKhoan;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//
//public class CustomUserDetail implements UserDetails {
//    private String ten_tai_khoan;
//    private String mat_khau;
//    private GrantedAuthority authority;
//    private Long ma_tai_khoan;
//
//    public CustomUserDetail(String ten_tai_khoan, String mat_khau, Long ma_tai_khoan) {
//        TaiKhoan taiKhoan =
//        this.ten_tai_khoan = ten_tai_khoan;
//        this.mat_khau = mat_khau;
//        this.authority = new SimpleGrantedAuthority("ROLE_" + role.getName());
//        this.ma_tai_khoan = ma_tai_khoan;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(this.authority);
//    }
//
//    @Override
//    public String getPassword() {
//        return this.password;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.username;
//    }
//
//    public Long getUserId() {
//        return this.userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
