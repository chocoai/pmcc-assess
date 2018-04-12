package com.copower.pmcc.assess.service;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.dao.BaseProcessDao;
import com.copower.pmcc.assess.dal.entity.BaseFormList;
import com.copower.pmcc.assess.dal.entity.BaseProcess;
import com.copower.pmcc.assess.dal.entity.BaseProcessForm;
import com.copower.pmcc.assess.dto.output.BaseProcessFormVo;
import com.copower.pmcc.assess.dto.output.FormConfigureFieldVo;
import com.copower.pmcc.bpm.api.dto.BpmProcessMapDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessMapService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/23
 * @time: 14:45
 */
@Service
public class BaseProcessService {
    @Autowired
    private BaseProcessDao hrProcessDao;
    @Autowired
    private BpmRpcProcessMapService bpmRpcProcessMapService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BaseFormService hrBaseFormService;
    @Autowired
    private FormConfigureService formConfigureService;

    public BaseProcess getProcessById(Integer id) {
        return hrProcessDao.getProcessById(id);
    }

    public BaseProcess getProcessByName(String name) {
        return hrProcessDao.getProcessByName(name);
    }

    public BaseProcess getProcessByBoxName(String boxName) {
        return hrProcessDao.getProcessByBoxName(boxName);
    }

    public void saveBaseProcess(BaseProcess hrBaseProcess) throws BusinessException {
        BpmProcessMapDto bpmProcessMapDto = new BpmProcessMapDto();
        bpmProcessMapDto.setTableId(hrBaseProcess.getId());
        bpmProcessMapDto.setTableName("tb_hr_base_process");
        bpmProcessMapDto.setAppKey(applicationConstant.getAppKey());
        bpmProcessMapDto.setName(hrBaseProcess.getName());
        bpmProcessMapDto.setCnName(hrBaseProcess.getCnName());
        bpmProcessMapDto.setBoxName(hrBaseProcess.getBoxName());
        bpmProcessMapDto.setBisEnable(hrBaseProcess.getBisEnable());
        if (hrBaseProcess.getId() > 0) {
            if (!hrProcessDao.updateBaseProcess(hrBaseProcess)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
            bpmRpcProcessMapService.updateProcessMap(bpmProcessMapDto);
        } else {
            if (!hrProcessDao.saveBaseProcess(hrBaseProcess)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
            bpmProcessMapDto.setTableId(hrBaseProcess.getId());
            bpmRpcProcessMapService.addProcessMap(bpmProcessMapDto);
        }
    }

    public void deleteBaseProcess(Integer id) throws BusinessException {
        if (!hrProcessDao.deleteBaseProcess(id)) {
            throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
        }
        bpmRpcProcessMapService.deleteProcessMap(id, "tb_hr_base_process", applicationConstant.getAppKey());
    }

    public BootstrapTableVo getBaseProcessList() {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseProcess> hrBaseProcessList = hrProcessDao.getBaseProcessList(requestBaseParam.getSearch());
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(hrBaseProcessList) ? new ArrayList<BaseProcess>() : hrBaseProcessList);
        return bootstrapTableVo;
    }

    //+++++++++++++++++++===================================================
    public List<BaseProcessForm> getProcessFormByProcess(String process) {
        return hrProcessDao.getProcessFormByProcess(process);
    }

    public List<BaseProcessFormVo> getProcessFormVos(String process, String boxName) {
        List<BaseProcessForm> hrProcessFormByProcess = hrProcessDao.getProcessForms(process,boxName);
        if (CollectionUtils.isEmpty(hrProcessFormByProcess)) return null;
        List<BaseFormList> baseFormLists = hrBaseFormService.getbaseFormList(hrProcessFormByProcess);
        return LangUtils.transform(hrProcessFormByProcess, p -> {
            BaseProcessFormVo hrBaseProcessFormVo = new BaseProcessFormVo();
            BeanUtils.copyProperties(p, hrBaseProcessFormVo);
            if (CollectionUtils.isNotEmpty(baseFormLists)) {
                for (BaseFormList baseFormList : baseFormLists) {
                    if (p.getProcess().equals(baseFormList.getFormName()) && p.getName().equals(baseFormList.getName())) {
                        hrBaseProcessFormVo.setFormListId(baseFormList.getId());
                        hrBaseProcessFormVo.setBisConfigure(baseFormList.getBisConfigure());
                        hrBaseProcessFormVo.setBisEnable(baseFormList.getBisEnable());
                        hrBaseProcessFormVo.setBisMultiple(baseFormList.getBisMultiple());
                        hrBaseProcessFormVo.setCustomeDisplayUrl(baseFormList.getCustomeDisplayUrl());
                        hrBaseProcessFormVo.setCustomUrl(baseFormList.getCustomUrl());
                        hrBaseProcessFormVo.setForeignKeyName(baseFormList.getForeignKeyName());
                        hrBaseProcessFormVo.setFormCnName(baseFormList.getCnName());
                        hrBaseProcessFormVo.setTableName(baseFormList.getTableName());
                        if (Boolean.TRUE == baseFormList.getBisMultiple()) {
                            List<FormConfigureFieldVo> fieldVos = Lists.newArrayList();
                            fieldVos = formConfigureService.getListFieldsShow(baseFormList.getId());
                            hrBaseProcessFormVo.setFieldList(fieldVos);
                            if(CollectionUtils.isNotEmpty(fieldVos)){
                                String s = JSONObject.toJSONString(fieldVos);
                                hrBaseProcessFormVo.setFieldListJson(s);
                            }
                        }
                    }
                }
            }
            return hrBaseProcessFormVo;
        });
    }


    public List<BaseProcessForm> getProcessFormList(String process, String activityName) {
        BaseProcessForm hrBaseProcessForm = new BaseProcessForm();
        hrBaseProcessForm.setProcess(process);
        hrBaseProcessForm.setBoxReActivityName(activityName);
        return hrProcessDao.getProcessFormList(hrBaseProcessForm);
    }

    public void saveBaseProcessForm(BaseProcessForm hrBaseProcess) throws BusinessException {
        if (hrBaseProcess.getId() > 0) {
            if (!hrProcessDao.updateBaseProcessForm(hrBaseProcess)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        } else {
            if (!hrProcessDao.saveBaseProcessForm(hrBaseProcess)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        }
    }

    public void deleteBaseProcessForm(Integer id) throws BusinessException {
        if (!hrProcessDao.deleteBaseProcessForm(id)) {
            throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
        }
    }

    public BootstrapTableVo getBaseProcessFormList(String process, String boxName) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseProcessForm> hrBaseProcessFormList = hrProcessDao.getBaseProcessFormList(requestBaseParam.getSearch(), process,boxName);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(hrBaseProcessFormList) ? new ArrayList<BaseProcessForm>() : hrBaseProcessFormList);
        return bootstrapTableVo;
    }

}
