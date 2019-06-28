package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureChildren;
import com.copower.pmcc.assess.service.data.DataInfrastructureChildrenService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zch on 2019/6/28.
 * 配套设施费用
 */
@RestController
@RequestMapping(value = "/dataInfrastructureChildren")
public class DataInfrastructureChildrenController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass()) ;
    @Autowired
    private DataInfrastructureChildrenService dataInfrastructureChildrenService;

    @PostMapping(value = "/saveAndUpdate")
    public HttpResult saveAndUpdate(String formData) {
        DataInfrastructureChildren dataInfrastructureChildren = null;
        try {
            dataInfrastructureChildren = JSONObject.parseObject(formData, DataInfrastructureChildren.class);
            if (dataInfrastructureChildren != null) {
                if (dataInfrastructureChildren.getId() == null || dataInfrastructureChildren.getId() == 0) {
                    dataInfrastructureChildrenService.addDataInfrastructureChildren(dataInfrastructureChildren);
                } else {
                    dataInfrastructureChildrenService.updateDataInfrastructureChildren(dataInfrastructureChildren);
                }
            }
            return HttpResult.newCorrectResult(dataInfrastructureChildren);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PostMapping(value = "/deleteData")
    public HttpResult deleteData(Integer id) {
        try {
            dataInfrastructureChildrenService.deleteDataInfrastructureChildren(id);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult(500, e);
        }
        return HttpResult.newCorrectResult();
    }

    @GetMapping(value = "/getDataById")
    public HttpResult getDataById(Integer id) {
        try {
            return HttpResult.newCorrectResult(dataInfrastructureChildrenService.getByDataInfrastructureChildrenId(id));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(DataInfrastructureChildren dataInfrastructureChildren) {
        return dataInfrastructureChildrenService.getBootstrapTableVo(dataInfrastructureChildren);
    }

    @GetMapping(value = "/getDataList")
    public HttpResult getDataList(DataInfrastructureChildren dataInfrastructureChildren) {
        try {
            List<DataInfrastructureChildren> dataInfrastructureChildrenList = dataInfrastructureChildrenService.getDataInfrastructureChildrenList(dataInfrastructureChildren);
            return HttpResult.newCorrectResult(dataInfrastructureChildrenList);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult(500, e);
        }
    }

}
