package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/basicApplyBatchDetail")
public class BasicApplyBatchDetailController {
    private final Logger logger = LoggerFactory.getLogger(getClass()) ;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;


    @RequestMapping(value = "/saveAndUpdateBasicApplyBatchDetail", name = "修改节点数据(只是修改)", method = RequestMethod.POST)
    public HttpResult saveAndUpdateBasicApplyBatchDetail(String formData) {
        try {
            BasicApplyBatchDetail batchDetail = JSONObject.parseObject(formData,BasicApplyBatchDetail.class) ;
            basicApplyBatchDetailService.updateBasicApplyBatchDetailInfo(batchDetail) ;
            return HttpResult.newCorrectResult(batchDetail);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("修改节点数据异常");
        }
    }
}
