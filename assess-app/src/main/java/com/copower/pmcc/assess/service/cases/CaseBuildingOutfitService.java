package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseBuildingOutfitDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingOutfit;
import com.copower.pmcc.assess.dto.output.cases.CaseBuildingOutfitVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 12:06
 * @Description:
 */
@Service
public class CaseBuildingOutfitService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseBuildingOutfitDao caseBuildingOutfitDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public CaseBuildingOutfit getCaseBuildingOutfitById(Integer id) {
        return caseBuildingOutfitDao.getBuildingOutfitById(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseBuildingOutfit
     * @return
     */
    public List<CaseBuildingOutfit> getCaseBuildingOutfitList(CaseBuildingOutfit caseBuildingOutfit) {
        return caseBuildingOutfitDao.getBuildingOutfitList(caseBuildingOutfit);
    }

    public BootstrapTableVo getCaseBuildingOutfitLists(CaseBuildingOutfit caseBuildingOutfit) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseBuildingOutfitVo> vos = Lists.newArrayList();
        getCaseBuildingOutfitList(caseBuildingOutfit).stream().forEach(oo -> vos.add(getCaseBuildingOutfitVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseBuildingOutfitVo>() : vos);
        return vo;
    }

    public CaseBuildingOutfitVo getCaseBuildingOutfitVo(CaseBuildingOutfit caseBuildingOutfit) {
        CaseBuildingOutfitVo vo = new CaseBuildingOutfitVo();
        BeanUtils.copyProperties(caseBuildingOutfit, vo);
        if (caseBuildingOutfit.getDecorationPart() != null) {
            vo.setDecorationPartName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_DECORATION_PART, caseBuildingOutfit.getDecorationPart()));
        }
        if (caseBuildingOutfit.getDecoratingMaterial() != null) {
            vo.setDecoratingMaterialName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_DECORATING_MATERIAL, caseBuildingOutfit.getDecoratingMaterial()));
        }
        if (caseBuildingOutfit.getMaterialPrice() != null) {
            vo.setMaterialPriceName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_MATERIAL_PRICE, caseBuildingOutfit.getMaterialPrice()));
        }
        if (caseBuildingOutfit.getConstructionTechnology() != null) {
            vo.setConstructionTechnologyName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_CONSTRUCTION_TECHNOLOGY, caseBuildingOutfit.getConstructionTechnology()));
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
     * @param caseBuildingOutfit
     * @return
     */
    public boolean addCaseBuildingOutfit(CaseBuildingOutfit caseBuildingOutfit) {
        if (caseBuildingOutfit.getBuildingId() == null) {
            caseBuildingOutfit.setBuildingId(0);
        }
        return caseBuildingOutfitDao.addBuildingOutfit(caseBuildingOutfit);
    }

    /**
     * 编辑
     *
     * @param caseBuildingOutfit
     * @return
     */
    public boolean updateCaseBuildingOutfit(CaseBuildingOutfit caseBuildingOutfit) {
        return caseBuildingOutfitDao.updateBuildingOutfit(caseBuildingOutfit);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseBuildingOutfit(Integer id) {
        return caseBuildingOutfitDao.deleteBuildingOutfit(id);
    }

    public boolean removeCaseBuildingOutfit(CaseBuildingOutfit caseBuildingOutfit) {
        try {
            caseBuildingOutfitDao.deleteBuildingOutfit(caseBuildingOutfit.getId());
            return true;
        } catch (Exception e1) {
            return false;
        }
    }

    /**
     * 根据查询条件判断是否有数据
     * @param buildingId
     * @return
     */
    public boolean hasBuildingOutfitData(Integer buildingId){
        return caseBuildingOutfitDao.countByBuildingId(buildingId)>0;
    }
}
