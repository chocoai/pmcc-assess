package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.input.project.SchemeJudgeObjectDto;
import com.copower.pmcc.assess.dto.output.project.SchemeJudgeObjectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 估价对象
 * Created by 13426 on 2018/5/21.
 */
@Service
public class SchemeJudgeObjectService {
    @Autowired
    private SchemeJudgeObjectDao dao;

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
}
