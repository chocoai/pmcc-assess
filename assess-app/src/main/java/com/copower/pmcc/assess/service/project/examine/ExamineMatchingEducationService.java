package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineMatchingEducationDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingEducation;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineMatchingEducationVo;
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
 * @Date: 2018/7/20 11:59
 * @Description:教育条件
 */
@Service
public class ExamineMatchingEducationService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineMatchingEducationDao examineMatchingEducationDao;
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
    public ExamineMatchingEducation getExamineMatchingEducationById(Integer id) {
        return examineMatchingEducationDao.getMatchingEducationById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineMatchingEducation
     * @return
     */
    public List<ExamineMatchingEducation> getExamineMatchingEducationList(ExamineMatchingEducation examineMatchingEducation) {
        return examineMatchingEducationDao.getMatchingEducationList(examineMatchingEducation);
    }

    public BootstrapTableVo getExamineMatchingEducationLists(ExamineMatchingEducation examineMatchingEducation) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineMatchingEducationVo> vos = Lists.newArrayList();
        getExamineMatchingEducationList(examineMatchingEducation).stream().forEach(oo -> vos.add(getExamineMatchingEducationVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineMatchingEducationVo>() : vos);
        return vo;
    }

    public ExamineMatchingEducationVo getExamineMatchingEducationVo(ExamineMatchingEducation examineMatchingEducation) {
        ExamineMatchingEducationVo vo = new ExamineMatchingEducationVo();
        BeanUtils.copyProperties(examineMatchingEducation, vo);
        if (examineMatchingEducation.getDistance() != null) {
            vo.setDistanceName(getValue(AssessExamineTaskConstant.ESTATE_DISTANCE, examineMatchingEducation.getDistance()));
        }
        if (!StringUtils.isEmpty(examineMatchingEducation.getSchoolLevel())) {
            if (org.apache.commons.lang.StringUtils.isNumeric(examineMatchingEducation.getSchoolLevel())) {
                vo.setSchoolLevelName(getValue(AssessExamineTaskConstant.ESTATE_SCHOOL_LEVEL, Integer.parseInt(examineMatchingEducation.getSchoolLevel())));
            }
        }
        if (examineMatchingEducation.getSchoolNature() != null) {
            vo.setSchoolNatureName(getValue(AssessExamineTaskConstant.ESTATE_SCHOOL_NATURE, examineMatchingEducation.getSchoolNature()));
        }
        if (examineMatchingEducation.getSchoolGradation() != null) {
            vo.setSchoolGradationName(getValue(AssessExamineTaskConstant.ESTATE_SCHOOL_GRADATION, examineMatchingEducation.getSchoolGradation()));
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
     * @param examineMatchingEducation
     * @return
     */
    public boolean addExamineMatchingEducation(ExamineMatchingEducation examineMatchingEducation) {
        examineMatchingEducation.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineMatchingEducation.getDeclareId())) {
            examineMatchingEducation.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineMatchingEducation.getExamineType())) {
            examineMatchingEducation.setExamineType(0);
        }
        return examineMatchingEducationDao.addMatchingEducation(examineMatchingEducation);
    }

    /**
     * 编辑
     *
     * @param examineMatchingEducation
     * @return
     */
    public boolean updateExamineMatchingEducation(ExamineMatchingEducation examineMatchingEducation) {
        return examineMatchingEducationDao.updateMatchingEducation(examineMatchingEducation);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineMatchingEducation(Integer id) {
        return examineMatchingEducationDao.deleteMatchingEducation(id);
    }

}
