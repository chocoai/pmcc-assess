package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdCostBuildingDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostConstructionDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.ConstructionInstallationEngineeringDto;
import com.copower.pmcc.assess.dto.output.data.DataBuildingNewRateVo;
import com.copower.pmcc.assess.dto.output.data.InfrastructureVo;
import com.copower.pmcc.assess.dto.output.method.MdCostConstructionVo;
import com.copower.pmcc.assess.dto.output.method.MdCostVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
import com.copower.pmcc.assess.service.data.DataInfrastructureService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
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
    @Autowired
    private MdArchitecturalObjService mdArchitecturalObjService;


    public MdCost initExplore(SchemeJudgeObject schemeJudgeObject) {
        if (schemeJudgeObject == null) return null;
        MdCost mdCost = new MdCost();
        mdCost.setName(schemeJudgeObject.getName());
        mdCost.setCreator(commonService.thisUserAccount());
        saveAndUpdateMdCost(mdCost);
        return mdCost;
    }

    public void saveAndUpdateMdCost(MdCost mdCost) {
        if (mdCost.getId() == null) {
            mdCost.setCreator(commonService.thisUserAccount());
            mdCostDao.addEstateNetwork(mdCost);
        } else {
            mdCostDao.updateEstateNetwork(mdCost);
        }
    }

    public void saveAndUpdateMdCostBuilding(MdCostBuilding mdCostBuilding, MdCost mdCost) {
        if (mdCostBuilding.getId() == null) {
            mdCostBuilding.setPid(mdCost.getId());
            mdCostBuilding.setCreator(commonService.thisUserAccount());
            mdCostBuildingDao.addEstateNetwork(mdCostBuilding);
        } else {
            mdCostBuildingDao.updateEstateNetwork(mdCostBuilding);
        }
        MdArchitecturalObj mdArchitecturalObj = new MdArchitecturalObj();
        mdArchitecturalObj.setDatabaseName(FormatUtils.entityNameConvertToTableName(MdCost.class));
        mdArchitecturalObj.setPid(0);
        mdArchitecturalObj.setPlanDetailsId(mdCost.getPlanDetailsId());
        List<MdArchitecturalObj> mdArchitecturalObjList = mdArchitecturalObjService.getMdArchitecturalObjListByExample(mdArchitecturalObj);
        if (CollectionUtils.isNotEmpty(mdArchitecturalObjList)) {
            for (MdArchitecturalObj oo : mdArchitecturalObjList) {
                oo.setPid(mdCostBuilding.getId());
                mdArchitecturalObjService.saveMdArchitecturalObj(oo);
            }
        }
    }


    public void saveAndUpdateMdCostConstruction(MdCostConstruction mdCostConstruction, MdCost mdCost) {
        if (mdCostConstruction.getId() == null) {
            mdCostConstruction.setCreator(commonService.thisUserAccount());
            mdCostConstruction.setPid(mdCost.getId());
            mdCostConstructionDao.addEstateNetwork(mdCostConstruction);
        } else {
            mdCostConstructionDao.updateEstateNetwork(mdCostConstruction);
        }
        MdArchitecturalObj mdArchitecturalObj = new MdArchitecturalObj();
        mdArchitecturalObj.setDatabaseName(FormatUtils.entityNameConvertToTableName(MdCost.class));
        mdArchitecturalObj.setPid(0);
        mdArchitecturalObj.setPlanDetailsId(mdCost.getPlanDetailsId());
        List<MdArchitecturalObj> mdArchitecturalObjList = mdArchitecturalObjService.getMdArchitecturalObjListByExample(mdArchitecturalObj);
        if (CollectionUtils.isNotEmpty(mdArchitecturalObjList)) {
            for (MdArchitecturalObj oo : mdArchitecturalObjList) {
                oo.setPid(mdCostConstruction.getId());
                mdArchitecturalObjService.saveMdArchitecturalObj(oo);
            }
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

    public MdCostBuilding getMdCostBuilding(Integer id) {
        return mdCostBuildingDao.getEstateNetworkById(id);
    }

    public MdCostConstruction getMdCostConstruction(Integer id) {
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


    public MdCostVo getMdCostVo(MdCost mdCost){
        MdCostVo mdCostVo = new MdCostVo();
        if (mdCost == null){
            return mdCostVo;
        }
        BeanUtils.copyProperties(mdCost,mdCostVo);
        if (Objects.equal("2", mdCost.getType())) {
            MdCostConstruction query = new MdCostConstruction();
            query.setPid(mdCost.getId());
            List<MdCostConstruction> list = this.getMdCostConstructionList(query);
            if (CollectionUtils.isNotEmpty(list)) {
                mdCostVo.setMdCostConstruction(getMdCostConstructionVo(list.stream().findFirst().get()));
            }
        }
        if (Objects.equal("1", mdCost.getType())) {
            MdCostBuilding query = new MdCostBuilding();
            query.setPid(mdCost.getId());
            List<MdCostBuilding> list = this.getMdCostBuildingList(query);
            if (CollectionUtils.isNotEmpty(list)) {
                mdCostVo.setMdCostBuilding(list.stream().findFirst().get());
            }
        }
        return mdCostVo;
    }


    public MdCostConstructionVo getMdCostConstructionVo(MdCostConstruction mdCostConstruction){
        MdCostConstructionVo vo = new MdCostConstructionVo();
        if (mdCostConstruction == null){
            return vo;
        }
        BeanUtils.copyProperties(mdCostConstruction,vo);
        if (org.apache.commons.lang.StringUtils.isNotEmpty(mdCostConstruction.getConstructionInstallationEngineeringFeeIds())){
            List<Integer> integerList = FormatUtils.transformString2Integer(mdCostConstruction.getConstructionInstallationEngineeringFeeIds()) ;
            if (CollectionUtils.isNotEmpty(integerList)){
                for (Integer integer:integerList){
                    MdArchitecturalObj architecturalObj = mdArchitecturalObjService.getMdArchitecturalObjById(integer) ;
                    if (architecturalObj == null){
                        continue;
                    }
                    vo.getConstructionInstallationEngineeringFeeDtos().add(new KeyValueDto(architecturalObj.getId().toString(),architecturalObj.getPrice()==null?"0":architecturalObj.getPrice().setScale(2, BigDecimal.ROUND_UP).toString()));
                }
            }
        }
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
