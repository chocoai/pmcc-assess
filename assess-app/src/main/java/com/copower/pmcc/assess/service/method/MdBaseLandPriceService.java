package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.method.MdBaseLandPriceDao;
import com.copower.pmcc.assess.dal.basis.entity.MdBaseLandPrice;
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
public class MdBaseLandPriceService {
    @Autowired
    private MdBaseLandPriceDao schemeReimbursementDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    public List<MdBaseLandPrice> getObjectList(MdBaseLandPrice mdBaseLandPrice) {
        return schemeReimbursementDao.getObjectList(mdBaseLandPrice);
    }


    public MdBaseLandPrice getDataByProcessInsId(String processInsId) {
        MdBaseLandPrice where = new MdBaseLandPrice();
        where.setProcessInsId(processInsId);
        return schemeReimbursementDao.getMdBaseLandPrice(where);
    }

    public void applyCommit(String formData, String processInsId) {
        MdBaseLandPrice mdBaseLandPrice = JSON.parseObject(formData, MdBaseLandPrice.class);
        mdBaseLandPrice.setProcessInsId(processInsId);
        this.saveMdBaseLandPrice(mdBaseLandPrice);
    }


    public void saveMdBaseLandPrice(MdBaseLandPrice mdBaseLandPrice) {
        if (mdBaseLandPrice.getId() != null && mdBaseLandPrice.getId().intValue() > 0) {
            schemeReimbursementDao.editMdBaseLandPrice(mdBaseLandPrice);
        } else {
            mdBaseLandPrice.setCreator(processControllerComponent.getThisUser());
            schemeReimbursementDao.addMdBaseLandPrice(mdBaseLandPrice);
        }
    }

    public MdBaseLandPrice getSingleObject(Integer id) {
        return schemeReimbursementDao.getMdBaseLandPrice(id);
    }

}
