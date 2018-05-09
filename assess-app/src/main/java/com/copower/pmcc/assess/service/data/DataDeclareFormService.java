package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.dao.DataDeclareFormDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.BaseForm;
import com.copower.pmcc.assess.dal.entity.DataDeclareForm;
import com.copower.pmcc.assess.dto.output.data.DataDeclareFormVo;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseFormService;
import com.copower.pmcc.crm.api.dto.CrmCustomerDto;
import com.copower.pmcc.crm.api.dto.CrmCustomerLinkmanDto;
import com.copower.pmcc.crm.api.provider.CrmRpcCustomerService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 2018-4-24.
 */
@Service
public class DataDeclareFormService {
    @Autowired
    private DataDeclareFormDao dataDeclareFormDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ServiceComponent serviceComponent;
    @Autowired
    private BaseFormService baseFormService;
    @Autowired
    private CrmRpcCustomerService crmRpcCustomerService;

    /**
     * 获取数据列表
     *
     * @return
     */
    public BootstrapTableVo getList(Integer assessClass, String name) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataDeclareForm> list = dataDeclareFormDao.getDataDeclareFormList(assessClass, name);
        List<DataDeclareFormVo> voList = getVoList(list);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(voList) ? new ArrayList<DataDeclareFormVo>() : voList);
        return bootstrapTableVo;
    }

    private List<DataDeclareFormVo> getVoList(List<DataDeclareForm> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            DataDeclareFormVo dataDeclareFormVo = new DataDeclareFormVo();
            BeanUtils.copyProperties(p, dataDeclareFormVo);
            if (p.getAssessClass() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(p.getAssessClass());
                if (baseDataDic != null)
                    dataDeclareFormVo.setAssessClassName(baseDataDic.getName());
            }
            if (p.getFormId() != null) {
                BaseForm baseForm = baseFormService.getBaseForm(p.getFormId());
                if (baseForm != null)
                    dataDeclareFormVo.setFormIdName(baseForm.getCnName());
            }
            return dataDeclareFormVo;
        });
    }

    /**
     * 获取表单
     *
     * @param id
     * @return
     */
    public DataDeclareForm getCacheDataDeclareForm(Integer id) {
        String cacheKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_DECLARE_FORM, String.valueOf(id));
        try {
            DataDeclareForm dataDeclareForm = LangUtils.singleCache(cacheKey, id, DataDeclareForm.class, p -> dataDeclareFormDao.getDataDeclareForm(p));
            return dataDeclareForm;
        } catch (Exception e) {
            return dataDeclareFormDao.getDataDeclareForm(id);
        }
    }

    /**
     * 保存数据
     *
     * @param dataDeclareFormDto
     */
    public void saveData(DataDeclareForm dataDeclareFormDto) throws BusinessException {
        if (dataDeclareFormDto == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (dataDeclareFormDto.getId() != null && dataDeclareFormDto.getId() > 0) {
            dataDeclareFormDao.updateDataDeclareForm(dataDeclareFormDto);
        } else {
            dataDeclareFormDto.setCreator(serviceComponent.getThisUser());
            dataDeclareFormDao.addDataDeclareForm(dataDeclareFormDto);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public void deleteData(Integer id) throws BusinessException {
        if (id == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        dataDeclareFormDao.deleteDataDeclareForm(id);
    }
}
