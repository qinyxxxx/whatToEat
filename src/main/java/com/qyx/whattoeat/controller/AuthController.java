package com.qyx.whattoeat.controller;

import com.qyx.whattoeat.common.dto.ApiResponse;
import com.qyx.whattoeat.dto.LoginReq;
import com.qyx.whattoeat.dto.RegisterReq;
import com.qyx.whattoeat.dto.TokenResp;
import com.qyx.whattoeat.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yuxin Qin on 9/1/25
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ApiResponse<TokenResp> register(@RequestBody RegisterReq req) {
        return ApiResponse.success(userService.register(req));
    }

    @PostMapping("/login")
    public ApiResponse<TokenResp> login(@RequestBody LoginReq req) {
        return ApiResponse.success(userService.login(req));
    }

}
