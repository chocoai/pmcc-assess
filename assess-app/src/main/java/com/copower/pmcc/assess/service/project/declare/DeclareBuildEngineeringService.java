package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareBuildEngineeringDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineering;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareBuildEngineeringVo;
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
 * @Description:在建工程（土建）
 */
@Service
public class DeclareBuildEngineeringService {
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
    private DeclareBuildEngineeringDao declareBuildEngineeringDao;

    public Integer saveAndUpdateDeclareBuildEngineering(DeclareBuildEngineering declareBuildEngineering) {
        if (declareBuildEngineering.getId() == null) {
            declareBuildEngineering.setCreator(commonService.thisUserAccount());
            Integer id = declareBuildEngineeringDao.addDeclareBuildEngineering(declareBuildEngineering);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareBuildEngineering.class), id);
            return id;
        } else {
            declareBuildEngineeringDao.updateDeclareBuildEngineering(declareBuildEngineering);
            return null;
        }
    }

    public DeclareBuildEngineering getDeclareBuildEngineeringById(Integer id) {
        return declareBuildEngineeringDao.getDeclareBuildEngineeringById(id);
    }

    public BootstrapTableVo getDeclareBuildEngineeringListVos(DeclareBuildEngineering declareBuildEngineering) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareBuildEngineeringVo> vos = declareBuildEngineeringVoList(declareBuildEngineering);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareBuildEngineeringVo> declareBuildEngineeringVoList(DeclareBuildEngineering declareBuildEngineering) {
        List<DeclareBuildEngineering> declareBuildEngineerings = declareBuildEngineeringDao.getDeclareBuildEngineeringList(declareBuildEngineering);
        List<DeclareBuildEngineeringVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(declareBuildEngineerings)) {
            for (DeclareBuildEngineering landLevel : declareBuildEngineerings) {
                vos.add(getDeclareBuildEngineeringVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDeclareBuildEngineering(DeclareBuildEngineering declareBuildEngineering) {
        declareBuildEngineeringDao.removeDeclareBuildEngineering(declareBuildEngineering);
    }

    public DeclareBuildEngineeringVo getDeclareBuildEngineeringVo(DeclareBuildEngineering declareBuildEngineering) {
        DeclareBuildEngineeringVo vo = new DeclareBuildEngineeringVo();
        BeanUtils.copyProperties(declareBuildEngineering, vo);
        if (StringUtils.isNotBlank(declareBuildEngineering.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(declareBuildEngineering.getProvince()));//省
        }
        if (StringUtils.isNotBlank(declareBuildEngineering.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(declareBuildEngineering.getCity()));//市或者县
        }
        if (StringUtils.isNotBlank(declareBuildEngineering.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(declareBuildEngineering.getDistrict()));//县
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(declareBuildEngineering.getId(), null, FormatUtils.entityNameConvertToTableName(DeclareBuildEngineering.class));
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
