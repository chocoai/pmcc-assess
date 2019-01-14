package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectNumberRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataNumberRule;
import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecord;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataNumberRuleService;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    /**
     * 获取报告文号
     *
     * @param projectId  项目id
     * @param areaId     区域id
     * @param reportType 报告类型
     * @return
     */
    public String getReportNumber(Integer projectId, Integer areaId, Integer reportType) throws BusinessException {
        //1.预评报告单独走号，其它报告号连贯
        //2.生成报告号之后将其存储
        if (projectId == null || areaId == null || reportType == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(reportType);
        int year = DateUtils.getYear(new Date());
        int number = 1;
        DataNumberRule numberRule = dataNumberRuleService.getDataNumberRule(reportType);
        if (numberRule == null)
            throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
        String reportNumber = numberRule.getNumberRule().replaceAll("\\{prefix\\}", numberRule.getPrefix())
                .replaceAll("\\{year\\}", String.valueOf(year));
        if (StringUtils.equals(AssessDataDicKeyConstant.REPORT_TYPE_PREAUDIT, baseDataDic.getFieldName())) {
            //找到当前年份中编号报告为预评的最大号
            PageHelper.startPage(0, 1);//取一条数据
            List<ProjectNumberRecord> preauditNumberList = projectNumberRecordDao.getPreauditNumberList(year, reportType);
            if (CollectionUtils.isEmpty(preauditNumberList)) {
                reportNumber = reportNumber.replaceAll("\\{number\\}", StringUtils.leftPad(String.valueOf(number), numberRule.getFigures()));
            } else {
                number = preauditNumberList.get(0).getNumber() + 1;
                reportNumber = reportNumber.replaceAll("\\{number\\}", StringUtils.leftPad(String.valueOf(number), numberRule.getFigures()));
            }
        } else {
            //根据配置判断是否存在同号行为，如技术报告使用结果报告号
            //如果为同号配置，则根据项目区域及报告类型取得对应的报告编号
            if (numberRule.getSameReportType() != null) {

            } else {
                //直接取最大号
            }
        }
        ProjectNumberRecord projectNumberRecord = new ProjectNumberRecord();
        projectNumberRecord.setProjectId(projectId);
        projectNumberRecord.setAreaId(areaId);
        projectNumberRecord.setReportType(reportType);
        projectNumberRecord.setYear(year);
        projectNumberRecord.setNumber(number);
        projectNumberRecord.setNumberValue(reportNumber);
        projectNumberRecord.setCreator(commonService.thisUserAccount());
        projectNumberRecordDao.addProjectNumberRecord(projectNumberRecord);
        return "四川协和预评（2019）0001号";
    }

}
