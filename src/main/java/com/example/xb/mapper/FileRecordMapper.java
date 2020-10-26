package com.example.xb.mapper;

import com.example.xb.domain.FileRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileRecordMapper {
    /**
     * 保存附件
     * @param fileRecord 文件信息
     * @return 结果
     */
    int saveFile(FileRecord fileRecord);
}
