package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataReportAnalysisDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataReportAnalysis;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.common.CommonService;
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
    private ErpAreaService erpAreaService;
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

    public DataReportAnalysis getReportAnalysisByAreaId(String district, Integer type, Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        BaseDataDic cacheDataDicByFieldName = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_BACKGROUND);
        DataReportAnalysis dataReportAnalysis = new DataReportAnalysis();
        dataReportAnalysis.setMarketBackgroundType(type);
        dataReportAnalysis.setDistrict(district);
        String formatTime = sdf.format(time);
        dataReportAnalysis.setRelYear(Integer.valueOf(formatTime));
        dataReportAnalysis.setReportAnalysisType(cacheDataDicByFieldName.getId());
        List<DataReportAnalysis> dataReportAnalysisList = dataReportAnalysisDao.getDataReportAnalysisList(dataReportAnalysis);
        if(CollectionUtils.isNotEmpty(dataReportAnalysisList)){
            return dataReportAnalysisList.get(0);
        }
        dataReportAnalysis.setRelYear(null);
        dataReportAnalysisList = dataReportAnalysisDao.getDataReportAnalysisList(dataReportAnalysis);
        if(CollectionUtils.isNotEmpty(dataReportAnalysisList)){
            return dataReportAnalysisList.get(0);
        }
        return null;
    }
}
