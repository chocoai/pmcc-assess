package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.cases.dao.CaseEstateDao;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.cases.CaseEstateVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 14:41
 * @Description:
 */
@Service
public class CaseEstateService {
    @Autowired
    private CaseEstateDao caseEstateDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private CaseEstateParkingService caseEstateParkingService;
    @Autowired
    private CaseEstateSupplyService caseEstateSupplyService;
    @Autowired
    private CaseEstateNetworkService caseEstateNetworkService;
    @Autowired
    private CaseMatchingTrafficService caseMatchingTrafficService;
    @Autowired
    private CaseMatchingMedicalService caseMatchingMedicalService;
    @Autowired
    private CaseMatchingLeisurePlaceService caseMatchingLeisurePlaceService;
    @Autowired
    private CaseMatchingMaterialService caseMatchingMaterialService;
    @Autowired
    private CaseMatchingFinanceService caseMatchingFinanceService;
    @Autowired
    private CaseMatchingEnvironmentService caseMatchingEnvironmentService;
    @Autowired
    private CaseMatchingEducationService caseMatchingEducationService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public BootstrapTableVo getCaseEstateVos(CaseEstate caseEstate){
        List<CaseEstateVo> vos = Lists.newArrayList();
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseEstate> caseEstateList = caseEstateDao.autoCompleteCaseEstate(caseEstate.getName(),caseEstate.getProvince(),caseEstate.getCity(),caseEstate.getDistrict());
        if (!ObjectUtils.isEmpty(caseEstateList)){
            for (CaseEstate oo:caseEstateList){
                vos.add(getCaseEstateVo(oo));
            }
        }
        vo.setRows(vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<CaseEstate> getCaseEstateList(CaseEstate caseEstate) {

        return caseEstateDao.getEstateList(caseEstate);
    }

    public CaseEstate getCaseEstateById(Integer id) {

        return caseEstateDao.getEstateById(id);
    }

    /**
     *
     * 功能描述: 初始化子类
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/17 15:12
     */
    public void initAndUpdateSon(Integer id){
        CaseEstateParking estateParking = new CaseEstateParking();
        estateParking.setEstateId(0);
        CaseEstateNetwork caseEstateNetwork = new CaseEstateNetwork();
        caseEstateNetwork.setEstateId(0);
        CaseEstateSupply caseEstateSupply = new CaseEstateSupply();
        caseEstateSupply.setEstateId(0);
        CaseMatchingTraffic caseMatchingTraffic = new CaseMatchingTraffic();
        caseMatchingTraffic.setEstateId(0);
        CaseMatchingMedical caseMatchingMedical = new CaseMatchingMedical();
        caseMatchingMedical.setEstateId(0);
        CaseMatchingMaterial caseMatchingMaterial = new CaseMatchingMaterial();
        caseMatchingMaterial.setEstateId(0);
        CaseMatchingLeisurePlace caseMatchingLeisurePlace = new CaseMatchingLeisurePlace();
        caseMatchingLeisurePlace.setEstateId(0);
        CaseMatchingFinance caseMatchingFinance = new CaseMatchingFinance();
        caseMatchingFinance.setEstateId(0);
        CaseMatchingEnvironment caseMatchingEnvironment = new CaseMatchingEnvironment();
        caseMatchingEnvironment.setEstateId(0);
        CaseMatchingEducation caseMatchingEducation = new CaseMatchingEducation() ;
        caseMatchingEducation.setEstateId(0);
        List<CaseEstateParking> caseEstateParkings = caseEstateParkingService.getEstateParkingList(estateParking);
        List<CaseEstateNetwork> caseEstateNetworks = caseEstateNetworkService.getEstateNetworkLists(caseEstateNetwork);
        List<CaseEstateSupply> caseEstateSupplies = caseEstateSupplyService.getCaseEstateSupplyList(caseEstateSupply);
        List<CaseMatchingTraffic> caseMatchingTraffics = caseMatchingTrafficService.getMatchingTrafficList(caseMatchingTraffic);
        List<CaseMatchingMedical> caseMatchingMedicals = caseMatchingMedicalService.getCaseMatchingMedicalList(caseMatchingMedical);
        List<CaseMatchingMaterial> caseMatchingMaterials = caseMatchingMaterialService.getCaseMatchingMaterialList(caseMatchingMaterial);
        List<CaseMatchingLeisurePlace> caseMatchingLeisurePlaces = caseMatchingLeisurePlaceService.getCaseMatchingLeisurePlaceList(caseMatchingLeisurePlace);
        List<CaseMatchingFinance> caseMatchingFinances = caseMatchingFinanceService.getCaseMatchingFinanceList(caseMatchingFinance);
        List<CaseMatchingEnvironment> caseMatchingEnvironments = caseMatchingEnvironmentService.getCaseMatchingEnvironmentList(caseMatchingEnvironment);
        List<CaseMatchingEducation> caseMatchingEducations = caseMatchingEducationService.getCaseMatchingEducationList(caseMatchingEducation);
        if (id==null){//初始化
            if (!ObjectUtils.isEmpty(caseEstateParkings)){
                for (CaseEstateParking caseEstateParking:caseEstateParkings){
                    caseEstateParkingService.deleteEstateParking(caseEstateParking.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseEstateNetworks)){
                for (CaseEstateNetwork caseEstateNetwork1:caseEstateNetworks){
                    caseEstateNetworkService.deleteEstateNetwork(caseEstateNetwork1.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseEstateSupplies)){
                for (CaseEstateSupply caseEstateSupply1:caseEstateSupplies){
                    caseEstateSupplyService.deleteCaseEstateSupply(caseEstateSupply1.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseMatchingTraffics)){
                for (CaseMatchingTraffic oo :caseMatchingTraffics){
                    caseMatchingTrafficService.deleteMatchingTraffic(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseMatchingMedicals)){
                for (CaseMatchingMedical oo :caseMatchingMedicals){
                    caseMatchingMedicalService.deleteCaseMatchingMedical(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseMatchingMaterials)){
                for (CaseMatchingMaterial oo :caseMatchingMaterials){
                    caseMatchingMaterialService.deleteCaseMatchingMaterial(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseMatchingLeisurePlaces)){
                for (CaseMatchingLeisurePlace oo :caseMatchingLeisurePlaces){
                    caseMatchingLeisurePlaceService.deleteCaseMatchingLeisurePlace(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseMatchingFinances)){
                for (CaseMatchingFinance oo :caseMatchingFinances){
                    caseMatchingFinanceService.deleteCaseMatchingFinance(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseMatchingEnvironments)){
                for (CaseMatchingEnvironment oo :caseMatchingEnvironments){
                    caseMatchingEnvironmentService.deleteCaseMatchingEnvironment(oo.getId());
                }
            }
            if (!ObjectUtils.isEmpty(caseMatchingEducations)){
                for (CaseMatchingEducation oo :caseMatchingEducations){
                    caseMatchingEducationService.deleteCaseMatchingEducation(oo.getId());
                }
            }
        }else {//修改子类
            if (!ObjectUtils.isEmpty(caseEstateParkings)){
                for (CaseEstateParking caseEstateParking:caseEstateParkings){
                    caseEstateParking.setEstateId(id);
                    caseEstateParkingService.updateEstateParking(caseEstateParking);
                }
            }
            if (!ObjectUtils.isEmpty(caseEstateNetworks)){
                for (CaseEstateNetwork caseEstateNetwork1:caseEstateNetworks){
                    caseEstateNetwork1.setEstateId(id);
                    caseEstateNetworkService.updateEstateNetwork(caseEstateNetwork1);
                }
            }
            if (!ObjectUtils.isEmpty(caseEstateSupplies)){
                for (CaseEstateSupply caseEstateSupply1:caseEstateSupplies){
                    caseEstateSupply1.setEstateId(id);
                    caseEstateSupplyService.updateCaseEstateSupply(caseEstateSupply1);
                }
            }
            if (!ObjectUtils.isEmpty(caseMatchingTraffics)){
                for (CaseMatchingTraffic oo :caseMatchingTraffics){
                    oo.setEstateId(id);
                    caseMatchingTrafficService.updateMatchingTraffic(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseMatchingMedicals)){
                for (CaseMatchingMedical oo :caseMatchingMedicals){
                    oo.setEstateId(id);
                    caseMatchingMedicalService.updateCaseMatchingMedical(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseMatchingMaterials)){
                for (CaseMatchingMaterial oo :caseMatchingMaterials){
                    oo.setEstateId(id);
                    caseMatchingMaterialService.updateCaseMatchingMaterial(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseMatchingLeisurePlaces)){
                for (CaseMatchingLeisurePlace oo :caseMatchingLeisurePlaces){
                    oo.setEstateId(id);
                    caseMatchingLeisurePlaceService.updateCaseMatchingLeisurePlace(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseMatchingFinances)){
                for (CaseMatchingFinance oo :caseMatchingFinances){
                    oo.setEstateId(id);
                    caseMatchingFinanceService.updateCaseMatchingFinance(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseMatchingEnvironments)){
                for (CaseMatchingEnvironment oo :caseMatchingEnvironments){
                    oo.setEstateId(id);
                    caseMatchingEnvironmentService.updateCaseMatchingEnvironment(oo);
                }
            }
            if (!ObjectUtils.isEmpty(caseMatchingEducations)){
                for (CaseMatchingEducation oo :caseMatchingEducations){
                    oo.setEstateId(id);
                    caseMatchingEducationService.updateCaseMatchingEducation(oo);
                }
            }
        }
    }


    public Integer saveAndUpdateCaseEstate(CaseEstate caseEstate) {
        if (caseEstate.getId() == null || caseEstate.getId().intValue() == 0) {
            caseEstate.setCreator(commonService.thisUserAccount());
            caseEstate.setVersion(0);
            int id = caseEstateDao.addEstate(caseEstate) ;
            this.initAndUpdateSon(id);
            return id;
        } else {
            //更新版本
            CaseEstate oo = caseEstateDao.getEstateById(caseEstate.getId());
            if (oo != null) {
                if (oo.getVersion() == null) {
                    oo.setVersion(0);
                }
            }
            oo.setVersion(oo.getVersion()+1);
            BeanCopyHelp.copyPropertiesIgnoreNull(caseEstate,oo);
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            int id = caseEstateDao.addEstate(oo) ;
            this.initAndUpdateSon(id);
            return id;
        }
    }

    public boolean deleteCaseEstate(Integer id) {
        return caseEstateDao.deleteEstate(id);
    }

    public List<CaseEstate> autoCompleteCaseEstate(String name, Integer maxRows){
        List<CaseEstate> caseEstates = Lists.newArrayList();
        List<CaseEstate> caseEstateList = caseEstateDao.autoCompleteCaseEstate(name,null,null,null);
        Ordering<CaseEstate> ordering = Ordering.from(new Comparator<CaseEstate>() {
            @Override
            public int compare(CaseEstate o1, CaseEstate o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).reverse();
        Collections.sort(caseEstateList,ordering);
        if (!ObjectUtils.isEmpty(caseEstateList)) {
            for (int i = 0; i < maxRows; i++) {
                if (i < caseEstateList.size()) {
                    caseEstates.add(caseEstateList.get(i));
                }
            }
        }
        return caseEstates;
    }

    public CaseEstateVo getCaseEstateVo(CaseEstate caseEstate){
        CaseEstateVo vo = new CaseEstateVo();
        //获取格式化对象
        NumberFormat nt = NumberFormat.getPercentInstance();
        //设置百分数精确度2即保留两位小数
        nt.setMinimumFractionDigits(2);
        BeanUtils.copyProperties(caseEstate,vo);
        if (org.apache.commons.lang.StringUtils.isNotBlank(caseEstate.getProvince())) {
            //省
            vo.setProvinceName(erpAreaService.getSysAreaName(caseEstate.getProvince()));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(caseEstate.getCity())) {
            //市或者县
            vo.setCityName(erpAreaService.getSysAreaName(caseEstate.getCity()));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(caseEstate.getDistrict())) {
            //县
            vo.setDistrictName(erpAreaService.getSysAreaName(caseEstate.getDistrict()));
        }
        if (!org.springframework.util.StringUtils.isEmpty(caseEstate.getVolumetricRate())){
            if (NumberUtils.isNumber(caseEstate.getVolumetricRate())){
                vo.setVolumetricRateName(nt.format(Double.parseDouble(caseEstate.getVolumetricRate())));
            }
        }
        if (!org.springframework.util.StringUtils.isEmpty(caseEstate.getGreeningRate())){
            if (NumberUtils.isNumber(caseEstate.getGreeningRate())){
                vo.setGreeningRateName(nt.format(Double.parseDouble(caseEstate.getGreeningRate())));
            }
        }
        return vo;
    }
}
