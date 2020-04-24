package com.copower.pmcc.assess.controller.base;

import com.copower.pmcc.assess.service.base.BaseQrcodeService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zch on 2020-4-24.
 */
@RequestMapping(value = "/baseQrCode")
@RestController
public class BaseQrCodeController {
    private final Logger logger = LoggerFactory.getLogger(getClass()) ;

    @Autowired
    private BaseQrcodeService baseQrcodeService;

    @GetMapping(value = "/toImgQRCodePath")
    public HttpResult toImgQRCodePath(Integer tableId,  String type) {
        try {
            return HttpResult.newCorrectResult(200,baseQrcodeService.toImgQRCodePath(tableId, type));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult(e.getMessage()) ;
        }
    }


}
