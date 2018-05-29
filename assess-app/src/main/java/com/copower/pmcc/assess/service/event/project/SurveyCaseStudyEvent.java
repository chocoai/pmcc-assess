package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.constant.AssessTableNameConstant;
import com.copower.pmcc.assess.dal.dao.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.FormConfigureDao;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.dao.SurveyCaseStudyDetailDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.SurveyCaseStudyDetailService;
import com.copower.pmcc.assess.service.project.SurveyCaseStudyService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2018-5-21.
 */
@Component
public class SurveyCaseStudyEvent extends ProjectTaskEvent {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SurveyCaseStudyService surveyCaseStudyService;
    @Autowired
    private SurveyCaseStudyDetailService surveyCaseStudyDetailService;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private SurveyCaseStudyDetailDao surveyCaseStudyDetailDao;
    @Autowired
    private FormConfigureDao formConfigureDao;
    @Autowired
    private BaseAttachmentDao baseAttachmentDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private ProjectPhaseService projectPhaseService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) {
        super.processFinishExecute(processExecution);

        //现场查勘 如果关联了多个权证信息 则自动更新其它权证的查勘信息
        try {
            SurveyCaseStudy surveyCaseStudy = surveyCaseStudyService.getSurveyCaseStudy(processExecution.getProcessInstanceId());
            if (surveyCaseStudy == null)
                return;
            List<SurveyCaseStudyDetail> detailList = surveyCaseStudyDetailService.getDetailList(surveyCaseStudy.getPlanDetailsId());
            if (CollectionUtils.isNotEmpty(detailList)) {
                ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.CASE_STUDY);
                if(projectPhase==null) return;
                for (SurveyCaseStudyDetail surveyCaseStudyDetail : detailList) {
                    if (StringUtils.isNotBlank(surveyCaseStudyDetail.getCorrelationCard())) {
                        String belongWarrant = surveyCaseStudyDetail.getCorrelationCard();
                        List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(belongWarrant));
                        List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsByDeclareId(integers, projectPhase.getId());//待调整
                        if (CollectionUtils.isNotEmpty(projectPlanDetails)) {
                            Map<String, Object> objectMap = formConfigureDao.getObjectSingle(surveyCaseStudyDetail.getDynamicTableName(), surveyCaseStudyDetail.getDynamicTableId());
                            objectMap.remove("id");

                            //找出附件信息
                            BaseAttachment queryParam = new BaseAttachment();
                            queryParam.setTableId(surveyCaseStudyDetail.getId());
                            queryParam.setTableName(AssessTableNameConstant.SURVEY_CASE_STUDY_DETAIL);
                            List<BaseAttachment> attachmentList = baseAttachmentDao.getAttachmentList(queryParam);

                            for (ProjectPlanDetails projectPlanDetail : projectPlanDetails) {
                                //先将动态表单数据复制
                                Integer id = formConfigureDao.addObject(surveyCaseStudyDetail.getDynamicTableName(), objectMap);
                                surveyCaseStudyDetail.setDynamicTableId(id);
                                surveyCaseStudyDetail.setPlanDetailsId(projectPlanDetail.getId());
                                surveyCaseStudyDetail.setCorrelationCard(null);
                                surveyCaseStudyDetailDao.save(surveyCaseStudyDetail);

                                if (CollectionUtils.isNotEmpty(attachmentList)) {
                                    for (BaseAttachment baseAttachment : attachmentList) {
                                        baseAttachment.setTableId(surveyCaseStudyDetail.getId());
                                        //拷贝真实文件
                                        String filePath = baseAttachmentService.createFTPBasePath(SurveyCaseStudyDetail.class.getSimpleName(),
                                                DateUtils.formatNowToYMD(), processControllerComponent.getThisUser());
                                        ftpUtilsExtense.copyFile(baseAttachment.getFtpFilesName(), baseAttachment.getFilePath(), baseAttachment.getFtpFilesName(), filePath);
                                        baseAttachment.setFilePath(filePath);
                                        baseAttachmentDao.addAttachment(baseAttachment);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
