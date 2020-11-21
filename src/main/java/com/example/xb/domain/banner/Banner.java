package com.example.xb.domain.banner;

import com.example.xb.domain.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Banner  extends Base {

    private String bannerId;

    private String name;

    private String link;

    private String place;

    private String sort;

    private String status;
}
