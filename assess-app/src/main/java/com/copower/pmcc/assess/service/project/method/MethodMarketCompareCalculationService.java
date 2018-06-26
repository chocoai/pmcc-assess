package com.copower.pmcc.assess.service.project.method;

import com.copower.pmcc.assess.dal.dao.project.method.MethodMarketCompareCalculationDao;
import com.copower.pmcc.assess.dal.dao.project.method.MethodMarketCompareIndexDao;
import com.copower.pmcc.assess.dal.entity.MethodMarketCompareCalculation;
import com.copower.pmcc.assess.dal.entity.MethodMarketCompareIndex;
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
    @Autowired
    private MethodMarketCompareIndexDao methodMarketCompareIndexDao;

    public void save(List<MethodMarketCompareCalculationDto> methodMarketCompareCalculationDtos) {
        for(MethodMarketCompareCalculationDto methodMarketCompareCalculationDto:methodMarketCompareCalculationDtos){
            MethodMarketCompareIndex methodMarketCompareIndex = new MethodMarketCompareIndex();
            methodMarketCompareIndex.setName(methodMarketCompareCalculationDto.getName());
            methodMarketCompareIndex.setEvaluationObjectId(methodMarketCompareCalculationDto.getEvaluationObjectId());
            List<MethodMarketCompareIndex> marketCompareIndexList = methodMarketCompareIndexDao.getDataByObject(methodMarketCompareIndex);
            Integer compareIndexId = marketCompareIndexList.get(0).getId();
            methodMarketCompareCalculationDto.setCreator(commonService.thisUserAccount());
            methodMarketCompareCalculationDto.setCompareIndexId(compareIndexId);
            methodMarketCompareCalculationDao.save(methodMarketCompareCalculationDto);
        }
    }

    public List<MethodMarketCompareCalculation> getDataByEvaluationObjectId(Integer schemeEvaluationObjectId) {
        return methodMarketCompareCalculationDao.getDataByEvaluationObjectId(schemeEvaluationObjectId);
    }

    public void updata(List<MethodMarketCompareCalculationDto> methodMarketCompareCalculationDtos) {
        for(MethodMarketCompareCalculationDto methodMarketCompareCalculationDto:methodMarketCompareCalculationDtos){
            methodMarketCompareCalculationDao.update(methodMarketCompareCalculationDto);
        }
    }
}
