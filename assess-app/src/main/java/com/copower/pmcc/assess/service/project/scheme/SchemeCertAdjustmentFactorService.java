package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeCertAdjustmentFactorDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.SchemeCertAdjustmentFactor;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 方案主表
 * Created by 13426 on 2018/5/24.
 */
@Service
public class SchemeCertAdjustmentFactorService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeCertAdjustmentFactorDao schemeCertAdjustmentFactorDao;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;

    /**
     * 保存信息
     *
     * @param judgeObjectId
     * @param factors
     * @throws BusinessException
     */
    @Transactional
    public void saveCertAdjustmentFactor(Integer judgeObjectId, List<SchemeCertAdjustmentFactor> factors) throws BusinessException {
        if (CollectionUtils.isEmpty(factors))
            return;
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
        BigDecimal price = schemeJudgeObject.getOriginalPrice();
        //先删除
        schemeCertAdjustmentFactorDao.deleteCertAdjustmentFactorByDeclareId(schemeJudgeObject.getDeclareRecordId());
        //再添加
        for (SchemeCertAdjustmentFactor factor : factors) {
            price = price.multiply(factor.getFactor());
            factor.setDeclareId(schemeJudgeObject.getDeclareRecordId());
            factor.setCreator(commonService.thisUserAccount());
            schemeCertAdjustmentFactorDao.addCertAdjustmentFactor(factor);
        }
        //更新调整后的价格
        declareRecord.setPrice(price);
        declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
        schemeJudgeObject.setPrice(price);
        schemeJudgeObjectService.updateSchemeJudgeObject(schemeJudgeObject);
    }

    public List<SchemeCertAdjustmentFactor> getCertAdjustmentFactors(Integer declareId) {
        SchemeCertAdjustmentFactor examle = new SchemeCertAdjustmentFactor();
        examle.setDeclareId(declareId);
        List<SchemeCertAdjustmentFactor> certAdjustmentFactorList = schemeCertAdjustmentFactorDao.getCertAdjustmentFactorList(examle);
        return certAdjustmentFactorList;
    }
}