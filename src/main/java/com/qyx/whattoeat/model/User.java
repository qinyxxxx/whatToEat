package com.qyx.whattoeat.model;

import lombok.Data;

/**
 * Created by Yuxin Qin on 9/1/25
 */
@Data
public class User {
    private Long id;
    private String email;
    private String passwordHash;
    private String nickname;
}
