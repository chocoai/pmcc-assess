package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.ProjectPhaseDao;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDao;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.dao.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.SchemeEvaluationObjectDto;
import com.copower.pmcc.assess.dto.input.project.SchemeJudgeObjectDto;
import com.copower.pmcc.assess.dto.input.project.SchemeJudgeObjectStringDto;
import com.copower.pmcc.assess.dto.output.project.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.SchemeAreaGroupService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 估价对象
 * Created by 13426 on 2018/5/21.
 */
@Service
public class SchemeJudgeObjectService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeEvaluationObjectService evaluationObjectService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private SchemeJudgeObjectDao dao;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private ProjectPhaseDao projectPhaseDao;

    @Autowired
    private ProjectPlanDao projectPlanDao;

    @Transactional
    public void evaluationObjectSave(SchemeJudgeObjectStringDto objectDto) {
        ProjectPlan projectPlan = null;
        if (!StringUtils.isEmpty(objectDto.getProjectPlanID())) {
            projectPlan = projectPlanDao.getProjectplanById(Integer.parseInt(objectDto.getProjectPlanID()));
        }else {
            try {
              throw new Exception("异常！");
            }catch (Exception e){

            }
        }
        List<ProjectPhase> projectPhases = projectPhaseDao.getProjectPhase(projectPlan.getWorkStageId());
        String[] ids = objectDto.getId().split(",");
        String groupID = get(Integer.parseInt(ids[0])).getGroupId();
        String[] bestUseIds = objectDto.getBestUseId().split(",");
        String[] floorAreas = objectDto.getFloorArea().split(",");
        String[] groupNumbers = objectDto.getGroupNumber().split(",");
        String[] evaluationAreas = objectDto.getEvaluationArea().split(",");
        String[] seats = objectDto.getSeat().split(",");
        String[] flags = objectDto.getFlag().split(",");
        Date valueTimePoint = objectDto.getValueTimePoint();
        int j = 0;
        ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
        projectPlanDetails.setProjectWorkStageId(projectPlan.getWorkStageId());
        projectPlanDetails.setPlanId(projectPlan.getId());
        projectPlanDetails.setProjectId(projectPlan.getProjectId());
        projectPlanDetails.setProjectPhaseName(schemeAreaGroupService.get(groupID).getProvinceCityDistrictStr());
        projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
        projectPlanDetails.setBisLastLayer(false);
        projectPlanDetails.setSorting(j++);
        projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);
        for (int i = 0; i < ids.length; i++) {
            try {
                Integer id = Integer.parseInt(ids[i]);
                SchemeJudgeObjectDto dto = get(id);
//                groupID = dto.getGroupId();
                BigDecimal bigDecimal = new BigDecimal(floorAreas[i]);
                dto.setFloorArea(bigDecimal);
                dto.setBestUseId(Integer.parseInt(bestUseIds[i]));
                dto.setGroupNumber(groupNumbers[i]);
                dto.setEvaluationArea(evaluationAreas[i]);
                dto.setSeat(seats[i]);
                dto.setFlag(flags[i]);
                update(dto);
                if (flags[i].equals("1")) {//说明是拆分之后的添加的数据
                    SchemeJudgeObjectDto dtoA = get(id);
                    SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(dtoA.getGroupId());
                    SchemeEvaluationObjectDto evaluationObjectDto = new SchemeEvaluationObjectDto();
                    evaluationObjectDto.setCreator(commonService.thisUserAccount());
                    evaluationObjectDto.setName(dtoA.getName());
                    evaluationObjectDto.setProjectId(dtoA.getProjectId());
                    evaluationObjectDto.setAreaGroupId(schemeAreaGroup.getId());
                    dtoA.setFloorArea(bigDecimal);
                    dtoA.setBestUseId(Integer.parseInt(bestUseIds[i]));
                    dtoA.setGroupNumber(groupNumbers[i]);
                    dtoA.setEvaluationArea(evaluationAreas[i]);
                    dtoA.setSeat(seats[i]);
                    dtoA.setFlag(flags[i]);
                    try {
                        int idE = evaluationObjectService.add(evaluationObjectDto);
                        dtoA.setEvaluationId(idE);
                        update(dtoA);
                        dtoA.setId(null);
                        add(dtoA);//增加一条记录
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                        try {
                            throw e;
                        } catch (Exception e1) {

                        }
                    }
                }
                j = 0;
                ProjectPlanDetails projectPlanDetail = new ProjectPlanDetails();
                projectPlanDetail.setProjectWorkStageId(projectPlan.getWorkStageId());
                projectPlanDetail.setPlanId(projectPlan.getId());
                projectPlanDetail.setProjectId(projectPlan.getProjectId());
                projectPlanDetail.setProjectPhaseName(dto.getName());
                projectPlanDetail.setStatus(ProcessStatusEnum.RUN.getValue());
                projectPlanDetail.setPid(projectPlanDetails.getId());
                projectPlanDetail.setDeclareRecordId(dto.getDeclareRecordId());
                projectPlanDetail.setFirstPid(projectPlanDetails.getId());
                projectPlanDetail.setSorting(j++);
                projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetail);
            } catch (Exception e) {
                logger.error(e.getMessage());
                try {
                    throw e;
                } catch (Exception e1) {

                }
            }
        }

        //区域分组进行修改
        Integer id = Integer.parseInt(ids[0]);
        SchemeJudgeObjectDto dto = get(id);
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(groupID);
        schemeAreaGroup.setValueTimePoint(valueTimePoint);
        schemeAreaGroupService.update(schemeAreaGroup);

    }

    @Transactional
    public boolean add(SchemeJudgeObjectDto dto) {
        return dao.add(dto);
    }

    @Transactional
    public boolean update(SchemeJudgeObjectDto dto) {
        return dao.update(dto);
    }

    @Transactional(readOnly = true)
    public SchemeJudgeObjectDto get(Integer id) {
        return dao.get(id);
    }

    @Transactional
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    public List<SchemeJudgeObjectVo> listGroupId(String groupID) {
        List<SchemeJudgeObjectVo> schemeJudgeObjectVos = new ArrayList<>();
        for (SchemeJudgeObject s : dao.list(groupID)) {
            schemeJudgeObjectVos.add(change(s));
        }
        return schemeJudgeObjectVos;
    }

    public SchemeJudgeObjectVo change(SchemeJudgeObject object) {
        SchemeJudgeObjectVo vo = new SchemeJudgeObjectVo();
        BeanUtils.copyProperties(object, vo);
        return vo;
    }

    /**
     * 统计出现的次数
     *
     * @param strings
     * @return
     */
    public List<Integer> hashMapChange(String[] strings) {
        Integer[] integers = change(strings);
        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(integers));
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < list.size(); i++) {
            int count = 1;
            Integer value = map.get(list.get(i));
            if (value != null) {
                count = value + 1;
            }
            map.put(list.get(i), count);
        }

        List<Integer> arr = new ArrayList<>();
        for (Integer integer : map.keySet()) {
            Integer key = integer;
            Integer value = map.get(key);
            if (value >= 2) {//拆分一次以上
                System.out.println(key + " " + value);
                arr.add(key);
            }
        }
        return arr;
    }

    public Integer[] change(String[] strings) {
        Integer[] integers = new Integer[strings.length];
        for (int i = 0; i < strings.length; i++) {
            integers[i] = Integer.parseInt(strings[i]);
        }
        return integers;
    }
}
