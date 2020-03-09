package com.copower.pmcc.assess.service;

import com.copower.pmcc.ad.api.dto.AdBaseDataDicDto;
import com.copower.pmcc.ad.api.dto.AdCompanyQualificationDto;
import com.copower.pmcc.ad.api.dto.AdPersonalQualificationDto;
import com.copower.pmcc.ad.api.provider.AdRpcQualificationsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/1/23 10:41
 * @Description:资格相关
 */
@Service
public class AdRpcQualificationsAppService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AdRpcQualificationsService adRpcQualificationsService;

    ///公司资质
    //根据公司编号取得公司执业资质 3
    public AdCompanyQualificationDto getCompanyQualificationForPractising(Integer companyId) {
        AdCompanyQualificationDto companyQualificationDto = adRpcQualificationsService.getCompanyQualificationForPractising(companyId);
        return companyQualificationDto;
    }

    //根据公司编号取得公司营业执照
    public AdCompanyQualificationDto getCompanyQualificationForLicense(Integer companyId) {
        return adRpcQualificationsService.getCompanyQualificationForLicense(companyId);
    }

    ///个人资质
    //学历
    public List<AdPersonalQualificationDto> getAdPersonalEducationDto(String userAccount) throws Exception {
        return adRpcQualificationsService.getAdPersonalEducationDto(userAccount);
    }

    //身份证
    public List<AdPersonalQualificationDto> getAdPersonalIdentityDto(String userAccount) throws Exception {
        return adRpcQualificationsService.getAdPersonalIdentityDto(userAccount);
    }

    //资格
    public List<AdPersonalQualificationDto> getAdPersonalQualificationDto(String userAccount, String qualificationType) throws Exception {
        if (StringUtils.isNotBlank(userAccount) && StringUtils.isNotBlank(qualificationType)) {
            return adRpcQualificationsService.getAdPersonalQualificationDto(userAccount, qualificationType);
        }
        if (StringUtils.isNotBlank(qualificationType) && StringUtils.isEmpty(userAccount)){
            return adRpcQualificationsService.getAdPersonalQualificationDto(qualificationType);
        }
        return new ArrayList<>(0) ;
    }

    //获取职称列表
    public List<AdBaseDataDicDto> getAdBaseDataDicForTitle() {
        return adRpcQualificationsService.getAdBaseDataDicForTitle();
    }

    //获取资格列表.....
    public List<AdBaseDataDicDto> getAdBaseDataDicForQualification() {
        return adRpcQualificationsService.getAdBaseDataDicForQualification();
    }

    //根据PID取得下层资格信息
    public List<AdBaseDataDicDto> getAdQualificationListByPid(Integer pid) {
        return adRpcQualificationsService.getAdQualificationListByPid(pid);
    }
}
