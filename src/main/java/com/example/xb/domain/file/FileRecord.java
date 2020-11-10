package com.example.xb.domain.file;

import com.example.xb.domain.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FileRecord extends Base {

    /**
     * 文件id
     */
    private String fileId;
    /**
     * 文件对应记录id
     */
    private String recordId;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件完整路径
     */
    private String fileFullPath;
    /**
     * 文件拓展名
     */
    private String fileExt;

    /**
     * 状态（0 正常  1 停用)
     */
    private  String status;
}
