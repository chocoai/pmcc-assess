package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.common.enums.basic.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateLandStateDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.cases.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.dao.CaseEstateDao;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.output.cases.CaseEstateVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.data.DataBlockService;
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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 14:41
 * @Description:
 */
@Service
public class CaseEstateService {
    @Autowired
    private CaseEstateDao caseEstateDao;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CaseEstateLandStateService caseEstateLandStateService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicEstateLandStateDao basicEstateLandStateDao;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private CaseEstateParkingService caseEstateParkingService;
    @Autowired
    private BasicEstateParkingService basicEstateParkingService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicApplyBatchDao basicApplyBatchDao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public BootstrapTableVo getCaseEstateVos(CaseEstate caseEstate) {
        List<CaseEstateVo> vos = Lists.newArrayList();
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseEstate> caseEstateList = caseEstateDao.getCaseEstateList(caseEstate.getName(), caseEstate.getProvince(), caseEstate.getCity(), caseEstate.getDistrict());
        if (!ObjectUtils.isEmpty(caseEstateList)) {
            for (CaseEstate oo : caseEstateList) {
                vos.add(getCaseEstateVo(oo));
            }
        }
        vo.setRows(vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<CaseEstate> getCaseEstateList(CaseEstate caseEstate) {

        return caseEstateDao.getEstateList(caseEstate);
    }

    public CaseEstate getCaseEstateById(Integer id) {
        return caseEstateDao.getEstateById(id);
    }

    public Integer getVersion(Integer id) {
        if (id == null) return 0;
        CaseEstate caseEstate = caseEstateDao.getEstateById(id);
        if (caseEstate == null) return 0;
        return caseEstate.getVersion();
    }


    public Integer saveAndUpdateCaseEstate(CaseEstate caseEstate) throws Exception {
        if (caseEstate.getId() == null || caseEstate.getId().intValue() == 0) {
            int id = caseEstateDao.addEstate(caseEstate);
            return id;
        } else {
            caseEstateDao.updateEstate(caseEstate);
            return null;
        }
    }

    public boolean deleteCaseEstate(Integer id) {
        return caseEstateDao.deleteEstate(id);
    }

    public List<CustomCaseEntity> autoCompleteCaseEstate(String name, String province, String city) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CustomCaseEntity> caseEstateList = caseEstateDao.getLatestVersionEstateList(name, province, city, null);
        return caseEstateList;
    }

    public CaseEstateVo getCaseEstateVo(CaseEstate caseEstate) {
        CaseEstateVo vo = new CaseEstateVo();
        //获取格式化对象
        BeanUtils.copyProperties(caseEstate, vo);
        if (org.apache.commons.lang.StringUtils.isNotBlank(caseEstate.getProvince())) {
            //省
            vo.setProvinceName(erpAreaService.getSysAreaName(caseEstate.getProvince()));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(caseEstate.getCity())) {
            //市或者县
            vo.setCityName(erpAreaService.getSysAreaName(caseEstate.getCity()));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(caseEstate.getDistrict())) {
            //县
            vo.setDistrictName(erpAreaService.getSysAreaName(caseEstate.getDistrict()));
        }
        vo.setPositionName(baseDataDicService.getNameById(caseEstate.getPosition()));
        if (caseEstate.getBlockId() != null) {
            DataBlock dataBlock = dataBlockService.getDataBlockById(caseEstate.getBlockId());
            if (dataBlock != null) {
                vo.setBlockName(dataBlock.getName());
            }
        }
        vo.setSupplyGasName(baseDataDicService.getNameById(caseEstate.getSupplyGas()));
        vo.setSupplyPowerName(baseDataDicService.getNameById(caseEstate.getSupplyPower()));
        vo.setSupplyWaterName(baseDataDicService.getNameById(caseEstate.getSupplyWater()));
        vo.setDrainWaterName(baseDataDicService.getNameById(caseEstate.getDrainWater()));
        vo.setSupplyHeatingName(baseDataDicService.getNameById(caseEstate.getSupplyHeating()));
        return vo;
    }

    /**
     * 是否有楼盘信息
     *
     * @param name
     * @param province
     * @param city
     * @return
     */
    public Boolean hasEstateByName(String name, String province, String city) {
        return caseEstateDao.getEstateCountByName(name, province, city) > 0;
    }


    /**
     * 引用案列中的数据
     *
     * @param quoteId      caseEstate对应id
     * @param tableId basicEstate对应id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicEstate quoteCaseEstateToBasic(Integer quoteId, Integer tableId) throws Exception {
        if (quoteId == null || tableId == null) {
            throw new BusinessException("null point");
        }
        //更新批量申请表信息
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByEstateId(tableId);
        if(applyBatch!=null) {
            applyBatch.setQuoteId(quoteId);
            applyBatch.setBaseType(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
            basicApplyBatchDao.updateInfo(applyBatch);
        }

        BasicApplyBatchDetail batchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicEstate.class), tableId);
        if(batchDetail!=null) {
            batchDetail.setQuoteId(quoteId);
            batchDetail.setBaseType(BaseConstant.DATABASE_PMCC_ASSESS);
            basicApplyBatchDetailService.saveBasicApplyBatchDetail(batchDetail);
        }
        //案列数据复制到basic
        CaseEstate oldCaseEstateById = this.getCaseEstateById(quoteId);
        if (oldCaseEstateById == null) {
            return null;
        }
        BasicEstate basicEstate = new BasicEstate();
        basicEstateService.clearInvalidChildData(tableId);

        BeanUtils.copyProperties(oldCaseEstateById, basicEstate);
        basicEstate.setCreator(commonService.thisUserAccount());
        basicEstate.setGmtCreated(null);
        basicEstate.setGmtModified(null);
        basicEstate.setId(tableId);
        basicEstate.setApplyId(null);
        basicEstateService.saveAndUpdateBasicEstate(basicEstate,false);

        //删除原有的附件
        SysAttachmentDto deleteExample = new SysAttachmentDto();
        deleteExample.setTableId(tableId);
        deleteExample.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(deleteExample);
        if (!org.springframework.util.CollectionUtils.isEmpty(attachmentList)) {
            for (SysAttachmentDto item : attachmentList) {
                baseAttachmentService.deleteAttachment(item.getId());
            }
        }

        //附件拷贝
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(quoteId);
        example.setTableName(FormatUtils.entityNameConvertToTableName(CaseEstate.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(basicEstate.getId());
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        CaseEstateLandState queryLandState = new CaseEstateLandState();
        queryLandState.setEstateId(quoteId);
        List<CaseEstateLandState> oldCaseEstateLandStateList = caseEstateLandStateService.getCaseEstateLandStateList(queryLandState);
        if (!ObjectUtils.isEmpty(oldCaseEstateLandStateList)) {
            BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
            BeanUtils.copyProperties(oldCaseEstateLandStateList.get(0), basicEstateLandState);
            basicEstateLandState.setEstateId(tableId);
            basicEstateLandState.setCreator(commonService.thisUserAccount());
            basicEstateLandState.setApplyId(null);
            basicEstateLandState.setGmtCreated(null);
            basicEstateLandState.setGmtModified(null);
            basicEstateLandStateDao.addBasicEstateLandState(basicEstateLandState);
        }

        CaseEstateTagging oldCaseEstateTagging = new CaseEstateTagging();
        oldCaseEstateTagging.setTableId(quoteId);
        oldCaseEstateTagging.setType(EstateTaggingTypeEnum.ESTATE.getKey());
        List<CaseEstateTagging> oldCaseEstateTaggingList = caseEstateTaggingService.getCaseEstateTaggingList(oldCaseEstateTagging);
        if (!ObjectUtils.isEmpty(oldCaseEstateTaggingList)) {
            BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
            BeanUtils.copyProperties(oldCaseEstateTaggingList.get(0), basicEstateTagging);
            basicEstateTagging.setCreator(commonService.thisUserAccount());
            basicEstateTagging.setApplyId(null);
            basicEstateTagging.setTableId(tableId);
            basicEstateTagging.setName(null);
            basicEstateTagging.setGmtCreated(null);
            basicEstateTagging.setGmtModified(null);
            basicEstateTaggingService.addBasicEstateTagging(basicEstateTagging);
        }

        try {
            CaseEstateParking caseParking = new CaseEstateParking();
            caseParking.setEstateId(quoteId);
            List<CaseEstateParking> oldCaseEstateParkings = caseEstateParkingService.getEstateParkingList(caseParking);
            if (!ObjectUtils.isEmpty(oldCaseEstateParkings)) {
                for (CaseEstateParking oldCaseEstateParking : oldCaseEstateParkings) {
                    BasicEstateParking queryBasicEstateParking = new BasicEstateParking();
                    BeanCopyHelp.copyPropertiesIgnoreNull(oldCaseEstateParking, queryBasicEstateParking);
                    queryBasicEstateParking.setEstateId(tableId);
                    queryBasicEstateParking.setId(null);
                    queryBasicEstateParking.setGmtCreated(null);
                    queryBasicEstateParking.setGmtModified(null);
                    queryBasicEstateParking.setCreator(commonService.thisUserAccount());
                    Integer estateParkingId = basicEstateParkingService.saveAndUpdateBasicEstateParking(queryBasicEstateParking,false);

                    example = new SysAttachmentDto();
                    example.setTableId(oldCaseEstateParking.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(CaseEstateParking.class));
                    attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(estateParkingId);
                    attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateParking.class));
                    baseAttachmentService.copyFtpAttachments(example, attachmentDto);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("estate_id", String.valueOf(tableId));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
        synchronousDataDto.setWhereSql("estate_id=" + quoteId);
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseEstateNetwork.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//通信网络sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseEstateSupply.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicEstateSupply.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供应信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseMatchingTraffic.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingTraffic.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//交通信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseMatchingMedical.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMedical.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//医养信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseMatchingMaterial.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMaterial.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//原材料信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseMatchingLeisurePlace.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingLeisurePlace.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//休闲场所信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseMatchingFinance.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingFinance.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//金融服务信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseMatchingEnvironment.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEnvironment.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//环境因素信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseMatchingEducation.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEducation.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//教育信息sql

        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        return basicEstate;
    }


}
