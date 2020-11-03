package com.example.xb.service;

import com.example.xb.domain.FileRecord;

import java.util.List;

public interface FileRecordService {

    /**
     * 保存附件
     * @param fileRecord 文件信息
     * @return 结果
     */
    int saveFile(FileRecord fileRecord);

    /**
     * 获取文件列表
     * @param fileRecord
     * @return
     */
    List<FileRecord> fileList(FileRecord fileRecord);
}
