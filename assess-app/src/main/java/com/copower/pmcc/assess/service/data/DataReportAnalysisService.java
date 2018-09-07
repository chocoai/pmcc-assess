package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataReportAnalysisDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysis;
import com.copower.pmcc.assess.dto.output.data.DataReportAnalysisVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "dataReportAnalysisService")
public class DataReportAnalysisService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataReportAnalysisDao dataReportAnalysisDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ErpAreaService erpAreaService;

    /**
     * 保存数据
     *
     * @param dataReportAnalysis
     */
    public void saveAndUpdate(DataReportAnalysis dataReportAnalysis) {
        if (dataReportAnalysis.getId() != null && dataReportAnalysis.getId() > 0) {
            dataReportAnalysisDao.updateReportAnalysis(dataReportAnalysis);
        } else {
            dataReportAnalysis.setCreator(commonService.thisUserAccount());
            dataReportAnalysisDao.addReportAnalysis(dataReportAnalysis);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean removeReportAnalysis(Integer id) {
        return dataReportAnalysisDao.removeReportAnalysis(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public DataReportAnalysis getReportAnalysis(Integer id) {
        return dataReportAnalysisDao.getReportAnalysis(id);
    }


    /**
     * 获取数据列表
     *
     * @param name
     * @return
     */
    public BootstrapTableVo getReportAnalysisList(String name,Integer reportAnalysisType) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataReportAnalysis> hypothesisList = dataReportAnalysisDao.getReportAnalysisList(name,reportAnalysisType);
        List<DataReportAnalysisVo> vos = LangUtils.transform(hypothesisList, p -> getReportAnalysisVo(p));
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<DataReportAnalysisVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    /**
     * 根据取数据列表
     *
     * @param reportAnalysisType
     * @return
     */
    public List<DataReportAnalysis> getReportAnalysisList(Integer reportAnalysisType) {
        return dataReportAnalysisDao.getReportAnalysisList(reportAnalysisType);
    }


    public DataReportAnalysisVo getReportAnalysisVo(DataReportAnalysis evaluationReportAnalysis) {
        if (evaluationReportAnalysis == null) return null;
        DataReportAnalysisVo vo = new DataReportAnalysisVo();
        BaseDataDic baseDataDic = null;
        BeanUtils.copyProperties(evaluationReportAnalysis, vo);
        if (evaluationReportAnalysis.getReportAnalysisType() != null) {
            baseDataDic = baseDataDicService.getCacheDataDicById(evaluationReportAnalysis.getReportAnalysisType());
            if (baseDataDic != null) {
                vo.setReportAnalysisTypeName(baseDataDic.getName());
                baseDataDic = null;
            }
        }
        if (StringUtils.isNotBlank(evaluationReportAnalysis.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(evaluationReportAnalysis.getProvince()));//省
        }
        if (StringUtils.isNotBlank(evaluationReportAnalysis.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(evaluationReportAnalysis.getCity()));//市或者县
        }
        if (StringUtils.isNotBlank(evaluationReportAnalysis.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(evaluationReportAnalysis.getDistrict()));//县
        }
        if (!org.springframework.util.StringUtils.isEmpty(evaluationReportAnalysis.getEntrustment())){
            if (NumberUtils.isNumber(evaluationReportAnalysis.getEntrustment())){
                baseDataDic = baseDataDicService.getDataDicById(Integer.parseInt(evaluationReportAnalysis.getEntrustment()));
                if (baseDataDic != null){
                    vo.setEntrustmentName(baseDataDic.getName());
                    baseDataDic = null;
                }
            }
        }
        if (!org.springframework.util.StringUtils.isEmpty(evaluationReportAnalysis.getPurpose())){
            if (NumberUtils.isNumber(evaluationReportAnalysis.getPurpose())){
                baseDataDic = baseDataDicService.getDataDicById(Integer.parseInt(evaluationReportAnalysis.getPurpose()));
                if (baseDataDic != null){
                    vo.setPurposeName(baseDataDic.getName());
                    baseDataDic = null;
                }
            }
        }
        return vo;
    }

}
