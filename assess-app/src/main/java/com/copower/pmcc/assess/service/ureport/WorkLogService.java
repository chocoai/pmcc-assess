package com.copower.pmcc.assess.service.ureport;

import com.bstek.ureport.build.BeanPageDataSet;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.bpm.api.dto.SysWorkLogVo;
import com.copower.pmcc.bpm.api.provider.BpmRpcToolsService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class WorkLogService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BpmRpcToolsService bpmRpcToolsService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;

    public BeanPageDataSet getWorkLogList(String dsname, String datasetName, Map<String, Object> maps) throws Exception {
        String queryProjectName = "";
        String queryTitle = "";
        String queryStartTime = "";
        String queryEndTime = "";
        String queryUserAccountName = "";
        Integer pageIndex = objectToInteger(maps.get("_pageIndex"));
        Integer fixRows = objectToInteger(maps.get("_fixRows"));
        if (maps.get("queryUserAccountName") != null) {
            queryUserAccountName = (String) maps.get("queryUserAccountName");
        }
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
        if (StringUtil.isNotEmpty(queryStartTime))
            startTimeParse = DateUtils.convertDate(queryStartTime);
        if (StringUtil.isNotEmpty(queryEndTime))
            endTimeParse = DateUtils.convertDate(queryEndTime);
        Integer departmentId = commonService.thisUser().getDepartmentId();
        Page<PageInfo> page = PageHelper.startPage(pageIndex, fixRows);
        BootstrapTableVo workLogList = bpmRpcToolsService.getWorkLogListByDepartment(departmentId, queryUserAccountName, queryProjectName, queryTitle, startTimeParse, endTimeParse, pageIndex, fixRows);
        List<SysWorkLogVo> list = workLogList.getRows();
        for (SysWorkLogVo item : list) {
            item.setContent(publicService.delHtmlTags(item.getContent()));
        }
        Long pageTotal = workLogList.getTotal() % fixRows > 0 ? workLogList.getTotal() / fixRows + 1 : workLogList.getTotal() / fixRows;
        return new BeanPageDataSet(list, pageTotal.intValue());
    }

    private String objectToString(Object obj) {
        if (obj == null) return "";
        return StringUtils.defaultString(String.valueOf(obj));
    }

    private Integer objectToInteger(Object obj) {
        String string = objectToString(obj);
        if (StringUtils.isNotBlank(string) && StringUtils.isNumeric(string))
            return Integer.valueOf(string);
        return 0;
    }
}
