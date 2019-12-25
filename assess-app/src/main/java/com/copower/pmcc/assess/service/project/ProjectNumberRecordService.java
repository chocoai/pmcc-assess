package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectNumberRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataNumberRule;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecord;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataNumberRuleService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kings on 2019-1-14.
 */
@Service
public class ProjectNumberRecordService {
    @Autowired
    private ProjectNumberRecordDao projectNumberRecordDao;
    @Autowired
    private DataNumberRuleService dataNumberRuleService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private ProjectInfoService projectInfoService;

    public List<String> getReportNumberList(Integer projectId, AssessProjectTypeEnum assessProjectTypeEnum, Integer reportType) {
        ProjectNumberRecord where = new ProjectNumberRecord();
        where.setBisDelete(false);
        where.setProjectId(projectId);
        where.setAssessProjectType(assessProjectTypeEnum.getKey());
        where.setReportType(reportType);
        List<ProjectNumberRecord> numberList = projectNumberRecordDao.getProjectNumberRecordList(where);
        List<String> list = LangUtils.transform(numberList, o -> o.getNumberValue());
        return list;
    }


    /**
     * 获取有效文号记录数据
     *
     * @param projectId
     * @param areaId
     * @param reportType
     * @return
     */
    public ProjectNumberRecord getProjectNumberRecord(Integer projectId, Integer areaId, AssessProjectTypeEnum assessProjectTypeEnum, Integer reportType) {
        ProjectNumberRecord where = new ProjectNumberRecord();
        where.setProjectId(projectId);
        where.setAreaId(areaId);
        where.setAssessProjectType(assessProjectTypeEnum.getKey());
        where.setReportType(reportType);
        where.setBisDelete(false);
        List<ProjectNumberRecord> numberRecordList = projectNumberRecordDao.getProjectNumberRecordList(where);
        if (CollectionUtils.isNotEmpty(numberRecordList)) return numberRecordList.get(0);
        return null;
    }

    /**
     * 获取文号记录数据（有效与无效）
     *
     * @param projectId
     * @param areaId
     * @param reportType
     * @return
     */
    public List<ProjectNumberRecord> getAllNumberRecord(Integer projectId, Integer areaId, AssessProjectTypeEnum assessProjectTypeEnum, Integer reportType) {
        ProjectNumberRecord where = new ProjectNumberRecord();
        where.setProjectId(projectId);
        where.setAreaId(areaId);
        where.setAssessProjectType(assessProjectTypeEnum.getKey());
        where.setReportType(reportType);
        List<ProjectNumberRecord> numberRecordList = projectNumberRecordDao.getProjectNumberRecordList(where);
        if (CollectionUtils.isNotEmpty(numberRecordList)) return numberRecordList;
        return null;
    }

    public void updateProjectNumberRecord(ProjectNumberRecord projectNumberRecord) {
        projectNumberRecordDao.editProjectNumberRecord(projectNumberRecord);
    }

    public String getWordNumber2(String value) throws BusinessException {
        Pattern pattern = Pattern.compile("\\d+号$");
        if (StringUtils.isNotEmpty(value)) {
            Matcher matcher = pattern.matcher(value);
            while (matcher.find()) {
                if (StringUtils.isNotEmpty(matcher.group())) {
                    return StringUtils.remove(matcher.group(), "号");
                }
            }
        }
        return "";
    }

    /**
     * 获取报告文号
     *
     * @param areaId     区域id
     * @param reportType 报告类型
     * @return
     */
    public String getReportNumber(ProjectInfo projectInfo, Integer areaId, AssessProjectTypeEnum assessProjectType, Integer reportType, Boolean isMustTakeNew) throws BusinessException {
        //1.根据文号规则走号
        //2.生成报告号之后将其存储
        if (projectInfo == null || areaId == null || reportType == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        //判断该区域的该报告类型是否已经有文号，如果已有则直接返回
        int year = DateUtils.getYear(new Date());
        if (isMustTakeNew == Boolean.FALSE) {
            ProjectNumberRecord numberRecord = projectNumberRecordDao.getProjectNumberRecord(projectInfo.getId(), areaId, year, assessProjectType.getKey(), reportType);
            if (numberRecord != null) return numberRecord.getNumberValue();
        }
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(reportType);
        int number = 1;
        DataNumberRule numberRule = dataNumberRuleService.getDataNumberRule(assessProjectType, reportType);
        if (numberRule == null)
            throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
        if (numberRule.getStartNumber() != null)
            number = numberRule.getStartNumber();
        String reportNumber = numberRule.getNumberRule().replaceAll("\\{prefix\\}", numberRule.getPrefix())
                .replaceAll("\\{year\\}", String.valueOf(year));
        //根据配置判断是否属于分组文号，如技术报告使用结果报告号等
        //得到同分组文号的文号规则，根据项目区域找到最大编号
        //根据项目区域及报告类型取得对应的报告编号
        if (numberRule.getGroupName() != null) {
            List<DataNumberRule> numberRuleGroup = dataNumberRuleService.getDataNumberRuleByGroup(assessProjectType, numberRule.getGroupName());
            if(CollectionUtils.isNotEmpty(numberRuleGroup)){
                List<Integer> reportTypeList = generateCommonMethod.removeDuplicate(LangUtils.transform(numberRuleGroup, o -> o.getReportType()));//去重
                if(CollectionUtils.isNotEmpty(reportTypeList)){
                    //取同一项目类型同一组下最大的编号
                    List<ProjectNumberRecord> projectNumberRecordList = projectNumberRecordDao.getProjectNumberRecordList(assessProjectType.getKey(), reportTypeList);
                    if (CollectionUtils.isNotEmpty(projectNumberRecordList)) {
                        number = projectNumberRecordList.get(0).getNumber() + 1;
                    }
                }
            }
        }
        reportNumber = reportNumber.replaceAll("\\{number\\}", StringUtils.leftPad(String.valueOf(number), numberRule.getFigures(), '0'));
        ProjectNumberRecord projectNumberRecord = new ProjectNumberRecord();
        projectNumberRecord.setProjectId(projectInfo.getId());
        projectNumberRecord.setAreaId(areaId);
        projectNumberRecord.setAssessProjectType(assessProjectType.getKey());
        projectNumberRecord.setReportType(reportType);
        projectNumberRecord.setYear(year);
        projectNumberRecord.setNumber(number);
        projectNumberRecord.setNumberValue(reportNumber);
        projectNumberRecord.setCreator(commonService.thisUserAccount());
        projectNumberRecordDao.addProjectNumberRecord(projectNumberRecord);

        //更新项目信息中报告文号生成时间
        if (StringUtils.equals(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT, baseDataDic.getFieldName())) {
            projectInfo.setPreauditNumberDate(new Date());
            projectInfoService.saveProjectInfo(projectInfo);
        }
        if (StringUtils.equals(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT, baseDataDic.getFieldName())) {
            projectInfo.setResultNumberDate(new Date());
            projectInfoService.saveProjectInfo(projectInfo);
        }
        return reportNumber;
    }
}
