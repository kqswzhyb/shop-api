package com.example.xb.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
/**
 * 产品参数
 */
public class Parameter extends Base {

    private String parameterId;

    private String parameterCode;

    private String name;

    private String status;
}
