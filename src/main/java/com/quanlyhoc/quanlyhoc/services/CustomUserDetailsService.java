package com.quanlyhoc.quanlyhoc.services;

import com.quanlyhoc.quanlyhoc.models.TaiKhoan;
import com.quanlyhoc.quanlyhoc.repositories.TaiKhoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan taiKhoan = taiKhoanRepository.findByTenTaiKhoan(username);
        if (taiKhoan == null) {
            throw new UsernameNotFoundException("Tài khoản không tìm thấy");
        }
        return new User(taiKhoan.getTenTaiKhoan(), taiKhoan.getMatKhau(), getAuthorities(taiKhoan.getVaiTro().toUpperCase()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
