package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineHouseDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouse;
import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitHuxing;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineHouseVo;
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
public class ExamineHouseService {
    @Autowired
    private ExamineHouseDao examineHouseDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ExamineUnitHuxingService examineUnitHuxingService;

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public ExamineHouse getHouseById(Integer id) {
        return examineHouseDao.getHouseById(id);
    }

    /**
     * 获取数据
     *
     * @param declareId
     * @return
     */
    public ExamineHouse getHouseByDeclareId(Integer declareId,Integer planDetailsId,ExamineTypeEnum examineTypeEnum) {
        return examineHouseDao.getHouseByDeclareId(declareId,planDetailsId,examineTypeEnum.getId());
    }

    public ExamineHouse getHouseByDeclareId(Integer declareId,ExamineTypeEnum examineTypeEnum) {
        return examineHouseDao.getHouseByDeclareId(declareId,examineTypeEnum.getId());
    }

    public ExamineHouseVo getExamineHouseVo(ExamineHouse examineHouse) {
        if (examineHouse == null) return null;
        ExamineHouseVo examineHouseVo = new ExamineHouseVo();
        BeanUtils.copyProperties(examineHouse, examineHouseVo);
        BaseDataDic baseDataDic = null;
        if (examineHouse.getCertUse() != null){
            baseDataDic = baseDataDicService.getDataDicById(examineHouse.getCertUse());
            if (baseDataDic != null){
                examineHouseVo.setCertUseName(baseDataDic.getName());
            }
            baseDataDic = null;
        }
        if (examineHouse.getPracticalUse() != null){
            baseDataDic = baseDataDicService.getDataDicById(examineHouse.getPracticalUse());
            if (baseDataDic != null){
                examineHouseVo.setPracticalUseName(baseDataDic.getName());
            }
            baseDataDic = null;
        }
        if (examineHouse.getUseEnvironment() != null){
            baseDataDic = baseDataDicService.getDataDicById(examineHouse.getUseEnvironment());
            if (baseDataDic != null){
                examineHouseVo.setUseEnvironmentName(baseDataDic.getName());
            }
            baseDataDic = null;
        }
        if (examineHouse.getHuxingId() != null){
            ExamineUnitHuxing examineUnitHuxing = examineUnitHuxingService.getExamineUnitHuxingById(examineHouse.getHuxingId());
            examineHouseVo.setHuxingName(examineUnitHuxing.getDescription());
        }
        if (examineHouse.getNewsHuxing() != null){
            baseDataDic = baseDataDicService.getDataDicById(examineHouse.getNewsHuxing());
            if (baseDataDic != null){
                examineHouseVo.setNewsHuxingName(baseDataDic.getName());
            }
            baseDataDic = null;
        }
        return examineHouseVo;
    }


    /**
     * 保存数据
     *
     * @param examineHouse
     */
    @Transactional
    public Integer saveHouse(ExamineHouse examineHouse) throws BusinessException {
        if (examineHouse == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (examineHouse.getId() != null && examineHouse.getId() > 0) {
            examineHouseDao.updateHouse(examineHouse);
        } else {
            examineHouse.setCreator(commonService.thisUserAccount());
            examineHouseDao.addHouse(examineHouse);
        }
        return examineHouse.getId();
    }
}
