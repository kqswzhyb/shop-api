package com.example.xb.service.impl;

import com.example.xb.domain.FileRecord;
import com.example.xb.mapper.FileRecordMapper;
import com.example.xb.service.FileRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
