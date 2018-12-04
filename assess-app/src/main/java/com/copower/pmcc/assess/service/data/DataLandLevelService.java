package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDao;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevel;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/4 18:24
 * @Description:土地级别维护
 */
@Service
public class DataLandLevelService {
    @Autowired
    private DataLandLevelDao dataLandLevelDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public void saveAndUpdateDataLandLevel(DataLandLevel dataLandLevel){
        if (dataLandLevel.getId()==null){
            dataLandLevel.setCreator(commonService.thisUserAccount());
            dataLandLevelDao.addDataLandLevel(dataLandLevel);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DataLandLevel.class),dataLandLevel.getId());
        }else {
            dataLandLevelDao.updateDataLandLevel(dataLandLevel);
        }
    }

    public DataLandLevel getDataLandLevelById(Integer id){
        return dataLandLevelDao.getDataLandLevelById(id);
    }

    public BootstrapTableVo getDataLandLevelListVos(DataLandLevel dataLandLevel){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLandLevel> dataLandLevels = dataLandLevelDao.getDataLandLevelList(dataLandLevel);
        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentListByTableName(FormatUtils.entityNameConvertToTableName(DataLandLevel.class),LangUtils.transform(dataLandLevels,o->o.getId()));
        List<DataLandLevelVo> vos= LangUtils.transform(dataLandLevels,o->getDataLandLevelVo(o,attachmentDtos));
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public void removeDataLandLevel(DataLandLevel dataLandLevel){
        dataLandLevelDao.removeDataLandLevel(dataLandLevel);
    }

    public DataLandLevelVo getDataLandLevelVo(DataLandLevel dataLandLevel,List<SysAttachmentDto> attachmentDtos){
        DataLandLevelVo vo = new DataLandLevelVo();
        BeanUtils.copyProperties(dataLandLevel,vo);
        if (StringUtils.isNotBlank(dataLandLevel.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(dataLandLevel.getProvince()));//省
        }
        if (StringUtils.isNotBlank(dataLandLevel.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(dataLandLevel.getCity()));//市或者县
        }
        if (StringUtils.isNotBlank(dataLandLevel.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(dataLandLevel.getDistrict()));//县
        }
        if(!CollectionUtils.isEmpty(attachmentDtos)){
            StringBuilder stringBuilder=new StringBuilder();
            for (SysAttachmentDto attachmentDto : attachmentDtos) {
                if(attachmentDto.getTableId().equals(dataLandLevel.getId())){
                    stringBuilder.append(baseAttachmentService.getViewHtml(attachmentDto));
                }
            }
            vo.setFileViewName(stringBuilder.toString());
        }
        return vo;
    }
}
