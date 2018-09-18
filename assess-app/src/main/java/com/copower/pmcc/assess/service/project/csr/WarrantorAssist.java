package com.copower.pmcc.assess.service.project.csr;

import com.copower.pmcc.bpm.api.annotation.WorkFlowAnnotation;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/31
 * @time: 10:16
 */
@Component
@WorkFlowAnnotation(desc = "债权评估保证人情况")
public class WarrantorAssist extends financialClaimBaseAssist {
}
