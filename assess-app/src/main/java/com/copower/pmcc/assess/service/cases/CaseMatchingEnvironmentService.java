package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseMatchingEnvironmentDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingEnvironment;
import com.copower.pmcc.assess.dto.output.cases.CaseMatchingEnvironmentVo;
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
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/17 15:29
 * @Description:
 */
@Service
public class CaseMatchingEnvironmentService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseMatchingEnvironmentDao caseMatchingEnvironmentDao;
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
    public CaseMatchingEnvironment getCaseMatchingEnvironmentById(Integer id) {
        return caseMatchingEnvironmentDao.getMatchingEnvironmentById(id);
    }

    public void upgradeVersion(CaseMatchingEnvironment po)throws Exception{
        if (po.getId()==null || po.getId().intValue() == 0){
            po.setCreator(commonService.thisUserAccount());
            po.setVersion(0);
            this.addCaseMatchingEnvironment(po);
        }else {
            CaseMatchingEnvironment oo = getCaseMatchingEnvironmentById(po.getId());
            if (oo.getVersion() == null){
                oo.setVersion(0);
            }
            int version = oo.getVersion() + 1;
            BeanUtils.copyProperties(po,oo);
            oo.setVersion(version);
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            this.addCaseMatchingEnvironment(oo);
        }
    }

    /**
     * 获取数据列表
     *
     * @param caseMatchingEnvironment
     * @return
     */
    public List<CaseMatchingEnvironment> getCaseMatchingEnvironmentList(CaseMatchingEnvironment caseMatchingEnvironment) {
        return caseMatchingEnvironmentDao.getMatchingEnvironmentList(caseMatchingEnvironment);
    }

    public BootstrapTableVo getCaseMatchingEnvironmentLists(CaseMatchingEnvironment caseMatchingEnvironment) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseMatchingEnvironmentVo> vos = Lists.newArrayList();
        getCaseMatchingEnvironmentList(caseMatchingEnvironment).stream().forEach(oo -> vos.add(getCaseMatchingEnvironmentVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseMatchingEnvironmentVo>() : vos);
        return vo;
    }

    public CaseMatchingEnvironmentVo getCaseMatchingEnvironmentVo(CaseMatchingEnvironment caseMatchingEnvironment) {
        CaseMatchingEnvironmentVo vo = new CaseMatchingEnvironmentVo();
        BeanUtils.copyProperties(caseMatchingEnvironment, vo);
        if (!StringUtils.isEmpty(caseMatchingEnvironment.getType())) {
            if (org.apache.commons.lang.StringUtils.isNumeric(caseMatchingEnvironment.getType())) {
                vo.setTypeName(getValue(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE, Integer.parseInt(caseMatchingEnvironment.getType())));
            }
        }
        if (caseMatchingEnvironment.getCategory() != null) {
            vo.setCategoryName(getValue(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_CATEGORY, caseMatchingEnvironment.getCategory()));
        }
        if (caseMatchingEnvironment.getInfluenceDegree() != null) {
            vo.setInfluenceDegreeName(getValue(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_INFLUENCE_DEGREE, caseMatchingEnvironment.getInfluenceDegree()));
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
     * @param caseMatchingEnvironment
     * @return
     */
    public boolean addCaseMatchingEnvironment(CaseMatchingEnvironment caseMatchingEnvironment) {
        caseMatchingEnvironment.setCreator(commonService.thisUserAccount());
        return caseMatchingEnvironmentDao.addMatchingEnvironment(caseMatchingEnvironment);
    }

    /**
     * 编辑
     *
     * @param caseMatchingEnvironment
     * @return
     */
    public boolean updateCaseMatchingEnvironment(CaseMatchingEnvironment caseMatchingEnvironment) {
        return caseMatchingEnvironmentDao.updateMatchingEnvironment(caseMatchingEnvironment);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseMatchingEnvironment(Integer id) {
        return caseMatchingEnvironmentDao.deleteMatchingEnvironment(id);
    }
}
