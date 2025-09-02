package com.qyx.whattoeat.mapper;

import com.qyx.whattoeat.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Yuxin Qin on 9/1/25
 */

@Mapper
public interface UserMapper {
    int insert(User user);
    User findByEmail(@Param("email") String email);
    User findById(@Param("userId") Long userId);
}
