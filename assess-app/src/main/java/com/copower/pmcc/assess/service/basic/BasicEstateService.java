package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicEstateDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstate;
import com.copower.pmcc.assess.dal.basis.entity.DataBlock;
import com.copower.pmcc.assess.dal.basis.entity.DataDeveloper;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevel;
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
import com.google.common.collect.Ordering;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
            Integer id = basicEstateDao.saveBasicEstate(basicEstate);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class), id);
            return  id ;
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
        return  id ;
    }

    public Integer upgradeVersion(BasicEstate basicEstate)throws Exception{
        //无 id 情况下save
        if (basicEstate.getId() == null || basicEstate.getId().intValue() == 0) {
            basicEstate.setCreator(commonService.thisUserAccount());
            //有id情况下 save
        }else {
            basicEstate.setCreator(commonService.thisUserAccount());
            //不再使用这种方式
            ddlMySqlAssist.insertBasicEstate(basicEstate);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class), basicEstate.getId());
        }
        if (basicEstate.getVersion() == null) {
            basicEstate.setVersion(0);
        }
        Integer id = basicEstateDao.saveBasicEstate(basicEstate);
        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class), id);
        return  id ;
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

    public List<BasicEstate> autoComplete(BasicEstate basicEstate) throws Exception {
        List<BasicEstate> basicEstates = basicEstateDao.autoComplete(basicEstate);
        Ordering<BasicEstate> ordering = Ordering.from(new Comparator<BasicEstate>() {
            @Override
            public int compare(BasicEstate o1, BasicEstate o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).reverse();
        Collections.sort(basicEstates, ordering);
        return basicEstateDao.autoComplete(basicEstate);
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
        if (basicEstate.getLandLevel() != null) {
            DataLandLevel dataLandLevel = dataLandLevelService.getDataLandLevelById(basicEstate.getLandLevel());
            if (dataLandLevel != null) {
                vo.setLandLevelName(dataLandLevel.getLeve());
            }
        }
        return vo;
    }

    public final static String insertSql(){
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(FormatUtils.entityNameConvertToTableName(BasicEstate.class)); ;

        builder.append(" (").append("id, apply_id, province, city, \n" +
                "      district, block_id, developer_id, \n" +
                "      name, street, number, \n" +
                "      land_level, attach_number, floor_area, \n" +
                "      cover_an_area, volumetric_rate, greening_rate, \n" +
                "      building_number, position, description, \n" +
                "      average_price, price_range, version, \n" +
                "      creator, gmt_created, gmt_modified").append(") ");
        builder.append("values (") ;
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
