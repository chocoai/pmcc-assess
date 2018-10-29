package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.common.enums.ComputeDataTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeSurePriceFactorDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceFactor;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
public class SchemeSurePriceFactorService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeSurePriceFactorDao schemeSurePriceFactorDao;
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
    public SchemeJudgeObjectVo saveSurePriceFactor(Integer judgeObjectId, List<SchemeSurePriceFactor> factors) throws BusinessException {
        if (CollectionUtils.isEmpty(factors))
            return null;
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
        BigDecimal price = schemeJudgeObject.getOriginalPrice();
        //先删除
        schemeSurePriceFactorDao.deleteSurePriceFactorByDeclareId(schemeJudgeObject.getDeclareRecordId());
        //再添加
        for (SchemeSurePriceFactor factor : factors) {
            if (factor.getType().equals(ComputeDataTypeEnum.ABSOLUTE.getId())) {
                price = price.add(factor.getCoefficient());
            } else {
                price = price.multiply(factor.getCoefficient());
            }
            factor.setDeclareId(schemeJudgeObject.getDeclareRecordId());
            factor.setCreator(commonService.thisUserAccount());
            schemeSurePriceFactorDao.addSurePriceFactor(factor);
        }
        //更新调整后的价格
        price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
        declareRecord.setPrice(price);
        declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
        schemeJudgeObject.setPrice(price);
        schemeJudgeObjectService.updateSchemeJudgeObject(schemeJudgeObject);

        SchemeJudgeObjectVo schemeJudgeObjectVo = new SchemeJudgeObjectVo();
        schemeJudgeObjectVo.setPrice(price);
        schemeJudgeObjectVo.setCoefficient(schemeJudgeObjectService.getCoefficientByDeclareId(declareRecord.getId()));
        return schemeJudgeObjectVo;
    }

    public List<SchemeSurePriceFactor> getSurePriceFactors(Integer declareId) {
        SchemeSurePriceFactor examle = new SchemeSurePriceFactor();
        examle.setDeclareId(declareId);
        List<SchemeSurePriceFactor> certAdjustmentFactorList = schemeSurePriceFactorDao.getSurePriceFactorList(examle);
        return certAdjustmentFactorList;
    }

    /**
     * 拷贝调整因素
     *
     * @param beCopyJudgeId
     * @param judgeIds
     */
    @Transactional
    public void copySurePriceFactor(Integer beCopyJudgeId, List<Integer> judgeIds) {
        //除了复制调整因素信息，还需调整申报记录与委估对象中的价格
        if (CollectionUtils.isEmpty(judgeIds)) return;
        SchemeJudgeObject beCopyJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(beCopyJudgeId);
        if (beCopyJudgeObject == null) return;
        List<SchemeSurePriceFactor> factorList = schemeSurePriceFactorDao.getFactorListByDeclareId(beCopyJudgeObject.getDeclareRecordId());
        if (CollectionUtils.isEmpty(factorList)) return;
        for (Integer judgeId : judgeIds) {
            SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeId);
            //清空原调整因素
            schemeSurePriceFactorDao.deleteSurePriceFactorByDeclareId(judgeObject.getDeclareRecordId());

            for (SchemeSurePriceFactor factor : factorList) {
                SchemeSurePriceFactor surePriceFactor = new SchemeSurePriceFactor();
                BeanUtils.copyProperties(factor, surePriceFactor);
                surePriceFactor.setId(null);
                surePriceFactor.setDeclareId(judgeObject.getDeclareRecordId());
                schemeSurePriceFactorDao.addSurePriceFactor(surePriceFactor);
            }
            judgeObject.setPrice(beCopyJudgeObject.getPrice());
            schemeJudgeObjectService.updateSchemeJudgeObject(judgeObject);

            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(judgeObject.getDeclareRecordId());
            declareRecord.setPrice(beCopyJudgeObject.getPrice());
            try {
                declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
            } catch (BusinessException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}