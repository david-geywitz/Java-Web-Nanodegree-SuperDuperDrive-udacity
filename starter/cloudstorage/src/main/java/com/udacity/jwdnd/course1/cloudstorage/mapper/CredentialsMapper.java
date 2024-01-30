package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CredentialsMapper {

    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userId) VALUES (#{url},#{username},#{key},#{password},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int addCredentials(Credentials credentials);

    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    List<Credentials> getUserCredentials(Integer userId);

    @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    Credentials getCredentials(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url=#{url}, key=#{key}, password=#{password}, username=#{username} WHERE credentialId=#{credentialId}")
    int updateCredentials(Credentials credentials);

    @Delete("DELETE CREDENTIALS WHERE credentialId = #{credentialId}")
    void deleteCredentials(Integer credentialId);

}