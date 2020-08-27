package com.copower.pmcc.assess.proxy.face;

import com.copower.pmcc.assess.common.enums.basic.BasicTenementTypeEnum;
import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.io.Serializable;

public interface BasicTenementTypeInterface extends Serializable {

    /**
     * 根据物业类型获取列名
     * @param typeEnums
     * @return
     */
    public String toColumns(BasicTenementTypeEnum ...typeEnums) ;

    /**
     * 根据物业类型获取下拉框html
     * @param typeEnums
     * @return
     */
    public String toOptions(BasicTenementTypeEnum ...typeEnums) ;


    /**
     * 根据物业类型获取导入html
     * @param typeEnums
     * @return
     */
    public KeyValueDto[] priceExportColumns(BasicTenementTypeEnum ...typeEnums) ;

    /**
     * 根据物业类型获取页面class选择器前缀
     * @param typeEnums
     * @return
     */
    public String toHtmlViewClassNamePrefix(BasicTenementTypeEnum ...typeEnums) ;
}
