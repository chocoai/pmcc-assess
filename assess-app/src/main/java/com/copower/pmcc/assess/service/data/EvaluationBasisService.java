package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationBasisDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.data.DataEvaluationBasisVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.declare.DeclarePublicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 评估依据
 * Created by 13426 on 2018/4/28.
 */
@Service
public class EvaluationBasisService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationBasisDao evaluationBasisDao;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private DataReportTemplateItemService dataReportTemplateItemService;
    @Autowired
    private DeclarePublicService declarePublicService;

    /**
     * 保存数据
     *
     * @param formData
     */
    public void saveAndUpdate(String formData) {
        DataEvaluationBasis evaluationBasis = JSON.parseObject(formData, DataEvaluationBasis.class);
        if (evaluationBasis.getId() != null && evaluationBasis.getId() > 0) {
            evaluationBasisDao.updateBasis(evaluationBasis);
        } else {
            evaluationBasis.setCreator(commonService.thisUserAccount());
            evaluationBasisDao.addBasis(evaluationBasis);
            //修改子模板
            dataReportTemplateItemService.templateItemToSetMasterId(evaluationBasis.getId(), SchemeSupportTypeEnum.BASIS.getKey());
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean removeBasis(Integer id) {
        return evaluationBasisDao.removeBasis(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public DataEvaluationBasis getBasis(Integer id) {
        return evaluationBasisDao.getBasis(id);
    }


    /**
     * 获取数据列表
     *
     * @param name
     * @return
     */
    public BootstrapTableVo getBasisList(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataEvaluationBasis> hypothesisList = evaluationBasisDao.getBasisList(name);
        List<DataEvaluationBasisVo> vos = LangUtils.transform(hypothesisList, p -> getBasisVo(p));
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<DataEvaluationBasisVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    /**
     * 根据委估目的及评估方法获取数据列表
     *
     * @param purpose
     * @return
     */
    public List<DataEvaluationBasis> getEnableBasisList(Integer type, Integer category, Integer purpose) {
        String typeStr = new String();
        String categoryStr = new String();
        String methodStr = new String();
        String purposeStr = new String();
        if (type != null && type > 0) {
            typeStr = String.format(",%s,", type);
        }
        if (category != null && category > 0) {
            categoryStr = String.format(",%s,", category);
        }
        if (purpose != null && purpose > 0) {
            purposeStr = String.format(",%s,", purpose);
        }
        return evaluationBasisDao.getEnableBasisList(typeStr, categoryStr, purposeStr);
    }


    public DataEvaluationBasisVo getBasisVo(DataEvaluationBasis evaluationBasis) {
        if (evaluationBasis == null) return null;
        List<BaseDataDic> methodDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_EVALUATION_METHOD);
        List<BaseDataDic> purposeDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        DataEvaluationBasisVo vo = new DataEvaluationBasisVo();
        BeanUtils.copyProperties(evaluationBasis, vo);
        if (StringUtils.isNotBlank(evaluationBasis.getMethod())) {
            vo.setMethodStr(baseDataDicService.getDataDicName(methodDicList, evaluationBasis.getMethod()));
        }
        if (StringUtils.isNotBlank(evaluationBasis.getEntrustmentPurpose())) {
            vo.setEntrustmentPurposeStr(baseDataDicService.getDataDicName(purposeDicList, evaluationBasis.getEntrustmentPurpose()));
        }
        vo.setTypeName(baseProjectClassifyService.getTypeAndCategoryName(evaluationBasis.getType(), evaluationBasis.getCategory()));
        return vo;
    }


    /**
     * 获取上报告内容
     *
     * @param projectInfo
     * @return
     */
    public String getReportBasic(ProjectInfo projectInfo) {
        //根据项目类型、委托目的按排序顺序获取数据
        //获取到数据后根据对应的规则生成相关报告数据内容
        List<DataEvaluationBasis> basisList = this.getEnableBasisList(projectInfo.getProjectTypeId(), projectInfo.getProjectCategoryId(), projectInfo.getEntrustPurpose());
        if (CollectionUtils.isEmpty(basisList)) return "";
        //委估单位
        DeclareApply declareApplyByProjectId = declarePublicService.getDeclareApplyByProjectId(projectInfo.getId());
        String unit = declareApplyByProjectId.getClient();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < basisList.size(); i++) {
            DataEvaluationBasis basis = basisList.get(i);
            stringBuilder.append("<p style=\"text-indent:2em\">").append(String.format("%s、%s",i+1,basis.getName())).append("</p>");
            stringBuilder.append("<p style=\"text-indent:2em\">").append(basis.getTemplate()).append("</p>");
            //经济行为依据
            if (AssessReportFieldConstant.BASIS_ECONOMIC_BEHAVIOR.equals(basis.getFieldName())) {
                DataReportTemplateItem dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.ENTRUSTING_PARTY);
                stringBuilder.append("<p style=\"text-indent:2em\">").append(dataReportTemplateByField.getTemplate().replace("#{委托单位}", unit)).append("</p>");

            }
        }
        return stringBuilder.toString();
    }
}
