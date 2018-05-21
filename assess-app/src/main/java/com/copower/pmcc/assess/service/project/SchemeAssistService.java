package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.DeclareRecordItems;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.EvaluationMethodFieldDao;
import com.copower.pmcc.assess.dal.dao.EvaluationThinkingFieldDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationMethodVo;
import com.copower.pmcc.assess.dto.output.project.DeclareRecordVo;
import com.copower.pmcc.assess.dto.output.project.SchemeAreaGroupVo;
import com.copower.pmcc.assess.service.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBestUseDescriptionService;
import com.copower.pmcc.assess.service.data.EvaluationMethodService;
import com.copower.pmcc.assess.service.data.EvaluationThinkingService;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 评估工作方案
 * Created by 13426 on 2018/5/15.
 */
@Service
public class SchemeAssistService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private SchemeAreaGroupAuxiliaryService auxiliaryService;
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

    public List<DataBestUseDescription> dataBestUseDescriptionList(){
        return dataBestUseDescriptionService.dataBestUseDescriptionList();
    }

    public List<EvaluationThinkingField> schemeassistservice(Integer id){
        return thinkingFieldDao.schemeassistservice(id);
    }

    public EvaluationThinkingDto get(Integer id){
        return thinkingService.get(id);
    }

    public List<EvaluationMethodVo> listMethod(Integer method) {
        return methodService.list(method);
    }

    public List<EvaluationMethodField> list(Integer methodId,Integer type) {
       return methodFieldDao.schemeassistservice(methodId,type);
    }

    public List<EvaluationThinking> thinkingList(){
        return thinkingService.list(null);
    }

    /*评估方法 字典*/
    public List<BaseDataDic> evaluationmethod(){
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        return baseDataDics;
    }

    public DeclareRecordItems items(String projectID){
        DeclareRecordItems items = declareRecordService.foreachDeclareRecord(projectID);
        if (items.getItems().size()>0){
            return items;
        }
        return null;
    }

    /**
     * 区域分组
     * @param auxiliaryID
     * @return
     */
    public List<SchemeAreaGroupVo> schemeAreaGroupVoList(Integer auxiliaryID){
        SchemeAreaGroupAuxiliary schemeAreaGroupAuxiliary =  auxiliaryService.get(auxiliaryID);
        String groupID = schemeAreaGroupAuxiliary.getGroupId();
        List<SchemeAreaGroupVo> vos = schemeAreaGroupService.schemeAreaGroupVoList(groupID);
        return vos;
    }

    public List<SchemeAreaGroupAuxiliary> schemeareagroupauxiliary(String projectID){
        List<SchemeAreaGroupAuxiliary> schemeAreaGroupAuxiliaries =  auxiliaryService.list(projectID);
        if (schemeAreaGroupAuxiliaries.size()<1){
            declareRecordService.schemeareagroupauxiliary(projectID);//初始化
            List<SchemeAreaGroupAuxiliary> schemeAreaGroupAuxiliaries1 = auxiliaryService.list(projectID);
            if (schemeAreaGroupAuxiliaries1.size()>=1){
                return schemeAreaGroupAuxiliaries1;
            }else {
                try {
                    throw  new Exception();
                }catch (Exception e){

                }
            }
        }
        return schemeAreaGroupAuxiliaries;
    }




    public DeclareRecordVo change(DeclareRecord declareRecord){
        DeclareRecordVo vo = new DeclareRecordVo();
        BeanUtils.copyProperties(declareRecord,vo);
        return vo;
    }

}
