package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.dal.cases.dao.CaseEstateTaggingDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateTagging;
import com.copower.pmcc.assess.dto.input.MapDto;
import com.copower.pmcc.assess.dto.input.cases.CaseEstateTaggingDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Lists;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    public List<MapDto> mapDtoList(Integer estateId, String type) throws Exception {
        List<MapDto> mapDtoList = new ArrayList<MapDto>(10);
        CaseEstateTagging query = new CaseEstateTagging();
        if (estateId != null) {
            query.setEstateId(estateId);
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
                mapDto.setLat(new BigDecimal(tagging.getLat())).setLon(new BigDecimal(tagging.getLng())).setId(tagging.getId()).setType(tagging.getType()).setEstateId(tagging.getEstateId());
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
        caseEstateTaggingDao.saveCaseEstateTagging(caseEstateTagging);
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

    public BootstrapTableVo getEstateTaggingList(Integer estateId) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        CaseEstateTagging where = new CaseEstateTagging();
        where.setEstateId(estateId);
        List<CaseEstateTagging> caseEstateTaggingList = caseEstateTaggingDao.caseEstateTaggingList(where);
        vo.setTotal((long) caseEstateTaggingList.size());
        vo.setRows(ObjectUtils.isEmpty(caseEstateTaggingList) ? Lists.newArrayList() : caseEstateTaggingList);
        return vo;
    }

    public CaseEstateTaggingDto getCaseEstateTaggingDto(CaseEstateTagging caseEstateTagging)throws Exception {
        if (caseEstateTagging == null) {
            return null;
        }
        CaseEstateTaggingDto dto = new CaseEstateTaggingDto();
        org.springframework.beans.BeanUtils.copyProperties(caseEstateTagging, dto);
        if (caseEstateTagging.getType().equals(EstateTaggingTypeEnum.ESTATE.getKey())) {
            CaseEstateTagging queryA = new CaseEstateTagging();
            queryA.setType(EstateTaggingTypeEnum.BUILDING.getKey());
            queryA.setEstateId(caseEstateTagging.getEstateId());
            List<CaseEstateTagging> caseEstateTaggings = caseEstateTaggingDao.caseEstateTaggingList(queryA);
            if (!org.springframework.util.ObjectUtils.isEmpty(caseEstateTaggings)){
                for (CaseEstateTagging tagging:caseEstateTaggings){
                    CaseEstateTaggingDto taggingDto = new CaseEstateTaggingDto();
                    org.springframework.beans.BeanUtils.copyProperties(tagging,taggingDto);
                    CaseEstateTagging queryB = new CaseEstateTagging();
                    queryB.setEstateId(caseEstateTagging.getEstateId());
                    queryB.setType(EstateTaggingTypeEnum.UNIT.getKey());
                    List<CaseEstateTagging> taggingList = caseEstateTaggingDao.caseEstateTaggingList(queryB);
                    if (!org.springframework.util.ObjectUtils.isEmpty(taggingList)){
                        for (CaseEstateTagging estateTagging:taggingList){
                            CaseEstateTaggingDto caseEstateTaggingDto = new CaseEstateTaggingDto();
                            org.springframework.beans.BeanUtils.copyProperties(estateTagging,caseEstateTaggingDto);
                            CaseEstateTagging queryC = new CaseEstateTagging();
                            queryC.setEstateId(caseEstateTagging.getEstateId());
                            //HOUSE type 或者以后非ESTATE,非BUILDING,非UNIT 的bean
                            queryC.setType(EstateTaggingTypeEnum.HOUSE.getKey());
                            List<CaseEstateTagging> caseEstateTaggingList = caseEstateTaggingDao.caseEstateTaggingList(queryC);
                            if (!org.springframework.util.ObjectUtils.isEmpty(caseEstateTaggingList)){
                                for (CaseEstateTagging tagging1:caseEstateTaggingList){
                                    CaseEstateTaggingDto dto1 = new CaseEstateTaggingDto();
                                    org.springframework.beans.BeanUtils.copyProperties(tagging1,dto1);
                                    caseEstateTaggingDto.getChildren().add(dto1);
                                }
                            }
                            taggingDto.getChildren().add(caseEstateTaggingDto);
                        }
                    }
                    dto.getChildren().add(taggingDto);
                }
            }
        }
        return dto;
    }

}
