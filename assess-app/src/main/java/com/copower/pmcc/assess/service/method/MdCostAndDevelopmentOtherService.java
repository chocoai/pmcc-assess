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
        if (mdCostAndDevelopmentOther.getId() == null || mdCostAndDevelopmentOther.getId().intValue()==0) {
            if (Objects.equal(mdCostAndDevelopmentOther.getType(), MdCostBuilding.class.getSimpleName())) {
                mdCostAndDevelopmentOther.setDatabaseName(FormatUtils.entityNameConvertToTableName(MdCostBuilding.class));
            }
            if (Objects.equal(mdCostAndDevelopmentOther.getType(), MdCostConstruction.class.getSimpleName())) {
                mdCostAndDevelopmentOther.setDatabaseName(FormatUtils.entityNameConvertToTableName(MdCostConstruction.class));
            }
            if (Objects.equal(mdCostAndDevelopmentOther.getType(), MdDevelopmentHypothesis.class.getSimpleName())) {
                mdCostAndDevelopmentOther.setDatabaseName(FormatUtils.entityNameConvertToTableName(MdDevelopmentHypothesis.class));
            }
            if (Objects.equal(mdCostAndDevelopmentOther.getType(), MdDevelopmentArchitectural.class.getSimpleName())) {
                mdCostAndDevelopmentOther.setDatabaseName(FormatUtils.entityNameConvertToTableName(MdDevelopmentArchitectural.class));
            }
            mdCostAndDevelopmentOther.setPid(0);
            this.addMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
        } else {
            this.updateMdCostAndDevelopmentOther(mdCostAndDevelopmentOther);
        }
    }

    public List<MdCostAndDevelopmentOther> getMdCostAndDevelopmentOtherList(MdCostAndDevelopmentOther mdCostAndDevelopmentOther) {
        List<MdCostAndDevelopmentOther> otherList = mdCostAndDevelopmentOtherDao.getMdCostAndDevelopmentOtherList(mdCostAndDevelopmentOther);
        Ordering<MdCostAndDevelopmentOther> firstOrdering = Ordering.from(new Comparator<MdCostAndDevelopmentOther>() {
            @Override
            public int compare(MdCostAndDevelopmentOther o1, MdCostAndDevelopmentOther o2) {
                return o1.getGmtCreated().compareTo(o2.getGmtCreated());
            }
        }).reverse();//排序 并且反转
        Collections.sort(otherList, firstOrdering);
        return otherList;
    }

    public MdCostAndDevelopmentOther getMdCostAndDevelopmentOther(String entityName,int pid){
        MdCostAndDevelopmentOther mdCostAndDevelopmentOther = new MdCostAndDevelopmentOther();
        mdCostAndDevelopmentOther.setPid(pid);
        mdCostAndDevelopmentOther.setType(entityName);
        List<MdCostAndDevelopmentOther> otherList = getMdCostAndDevelopmentOtherList(mdCostAndDevelopmentOther);
        if (!ObjectUtils.isEmpty(otherList)) {
            mdCostAndDevelopmentOther = otherList.get(0);
            return  mdCostAndDevelopmentOther;
        } else {
            return null;
        }
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