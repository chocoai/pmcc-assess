package com.copower.pmcc.assess.dto.output.project;

import com.copower.pmcc.assess.dal.entity.ProjectSuspend;
import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/2
 * @time: 17:19
 */
public class ProjectSuspendVo extends ProjectSuspend {

    private String suspendUserName;

    private List<KeyValueDto> files;

    public String getSuspendUserName() {
        return suspendUserName;
    }

    public void setSuspendUserName(String suspendUserName) {
        this.suspendUserName = suspendUserName;
    }

    public List<KeyValueDto> getFiles() {
        return files;
    }

    public void setFiles(List<KeyValueDto> files) {
        this.files = files;
    }
}
