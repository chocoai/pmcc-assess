package com.copower.pmcc.assess.service.project.survey;


import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.common.enums.DeclareCertificateTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryContentDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryContent;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryContentVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyAssetInventoryContentService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveyAssetInventoryContentDao surveyAssetInventoryContentDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
    @Autowired
    private ProjectInfoService projectInfoService;

    public BootstrapTableVo getList(Integer planDetailsId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetInventoryContent> surveyAssetInventoryContentsList = surveyAssetInventoryContentDao.getSurveyAssetInventoryContent(planDetailsId);
        List<SurveyAssetInventoryContentVo> surveyAssetInventoryContentVos = getVoList(surveyAssetInventoryContentsList);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(surveyAssetInventoryContentVos) ? new ArrayList<SurveyAssetInventoryContent>() : surveyAssetInventoryContentVos);
        return vo;
    }

    public List<SurveyAssetInventoryContent> getContentListByPlanDetailsId(Integer planDetailsId) {
        List<SurveyAssetInventoryContent> surveyAssetInventoryContentsList = surveyAssetInventoryContentDao.getSurveyAssetInventoryContent(planDetailsId);
        return surveyAssetInventoryContentsList;
    }

    public List<SurveyAssetInventoryContentVo> getVoList(List<SurveyAssetInventoryContent> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            SurveyAssetInventoryContentVo surveyAssetInventoryContentVo = new SurveyAssetInventoryContentVo();
            BeanUtils.copyProperties(p, surveyAssetInventoryContentVo);
            surveyAssetInventoryContentVo.setInventoryContentName(baseDataDicService.getNameById(p.getInventoryContent()));
            return surveyAssetInventoryContentVo;
        });
    }

    public void saveAssetInventoryContent(SurveyAssetInventoryContent surveyAssetInventoryContent) throws BusinessException {
        if (surveyAssetInventoryContent == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (surveyAssetInventoryContent.getId() != null && surveyAssetInventoryContent.getId() > 0) {
            surveyAssetInventoryContentDao.update(surveyAssetInventoryContent);
        } else {
            surveyAssetInventoryContent.setCreator(commonService.thisUserAccount());
            surveyAssetInventoryContentDao.add(surveyAssetInventoryContent);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInventoryContent.class), surveyAssetInventoryContent.getId());
        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if (id == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        return surveyAssetInventoryContentDao.delete(id);
    }

    /**
     * 初始化资产清查项
     *
     * @param projectPlanDetails
     * @param declareRecord
     * @return
     */
    public List<SurveyAssetInventoryContent> initAssetInventoryContent(ProjectPlanDetails projectPlanDetails, DeclareRecord declareRecord) {
        List<SurveyAssetInventoryContent> inventoryContents = getContentListByPlanDetailsId(projectPlanDetails.getId());
        if(CollectionUtils.isNotEmpty(inventoryContents)) return inventoryContents;
        List<SurveyAssetInventoryContent> inventoryContentList = Lists.newArrayList();
        if (declareRecord == null) return inventoryContentList;
        List<BaseDataDic> baseDataDicList = baseDataDicService.getCacheDataDicList(declareRecord.getInventoryContentKey());
        if (CollectionUtils.isEmpty(baseDataDicList)) return inventoryContentList;
        AssessProjectTypeEnum projectType = projectInfoService.getAssessProjectType(projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId()).getProjectCategoryId());
        for (BaseDataDic baseDataDic : baseDataDicList) {
            SurveyAssetInventoryContent surveyAssetInventoryContent = new SurveyAssetInventoryContent();
            surveyAssetInventoryContent.setProjectId(projectPlanDetails.getProjectId());
            surveyAssetInventoryContent.setPlanDetailsId(projectPlanDetails.getId());
            surveyAssetInventoryContent.setDeclareId(declareRecord.getId());
            surveyAssetInventoryContent.setInventoryContent(baseDataDic.getId());
            if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS.equals(baseDataDic.getFieldName())) {//登记地址与实际地址
                surveyAssetInventoryContent.setRegistration(declareRecord.getSeat());
                inventoryContentList.add(surveyAssetInventoryContent);
            }
            if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_STRUCTURE.equals(baseDataDic.getFieldName())) {//登记结构与实际结构
                if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE.equals(projectType)) {
                    surveyAssetInventoryContent.setRegistration(declareRecord.getHousingStructure());
                    inventoryContentList.add(surveyAssetInventoryContent);
                }
            }
            if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_USE.equals(baseDataDic.getFieldName())) {//登记用途与实际用途
                if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE.equals(projectType)) {
                    surveyAssetInventoryContent.setRegistration(declareRecord.getCertUse());
                } else if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_LAND.equals(projectType)) {
                    surveyAssetInventoryContent.setRegistration(declareRecord.getLandCertUse());
                }
                inventoryContentList.add(surveyAssetInventoryContent);
            }
            if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_SPACE.equals(baseDataDic.getFieldName())) {//登记空间位置与实际空间位置
                if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_HOUSE.equals(projectType)) {
                    inventoryContentList.add(surveyAssetInventoryContent);
                }
            }
            if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_HOUSE_LAND_ADDRESS.equals(baseDataDic.getFieldName())) {//房产证与土地证证载地址
                if (DeclareCertificateTypeEnum.HOUSE.getKey().equals(declareRecord.getType()) || DeclareCertificateTypeEnum.LAND.getKey().equals(declareRecord.getType())) {
                    surveyAssetInventoryContent.setRegistration(declareRecord.getSeat());
                    inventoryContentList.add(surveyAssetInventoryContent);
                }
            }
            if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_AREA.equals(baseDataDic.getFieldName())) {//登记面积与实际面积
                if (declareRecord.getFloorArea() != null)
                    surveyAssetInventoryContent.setRegistration(String.valueOf(declareRecord.getFloorArea()));
                inventoryContentList.add(surveyAssetInventoryContent);
            }
            if (AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_FOUR_TO_LAND.equals(baseDataDic.getFieldName())) {//土地四至
                if (AssessProjectTypeEnum.ASSESS_PROJECT_TYPE_LAND.equals(projectType)) {
                    inventoryContentList.add(surveyAssetInventoryContent);
                }
            }
        }
        for (SurveyAssetInventoryContent item : inventoryContentList) {
            try {
                surveyAssetInventoryContentService.saveAssetInventoryContent(item);
            } catch (BusinessException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return inventoryContentList;
    }
}
