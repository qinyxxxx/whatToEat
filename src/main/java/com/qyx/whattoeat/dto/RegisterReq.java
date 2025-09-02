package com.qyx.whattoeat.dto;

import lombok.Data;

/**
 * Created by Yuxin Qin on 9/1/25
 */
@Data
public class RegisterReq {
    private String email;
    private String password;
    private String nickname;
}
