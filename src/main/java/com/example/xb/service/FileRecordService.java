package com.example.xb.service;

import com.example.xb.domain.file.FileRecord;

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

    /**
     * 通过recordID删除文件记录
     *
     * @param recordId 记录ID
     * @return 结果
     */
    int deleteFileById(String recordId);

    /**
     * 批量保存附件
     * @param list
     * @return
     */
    int bathSaveFile(List<FileRecord> list);
}
