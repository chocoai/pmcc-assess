package com.copower.pmcc.assess.service.project.survey;


import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryContentDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryContent;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryContentVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        List<BaseDataDic> inventoryContentList = baseDataDicService.getCacheDataDicList(declareRecord.getInventoryContentKey());
        Collections.sort(inventoryContentList, Comparator.comparing(BaseDataDic::getSorting).reversed());//降序排列
        List<SurveyAssetInventoryContent> list = surveyAssetInventoryContentDao.getSurveyAssetInventoryContent(projectPlanDetails.getId());
        if (CollectionUtils.isEmpty(list)) {
            for (BaseDataDic baseDataDic : inventoryContentList) {
                Integer projectId = projectPlanDetails.getProjectId();
                SurveyAssetInventoryContent surveyAssetInventoryContent = new SurveyAssetInventoryContent();
                surveyAssetInventoryContent.setProjectId(projectId);
                surveyAssetInventoryContent.setPlanDetailsId(projectPlanDetails.getId());
                surveyAssetInventoryContent.setInventoryContent(baseDataDic.getId());
                if(StringUtils.isNotBlank(baseDataDic.getFieldName())){
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
                surveyAssetInventoryContentDao.save(surveyAssetInventoryContent);
            }
            list = surveyAssetInventoryContentDao.getSurveyAssetInventoryContent(projectPlanDetails.getId());
        }
        return list;
    }
}
