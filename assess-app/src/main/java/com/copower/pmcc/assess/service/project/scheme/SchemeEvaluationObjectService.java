package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeEvaluationObjectDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeEvaluationObject;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 委估对象 (合并的记录)
 * Created by 13426 on 2018/5/21.
 */
@Service
public class SchemeEvaluationObjectService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeEvaluationObjectDao dao;

    public int add(SchemeEvaluationObject dto) {
        dto.setCreator(commonService.thisUserAccount());
        return dao.add(dto);
    }

    public boolean update(SchemeEvaluationObject schemeEvaluationObject) {
        return dao.update(schemeEvaluationObject);
    }

    public SchemeEvaluationObject get(Integer id) {
        return dao.get(id);
    }

    public List<SchemeEvaluationObject> getDataListByGroupId(Integer groupId, Integer projectId) {
        return dao.getDataListByGroupId(groupId,projectId);
    }


    public boolean remove(Integer id) {
        return dao.remove(id);
    }
}
