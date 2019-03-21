package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryRightRecordCenterDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: zch
 * @date: 2019/3/20 14:54
 * @description:他项权利主类 (他项权利主类 > 他项权力申报类 > 他项权力普通类)
 */
@Service
public class SurveyAssetInventoryRightRecordCenterService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private SurveyAssetInventoryRightRecordCenterDao surveyAssetInventoryRightRecordCenterDao;
    @Autowired
    private SurveyAssetInventoryRightRecordService surveyAssetInventoryRightRecordService;
    @Autowired
    private DeclareRecordService declareRecordService;

    public Map<SchemeJudgeObject,DeclareRecord> getDeclareRecordJudgeObjectMap(List<DeclareRecord> declareRecordList,List<SchemeJudgeObject> schemeJudgeObjectList){
        Map<SchemeJudgeObject,DeclareRecord> schemeJudgeObjectDeclareRecordMap = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject:schemeJudgeObjectList){
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord != null){
                long count = declareRecordList.stream().filter(declareRecord1 -> Objects.equal(declareRecord1.getId(),declareRecord.getId())).count();
                if (count >= 1){
                    schemeJudgeObjectDeclareRecordMap.put(schemeJudgeObject,declareRecord);
                }
            }
        }
        return schemeJudgeObjectDeclareRecordMap;
    }

    public List<DeclareRecord> getDeclareRecordList(String[] ids){
        List<DeclareRecord> declareRecordList = Lists.newArrayList();
        for (String id:ids){
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(Integer.parseInt(id));
            if (declareRecord != null){
                declareRecordList.add(declareRecord);
            }
        }
        return declareRecordList;
    }

    public List<SurveyAssetInventoryRightRecord> getSurveyAssetInventoryRightRecordList(Integer inventoryRightRecordCenterId, Integer projectId, Integer planDetailsId) {
        SurveyAssetInventoryRightRecord select = new SurveyAssetInventoryRightRecord();
        select.setProjectId(projectId);
        select.setPlanDetailsId(planDetailsId);
        if (inventoryRightRecordCenterId == null) {
            select.setCreator(commonService.thisUserAccount());
        }else {
            select.setInventoryRightRecordCenterId(inventoryRightRecordCenterId);
        }
        List<SurveyAssetInventoryRightRecord> rightRecordList = surveyAssetInventoryRightRecordService.surveyAssetInventoryRightRecordList(select);
        return rightRecordList;
    }

    /**
     * 更新关联子类
     *
     * @param projectPlanDetails
     * @param processInsId
     * @throws Exception
     */
    public void updateSonRecord(ProjectPlanDetails projectPlanDetails, String processInsId, String formData) throws Exception {
        List<SurveyAssetInventoryRightRecord> rightRecordList = this.getSurveyAssetInventoryRightRecordList(null,projectPlanDetails.getProjectId(),projectPlanDetails.getId());
        SurveyAssetInventoryRightRecordCenter query = new SurveyAssetInventoryRightRecordCenter();
        if (StringUtils.isEmpty(processInsId)) {
            processInsId = "0";
        }
        query.setProcessInsId(processInsId);
        query.setPlanDetailsId(projectPlanDetails.getId());
        query.setProjectId(projectPlanDetails.getProjectId());
        query.setCreator(commonService.thisUserAccount());

        List<SurveyAssetInventoryRightRecordCenter> list = this.getSurveyAssetInventoryRightRecordCenterList(query);
        if (CollectionUtils.isNotEmpty(list)) {
            SurveyAssetInventoryRightRecordCenter center = list.get(0);
            if (CollectionUtils.isNotEmpty(rightRecordList)) {
                for (SurveyAssetInventoryRightRecord oo : rightRecordList) {
                    oo.setInventoryRightRecordCenterId(center.getId());
                    surveyAssetInventoryRightRecordService.updateSurveyAssetInventoryRightRecord(oo);
                }
            }
        } else {
            this.saveAndUpdate(query);
            if (CollectionUtils.isNotEmpty(rightRecordList)) {
                for (SurveyAssetInventoryRightRecord oo : rightRecordList) {
                    oo.setInventoryRightRecordCenterId(query.getId());
                    surveyAssetInventoryRightRecordService.updateSurveyAssetInventoryRightRecord(oo);
                }
            }
        }
    }

    public void saveAndUpdate(SurveyAssetInventoryRightRecordCenter surveyAssetInventoryRightRecordCenter) throws Exception {
        if (surveyAssetInventoryRightRecordCenter == null) {
            return;
        }
        if (surveyAssetInventoryRightRecordCenter.getId() == null) {
            surveyAssetInventoryRightRecordCenter.setCreator(commonService.thisUserAccount());
            surveyAssetInventoryRightRecordCenterDao.addSurveyAssetInventoryRightRecordCenter(surveyAssetInventoryRightRecordCenter);
        } else {
            surveyAssetInventoryRightRecordCenterDao.updateSurveyAssetInventoryRightRecordCenter(surveyAssetInventoryRightRecordCenter);
        }
    }

    public SurveyAssetInventoryRightRecordCenter getSurveyAssetInventoryRightRecordCenterById(Integer id) throws Exception {
        if (id == null) {
            return null;
        }
        return surveyAssetInventoryRightRecordCenterDao.getSurveyAssetInventoryRightRecordCenterById(id);
    }

    public List<SurveyAssetInventoryRightRecordCenter> getSurveyAssetInventoryRightRecordCenterList(SurveyAssetInventoryRightRecordCenter query) {
        if (query == null) {
            return new ArrayList<SurveyAssetInventoryRightRecordCenter>(0);
        }
        return surveyAssetInventoryRightRecordCenterDao.surveyAssetInventoryRightRecordCenterList(query);
    }
}
