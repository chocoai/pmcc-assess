package com.copower.pmcc.assess.proxy.face;

import com.copower.pmcc.assess.dal.basis.entity.ProjectPlan;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.erp.common.exception.BusinessException;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/25
 * @time: 16:44
 */
public interface ProjectPlanExecuteInterface {
    //计划后台执行内容
    void execute(ProjectPlan projectPlan,ProjectWorkStage projectWorkStage) throws BusinessException;

}
