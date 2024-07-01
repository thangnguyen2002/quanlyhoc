package com.quanlyhoc.quanlyhoc.dtos;

import com.quanlyhoc.quanlyhoc.models.TaiKhoan;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetail implements UserDetails {
    private final String tenTaiKhoan;
    private final String matKhau;
    private final GrantedAuthority authority;
    @Getter
    @Setter
    private Long maTaiKhoan;

    public CustomUserDetail(TaiKhoan taiKhoan) {
        this.tenTaiKhoan = taiKhoan.getTenTaiKhoan();
        this.matKhau = taiKhoan.getMatKhau();
        this.authority = new SimpleGrantedAuthority("ROLE_" + taiKhoan.getVaiTro().toUpperCase());
        this.maTaiKhoan = taiKhoan.getMaTaiKhoan();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(this.authority);
    }

    @Override
    public String getPassword() {
        return this.matKhau;
    }

    @Override
    public String getUsername() {
        return this.tenTaiKhoan;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
