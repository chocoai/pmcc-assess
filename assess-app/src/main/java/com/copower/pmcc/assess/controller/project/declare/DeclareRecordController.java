package com.copower.pmcc.assess.controller.project.declare;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectInfoService projectInfoService;

    @RequestMapping(value = "/view/{declareId}", name = "转到详情页面 ", method = {RequestMethod.GET})
    public ModelAndView index(@PathVariable(name = "declareId", required = true) Integer declareId) {
        String view = "/project/stageDeclare/declareRecord/detail";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject(StringUtils.uncapitalize(DeclareRecord.class.getSimpleName()), declareRecordService.getDeclareRecordById(declareId));
        return modelAndView;
    }

    @RequestMapping(value = "/editDeclareRecordNumber/{projectId}", name = "更改  权证  编号 ", method = {RequestMethod.GET})
    public ModelAndView editDeclareRecordNumber(@PathVariable(name = "projectId", required = true) Integer projectId) {
        String view = "/project/stageDeclare/declareRecord/editDeclareRecordNumberView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject(StringUtils.uncapitalize(ProjectInfo.class.getSimpleName()), projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId)));
        return modelAndView;
    }


    @RequestMapping(value = "/getDeclareRecordList", method = {RequestMethod.GET}, name = "获取申报记录数据")
    public BootstrapTableVo getDeclareRecordList(Integer projectId, String name, String seat, Boolean bisPartIn, String province, String city, String district, String inventoryStatus) {
        return declareRecordService.getDeclareRecordList(projectId, name, seat, bisPartIn, province, city, district, inventoryStatus);
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
    public BootstrapTableVo getBootstrapTableVoDispatch(DeclareRecord declareRecord) {
        return declareRecordService.getBootstrapTableVoDispatch(declareRecord);
    }

    @GetMapping(value = "/getBootstrapTableVo", name = "获取列表")
    public BootstrapTableVo getBootstrapTableVo(DeclareRecord declareRecord) {
        return declareRecordService.getBootstrapTableVo(declareRecord);
    }

    @PostMapping(value = "/saveAndUpdateDeclareRecord", name = "保存或者修改")
    public HttpResult saveAndUpdateDeclareRecord(String fomData) {
        try {
            List<DeclareRecord> declareRecordList = JSONObject.parseArray(fomData, DeclareRecord.class);
            declareRecordList.forEach(declareRecord -> declareRecordService.saveAndUpdateDeclareRecord(declareRecord));
            return HttpResult.newCorrectResult(declareRecordList);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e);
        }
    }

    @PostMapping(value = "/saveDeclareRecord", name = "保存或者修改")
    public HttpResult saveDeclareRecord(String formData, @RequestParam(required = false, defaultValue = "false") boolean updateNull) {
        try {
            DeclareRecord declareRecord = JSONObject.parseObject(formData, DeclareRecord.class);
            declareRecordService.saveAndUpdateDeclareRecord(declareRecord, updateNull);
            return HttpResult.newCorrectResult(declareRecord);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e);
        }
    }

    @PostMapping(value = "/saveDeclareRecordArray", name = "保存或者修改")
    public HttpResult saveDeclareRecordArray(String formData, @RequestParam(required = false, defaultValue = "false") boolean updateNull) {
        try {
            List<DeclareRecord> declareRecordList = JSONObject.parseArray(formData, DeclareRecord.class);
            declareRecordList.forEach(declareRecord -> declareRecordService.saveAndUpdateDeclareRecord(declareRecord, updateNull));
            return HttpResult.newCorrectResult(declareRecordList);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e);
        }
    }

    @PostMapping(value = "/changeDeclareRecordNumber", name = "变更权证号")
    public HttpResult changeDeclareRecordNumber(Integer declareRecordId, Integer projectId, String number) {
        try {
            return HttpResult.newCorrectResult(declareRecordService.changeDeclareRecordNumber(declareRecordId, projectId, number));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e);
        }
    }

    @PostMapping(value = "/autoChangeDeclareRecordNumber", name = "自动变更权证号")
    public HttpResult autoChangeDeclareRecordNumber(Integer projectId) {
        try {
            return HttpResult.newCorrectResult(declareRecordService.autoChangeDeclareRecordNumber(projectId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e);
        }
    }


    @RequestMapping(value = "/getCertificateId", name = "获取证书id", method = RequestMethod.GET)
    public HttpResult getCertificateId(Integer declareRecordId) {
        return getDeclareRecordById(declareRecordId);
    }

    @RequestMapping(value = "/getDeclareRecordById", name = "获取证书id", method = RequestMethod.GET)
    public HttpResult getDeclareRecordById(Integer declareRecordId) {
        try {
            return HttpResult.newCorrectResult(declareRecordService.getDeclareRecordById(declareRecordId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/getDeclareRecordListByIds", name = "获取证书id", method = RequestMethod.GET)
    public HttpResult getDeclareRecordListByIds(String id) {
        try {
            return HttpResult.newCorrectResult(declareRecordService.getDeclareRecordListByIds(FormatUtils.transformString2Integer(id)));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
