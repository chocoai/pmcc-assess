package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class ScriptTemplateWithBLOBs extends ScriptTemplate implements Serializable {
    /**
     * 模板变量
     */
    private String parameter;

    /**
     * 模板原始文本
     */
    private String templateOriginalText;

    /**
     * 脚本模板
     */
    private String scriptTemplate;

    /**
     * tb_script_template
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模板变量
     * @return parameter 模板变量
     */
    public String getParameter() {
        return parameter;
    }

    /**
     * 模板变量
     * @param parameter 模板变量
     */
    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }

    /**
     * 模板原始文本
     * @return template_original_text 模板原始文本
     */
    public String getTemplateOriginalText() {
        return templateOriginalText;
    }

    /**
     * 模板原始文本
     * @param templateOriginalText 模板原始文本
     */
    public void setTemplateOriginalText(String templateOriginalText) {
        this.templateOriginalText = templateOriginalText == null ? null : templateOriginalText.trim();
    }

    /**
     * 脚本模板
     * @return script_template 脚本模板
     */
    public String getScriptTemplate() {
        return scriptTemplate;
    }

    /**
     * 脚本模板
     * @param scriptTemplate 脚本模板
     */
    public void setScriptTemplate(String scriptTemplate) {
        this.scriptTemplate = scriptTemplate == null ? null : scriptTemplate.trim();
    }

    public static ScriptTemplateWithBLOBs.Builder builder() {
        return new ScriptTemplateWithBLOBs.Builder();
    }

    /**
     * tb_script_template
     */
    public static class Builder extends ScriptTemplate.Builder {
        /**
         * tb_script_template
         */
        private ScriptTemplateWithBLOBs obj;

        public Builder() {
            this.obj = new ScriptTemplateWithBLOBs();
        }

        /**
         * 
         * @param id 
         */
        public Builder id(Integer id) {
            obj.setId(id);
            return this;
        }

        /**
         * 模板名称
         * @param templateName 模板名称
         */
        public Builder templateName(String templateName) {
            obj.setTemplateName(templateName);
            return this;
        }

        /**
         * 模板key
         * @param templateKey 模板key
         */
        public Builder templateKey(String templateKey) {
            obj.setTemplateKey(templateKey);
            return this;
        }

        /**
         * 创建人
         * @param creator 创建人
         */
        public Builder creator(String creator) {
            obj.setCreator(creator);
            return this;
        }

        /**
         * 创建时间
         * @param gmtCreated 创建时间
         */
        public Builder gmtCreated(java.util.Date gmtCreated) {
            obj.setGmtCreated(gmtCreated);
            return this;
        }

        /**
         * 最后更新时间，记录变化后会自动更新时间戳
         * @param gmtModified 最后更新时间，记录变化后会自动更新时间戳
         */
        public Builder gmtModified(java.util.Date gmtModified) {
            obj.setGmtModified(gmtModified);
            return this;
        }

        /**
         * 模板变量
         * @param parameter 模板变量
         */
        public Builder parameter(String parameter) {
            obj.setParameter(parameter);
            return this;
        }

        /**
         * 模板原始文本
         * @param templateOriginalText 模板原始文本
         */
        public Builder templateOriginalText(String templateOriginalText) {
            obj.setTemplateOriginalText(templateOriginalText);
            return this;
        }

        /**
         * 脚本模板
         * @param scriptTemplate 脚本模板
         */
        public Builder scriptTemplate(String scriptTemplate) {
            obj.setScriptTemplate(scriptTemplate);
            return this;
        }

        public ScriptTemplateWithBLOBs build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        templateName("template_name", "templateName", "VARCHAR", false),
        templateKey("template_key", "templateKey", "VARCHAR", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false),
        parameter("parameter", "parameter", "LONGVARCHAR", false),
        templateOriginalText("template_original_text", "templateOriginalText", "LONGVARCHAR", false),
        scriptTemplate("script_template", "scriptTemplate", "LONGVARCHAR", false);

        /**
         * tb_script_template
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_script_template
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_script_template
         */
        private final String column;

        /**
         * tb_script_template
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_script_template
         */
        private final String javaProperty;

        /**
         * tb_script_template
         */
        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}