package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.MethodMarketCompareFactorDao;
import com.copower.pmcc.assess.dal.entity.MethodMarketCompareFactor;
import com.copower.pmcc.assess.dto.input.project.MethodMarketCompareFactorDto;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zly on 2018/5/31.
 */
@Service
public class MethodMarketCompareFactorService {

    @Autowired
    private MethodMarketCompareFactorDao methodMarketCompareFactorDao;
    @Autowired
    private CommonService commonService;

    public void save(List<MethodMarketCompareFactorDto> methodMarketCompareFactorDtos) {
        for(MethodMarketCompareFactorDto methodMarketCompareFactorDto:methodMarketCompareFactorDtos){
            methodMarketCompareFactorDto.setCreator(commonService.thisUserAccount());
            methodMarketCompareFactorDao.save(methodMarketCompareFactorDto);
        }
    }

    public List<MethodMarketCompareFactor> getDataByEvaluationObjectId(Integer schemeEvaluationObjectId) {
        return methodMarketCompareFactorDao.getDataByEvaluationObjectId(schemeEvaluationObjectId);
    }

    public void updata(List<MethodMarketCompareFactorDto> methodMarketCompareFactorDtos) {
        for(MethodMarketCompareFactorDto methodMarketCompareFactorDto:methodMarketCompareFactorDtos){
            methodMarketCompareFactorDao.update(methodMarketCompareFactorDto);
        }

    }
}
