package com.copower.pmcc.assess.service;

import com.copower.pmcc.erp.api.dto.ocr.aliyun.OcrBaseVo;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zch
 * @Date: 2018/11/8 09:28
 * @Description:图片识别
 */
@Service
public class ErpOcrService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;

    /**
     * 图片识别营业执照
     * @param id
     * @return
     */
    public OcrBaseVo getOcrJson(Integer id){
        return erpRpcToolsService.getOcrJson(id);
    }

}
