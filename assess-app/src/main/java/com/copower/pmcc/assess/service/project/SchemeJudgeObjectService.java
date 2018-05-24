package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.dao.ProjectPhaseDao;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDao;
import com.copower.pmcc.assess.dal.dao.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.dao.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.SchemeEvaluationObjectDto;
import com.copower.pmcc.assess.dto.input.project.SchemeJudgeObjectApplyDto;
import com.copower.pmcc.assess.dto.input.project.SchemeJudgeObjectDto;
import com.copower.pmcc.assess.dto.input.project.SchemeJudgeObjectStringDto;
import com.copower.pmcc.assess.dto.output.project.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.SchemeAreaGroupService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Sets;
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

    /**
     * 保存委估对象
     * @param formData
     */
    public void saveEvaluationObject(String formData) {
        SchemeJudgeObjectApplyDto applyDto = JSON.parseObject(formData, SchemeJudgeObjectApplyDto.class);
        //1.保存估价对象信息 2.根据测算号生成委估对象 3.根据委估信息生成对应的计划目录

        List<SchemeJudgeObject> schemeJudgeObjects = applyDto.getSchemeJudgeObjects();
        ProjectPlan projectPlan = projectPlanDao.getProjectplanById(applyDto.getPlanId());

        HashSet<Integer> hashSet= Sets.newHashSet();
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjects) {
            saveJudgeObject(schemeJudgeObject);
            hashSet.add(schemeJudgeObject.getGroupNumber());
        }

        //清空原生成的数据


        ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
        projectPlanDetails.setProjectWorkStageId(projectPlan.getWorkStageId());
        projectPlanDetails.setPlanId(projectPlan.getId());
        projectPlanDetails.setProjectId(projectPlan.getProjectId());
        projectPlanDetails.setProjectPhaseName(applyDto.getAreaGroupName());
        projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
        projectPlanDetails.setBisLastLayer(false);
        projectPlanDetails.setSorting(0);
        projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);

        hashSet.forEach(p->{
            SchemeEvaluationObject schemeEvaluationObject=new SchemeEvaluationObjectDto();
            schemeEvaluationObject.setProjectId(projectPlan.getProjectId());
            schemeEvaluationObject.setAreaGroupId(applyDto.getAreaGroupId());
            schemeEvaluationObject.setName(String.format("%s号委估对象",p));
            schemeEvaluationObject.setGroupNumber(p);
            schemeEvaluationObject.setCreator(commonService.thisUserAccount());
            evaluationObjectService.add(schemeEvaluationObject);

            ProjectPlanDetails details = new ProjectPlanDetails();
            details.setProjectWorkStageId(projectPlan.getWorkStageId());
            details.setPlanId(projectPlan.getId());
            details.setProjectId(projectPlan.getProjectId());
            details.setProjectPhaseName(schemeEvaluationObject.getName());
            details.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
            details.setEvaluationId(schemeEvaluationObject.getId());
            details.setPid(projectPlanDetails.getId());
            details.setBisLastLayer(false);
            details.setSorting(p);
            projectPlanDetailsDao.addProjectPlanDetails(details);
        });


    }


    public void saveJudgeObject(SchemeJudgeObject schemeJudgeObject) {
        if (schemeJudgeObject.getId() != null && schemeJudgeObject.getId() > 0) {
            update(schemeJudgeObject);
        } else {
            add(schemeJudgeObject);
        }
    }

    @Transactional
    public void evaluationObjectSave(SchemeJudgeObjectStringDto objectDto) {
        ProjectPlan projectPlan = null;
        if (!StringUtils.isEmpty(objectDto.getProjectPlanID())) {
           // projectPlan = projectPlanDao.getProjectplanById(Integer.parseInt(objectDto.getProjectPlanID()));
        } else {
            try {
                throw new Exception("异常！");
            } catch (Exception e) {

            }
        }
        String[] ids = objectDto.getId().split(",");
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
        projectPlanDetails.setProjectPhaseName(schemeAreaGroupService.get(objectDto.getAreaGroupId()).getProvinceCityDistrictStr());
        projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
        projectPlanDetails.setBisLastLayer(false);
        projectPlanDetails.setSorting(j++);
        projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);
        for (int i = 0; i < ids.length; i++) {
            try {
                Integer id = Integer.parseInt(ids[i]);
                SchemeJudgeObjectDto dto = get(id);
//                groupID = dto.getGroupId();
//                BigDecimal bigDecimal = new BigDecimal(floorAreas[i]);
//                dto.setFloorArea(bigDecimal);
//                dto.setBestUseId(Integer.parseInt(bestUseIds[i]));
//                dto.setGroupNumber(groupNumbers[i]);
//                dto.setEvaluationArea(evaluationAreas[i]);
//                dto.setSeat(seats[i]);
//                dto.setBisSplit(false);
//                update(dto);
//                if (flags[i].equals("1")) {//说明是拆分之后的添加的数据
//                    SchemeJudgeObjectDto dtoA = get(id);
//                    SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(dtoA.getAreaGroupId());
//                    SchemeEvaluationObjectDto evaluationObjectDto = new SchemeEvaluationObjectDto();
//                    evaluationObjectDto.setCreator(commonService.thisUserAccount());
//                    evaluationObjectDto.setName(dtoA.getName());
//                    evaluationObjectDto.setProjectId(dtoA.getProjectId());
//                    evaluationObjectDto.setAreaGroupId(schemeAreaGroup.getId());
//                    dtoA.setFloorArea(bigDecimal);
//                    dtoA.setBestUseId(Integer.parseInt(bestUseIds[i]));
//                    dtoA.setGroupNumber(groupNumbers[i]);
//                    dtoA.setEvaluationArea(evaluationAreas[i]);
//                    dtoA.setSeat(seats[i]);
//                    dtoA.setBisSplit(true);
//                    try {
//                        int idE = evaluationObjectService.add(evaluationObjectDto);
//                        dtoA.setEvaluationId(idE);
//                        update(dtoA);
//                        dtoA.setId(null);
//                        add(dtoA);//增加一条记录
//                    } catch (Exception e) {
//                        logger.error(e.getMessage());
//                        try {
//                            throw e;
//                        } catch (Exception e1) {
//
//                        }
//                    }
//                }
                ProjectPlanDetails projectPlanDetail = new ProjectPlanDetails();
                projectPlanDetail.setProjectWorkStageId(projectPlan.getWorkStageId());
                projectPlanDetail.setPlanId(projectPlan.getId());
                projectPlanDetail.setProjectId(projectPlan.getProjectId());
                projectPlanDetail.setProjectPhaseName(dto.getName());
                projectPlanDetail.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                projectPlanDetail.setPid(projectPlanDetails.getId());
                projectPlanDetail.setDeclareRecordId(dto.getDeclareRecordId());
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
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(objectDto.getAreaGroupId());
        schemeAreaGroup.setValueTimePoint(valueTimePoint);
        schemeAreaGroupService.update(schemeAreaGroup);

    }

    @Transactional
    public boolean add(SchemeJudgeObject dto) {
        return dao.add(dto);
    }

    @Transactional
    public boolean update(SchemeJudgeObject dto) {
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

    public List<SchemeJudgeObjectVo> listGroupId(Integer areaGroupId) {
        List<SchemeJudgeObjectVo> schemeJudgeObjectVos = new ArrayList<>();
        for (SchemeJudgeObject s : dao.list(areaGroupId)) {
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
