package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FileService {

    private FilesMapper filesMapper;

    public FileService(FilesMapper filesMapper) {
        this.filesMapper = filesMapper;
    }

    public List<Files> getFiles(Integer loggedUserId) {
        return filesMapper.getUserFiles(loggedUserId);
    }

    public boolean addFiles(MultipartFile file, Integer loggerUserId){
        try{
            if(null == file.getOriginalFilename() || file.getOriginalFilename().isEmpty()){
                return false;
            }
            Files newFile = new Files();
            newFile.setUserId(loggerUserId);
            newFile.setFilename(file.getOriginalFilename());
            newFile.setFileSize(file.getSize());
            newFile.setContentType(file.getContentType());
            newFile.setFileData(file.getBytes());
            filesMapper.addFiles(newFile);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public void updateFiles(Files files) {
        filesMapper.updateFile(files);
    }

    public Files getFile(Integer fileId, Integer userId) {
        return filesMapper.getFiles(fileId, userId);
    }

    public void deleteFile(Integer fileId) {
        filesMapper.deleteFile(fileId);
    }

    public boolean isDuplicateFileName(String originalFilename) {
        return (null != filesMapper.findByFilename(originalFilename));
    }
}
