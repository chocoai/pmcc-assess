package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.data.CaseComparisonFieldDao;
import com.copower.pmcc.assess.dal.basis.entity.DataCaseComparisonField;
import com.copower.pmcc.assess.dto.input.data.CaseComparisonFieldDto;
import com.copower.pmcc.assess.dto.output.data.CaseComparisonFieldVo;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 13426 on 2018/5/3.
 */
@Service
public class CaseComparisonFieldService {

    @Autowired
    private CaseComparisonFieldDao dao;

    @Autowired
    private FormConfigureService configureService;
    @Autowired
    private CommonService commonService;

    @Transactional
    public boolean add(CaseComparisonFieldDto dto) {
        if (dto.getCreator()==null)dto.setCreator(commonService.thisUserAccount());
        if (dto.getGmtCreated()==null)dto.setGmtCreated(new Date());
        return dao.add(dto);
    }

    @Transactional
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    @Transactional
    public boolean update(CaseComparisonFieldDto dto) {
        return dao.update(dto);
    }

    public BootstrapTableVo listVos(Integer caseId,String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataCaseComparisonField> vos = dao.list(caseId,name);
        List<CaseComparisonFieldVo> caseComparisonFieldVoList = getVoList(vos);
//        List<CaseComparisonFieldVo> caseComparisonFieldVos = new ArrayList<CaseComparisonFieldVo>();
//        for(DataCaseComparisonField dataCaseComparisonField:vos){
//            CaseComparisonFieldVo caseComparisonFieldVo = new CaseComparisonFieldVo();
//            Integer type = dataCaseComparisonField.getType();
//            String typeName = ExamineTypeEnum.getName(type);
//            caseComparisonFieldVo.setuName(dataCaseComparisonField.getuName());
//            caseComparisonFieldVo.setTypeName(typeName);
//            caseComparisonFieldVo.setTableName(dataCaseComparisonField.getTableName());
//            caseComparisonFieldVo.setFieldName(dataCaseComparisonField.getFieldName());
//            caseComparisonFieldVos.add(caseComparisonFieldVo);
//        }
        vo.setRows(CollectionUtils.isEmpty(caseComparisonFieldVoList) ? new ArrayList<CaseComparisonFieldVo>() : caseComparisonFieldVoList);
        vo.setTotal(page.getTotal());
        return vo;
    }

    private List<CaseComparisonFieldVo> getVoList(List<DataCaseComparisonField> list) {
        if (org.apache.commons.collections.CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            CaseComparisonFieldVo caseComparisonFieldVo = new CaseComparisonFieldVo();
            BeanUtils.copyProperties(p, caseComparisonFieldVo);

            if (p.getType() != null) {
                String name = ExamineTypeEnum.getName(p.getType());
                if (name != null)
                    caseComparisonFieldVo.setTypeName(name);
            }
            return caseComparisonFieldVo;
        });
    }

    public List<KeyValueDto> getTableList() {
        return configureService.getTableList();
    }

    public List<KeyValueDto> getFieldList(String tableName){
        return configureService.getFieldList(tableName);
    }

    public List<DataCaseComparisonField> getData(Integer caseId) {
        return dao.getData(caseId);
    }
}
