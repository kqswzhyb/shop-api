package com.example.xb.domain.vo;

import com.example.xb.domain.dic.Dic;
import com.example.xb.domain.dic.DicType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DiCTypeVo extends DicType {

    private List<Dic> dicList;
}
