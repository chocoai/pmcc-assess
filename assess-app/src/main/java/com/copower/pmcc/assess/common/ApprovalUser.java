package com.copower.pmcc.assess.common;

import com.copower.pmcc.bpm.core.process.support.ApprovalUsersService;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/24
 * @time: 14:27
 */
@Component("approvalUsersService")
public class ApprovalUser extends ApprovalUsersService {

    @Override
    public String project_manager(String currUserAccount, Integer projectId) {
        return null;
    }
}
