package com.qyx.whattoeat.dto;

import lombok.Data;

/**
 * Created by Yuxin Qin on 9/1/25
 */
@Data
public class TokenResp {
    private String token;
    public TokenResp(String t){
        this.token=t;
    }
}
