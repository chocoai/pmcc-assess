package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdCalculatingMethodEngineeringCostDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zch on 2019-8-28.
 * 工程费
 */
@Service
public class MdCalculatingMethodEngineeringCostService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private MdCalculatingMethodEngineeringCostDao mdCalculatingMethodEngineeringCostDao;
    @Autowired
    private MdArchitecturalObjService mdArchitecturalObjService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private SurveyCommonService surveyCommonService;

    public void setMdCalculatingMethodEngineeringCost(Integer planDetailsId, String databaseName, String type) {
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(projectPlanDetails.getJudgeObjectId());
        BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
        BigDecimal area = schemeJudgeObject.getFloorArea() != null ? schemeJudgeObject.getFloorArea() : schemeJudgeObject.getEvaluationArea();
        setMdCalculatingMethodEngineeringCost2(projectPlanDetails, basicApply, area, databaseName, type);
    }

    /**
     * 设置工程费
     *
     * @param projectPlanDetails
     * @param basicApply
     * @param area
     * @param databaseName
     */
    private void setMdCalculatingMethodEngineeringCost2(ProjectPlanDetails projectPlanDetails, BasicApply basicApply, BigDecimal area, String databaseName, String type) {
        if (basicApply == null) {
            return;
        }
        mdArchitecturalObjService.clear(projectPlanDetails.getId());
        this.clear(projectPlanDetails);

        List<MdArchitecturalObj> mdArchitecturalObjList = Lists.newArrayList();
        MdArchitecturalObj select = new MdArchitecturalObj();
        select.setDatabaseName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        select.setPid(basicApply.getBasicEstateId());
        List<MdArchitecturalObj> mdArchitecturalObjList2 = mdArchitecturalObjService.getMdArchitecturalObjListByExample(select);
        if (CollectionUtils.isNotEmpty(mdArchitecturalObjList2)) {
            mdArchitecturalObjList.addAll(mdArchitecturalObjList2);
        }
        select.setDatabaseName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
        select.setPid(basicApply.getBasicBuildingId());
        mdArchitecturalObjList2 = mdArchitecturalObjService.getMdArchitecturalObjListByExample(select);
        if (CollectionUtils.isNotEmpty(mdArchitecturalObjList2)) {
            mdArchitecturalObjList.addAll(mdArchitecturalObjList2);
        }
        if (CollectionUtils.isNotEmpty(mdArchitecturalObjList)) {
            for (MdArchitecturalObj oo : mdArchitecturalObjList) {
                MdCalculatingMethodEngineeringCost mdCalculatingMethodEngineeringCost = new MdCalculatingMethodEngineeringCost();
                mdCalculatingMethodEngineeringCost.setCreator(commonService.thisUserAccount());
                mdCalculatingMethodEngineeringCost.setArea(area);
                mdCalculatingMethodEngineeringCost.setArchitecturalObjId(0);
                mdCalculatingMethodEngineeringCost.setPlanDetailsId(projectPlanDetails.getId());
                mdCalculatingMethodEngineeringCost.setProjectId(projectPlanDetails.getProjectId());
                mdCalculatingMethodEngineeringCost.setPrice(new BigDecimal(0));
                if (StringUtils.isNotBlank(type)) {
                    mdCalculatingMethodEngineeringCost.setType(type);
                }
                this.saveMdCalculatingMethodEngineeringCost(mdCalculatingMethodEngineeringCost);


                MdArchitecturalObj obj = mdArchitecturalObjService.getMdArchitecturalObjById(oo.getId());
                oo.setId(null);
                oo.setPlanDetailsId(projectPlanDetails.getId());
                oo.setPid(mdCalculatingMethodEngineeringCost.getId());
                oo.setJsonContent(obj.getJsonContent());
                oo.setPrice(new BigDecimal(0));
                oo.setCreator(commonService.thisUserAccount());
                oo.setDatabaseName(databaseName);
                mdArchitecturalObjService.saveMdArchitecturalObj(oo);

                mdCalculatingMethodEngineeringCost.setArchitecturalObjId(oo.getId());
                this.saveMdCalculatingMethodEngineeringCost(mdCalculatingMethodEngineeringCost);
            }
        }
    }


    public boolean saveMdCalculatingMethodEngineeringCost(MdCalculatingMethodEngineeringCost mdCalculatingMethodEngineeringCost) {
        if (mdCalculatingMethodEngineeringCost == null) {
            return false;
        }
        if (mdCalculatingMethodEngineeringCost.getId() != null && mdCalculatingMethodEngineeringCost.getId() != 0) {
            return mdCalculatingMethodEngineeringCostDao.updateMdCalculatingMethodEngineeringCost(mdCalculatingMethodEngineeringCost);
        } else {
            mdCalculatingMethodEngineeringCost.setCreator(commonService.thisUserAccount());
            return mdCalculatingMethodEngineeringCostDao.addMdCalculatingMethodEngineeringCost(mdCalculatingMethodEngineeringCost);
        }
    }

    public void clear(ProjectPlanDetails projectPlanDetails) {
        MdCalculatingMethodEngineeringCost engineeringCost = new MdCalculatingMethodEngineeringCost();
        engineeringCost.setPlanDetailsId(projectPlanDetails.getId());
        engineeringCost.setProjectId(projectPlanDetails.getProjectId());
        engineeringCost.setPrice(new BigDecimal(0));
        List<MdCalculatingMethodEngineeringCost> list = getMdCalculatingMethodEngineeringCostListByExample(engineeringCost);
        if (CollectionUtils.isNotEmpty(list)) {
            for (MdCalculatingMethodEngineeringCost obj : list) {
                deleteMdCalculatingMethodEngineeringCostById(obj.getId());
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(MdCalculatingMethodEngineeringCost oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<MdCalculatingMethodEngineeringCost> childrenList = getMdCalculatingMethodEngineeringCostListByExample(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(childrenList);
        return vo;
    }

    public MdCalculatingMethodEngineeringCost getMdCalculatingMethodEngineeringCostById(Integer id) {
        return mdCalculatingMethodEngineeringCostDao.getMdCalculatingMethodEngineeringCostById(id);
    }

    public boolean deleteMdCalculatingMethodEngineeringCostById(Integer id) {
        return mdCalculatingMethodEngineeringCostDao.deleteMdCalculatingMethodEngineeringCostById(id);
    }

    public List<MdCalculatingMethodEngineeringCost> getMdCalculatingMethodEngineeringCostListByExample(MdCalculatingMethodEngineeringCost oo) {
        return mdCalculatingMethodEngineeringCostDao.getMdCalculatingMethodEngineeringCostListByExample(oo);
    }

}
