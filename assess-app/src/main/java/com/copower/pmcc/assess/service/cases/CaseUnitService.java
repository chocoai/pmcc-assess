package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.dal.cases.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.dao.CaseUnitDao;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.cases.CaseUnitHuxingVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 14:39
 * @Description:案例 单元信息
 */
@Service
public class CaseUnitService {
    @Autowired
    private CaseUnitDao caseUnitDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private CaseUnitDecorateService caseUnitDecorateService;
    @Autowired
    private CaseUnitElevatorService caseUnitElevatorService;
    @Autowired
    private CaseUnitHuxingService caseUnitHuxingService;
    @Autowired
    private CaseHouseService caseHouseService;
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void initAndUpdateSon(Integer oldId,Integer newId){
        CaseUnitDecorate caseUnitDecorate = new CaseUnitDecorate();
        caseUnitDecorate.setUnitId(oldId);
        CaseUnitElevator caseUnitElevator = new CaseUnitElevator();
        caseUnitElevator.setUnitId(oldId);
        CaseUnitHuxing caseUnitHuxing = new CaseUnitHuxing();
        caseUnitHuxing.setUnitId(oldId);
        CaseHouse queryHouse = new CaseHouse();
        queryHouse.setUnitId(oldId);
        List<CaseUnitDecorate> caseUnitDecorates = caseUnitDecorateService.getCaseUnitDecorateList(caseUnitDecorate);
        List<CaseUnitElevator> caseUnitElevators = caseUnitElevatorService.getUnitElevatorList(caseUnitElevator);
        List<CaseUnitHuxingVo> caseUnitHuxings = caseUnitHuxingService.getCaseUnitHuxingList(caseUnitHuxing);
        List<CaseHouse> caseHouseList = caseHouseService.getCaseHouseList(queryHouse);
        if (oldId==null){
            if (!ObjectUtils.isEmpty(caseUnitDecorates)){
                for (CaseUnitDecorate oo:caseUnitDecorates){
                    caseUnitDecorateService.deleteCaseUnitDecorate(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseUnitElevators)){
                for (CaseUnitElevator oo:caseUnitElevators){
                    caseUnitElevatorService.deleteUnitElevator(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseUnitHuxings)){
                for (CaseUnitHuxingVo oo:caseUnitHuxings){
                    caseUnitHuxingService.deleteCaseUnitHuxing(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseList)){
                for (CaseHouse oo:caseHouseList){
                    caseHouseService.deleteCaseHouse(oo.getId());
                }
            }
        }

        if (oldId!=null){
            if (!ObjectUtils.isEmpty(caseUnitDecorates)){
                for (CaseUnitDecorate oo:caseUnitDecorates){
                    oo.setUnitId(newId);
                    caseUnitDecorateService.updateCaseUnitDecorate(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseUnitElevators)){
                for (CaseUnitElevator oo:caseUnitElevators){
                    oo.setUnitId(newId);
                    caseUnitElevatorService.updateUnitElevator(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseUnitHuxings)){
                for (CaseUnitHuxingVo oo:caseUnitHuxings){
                    oo.setUnitId(newId);
                    caseUnitHuxingService.updateCaseUnitHuxing(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseHouseList)){
                for (CaseHouse oo:caseHouseList){
                    oo.setUnitId(newId);
                    caseHouseService.saveAndUpdateCaseHouse(oo);
                }
            }
        }
    }

    public BootstrapTableVo getCaseUnitListVos(CaseUnit caseUnit) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseUnit> caseUnits = getCaseUnitList(caseUnit);
        vo.setRows(caseUnits);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<CaseUnit> getCaseUnitList(CaseUnit caseUnit) {
        return caseUnitDao.getUnitList(caseUnit);
    }

    public CaseUnit getCaseUnitById(Integer id) {
        return caseUnitDao.getUnitById(id);
    }

    public Integer getVersion(Integer id){
        if(id==null) return 0;
        CaseUnit caseUnit = caseUnitDao.getUnitById(id);
        if(caseUnit==null) return 0;
        return caseUnit.getVersion();
    }


    public Integer saveAndUpdateCaseUnit(CaseUnit caseUnit) {
        Integer id = null;
        if (caseUnit.getId() == null || caseUnit.getId().intValue() == 0) {
            id = caseUnitDao.addUnit(caseUnit);
            return  id;
        } else {
            caseUnitDao.updateUnit(caseUnit);
            return null;
        }
    }

    public int updateBuildingId(Integer oldBuildingId, Integer newBuildingId){
        return caseUnitDao.updateBuildingMainId(oldBuildingId, newBuildingId);
    }

    public List<CustomCaseEntity> autoCompleteCaseUnit(String unitNumber, Integer caseBuildingId){
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CustomCaseEntity> caseEntityList = caseUnitDao.getLatestVersionUnitList(unitNumber, caseBuildingId);
        return caseEntityList;
    }

    public boolean deleteCaseUnit(Integer id) {
        return caseUnitDao.deleteUnit(id);
    }


    /**
     * 是否有单元
     * @param unitNumber
     * @param caseBuildingId
     * @return
     */
    public boolean hasUnit(String unitNumber, Integer caseBuildingId){
        return caseUnitDao.getUnitCount(unitNumber, caseBuildingId) > 0;
    }

    public CaseEstateTagging getCaseEstateTaggingByUnitId(Integer unitId)throws Exception{
        CaseEstateTagging query = new CaseEstateTagging();
        query.setDataId(unitId);
        query.setType(EstateTaggingTypeEnum.UNIT.getKey());
        List<CaseEstateTagging> caseEstateTaggingList = caseEstateTaggingService.getCaseEstateTaggingList(query);
        if (!ObjectUtils.isEmpty(caseEstateTaggingList)){
            return caseEstateTaggingList.get(0);
        }
        return null;
    }
}
