package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyHouseCertDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyHouseCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyLandCert;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:04
 * @Description:房产证
 */
@Service
public class DeclareRealtyHouseCertService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareRealtyHouseCertDao declareRealtyHouseCertDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private DeclareRealtyLandCertService declareRealtyLandCertService;

    public String importData(DeclareRealtyHouseCert declareRealtyHouseCert, MultipartFile multipartFile) throws Exception {
        Workbook hssfWorkbook = null;
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        //2.读取文件
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            hssfWorkbook = PoiUtils.isExcel2003(filePath) ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        Sheet sheet = hssfWorkbook.getSheetAt(0);//只取第一个sheet
        int startRowNumber = 2;//读取数据的起始行
        return null;
    }

    public Integer saveAndUpdateDeclareRealtyHouseCert(DeclareRealtyHouseCert declareRealtyHouseCert) {
        if (declareRealtyHouseCert.getId() == null) {
            declareRealtyHouseCert.setCreator(commonService.thisUserAccount());
            Integer pid = declareRealtyHouseCert.getPid();
            DeclareRealtyLandCert oo = null;
            //处理关联数据
            if (pid != null && pid.intValue() != 0) {
                oo = declareRealtyLandCertService.getDeclareRealtyLandCertById(pid);
                if (oo != null) {
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getCity())) {
                        declareRealtyHouseCert.setCity(oo.getCity());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getDistrict())) {
                        declareRealtyHouseCert.setDistrict(oo.getDistrict());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getProvince())) {
                        declareRealtyHouseCert.setProvince(oo.getProvince());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getFloor())) {
                        declareRealtyHouseCert.setFloor(oo.getFloor());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getRoomNumber())) {
                        declareRealtyHouseCert.setRoomNumber(oo.getRoomNumber());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getUnit())) {
                        declareRealtyHouseCert.setUnit(oo.getUnit());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getBuildingNumber())) {
                        declareRealtyHouseCert.setBuildingNumber(oo.getBuildingNumber());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getAttachedNumber())) {
                        declareRealtyHouseCert.setAttachedNumber(oo.getAttachedNumber());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getStreetNumber())) {
                        declareRealtyHouseCert.setStreetNumber(oo.getStreetNumber());
                    }
                    if (!org.springframework.util.StringUtils.isEmpty(oo.getBeLocated())) {
                        declareRealtyHouseCert.setBeLocated(oo.getBeLocated());
                    }
                }
            }
            Integer id = null;
            id = declareRealtyHouseCertDao.addDeclareRealtyHouseCert(declareRealtyHouseCert);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class), id);
            return id;
        } else {
            declareRealtyHouseCertDao.updateDeclareRealtyHouseCert(declareRealtyHouseCert);
            return null;
        }
    }

    public DeclareRealtyHouseCert getDeclareRealtyHouseCertById(Integer id) {
        return declareRealtyHouseCertDao.getDeclareRealtyHouseCertById(id);
    }

    public BootstrapTableVo getDeclareRealtyHouseCertListVos(DeclareRealtyHouseCert declareRealtyHouseCert) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRealtyHouseCertVo> vos = landLevels(declareRealtyHouseCert);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareRealtyHouseCertVo> landLevels(DeclareRealtyHouseCert declareRealtyHouseCert) {
        List<DeclareRealtyHouseCert> declareRealtyHouseCerts = declareRealtyHouseCertDao.getDeclareRealtyHouseCertList(declareRealtyHouseCert);
        List<DeclareRealtyHouseCertVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(declareRealtyHouseCerts)) {
            for (DeclareRealtyHouseCert landLevel : declareRealtyHouseCerts) {
                vos.add(getDeclareRealtyHouseCertVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDeclareRealtyHouseCert(DeclareRealtyHouseCert declareRealtyHouseCert) {
        try {
            declareRealtyHouseCertDao.removeDeclareRealtyHouseCert(declareRealtyHouseCert);
        } catch (Exception e1) {
            try {
                throw new Exception();
            } catch (Exception e11) {

            }
        }
    }

    public DeclareRealtyHouseCertVo getDeclareRealtyHouseCertVo(DeclareRealtyHouseCert declareRealtyHouseCert) {
        DeclareRealtyHouseCertVo vo = new DeclareRealtyHouseCertVo();
        BeanUtils.copyProperties(declareRealtyHouseCert, vo);
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
