package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SchemeInfo;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.output.method.SchemeInfoVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 方案主表
 * Created by 13426 on 2018/5/24.
 */
@Service
public class SchemeInfoService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeInfoDao schemeInfoDao;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    /**
     * 保存信息
     *
     * @param schemeInfo
     * @throws BusinessException
     */
    public void saveSchemeInfo(SchemeInfo schemeInfo) throws BusinessException {
        if (schemeInfo == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (schemeInfo.getId() != null && schemeInfo.getId() > 0) {
            schemeInfoDao.updateInfo(schemeInfo);
        } else {
            schemeInfo.setCreator(commonService.thisUserAccount());
            schemeInfoDao.addInfo(schemeInfo);
        }
    }

    public SchemeInfo getSchemeInfo(Integer judgeObjectId, Integer methodType) {
        SchemeInfo examle = new SchemeInfo();
        examle.setJudgeObjectId(judgeObjectId);
        examle.setMethodType(methodType);
        SchemeInfo schemeInfo = schemeInfoDao.getSchemeInfo(examle);
        return schemeInfo;
    }

    public List<SchemeInfo> getSchemeInfoList(Integer projectId) {
        SchemeInfo examle = new SchemeInfo();
        examle.setProjectId(projectId);
        return schemeInfoDao.getInfoList(examle);
    }

    public List<SchemeInfo> getInfoList(SchemeInfo oo) {
        return schemeInfoDao.getInfoList(oo);
    }

    public void deleteSchemeInfoByProjectId(Integer projectId) {
        List<SchemeInfo> schemeInfoList = getSchemeInfoList(projectId);
        if (!CollectionUtils.isEmpty(schemeInfoList)) {
            schemeInfoList.forEach(o -> schemeInfoDao.deleteInfo(o.getId()));
        }
    }

    public SchemeInfo getSchemeInfo(Integer planDetailsId) {
        SchemeInfo examle = new SchemeInfo();
        examle.setPlanDetailsId(planDetailsId);
        SchemeInfo schemeInfo = schemeInfoDao.getSchemeInfo(examle);
        return schemeInfo;
    }

    public BootstrapTableVo getBootstrapTableVo(Integer methodType,Integer methodDataId,Integer projectId){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SchemeInfo> schemeInfoList = schemeInfoDao.getSchemeInfoByProjectId(methodType, methodDataId, projectId);
        List<SchemeInfoVo> voList = Lists.newArrayList();
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(schemeInfoList)){
            schemeInfoList.forEach(oo -> voList.add(getSchemeInfoVo(oo)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }

    public SchemeInfoVo getSchemeInfoVo(SchemeInfo schemeInfo) {
        if (schemeInfo == null) {
            return null;
        }
        SchemeInfoVo vo = new SchemeInfoVo();
        BeanUtils.copyProperties(schemeInfo, vo);
        if (schemeInfo.getJudgeObjectId() != null) {
            SchemeJudgeObject schemeJudgeObject =schemeJudgeObjectService.getSchemeJudgeObject(schemeInfo.getJudgeObjectId());
            if (schemeJudgeObject != null){
                vo.setJudgeObjectName(schemeJudgeObject.getName());
                vo.setCertName(schemeJudgeObject.getCertName());
            }
        }
        vo.setMethodName(baseDataDicService.getNameById(schemeInfo.getMethodType()));
        return vo;
    }

    public BootstrapTableVo getSchemeIncomeVo(Integer methodType,Integer methodDataId,Integer projectId){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SchemeInfo> schemeInfoList = schemeInfoDao.getSchemeInfoStart(methodType, methodDataId, projectId);
        List<SchemeInfoVo> voList = Lists.newArrayList();
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(schemeInfoList)){
            //事项被删除的排除
            for (SchemeInfo item: schemeInfoList) {
                ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(item.getPlanDetailsId());
                if (planDetails!=null){
                    voList.add(getSchemeInfoVo(item)) ;
                }
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }
}