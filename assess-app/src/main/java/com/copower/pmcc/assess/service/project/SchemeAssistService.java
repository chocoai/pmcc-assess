package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.EvaluationMethodFieldDao;
import com.copower.pmcc.assess.dal.dao.EvaluationThinkingFieldDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingDto;
import com.copower.pmcc.assess.dto.input.project.SchemeEvaluationObjectDto;
import com.copower.pmcc.assess.dto.input.project.SchemeJudgeFunctionDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationMethodVo;
import com.copower.pmcc.assess.dto.output.project.DeclareRecordVo;
import com.copower.pmcc.assess.dto.output.project.SchemeAreaGroupVo;
import com.copower.pmcc.assess.dto.output.project.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBestUseDescriptionService;
import com.copower.pmcc.assess.service.data.EvaluationMethodService;
import com.copower.pmcc.assess.service.data.EvaluationThinkingService;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    public int schemeEvaluationObjectSave(SchemeEvaluationObjectDto dto) {
        return schemeEvaluationObjectService.add(dto);
    }

    @Transactional
    public boolean addSchemeJudgeFunctionDto(SchemeJudgeFunctionDto dto) {
        //只是保存评估方法中的评估思路
        if (StringUtils.isEmpty(dto.getNotApplicableReason()) && StringUtils.isEmpty(dto.getApplicableReason()) && !StringUtils.isEmpty(dto.getThinking())) {
            dto.setCreator(commonService.thisUserAccount());
            dto.setBisApplicable(true);//适用
            return schemeJudgeFunctionService.add(dto);
        } else {
            SchemeJudgeFunctionDto functionDto = schemeJudgeFunctionService.get(commonService.thisUserAccount(), dto.getMethodType(), dto.getJudgeObjectId());
            functionDto.setApplicableReason(dto.getApplicableReason());
            functionDto.setBisApplicable(true);
            functionDto.setNotApplicableReason(dto.getNotApplicableReason());
            functionDto.setJudgeObjectId(dto.getJudgeObjectId());
            return schemeJudgeFunctionService.update(functionDto);
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

    public List<EvaluationThinking> thinkingList() {
        return thinkingService.list(null);
    }

    /*评估方法 字典*/
    public List<BaseDataDic> evaluationmethod() {
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        return baseDataDics;
    }


    /**
     * 区域分组
     *
     * @param projectID
     * @return
     */
    public List<SchemeAreaGroupVo> schemeAreaGroupVoList(Integer projectID) {
        List<SchemeAreaGroupVo> vos = null;
        vos = schemeAreaGroupService.schemeAreaGroupVoList(projectID);
        if (vos.size() < 1) {
            //说明需要初始化
            declareRecordService.schemeareagroupauxiliary(projectID + "");//初始化
            vos = schemeAreaGroupService.schemeAreaGroupVoList(projectID);
            if (vos.size() >= 1) {
                return vos;
            } else {
                try {
                    throw new Exception();
                } catch (Exception e) {

                }
            }
        }
        return vos;
    }

    /**
     *
     * @param groupID
     * @return
     */
    public List<SchemeJudgeObjectVo> schemeAreaGroupVoListX(String groupID) {
        List<SchemeJudgeObjectVo> vos = null;
        vos = judgeObjectService.listGroupId(groupID);
        return vos;
    }


    public DeclareRecordVo change(DeclareRecord declareRecord) {
        DeclareRecordVo vo = new DeclareRecordVo();
        BeanUtils.copyProperties(declareRecord, vo);
        return vo;
    }

}
