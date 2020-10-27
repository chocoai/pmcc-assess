package com.copower.pmcc.assess.service;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.ScriptTemplateDao;
import com.copower.pmcc.assess.dal.basis.entity.ScriptTemplate;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.*;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.google.common.collect.*;
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
    @Autowired
    private ApplicationConstant applicationConstant;

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
    private Expression fetchExpression(ScriptTemplate scriptTemplateWithBLOBs) {
        Expression expression = this.aviatorEvaluatorInstance.compile(scriptTemplateWithBLOBs.getId().toString(), scriptTemplateWithBLOBs.getScriptTemplate(), true);
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
     * @param projectId
     * @param templateId
     * @return
     * @throws BusinessException
     */
    public String executeScriptTemplate(Integer projectId, Integer templateId) throws BusinessException {
        String contentResult = "";

        if (templateId == null) {
            return contentResult;
        }

        //获取模板
        ScriptTemplate scriptTemplate = scriptTemplateDao.getScriptTemplateById(templateId);
        if (scriptTemplate == null) {
            throw new BusinessException("未找到配置的脚本模板");
        }

        if (StringUtils.isBlank(scriptTemplate.getScriptTemplate())) {
            return contentResult;
        }
        Map<String, Object> evn = Maps.newHashMap();

        //脚本执行
        Expression expression = fetchExpression(scriptTemplate);
        contentResult = (String) expression.execute(evn);

        return contentResult;
    }


    /**
     * 执行脚本
     *
     * @param evn
     * @return
     */
    public String executeScriptTemplate(Integer templateId, Map<String, Object> evn) throws BusinessException {
        String contentResult = "";

        if (templateId == null) {
            return contentResult;
        }

        //获取模板
        ScriptTemplate scriptTemplate = scriptTemplateDao.getScriptTemplateById(templateId);
        if (scriptTemplate == null) {
            throw new BusinessException("未找到配置的脚本模板");
        }

        if (StringUtils.isBlank(scriptTemplate.getScriptTemplate())) {
            return contentResult;
        }

        //脚本执行
        Expression expression = fetchExpression(scriptTemplate);
        contentResult = (String) expression.execute(evn);

        return contentResult;
    }

    /**
     * 执行脚本
     *
     * @param evn
     * @return
     */
    public String executeScriptTemplate(String scriptTemplate, Map<String, Object> evn) {
        String contentResult = "";

        if (StringUtils.isBlank(scriptTemplate)) {
            return contentResult;
        }

        String cacheKey = Md5Utils.md5Encode(scriptTemplate);
        try {
            //脚本执行
            Expression expression = this.aviatorEvaluatorInstance.compile(cacheKey, scriptTemplate, true);
            return (String) expression.execute(evn);
        } finally {
            this.aviatorEvaluatorInstance.invalidateCacheByKey(cacheKey);
        }
    }


}
