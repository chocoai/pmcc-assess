package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareBuildEquipmentInstallDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEquipmentInstall;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareBuildEquipmentInstallVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/27 11:27
 * @Description:在建工程（设备安装）
 */
@Service
public class DeclareBuildEquipmentInstallService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DeclareBuildEquipmentInstallDao declareBuildEquipmentInstallDao;

    public Integer saveAndUpdateDeclareBuildEquipmentInstall(DeclareBuildEquipmentInstall declareBuildEquipmentInstall) {
        if (declareBuildEquipmentInstall.getId() == null) {
            declareBuildEquipmentInstall.setCreator(commonService.thisUserAccount());
            Integer id = declareBuildEquipmentInstallDao.addDeclareBuildEquipmentInstall(declareBuildEquipmentInstall);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareBuildEquipmentInstall.class), id);
            return id;
        } else {
            declareBuildEquipmentInstallDao.updateDeclareBuildEquipmentInstall(declareBuildEquipmentInstall);
            return null;
        }
    }

    public DeclareBuildEquipmentInstall getDeclareBuildEquipmentInstallById(Integer id) {
        return declareBuildEquipmentInstallDao.getDeclareBuildEquipmentInstallById(id);
    }

    public BootstrapTableVo getDeclareBuildEquipmentInstallListVos(DeclareBuildEquipmentInstall declareBuildEquipmentInstall) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareBuildEquipmentInstallVo> vos = declareBuildEquipmentInstallVos(declareBuildEquipmentInstall);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareBuildEquipmentInstallVo> declareBuildEquipmentInstallVos(DeclareBuildEquipmentInstall declareBuildEquipmentInstall) {
        List<DeclareBuildEquipmentInstall> declareBuildEquipmentInstalls = declareBuildEquipmentInstallDao.getDeclareBuildEquipmentInstallList(declareBuildEquipmentInstall);
        List<DeclareBuildEquipmentInstallVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(declareBuildEquipmentInstalls)) {
            for (DeclareBuildEquipmentInstall landLevel : declareBuildEquipmentInstalls) {
                vos.add(getDeclareBuildEquipmentInstallVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDeclareBuildEquipmentInstall(DeclareBuildEquipmentInstall declareBuildEquipmentInstall) {
        declareBuildEquipmentInstallDao.removeDeclareBuildEquipmentInstall(declareBuildEquipmentInstall);
    }

    public DeclareBuildEquipmentInstallVo getDeclareBuildEquipmentInstallVo(DeclareBuildEquipmentInstall declareBuildEquipmentInstall) {
        DeclareBuildEquipmentInstallVo vo = new DeclareBuildEquipmentInstallVo();
        BeanUtils.copyProperties(declareBuildEquipmentInstall, vo);
        if (StringUtils.isNotBlank(declareBuildEquipmentInstall.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(declareBuildEquipmentInstall.getProvince()));//省
        }
        if (StringUtils.isNotBlank(declareBuildEquipmentInstall.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(declareBuildEquipmentInstall.getCity()));//市或者县
        }
        if (StringUtils.isNotBlank(declareBuildEquipmentInstall.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(declareBuildEquipmentInstall.getDistrict()));//县
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(declareBuildEquipmentInstall.getId(), null, FormatUtils.entityNameConvertToTableName(DeclareBuildEquipmentInstall.class));
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
