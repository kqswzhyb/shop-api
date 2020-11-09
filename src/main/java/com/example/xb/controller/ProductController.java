package com.example.xb.controller;

import com.example.xb.domain.*;
import com.example.xb.domain.page.DataDomain;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.domain.vo.ProductParameterVo;
import com.example.xb.domain.vo.ProductVo;
import com.example.xb.service.*;
import com.example.xb.utils.JwtUtil;
import com.example.xb.utils.UUIDUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
@Api(tags = "产品管理")
public class ProductController extends BaseController{
    @Autowired
    private AttrBaseService attrBaseService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDesService productDesService;
    @Autowired
    private ProductParameterService productParameterService;
    @Autowired
    private FileRecordService fileRecordService;
    @Autowired
    private ProductgService productgService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/list")
    @ApiOperation(value = "分页获取产品信息", notes = "分页获取产品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "10")
    }
    )
    public AjaxResult list(@ApiIgnore() Product product, String current, String pageSize) {
        DataDomain dd = new DataDomain(current, pageSize);
        ResultInfo resultInfo = startPage(dd);
        List<ProductVo> list = productService.productList(product);
        dd.setRecords(list);
        PageInfo pageInfo = new PageInfo(list);
        dd.setTotal(pageInfo.getTotal());

        return new AjaxResult(resultInfo, "1".equals(resultInfo.getCode()) ? null : dd);
    }

    /**
     * 创建新产品
     *
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "创建新产品基础", notes = "创建新产品基础")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult save(@RequestBody ProductVo productVo) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(productVo.getName())) {
            resultInfo.error("产品名为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(productVo.getProductCode())) {
            resultInfo.error("产品编码为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(productVo.getBrandId())) {
            resultInfo.error("产品品牌id为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(productVo.getProductUnit())) {
            resultInfo.error("产品单位为空");
            return new AjaxResult(resultInfo, null);
        }
        String Uid= UUIDUtil.NewUUID();

        List<FileRecord> fileList = productVo.getFileRecordList();
        int j = 1;
        if(fileList.size()!=0) {
            for(FileRecord child:fileList) {
                child.setFileId(UUIDUtil.NewUUID());
                child.setCreateBy(jwtUtil.getJwtUserId());
                child.setStatus("0");
                child.setRecordId(Uid);
            }
            j = fileRecordService.bathSaveFile(fileList);
        }

        List<ProductDes> desList = productVo.getProductDesList();
        int k = 1;
        if(desList.size()!=0) {
            for(ProductDes child:desList) {
                child.setDesId(UUIDUtil.NewUUID());
                child.setCreateBy(jwtUtil.getJwtUserId());
                child.setStatus("0");
                child.setProductId(Uid);
            }
            k = productDesService.batchSaveProductDes(desList);
        }

        List<ProductParameterVo> parameterList = productVo.getProductParameterVoList();
        List<ProductParameter> ppList= new ArrayList<>();
        int l = 1;
        if(parameterList.size()!=0) {
            for(ProductParameterVo child:parameterList) {
                ProductParameter productParameter = new ProductParameter();
                productParameter.setContent(child.getContent());
                productParameter.setProductId(Uid);
                productParameter.setParameterId(child.getParameterId());
            }
            l = productParameterService.batchSaveProductParameter(ppList);
        }

        List<Productg> productgList = productVo.getProductgList();
        int n = 1;
        if(productgList.size()!=0) {
            for(Productg child:productgList) {
                child.setProductgId(UUIDUtil.NewUUID());
                child.setCreateBy(jwtUtil.getJwtUserId());
                child.setStatus("0");
                child.setProductId(Uid);
            }
            n = productgService.batchSaveProductg(productgList);
        }

        productVo.setProductId(Uid);
        productVo.setCreateBy(jwtUtil.getJwtUserId());
        productVo.setStatus("0");

        List<AttrBase> attrBaseList = productVo.getAttrBaseList();
        int m = 1;
        if(attrBaseList.size()!=0) {
            for(AttrBase child:attrBaseList) {
                child.setAttrId(UUIDUtil.NewUUID());
                child.setCreateBy(jwtUtil.getJwtUserId());
                child.setStatus("0");
                child.setProductId(Uid);
            }
            m = attrBaseService.batchSaveAttrBase(attrBaseList);
        }

        int i = productService.saveProduct(productVo);
        if (i == 1&& j!=0 && k!=0&&l!=0&&m!=0&&n!=0) {
            resultInfo.success("创建成功");
        } else {
            resultInfo.error("创建失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 更新产品
     *
     * @return
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新产品基础", notes = "更新产品基础")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult update(@RequestBody ProductVo productVo) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(productVo.getName())) {
            resultInfo.error("产品名为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(productVo.getProductCode())) {
            resultInfo.error("产品编码为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(productVo.getBrandId())) {
            resultInfo.error("产品品牌id为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(productVo.getProductUnit())) {
            resultInfo.error("产品单位为空");
            return new AjaxResult(resultInfo, null);
        }
        String Uid= productVo.getProductId();

        fileRecordService.deleteFileById(Uid);
        productDesService.deleteProductDesById(Uid);
        productParameterService.deleteProductParameterById(Uid);
        productgService.deleteProductgById(Uid);
        attrBaseService.deleteAttrBaseById(Uid);

        List<FileRecord> fileList = productVo.getFileRecordList();
        int j = 1;
        if(fileList.size()!=0) {
            for(FileRecord child:fileList) {
                child.setFileId(UUIDUtil.NewUUID());
                child.setCreateBy(jwtUtil.getJwtUserId());
                child.setStatus("0");
                child.setRecordId(Uid);
            }
            j = fileRecordService.bathSaveFile(fileList);
        }

        List<ProductDes> desList = productVo.getProductDesList();
        int k = 1;
        if(desList.size()!=0) {
            for(ProductDes child:desList) {
                child.setDesId(UUIDUtil.NewUUID());
                child.setCreateBy(jwtUtil.getJwtUserId());
                child.setStatus("0");
                child.setProductId(Uid);
            }
            k = productDesService.batchSaveProductDes(desList);
        }

        List<ProductParameterVo> parameterList = productVo.getProductParameterVoList();
        List<ProductParameter> ppList= new ArrayList<>();
        int l = 1;
        if(parameterList.size()!=0) {
            for(ProductParameterVo child:parameterList) {
                ProductParameter productParameter = new ProductParameter();
                productParameter.setContent(child.getContent());
                productParameter.setProductId(Uid);
                productParameter.setParameterId(child.getParameterId());
            }
            l = productParameterService.batchSaveProductParameter(ppList);
        }

        List<Productg> productgList = productVo.getProductgList();
        int n = 1;
        if(productgList.size()!=0) {
            for(Productg child:productgList) {
                child.setProductgId(UUIDUtil.NewUUID());
                child.setCreateBy(jwtUtil.getJwtUserId());
                child.setStatus("0");
                child.setProductId(Uid);
            }
            n = productgService.batchSaveProductg(productgList);
        }

        productVo.setProductId(Uid);
        productVo.setCreateBy(jwtUtil.getJwtUserId());
        productVo.setStatus("0");

        List<AttrBase> attrBaseList = productVo.getAttrBaseList();
        int m = 1;
        if(attrBaseList.size()!=0) {
            for(AttrBase child:attrBaseList) {
                child.setAttrId(UUIDUtil.NewUUID());
                child.setCreateBy(jwtUtil.getJwtUserId());
                child.setStatus("0");
                child.setProductId(Uid);
            }
            m = attrBaseService.batchSaveAttrBase(attrBaseList);
        }

        int i = productService.updateProduct(productVo);
        if (i == 1&& j!=0 && k!=0&&l!=0&&m!=0&&n!=0) {
            resultInfo.success("更新成功");
        } else {
            resultInfo.error("更新失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 根据id删除产品
     *
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "根据id删除产品", notes = "根据id删除产品")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult delete(String productId) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(productId)) {
            resultInfo.error("productId不能为空");
            return new AjaxResult(resultInfo, null);
        }
        int i = productService.deleteProductById(productId);
        fileRecordService.deleteFileById(productId);
        productDesService.deleteProductDesById(productId);
        productParameterService.deleteProductParameterById(productId);
        productgService.deleteProductgById(productId);
        attrBaseService.deleteAttrBaseById(productId);
        if (i == 1) {
            resultInfo.success("删除成功");
        } else {
            resultInfo.error("该productId不存在，无法删除");
        }
        return new AjaxResult(resultInfo, null);
    }
}
