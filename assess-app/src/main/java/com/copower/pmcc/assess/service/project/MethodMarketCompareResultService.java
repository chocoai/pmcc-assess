package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.MethodMarketCompareResultDao;
import com.copower.pmcc.assess.dal.entity.MethodMarketCompareResult;
import com.copower.pmcc.assess.dto.input.project.MethodMarketCompareResultDto;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zly on 2018/5/31.
 */
@Service
public class MethodMarketCompareResultService {
    @Autowired
    private MethodMarketCompareResultDao methodMarketCompareResultDao;

    @Autowired
    private CommonService commonService;

    public void save(List<MethodMarketCompareResultDto> methodMarketCompareResultDtos) {
        for (MethodMarketCompareResultDto methodMarketCompareResultDto : methodMarketCompareResultDtos) {
            methodMarketCompareResultDto.setCreator(commonService.thisUserAccount());
            methodMarketCompareResultDao.save(methodMarketCompareResultDto);
        }
    }

    public List<MethodMarketCompareResult> getDataByEvaluationObjectId(Integer schemeEvaluationObjectId) {
        return methodMarketCompareResultDao.getDataByEvaluationObjectId(schemeEvaluationObjectId);
    }
}
