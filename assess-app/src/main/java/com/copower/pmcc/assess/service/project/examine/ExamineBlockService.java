package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.common.enums.ExamineTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineBlockDao;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBlock;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineBlockVo;
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
public class ExamineBlockService {
    @Autowired
    private ExamineBlockDao examineBlockDao;
    @Autowired
    private CommonService commonService;

    /**
     * 获取版块
     *
     * @param id
     * @return
     */
    public ExamineBlock getBlockById(Integer id) {
        return examineBlockDao.getBlockById(id);
    }

    /**
     * 获取版块
     *
     * @param declareId
     * @return
     */
    public ExamineBlock getBlockByDeclareId(Integer declareId, ExamineTypeEnum examineTypeEnum) {
        return examineBlockDao.getBlockByDeclareId(declareId,examineTypeEnum.getId());
    }

    public ExamineBlockVo getExamineBlockVo(ExamineBlock examineBlock) {
        if (examineBlock == null) return null;
        ExamineBlockVo examineBlockVo = new ExamineBlockVo();
        BeanUtils.copyProperties(examineBlock, examineBlockVo);
        return examineBlockVo;
    }

    /**
     * 保存版块
     *
     * @param examineBlock
     */
    @Transactional
    public Integer saveBlock(ExamineBlock examineBlock) throws BusinessException {
        if (examineBlock == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (examineBlock.getId() != null && examineBlock.getId() > 0) {
            examineBlockDao.updateBlock(examineBlock);
        } else {
            examineBlock.setCreator(commonService.thisUserAccount());
            examineBlockDao.addBlock(examineBlock);
        }
        return examineBlock.getId();
    }
}
