package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.dao.project.scheme.SchemeEvaluationObjectDao;
import com.copower.pmcc.assess.dal.entity.SchemeEvaluationObject;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeEvaluationObjectDto;
import com.copower.pmcc.assess.service.SchemeAreaGroupService;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 委估对象 (合并的记录)
 * Created by 13426 on 2018/5/21.
 */
@Service
public class SchemeEvaluationObjectService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private SchemeEvaluationObjectDao dao;

    @Transactional
    public int add(SchemeEvaluationObject dto) {
        dto.setCreator(commonService.thisUserAccount());
        return dao.add(dto);
    }

    @Transactional
    public boolean update(SchemeEvaluationObjectDto dto) {
        return dao.update(dto);
    }

    @Transactional(readOnly = true)
    public SchemeEvaluationObjectDto get(Integer id) {
        return dao.get(id);
    }

    @Transactional
    public boolean remove(Integer id) {
        return dao.remove(id);
    }
}
