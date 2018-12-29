package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyAssetCommonDataDto;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by zly on 2018/5/9.
 */

@Service
public class SurveyAssetInventoryService extends BaseService {
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SurveyAssetInventoryDao surveyAssetInventoryDao;
    @Autowired
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    /**
     * 保存资产清查数据
     *
     * @param projectPlanDetails
     * @param processInsId
     * @param surveyAssetCommonDataDto
     * @throws BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(ProjectPlanDetails projectPlanDetails, String processInsId, SurveyAssetCommonDataDto surveyAssetCommonDataDto) throws BusinessException {
        Integer projectId = projectPlanDetails.getProjectId();
        Integer planDetailsId = projectPlanDetails.getId();
        if (surveyAssetCommonDataDto != null) {
            SurveyAssetInventory surveyAssetInventory = surveyAssetCommonDataDto.getSurveyAssetInventory();
            if (surveyAssetInventory == null)
                throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
            if (surveyAssetInventory.getId() != null && surveyAssetInventory.getId() > 0) {
                surveyAssetInventoryDao.update(surveyAssetInventory);
            } else {
                surveyAssetInventory.setProjectId(projectId);
                surveyAssetInventory.setPlanDetailId(planDetailsId);
                surveyAssetInventory.setProcessInsId(processInsId);
                surveyAssetInventory.setDeclareRecordId(projectPlanDetails.getDeclareRecordId());
                surveyAssetInventory.setCreator(commonService.thisUserAccount());
                surveyAssetInventoryDao.save(surveyAssetInventory);

                //更新附件
                baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInventory.class),surveyAssetInventory.getId());
            }
            List<SurveyAssetInventoryContent> assetInventoryContentList = surveyAssetCommonDataDto.getAssetInventoryContentList();
            if (CollectionUtils.isNotEmpty(assetInventoryContentList)) {
                for (SurveyAssetInventoryContent surveyAssetInventoryContent : assetInventoryContentList) {
                    surveyAssetInventoryContentService.save(surveyAssetInventoryContent);
                }
            }
        }
    }

    public SurveyAssetCommonDataDto format(String val) {
        SurveyAssetCommonDataDto dto = null;
        if (StringUtils.isNotBlank(val)) {
            dto = JSON.parseObject(val, SurveyAssetCommonDataDto.class);
        }
        return dto;
    }

    public SurveyAssetInventory getDataByProcessInsId(String processInsId) {
        return surveyAssetInventoryDao.getDataByProcessInsId(processInsId);
    }

    public SurveyAssetInventory getDataByPlanDetailsId(Integer planDetailsId) {
        return surveyAssetInventoryDao.getDataByPlanDetailsId(planDetailsId);
    }

    /**
     * 反写申报记录数据的证载用途与实际用途
     * @param surveyAssetInventory
     */
    public void writeBackDeclareRecord(SurveyAssetInventory surveyAssetInventory){
        if (surveyAssetInventory != null) {
            List<SurveyAssetInventoryContent> contentList = surveyAssetInventoryContentService.getContentListByPlanDetailsId(surveyAssetInventory.getPlanDetailId());
            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_USE);
            if (CollectionUtils.isNotEmpty(contentList) && baseDataDic != null) {
                for (SurveyAssetInventoryContent content : contentList) {
                    if (content.getInventoryContent().equals(baseDataDic.getId())) {
                        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(surveyAssetInventory.getDeclareRecordId());
                        if (declareRecord != null) {
                            declareRecord.setCertUse(content.getRegistration());
                            declareRecord.setPracticalUse(content.getActual());
                            try {
                                declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
                            } catch (BusinessException e) {
                                log.error(e.getMessage(), e);
                            }
                        }
                    }
                }
            }
        }
    }
}
