package com.copower.pmcc.assess.service.project.survey;


import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessProjectClassifyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryContentDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryContentVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SurveyAssetInventoryContentService {
    @Autowired
    private SurveyAssetInventoryContentDao surveyAssetInventoryContentDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

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

    public void save(SurveyAssetInventoryContent surveyAssetInventoryContent) throws BusinessException {
        if (surveyAssetInventoryContent == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (surveyAssetInventoryContent.getId() != null && surveyAssetInventoryContent.getId() > 0) {
            surveyAssetInventoryContentDao.update(surveyAssetInventoryContent);
        } else {
            surveyAssetInventoryContent.setCreator(commonService.thisUserAccount());
            surveyAssetInventoryContentDao.save(surveyAssetInventoryContent);

            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInventoryContent.class), surveyAssetInventoryContent.getId());
        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if (id == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        ;
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
        //房产类型345
        BaseProjectClassify houseProperty = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.SINGLE_HOUSE_PROPERTY_CERTIFICATE_TYPE_SIMPLE);
        //土地类型346
        BaseProjectClassify houseLand = baseProjectClassifyService.getCacheProjectClassifyByFieldName(AssessProjectClassifyConstant.SINGLE_HOUSE_LAND_CERTIFICATE_TYPE_SIMPLE);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectPlanDetails.getProjectId());
        //项目类型
        Integer projectCategoryId = projectInfo.getProjectCategoryId();
        List<BaseDataDic> inventoryContentList = Lists.newArrayList();
        //房产
        if (houseProperty.getId().equals(projectCategoryId)) {
            inventoryContentList = baseDataDicService.getCacheDataDicList(declareRecord.getInventoryContentKey());
            Iterator<BaseDataDic> iterator = inventoryContentList.iterator();
            while (iterator.hasNext()) {
                BaseDataDic item = iterator.next();
                if (item.getFieldName().equals(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_FOUR_TO_LAND)) {
                    iterator.remove();
                }
            }
        }
        //土地
        if (houseLand.getId().equals(projectCategoryId)) {
            inventoryContentList.add(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS));
            inventoryContentList.add(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_USE));
            inventoryContentList.add(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_AREA));
            if (!declareRecord.getHasCert()) {
                inventoryContentList.add(baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_FOUR_TO_LAND));
            }
        }
        Collections.sort(inventoryContentList, Comparator.comparing(BaseDataDic::getSorting).reversed());//降序排列
        List<SurveyAssetInventoryContent> list = surveyAssetInventoryContentDao.getSurveyAssetInventoryContent(projectPlanDetails.getId());

        if (CollectionUtils.isEmpty(list)) {
            for (BaseDataDic baseDataDic : inventoryContentList) {
                Integer projectId = projectPlanDetails.getProjectId();
                SurveyAssetInventoryContent surveyAssetInventoryContent = new SurveyAssetInventoryContent();
                surveyAssetInventoryContent.setProjectId(projectId);
                surveyAssetInventoryContent.setPlanDetailsId(projectPlanDetails.getId());
                surveyAssetInventoryContent.setInventoryContent(baseDataDic.getId());
                if (projectCategoryId.equals(houseProperty.getId())) {
                    if (StringUtils.isNotBlank(baseDataDic.getFieldName())) {
                        switch (baseDataDic.getFieldName()) {
                            case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS://登记地址与实际地址
                            case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_HOUSE_LAND_ADDRESS://房产证与土地证证载地址
                                surveyAssetInventoryContent.setRegistration(declareRecord.getSeat());
                                break;
                            case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_USE://登记用途与实际用途
                                if (StringUtils.isNotBlank(declareRecord.getCertUse())) {
                                    surveyAssetInventoryContent.setRegistration(declareRecord.getCertUse());
                                }
                                break;
                            case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_STRUCTURE://登记结构与实际结构
                                if (StringUtils.isNotBlank(declareRecord.getHousingStructure())) {
                                    surveyAssetInventoryContent.setRegistration(declareRecord.getHousingStructure());
                                }
                                break;
                            case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_AREA://登记面积与实际面积
                                if (declareRecord.getFloorArea() != null)
                                    surveyAssetInventoryContent.setRegistration(String.valueOf(declareRecord.getFloorArea()));
                                break;
                        }
                    }
                }
                if (projectCategoryId.equals(houseLand.getId())) {
                    if (StringUtils.isNotBlank(baseDataDic.getFieldName())) {
                        switch (baseDataDic.getFieldName()) {
                            case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS://登记地址与实际地址
                                if (StringUtils.isNotBlank(declareRecord.getSeat())) {
                                    surveyAssetInventoryContent.setRegistration(declareRecord.getSeat());
                                }
                                break;
                            case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_USE://登记用途与实际用途
                                if (StringUtils.isNotBlank(declareRecord.getLandCertUse())) {
                                    surveyAssetInventoryContent.setRegistration(declareRecord.getLandCertUse());
                                }
                                break;
                            case AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_AREA://登记面积与实际面积
                                if (declareRecord.getFloorArea() != null)
                                    surveyAssetInventoryContent.setRegistration(String.valueOf(declareRecord.getFloorArea()));
                                break;
                        }
                    }
                }
                surveyAssetInventoryContent.setDeclareId(declareRecord.getId());
                surveyAssetInventoryContentDao.save(surveyAssetInventoryContent);
            }
            list = surveyAssetInventoryContentDao.getSurveyAssetInventoryContent(projectPlanDetails.getId());
        }
        return list;
    }
}
