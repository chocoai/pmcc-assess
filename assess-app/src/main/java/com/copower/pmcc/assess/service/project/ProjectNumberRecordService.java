package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.enums.AssessProjectTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomProjectNumberRecord;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomProjectNumberRecordMapper;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectNumberRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataNumberRuleService;
import com.copower.pmcc.erp.api.dto.SysSymbolListDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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
    private ProjectInfoService projectInfoService;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private CustomProjectNumberRecordMapper customProjectNumberRecordMapper;

    public List<String> getReportNumberList(Integer projectId, AssessProjectTypeEnum assessProjectTypeEnum, Integer reportType) {
        List<ProjectNumberRecord> numberList = getReportNumberRecordList(projectId, assessProjectTypeEnum, reportType);
        List<String> list = LangUtils.transform(numberList, o -> o.getNumberValue());
        return list;
    }

    public List<ProjectNumberRecord> getReportNumberRecordList(Integer projectId, AssessProjectTypeEnum assessProjectTypeEnum, Integer reportType) {
        ProjectNumberRecord where = new ProjectNumberRecord();
        where.setBisDelete(false);
        where.setProjectId(projectId);
        if (assessProjectTypeEnum != null)
            where.setAssessProjectType(assessProjectTypeEnum.getKey());
        if (reportType != null)
            where.setReportType(reportType);
        List<ProjectNumberRecord> numberList = projectNumberRecordDao.getProjectNumberRecordList(where);
        return numberList;
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
    @Transactional(rollbackFor = Exception.class)
    public SysSymbolListDto getReportNumber(ProjectInfo projectInfo, Integer areaId, AssessProjectTypeEnum assessProjectType, Integer reportType, Boolean isMustTakeNew) throws BusinessException {
        return getReportNumber(projectInfo,areaId,0,assessProjectType,reportType,isMustTakeNew);
    }

    public SysSymbolListDto getReportNumber(ProjectInfo projectInfo, Integer areaId,Integer groupId, AssessProjectTypeEnum assessProjectType, Integer reportType, Boolean isMustTakeNew) throws BusinessException {
        //1.根据文号规则走号
        //2.生成报告号之后将其存储
        if (projectInfo == null || areaId == null || reportType == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        SysSymbolListDto sysSymbolListDto = new SysSymbolListDto();
        int year = DateUtils.getYear(DateUtils.today());
        if (isMustTakeNew == Boolean.FALSE) {
            ProjectNumberRecord numberRecord = projectNumberRecordDao.getProjectNumberRecord(projectInfo.getId(), areaId,groupId, year, assessProjectType.getKey(), reportType);
            if (numberRecord != null) {
                sysSymbolListDto.setSymbol(numberRecord.getNumberValue());
                return sysSymbolListDto;
            }
        }
        DataNumberRule numberRule = dataNumberRuleService.getDataNumberRule(assessProjectType, reportType);
        if (numberRule == null)
            throw new BusinessException(HttpReturnEnum.NOTFIND.getName());

        List<SysSymbolListDto> sysSymbolList = erpRpcToolsService.getSysSymbolList(applicationConstant.getAppKey(), numberRule.getErpRuleId(), year);
        if (CollectionUtils.isNotEmpty(sysSymbolList))
            sysSymbolListDto = sysSymbolList.get(0);
        else
            sysSymbolListDto = getSymbolDto(numberRule.getErpRuleId(), year);
        if (sysSymbolListDto != null) {
            ProjectNumberRecord projectNumberRecord = new ProjectNumberRecord();
            projectNumberRecord.setProjectId(projectInfo.getId());
            projectNumberRecord.setAreaId(areaId);
            projectNumberRecord.setGroupId(groupId);
            projectNumberRecord.setAssessProjectType(assessProjectType.getKey());
            projectNumberRecord.setReportType(reportType);
            projectNumberRecord.setYear(year);
            projectNumberRecord.setNumberValue(sysSymbolListDto.getSymbol());
            projectNumberRecord.setNumber(sysSymbolListDto.getSymbolNumber());
            projectNumberRecord.setCreator(commonService.thisUserAccount());
            projectNumberRecordDao.addProjectNumberRecord(projectNumberRecord);
        }
        return sysSymbolListDto;
    }

    //erp获取文号
    public synchronized SysSymbolListDto getSymbolDto(Integer ruleId, Integer years) throws BusinessException {
        SysSymbolListDto sysSymbol = erpRpcToolsService.getSysSymbol(applicationConstant.getAppKey(), ruleId, years);
        if (sysSymbol != null)
            erpRpcToolsService.updateSymbolExamine(applicationConstant.getAppKey(), sysSymbol.getSymbol());
        return sysSymbol;
    }


    public BootstrapTableVo getProjectNumberRecordList(String projectName, Integer reportType, String numberValue) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CustomProjectNumberRecord> customNumberRecordList = customProjectNumberRecordMapper.getAllProjectNumberRecord(projectName, reportType, numberValue);
        List<CustomProjectNumberRecord> vos = LangUtils.transform(customNumberRecordList, o -> getCustomProjectNumberRecord(o));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CustomProjectNumberRecord>() : vos);
        return vo;
    }

    public CustomProjectNumberRecord getCustomProjectNumberRecord(CustomProjectNumberRecord data) {
        if (data == null) {
            return null;
        }
        CustomProjectNumberRecord vo = new CustomProjectNumberRecord();
        BeanUtils.copyProperties(data, vo);
        if (data.getReportType() != null) {
            vo.setReportTypeName(baseDataDicService.getNameById(data.getReportType()));
        }
        return vo;
    }

    /**
     * 文号拿取
     *
     * @param areaId
     * @param projectId
     * @param reportType
     * @return
     * @throws BusinessException
     */
    public SysSymbolListDto getSysSymbolListDto(Integer areaId, Integer projectId, Integer reportType, String assessProjectType) throws BusinessException {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        AssessProjectTypeEnum assessProjectTypeEnum = null;
        for (AssessProjectTypeEnum typeEnum : AssessProjectTypeEnum.values()) {
            if (!com.google.common.base.Objects.equal(assessProjectType, typeEnum.getKey())) {
                continue;
            }


            assessProjectTypeEnum = typeEnum;
        }
        return getReportNumber(projectInfo, areaId, assessProjectTypeEnum, reportType, false);
    }


    public List<String> getReportNumberByArea(Integer projectId, Integer areaId, Integer reportType) {
        ProjectNumberRecord where = new ProjectNumberRecord();
        where.setBisDelete(false);
        where.setProjectId(projectId);
        where.setAreaId(areaId);
        where.setReportType(reportType);
        List<ProjectNumberRecord> numberList = projectNumberRecordDao.getProjectNumberRecordList(where);
        List<String> list = LangUtils.transform(numberList, o -> o.getNumberValue());
        return list;
    }

    public List<String> getProjectNumberRecordList(ProjectNumberRecord projectNumberRecord) {
        List<ProjectNumberRecord> numberList = projectNumberRecordDao.getProjectNumberRecordList(projectNumberRecord);
        List<String> list = LangUtils.transform(numberList, o -> o.getNumberValue());
        return list;
    }

}
