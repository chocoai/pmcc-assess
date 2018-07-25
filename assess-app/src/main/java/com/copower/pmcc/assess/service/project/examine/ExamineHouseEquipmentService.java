package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.common.enums.ExamineHouseEquipmentTypeEnum;
import com.copower.pmcc.assess.common.enums.ExamineMatchingLeisurePlaceTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineHouseEquipmentDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseEquipment;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineHouseEquipmentVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/25 14:51
 * @Description:设备 包含（空调、新风、供暖）
 */
@Service
public class ExamineHouseEquipmentService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineHouseEquipmentDao examineHouseEquipmentDao;
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
    public ExamineHouseEquipment getExamineHouseEquipmentById(Integer id) {
        return examineHouseEquipmentDao.getHouseEquipmentById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineHouseEquipment
     * @return
     */
    public List<ExamineHouseEquipment> getExamineHouseEquipmentList(ExamineHouseEquipment examineHouseEquipment) {
        return examineHouseEquipmentDao.getHouseEquipmentList(examineHouseEquipment);
    }

    public BootstrapTableVo getExamineHouseEquipmentLists(ExamineHouseEquipment examineHouseEquipment) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineHouseEquipmentVo> vos = Lists.newArrayList();
        getExamineHouseEquipmentList(examineHouseEquipment).stream().forEach(oo -> vos.add(getExamineHouseEquipmentVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineHouseEquipmentVo>() : vos);
        return vo;
    }

    public ExamineHouseEquipmentVo getExamineHouseEquipmentVo(ExamineHouseEquipment examineHouseEquipment) {
        ExamineHouseEquipmentVo vo = new ExamineHouseEquipmentVo();
        BeanUtils.copyProperties(examineHouseEquipment, vo);
        String type = examineHouseEquipment.getType();
        if (!org.springframework.util.StringUtils.isEmpty(type)){
            ExamineHouseEquipmentTypeEnum typeEnum = ExamineHouseEquipmentTypeEnum.getEnumByName(ExamineHouseEquipmentTypeEnum.getNameByKey(type));
            String key = typeEnum.getKey();
            if (key.equals(ExamineHouseEquipmentTypeEnum.houseAirConditioner.getKey())) {
                vo.setCategoryName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_AIR_CONDITIONING_MODE,examineHouseEquipment.getCategory()));
            }
            if (key.equals(ExamineHouseEquipmentTypeEnum.houseNewWind.getKey())) {
                vo.setCategoryName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_WAY_WIND,examineHouseEquipment.getCategory()));
            }
            if (key.equals(ExamineHouseEquipmentTypeEnum.houseHeating.getKey())) {
                vo.setCategoryName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_HEATING_METHOD,examineHouseEquipment.getCategory()));
            }
        }
        if (examineHouseEquipment.getEquipmentPrice() !=null){
            vo.setEquipmentPriceName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_EQUIPMENT_PRICE_RANGE,examineHouseEquipment.getEquipmentPrice()));
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
     * @param examineHouseEquipment
     * @return
     */
    public boolean addExamineHouseEquipment(ExamineHouseEquipment examineHouseEquipment) {
        examineHouseEquipment.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineHouseEquipment.getDeclareId())) {
            examineHouseEquipment.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineHouseEquipment.getExamineType())) {
            examineHouseEquipment.setExamineType(0);
        }
        return examineHouseEquipmentDao.addHouseEquipment(examineHouseEquipment);
    }

    /**
     * 编辑
     *
     * @param examineHouseEquipment
     * @return
     */
    public boolean updateExamineHouseEquipment(ExamineHouseEquipment examineHouseEquipment) {
        return examineHouseEquipmentDao.updateHouseEquipment(examineHouseEquipment);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineHouseEquipment(Integer id) {
        return examineHouseEquipmentDao.deleteHouseEquipment(id);
    }

    public List<BaseDataDic> examineExamineHouseEquipment_grade(ExamineHouseEquipmentTypeEnum typeEnum) {
        String key = typeEnum.getKey();
        if (!org.springframework.util.StringUtils.isEmpty(key)) {
            List<BaseDataDic> baseDataDic = null;
            if (key.equals(ExamineHouseEquipmentTypeEnum.houseAirConditioner.getKey())) {
                baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_AIR_CONDITIONING_MODE);
            }
            if (key.equals(ExamineHouseEquipmentTypeEnum.houseNewWind.getKey())) {
                baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_WAY_WIND);
            }
            if (key.equals(ExamineHouseEquipmentTypeEnum.houseHeating.getKey())) {
                baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_HOUSE_HEATING_METHOD);
            }
            if (!ObjectUtils.isEmpty(baseDataDic)) {
                return baseDataDic;
            }
        }
        return null;
    }
}
