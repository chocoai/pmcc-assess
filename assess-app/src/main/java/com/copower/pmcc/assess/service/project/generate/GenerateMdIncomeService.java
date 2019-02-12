package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.Document;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseReportField;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.method.MdIncomeService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/2/12 16:43
 * @Description:收益法报告字段处理
 */
public class GenerateMdIncomeService {
    private Integer miId;
    private Integer projectId;
    private Integer areaId;
    private Integer baseReportTemplateId;

    private MdIncomeService mdIncomeService;
    private CommonService commonService;
    private BaseReportFieldService baseReportFieldService;
    private BaseAttachmentService baseAttachmentService;
    private DataSetUseFieldService dataSetUseFieldService;

    private GenerateMdIncomeService() {

    }

    public GenerateMdIncomeService(Integer miId, Integer projectId, Integer areaId) {
        this.miId = miId;
        this.projectId = projectId;
        this.areaId = areaId;
        this.commonService = SpringContextUtils.getBean(CommonService.class);
        this.baseReportFieldService = SpringContextUtils.getBean(BaseReportFieldService.class);
        this.baseAttachmentService = SpringContextUtils.getBean(BaseAttachmentService.class);
        this.dataSetUseFieldService = SpringContextUtils.getBean(DataSetUseFieldService.class);
    }

    /**
     * 获取替换后的报告模板
     * @return
     * @throws Exception
     */
    public String generateCompareFile() throws Exception{
        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(AssessReportFieldConstant.INCOME_TEMPLATE);
        List<SysAttachmentDto> dtoList = baseAttachmentService.getByField_tableId(baseReportField.getId(), null, FormatUtils.entityNameConvertToTableName(BaseReportField.class));
        if (CollectionUtils.isEmpty(dtoList))
            throw new BusinessException("模板文件未找到");
        String localPath = baseAttachmentService.downloadFtpFileToLocal(dtoList.get(0).getId());
        Document document = new Document(localPath);
        return localPath;
    }
}
