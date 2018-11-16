package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicEstateDao;
import com.copower.pmcc.assess.dal.basic.entity.*;
import com.copower.pmcc.assess.dal.basis.entity.DataBlock;
import com.copower.pmcc.assess.dal.basis.entity.DataDeveloper;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.assess.service.data.DataDeveloperService;
import com.copower.pmcc.assess.service.data.DataLandLevelService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

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
    private DataLandLevelService dataLandLevelService;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicEstateDao basicEstateDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private BasicEstateNetworkService basicEstateNetworkService;
    @Autowired
    private BasicEstateParkingService basicEstateParkingService;
    @Autowired
    private BasicEstateSupplyService basicEstateSupplyService;
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
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void initUpdateSon(Integer oldId, Integer newId,BasicEstate basicEstate) throws Exception {
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

        queryBasicMatchingTraffic.setEstateId(oldId);
        queryBasicMatchingMedical.setEstateId(oldId);
        queryBasicMatchingMaterial.setEstateId(oldId);
        queryBasicMatchingLeisurePlace.setEstateId(oldId);

        queryBasicMatchingFinance.setEstateId(oldId);
        queryBasicMatchingEnvironment.setEstateId(oldId);
        queryBasicMatchingEducation.setEstateId(oldId);
        queryBasicEstateNetwork.setEstateId(oldId);
        queryBasicEstateParking.setEstateId(oldId);
        queryBasicEstateSupply.setEstateId(oldId);


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

        if (newId == null) {
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
                for (BasicMatchingLeisurePlace oo:basicMatchingLeisurePlaceList){
                    basicMatchingLeisurePlaceService.deleteBasicMatchingLeisurePlace(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(basicMatchingMaterialList)) {
                for (BasicMatchingMaterial oo:basicMatchingMaterialList){
                    basicMatchingMaterialService.deleteBasicMatchingMaterial(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(basicMatchingMedicalList)) {
                for (BasicMatchingMedical oo:basicMatchingMedicalList){
                    basicMatchingMedicalService.deleteBasicMatchingMedical(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(basicMatchingTrafficList)) {
                for (BasicMatchingTraffic oo:basicMatchingTrafficList){
                    basicMatchingTrafficService.deleteBasicMatchingTraffic(oo.getId());
                }
            }
        }

        if (newId != null) {
            if (!ObjectUtils.isEmpty(basicEstateNetworkList)) {
                for (BasicEstateNetwork oo : basicEstateNetworkList) {
                    oo.setEstateId(newId);
                    oo.setTemporary(basicEstate.getTemporary());
                    basicEstateNetworkService.saveAndUpdateBasicEstateNetwork(oo);
                }
            }
            if (!ObjectUtils.isEmpty(basicEstateParkingList)) {
                for (BasicEstateParking oo : basicEstateParkingList) {
                    oo.setEstateId(newId);
                    oo.setTemporary(basicEstate.getTemporary());
                    basicEstateParkingService.saveAndUpdateBasicEstateParking(oo);
                }
            }
            if (!ObjectUtils.isEmpty(basicEstateSupplyList)) {
                for (BasicEstateSupply oo : basicEstateSupplyList) {
                    oo.setEstateId(newId);
                    oo.setTemporary(basicEstate.getTemporary());
                    basicEstateSupplyService.saveAndUpdateBasicEstateSupply(oo);
                }
            }
            if (!ObjectUtils.isEmpty(basicMatchingEducationList)) {
                for (BasicMatchingEducation oo : basicMatchingEducationList) {
                    oo.setEstateId(newId);
                    oo.setTemporary(basicEstate.getTemporary());
                    basicMatchingEducationService.saveAndUpdateBasicMatchingEducation(oo);
                }
            }
            if (!ObjectUtils.isEmpty(basicMatchingEnvironmentList)) {
                for (BasicMatchingEnvironment oo : basicMatchingEnvironmentList) {
                    oo.setEstateId(newId);
                    oo.setTemporary(basicEstate.getTemporary());
                    basicMatchingEnvironmentService.saveAndUpdateBasicMatchingEnvironment(oo);
                }
            }
            if (!ObjectUtils.isEmpty(basicMatchingFinanceList)) {
                for (BasicMatchingFinance oo : basicMatchingFinanceList) {
                    oo.setEstateId(newId);
                    oo.setTemporary(basicEstate.getTemporary());
                    basicMatchingFinanceService.saveAndUpdateBasicMatchingFinance(oo);
                }
            }
            if (!ObjectUtils.isEmpty(basicMatchingLeisurePlaceList)) {
                for (BasicMatchingLeisurePlace oo:basicMatchingLeisurePlaceList){
                    oo.setEstateId(newId);
                    oo.setTemporary(basicEstate.getTemporary());
                    basicMatchingLeisurePlaceService.saveAndUpdateBasicMatchingLeisurePlace(oo);
                }
            }
            if (!ObjectUtils.isEmpty(basicMatchingMaterialList)) {
                for (BasicMatchingMaterial oo:basicMatchingMaterialList){
                    oo.setEstateId(newId);
                    oo.setTemporary(basicEstate.getTemporary());
                    basicMatchingMaterialService.saveAndUpdateBasicMatchingMaterial(oo);
                }
            }
            if (!ObjectUtils.isEmpty(basicMatchingMedicalList)) {
                for (BasicMatchingMedical oo:basicMatchingMedicalList){
                    oo.setEstateId(newId);
                    oo.setTemporary(basicEstate.getTemporary());
                    basicMatchingMedicalService.saveAndUpdateBasicMatchingMedical(oo);
                }
            }
            if (!ObjectUtils.isEmpty(basicMatchingTrafficList)) {
                for (BasicMatchingTraffic oo:basicMatchingTrafficList){
                    oo.setEstateId(newId);
                    oo.setTemporary(basicEstate.getTemporary());
                    basicMatchingTrafficService.saveAndUpdateBasicMatchingTraffic(oo);
                }
            }
        }
    }

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
            Integer id = basicEstateDao.saveBasicEstate(basicEstate);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class), id);
            return id;
        } else {
            basicEstateDao.updateBasicEstate(basicEstate);
            return null;
        }
    }

    public Integer saveBasicEstate(BasicEstate basicEstate) throws Exception {
        if (basicEstate.getId() == null || basicEstate.getId().intValue() == 0) {
            basicEstate.setCreator(commonService.thisUserAccount());
        }
        Integer id = basicEstateDao.saveBasicEstate(basicEstate);
        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class), id);
        return id;
    }

    public Integer upgradeVersion(BasicEstate basicEstate) throws Exception {
        //无 id 情况下save
        if (basicEstate.getId() == null || basicEstate.getId().intValue() == 0) {
            basicEstate.setCreator(commonService.thisUserAccount());
            if (basicEstate.getVersion() == null) {
                basicEstate.setVersion(0);
            }
            Integer id = basicEstateDao.saveBasicEstate(basicEstate);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class), id);
            basicEstate.setId(id);
            this.initUpdateSon(0, id,basicEstate);
            return id;
            //有id情况下 save
        } else {
            //不再使用这种方式
//            ddlMySqlAssist.insertBasicEstate(basicEstate);
//            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class), basicEstate.getId());
            BasicEstate oo = this.getBasicEstateById(basicEstate.getId());
            if (oo.getVersion() == null) {
                oo.setVersion(0);
            }
            basicEstate.setVersion(oo.getVersion() + 1);
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
        //获取格式化对象
        NumberFormat nt = NumberFormat.getPercentInstance();
        //设置百分数精确度2即保留两位小数
        nt.setMinimumFractionDigits(2);
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
        if (!org.springframework.util.StringUtils.isEmpty(basicEstate.getVolumetricRate())) {
            if (NumberUtils.isNumber(basicEstate.getVolumetricRate())) {
                vo.setVolumetricRateName(nt.format(Double.parseDouble(basicEstate.getVolumetricRate())));
            }
        }
        if (!org.springframework.util.StringUtils.isEmpty(basicEstate.getGreeningRate())) {
            if (NumberUtils.isNumber(basicEstate.getGreeningRate())) {
                vo.setGreeningRateName(nt.format(Double.parseDouble(basicEstate.getGreeningRate())));
            }
        }
        if (basicEstate.getDeveloperId() != null) {
            DataDeveloper dataDeveloper = dataDeveloperService.getByDataDeveloperId(basicEstate.getDeveloperId());
            if (dataDeveloper != null) {
                vo.setDeveloperName(dataDeveloper.getName());
            }
        }
        if (basicEstate.getBlockId() != null) {
            DataBlock dataBlock = dataBlockService.getDataBlockById(basicEstate.getBlockId());
            if (dataBlock != null) {
                vo.setBlockName(dataBlock.getName());
            }
        }
        return vo;
    }

    public final static String insertSql() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        ;

        builder.append(" (").append("id, apply_id, province, city, \n" +
                "      district, block_id, developer_id, \n" +
                "      name, street, number, \n" +
                "      land_level, attach_number, floor_area, \n" +
                "      cover_an_area, volumetric_rate, greening_rate, \n" +
                "      building_number, position, description, \n" +
                "      average_price, price_range, version, \n" +
                "      creator, gmt_created, gmt_modified").append(") ");
        builder.append("values (");
        builder.append("#{id,jdbcType=INTEGER},#{applyId,jdbcType=INTEGER}, #{province,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, \n" +
                "      #{district,jdbcType=VARCHAR}, #{blockId,jdbcType=INTEGER}, #{developerId,jdbcType=INTEGER}, \n" +
                "      #{name,jdbcType=VARCHAR}, #{street,jdbcType=VARCHAR}, #{number,jdbcType=VARCHAR}, \n" +
                "      #{landLevel,jdbcType=INTEGER}, #{attachNumber,jdbcType=VARCHAR}, #{floorArea,jdbcType=DECIMAL}, \n" +
                "      #{coverAnArea,jdbcType=DECIMAL}, #{volumetricRate,jdbcType=VARCHAR}, #{greeningRate,jdbcType=VARCHAR}, \n" +
                "      #{buildingNumber,jdbcType=INTEGER}, #{position,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, \n" +
                "      #{averagePrice,jdbcType=VARCHAR}, #{priceRange,jdbcType=VARCHAR}, #{version,jdbcType=INTEGER}, \n" +
                "      #{creator,jdbcType=VARCHAR}, #{gmtCreated,jdbcType=TIMESTAMP}, #{gmtModified,jdbcType=TIMESTAMP}");
        builder.append(" ) ");
        return builder.toString();
    }


}
