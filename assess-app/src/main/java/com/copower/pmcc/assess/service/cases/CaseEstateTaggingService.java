package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.dal.cases.custom.mapper.CustomCaseMapper;
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
import java.util.Collections;
import java.util.Comparator;
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
    private CaseEstateService caseEstateService;
    @Autowired
    private CaseBuildingMainService caseBuildingMainService;
    @Autowired
    private CaseUnitService caseUnitService;
    @Autowired
    private CaseHouseService caseHouseService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private CustomCaseMapper customCaseMapper;


    public List<CaseEstateTagging> queryCaseEstateTagging(Integer dataId, String type) throws Exception {
        List<CaseEstateTagging> caseEstateTaggingList = new ArrayList<CaseEstateTagging>(10);
        if (Objects.equal(type, EstateTaggingTypeEnum.ESTATE.getKey())) {
            CaseEstate caseEstate = caseEstateService.getCaseEstateById(dataId);
            if (caseEstate != null) {
                CaseBuildingMain queryMain = new CaseBuildingMain();
                queryMain.setEstateId(caseEstate.getId());
                List<CaseBuildingMain> mainList = caseBuildingMainService.getCaseBuildingMainList(queryMain);
                if (!ObjectUtils.isEmpty(mainList)) {
                    for (CaseBuildingMain caseBuildingMain : mainList) {
                        CaseEstateTagging buildTagging = getCaseEstateTagging(caseBuildingMain.getId(), EstateTaggingTypeEnum.BUILDING.getKey());
                        if (buildTagging != null) {
                            caseEstateTaggingList.add(buildTagging);
                        }
                    }
                }
            }
        }
        if (Objects.equal(type, EstateTaggingTypeEnum.BUILDING.getKey())) {
            CaseBuildingMain main = caseBuildingMainService.getCaseBuildingMainById(dataId);
            CaseUnit queryUnit = new CaseUnit();
            queryUnit.setBuildingMainId(main.getId());
            List<CaseUnit> caseUnitList = caseUnitService.getCaseUnitList(queryUnit);
            if (!ObjectUtils.isEmpty(caseUnitList)) {
                for (CaseUnit caseUnit : caseUnitList) {
                    CaseEstateTagging buildTagging = getCaseEstateTagging(caseUnit.getId(), EstateTaggingTypeEnum.UNIT.getKey());
                    if (buildTagging != null) {
                        caseEstateTaggingList.add(buildTagging);
                    }
                }
            }
        }
        return caseEstateTaggingList;
    }


    public List<MapDto> mapDtoList(Integer dataId, String type) throws Exception {
        List<MapDto> mapDtoList = new ArrayList<MapDto>(10);
        CaseEstateTagging query = new CaseEstateTagging();
        if (dataId != null) {
            query.setDataId(dataId);
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
                mapDto.setLat(new BigDecimal(tagging.getLat())).setLon(new BigDecimal(tagging.getLng())).setId(tagging.getId()).setType(tagging.getType()).setDataId(tagging.getDataId());
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
        where.setDataId(dataId);
        where.setType(type);
        List<CaseEstateTagging> caseEstateTaggingList = caseEstateTaggingDao.caseEstateTaggingList(where);
        vo.setTotal((long) caseEstateTaggingList.size());
        vo.setRows(ObjectUtils.isEmpty(caseEstateTaggingList) ? Lists.newArrayList() : caseEstateTaggingList);
        return vo;
    }

    public CaseEstateTagging getCaseEstateTagging(Integer dataId, String type) throws Exception {
        if (dataId == null) {
            return null;
        }
        if (StringUtils.isEmpty(type)) {
            return null;
        }
        CaseEstateTagging query = new CaseEstateTagging();
        query.setDataId(dataId);
        query.setType(type);
        List<CaseEstateTagging> caseEstateTaggingList = caseEstateTaggingDao.caseEstateTaggingList(query);
        if (!ObjectUtils.isEmpty(caseEstateTaggingList)) {
            return caseEstateTaggingList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 获取标记的经纬度
     *
     * @param estateId
     * @return
     * @throws Exception
     */
    public CaseEstateTaggingDto getCaseEstateTaggingDto(Integer estateId) throws Exception {
        CaseEstate caseEstate = caseEstateService.getCaseEstateById(estateId);
        if (caseEstate == null) {
            return null;
        }
        CaseEstateTaggingDto dto = new CaseEstateTaggingDto();
        //找到楼盘对应的标记信息数据
        CaseEstateTagging estateTagging = getCaseEstateTagging(caseEstate.getId(), EstateTaggingTypeEnum.ESTATE.getKey());
        if (estateTagging != null) {
            org.springframework.beans.BeanUtils.copyProperties(estateTagging, dto);
            CaseBuildingMain queryMain = new CaseBuildingMain();
            queryMain.setEstateId(caseEstate.getId());
            //楼栋数据列表
            List<CaseBuildingMain> mainList = customCaseMapper.screenBuildList(estateId);

            //接着找出相同的
            if (!ObjectUtils.isEmpty(mainList)) {
                for (CaseBuildingMain main : mainList) {
                    //找到楼栋数据对应的标记信息数据
                    CaseEstateTagging buildTagging = getCaseEstateTagging(main.getId(), EstateTaggingTypeEnum.BUILDING.getKey());
                    if (buildTagging != null) {
                        CaseEstateTaggingDto buildTaggingDto = new CaseEstateTaggingDto();
                        org.springframework.beans.BeanUtils.copyProperties(buildTagging, buildTaggingDto);
                        dto.getChildren().add(buildTaggingDto);
                    }
                }
            }
        }
        return dto;
    }


    @Deprecated
    private boolean parseBuild(List<CaseBuildingMain> mainList) {
        boolean flag = false;
        flag = this.checkCaseBuildingMain(mainList);
        if (!flag) {
            //如果检测到列表中没有相同的 那么跳出递归
            return flag;
        } else {

            for (CaseBuildingMain main : mainList) {

                for (int i = 0; i < mainList.size(); i++) {
                    int n1 = Integer.parseInt(main.getBuildingNumber());
                    int n2 = Integer.parseInt(mainList.get(i).getBuildingNumber());
                    int v1 = main.getVersion().intValue();
                    int v2 = mainList.get(i).getVersion().intValue();
                    if (n1 == n2 && main.hashCode() != mainList.get(i).hashCode()) {
                        //那么比较两者的版本大小(默认是舍去版本小的)

                        if (v1 >= v2) {
                            mainList.remove(mainList.get(i));
                            //马上重新开始方法
                            this.parseBuild(mainList);
                            //直到不在存在相同号码为止
                        } else {
                            mainList.remove(main);
                            this.parseBuild(mainList);
                            //直到不在存在相同号码为止
                        }
                    }
                }

            }
        }
        return flag;
    }



    /**
     * 检测列表中是否还有相同的
     *
     * @param mainList
     * @return
     */
    @Deprecated
    private boolean checkCaseBuildingMain(List<CaseBuildingMain> mainList) {
        boolean flag = false;
        outer:
        for (CaseBuildingMain main : mainList) {
            for (CaseBuildingMain caseBuildingMain : mainList) {
                //当检测到相同的号码时马上跳出循环
                if (Integer.parseInt(main.getBuildingNumber()) == Integer.parseInt(caseBuildingMain.getBuildingNumber()) && main.hashCode() != caseBuildingMain.hashCode()) {
                    flag = true;
                    break outer;
                }
            }
        }
        return flag;
    }
}
