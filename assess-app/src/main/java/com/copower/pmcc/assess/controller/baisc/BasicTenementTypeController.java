package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.common.enums.basic.BasicTenementTypeEnum;
import com.copower.pmcc.assess.service.basic.BasicTenementTypeService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 根据物业类型获取的各种数据
 */
@RequestMapping(value = "/basicTenementType")
@RestController
public class BasicTenementTypeController {
    private Logger logger = LoggerFactory.getLogger(getClass()) ;
    @Autowired
    private BasicTenementTypeService basicTenementTypeService;

    @GetMapping(value = "/getTenementTypeValue")
    public HttpResult getTenementTypeValue(String tenementType, int num) {
        Object obj = "";
        try {
            BasicTenementTypeEnum typeEnum = BasicTenementTypeEnum.getEnumByName(tenementType);
            if (typeEnum == null){
                return HttpResult.newCorrectResult(200, obj);
            }
            switch (num) {
                case 1:
                    obj = basicTenementTypeService.priceExportColumns(typeEnum);
                    break;
                case 2:
                    obj = basicTenementTypeService.toColumns(typeEnum);
                    break;
                case 3:
                    obj = basicTenementTypeService.toOptions(typeEnum);
                    break;
                case 4:
                    obj = basicTenementTypeService.toHtmlViewClassNamePrefix(typeEnum);
                    break;
                default:
                    break;
            }
            return HttpResult.newCorrectResult(200, obj);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

}
