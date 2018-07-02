package com.copower.pmcc.assess.service.base;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.dao.base.BaseProcessDao;
import com.copower.pmcc.assess.dal.entity.BaseFormModule;
import com.copower.pmcc.assess.dal.entity.BaseProcess;
import com.copower.pmcc.assess.dal.entity.BaseProcessForm;
import com.copower.pmcc.assess.dto.output.base.BaseProcessFormVo;
import com.copower.pmcc.assess.dto.output.base.BaseProcessVo;
import com.copower.pmcc.assess.dto.output.FormConfigureFieldVo;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
    private FormConfigureService formConfigureService;
    @Autowired
    private BaseFormService baseFormService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;

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
        if (hrBaseProcess.getId() != null && hrBaseProcess.getId() > 0) {
            if (!hrProcessDao.updateBaseProcess(hrBaseProcess)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        } else {
            if (!hrProcessDao.saveBaseProcess(hrBaseProcess)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        }
    }

    public void deleteBaseProcess(Integer id) throws BusinessException {
        if (!hrProcessDao.deleteBaseProcess(id)) {
            throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
        }
    }

    public BootstrapTableVo getBaseProcessList() {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseProcess> hrBaseProcessList = hrProcessDao.getBaseProcessList(requestBaseParam.getSearch());
        List<BaseProcessVo> list = getBaseProcessVos(hrBaseProcessList);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<BaseProcessVo>() : list);
        return bootstrapTableVo;
    }

    public List<BaseProcessVo> getBaseProcessVos(List<BaseProcess> baseProcessList) {
        if (CollectionUtils.isNotEmpty(baseProcessList)) {
            return LangUtils.transform(baseProcessList, p -> {
                BaseProcessVo baseProcessVo = new BaseProcessVo();
                BeanUtils.copyProperties(p, baseProcessVo);
                if (StringUtils.isNotBlank(p.getBoxName())) {
                    Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(p.getBoxName());
                    BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
                    if (boxReDto != null)
                        baseProcessVo.setDisplayBoxName(boxReDto.getCnName());
                }
                return baseProcessVo;
            });
        }
        return null;
    }


    //+++++++++++++++++++===================================================
    public List<BaseProcessForm> getProcessFormByProcess(Integer processId) {
        return hrProcessDao.getProcessFormByProcess(processId);
    }

    public List<BaseProcessFormVo> getProcessFormVos(String processName) {
        BaseProcess baseProcess = hrProcessDao.getProcessByName(processName);
        List<BaseProcessForm> baseProcessFormList = hrProcessDao.getProcessFormByProcess(baseProcess.getId());
        if (CollectionUtils.isEmpty(baseProcessFormList)) return null;
        List<Integer> moduleIds = LangUtils.transform(baseProcessFormList, p -> p.getFormModuleId());
        List<BaseFormModule> baseFormModuleList = baseFormService.getBaseFormModuleList(moduleIds);
        return LangUtils.transform(baseProcessFormList, p -> {
            BaseProcessFormVo baseProcessFormVo = new BaseProcessFormVo();
            BeanUtils.copyProperties(p, baseProcessFormVo);
            for (BaseFormModule baseFormModule : baseFormModuleList) {
                if(baseFormModule.getId().intValue()==p.getFormModuleId().intValue()){
                    baseProcessFormVo.setBisConfigure(baseFormModule.getBisConfigure());
                    baseProcessFormVo.setBisEnable(baseFormModule.getBisEnable());
                    baseProcessFormVo.setBisMultiple(baseFormModule.getBisMultiple());
                    baseProcessFormVo.setCustomeDisplayUrl(baseFormModule.getCustomDisplayUrl());
                    baseProcessFormVo.setCustomUrl(baseFormModule.getCustomUrl());
                    baseProcessFormVo.setForeignKeyName(baseFormModule.getForeignKeyName());
                    baseProcessFormVo.setTableName(baseFormModule.getTableName());
                    if (Boolean.TRUE == baseFormModule.getBisMultiple()) {
                        List<FormConfigureFieldVo> fieldVos = Lists.newArrayList();
                        fieldVos = formConfigureService.getListShowFields(baseFormModule.getId());
                        baseProcessFormVo.setFieldList(fieldVos);
                        if (CollectionUtils.isNotEmpty(fieldVos)) {
                            String s = JSONObject.toJSONString(fieldVos);
                            baseProcessFormVo.setFieldListJson(s);
                        }
                    }
                }
            }
            return baseProcessFormVo;
        });
    }


    public List<BaseProcessForm> getProcessFormList(Integer processId, String activityName) {
        BaseProcessForm hrBaseProcessForm = new BaseProcessForm();
        hrBaseProcessForm.setProcessId(processId);
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

    public BootstrapTableVo getBaseProcessFormList(Integer processId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseProcessForm> hrBaseProcessFormList = hrProcessDao.getBaseProcessFormList(requestBaseParam.getSearch(), processId);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(hrBaseProcessFormList) ? new ArrayList<BaseProcessForm>() : hrBaseProcessFormList);
        return bootstrapTableVo;
    }

    private List<BaseProcessFormVo> getBaseProcessFormVos(List<BaseProcessForm> baseProcessForms) {
        if (CollectionUtils.isEmpty(baseProcessForms)) return null;
        return LangUtils.transform(baseProcessForms, p -> {
            BaseProcessFormVo baseProcessFormVo = new BaseProcessFormVo();
            BeanUtils.copyProperties(p, baseProcessFormVo);
            if (p.getFormModuleId() != null) {
                BaseFormModule baseFormModule = baseFormService.getBaseFormModule(p.getFormModuleId());
                if (baseFormModule != null) {
                    baseProcessFormVo.setFormModuleName(baseFormModule.getCnName());
                }
            }
            return baseProcessFormVo;
        });

    }
}
