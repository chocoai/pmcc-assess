package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineHouseWaterDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseWater;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineHouseWaterVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/25 18:08
 * @Description:供排水情况
 */
@Service
public class ExamineHouseWaterService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineHouseWaterDao examineHouseWaterDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public ExamineHouseWater getExamineHouseWaterById(Integer id) {
        return examineHouseWaterDao.getHouseWaterById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineHouseWater
     * @return
     */
    public List<ExamineHouseWater> getExamineHouseWaterList(ExamineHouseWater examineHouseWater) {
        return examineHouseWaterDao.getHouseWaterList(examineHouseWater);
    }

    public BootstrapTableVo getExamineHouseWaterLists(ExamineHouseWater examineHouseWater) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineHouseWaterVo> vos = Lists.newArrayList();
        getExamineHouseWaterList(examineHouseWater).stream().forEach(oo -> vos.add(getExamineHouseWaterVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineHouseWaterVo>() : vos);
        return vo;
    }

    public ExamineHouseWaterVo getExamineHouseWaterVo(ExamineHouseWater examineHouseWater) {
        ExamineHouseWaterVo vo = new ExamineHouseWaterVo();
        BeanUtils.copyProperties(examineHouseWater, vo);
        if (examineHouseWater.getSupplyErectionMethod() != null){
            vo.setSupplyErectionMethodName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_SUPPLY_ERECTION_METHOD,examineHouseWater.getSupplyErectionMethod()));
        }
        if (examineHouseWater.getPretreatedWater() != null){
            vo.setPretreatedWaterName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_PRETREATED_WATER,examineHouseWater.getPretreatedWater()));
        }
        if (examineHouseWater.getDrainageCircuit() != null){
            vo.setDrainageCircuitName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_WATER_DRAINAGE_CIRCUIT,examineHouseWater.getDrainageCircuit()));
        }
        if (examineHouseWater.getDrainageCircuitCount() != null){
            vo.setDrainageCircuitCountName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_WATER_DRAINAGE_CIRCUIT_COUNT,examineHouseWater.getDrainageCircuitCount()));
        }
        if (org.apache.commons.lang.StringUtils.isNumeric(examineHouseWater.getPurificationEquipmentPrice())){
            vo.setPurificationEquipmentPriceName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_PURIFICATION_EQUIPMENT_PRICE,Integer.parseInt(examineHouseWater.getPurificationEquipmentPrice())));
        }
        if (org.apache.commons.lang.StringUtils.isNumeric(examineHouseWater.getWaterIntakeEquipmentPrice())){
            vo.setPurificationEquipmentPriceName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_WATER_INTAKE_EQUIPMENT_PRICE,Integer.parseInt(examineHouseWater.getWaterIntakeEquipmentPrice())));
        }
        return vo;
    }

    private String getValue(String key, Integer v) {
        StringBuilder builder = new StringBuilder(1024);
        List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(key);
        if (baseDataDic.size() >= 1) {
            if (v != null) {
                for (BaseDataDic base : baseDataDic) {
                    if (base.getId().intValue() == v.intValue()) {
                        builder.append(base.getName());
                    }
                }
            }
        }
        return builder.toString();
    }

    /**
     * 新增
     *
     * @param examineHouseWater
     * @return
     */
    public boolean addExamineHouseWater(ExamineHouseWater examineHouseWater) {
        examineHouseWater.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineHouseWater.getDeclareId())) {
            examineHouseWater.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineHouseWater.getExamineType())) {
            examineHouseWater.setExamineType(0);
        }
        return examineHouseWaterDao.addHouseWater(examineHouseWater);
    }

    /**
     * 编辑
     *
     * @param examineHouseWater
     * @return
     */
    public boolean updateExamineHouseWater(ExamineHouseWater examineHouseWater) {
        return examineHouseWaterDao.updateHouseWater(examineHouseWater);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineHouseWater(Integer id) {
        return examineHouseWaterDao.deleteHouseWater(id);
    }


}
