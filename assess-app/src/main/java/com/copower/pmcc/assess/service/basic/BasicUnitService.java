package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.basic.BasicApplyPartInModeEnum;
import com.copower.pmcc.assess.common.enums.basic.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateTaggingDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.output.basic.BasicUnitVo;
import com.copower.pmcc.assess.dto.output.cases.CaseUnitHuxingVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.cases.CaseEstateTaggingService;
import com.copower.pmcc.assess.service.cases.CaseUnitHuxingService;
import com.copower.pmcc.assess.service.cases.CaseUnitService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicUnit getBasicUnitById(Integer id) throws Exception {
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


    public BootstrapTableVo getBootstrapTableVo(BasicUnit basicUnit) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicUnit> basicUnitList = basicUnitDao.basicUnitList(basicUnit);
        List<BasicUnitVo> transform = LangUtils.transform(basicUnitList, o -> getBasicUnitVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(transform) ? new ArrayList<BasicUnitVo>(10) : transform);
        return vo;
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
    public void clearInvalidData(Integer applyId) throws Exception {
        BasicUnit unit = null;
        if (applyId == null || applyId.equals(0)) {
            BasicUnit where = new BasicUnit();
            where.setApplyId(0);
            where.setCreator(commonService.thisUserAccount());
            List<BasicUnit> unitList = basicUnitDao.basicUnitList(where);
            if (CollectionUtils.isEmpty(unitList)) return;
            unit = unitList.get(0);
        } else {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(applyId);
            if (basicApply != null)
                unit = basicUnitDao.getBasicUnitById(basicApply.getBasicUnitId());
        }
        if (unit == null) return;
        //清除标记
        BasicEstateTagging where = new BasicEstateTagging();
        where.setTableId(unit.getId());
        where.setType("unit");
        basicEstateTaggingDao.removeBasicEstateTagging(where);
        clearInvalidChildData(unit.getId());
        StringBuilder sqlBulder = new StringBuilder();
        sqlBulder.append(String.format("update %s set bis_delete=1 where id=%s;", FormatUtils.entityNameConvertToTableName(BasicUnit.class), unit.getId()));
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


    /**
     * 将 CaseUnit 下的子类 转移到 BasicUnit下的子类中去 (用做过程数据)
     *
     * @param caseUnitId
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicUnit appWriteUnit(Integer caseUnitId, String unitPartInMode, Integer applyId) throws Exception {
        if (caseUnitId == null) {
            throw new BusinessException("null point");
        }
        CaseUnit caseUnit = caseUnitService.getCaseUnitById(caseUnitId);
        if (caseUnit == null) {
            throw new BusinessException("null point");
        }
        applyId = applyId == null ? 0 : applyId;
        this.clearInvalidData(applyId);
        BasicUnit basicUnit = new BasicUnit();
        BeanUtils.copyProperties(caseUnit, basicUnit);
        basicUnit.setApplyId(applyId);
        basicUnit.setCreator(commonService.thisUserAccount());
        basicUnit.setGmtCreated(null);
        basicUnit.setGmtModified(null);
        if (StringUtils.equals(unitPartInMode, BasicApplyPartInModeEnum.REFERENCE.getKey())) {
            basicUnit.setUnitNumber(null);
        }
        basicUnitDao.addBasicUnit(basicUnit);

        //附件拷贝
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(caseUnit.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(CaseUnit.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(basicUnit.getId());
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        //标注拷贝
        CaseEstateTagging caseEstateTagging = new CaseEstateTagging();
        caseEstateTagging.setDataId(caseUnitId);
        caseEstateTagging.setType(EstateTaggingTypeEnum.UNIT.getKey());
        List<CaseEstateTagging> oldCaseEstateTaggingList = caseEstateTaggingService.getCaseEstateTaggingList(caseEstateTagging);
        if (!ObjectUtils.isEmpty(oldCaseEstateTaggingList)) {
            BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
            BeanUtils.copyProperties(oldCaseEstateTaggingList.get(0), basicEstateTagging);
            basicEstateTagging.setCreator(commonService.thisUserAccount());
            basicEstateTagging.setName(null);
            basicEstateTagging.setTableId(basicUnit.getId());
            basicEstateTagging.setGmtCreated(null);
            basicEstateTagging.setGmtModified(null);
            basicEstateTaggingService.addBasicEstateTagging(basicEstateTagging);
        }

        try {
            List<CaseUnitHuxingVo> caseUnitHuxingList = null;
            CaseUnitHuxing query = new CaseUnitHuxing();
            query.setUnitId(caseUnitId);
            caseUnitHuxingList = caseUnitHuxingService.getCaseUnitHuxingList(query);
            if (!ObjectUtils.isEmpty(caseUnitHuxingList)) {
                for (CaseUnitHuxingVo caseUnitHuxing : caseUnitHuxingList) {
                    BasicUnitHuxing basicUnitHuxing = new BasicUnitHuxing();
                    BeanUtils.copyProperties(caseUnitHuxing, basicUnitHuxing);
                    basicUnitHuxing.setUnitId(basicUnit.getId());
                    basicUnitHuxing.setId(null);
                    basicUnitHuxing.setGmtCreated(null);
                    basicUnitHuxing.setGmtModified(null);
                    Integer id = basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(basicUnitHuxing, false);

                    //附件拷贝
                    example = new SysAttachmentDto();
                    example.setTableId(caseUnitHuxing.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(CaseUnitHuxing.class));
                    attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(id);
                    attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
                    baseAttachmentService.copyFtpAttachments(example, attachmentDto);
                }
            }
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
        }

        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("unit_id", String.valueOf(basicUnit.getId()));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        synchronousDataDto.setWhereSql("unit_id=" + caseUnit.getId());
        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseUnitDecorate.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋内装sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseUnitElevator.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//配备电梯sql

        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        return basicUnit;
    }

    //引用项目中数据
    @Transactional(rollbackFor = Exception.class)
    public BasicUnit getBasicUnitByFromProject(Integer applyId) throws Exception {
        if (applyId == null) {
            throw new BusinessException("null point");
        }
        BasicUnit oldBasicUnit = this.getBasicUnitByApplyId(applyId);
        if (oldBasicUnit == null) {
            throw new BusinessException("null point");
        }

        this.clearInvalidData(0);
        BasicUnit basicUnit = new BasicUnit();
        BeanUtils.copyProperties(oldBasicUnit, basicUnit);
        basicUnit.setApplyId(0);
        basicUnit.setCreator(commonService.thisUserAccount());
        basicUnit.setGmtCreated(null);
        basicUnit.setGmtModified(null);
        basicUnit.setId(null);
        this.saveAndUpdateBasicUnit(basicUnit, true);


        //附件拷贝
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(oldBasicUnit.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(basicUnit.getId());
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        BasicEstateTagging oldBasicEstateTagging = new BasicEstateTagging();
        oldBasicEstateTagging.setTableId(oldBasicUnit.getId());
        oldBasicEstateTagging.setType(EstateTaggingTypeEnum.UNIT.getKey());
        List<BasicEstateTagging> oldBasicEstateTaggingList = basicEstateTaggingService.getBasicEstateTaggingList(oldBasicEstateTagging);
        if (!ObjectUtils.isEmpty(oldBasicEstateTaggingList)) {
            BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
            BeanUtils.copyProperties(oldBasicEstateTaggingList.get(0), basicEstateTagging);
            basicEstateTagging.setCreator(commonService.thisUserAccount());
            basicEstateTagging.setTableId(basicUnit.getId());
            basicEstateTagging.setName(null);
            basicEstateTagging.setGmtCreated(null);
            basicEstateTagging.setGmtModified(null);
            basicEstateTaggingService.addBasicEstateTagging(basicEstateTagging);
        }
        try {
            List<BasicUnitHuxing> oldBasicUnitHuxingList = null;
            BasicUnitHuxing query = new BasicUnitHuxing();
            query.setUnitId(oldBasicUnit.getId());
            oldBasicUnitHuxingList = basicUnitHuxingService.basicUnitHuxingList(query);
            if (!ObjectUtils.isEmpty(oldBasicUnitHuxingList)) {
                for (BasicUnitHuxing oldBasicUnitHuxing : oldBasicUnitHuxingList) {
                    BasicUnitHuxing basicUnitHuxing = new BasicUnitHuxing();
                    BeanUtils.copyProperties(oldBasicUnitHuxing, basicUnitHuxing);
                    basicUnitHuxing.setUnitId(basicUnit.getId());
                    basicUnitHuxing.setId(null);
                    basicUnitHuxing.setGmtCreated(null);
                    basicUnitHuxing.setGmtModified(null);
                    Integer id = basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(basicUnitHuxing, false);

                    //附件拷贝
                    example = new SysAttachmentDto();
                    example.setTableId(oldBasicUnitHuxing.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
                    attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(id);
                    attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
                    baseAttachmentService.copyFtpAttachments(example, attachmentDto);
                }
            }
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
        }

        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("unit_id", String.valueOf(basicUnit.getId()));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        synchronousDataDto.setWhereSql("unit_id=" + oldBasicUnit.getId());
        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋内装sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//配备电梯sql

        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        return basicUnit;
    }


    /**
     * 引用项目中的数据批量时
     *
     * @param id      老数据对应id
     * @param tableId basicUnit对应id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicUnit quoteUnitData(Integer id, Integer tableId) throws Exception {
        if (id == null || tableId == null) {
            throw new BusinessException("null point");
        }
        clearInvalidChildData(tableId);
        //更新批量申请表信息
        BasicApplyBatchDetail batchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail("tb_basic_unit", tableId);
        batchDetail.setQuoteId(id);
        batchDetail.setBaseType(BaseConstant.DATABASE_PMCC_ASSESS);
        basicApplyBatchDetailDao.updateInfo(batchDetail);
        Integer parentTableId = basicApplyBatchDetailService.getParentTableId(batchDetail);

        BasicUnit oldBasicUnit = this.getBasicUnitById(id);
        BasicUnit basicUnit = new BasicUnit();
        BeanUtils.copyProperties(oldBasicUnit, basicUnit);
        basicUnit.setCreator(commonService.thisUserAccount());
        basicUnit.setGmtCreated(null);
        basicUnit.setGmtModified(null);
        basicUnit.setId(tableId);
        basicUnit.setApplyId(null);
        basicUnit.setBuildingId(parentTableId);

        this.saveAndUpdateBasicUnit(basicUnit, false);


        //删除原有的附件
        SysAttachmentDto deleteExample = new SysAttachmentDto();
        deleteExample.setTableId(tableId);
        deleteExample.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
        List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(deleteExample);
        if (!CollectionUtils.isEmpty(attachmentList)) {
            for (SysAttachmentDto item : attachmentList) {
                baseAttachmentService.deleteAttachment(item.getId());
            }
        }


        //附件拷贝
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(id);
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(tableId);
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        BasicEstateTagging oldBasicEstateTagging = new BasicEstateTagging();
        oldBasicEstateTagging.setTableId(id);
        oldBasicEstateTagging.setType(EstateTaggingTypeEnum.UNIT.getKey());
        List<BasicEstateTagging> oldBasicEstateTaggingList = basicEstateTaggingService.getBasicEstateTaggingList(oldBasicEstateTagging);
        if (!ObjectUtils.isEmpty(oldBasicEstateTaggingList)) {
            BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
            BeanUtils.copyProperties(oldBasicEstateTaggingList.get(0), basicEstateTagging);
            basicEstateTagging.setCreator(commonService.thisUserAccount());
            basicEstateTagging.setApplyId(null);
            basicEstateTagging.setTableId(tableId);
            basicEstateTagging.setName(null);
            basicEstateTagging.setGmtCreated(null);
            basicEstateTagging.setGmtModified(null);
            basicEstateTaggingService.addBasicEstateTagging(basicEstateTagging);
        }
        try {
            List<BasicUnitHuxing> oldBasicUnitHuxingList = null;
            BasicUnitHuxing query = new BasicUnitHuxing();
            query.setUnitId(id);
            oldBasicUnitHuxingList = basicUnitHuxingService.basicUnitHuxingList(query);
            if (!ObjectUtils.isEmpty(oldBasicUnitHuxingList)) {
                for (BasicUnitHuxing oldBasicUnitHuxing : oldBasicUnitHuxingList) {
                    BasicUnitHuxing basicUnitHuxing = new BasicUnitHuxing();
                    BeanUtils.copyProperties(oldBasicUnitHuxing, basicUnitHuxing);
                    basicUnitHuxing.setUnitId(tableId);
                    basicUnitHuxing.setId(null);
                    basicUnitHuxing.setGmtCreated(null);
                    basicUnitHuxing.setGmtModified(null);
                    Integer huxingId = basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(basicUnitHuxing, false);

                    //附件拷贝
                    example = new SysAttachmentDto();
                    example.setTableId(oldBasicUnitHuxing.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
                    attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(huxingId);
                    attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
                    baseAttachmentService.copyFtpAttachments(example, attachmentDto);
                }
            }
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
        }

        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("unit_id", String.valueOf(tableId));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        synchronousDataDto.setWhereSql("unit_id=" + id);
        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋内装sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//配备电梯sql

        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        return basicUnit;
    }

    /**
     * 拷贝查勘楼栋数据
     *
     * @param sourceUnitId
     * @param containChild
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicUnit copyBasicUnit(Integer sourceUnitId, Boolean containChild) throws Exception {
        if (sourceUnitId == null) return null;
        BasicUnit sourceBasicUnit = getBasicUnitById(sourceUnitId);
        if (sourceBasicUnit == null) return null;
        BasicUnit targetBasicUnit = new BasicUnit();
        BeanUtils.copyProperties(sourceBasicUnit,targetBasicUnit);
        targetBasicUnit.setId(null);
        targetBasicUnit.setCreator(commonService.thisUserAccount());
        targetBasicUnit.setGmtCreated(null);
        targetBasicUnit.setGmtModified(null);
        this.saveAndUpdateBasicUnit(targetBasicUnit, true);

        //附件拷贝
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(sourceUnitId);
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(targetBasicUnit.getId());
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        basicEstateTaggingService.copyTagging(EstateTaggingTypeEnum.UNIT, sourceUnitId, targetBasicUnit.getId());

        if(containChild){
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
                        baseAttachmentService.copyFtpAttachments(oldBasicUnitHuxing.getId(), huxingId);//附件拷贝
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
