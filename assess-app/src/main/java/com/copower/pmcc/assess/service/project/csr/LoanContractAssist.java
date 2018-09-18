package com.copower.pmcc.assess.service.project.csr;

import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import org.springframework.stereotype.Component;

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
