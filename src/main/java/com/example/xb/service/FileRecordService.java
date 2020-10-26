package com.example.xb.service;

import com.example.xb.domain.FileRecord;

public interface FileRecordService {

    /**
     * 保存附件
     * @param fileRecord 文件信息
     * @return 结果
     */
    int saveFile(FileRecord fileRecord);
}
