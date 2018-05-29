package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.DataReportAnalysisFieldDao;
import com.copower.pmcc.assess.dal.entity.DataReportAnalysisField;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service(value = "dataReportAnalysisFieldService")
public class DataReportAnalysisFieldService {

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Autowired
    private BaseDataDicService baseDataDicService;

    @Autowired
    private DataReportAnalysisFieldDao dataReportAnalysisFieldDao;




    public BootstrapTableVo getFieldList(Integer pid,String name) {
        //Bootstrap表格对象
        BootstrapTableVo vo = new BootstrapTableVo();
        //分页参数对象
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(),requestBaseParam.getLimit());
        //查询数据
        List<DataReportAnalysisField> dataReportAnalysesFieldList = null;
        dataReportAnalysesFieldList = dataReportAnalysisFieldDao.getFieldList(pid,name);

        vo.setTotal(page.getTotal());
        vo.setRows(dataReportAnalysesFieldList);
        return vo;
    }

    public boolean addField(DataReportAnalysisField field) {
        Boolean flag = false;
        field.setCreator(processControllerComponent.getThisUser());
        field.setGmtCreated(new Date());
        flag = dataReportAnalysisFieldDao.addField(field);
        return flag;
    }

    public boolean deleteField(Integer id) {
        boolean flag = false;
        flag = dataReportAnalysisFieldDao.deleteField(id);
        return flag;
    }

    public List<DataReportAnalysisField> getDataReportAnalysisField(Integer analysisId) {
       return dataReportAnalysisFieldDao.getDataReportAnalysisField(analysisId);

    }
}
