package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.*;
import com.copower.pmcc.assess.dal.basis.dao.data.DataBlockDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseRoomDecorateVo;
import com.copower.pmcc.assess.dto.output.data.DataBlockVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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
 * @Date: 2018/9/11 10:01
 * @Description:基础版块维护
 */
@Service
public class DataBlockService {
    @Autowired
    private DataBlockDao dataBlockDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicEstateStreetInfoService basicEstateStreetInfoService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicApplyDao basicApplyDao;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Integer saveAndUpdateDataBlock(DataBlock dataBlock) {
        if (dataBlock.getId() == null || dataBlock.getId().intValue() == 0) {
            dataBlock.setCreator(commonService.thisUserAccount());
            try {
                return dataBlockDao.addDataBlock(dataBlock);
            } catch (Exception e1) {
                logger.error(e1.getMessage(), e1);
                return null;
            }
        } else {
            dataBlockDao.updateDataBlock(dataBlock);
            return null;
        }
    }

    public DataBlock getDataBlockById(Integer id) {
        if (id == null) {
            logger.error("null point");
            return null;
        }
        return dataBlockDao.getDataBlockById(id);
    }

    public BootstrapTableVo getDataBlockListVos(String province, String city, String district, String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataBlockVo> vos = dataBlockVos(province, city, district, name);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DataBlockVo> dataBlockVos(DataBlock dataBlock) {
        List<DataBlock> dataBlocks = dataBlockDao.getDataBlockList(dataBlock);
        List<DataBlockVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataBlocks)) {
            for (DataBlock landLevel : dataBlocks) {
                vos.add(getDataBlockVo(landLevel));
            }
        }
        return vos;
    }

    public List<DataBlockVo> dataBlockVos(String province, String city, String district, String name) {
        List<DataBlock> dataBlocks = dataBlockDao.getDataBlockList(province, city, district, name);
        List<DataBlockVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataBlocks)) {
            for (DataBlock landLevel : dataBlocks) {
                vos.add(getDataBlockVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDataBlock(DataBlock dataBlock) {
        dataBlockDao.removeDataBlock(dataBlock);
    }

    public DataBlockVo getDataBlockVo(DataBlock dataBlock) {
        DataBlockVo vo = new DataBlockVo();
        BeanUtils.copyProperties(dataBlock, vo);
        if (StringUtils.isNotBlank(dataBlock.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(dataBlock.getProvince()));
        }
        if (StringUtils.isNotBlank(dataBlock.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(dataBlock.getCity()));
        }
        if (StringUtils.isNotBlank(dataBlock.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(dataBlock.getDistrict()));
        }
        return vo;
    }

    /**
     * 根据区域获取版块信息
     *
     * @param province
     * @param city
     * @param distric
     * @return
     */
    public List<DataBlock> getDataBlockListByArea(String province, String city, String distric) {
        if (StringUtils.isBlank(province) || StringUtils.isBlank(city))
            return null;
        DataBlock dataBlock = new DataBlock();
        dataBlock.setProvince(province);
        dataBlock.setCity(city);
        if (StringUtils.isNotBlank(distric))
            dataBlock.setDistrict(distric);
        return dataBlockDao.getDataBlockList(dataBlock);
    }

    /**
     * 版块是否已存在
     *
     * @param province
     * @param city
     * @param distric
     * @param blockName
     * @return
     */
    public Boolean isExistBlock(String province, String city, String distric, String blockName) {
        DataBlock dataBlock = new DataBlock();
        dataBlock.setProvince(province);
        dataBlock.setCity(city);
        if (StringUtils.isNotBlank(distric))
            dataBlock.setDistrict(distric);
        dataBlock.setName(blockName);
        List<DataBlock> blockList = dataBlockDao.getDataBlockList(dataBlock);
        return blockList.size() > 0;
    }


    public void updateOldData(Integer key) throws Exception {
        List<BasicHouse> basicHouseList = basicHouseService.getBasicHouseList(new BasicHouse());
        if (CollectionUtils.isNotEmpty(basicHouseList)) {
            for (BasicHouse basicHouse : basicHouseList) {
                if (key == 3) {
                    List<BasicHouseRoom> basicHouseRoomList = basicHouseRoomService.getBasicHouseRoomList(basicHouse.getId());
                    if (CollectionUtils.isNotEmpty(basicHouseRoomList)) {
                        for (BasicHouseRoom basicHouseRoom : basicHouseRoomList) {
                            BasicHouseRoomDecorate where = new BasicHouseRoomDecorate();
                            where.setRoomId(basicHouseRoom.getId());
                            List<BasicHouseRoomDecorate> houseRoomDecorateList = basicHouseRoomDecorateService.basicHouseRoomDecorateList(where);
                            if (CollectionUtils.isNotEmpty(houseRoomDecorateList)) {
                                for (BasicHouseRoomDecorate basicHouseRoomDecorate : houseRoomDecorateList) {
                                    basicHouseRoomDecorate.setHouseId(basicHouse.getId());
                                    basicHouseRoomDecorateService.saveAndUpdateBasicHouseRoomDecorate(basicHouseRoomDecorate, false);
                                }
                            }
                        }
                    }
                }
                if(key==4){
                    BasicApply where = new BasicApply();
                    where.setBasicHouseId(basicHouse.getId());
                    List<BasicApply> basicApplies = basicApplyDao.getBasicApplyList(where);
                    if (CollectionUtils.isNotEmpty(basicApplies)) {
                        BasicApply apply = basicApplies.get(0);
                        if(StringUtils.isNotBlank(apply.getStructuralInfo()) ){
                            BasicApplyBatchDetail basicApplyBatchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicHouse.class), basicHouse.getId());
                            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(apply.getPlanDetailsId());
                            if (projectPlanDetails != null)
                                apply.setDeclareRecordId(projectPlanDetails.getDeclareRecordId());
                            if(basicApplyBatchDetail!=null){
                                apply.setBatchDetailId(basicApplyBatchDetail.getId());
                                List<BasicApplyBatchDetail> list = Lists.newArrayList();
                                basicApplyBatchDetailService.collectionParentBatchDetails(basicApplyBatchDetail.getId(), list);
                                List<KeyValueDto> keyValueDtos = Lists.newArrayList();
                                for (int i = list.size() - 1; i >= 0; i--) {
                                    BasicApplyBatchDetail batchDetail = list.get(i);
                                    KeyValueDto keyValueDto = new KeyValueDto();
                                    keyValueDto.setKey(batchDetail.getType());
                                    keyValueDto.setValue(String.valueOf(batchDetail.getTableId()));
                                    keyValueDtos.add(keyValueDto);
                                }
                                apply.setStructuralInfo(JSON.toJSONString(keyValueDtos));
                                basicApplyBatchDetail.setDeclareRecordId(projectPlanDetails.getDeclareRecordId());
                                basicApplyBatchDetailService.saveBasicApplyBatchDetail(basicApplyBatchDetail);
                            }
                            basicApplyDao.updateBasicApply(apply);
                        }
                    }
                }
            }
        }
    }
}
