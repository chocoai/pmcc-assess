package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.ad.api.enums.AdPersonalEnum;
import com.copower.pmcc.assess.dal.basis.dao.data.DataQualificationDao;
import com.copower.pmcc.assess.dal.basis.dao.project.generate.GenerateReportInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.DataQualification;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportInfo;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dto.output.project.generate.GenerateReportInfoVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/1/28 10:53
 * @Description:报告生成
 */
@Service
public class GenerateReportInfoService {
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private GenerateReportInfoDao generateReportInfoDao;
    @Autowired
    private DataQualificationDao dataQualificationDao;
    @Autowired
    private PublicService publicService;

    public GenerateReportInfo getGenerateReportInfoByAreaGroupId(Integer areaGroupId, Integer projectPlanId) throws Exception {
        return generateReportInfoDao.getGenerateReportInfoByAreaGroupId(areaGroupId, projectPlanId);
    }

    public GenerateReportInfo getGenerateReportInfoByProcessInsId(String processInsId) throws Exception {
        GenerateReportInfo generateReportGeneration = new GenerateReportInfo();
        generateReportGeneration.setProcessInsId(processInsId);
        return generateReportInfoDao.getGenerateReportInfo(generateReportGeneration);
    }

    public boolean updateGenerateReportInfo(GenerateReportInfo generateReportGeneration) throws Exception {
        return generateReportInfoDao.updateGenerateReportInfo(generateReportGeneration);
    }

    public boolean addGenerateReportInfo(GenerateReportInfo generateReportGeneration) throws Exception {
        return generateReportInfoDao.addGenerateReportInfo(generateReportGeneration);
    }

    public boolean deleteGenerateReportInfo(Integer id) throws Exception {
        return generateReportInfoDao.deleteGenerateReportInfo(id);
    }

    public GenerateReportInfo getByGenerateReportInfoId(Integer id) throws Exception {
        return generateReportInfoDao.getByGenerateReportInfoId(id);
    }

    public GenerateReportInfo getGenerateReportInfo(GenerateReportInfo generateReportGeneration) {
        return generateReportInfoDao.getGenerateReportInfo(generateReportGeneration);
    }

    public List<GenerateReportInfo> generateReportGenerationList(GenerateReportInfo generateReportGeneration) {
        return generateReportInfoDao.generateReportGenerationList(generateReportGeneration);
    }

    public GenerateReportInfoVo getGenerateReportInfoVo(GenerateReportInfo generateReportGeneration) {
        if (generateReportGeneration == null) return null;
        GenerateReportInfoVo vo = new GenerateReportInfoVo();
        BeanUtils.copyProperties(generateReportGeneration, vo);
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(generateReportGeneration.getAreaGroupId());
        if (schemeAreaGroup != null)
            vo.setAreaGroupName(schemeAreaGroup.getAreaName());
        //取资质名称
        if(StringUtils.isNotBlank(generateReportGeneration.getQualificationType())){
            AdPersonalEnum adPersonalEnum = AdPersonalEnum.create(generateReportGeneration.getQualificationType());
            if(adPersonalEnum!=null)
                vo.setQualificationTypeName(adPersonalEnum.getName());
        }
        //取估价师名称
        if(StringUtils.isNotBlank(generateReportGeneration.getRealEstateAppraiser())){
            List<Integer> list = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(generateReportGeneration.getRealEstateAppraiser()));
            List<DataQualification> dataQualifications = dataQualificationDao.getDataQualifications(list);
            if(!CollectionUtils.isEmpty(dataQualifications)){
                String userName = publicService.getUserNameByAccountList(LangUtils.transform(dataQualifications, o -> o.getUserAccount()));
                vo.setRealEstateAppraiserName(userName);
            }
        }
        return vo;
    }
}
