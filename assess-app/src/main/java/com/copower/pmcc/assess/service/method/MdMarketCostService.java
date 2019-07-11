package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.constant.AssessMarketCostConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostBuildingDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostConstructionDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.ConstructionInstallationEngineeringDto;
import com.copower.pmcc.assess.dto.output.data.DataBuildingNewRateVo;
import com.copower.pmcc.assess.dto.output.data.InfrastructureVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
import com.copower.pmcc.assess.service.data.DataInfrastructureService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.base.Objects;
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
    private DataBuildingNewRateService dataBuildingNewRateService;
    @Autowired
    private DataInfrastructureService dataInfrastructureService;


    public MdCost initExplore(SchemeJudgeObject schemeJudgeObject) {
        if (schemeJudgeObject == null) return null;
        MdCost mdCost = new MdCost();
        mdCost.setName(schemeJudgeObject.getName());
        mdCost.setCreator(commonService.thisUserAccount());
        saveAndUpdateMdCost(mdCost);
        return mdCost;
    }

    public Integer saveAndUpdateMdCost(MdCost mdCost){
        if (mdCost.getId() == null){
            mdCost.setCreator(commonService.thisUserAccount());
            return mdCostDao.addEstateNetwork(mdCost);
        }else {
            mdCostDao.updateEstateNetwork(mdCost);
            return null;
        }
    }

    public Integer saveAndUpdateMdCostBuilding(MdCostBuilding mdCostBuilding){
        if (mdCostBuilding.getId()==null){
            mdCostBuilding.setCreator(commonService.thisUserAccount());
            return mdCostBuildingDao.addEstateNetwork(mdCostBuilding);
        }else {
            mdCostBuildingDao.updateEstateNetwork(mdCostBuilding);
            return  null;
        }
    }



    public Integer saveAndUpdateMdCostConstruction(MdCostConstruction mdCostConstruction) {
        if (mdCostConstruction.getId() == null){
            mdCostConstruction.setCreator(commonService.thisUserAccount());
            return mdCostConstructionDao.addEstateNetwork(mdCostConstruction);
        }else {
            mdCostConstructionDao.updateEstateNetwork(mdCostConstruction);
            return null;
        }
    }

    public MdCost getByMdCostId(int id) {
        MdCost mdCost = mdCostDao.getEstateNetworkById(id);
        return mdCost;
    }

    public List<MdCost> getMdCostList(MdCost mdCost) {
        return mdCostDao.getEstateNetworkList(mdCost);
    }

    public List<MdCostBuilding> getMdCostBuildingList(MdCostBuilding mdCostBuilding) {
        return mdCostBuildingDao.getEstateNetworkList(mdCostBuilding);
    }

    public MdCostBuilding getMdCostBuilding(Integer id){
        return mdCostBuildingDao.getEstateNetworkById(id);
    }

    public MdCostConstruction getMdCostConstruction(Integer id){
        return mdCostConstructionDao.getEstateNetworkById(id);
    }

    public List<MdCostConstruction> getMdCostConstructionList(MdCostConstruction mdCostConstruction) {
        return mdCostConstructionDao.getEstateNetworkList(mdCostConstruction);
    }

    public List<InfrastructureVo> infrastructureList(ProjectInfo projectInfo) {
        List<InfrastructureVo> vos = dataInfrastructureService.infrastructureList(new DataInfrastructure());
        List<InfrastructureVo> tela = Lists.newArrayList();
        if (projectInfo == null) {
            return vos;
        }
        String province = projectInfo.getProvince();
        String city = projectInfo.getCity();
        for (InfrastructureVo vo : vos) {
            if (Objects.equal(vo.getProvince(), province)) {
                if (Objects.equal(vo.getCity(), city)) {
                    tela.add(vo);
                }
            }
        }
        return tela;
    }

    public List<DataBuildingNewRateVo> dataBuildingNewRateList() {
        return dataBuildingNewRateService.dataBuildingNewRateList();
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
