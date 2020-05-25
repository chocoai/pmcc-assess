package com.copower.pmcc.assess.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.NetDownloadUtils;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.input.map.PolygonMapData;
import com.copower.pmcc.assess.dto.output.project.ProjectMemberVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectMemberService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.enums.ProcessActivityEnum;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.erp.api.dto.*;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.*;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.copower.pmcc.erp.redis.client.SimpleRedisStandalone;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kings on 2018-5-29.
 */
@Service
public class PublicService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private CommonService commonService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private ErpRpcProjectService erpRpcProjectService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;

    /**
     * 获取当前公司
     *
     * @return
     */
    public SysDepartmentDto getCurrentCompany() {
        return erpRpcDepartmentService.getDepartmentAssess();
    }

    /**
     * 获取到说明的视图信息
     *
     * @param title
     * @param message
     * @return
     */
    public ModelAndView getExplainPage(String title, String message) {
        ModelAndView modelAndView = new ModelAndView("/base/explainPage");
        modelAndView.addObject("title", title);
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    /**
     * 获取用户名称by 账号
     *
     * @param account
     * @return
     */
    public String getUserNameByAccount(String account) {
        if (StringUtils.isBlank(account)) return "";
        List<String> list = FormatUtils.transformString2List(account);
        List<SysUserDto> sysUserList = erpRpcUserService.getSysUserList(list);
        if (CollectionUtils.isNotEmpty(sysUserList)) {
            return FormatUtils.transformListString(LangUtils.transform(sysUserList, p -> p.getUserName()));
        }
        return "";
    }

    public String getUserNameByAccountList(List<String> list) {
        if (CollectionUtils.isEmpty(list)) return "";
        List<SysUserDto> sysUserList = erpRpcUserService.getSysUserList(list);
        if (CollectionUtils.isNotEmpty(sysUserList)) {
            return FormatUtils.transformListString(LangUtils.transform(sysUserList, p -> p.getUserName()));
        }
        return "";
    }

    /**
     * 提取模板中的字段为json格式字符串
     *
     * @param template
     * @return
     */
    public String extractField(String template) {
        String regex = "\\{(.*?)\\}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(template);
        List<Map<String, String>> maps = Lists.newArrayList();
        while (m.find()) {
            Map<String, String> map = Maps.newHashMap();
            String result = m.group();
            map.put("key", result.replaceAll("^\\{|\\}$", ""));
            map.put("value", "");
            maps.add(map);
        }
        return JSON.toJSONString(maps);
    }

    /**
     * 获取返回修改的审批模型数据
     *
     * @param approvalModelDto
     * @return
     */
    public ApprovalModelDto getEditApprovalModel(ApprovalModelDto approvalModelDto) {
        approvalModelDto.setOpinions("返回修改");
        approvalModelDto.setActivityKey(ProcessActivityEnum.EDIT.getValue());
        approvalModelDto.setConclusion(TaskHandleStateEnum.AGREE.getValue());
        approvalModelDto.setCurrentStep(-1);
        return approvalModelDto;
    }

    /**
     * 获取数据同步sql
     *
     * @param synchronousDataDto
     * @return
     */
    public String getSynchronousSql(SynchronousDataDto synchronousDataDto) {
        String format = null;
        try {
            String sqlTemplate = "select column_name from information_schema.columns where table_schema='%s' and table_name='%s'";

            //获取源数据表的字段
            List<Map<String, Object>> mapList = jdbcTemplate.queryForList(String.format(sqlTemplate, synchronousDataDto.getSourceDataBase(), synchronousDataDto.getSourceTable()));
            List<String> sourceFieldList = Lists.newArrayList();
            for (Map<String, Object> map : mapList) {
                sourceFieldList.add(String.valueOf(map.get("column_name")));
            }

            //获取目标数据表的字段
            mapList = jdbcTemplate.queryForList(String.format(sqlTemplate, synchronousDataDto.getTargeDataBase(), synchronousDataDto.getTargeTable()));
            List<String> targeFieldList = Lists.newArrayList();
            for (Map<String, Object> map : mapList) {
                targeFieldList.add(String.valueOf(map.get("column_name")));
            }

            Map<String, String> resultMap = Maps.newHashMap();//字段对应关系map
            if (CollectionUtils.isEmpty(targeFieldList)) return null;
            for (String targetField : targeFieldList) {
                if (CollectionUtils.isNotEmpty(synchronousDataDto.getIgnoreField()) && synchronousDataDto.getIgnoreField().contains(targetField))
                    continue;
                if (CollectionUtils.isNotEmpty(sourceFieldList) && sourceFieldList.contains(targetField)) {
                    resultMap.put(targetField, targetField);
                }
            }

            if (synchronousDataDto.getFieldMapping() != null) {//自建对应关系
                resultMap.putAll(synchronousDataDto.getFieldMapping());
            }

            if (synchronousDataDto.getFieldDefaultValue() != null) {
                for (Map.Entry<String, String> stringEntry : synchronousDataDto.getFieldDefaultValue().entrySet()) {
                    if (targeFieldList.contains(stringEntry.getKey())) {
                        resultMap.put(stringEntry.getKey(), String.format("'%s'", stringEntry.getValue()));
                    }
                }
            }
            StringBuilder sourceBuilder = new StringBuilder();
            StringBuilder targetBuilder = new StringBuilder();
            for (Map.Entry<String, String> entry : resultMap.entrySet()) {
                targetBuilder.append(entry.getKey()).append(",");
                sourceBuilder.append(entry.getValue()).append(",");
            }

            format = String.format("INSERT INTO %s.%s(%s) SELECT %s FROM %s.%s WHERE %s",
                    synchronousDataDto.getTargeDataBase(), synchronousDataDto.getTargeTable(),
                    targetBuilder.deleteCharAt(targetBuilder.length() - 1).toString(), sourceBuilder.deleteCharAt(sourceBuilder.length() - 1).toString(),
                    synchronousDataDto.getSourceDataBase(), synchronousDataDto.getSourceTable(),
                    org.springframework.util.StringUtils.isEmpty(synchronousDataDto.getWhereSql()) ? "1=1" : synchronousDataDto.getWhereSql());
        } catch (DataAccessException e) {
            log.error(e.getMessage(), e);
            return "";
        }
        if (StringUtils.isBlank(format) || StringUtils.equalsIgnoreCase(format, "null")) return "";
        return format + ";";
    }

    /**
     * 下载定位图片
     *
     * @param lng            经度
     * @param lat            纬度
     * @param baseAttachment
     */
    public void downLoadLocationImage(String lng, String lat, SysAttachmentDto baseAttachment) {
        String surveyLocaltion = String.format("%s,%s", lng, lat);
        String localDir = baseAttachmentService.createTempDirPath(commonService.thisUserAccount());
        String imageName = baseAttachmentService.createNoRepeatFileName("jpg");
        String url = String.format("%s?location=%s&zoom=17&size=900*600&markers=mid,,A:%s&key=%s",
                BaseConstant.MPA_API_URL, surveyLocaltion, surveyLocaltion, BaseConstant.MAP_WEB_SERVICE_KEY);
        try {
            NetDownloadUtils.download(url, imageName, localDir);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        //再将图片上传到FTP
        String ftpFileName = baseAttachmentService.createNoRepeatFileName("jpg");
        String ftpDirName = baseAttachmentService.createFTPBasePath(DateUtils.formatDate(new Date(), "yyyy-MM"), DateUtils.formatNowToYMD(), commonService.thisUserAccount());
        try {
            ftpUtilsExtense.uploadFilesToFTP(ftpDirName, new FileInputStream(localDir + File.separator + imageName), ftpFileName);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        //数据库添加定位图片记录
        baseAttachment.setFtpFileName(ftpFileName);
        baseAttachment.setFileExtension("jpg");
        baseAttachment.setFilePath(ftpDirName);
        baseAttachment.setFileSize(FileUtils.getSize(new File(localDir + File.separator + imageName).length()));
        baseAttachment.setCreater(commonService.thisUserAccount());
        baseAttachmentService.addAttachment(baseAttachment);
    }

    public void html2canvasNetDownloadUtils(String canvasCode, Integer tableId, String tableName, String fieldsName) throws Exception {
        final String BASE64_PREFIX = "data:image/jpeg;base64,";
        //这的jpg必须和页面一致
        String imgFilePath = org.apache.commons.io.FileUtils.getTempDirectoryPath() + UUID.randomUUID().toString().substring(1, 7) + ".jpg";
        com.copower.pmcc.assess.common.FileUtils.base64ToImage(StringUtils.remove(canvasCode, BASE64_PREFIX), imgFilePath);
        SysAttachmentDto baseAttachment = new SysAttachmentDto();
        baseAttachment.setTableId(tableId);
        baseAttachment.setTableName(tableName);
        baseAttachment.setFieldsName(fieldsName);
        baseAttachmentService.importAjaxFileHandle(imgFilePath, baseAttachment);
    }

    /**
     * 形成折线
     *
     * @param polygonMapDataList
     * @param tableId
     * @param tableName
     * @param fieldsName
     * @throws Exception
     */
    public void newHtml2canvasNetDownloadUtils(List<PolygonMapData> polygonMapDataList, Integer tableId, String tableName, String fieldsName) throws Exception {
        if (CollectionUtils.isEmpty(polygonMapDataList)) {
            return;
        }
        PolygonMapData polygonMapData = polygonMapDataList.get(0);
        List<Double> doubleList = polygonMapData.getPath().get(0);
        String surveyLocaltion = String.format("%s,%s", doubleList.get(0).toString(), doubleList.get(1).toString());
        String localDir = baseAttachmentService.createTempDirPath(commonService.thisUserAccount());
        String imageName = baseAttachmentService.createNoRepeatFileName("jpg");
        Iterator<PolygonMapData> iterator = polygonMapDataList.iterator();
        //  https://restapi.amap.com/v3/staticmap?zoom=15&size=500*500&paths=10,0x0000ff,1,,:116.31604,39.96491;116.320816,39.966606;116.321785,39.966827;116.32361,39.966957&key=0d3f1144352d7e2b683e37dd3757156a
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("https://restapi.amap.com/v3/staticmap?zoom=15&size=500*500");
        stringBuffer.append("&paths=");
        while (iterator.hasNext()) {
            LinkedList<String> linkedList = new LinkedList<>();
            PolygonMapData mapData = iterator.next();
            List<List<Double>> dataPath = mapData.getPath();
            Iterator<List<Double>> listIterator = dataPath.iterator();
            while (listIterator.hasNext()) {
                List<Double> doubles = listIterator.next();
                String value = StringUtils.join(doubles, ",");
                linkedList.add(value);
            }
            String fillColor = mapData.getFillColor();
            String v = StringUtils.join(linkedList, ";");
            stringBuffer.append("10,").append("0x0000ff").append(",").append("1").append(",").append("").append(",").append(":").append(v);
        }
        stringBuffer.append("&key=").append(BaseConstant.MAP_WEB_SERVICE_KEY);
        try {
            NetDownloadUtils.download(stringBuffer.toString(), imageName, localDir);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        SysAttachmentDto baseAttachment = new SysAttachmentDto();
        baseAttachment.setTableId(tableId);
        baseAttachment.setTableName(tableName);
        baseAttachment.setFieldsName(fieldsName);
        String path = String.join(File.separator, localDir, imageName);
        baseAttachmentService.importAjaxFileHandle(path, baseAttachment);
    }


    /**
     * 字符串去重
     *
     * @param list
     * @return
     */
    public String districtString(List<String> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        HashSet<String> hashSet = Sets.newHashSet();
        list.forEach(o -> hashSet.add(o));
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : hashSet) {
            stringBuilder.append(s).append(",");
        }
        return StringUtils.strip(stringBuilder.toString(), ",");
    }

    /**
     * 最小单元融合字符串
     *
     * @param list
     * @return
     */
    public String fusinString(List<String> list, Boolean onlySame) {
        if (CollectionUtils.isEmpty(list)) return null;
        //去除重复
        list = generateCommonMethod.removeDuplicate(list);
        //xx楼盘1栋2单元1011号 //xx楼盘2栋2单元1011号
        if (list.size() == 1) return list.get(0);
        String samePart = list.get(0);//以第一个字符串作为基础
        for (String s : list) {
            samePart = getSamePart(samePart, s);
        }
        //samePart 如果后面为数字则去掉数字
        samePart = samePart.replaceAll("\\d+$", "");
        if (onlySame) return samePart;
        StringBuilder resultBuilder = new StringBuilder(samePart);
        for (String s : list) {
            resultBuilder.append(s.replace(samePart, "")).append("、");
        }
        return resultBuilder.deleteCharAt(resultBuilder.length() - 1).toString();
    }

    /**
     * 获取两字符串相同部分
     *
     * @param var1
     * @param var2
     * @return
     */
    public String getSamePart(String var1, String var2) {
        if (StringUtils.isBlank(var1) || StringUtils.isBlank(var2)) return "";
        int length = var1.length() > var2.length() ? var2.length() : var1.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (var1.charAt(i) == var2.charAt(i)) {
                result.append(var1.charAt(i));
            } else {
                break;
            }
        }
        return result.toString();
    }

    /**
     * 计算数值差异
     *
     * @param var1
     * @param var2
     * @return 返回10则有10%的差异
     */
    public int computeDifference(BigDecimal var1, BigDecimal var2) {
        if (var1 == null || var2 == null) return -1;//表示错误数据
        if (var1.intValue() <= 0 || var2.intValue() <= 0) return -1;
        if (var1.compareTo(var2) == 0) return 0;
        BigDecimal maxDecimal = var1.compareTo(var2) > 0 ? var1 : var2;
        BigDecimal minDecimal = var1.compareTo(var2) < 0 ? var1 : var2;
        return maxDecimal.divide(minDecimal, 2, BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal("1")).multiply(new BigDecimal("100")).intValue();
    }

    /**
     * 计算两个日期相距年份，只计算年月日
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public BigDecimal diffDateYear(Date endDate, Date startDate) {
        if (startDate == null || endDate == null)
            throw new IllegalArgumentException();

        Integer year1 = DateUtils.getYear(startDate);
        Integer year2 = DateUtils.getYear(endDate);
        //相差年份的天数(如2010-2018，包括2018，这九年的总共天数)
        Integer timeDistance = 0;
        for (int i = year1; i <= year2; i++) {
            //闰年
            if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                timeDistance += DateUtils.DAYS_PER_YEAR + 1;
            } else {
                timeDistance += DateUtils.DAYS_PER_YEAR;
            }
        }
        //平均年份天数（相差年份的天数/计算的年份）
        Integer ages = year2 - year1 + 1;
        BigDecimal averageDay = new BigDecimal(timeDistance).divide(new BigDecimal(ages), 2, BigDecimal.ROUND_HALF_UP);
        //开始日期与结束日期相差天数(默认从开始时间0点到结束时间24点)
        int days = DateUtils.diffDate(endDate, startDate) + 1;
        //相差年份
        BigDecimal distanceAge = new BigDecimal(days).divide(averageDay, 2, BigDecimal.ROUND_HALF_UP);
        return distanceAge;
    }

    /**
     * 获取value值 form kevaluedto的json字符串数组中
     *
     * @param kevValueJson
     * @param key
     * @return
     */
    public String getValueFromJSON(String kevValueJson, String key) {
        if (StringUtils.isBlank(kevValueJson)) return null;
        try {
            List<KeyValueDto> valueDtoList = JSONObject.parseArray(kevValueJson, KeyValueDto.class);
            if (CollectionUtils.isEmpty(valueDtoList)) return null;
            if (CollectionUtils.isNotEmpty(valueDtoList)) {
                for (KeyValueDto keyValueDto : valueDtoList) {
                    if (key.equals(keyValueDto.getKey())) {
                        return keyValueDto.getValue();
                    }
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public void writeToErpProject(ProjectInfo projectInfo) {
        ProjectMemberVo projectMember = projectMemberService.getProjectMember(projectInfo.getId());
        SysProjectDto sysProjectDto = new SysProjectDto();
        sysProjectDto.setId(0);
        sysProjectDto.setProjectId(projectInfo.getId());
        sysProjectDto.setProjectName(projectInfo.getProjectName());
        sysProjectDto.setProjectManager(projectMember.getUserAccountManager());
        sysProjectDto.setProjectMember(projectMember.getUserAccountMember());
        sysProjectDto.setProjectJson(JSON.toJSONString(projectInfo));
        sysProjectDto.setProjectCompanyId(getCurrentCompany().getCompanyId());
        sysProjectDto.setProjectDepartmentId(projectInfo.getDepartmentId());
        sysProjectDto.setAppKey(applicationConstant.getAppKey());
        sysProjectDto.setStatus(ProjectStatusEnum.NORMAL.getKey());
        sysProjectDto.setProjectDetailsUrl(String.format("/projectCenter/projectInfo?projectId=%s", projectInfo.getId()));
        projectInfo.setPublicProjectId(erpRpcProjectService.saveProject(sysProjectDto));
        projectInfoService.updateProjectInfo(projectInfo);
    }

    public String tagfilter(String str) {
        final String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签
        Pattern pattern = Pattern.compile(regxpForHtml);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result1 = matcher.find();
        while (result1) {
            matcher.appendReplacement(sb, "");
            result1 = matcher.find();
        }
        matcher.appendTail(sb);
        return sb.toString().trim();
    }

    //去掉标签
    public String delHtmlTags(String htmlStr) {
        //定义script的正则表达式，去除js可以防止注入
        String scriptRegex = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        //定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
        String styleRegex = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        //定义HTML标签的正则表达式，去除标签，只提取文字内容
        String htmlRegex = "<[^>]+>";
        //定义空格,回车,换行符,制表符
        String spaceRegex = "\\s*|\t|\r|\n";

        // 过滤script标签
        htmlStr = htmlStr.replaceAll(scriptRegex, "");
        // 过滤style标签
        htmlStr = htmlStr.replaceAll(styleRegex, "");
        // 过滤html标签
        htmlStr = htmlStr.replaceAll(htmlRegex, "");
        // 过滤空格等
        htmlStr = htmlStr.replaceAll(spaceRegex, "");

        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        return htmlStr.trim(); // 返回文本字符串
    }

    /**
     * 记录流程监听器名称
     *
     * @param processInsId
     * @param executorName
     */
    public void setRedisProcessExecutorName(String processInsId, String executorName) {
        if (StringUtils.isBlank(processInsId) || StringUtils.isBlank(executorName)) return;
        SimpleRedisStandalone simpleRedisStandalone = SpringContextUtils.getBean(SimpleRedisStandalone.class);
        String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_PROCESS_INSTANCE_ID, processInsId);
        simpleRedisStandalone.setex(costsKeyPrefix, 60, FormatUtils.toLowerCaseFirstChar(executorName));
    }

    /**
     * 获取流程监听器名称
     *
     * @param processInsId
     */
    public String getRedisProcessExecutorName(String processInsId) {
        if (StringUtils.isBlank(processInsId)) return null;
        SimpleRedisStandalone simpleRedisStandalone = SpringContextUtils.getBean(SimpleRedisStandalone.class);
        String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_PROCESS_INSTANCE_ID, processInsId);
        return simpleRedisStandalone.get(costsKeyPrefix);
    }

    /**
     * 更新监听器
     *
     * @param processInsId
     * @param executorName
     * @throws BpmException
     */
    public void updateProcessEventExecutor(String processInsId, String executorName) throws BpmException {
        setRedisProcessExecutorName(processInsId, executorName);
        bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, executorName);
    }

    /**
     * 判断两个list string是否完全一致
     *
     * @param var1
     * @param var2
     * @return
     */
    public Boolean listIsConsistent(List<String> var1, List<String> var2) {
        if (CollectionUtils.isEmpty(var1) || CollectionUtils.isEmpty(var2)) return false;
        if (var1.size() != var2.size()) return false;
        Iterator<String> iteratorVar1 = var1.iterator();
        while (iteratorVar1.hasNext()) {
            Iterator<String> iteratorVar2 = var2.iterator();
            while (iteratorVar2.hasNext()) {
                if (iteratorVar1.next().equals(iteratorVar2.next())) {
                    iteratorVar1.remove();
                    iteratorVar2.remove();
                }
            }
        }
        if (var1.size() > 0 || var2.size() > 0) return false;
        return true;
    }

    /**
     * 比较Integer
     *
     * @param var1
     * @param var2
     * @return
     */
    public Boolean equalsInteger(Integer var1, Integer var2) {
        if (var1 == null && var2 == null) return true;
        if (var1 == null && var2 != null) return false;
        if (var1 != null && var2 == null) return false;
        return var1.intValue() == var2.intValue();
    }

    /**
     * 比较String
     *
     * @param var1
     * @param var2
     * @return
     */
    public Boolean equalsString(String var1, String var2) {
        if (var1 == null && var2 == null) return true;
        if (var1 == null && var2 != null) return false;
        if (var1 != null && var2 == null) return false;
        return var1.equals(var2);
    }
}
