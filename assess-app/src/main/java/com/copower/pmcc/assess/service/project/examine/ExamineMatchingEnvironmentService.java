package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineMatchingEnvironmentDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingEnvironment;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineMatchingEnvironmentVo;
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
 * @Date: 2018/7/20 15:51
 * @Description:环境因素
 */
@Service
public class ExamineMatchingEnvironmentService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineMatchingEnvironmentDao examineMatchingEnvironmentDao;
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
    public ExamineMatchingEnvironment getExamineMatchingEnvironmentById(Integer id) {
        return examineMatchingEnvironmentDao.getMatchingEnvironmentById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineMatchingEnvironment
     * @return
     */
    public List<ExamineMatchingEnvironment> getExamineMatchingEnvironmentList(ExamineMatchingEnvironment examineMatchingEnvironment) {
        return examineMatchingEnvironmentDao.getMatchingEnvironmentList(examineMatchingEnvironment);
    }

    public BootstrapTableVo getExamineMatchingEnvironmentLists(ExamineMatchingEnvironment examineMatchingEnvironment) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineMatchingEnvironmentVo> vos = Lists.newArrayList();
        getExamineMatchingEnvironmentList(examineMatchingEnvironment).stream().forEach(oo -> vos.add(getExamineMatchingEnvironmentVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineMatchingEnvironmentVo>() : vos);
        return vo;
    }

    public ExamineMatchingEnvironmentVo getExamineMatchingEnvironmentVo(ExamineMatchingEnvironment examineMatchingEnvironment) {
        ExamineMatchingEnvironmentVo vo = new ExamineMatchingEnvironmentVo();
        BeanUtils.copyProperties(examineMatchingEnvironment, vo);
        if (!StringUtils.isEmpty(examineMatchingEnvironment.getType())) {
            if (org.apache.commons.lang.StringUtils.isNumeric(examineMatchingEnvironment.getType())) {
                vo.setTypeName(getValue(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_TYPE, Integer.parseInt(examineMatchingEnvironment.getType())));
            }
        }
        if (examineMatchingEnvironment.getCategory() != null) {
            vo.setCategoryName(getValue(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_CATEGORY, examineMatchingEnvironment.getCategory()));
        }
        if (examineMatchingEnvironment.getInfluenceDegree() != null) {
            vo.setInfluenceDegreeName(getValue(AssessExamineTaskConstant.ESTATE_ENVIRONMENT_INFLUENCE_DEGREE, examineMatchingEnvironment.getInfluenceDegree()));
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
     * @param examineMatchingEnvironment
     * @return
     */
    public boolean addExamineMatchingEnvironment(ExamineMatchingEnvironment examineMatchingEnvironment) {
        examineMatchingEnvironment.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineMatchingEnvironment.getDeclareId())) {
            examineMatchingEnvironment.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineMatchingEnvironment.getExamineType())) {
            examineMatchingEnvironment.setExamineType(0);
        }
        return examineMatchingEnvironmentDao.addMatchingEnvironment(examineMatchingEnvironment);
    }

    /**
     * 编辑
     *
     * @param examineMatchingEnvironment
     * @return
     */
    public boolean updateExamineMatchingEnvironment(ExamineMatchingEnvironment examineMatchingEnvironment) {
        return examineMatchingEnvironmentDao.updateMatchingEnvironment(examineMatchingEnvironment);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineMatchingEnvironment(Integer id) {
        return examineMatchingEnvironmentDao.deleteMatchingEnvironment(id);
    }
}
