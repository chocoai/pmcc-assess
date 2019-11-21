package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseEstateLandStateDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateLandState;
import com.copower.pmcc.assess.dto.output.cases.CaseEstateLandStateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.assess.service.data.DataLandLevelService;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: zch
 * @Date: 2018/9/12 10:54
 * @Description:
 */
@Service
public class CaseEstateLandStateService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseEstateLandStateDao caseEstateLandStateDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataLandLevelService dataLandLevelService;
    @Autowired
    private DataLandLevelDetailService dataLandLevelDetailService;

    public List<CaseEstateLandState> getCaseEstateLandStateList(CaseEstateLandState caseEstateLandState) {

        return caseEstateLandStateDao.getEstateLandStateList(caseEstateLandState);
    }

    public CaseEstateLandState getCaseEstateLandStateById(Integer id) {

        return caseEstateLandStateDao.getEstateLandStateById(id);
    }

    public boolean saveAndUpdateCaseEstateLandState(CaseEstateLandState caseEstateLandState) {
        if (caseEstateLandState.getId() == null || caseEstateLandState.getId().intValue() == 0) {
            return caseEstateLandStateDao.addEstateLandState(caseEstateLandState);
        } else {
            return caseEstateLandStateDao.updateEstateLandState(caseEstateLandState);
        }
    }

    public boolean deleteCaseEstateLandState(Integer id) {
        return caseEstateLandStateDao.deleteEstateLandState(id);
    }

    public CaseEstateLandStateVo getCaseEstateLandStateVo(CaseEstateLandState caseEstateLandState){
        CaseEstateLandStateVo vo = new CaseEstateLandStateVo();
        BeanUtils.copyProperties(caseEstateLandState,vo);
        if (caseEstateLandState.getLandUseType() != null){
            vo.setLandUseTypeName(baseDataDicService.getNameById(caseEstateLandState.getLandUseType()));
        }
        if (caseEstateLandState.getLandUseCategory() != null){
            vo.setLandUseCategoryName(baseDataDicService.getNameById(caseEstateLandState.getLandUseCategory()));
        }
        if (caseEstateLandState.getLandLevel() != null) {
            vo.setLandLevelName(dataLandLevelDetailService.getCacheNameById(caseEstateLandState.getLandLevel()));
        }
        if (caseEstateLandState.getShapeState() != null) {
            vo.setShapeStateName(baseDataDicService.getNameById(caseEstateLandState.getShapeState()));
        }
        if (caseEstateLandState.getPlaneness() != null) {
            vo.setPlanenessName(baseDataDicService.getNameById(caseEstateLandState.getPlaneness()));
        }
        if (caseEstateLandState.getDevelopmentDegree() != null) {
            vo.setDevelopmentDegreeName(baseDataDicService.getNameById(caseEstateLandState.getDevelopmentDegree()));
        }
        if (caseEstateLandState.getTopographicTerrain() != null) {
            vo.setTopographicTerrainName(baseDataDicService.getNameById(caseEstateLandState.getTopographicTerrain()));
        }
        vo.setInfrastructureCompletenessName(baseDataDicService.getNameById(caseEstateLandState.getInfrastructureCompleteness()));
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(caseEstateLandState.getDevelopmentDegreeContent())){
            List<String> stringList = Lists.newArrayList(caseEstateLandState.getDevelopmentDegreeContent().split(",")).stream().map(s -> baseDataDicService.getNameById(s)).collect(Collectors.toList());
            vo.setDevelopmentDegreeContentName(org.apache.commons.lang3.StringUtils.join(stringList,"、"));
        }
        vo.setHoldOnName(baseDataDicService.getNameById(caseEstateLandState.getHoldOn()));
        vo.setBearingCapacityName(baseDataDicService.getNameById(caseEstateLandState.getBearingCapacity()));
        vo.setFertilityName(baseDataDicService.getNameById(caseEstateLandState.getFertility()));
        vo.setPhName(baseDataDicService.getNameById(caseEstateLandState.getPh()));
        vo.setContaminatedName(baseDataDicService.getNameById(caseEstateLandState.getContaminated()));
        return vo;
    }
}
