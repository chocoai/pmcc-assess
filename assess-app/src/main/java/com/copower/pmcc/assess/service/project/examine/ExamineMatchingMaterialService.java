package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineMatchingMaterialDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingMaterial;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineMatchingMaterialVo;
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
 * @Date: 2018/8/2 14:39
 * @Description:原料供应及销售条件
 */

@Service
public class ExamineMatchingMaterialService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineMatchingMaterialDao examineMatchingMaterialDao;
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
    public ExamineMatchingMaterial getExamineMatchingMaterialById(Integer id) {
        return examineMatchingMaterialDao.getMatchingMaterialById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineMatchingMaterial
     * @return
     */
    public List<ExamineMatchingMaterial> getExamineMatchingMaterialList(ExamineMatchingMaterial examineMatchingMaterial) {
        return examineMatchingMaterialDao.getMatchingMaterialList(examineMatchingMaterial);
    }

    public BootstrapTableVo getExamineMatchingMaterialLists(ExamineMatchingMaterial examineMatchingMaterial) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineMatchingMaterialVo> vos = Lists.newArrayList();
        getExamineMatchingMaterialList(examineMatchingMaterial).stream().forEach(oo -> vos.add(getExamineMatchingMaterialVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineMatchingMaterialVo>() : vos);
        return vo;
    }

    public ExamineMatchingMaterialVo getExamineMatchingMaterialVo(ExamineMatchingMaterial examineMatchingMaterial) {
        ExamineMatchingMaterialVo vo = new ExamineMatchingMaterialVo();
        BeanUtils.copyProperties(examineMatchingMaterial, vo);
        if (examineMatchingMaterial.getCategory() != null) {
            vo.setCategoryName(getValue(AssessExamineTaskConstant.ESTATE_SUPPLY_NEW_TYPE, examineMatchingMaterial.getCategory()));
        }
        if (examineMatchingMaterial.getScale() != null) {
            vo.setScaleName(getValue(AssessExamineTaskConstant.ESTATE_SUPPLY_NEW_SCALE, examineMatchingMaterial.getScale()));
        }
        if (examineMatchingMaterial.getDistance() != null) {
            vo.setDistanceName(getValue(AssessExamineTaskConstant.ESTATE_SUPPLY_NEW_DISTANCE, examineMatchingMaterial.getDistance()));
        }
        return vo;
    }

    private String getValue(String key, Integer v) {
        StringBuilder builder = new StringBuilder(1024);
        if (!StringUtils.isEmpty(key)){
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
        }
        return builder.toString();
    }

    /**
     * 新增
     *
     * @param examineMatchingMaterial
     * @return
     */
    public boolean addExamineMatchingMaterial(ExamineMatchingMaterial examineMatchingMaterial) {
        examineMatchingMaterial.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineMatchingMaterial.getDeclareId())) {
            examineMatchingMaterial.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineMatchingMaterial.getExamineType())) {
            examineMatchingMaterial.setExamineType(0);
        }
        return examineMatchingMaterialDao.addMatchingMaterial(examineMatchingMaterial);
    }

    /**
     * 编辑
     *
     * @param examineMatchingMaterial
     * @return
     */
    public boolean updateExamineMatchingMaterial(ExamineMatchingMaterial examineMatchingMaterial) {
        return examineMatchingMaterialDao.updateMatchingMaterial(examineMatchingMaterial);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineMatchingMaterial(Integer id) {
        return examineMatchingMaterialDao.deleteMatchingMaterial(id);
    }
}
