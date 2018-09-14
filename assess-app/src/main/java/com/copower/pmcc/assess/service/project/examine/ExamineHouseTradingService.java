package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineHouseTradingDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTrading;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineHouseTradingVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by kings on 2018-7-6.
 */
@Service
public class ExamineHouseTradingService {
    @Autowired
    private ExamineHouseTradingDao examineHouseTradingDao;
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
    public ExamineHouseTrading getHouseTradingById(Integer id) {
        return examineHouseTradingDao.getHouseTradingById(id);
    }

    /**
     * 获取数据
     *
     * @param declareId
     * @return
     */
    public ExamineHouseTrading getHouseTradingByDeclareId(Integer declareId, Integer planDetailsId, ExamineTypeEnum examineTypeEnum) {
        return examineHouseTradingDao.getHouseTradingByDeclareId(declareId, planDetailsId, examineTypeEnum.getId());
    }

    public ExamineHouseTradingVo getExamineHouseTradingVo(ExamineHouseTrading examineHouseTrading) {
        if (examineHouseTrading == null) return null;
        BaseDataDic sysDataDicTemp = null;
        ExamineHouseTradingVo examineHouseTradingVo = new ExamineHouseTradingVo();
        BeanUtils.copyProperties(examineHouseTrading, examineHouseTradingVo);
        if (examineHouseTrading.getDescriptionType() != null) {
            sysDataDicTemp = baseDataDicService.getDataDicById(examineHouseTrading.getDescriptionType());
            if (sysDataDicTemp != null) {
                examineHouseTradingVo.setDescriptionTypeName(sysDataDicTemp.getName());
                sysDataDicTemp = null;
            }
        }
        if (examineHouseTrading.getTradingType() != null) {
            sysDataDicTemp = baseDataDicService.getDataDicById(examineHouseTrading.getTradingType());
            if (sysDataDicTemp != null) {
                examineHouseTradingVo.setTradingTypeName(sysDataDicTemp.getName());
                sysDataDicTemp = null;
            }
        }
        if (!StringUtils.isEmpty(examineHouseTrading.getPaymentMethod())) {
            if (NumberUtils.isNumber(examineHouseTrading.getPaymentMethod())){
                sysDataDicTemp = baseDataDicService.getDataDicById(Integer.parseInt(examineHouseTrading.getPaymentMethod()));
                if (sysDataDicTemp != null) {
                    examineHouseTradingVo.setPaymentMethodName(sysDataDicTemp.getName());
                    sysDataDicTemp = null;
                }
            }
        }
        if (!StringUtils.isEmpty(examineHouseTrading.getNormalTransaction())) {
            if (NumberUtils.isNumber(examineHouseTrading.getNormalTransaction())){
                sysDataDicTemp = baseDataDicService.getDataDicById(Integer.parseInt(examineHouseTrading.getNormalTransaction()));
                if (sysDataDicTemp != null) {
                    examineHouseTradingVo.setNormalTransactionName(sysDataDicTemp.getName());
                    sysDataDicTemp = null;
                }
            }
        }
        if (!StringUtils.isEmpty(examineHouseTrading.getInformationType())) {
            if (NumberUtils.isNumber(examineHouseTrading.getInformationType())){
                sysDataDicTemp = baseDataDicService.getDataDicById(Integer.parseInt(examineHouseTrading.getInformationType()));
                if (sysDataDicTemp != null) {
                    examineHouseTradingVo.setInformationTypeName(sysDataDicTemp.getName());
                    sysDataDicTemp = null;
                }
            }
        }
        if (!StringUtils.isEmpty(examineHouseTrading.getTaxBurden())) {
            if (NumberUtils.isNumber(examineHouseTrading.getTaxBurden())){
                sysDataDicTemp = baseDataDicService.getDataDicById(Integer.parseInt(examineHouseTrading.getTaxBurden()));
                if (sysDataDicTemp != null) {
                    examineHouseTradingVo.setTaxBurdenName(sysDataDicTemp.getName());
                    sysDataDicTemp = null;
                }
            }
        }
        return examineHouseTradingVo;
    }

    /**
     * 保存数据
     *
     * @param examineHouseTrading
     */
    @Transactional
    public Integer saveHouseTrading(ExamineHouseTrading examineHouseTrading) throws BusinessException {
        if (examineHouseTrading == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (examineHouseTrading.getId() != null && examineHouseTrading.getId() > 0) {
            examineHouseTradingDao.updateHouseTrading(examineHouseTrading);
        } else {
            examineHouseTrading.setCreator(commonService.thisUserAccount());
            examineHouseTradingDao.addHouseTrading(examineHouseTrading);
        }
        return examineHouseTrading.getId();
    }
}
