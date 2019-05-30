package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataContractCalculateToolDao;
import com.copower.pmcc.assess.dal.basis.entity.DataContractCalculateTool;
import com.copower.pmcc.assess.dto.output.data.DataContractCalculateToolVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DataContractCalculateToolService {
    @Autowired
    private DataContractCalculateToolDao dataContractCalculateToolDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Autowired
    private BaseAttachmentService baseAttachmentService;


    public DataContractCalculateTool getByDataContractCalculateToolId(Integer id) {
        DataContractCalculateTool object = dataContractCalculateToolDao.getSingleObject(id);
        return object;
    }


    /**
     * 保存
     *
     * @param dataContractCalculateTool
     * @throws BusinessException
     */
    public void saveObject(DataContractCalculateTool dataContractCalculateTool) {
        if (dataContractCalculateTool.getId() != null && dataContractCalculateTool.getId() > 0) {
            dataContractCalculateToolDao.updateObject(dataContractCalculateTool);
        } else {
            dataContractCalculateTool.setCreator(processControllerComponent.getThisUser());
            dataContractCalculateToolDao.addObject(dataContractCalculateTool);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DataContractCalculateTool.class), dataContractCalculateTool.getId());

        }
    }


    /**
     * 删除
     *
     * @param id
     * @throws BusinessException
     */
    public boolean deleteDataContractCalculateTool(Integer id) throws BusinessException {
        return dataContractCalculateToolDao.deleteObject(id);
    }

    /**
     * 获取数据列表
     */
    public BootstrapTableVo getDataContractCalculateToolList() {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataContractCalculateTool> list = dataContractCalculateToolDao.getListObject(new DataContractCalculateTool());
        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentListByTableName(FormatUtils.entityNameConvertToTableName(DataContractCalculateTool.class), LangUtils.transform(list, o -> o.getId()));
        List<DataContractCalculateToolVo> vos = LangUtils.transform(list, p -> getContractCalculateToolVo(p, attachmentDtos));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DataContractCalculateTool>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public DataContractCalculateToolVo getContractCalculateToolVo(DataContractCalculateTool reportAnalysis, List<SysAttachmentDto> attachmentDtos) {
        if (reportAnalysis == null) return null;
        DataContractCalculateToolVo vo = new DataContractCalculateToolVo();
        BeanUtils.copyProperties(reportAnalysis, vo);
        if (!org.springframework.util.CollectionUtils.isEmpty(attachmentDtos)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (SysAttachmentDto attachmentDto : attachmentDtos) {
                if (attachmentDto.getTableId().equals(reportAnalysis.getId())) {
                    stringBuilder.append(baseAttachmentService.getViewHtml(attachmentDto));
                }
            }
            vo.setFileViewName(stringBuilder.toString());
        }

        return vo;
    }


}
