package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyAssetCommonDataDto;
import com.copower.pmcc.assess.dto.output.basic.SurveyAssetInventoryVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    public void save(ProjectPlanDetails projectPlanDetails, String processInsId, SurveyAssetCommonDataDto surveyAssetCommonDataDto) throws BusinessException {
        save(projectPlanDetails.getId(),projectPlanDetails.getProjectId(),projectPlanDetails.getDeclareRecordId(),processInsId,surveyAssetCommonDataDto) ;
    }

    /**
     * 保存资产清查数据
     *
     * @param planDetailId
     * @param declareId
     * @param projectId
     * @param processInsId
     * @param surveyAssetCommonDataDto
     * @throws BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(Integer planDetailId,Integer projectId, Integer declareId,String processInsId, SurveyAssetCommonDataDto surveyAssetCommonDataDto) throws BusinessException {
        if (surveyAssetCommonDataDto != null) {
            SurveyAssetInventory surveyAssetInventory = surveyAssetCommonDataDto.getSurveyAssetInventory();
            if (surveyAssetInventory == null)
                throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
            if (surveyAssetInventory.getId() != null && surveyAssetInventory.getId() > 0) {
                surveyAssetInventory.setProcessInsId(processInsId);
                surveyAssetInventoryDao.update(surveyAssetInventory);
            } else {
                surveyAssetInventory.setProjectId(projectId);
                surveyAssetInventory.setPlanDetailId(planDetailId);
                surveyAssetInventory.setProcessInsId(processInsId);
                surveyAssetInventory.setDeclareRecordId(declareId);
                surveyAssetInventory.setCreator(commonService.thisUserAccount());
                surveyAssetInventoryDao.save(surveyAssetInventory);
                //更新附件
                baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInventory.class), surveyAssetInventory.getId());
            }
            List<SurveyAssetInventoryContent> assetInventoryContentList = surveyAssetCommonDataDto.getAssetInventoryContentList();
            if (CollectionUtils.isNotEmpty(assetInventoryContentList)) {
                for (SurveyAssetInventoryContent surveyAssetInventoryContent : assetInventoryContentList) {
                    surveyAssetInventoryContent.setMasterId(surveyAssetInventory.getId());
                    surveyAssetInventoryContentService.saveAssetInventoryContent(surveyAssetInventoryContent);
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

    public SurveyAssetInventoryVo getDataByPlanDetailsId(Integer planDetailsId) {
        SurveyAssetInventory dataByPlanDetailsId = surveyAssetInventoryDao.getDataByPlanDetailsId(planDetailsId);
        return getSurveyAssetInventoryVo(dataByPlanDetailsId);
    }

    public SurveyAssetInventory getDataByDeclareId(Integer declareId) {
        return surveyAssetInventoryDao.getDataByDeclareId(declareId);
    }

    public List<SurveyAssetInventory> getDataByDeclareIds(List<Integer> declareIds) {
        return surveyAssetInventoryDao.getDataByDeclareIds(declareIds);
    }

    /**
     * 反写申报记录数据的证载用途与实际用途
     *
     * @param surveyAssetInventory
     */
    @Transactional(rollbackFor = Exception.class)
    public void writeBackDeclareRecord(SurveyAssetInventory surveyAssetInventory) throws BusinessException {
        if (surveyAssetInventory != null) {
            List<SurveyAssetInventoryContent> contentList = surveyAssetInventoryContentService.getContentListByPlanDetailsId(surveyAssetInventory.getPlanDetailId());
            if (CollectionUtils.isNotEmpty(contentList)) {
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(surveyAssetInventory.getDeclareRecordId());
                BaseDataDic areaDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_AREA);
                BaseDataDic addressDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS);

                for (SurveyAssetInventoryContent content : contentList) {
                    //反写实际面积
                    if (content.getInventoryContent().equals(areaDic.getId()) && StringUtils.isNotBlank(content.getActual()) && NumberUtils.isNumber(content.getActual())) {
                        declareRecord.setPracticalArea(new BigDecimal(content.getActual()));
                    }
                    //反写实际地址
                    if (content.getInventoryContent().equals(addressDic.getId()) && StringUtils.isNotBlank(content.getActual())) {
                        declareRecord.setSeat(content.getActual());
                    }
                }
                declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
            }
        }
    }


    public SurveyAssetInventoryVo getSurveyAssetInventoryVo(SurveyAssetInventory surveyAssetInventory) {
        if (surveyAssetInventory == null) {
            return null;
        }
        SurveyAssetInventoryVo vo = new SurveyAssetInventoryVo();
        BeanUtils.copyProperties(surveyAssetInventory, vo);
        if (surveyAssetInventory.getApplication() != null) {
            vo.setApplicationName(baseDataDicService.getNameById(surveyAssetInventory.getApplication()));
        }
        if (StringUtils.isNotBlank(surveyAssetInventory.getCertificate())) {
            vo.setCertificateName(baseDataDicService.getNameById(surveyAssetInventory.getCertificate()));
        }
        return vo;
    }

    public SurveyAssetInventory getSurveyAssetInventoryById(Integer id){
        return surveyAssetInventoryDao.getSurveyAssetInventoryById(id) ;
    }

}
