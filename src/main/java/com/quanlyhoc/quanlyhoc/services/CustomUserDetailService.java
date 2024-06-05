//package com.quanlyhoc.quanlyhoc.services;
//
//import com.quanlyhoc.quanlyhoc.dtos.CustomUserDetail;
//import com.quanlyhoc.quanlyhoc.models.User;
//import com.quanlyhoc.quanlyhoc.repositories.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailService implements UserDetailsService {
//    private final UserRepository userRepository;
//
//    public CustomUserDetail getCurrentUserDetails() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetail) {
//            return (CustomUserDetail) authentication.getPrincipal();
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
//        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByPhoneNumber(phoneNumber));
//        if (optionalUser.isEmpty()) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        User user = optionalUser.get();
//        return new CustomUserDetail(user.getPhoneNumber(), user.getPassword(), user.getRole(), user.getId());
//    }
//}
//
