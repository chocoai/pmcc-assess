package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.SchemeInfoDetailEnum;
import com.copower.pmcc.assess.dal.dao.SchemeInfoDao;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.entity.SchemeInfo;
import com.copower.pmcc.assess.dal.entity.SchemeInfoDetail;
import com.copower.pmcc.assess.dto.input.project.SchemeInfoFormDataDto;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 方案主表
 * Created by 13426 on 2018/5/24.
 */
@Service
public class SchemeInfoService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SchemeInfoDetailService schemeInfoDetailService;

    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SchemeInfoDao dao;

    /**
     * 这里的分隔符必须和页面相对应
     * @param planDetailsId
     * @param processInsId
     * @param princiPle
     * @param hypothesis
     * @param basis
     */
    public void saveChange(Integer planDetailsId, String processInsId, String princiPle, String hypothesis, String basis) {
        String[] princiPles = princiPle.split(".");
        String[] hypothesisS = hypothesis.split(".");
        String[] basisS = basis.split(".");
        for (String s:princiPles){
            String[] strs = s.split(",");
            String content = strs[0];
            String dataID = strs[1];
            save(planDetailsId,processInsId,content,dataID,SchemeInfoDetailEnum.HYPOTHESIS.getDataType());
        }
        for (String s:hypothesisS){
            String[] strs = s.split(",");
            String content = strs[0];
            String dataID = strs[1];
            save(planDetailsId,processInsId,content,dataID,SchemeInfoDetailEnum.HYPOTHESIS.getDataType());
        }
        for (String s:basisS){
            String[] strs = s.split(",");
            String content = strs[0];
            String dataID = strs[1];
            save(planDetailsId,processInsId,content,dataID,SchemeInfoDetailEnum.HYPOTHESIS.getDataType());
        }
    }

    /**
     * dataType 类型 0假设 1原则 2依据 3利用描述 4价值时点 使用枚举传入
     *
     * @param planDetailsId
     * @param content
     * @param dataID
     * @param processInsId
     * @param dataType
     */
    @Transactional
    public void save(Integer planDetailsId, String processInsId,String content, String dataID, Integer dataType) {
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
        SchemeInfo schemeInfo = new SchemeInfo();
        schemeInfo.setCreator(commonService.thisUserAccount());
        schemeInfo.setProcessInsId(processInsId);
        schemeInfo.setProjectId(projectPlanDetails.getProjectId());
        schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
        int id = addReturnID(schemeInfo);
        String[] contentS = content.split(",");//这里最好是页面强制适用一种特定符号结尾如$
        String[] dataIDS = dataID.split(",");
        for (int i = 0; i < dataIDS.length; i++) {
            SchemeInfoDetail schemeInfoDetail = new SchemeInfoDetail();
            schemeInfoDetail.setCreator(commonService.thisUserAccount());
            schemeInfoDetail.setContent(contentS[i]);
            if (!StringUtils.isEmpty(dataIDS[i])) schemeInfoDetail.setDataId(Integer.parseInt(dataIDS[i]));
            schemeInfoDetail.setDataType(dataType);
            schemeInfoDetail.setSchemeInfoId(id);
            schemeInfoDetailService.addReturnID(schemeInfoDetail);
        }
    }

    public int addReturnID(SchemeInfo schemeInfo) {
        return dao.addReturnID(schemeInfo);
    }

    @Transactional
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    @Transactional
    public boolean update(SchemeInfo schemeInfo) {
        return dao.update(schemeInfo);
    }

    public SchemeInfoFormDataDto formDataDto(String formData) {
        SchemeInfoFormDataDto schemeInfoFormDataDto = null;
        if (!StringUtils.isEmpty(formData)) {
            schemeInfoFormDataDto = JSONObject.parseObject(formData, SchemeInfoFormDataDto.class);
        }
        return schemeInfoFormDataDto;
    }
}