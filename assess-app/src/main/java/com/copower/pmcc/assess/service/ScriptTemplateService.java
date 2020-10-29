package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.basis.dao.ScriptTemplateDao;
import com.copower.pmcc.assess.dal.basis.entity.ScriptTemplateWithBLOBs;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ScriptTemplateService {
    @Autowired
    private ScriptTemplateDao scriptTemplateDao;
    @Autowired
    private CommonService commonService;

    public ScriptTemplateWithBLOBs getScriptTemplateById(Integer id) {
        return scriptTemplateDao.getScriptTemplateById(id);
    }

    public BootstrapTableVo getScriptTemplateDataList(String templateName, String templateKey) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ScriptTemplateWithBLOBs> list = scriptTemplateDao.getScriptTemplateList(templateName, templateKey);
        vo.setTotal(page.getTotal());
        vo.setRows(list);
        return vo;
    }

    public void deleteScriptTemplateByIds(List<Integer> ids) {
        scriptTemplateDao.deleteScriptTemplateByIds(ids);
    }

    public void saveAndUpdateScriptTemplate(ScriptTemplateWithBLOBs scriptTemplate) {
        if (scriptTemplate == null) {
            return;
        }
        if (scriptTemplate.getId() == null || scriptTemplate.getId() == 0) {
            if (StringUtils.isBlank(scriptTemplate.getCreator())) {
                scriptTemplate.setCreator(commonService.thisUserAccount());
            }
            scriptTemplateDao.addScriptTemplate(scriptTemplate);
        } else {
            scriptTemplateDao.updateScriptTemplate(scriptTemplate);
        }
    }

    /**
     * 脚本引擎实例对象
     */
    private AviatorEvaluatorInstance aviatorEvaluatorInstance;


    @PostConstruct
    public void init() throws NoSuchMethodException, IllegalAccessException {
        /**
         * 初始化脚本引擎实例对象
         */
        this.aviatorEvaluatorInstance = AviatorEvaluator.getInstance();
        //this.aviatorEvaluatorInstance.importFunctions(ScriptTemplateExtensionFunctionUtils.class);//外部工具类，引用
    }


    /**
     * 带缓存的模板
     *
     * @param scriptTemplateWithBLOBs
     * @return
     */
    private Expression fetchExpression(ScriptTemplateWithBLOBs scriptTemplateWithBLOBs) {
//        Expression expression = this.aviatorEvaluatorInstance.compile(scriptTemplateWithBLOBs.getId().toString(), scriptTemplateWithBLOBs.getScriptTemplate(), true);
        Expression expression = this.aviatorEvaluatorInstance.compile(scriptTemplateWithBLOBs.getScriptTemplate(), true);
        return expression;
    }


    /**
     * 失效某个脚本模板
     *
     * @param templateId
     * @throws BusinessException
     */
    public void invalidateScriptTemplate(Integer templateId) {
        if (templateId == null) {
            return;
        }
        this.aviatorEvaluatorInstance.invalidateCacheByKey(templateId.toString());
    }

    /**
     * 清除所有脚本模板缓存
     */
    public void clearScriptTemplateCache() {
        this.aviatorEvaluatorInstance.clearExpressionCache();
    }


    /**
     * 执行模板
     *
     * @param templateKey
     * @return
     * @throws BusinessException
     */
    public String executeScriptTemplate(String templateKey) throws BusinessException {
        return executeScriptTemplate(templateKey ,null);
    }


    /**
     * 执行脚本
     *
     * @param evn
     * @return
     */
    public String executeScriptTemplate(String templateKey, Map<String, Object> evn) throws BusinessException {
        ScriptTemplateWithBLOBs scriptTemplate = scriptTemplateDao.getScriptTemplateByKey(templateKey);
        return executeScriptTemplate(scriptTemplate, evn);
    }

    public String executeScriptTemplate(Integer id) throws BusinessException {
        ScriptTemplateWithBLOBs scriptTemplate = getScriptTemplateById(id);
        return executeScriptTemplate(scriptTemplate, null);
    }


    public String executeScriptTemplate(ScriptTemplateWithBLOBs scriptTemplate, Map<String, Object> evn) throws BusinessException {
        Object contentResult = "";
        if (scriptTemplate == null) {
            throw new BusinessException("未找到配置的脚本模板");
        }
        if (StringUtils.isBlank(scriptTemplate.getScriptTemplate())) {
            return "";
        }
        //脚本执行
        Expression expression = fetchExpression(scriptTemplate);
        if (evn != null && !evn.isEmpty()) {
            contentResult =  expression.execute(evn);
        }else {
            contentResult = expression.execute();
        }

        return contentResult.toString();
    }


}
