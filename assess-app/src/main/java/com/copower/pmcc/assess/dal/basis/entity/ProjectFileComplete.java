package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ProjectFileComplete implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 所属项目
     */
    private Integer projectId;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 排序
     */
    private Integer fileSort;

    /**
     * 归档日期
     */
    private Date fileDate;

    /**
     * 档案类型
     */
    private String fileType;

    /**
     * 档案编号
     */
    private String fileNumber;

    /**
     * 档案名称
     */
    private String fileName;

    /**
     * 是否存在纸质文件
     */
    private Boolean bisPaper;

    /**
     * 是否有效
     */
    private Boolean bisEnable;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     */
    private Date modified;

    /**
     * tb_project_file_complete
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
     * 所属项目
     * @return project_id 所属项目
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 所属项目
     * @param projectId 所属项目
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 年份
     * @return year 年份
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 年份
     * @param year 年份
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 排序
     * @return file_sort 排序
     */
    public Integer getFileSort() {
        return fileSort;
    }

    /**
     * 排序
     * @param fileSort 排序
     */
    public void setFileSort(Integer fileSort) {
        this.fileSort = fileSort;
    }

    /**
     * 归档日期
     * @return file_date 归档日期
     */
    public Date getFileDate() {
        return fileDate;
    }

    /**
     * 归档日期
     * @param fileDate 归档日期
     */
    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }

    /**
     * 档案类型
     * @return file_type 档案类型
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * 档案类型
     * @param fileType 档案类型
     */
    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    /**
     * 档案编号
     * @return file_number 档案编号
     */
    public String getFileNumber() {
        return fileNumber;
    }

    /**
     * 档案编号
     * @param fileNumber 档案编号
     */
    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber == null ? null : fileNumber.trim();
    }

    /**
     * 档案名称
     * @return file_name 档案名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 档案名称
     * @param fileName 档案名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * 是否存在纸质文件
     * @return bis_paper 是否存在纸质文件
     */
    public Boolean getBisPaper() {
        return bisPaper;
    }

    /**
     * 是否存在纸质文件
     * @param bisPaper 是否存在纸质文件
     */
    public void setBisPaper(Boolean bisPaper) {
        this.bisPaper = bisPaper;
    }

    /**
     * 是否有效
     * @return bis_enable 是否有效
     */
    public Boolean getBisEnable() {
        return bisEnable;
    }

    /**
     * 是否有效
     * @param bisEnable 是否有效
     */
    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
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
     * @return created 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 创建时间
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     * @return modified 最后更新时间，记录变化后会自动更新时间戳
     */
    public Date getModified() {
        return modified;
    }

    /**
     * 最后更新时间，记录变化后会自动更新时间戳
     * @param modified 最后更新时间，记录变化后会自动更新时间戳
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

    public static ProjectFileComplete.Builder builder() {
        return new ProjectFileComplete.Builder();
    }

    /**
     * tb_project_file_complete
     */
    public static class Builder {
        /**
         * tb_project_file_complete
         */
        private ProjectFileComplete obj;

        public Builder() {
            this.obj = new ProjectFileComplete();
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
         * 所属项目
         * @param projectId 所属项目
         */
        public Builder projectId(Integer projectId) {
            obj.setProjectId(projectId);
            return this;
        }

        /**
         * 年份
         * @param year 年份
         */
        public Builder year(Integer year) {
            obj.setYear(year);
            return this;
        }

        /**
         * 排序
         * @param fileSort 排序
         */
        public Builder fileSort(Integer fileSort) {
            obj.setFileSort(fileSort);
            return this;
        }

        /**
         * 归档日期
         * @param fileDate 归档日期
         */
        public Builder fileDate(Date fileDate) {
            obj.setFileDate(fileDate);
            return this;
        }

        /**
         * 档案类型
         * @param fileType 档案类型
         */
        public Builder fileType(String fileType) {
            obj.setFileType(fileType);
            return this;
        }

        /**
         * 档案编号
         * @param fileNumber 档案编号
         */
        public Builder fileNumber(String fileNumber) {
            obj.setFileNumber(fileNumber);
            return this;
        }

        /**
         * 档案名称
         * @param fileName 档案名称
         */
        public Builder fileName(String fileName) {
            obj.setFileName(fileName);
            return this;
        }

        /**
         * 是否存在纸质文件
         * @param bisPaper 是否存在纸质文件
         */
        public Builder bisPaper(Boolean bisPaper) {
            obj.setBisPaper(bisPaper);
            return this;
        }

        /**
         * 是否有效
         * @param bisEnable 是否有效
         */
        public Builder bisEnable(Boolean bisEnable) {
            obj.setBisEnable(bisEnable);
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
         * @param created 创建时间
         */
        public Builder created(Date created) {
            obj.setCreated(created);
            return this;
        }

        /**
         * 最后更新时间，记录变化后会自动更新时间戳
         * @param modified 最后更新时间，记录变化后会自动更新时间戳
         */
        public Builder modified(Date modified) {
            obj.setModified(modified);
            return this;
        }

        public ProjectFileComplete build() {
            return this.obj;
        }
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        projectId("project_id", "projectId", "INTEGER", false),
        year("year", "year", "INTEGER", false),
        fileSort("file_sort", "fileSort", "INTEGER", false),
        fileDate("file_date", "fileDate", "TIMESTAMP", false),
        fileType("file_type", "fileType", "VARCHAR", false),
        fileNumber("file_number", "fileNumber", "VARCHAR", false),
        fileName("file_name", "fileName", "VARCHAR", false),
        bisPaper("bis_paper", "bisPaper", "BIT", false),
        bisEnable("bis_enable", "bisEnable", "BIT", false),
        creator("creator", "creator", "VARCHAR", false),
        created("created", "created", "TIMESTAMP", false),
        modified("modified", "modified", "TIMESTAMP", false);

        /**
         * tb_project_file_complete
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_project_file_complete
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_project_file_complete
         */
        private final String column;

        /**
         * tb_project_file_complete
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_project_file_complete
         */
        private final String javaProperty;

        /**
         * tb_project_file_complete
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