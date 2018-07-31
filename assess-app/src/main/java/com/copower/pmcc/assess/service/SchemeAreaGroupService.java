package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeAreaGroupDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 区域分组
 * Created by 13426 on 2018/5/14.
 */
@Service
public class SchemeAreaGroupService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SchemeAreaGroupDao dao;

    @Transactional
    public int add(SchemeAreaGroup schemeAreaGroup) {
        return dao.add(schemeAreaGroup);
    }

    /**
     * 根据分组 获取数据信息
     *
     * @param projectId
     * @return
     */
    public List<SchemeAreaGroup> schemeAreaGroupVoList(Integer projectId) {
        List<SchemeAreaGroup> schemeAreaGroupList = dao.getSchemeAreaGroupByProjectId(projectId);
        return schemeAreaGroupList;
    }



    @Transactional
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    @Transactional
    public boolean update(SchemeAreaGroup schemeAreaGroup) {
        return dao.update(schemeAreaGroup);
    }


    @Transactional(readOnly = true)
    public SchemeAreaGroup get(Integer id) {
        return dao.get(id);
    }

}
