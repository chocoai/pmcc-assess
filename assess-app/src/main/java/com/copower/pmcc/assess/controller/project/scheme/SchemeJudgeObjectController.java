package com.copower.pmcc.assess.controller.project.scheme;

import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/schemeJudgeObject")
public class SchemeJudgeObjectController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;

    @GetMapping(value = "/getSchemeJudgeObjectList")
    public BootstrapTableVo getSchemeJudgeObjectList(String name, String certName, String seat, String ownership, Integer areaGroupId) {
        return schemeJudgeObjectService.getJudgeObjectListByQuery(name, certName, seat, ownership, areaGroupId);
    }


    @GetMapping(value = "/getSchemeJudgeObjectListAll")
    public BootstrapTableVo getSchemeJudgeObjectListAll(String name, String certName, String seat, String ownership, Integer areaGroupId) {
        return schemeJudgeObjectService.getSchemeJudgeObjectListAll(name, certName, seat, ownership, areaGroupId);
    }


}
