package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseEstateDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;
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
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.text.NumberFormat;
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
    private CaseBuildingService caseBuildingService;
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
        if (caseEstate == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseEstateDao.getEstateList(caseEstate);
    }

    public CaseEstate getCaseEstateById(Integer id) {
        if (id == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseEstateDao.getEstateById(id);
    }

    public Integer saveAndUpdateCaseEstate(CaseEstate caseEstate) {
        if (caseEstate == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        if (caseEstate.getId() == null || caseEstate.getId().intValue() == 0) {
            caseEstate.setCreator(commonService.thisUserAccount());
            caseEstate.setVersion(0);
            return caseEstateDao.addEstate(caseEstate);
        } else {
            //更新版本
            CaseEstate oo = caseEstateDao.getEstateById(caseEstate.getId());
            if (oo != null) {
                if (oo.getVersion() == null) {
                    oo.setVersion(0);
                }
            }
            caseEstate.setVersion(oo.getVersion()+1);
            caseEstateDao.updateEstate(caseEstate);
            return null;
        }
    }

    public boolean deleteCaseEstate(Integer id) {
        if (id == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        //
        return caseEstateDao.deleteEstate(id);
    }

    public List<CaseEstate> autoCompleteCaseEstate(String name, Integer maxRows) {
        if (maxRows == null || StringUtils.isEmpty(name)) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        List<CaseEstate> caseEstates = Lists.newArrayList();
        List<CaseEstate> caseEstateList = caseEstateDao.autoCompleteCaseEstate(name,null,null,null);
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
            vo.setProvinceName(erpAreaService.getSysAreaName(caseEstate.getProvince()));//省
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(caseEstate.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(caseEstate.getCity()));//市或者县
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(caseEstate.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(caseEstate.getDistrict()));//县
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
