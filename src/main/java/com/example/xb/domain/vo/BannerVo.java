package com.example.xb.domain.vo;

import com.example.xb.domain.banner.Banner;
import com.example.xb.domain.file.FileRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BannerVo extends Banner {

    private List<FileRecord> fileRecordList;
}
