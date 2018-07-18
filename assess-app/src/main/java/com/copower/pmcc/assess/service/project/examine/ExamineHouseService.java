package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineHouseDao;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouse;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineHouseVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kings on 2018-7-6.
 */
@Service
public class ExamineHouseService {
    @Autowired
    private ExamineHouseDao examineHouseDao;
    @Autowired
    private CommonService commonService;

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public ExamineHouse getHouseById(Integer id) {
        return examineHouseDao.getHouseById(id);
    }

    /**
     * 获取数据
     *
     * @param declareId
     * @return
     */
    public ExamineHouse getHouseByDeclareId(Integer declareId) {
        return examineHouseDao.getHouseByDeclareId(declareId);
    }

    public ExamineHouseVo getExamineHouseVo(ExamineHouse examineHouse) {
        if (examineHouse == null) return null;
        ExamineHouseVo examineHouseVo = new ExamineHouseVo();
        BeanUtils.copyProperties(examineHouse, examineHouseVo);
        return examineHouseVo;
    }


    /**
     * 保存数据
     *
     * @param examineHouse
     */
    @Transactional
    public Integer saveHouse(ExamineHouse examineHouse) throws BusinessException {
        if (examineHouse == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (examineHouse.getId() != null && examineHouse.getId() > 0) {
            examineHouseDao.updateHouse(examineHouse);
        } else {
            examineHouse.setCreator(commonService.thisUserAccount());
            examineHouseDao.addHouse(examineHouse);
        }
        return examineHouse.getId();
    }
}
