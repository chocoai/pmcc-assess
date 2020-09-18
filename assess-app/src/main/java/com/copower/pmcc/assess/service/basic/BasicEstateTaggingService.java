package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateTaggingDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateTagging;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnit;
import com.copower.pmcc.assess.dto.input.MapDto;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateTaggingGaoDe;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateTaggingVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:54
 * @Description:
 */
@Service
public class BasicEstateTaggingService {
    @Autowired
    private BasicEstateTaggingDao basicEstateTaggingDao;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void clearInvalidData(Integer appId) throws Exception {
        BasicEstateTagging query = new BasicEstateTagging();
        query.setApplyId(appId);
        if (appId == null || appId == 0)
            query.setCreator(commonService.thisUserAccount());
        basicEstateTaggingDao.removeBasicEstateTagging(query);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicEstateTagging getBasicEstateTaggingById(Integer id) {
        return basicEstateTaggingDao.getBasicEstateTaggingById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addBasicEstateTagging(BasicEstateTagging basicEstateTagging) throws Exception {
        //先清除标记
        BasicEstateTagging where = new BasicEstateTagging();
        where.setCreator(commonService.thisUserAccount());
        where.setTableId(basicEstateTagging.getTableId());
        where.setType(basicEstateTagging.getType());
        basicEstateTaggingDao.removeBasicEstateTagging(where);

        basicEstateTagging.setCreator(commonService.thisUserAccount());
        basicEstateTaggingDao.addBasicEstateTagging(basicEstateTagging);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addBasicEstateTaggingByTableId(BasicEstateTagging basicEstateTagging) throws Exception {
        //先清除标记
        BasicEstateTagging where = new BasicEstateTagging();
        where.setTableId(basicEstateTagging.getTableId());
        where.setType(basicEstateTagging.getType());
        basicEstateTaggingDao.removeBasicEstateTagging(where);

        basicEstateTagging.setCreator(commonService.thisUserAccount());
        basicEstateTaggingDao.addBasicEstateTagging(basicEstateTagging);
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicEstateTagging(Integer id) throws Exception {
        return basicEstateTaggingDao.deleteBasicEstateTagging(id);
    }

    public boolean updateBasicEstateTagging(BasicEstateTagging basicEstateTagging, boolean updateNull) {
        if (updateNull) {
            BasicEstateTagging estateTagging = basicEstateTaggingDao.getBasicEstateTaggingById(basicEstateTagging.getId());
            if (estateTagging != null) {
                basicEstateTagging.setBisDelete(estateTagging.getBisDelete());
                basicEstateTagging.setCreator(estateTagging.getCreator());
                basicEstateTagging.setGmtCreated(estateTagging.getGmtCreated());
                basicEstateTagging.setGmtModified(DateUtils.now());
            }
        }
        return basicEstateTaggingDao.updateBasicEstateTagging(basicEstateTagging, updateNull);
    }

    /**
     * 获取数据列表
     *
     * @param basicEstateTagging
     * @return
     * @throws Exception
     */
    public List<BasicEstateTagging> getBasicEstateTaggingList(BasicEstateTagging basicEstateTagging) throws Exception {
        return basicEstateTaggingDao.getBasicEstateTaggingList(basicEstateTagging);
    }

    public List<BasicEstateTaggingVo> getEstateTaggingList(Integer applyId, String type) {
        BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
        if (applyId == null || applyId == 0)
            basicEstateTagging.setCreator(commonService.thisUserAccount());
        basicEstateTagging.setApplyId(applyId);
        basicEstateTagging.setType(type);
        return basicEstateTaggingDao.getBasicEstateTaggingList(basicEstateTagging).stream().map(oo -> getBasicEstateTaggingVo(oo)).collect(Collectors.toList());
    }


    public List<BasicEstateTaggingVo> getApplyBatchEstateTaggingsByTableId(Integer tableId, String type) {
        if (tableId == null || StringUtils.isBlank(type)) return null;
        if (BasicFormClassifyEnum.BUILDING.getKey().equals(type)) {
            BasicApplyBatchDetail detail = basicApplyBatchDetailService.getBasicApplyBatchDetail(BasicFormClassifyEnum.BUILDING.getTableName(), tableId);
            if (detail != null && detail.getPid() > 0) {
                BasicApplyBatchDetail parentDetail = basicApplyBatchDetailService.getDataById(detail.getPid());
                if (parentDetail != null && BasicFormClassifyEnum.BUILDING.getTableName().equals(parentDetail.getTableName()) && parentDetail.getTableId() != null) {
                    tableId = parentDetail.getTableId();
                }
            }
        }
        BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
        basicEstateTagging.setTableId(tableId);
        basicEstateTagging.setType(type);
        return basicEstateTaggingDao.getBasicEstateTaggingList(basicEstateTagging).stream().map(oo -> getBasicEstateTaggingVo(oo)).collect(Collectors.toList());
    }

    public void removeBasicEstateTagging(BasicEstateTagging basicEstateTagging) throws Exception {
        basicEstateTaggingDao.removeBasicEstateTagging(basicEstateTagging);
    }

    public Boolean hasBasicEstateTagging(Integer tableId, BasicFormClassifyEnum estateTaggingTypeEnum) {
        BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
        basicEstateTagging.setTableId(tableId);
        basicEstateTagging.setType(estateTaggingTypeEnum.getKey());
        return basicEstateTaggingDao.getEstateTaggingCount(basicEstateTagging) > 0;
    }

    /**
     * 获取单元下的标注
     *
     * @param unitPartInMode
     * @param tableId
     * @param caseUnitId
     * @return
     */
    public BasicEstateTagging getUnitTagging(String unitPartInMode, Integer tableId, Integer caseUnitId) throws Exception {
        BasicEstateTagging tagging = new BasicEstateTagging();
        if (StringUtils.isBlank(unitPartInMode)) {

        } else {
            BasicEstateTagging where = new BasicEstateTagging();
            where.setTableId(tableId);
            where.setType(BasicFormClassifyEnum.UNIT.getKey());

            List<BasicEstateTagging> taggingList = basicEstateTaggingDao.getBasicEstateTaggingList(where);
            if (!CollectionUtils.isEmpty(taggingList))
                tagging = taggingList.get(0);
        }
        return tagging;
    }


    /**
     * 获取单元下的标注
     *
     * @param houseTableId
     * @return
     */
    public BasicEstateTagging getUnitTaggingByHouseTableId(Integer houseTableId) throws Exception {
        BasicApplyBatchDetail basicApplyBatchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail("tb_basic_house", houseTableId);
        BasicUnit basicUnit = basicApplyBatchDetailService.getBasicUnitByBatchDetailId(basicApplyBatchDetail.getPid());
        List<BasicEstateTaggingVo> taggings = getApplyBatchEstateTaggingsByTableId(basicUnit.getId(), "unit");
        if (CollectionUtils.isEmpty(taggings)) return null;
        return taggings.get(0);
    }

    /**
     * 删除房屋下标注信息
     *
     * @param tableId
     */
    public void deleteHouseTagging(Integer tableId) throws SQLException {
        BasicEstateTagging where = new BasicEstateTagging();
        where.setTableId(tableId);
        where.setType(BasicFormClassifyEnum.HOUSE.getKey());

        List<BasicEstateTagging> taggingList = basicEstateTaggingDao.getBasicEstateTaggingList(where);
        if (CollectionUtils.isEmpty(taggingList)) return;
        taggingList.forEach(o -> basicEstateTaggingDao.deleteBasicEstateTagging(o.getId()));
    }

    public BasicEstateTaggingVo getBasicEstateTaggingVo(BasicEstateTagging oo) {
        if (oo == null) {
            return null;
        }
        BasicEstateTaggingVo vo = new BasicEstateTaggingVo();
        BeanUtils.copyProperties(oo, vo);
        if (StringUtils.isNotEmpty(oo.getPathArray())) {
            try {
                List<BasicEstateTaggingGaoDe> gaoDeList = JSON.parseArray(oo.getPathArray(), BasicEstateTaggingGaoDe.class);
                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(gaoDeList)) {
                    vo.getGaoDeList().addAll(gaoDeList);
                }
            } catch (Exception e) {
            }
        }
        return vo;
    }

    /**
     * 拷贝
     *
     * @param taggingTypeEnum
     * @param sourceId
     * @param targetId
     * @throws Exception
     */
    public void copyTagging(BasicFormClassifyEnum taggingTypeEnum, Integer sourceId, Integer targetId) throws Exception {
        BasicEstateTagging oldBasicEstateTagging = new BasicEstateTagging();
        oldBasicEstateTagging.setTableId(sourceId);
        oldBasicEstateTagging.setType(taggingTypeEnum.getKey());
        List<BasicEstateTagging> oldBasicEstateTaggingList = getBasicEstateTaggingList(oldBasicEstateTagging);
        if (!ObjectUtils.isEmpty(oldBasicEstateTaggingList)) {
            for (BasicEstateTagging tagging : oldBasicEstateTaggingList) {
                BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
                BeanUtils.copyProperties(tagging, basicEstateTagging);
                basicEstateTagging.setId(null);
                basicEstateTagging.setTableId(targetId);
                basicEstateTagging.setCreator(commonService.thisUserAccount());
                basicEstateTagging.setGmtCreated(null);
                basicEstateTagging.setGmtModified(null);
                addBasicEstateTagging(basicEstateTagging);
            }
        }
    }

    public List<MapDto> mapDtoList(Integer dataId, String type) throws Exception {
        List<MapDto> mapDtoList = new ArrayList<MapDto>(10);
        BasicEstateTagging query = new BasicEstateTagging();
        if (dataId != null) {
            query.setTableId(dataId);
        }
        if (StringUtils.isNotBlank(type)) {
            query.setType(type);
        }
        List<BasicEstateTagging> taggingList = getBasicEstateTaggingList(query);
        if (!ObjectUtils.isEmpty(taggingList)) {
            for (BasicEstateTagging tagging : taggingList) {
                if (!NumberUtils.isNumber(tagging.getLat()) || !NumberUtils.isNumber(tagging.getLng())) {
                    continue;
                }
                MapDto mapDto = new MapDto();
                mapDto.setLat(new BigDecimal(tagging.getLat())).setLon(new BigDecimal(tagging.getLng())).setId(tagging.getId()).setType(tagging.getType()).setDataId(tagging.getTableId());
                if (StringUtils.isNotBlank(tagging.getName())) {
                    mapDto.setName(tagging.getName());
                }
                mapDtoList.add(mapDto);
            }
        }
        return mapDtoList;
    }

}
