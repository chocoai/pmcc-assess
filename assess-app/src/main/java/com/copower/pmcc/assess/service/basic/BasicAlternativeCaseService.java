package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicAlternativeCaseDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.BasicAlternativeCaseDto;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:53
 */
@Service
public class BasicAlternativeCaseService extends BaseService {
    @Autowired
    private BasicAlternativeCaseDao basicAlternativeCaseDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;
    @Autowired
    private BasicApplyBatchDao basicApplyBatchDao;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    public Integer addBasicAlternativeCase(BasicAlternativeCase basicAlternativeCase) {
        String name = basicApplyBatchDetailService.getFullNameByBatchDetailId(basicAlternativeCase.getBatchDetailId());
        basicAlternativeCase.setName(name);
        //设置项目类别
        BasicApplyBatchDetail applyBatchDetail = basicApplyBatchDetailService.getDataById(basicAlternativeCase.getBatchDetailId());
        BasicApplyBatch basicApplyBatch = basicApplyBatchDao.getBasicApplyBatchById(applyBatchDetail.getApplyBatchId());
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(basicApplyBatch.getProjectId());
        if (projectInfo == null && basicApplyBatch.getPlanDetailsId()!=null) {
            ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(basicApplyBatch.getPlanDetailsId());
            projectInfo = projectInfoService.getProjectInfoById(planDetails.getProjectId());
        }
        if (projectInfo != null) {
            basicAlternativeCase.setProjectCategoryId(projectInfo.getProjectCategoryId());
        }
        basicAlternativeCase.setCreator(commonService.thisUserAccount());
        return basicAlternativeCaseDao.addBasicAlternativeCase(basicAlternativeCase);
    }

    public BasicAlternativeCase getBasicAlternativeCaseById(Integer id) {
        return basicAlternativeCaseDao.getBasicAlternativeCaseById(id);
    }

    public BootstrapTableVo getBasicAlternativeCaseList(String name, String tbType, Integer projectId, Integer planDetailsId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        if (projectId == null) {
            ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
            projectId = planDetails.getProjectId();
        }
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        List<BasicAlternativeCase> alternativeCases = basicAlternativeCaseDao.getBasicAlternativeCaseList(name, tbType, commonService.thisUserAccount(), projectInfo.getProjectCategoryId());
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(alternativeCases) ? new ArrayList<BasicAlternativeCase>() : alternativeCases);
        return vo;
    }

    public BasicAlternativeCaseDto getBasicAlternativeCaseDto(Integer id) {
        BasicAlternativeCase basicAlternativeCase = this.getBasicAlternativeCaseById(id);
        BasicApplyBatchDetail applyBatchDetail = basicApplyBatchDetailDao.getInfoById(basicAlternativeCase.getBusinessId());
        BasicApplyBatch basicApplyBatch = basicApplyBatchDao.getBasicApplyBatchById(applyBatchDetail.getApplyBatchId());
        BasicAlternativeCaseDto basicAlternativeCaseDto = new BasicAlternativeCaseDto();
        basicAlternativeCaseDto.setApplyBatchId(basicApplyBatch.getId());
        basicAlternativeCaseDto.setFormClassify(basicApplyBatch.getClassify());
        basicAlternativeCaseDto.setFormType(basicApplyBatch.getType());
        basicAlternativeCaseDto.setTableId(applyBatchDetail.getTableId());
        basicAlternativeCaseDto.setTableName(applyBatchDetail.getTableName());
        basicAlternativeCaseDto.setPlanDetailsId(basicApplyBatch.getPlanDetailsId());
        return basicAlternativeCaseDto;
    }

    public boolean deleteDataById(Integer id) {
        return basicAlternativeCaseDao.deleteInfo(id);
    }

    /**
     * 通过备选案例引用数据
     *
     * @param id
     * @return
     */
    public BasicApplyBatch referenceDataById(Integer id, Integer projectId, Integer planDetailsId) {
        //通过id引用数据结构及关联的数据信息
        BasicAlternativeCase basicAlternativeCase = getBasicAlternativeCaseById(id);
        if (basicAlternativeCase == null) return null;
        List<BasicApplyBatchDetail> list = Lists.newArrayList();
        basicApplyBatchDetailService.collectionParentBatchDetails(basicAlternativeCase.getBatchDetailId(), list);
        if (CollectionUtils.isEmpty(list)) return null;
        BasicApplyBatchDetail topBatchDetai = list.get(list.size() - 1);
        BasicApplyBatch sourceApplyBatch = basicApplyBatchDao.getBasicApplyBatchById(topBatchDetai.getApplyBatchId());
        BasicApplyBatch newBasicApplyBatch = new BasicApplyBatch();
        BeanUtils.copyProperties(sourceApplyBatch, newBasicApplyBatch, BaseConstant.ASSESS_IGNORE_ARRAY);
        newBasicApplyBatch.setProjectId(projectId);
        newBasicApplyBatch.setPlanDetailsId(planDetailsId);
        newBasicApplyBatch.setDraftFlag(false);
        newBasicApplyBatch.setBisDelete(false);
        newBasicApplyBatch.setCreator(commonService.thisUserAccount());
        basicApplyBatchDao.addBasicApplyBatch(newBasicApplyBatch);
        Integer pid = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            BasicApplyBatchDetail sourceApplyBatchDetail = list.get(i);
            BasicApplyBatchDetail newApplyBatchDetail = new BasicApplyBatchDetail();
            newApplyBatchDetail.setPid(pid);
            newApplyBatchDetail.setApplyBatchId(newBasicApplyBatch.getId());
            newApplyBatchDetail.setTableName(sourceApplyBatchDetail.getTableName());
            BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByKey(sourceApplyBatchDetail.getType());
            try {
                Object entity = entityAbstract.copyBasicEntity(sourceApplyBatchDetail.getTableId(), null, true);
                Integer entityId = (Integer) entityAbstract.getProperty(entity, "id");
                newApplyBatchDetail.setTableId(entityId);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            newApplyBatchDetail.setType(sourceApplyBatchDetail.getType());
            newApplyBatchDetail.setName(sourceApplyBatchDetail.getName());
            newApplyBatchDetail.setDisplayName(sourceApplyBatchDetail.getDisplayName());
            basicApplyBatchDetailService.saveBasicApplyBatchDetail(newApplyBatchDetail);
            pid = newApplyBatchDetail.getId();
        }
        return newBasicApplyBatch;
    }

    public void writeProjectCategoryId() {
        BasicAlternativeCase basicAlternativeCase = new BasicAlternativeCase();
        List<BasicAlternativeCase> basicAlternativeCaseList = basicAlternativeCaseDao.getBasicAlternativeCaseList(basicAlternativeCase);
        if (CollectionUtils.isNotEmpty(basicAlternativeCaseList)) {
            for (BasicAlternativeCase item : basicAlternativeCaseList) {
                if (item.getProjectCategoryId() == null) {
                    //设置项目类别
                    BasicApplyBatchDetail applyBatchDetail = basicApplyBatchDetailService.getDataById(item.getBatchDetailId());
                    if (applyBatchDetail != null) {
                        BasicApplyBatch basicApplyBatch = basicApplyBatchDao.getBasicApplyBatchById(applyBatchDetail.getApplyBatchId());
                        if(basicApplyBatch==null) continue;
                        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(basicApplyBatch.getProjectId());
                        if (projectInfo == null) continue;
                        item.setProjectCategoryId(projectInfo.getProjectCategoryId());
                        basicAlternativeCaseDao.updateBasicAlternativeCase(item);
                    }
                }
            }
        }

    }
}
