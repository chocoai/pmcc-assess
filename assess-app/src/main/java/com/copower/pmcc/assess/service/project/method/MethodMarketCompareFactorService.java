package com.copower.pmcc.assess.service.project.method;

import com.copower.pmcc.assess.dal.basis.dao.project.method.MethodMarketCompareFactorDao;
import com.copower.pmcc.assess.dal.basis.entity.MethodMarketCompareFactor;
import com.copower.pmcc.assess.dto.input.project.method.MethodMarketCompareFactorDto;
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
