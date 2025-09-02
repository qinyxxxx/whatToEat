package com.qyx.whattoeat.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.qyx.whattoeat.auth.jwt.JwtAuthFilter.SimpleUserPrincipal;

/**
* 
* Created by Yuxin Qin on 9/1/25
*/public class CurrentUser {
    public static Long id() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if (a == null || !(a.getPrincipal() instanceof SimpleUserPrincipal p)) return null;
        return p.id();
    }
    public static String email() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if (a == null || !(a.getPrincipal() instanceof SimpleUserPrincipal p)) return null;
        return p.email();
    }
}
