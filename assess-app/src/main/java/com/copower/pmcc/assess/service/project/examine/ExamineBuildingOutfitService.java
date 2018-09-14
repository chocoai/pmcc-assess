package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineBuildingOutfitDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingOutfit;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineBuildingOutfitVo;
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
 * @Date: 2018/7/23 17:32
 * @Description:楼栋外装情况
 */
@Service
public class ExamineBuildingOutfitService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineBuildingOutfitDao examineBuildingOutfitDao;
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
    public ExamineBuildingOutfit getExamineBuildingOutfitById(Integer id) {
        return examineBuildingOutfitDao.getBuildingOutfitById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineBuildingOutfit
     * @return
     */
    public List<ExamineBuildingOutfit> getExamineBuildingOutfitList(ExamineBuildingOutfit examineBuildingOutfit) {
        return examineBuildingOutfitDao.getBuildingOutfitList(examineBuildingOutfit);
    }

    public BootstrapTableVo getExamineBuildingOutfitLists(ExamineBuildingOutfit examineBuildingOutfit) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineBuildingOutfitVo> vos = Lists.newArrayList();
        getExamineBuildingOutfitList(examineBuildingOutfit).stream().forEach(oo -> vos.add(getExamineBuildingOutfitVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineBuildingOutfitVo>() : vos);
        return vo;
    }

    public ExamineBuildingOutfitVo getExamineBuildingOutfitVo(ExamineBuildingOutfit examineBuildingOutfit) {
        ExamineBuildingOutfitVo vo = new ExamineBuildingOutfitVo();
        BeanUtils.copyProperties(examineBuildingOutfit, vo);
        if (examineBuildingOutfit.getDecorationPart() != null) {
            vo.setDecorationPartName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_DECORATION_PART, examineBuildingOutfit.getDecorationPart()));
        }
        if (examineBuildingOutfit.getDecoratingMaterial() != null) {
            vo.setDecoratingMaterialName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_DECORATING_MATERIAL, examineBuildingOutfit.getDecoratingMaterial()));
        }
        if (examineBuildingOutfit.getMaterialPrice() != null) {
            vo.setMaterialPriceName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_MATERIAL_PRICE, examineBuildingOutfit.getMaterialPrice()));
        }
        if (examineBuildingOutfit.getConstructionTechnology() != null) {
            vo.setConstructionTechnologyName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_CONSTRUCTION_TECHNOLOGY, examineBuildingOutfit.getConstructionTechnology()));
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
     * @param examineBuildingOutfit
     * @return
     */
    public boolean addExamineBuildingOutfit(ExamineBuildingOutfit examineBuildingOutfit) {
        examineBuildingOutfit.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineBuildingOutfit.getDeclareId())) {
            examineBuildingOutfit.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineBuildingOutfit.getExamineType())) {
            examineBuildingOutfit.setExamineType(0);
        }
        if (examineBuildingOutfit.getBuildingId() == null) {
            examineBuildingOutfit.setBuildingId(0);
        }
        return examineBuildingOutfitDao.addBuildingOutfit(examineBuildingOutfit);
    }

    /**
     * 编辑
     *
     * @param examineBuildingOutfit
     * @return
     */
    public boolean updateExamineBuildingOutfit(ExamineBuildingOutfit examineBuildingOutfit) {
        return examineBuildingOutfitDao.updateBuildingOutfit(examineBuildingOutfit);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineBuildingOutfit(Integer id) {
        return examineBuildingOutfitDao.deleteBuildingOutfit(id);
    }

    public boolean removeExamineBuildingOutfit(ExamineBuildingOutfit examineBuildingOutfit){
        try {
            examineBuildingOutfitDao.removeExamineBuildingOutfit(examineBuildingOutfit);
            return  true;
        } catch (Exception e1) {
            return  false;
        }
    }

    public boolean initRemove(){
        try {
            examineBuildingOutfitDao.initRemove();
        } catch (Exception e1) {
            return false;
        }
        return true;
    }
}
