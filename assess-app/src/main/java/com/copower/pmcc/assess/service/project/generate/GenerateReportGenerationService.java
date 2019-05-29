package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.ad.api.enums.AdPersonalEnum;
import com.copower.pmcc.assess.dal.basis.dao.data.DataQualificationDao;
import com.copower.pmcc.assess.dal.basis.dao.project.generate.GenerateReportGenerationDao;
import com.copower.pmcc.assess.dal.basis.entity.DataQualification;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportGeneration;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dto.output.project.generate.GenerateReportGenerationVo;
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
public class GenerateReportGenerationService {
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private GenerateReportGenerationDao generateReportGenerationDao;
    @Autowired
    private DataQualificationDao dataQualificationDao;
    @Autowired
    private PublicService publicService;

    public GenerateReportGeneration getGenerateReportGenerationByAreaGroupId(Integer areaGroupId, Integer projectPlanId) throws Exception {
        return generateReportGenerationDao.getGenerateReportGenerationByAreaGroupId(areaGroupId, projectPlanId);
    }

    public GenerateReportGeneration getGenerateReportGenerationByProcessInsId(String processInsId) throws Exception {
        GenerateReportGeneration generateReportGeneration = new GenerateReportGeneration();
        generateReportGeneration.setProcessInsId(processInsId);
        return generateReportGenerationDao.getGenerateReportGeneration(generateReportGeneration);
    }

    public boolean updateGenerateReportGeneration(GenerateReportGeneration generateReportGeneration) throws Exception {
        return generateReportGenerationDao.updateGenerateReportGeneration(generateReportGeneration);
    }

    public boolean addGenerateReportGeneration(GenerateReportGeneration generateReportGeneration) throws Exception {
        return generateReportGenerationDao.addGenerateReportGeneration(generateReportGeneration);
    }

    public boolean deleteGenerateReportGeneration(Integer id) throws Exception {
        return generateReportGenerationDao.deleteGenerateReportGeneration(id);
    }

    public GenerateReportGeneration getByGenerateReportGenerationId(Integer id) throws Exception {
        return generateReportGenerationDao.getByGenerateReportGenerationId(id);
    }

    public GenerateReportGeneration getGenerateReportGeneration(GenerateReportGeneration generateReportGeneration) {
        return generateReportGenerationDao.getGenerateReportGeneration(generateReportGeneration);
    }

    public List<GenerateReportGeneration> generateReportGenerationList(GenerateReportGeneration generateReportGeneration) {
        return generateReportGenerationDao.generateReportGenerationList(generateReportGeneration);
    }

    public GenerateReportGenerationVo getGenerateReportGenerationVo(GenerateReportGeneration generateReportGeneration) {
        if (generateReportGeneration == null) return null;
        GenerateReportGenerationVo vo = new GenerateReportGenerationVo();
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
