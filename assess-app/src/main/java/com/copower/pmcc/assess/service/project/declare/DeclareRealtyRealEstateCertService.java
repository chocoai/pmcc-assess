package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyRealEstateCertDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyRealEstateCert;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRealtyRealEstateCertVo;
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
 * @Date: 2018/9/19 10:03
 * @Description:不动产证
 */
@Service
public class DeclareRealtyRealEstateCertService {
    @Autowired
    private DeclareRealtyRealEstateCertDao declareRealtyRealEstateCertDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public Integer saveAndUpdateDeclareRealtyRealEstateCert(DeclareRealtyRealEstateCert declareRealtyRealEstateCert){
        if (declareRealtyRealEstateCert.getId()==null){
            declareRealtyRealEstateCert.setCreator(commonService.thisUserAccount());
            Integer id = declareRealtyRealEstateCertDao.addDeclareRealtyRealEstateCert(declareRealtyRealEstateCert);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), id);
            return  id;
        }else {
            declareRealtyRealEstateCertDao.updateDeclareRealtyRealEstateCert(declareRealtyRealEstateCert);
            return null;
        }
    }

    public DeclareRealtyRealEstateCert getDeclareRealtyRealEstateCertById(Integer id){
        return declareRealtyRealEstateCertDao.getDeclareRealtyRealEstateCertById(id);
    }

    public BootstrapTableVo getDeclareRealtyRealEstateCertListVos(DeclareRealtyRealEstateCert declareRealtyRealEstateCert){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRealtyRealEstateCertVo> vos = landLevels(declareRealtyRealEstateCert);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }
    public  List<DeclareRealtyRealEstateCertVo> landLevels(DeclareRealtyRealEstateCert declareRealtyRealEstateCert){
        List<DeclareRealtyRealEstateCert> declareRealtyRealEstateCerts = declareRealtyRealEstateCertDao.getDeclareRealtyRealEstateCertList(declareRealtyRealEstateCert);
        List<DeclareRealtyRealEstateCertVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(declareRealtyRealEstateCerts)){
            for (DeclareRealtyRealEstateCert landLevel:declareRealtyRealEstateCerts){
                vos.add(getDeclareRealtyRealEstateCertVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDeclareRealtyRealEstateCert(DeclareRealtyRealEstateCert declareRealtyRealEstateCert){
        try {
            declareRealtyRealEstateCertDao.removeDeclareRealtyRealEstateCert(declareRealtyRealEstateCert);
        } catch (Exception e1) {
            try {
                throw  new Exception();
            } catch (Exception e11) {

            }
        }
    }

    public DeclareRealtyRealEstateCertVo getDeclareRealtyRealEstateCertVo(DeclareRealtyRealEstateCert declareRealtyRealEstateCert){
        DeclareRealtyRealEstateCertVo vo = new DeclareRealtyRealEstateCertVo();
        BeanUtils.copyProperties(declareRealtyRealEstateCert,vo);
        if (StringUtils.isNotBlank(declareRealtyRealEstateCert.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(declareRealtyRealEstateCert.getProvince()));//省
        }
        if (StringUtils.isNotBlank(declareRealtyRealEstateCert.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(declareRealtyRealEstateCert.getCity()));//市或者县
        }
        if (StringUtils.isNotBlank(declareRealtyRealEstateCert.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(declareRealtyRealEstateCert.getDistrict()));//县
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(declareRealtyRealEstateCert.getId(), null, FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class));
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
