package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.common.enums.ExamineHouseTradingSellAndLeaseDtoTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingLease;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingSell;
import com.copower.pmcc.assess.dto.input.project.survey.ExamineHouseTradingSellAndLeaseDto;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineHouseTradingSellAndLeaseVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.google.common.base.Objects;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * @Auther: zch
 * @Date: 2018/7/27 18:38
 * @Description:房屋出售和房屋出租
 */
@Service
public class ExamineHouseTradingSellAndLeaseDtoService {//

    @Autowired
    private ExamineHouseTradingSellService examineHouseTradingSellService;
    @Autowired
    private ExamineHouseTradingLeaseService examineHouseTradingLeaseService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public BaseDataDic getBaseDataDicById(Integer id) {
        if (id != null) {
            return baseDataDicService.getDataDicById(id);
        }
        return null;
    }

    @Transactional
    public void save(ExamineHouseTradingSellAndLeaseDto examineHouseTradingSellAndLeaseDto) {
        if (examineHouseTradingSellAndLeaseDto != null) {
            //在页面需要用id取出key 然后比较
            if (!StringUtils.isEmpty(examineHouseTradingSellAndLeaseDto.getTradingType())) {
                if (Objects.equal(ExamineHouseTradingSellAndLeaseDtoTypeEnum.ExamineHouseTradingLease.getKey(), examineHouseTradingSellAndLeaseDto.getTradingType())) {
                    ExamineHouseTradingLease examineHouseTradingLease = new ExamineHouseTradingLease();
                    BeanUtils.copyProperties(examineHouseTradingSellAndLeaseDto, examineHouseTradingLease);
                    examineHouseTradingLeaseService.addExamineHouseTradingLease(examineHouseTradingLease);
                }
                if (Objects.equal(ExamineHouseTradingSellAndLeaseDtoTypeEnum.ExamineHouseTradingSell.getKey(), examineHouseTradingSellAndLeaseDto.getTradingType())) {
                    ExamineHouseTradingSell examineHouseTradingSell = new ExamineHouseTradingSell();
                    BeanUtils.copyProperties(examineHouseTradingSellAndLeaseDto, examineHouseTradingSell);
                    examineHouseTradingSellService.addExamineHouseTradingSell(examineHouseTradingSell);
                }
            }
        }
    }

    public BootstrapTableVo getVoList(String type, ExamineHouseTradingSell examineHouseTradingSell, ExamineHouseTradingLease examineHouseTradingLease) {
        BootstrapTableVo vo = null;
        if (Objects.equal(type, ExamineHouseTradingSellAndLeaseDtoTypeEnum.ExamineHouseTradingLease.getKey())) {
            vo = examineHouseTradingLeaseService.getExamineHouseTradingLeaseLists(examineHouseTradingLease, type);
        }
        if (Objects.equal(type, ExamineHouseTradingSellAndLeaseDtoTypeEnum.ExamineHouseTradingSell.getKey())) {
            vo = examineHouseTradingSellService.getExamineHouseTradingSellLists(examineHouseTradingSell, type);
        }
        return vo;
    }

    @Transactional
    public boolean remove(String type, Integer id) {
        if (Objects.equal(type, ExamineHouseTradingSellAndLeaseDtoTypeEnum.ExamineHouseTradingSell.getKey())) {
            examineHouseTradingSellService.removeExamineHouseTradingSell(id);
            return true;
        }
        if (Objects.equal(type, ExamineHouseTradingSellAndLeaseDtoTypeEnum.ExamineHouseTradingLease.getKey())) {
            examineHouseTradingLeaseService.removeExamineHouseTradingLease(id);
            return true;
        }
        return false;
    }
}
