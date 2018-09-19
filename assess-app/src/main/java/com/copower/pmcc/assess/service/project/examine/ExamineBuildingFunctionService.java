package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineBuildingFunctionDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingFunction;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineBuildingFunctionVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 11:49
 * @Description:建筑功能
 */
@Service
public class ExamineBuildingFunctionService {
    @Autowired
    private ExamineBuildingFunctionDao examineBuildingFunctionDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public Integer saveAndUpdateExamineBuildingFunction(ExamineBuildingFunction examineBuildingFunction) {
        if (examineBuildingFunction.getId() == null) {
            examineBuildingFunction.setCreator(commonService.thisUserAccount());
            return examineBuildingFunctionDao.addExamineBuildingFunction(examineBuildingFunction);
        } else {
            examineBuildingFunctionDao.updateExamineBuildingFunction(examineBuildingFunction);
            return null;
        }
    }

    public ExamineBuildingFunction getExamineBuildingFunctionById(Integer id) {
        return examineBuildingFunctionDao.getExamineBuildingFunctionById(id);
    }

    public BootstrapTableVo getExamineBuildingFunctionListVos(ExamineBuildingFunction examineBuildingFunction) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineBuildingFunctionVo> vos = getExamineBuildingFunctionList(examineBuildingFunction);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<ExamineBuildingFunctionVo> getExamineBuildingFunctionList(ExamineBuildingFunction examineBuildingFunction) {
        List<ExamineBuildingFunction> examineBuildingFunctions = examineBuildingFunctionDao.getExamineBuildingFunctionList(examineBuildingFunction);
        List<ExamineBuildingFunctionVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(examineBuildingFunctions)) {
            for (ExamineBuildingFunction landLevel : examineBuildingFunctions) {
                vos.add(getExamineBuildingFunctionVo(landLevel));
            }
        }
        return vos;
    }

    public boolean removeExamineBuildingFunction(ExamineBuildingFunction examineBuildingFunction) {
        try {
            examineBuildingFunctionDao.removeExamineBuildingFunction(examineBuildingFunction);
            return true;
        } catch (Exception e1) {
            try {
                throw new Exception();
            } catch (Exception e11) {

            }
        }
        return false;
    }

    public ExamineBuildingFunctionVo getExamineBuildingFunctionVo(ExamineBuildingFunction examineBuildingFunction) {
        ExamineBuildingFunctionVo vo = new ExamineBuildingFunctionVo();
        BeanUtils.copyProperties(examineBuildingFunction, vo);
        if (examineBuildingFunction.getDecorationPart() != null) {
            vo.setDecorationPartName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_DECORATION_PART, examineBuildingFunction.getDecorationPart()));
        }
        if (examineBuildingFunction.getDecoratingMaterial() != null) {
            vo.setDecoratingMaterialName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_DECORATING_MATERIAL, examineBuildingFunction.getDecoratingMaterial()));
        }
        if (examineBuildingFunction.getMaterialPrice() != null) {
            vo.setMaterialPriceName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_MATERIAL_PRICE, examineBuildingFunction.getMaterialPrice()));
        }
        if (examineBuildingFunction.getConstructionTechnology() != null) {
            vo.setConstructionTechnologyName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_CONSTRUCTION_TECHNOLOGY, examineBuildingFunction.getConstructionTechnology()));
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
}
