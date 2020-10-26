package com.example.xb.domain;

import lombok.Data;

@Data
public class FileRecord extends  Base{

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
}
