package org.yunyangu.yunyangu.mapper;

import org.apache.ibatis.annotations.*;
import org.yunyangu.yunyangu.entity.User;

@Mapper
public interface UserMapper {

    @Select("select * from User where user_id=#{userId}")
    User getUserById(@Param("userId") Integer userId);

    @Insert("insert into User(name,avatar,password,phone,email,address) " +
            "values (#{user.name}," +
            "#{user.avatar},"+
            "#{user.password},"+
            "#{user.phone},"+
            "#{user.email},"+
            "#{user.address})")
    @Options(useGeneratedKeys = true,keyProperty = "user.userId")
    Integer register(@Param("user") User user);

    @Update("update User set name=#{newName},address=#{newAddress},avatar=#{newAvatar},email=#{newEmail},phone=#{newPhone},signature=#{newSignature} where user_id=#{userID}")
    Integer updateUserInfo(Integer userID, String newName, String newAddress,String newAvatar, String newEmail, String newPhone, String newSignature);

    // 检查数据库中是否已存在同名用户
    @Select("select count(*) from User where name=#{name}")
    boolean existsByUsername(String name);

    @Select("select user_id from User where name=#{name}")
    Integer getUserByName(String name);
}
