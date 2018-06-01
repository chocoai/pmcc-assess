package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.MethodMarketCompareCalculationDao;
import com.copower.pmcc.assess.dal.entity.MethodMarketCompareCalculation;
import com.copower.pmcc.assess.dto.input.project.MethodMarketCompareCalculationDto;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zly on 2018/5/31.
 */
@Service
public class MethodMarketCompareCalculationService {

    @Autowired
    private MethodMarketCompareCalculationDao methodMarketCompareCalculationDao;
    @Autowired
    private CommonService commonService;

    public void save(List<MethodMarketCompareCalculationDto> methodMarketCompareCalculationDtos) {
        for(MethodMarketCompareCalculationDto methodMarketCompareCalculationDto:methodMarketCompareCalculationDtos){
            methodMarketCompareCalculationDto.setCreator(commonService.thisUserAccount());
            methodMarketCompareCalculationDao.save(methodMarketCompareCalculationDto);
        }
    }

    public List<MethodMarketCompareCalculation> getDataByEvaluationObjectId(Integer schemeEvaluationObjectId) {
        return methodMarketCompareCalculationDao.getDataByEvaluationObjectId(schemeEvaluationObjectId);
    }
}
