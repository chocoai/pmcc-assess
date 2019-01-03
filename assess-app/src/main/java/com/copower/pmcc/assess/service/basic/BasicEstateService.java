package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.common.enums.BasicApplyPartInModeEnum;
import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.dal.basic.dao.BasicEstateDao;
import com.copower.pmcc.assess.dal.basic.dao.BasicEstateLandStateDao;
import com.copower.pmcc.assess.dal.basic.entity.*;
import com.copower.pmcc.assess.dal.basis.entity.DataBlock;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateVo;
import com.copower.pmcc.assess.service.ErpAreaService;
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
    private DataDeveloperService dataDeveloperService;
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
        return vo;
    }


    /**
     * 清理无效数据
     *
     * @throws Exception
     */
    @Transactional(value = "transactionManagerBasic", rollbackFor = Exception.class)
    public void clearInvalidData(Integer applyId) throws Exception {
        BasicEstate where = new BasicEstate();
        where.setApplyId(applyId);
        if (applyId.equals(0))
            where.setCreator(commonService.thisUserAccount());
        List<BasicEstate> estateList = basicEstateDao.basicEstateList(where);
        if (CollectionUtils.isEmpty(estateList)) return;
        BasicEstate estate = estateList.get(0);
        BasicEstateLandState estateLandState = basicEstateLandStateDao.getLandStateByEstateId(estate.getId());

        BasicEstateNetwork queryBasicEstateNetwork = new BasicEstateNetwork();
        BasicEstateParking queryBasicEstateParking = new BasicEstateParking();
        BasicEstateSupply queryBasicEstateSupply = new BasicEstateSupply();
        BasicMatchingEducation queryBasicMatchingEducation = new BasicMatchingEducation();
        BasicMatchingEnvironment queryBasicMatchingEnvironment = new BasicMatchingEnvironment();
        BasicMatchingFinance queryBasicMatchingFinance = new BasicMatchingFinance();
        BasicMatchingLeisurePlace queryBasicMatchingLeisurePlace = new BasicMatchingLeisurePlace();
        BasicMatchingMaterial queryBasicMatchingMaterial = new BasicMatchingMaterial();
        BasicMatchingMedical queryBasicMatchingMedical = new BasicMatchingMedical();
        BasicMatchingTraffic queryBasicMatchingTraffic = new BasicMatchingTraffic();
        SysAttachmentDto querySysAttachmentDto = new SysAttachmentDto();

        queryBasicMatchingTraffic.setEstateId(estate.getId());
        queryBasicMatchingMedical.setEstateId(estate.getId());
        queryBasicMatchingMaterial.setEstateId(estate.getId());
        queryBasicMatchingLeisurePlace.setEstateId(estate.getId());
        queryBasicMatchingFinance.setEstateId(estate.getId());
        queryBasicMatchingEnvironment.setEstateId(estate.getId());
        queryBasicMatchingEducation.setEstateId(estate.getId());
        queryBasicEstateNetwork.setEstateId(estate.getId());
        queryBasicEstateParking.setEstateId(estate.getId());
        queryBasicEstateSupply.setEstateId(estate.getId());
        querySysAttachmentDto.setTableId(estate.getId());


        queryBasicMatchingTraffic.setCreator(commonService.thisUserAccount());
        queryBasicMatchingMedical.setCreator(commonService.thisUserAccount());
        queryBasicMatchingMaterial.setCreator(commonService.thisUserAccount());
        queryBasicMatchingLeisurePlace.setCreator(commonService.thisUserAccount());
        queryBasicMatchingFinance.setCreator(commonService.thisUserAccount());
        queryBasicMatchingEnvironment.setCreator(commonService.thisUserAccount());
        queryBasicMatchingEducation.setCreator(commonService.thisUserAccount());
        queryBasicEstateNetwork.setCreator(commonService.thisUserAccount());
        queryBasicEstateParking.setCreator(commonService.thisUserAccount());
        queryBasicEstateSupply.setCreator(commonService.thisUserAccount());
        querySysAttachmentDto.setCreater(commonService.thisUserAccount());

        List<BasicEstateNetwork> basicEstateNetworkList = null;
        List<BasicEstateParking> basicEstateParkingList = null;
        List<BasicEstateSupply> basicEstateSupplyList = null;
        List<BasicMatchingEducation> basicMatchingEducationList = null;
        List<BasicMatchingEnvironment> basicMatchingEnvironmentList = null;
        List<BasicMatchingFinance> basicMatchingFinanceList = null;
        List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList = null;
        List<BasicMatchingMaterial> basicMatchingMaterialList = null;
        List<BasicMatchingMedical> basicMatchingMedicalList = null;
        List<BasicMatchingTraffic> basicMatchingTrafficList = null;
        List<SysAttachmentDto> sysAttachmentDtoList = null;

        basicEstateNetworkList = basicEstateNetworkService.basicEstateNetworkList(queryBasicEstateNetwork);
        basicEstateParkingList = basicEstateParkingService.basicEstateParkingList(queryBasicEstateParking);
        basicEstateSupplyList = basicEstateSupplyService.basicEstateSupplyList(queryBasicEstateSupply);
        basicMatchingEducationList = basicMatchingEducationService.basicMatchingEducationList(queryBasicMatchingEducation);
        basicMatchingEnvironmentList = basicMatchingEnvironmentService.basicMatchingEnvironmentList(queryBasicMatchingEnvironment);
        basicMatchingFinanceList = basicMatchingFinanceService.basicMatchingFinanceList(queryBasicMatchingFinance);
        basicMatchingLeisurePlaceList = basicMatchingLeisurePlaceService.basicMatchingLeisurePlaceList(queryBasicMatchingLeisurePlace);
        basicMatchingMaterialList = basicMatchingMaterialService.basicMatchingMaterialList(queryBasicMatchingMaterial);
        basicMatchingMedicalList = basicMatchingMedicalService.basicMatchingMedicalList(queryBasicMatchingMedical);
        basicMatchingTrafficList = basicMatchingTrafficService.basicMatchingTrafficList(queryBasicMatchingTraffic);

        sysAttachmentDtoList = baseAttachmentService.getAttachmentList(querySysAttachmentDto);

        if (!ObjectUtils.isEmpty(basicEstateNetworkList)) {
            for (BasicEstateNetwork oo : basicEstateNetworkList) {
                basicEstateNetworkService.deleteBasicEstateNetwork(oo.getId());
            }
        }
        if (!ObjectUtils.isEmpty(basicEstateParkingList)) {
            for (BasicEstateParking oo : basicEstateParkingList) {
                basicEstateParkingService.deleteBasicEstateParking(oo.getId());
            }
        }
        if (!ObjectUtils.isEmpty(basicEstateSupplyList)) {
            for (BasicEstateSupply oo : basicEstateSupplyList) {
                basicEstateSupplyService.deleteBasicEstateSupply(oo.getId());
            }
        }
        if (!ObjectUtils.isEmpty(basicMatchingEducationList)) {
            for (BasicMatchingEducation oo : basicMatchingEducationList) {
                basicMatchingEducationService.deleteBasicMatchingEducation(oo.getId());
            }
        }
        if (!ObjectUtils.isEmpty(basicMatchingEnvironmentList)) {
            for (BasicMatchingEnvironment oo : basicMatchingEnvironmentList) {
                basicMatchingEnvironmentService.deleteBasicMatchingEnvironment(oo.getId());
            }
        }
        if (!ObjectUtils.isEmpty(basicMatchingFinanceList)) {
            for (BasicMatchingFinance oo : basicMatchingFinanceList) {
                basicMatchingFinanceService.deleteBasicMatchingFinance(oo.getId());
            }
        }
        if (!ObjectUtils.isEmpty(basicMatchingLeisurePlaceList)) {
            for (BasicMatchingLeisurePlace oo : basicMatchingLeisurePlaceList) {
                basicMatchingLeisurePlaceService.deleteBasicMatchingLeisurePlace(oo.getId());
            }
        }
        if (!ObjectUtils.isEmpty(basicMatchingMaterialList)) {
            for (BasicMatchingMaterial oo : basicMatchingMaterialList) {
                basicMatchingMaterialService.deleteBasicMatchingMaterial(oo.getId());
            }
        }
        if (!ObjectUtils.isEmpty(basicMatchingMedicalList)) {
            for (BasicMatchingMedical oo : basicMatchingMedicalList) {
                basicMatchingMedicalService.deleteBasicMatchingMedical(oo.getId());
            }
        }
        if (!ObjectUtils.isEmpty(basicMatchingTrafficList)) {
            for (BasicMatchingTraffic oo : basicMatchingTrafficList) {
                basicMatchingTrafficService.deleteBasicMatchingTraffic(oo.getId());
            }
        }
        if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                baseAttachmentService.deleteAttachment(sysAttachmentDto.getId());
            }
        }

        if (estateLandState != null) {
            basicEstateLandStateDao.deleteBasicEstateLandState(estateLandState.getId());//删除土地信息
        }
        basicEstateDao.deleteBasicEstate(estate.getId());//删除楼盘信息
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
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstateLandState.class.getSimpleName()), basicEstateLandStateService.getBasicEstateLandStateVo(estateLandState));
        return objectMap;
    }

    public BasicEstate getBasicEstateByApplyId(Integer applyId) throws Exception {
        BasicEstate where = new BasicEstate();
        where.setApplyId(applyId);
        if (applyId == null || applyId == 0)
            where.setCreator(commonService.thisUserAccount());
        List<BasicEstate> basicEstates = basicEstateDao.basicEstateList(where);
        if (CollectionUtils.isEmpty(basicEstates)) return null;
        return basicEstates.get(0);
    }


    /**
     * 添加楼盘及土地基本信息
     *
     * @return
     * @throws Exception
     */
    @Transactional(value = "transactionManagerBasic", rollbackFor = Exception.class)
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
    @Transactional(value = "transactionManagerBasic", rollbackFor = Exception.class)
    public Map<String, Object> appWriteEstate(Integer caseEstateId, String estatePartInMode, Integer applyId) throws Exception {
        this.clearInvalidData(0);
        //清空此申请id下所有的楼盘数据
        if (applyId != null) {
            this.clearInvalidData(applyId);
        }
        if (caseEstateId == null) {
            throw new BusinessException("null point");
        }
        Map<String, Object> objectMap = new HashMap<String, Object>(2);

        CaseEstate caseEstate = caseEstateService.getCaseEstateById(caseEstateId);
        if (caseEstate == null) {
            return objectMap;
        }


        BasicEstate basicEstate = new BasicEstate();
        BeanUtils.copyProperties(caseEstate, basicEstate);
        basicEstate.setApplyId(applyId == null ? 0 : applyId);
        basicEstate.setCreator(commonService.thisUserAccount());
        basicEstate.setGmtCreated(null);
        basicEstate.setGmtModified(null);
        if (StringUtils.equals(estatePartInMode, BasicApplyPartInModeEnum.REFERENCE.getKey())) {
            basicEstate.setName(null);
        }
        basicEstateDao.addBasicEstate(basicEstate);

        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstate.class.getSimpleName()), getBasicEstateVo(basicEstate));

        //附件拷贝
        List<SysAttachmentDto> sysAttachmentDtoList = null;
        SysAttachmentDto query = new SysAttachmentDto();
        query.setTableId(caseEstate.getId());
        query.setTableName(FormatUtils.entityNameConvertToTableName(CaseEstate.class));
        sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
        if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                SysAttachmentDto attachmentDto = new SysAttachmentDto();
                attachmentDto.setTableId(basicEstate.getId());
                attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
                baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
            }
        }

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
        }

        CaseEstateParking estateParking = new CaseEstateParking();
        estateParking.setEstateId(caseEstateId);
        CaseEstateNetwork caseEstateNetwork = new CaseEstateNetwork();
        caseEstateNetwork.setEstateId(caseEstateId);
        CaseEstateSupply caseEstateSupply = new CaseEstateSupply();
        caseEstateSupply.setEstateId(caseEstateId);
        CaseEstateTagging caseEstateTagging = new CaseEstateTagging();
        caseEstateTagging.setDataId(caseEstateId);
        caseEstateTagging.setType(EstateTaggingTypeEnum.ESTATE.getKey());
        CaseMatchingTraffic caseMatchingTraffic = new CaseMatchingTraffic();
        caseMatchingTraffic.setEstateId(caseEstateId);
        CaseMatchingMedical caseMatchingMedical = new CaseMatchingMedical();
        caseMatchingMedical.setEstateId(caseEstateId);
        CaseMatchingMaterial caseMatchingMaterial = new CaseMatchingMaterial();
        caseMatchingMaterial.setEstateId(caseEstateId);
        CaseMatchingLeisurePlace caseMatchingLeisurePlace = new CaseMatchingLeisurePlace();
        caseMatchingLeisurePlace.setEstateId(caseEstateId);
        CaseMatchingFinance caseMatchingFinance = new CaseMatchingFinance();
        caseMatchingFinance.setEstateId(caseEstateId);
        CaseMatchingEnvironment caseMatchingEnvironment = new CaseMatchingEnvironment();
        caseMatchingEnvironment.setEstateId(caseEstateId);
        CaseMatchingEducation caseMatchingEducation = new CaseMatchingEducation();
        caseMatchingEducation.setEstateId(caseEstateId);

        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<CaseMatchingTraffic> caseMatchingTraffics = caseMatchingTrafficService.getMatchingTrafficList(caseMatchingTraffic);
                    if (!ObjectUtils.isEmpty(caseMatchingTraffics)) {
                        for (CaseMatchingTraffic oo : caseMatchingTraffics) {
                            BasicMatchingTraffic queryBasicMatchingTraffic = new BasicMatchingTraffic();
                            BeanUtils.copyProperties(oo, queryBasicMatchingTraffic);
                            queryBasicMatchingTraffic.setEstateId(basicEstate.getId());
                            queryBasicMatchingTraffic.setId(null);
                            queryBasicMatchingTraffic.setGmtCreated(null);
                            queryBasicMatchingTraffic.setGmtModified(null);
                            queryBasicMatchingTraffic.setCreator(commonService.thisUserAccount());
                            basicMatchingTrafficService.saveAndUpdateBasicMatchingTraffic(queryBasicMatchingTraffic);
                        }
                    }
                } catch (Exception e1) {
                    logger.error("", e1);
                }
            }
        });
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<CaseMatchingMedical> caseMatchingMedicals = caseMatchingMedicalService.getCaseMatchingMedicalList(caseMatchingMedical);
                    if (!ObjectUtils.isEmpty(caseMatchingMedicals)) {
                        for (CaseMatchingMedical oo : caseMatchingMedicals) {
                            BasicMatchingMedical queryBasicMatchingMedical = new BasicMatchingMedical();
                            BeanUtils.copyProperties(oo, queryBasicMatchingMedical);
                            queryBasicMatchingMedical.setEstateId(basicEstate.getId());
                            queryBasicMatchingMedical.setId(null);
                            queryBasicMatchingMedical.setGmtCreated(null);
                            queryBasicMatchingMedical.setGmtModified(null);
                            queryBasicMatchingMedical.setCreator(commonService.thisUserAccount());
                            basicMatchingMedicalService.saveAndUpdateBasicMatchingMedical(queryBasicMatchingMedical);
                        }
                    }
                } catch (Exception e1) {
                    logger.error("", e1);
                }
            }
        });
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<CaseMatchingMaterial> caseMatchingMaterials = caseMatchingMaterialService.getCaseMatchingMaterialList(caseMatchingMaterial);
                    if (!ObjectUtils.isEmpty(caseMatchingMaterials)) {
                        for (CaseMatchingMaterial oo : caseMatchingMaterials) {
                            BasicMatchingMaterial queryBasicMatchingMaterial = new BasicMatchingMaterial();
                            BeanUtils.copyProperties(oo, queryBasicMatchingMaterial);
                            queryBasicMatchingMaterial.setEstateId(basicEstate.getId());
                            queryBasicMatchingMaterial.setId(null);
                            queryBasicMatchingMaterial.setGmtCreated(null);
                            queryBasicMatchingMaterial.setGmtModified(null);
                            queryBasicMatchingMaterial.setCreator(commonService.thisUserAccount());
                            basicMatchingMaterialService.saveAndUpdateBasicMatchingMaterial(queryBasicMatchingMaterial);
                        }
                    }
                } catch (Exception e1) {
                    logger.error("", e1);
                }
            }
        });
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<CaseMatchingLeisurePlace> caseMatchingLeisurePlaces = caseMatchingLeisurePlaceService.getCaseMatchingLeisurePlaceList(caseMatchingLeisurePlace);
                    if (!ObjectUtils.isEmpty(caseMatchingLeisurePlaces)) {
                        for (CaseMatchingLeisurePlace oo : caseMatchingLeisurePlaces) {
                            BasicMatchingLeisurePlace queryBasicMatchingLeisurePlace = new BasicMatchingLeisurePlace();
                            BeanUtils.copyProperties(oo, queryBasicMatchingLeisurePlace);
                            queryBasicMatchingLeisurePlace.setEstateId(basicEstate.getId());
                            queryBasicMatchingLeisurePlace.setId(null);
                            queryBasicMatchingLeisurePlace.setGmtCreated(null);
                            queryBasicMatchingLeisurePlace.setGmtModified(null);
                            queryBasicMatchingLeisurePlace.setCreator(commonService.thisUserAccount());
                            basicMatchingLeisurePlaceService.saveAndUpdateBasicMatchingLeisurePlace(queryBasicMatchingLeisurePlace);
                        }
                    }
                } catch (Exception e1) {
                    logger.error("", e1);
                }
            }
        });
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<CaseMatchingFinance> caseMatchingFinances = caseMatchingFinanceService.getCaseMatchingFinanceList(caseMatchingFinance);
                    if (!ObjectUtils.isEmpty(caseMatchingFinances)) {
                        for (CaseMatchingFinance oo : caseMatchingFinances) {
                            BasicMatchingFinance queryBasicMatchingFinance = new BasicMatchingFinance();
                            BeanUtils.copyProperties(oo, queryBasicMatchingFinance);
                            queryBasicMatchingFinance.setEstateId(basicEstate.getId());
                            queryBasicMatchingFinance.setId(null);
                            queryBasicMatchingFinance.setGmtCreated(null);
                            queryBasicMatchingFinance.setGmtModified(null);
                            queryBasicMatchingFinance.setCreator(commonService.thisUserAccount());
                            basicMatchingFinanceService.saveAndUpdateBasicMatchingFinance(queryBasicMatchingFinance);
                        }
                    }
                } catch (Exception e1) {
                    logger.error("", e1);
                }
            }
        });
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<CaseMatchingEnvironment> caseMatchingEnvironments = caseMatchingEnvironmentService.getCaseMatchingEnvironmentList(caseMatchingEnvironment);
                    if (!ObjectUtils.isEmpty(caseMatchingEnvironments)) {
                        for (CaseMatchingEnvironment oo : caseMatchingEnvironments) {
                            BasicMatchingEnvironment queryBasicMatchingEnvironment = new BasicMatchingEnvironment();
                            BeanUtils.copyProperties(oo, queryBasicMatchingEnvironment);
                            queryBasicMatchingEnvironment.setEstateId(basicEstate.getId());
                            queryBasicMatchingEnvironment.setId(null);
                            queryBasicMatchingEnvironment.setGmtCreated(null);
                            queryBasicMatchingEnvironment.setGmtModified(null);
                            queryBasicMatchingEnvironment.setCreator(commonService.thisUserAccount());
                            basicMatchingEnvironmentService.saveAndUpdateBasicMatchingEnvironment(queryBasicMatchingEnvironment);
                        }
                    }
                } catch (Exception e1) {
                    logger.error("", e1);
                }
            }
        });
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<CaseMatchingEducation> caseMatchingEducations = caseMatchingEducationService.getCaseMatchingEducationList(caseMatchingEducation);
                    if (!ObjectUtils.isEmpty(caseMatchingEducations)) {
                        for (CaseMatchingEducation oo : caseMatchingEducations) {
                            BasicMatchingEducation queryBasicMatchingEducation = new BasicMatchingEducation();
                            BeanUtils.copyProperties(oo, queryBasicMatchingEducation);
                            queryBasicMatchingEducation.setEstateId(basicEstate.getId());
                            queryBasicMatchingEducation.setId(null);
                            queryBasicMatchingEducation.setCreator(commonService.thisUserAccount());
                            queryBasicMatchingEducation.setGmtCreated(null);
                            queryBasicMatchingEducation.setGmtModified(null);
                            basicMatchingEducationService.saveAndUpdateBasicMatchingEducation(queryBasicMatchingEducation);
                        }
                    }
                } catch (Exception e1) {
                    logger.error("", e1);
                }
            }
        });
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
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
                            List<SysAttachmentDto> sysAttachmentDtoList = null;
                            SysAttachmentDto query = new SysAttachmentDto();
                            query.setTableId(caseEstateParking.getId());
                            query.setTableName(FormatUtils.entityNameConvertToTableName(CaseEstateParking.class));
                            sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                            if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                                for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                                    SysAttachmentDto attachmentDto = new SysAttachmentDto();
                                    attachmentDto.setTableId(id);
                                    attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateParking.class));
                                    baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                                }
                            }
                        }
                    }
                } catch (Exception e1) {
                    logger.error("", e1);
                }
            }
        });
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<CaseEstateNetwork> caseEstateNetworks = caseEstateNetworkService.getEstateNetworkLists(caseEstateNetwork);
                    if (!ObjectUtils.isEmpty(caseEstateNetworks)) {
                        for (CaseEstateNetwork caseEstateNetwork1 : caseEstateNetworks) {
                            BasicEstateNetwork queryBasicEstateNetwork = new BasicEstateNetwork();
                            BeanCopyHelp.copyPropertiesIgnoreNull(caseEstateNetwork1, queryBasicEstateNetwork);
                            queryBasicEstateNetwork.setEstateId(basicEstate.getId());
                            queryBasicEstateNetwork.setId(null);
                            queryBasicEstateNetwork.setGmtCreated(null);
                            queryBasicEstateNetwork.setGmtModified(null);
                            queryBasicEstateNetwork.setCreator(commonService.thisUserAccount());
                            basicEstateNetworkService.saveAndUpdateBasicEstateNetwork(queryBasicEstateNetwork);
                        }
                    }
                } catch (Exception e1) {
                    logger.error("", e1);
                }
            }
        });
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<CaseEstateSupply> caseEstateSupplies = caseEstateSupplyService.getCaseEstateSupplyList(caseEstateSupply);
                    if (!ObjectUtils.isEmpty(caseEstateSupplies)) {
                        for (CaseEstateSupply caseEstateSupply1 : caseEstateSupplies) {
                            BasicEstateSupply queryBasicEstateSupply = new BasicEstateSupply();
                            BeanCopyHelp.copyPropertiesIgnoreNull(caseEstateSupply1, queryBasicEstateSupply);
                            queryBasicEstateSupply.setEstateId(basicEstate.getId());
                            queryBasicEstateSupply.setId(null);
                            queryBasicEstateSupply.setGmtCreated(null);
                            queryBasicEstateSupply.setGmtModified(null);
                            queryBasicEstateSupply.setCreator(commonService.thisUserAccount());
                            basicEstateSupplyService.saveAndUpdateBasicEstateSupply(queryBasicEstateSupply);
                        }
                    }
                } catch (Exception e1) {
                    logger.error("", e1);
                }
            }
        });

        if (org.apache.commons.lang3.StringUtils.equals(estatePartInMode, BasicApplyPartInModeEnum.UPGRADE.getKey())) {
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        List<CaseEstateTagging> caseEstateTaggings = caseEstateTaggingService.getCaseEstateTaggingList(caseEstateTagging);
                        copyTaggingFromCase(caseEstateTaggings);
                    } catch (Exception e1) {
                        logger.error("", e1);
                    }
                }
            });
        }
        return objectMap;
    }


    /**
     * 拷贝tagging数据
     *
     * @param caseEstateTaggings
     * @throws Exception
     */
    public void copyTaggingFromCase(List<CaseEstateTagging> caseEstateTaggings) throws Exception {
        if (!ObjectUtils.isEmpty(caseEstateTaggings)) {
            for (CaseEstateTagging caseEstateTagging : caseEstateTaggings) {
                BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
                BeanCopyHelp.copyPropertiesIgnoreNull(caseEstateTagging, basicEstateTagging);
                basicEstateTagging.setApplyId(0);
                basicEstateTagging.setId(null);
                basicEstateTagging.setGmtCreated(null);
                basicEstateTagging.setGmtModified(null);
                basicEstateTagging.setCreator(commonService.thisUserAccount());
                basicEstateTaggingService.addBasicEstateTagging(basicEstateTagging);
            }
        }
    }
}
