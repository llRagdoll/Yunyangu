package org.yunyangu.yunyangu.mapper;

import org.apache.ibatis.annotations.*;
import org.yunyangu.yunyangu.entity.User;

@Mapper
public interface UserMapper {

    @Select("select * from User where user_id=#{userID}")
    User getUserById(@Param("userID") Integer userID);

    @Insert("insert into User(name,avatar,password,phone,email,address) " +
            "values (#{user.name}," +
            "#{user.avatar},"+
            "#{user.password},"+
            "#{user.phone},"+
            "#{user.email})"+
            "#{user.address}")
    @Options(useGeneratedKeys = true,keyProperty = "user.user_id")
    Integer register(@Param("user") User user);

    @Update("update User set name=#{newName},address=#{newAddress},email=#{newEmail},phone=#{newPhone},signature=#{newSignature} where user_id=#{userID}")
    Integer updateUserInfo(int userID, String newName, String newAddress, String newEmail, String newPhone, String newSignature);
}
