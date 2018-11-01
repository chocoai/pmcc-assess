package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.cases.dao.CaseUnitDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnit;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitDecorate;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitElevator;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitHuxing;
import com.copower.pmcc.assess.dto.output.cases.CaseUnitHuxingVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Ordering;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void initAndUpdateSon(Integer id){
        final int num = 0;
        CaseUnitDecorate caseUnitDecorate = new CaseUnitDecorate();
        caseUnitDecorate.setUnitId(num);
        CaseUnitElevator caseUnitElevator = new CaseUnitElevator();
        caseUnitElevator.setUnitId(num);
        CaseUnitHuxing caseUnitHuxing = new CaseUnitHuxing();
        caseUnitHuxing.setUnitId(num);
        List<CaseUnitDecorate> caseUnitDecorates = caseUnitDecorateService.getCaseUnitDecorateList(caseUnitDecorate);
        List<CaseUnitElevator> caseUnitElevators = caseUnitElevatorService.getEstateNetworkLists(caseUnitElevator);
        List<CaseUnitHuxingVo> caseUnitHuxings = caseUnitHuxingService.getCaseUnitHuxingList(caseUnitHuxing);
        if (id==null){
            if (!ObjectUtils.isEmpty(caseUnitDecorates)){
                for (CaseUnitDecorate oo:caseUnitDecorates){
                    caseUnitDecorateService.deleteCaseUnitDecorate(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseUnitElevators)){
                for (CaseUnitElevator oo:caseUnitElevators){
                    caseUnitElevatorService.deleteEstateNetwork(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseUnitHuxings)){
                for (CaseUnitHuxingVo oo:caseUnitHuxings){
                    caseUnitHuxingService.deleteCaseUnitHuxing(oo.getId());
                }
            }
        }

        if (id!=null){
            if (!ObjectUtils.isEmpty(caseUnitDecorates)){
                for (CaseUnitDecorate oo:caseUnitDecorates){
                    oo.setUnitId(id);
                    caseUnitDecorateService.updateCaseUnitDecorate(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseUnitElevators)){
                for (CaseUnitElevator oo:caseUnitElevators){
                    oo.setUnitId(id);
                    caseUnitElevatorService.updateEstateNetwork(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseUnitHuxings)){
                for (CaseUnitHuxingVo oo:caseUnitHuxings){
                    oo.setUnitId(id);
                    caseUnitHuxingService.updateCaseUnitHuxing(oo);
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

    public Integer saveAndUpdateCaseUnit(CaseUnit caseUnit) {
        Integer id = null;
        if (caseUnit.getId() == null || caseUnit.getId().intValue() == 0) {
            caseUnit.setCreator(commonService.thisUserAccount());
            caseUnit.setVersion(0);
            id = caseUnitDao.addUnit(caseUnit);
            this.initAndUpdateSon(id);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseUnit.class), id);
            return  id;
        } else {
            CaseUnit oo = caseUnitDao.getUnitById(caseUnit.getId());
            if (oo != null) {
                if (oo.getVersion() == null) {
                    oo.setVersion(0);
                }
            }
            caseUnit.setVersion(oo.getVersion() + 1);
            caseUnitDao.updateUnit(caseUnit);
            return null;
        }
    }

    public Integer upgradeVersion(CaseUnit caseUnit)throws Exception{
        Integer id = null;
        if (caseUnit.getId() == null || caseUnit.getId().intValue() == 0) {
            caseUnit.setCreator(commonService.thisUserAccount());
            caseUnit.setVersion(0);
            id = caseUnitDao.addUnit(caseUnit);
            this.initAndUpdateSon(id);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseUnit.class), id);
            return  id;
        } else {
            CaseUnit oo = caseUnitDao.getUnitById(caseUnit.getId());
            if (oo != null) {
                if (oo.getVersion() == null) {
                    oo.setVersion(0);
                }
            }
            int version = oo.getVersion() + 1;
            BeanCopyHelp.copyPropertiesIgnoreNull(caseUnit, oo);
            oo.setVersion(version);
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            id = caseUnitDao.addUnit(oo);
            this.initAndUpdateSon(id);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseUnit.class), id);
            caseUnit.setId(id);
            return id;
        }
    }

    public List<CaseUnit> autoCompleteCaseUnit(String unitNumber, Integer caseBuildingMainId, Integer maxRows){
        List<CaseUnit> caseUnitList = null;
        caseUnitList = caseUnitDao.autoCompleteCaseUnit(unitNumber, caseBuildingMainId);
        List<CaseUnit> list = new ArrayList<CaseUnit>(10);
        Ordering<CaseUnit> ordering = Ordering.from(new Comparator<CaseUnit>() {
            @Override
            public int compare(CaseUnit o1, CaseUnit o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).reverse();
        Collections.sort(caseUnitList,ordering);
        if (!ObjectUtils.isEmpty(caseUnitList)) {
            for (int i = 0; i < maxRows; i++) {
                if (i < caseUnitList.size()) {
                    list.add(caseUnitList.get(i));
                }
            }
        }
        return list;
    }

    public boolean deleteCaseUnit(Integer id) {
        return caseUnitDao.deleteUnit(id);
    }
}
