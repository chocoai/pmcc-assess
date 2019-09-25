package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataNumberRuleDao;
import com.copower.pmcc.assess.dal.basis.entity.DataNumberRule;
import com.copower.pmcc.assess.dto.output.data.DataNumberRuleVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value="dataNumberRuleService")
public class DataNumberRuleService {

    @Autowired
    private DataNumberRuleDao dataNumberRuleDao;

    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    public BootstrapTableVo getList(Integer reportType) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataNumberRule> dataNumberRulesList = dataNumberRuleDao.getDataNumberRule(reportType);
        List<DataNumberRuleVo> dataNumberRuleVos = getVoList(dataNumberRulesList);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(dataNumberRuleVos) ? new ArrayList<DataNumberRuleVo>() : dataNumberRuleVos);
        return vo;
    }

    private List<DataNumberRuleVo> getVoList(List<DataNumberRule> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            DataNumberRuleVo dataNumberRuleVo = new DataNumberRuleVo();
            BeanUtils.copyProperties(p, dataNumberRuleVo);
            dataNumberRuleVo.setReportTypeName(baseDataDicService.getNameById(p.getReportType()));
            dataNumberRuleVo.setSameReportTypeName(baseDataDicService.getNameById(p.getSameReportType()));
            return dataNumberRuleVo;
        });
    }

    public DataNumberRule getDataNumberRule(Integer reportType){
        List<DataNumberRule> dataNumberRulesList = dataNumberRuleDao.getDataNumberRule(reportType);
        if(CollectionUtils.isEmpty(dataNumberRulesList)) return null;
        return dataNumberRulesList.get(0);
    }

    public DataNumberRule getDataNumberRule(String fieldName){
        DataNumberRule where=new DataNumberRule();
        where.setFieldName(fieldName);
        List<DataNumberRule> dataNumberRulesList = dataNumberRuleDao.getDataNumberRule(where);
        if(CollectionUtils.isEmpty(dataNumberRulesList)) return null;
        return dataNumberRulesList.get(0);
    }

    public boolean save(DataNumberRule dataNumberRule) throws BusinessException {
        if(dataNumberRule == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if(dataNumberRule.getId() != null && dataNumberRule.getId() > 0){
            return dataNumberRuleDao.update(dataNumberRule);
        }else{
            dataNumberRule.setCreator(processControllerComponent.getThisUser());
            return dataNumberRuleDao.save(dataNumberRule);
        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if(id ==null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());;
        return dataNumberRuleDao.delete(id);
    }

    public List<DataNumberRule> getDataNumberRuleByGroup(String groupName){
        DataNumberRule dataNumberRule = new DataNumberRule();
        dataNumberRule.setGroupName(groupName);
        List<DataNumberRule> dataNumberRulesList = dataNumberRuleDao.getDataNumberRule(dataNumberRule);
        return dataNumberRulesList;
    }

    public List<Integer> getSameGroupReportType(Integer reportType){
        DataNumberRule dataNumberRulesList = getDataNumberRule(reportType);
        List<DataNumberRule> numberRuleGroup = getDataNumberRuleByGroup(dataNumberRulesList.getGroupName());
        //一个分组下的reportType
        List<Integer> reportTypes = LangUtils.transform(numberRuleGroup, o -> o.getReportType());
        //去重
        List<Integer> reportTypeList = generateCommonMethod.removeDuplicate(reportTypes);
        return  reportTypeList;
    }
}
