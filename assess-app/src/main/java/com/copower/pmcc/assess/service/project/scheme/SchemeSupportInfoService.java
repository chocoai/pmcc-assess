package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeSupportInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.EvaluationBasisService;
import com.copower.pmcc.assess.service.data.EvaluationHypothesisService;
import com.copower.pmcc.assess.service.data.EvaluationPrincipleService;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SchemeSupportInfoService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private SchemeSupportInfoDao schemeSupportInfoDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationHypothesisService evaluationHypothesisService;
    @Autowired
    private EvaluationBasisService evaluationBasisService;
    @Autowired
    private EvaluationPrincipleService evaluationPrincipleService;


    /**
     * 保存支撑信息
     *
     * @param schemeSupportInfo
     */
    public void saveSupportInfo(SchemeSupportInfo schemeSupportInfo) throws BusinessException {
        if (schemeSupportInfo == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (schemeSupportInfo.getId() != null && schemeSupportInfo.getId() > 0) {
            schemeSupportInfoDao.updateSupportInfo(schemeSupportInfo);
        } else {
            schemeSupportInfo.setCreator(commonService.thisUserAccount());
            schemeSupportInfoDao.addSupportInfo(schemeSupportInfo);
        }
    }

    /**
     * 获取支持数据列表
     * @param planDetailsId
     * @return
     */
    public List<SchemeSupportInfo> getSupportInfoList(Integer planDetailsId){
        SchemeSupportInfo where=new SchemeSupportInfo();
        where.setPlanDetailsId(planDetailsId);
        return schemeSupportInfoDao.getSupportInfoList(where);
    }

    /**
     * 初始化该任务所需要的支撑信息
     *
     * @param planDetailsId
     */
    @Transactional(rollbackFor = Exception.class)
    public void initSupportInfo(Integer planDetailsId, Integer purpose, String methodType) {
        //先检查该任务是否已有初始话数据
        //1.根据项目的委托目的，当前方法的类型;分别到 评估假设、评估依据、评估原则（最佳利用描述、价值时点说明）找出与之关联的模板
        //2.将模板数据初始话到支撑信息表中，方便后续处理

        int count = schemeSupportInfoDao.getCountByPlanDetailsId(planDetailsId);
        if (count > 0) return;
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(methodType);
        List<DataEvaluationPrinciple> principleList = evaluationPrincipleService.getPrincipleList(baseDataDic.getId(), purpose);//原则
        SchemeSupportInfo schemeSupportInfo = null;
        if (CollectionUtils.isNotEmpty(principleList)) {
            for (DataEvaluationPrinciple evaluationPrinciple : principleList) {
                schemeSupportInfo = new SchemeSupportInfo();
                schemeSupportInfo.setCreator(commonService.thisUserAccount());
                schemeSupportInfo.setName(evaluationPrinciple.getName());
                schemeSupportInfo.setTemplate(evaluationPrinciple.getTemplate());
                schemeSupportInfo.setSupportType(SchemeSupportTypeEnum.PRINCIPLE.getId());
                schemeSupportInfo.setSupportTypeName(SchemeSupportTypeEnum.PRINCIPLE.getName());
                schemeSupportInfo.setJsonContent(publicService.extractField(evaluationPrinciple.getTemplate()));
                schemeSupportInfo.setPlanDetailsId(planDetailsId);
                schemeSupportInfoDao.addSupportInfo(schemeSupportInfo);
            }
        }
        List<DataEvaluationHypothesis> hypothesisList = evaluationHypothesisService.getHypothesisList(baseDataDic.getId(), purpose);//假设
        if (CollectionUtils.isNotEmpty(hypothesisList)) {
            for (DataEvaluationHypothesis evaluationHypothesis : hypothesisList) {
                schemeSupportInfo = new SchemeSupportInfo();
                schemeSupportInfo.setCreator(commonService.thisUserAccount());
                schemeSupportInfo.setName(evaluationHypothesis.getName());
                schemeSupportInfo.setTemplate(evaluationHypothesis.getTemplate());
                schemeSupportInfo.setSupportType(SchemeSupportTypeEnum.HYPOTHESIS.getId());
                schemeSupportInfo.setSupportTypeName(SchemeSupportTypeEnum.HYPOTHESIS.getName());
                schemeSupportInfo.setJsonContent(publicService.extractField(evaluationHypothesis.getTemplate()));
                schemeSupportInfo.setPlanDetailsId(planDetailsId);
                schemeSupportInfoDao.addSupportInfo(schemeSupportInfo);
            }
        }
        List<DataEvaluationBasis> basisList = evaluationBasisService.getBasisList(baseDataDic.getId(), purpose);//依据
        if (CollectionUtils.isNotEmpty(basisList)) {
            for (DataEvaluationBasis evaluationBasis : basisList) {
                schemeSupportInfo = new SchemeSupportInfo();
                schemeSupportInfo.setCreator(commonService.thisUserAccount());
                schemeSupportInfo.setName(evaluationBasis.getName());
                schemeSupportInfo.setTemplate(evaluationBasis.getTemplate());
                schemeSupportInfo.setSupportType(SchemeSupportTypeEnum.BASIS.getId());
                schemeSupportInfo.setSupportTypeName(SchemeSupportTypeEnum.BASIS.getName());
                schemeSupportInfo.setJsonContent(publicService.extractField(evaluationBasis.getTemplate()));
                schemeSupportInfo.setPlanDetailsId(planDetailsId);
                schemeSupportInfoDao.addSupportInfo(schemeSupportInfo);
            }
        }

    }
}
