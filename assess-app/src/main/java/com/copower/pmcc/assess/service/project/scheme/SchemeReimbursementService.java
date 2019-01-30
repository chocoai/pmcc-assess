package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReimbursementDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReimbursement;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class SchemeReimbursementService {
    @Autowired
    private SchemeReimbursementDao schemeReimbursementDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    public SchemeReimbursement getDataByPlanDetailsId(Integer planDetailsId) {
        SchemeReimbursement where = new SchemeReimbursement();
        where.setPlanDetailsId(planDetailsId);
        return schemeReimbursementDao.getSchemeReimbursement(where);
    }

    public void saveSchemeReimbursement(SchemeReimbursement schemeReimbursement) {
        if (schemeReimbursement.getId() != null && schemeReimbursement.getId() > 0) {
            schemeReimbursementDao.editSchemeReimbursement(schemeReimbursement);
        } else {
            schemeReimbursement.setCreator(processControllerComponent.getThisUser());
            schemeReimbursementDao.addSchemeReimbursement(schemeReimbursement);
        }
    }

    public List<SchemeReimbursement> getSchemeReimbursements(List<Integer> judgeObjectIds) {
        return schemeReimbursementDao.getSchemeReimbursements(judgeObjectIds);
    }

    public SchemeReimbursement getSingleObject(Integer id) {
        return schemeReimbursementDao.getSchemeReimbursement(id);
    }


    /**
     * 获取法定有限受偿款完整描述（单位万元）
     *
     * @param judgeObjectId
     * @return
     */
    public String getFullDescription(Integer judgeObjectId) {
        SchemeReimbursement schemeReimbursement = new SchemeReimbursement();
        schemeReimbursement.setJudgeObjectId(judgeObjectId);
        List<SchemeReimbursement> list = schemeReimbursementDao.getObjectList(schemeReimbursement);
        StringBuilder builder = new StringBuilder();
        if (CollectionUtils.isNotEmpty(list)) {
            SchemeReimbursement object = list.get(0);
            BigDecimal decimal = new BigDecimal("10000");
            builder.append("notSetUpTotalPrice" + ":" + (object.getNotSetUpTotalPrice().divide(decimal)));
            builder.append(",");
            builder.append("mortgagedTotalPrice" + ":" + (object.getMortgagedTotalPrice().divide(decimal)));
            builder.append(",");
            builder.append("owedTotalPrice" + ":" + (object.getOwedTotalPrice().divide(decimal)));
            builder.append(",");
            builder.append("otherTotalPrice" + ":" + (object.getOtherTotalPrice().divide(decimal)));
            builder.append(",");
            builder.append("knowTotalPrice" + ":" + (object.getKnowTotalPrice().divide(decimal)));
            builder.append(",");
            builder.append("mortgageTotalPrice" + ":" + (object.getMortgageTotalPrice().divide(decimal)));
            return builder.toString();
        }
        return null;
    }
}
