package com.copower.pmcc.assess.service.project.taks.assist.financialClaim;

import com.copower.pmcc.assess.dal.dao.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.input.project.ProjectPlanDetailsDto;
import com.copower.pmcc.assess.dto.output.project.ProjectPlanDetailsVo;
import com.copower.pmcc.assess.proxy.face.ProjectTaskInterface;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanFinancialClaimService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanService;
import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/31
 * @time: 10:14
 */
@Component
@WorkFlowAnnotation(desc = "债权评估借款合同基本情况")
public class LoanContractAssist extends financialClaimBaseAssist {
}
