package com.copower.pmcc.assess.dal.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseAttachmentKeepExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseAttachmentKeepExample() {
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

        public Criteria andAttachmentIdIsNull() {
            addCriterion("attachment_id is null");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdIsNotNull() {
            addCriterion("attachment_id is not null");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdEqualTo(Integer value) {
            addCriterion("attachment_id =", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdNotEqualTo(Integer value) {
            addCriterion("attachment_id <>", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdGreaterThan(Integer value) {
            addCriterion("attachment_id >", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("attachment_id >=", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdLessThan(Integer value) {
            addCriterion("attachment_id <", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdLessThanOrEqualTo(Integer value) {
            addCriterion("attachment_id <=", value, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdIn(List<Integer> values) {
            addCriterion("attachment_id in", values, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdNotIn(List<Integer> values) {
            addCriterion("attachment_id not in", values, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdBetween(Integer value1, Integer value2) {
            addCriterion("attachment_id between", value1, value2, "attachmentId");
            return (Criteria) this;
        }

        public Criteria andAttachmentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("attachment_id not between", value1, value2, "attachmentId");
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

        public Criteria andMdifiedIsNull() {
            addCriterion("mdified is null");
            return (Criteria) this;
        }

        public Criteria andMdifiedIsNotNull() {
            addCriterion("mdified is not null");
            return (Criteria) this;
        }

        public Criteria andMdifiedEqualTo(Date value) {
            addCriterion("mdified =", value, "mdified");
            return (Criteria) this;
        }

        public Criteria andMdifiedNotEqualTo(Date value) {
            addCriterion("mdified <>", value, "mdified");
            return (Criteria) this;
        }

        public Criteria andMdifiedGreaterThan(Date value) {
            addCriterion("mdified >", value, "mdified");
            return (Criteria) this;
        }

        public Criteria andMdifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("mdified >=", value, "mdified");
            return (Criteria) this;
        }

        public Criteria andMdifiedLessThan(Date value) {
            addCriterion("mdified <", value, "mdified");
            return (Criteria) this;
        }

        public Criteria andMdifiedLessThanOrEqualTo(Date value) {
            addCriterion("mdified <=", value, "mdified");
            return (Criteria) this;
        }

        public Criteria andMdifiedIn(List<Date> values) {
            addCriterion("mdified in", values, "mdified");
            return (Criteria) this;
        }

        public Criteria andMdifiedNotIn(List<Date> values) {
            addCriterion("mdified not in", values, "mdified");
            return (Criteria) this;
        }

        public Criteria andMdifiedBetween(Date value1, Date value2) {
            addCriterion("mdified between", value1, value2, "mdified");
            return (Criteria) this;
        }

        public Criteria andMdifiedNotBetween(Date value1, Date value2) {
            addCriterion("mdified not between", value1, value2, "mdified");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNull() {
            addCriterion("owner is null");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNotNull() {
            addCriterion("owner is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerEqualTo(String value) {
            addCriterion("owner =", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotEqualTo(String value) {
            addCriterion("owner <>", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThan(String value) {
            addCriterion("owner >", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("owner >=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThan(String value) {
            addCriterion("owner <", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThanOrEqualTo(String value) {
            addCriterion("owner <=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLike(String value) {
            addCriterion("owner like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotLike(String value) {
            addCriterion("owner not like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerIn(List<String> values) {
            addCriterion("owner in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotIn(List<String> values) {
            addCriterion("owner not in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerBetween(String value1, String value2) {
            addCriterion("owner between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotBetween(String value1, String value2) {
            addCriterion("owner not between", value1, value2, "owner");
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