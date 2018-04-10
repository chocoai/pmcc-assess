package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseAttachmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseAttachmentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("table_name is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("table_name is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("table_name =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("table_name <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("table_name >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("table_name >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("table_name <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("table_name <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("table_name like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("table_name not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("table_name in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("table_name not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("table_name between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("table_name not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableIdIsNull() {
            addCriterion("table_id is null");
            return (Criteria) this;
        }

        public Criteria andTableIdIsNotNull() {
            addCriterion("table_id is not null");
            return (Criteria) this;
        }

        public Criteria andTableIdEqualTo(Integer value) {
            addCriterion("table_id =", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotEqualTo(Integer value) {
            addCriterion("table_id <>", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThan(Integer value) {
            addCriterion("table_id >", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("table_id >=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThan(Integer value) {
            addCriterion("table_id <", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdLessThanOrEqualTo(Integer value) {
            addCriterion("table_id <=", value, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdIn(List<Integer> values) {
            addCriterion("table_id in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotIn(List<Integer> values) {
            addCriterion("table_id not in", values, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdBetween(Integer value1, Integer value2) {
            addCriterion("table_id between", value1, value2, "tableId");
            return (Criteria) this;
        }

        public Criteria andTableIdNotBetween(Integer value1, Integer value2) {
            addCriterion("table_id not between", value1, value2, "tableId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIsNull() {
            addCriterion("process_ins_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIsNotNull() {
            addCriterion("process_ins_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdEqualTo(String value) {
            addCriterion("process_ins_id =", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotEqualTo(String value) {
            addCriterion("process_ins_id <>", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdGreaterThan(String value) {
            addCriterion("process_ins_id >", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdGreaterThanOrEqualTo(String value) {
            addCriterion("process_ins_id >=", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLessThan(String value) {
            addCriterion("process_ins_id <", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLessThanOrEqualTo(String value) {
            addCriterion("process_ins_id <=", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdLike(String value) {
            addCriterion("process_ins_id like", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotLike(String value) {
            addCriterion("process_ins_id not like", value, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdIn(List<String> values) {
            addCriterion("process_ins_id in", values, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotIn(List<String> values) {
            addCriterion("process_ins_id not in", values, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdBetween(String value1, String value2) {
            addCriterion("process_ins_id between", value1, value2, "processInsId");
            return (Criteria) this;
        }

        public Criteria andProcessInsIdNotBetween(String value1, String value2) {
            addCriterion("process_ins_id not between", value1, value2, "processInsId");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNull() {
            addCriterion("file_path is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("file_path is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("file_path =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("file_path <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("file_path >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("file_path >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("file_path <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("file_path <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("file_path like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("file_path not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("file_path in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("file_path not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("file_path between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("file_path not between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathPdfIsNull() {
            addCriterion("file_path_pdf is null");
            return (Criteria) this;
        }

        public Criteria andFilePathPdfIsNotNull() {
            addCriterion("file_path_pdf is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathPdfEqualTo(String value) {
            addCriterion("file_path_pdf =", value, "filePathPdf");
            return (Criteria) this;
        }

        public Criteria andFilePathPdfNotEqualTo(String value) {
            addCriterion("file_path_pdf <>", value, "filePathPdf");
            return (Criteria) this;
        }

        public Criteria andFilePathPdfGreaterThan(String value) {
            addCriterion("file_path_pdf >", value, "filePathPdf");
            return (Criteria) this;
        }

        public Criteria andFilePathPdfGreaterThanOrEqualTo(String value) {
            addCriterion("file_path_pdf >=", value, "filePathPdf");
            return (Criteria) this;
        }

        public Criteria andFilePathPdfLessThan(String value) {
            addCriterion("file_path_pdf <", value, "filePathPdf");
            return (Criteria) this;
        }

        public Criteria andFilePathPdfLessThanOrEqualTo(String value) {
            addCriterion("file_path_pdf <=", value, "filePathPdf");
            return (Criteria) this;
        }

        public Criteria andFilePathPdfLike(String value) {
            addCriterion("file_path_pdf like", value, "filePathPdf");
            return (Criteria) this;
        }

        public Criteria andFilePathPdfNotLike(String value) {
            addCriterion("file_path_pdf not like", value, "filePathPdf");
            return (Criteria) this;
        }

        public Criteria andFilePathPdfIn(List<String> values) {
            addCriterion("file_path_pdf in", values, "filePathPdf");
            return (Criteria) this;
        }

        public Criteria andFilePathPdfNotIn(List<String> values) {
            addCriterion("file_path_pdf not in", values, "filePathPdf");
            return (Criteria) this;
        }

        public Criteria andFilePathPdfBetween(String value1, String value2) {
            addCriterion("file_path_pdf between", value1, value2, "filePathPdf");
            return (Criteria) this;
        }

        public Criteria andFilePathPdfNotBetween(String value1, String value2) {
            addCriterion("file_path_pdf not between", value1, value2, "filePathPdf");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("file_name is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("file_name is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("file_name =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("file_name <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("file_name >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("file_name >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("file_name <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("file_name <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("file_name like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("file_name not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("file_name in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("file_name not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("file_name between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("file_name not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFtpFilesNameIsNull() {
            addCriterion("ftp_files_name is null");
            return (Criteria) this;
        }

        public Criteria andFtpFilesNameIsNotNull() {
            addCriterion("ftp_files_name is not null");
            return (Criteria) this;
        }

        public Criteria andFtpFilesNameEqualTo(String value) {
            addCriterion("ftp_files_name =", value, "ftpFilesName");
            return (Criteria) this;
        }

        public Criteria andFtpFilesNameNotEqualTo(String value) {
            addCriterion("ftp_files_name <>", value, "ftpFilesName");
            return (Criteria) this;
        }

        public Criteria andFtpFilesNameGreaterThan(String value) {
            addCriterion("ftp_files_name >", value, "ftpFilesName");
            return (Criteria) this;
        }

        public Criteria andFtpFilesNameGreaterThanOrEqualTo(String value) {
            addCriterion("ftp_files_name >=", value, "ftpFilesName");
            return (Criteria) this;
        }

        public Criteria andFtpFilesNameLessThan(String value) {
            addCriterion("ftp_files_name <", value, "ftpFilesName");
            return (Criteria) this;
        }

        public Criteria andFtpFilesNameLessThanOrEqualTo(String value) {
            addCriterion("ftp_files_name <=", value, "ftpFilesName");
            return (Criteria) this;
        }

        public Criteria andFtpFilesNameLike(String value) {
            addCriterion("ftp_files_name like", value, "ftpFilesName");
            return (Criteria) this;
        }

        public Criteria andFtpFilesNameNotLike(String value) {
            addCriterion("ftp_files_name not like", value, "ftpFilesName");
            return (Criteria) this;
        }

        public Criteria andFtpFilesNameIn(List<String> values) {
            addCriterion("ftp_files_name in", values, "ftpFilesName");
            return (Criteria) this;
        }

        public Criteria andFtpFilesNameNotIn(List<String> values) {
            addCriterion("ftp_files_name not in", values, "ftpFilesName");
            return (Criteria) this;
        }

        public Criteria andFtpFilesNameBetween(String value1, String value2) {
            addCriterion("ftp_files_name between", value1, value2, "ftpFilesName");
            return (Criteria) this;
        }

        public Criteria andFtpFilesNameNotBetween(String value1, String value2) {
            addCriterion("ftp_files_name not between", value1, value2, "ftpFilesName");
            return (Criteria) this;
        }

        public Criteria andFileExtensionIsNull() {
            addCriterion("file_extension is null");
            return (Criteria) this;
        }

        public Criteria andFileExtensionIsNotNull() {
            addCriterion("file_extension is not null");
            return (Criteria) this;
        }

        public Criteria andFileExtensionEqualTo(String value) {
            addCriterion("file_extension =", value, "fileExtension");
            return (Criteria) this;
        }

        public Criteria andFileExtensionNotEqualTo(String value) {
            addCriterion("file_extension <>", value, "fileExtension");
            return (Criteria) this;
        }

        public Criteria andFileExtensionGreaterThan(String value) {
            addCriterion("file_extension >", value, "fileExtension");
            return (Criteria) this;
        }

        public Criteria andFileExtensionGreaterThanOrEqualTo(String value) {
            addCriterion("file_extension >=", value, "fileExtension");
            return (Criteria) this;
        }

        public Criteria andFileExtensionLessThan(String value) {
            addCriterion("file_extension <", value, "fileExtension");
            return (Criteria) this;
        }

        public Criteria andFileExtensionLessThanOrEqualTo(String value) {
            addCriterion("file_extension <=", value, "fileExtension");
            return (Criteria) this;
        }

        public Criteria andFileExtensionLike(String value) {
            addCriterion("file_extension like", value, "fileExtension");
            return (Criteria) this;
        }

        public Criteria andFileExtensionNotLike(String value) {
            addCriterion("file_extension not like", value, "fileExtension");
            return (Criteria) this;
        }

        public Criteria andFileExtensionIn(List<String> values) {
            addCriterion("file_extension in", values, "fileExtension");
            return (Criteria) this;
        }

        public Criteria andFileExtensionNotIn(List<String> values) {
            addCriterion("file_extension not in", values, "fileExtension");
            return (Criteria) this;
        }

        public Criteria andFileExtensionBetween(String value1, String value2) {
            addCriterion("file_extension between", value1, value2, "fileExtension");
            return (Criteria) this;
        }

        public Criteria andFileExtensionNotBetween(String value1, String value2) {
            addCriterion("file_extension not between", value1, value2, "fileExtension");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNull() {
            addCriterion("file_size is null");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNotNull() {
            addCriterion("file_size is not null");
            return (Criteria) this;
        }

        public Criteria andFileSizeEqualTo(String value) {
            addCriterion("file_size =", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotEqualTo(String value) {
            addCriterion("file_size <>", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThan(String value) {
            addCriterion("file_size >", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThanOrEqualTo(String value) {
            addCriterion("file_size >=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThan(String value) {
            addCriterion("file_size <", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThanOrEqualTo(String value) {
            addCriterion("file_size <=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLike(String value) {
            addCriterion("file_size like", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotLike(String value) {
            addCriterion("file_size not like", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeIn(List<String> values) {
            addCriterion("file_size in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotIn(List<String> values) {
            addCriterion("file_size not in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeBetween(String value1, String value2) {
            addCriterion("file_size between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotBetween(String value1, String value2) {
            addCriterion("file_size not between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andReNameIsNull() {
            addCriterion("re_name is null");
            return (Criteria) this;
        }

        public Criteria andReNameIsNotNull() {
            addCriterion("re_name is not null");
            return (Criteria) this;
        }

        public Criteria andReNameEqualTo(String value) {
            addCriterion("re_name =", value, "reName");
            return (Criteria) this;
        }

        public Criteria andReNameNotEqualTo(String value) {
            addCriterion("re_name <>", value, "reName");
            return (Criteria) this;
        }

        public Criteria andReNameGreaterThan(String value) {
            addCriterion("re_name >", value, "reName");
            return (Criteria) this;
        }

        public Criteria andReNameGreaterThanOrEqualTo(String value) {
            addCriterion("re_name >=", value, "reName");
            return (Criteria) this;
        }

        public Criteria andReNameLessThan(String value) {
            addCriterion("re_name <", value, "reName");
            return (Criteria) this;
        }

        public Criteria andReNameLessThanOrEqualTo(String value) {
            addCriterion("re_name <=", value, "reName");
            return (Criteria) this;
        }

        public Criteria andReNameLike(String value) {
            addCriterion("re_name like", value, "reName");
            return (Criteria) this;
        }

        public Criteria andReNameNotLike(String value) {
            addCriterion("re_name not like", value, "reName");
            return (Criteria) this;
        }

        public Criteria andReNameIn(List<String> values) {
            addCriterion("re_name in", values, "reName");
            return (Criteria) this;
        }

        public Criteria andReNameNotIn(List<String> values) {
            addCriterion("re_name not in", values, "reName");
            return (Criteria) this;
        }

        public Criteria andReNameBetween(String value1, String value2) {
            addCriterion("re_name between", value1, value2, "reName");
            return (Criteria) this;
        }

        public Criteria andReNameNotBetween(String value1, String value2) {
            addCriterion("re_name not between", value1, value2, "reName");
            return (Criteria) this;
        }

        public Criteria andReActivityNameIsNull() {
            addCriterion("re_activity_name is null");
            return (Criteria) this;
        }

        public Criteria andReActivityNameIsNotNull() {
            addCriterion("re_activity_name is not null");
            return (Criteria) this;
        }

        public Criteria andReActivityNameEqualTo(String value) {
            addCriterion("re_activity_name =", value, "reActivityName");
            return (Criteria) this;
        }

        public Criteria andReActivityNameNotEqualTo(String value) {
            addCriterion("re_activity_name <>", value, "reActivityName");
            return (Criteria) this;
        }

        public Criteria andReActivityNameGreaterThan(String value) {
            addCriterion("re_activity_name >", value, "reActivityName");
            return (Criteria) this;
        }

        public Criteria andReActivityNameGreaterThanOrEqualTo(String value) {
            addCriterion("re_activity_name >=", value, "reActivityName");
            return (Criteria) this;
        }

        public Criteria andReActivityNameLessThan(String value) {
            addCriterion("re_activity_name <", value, "reActivityName");
            return (Criteria) this;
        }

        public Criteria andReActivityNameLessThanOrEqualTo(String value) {
            addCriterion("re_activity_name <=", value, "reActivityName");
            return (Criteria) this;
        }

        public Criteria andReActivityNameLike(String value) {
            addCriterion("re_activity_name like", value, "reActivityName");
            return (Criteria) this;
        }

        public Criteria andReActivityNameNotLike(String value) {
            addCriterion("re_activity_name not like", value, "reActivityName");
            return (Criteria) this;
        }

        public Criteria andReActivityNameIn(List<String> values) {
            addCriterion("re_activity_name in", values, "reActivityName");
            return (Criteria) this;
        }

        public Criteria andReActivityNameNotIn(List<String> values) {
            addCriterion("re_activity_name not in", values, "reActivityName");
            return (Criteria) this;
        }

        public Criteria andReActivityNameBetween(String value1, String value2) {
            addCriterion("re_activity_name between", value1, value2, "reActivityName");
            return (Criteria) this;
        }

        public Criteria andReActivityNameNotBetween(String value1, String value2) {
            addCriterion("re_activity_name not between", value1, value2, "reActivityName");
            return (Criteria) this;
        }

        public Criteria andFieldsNameIsNull() {
            addCriterion("fields_name is null");
            return (Criteria) this;
        }

        public Criteria andFieldsNameIsNotNull() {
            addCriterion("fields_name is not null");
            return (Criteria) this;
        }

        public Criteria andFieldsNameEqualTo(String value) {
            addCriterion("fields_name =", value, "fieldsName");
            return (Criteria) this;
        }

        public Criteria andFieldsNameNotEqualTo(String value) {
            addCriterion("fields_name <>", value, "fieldsName");
            return (Criteria) this;
        }

        public Criteria andFieldsNameGreaterThan(String value) {
            addCriterion("fields_name >", value, "fieldsName");
            return (Criteria) this;
        }

        public Criteria andFieldsNameGreaterThanOrEqualTo(String value) {
            addCriterion("fields_name >=", value, "fieldsName");
            return (Criteria) this;
        }

        public Criteria andFieldsNameLessThan(String value) {
            addCriterion("fields_name <", value, "fieldsName");
            return (Criteria) this;
        }

        public Criteria andFieldsNameLessThanOrEqualTo(String value) {
            addCriterion("fields_name <=", value, "fieldsName");
            return (Criteria) this;
        }

        public Criteria andFieldsNameLike(String value) {
            addCriterion("fields_name like", value, "fieldsName");
            return (Criteria) this;
        }

        public Criteria andFieldsNameNotLike(String value) {
            addCriterion("fields_name not like", value, "fieldsName");
            return (Criteria) this;
        }

        public Criteria andFieldsNameIn(List<String> values) {
            addCriterion("fields_name in", values, "fieldsName");
            return (Criteria) this;
        }

        public Criteria andFieldsNameNotIn(List<String> values) {
            addCriterion("fields_name not in", values, "fieldsName");
            return (Criteria) this;
        }

        public Criteria andFieldsNameBetween(String value1, String value2) {
            addCriterion("fields_name between", value1, value2, "fieldsName");
            return (Criteria) this;
        }

        public Criteria andFieldsNameNotBetween(String value1, String value2) {
            addCriterion("fields_name not between", value1, value2, "fieldsName");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Integer value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Integer value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Integer value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Integer value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Integer> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Integer> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andBisLastEditIsNull() {
            addCriterion("bis_last_edit is null");
            return (Criteria) this;
        }

        public Criteria andBisLastEditIsNotNull() {
            addCriterion("bis_last_edit is not null");
            return (Criteria) this;
        }

        public Criteria andBisLastEditEqualTo(Boolean value) {
            addCriterion("bis_last_edit =", value, "bisLastEdit");
            return (Criteria) this;
        }

        public Criteria andBisLastEditNotEqualTo(Boolean value) {
            addCriterion("bis_last_edit <>", value, "bisLastEdit");
            return (Criteria) this;
        }

        public Criteria andBisLastEditGreaterThan(Boolean value) {
            addCriterion("bis_last_edit >", value, "bisLastEdit");
            return (Criteria) this;
        }

        public Criteria andBisLastEditGreaterThanOrEqualTo(Boolean value) {
            addCriterion("bis_last_edit >=", value, "bisLastEdit");
            return (Criteria) this;
        }

        public Criteria andBisLastEditLessThan(Boolean value) {
            addCriterion("bis_last_edit <", value, "bisLastEdit");
            return (Criteria) this;
        }

        public Criteria andBisLastEditLessThanOrEqualTo(Boolean value) {
            addCriterion("bis_last_edit <=", value, "bisLastEdit");
            return (Criteria) this;
        }

        public Criteria andBisLastEditIn(List<Boolean> values) {
            addCriterion("bis_last_edit in", values, "bisLastEdit");
            return (Criteria) this;
        }

        public Criteria andBisLastEditNotIn(List<Boolean> values) {
            addCriterion("bis_last_edit not in", values, "bisLastEdit");
            return (Criteria) this;
        }

        public Criteria andBisLastEditBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_last_edit between", value1, value2, "bisLastEdit");
            return (Criteria) this;
        }

        public Criteria andBisLastEditNotBetween(Boolean value1, Boolean value2) {
            addCriterion("bis_last_edit not between", value1, value2, "bisLastEdit");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNull() {
            addCriterion("creater is null");
            return (Criteria) this;
        }

        public Criteria andCreaterIsNotNull() {
            addCriterion("creater is not null");
            return (Criteria) this;
        }

        public Criteria andCreaterEqualTo(String value) {
            addCriterion("creater =", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotEqualTo(String value) {
            addCriterion("creater <>", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThan(String value) {
            addCriterion("creater >", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterGreaterThanOrEqualTo(String value) {
            addCriterion("creater >=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThan(String value) {
            addCriterion("creater <", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLessThanOrEqualTo(String value) {
            addCriterion("creater <=", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterLike(String value) {
            addCriterion("creater like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotLike(String value) {
            addCriterion("creater not like", value, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterIn(List<String> values) {
            addCriterion("creater in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotIn(List<String> values) {
            addCriterion("creater not in", values, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterBetween(String value1, String value2) {
            addCriterion("creater between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreaterNotBetween(String value1, String value2) {
            addCriterion("creater not between", value1, value2, "creater");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andModifiedIsNull() {
            addCriterion("modified is null");
            return (Criteria) this;
        }

        public Criteria andModifiedIsNotNull() {
            addCriterion("modified is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedEqualTo(Date value) {
            addCriterion("modified =", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotEqualTo(Date value) {
            addCriterion("modified <>", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedGreaterThan(Date value) {
            addCriterion("modified >", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("modified >=", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedLessThan(Date value) {
            addCriterion("modified <", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedLessThanOrEqualTo(Date value) {
            addCriterion("modified <=", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedIn(List<Date> values) {
            addCriterion("modified in", values, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotIn(List<Date> values) {
            addCriterion("modified not in", values, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedBetween(Date value1, Date value2) {
            addCriterion("modified between", value1, value2, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotBetween(Date value1, Date value2) {
            addCriterion("modified not between", value1, value2, "modified");
            return (Criteria) this;
        }

        public Criteria andModifierIsNull() {
            addCriterion("modifier is null");
            return (Criteria) this;
        }

        public Criteria andModifierIsNotNull() {
            addCriterion("modifier is not null");
            return (Criteria) this;
        }

        public Criteria andModifierEqualTo(String value) {
            addCriterion("modifier =", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotEqualTo(String value) {
            addCriterion("modifier <>", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThan(String value) {
            addCriterion("modifier >", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierGreaterThanOrEqualTo(String value) {
            addCriterion("modifier >=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThan(String value) {
            addCriterion("modifier <", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLessThanOrEqualTo(String value) {
            addCriterion("modifier <=", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierLike(String value) {
            addCriterion("modifier like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotLike(String value) {
            addCriterion("modifier not like", value, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierIn(List<String> values) {
            addCriterion("modifier in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotIn(List<String> values) {
            addCriterion("modifier not in", values, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierBetween(String value1, String value2) {
            addCriterion("modifier between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andModifierNotBetween(String value1, String value2) {
            addCriterion("modifier not between", value1, value2, "modifier");
            return (Criteria) this;
        }

        public Criteria andProcessTaskIdIsNull() {
            addCriterion("process_task_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessTaskIdIsNotNull() {
            addCriterion("process_task_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessTaskIdEqualTo(String value) {
            addCriterion("process_task_id =", value, "processTaskId");
            return (Criteria) this;
        }

        public Criteria andProcessTaskIdNotEqualTo(String value) {
            addCriterion("process_task_id <>", value, "processTaskId");
            return (Criteria) this;
        }

        public Criteria andProcessTaskIdGreaterThan(String value) {
            addCriterion("process_task_id >", value, "processTaskId");
            return (Criteria) this;
        }

        public Criteria andProcessTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("process_task_id >=", value, "processTaskId");
            return (Criteria) this;
        }

        public Criteria andProcessTaskIdLessThan(String value) {
            addCriterion("process_task_id <", value, "processTaskId");
            return (Criteria) this;
        }

        public Criteria andProcessTaskIdLessThanOrEqualTo(String value) {
            addCriterion("process_task_id <=", value, "processTaskId");
            return (Criteria) this;
        }

        public Criteria andProcessTaskIdLike(String value) {
            addCriterion("process_task_id like", value, "processTaskId");
            return (Criteria) this;
        }

        public Criteria andProcessTaskIdNotLike(String value) {
            addCriterion("process_task_id not like", value, "processTaskId");
            return (Criteria) this;
        }

        public Criteria andProcessTaskIdIn(List<String> values) {
            addCriterion("process_task_id in", values, "processTaskId");
            return (Criteria) this;
        }

        public Criteria andProcessTaskIdNotIn(List<String> values) {
            addCriterion("process_task_id not in", values, "processTaskId");
            return (Criteria) this;
        }

        public Criteria andProcessTaskIdBetween(String value1, String value2) {
            addCriterion("process_task_id between", value1, value2, "processTaskId");
            return (Criteria) this;
        }

        public Criteria andProcessTaskIdNotBetween(String value1, String value2) {
            addCriterion("process_task_id not between", value1, value2, "processTaskId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}