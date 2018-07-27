package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineHouseTradingLeaseDao;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineHouseTradingSellDao;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingLease;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingSell;
import com.copower.pmcc.assess.dto.input.project.survey.ExamineHouseTradingSellAndLeaseDto;
import com.google.common.base.Objects;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/27 18:38
 * @Description:
 */
@Service
public class ExamineHouseTradingSellAndLeaseDtoService {//

    @Autowired
    private ExamineHouseTradingLeaseDao examineHouseTradingLeaseDao;
    @Autowired
    private ExamineHouseTradingSellDao examineHouseTradingSellDao;

    public void save(ExamineHouseTradingSellAndLeaseDto examineHouseTradingSellAndLeaseDto) {
        if (examineHouseTradingSellAndLeaseDto != null) {
            //周一 需要用id取出key 然后比较
            if (!StringUtils.isEmpty(examineHouseTradingSellAndLeaseDto.getTradingType())) {
                if (Objects.equal("586", examineHouseTradingSellAndLeaseDto.getTradingType())) {
                    ExamineHouseTradingLease examineHouseTradingLease = new ExamineHouseTradingLease();
                    BeanUtils.copyProperties(examineHouseTradingSellAndLeaseDto, examineHouseTradingLease);
                }
                if (Objects.equal("587", examineHouseTradingSellAndLeaseDto.getTradingType())) {
                    ExamineHouseTradingSell examineHouseTradingSell = new ExamineHouseTradingSell();
                    BeanUtils.copyProperties(examineHouseTradingSellAndLeaseDto, examineHouseTradingSell);
                }
            }
        }
    }
    public List<ExamineHouseTradingLease> examineHouseTradingLeaseList(ExamineHouseTradingLease examineHouseTradingLease){
        return examineHouseTradingLeaseDao.examineHouseTradingLeaseList(examineHouseTradingLease);
    }

    public List<ExamineHouseTradingSell> examineHouseTradingSells(ExamineHouseTradingSell examineHouseTradingSell) {
        return examineHouseTradingSellDao.examineHouseTradingSells(examineHouseTradingSell);
    }
}
