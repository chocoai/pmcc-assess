package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
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
    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<CaseEstateLandState> getCaseEstateLandStateList(CaseEstateLandState caseEstateLandState) {

        return caseEstateLandStateDao.getEstateLandStateList(caseEstateLandState);
    }

    public CaseEstateLandState getCaseEstateLandStateById(Integer id) {

        return caseEstateLandStateDao.getEstateLandStateById(id);
    }

    public Integer upgradeVersion(CaseEstateLandState caseEstateLandState) throws Exception {
        if (caseEstateLandState.getId() == null || caseEstateLandState.getId().intValue() == 0) {
            caseEstateLandState.setCreator(commonService.thisUserAccount());
            caseEstateLandState.setVersion(0);
            Integer id = caseEstateLandStateDao.saveCaseEstateLandState(caseEstateLandState);
            caseEstateLandState.setId(id);
            return id;
        } else {
            //更新版本
            CaseEstateLandState oo = caseEstateLandStateDao.getEstateLandStateById(caseEstateLandState.getId());
            if (oo != null) {
                if (oo.getVersion() == null) {
                    oo.setVersion(0);
                }
            }
            int version = oo.getVersion() + 1;
            BeanCopyHelp.copyPropertiesIgnoreNull(caseEstateLandState, oo);
            oo.setVersion(version);
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            oo.setCreator(commonService.thisUserAccount());
            Integer id = caseEstateLandStateDao.saveCaseEstateLandState(oo);
            caseEstateLandState.setId(id);
            return id;
        }
    }

    public boolean saveAndUpdateCaseEstateLandState(CaseEstateLandState caseEstateLandState) {
        if (caseEstateLandState.getId() == null || caseEstateLandState.getId().intValue() == 0) {
            caseEstateLandState.setCreator(commonService.thisUserAccount());
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
        BaseDataDic dataDic = null;
        if (caseEstateLandState.getLandUseType() != null){
            dataDic = baseDataDicService.getDataDicById(caseEstateLandState.getLandUseType());
            if (dataDic != null){
                vo.setLandUseTypeName(dataDic.getName());
                dataDic = null;
            }
        }
        if (caseEstateLandState.getLandUseCategory() != null){
            dataDic = baseDataDicService.getDataDicById(caseEstateLandState.getLandUseCategory());
            if (dataDic != null){
                vo.setLandUseCategoryName(dataDic.getName());
                dataDic = null;
            }
        }
        if (caseEstateLandState.getLandLevel() != null) {
            DataLandLevelDetail dataLandLevelDetail = dataLandLevelDetailService.getDataLandLevelDetailById(caseEstateLandState.getLandLevel());
            if (dataLandLevelDetail != null) {
                vo.setLandLevelName(String.format("%s%s",dataLandLevelDetail.getCategory(),dataLandLevelDetail.getClassify()));
            }
        }
        return vo;
    }
}
