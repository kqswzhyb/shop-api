package com.example.xb.service.impl;

import com.example.xb.domain.file.FileRecord;
import com.example.xb.mapper.FileRecordMapper;
import com.example.xb.service.FileRecordService;
import com.example.xb.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 */
@Service
public class FileRecordServiceImpl implements FileRecordService {

    @Autowired
    private FileRecordMapper fileMapper;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    @Override
    public int saveFile(FileRecord fileRecord) {
        return fileMapper.saveFile(fileRecord);
    }

    @Override
    public List<FileRecord> fileList(FileRecord fileRecord) {
        return fileMapper.fileList(fileRecord);
    }

    @Override
    public int deleteFileById(String recordId) {
        List<FileRecord> list= fileMapper.fileListById(recordId);
        System.out.println(list);
        FileRecord fileR= list.get(0);
        fileUploadUtil.deleteImg(fileR.getFilePath()+fileR.getFileName()+"."+fileR.getFileExt());
        return fileMapper.deleteFileById(recordId);
    }

    @Override
    public int bathSaveFile(List<FileRecord> list) {
        if(list.size()==0) {
            return 1;
        }else {
            return fileMapper.bathSaveFile(list);
        }
    }
}
