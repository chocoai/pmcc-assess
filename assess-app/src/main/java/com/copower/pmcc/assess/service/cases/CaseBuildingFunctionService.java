package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseBuildingFunctionDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingFunction;
import com.copower.pmcc.assess.dto.output.cases.CaseBuildingFunctionVo;
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
 * @Date: 2018/9/18 11:57
 * @Description:
 */
@Service
public class CaseBuildingFunctionService {
    @Autowired
    private CaseBuildingFunctionDao caseBuildingFunctionDao;

    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public Integer saveAndUpdateCaseBuildingFunction(CaseBuildingFunction caseBuildingFunction) {
        if (caseBuildingFunction.getId() == null) {
            caseBuildingFunction.setCreator(commonService.thisUserAccount());
            return caseBuildingFunctionDao.addBuildingOutfit(caseBuildingFunction);
        } else {
            caseBuildingFunctionDao.updateBuildingOutfit(caseBuildingFunction);
            return null;
        }
    }

    public CaseBuildingFunction getCaseBuildingFunctionById(Integer id) {
        return caseBuildingFunctionDao.getBuildingOutfitById(id);
    }

    public BootstrapTableVo getCaseBuildingFunctionListVos(CaseBuildingFunction caseBuildingFunction) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseBuildingFunctionVo> vos = getCaseBuildingFunctionList(caseBuildingFunction);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<CaseBuildingFunctionVo> getCaseBuildingFunctionList(CaseBuildingFunction caseBuildingFunction) {
        List<CaseBuildingFunction> caseBuildingFunctions = caseBuildingFunctionDao.getBuildingOutfitList(caseBuildingFunction);
        List<CaseBuildingFunctionVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(caseBuildingFunctions)) {
            for (CaseBuildingFunction landLevel : caseBuildingFunctions) {
                vos.add(getCaseBuildingFunctionVo(landLevel));
            }
        }
        return vos;
    }

    public List<CaseBuildingFunction> getCaseBuildingFunctionListO(CaseBuildingFunction caseBuildingFunction) {
        List<CaseBuildingFunction> caseBuildingFunctions = caseBuildingFunctionDao.getBuildingOutfitList(caseBuildingFunction);
        return caseBuildingFunctions;
    }

    public void upgradeVersion(CaseBuildingFunction caseBuildingFunction)throws Exception{
        if (caseBuildingFunction.getId() == null){
            caseBuildingFunction.setCreator(commonService.thisUserAccount());
            caseBuildingFunction.setVersion(0);
            caseBuildingFunctionDao.addBuildingOutfit(caseBuildingFunction);
            return;
        }
        if (caseBuildingFunction.getId() != null){
            CaseBuildingFunction oo = this.getCaseBuildingFunctionById(caseBuildingFunction.getId());
            if (oo.getVersion() == null){
                oo.setVersion(0);
            }
            int version = oo.getVersion() + 1;
            BeanCopyHelp.copyPropertiesIgnoreNull(caseBuildingFunction, oo);
            oo.setVersion(version);
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            oo.setCreator(commonService.thisUserAccount());
            caseBuildingFunctionDao.addBuildingOutfit(oo);
            return;
        }
    }

    public boolean removeCaseBuildingFunction(CaseBuildingFunction caseBuildingFunction) {
        try {
            caseBuildingFunctionDao.deleteBuildingOutfit(caseBuildingFunction.getId());
            return true;
        } catch (Exception e1) {
            try {
                throw new Exception();
            } catch (Exception e11) {

            }
        }
        return false;
    }

    public CaseBuildingFunctionVo getCaseBuildingFunctionVo(CaseBuildingFunction caseBuildingFunction) {
        CaseBuildingFunctionVo vo = new CaseBuildingFunctionVo();
        BeanUtils.copyProperties(caseBuildingFunction, vo);
        if (caseBuildingFunction.getDecorationPart() != null) {
            vo.setDecorationPartName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_DECORATION_PART, caseBuildingFunction.getDecorationPart()));
        }
        if (caseBuildingFunction.getDecoratingMaterial() != null) {
            vo.setDecoratingMaterialName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_DECORATING_MATERIAL, caseBuildingFunction.getDecoratingMaterial()));
        }
        if (caseBuildingFunction.getMaterialPrice() != null) {
            vo.setMaterialPriceName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_MATERIAL_PRICE, caseBuildingFunction.getMaterialPrice()));
        }
        if (caseBuildingFunction.getConstructionTechnology() != null) {
            vo.setConstructionTechnologyName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_CONSTRUCTION_TECHNOLOGY, caseBuildingFunction.getConstructionTechnology()));
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
     * 根据查询条件判断是否有数据
     * @param buildingId
     * @return
     */
    public boolean hasBuildingFunctionData(Integer buildingId){
        return caseBuildingFunctionDao.countByBuildingId(buildingId)>0;
    }
}
