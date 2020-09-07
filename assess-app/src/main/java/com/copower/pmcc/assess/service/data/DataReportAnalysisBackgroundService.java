package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataReportAnalysisDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysis;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DataReportAnalysisBackgroundService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataReportAnalysisDao dataReportAnalysisDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private DataReportTemplateItemService dataReportTemplateItemService;

    /**
     * 保存数据
     *
     * @param dataReportAnalysis
     */
    public void saveAndUpdate(DataReportAnalysis dataReportAnalysis) {
        if (dataReportAnalysis.getId() != null && dataReportAnalysis.getId() > 0) {
            dataReportAnalysisDao.updateReportAnalysis(dataReportAnalysis);
        } else {
            BaseDataDic cacheDataDicByFieldName = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND);
            dataReportAnalysis.setReportAnalysisType(cacheDataDicByFieldName.getId());
            dataReportAnalysis.setCreator(commonService.thisUserAccount());
            dataReportAnalysisDao.addReportAnalysis(dataReportAnalysis);
            //修改子模板
            dataReportTemplateItemService.templateItemToSetMasterId(dataReportAnalysis.getId(), SchemeSupportTypeEnum.REPORT_ANALYSIS_CATEGORY_MARKET.getKey());
        }
    }

    /**
     * 获取该区域配置的模板
     *
     * @param areaId
     * @param type
     * @return
     */
    public DataReportAnalysis getReportAnalysisByAreaId(Integer areaId, Integer type) {
        if (areaId == null || type == null) return null;
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(areaId);
        if (schemeAreaGroup == null) return null;
        BaseDataDic cacheDataDicByFieldName = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND);
        DataReportAnalysis dataReportAnalysis = new DataReportAnalysis();
        dataReportAnalysis.setMarketBackgroundType(type);
        dataReportAnalysis.setProvince(schemeAreaGroup.getProvince());
        dataReportAnalysis.setCity(schemeAreaGroup.getCity());
        dataReportAnalysis.setDistrict(schemeAreaGroup.getDistrict());
        dataReportAnalysis.setRelYear(DateUtils.getYear(schemeAreaGroup.getValueTimePoint()));
        dataReportAnalysis.setReportAnalysisType(cacheDataDicByFieldName.getId());
        List<DataReportAnalysis> dataReportAnalysisList = dataReportAnalysisDao.getDataReportAnalysisList(dataReportAnalysis);
        if (CollectionUtils.isEmpty(dataReportAnalysisList)) return null;
        return dataReportAnalysisList.get(0);
    }
}
