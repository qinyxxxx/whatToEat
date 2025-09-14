package com.qyx.whattoeat.dto;

import lombok.Data;

/**
 * Created by Yuxin Qin on 9/1/25
 */
@Data
public class TokenResp {
    private String token;

    private Long userId;

    private String nickname;
    private String email;

    public TokenResp(String token, Long id, String nickname, String email) {
        this.token = token;
        this.userId = id;
        this.nickname = nickname;
        this.email = email;
    }
}
