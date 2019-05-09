package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyExaminePurenessLandDao;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExaminePurenessLand;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExaminePurenessLandVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zch
 * @date: 2019/5/9 16:14
 * @description:土地的查勘表
 */
@Service
public class SurveyExaminePurenessLandService {

    @Autowired
    private SurveyExaminePurenessLandDao dataLandDetailAchievementDao;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataLandLevelDetailService dataLandLevelDetailService;

    public boolean saveSurveyExaminePurenessLand(SurveyExaminePurenessLand oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            return dataLandDetailAchievementDao.saveSurveyExaminePurenessLand(oo);
        } else {
            return dataLandDetailAchievementDao.editSurveyExaminePurenessLand(oo);
        }
    }

    public boolean deleteSurveyExaminePurenessLand(Integer id){
        return dataLandDetailAchievementDao.deleteSurveyExaminePurenessLand(id);
    }

    public SurveyExaminePurenessLand getSurveyExaminePurenessLandById(Integer id){
        return dataLandDetailAchievementDao.getSurveyExaminePurenessLandById(id);
    }

    public List<SurveyExaminePurenessLand> getSurveyExaminePurenessLandList(SurveyExaminePurenessLand oo){
        return dataLandDetailAchievementDao.getSurveyExaminePurenessLandList(oo);
    }

    public BootstrapTableVo getBootstrapTableVo(SurveyExaminePurenessLand oo){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyExaminePurenessLand> list = getSurveyExaminePurenessLandList(oo);
        List<SurveyExaminePurenessLandVo> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)){
            list.stream().forEach(po -> voList.add(getSurveyExaminePurenessLandVo(po)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }

    public SurveyExaminePurenessLandVo getSurveyExaminePurenessLandVo(SurveyExaminePurenessLand oo){
        if (oo==null){
            return null;
        }
        SurveyExaminePurenessLandVo vo = new SurveyExaminePurenessLandVo();
        org.springframework.beans.BeanUtils.copyProperties(oo,vo);
        try {
            vo.setAreaName(erpAreaService.getAreaFullName(oo.getProvince(),oo.getCity(),oo.getDistrict()));
            vo.setProvinceName(erpAreaService.getSysAreaName(oo.getProvince()));
            vo.setCityName(erpAreaService.getSysAreaName(oo.getCity()));
            vo.setDistrictName(erpAreaService.getSysAreaName(oo.getDistrict()));
        } catch (Exception e) {
        }
        vo.setLandUseTypeName(baseDataDicService.getNameById(oo.getLandUseType()));
        vo.setLandUseCategoryName(baseDataDicService.getNameById(oo.getLandUseCategory()));
        if (oo.getLandLevel() != null) {
            vo.setLandLevelName(dataLandLevelDetailService.getCacheNameById(oo.getLandLevel()));
        }
        vo.setShapeStateName(baseDataDicService.getNameById(oo.getShapeState()));
        vo.setPlanenessName(baseDataDicService.getNameById(oo.getPlaneness()));
        vo.setDevelopmentDegreeName(baseDataDicService.getNameById(oo.getDevelopmentDegree()));
        vo.setTopographicTerrainName(baseDataDicService.getNameById(oo.getTopographicTerrain()));
        vo.setInfrastructureCompletenessName(baseDataDicService.getNameById(oo.getInfrastructureCompleteness()));
        vo.setPhName(baseDataDicService.getNameById(oo.getPh()));
        vo.setHoldOnName(baseDataDicService.getNameById(oo.getHoldOn()));
        vo.setFertilityName(baseDataDicService.getNameById(oo.getFertility()));
        vo.setContaminatedName(baseDataDicService.getNameById(oo.getContaminated()));
        vo.setBearingCapacityName(baseDataDicService.getNameById(oo.getBearingCapacity()));
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(oo.getDevelopmentDegreeContent())){
            List<String> stringList = Lists.newArrayList(oo.getDevelopmentDegreeContent().split(",")).stream().map(s -> baseDataDicService.getNameById(s)).collect(Collectors.toList());
            vo.setDevelopmentDegreeContentName(org.apache.commons.lang3.StringUtils.join(stringList,"、"));
        }
        vo.setInformationTypeName(baseDataDicService.getNameById(oo.getInformationType()));
        vo.setInformationCategoryName(baseDataDicService.getNameById(oo.getInformationCategory()));
        vo.setPriceConnotationName(baseDataDicService.getNameById(oo.getPriceConnotation()));
        return vo;
    }
    
}
