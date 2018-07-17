package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.suvey.SurveyExamineTaskDao;
import com.copower.pmcc.assess.dal.basis.entity.DataExamineTask;
import com.copower.pmcc.assess.dal.basis.entity.SurveyExamineTask;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyExamineTaskDto;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyExamineTaskVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.data.DataExamineTaskService;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by zly on 2018/5/15.
 */
@Service
public class SurveyExamineTaskService {
    @Autowired
    private SurveyExamineTaskDao surveyExamineTaskDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private DataExamineTaskService dataExamineTaskService;


    /**
     * 获取调查任务
     *
     * @param id
     * @return
     */
    public SurveyExamineTask getSurveyExamineTask(Integer id) {
        return surveyExamineTaskDao.getSurveyExamineTaskById(id);
    }

    /**
     * 获取调查任务
     *
     * @param planDetailsId
     * @return
     */
    public List<SurveyExamineTask> getTaskListByPlanDetailsId(Integer planDetailsId) {
        SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
        surveyExamineTask.setPlanDetailsId(planDetailsId);
        return surveyExamineTaskDao.getSurveyExamineTaskList(surveyExamineTask);
    }

    /**
     * 获取vos
     *
     * @param taskList
     * @return
     */
    public List<SurveyExamineTaskVo> getSurveyExamineTaskVos(List<SurveyExamineTask> taskList) {
        if (CollectionUtils.isEmpty(taskList)) return null;
        return LangUtils.transform(taskList, p -> {
            SurveyExamineTaskVo surveyExamineTaskVo = new SurveyExamineTaskVo();
            BeanUtils.copyProperties(p, surveyExamineTaskVo);
            if (p.getPid() != null && p.getPid() > 0)
                surveyExamineTaskVo.set_parentId(p.getPid());
            if (StringUtils.isNotBlank(p.getUserAccount())) {
                surveyExamineTaskVo.setUserName(publicService.getUserNameByAccount(p.getUserAccount()));
            }
            return surveyExamineTaskVo;
        });
    }


    /**
     * 删除任务
     *
     * @param planDetailsId
     */
    public void deleteTaskByPlanDetailsId(Integer planDetailsId) {
        SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
        surveyExamineTask.setPlanDetailsId(planDetailsId);
        surveyExamineTaskDao.deleteSurveyExamineTask(surveyExamineTask);
    }

    /**
     * 保存选择的调查任务
     * @param surveyExamineTaskDto
     * @throws BusinessException
     */
    @Transactional
    public void saveSelectExamineTask(SurveyExamineTaskDto surveyExamineTaskDto) throws BusinessException {
        List<Integer> list = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(surveyExamineTaskDto.getDataTaskIds()));
        if (CollectionUtils.isNotEmpty(list)) {
            for (Integer id : list) {
                DataExamineTask dataExamineTask = dataExamineTaskService.getCacheDataExamineTaskById(id);
                if (dataExamineTask != null) {
                    SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
                    surveyExamineTask.setPid(surveyExamineTaskDto.getPid());
                    surveyExamineTask.setUserAccount(surveyExamineTaskDto.getUserAccount());
                    surveyExamineTask.setBisMust(dataExamineTask.getBisMust());
                    surveyExamineTask.setPlanDetailsId(surveyExamineTaskDto.getPlanDetailsId());
                    surveyExamineTask.setName(dataExamineTask.getName());
                    surveyExamineTask.setSorting(dataExamineTask.getSorting());
                    surveyExamineTask.setExamineType(surveyExamineTaskDto.getExamineType());
                    surveyExamineTask.setDeclareId(surveyExamineTaskDto.getDeclareRecordId());
                    surveyExamineTask.setDataTaskId(id);
                    surveyExamineTask.setBisFinish(false);
                    surveyExamineTask.setCreator(commonService.thisUserAccount());
                    surveyExamineTaskDao.addSurveyExamineTask(surveyExamineTask);
                }
            }
        }
    }

    /**
     * 保存调查任务
     *
     * @param surveyExamineTask
     * @return
     */
    public void saveSurveyExamineTask(SurveyExamineTask surveyExamineTask) throws BusinessException {
        if (surveyExamineTask == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (surveyExamineTask.getId() != null && surveyExamineTask.getId() > 0) {
            surveyExamineTaskDao.updateSurveyExamineTask(surveyExamineTask);
        } else {
            surveyExamineTask.setCreator(commonService.thisUserAccount());
            surveyExamineTaskDao.addSurveyExamineTask(surveyExamineTask);
        }
    }

    /**
     * 删除调查任务
     *
     * @param id
     */
    public void deleteSurveyExamineTask(Integer id) {
        surveyExamineTaskDao.deleteSurveyExamineTask(id);
    }

    /**
     * 保存批量设置
     *
     * @param ids
     * @param userAccount
     */
    public void saveFastSet(String ids, String userAccount) throws BusinessException {
        if (StringUtils.isBlank(ids))
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (StringUtils.isBlank(userAccount))
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        List<Integer> list = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
        if (CollectionUtils.isNotEmpty(list)) {
            for (Integer id : list) {
                SurveyExamineTask surveyExamineTask = surveyExamineTaskDao.getSurveyExamineTaskById(id);
                if (surveyExamineTask != null) {
                    surveyExamineTask.setUserAccount(userAccount);
                    surveyExamineTaskDao.updateSurveyExamineTask(surveyExamineTask);
                }
            }
        }

    }


    /**
     * 获取可添加的任务
     *
     * @param dataTaskId
     * @param planDetailsId
     * @return
     */
    public List<DataExamineTask> getCanAppendTaskList(Integer dataTaskId, Integer pid, Integer planDetailsId) {
        List<DataExamineTask> dataExamineTasks = dataExamineTaskService.getCacheDataExamineTaskListByPid(dataTaskId);//获取所有任务
        SurveyExamineTask surveyExamineTask = new SurveyExamineTask();
        surveyExamineTask.setPlanDetailsId(planDetailsId);
        surveyExamineTask.setPid(pid);
        List<SurveyExamineTask> examineTaskList = surveyExamineTaskDao.getSurveyExamineTaskList(surveyExamineTask);
        if (CollectionUtils.isNotEmpty(examineTaskList)) {
            dataExamineTasks.removeIf(p -> {
                for (SurveyExamineTask examineTask : examineTaskList) {
                    if (examineTask.getDataTaskId().equals(p.getId()))
                        return true;
                }
                return false;
            });
        }

        return dataExamineTasks;
    }


}
