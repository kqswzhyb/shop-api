package com.example.xb.service;

import com.example.xb.domain.FileRecord;
import com.example.xb.mapper.FileRecordMapper;

public interface FileRecordService extends FileRecordMapper {

    /**
     * 保存附件
     * @param fileRecord 文件信息
     * @return 结果
     */
    @Override
    int saveFile(FileRecord fileRecord);
}
