package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineEstateDao;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstate;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineEstateVo;
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
public class ExamineEstateService {
    @Autowired
    private ExamineEstateDao examineEstateDao;
    @Autowired
    private CommonService commonService;

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public ExamineEstate getEstateById(Integer id) {
        return examineEstateDao.getEstateById(id);
    }

    /**
     * 获取数据
     *
     * @param declareId
     * @return
     */
    public ExamineEstate getEstateByDeclareId(Integer declareId,Integer planDetailsId,ExamineTypeEnum examineTypeEnum) {
        return examineEstateDao.getEstateByDeclareId(declareId,planDetailsId,examineTypeEnum.getId());
    }

    public ExamineEstateVo getExamineEstateVo(ExamineEstate examineEstate) {
        if (examineEstate == null) return null;
        ExamineEstateVo examineEstateVo = new ExamineEstateVo();
        BeanUtils.copyProperties(examineEstate, examineEstateVo);
        return examineEstateVo;
    }

    /**
     * 保存数据
     *
     * @param examineEstate
     */
    @Transactional
    public Integer saveEstate(ExamineEstate examineEstate) throws BusinessException {
        if (examineEstate == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (examineEstate.getId() != null && examineEstate.getId() > 0) {
            examineEstateDao.updateEstate(examineEstate);
        } else {
            examineEstate.setCreator(commonService.thisUserAccount());
            examineEstateDao.addEstate(examineEstate);
        }
        return examineEstate.getId();
    }
}
