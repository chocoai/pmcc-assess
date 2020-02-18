package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomCaseMapper;
import com.copower.pmcc.assess.dal.cases.dao.CaseEstateTaggingDao;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.input.MapDto;
import com.copower.pmcc.assess.dto.input.cases.CaseEstateTaggingDto;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:54
 * @Description:
 */
@Service
public class CaseEstateTaggingService {
    @Autowired
    private CaseEstateTaggingDao caseEstateTaggingDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private CustomCaseMapper customCaseMapper;

    public List<CaseEstateTaggingDto> queryCaseEstateTagging(Integer dataId, String type) throws Exception {
        List<CaseEstateTaggingDto> caseEstateTaggingDtos = new ArrayList<CaseEstateTaggingDto>(10);
        if (Objects.equal(type, BasicFormClassifyEnum.ESTATE.getKey())) {
            List<CaseBuilding> caseBuildingList = customCaseMapper.screenBuildList(dataId);
            if (!ObjectUtils.isEmpty(caseBuildingList)) {
                for (CaseBuilding main : caseBuildingList) {
                    CaseEstateTaggingDto dto = getCaseEstateTagging(main.getId(), BasicFormClassifyEnum.BUILDING.getKey());
                    if (dto != null) {
                        caseEstateTaggingDtos.add(dto);
                    }
                }
            }
        }
        if (Objects.equal(type, BasicFormClassifyEnum.UNIT.getKey())) {
            List<CaseHouse> caseHouseList = customCaseMapper.screenHouseList(dataId);
            if (!ObjectUtils.isEmpty(caseHouseList)) {
                for (CaseHouse caseHouse : caseHouseList) {
                    CaseEstateTaggingDto dto = getCaseEstateTagging(caseHouse.getId(), BasicFormClassifyEnum.HOUSE.getKey());
                    if (dto != null) {
                        //必要的 (处理为本地图片地址否则无法识别)
                        if (dto.getAttachmentId() != null) {
                            String huxingImg = baseAttachmentService.getViewImageUrl(dto.getAttachmentId());
                            if (StringUtils.isNotBlank(huxingImg)) {
                                dto.setHuxingImg(huxingImg);
                            }
                        }
                        caseEstateTaggingDtos.add(dto);
                    }
                }
            }
        }
        if (Objects.equal(type, BasicFormClassifyEnum.BUILDING.getKey())) {
            List<CaseUnit> caseUnitList = customCaseMapper.screenUnitList(dataId);
            if (!ObjectUtils.isEmpty(caseUnitList)) {
                for (CaseUnit caseUnit : caseUnitList) {
                    CaseEstateTaggingDto dto = getCaseEstateTagging(caseUnit.getId(), BasicFormClassifyEnum.UNIT.getKey());
                    if (dto != null) {
                        caseEstateTaggingDtos.add(dto);
                    }
                }
            }
        }

        return caseEstateTaggingDtos;
    }


    public List<MapDto> mapDtoList(Integer dataId, String type) throws Exception {
        List<MapDto> mapDtoList = new ArrayList<MapDto>(10);
        CaseEstateTagging query = new CaseEstateTagging();
        if (dataId != null) {
            query.setTableId(dataId);
        }
        if (StringUtils.isNotBlank(type)) {
            query.setType(type);
        }
        List<CaseEstateTagging> taggingList = getCaseEstateTaggingList(query);
        if (!ObjectUtils.isEmpty(taggingList)) {
            for (CaseEstateTagging tagging : taggingList) {
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

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public CaseEstateTagging getCaseEstateTaggingById(Integer id) throws Exception {
        return caseEstateTaggingDao.getCaseEstateTaggingById(id);
    }


    /**
     * 新增或者修改
     *
     * @param caseEstateTagging
     * @return
     * @throws Exception
     */
    public void saveCaseEstateTagging(CaseEstateTagging caseEstateTagging) throws Exception {
        if (caseEstateTagging.getId() == null) {
            caseEstateTagging.setCreator(commonService.thisUserAccount());
            caseEstateTaggingDao.saveCaseEstateTagging(caseEstateTagging);
        } else {
            caseEstateTaggingDao.updateCaseEstateTagging(caseEstateTagging);
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteCaseEstateTagging(Integer id) throws Exception {
        return caseEstateTaggingDao.deleteCaseEstateTagging(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseEstateTagging
     * @return
     * @throws Exception
     */
    public List<CaseEstateTagging> getCaseEstateTaggingList(CaseEstateTagging caseEstateTagging) throws Exception {
        return caseEstateTaggingDao.caseEstateTaggingList(caseEstateTagging);
    }

    public void removeCaseEstateTagging(CaseEstateTagging caseEstateTagging) throws Exception {
        caseEstateTaggingDao.removeCaseEstateTagging(caseEstateTagging);
    }

    public BootstrapTableVo getEstateTaggingList(Integer dataId, String type) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        CaseEstateTagging where = new CaseEstateTagging();
        where.setTableId(dataId);
        where.setType(type);
        List<CaseEstateTagging> caseEstateTaggingList = caseEstateTaggingDao.caseEstateTaggingList(where);
        vo.setTotal((long) caseEstateTaggingList.size());
        vo.setRows(ObjectUtils.isEmpty(caseEstateTaggingList) ? Lists.newArrayList() : caseEstateTaggingList);
        return vo;
    }

    public CaseEstateTaggingDto getCaseEstateTagging(Integer dataId, String type) throws Exception {
        if (dataId == null) {
            return null;
        }
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        CaseEstateTagging query = new CaseEstateTagging();
        query.setTableId(dataId);
        query.setType(type);
        List<CaseEstateTagging> caseEstateTaggingList = caseEstateTaggingDao.caseEstateTaggingList(query);
        if (!ObjectUtils.isEmpty(caseEstateTaggingList)) {
            CaseEstateTaggingDto dto = new CaseEstateTaggingDto();
            org.springframework.beans.BeanUtils.copyProperties(caseEstateTaggingList.get(0), dto);
            return dto;
        } else {
            return null;
        }
    }

}
