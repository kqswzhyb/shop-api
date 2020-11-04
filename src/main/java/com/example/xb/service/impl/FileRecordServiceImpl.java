package com.example.xb.service.impl;

import com.example.xb.domain.FileRecord;
import com.example.xb.mapper.FileRecordMapper;
import com.example.xb.service.FileRecordService;
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
