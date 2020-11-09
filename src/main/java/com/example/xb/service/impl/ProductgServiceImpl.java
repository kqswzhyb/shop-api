package com.example.xb.service.impl;

import com.example.xb.domain.ProductAttr;
import com.example.xb.domain.Productg;
import com.example.xb.mapper.ProductgMapper;
import com.example.xb.service.ProductAttrService;
import com.example.xb.service.ProductgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductgServiceImpl implements ProductgService {
    @Autowired
    private ProductgMapper productgMapper;
    @Autowired
    private ProductAttrService productAttrService;

    @Override
    public List<Productg> productgList(String productId) {
        return productgMapper.productgList(productId);
    }

    @Override
    public int batchSaveProductg(List<Productg> list) {
        for(Productg child:list) {
          List<String> attrList = child.getAttrList();
          productAttrService.deleteProductAttrById(child.getProductgId());
          if(attrList.size()!=0) {
              List<ProductAttr> newList = new ArrayList<>();
              for(String attr:attrList) {
                  ProductAttr productAttr = new ProductAttr();
                  productAttr.setAttrSonId(attr);
                  productAttr.setProductgId(child.getProductgId());
                  newList.add(productAttr);
              }
              productAttrService.batchSaveProductAttr(newList);
          }
        }
        return productgMapper.batchSaveProductg(list);
    }

    @Override
    public int deleteProductgById(String productId) {
        List<Productg> list = productgMapper.productgList(productId);
        productAttrService.deleteProductAttrById(list.get(0).getProductgId());
        return productgMapper.deleteProductgById(productId);
    }
}
