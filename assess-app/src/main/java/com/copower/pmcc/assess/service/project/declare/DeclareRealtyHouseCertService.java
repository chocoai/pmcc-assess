package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyHouseCertDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyHouseCert;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyHouseCertVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
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
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:04
 * @Description:房产证
 */
@Service
public class DeclareRealtyHouseCertService {
    @Autowired
    private DeclareRealtyHouseCertDao declareRealtyHouseCertDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public Integer saveAndUpdateDeclareRealtyHouseCert(DeclareRealtyHouseCert declareRealtyHouseCert){
        if (declareRealtyHouseCert.getId()==null){
            declareRealtyHouseCert.setCreator(commonService.thisUserAccount());
            Integer id = null;
            id = declareRealtyHouseCertDao.addDeclareRealtyHouseCert(declareRealtyHouseCert);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), id);
            return  id ;
        }else {
            declareRealtyHouseCertDao.updateDeclareRealtyHouseCert(declareRealtyHouseCert);
            return null;
        }
    }

    public DeclareRealtyHouseCert getDeclareRealtyHouseCertById(Integer id){
        return declareRealtyHouseCertDao.getDeclareRealtyHouseCertById(id);
    }

    public BootstrapTableVo getDeclareRealtyHouseCertListVos(DeclareRealtyHouseCert declareRealtyHouseCert){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRealtyHouseCertVo> vos = landLevels(declareRealtyHouseCert);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }
    public  List<DeclareRealtyHouseCertVo> landLevels(DeclareRealtyHouseCert declareRealtyHouseCert){
        List<DeclareRealtyHouseCert> declareRealtyHouseCerts = declareRealtyHouseCertDao.getDeclareRealtyHouseCertList(declareRealtyHouseCert);
        List<DeclareRealtyHouseCertVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(declareRealtyHouseCerts)){
            for (DeclareRealtyHouseCert landLevel:declareRealtyHouseCerts){
                vos.add(getDeclareRealtyHouseCertVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDeclareRealtyHouseCert(DeclareRealtyHouseCert declareRealtyHouseCert){
        try {
            declareRealtyHouseCertDao.removeDeclareRealtyHouseCert(declareRealtyHouseCert);
        } catch (Exception e1) {
            try {
                throw  new Exception();
            } catch (Exception e11) {

            }
        }
    }

    public DeclareRealtyHouseCertVo getDeclareRealtyHouseCertVo(DeclareRealtyHouseCert declareRealtyHouseCert){
        DeclareRealtyHouseCertVo vo = new DeclareRealtyHouseCertVo();
        BeanUtils.copyProperties(declareRealtyHouseCert,vo);
        if (StringUtils.isNotBlank(declareRealtyHouseCert.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(declareRealtyHouseCert.getProvince()));//省
        }
        if (StringUtils.isNotBlank(declareRealtyHouseCert.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(declareRealtyHouseCert.getCity()));//市或者县
        }
        if (StringUtils.isNotBlank(declareRealtyHouseCert.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(declareRealtyHouseCert.getDistrict()));//县
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(declareRealtyHouseCert.getId(), null, FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            if (sysAttachmentDtos.size() >= 1) {
                for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                    if (sysAttachmentDto != null) {
                        builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto));
                        builder.append(" ");
                    }
                }
            }
            vo.setFileViewName(builder.toString());
        }
        return vo;
    }

}
