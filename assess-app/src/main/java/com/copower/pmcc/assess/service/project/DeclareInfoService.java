package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.DeclareInfoDao;
import com.copower.pmcc.assess.dal.dao.DeclareUseClassifyDao;
import com.copower.pmcc.assess.dal.dao.FormConfigureDao;
import com.copower.pmcc.assess.dal.entity.BaseFormModule;
import com.copower.pmcc.assess.dal.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.entity.DeclareInfo;
import com.copower.pmcc.assess.dal.entity.DeclareUseClassify;
import com.copower.pmcc.assess.dto.input.DeclareInfoDto;
import com.copower.pmcc.assess.dto.input.FormConfigureDto;
import com.copower.pmcc.assess.dto.output.BaseFormModuleVo;
import com.copower.pmcc.assess.service.base.BaseFormService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kings on 2018-5-10.
 */
@Service
public class DeclareInfoService {
    @Autowired
    private DeclareInfoDao declareInfoDao;
    @Autowired
    private FormConfigureService formConfigureService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DeclareUseClassifyDao declareUseClassifyDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private BaseFormService baseFormService;

    /**
     * 获取申报信息by processInsId
     *
     * @param processInsId
     * @return
     */
    public DeclareInfo getDeclareInfoByProcessInsId(String processInsId) {
        if (StringUtils.isBlank(processInsId)) return null;
        return declareInfoDao.getDeclareInfoByProcessInsId(processInsId);
    }

    /**
     * 保存申报数据
     *
     * @param declareInfoDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveDeclareInfo(DeclareInfoDto declareInfoDto) throws BusinessException {
        FormConfigureDto formConfigureDto = declareInfoDto.getFormConfigureDto();
        Integer mainId = 0;
        if (declareInfoDto.getId() != null && declareInfoDto.getId() > 0) {
            //暂不处理
        } else {
            mainId = formConfigureService.saveData(formConfigureDto);
            DeclareInfo declareInfo = new DeclareInfo();
            declareInfo.setId(mainId);
            declareInfo.setProjectId(declareInfoDto.getProjectId());
            declareInfo.setProcessInsId(declareInfoDto.getProcessInsId());
            declareInfo.setPlanDetailId(declareInfoDto.getPlanDetailId());
            declareInfo.setDynamicTableName(declareInfoDto.getFormConfigureDto().getMultipleFormList().get(0).getTableName());
            declareInfo.setCreator(commonService.thisUserAccount());
            declareInfoDao.editDeclareInfo(declareInfo);
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
     * @param projectId
     * @param planDetailsId
     * @param projectClassifyId
     */
    public void delDeclareUserClassify(Integer projectId, Integer planDetailsId,Integer projectClassifyId) {
        //先删除该分类下的申报信息
        DeclareUseClassify declareUseClassify = declareUseClassifyDao.getDeclareUseClassify(projectId,planDetailsId,projectClassifyId);
        if (declareUseClassify != null) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(declareUseClassify.getProjectClassifyId());
            if(projectClassify!=null&&projectClassify.getFormModuleId()!=null){
                BaseFormModule baseFormModule = baseFormService.getBaseFormModule(projectClassify.getFormModuleId());
                if(baseFormModule!=null&&StringUtils.isNotBlank(baseFormModule.getForeignKeyName()) ){
                    jdbcTemplate.execute(String.format("DELETE FROM %s WHERE %s=%s",baseFormModule.getTableName(),baseFormModule.getForeignKeyName(),declareUseClassify.getPlanDetailsId()));
                }
            }
            declareUseClassifyDao.deleteDeclareUseClassify(declareUseClassify.getId());
        }
    }

    /**
     * 获取
     * @param projectId
     * @param planDetailsId
     * @return
     */
    public List<BaseFormModuleVo> getBaseFormModuleVos(Integer projectId,Integer planDetailsId){
        List<DeclareUseClassify> useClassifyList = declareUseClassifyDao.getDeclareUseClassifyList(projectId, planDetailsId);
        if(CollectionUtils.isEmpty(useClassifyList)){
            //查看计划是否设置到范围

        }
        return null;
    }
}
