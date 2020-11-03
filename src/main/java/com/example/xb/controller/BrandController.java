package com.example.xb.controller;

import com.example.xb.domain.Brand;
import com.example.xb.domain.page.DataDomain;
import com.example.xb.domain.result.AjaxResult;
import com.example.xb.domain.result.ResultInfo;
import com.example.xb.domain.vo.BrandVo;
import com.example.xb.service.BrandService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/v1/brand")
@Api(tags = "品牌管理")
public class BrandController extends BaseController {

    @Autowired
    private BrandService brandService;

    /**
     * 获取品牌列表
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "分页获取品牌信息", notes = "分页获取品牌信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "每页数量", defaultValue = "10")
    }
    )
    public AjaxResult list(@ApiIgnore() Brand brand, String current, String size) {
        DataDomain dd = new DataDomain();
        dd.setCurrent(!StringUtils.isEmptyOrWhitespace(current) ? current : "1");
        dd.setSize(!StringUtils.isEmptyOrWhitespace(size) ? size : "10");
        ResultInfo resultInfo = startPage(dd);
        List<BrandVo> list = brandService.brandList(brand);
        dd.setRecords(list);
        PageInfo pageInfo = new PageInfo(list);
        dd.setTotal(pageInfo.getTotal());

        return new AjaxResult(resultInfo, "1".equals(resultInfo.getCode()) ? null : dd);
    }
}
