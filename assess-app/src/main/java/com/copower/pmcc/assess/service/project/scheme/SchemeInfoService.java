package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.SchemeInfoDetailEnum;
import com.copower.pmcc.assess.dal.dao.project.scheme.SchemeInfoDao;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.entity.SchemeInfo;
import com.copower.pmcc.assess.dal.entity.SchemeInfoDetail;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeInfoDetailVDto;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
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
     *
     * @param detailVDto
     */
    @Transactional
    public void saveChange(SchemeInfoDetailVDto detailVDto) {
        SchemeInfo schemeInfo = new SchemeInfo();
        if (StringUtils.isEmpty(detailVDto.getProcessInsId())  || StringUtils.isEmpty(detailVDto.getProjectID())){
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(detailVDto.getPlanDetailsId());
            detailVDto.setProcessInsId(projectPlanDetails.getProcessInsId());
            detailVDto.setProjectID(projectPlanDetails.getProjectId()+"");
        }
        schemeInfo.setCreator(commonService.thisUserAccount());
        schemeInfo.setProcessInsId(detailVDto.getProcessInsId());
        schemeInfo.setPlanDetailsId(detailVDto.getPlanDetailsId());
        schemeInfo.setProjectId(Integer.parseInt(detailVDto.getProjectID()));
        int id = addReturnID(schemeInfo);
        save(detailVDto.getBasisContent(), detailVDto.getBasisDataID(), SchemeInfoDetailEnum.BASIS.getDataType(), id);
        save(detailVDto.getHypothesisContent(), detailVDto.getHypothesisDataID(), SchemeInfoDetailEnum.HYPOTHESIS.getDataType(), id);
        save(detailVDto.getPrinciPleContent(), detailVDto.getPrinciPleDataID(), SchemeInfoDetailEnum.PRINCIPLE.getDataType(), id);
    }

    /**
     * dataType 类型 0假设 1原则 2依据 3利用描述 4价值时点 使用枚举传入
     *
     * @param content
     * @param dataID
     * @param dataType
     * @param schemeInfoID
     */
    @Transactional
    public void save(String content, String dataID, Integer dataType, Integer schemeInfoID) {
        String[] contentS = content.split("<<");//这里最好是页面强制适用一种特定符号结尾如 <<
        String[] dataIDS = dataID.split(",");
        for (int i = 0; i < dataIDS.length; i++) {
            SchemeInfoDetail schemeInfoDetail = new SchemeInfoDetail();
            schemeInfoDetail.setCreator(commonService.thisUserAccount());
            schemeInfoDetail.setContent(contentS[i]);
            if (!StringUtils.isEmpty(dataIDS[i])) schemeInfoDetail.setDataId(Integer.parseInt(dataIDS[i]));
            schemeInfoDetail.setDataType(dataType);
            schemeInfoDetail.setSchemeInfoId(schemeInfoID);
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

    public SchemeInfoDetailVDto formDataDto(String formData) {
        SchemeInfoDetailVDto detailVDto = null;
        if (!StringUtils.isEmpty(formData)) {
            detailVDto = JSONObject.parseObject(formData, SchemeInfoDetailVDto.class);
        }
        return detailVDto;
    }
}