package com.copower.pmcc.assess.controller.project.declare;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:35
 * @Description:申报记录控制器
 */
@RequestMapping(value = "/declareRecord")
@RestController
public class DeclareRecordController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private DeclareRecordService declareRecordService;

    @RequestMapping(value = "/getDeclareRecordList", method = {RequestMethod.GET}, name = "获取申报记录数据")
    public BootstrapTableVo getDeclareRecordList(Integer projectId, String name, String seat, Boolean bisPartIn) {
        return declareRecordService.getDeclareRecordList(projectId, name, seat, bisPartIn);
    }

    @RequestMapping(value = "/addOrRemoveDeclareRecord", name = "添加或移除申报记录数据", method = RequestMethod.POST)
    public HttpResult addOrRemoveDeclareRecord(String ids, Boolean bisPartIn) {
        try {
            declareRecordService.addOrRemoveDeclareRecord(ids, bisPartIn);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @GetMapping(value = "/getBootstrapTableVoDispatch", name = "获取列表,参数里有项目id传入更好")
    public BootstrapTableVo getBootstrapTableVo(DeclareRecord declareRecord) {
        return declareRecordService.getBootstrapTableVo(declareRecord);
    }

    @PostMapping(value = "/saveAndUpdateDeclareRecord", name = "保存或者修改")
    public HttpResult saveAndUpdateDeclareRecord(String fomData) {
        try {
            List<DeclareRecord> declareRecordList = JSONObject.parseArray(fomData, DeclareRecord.class);
            declareRecordList.forEach(declareRecord -> declareRecordService.saveAndUpdateDeclareRecord(declareRecord));
            return HttpResult.newCorrectResult(200, declareRecordList);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }


    @RequestMapping(value = "/getCertificateId", name = "获取证书id", method = RequestMethod.GET)
    public HttpResult getCertificateId(Integer declareRecordId) {
        try {
            DeclareRecord declareRecordById = declareRecordService.getDeclareRecordById(declareRecordId);
            return HttpResult.newCorrectResult(declareRecordById);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
