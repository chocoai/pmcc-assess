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

    public void saveChange(SchemeInfoFormDataDto p,SchemeInfoFormDataDto h,SchemeInfoFormDataDto b) {
        logger.info(p+"");

    }

    /**
     * 这里的分隔符必须和页面相对应
     *
     * @param planDetailsId
     * @param processInsId
     * @param formData
     */
    public void saveChange(Integer planDetailsId, String processInsId, String formData) {
        if (!StringUtils.isEmpty(processInsId) && !StringUtils.isEmpty(formData)) {
            String[] strings = formData.split(".");
            for (String s : strings) {
                String[] ss = s.split(",");
                save(planDetailsId, ss[0], ss[1], processInsId, SchemeInfoDetailEnum.HYPOTHESIS.getDataType());
                save(planDetailsId, ss[0], ss[1], processInsId, SchemeInfoDetailEnum.PRINCIPLE.getDataType());
                save(planDetailsId, ss[0], ss[1], processInsId, SchemeInfoDetailEnum.Basis.getDataType());
            }
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
    private void save(Integer planDetailsId, String content, String dataID, String processInsId, Integer dataType) {
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
        SchemeInfo schemeInfo = new SchemeInfo();
        schemeInfo.setCreator(commonService.thisUserAccount());
        schemeInfo.setProcessInsId(processInsId);
        schemeInfo.setProjectId(projectPlanDetails.getProjectId());
        schemeInfo.setPlanDetailsId(projectPlanDetails.getId());
        int id = addReturnID(schemeInfo);
        String[] contentS = content.split(",");
        String[] dataIDS = dataID.split(",");
        for (int i = 0; i < contentS.length; i++) {
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