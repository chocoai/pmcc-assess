package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.enums.CaseComparisonEnum;
import com.copower.pmcc.assess.dal.dao.CaseComparisonDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.data.CaseComparisonDto;
import com.copower.pmcc.assess.dto.output.data.CaseComparisonVo;
import com.copower.pmcc.assess.dto.output.data.DataNumberRuleVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseFormModuleService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 3.1.2.15	案例对比配置
 * Created by 13426 on 2018/5/3.
 */
@Service
public class CaseComparisonService {

    @Autowired
    private CaseComparisonDao caseComparisonDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private BaseFormModuleService baseFormModuleService;



    @Transactional
    public boolean add(CaseComparisonDto dto) {
        if (dto.getCreator() == null || dto.getCreator() == "") dto.setCreator(commonService.thisUserAccount());
        if (dto.getGmtCreated() == null) dto.setGmtCreated(new Date());
        return caseComparisonDao.add(dto);
    }

    @Transactional
    public boolean remove(Integer id) {
        return caseComparisonDao.remove(id);
    }

    @Transactional
    public boolean update(CaseComparisonDto dto) {
        if (dto.getCreator() == null || dto.getCreator() == "") dto.setCreator(commonService.thisUserAccount());
        if (dto.getGmtCreated() == null) dto.setGmtCreated(new Date());
        return caseComparisonDao.update(dto);
    }

//    @Transactional(readOnly = true)
//    public CaseComparisonVo get(Integer id) {
//        return change(caseComparisonDao.get(id));
//    }

    @Transactional(readOnly = true)
    private List<DataCaseComparison> list(String name) {
        List<DataCaseComparison> list = caseComparisonDao.list(name);
        return list;
    }

//    public BootstrapTableVo listVos(String name) {
//        BootstrapTableVo vo = new BootstrapTableVo();
//        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
//        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
//        List<DataCaseComparison> list = list(name);
//        List<CaseComparisonVo> vos = LangUtils.transform(list, p -> {
//            return change(p);
//        });
//        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CaseComparisonVo>() : vos);
//        vo.setTotal(page.getTotal());
//        return vo;
//    }

    private CaseComparisonDto change(CaseComparisonVo vo) {
        CaseComparisonDto dto = new CaseComparisonVo();
        BeanUtils.copyProperties(vo, dto);
        return dto;
    }

//    private CaseComparisonVo change(DataCaseComparison dataCaseComparison) {
//        CaseComparisonVo vo = new CaseComparisonVo();
//        BeanUtils.copyProperties(dataCaseComparison, vo);
//        if (dataCaseComparison.getFormTypeId() != null && dataCaseComparison.getFormTypeId() > 0) {
//            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(dataCaseComparison.getFormTypeId());
//            if (baseDataDic != null) {
//                vo.setFormTypeName(baseDataDic.getName());
//            }
//        }
//        return vo;
//    }

    public Map<Integer, Object> getTypeMap() {
        Map<Integer, Object> map = new HashMap<>();
        map.put(CaseComparisonEnum.CASE_COMPARISON_ONE_ENUM.getNum(), CaseComparisonEnum.Text.getVar());
        map.put(CaseComparisonEnum.CASE_COMPARISON_TWO_ENUM.getNum(), CaseComparisonEnum.NO_Text.getVar());
        return map;
    }

    public List<DataCaseComparison> getAll() {
       return caseComparisonDao.getAll();
    }

    public BootstrapTableVo getDataByExploreFormType(Integer exploreFormType) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataCaseComparison> dataCaseComparisonList = caseComparisonDao.getDataByExploreFormType(exploreFormType);
        List<CaseComparisonVo> caseComparisonVoList = getVoList(dataCaseComparisonList);
//        for (DataCaseComparison dataCaseComparison:dataCaseComparisonList){
//            Integer caseFormType = dataCaseComparison.getCaseFormType();
//            BaseProjectClassify baseProjectClassify = baseProjectClassifyService.getDataById(caseFormType);
//            String name = baseProjectClassify.getName();
//            CaseComparisonVo caseComparisonVo = new CaseComparisonVo();
//        }
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(caseComparisonVoList) ? new ArrayList<CaseComparisonVo>() : caseComparisonVoList);
//        BaseProjectClassify baseProjectClassify = baseProjectClassifyService.getDataById(id);
//        Integer formModuleId = baseProjectClassify.getFormModuleId();
//        BaseFormModule baseFormModule = baseFormModuleService.getDataById(formModuleId);
        return vo;
    }

    private List<CaseComparisonVo> getVoList(List<DataCaseComparison> list) {
        if (org.apache.commons.collections.CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            CaseComparisonVo caseComparisonVo = new CaseComparisonVo();
            BeanUtils.copyProperties(p, caseComparisonVo);
            Integer exploreFormType = p.getExploreFormType();
            BaseProjectClassify projectClassify = baseProjectClassifyService.getDataById(exploreFormType);
            Integer formModuleId = projectClassify.getFormModuleId();
            BaseFormModule baseFormModule = baseFormModuleService.getDataById(formModuleId);
            String tableName = baseFormModule.getTableName();
            caseComparisonVo.setTableName(tableName);
            if (p.getCaseFormType() != null) {
                BaseProjectClassify baseProjectClassify = baseProjectClassifyService.getDataById(p.getCaseFormType());
                if (baseProjectClassify != null)
                    caseComparisonVo.setCaseFormTypeName(baseProjectClassify.getName());
            }
            return caseComparisonVo;
        });
    }
}
