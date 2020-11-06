package com.example.xb.domain.vo;

import com.example.xb.domain.Brand;
import com.example.xb.domain.FileRecord;
import com.example.xb.domain.Product;
import com.example.xb.domain.ProductDes;
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
}
