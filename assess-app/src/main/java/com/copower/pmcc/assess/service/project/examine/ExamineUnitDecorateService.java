package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineUnitDecorateDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitDecorate;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineUnitDecorateVo;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/24 17:00
 * @Description:室内公共装修
 */
@Service
public class ExamineUnitDecorateService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineUnitDecorateDao examineUnitDecorateDao;
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
    public ExamineUnitDecorate getExamineUnitDecorateById(Integer id) {
        return examineUnitDecorateDao.getUnitDecorateById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineUnitDecorate
     * @return
     */
    public List<ExamineUnitDecorate> getExamineUnitDecorateList(ExamineUnitDecorate examineUnitDecorate) {
        return examineUnitDecorateDao.getUnitDecorateList(examineUnitDecorate);
    }

    public BootstrapTableVo getExamineUnitDecorateLists(ExamineUnitDecorate examineUnitDecorate) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineUnitDecorateVo> vos = Lists.newArrayList();
        getExamineUnitDecorateList(examineUnitDecorate).stream().forEach(oo -> vos.add(getExamineUnitDecorateVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineUnitDecorateVo>() : vos);
        return vo;
    }

    public ExamineUnitDecorateVo getExamineUnitDecorateVo(ExamineUnitDecorate examineUnitDecorate) {
        ExamineUnitDecorateVo vo = new ExamineUnitDecorateVo();
        BeanUtils.copyProperties(examineUnitDecorate, vo);
        if (examineUnitDecorate.getDecorationPart() != null) {
            vo.setDecorationPartName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_DECORATION_PART, examineUnitDecorate.getDecorationPart()));
        }
        if (examineUnitDecorate.getDecoratingMaterial() != null) {
            vo.setDecoratingMaterialName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_DECORATING_MATERIAL, examineUnitDecorate.getDecoratingMaterial()));
        }
        if (examineUnitDecorate.getMaterialPriceRange() != null) {
            vo.setMaterialPriceName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_MATERIAL_PRICE, examineUnitDecorate.getMaterialPriceRange()));
        }
        if (examineUnitDecorate.getConstructionTechnology() != null) {
            vo.setConstructionTechnologyName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_CONSTRUCTION_TECHNOLOGY, examineUnitDecorate.getConstructionTechnology()));
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
     * @param examineUnitDecorate
     * @return
     */
    public boolean addExamineUnitDecorate(ExamineUnitDecorate examineUnitDecorate) {
        examineUnitDecorate.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineUnitDecorate.getDeclareId())) {
            examineUnitDecorate.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineUnitDecorate.getExamineType())) {
            examineUnitDecorate.setExamineType(0);
        }
        return examineUnitDecorateDao.addUnitDecorate(examineUnitDecorate);
    }

    /**
     * 编辑
     *
     * @param examineUnitDecorate
     * @return
     */
    public boolean updateExamineUnitDecorate(ExamineUnitDecorate examineUnitDecorate) {
        return examineUnitDecorateDao.updateUnitDecorate(examineUnitDecorate);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineUnitDecorate(Integer id) {
        return examineUnitDecorateDao.deleteUnitDecorate(id);
    }
}
