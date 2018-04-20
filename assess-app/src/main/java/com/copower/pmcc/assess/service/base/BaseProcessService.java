package com.copower.pmcc.assess.service.base;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.dao.BaseProcessDao;
import com.copower.pmcc.assess.dal.entity.BaseFormModule;
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
    private ApplicationConstant applicationConstant;
    @Autowired
    private BaseFormService baseFormService;

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
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(hrBaseProcessList) ? new ArrayList<BaseProcess>() : hrBaseProcessList);
        return bootstrapTableVo;
    }

    //+++++++++++++++++++===================================================
    public List<BaseProcessForm> getProcessFormByProcess(Integer processId) {
        return hrProcessDao.getProcessFormByProcess(processId);
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
