package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.dal.basis.dao.base.BaseFileTemplateDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseFileTemplate;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/8/2 14:07
 * @Description:围护结构
 */
@Service
public class BaseFileTemplateService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseFileTemplateDao baseFileTemplateDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public BaseFileTemplate getFileTemplateById(Integer id) {
        return baseFileTemplateDao.getFileTemplateById(id);
    }

    public BaseFileTemplate getFileTemplateByName(String name) {
        return baseFileTemplateDao.getFileTemplateByName(name);
    }

    /**
     * 获取附件id
     * @param name
     * @return
     */
    public Integer getAttachmentId(String name){
        BaseFileTemplate fileTemplate = baseFileTemplateDao.getFileTemplateByName(name);
        if(fileTemplate==null) return null;
        SysAttachmentDto sysAttachmentDto=new SysAttachmentDto();
        sysAttachmentDto.setTableId(fileTemplate.getId());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BaseFileTemplate.class));
        List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if(CollectionUtils.isEmpty(attachmentList)) return null;
        return attachmentList.get(0).getId();
    }

    /**
     * 获取文件模板列表
     * @param name
     * @param remark
     * @return
     */
    public BootstrapTableVo getFileTemplateList(String name,String remark) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseFileTemplate> templateList = baseFileTemplateDao.getFileTemplateList(name, remark);
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(templateList) ? new ArrayList<BaseFileTemplate>() : templateList);
        return vo;
    }

    /**
     * 保存文件模板
     * @param baseFileTemplate
     */
    public void saveFileTemplate(BaseFileTemplate baseFileTemplate) throws BusinessException {
        if(baseFileTemplate==null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if(baseFileTemplate.getId()!=null&&baseFileTemplate.getId()>0){
            baseFileTemplateDao.updateFileTemplate(baseFileTemplate);
        }else{
            baseFileTemplate.setCreator(commonService.thisUserAccount());
            baseFileTemplateDao.addFileTemplate(baseFileTemplate);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BaseFileTemplate.class),baseFileTemplate.getId());
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public void deleteFileTemplate(Integer id) {
        baseFileTemplateDao.deleteFileTemplate(id);
    }


}
