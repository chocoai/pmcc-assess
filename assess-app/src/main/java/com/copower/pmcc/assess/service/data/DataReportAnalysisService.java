package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.data.DataReportAnalysisDao;
import com.copower.pmcc.assess.dal.dao.data.DataReportAnalysisFieldDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.DataReportAnalysis;
import com.copower.pmcc.assess.dal.entity.DataReportAnalysisField;
import com.copower.pmcc.assess.dto.output.data.DataReportAnalysisVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service(value = "dataReportAnalysisService")
public class DataReportAnalysisService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CommonService commonService;

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Autowired
    private DataReportAnalysisDao analysisDao;

    @Autowired
    private DataReportAnalysisFieldDao fieldDao;

    @Autowired
    private BaseDataDicService baseDataDicService;


    /**
     * 新增一条报告分析
     *
     * @param dataReportAnalysis 报告分析entity
     * @return true or false
     * @throws Exception
     */
    @Transactional
    public boolean addDataReportAnalysis(DataReportAnalysis dataReportAnalysis) throws Exception {
        boolean flag = false;
        dataReportAnalysis.setCreator(processControllerComponent.getThisUser());
        dataReportAnalysis.setGmtCreated(new Date());
        flag = analysisDao.addDataReportAnalysis(dataReportAnalysis);
        return flag;
    }

    @Transactional
    public void saveAndUpdate(DataReportAnalysis oo, String field) {
        if (!ObjectUtils.isEmpty(oo.getId())) {//update
            oo.setCreator(analysisDao.getReportAnalysis(oo.getId()).getCreator());
            analysisDao.update(oo);
            if (!StringUtils.isEmpty(field)) {// 字段
                // 因为是修改所以可能所有的数据数据库中都已经有相关信息了  有可能增加一些字段,有可能删去一些字段
                String[] fields = field.split(",");
                for (String f : fields) {
                    if (!StringUtils.isEmpty(f)) fieldDao.delete(f, oo.getId());
                }
                for (String f : fields) {
                    if (!StringUtils.isEmpty(f)) {
                        DataReportAnalysisField fieldDto = new DataReportAnalysisField();
                        fieldDto.setName(f);
                        fieldDto.setCreator(commonService.thisUserAccount());
                        fieldDto.setAnalysisId(oo.getId());
                        try {
                            fieldDao.addField(fieldDto);//会自动判断是否存在已经添加过的字段
                        } catch (Exception e) {
                            try {
                                logger.error("错误打印!" + e.getMessage());
                            } catch (Exception e1) {
                                throw e;
                            }
                        }
                    }
                }
            }

        } else {// add
            oo.setCreator(commonService.thisUserAccount());
            Integer id = null;
            try {
                id = analysisDao.save(oo);
            } catch (Exception e) {
                try {
                    logger.error("异常啦!" + e.getMessage());
                    throw e;
                } catch (Exception e1) {

                }
            }
            if (!StringUtils.isEmpty(field)) {
                String[] fields = field.split(",");
                for (String f : fields) {//字段
                    if (!StringUtils.isEmpty(f)) {
                        DataReportAnalysisField fieldDto = new DataReportAnalysisField();
                        fieldDto.setName(f);
                        fieldDto.setCreator(commonService.thisUserAccount());
                        fieldDto.setAnalysisId(id);
                        try {
                            if (id != null) fieldDao.addField(fieldDto);//会自动判断是否存在已经添加过的字段
                        } catch (Exception e) {
                            try {
                                logger.error("错误打印!" + e.getMessage());
                            } catch (Exception e1) {
                                throw e;
                            }
                        }
                    }
                }

            }
        }
    }

    @Transactional
    public boolean update(DataReportAnalysis dataReportAnalysis) {
        DataReportAnalysis dataReportAnalysis1 = analysisDao.getReportAnalysis(dataReportAnalysis.getId());
        dataReportAnalysis.setCreator(dataReportAnalysis1.getCreator());
        return analysisDao.update(dataReportAnalysis);
    }

    /**
     * 查询报告分析列表
     *
     * @param keyWord 查询条件
     * @return BootstrapTableVo 表格对象
     * @throws Exception
     */
    public BootstrapTableVo getList(String keyWord) throws Exception {
        //Bootstrap表格对象
        BootstrapTableVo vo = new BootstrapTableVo();
        //分页参数对象
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        //查询数据
        List<DataReportAnalysis> dataReportAnalysesList = null;
        List<DataReportAnalysisVo> dataReportAnalysesVoList = null;
        dataReportAnalysesList = analysisDao.getList(keyWord);
        dataReportAnalysesVoList = this.getVoList(dataReportAnalysesList);

        vo.setTotal(page.getTotal());
        vo.setRows(dataReportAnalysesVoList);
        return vo;
    }

    /**
     * 讲字典数据id转换成对应的文本
     *
     * @param list 数据列表
     * @return entity派生类集合
     */
    private List<DataReportAnalysisVo> getVoList(List<DataReportAnalysis> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            return LangUtils.transform(list, p -> {
                DataReportAnalysisVo vo = new DataReportAnalysisVo();
                BeanUtils.copyProperties(p, vo);
                if (p.getCategory() != null) {
                    BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(p.getCategory());
                    if (baseDataDic != null) {
                        vo.setCategoryName(baseDataDic.getName());
                    }
                }
                if (p.getCategoryField() != null) {
                    BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(p.getCategoryField());
                    if (baseDataDic != null) {
                        vo.setCategoryFieldName(baseDataDic.getName());
                    }
                }
                return vo;
            });

        } else {
            return null;
        }
    }

    public DataReportAnalysis getReportAnalysis(Integer id) throws Exception {
        return analysisDao.getReportAnalysis(id);
    }

    /**
     * 删除报告分析以及其所有子项
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteReportAnalysis(Integer id) throws Exception {
        boolean flag = false;
        //删除子项
        fieldDao.deleteFields(id);
        //删除主项
        flag = analysisDao.deleteReportAnalysis(id);
        return flag;
    }

    public List<DataReportAnalysisVo> getDataReportAnalysisByCategory(Integer category) {
        List<DataReportAnalysis> dataReportAnalyses = analysisDao.getDataReportAnalysisByCategory(category);
        List<DataReportAnalysisVo> dataReportAnalysisVos = getVoList(dataReportAnalyses);
        return dataReportAnalysisVos;
    }
}
