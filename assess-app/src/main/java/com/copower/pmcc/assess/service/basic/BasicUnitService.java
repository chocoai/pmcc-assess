package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.basic.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateTaggingDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.output.basic.BasicUnitVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.cases.CaseEstateTaggingService;
import com.copower.pmcc.assess.service.cases.CaseUnitHuxingService;
import com.copower.pmcc.assess.service.cases.CaseUnitService;
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
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicUnitService {
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
    private CaseUnitHuxingService caseUnitHuxingService;
    @Autowired
    private CaseUnitService caseUnitService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;
    @Autowired
    private BasicEstateTaggingDao basicEstateTaggingDao;
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;
    @Autowired
    private BasicBuildingService basicBuildingService;

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

    /**
     * 新增或者修改
     *
     * @param basicUnit
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicUnit(BasicUnit basicUnit, boolean updateNull) throws Exception {
        if (basicUnit.getId() == null || basicUnit.getId().intValue() == 0) {
            basicUnit.setCreator(commonService.thisUserAccount());
            Integer id = basicUnitDao.addBasicUnit(basicUnit);
            return id;
        } else {
            if (updateNull) {
                BasicUnit unit = basicUnitDao.getBasicUnitById(basicUnit.getId());
                if (unit != null) {
                    basicUnit.setCreator(unit.getCreator());
                    basicUnit.setGmtCreated(unit.getGmtCreated());
                    basicUnit.setGmtModified(DateUtils.now());
                }
            }
            basicUnitDao.updateBasicUnit(basicUnit, updateNull);
            return basicUnit.getId();
        }
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
    public List<BasicUnit> basicUnitList(BasicUnit basicUnit) throws Exception {
        if (basicUnit == null) {
            return null;
        }
        return basicUnitDao.basicUnitList(basicUnit);
    }


    public BootstrapTableVo getBootstrapTableVo(BasicUnit basicUnit) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicUnit> basicUnitList = basicUnitDao.basicUnitList(basicUnit);
        List<BasicUnitVo> transform = LangUtils.transform(basicUnitList, o -> getBasicUnitVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(transform) ? new ArrayList<BasicUnitVo>(10) : transform);
        return vo;
    }

    public List<CustomCaseEntity> autoCompleteCaseUnit(String unitNumber, Integer caseBuildingId) {
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
    public void clearInvalidData(Integer unitId) throws Exception {
        if (unitId == null) return;
        clearInvalidChildData(unitId);

        //清除标记
        BasicEstateTagging where = new BasicEstateTagging();
        where.setTableId(unitId);
        where.setType(EstateTaggingTypeEnum.UNIT.getKey());
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
    public void clearInvalidChildData(Integer unitId) throws Exception {
        StringBuilder sqlBulder = new StringBuilder();
        String baseSql = "update %s set bis_delete=1 where unit_id=%s;";
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class), unitId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class), unitId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class), unitId));
        ddlMySqlAssist.customTableDdl(sqlBulder.toString());
    }


    /**
     * 获取数据
     *
     * @param applyId
     * @return
     * @throws Exception
     */
    public BasicUnit getBasicUnitByApplyId(Integer applyId) {
        if (applyId == null) return null;
        BasicUnit where = new BasicUnit();
        where.setApplyId(applyId);
        where.setCreator(commonService.thisUserAccount());
        List<BasicUnit> basicUnits = basicUnitDao.basicUnitList(where);
        if (!CollectionUtils.isEmpty(basicUnits)) {
            return basicUnits.get(0);
        } else {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(applyId);
            return basicUnitDao.getBasicUnitById(basicApply.getBasicUnitId());
        }
    }

    /**
     * 添加楼盘及土地基本信息
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

    @Transactional(rollbackFor = Exception.class)
    public BasicUnit copyBasicUnit(Integer sourceUnitId, Integer targetUnitId, Boolean containChild) throws Exception {
        return copyBasicUnitIgnore(sourceUnitId, targetUnitId, containChild);
    }

    /**
     * 拷贝查勘楼栋数据
     *
     * @param sourceUnitId
     * @param targetUnitId
     * @param containChild 是否包含从表数据
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicUnit copyBasicUnitIgnore(Integer sourceUnitId, Integer targetUnitId, Boolean containChild, String... ignoreProperties) throws Exception {
        if (sourceUnitId == null) return null;
        BasicUnit sourceBasicUnit = getBasicUnitById(sourceUnitId);
        if (sourceBasicUnit == null) return null;
        BasicUnit targetBasicUnit = getBasicUnitById(targetUnitId);
        if (targetBasicUnit == null) {
            targetBasicUnit = new BasicUnit();
            BeanUtils.copyProperties(sourceBasicUnit, targetBasicUnit, ignoreProperties);
            targetBasicUnit.setId(null);
            targetBasicUnit.setCreator(commonService.thisUserAccount());
            targetBasicUnit.setGmtCreated(null);
            targetBasicUnit.setGmtModified(null);
        } else {
            BeanUtils.copyProperties(sourceBasicUnit, targetBasicUnit, "id");
        }
        this.saveAndUpdateBasicUnit(targetBasicUnit, true);
        if (targetUnitId != null && targetUnitId > 0) {//目标数据已存在，先清理目标数据的从表数据
            clearInvalidChildData(targetUnitId);

            SysAttachmentDto where=new SysAttachmentDto();
            where.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
            where.setTableId(targetUnitId);
            baseAttachmentService.deleteAttachmentByDto(where);
        }
        //附件拷贝
        baseAttachmentService.copyFtpAttachments(FormatUtils.entityNameConvertToTableName(BasicUnit.class), sourceUnitId, targetBasicUnit.getId());//附件拷贝
        basicEstateTaggingService.copyTagging(EstateTaggingTypeEnum.UNIT, sourceUnitId, targetBasicUnit.getId());
        if (containChild) {
            try {
                List<BasicUnitHuxing> oldBasicUnitHuxingList = null;
                BasicUnitHuxing query = new BasicUnitHuxing();
                query.setUnitId(sourceUnitId);
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
            synchronousDataDto.setWhereSql("unit_id=" + sourceUnitId);
            synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋内装sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//配备电梯sql

            ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        }
        return targetBasicUnit;
    }

}
