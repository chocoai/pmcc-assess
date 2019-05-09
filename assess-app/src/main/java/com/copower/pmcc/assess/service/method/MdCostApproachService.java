package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostApproachDao;
import com.copower.pmcc.assess.dal.basis.entity.MdCostApproach;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基准地价系数修正法
 *
 * @author noOne
 */

@Service
public class MdCostApproachService {
    @Autowired
    private MdCostApproachDao schemeReimbursementDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    public List<MdCostApproach> getObjectList(MdCostApproach mdCostApproach) {
        return schemeReimbursementDao.getObjectList(mdCostApproach);
    }


    public MdCostApproach getDataByProcessInsId(String processInsId) {
        MdCostApproach where = new MdCostApproach();
        where.setProcessInsId(processInsId);
        return schemeReimbursementDao.getMdCostApproach(where);
    }

    public void applyCommit(String formData, String processInsId) {
        MdCostApproach mdCostApproach = JSON.parseObject(formData, MdCostApproach.class);
        mdCostApproach.setProcessInsId(processInsId);
        this.saveMdCostApproach(mdCostApproach);
    }


    public void saveMdCostApproach(MdCostApproach mdCostApproach) {
        if (mdCostApproach.getId() != null && mdCostApproach.getId().intValue() > 0) {
            schemeReimbursementDao.editMdCostApproach(mdCostApproach);
        } else {
            mdCostApproach.setCreator(processControllerComponent.getThisUser());
            schemeReimbursementDao.addMdCostApproach(mdCostApproach);
        }
    }

    public MdCostApproach getSingleObject(Integer id) {
        return schemeReimbursementDao.getMdCostApproach(id);
    }

}
