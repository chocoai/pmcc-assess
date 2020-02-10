package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.enums.basic.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.dao.CaseUnitDao;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.output.cases.CaseUnitHuxingVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicEstateTaggingService;
import com.copower.pmcc.assess.service.basic.BasicUnitHuxingService;
import com.copower.pmcc.assess.service.basic.BasicUnitService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
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

import java.util.HashMap;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 14:39
 * @Description:案例 单元信息
 */
@Service
public class CaseUnitService {
    @Autowired
    private CaseUnitDao caseUnitDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private CaseUnitDecorateService caseUnitDecorateService;
    @Autowired
    private CaseUnitElevatorService caseUnitElevatorService;
    @Autowired
    private CaseUnitHuxingService caseUnitHuxingService;
    @Autowired
    private CaseHouseService caseHouseService;
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void initAndUpdateSon(Integer oldId, Integer newId) {
        CaseUnitDecorate caseUnitDecorate = new CaseUnitDecorate();
        caseUnitDecorate.setUnitId(oldId);
        CaseUnitElevator caseUnitElevator = new CaseUnitElevator();
        caseUnitElevator.setUnitId(oldId);
        CaseUnitHuxing caseUnitHuxing = new CaseUnitHuxing();
        caseUnitHuxing.setUnitId(oldId);
        CaseHouse queryHouse = new CaseHouse();
        queryHouse.setUnitId(oldId);
        List<CaseUnitDecorate> caseUnitDecorates = caseUnitDecorateService.getCaseUnitDecorateList(caseUnitDecorate);
        List<CaseUnitElevator> caseUnitElevators = caseUnitElevatorService.getUnitElevatorList(caseUnitElevator);
        List<CaseUnitHuxingVo> caseUnitHuxings = caseUnitHuxingService.getCaseUnitHuxingList(caseUnitHuxing);
        List<CaseHouse> caseHouseList = caseHouseService.getCaseHouseList(queryHouse);
        if (oldId == null) {
            if (!ObjectUtils.isEmpty(caseUnitDecorates)) {
                for (CaseUnitDecorate oo : caseUnitDecorates) {
                    caseUnitDecorateService.deleteCaseUnitDecorate(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseUnitElevators)) {
                for (CaseUnitElevator oo : caseUnitElevators) {
                    caseUnitElevatorService.deleteUnitElevator(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseUnitHuxings)) {
                for (CaseUnitHuxingVo oo : caseUnitHuxings) {
                    caseUnitHuxingService.deleteCaseUnitHuxing(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseList)) {
                for (CaseHouse oo : caseHouseList) {
                    caseHouseService.deleteCaseHouse(oo.getId());
                }
            }
        }

        if (oldId != null) {
            if (!ObjectUtils.isEmpty(caseUnitDecorates)) {
                for (CaseUnitDecorate oo : caseUnitDecorates) {
                    oo.setUnitId(newId);
                    caseUnitDecorateService.updateCaseUnitDecorate(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseUnitElevators)) {
                for (CaseUnitElevator oo : caseUnitElevators) {
                    oo.setUnitId(newId);
                    caseUnitElevatorService.updateUnitElevator(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseUnitHuxings)) {
                for (CaseUnitHuxingVo oo : caseUnitHuxings) {
                    oo.setUnitId(newId);
                    caseUnitHuxingService.updateCaseUnitHuxing(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseList)) {
                for (CaseHouse oo : caseHouseList) {
                    oo.setUnitId(newId);
                    caseHouseService.saveAndUpdateCaseHouse(oo);
                }
            }
        }
    }

    public BootstrapTableVo getCaseUnitListVos(Integer caseBuildingId, String unitName) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseUnit> caseUnits = caseUnitDao.getUnitList(caseBuildingId, unitName);
        vo.setRows(caseUnits);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<CaseUnit> getCaseUnitList(CaseUnit caseUnit) {
        return caseUnitDao.getUnitList(caseUnit);
    }

    public CaseUnit getCaseUnitById(Integer id) {
        return caseUnitDao.getUnitById(id);
    }

    public Integer getVersion(Integer id) {
        if (id == null) return 0;
        CaseUnit caseUnit = caseUnitDao.getUnitById(id);
        if (caseUnit == null) return 0;
        return caseUnit.getVersion();
    }


    public Integer saveAndUpdateCaseUnit(CaseUnit caseUnit) {
        Integer id = null;
        if (caseUnit.getId() == null || caseUnit.getId().intValue() == 0) {
            id = caseUnitDao.addUnit(caseUnit);
            return id;
        } else {
            caseUnitDao.updateUnit(caseUnit);
            return null;
        }
    }

    public int updateBuildingId(Integer oldBuildingId, Integer newBuildingId) {
        return caseUnitDao.updateBuildingMainId(oldBuildingId, newBuildingId);
    }

    public List<CustomCaseEntity> autoCompleteCaseUnit(String unitNumber, Integer caseBuildingId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CustomCaseEntity> caseEntityList = caseUnitDao.getLatestVersionUnitList(unitNumber, caseBuildingId);
        return caseEntityList;
    }

    public boolean deleteCaseUnit(Integer id) {
        return caseUnitDao.deleteUnit(id);
    }


    /**
     * 是否有单元
     *
     * @param unitNumber
     * @param caseBuildingId
     * @return
     */
    public boolean hasUnit(String unitNumber, Integer caseBuildingId) {
        return caseUnitDao.getUnitCount(unitNumber, caseBuildingId) > 0;
    }

    public CaseEstateTagging getCaseEstateTaggingByUnitId(Integer unitId) throws Exception {
        CaseEstateTagging query = new CaseEstateTagging();
        query.setTableId(unitId);
        query.setType(EstateTaggingTypeEnum.UNIT.getKey());
        List<CaseEstateTagging> caseEstateTaggingList = caseEstateTaggingService.getCaseEstateTaggingList(query);
        if (!ObjectUtils.isEmpty(caseEstateTaggingList)) {
            return caseEstateTaggingList.get(0);
        }
        return null;
    }

    /**
     * 引用案列中的数据
     *
     * @param id      caseEstate对应id
     * @param tableId basicEstate对应id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicUnit quoteCaseUnitToBasic(Integer id, Integer tableId) throws Exception {
        if (id == null || tableId == null) {
            throw new BusinessException("null point");
        }
        //清除数据
        basicUnitService.clearInvalidChildData(tableId);
        //更新批量申请表信息
        BasicApplyBatchDetail batchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicUnit.class), tableId);
        if (batchDetail != null) {
            batchDetail.setQuoteId(id);
            batchDetail.setBaseType(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
            basicApplyBatchDetailDao.updateInfo(batchDetail);
        }
        CaseUnit oldCaseUnit = this.getCaseUnitById(id);
        BasicUnit oldBasicUnit = basicUnitService.getBasicUnitById(tableId);
        BasicUnit basicUnit = new BasicUnit();
        BeanUtils.copyProperties(oldCaseUnit, basicUnit);
        basicUnit.setCreator(commonService.thisUserAccount());
        basicUnit.setGmtCreated(null);
        basicUnit.setGmtModified(null);
        basicUnit.setId(tableId);
        basicUnit.setApplyId(null);
        basicUnit.setBuildingId(null);
        basicUnit.setBuildingId(oldBasicUnit.getBuildingId());

        basicUnitService.saveAndUpdateBasicUnit(basicUnit, false);

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
        example.setTableName(FormatUtils.entityNameConvertToTableName(CaseUnit.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(tableId);
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        CaseEstateTagging oldCaseEstateTagging = new CaseEstateTagging();
        oldCaseEstateTagging.setTableId(id);
        oldCaseEstateTagging.setType(EstateTaggingTypeEnum.UNIT.getKey());
        List<CaseEstateTagging> oldCaseUnitTaggingList = caseEstateTaggingService.getCaseEstateTaggingList(oldCaseEstateTagging);
        if (!ObjectUtils.isEmpty(oldCaseUnitTaggingList)) {
            BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
            BeanUtils.copyProperties(oldCaseUnitTaggingList.get(0), basicEstateTagging);
            basicEstateTagging.setCreator(commonService.thisUserAccount());
            basicEstateTagging.setApplyId(null);
            basicEstateTagging.setTableId(tableId);
            basicEstateTagging.setName(null);
            basicEstateTagging.setGmtCreated(null);
            basicEstateTagging.setGmtModified(null);
            basicEstateTaggingService.addBasicEstateTagging(basicEstateTagging);
        }
        try {
            List<CaseUnitHuxingVo> caseUnitHuxingList = null;
            CaseUnitHuxing query = new CaseUnitHuxing();
            query.setUnitId(id);
            caseUnitHuxingList = caseUnitHuxingService.getCaseUnitHuxingList(query);
            if (!ObjectUtils.isEmpty(caseUnitHuxingList)) {
                for (CaseUnitHuxing oldCaseUnitHuxing : caseUnitHuxingList) {
                    BasicUnitHuxing basicUnitHuxing = new BasicUnitHuxing();
                    BeanUtils.copyProperties(oldCaseUnitHuxing, basicUnitHuxing);
                    basicUnitHuxing.setUnitId(tableId);
                    basicUnitHuxing.setId(null);
                    basicUnitHuxing.setGmtCreated(null);
                    basicUnitHuxing.setGmtModified(null);
                    Integer huxingId = basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(basicUnitHuxing, false);

                    //附件拷贝
                    example = new SysAttachmentDto();
                    example.setTableId(oldCaseUnitHuxing.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(CaseUnitHuxing.class));
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
}
