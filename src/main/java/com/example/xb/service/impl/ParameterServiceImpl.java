package com.example.xb.service.impl;

import com.example.xb.domain.product.Parameter;
import com.example.xb.mapper.ParameterMapper;
import com.example.xb.service.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParameterServiceImpl implements ParameterService {
    @Autowired
    private ParameterMapper parameterMapper;
    @Override
    public List<Parameter> parameterList(Parameter parameter) {
        return parameterMapper.parameterList(parameter);
    }

    @Override
    public int saveParameter(Parameter parameter) {
        return parameterMapper.saveParameter(parameter);
    }

    @Override
    public int updateParameter(Parameter parameter) {
        return parameterMapper.updateParameter(parameter);
    }

    @Override
    public int deleteParameterById(String parameterId) {
        return parameterMapper.deleteParameterById(parameterId);
    }
}
