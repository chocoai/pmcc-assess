package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineEstateLandStateDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateLandState;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineEstateLandStateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kings on 2018-7-6.
 */
@Service
public class ExamineEstateLandStateService {
    @Autowired
    private ExamineEstateLandStateDao examineEstateLandStateDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public ExamineEstateLandState getEstateLandStateById(Integer id) {
        return examineEstateLandStateDao.getEstateLandStateById(id);
    }

    /**
     * 获取数据
     *
     * @param declareId
     * @return
     */
    public ExamineEstateLandState getEstateLandStateByDeclareId(Integer declareId,Integer planDetailsId,ExamineTypeEnum examineTypeEnum) {
        return examineEstateLandStateDao.getEstateLandStateByDeclareId(declareId,planDetailsId,examineTypeEnum.getId());
    }

    public ExamineEstateLandStateVo getExamineEstateLandStateVo(ExamineEstateLandState examineEstateLandState) {
        if (examineEstateLandState == null) return null;
        ExamineEstateLandStateVo examineEstateLandStateVo = new ExamineEstateLandStateVo();
        BeanUtils.copyProperties(examineEstateLandState, examineEstateLandStateVo);
        BaseDataDic sysDataDicTemp = null;
        if (examineEstateLandState.getLandLevel() != null){
            sysDataDicTemp = baseDataDicService.getDataDicById(examineEstateLandState.getLandLevel());
            if (sysDataDicTemp != null){
                examineEstateLandStateVo.setLandLevelName(sysDataDicTemp.getName());
                sysDataDicTemp = null;
            }
        }
        if (examineEstateLandState.getLandUseType() != null){
            sysDataDicTemp = baseDataDicService.getDataDicById(examineEstateLandState.getLandUseType());
            if (sysDataDicTemp != null){
                examineEstateLandStateVo.setLandUseTypeName(sysDataDicTemp.getName());
                sysDataDicTemp = null;
            }
        }
        if (examineEstateLandState.getLandUseCategory() != null){
            sysDataDicTemp = baseDataDicService.getDataDicById(examineEstateLandState.getLandUseCategory());
            if (sysDataDicTemp != null){
                examineEstateLandStateVo.setLandUseCategoryName(sysDataDicTemp.getName());
                sysDataDicTemp = null;
            }
        }
        return examineEstateLandStateVo;
    }

    /**
     * 保存数据
     *
     * @param examineEstateLandState
     */
    @Transactional
    public Integer saveEstateLandState(ExamineEstateLandState examineEstateLandState) throws BusinessException {
        if (examineEstateLandState == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (examineEstateLandState.getId() != null && examineEstateLandState.getId() > 0) {
            examineEstateLandStateDao.updateEstateLandState(examineEstateLandState);
        } else {
            examineEstateLandState.setCreator(commonService.thisUserAccount());
            examineEstateLandStateDao.addEstateLandState(examineEstateLandState);
        }
        return examineEstateLandState.getId();
    }
}
