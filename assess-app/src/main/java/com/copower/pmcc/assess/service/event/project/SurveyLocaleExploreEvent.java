package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.dao.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.FormConfigureDao;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.dao.SurveyLocaleExploreDetailDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.SurveyLocaleExploreDetailDto;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.SurveyLocaleExploreDetailService;
import com.copower.pmcc.assess.service.project.SurveyLocaleExploreService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2018-5-21.
 */
public class SurveyLocaleExploreEvent extends BaseProcessEvent {
    @Autowired
    private ServiceComponent serviceComponent;
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
                for (SurveyLocaleExploreDetail surveyLocaleExploreDetail : detailList) {
                    if (StringUtils.isNotBlank(surveyLocaleExploreDetail.getBelongWarrant())) {
                        //1.将该数据复制 设置对应的detailId 清空所属权证 动态表单数据复制

                        //2.附件信息复制（考虑是否复制对应的文件，方便统一管理应该复制）

                        //3.如果数据已存在，应该先删除原有，再新添加

                        String belongWarrant = surveyLocaleExploreDetail.getBelongWarrant();
                        List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(belongWarrant));
                        List<ProjectPlanDetails> projectPlanDetails = projectPlanDetailsDao.getProjectPlanDetailsByDeclareId(integers, 3);
                        if (CollectionUtils.isNotEmpty(projectPlanDetails)) {
                            Map<String, Object> objectMap = formConfigureDao.getObjectSingle(surveyLocaleExploreDetail.getDynamicTableName(), surveyLocaleExploreDetail.getDynamicTableId());
                            objectMap.remove("id");

                            //找出附件信息
                            BaseAttachment queryParam = new BaseAttachment();
                            queryParam.setTableId(surveyLocaleExploreDetail.getId());
                            queryParam.setTableName("tb_survey_locale_explore_detail");
                            List<BaseAttachment> attachmentList = baseAttachmentDao.getAttachmentList(queryParam);

                            for (ProjectPlanDetails projectPlanDetail : projectPlanDetails) {
                                List<SurveyLocaleExploreDetail> exploreDetails = surveyLocaleExploreDetailService.getDetailList(projectPlanDetail.getId());
                                if (CollectionUtils.isNotEmpty(exploreDetails)) {
                                    //先删除原有数据
                                    exploreDetails.forEach(p -> {
                                        try {
                                            surveyLocaleExploreDetailService.delete(p.getId());
                                        } catch (BusinessException e) {
                                            e.printStackTrace();
                                        }
                                    });
                                }
                                //先将动态表单数据复制
                                Integer id = formConfigureDao.addObject(surveyLocaleExploreDetail.getDynamicTableName(), objectMap);
                                surveyLocaleExploreDetail.setDynamicTableId(id);
                                surveyLocaleExploreDetail.setPlanDetailsId(projectPlanDetail.getId());
                                surveyLocaleExploreDetail.setBelongWarrant(null);
                                surveyLocaleExploreDetailDao.save(surveyLocaleExploreDetail);

                                if (CollectionUtils.isNotEmpty(attachmentList)) {
                                    for (BaseAttachment baseAttachment : attachmentList) {
                                        baseAttachment.setTableId(surveyLocaleExploreDetail.getId());
                                        //拷贝真实文件
                                        String filePath =  baseAttachmentService.createFTPBasePath(FormatUtils.underlineToCamel("tb_survey_locale_explore_detail", false),
                                                DateUtils.formatNowToYMD(), serviceComponent.getThisUser());
                                        ftpUtilsExtense.copyFile(baseAttachment.getFileName(), baseAttachment.getFilePath(), baseAttachment.getFileName(), filePath);
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
