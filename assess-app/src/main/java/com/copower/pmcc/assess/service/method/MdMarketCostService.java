package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.constant.AssessMarketCostConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostBuildingDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostConstructionDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.input.method.ConstructionInstallationEngineeringDto;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Random;

/**
 * @Auther: zch
 * @Date: 2018/8/9 10:43
 * @Description:成本法
 */
@Service
public class MdMarketCostService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MdCostBuildingDao mdCostBuildingDao;
    @Autowired
    private MdCostConstructionDao mdCostConstructionDao;
    @Autowired
    private MdCostDao mdCostDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    public BootstrapTableVo getBaseDicTree() {
        List<ConstructionInstallationEngineeringDto> installationEngineeringDtos = Lists.newArrayList();
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessMarketCostConstant.BUILD_SECURITY_ENGINEERING_PROJECT);
        ConstructionInstallationEngineeringDto installationEngineeringDto = new ConstructionInstallationEngineeringDto();
        BeanUtils.copyProperties(baseDataDic,installationEngineeringDto);
        installationEngineeringDto.setNumber("0");
        installationEngineeringDtos.add(installationEngineeringDto);
        int i = 0;
        changeConstructionInstallationEngineeringDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.BUILD_SECURITY_ENGINEERING_PROJECT), installationEngineeringDtos,AssessMarketCostConstant.BUILD_SECURITY_ENGINEERING_PROJECT,i);

        changeConstructionInstallationEngineeringDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.SOIL_ENGINEERING_PROJECT), installationEngineeringDtos,AssessMarketCostConstant.SOIL_ENGINEERING_PROJECT,++i);
        changeConstructionInstallationEngineeringDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.ERECT_ENGINEERING_PROJECT), installationEngineeringDtos,AssessMarketCostConstant.ERECT_ENGINEERING_PROJECT,++i);
        changeConstructionInstallationEngineeringDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.DECORATE_ENGINEERING_PROJECT), installationEngineeringDtos,AssessMarketCostConstant.DECORATE_ENGINEERING_PROJECT,++i);
        changeConstructionInstallationEngineeringDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.SUBSIDIARY_ENGINEERING_PROJECT), installationEngineeringDtos,AssessMarketCostConstant.SUBSIDIARY_ENGINEERING_PROJECT,++i);
        changeConstructionInstallationEngineeringDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.TWOLOADING_ENGINEERING_PROJECT), installationEngineeringDtos,AssessMarketCostConstant.TWOLOADING_ENGINEERING_PROJECT,++i);
        BootstrapTableVo vo = new BootstrapTableVo();
        vo.setTotal(Integer.toUnsignedLong(installationEngineeringDtos.size()));
        vo.setRows(installationEngineeringDtos);
        return vo;
    }

    private void changeConstructionInstallationEngineeringDto(List<BaseDataDic> baseDataDics, List<ConstructionInstallationEngineeringDto> ztreeDtos,String key,int i) {
        Random random = new Random(System.currentTimeMillis());
        int v = 1;
        if (!ObjectUtils.isEmpty(baseDataDics)) {
            for (BaseDataDic baseDataDic : baseDataDics) {
                ConstructionInstallationEngineeringDto engineeringDto = new ConstructionInstallationEngineeringDto();
                BeanUtils.copyProperties(baseDataDic, engineeringDto);
                BaseDataDic dic = baseDataDicService.getCacheDataDicByFieldName(key);
                engineeringDto.set_parentId(dic.getId());
                if (i!=0){
                    engineeringDto.setNumber(String.format("%d-%d",i,v++));
                }else {
                    engineeringDto.setNumber(String.valueOf(v++));
                }
                engineeringDto.setArea(random.nextInt(100));
                ztreeDtos.add(engineeringDto);
            }
        }
    }
}
