package com.qyx.whattoeat.service;

import com.qyx.whattoeat.auth.jwt.JwtUtil;
import com.qyx.whattoeat.dto.LoginReq;
import com.qyx.whattoeat.dto.RegisterReq;
import com.qyx.whattoeat.dto.TokenResp;
import com.qyx.whattoeat.mapper.UserMapper;
import com.qyx.whattoeat.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yuxin Qin on 9/1/25
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    @Transactional
    public TokenResp register(RegisterReq req) {
        if (userMapper.findByEmail(req.getEmail()) != null) {
            throw new IllegalArgumentException("Email already registered");
        }
        User u = new User();
        u.setEmail(req.getEmail());
        u.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        u.setNickname(req.getNickname());
        userMapper.insert(u);
        return new TokenResp(jwtUtil.generate(u.getId(), u.getEmail()));
    }

    public TokenResp login(LoginReq req) {
        User u = userMapper.findByEmail(req.getEmail());
        if (u == null || !passwordEncoder.matches(req.getPassword(), u.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return new TokenResp(jwtUtil.generate(u.getId(), u.getEmail()));
    }

    public User getById(Long userId) {
        return userMapper.findById(userId);
    }

    public User getByEmail(String email) {
        return userMapper.findByEmail(email);
    }
}
