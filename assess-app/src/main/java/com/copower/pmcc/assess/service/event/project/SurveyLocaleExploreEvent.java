package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.constant.AssessTableNameConstant;
import com.copower.pmcc.assess.dal.dao.base.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.FormConfigureDao;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.dao.SurveyLocaleExploreDetailDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.SurveyLocaleExploreDetailService;
import com.copower.pmcc.assess.service.project.SurveyLocaleExploreService;
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
public class SurveyLocaleExploreEvent extends ProjectTaskEvent {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SurveyLocaleExploreService surveyLocaleExploreService;
    @Autowired
    private SurveyLocaleExploreDetailService surveyLocaleExploreDetailService;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private SurveyLocaleExploreDetailDao surveyLocaleExploreDetailDao;
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
            SurveyLocaleExplore surveyLocaleExplore = surveyLocaleExploreService.getSurveyLocaleExplore(processExecution.getProcessInstanceId());
            if (surveyLocaleExplore == null)
                return;
            List<SurveyLocaleExploreDetail> detailList = surveyLocaleExploreDetailService.getDetailList(surveyLocaleExplore.getPlanDetailsId());
            if (CollectionUtils.isNotEmpty(detailList)) {
                ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.LOCALE_EXPLORE);
                if(projectPhase==null) return;
                for (SurveyLocaleExploreDetail surveyLocaleExploreDetail : detailList) {
                    if (StringUtils.isNotBlank(surveyLocaleExploreDetail.getCorrelationCard())) {
                        String belongWarrant = surveyLocaleExploreDetail.getCorrelationCard();
                        List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(belongWarrant));
                        List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsByDeclareId(integers, projectPhase.getId());//待调整
                        if (CollectionUtils.isNotEmpty(projectPlanDetails)) {
                            Map<String, Object> objectMap = formConfigureDao.getObjectSingle(surveyLocaleExploreDetail.getDynamicTableName(), surveyLocaleExploreDetail.getDynamicTableId());
                            objectMap.remove("id");

                            //找出附件信息
                            BaseAttachment queryParam = new BaseAttachment();
                            queryParam.setTableId(surveyLocaleExploreDetail.getId());
                            queryParam.setTableName(AssessTableNameConstant.SURVEY_LOCALE_EXPLORE_DETAIL);
                            List<BaseAttachment> attachmentList = baseAttachmentDao.getAttachmentList(queryParam);

                            for (ProjectPlanDetails projectPlanDetail : projectPlanDetails) {
                                //先将动态表单数据复制
                                Integer id = formConfigureDao.addObject(surveyLocaleExploreDetail.getDynamicTableName(), objectMap);
                                surveyLocaleExploreDetail.setDynamicTableId(id);
                                surveyLocaleExploreDetail.setPlanDetailsId(projectPlanDetail.getId());
                                surveyLocaleExploreDetail.setCorrelationCard(null);
                                surveyLocaleExploreDetailDao.save(surveyLocaleExploreDetail);

                                if (CollectionUtils.isNotEmpty(attachmentList)) {
                                    for (BaseAttachment baseAttachment : attachmentList) {
                                        baseAttachment.setTableId(surveyLocaleExploreDetail.getId());
                                        //拷贝真实文件
                                        String filePath = baseAttachmentService.createFTPBasePath(SurveyLocaleExploreDetail.class.getSimpleName(),
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
