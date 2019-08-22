package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.common.enums.BasicApplyPartInModeEnum;
import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateLandStateDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.*;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.assess.service.data.DataDeveloperService;
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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicEstateService {
    @Autowired
    private PublicService publicService;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicEstateDao basicEstateDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicEstateLandStateDao basicEstateLandStateDao;
    @Autowired
    private BasicEstateNetworkService basicEstateNetworkService;
    @Autowired
    private BasicEstateParkingService basicEstateParkingService;
    @Autowired
    private BasicEstateSupplyService basicEstateSupplyService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private BasicMatchingEducationService basicMatchingEducationService;
    @Autowired
    private BasicMatchingEnvironmentService basicMatchingEnvironmentService;
    @Autowired
    private BasicMatchingFinanceService basicMatchingFinanceService;
    @Autowired
    private BasicMatchingLeisurePlaceService basicMatchingLeisurePlaceService;
    @Autowired
    private BasicMatchingMaterialService basicMatchingMaterialService;
    @Autowired
    private BasicMatchingMedicalService basicMatchingMedicalService;
    @Autowired
    private BasicMatchingTrafficService basicMatchingTrafficService;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private CaseMatchingTrafficService caseMatchingTrafficService;
    @Autowired
    private CaseMatchingMedicalService caseMatchingMedicalService;
    @Autowired
    private CaseMatchingMaterialService caseMatchingMaterialService;
    @Autowired
    private CaseMatchingLeisurePlaceService caseMatchingLeisurePlaceService;
    @Autowired
    private CaseMatchingFinanceService caseMatchingFinanceService;
    @Autowired
    private CaseMatchingEnvironmentService caseMatchingEnvironmentService;
    @Autowired
    private CaseMatchingEducationService caseMatchingEducationService;
    @Autowired
    private CaseEstateParkingService caseEstateParkingService;
    @Autowired
    private CaseEstateNetworkService caseEstateNetworkService;
    @Autowired
    private CaseEstateSupplyService caseEstateSupplyService;
    @Autowired
    private CaseEstateService caseEstateService;
    @Autowired
    private CaseEstateLandStateService caseEstateLandStateService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private DataDeveloperService dataDeveloperService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicApplyBatchDao basicApplyBatchDao;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicEstate getBasicEstateById(Integer id) throws Exception {
        return basicEstateDao.getBasicEstateById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicEstate
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicEstate(BasicEstate basicEstate) throws Exception {
        if (basicEstate.getId() == null || basicEstate.getId().intValue() == 0) {
            basicEstate.setCreator(commonService.thisUserAccount());
            Integer id = basicEstateDao.addBasicEstate(basicEstate);
            return id;
        } else {
            basicEstateDao.updateBasicEstate(basicEstate);
            return null;
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicEstate(Integer id) throws Exception {
        return basicEstateDao.deleteBasicEstate(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicEstate
     * @return
     * @throws Exception
     */
    public List<BasicEstate> basicEstateList(BasicEstate basicEstate) throws Exception {
        return basicEstateDao.basicEstateList(basicEstate);
    }


    public BootstrapTableVo getBootstrapTableVo(BasicEstate basicEstate) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstate> basicEstateList = basicEstateDao.basicEstateList(basicEstate);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicEstateList) ? new ArrayList<BasicEstate>(10) : basicEstateList);
        return vo;
    }

    public BasicEstateVo getBasicEstateVo(BasicEstate basicEstate) {
        if (basicEstate == null) {
            return null;
        }
        BasicEstateVo vo = new BasicEstateVo();
        BeanUtils.copyProperties(basicEstate, vo);
        if (org.apache.commons.lang.StringUtils.isNotBlank(basicEstate.getProvince())) {
            //省
            vo.setProvinceName(erpAreaService.getSysAreaName(basicEstate.getProvince()));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(basicEstate.getCity())) {
            //市或者县
            vo.setCityName(erpAreaService.getSysAreaName(basicEstate.getCity()));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(basicEstate.getDistrict())) {
            //县
            vo.setDistrictName(erpAreaService.getSysAreaName(basicEstate.getDistrict()));
        }
        vo.setPositionName(baseDataDicService.getNameById(basicEstate.getPosition()));
        if (basicEstate.getBlockId() != null) {
            DataBlock dataBlock = dataBlockService.getDataBlockById(basicEstate.getBlockId());
            if (dataBlock != null) {
                vo.setBlockName(dataBlock.getName());
            }
        }
        vo.setSupplyGasName(baseDataDicService.getNameById(basicEstate.getSupplyGas()));
        vo.setSupplyPowerName(baseDataDicService.getNameById(basicEstate.getSupplyPower()));
        vo.setSupplyWaterName(baseDataDicService.getNameById(basicEstate.getSupplyWater()));
        vo.setDrainWaterName(baseDataDicService.getNameById(basicEstate.getDrainWater()));
        vo.setSupplyHeatingName(baseDataDicService.getNameById(basicEstate.getSupplyHeating()));
        vo.setSupplyCommunicationName(baseDataDicService.getNameById(basicEstate.getSupplyCommunication()));
        vo.setSupplyRoadName(baseDataDicService.getNameById(basicEstate.getSupplyRoad()));
        if (NumberUtils.isNumber(basicEstate.getDeveloper())){
            DataDeveloper dataDeveloper = dataDeveloperService.getByDataDeveloperId(Integer.parseInt(basicEstate.getDeveloper()));
            if (dataDeveloper != null){
                vo.setDeveloper(dataDeveloper.getName());
                vo.setDeveloperName(dataDeveloper.getName());
                vo.setDataDeveloper(dataDeveloperService.getDataDeveloperVo(dataDeveloper));
            }
        }
        return vo;
    }


    /**
     * 清理无效数据
     *
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void clearInvalidData(Integer applyId) throws Exception {
        BasicEstate estate =null;
        if (applyId.equals(0)){
            BasicEstate where = new BasicEstate();
            where.setApplyId(applyId);
            where.setCreator(commonService.thisUserAccount());
            List<BasicEstate> estateList = basicEstateDao.basicEstateList(where);
            if (CollectionUtils.isEmpty(estateList)) return;
            estate = estateList.get(0);
        }else{
            BasicApply basicApply = basicApplyService.getByBasicApplyId(applyId);
            estate=basicEstateDao.getBasicEstateById(basicApply.getBasicEstateId());
        }

        StringBuilder sqlBulder = new StringBuilder();
        String baseSql = "delete from %s where estate_id=%s;";
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class), estate.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicEstateParking.class), estate.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicEstateSupply.class), estate.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingEducation.class), estate.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingEnvironment.class), estate.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingFinance.class), estate.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingLeisurePlace.class), estate.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingMaterial.class), estate.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingMedical.class), estate.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingTraffic.class), estate.getId()));

        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicEstateLandState.class), estate.getId()));
        sqlBulder.append(String.format("delete from %s where id=%s;", FormatUtils.entityNameConvertToTableName(BasicEstate.class), estate.getId()));
        ddlMySqlAssist.customTableDdl(sqlBulder.toString());
    }


    @Transactional(rollbackFor = Exception.class)
    public void clearInvalidData2(Integer tableId) throws Exception {
        StringBuilder sqlBulder = new StringBuilder();
        String baseSql = "delete from %s where estate_id=%s;";
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicEstateParking.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicEstateSupply.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingEducation.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingEnvironment.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingFinance.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingLeisurePlace.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingMaterial.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingMedical.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingTraffic.class), tableId));

        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicEstateLandState.class), tableId));
        ddlMySqlAssist.customTableDdl(sqlBulder.toString());
    }
    /**
     * 获取数据
     *
     * @param applyId
     * @return
     * @throws Exception
     */
    public Map<String, Object> getBasicEstateMapByApplyId(Integer applyId) throws Exception {
        Map<String, Object> objectMap = Maps.newHashMap();
        BasicEstate where = new BasicEstate();
        where.setApplyId(applyId);
        if (applyId == null || applyId == 0)
            where.setCreator(commonService.thisUserAccount());
        List<BasicEstate> basicEstates = basicEstateDao.basicEstateList(where);
        if (CollectionUtils.isEmpty(basicEstates)) return null;
        BasicEstate basicEstate = basicEstates.get(0);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstate.class.getSimpleName()), getBasicEstateVo(basicEstate));

        BasicEstateLandState estateLandState = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
        BasicEstateLandStateVo basicEstateLandStateVo = basicEstateLandStateService.getBasicEstateLandStateVo(estateLandState);
        if(basicEstateLandStateVo==null){
            basicEstateLandStateVo = new BasicEstateLandStateVo();
            basicEstateLandStateVo.setEstateId(estateLandState.getId());
        }
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstateLandState.class.getSimpleName()), basicEstateLandStateVo);
        return objectMap;
    }

    public BasicEstate getBasicEstateByApplyId(Integer applyId) {
        if (applyId == null || applyId == 0){
            BasicEstate where = new BasicEstate();
            where.setApplyId(applyId);
            where.setCreator(commonService.thisUserAccount());
            List<BasicEstate> basicEstates = basicEstateDao.basicEstateList(where);
            if (CollectionUtils.isEmpty(basicEstates)) return null;
            return basicEstates.get(0);
        }else{
            BasicApply basicApply = basicApplyService.getByBasicApplyId(applyId);
            return basicEstateDao.getBasicEstateById(basicApply.getBasicEstateId());
        }
    }


    /**
     * 添加楼盘及土地基本信息
     *
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addEstateAndLandstate(String estateName, String province, String city) throws Exception {
        this.clearInvalidData(0);
        Map<String, Object> objectMap = Maps.newHashMap();

        BasicEstate basicEstate = new BasicEstate();
        basicEstate.setName(estateName);
        basicEstate.setProvince(province);
        basicEstate.setCity(city);
        basicEstate.setApplyId(0);
        basicEstate.setCreator(commonService.thisUserAccount());
        basicEstateDao.addBasicEstate(basicEstate);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstate.class.getSimpleName()), getBasicEstateVo(basicEstate));

        BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
        basicEstateLandState.setName(estateName);
        basicEstateLandState.setEstateId(basicEstate.getId());
        basicEstateLandState.setCreator(commonService.thisUserAccount());
        basicEstateLandStateDao.saveBasicEstateLandState(basicEstateLandState);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstateLandState.class.getSimpleName()), basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandState));
        return objectMap;
    }


    /**
     * 将 CaseEstate 下的子类 转移到 BasicEstate下的子类中去 (用做过程数据)
     *
     * @param caseEstateId
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> appWriteEstate(Integer caseEstateId, String estatePartInMode, Integer applyId) throws Exception {
        if (caseEstateId == null) {
            throw new BusinessException("null point");
        }
        applyId = applyId == null ? 0 : applyId;
        this.clearInvalidData(applyId);

        Map<String, Object> objectMap = new HashMap<String, Object>(2);
        CaseEstate caseEstate = caseEstateService.getCaseEstateById(caseEstateId);
        if (caseEstate == null) {
            return objectMap;
        }

        BasicEstate basicEstate = new BasicEstate();
        BeanUtils.copyProperties(caseEstate, basicEstate);
        basicEstate.setApplyId(applyId);
        basicEstate.setCreator(commonService.thisUserAccount());
        basicEstate.setGmtCreated(null);
        basicEstate.setGmtModified(null);
        if (StringUtils.equals(estatePartInMode, BasicApplyPartInModeEnum.REFERENCE.getKey())) {
            basicEstate.setName(null);
        }
        basicEstateDao.addBasicEstate(basicEstate);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstate.class.getSimpleName()), getBasicEstateVo(basicEstate));

        //附件拷贝
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(caseEstate.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(CaseEstate.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(basicEstate.getId());
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        CaseEstateLandState queryLandState = new CaseEstateLandState();
        queryLandState.setEstateId(caseEstateId);
        List<CaseEstateLandState> caseEstateLandStateList = caseEstateLandStateService.getCaseEstateLandStateList(queryLandState);
        if (!ObjectUtils.isEmpty(caseEstateLandStateList)) {
            BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
            BeanUtils.copyProperties(caseEstateLandStateList.get(0), basicEstateLandState);
            basicEstateLandState.setEstateId(basicEstate.getId());
            basicEstateLandState.setCreator(commonService.thisUserAccount());
            basicEstateLandState.setApplyId(applyId == null ? 0 : applyId);
            basicEstateLandState.setGmtCreated(null);
            basicEstateLandState.setGmtModified(null);
            basicEstateLandStateDao.saveBasicEstateLandState(basicEstateLandState);
            objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstateLandState.class.getSimpleName()), basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandState));
        }else{
            objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstateLandState.class.getSimpleName()), new BasicEstateLandStateVo());
        }

        CaseEstateTagging caseEstateTagging = new CaseEstateTagging();
        caseEstateTagging.setDataId(caseEstateId);
        caseEstateTagging.setType(EstateTaggingTypeEnum.ESTATE.getKey());

        try {
            CaseEstateParking estateParking = new CaseEstateParking();
            estateParking.setEstateId(caseEstateId);
            List<CaseEstateParking> caseEstateParkings = caseEstateParkingService.getEstateParkingList(estateParking);
            if (!ObjectUtils.isEmpty(caseEstateParkings)) {
                for (CaseEstateParking caseEstateParking : caseEstateParkings) {
                    BasicEstateParking queryBasicEstateParking = new BasicEstateParking();
                    BeanCopyHelp.copyPropertiesIgnoreNull(caseEstateParking, queryBasicEstateParking);
                    queryBasicEstateParking.setEstateId(basicEstate.getId());
                    queryBasicEstateParking.setId(null);
                    queryBasicEstateParking.setGmtCreated(null);
                    queryBasicEstateParking.setGmtModified(null);
                    queryBasicEstateParking.setCreator(commonService.thisUserAccount());
                    Integer id = basicEstateParkingService.saveAndUpdateBasicEstateParking(queryBasicEstateParking);

                    example = new SysAttachmentDto();
                    example.setTableId(caseEstateParking.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(CaseEstateParking.class));
                    attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(id);
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
        map.put("estate_id", String.valueOf(basicEstate.getId()));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
        synchronousDataDto.setWhereSql("estate_id=" + caseEstate.getId());
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

        sqlBuilder.append(this.copyTaggingFromCase(EstateTaggingTypeEnum.ESTATE, caseEstate.getId(), applyId));
        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        return objectMap;
    }

    //引用项目中的数据
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> getBasicEstateMapFromProject(Integer applyId) throws Exception {
        BasicApply referenceBasicApply = basicApplyService.getByBasicApplyId(applyId);
        if (referenceBasicApply == null) {
            throw new BusinessException("null point");
        }
        this.clearInvalidData(0);

        Map<String, Object> objectMap = new HashMap<String, Object>(2);
        BasicEstate oldEstateByApplyId = this.getBasicEstateByApplyId(applyId);
        if (oldEstateByApplyId == null) {
            return objectMap;
        }

        BasicEstate basicEstate = new BasicEstate();
        BeanUtils.copyProperties(oldEstateByApplyId, basicEstate);
        basicEstate.setApplyId(0);
        basicEstate.setCreator(commonService.thisUserAccount());
        basicEstate.setGmtCreated(null);
        basicEstate.setGmtModified(null);
        basicEstate.setId(null);

        this.saveAndUpdateBasicEstate(basicEstate);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstate.class.getSimpleName()), getBasicEstateVo(basicEstate));


        //附件拷贝
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(oldEstateByApplyId.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(basicEstate.getId());
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        BasicEstateLandState queryLandState = new BasicEstateLandState();
        queryLandState.setEstateId(oldEstateByApplyId.getId());
        List<BasicEstateLandState> oldBasicEstateLandStateList = basicEstateLandStateService.basicEstateLandStateList(queryLandState);
        if (!ObjectUtils.isEmpty(oldBasicEstateLandStateList)) {
            BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
            BeanUtils.copyProperties(oldBasicEstateLandStateList.get(0), basicEstateLandState);
            basicEstateLandState.setEstateId(basicEstate.getId());
            basicEstateLandState.setCreator(commonService.thisUserAccount());
            basicEstateLandState.setApplyId(0);
            basicEstateLandState.setGmtCreated(null);
            basicEstateLandState.setGmtModified(null);
            basicEstateLandStateDao.saveBasicEstateLandState(basicEstateLandState);
            objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstateLandState.class.getSimpleName()), basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandState));
        }else{
            objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstateLandState.class.getSimpleName()), new BasicEstateLandStateVo());
        }

        BasicEstateTagging oldBasicEstateTagging = new BasicEstateTagging();
        oldBasicEstateTagging.setApplyId(applyId);
        oldBasicEstateTagging.setType(EstateTaggingTypeEnum.ESTATE.getKey());
        List<BasicEstateTagging> oldBasicEstateTaggingList = basicEstateTaggingService.getBasicEstateTaggingList(oldBasicEstateTagging);
        if (!ObjectUtils.isEmpty(oldBasicEstateTaggingList)) {
            BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
            BeanUtils.copyProperties(oldBasicEstateTaggingList.get(0), basicEstateTagging);
            basicEstateTagging.setCreator(commonService.thisUserAccount());
            basicEstateTagging.setApplyId(0);
            basicEstateTagging.setName(null);
            basicEstateTagging.setGmtCreated(null);
            basicEstateTagging.setGmtModified(null);
            basicEstateTaggingService.addBasicEstateTagging(basicEstateTagging);
            objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstateTagging.class.getSimpleName()), basicEstateTaggingService.getBasicEstateTaggingVo(basicEstateTagging));
        }

        try {
            BasicEstateParking estateParking = new BasicEstateParking();
            estateParking.setEstateId(oldEstateByApplyId.getId());
            List<BasicEstateParking> oldBasicEstateParkings = basicEstateParkingService.basicEstateParkingList(estateParking);
            if (!ObjectUtils.isEmpty(oldBasicEstateParkings)) {
                for (BasicEstateParking oldBasicEstateParking : oldBasicEstateParkings) {
                    BasicEstateParking queryBasicEstateParking = new BasicEstateParking();
                    BeanCopyHelp.copyPropertiesIgnoreNull(oldBasicEstateParking, queryBasicEstateParking);
                    queryBasicEstateParking.setEstateId(basicEstate.getId());
                    queryBasicEstateParking.setId(null);
                    queryBasicEstateParking.setGmtCreated(null);
                    queryBasicEstateParking.setGmtModified(null);
                    queryBasicEstateParking.setCreator(commonService.thisUserAccount());
                    Integer id = basicEstateParkingService.saveAndUpdateBasicEstateParking(queryBasicEstateParking);

                    example = new SysAttachmentDto();
                    example.setTableId(oldBasicEstateParking.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateParking.class));
                    attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(id);
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
        map.put("estate_id", String.valueOf(basicEstate.getId()));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
        synchronousDataDto.setWhereSql("estate_id=" + oldEstateByApplyId.getId());
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//通信网络sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateSupply.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicEstateSupply.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供应信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingTraffic.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingTraffic.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//交通信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMedical.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMedical.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//医养信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMaterial.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMaterial.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//原材料信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingLeisurePlace.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingLeisurePlace.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//休闲场所信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingFinance.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingFinance.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//金融服务信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEnvironment.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEnvironment.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//环境因素信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEducation.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEducation.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//教育信息sql

        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        return objectMap;
    }


    /**
     * 引用项目中的数据批量时
     *
     * @param id      老数据对应id
     * @param tableId basicEstate对应id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> quoteEstateData(Integer id,Integer tableId) throws Exception {
        if (id == null || tableId==null) {
            throw new BusinessException("null point");
        }
        this.clearInvalidData2(tableId);
        //更新批量申请表信息
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByEstateId(tableId);
        applyBatch.setQuoteId(id);
        applyBatch.setBaseType(BaseConstant.DATABASE_PMCC_ASSESS);
        basicApplyBatchDao.updateInfo(applyBatch);

        Map<String, Object> objectMap = new HashMap<String, Object>(2);
        BasicEstate oldEstateByApplyId = this.getBasicEstateById(id);
        if (oldEstateByApplyId == null) {
            return objectMap;
        }
        BasicEstate basicEstate = new BasicEstate();
        BeanUtils.copyProperties(oldEstateByApplyId, basicEstate);
        basicEstate.setCreator(commonService.thisUserAccount());
        basicEstate.setGmtCreated(null);
        basicEstate.setGmtModified(null);
        basicEstate.setId(tableId);
        basicEstate.setApplyId(null);

        this.saveAndUpdateBasicEstate(basicEstate);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstate.class.getSimpleName()), getBasicEstateVo(basicEstate));

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
        example.setTableId(id);
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(tableId);
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        BasicEstateLandState queryLandState = new BasicEstateLandState();
        queryLandState.setEstateId(id);
        List<BasicEstateLandState> oldBasicEstateLandStateList = basicEstateLandStateService.basicEstateLandStateList(queryLandState);
        if (!ObjectUtils.isEmpty(oldBasicEstateLandStateList)) {
            BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
            BeanUtils.copyProperties(oldBasicEstateLandStateList.get(0), basicEstateLandState);
            basicEstateLandState.setEstateId(tableId);
            basicEstateLandState.setCreator(commonService.thisUserAccount());
            basicEstateLandState.setApplyId(null);
            basicEstateLandState.setGmtCreated(null);
            basicEstateLandState.setGmtModified(null);
            basicEstateLandStateDao.saveBasicEstateLandState(basicEstateLandState);
            objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstateLandState.class.getSimpleName()), basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandState));
        }else{
            objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstateLandState.class.getSimpleName()), new BasicEstateLandStateVo());
        }

        BasicEstateTagging oldBasicEstateTagging = new BasicEstateTagging();
        oldBasicEstateTagging.setTableId(id);
        oldBasicEstateTagging.setType(EstateTaggingTypeEnum.ESTATE.getKey());
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
            objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstateTagging.class.getSimpleName()), basicEstateTaggingService.getBasicEstateTaggingVo(basicEstateTagging));
        }

        try {
            BasicEstateParking estateParking = new BasicEstateParking();
            estateParking.setEstateId(id);
            List<BasicEstateParking> oldBasicEstateParkings = basicEstateParkingService.basicEstateParkingList(estateParking);
            if (!ObjectUtils.isEmpty(oldBasicEstateParkings)) {
                for (BasicEstateParking oldBasicEstateParking : oldBasicEstateParkings) {
                    BasicEstateParking queryBasicEstateParking = new BasicEstateParking();
                    BeanCopyHelp.copyPropertiesIgnoreNull(oldBasicEstateParking, queryBasicEstateParking);
                    queryBasicEstateParking.setEstateId(tableId);
                    queryBasicEstateParking.setId(null);
                    queryBasicEstateParking.setGmtCreated(null);
                    queryBasicEstateParking.setGmtModified(null);
                    queryBasicEstateParking.setCreator(commonService.thisUserAccount());
                    Integer parkingId = basicEstateParkingService.saveAndUpdateBasicEstateParking(queryBasicEstateParking);

                    example = new SysAttachmentDto();
                    example.setTableId(oldBasicEstateParking.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateParking.class));
                    attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(parkingId);
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
        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
        synchronousDataDto.setWhereSql("estate_id=" + id);
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//通信网络sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateSupply.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicEstateSupply.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供应信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingTraffic.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingTraffic.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//交通信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMedical.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMedical.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//医养信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMaterial.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMaterial.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//原材料信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingLeisurePlace.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingLeisurePlace.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//休闲场所信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingFinance.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingFinance.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//金融服务信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEnvironment.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEnvironment.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//环境因素信息sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEducation.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEducation.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//教育信息sql

        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        return objectMap;
    }



    /**
     * 拷贝tagging数据
     *
     * @param typeEnum
     * @param dataId
     * @param applyId
     * @return
     * @throws Exception
     */
    public String copyTaggingFromCase(EstateTaggingTypeEnum typeEnum, Integer dataId, Integer applyId) throws Exception {
        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        synchronousDataDto.setWhereSql(String.format("data_id=%s and type='%s'", dataId, typeEnum.getKey()));
        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseEstateTagging.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicEstateTagging.class));
        HashMap<String, String> map = Maps.newHashMap();
        map.put("apply_id", String.valueOf(applyId));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));
        return sqlBuilder.toString();
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Map<String, Object> getBasicEstateMapById(Integer id) throws Exception {
        Map<String, Object> objectMap = Maps.newHashMap();

        BasicEstate basicEstate = this.getBasicEstateById(id);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstate.class.getSimpleName()), getBasicEstateVo(basicEstate));

        BasicEstateLandState estateLandState = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstateLandState.class.getSimpleName()), basicEstateLandStateService.getBasicEstateLandStateVo(estateLandState));
        return objectMap;
    }
}
