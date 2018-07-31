package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationMethodFieldDao;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationThinkingFieldDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingDto;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeJudgeFunctionApplyDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationMethodVo;
import com.copower.pmcc.assess.service.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBestUseDescriptionService;
import com.copower.pmcc.assess.service.data.EvaluationMethodService;
import com.copower.pmcc.assess.service.data.EvaluationThinkingService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 评估工作方案
 * Created by 13426 on 2018/5/15.
 */
@Service
public class SchemeAssistService {
    @Autowired
    private SchemeJudgeObjectService judgeObjectService;
    @Autowired
    private SchemeEvaluationObjectService schemeEvaluationObjectService;

    @Autowired
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private EvaluationThinkingFieldDao thinkingFieldDao;
    @Autowired
    private EvaluationMethodService methodService;
    @Autowired
    private EvaluationThinkingService thinkingService;
    @Autowired
    private EvaluationMethodFieldDao methodFieldDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DataBestUseDescriptionService dataBestUseDescriptionService;

    /**
     * 保存评估方法
     * @param schemeJudgeFunctionApplyDto
     */
    public void saveJudgeFunction(SchemeJudgeFunctionApplyDto schemeJudgeFunctionApplyDto){
        List<SchemeJudgeFunction> judgeFunctionList = schemeJudgeFunctionApplyDto.getJudgeFunctionList();
        if(CollectionUtils.isNotEmpty(judgeFunctionList)){
            judgeFunctionList.forEach(p->{
                if(p.getId()!=null&&p.getId()>0){
                    schemeJudgeFunctionService.update(p);
                }else {
                    p.setCreator(commonService.thisUserAccount());
                    schemeJudgeFunctionService.add(p);
                }
            });
        }
    }

    public List<DataBestUseDescription> dataBestUseDescriptionList() {
        return dataBestUseDescriptionService.dataBestUseDescriptionList();
    }

    public List<EvaluationThinkingField> schemeassistserviceThinkFilds(Integer id, Integer type) {
        return thinkingFieldDao.schemeassistservice(id, type);
    }

    public EvaluationThinkingDto get(Integer id) {
        return thinkingService.get(id);
    }

    public List<EvaluationMethodVo> listMethod(Integer method) {
        return methodService.list(method);
    }

    public List<EvaluationMethodField> list(Integer methodId, Integer type) {
        return methodFieldDao.schemeassistservice(methodId, type);
    }

    /*评估方法 字典*/
    public List<BaseDataDic> dataDicMethodList() {
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        return baseDataDics;
    }

    /**
     * 获取申报信息区域分组 或初始化
     * @param projectId
     * @return
     */
    public List<SchemeAreaGroup> getSchemeGroup(Integer projectId) {
        return declareRecordService.getSchemeGroup(projectId);
    }


}
