package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.entity.ProjectPhase;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcAttachmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.copower.pmcc.erp.constant.CacheConstant;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/8/10
 * @time: 17:04
 */
@Service
public class BaseAttachmentService {
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpRpcAttachmentService erpRpcAttachmentService;

    /**
     * 创建不重复的文件名
     *
     * @param suffix 文件的后缀名 如docx jpg
     * @return
     */
    public String createNoRepeatFileName(String suffix) {
        return erpRpcAttachmentService.createNoRepeatFileName(suffix);
    }

    /**
     * 创建文件存放目录
     *
     * @param params
     * @return
     */
    public String createTempBasePath(String... params) {
        return erpRpcAttachmentService.createTempPath(params);
    }

    /**
     * 创建FTP文件存放目录
     *
     * @param params
     * @return
     */
    public String createFTPBasePath(String... params) {
        return erpRpcAttachmentService.createFTPPath(params);
    }

    public List<SysAttachmentDto> getAttachmentListByTableName(String tableName, List<Integer> integers) {
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableName(tableName);
        return erpRpcAttachmentService.getAttachmentListByIds(integers, sysAttachmentDto);
    }

    /**
     * @param table_id
     * @param fields_name
     * @return
     * @time zch 2018-05-09
     */
    public List<SysAttachmentDto> getByField_tableId(int table_id, String fields_name, String tableName) {
        SysAttachmentDto sysAttachment = new SysAttachmentDto();
        sysAttachment.setTableId(table_id);
        sysAttachment.setFieldsName(fields_name);
        sysAttachment.setTableName(tableName);
        return getAttachmentList(sysAttachment);
    }

    public List<SysAttachmentDto> getAttachmentList(List<Integer> tableIds, SysAttachmentDto sysAttachment) {
        return erpRpcAttachmentService.getAttachmentListByTableIds(tableIds, sysAttachment);
    }

    public List<SysAttachmentDto> getAttachmentList(SysAttachmentDto sysAttachment) {
        return erpRpcAttachmentService.getAttachmentList(sysAttachment);
    }

    public SysAttachmentDto getSysAttachmentDto(Integer attachmentId) {
        return erpRpcAttachmentService.getAttachmentDtoById(attachmentId);
    }

    public void addAttachment(SysAttachmentDto sysAttachment) {
        erpRpcAttachmentService.addAttachment(sysAttachment);
    }

    public void updateAttachment(SysAttachmentDto sysAttachment) {
        erpRpcAttachmentService.updateAttachment(sysAttachment);
    }

    public List<SysAttachmentDto> getApprovalLogList(String processInsId, List<String> taskIds) {
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setProcessInsId(processInsId);
        return erpRpcAttachmentService.getAttachmentListByTaskIds(taskIds, sysAttachmentDto);
    }

    /**
     * 附件关联到业务表
     *
     * @param tableName
     * @param fieldName
     * @param relationTableId
     */
    public void relationToTable(String tableName, String fieldName, Integer relationTableId) {
        SysAttachmentDto queryParam = new SysAttachmentDto();
        queryParam.setTableName(tableName);
        queryParam.setTableId(0);
        if (StringUtils.isNotBlank(fieldName))
            queryParam.setFieldsName(fieldName);
        queryParam.setCreater(commonService.thisUserAccount());
        List<SysAttachmentDto> attachmentList = getAttachmentList(queryParam);
        if (CollectionUtils.isNotEmpty(attachmentList)) {
            for (SysAttachmentDto baseAttachment : attachmentList) {
                baseAttachment.setTableId(relationTableId);
                erpRpcAttachmentService.updateAttachment(baseAttachment);
            }
        }
    }

    private List<SysAttachmentDto> getSysAttachmentDtos(Integer id, String fieldsName) {
        SysAttachmentDto bidSysAttachmentDto = new SysAttachmentDto();
        bidSysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(ProjectPhase.class));
        bidSysAttachmentDto.setFieldsName(fieldsName);
        bidSysAttachmentDto.setTableId(id);
        return getAttachmentList(bidSysAttachmentDto);
    }

    /**
     * 获取工作事项的工作模板
     *
     * @param id
     * @return
     */
    public List<SysAttachmentDto> getProjectPhaseWorkTemplate(Integer id) {
        try {
            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_ATTACHMENT_PHASE_TEMPLATE_WORK, id.toString());
            return LangUtils.listCache(costsKeyPrefix, id, SysAttachmentDto.class, input -> {
                return getSysAttachmentDtos(input, "workTemp");
            });
        } catch (Exception e) {
            return getSysAttachmentDtos(id, "workTemp");
        }
    }

    /**
     * 获取
     *
     * @param id
     * @return
     */
    public List<SysAttachmentDto> getProjectPhaseProcessTemplate(Integer id) {
        try {
            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_ATTACHMENT_PHASE_TEMPLATE_PROCESS, id.toString());
            return LangUtils.listCache(costsKeyPrefix, id, SysAttachmentDto.class, input -> {
                return getSysAttachmentDtos(input, "processTemp");
            });
        } catch (Exception e) {
            return getSysAttachmentDtos(id, "processTemp");
        }
    }

    /**
     * 附件信息批量更新
     * @param queryParam
     * @param sysAttachmentDto
     */
    public void updateAttachementByExample(SysAttachmentDto queryParam, SysAttachmentDto sysAttachmentDto) {
        erpRpcAttachmentService.updateAttachmentByParam(queryParam,sysAttachmentDto);
    }

    /**
     * 拷贝FTP附件
     *
     * @param attachmentId
     * @param baseAttachment
     * @return
     */
    public SysAttachmentDto copyFtpAttachment(Integer attachmentId, SysAttachmentDto baseAttachment) throws Exception {
        return erpRpcAttachmentService.copyFtpAttachment(attachmentId, baseAttachment);
    }

    /**
     * 获取附件显示html
     *
     * @param baseAttachment
     * @return
     */
    public String getViewHtml(SysAttachmentDto baseAttachment) {
        return erpRpcAttachmentService.getAttachmentViewHtml(baseAttachment);
    }

    /**
     * 下载ftp附件到本地
     *
     * @param attachmentId
     * @return
     * @throws Exception
     */
    public String downloadFtpFileToLocal(Integer attachmentId) throws Exception {
        return erpRpcAttachmentService.downloadFtpFileToLocal(attachmentId);
    }
}
