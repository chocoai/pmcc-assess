package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.common.enums.ComputeDataTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeSurePriceFactorDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceFactor;
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
    @Transactional(rollbackFor = Exception.class)
    public SchemeJudgeObject saveSurePriceFactor(Integer judgeObjectId, BigDecimal price, List<SchemeSurePriceFactor> factors) throws BusinessException {
        if (CollectionUtils.isEmpty(factors))
            return null;
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeObjectId);
        BigDecimal resultPrice = schemeJudgeObject.getOriginalPrice();
        if (resultPrice == null) resultPrice = price;
        //先删除
        schemeSurePriceFactorDao.deleteSurePriceFactorByJudgeObjectId(schemeJudgeObject.getId());
        //再添加
        StringBuilder factorBuilder=new StringBuilder();
        for (SchemeSurePriceFactor factor : factors) {
            if (factor.getType().equals(ComputeDataTypeEnum.ABSOLUTE.getId())) {
                resultPrice = resultPrice.add(factor.getCoefficient());
                factorBuilder.append(factor.getFactor()).append(":").append(factor.getCoefficient()).append(";");
            } else {
                resultPrice = resultPrice.multiply(factor.getCoefficient().add(new BigDecimal("1")));
                factorBuilder.append(factor.getFactor()).append(":").append(factor.getCoefficient().multiply(new BigDecimal("100"))).append("%;");
            }
            factor.setJudgeObjectId(schemeJudgeObject.getId());
            factor.setCreator(commonService.thisUserAccount());
            schemeSurePriceFactorDao.addSurePriceFactor(factor);
        }
        //更新调整后的价格
        schemeJudgeObject.setPrice(resultPrice.setScale(0, BigDecimal.ROUND_HALF_UP));
        schemeJudgeObject.setFactor(factorBuilder.toString());
        schemeJudgeObjectService.updateSchemeJudgeObject(schemeJudgeObject);
        return schemeJudgeObject;
    }

    public List<SchemeSurePriceFactor> getSurePriceFactors(Integer judgeObjectId) {
        List<SchemeSurePriceFactor> certAdjustmentFactorList = schemeSurePriceFactorDao.getFactorListByJudgeObjectId(judgeObjectId);
        return certAdjustmentFactorList;
    }

    /**
     * 拷贝调整因素
     *
     * @param beCopyJudgeId
     * @param judgeIds
     */
    @Transactional(rollbackFor = Exception.class)
    public void copySurePriceFactor(Integer beCopyJudgeId, List<Integer> judgeIds) {
        //除了复制调整因素信息，还需调整申报记录与委估对象中的价格
        if (CollectionUtils.isEmpty(judgeIds)) return;
        SchemeJudgeObject beCopyJudgeObject = schemeJudgeObjectService.getSchemeJudgeObject(beCopyJudgeId);
        if (beCopyJudgeObject == null) return;
        List<SchemeSurePriceFactor> factorList = schemeSurePriceFactorDao.getFactorListByJudgeObjectId(beCopyJudgeId);
        if (CollectionUtils.isEmpty(factorList)) return;
        for (Integer judgeId : judgeIds) {
            SchemeJudgeObject judgeObject = schemeJudgeObjectService.getSchemeJudgeObject(judgeId);
            //清空原调整因素
            schemeSurePriceFactorDao.deleteSurePriceFactorByJudgeObjectId(judgeObject.getId());
            for (SchemeSurePriceFactor factor : factorList) {
                SchemeSurePriceFactor surePriceFactor = new SchemeSurePriceFactor();
                BeanUtils.copyProperties(factor, surePriceFactor);
                surePriceFactor.setId(null);
                surePriceFactor.setJudgeObjectId(judgeObject.getId());
                schemeSurePriceFactorDao.addSurePriceFactor(surePriceFactor);
            }
            judgeObject.setPrice(beCopyJudgeObject.getPrice());
            judgeObject.setFactor(beCopyJudgeObject.getFactor());
            schemeJudgeObjectService.updateSchemeJudgeObject(judgeObject);
        }
    }
}