package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyFormNameEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateTaggingDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.input.basic.BasicFormClassifyParamDto;
import com.copower.pmcc.assess.dto.output.basic.BasicUnitVo;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicUnitService extends BasicEntityAbstract {
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BasicUnitDao basicUnitDao;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicEstateTaggingDao basicEstateTaggingDao;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private PublicBasicService publicBasicService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicUnit getBasicUnitById(Integer id) {
        return basicUnitDao.getBasicUnitById(id);
    }

    public List<BasicUnit> getBasicUnitListByIds(List<Integer> ids) {
        return basicUnitDao.getBasicUnitListByIds(ids);
    }

    public List<BasicUnit> getBasicUnitByUnitId(Integer buildingId) {
        BasicUnit unit = new BasicUnit();
        unit.setBuildingId(buildingId);
        unit.setBisDelete(false);
        return basicUnitDao.getBasicUnitList(unit);
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicUnit(Integer id) throws Exception {
        return basicUnitDao.deleteBasicUnit(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicUnit
     * @return
     * @throws Exception
     */
    public List<BasicUnit> getBasicUnitList(BasicUnit basicUnit) {
        if (basicUnit == null) {
            return null;
        }
        return basicUnitDao.getBasicUnitList(basicUnit);
    }


    public BootstrapTableVo getBootstrapTableVo(BasicUnit basicUnit) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicUnit> basicUnitList = basicUnitDao.getBasicUnitList(basicUnit);
        List<BasicUnitVo> transform = LangUtils.transform(basicUnitList, o -> getBasicUnitVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(transform) ? new ArrayList<BasicUnitVo>(10) : transform);
        return vo;
    }

    public List<CustomCaseEntity> autoCompleteCaseUnit(String unitNumber, Integer caseBuildingId) {
        if (StringUtils.isBlank(unitNumber) || caseBuildingId == null) return null;
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CustomCaseEntity> caseEntityList = basicUnitDao.getLatestVersionUnitList(unitNumber, caseBuildingId);
        return caseEntityList;
    }

    public BasicUnitVo getBasicUnitVo(BasicUnit basicUnit) {
        if (basicUnit == null) {
            return null;
        }
        BasicUnitVo vo = new BasicUnitVo();
        BeanUtils.copyProperties(basicUnit, vo);
        vo.setCreatorName(publicService.getUserNameByAccount(basicUnit.getCreator()));
        return vo;
    }


    /**
     * 清理无效数据
     *
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void clearInvalidData(Integer unitId) throws Exception {
        if (unitId == null) return;
        clearInvalidChildData(unitId);

        //清除标记
        BasicEstateTagging where = new BasicEstateTagging();
        where.setTableId(unitId);
        where.setType(BasicFormClassifyEnum.UNIT.getKey());
        basicEstateTaggingDao.removeBasicEstateTagging(where);

        StringBuilder sqlBulder = new StringBuilder();
        sqlBulder.append(String.format("update %s set bis_delete=1 where id=%s;", FormatUtils.entityNameConvertToTableName(BasicUnit.class), unitId));
        ddlMySqlAssist.customTableDdl(sqlBulder.toString());
    }

    /**
     * 清理从表数据
     *
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void clearInvalidChildData(Integer unitId) throws Exception {
        StringBuilder sqlBulder = new StringBuilder();
        String baseSql = "update %s set bis_delete=1 where unit_id=%s;";
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class), unitId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class), unitId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class), unitId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicUnitStairs.class), unitId));
        ddlMySqlAssist.customTableDdl(sqlBulder.toString());
    }

    public BasicUnit getBasicUnitByApplyId(Integer applyId) {
        if (applyId == null) return null;
        return getBasicUnitByBasicApply(basicApplyService.getByBasicApplyId(applyId));
    }

    /**
     * 获取数据
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public BasicUnit getBasicUnitByBasicApply(BasicApply basicApply) {
        if (basicApply == null) return null;
        String structuralInfo = basicApply.getStructuralInfo();
        List<KeyValueDto> keyValueDtos = JSON.parseArray(structuralInfo, KeyValueDto.class);
        for (KeyValueDto keyValueDto : keyValueDtos) {
            if (StringUtils.isNotBlank(keyValueDto.getKey()) && keyValueDto.getKey().contains(BasicFormClassifyEnum.UNIT.getKey())) {
                return getBasicUnitById(Integer.valueOf(keyValueDto.getValue()));
            }
        }
        return null;
    }

    /**
     * 添加信息
     *
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicUnit addUnit(String unitNumber) throws Exception {
        this.clearInvalidData(0);
        BasicUnit basicUnit = new BasicUnit();
        basicUnit.setUnitNumber(unitNumber);
        basicUnit.setApplyId(0);
        basicUnit.setCreator(commonService.thisUserAccount());
        basicUnitDao.addBasicUnit(basicUnit);
        return basicUnit;
    }

    @Override
    public Integer saveAndUpdate(Object o, Boolean updateNull) {
        if (o == null) return null;
        BasicUnit basicUnit = (BasicUnit) o;
        if (basicUnit.getId() == null || basicUnit.getId().intValue() == 0) {
            basicUnit.setCreator(commonService.thisUserAccount());
            Integer id = basicUnitDao.addBasicUnit(basicUnit);
            return id;
        } else {
            if (updateNull) {
                BasicUnit unit = basicUnitDao.getBasicUnitById(basicUnit.getId());
                if (unit != null) {
                    basicUnit.setBisDelete(unit.getBisDelete());
                    basicUnit.setCreator(unit.getCreator());
                    basicUnit.setGmtCreated(unit.getGmtCreated());
                    basicUnit.setGmtModified(DateUtils.now());
                }
            }
            basicUnitDao.updateBasicUnit(basicUnit, updateNull);
            return basicUnit.getId();
        }
    }

    @Override
    public Integer saveAndUpdateByFormData(String formData, Integer planDetailsId) throws Exception {
        if (StringUtils.isBlank(formData)) return null;
        JSONObject jsonObject = JSON.parseObject(formData);
        BasicUnit basicUnit = null;
        String jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_UNIT.getVar());
        if (StringUtils.isNotEmpty(jsonContent)) {
            basicUnit = JSONObject.parseObject(jsonContent, BasicUnit.class);
            //原来数据做记录,将老数据复制一条
            BasicUnit oldBasicUnit = getBasicUnitById(basicUnit.getId());
            if (oldBasicUnit != null && StringUtils.isNotBlank(oldBasicUnit.getUnitNumber())) {
                BasicUnit version = (BasicUnit) copyBasicEntity(oldBasicUnit.getId(), null, false);
                version.setRelevanceId(oldBasicUnit.getId());
                version.setEstateId(0);
                version.setBuildingId(0);
                saveAndUpdate(version, false);
            }

            if (basicUnit != null) {
                BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingById(basicUnit.getBuildingId());
                if (basicBuilding != null)
                    basicUnit.setFullName(basicBuilding.getFullName() + basicUnit.getUnitNumber());
                saveAndUpdate(basicUnit, true);
                BasicApplyBatchDetail unitDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicUnit.class), basicUnit.getId());
                if (unitDetail != null) {
                    unitDetail.setName(basicUnit.getUnitNumber());
                    unitDetail.setDisplayName(basicUnit.getUnitNumber());
                    basicApplyBatchDetailService.saveBasicApplyBatchDetail(unitDetail);
                }
                return basicUnit.getId();
            }
        }
        return null;
    }

    @Override
    public Object getBasicEntityById(Integer id) {
        return getBasicUnitById(id);
    }

    @Override
    public Object copyBasicEntity(Integer sourceId, Integer targetId, Boolean containChild) throws Exception {
        return copyBasicEntityIgnore(sourceId, targetId, containChild, null);
    }

    @Override
    public Object copyBasicEntityIgnore(Integer sourceId, Integer targetId, Boolean containChild, List<String> ignoreList) throws Exception {
        if (sourceId == null) return null;
        BasicUnit sourceBasicUnit = getBasicUnitById(sourceId);
        if (sourceBasicUnit == null) return null;
        BasicUnit targetBasicUnit = (targetId == null || targetId <= 0) ? null : getBasicUnitById(targetId);
        if (CollectionUtils.isEmpty(ignoreList)) ignoreList = Lists.newArrayList();
        ignoreList.addAll(Lists.newArrayList(BaseConstant.ASSESS_IGNORE_ARRAY));
        if (targetBasicUnit == null) {
            targetBasicUnit = new BasicUnit();
            BeanUtils.copyProperties(sourceBasicUnit, targetBasicUnit, ignoreList.toArray(new String[ignoreList.size()]));
            targetBasicUnit.setCreator(commonService.thisUserAccount());
        } else {
            BeanUtils.copyProperties(sourceBasicUnit, targetBasicUnit, ignoreList.toArray(new String[ignoreList.size()]));
        }
        this.saveAndUpdate(targetBasicUnit, true);
        if (targetId != null && targetId > 0) {//目标数据已存在，先清理目标数据的从表数据
            clearInvalidChildData(targetId);

            SysAttachmentDto where = new SysAttachmentDto();
            where.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
            where.setTableId(targetId);
            baseAttachmentService.deleteAttachmentByDto(where);
        }
        //附件拷贝
        baseAttachmentService.copyFtpAttachments(FormatUtils.entityNameConvertToTableName(BasicUnit.class), sourceId, targetBasicUnit.getId());//附件拷贝
        basicEstateTaggingService.copyTagging(BasicFormClassifyEnum.UNIT, sourceId, targetBasicUnit.getId());
        if (containChild) {
            try {
                List<BasicUnitHuxing> oldBasicUnitHuxingList = null;
                BasicUnitHuxing query = new BasicUnitHuxing();
                query.setUnitId(sourceId);
                oldBasicUnitHuxingList = basicUnitHuxingService.basicUnitHuxingList(query);
                if (!ObjectUtils.isEmpty(oldBasicUnitHuxingList)) {
                    for (BasicUnitHuxing oldBasicUnitHuxing : oldBasicUnitHuxingList) {
                        BasicUnitHuxing basicUnitHuxing = new BasicUnitHuxing();
                        BeanUtils.copyProperties(oldBasicUnitHuxing, basicUnitHuxing);
                        basicUnitHuxing.setUnitId(targetBasicUnit.getId());
                        basicUnitHuxing.setId(null);
                        basicUnitHuxing.setGmtCreated(null);
                        basicUnitHuxing.setGmtModified(null);
                        Integer huxingId = basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(basicUnitHuxing, false);
                        baseAttachmentService.copyFtpAttachments(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class), oldBasicUnitHuxing.getId(), huxingId);//附件拷贝
                    }
                }
            } catch (Exception e) {
                logger.info(e.getMessage(), e);
            }

            StringBuilder sqlBuilder = new StringBuilder();
            SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
            HashMap<String, String> map = Maps.newHashMap();
            map.put("unit_id", String.valueOf(targetBasicUnit.getId()));
            map.put("creator", commonService.thisUserAccount());
            synchronousDataDto.setFieldDefaultValue(map);
            synchronousDataDto.setWhereSql("unit_id=" + sourceId);
            synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋内装sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//配备电梯sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitStairs.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitStairs.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//单元 单元楼梯信息 sql

            ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        }
        return targetBasicUnit;
    }

    @Override
    public List<BasicFormClassifyEnum> getLowerFormClassifyList() {
        List<BasicFormClassifyEnum> list = Lists.newArrayList();
        list.add(BasicFormClassifyEnum.HOUSE);
        return list;
    }

    @Override
    public ModelAndView getEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/realEstate/unit");
        modelAndView.addObject("basicUnit", getBasicUnitById(basicFormClassifyParamDto.getTbId()));
        Integer applyBatchId = basicFormClassifyParamDto.getApplyBatchId();
        Integer tbId = basicFormClassifyParamDto.getTbId();
        BasicApplyBatchDetail basicApplyBatchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(applyBatchId, FormatUtils.entityNameConvertToTableName(BasicUnit.class), tbId);
        if (basicApplyBatchDetail != null) {//获取引用id
            basicApplyBatchDetail = basicApplyBatchDetailService.getDataById(basicApplyBatchDetail.getPid());
            if (basicApplyBatchDetail != null) {
                BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByTableName(basicApplyBatchDetail.getTableName());
                Object entity = entityAbstract.getBasicEntityById(basicApplyBatchDetail.getTableId());
                if (entity != null) {
                    modelAndView.addObject("quoteId", entityAbstract.getProperty(entity, "quoteId"));
                }
            }
        }
        return modelAndView;
    }

    @Override
    public ModelAndView getDetailModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/realEstate/detail/unit");
        modelAndView.addObject("basicUnit", getBasicUnitVo(getBasicUnitById(basicFormClassifyParamDto.getTbId())));
        return modelAndView;
    }

    @Override
    public ModelAndView getPhoneEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/realEstate/photo/unit");
        modelAndView.addObject("basicUnit", getBasicUnitById(basicFormClassifyParamDto.getTbId()));
        return modelAndView;
    }
}
