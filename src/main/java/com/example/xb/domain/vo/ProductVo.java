package com.example.xb.domain.vo;

import com.example.xb.domain.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductVo extends Product {
    /**
     * 产品的品牌
     */
    private Brand brand;
    /**
     * 产品附件
     */
    private List<FileRecord> fileRecordList;
    /**
     * 产品描述
     */
    private List<ProductDes> productDesList;
    /**
     * 产品参数
     */
    private List<ProductParameterVo> productParameterVoList;
}
