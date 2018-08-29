package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineEstateDao;
import com.copower.pmcc.assess.dal.basis.entity.DataDeveloper;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstate;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineEstateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataDeveloperService;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataDeveloperService dataDeveloperService;
    private Logger logger = LoggerFactory.getLogger(getClass());

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
        if (examineEstate.getDeveloperId()!=null){
            try {
                DataDeveloper dataDeveloper = dataDeveloperService.getByDataDeveloperId(examineEstate.getDeveloperId());
                if (dataDeveloper != null){
                    examineEstateVo.setDeveloperName(dataDeveloper.getName());
                }
            } catch (Exception e1) {
                logger.error(String.format("没有找到实体 == >%s",e1.getMessage()));
            }
        }
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
