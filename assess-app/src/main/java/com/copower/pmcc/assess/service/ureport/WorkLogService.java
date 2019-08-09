package com.copower.pmcc.assess.service.ureport;

import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.bpm.api.dto.SysWorkLogVo;
import com.copower.pmcc.bpm.api.provider.BpmRpcToolsService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WorkLogService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BpmRpcToolsService bpmRpcToolsService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;

    public List<SysWorkLogVo> getWorkLogList(String dsname, String datasetName, Map<String, Object> maps) throws Exception {
        String queryProjectName = "";
        String queryTitle = "";
        String queryStartTime = "";
        String queryEndTime = "";
        if (maps.get("queryProjectName") != null) {
            queryProjectName = (String) maps.get("queryProjectName");
        }
        if (maps.get("queryTitle") != null) {
            queryTitle = (String) maps.get("queryTitle");
        }
        if (maps.get("queryStartTime") != null) {
            queryStartTime = (String) maps.get("queryStartTime");
        }
        if (maps.get("queryEndTime") != null) {
            queryEndTime = (String) maps.get("queryEndTime");
        }
        Date startTimeParse = null;
        Date endTimeParse = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtil.isNotEmpty(queryStartTime))
            startTimeParse = sdf.parse(queryStartTime);
        if (StringUtil.isNotEmpty(queryEndTime))
            endTimeParse = sdf.parse(queryEndTime);
        Integer departmentId = commonService.thisUser().getDepartmentId();
        BootstrapTableVo workLogList = bpmRpcToolsService.getWorkLogListByDepartment(departmentId, applicationConstant.getAppKey(), queryProjectName, queryTitle, startTimeParse, endTimeParse, 0, 0);
        List<SysWorkLogVo> list = workLogList.getRows();
        for (SysWorkLogVo item : list) {
            item.setContent(publicService.delHtmlTags(item.getContent()));
        }
        return list;
    }

}
