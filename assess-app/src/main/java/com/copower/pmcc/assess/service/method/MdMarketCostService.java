package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostConstructionDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.method.MdCostConstructionVo;
import com.copower.pmcc.assess.dto.output.method.MdCostVo;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/8/9 10:43
 * @Description:成本法
 */
@Service
public class MdMarketCostService {
    @Autowired
    private MdCostConstructionDao mdCostConstructionDao;
    @Autowired
    private MdCostDao mdCostDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private MdArchitecturalObjService mdArchitecturalObjService;
    @Autowired
    private MdDevelopmentIncomeCategoryService mdDevelopmentIncomeCategoryService;


    public MdCost initExplore(SchemeJudgeObject schemeJudgeObject) {
        if (schemeJudgeObject == null) return null;
        MdCost mdCost = new MdCost();
        mdCost.setName(schemeJudgeObject.getName());
        mdCost.setCreator(commonService.thisUserAccount());
        saveAndUpdateMdCost(mdCost);
        return mdCost;
    }

    public void saveAndUpdateMdCost(MdCost mdCost) {
        if (mdCost.getId() == null || mdCost.getId() == 0) {
            mdCost.setCreator(commonService.thisUserAccount());
            mdCostDao.addEstateNetwork(mdCost);
        } else {
            mdCostDao.updateEstateNetwork(mdCost);
        }
    }


    public Integer saveAndUpdateMdCostConstruction(String formData) {
        JSONObject jsonObject = JSON.parseObject(formData);
        MdCost mdCost = new MdCost();
        MdCostConstruction mdCostConstruction = JSONObject.parseObject(formData, MdCostConstruction.class);
        mdCost.setType((String) jsonObject.get("type"));
        mdCost.setId(mdCostConstruction.getPid());
        mdCost.setPrice(mdCostConstruction.getConstructionAssessmentPriceCorrecting());
        saveAndUpdateMdCost(mdCost);
        mdCostConstruction.setPid(mdCost.getId());
        saveMdCostConstructionAndUpdate(mdCostConstruction);
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
        MdDevelopmentIncomeCategory mdDevelopmentIncomeCategory = new MdDevelopmentIncomeCategory();
        mdDevelopmentIncomeCategory.setPid(0);
        mdDevelopmentIncomeCategory.setPlanDetailsId(mdCost.getPlanDetailsId());
        List<MdDevelopmentIncomeCategory> mdDevelopmentIncomeCategoryList = mdDevelopmentIncomeCategoryService.getMdDevelopmentIncomeCategoryListByExample(mdDevelopmentIncomeCategory) ;
        if (CollectionUtils.isNotEmpty(mdDevelopmentIncomeCategoryList)){
            for (MdDevelopmentIncomeCategory oo:mdDevelopmentIncomeCategoryList){
                oo.setPid(mdCostConstruction.getId());
                mdDevelopmentIncomeCategoryService.saveMdDevelopmentIncomeCategory(oo) ;
            }
        }

        return mdCost.getId();
    }

    public void saveMdCostConstructionAndUpdate(MdCostConstruction mdCostConstruction) {
        if (mdCostConstruction.getId() == null || mdCostConstruction.getId() == 0) {
            mdCostConstruction.setCreator(commonService.thisUserAccount());
            mdCostConstructionDao.addEstateNetwork(mdCostConstruction);
        } else {
            mdCostConstructionDao.updateEstateNetwork(mdCostConstruction);
        }
    }

    public MdCost getByMdCostId(int id) {
        MdCost mdCost = mdCostDao.getEstateNetworkById(id);
        return mdCost;
    }

    public List<MdCost> getMdCostList(MdCost mdCost) {
        return mdCostDao.getEstateNetworkList(mdCost);
    }


    public MdCostConstruction getMdCostConstruction(Integer id) {
        return mdCostConstructionDao.getEstateNetworkById(id);
    }

    public List<MdCostConstruction> getMdCostConstructionList(MdCostConstruction mdCostConstruction) {
        return mdCostConstructionDao.getEstateNetworkList(mdCostConstruction);
    }


    public MdCostVo getMdCostVo(MdCost mdCost) {
        MdCostVo mdCostVo = new MdCostVo();
        if (mdCost == null) {
            return mdCostVo;
        }
        BeanUtils.copyProperties(mdCost, mdCostVo);
        if (mdCost.getId() != null && mdCost.getId() != 0) {
            MdCostConstruction query = new MdCostConstruction();
            query.setPid(mdCost.getId());
            List<MdCostConstruction> list = this.getMdCostConstructionList(query);
            if (CollectionUtils.isNotEmpty(list)) {
                mdCostVo.setMdCostConstruction(getMdCostConstructionVo(list.stream().findFirst().get()));
            }
        }
        return mdCostVo;
    }


    public MdCostConstructionVo getMdCostConstructionVo(MdCostConstruction mdCostConstruction) {
        MdCostConstructionVo vo = new MdCostConstructionVo();
        if (mdCostConstruction == null) {
            return vo;
        }
        BeanUtils.copyProperties(mdCostConstruction, vo);
        if (org.apache.commons.lang.StringUtils.isNotEmpty(mdCostConstruction.getConstructionInstallationEngineeringFeeIds())) {
            List<Integer> integerList = FormatUtils.transformString2Integer(mdCostConstruction.getConstructionInstallationEngineeringFeeIds());
            if (CollectionUtils.isNotEmpty(integerList)) {
                for (Integer integer : integerList) {
                    MdArchitecturalObj architecturalObj = mdArchitecturalObjService.getMdArchitecturalObjById(integer);
                    if (architecturalObj == null) {
                        continue;
                    }
                    vo.getConstructionInstallationEngineeringFeeDtos().add(new KeyValueDto(architecturalObj.getId().toString(), architecturalObj.getPrice() == null ? "0" : architecturalObj.getPrice().setScale(2, BigDecimal.ROUND_UP).toString()));
                }
            }
        }
        return vo;
    }


}
