package com.example.xb.mapper;

import com.example.xb.domain.product.Parameter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParameterMapper {
    /**
     * 获取参数列表
     * @return 参数列表
     */
    List<Parameter> parameterList(Parameter parameter);


    /**
     *创建参数
     * @param
     * @return
     */
    int saveParameter(Parameter parameter);

    /**
     *更新参数
     * @param
     * @return
     */
    int updateParameter(Parameter parameter);

    /**
     *删除
     * @param
     * @return 结果
     */
    int deleteParameterById(String parameterId);
}
