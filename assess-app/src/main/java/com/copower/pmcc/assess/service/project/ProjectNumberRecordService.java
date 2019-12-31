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
import com.copower.pmcc.erp.api.dto.SysSymbolListDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
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
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;
    @Autowired
    private ApplicationConstant applicationConstant;

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
    public SysSymbolListDto getReportNumber(ProjectInfo projectInfo, Integer areaId, AssessProjectTypeEnum assessProjectType, Integer reportType, Boolean isMustTakeNew) throws BusinessException {
        //1.根据文号规则走号
        //2.生成报告号之后将其存储
        if (projectInfo == null || areaId == null || reportType == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        SysSymbolListDto sysSymbolListDto = new SysSymbolListDto();
        int year = DateUtils.getYear(DateUtils.today());
        if (isMustTakeNew == Boolean.FALSE) {
            ProjectNumberRecord numberRecord = projectNumberRecordDao.getProjectNumberRecord(projectInfo.getId(), areaId, year, assessProjectType.getKey(), reportType);
            if (numberRecord != null) {
                sysSymbolListDto.setSymbol(numberRecord.getNumberValue());
                return sysSymbolListDto;
            }
        }
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(reportType);
        DataNumberRule numberRule = dataNumberRuleService.getDataNumberRule(assessProjectType, reportType);
        if (numberRule == null)
            throw new BusinessException(HttpReturnEnum.NOTFIND.getName());

        List<SysSymbolListDto> sysSymbolList = erpRpcToolsService.getSysSymbolList(applicationConstant.getAppKey(), numberRule.getErpRuleId(), year);
        if (CollectionUtils.isNotEmpty(sysSymbolList))
            sysSymbolListDto = sysSymbolList.get(0);
        else
            sysSymbolListDto = erpRpcToolsService.getSysSymbol(applicationConstant.getAppKey(), numberRule.getErpRuleId(), year);
        if (sysSymbolListDto != null) {
            ProjectNumberRecord projectNumberRecord = new ProjectNumberRecord();
            projectNumberRecord.setProjectId(projectInfo.getId());
            projectNumberRecord.setAreaId(areaId);
            projectNumberRecord.setAssessProjectType(assessProjectType.getKey());
            projectNumberRecord.setReportType(reportType);
            projectNumberRecord.setYear(year);
            projectNumberRecord.setNumberValue(sysSymbolListDto.getSymbol());
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
            erpRpcToolsService.updateSymbolUsed(applicationConstant.getAppKey(), sysSymbolListDto.getSymbol());
        }
        return sysSymbolListDto;
    }
}
