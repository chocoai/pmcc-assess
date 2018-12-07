package com.copower.pmcc.assess.service.cases;

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

    public void removeCaseBuildingFunction(CaseBuildingFunction caseBuildingFunction) {
        caseBuildingFunctionDao.deleteBuildingOutfit(caseBuildingFunction.getId());
    }

    public CaseBuildingFunctionVo getCaseBuildingFunctionVo(CaseBuildingFunction caseBuildingFunction) {
        CaseBuildingFunctionVo vo = new CaseBuildingFunctionVo();
        BeanUtils.copyProperties(caseBuildingFunction, vo);
        if (caseBuildingFunction.getType() != null) {
            vo.setTypeName(baseDataDicService.getNameById(caseBuildingFunction.getType()));
        }
        if (caseBuildingFunction.getDecorationPart() != null) {
            vo.setDecorationPartName(baseDataDicService.getNameById(caseBuildingFunction.getDecorationPart()));
        }
        return vo;
    }

    /**
     * 根据查询条件判断是否有数据
     *
     * @param buildingId
     * @return
     */
    public boolean hasBuildingFunctionData(Integer buildingId) {
        return caseBuildingFunctionDao.countByBuildingId(buildingId) > 0;
    }
}
