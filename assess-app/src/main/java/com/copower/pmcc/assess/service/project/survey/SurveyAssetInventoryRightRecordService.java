package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryRightRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRight;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightRecord;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryRightRecordVo;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zch
 * @date: 2019/3/18 17:29
 * @description:他项权力申报类 (他项权利主类 > 他项权力申报类 > 他项权力普通类)
 */
@Service
public class SurveyAssetInventoryRightRecordService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveyAssetInventoryRightRecordDao surveyAssetInventoryRightRecordDao;
    @Autowired
    private SurveyAssetInventoryRightService surveyAssetInventoryRightService;
    @Autowired
    private DeclareRecordService declareRecordService;

    @Autowired
    private CommonService commonService;

    public void clear(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord) {
        surveyAssetInventoryRightRecord.setCreator(commonService.thisUserAccount());
        List<SurveyAssetInventoryRightRecord> surveyAssetInventoryRightRecordList = this.surveyAssetInventoryRightRecordList(surveyAssetInventoryRightRecord);
        if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightRecordList)) {
            for (SurveyAssetInventoryRightRecord inventoryRightRecord : surveyAssetInventoryRightRecordList) {
                SurveyAssetInventoryRight select = new SurveyAssetInventoryRight();
                select.setInventoryRightRecordId(inventoryRightRecord.getId());
                surveyAssetInventoryRightService.removeSurveyAssetInventoryRight(select);
                try {
                    deleteSurveyAssetInventoryRightRecordById(inventoryRightRecord.getId());
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        }
    }

    /**
     * 利用申报id获取他项权力组信息
     *
     * @param declareRecordId
     * @param projectId
     * @return
     */
    public List<SurveyAssetInventoryRightRecord> getSurveyAssetInventoryRightRecordByDeclareRecord(Integer declareRecordId, Integer projectId) {
        List<SurveyAssetInventoryRightRecord> surveyAssetInventoryRightRecordList = Lists.newArrayList();
        String value = declareRecordId.toString();
        SurveyAssetInventoryRightRecord query = new SurveyAssetInventoryRightRecord();
        query.setProjectId(projectId);
        List<SurveyAssetInventoryRightRecord> rightRecordList = this.surveyAssetInventoryRightRecordList(query);
        if (CollectionUtils.isNotEmpty(rightRecordList)) {
            rightRecordList.stream().forEach(surveyAssetInventoryRightRecord -> {
                if (StringUtils.isNotBlank(value)) {
                    if (StringUtils.isNotBlank(surveyAssetInventoryRightRecord.getRecordIds())) {
                        List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(surveyAssetInventoryRightRecord.getRecordIds()));
                        if (integers.contains(declareRecordId)) {
                            surveyAssetInventoryRightRecordList.add(surveyAssetInventoryRightRecord);
                        }
                    }
                }
            });
        }
        return surveyAssetInventoryRightRecordList;
    }


    public boolean addSurveyAssetInventoryRightRecord(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord) throws Exception {
        surveyAssetInventoryRightRecord.setCreator(commonService.thisUserAccount());
        return surveyAssetInventoryRightRecordDao.addSurveyAssetInventoryRightRecord(surveyAssetInventoryRightRecord);
    }

    public boolean updateSurveyAssetInventoryRightRecord(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord) throws Exception {
        SurveyAssetInventoryRight select = new SurveyAssetInventoryRight();
        select.setInventoryRightRecordId(surveyAssetInventoryRightRecord.getId());
        List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightService.getSurveyAssetInventoryRightList(select);
        if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightList)) {
            for (SurveyAssetInventoryRight oo : surveyAssetInventoryRightList) {
                oo.setInventoryRightRecordId(surveyAssetInventoryRightRecord.getId());
                oo.setPlanDetailsId(surveyAssetInventoryRightRecord.getPlanDetailsId());
                oo.setProjectId(surveyAssetInventoryRightRecord.getProjectId());
                surveyAssetInventoryRightService.save(oo);
            }
        }
        return surveyAssetInventoryRightRecordDao.updateSurveyAssetInventoryRightRecord(surveyAssetInventoryRightRecord);
    }

    public boolean deleteSurveyAssetInventoryRightRecordById(Integer id) throws Exception {
        return surveyAssetInventoryRightRecordDao.deleteSurveyAssetInventoryRightRecordById(id);
    }

    public SurveyAssetInventoryRightRecord getSurveyAssetInventoryRightRecordById(Integer id) throws Exception {
        return surveyAssetInventoryRightRecordDao.getSurveyAssetInventoryRightRecordById(id);
    }

    public List<SurveyAssetInventoryRightRecord> surveyAssetInventoryRightRecordList(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord) {
        return surveyAssetInventoryRightRecordDao.surveyAssetInventoryRightRecordList(surveyAssetInventoryRightRecord);
    }

    public SurveyAssetInventoryRightRecordVo getSurveyAssetInventoryRightRecordVo(SurveyAssetInventoryRightRecord oo) {
        SurveyAssetInventoryRightRecordVo vo = new SurveyAssetInventoryRightRecordVo();
        if (oo == null) {
            return null;
        }
        org.springframework.beans.BeanUtils.copyProperties(oo, vo);
        List<DeclareRecord> declareRecordList = Lists.newArrayList();
        if (StringUtils.isNotBlank(oo.getRecordIds())) {
            String[] strings = oo.getRecordIds().split(",");
            for (String s : strings) {
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(Integer.parseInt(s));
                if (declareRecord != null) {
                    declareRecordList.add(declareRecord);
                }
            }
        }
        if (CollectionUtils.isNotEmpty(declareRecordList)) {
            StringBuilder stringBuilder = new StringBuilder(8);
            for (int i = 0; i < declareRecordList.size(); i++) {
                stringBuilder.append(declareRecordList.get(i).getName());
                if (i != declareRecordList.size() - 1) {
                    if (declareRecordList.size() != 1) {
                        stringBuilder.append(",");
                    }
                }
            }
            vo.setRecordNames(stringBuilder.toString());
        }
        return vo;
    }

}
