package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dal.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.input.project.SchemeJudgeObjectDto;
import com.copower.pmcc.assess.dto.input.project.SchemeJudgeObjectStringDto;
import com.copower.pmcc.assess.dto.output.project.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.SchemeAreaGroupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 估价对象
 * Created by 13426 on 2018/5/21.
 */
@Service
public class SchemeJudgeObjectService {
    @Autowired
    private SchemeEvaluationObjectService evaluationObjectService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private SchemeJudgeObjectDao dao;

    public void evaluationObjectSave(SchemeJudgeObjectStringDto objectDto) {
        String[] ids = objectDto.getId().split(",");
        String[] bestUseIds = objectDto.getBestUseId().split(",");
        String[] floorAreas = objectDto.getFloorArea().split(",");
        String[] groupNumbers = objectDto.getGroupNumber().split(",");
        String[] evaluationAreas = objectDto.getEvaluationArea().split(",");
        Date valueTimePoint = objectDto.getValueTimePoint();
        for (int i = 0; i < ids.length; i++) {
            Integer id = Integer.parseInt(ids[i]);
            SchemeJudgeObjectDto dto = get(id);
            BigDecimal bigDecimal = new BigDecimal(floorAreas[i]);
            dto.setFloorArea(bigDecimal);
            dto.setGroupNumber(groupNumbers[i]);
            dto.setEvaluationArea(evaluationAreas[i]);
            update(dto);
        }

        Integer id = Integer.parseInt(ids[0]);
        SchemeJudgeObjectDto dto = get(id);
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(dto.getAreaGroupId());
        schemeAreaGroup.setValueTimePoint(valueTimePoint);
        schemeAreaGroupService.update(schemeAreaGroup);

        List<Integer> integers = hashMapChange(ids);
        //tb_scheme_evaluation_object
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

    public List<SchemeJudgeObjectVo> listGroupId(String groupId) {
        List<SchemeJudgeObjectVo> schemeJudgeObjectVos = new ArrayList<>();
        for (SchemeJudgeObject s : dao.list(groupId)) {
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
     * @param strings
     * @return
     */
    public List<Integer> hashMapChange(String[] strings){
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
        for (Integer integer:map.keySet()){
            Integer key = integer;
            Integer value = map.get(key);
            if (value>=2){//拆分一次
                System.out.println(key+" "+value);
                arr.add(key);
            }
        }
        return arr;
    }

    public Integer[] change(String[] strings){
        Integer[] integers = new Integer[strings.length];
        for (int i = 0; i < strings.length; i++) {
            integers[i] = Integer.parseInt(strings[i]);
        }
        return integers;
    }
}
