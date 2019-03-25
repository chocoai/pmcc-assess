package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataReportAnalysisDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.data.DataReportAnalysisVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
    @Autowired
    private DataReportTemplateItemService dataReportTemplateItemService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BasicEstateService basicEstateService;

    /**
     * 保存数据
     *
     * @param dataReportAnalysis
     */
    public void saveAndUpdate(DataReportAnalysis dataReportAnalysis) {
        if (dataReportAnalysis.getId() != null && dataReportAnalysis.getId() > 0) {
            dataReportAnalysisDao.updateReportAnalysis(dataReportAnalysis);
        } else {
            BaseDataDic cacheDataDicByFieldName = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_LIQUIDITY);
            dataReportAnalysis.setReportAnalysisType(cacheDataDicByFieldName.getId());
            dataReportAnalysis.setCreator(commonService.thisUserAccount());
            dataReportAnalysisDao.addReportAnalysis(dataReportAnalysis);
            //修改子模板
            dataReportTemplateItemService.templateItemToSetMasterId(dataReportAnalysis.getId(), SchemeSupportTypeEnum.REPORT_ANALYSIS_CATEGORY_LIQUIDITY.getKey());
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
    public BootstrapTableVo getReportAnalysisList(String name,Integer type, Integer reportAnalysisType) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataReportAnalysis> hypothesisList = dataReportAnalysisDao.getReportAnalysisList(name,type, reportAnalysisType);
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


    public DataReportAnalysisVo getReportAnalysisVo(DataReportAnalysis reportAnalysis) {
        if (reportAnalysis == null) return null;
        DataReportAnalysisVo vo = new DataReportAnalysisVo();
        BeanUtils.copyProperties(reportAnalysis, vo);
        vo.setReportAnalysisTypeName(baseDataDicService.getNameById(reportAnalysis.getReportAnalysisType()));
        List<BaseDataDic> purposeDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        if (StringUtils.isNotBlank(reportAnalysis.getEntrustmentPurpose())) {
            vo.setEntrustmentPurposeName(baseDataDicService.getDataDicName(purposeDicList, reportAnalysis.getEntrustmentPurpose()));
        }
        if(reportAnalysis.getMarketBackgroundType()!=null){
            vo.setMarketBackgroundTypeName(baseDataDicService.getNameById(reportAnalysis.getMarketBackgroundType()));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(reportAnalysis.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(reportAnalysis.getProvince()));//省
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(reportAnalysis.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(reportAnalysis.getCity()));//市或者县
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(reportAnalysis.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(reportAnalysis.getDistrict()));//县
        }

        return vo;
    }

    /**
     * 获取报告分析数据
     *
     * @param type
     * @param entrustmentPurpose
     * @return
     */
    public List<DataReportAnalysis> getDataReportAnalysisList(String province,String city,String district,Integer type, Integer entrustmentPurpose) {
        String entrustmentPurposeString = String.format(",%s,",entrustmentPurpose);
        return dataReportAnalysisDao.getReportAnalysisList(province,city,district,type,entrustmentPurposeString);
    }

    /**
     * 获取上报告的变现分析数据
     * @return
     */
    public String getReportLiquidity(ProjectInfo projectInfo, Integer areaGroupId) throws Exception {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_LIQUIDITY);
        if (baseDataDic == null) return "";
        List<DataReportAnalysis> reportAnalysisList = dataReportAnalysisDao.getReportAnalysisList(baseDataDic.getId());
        if (CollectionUtils.isEmpty(reportAnalysisList)) return "";
        StringBuilder stringBuilder = new StringBuilder();

        //对应委估对象
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaGroupId);
        for (int i = 0; i < reportAnalysisList.size(); i++) {
            DataReportAnalysis dataReportAnalysis = reportAnalysisList.get(i);
            stringBuilder.append("<p style=\"text-indent:2em\">").append(String.format("%s、%s", i + 1, dataReportAnalysis.getName())).append("</p>");
            stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportAnalysis.getTemplate()).append("</p>");
            //开发程度分析
            if (AssessReportFieldConstant.DEVELOPMENT_LEVEL.equals(dataReportAnalysis.getFieldName())) {
                DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.BUILDING_PROFILE);
                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
                    BasicEstate basicEstate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
                    if(StringUtils.isNotBlank(basicEstate.getDescription())) {
                        stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{估价对象号}", judgeObject.getNumber() + "号").replace("#{楼盘概况}", basicEstate.getDescription())).append("</p>");
                    }
                }
            }

        }
        return stringBuilder.toString();
    }
}
