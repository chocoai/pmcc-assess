package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.SchemeAreaGroupAuxiliaryDao;
import com.copower.pmcc.assess.dal.entity.SchemeAreaGroupAuxiliary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 13426 on 2018/5/18.
 */
@Service
public class SchemeAreaGroupAuxiliaryService {

    @Autowired
    private SchemeAreaGroupAuxiliaryDao areaGroupAuxiliaryDao;

    @Transactional
    public boolean add(SchemeAreaGroupAuxiliary auxiliary){
        return areaGroupAuxiliaryDao.add(auxiliary);
    }

    public List<SchemeAreaGroupAuxiliary> list(String projectID){
        return areaGroupAuxiliaryDao.list(projectID);
    }

    public SchemeAreaGroupAuxiliary get(Integer id){
        return areaGroupAuxiliaryDao.get(id);
    }
}
