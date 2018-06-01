package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.MethodMarketCompareIndexDao;
import com.copower.pmcc.assess.dal.entity.MethodMarketCompareIndex;
import com.copower.pmcc.assess.dto.input.project.MethodMarketCompareIndexDto;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zly on 2018/5/31.
 */
@Service
public class MethodMarketCompareIndexService {

    @Autowired
    private MethodMarketCompareIndexDao methodMarketCompareIndexDao;
    @Autowired
    private CommonService commonService;
    public void save(List<MethodMarketCompareIndexDto> methodMarketCompareIndexDtos) {
        for(MethodMarketCompareIndexDto methodMarketCompareIndexDto:methodMarketCompareIndexDtos){
            methodMarketCompareIndexDto.setCreator(commonService.thisUserAccount());
            methodMarketCompareIndexDao.save(methodMarketCompareIndexDto);
        }
    }

    public List<MethodMarketCompareIndex> getDataByEvaluationObjectId(Integer schemeEvaluationObjectId) {
        return methodMarketCompareIndexDao.getDataByEvaluationObjectId(schemeEvaluationObjectId);
    }
}
