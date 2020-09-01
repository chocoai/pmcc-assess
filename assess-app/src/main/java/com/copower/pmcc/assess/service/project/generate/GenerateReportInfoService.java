package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.ad.api.enums.AdPersonalEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.generate.GenerateReportInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportInfo;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dto.output.project.generate.GenerateReportInfoVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    private PublicService publicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;


    public GenerateReportInfo getGenerateReportInfoByAreaGroupId(Integer areaGroupId, Integer projectPlanId) {
        return generateReportInfoDao.getGenerateReportInfoByAreaGroupId(areaGroupId, projectPlanId);
    }

    public GenerateReportInfo getGenerateReportInfoByAreaId(Integer areaGroupId) {
        GenerateReportInfo where = new GenerateReportInfo();
        where.setAreaGroupId(areaGroupId);
        return generateReportInfoDao.getGenerateReportInfo(where);
    }

    public void saveGenerateReportInfo(GenerateReportInfo generateReportInfo) {
        if (generateReportInfo == null) return;
        if (generateReportInfo.getId() != null && generateReportInfo.getId() > 0) {
            updateGenerateReportInfo(generateReportInfo);
        } else {
            addGenerateReportInfo(generateReportInfo);
        }
    }

    public boolean updateGenerateReportInfo(GenerateReportInfo generateReportInfo) {
        return generateReportInfoDao.updateGenerateReportInfo(generateReportInfo);
    }

    public boolean addGenerateReportInfo(GenerateReportInfo generateReportInfo) {
        return generateReportInfoDao.addGenerateReportInfo(generateReportInfo);
    }

    /**
     * 获取生成的数据列表
     *
     * @param projectId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void initGenerateReportInfo(Integer projectId, Integer planId) {
        GenerateReportInfo where = new GenerateReportInfo();
        where.setProjectId(projectId);
        List<GenerateReportInfo> generationList = generateReportGenerationList(where);
        List<SchemeAreaGroup> areaGroupList = schemeAreaGroupService.getAreaGroupEnableByProjectId(projectId);
        if (CollectionUtils.isEmpty(areaGroupList)) return;
        Iterator<SchemeAreaGroup> areaGroupIterator = areaGroupList.iterator();
        while (areaGroupIterator.hasNext()) {
            SchemeAreaGroup areaGroup = (SchemeAreaGroup) areaGroupIterator.next();
            if (CollectionUtils.isNotEmpty(generationList)) {
                Iterator<GenerateReportInfo> reportInfoIterator = generationList.iterator();
                while (reportInfoIterator.hasNext()) {
                    if (reportInfoIterator.next().getAreaGroupId().equals(areaGroup.getId())) {
                        reportInfoIterator.remove();
                        areaGroupIterator.remove();
                    }
                }
            }
        }
        if (CollectionUtils.isNotEmpty(generationList)) {
            generationList.forEach(o -> deleteGenerateReportInfo(o.getId()));
        }
        if (CollectionUtils.isNotEmpty(areaGroupList)) {
            for (SchemeAreaGroup schemeAreaGroup : areaGroupList) {
                GenerateReportInfo generateReportInfo = new GenerateReportInfo();
                generateReportInfo.setProjectId(projectId);
                generateReportInfo.setProjectPlanId(planId);
                generateReportInfo.setAreaGroupId(schemeAreaGroup.getId());
                generateReportInfo.setInvestigationsStartDate(new Date());
                generateReportInfo.setInvestigationsEndDate(new Date());
                generateReportInfo.setReportIssuanceDate(new Date());
                generateReportInfo.setHomeWorkEndTime(new Date());
                generateReportInfo.setCreator(commonService.thisUserAccount());
                addGenerateReportInfo(generateReportInfo);
                generationList.add(generateReportInfo);
            }
        }
    }


    public boolean deleteGenerateReportInfo(Integer id) {
        return generateReportInfoDao.deleteGenerateReportInfo(id);
    }

    public void deleteGenerateReportInfoByProjectId(Integer projectId) {
        List<GenerateReportInfo> reportInfoList = generateReportInfoDao.getGenerateReportInfoList(projectId);
        if (CollectionUtils.isEmpty(reportInfoList)) return;
        reportInfoList.forEach(o -> deleteGenerateReportInfo(o.getId()));
    }

    public GenerateReportInfo getByGenerateReportInfoId(Integer id) {
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
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(generateReportGeneration.getAreaGroupId());
        if (schemeAreaGroup != null)
            vo.setAreaGroupName(schemeAreaGroup.getAreaName());
        //取资质名称
        if (StringUtils.isNotBlank(generateReportGeneration.getQualificationType())) {
            AdPersonalEnum adPersonalEnum = AdPersonalEnum.create(generateReportGeneration.getQualificationType());
            if (adPersonalEnum != null)
                vo.setQualificationTypeName(adPersonalEnum.getName());
        }
        //取估价师名称
        if (StringUtils.isNotBlank(generateReportGeneration.getRealEstateAppraiser())) {
            List<String> stringList = FormatUtils.transformString2List(generateReportGeneration.getRealEstateAppraiser());
            if (!CollectionUtils.isEmpty(stringList)) {
                String userName = publicService.getUserNameByAccountList(stringList);
                vo.setRealEstateAppraiserName(userName);
            }
        }
        if (StringUtils.isNotBlank(generateReportGeneration.getReportType())) {
            List<String> stringList = FormatUtils.transformString2List(generateReportGeneration.getReportType());
            List<String> strings = new ArrayList<>(stringList.size());
            for (String s : stringList) {
                strings.add(baseDataDicService.getNameById(s));
            }
            vo.setReportTypeName(StringUtils.join(strings, ","));
        }
        return vo;
    }

    public static Map<String, String> getQualificationTypes() {
        Map<String, String> qualificationTypes = new HashMap<>();
        qualificationTypes.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCTDGJS.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCTDGJS.getName());
        qualificationTypes.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCZCGJS.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCZCGJS.getName());
        qualificationTypes.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_SFJDR.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_SFJDR.getName());
        qualificationTypes.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getName());
        return qualificationTypes;
    }
}
