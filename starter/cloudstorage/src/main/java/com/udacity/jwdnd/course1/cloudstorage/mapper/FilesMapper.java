package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FilesMapper {

    @Insert("INSERT INTO FILES (filename, contentType, fileSize, userId, fileData) VALUES (#{filename},#{contentType},#{fileSize},#{userId},#{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFiles(Files files);

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<Files> getUserFiles(Integer userId);

    @Select("SELECT * FROM FILES WHERE fileId = #{fileId} AND userId = #{userId}")
    Files getFiles(Integer fileId, Integer userId);

    @Select("SELECT * FROM files WHERE filename = #{filename}")
    public Files findByFilename(String filename);

    @Update("UPDATE FILES SET filename = #{filename}, contentType=#{contentType}, fileSize = #{fileSize}, userId=#{userId}, fileData=#{fileDate} where fileId=#{fileId}")
    void updateFile(Files files);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void deleteFile(Integer fileId);
}