package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.constant.AssessMarketCostConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostBuildingDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostConstructionDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.InfrastructureCost;
import com.copower.pmcc.assess.dal.basis.entity.InfrastructureMatchingCost;
import com.copower.pmcc.assess.dto.input.method.ConstructionInstallationEngineeringDto;
import com.copower.pmcc.assess.dto.output.data.DataBuildingNewRateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
import com.copower.pmcc.assess.service.data.DataInfrastructureCostService;
import com.copower.pmcc.assess.service.data.DataInfrastructureMatchingCostService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    @Autowired
    private DataInfrastructureCostService dataInfrastructureCostService;
    @Autowired
    private DataInfrastructureMatchingCostService dataInfrastructureMatchingCostService;
    @Autowired
    private DataBuildingNewRateService dataBuildingNewRateService;

    public List<DataBuildingNewRateVo> dataBuildingNewRateList(){
        return dataBuildingNewRateService.dataBuildingNewRateList();
    }

    public List<InfrastructureCost> infrastructureCostList() {
        List<InfrastructureCost> infrastructureCosts = dataInfrastructureCostService.infrastructureCostList();
        Ordering<InfrastructureCost> firstOrdering = Ordering.from(new Comparator<InfrastructureCost>() {
            @Override
            public int compare(InfrastructureCost o1, InfrastructureCost o2) {
                return o1.getGmtCreated().compareTo(o2.getGmtCreated());
            }
        }).reverse();//排序 并且反转
        Collections.sort(infrastructureCosts, firstOrdering);
        List<InfrastructureCost> costList = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(infrastructureCosts)) {
            costList.add(infrastructureCosts.get(0));
        }
        return costList;
    }

    public List<InfrastructureMatchingCost> infrastructureMatchingCosts() {
        List<InfrastructureMatchingCost> infrastructureMatchingCosts = dataInfrastructureMatchingCostService.infrastructureMatchingCosts();
        Ordering<InfrastructureMatchingCost> firstOrdering = Ordering.from(new Comparator<InfrastructureMatchingCost>() {
            @Override
            public int compare(InfrastructureMatchingCost o1, InfrastructureMatchingCost o2) {
                return o1.getGmtCreated().compareTo(o2.getGmtCreated());
            }
        }).reverse();//排序 并且反转
        Collections.sort(infrastructureMatchingCosts, firstOrdering);
        List<InfrastructureMatchingCost> costList = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(infrastructureMatchingCosts)) {
            costList.add(infrastructureMatchingCosts.get(0));
        }
        return costList;
    }

    public List<BaseDataDic> getAddedValueAdditionalTaxRate() {
        return baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.BUILD_ADDEDVALUEADDITIONALTAXRATE);
    }

    public BootstrapTableVo getBaseDicTree() {
        List<ConstructionInstallationEngineeringDto> installationEngineeringDtos = Lists.newArrayList();
//        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessMarketCostConstant.BUILD_SECURITY_ENGINEERING_PROJECT);
//        ConstructionInstallationEngineeringDto installationEngineeringDto = new ConstructionInstallationEngineeringDto();
//        BeanUtils.copyProperties(baseDataDic,installationEngineeringDto);
//        installationEngineeringDto.setNumber("0");
//        installationEngineeringDtos.add(installationEngineeringDto);
        int i = 0;
        changeConstructionInstallationEngineeringDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.BUILD_SECURITY_ENGINEERING_PROJECT), installationEngineeringDtos, null, i);

        changeConstructionInstallationEngineeringDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.SOIL_ENGINEERING_PROJECT), installationEngineeringDtos, AssessMarketCostConstant.SOIL_ENGINEERING_PROJECT, ++i);
        changeConstructionInstallationEngineeringDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.ERECT_ENGINEERING_PROJECT), installationEngineeringDtos, AssessMarketCostConstant.ERECT_ENGINEERING_PROJECT, ++i);
        changeConstructionInstallationEngineeringDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.DECORATE_ENGINEERING_PROJECT), installationEngineeringDtos, AssessMarketCostConstant.DECORATE_ENGINEERING_PROJECT, ++i);
        changeConstructionInstallationEngineeringDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.SUBSIDIARY_ENGINEERING_PROJECT), installationEngineeringDtos, AssessMarketCostConstant.SUBSIDIARY_ENGINEERING_PROJECT, ++i);
        changeConstructionInstallationEngineeringDto(baseDataDicService.getCacheDataDicList(AssessMarketCostConstant.TWOLOADING_ENGINEERING_PROJECT), installationEngineeringDtos, AssessMarketCostConstant.TWOLOADING_ENGINEERING_PROJECT, ++i);
        BootstrapTableVo vo = new BootstrapTableVo();
        vo.setTotal(Integer.toUnsignedLong(installationEngineeringDtos.size()));
        vo.setRows(installationEngineeringDtos);
        return vo;
    }

    private void changeConstructionInstallationEngineeringDto(List<BaseDataDic> baseDataDics, List<ConstructionInstallationEngineeringDto> ztreeDtos, String key, int i) {
        int v = 1;
        if (!ObjectUtils.isEmpty(baseDataDics)) {
            for (BaseDataDic baseDataDic : baseDataDics) {
                ConstructionInstallationEngineeringDto engineeringDto = new ConstructionInstallationEngineeringDto();
                BeanUtils.copyProperties(baseDataDic, engineeringDto);
                if (!StringUtils.isEmpty(key)) {
                    BaseDataDic dic = baseDataDicService.getCacheDataDicByFieldName(key);
                    engineeringDto.set_parentId(dic.getId());
                } else {
                    //未传入key  说明是父节点
                    engineeringDto.setParent(true);
                }
                if (i != 0) {
                    engineeringDto.setNumber(String.format("%d-%d", i, v++));
                } else {
                    engineeringDto.setNumber(String.valueOf(v++));
                }
                ztreeDtos.add(engineeringDto);
            }
        }
    }
}
