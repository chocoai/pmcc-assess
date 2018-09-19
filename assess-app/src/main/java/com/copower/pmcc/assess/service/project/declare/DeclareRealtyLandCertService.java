package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyLandCertDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyLandCert;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyLandCertVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:06
 * @Description:土地证
 */
@Service
public class DeclareRealtyLandCertService {
    @Autowired
    private DeclareRealtyLandCertDao declareRealtyLandCertDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;

    public Integer saveAndUpdateDeclareRealtyLandCert(DeclareRealtyLandCert declareRealtyLandCert){
        if (declareRealtyLandCert.getId()==null){
            declareRealtyLandCert.setCreator(commonService.thisUserAccount());
            return declareRealtyLandCertDao.addDeclareRealtyLandCert(declareRealtyLandCert);
        }else {
            declareRealtyLandCertDao.updateDeclareRealtyLandCert(declareRealtyLandCert);
            return null;
        }
    }

    public DeclareRealtyLandCert getDeclareRealtyLandCertById(Integer id){
        return declareRealtyLandCertDao.getDeclareRealtyLandCertById(id);
    }

    public BootstrapTableVo getDeclareRealtyLandCertListVos(DeclareRealtyLandCert declareRealtyLandCert){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRealtyLandCertVo> vos = landLevels(declareRealtyLandCert);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }
    public  List<DeclareRealtyLandCertVo> landLevels(DeclareRealtyLandCert declareRealtyLandCert){
        List<DeclareRealtyLandCert> declareRealtyLandCerts = declareRealtyLandCertDao.getDeclareRealtyLandCertList(declareRealtyLandCert);
        List<DeclareRealtyLandCertVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(declareRealtyLandCerts)){
            for (DeclareRealtyLandCert landLevel:declareRealtyLandCerts){
                vos.add(getDeclareRealtyLandCertVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDeclareRealtyLandCert(DeclareRealtyLandCert declareRealtyLandCert){
        try {
            declareRealtyLandCertDao.removeDeclareRealtyLandCert(declareRealtyLandCert);
        } catch (Exception e1) {
            try {
                throw  new Exception();
            } catch (Exception e11) {

            }
        }
    }

    public DeclareRealtyLandCertVo getDeclareRealtyLandCertVo(DeclareRealtyLandCert declareRealtyLandCert){
        DeclareRealtyLandCertVo vo = new DeclareRealtyLandCertVo();
        BeanUtils.copyProperties(declareRealtyLandCert,vo);
        if (StringUtils.isNotBlank(declareRealtyLandCert.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(declareRealtyLandCert.getProvince()));//省
        }
        if (StringUtils.isNotBlank(declareRealtyLandCert.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(declareRealtyLandCert.getCity()));//市或者县
        }
        if (StringUtils.isNotBlank(declareRealtyLandCert.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(declareRealtyLandCert.getDistrict()));//县
        }
        return vo;
    }
}
