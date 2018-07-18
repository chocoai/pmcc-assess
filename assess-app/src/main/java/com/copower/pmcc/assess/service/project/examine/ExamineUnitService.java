package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineUnitDao;
import com.copower.pmcc.assess.dal.basis.entity.ExamineUnit;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kings on 2018-7-6.
 */
@Service
public class ExamineUnitService {
    @Autowired
    private ExamineUnitDao examineUnitDao;
    @Autowired
    private CommonService commonService;

    /**
     * 获取版块
     *
     * @param id
     * @return
     */
    public ExamineUnit getUnitById(Integer id) {
        return examineUnitDao.getUnitById(id);
    }

    /**
     * 保存版块
     *
     * @param examineUnit
     */
    @Transactional
    public Integer saveUnit(ExamineUnit examineUnit) throws BusinessException {
        if (examineUnit == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (examineUnit.getId() != null && examineUnit.getId() > 0) {
            examineUnitDao.updateUnit(examineUnit);
        } else {
            examineUnit.setCreator(commonService.thisUserAccount());
            examineUnitDao.addUnit(examineUnit);
        }
        return examineUnit.getId();
    }
}
