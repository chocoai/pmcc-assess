package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ScriptTemplate implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 模板名称
     */
    private String templateName;

    /**
     * 模板key
     */
    private String templateKey;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date gmtCreated;

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     */
    private Date gmtModified;

    /**
     * tb_script_template
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 模板名称
     * @return template_name 模板名称
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * 模板名称
     * @param templateName 模板名称
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    /**
     * 模板key
     * @return template_key 模板key
     */
    public String getTemplateKey() {
        return templateKey;
    }

    /**
     * 模板key
     * @param templateKey 模板key
     */
    public void setTemplateKey(String templateKey) {
        this.templateKey = templateKey == null ? null : templateKey.trim();
    }

    /**
     * 创建人
     * @return creator 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * 创建时间
     * @return gmt_created 创建时间
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * 创建时间
     * @param gmtCreated 创建时间
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     * @return gmt_modified 最后更新时间，记录变化后会自动更新时间戳
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     * @param gmtModified 最后更新时间，记录变化后会自动更新时间戳
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public static ScriptTemplate.Builder builder() {
        return new ScriptTemplate.Builder();
    }

    /**
     * tb_script_template
     */
    public static class Builder {
        /**
         * tb_script_template
         */
        private ScriptTemplate obj;

        public Builder() {
            this.obj = new ScriptTemplate();
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
        public Builder gmtCreated(Date gmtCreated) {
            obj.setGmtCreated(gmtCreated);
            return this;
        }

        /**
         * 最后更新时间，记录变化后会自动更新时间戳
         * @param gmtModified 最后更新时间，记录变化后会自动更新时间戳
         */
        public Builder gmtModified(Date gmtModified) {
            obj.setGmtModified(gmtModified);
            return this;
        }

        public ScriptTemplate build() {
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