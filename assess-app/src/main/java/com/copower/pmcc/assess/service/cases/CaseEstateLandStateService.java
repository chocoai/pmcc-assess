package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.dal.cases.dao.CaseEstateLandStateDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateLandState;
import com.copower.pmcc.assess.dto.output.cases.CaseEstateLandStateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.assess.service.data.DataLandLevelService;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            DataLandLevelDetail dataLandLevelDetail = dataLandLevelDetailService.getDataLandLevelDetailById(caseEstateLandState.getLandLevel());
            if (dataLandLevelDetail != null) {
                vo.setLandLevelName(String.format("%s%s",dataLandLevelDetail.getCategory(),dataLandLevelDetail.getClassify()));
            }
        }
        if (caseEstateLandState.getLandLevel() != null){
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
        return vo;
    }
}
