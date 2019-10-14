package com.copower.pmcc.assess.service.method;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostApproachDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostApproachItemDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostApproachTaxesDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.MdCostApproach;
import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachItem;
import com.copower.pmcc.assess.dal.basis.entity.MdCostApproachTaxes;
import com.copower.pmcc.assess.dto.output.method.MdCostApproachTaxesVo;
import com.copower.pmcc.assess.dto.output.method.MdCostApproachVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基准地价系数修正法
 *
 * @author noOne
 */

@Service
public class MdCostApproachService {
    @Autowired
    private MdCostApproachDao costApproachDao;
    @Autowired
    private MdCostApproachItemDao costApproachItemDao;
    @Autowired
    private MdCostApproachTaxesDao costApproachTaxesDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    public static BigDecimal Bhou = new BigDecimal("666.67");

    public List<MdCostApproach> getObjectList(MdCostApproach mdCostApproach) {
        return costApproachDao.getObjectList(mdCostApproach);
    }


    public MdCostApproach getDataByProcessInsId(String processInsId) {
        MdCostApproach where = new MdCostApproach();
        where.setProcessInsId(processInsId);
        return costApproachDao.getMdCostApproach(where);
    }

    public MdCostApproach getDataByPlanDetailsId(Integer planDetailsId) {
        MdCostApproach where = new MdCostApproach();
        where.setPlanDetailsId(planDetailsId);
        return costApproachDao.getMdCostApproach(where);
    }

    public void applyCommit(String formData, String processInsId) {
        MdCostApproach mdCostApproach = JSON.parseObject(formData, MdCostApproach.class);
        mdCostApproach.setProcessInsId(processInsId);
        this.saveMdCostApproach(mdCostApproach);
    }


    public void saveMdCostApproach(MdCostApproach mdCostApproach) {
        if (mdCostApproach.getId() != null && mdCostApproach.getId().intValue() > 0) {
            costApproachDao.editMdCostApproach(mdCostApproach);
        } else {
            mdCostApproach.setCreator(processControllerComponent.getThisUser());
            costApproachDao.addMdCostApproach(mdCostApproach);
        }
    }

    public MdCostApproach getSingleObject(Integer id) {
        return costApproachDao.getMdCostApproach(id);
    }

    public List<MdCostApproachItem> getMdCostApproachItemListByMasterId(Integer masterId) {
        MdCostApproachItem mdCostApproachItem = new MdCostApproachItem();
        mdCostApproachItem.setMasterId(masterId);
        return costApproachItemDao.getCostApproachItemList(mdCostApproachItem);
    }

    public BootstrapTableVo getMdCostApproachItemList(Integer masterId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<MdCostApproachItem> list = getMdCostApproachItemListByMasterId(masterId);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<MdCostApproachItem>() : list);
        return vo;
    }

    public List<MdCostApproachTaxes> getMdCostApproachTaxesListByMasterId(Integer masterId) {
        MdCostApproachTaxes mdCostApproachTaxes = new MdCostApproachTaxes();
        mdCostApproachTaxes.setMasterId(masterId);
        return costApproachTaxesDao.getCostApproachTaxesList(mdCostApproachTaxes);
    }

    public BootstrapTableVo getMdCostApproachTaxesList(Integer masterId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<MdCostApproachTaxes> list = getMdCostApproachTaxesListByMasterId(masterId);
        List<MdCostApproachTaxesVo> vos = LangUtils.transform(list, p -> getMdCostApproachTaxesVo(p));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<MdCostApproachTaxesVo>() : vos);
        return vo;
    }

    public MdCostApproachTaxesVo getMdCostApproachTaxesVo(MdCostApproachTaxes mdCostApproachTaxes) {
        if (mdCostApproachTaxes == null) return null;
        MdCostApproachTaxesVo mdCostApproachTaxesVo = new MdCostApproachTaxesVo();
        BeanUtils.copyProperties(mdCostApproachTaxes, mdCostApproachTaxesVo);
        if (mdCostApproachTaxes.getTypeId() != null && mdCostApproachTaxes.getTypeId() > 0) {
            mdCostApproachTaxesVo.setTypeName(baseDataDicService.getNameById(mdCostApproachTaxes.getTypeId()));
        }
        StringBuilder s = new StringBuilder();
        switch (mdCostApproachTaxes.getTypeKey()) {
            //青苗补偿费
            case AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_CROPS_COMPENSATE:
                //土地管理费
            case AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_LAND_MANAGER:
                //不可预见费
            case AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_CANNOT_FORESEE:
                //代征地比例
            case AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_LAND_ACQUISITION:
                if (mdCostApproachTaxes.getStandardFirst() != null) {
                    s.append(ArithmeticUtils.getPercentileSystem(mdCostApproachTaxes.getStandardFirst(), 4, BigDecimal.ROUND_HALF_UP));
                }
                break;
            default:
                s.append(mdCostApproachTaxes.getStandardFirst());
                break;
        }

        if (mdCostApproachTaxes.getStandardSecond() != null) {
            s.append("/").append(mdCostApproachTaxes.getStandardSecond());
        }
        if (StringUtils.isNotEmpty(s.toString())) {
            mdCostApproachTaxesVo.setStandard(s.toString());
        }

        return mdCostApproachTaxesVo;
    }

    /**
     * @param farmlandArea     农用地总面积
     * @param ploughArea       耕地面积
     * @param populationNumber 人口数
     */
    public void saveCostApproachTaxes(String formData, String farmlandArea, String ploughArea, String populationNumber) {
        MdCostApproachTaxes mdCostApproachTaxes = JSON.parseObject(formData, MdCostApproachTaxes.class);
        BaseDataDic dic = baseDataDicService.getCacheDataDicByFieldName(mdCostApproachTaxes.getTypeKey());
        mdCostApproachTaxes.setTypeId(dic.getId());
        //年平均产值
        BigDecimal yearProductionAverage = new BigDecimal("0");
        List<MdCostApproachItem> mdCostApproachItemList = getMdCostApproachItemListByMasterId(mdCostApproachTaxes.getMasterId());
        if (CollectionUtils.isNotEmpty(mdCostApproachItemList)) {
            for (MdCostApproachItem item : mdCostApproachItemList) {
                yearProductionAverage = yearProductionAverage.add(item.getAverageProduction());
            }
            yearProductionAverage = yearProductionAverage.divide(new BigDecimal(mdCostApproachItemList.size()), 2, BigDecimal.ROUND_HALF_UP);
        }
        //每亩土地人口 = 人口数/农用地总面积
        BigDecimal landPersonNumPerMu = new BigDecimal(populationNumber).divide(new BigDecimal(farmlandArea), 2, BigDecimal.ROUND_HALF_UP);
        //每亩耕地人口 = 人口数/耕地面积
        BigDecimal ploughPersonNumPerMu = new BigDecimal(populationNumber).divide(new BigDecimal(ploughArea), 2, BigDecimal.ROUND_HALF_UP);
        //耕地比例 = 耕地面积/农用地总面积
        BigDecimal ploughRatio = new BigDecimal(ploughArea).divide(new BigDecimal(farmlandArea), 4, BigDecimal.ROUND_HALF_UP);
        //非耕地比例 = 1-耕地比例
        BigDecimal cannotPloughRatio = new BigDecimal("1.00").subtract(ploughRatio);
        //人均耕地 = 耕地面积/人口数
        BigDecimal ploughRerCapita = new BigDecimal(ploughArea).divide(new BigDecimal(populationNumber), 4, BigDecimal.ROUND_HALF_UP);


        switch (mdCostApproachTaxes.getTypeKey()) {
            //土地补偿费
            case AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_LAND_COMPENSATE:
                BigDecimal landCompensate = new BigDecimal("0");
                if (mdCostApproachTaxes.getStandardFirst() != null) {
                    //年平均产值*标准1*耕地比例
                    landCompensate = landCompensate.add(yearProductionAverage.multiply(mdCostApproachTaxes.getStandardFirst()).multiply(ploughRatio));
                }
                if (mdCostApproachTaxes.getStandardSecond() != null) {
                    //年平均产值*标准2*非耕地比例
                    landCompensate = landCompensate.add(yearProductionAverage.multiply(mdCostApproachTaxes.getStandardSecond()).multiply(cannotPloughRatio));
                }
                mdCostApproachTaxes.setPrice(landCompensate.setScale(2, BigDecimal.ROUND_HALF_UP));
                break;
            //安置补助费
            case AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_PLACEMENT_COMPENSATE:

                BigDecimal placementCompensate = new BigDecimal("0");
                if (mdCostApproachTaxes.getStandardFirst() != null) {
                    //年平均产值*标准1*耕地比例*每亩土地人口
                    placementCompensate = placementCompensate.add(yearProductionAverage.
                            multiply(mdCostApproachTaxes.getStandardFirst()).multiply(ploughRatio).multiply(landPersonNumPerMu));
                }
                if (mdCostApproachTaxes.getStandardSecond() != null) {
                    //年平均产值*标准2*非耕地比例*每亩土地人口
                    placementCompensate = placementCompensate.add(yearProductionAverage.
                            multiply(mdCostApproachTaxes.getStandardSecond()).multiply(cannotPloughRatio).multiply(landPersonNumPerMu));
                }
                mdCostApproachTaxes.setPrice(placementCompensate.setScale(2, BigDecimal.ROUND_HALF_UP));
                break;
            //青苗补偿费
            case AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_CROPS_COMPENSATE:
                //年平均产值*标准1*耕地比例
                if (mdCostApproachTaxes.getStandardFirst() != null) {
                    mdCostApproachTaxes.setPrice(yearProductionAverage.multiply(mdCostApproachTaxes.getStandardFirst()).multiply(ploughRatio).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                break;
            //住房安置费
            case AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_HOUSE_COMPENSATE:
                //每亩土地人口*标准1*标准2
                if (mdCostApproachTaxes.getStandardFirst() != null && mdCostApproachTaxes.getStandardSecond() != null) {
                    BigDecimal houseCompensate = landPersonNumPerMu.multiply(mdCostApproachTaxes.getStandardFirst()).multiply(mdCostApproachTaxes.getStandardSecond());
                    mdCostApproachTaxes.setPrice(houseCompensate.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                break;
            //菜田建设金
            case AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_VEGETABLE_BUILD:
                //耕地比例*标准1
                if (mdCostApproachTaxes.getStandardFirst() != null) {
                    mdCostApproachTaxes.setPrice(mdCostApproachTaxes.getStandardFirst().multiply(ploughRatio).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                break;
            //耕地占用税
            case AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_OCCUPATION_LAND:
                //耕地比例*标准1*666.67
                if (mdCostApproachTaxes.getStandardFirst() != null) {
                    mdCostApproachTaxes.setPrice(mdCostApproachTaxes.getStandardFirst().multiply(ploughRatio).setScale(2, BigDecimal.ROUND_HALF_UP)
                            .multiply(Bhou).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                break;
            //耕地开垦费
            case AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_PLOUGH_RECLAIM:
                //耕地比例*标准1
                if (mdCostApproachTaxes.getStandardFirst() != null) {
                    mdCostApproachTaxes.setPrice(mdCostApproachTaxes.getStandardFirst().multiply(ploughRatio).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                break;
            //土地管理费
            case AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_LAND_MANAGER:
                //(土地补偿费+安置补助费+青苗补偿费+住房安置费+农房搬迁奖励基金+菜田建设金+耕地占用税+耕地开垦费)*标准1
                if (mdCostApproachTaxes.getStandardFirst() != null) {
                    BigDecimal landManager = new BigDecimal("0");
                    String[] keys = {AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_LAND_COMPENSATE,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_PLACEMENT_COMPENSATE,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_CROPS_COMPENSATE,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_HOUSE_COMPENSATE,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_REMOVAL_AWARD,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_VEGETABLE_BUILD,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_OCCUPATION_LAND,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_PLOUGH_RECLAIM};
                    List<MdCostApproachTaxes> list = getMdCostApproachTaxesListByKeys(mdCostApproachTaxes.getMasterId(), keys);
                    if (CollectionUtils.isNotEmpty(list)) {
                        for (MdCostApproachTaxes item : list) {
                            if (item.getPrice() != null) {
                                landManager = landManager.add(item.getPrice());
                            }
                        }
                    }

                    mdCostApproachTaxes.setPrice(mdCostApproachTaxes.getStandardFirst().multiply(landManager).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                break;
            //不可预见费
            case AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_CANNOT_FORESEE:
                //(土地补偿费+安置补助费+青苗补偿费+住房安置费+农房搬迁奖励基金+菜田建设金+耕地占用税+耕地开垦费+土地管理费)*标准1
                if (mdCostApproachTaxes.getStandardFirst() != null) {
                    BigDecimal cannotForesee = new BigDecimal("0");
                    String[] keys = {AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_LAND_COMPENSATE,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_PLACEMENT_COMPENSATE,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_CROPS_COMPENSATE,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_HOUSE_COMPENSATE,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_REMOVAL_AWARD,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_VEGETABLE_BUILD,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_OCCUPATION_LAND,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_PLOUGH_RECLAIM,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_LAND_MANAGER};
                    List<MdCostApproachTaxes> list = getMdCostApproachTaxesListByKeys(mdCostApproachTaxes.getMasterId(), keys);
                    if (CollectionUtils.isNotEmpty(list)) {
                        for (MdCostApproachTaxes item : list) {
                            if (item.getPrice() != null) {
                                cannotForesee = cannotForesee.add(item.getPrice());
                            }
                        }
                    }

                    mdCostApproachTaxes.setPrice(mdCostApproachTaxes.getStandardFirst().multiply(cannotForesee).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                break;
            //代征地比例
            case AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_LAND_ACQUISITION:
                //(土地补偿费+安置补助费+青苗补偿费+住房安置费+农房搬迁奖励基金+菜田建设金+耕地占用税+耕地开垦费+土地管理费+不可预见费)/(1-标准1)*标准1
                if (mdCostApproachTaxes.getStandardFirst() != null) {
                    BigDecimal cannotForesee = new BigDecimal("0");
                    String[] keys = {AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_LAND_COMPENSATE,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_PLACEMENT_COMPENSATE,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_CROPS_COMPENSATE,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_HOUSE_COMPENSATE,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_REMOVAL_AWARD,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_VEGETABLE_BUILD,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_OCCUPATION_LAND,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_PLOUGH_RECLAIM,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_LAND_MANAGER,
                            AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_CANNOT_FORESEE};
                    List<MdCostApproachTaxes> list = getMdCostApproachTaxesListByKeys(mdCostApproachTaxes.getMasterId(), keys);
                    if (CollectionUtils.isNotEmpty(list)) {
                        for (MdCostApproachTaxes item : list) {
                            if (item.getPrice() != null) {
                                cannotForesee = cannotForesee.add(item.getPrice());
                            }
                        }
                    }
                    BigDecimal temp = new BigDecimal("1").subtract(mdCostApproachTaxes.getStandardFirst());
                    mdCostApproachTaxes.setPrice(mdCostApproachTaxes.getStandardFirst().multiply(cannotForesee).divide(temp, 2, BigDecimal.ROUND_HALF_UP));
                }
                break;
        }

        if (mdCostApproachTaxes.getId() == null) {
            mdCostApproachTaxes.setCreator(commonService.thisUserAccount());
            costApproachTaxesDao.addCostApproachTaxes(mdCostApproachTaxes);
        } else {
            costApproachTaxesDao.updateCostApproachTaxes(mdCostApproachTaxes);
        }
    }


    //根据masterId和keys找数据
    public List<MdCostApproachTaxes> getMdCostApproachTaxesListByKeys(Integer masterId, String[] keys) {
        List<String> keyList = Arrays.asList(keys);
        List<MdCostApproachTaxes> list = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(keyList)) {
            for (String key : keyList) {
                MdCostApproachTaxes mdCostApproachTaxes = new MdCostApproachTaxes();
                mdCostApproachTaxes.setMasterId(masterId);
                mdCostApproachTaxes.setTypeKey(key);
                List<MdCostApproachTaxes> costApproachTaxesList = costApproachTaxesDao.getCostApproachTaxesList(mdCostApproachTaxes);
                if (CollectionUtils.isNotEmpty(costApproachTaxesList)) {
                    list.add(costApproachTaxesList.get(0));
                }
            }

        }
        return list;
    }

    //根据masterId和key找数据
    public MdCostApproachTaxes getMdCostApproachTaxesListByMasterId(Integer masterId, String key) {
        MdCostApproachTaxes mdCostApproachTaxes = new MdCostApproachTaxes();
        mdCostApproachTaxes.setMasterId(masterId);
        mdCostApproachTaxes.setTypeKey(key);
        List<MdCostApproachTaxes> costApproachTaxesList = costApproachTaxesDao.getCostApproachTaxesList(mdCostApproachTaxes);
        if (CollectionUtils.isNotEmpty(costApproachTaxesList)) return costApproachTaxesList.get(0);
        return mdCostApproachTaxes;
    }

    //根据masterId获取土地取得费
    public BigDecimal getLandAcquisitionBhou(Integer masterId) {
        BigDecimal total = new BigDecimal("0");
        List<MdCostApproachTaxes> costApproachTaxes = getMdCostApproachTaxesListByMasterId(masterId);
        if (CollectionUtils.isNotEmpty(costApproachTaxes)) {
            for (MdCostApproachTaxes item : costApproachTaxes) {
                if(item.getPrice()!=null) {
                    total = total.add(item.getPrice());
                }
            }
        }
        MdCostApproachTaxes landAcquisition = getMdCostApproachTaxesListByMasterId(masterId, AssessDataDicKeyConstant.DATA_LAND_APPROXIMATION_METHOD_LAND_ACQUISITION);
        if (landAcquisition != null && landAcquisition.getStandardFirst() != null) {
            total = total.subtract(landAcquisition.getPrice());
            return total.divide(new BigDecimal("1").subtract(landAcquisition.getStandardFirst()), 2, BigDecimal.ROUND_HALF_UP);
        }

        return null;
    }

    public MdCostApproachVo getMdCostApproachVo(MdCostApproach oo) {
        MdCostApproachVo vo = new MdCostApproachVo();
        if (oo == null) {
            return vo;
        }
        BeanUtils.copyProperties(oo, vo);

        if (org.apache.commons.lang3.StringUtils.isNotBlank(oo.getParcelSettingOuter())) {
            List<Integer> ids = FormatUtils.transformString2Integer(oo.getParcelSettingOuter());
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(ids)) {
                List<String> stringList = Lists.newArrayList();
                for (Integer integer : ids) {
                    stringList.add(baseDataDicService.getNameById(integer));
                }
                vo.setParcelSettingOuterName(org.apache.commons.lang3.StringUtils.join(stringList, "，"));
            }
        }

        if (org.apache.commons.lang3.StringUtils.isNotBlank(oo.getParcelSettingInner())) {
            List<Integer> ids = FormatUtils.transformString2Integer(oo.getParcelSettingInner());
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(ids)) {
                List<String> stringList = Lists.newArrayList();
                for (Integer integer : ids) {
                    stringList.add(baseDataDicService.getNameById(integer));
                }
                vo.setParcelSettingInnerName(org.apache.commons.lang3.StringUtils.join(stringList, "，"));
            }
        }
        return vo;
    }
}
