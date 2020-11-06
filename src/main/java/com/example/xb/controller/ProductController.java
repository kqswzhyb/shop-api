package com.example.xb.controller;

import com.example.xb.domain.FileRecord;
import com.example.xb.domain.Product;
import com.example.xb.domain.page.DataDomain;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.domain.vo.ProductVo;
import com.example.xb.service.FileRecordService;
import com.example.xb.service.ProductService;
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

import java.util.List;

@RestController
@RequestMapping("/v1/product")
@Api(tags = "产品管理")
public class ProductController extends BaseController{
    @Autowired
    private ProductService productService;
    @Autowired
    private FileRecordService fileRecordService;
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
        productVo.setProductId(Uid);
        productVo.setCreateBy(jwtUtil.getJwtUserId());
        productVo.setStatus("0");
        int i = productService.saveProduct(productVo);
        if (i == 1&& j!=0) {
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
        List<FileRecord> fileList = productVo.getFileRecordList();
        int j = 1;
        fileRecordService.deleteFileById(Uid);
        if(fileList.size()!=0) {
            for(FileRecord child:fileList) {
                child.setFileId(UUIDUtil.NewUUID());
                child.setCreateBy(jwtUtil.getJwtUserId());
                child.setStatus("0");
                child.setRecordId(Uid);
            }
            j = fileRecordService.bathSaveFile(fileList);
        }
        int i = productService.updateProduct(productVo);
        if (i == 1&& j!=0) {
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
        if (i == 1) {
            resultInfo.success("删除成功");
        } else {
            resultInfo.error("该productId不存在，无法删除");
        }
        return new AjaxResult(resultInfo, null);
    }
}
