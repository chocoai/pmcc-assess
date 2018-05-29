package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.CaseComparisonFieldDao;
import com.copower.pmcc.assess.dal.entity.DataCaseComparisonField;
import com.copower.pmcc.assess.dto.input.data.CaseComparisonFieldDto;
import com.copower.pmcc.assess.dto.output.data.CaseComparisonFieldVo;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
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
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CaseComparisonFieldVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<KeyValueDto> getTableList() {
        return configureService.getTableList();
    }

    public List<KeyValueDto> getFieldList(String tableName){
        return configureService.getFieldList(tableName);
    }

}
