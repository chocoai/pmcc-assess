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
    private BasicApplyBatchService basicApplyBatchService;
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
        if (projectInfo == null && basicApplyBatch.getPlanDetailsId() != null) {
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

    public BootstrapTableVo getBasicAlternativeCaseList(String name, Integer applyBatchDetailId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        BasicApplyBatchDetail applyBatchDetail = basicApplyBatchDetailService.getDataById(applyBatchDetailId);
        BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(applyBatchDetail.getApplyBatchId());
        if (basicApplyBatch == null) return vo;
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(basicApplyBatch.getProjectId());
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicAlternativeCase> alternativeCases = basicAlternativeCaseDao.getBasicAlternativeCaseList(name, applyBatchDetail.getType(), null, projectInfo.getProjectCategoryId());
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(alternativeCases) ? new ArrayList<BasicAlternativeCase>() : alternativeCases);
        return vo;
    }

    public BasicAlternativeCaseDto getBasicAlternativeCaseDto(Integer id) {
        BasicAlternativeCase basicAlternativeCase = this.getBasicAlternativeCaseById(id);
        BasicApplyBatchDetail applyBatchDetail = basicApplyBatchDetailDao.getInfoById(id);
        BasicApplyBatch basicApplyBatch = basicApplyBatchDao.getBasicApplyBatchById(applyBatchDetail.getApplyBatchId());
        BasicAlternativeCaseDto basicAlternativeCaseDto = new BasicAlternativeCaseDto();
        if (basicApplyBatch != null) {
            basicAlternativeCaseDto.setApplyBatchId(basicApplyBatch.getId());
            basicAlternativeCaseDto.setFormClassify(basicApplyBatch.getClassify());
            basicAlternativeCaseDto.setFormType(basicApplyBatch.getType());
            basicAlternativeCaseDto.setPlanDetailsId(basicApplyBatch.getPlanDetailsId());
        }
        basicAlternativeCaseDto.setTableId(applyBatchDetail.getTableId());
        basicAlternativeCaseDto.setTableName(applyBatchDetail.getTableName());
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
        return basicApplyBatchService.referenceDataByDetailId(basicAlternativeCase.getBatchDetailId(), projectId, planDetailsId);
    }
}
