package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareApplyDao;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareUseClassifyDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.basis.entity.DeclareApply;
import com.copower.pmcc.assess.dal.basis.entity.DeclareUseClassify;
import com.copower.pmcc.assess.dto.output.base.BaseFormModuleVo;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kings on 2018-5-10.
 */
@Service
public class DeclareApplyService {
    @Autowired
    private DeclareApplyDao declareApplyDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DeclareUseClassifyDao declareUseClassifyDao;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    /**
     * 获取申报信息by processInsId
     *
     * @param processInsId
     * @return
     */
    public DeclareApply getDeclareApplyByProcessInsId(String processInsId) {
        if (StringUtils.isBlank(processInsId)) return null;
        return declareApplyDao.getDeclareApplyByProcessInsId(processInsId);
    }

    /**
     * 保存申报数据
     *
     * @param declareApply
     */
    public void saveDeclareApply(DeclareApply declareApply) throws BusinessException {
        if (declareApply.getId() != null && declareApply.getId() > 0) {
            declareApplyDao.updateDeclareApply(declareApply);
        } else {
            declareApply.setCreator(commonService.thisUserAccount());
            declareApplyDao.addDeclareApply(declareApply);
        }
    }

    /**
     * 新增申报使用项目分类
     *
     * @param projectId
     * @param planDetailsId
     * @param projectClassifyId
     */
    public void addDeclareUserClassify(Integer projectId, Integer planDetailsId, Integer projectClassifyId) {
        DeclareUseClassify declareUseClassify = new DeclareUseClassify();
        declareUseClassify.setCreator(commonService.thisUserAccount());
        declareUseClassify.setProjectId(projectId);
        declareUseClassify.setPlanDetailsId(planDetailsId);
        declareUseClassify.setProjectClassifyId(projectClassifyId);
        declareUseClassifyDao.addDeclareUseClassify(declareUseClassify);
    }

    /**
     * 删除使用的项目分类
     *
     * @param projectId
     * @param planDetailsId
     * @param projectClassifyId
     */
    public void delDeclareUserClassify(Integer projectId, Integer planDetailsId, Integer projectClassifyId) {
        //先删除该分类下的申报信息
        DeclareUseClassify declareUseClassify = declareUseClassifyDao.getDeclareUseClassify(projectId, planDetailsId, projectClassifyId);
        if (declareUseClassify != null) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(declareUseClassify.getProjectClassifyId());

            declareUseClassifyDao.deleteDeclareUseClassify(declareUseClassify.getId());
        }
    }

    /**
     * 获取使用到的申报表数据
     *
     * @param projectId
     * @param planDetailsId
     * @return
     */
    public List<BaseFormModuleVo> getBaseFormModuleVos(Integer projectId, Integer planDetailsId) {
        List<BaseFormModuleVo> baseFormModuleVos = Lists.newArrayList();
        List<DeclareUseClassify> useClassifyList = declareUseClassifyDao.getDeclareUseClassifyList(projectId, planDetailsId);
        if (CollectionUtils.isEmpty(useClassifyList)) {

        }else{
            for (DeclareUseClassify declareUseClassify : useClassifyList) {
                BaseProjectClassify baseProjectClassify = baseProjectClassifyService.getCacheProjectClassifyById(declareUseClassify.getProjectClassifyId());

            }
        }
        return baseFormModuleVos;
    }

}
