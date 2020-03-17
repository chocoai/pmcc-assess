package com.copower.pmcc.assess.service.ureport;

import com.bstek.ureport.build.BeanPageDataSet;
import com.copower.pmcc.assess.dto.output.SysWorkLogNewVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.bpm.api.dto.SysWorkLogVo;
import com.copower.pmcc.bpm.api.provider.BpmRpcToolsService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkLogService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BpmRpcToolsService bpmRpcToolsService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public BootstrapTableVo<SysWorkLogNewVo> getBootstrapTableVo(String queryUserAccountName, String queryProjectName, String queryTitle, Date queryStartTime, Date queryEndTime) {
        Integer departmentId = commonService.thisUser().getDepartmentId();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        BootstrapTableVo workLogList = bpmRpcToolsService.getWorkLogListByDepartment(departmentId, queryUserAccountName, queryProjectName, queryTitle, queryStartTime, queryEndTime, requestBaseParam.getOffset(), requestBaseParam.getLimit());
        Iterator iterator = workLogList.getRows().iterator();
        List<SysWorkLogNewVo> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            SysWorkLogVo sysWorkLogVo = (SysWorkLogVo) next;
            list.add(getSysWorkLogNewVo(sysWorkLogVo));
        }
        workLogList.setRows(list);
        return workLogList;
    }

    public BootstrapTableVo<SysWorkLogNewVo> getWorkLogByProjectId(Integer publicProjectId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        BootstrapTableVo workLogList = bpmRpcToolsService.getWorkLogByProjectId(publicProjectId,  requestBaseParam.getOffset(), requestBaseParam.getLimit(),requestBaseParam.getSearch());
        Iterator iterator = workLogList.getRows().iterator();
        List<SysWorkLogNewVo> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            SysWorkLogVo sysWorkLogVo = (SysWorkLogVo) next;
            list.add(getSysWorkLogNewVo(sysWorkLogVo));
        }
        workLogList.setRows(list);
        return workLogList;
    }

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
        BootstrapTableVo workLogList = bpmRpcToolsService.getWorkLogListByDepartment(departmentId, queryUserAccountName, queryProjectName, queryTitle, startTimeParse, endTimeParse, pageIndex, fixRows);
        Iterator iterator = workLogList.getRows().iterator();
        List<SysWorkLogNewVo> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            SysWorkLogVo sysWorkLogVo = (SysWorkLogVo) next;
            list.add(getSysWorkLogNewVo(sysWorkLogVo));
        }
        Long pageTotal = workLogList.getTotal() % fixRows > 0 ? workLogList.getTotal() / fixRows + 1 : workLogList.getTotal() / fixRows;
        return new BeanPageDataSet(list, pageTotal.intValue());
    }

    /**
     * 在包装一次
     *
     * @param sysWorkLogVo
     * @return
     */
    private SysWorkLogNewVo getSysWorkLogNewVo(SysWorkLogVo sysWorkLogVo) {
        SysWorkLogNewVo newVo = new SysWorkLogNewVo();
        BeanUtils.copyProperties(sysWorkLogVo, newVo);
        newVo.setContent(publicService.delHtmlTags(newVo.getContent()));
        List<String> htmlView = new ArrayList<>();
        SysAttachmentDto query = new SysAttachmentDto();
        query.setProjectId(newVo.getAppProjectId());
        query.setFieldsName("log");
        query.setTableName("tb_box_approval_log");
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)) {
            Iterator<SysAttachmentDto> iterator = sysAttachmentDtoList.iterator();
            while (iterator.hasNext()) {
                SysAttachmentDto sysAttachmentDto = iterator.next();
                if (sysAttachmentDto == null) {
                    continue;
                }
                String viewHtml = baseAttachmentService.getViewHtml(sysAttachmentDto);
                if (StringUtils.isBlank(viewHtml)) {
                    continue;
                }
                htmlView.add(viewHtml);
            }
        }
        if (CollectionUtils.isNotEmpty(htmlView)) {
            StringBuilder js = new StringBuilder();
            js.append("<script>");
            js.append("setTimeout(function(){");
            js.append("document.writeln(").append(StringUtils.join(htmlView, " | ")).append(") ;");
            js.append("},100);");
            js.append("</script>");
//            newVo.setFileHtml(js.toString());
            newVo.setFileHtml(StringUtils.join(htmlView, " | "));
        }
        newVo.setCreatorName(publicService.getUserNameByAccount(sysWorkLogVo.getCreator()));
        newVo.setCreatedName(DateUtils.format(sysWorkLogVo.getCreated(), DateUtils.DATE_CHINESE_PATTERN));
        return newVo;
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
