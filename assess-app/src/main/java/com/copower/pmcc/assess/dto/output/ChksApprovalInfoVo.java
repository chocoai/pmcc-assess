package com.copower.pmcc.assess.dto.output;

import com.copower.pmcc.assess.dal.entity.ChksApprovalInfo;
import com.copower.pmcc.erp.api.dto.SysUserDto;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/28
 * @time: 16:08
 */
public class ChksApprovalInfoVo extends ChksApprovalInfo {
    private String activityName;

    private String userName;

    private List<SysUserDto> sysUserDtos;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<SysUserDto> getSysUserDtos() {
        return sysUserDtos;
    }

    public void setSysUserDtos(List<SysUserDto> sysUserDtos) {
        this.sysUserDtos = sysUserDtos;
    }
}
