package com.example.xb.controller;

import com.example.xb.domain.Brand;
import com.example.xb.domain.FileRecord;
import com.example.xb.domain.page.DataDomain;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.domain.vo.BrandVo;
import com.example.xb.service.BrandService;
import com.example.xb.service.FileRecordService;
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
@RequestMapping("/v1/brand")
@Api(tags = "品牌管理")
public class BrandController extends BaseController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private FileRecordService fileRecordService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取品牌列表
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "分页获取品牌信息", notes = "分页获取品牌信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "10")
    }
    )
    public AjaxResult list(@ApiIgnore() Brand brand, String current, String pageSize) {
        DataDomain dd = new DataDomain(current, pageSize);
        ResultInfo resultInfo = startPage(dd);
        List<BrandVo> list = brandService.brandList(brand);
        dd.setRecords(list);
        PageInfo pageInfo = new PageInfo(list);
        dd.setTotal(pageInfo.getTotal());

        return new AjaxResult(resultInfo, "1".equals(resultInfo.getCode()) ? null : dd);
    }

    /**
     * 创建新品牌
     *
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "创建新品牌", notes = "创建新品牌")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult save(@RequestBody BrandVo brandVo) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(brandVo.getName())) {
            resultInfo.error("品牌名为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(brandVo.getBrandCode())) {
            resultInfo.error("品牌编码为空");
            return new AjaxResult(resultInfo, null);
        }
        String Uid= UUIDUtil.NewUUID();
        List<FileRecord> fileList = brandVo.getFileRecordList();
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
        brandVo.setBrandId(Uid);
        brandVo.setCreateBy(jwtUtil.getJwtUserId());
        brandVo.setStatus("0");
        int i = brandService.saveBrand(brandVo);
        if (i == 1&& j!=0) {
            resultInfo.success("创建成功");
        } else {
            resultInfo.error("创建失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 更新品牌
     *
     * @return
     */
    @PutMapping("/save")
    @ApiOperation(value = "更新品牌", notes = "更新品牌")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult update(@RequestBody BrandVo brandVo) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(brandVo.getBrandId())) {
            resultInfo.error("品牌Id为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(brandVo.getName())) {
            resultInfo.error("品牌名为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(brandVo.getBrandCode())) {
            resultInfo.error("品牌编码为空");
            return new AjaxResult(resultInfo, null);
        }
        String Uid= brandVo.getBrandId();
        List<FileRecord> fileList = brandVo.getFileRecordList();
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
        int i = brandService.updateBrand(brandVo);
        if (i == 1&& j!=0) {
            resultInfo.success("更新成功");
        } else {
            resultInfo.error("更新失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 根据id删除品牌
     *
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "根据id删除品牌", notes = "根据id删除品牌")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult delete(String brandId) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(brandId)) {
            resultInfo.error("brandId不能为空");
            return new AjaxResult(resultInfo, null);
        }
        int i = brandService.deleteBrandById(brandId);
        fileRecordService.deleteFileById(brandId);
        if (i == 1) {
            resultInfo.success("删除成功");
        } else {
            resultInfo.error("该brandId不存在，无法删除");
        }
        return new AjaxResult(resultInfo, null);
    }
}
