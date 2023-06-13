package com.xmut.mapper;

import com.xmut.pojo.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author
 * @date: 2023/4/25
 **/
public interface UserMapper {

    @Select("select * from user where user_email=#{email} and user_password=#{password} and user_status='0'")
    @Results(id = "userResult",value = {
            @Result(id = true,property = "id",column = "user_id"),
            @Result(property ="name",column="user_name"),
            @Result(property ="password",column ="user_password"),
            @Result(property ="email",column ="user_email"),
            @Result(property ="role",column ="user_role"),
            @Result(property ="status",column ="user_status")
    })
    public User findUserByEmailAndPassword(User user);
}
