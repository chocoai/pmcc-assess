package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevel;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelDetailVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.assess.service.data.DataLandLevelService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @Auther: zch
 * @Date: 2018/9/4 18:38
 * @Description:土地级别维护
 */
@RequestMapping(value = "/dataLandLevel")
@RestController
public class DataLandLevelController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataLandLevelService dataLandLevelService;
    @Autowired
    private DataLandLevelDetailService dataLandLevelDetailService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;

    @RequestMapping(value = "/importData", name = "导入 基础维护(土地级别区域)", method = RequestMethod.POST)
    public HttpResult importData(HttpServletRequest request, DataLandLevel dataLandLevel) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String resultString = dataLandLevelService.importDataLandLevel(dataLandLevel, multipartFile);
            return HttpResult.newCorrectResult(200, resultString);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @RequestMapping(value = "/view", name = "detail 页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataLandLevelView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        setParams(modelAndView, true);
        //超级管理员
        String account = processControllerComponent.getThisUser() ;
        //需要咨询处理bpm写这个方法的人
        if (processControllerComponent.userIsAdmin(account)) {//失效的方法
            modelAndView.addObject("permission", "admin");//不设置 processControllerComponent.getThisUser(),防止超级管理员变更或者直接变为一组
        } else {
            modelAndView.addObject("permission", account);
        }

//        if ("admin".equals(account)) {
//            modelAndView.addObject("permission", "admin");//不设置 processControllerComponent.getThisUser(),防止超级管理员变更或者直接变为一组
//        } else {
//            modelAndView.addObject("permission", account);
//        }
        return modelAndView;
    }

    @RequestMapping(value = "/comeInLandLevelIndex", name = "转到申请页面 ", method = {RequestMethod.GET})
    public ModelAndView comeInLandLevelIndex(String processInsId) {
        String view = "/data/landModelDir/comeInLandLevelIndex";
        final String boxName = baseParameterService.getParameterValues(BaseParameterEnum.DATA_LAND_LEVEL_APPLY_KEY.getParameterKey());
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(view, boxReDto.getId());
        setParams(modelAndView, false);
        modelAndView.addObject("processInsId", processInsId);
        return modelAndView;
    }

    @GetMapping(value = "/comeInLandLevelApproval", name = "审批页面")
    public ModelAndView comeInLandLevelApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/data/landModelDir/comeInLandLevelApproval", processInsId, boxId, taskId, agentUserAccount);
        setParams(modelAndView, true);
        modelAndView.addObject("processInsId", processInsId);
        return modelAndView;
    }

    @RequestMapping(value = "/comeInLandLevelDetail", name = "详情页面 ", method = {RequestMethod.GET})
    public ModelAndView comeInLandLevelDetail(Integer boxId, String processInsId) {
        String view = "/data/landModelDir/comeInLandLevelApproval";
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView(view, processInsId, boxId, "-1", "");
        setParams(modelAndView, true);
        modelAndView.addObject("processInsId", processInsId);
        return modelAndView;
    }

    @PostMapping(value = "/submitDataLandLevel", name = "发起流程")
    public HttpResult submit() {
        try {
            dataLandLevelService.submitProcess();
            return HttpResult.newCorrectResult(200, "success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @GetMapping(value = "/comeInLandLevelApprovalSubmit", name = "审批提交")
    public HttpResult comeInLandLevelApprovalSubmit(ApprovalModelDto approvalModelDto) {
        try {
            dataLandLevelService.comeInLandLevelApprovalSubmit(approvalModelDto);
            return HttpResult.newCorrectResult(200);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @PostMapping(value = "/comeInLandLevelEditSubmit", name = "编辑提交 (修改提交)")
    public HttpResult comeInLandLevelEditSubmit(ApprovalModelDto approvalModelDto) {
        try {
            dataLandLevelService.comeInLandLevelEditSubmit(approvalModelDto);
            return HttpResult.newCorrectResult(200);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @GetMapping(value = "/comeInLandLevelEdit", name = "流程修改页面")
    public ModelAndView comeInLandLevelEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/data/landModelDir/comeInLandLevelEdit", processInsId, boxId, taskId, agentUserAccount);
        setParams(modelAndView, false);
        modelAndView.addObject("processInsId", processInsId);
        return modelAndView;
    }

    @RequestMapping(value = "/getDataLandLevelListVos", method = {RequestMethod.GET}, name = "获取土地级别维护列表")
    public BootstrapTableVo getDataLandLevelListVos(DataLandLevel dataLandLevel) {
        return dataLandLevelService.getDataLandLevelListVos(dataLandLevel);
    }

    @RequestMapping(value = "/deleteDataLandLevelById", method = {RequestMethod.POST}, name = "删除土地级别维护")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                int count = dataLandLevelDetailService.getCountByLandLevelId(id);
                if (count > 0) {
                    return HttpResult.newErrorResult("请先删除明细中的数据");
                }
                DataLandLevel dataLandLevel = new DataLandLevel();
                dataLandLevel.setId(id);
                dataLandLevelService.removeDataLandLevel(dataLandLevel);
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            baseService.writeExceptionInfo(e1);
            return HttpResult.newErrorResult(e1);
        }
    }

    @RequestMapping(value = "/saveAndUpdateDataLandLevel", method = {RequestMethod.POST}, name = "更新土地级别维护")
    public HttpResult saveAndUpdate(String formData) {
        try {
            DataLandLevel dataLandLevel = JSON.parseObject(formData, DataLandLevel.class);
            dataLandLevelService.saveAndUpdateDataLandLevel(dataLandLevel);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


    @RequestMapping(value = "/getDataLandLevelDetailList", method = {RequestMethod.GET}, name = "获取土地级别信息列表")
    public HttpResult getDataLandLevelDetailList(DataLandLevelDetail dataLandLevelDetail) {
        try {
            return HttpResult.newCorrectResult(200, dataLandLevelDetailService.getDataLandLevelDetailList(dataLandLevelDetail));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @GetMapping(value = "/getDataLandLevelDetailTree", name = "Ztree 数据")
    public HttpResult getDataLandLevelDetailTree(DataLandLevelDetail dataLandLevelDetail) {
        try {
            dataLandLevelDetail.setBisDelete(false);
            List<DataLandLevelDetail> dataLandLevelDetailList = dataLandLevelDetailService.getDataLandLevelDetailTree(dataLandLevelDetail);
            List<ZtreeDto> ztreeDtoList = new ArrayList<ZtreeDto>(dataLandLevelDetailList.size());
            if (CollectionUtils.isNotEmpty(dataLandLevelDetailList)) {
                dataLandLevelDetailList.forEach(landLevelDetail -> ztreeDtoList.add(getZtreeDto(dataLandLevelDetailService.getDataLandLevelDetailVo(landLevelDetail))));
            }
            return HttpResult.newCorrectResult(200, ztreeDtoList);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }


    @RequestMapping(value = "/saveAndUpdateDataLandLevelDetail", method = {RequestMethod.POST}, name = "保存土地级别信息")
    public HttpResult saveAndUpdateDataLandLevelDetail(DataLandLevelDetail dataLandLevelDetail) {
        try {
            dataLandLevelDetailService.saveAndUpdateDataLandLevelDetail(dataLandLevelDetail);
            return HttpResult.newCorrectResult("success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, String.join("", "", e.getMessage()));
        }
    }


    @GetMapping(value = "/getDataLandLevelDetailById", name = "获取 土地级别信息")
    public HttpResult getDataLandLevelDetailById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, dataLandLevelDetailService.getDataLandLevelDetailById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


    @RequestMapping(value = "/removeDataLandLevelDetail", method = {RequestMethod.POST}, name = "删除土地级别信息")
    public HttpResult removeDataLandLevelDetail(String id) {
        try {
            FormatUtils.transformString2Integer(id).forEach(integer -> dataLandLevelDetailService.removeDataLandLevelDetail(integer));
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


    @RequestMapping(value = "/importLandLevelDetail", name = "导入 (土地级别详情)", method = RequestMethod.POST)
    public HttpResult importLandLevelDetail(HttpServletRequest request, DataLandLevelDetail dataLandLevelDetail) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String resultString = dataLandLevelDetailService.importLandLevelDetail(dataLandLevelDetail, multipartFile);
            return HttpResult.newCorrectResult(200, resultString);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }

    }

    private ZtreeDto getZtreeDto(DataLandLevelDetailVo vo) {
        ZtreeDto ztreeDto = new ZtreeDto();
        ztreeDto.setId(vo.getId());
        ztreeDto.setName(vo.getName());
        ztreeDto.setPid(vo.getPid());
        ztreeDto.set_parentId(vo.getPid());
        if (StringUtils.isBlank(vo.getName())){
            if (vo.getPid() == null || vo.getPid() == 0) {
                ztreeDto.setPid(0);
                ztreeDto.set_parentId(0);
                ztreeDto.setName(vo.getClassifyName());
            } else {
                ztreeDto.setPid(vo.getPid());
                ztreeDto.set_parentId(vo.getPid());
                ztreeDto.setName(vo.getTypeName());
            }
        }
        return ztreeDto;
    }

    private void setParams(ModelAndView modelAndView, boolean readOnly) {
        modelAndView.addObject(StringUtils.uncapitalize(SysUserDto.class.getSimpleName()), processControllerComponent.getThisUserInfo());
        modelAndView.addObject("readOnly", readOnly);
    }


}
