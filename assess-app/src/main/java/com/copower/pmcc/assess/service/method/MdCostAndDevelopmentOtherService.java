package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdCostAndDevelopmentOtherDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Ordering;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/8/23 09:22
 * @Description:
 */
@Service
public class MdCostAndDevelopmentOtherService {
    @Autowired
    private MdCostAndDevelopmentOtherDao mdCostAndDevelopmentOtherDao;
    @Autowired
    private CommonService commonService;

    public void removePid(){
        mdCostAndDevelopmentOtherDao.deletePid(0);
    }

    public void saveAndUpdateMdCostAndDevelopmentOther(MdCostAndDevelopmentOther mdCostAndDevelopmentOther) {
//        if (mdCostAndDevelopmentOther.getId() == null || mdCostAndDevelopmentOther.getId().intValue()==0) {
//            if (Objects.equal(mdCostAndDevelopmentOther.getType(), MdCostBuilding.class.getSimpleName())) {
//                mdCostAndDevelopmentOther.setDatabaseName(FormatUtils.entityNameConvertToTableName(MdCostBuilding.class));
//            }
//            if (Objects.equal(mdCostAndDevelopmentOther.getType(), MdCostConstruction.class.getSimpleName())) {
//                mdCostAndDevelopmentOther.setDatabaseName(FormatUtils.entityNameConvertToTableName(MdCostConstruction.class));
//            }
//            if (Objects.equal(mdCostAndDevelopmentOther.getType(), MdDevelopmentHypothesis.class.getSimpleName())) {
//                mdCostAndDevelopmentOther.setDatabaseName(FormatUtils.entityNameConvertToTableName(MdDevelopmentHypothesis.class));
//            }
//            if (Objects.equal(mdCostAndDevelopmentOther.getType(), MdDevelopmentArchitectural.class.getSimpleName())) {
//                mdCostAndDevelopmentOther.setDatabaseName(FormatUtils.entityNameConvertToTableName(MdDevelopmentArchitectural.class));
//            }
//            mdCostAndDevelopmentOther.setPid(0);
//            this.addMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
//        } else {
//            this.updateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
//        }
    }



    public List<MdCostAndDevelopmentOther> costAndDevelopmentOtherList(String type,Integer pid,String databaseName){
        return mdCostAndDevelopmentOtherDao.costAndDevelopmentOtherList(type, pid, databaseName);
    }

    public MdCostAndDevelopmentOther getByPidMdCostAndDevelopmentOther(String type,Integer pid,String databaseName){
        List<MdCostAndDevelopmentOther> mdCostAndDevelopmentOtherList = costAndDevelopmentOtherList(type, pid, databaseName);
        if (!ObjectUtils.isEmpty(mdCostAndDevelopmentOtherList)){
            return  mdCostAndDevelopmentOtherList.get(0);
        }
        return null;
    }



    public int addMdCostAndDevelopmentOther(MdCostAndDevelopmentOther mdCostAndDevelopmentOther) {
        mdCostAndDevelopmentOther.setCreator(commonService.thisUserAccount());
        return mdCostAndDevelopmentOtherDao.addMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
    }

    public boolean updateMdCostAndDevelopmentOther(MdCostAndDevelopmentOther mdCostAndDevelopmentOther) {
        return mdCostAndDevelopmentOtherDao.updateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
    }

    public MdCostAndDevelopmentOther getMdCostAndDevelopmentOther(Integer id) {
        return mdCostAndDevelopmentOtherDao.getMdCostAndDevelopmentOther(id);
    }
}