package com.copower.pmcc.assess.controller.method;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentInfrastructureChildren;
import com.copower.pmcc.assess.service.method.MdDevelopmentInfrastructureChildrenService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by zch on 2019/7/31.
 */

@RequestMapping(value = "/mdDevelopmentInfrastructureChildren")
@RestController
public class MdDevelopmentInfrastructureChildrenController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MdDevelopmentInfrastructureChildrenService mdDevelopmentInfrastructureChildrenService;

    @PostMapping(value = "/save")
    public HttpResult save(String forData){
        try {
            List<MdDevelopmentInfrastructureChildren> childrenList = JSONObject.parseArray(forData,MdDevelopmentInfrastructureChildren.class) ;
            if (CollectionUtils.isNotEmpty(childrenList)){
                for (MdDevelopmentInfrastructureChildren mdDevelopmentInfrastructureChildren:childrenList){
                    mdDevelopmentInfrastructureChildrenService.saveMdDevelopmentInfrastructureChildren(mdDevelopmentInfrastructureChildren);
                }
            }
            return HttpResult.newCorrectResult(200, childrenList);
        } catch (Exception e) {
            logger.error("异常", e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(MdDevelopmentInfrastructureChildren oo){
        return mdDevelopmentInfrastructureChildrenService.getBootstrapTableVo(oo);
    }

    @GetMapping(value = "/getMdDevelopmentInfrastructureChildrenList")
    public HttpResult getMdDevelopmentInfrastructureChildrenList(MdDevelopmentInfrastructureChildren oo){
        return HttpResult.newCorrectResult(200, mdDevelopmentInfrastructureChildrenService.getMdDevelopmentInfrastructureChildrenListByExample(oo));
    }

    @PostMapping(value = "/delete")
    public HttpResult remove(String id){
        try {
            List<Integer> integerList = FormatUtils.transformString2Integer(id) ;
            if (CollectionUtils.isNotEmpty(integerList)){
                for (Integer integer:integerList){
                    mdDevelopmentInfrastructureChildrenService.deleteMdDevelopmentInfrastructureChildrenById(integer) ;
                }
            }
            return HttpResult.newCorrectResult(200, "success");
        } catch (Exception e) {
            logger.error("异常", e);
            return HttpResult.newErrorResult(500, e);
        }
    }
}
