package com.example.xb.controller;

import com.example.xb.domain.banner.Banner;
import com.example.xb.domain.file.FileRecord;
import com.example.xb.domain.page.DataDomain;
import com.example.xb.domain.product.Brand;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.domain.vo.BannerVo;
import com.example.xb.domain.vo.BrandVo;
import com.example.xb.service.BannerService;
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
@RequestMapping("/v1/banner")
@Api(tags = "广告轮播图管理")
public class BannerController extends BaseController{
    @Autowired
    private BannerService bannerService;

    @Autowired
    private FileRecordService fileRecordService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 获取轮播图列表
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取轮播图列表", notes = "获取轮播图列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", defaultValue = "10")
    }
    )
    public AjaxResult list(@ApiIgnore() Banner banner, String current, String pageSize) {
        DataDomain dd = new DataDomain(current, pageSize);
        ResultInfo resultInfo = startPage(dd);
        List<BannerVo> list = bannerService.bannerList(banner);
        dd.setRecords(list);
        PageInfo pageInfo = new PageInfo(list);
        dd.setTotal(pageInfo.getTotal());

        return new AjaxResult(resultInfo, "1".equals(resultInfo.getCode()) ? null : dd);
    }

    /**
     * 创建新轮播图
     *
     * @return
     */
    @PostMapping("/save")
    @ApiOperation(value = "创建新轮播图", notes = "创建新轮播图")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult save(@RequestBody BannerVo bannerVo) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(bannerVo.getName())) {
            resultInfo.error("名称为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(bannerVo.getLink())) {
            resultInfo.error("链接为空");
            return new AjaxResult(resultInfo, null);
        }
        String Uid= UUIDUtil.NewUUID();
        List<FileRecord> fileList = bannerVo.getFileRecordList();
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
        bannerVo.setBannerId(Uid);
        bannerVo.setCreateBy(jwtUtil.getJwtUserId());
        bannerVo.setStatus("0");
        int i = bannerService.saveBanner(bannerVo);
        if (i == 1&& j!=0) {
            resultInfo.success("创建成功");
        } else {
            resultInfo.error("创建失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 更新banner
     *
     * @return
     */
    @PutMapping("/update")
    @ApiOperation(value = "更新轮播图", notes = "更新轮播图")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult update(@RequestBody BannerVo bannerVo) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(bannerVo.getBannerId())) {
            resultInfo.error("bannerId为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(bannerVo.getName())) {
            resultInfo.error("名称为空");
            return new AjaxResult(resultInfo, null);
        }
        if (StringUtils.isEmptyOrWhitespace(bannerVo.getLink())) {
            resultInfo.error("链接为空");
            return new AjaxResult(resultInfo, null);
        }
        String Uid= bannerVo.getBannerId();
        List<FileRecord> fileList = bannerVo.getFileRecordList();
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
        int i = bannerService.updateBanner(bannerVo);
        if (i == 1&& j!=0) {
            resultInfo.success("更新成功");
        } else {
            resultInfo.error("更新失败");
        }
        return new AjaxResult(resultInfo, null);
    }

    /**
     * 根据id删除轮播图
     *
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "根据id删除轮播图", notes = "根据id删除轮播图")
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult delete(String bannerId) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmptyOrWhitespace(bannerId)) {
            resultInfo.error("brandId不能为空");
            return new AjaxResult(resultInfo, null);
        }
        int i = bannerService.deleteBannerById(bannerId);
        fileRecordService.deleteFileById(bannerId);
        if (i == 1) {
            resultInfo.success("删除成功");
        } else {
            resultInfo.error("该bannerId不存在，无法删除");
        }
        return new AjaxResult(resultInfo, null);
    }
}
