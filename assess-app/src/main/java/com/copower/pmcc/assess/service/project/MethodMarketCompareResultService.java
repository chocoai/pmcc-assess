package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.MethodMarketCompareIndexDao;
import com.copower.pmcc.assess.dal.dao.MethodMarketCompareResultDao;
import com.copower.pmcc.assess.dal.entity.MethodMarketCompareIndex;
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
    @Autowired
    private MethodMarketCompareIndexDao methodMarketCompareIndexDao;

    public void save(List<MethodMarketCompareResultDto> methodMarketCompareResultDtos) {
        for (MethodMarketCompareResultDto methodMarketCompareResultDto : methodMarketCompareResultDtos) {
            MethodMarketCompareIndex methodMarketCompareIndex = new MethodMarketCompareIndex();
            methodMarketCompareIndex.setName(methodMarketCompareResultDto.getName());
            methodMarketCompareIndex.setEvaluationObjectId(methodMarketCompareResultDto.getEvaluationObjectId());
            List<MethodMarketCompareIndex> marketCompareIndexList = methodMarketCompareIndexDao.getDataByObject(methodMarketCompareIndex);
            Integer compareIndexId = marketCompareIndexList.get(0).getId();
            methodMarketCompareResultDto.setCreator(commonService.thisUserAccount());
            methodMarketCompareResultDto.setCompareIndexId(compareIndexId);
            methodMarketCompareResultDao.save(methodMarketCompareResultDto);
        }
    }

    public List<MethodMarketCompareResult> getDataByEvaluationObjectId(Integer schemeEvaluationObjectId) {
        return methodMarketCompareResultDao.getDataByEvaluationObjectId(schemeEvaluationObjectId);
    }

    public void updata(List<MethodMarketCompareResultDto> methodMarketCompareResultDtos) {
        for (MethodMarketCompareResultDto methodMarketCompareResultDto : methodMarketCompareResultDtos) {
            methodMarketCompareResultDao.update(methodMarketCompareResultDto);
        }
    }
}
