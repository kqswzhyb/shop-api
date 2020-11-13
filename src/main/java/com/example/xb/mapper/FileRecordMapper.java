package com.example.xb.mapper;

import com.example.xb.domain.file.FileRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface FileRecordMapper {
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
     * 通过Id获取文件列表
     * @param recordId
     * @return
     */
    List<FileRecord> fileListById(String recordId);


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
