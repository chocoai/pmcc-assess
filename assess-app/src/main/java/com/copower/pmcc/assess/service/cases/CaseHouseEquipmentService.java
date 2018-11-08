package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.enums.ExamineHouseEquipmentTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseHouseEquipmentDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseEquipment;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseEquipmentVo;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 10:08
 * @Description:
 */
@Service
public class CaseHouseEquipmentService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseHouseEquipmentDao caseHouseEquipmentDao;
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
    public CaseHouseEquipment getCaseHouseEquipmentById(Integer id) {
        return caseHouseEquipmentDao.getHouseEquipmentById(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseHouseEquipment
     * @return
     */
    public List<CaseHouseEquipment> getCaseHouseEquipmentList(CaseHouseEquipment caseHouseEquipment) {
        return caseHouseEquipmentDao.getHouseEquipmentList(caseHouseEquipment);
    }

    public BootstrapTableVo getCaseHouseEquipmentLists(CaseHouseEquipment caseHouseEquipment) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseHouseEquipmentVo> vos = Lists.newArrayList();
        getCaseHouseEquipmentList(caseHouseEquipment).stream().forEach(oo -> vos.add(getCaseHouseEquipmentVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseHouseEquipmentVo>() : vos);
        return vo;
    }

    public CaseHouseEquipmentVo getCaseHouseEquipmentVo(CaseHouseEquipment caseHouseEquipment) {
        CaseHouseEquipmentVo vo = new CaseHouseEquipmentVo();
        BeanUtils.copyProperties(caseHouseEquipment, vo);
        String type = caseHouseEquipment.getType();
        if (!org.springframework.util.StringUtils.isEmpty(type)){
            ExamineHouseEquipmentTypeEnum typeEnum = ExamineHouseEquipmentTypeEnum.getEnumByName(ExamineHouseEquipmentTypeEnum.getNameByKey(type));
            String key = typeEnum.getKey();
            if (key.equals(ExamineHouseEquipmentTypeEnum.houseAirConditioner.getKey())) {
                vo.setCategoryName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_AIR_CONDITIONING_MODE,caseHouseEquipment.getCategory()));
            }
            if (key.equals(ExamineHouseEquipmentTypeEnum.houseNewWind.getKey())) {
                vo.setCategoryName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_WAY_WIND,caseHouseEquipment.getCategory()));
            }
            if (key.equals(ExamineHouseEquipmentTypeEnum.houseHeating.getKey())) {
                vo.setCategoryName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_HEATING_METHOD,caseHouseEquipment.getCategory()));
            }
        }
        if (caseHouseEquipment.getEquipmentPrice() !=null){
            vo.setEquipmentPriceName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_EQUIPMENT_PRICE_RANGE,caseHouseEquipment.getEquipmentPrice()));
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
     * @param caseHouseEquipment
     * @return
     */
    public boolean addCaseHouseEquipment(CaseHouseEquipment caseHouseEquipment) {
        caseHouseEquipment.setCreator(commonService.thisUserAccount());
        return caseHouseEquipmentDao.addHouseEquipment(caseHouseEquipment);
    }

    /**
     * 编辑
     *
     * @param caseHouseEquipment
     * @return
     */
    public boolean updateCaseHouseEquipment(CaseHouseEquipment caseHouseEquipment) {
        return caseHouseEquipmentDao.updateHouseEquipment(caseHouseEquipment);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseHouseEquipment(Integer id) {
        return caseHouseEquipmentDao.deleteHouseEquipment(id);
    }

    public List<BaseDataDic> examineCaseHouseEquipment_grade(ExamineHouseEquipmentTypeEnum typeEnum) {
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

    public void upgradeVersion(CaseHouseEquipment oo) throws Exception {
        if (oo.getId() == null || oo.getId().intValue() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            oo.setVersion(0);
            this.addCaseHouseEquipment(oo);
        }
        if (oo.getId().intValue() >= 1) {
            CaseHouseEquipment po = this.getCaseHouseEquipmentById(oo.getId());
            if (po.getVersion() == null){
                po.setVersion(0);
            }
            int version = po.getVersion() +1;
            BeanUtils.copyProperties(oo,po);
            po.setVersion(version);
            po.setCreator(commonService.thisUserAccount());
            po.setId(null);
            this.addCaseHouseEquipment(po);

        }
    }
}
