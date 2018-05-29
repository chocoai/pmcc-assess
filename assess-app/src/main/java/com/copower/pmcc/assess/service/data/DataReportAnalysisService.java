package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.DataReportAnalysisDao;
import com.copower.pmcc.assess.dal.dao.DataReportAnalysisFieldDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.DataReportAnalysis;
import com.copower.pmcc.assess.dto.output.data.DataReportAnalysisVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service(value = "dataReportAnalysisService")
public class DataReportAnalysisService {

    @Autowired

    private ProcessControllerComponent processControllerComponent;

    @Autowired
    private DataReportAnalysisDao dataReportAnalysisDao;

    @Autowired
    private DataReportAnalysisFieldDao dataReportAnalysisFieldDao;

    @Autowired
    private BaseDataDicService baseDataDicService;


    /**
     *  新增一条报告分析
     * @param dataReportAnalysis 报告分析entity
     * @return true or false
     * @throws Exception
     */
    public boolean addDataReportAnalysis(DataReportAnalysis dataReportAnalysis) throws Exception{
        boolean flag = false;
        dataReportAnalysis.setCreator(processControllerComponent.getThisUser());
        dataReportAnalysis.setGmtCreated(new Date());
        flag = dataReportAnalysisDao.addDataReportAnalysis(dataReportAnalysis);
        return flag;
    }

    /**
     * 查询报告分析列表
     * @param keyWord 查询条件
     * @return BootstrapTableVo 表格对象
     * @throws Exception
     */
    public BootstrapTableVo getList(String keyWord) throws Exception{
        //Bootstrap表格对象
        BootstrapTableVo vo = new BootstrapTableVo();
        //分页参数对象
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(),requestBaseParam.getLimit());
        //查询数据
        List<DataReportAnalysis> dataReportAnalysesList = null;
        List<DataReportAnalysisVo> dataReportAnalysesVoList = null;
        dataReportAnalysesList = dataReportAnalysisDao.getList(keyWord);
        dataReportAnalysesVoList = this.getVoList(dataReportAnalysesList);

        vo.setTotal(page.getTotal());
        vo.setRows(dataReportAnalysesVoList);
        return vo;
    }

    /**
     * 讲字典数据id转换成对应的文本
     * @param list 数据列表
     * @return entity派生类集合
     */
    private List<DataReportAnalysisVo> getVoList(List<DataReportAnalysis> list) {
        if(CollectionUtils.isNotEmpty(list)){
            return LangUtils.transform(list, p ->{
                DataReportAnalysisVo vo = new DataReportAnalysisVo();
                BeanUtils.copyProperties(p,vo);
                if(p.getCategory() != null){
                    BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(p.getCategory());
                    if(baseDataDic != null){
                        vo.setCategoryName(baseDataDic.getName());
                    }
                }
                if(p.getCategoryField() != null){
                    BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(p.getCategoryField());
                    if(baseDataDic != null){
                        vo.setCategoryFieldName(baseDataDic.getName());
                    }
                }
                return vo;
            });

        }else{
            return null;
        }
    }

    public DataReportAnalysis getReportAnalysis(Integer id) throws  Exception{
        return dataReportAnalysisDao.getReportAnalysis(id);
    }

    /**
     *删除报告分析以及其所有子项
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteReportAnalysis(Integer id) throws Exception{
        boolean flag = false;
        //删除子项
        dataReportAnalysisFieldDao.deleteFields(id);
        //删除主项
        flag = dataReportAnalysisDao.deleteReportAnalysis(id);
        return flag;
    }

    public List<DataReportAnalysisVo> getDataReportAnalysisByCategory(Integer category) {
        List<DataReportAnalysis> dataReportAnalyses = dataReportAnalysisDao.getDataReportAnalysisByCategory(category);
        List<DataReportAnalysisVo> dataReportAnalysisVos = getVoList(dataReportAnalyses);
        return dataReportAnalysisVos;
    }
}
