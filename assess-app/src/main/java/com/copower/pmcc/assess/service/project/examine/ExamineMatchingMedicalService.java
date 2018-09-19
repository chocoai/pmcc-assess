package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineMatchingMedicalDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMedical;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineMatchingMedicalVo;
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
 * @Date: 2018/7/23 16:25
 * @Description:医养条件
 */
@Service
public class ExamineMatchingMedicalService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineMatchingMedicalDao examineMatchingMedicalDao;
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
    public ExamineMatchingMedical getExamineMatchingMedicalById(Integer id) {
        return examineMatchingMedicalDao.getExamineMatchingMedicalById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineMatchingMedical
     * @return
     */
    public List<ExamineMatchingMedical> getExamineMatchingMedicalList(ExamineMatchingMedical examineMatchingMedical) {
        return examineMatchingMedicalDao.getExamineMatchingMedicalList(examineMatchingMedical);
    }

    public BootstrapTableVo getExamineMatchingMedicalLists(ExamineMatchingMedical examineMatchingMedical) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineMatchingMedicalVo> vos = Lists.newArrayList();
        getExamineMatchingMedicalList(examineMatchingMedical).stream().forEach(oo -> vos.add(getExamineMatchingMedicalVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineMatchingMedicalVo>() : vos);
        return vo;
    }

    public ExamineMatchingMedicalVo getExamineMatchingMedicalVo(ExamineMatchingMedical examineMatchingMedical) {
        ExamineMatchingMedicalVo vo = new ExamineMatchingMedicalVo();
        BeanUtils.copyProperties(examineMatchingMedical, vo);
        if (examineMatchingMedical.getDistance() != null) {
            vo.setDistanceName(getValue(AssessExamineTaskConstant.ESTATE_EXAMINEMATCHINGMEDICAL_DISTANCE, examineMatchingMedical.getDistance()));
        }
        if (!StringUtils.isEmpty(examineMatchingMedical.getOrganizationLevel())){
            if (org.apache.commons.lang.StringUtils.isNumeric(examineMatchingMedical.getOrganizationLevel())){
                vo.setOrganizationLevelName(getValue(AssessExamineTaskConstant.ESTATE_EXAMINEMATCHINGMEDICAL_LEVEL,Integer.parseInt(examineMatchingMedical.getOrganizationLevel())));
            }
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
     * @param examineMatchingMedical
     * @return
     */
    public boolean addExamineMatchingMedical(ExamineMatchingMedical examineMatchingMedical) {
        examineMatchingMedical.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineMatchingMedical.getDeclareId())) {
            examineMatchingMedical.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineMatchingMedical.getExamineType())) {
            examineMatchingMedical.setExamineType(0);
        }
        return examineMatchingMedicalDao.addExamineMatchingMedical(examineMatchingMedical);
    }

    /**
     * 编辑
     *
     * @param examineMatchingMedical
     * @return
     */
    public boolean updateExamineMatchingMedical(ExamineMatchingMedical examineMatchingMedical) {
        return examineMatchingMedicalDao.updateExamineMatchingMedical(examineMatchingMedical);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineMatchingMedical(Integer id) {
        return examineMatchingMedicalDao.deleteExamineMatchingMedical(id);
    }

}
