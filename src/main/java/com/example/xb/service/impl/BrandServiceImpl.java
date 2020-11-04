package com.example.xb.service.impl;

import com.example.xb.domain.Brand;
import com.example.xb.domain.FileRecord;
import com.example.xb.domain.vo.BrandVo;
import com.example.xb.mapper.BrandMapper;
import com.example.xb.mapper.FileRecordMapper;
import com.example.xb.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrandServiceImpl  implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private FileRecordMapper fileRecordMapper;


    @Override
    public List<BrandVo> brandList(Brand brand) {

        return brandMapper.brandList(brand);
//        for(BrandVo child:list) {
//            FileRecord fileRecord =new FileRecord();
//            fileRecord.setRecordId(brand.getBrandId());
//            child.setFileRecordList(fileRecordMapper.fileList(fileRecord));
//        }
//        return list;
    }

    @Override
    public int saveBrand(BrandVo brandVo) {
        return brandMapper.saveBrand(brandVo);
    }

    @Override
    public int updateBrand(BrandVo brandVo) {
        return brandMapper.updateBrand(brandVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBrandById(String brandId) {
        fileRecordMapper.deleteFileById(brandId);
        return brandMapper.deleteBrandById(brandId);
    }
}
