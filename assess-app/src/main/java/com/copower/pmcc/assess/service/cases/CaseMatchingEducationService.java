package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseMatchingEducationDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingEducation;
import com.copower.pmcc.assess.dto.output.cases.CaseMatchingEducationVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
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
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/17 15:25
 * @Description:
 */
@Service
public class CaseMatchingEducationService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseMatchingEducationDao caseMatchingEducationDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public CaseMatchingEducation getCaseMatchingEducationById(Integer id) {
        return caseMatchingEducationDao.getMatchingEducationById(id);
    }

    public void upgradeVersion(CaseMatchingEducation po)throws Exception{
        if (po.getId()==null || po.getId().intValue() == 0){
            po.setCreator(commonService.thisUserAccount());
            po.setVersion(0);
            this.addCaseMatchingEducation(po);
        }else {
            CaseMatchingEducation oo = getCaseMatchingEducationById(po.getId());
            if (oo.getVersion() == null){
                oo.setVersion(0);
            }
            int version = oo.getVersion() + 1;
            BeanUtils.copyProperties(po,oo);
            oo.setVersion(version);
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            this.addCaseMatchingEducation(oo);
        }
    }

    /**
     * 获取数据列表
     *
     * @param caseMatchingEducation
     * @return
     */
    public List<CaseMatchingEducation> getCaseMatchingEducationList(CaseMatchingEducation caseMatchingEducation) {
        return caseMatchingEducationDao.getMatchingEducationList(caseMatchingEducation);
    }

    public BootstrapTableVo getCaseMatchingEducationLists(CaseMatchingEducation caseMatchingEducation) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseMatchingEducationVo> vos = Lists.newArrayList();
        getCaseMatchingEducationList(caseMatchingEducation).stream().forEach(oo -> vos.add(getCaseMatchingEducationVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseMatchingEducationVo>() : vos);
        return vo;
    }

    public CaseMatchingEducationVo getCaseMatchingEducationVo(CaseMatchingEducation caseMatchingEducation) {
        CaseMatchingEducationVo vo = new CaseMatchingEducationVo();
        BeanUtils.copyProperties(caseMatchingEducation, vo);
        if (caseMatchingEducation.getDistance() != null) {
            vo.setDistanceName(getValue(AssessExamineTaskConstant.ESTATE_DISTANCE, caseMatchingEducation.getDistance()));
        }
        if (!StringUtils.isEmpty(caseMatchingEducation.getSchoolLevel())) {
            if (org.apache.commons.lang.StringUtils.isNumeric(caseMatchingEducation.getSchoolLevel())) {
                vo.setSchoolLevelName(getValue(AssessExamineTaskConstant.ESTATE_SCHOOL_LEVEL, Integer.parseInt(caseMatchingEducation.getSchoolLevel())));
            }
        }
        if (caseMatchingEducation.getSchoolNature() != null) {
            vo.setSchoolNatureName(getValue(AssessExamineTaskConstant.ESTATE_SCHOOL_NATURE, caseMatchingEducation.getSchoolNature()));
        }
        if (caseMatchingEducation.getSchoolGradation() != null) {
            vo.setSchoolGradationName(getValue(AssessExamineTaskConstant.ESTATE_SCHOOL_GRADATION, caseMatchingEducation.getSchoolGradation()));
        }
        return vo;
    }

    private String getValue(String key, Integer v) {
        StringBuilder builder = new StringBuilder(1024);
        List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(key);
        if (baseDataDic.size() >= 1) {
            if (v != null) {
                for (BaseDataDic base : baseDataDic) {
                    if (base.getId().intValue() == v.intValue()) {
                        builder.append(base.getName());
                    }
                }
            }
        }
        return builder.toString();
    }

    /**
     * 新增
     *
     * @param caseMatchingEducation
     * @return
     */
    public boolean addCaseMatchingEducation(CaseMatchingEducation caseMatchingEducation) {
        caseMatchingEducation.setCreator(commonService.thisUserAccount());
        return caseMatchingEducationDao.addMatchingEducation(caseMatchingEducation);
    }

    /**
     * 编辑
     *
     * @param caseMatchingEducation
     * @return
     */
    public boolean updateCaseMatchingEducation(CaseMatchingEducation caseMatchingEducation) {
        return caseMatchingEducationDao.updateMatchingEducation(caseMatchingEducation);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseMatchingEducation(Integer id) {
        return caseMatchingEducationDao.deleteMatchingEducation(id);
    }

}
