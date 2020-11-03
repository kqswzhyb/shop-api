package com.example.xb.mapper;

import com.example.xb.domain.FileRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
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
}
