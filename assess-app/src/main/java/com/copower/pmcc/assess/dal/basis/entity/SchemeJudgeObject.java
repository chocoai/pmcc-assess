package com.copower.pmcc.assess.dal.basis.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class SchemeJudgeObject implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer pid;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 区域分组id
     */
    private Integer areaGroupId;

    /**
     * 原始区域id
     */
    private Integer originalAreaGroupId;

    /**
     * 申报记录id
     */
    private Integer declareRecordId;

    /**
     * 建筑状态（已完、在建）
     */
    private Integer buildingStatus;

    /**
     * 
     */
    private Integer basicApplyId;

    /**
     * 委估对象编号
     */
    private String number;

    /**
     * 原委估对象编号
     */
    private String originalNumber;

    /**
     * 拆分编号
     */
    private Integer splitNumber;

    /**
     * 权证名称
     */
    private String name;

    /**
     * 权证名称
     */
    private String certName;

    /**
     * 所有权人
     */
    private String ownership;

    /**
     * 座落
     */
    private String seat;

    /**
     * 证载用途
     */
    private String certUse;

    /**
     * 实际用途
     */
    private String practicalUse;

    /**
     * 土地证载用途
     */
    private String landCertUse;

    /**
     * 土地实际用途
     */
    private String landPracticalUse;

    /**
     * 土地终止时间
     */
    private Date landUseEndDate;

    /**
     * 土地法定年限
     */
    private BigDecimal landLegalYear;

    /**
     * 土地剩余年限
     */
    private BigDecimal landRemainingYear;

    /**
     * 设定用途大类
     */
    private Integer setUseClassify;

    /**
     * 设定用途
     */
    private Integer setUse;

    /**
     * 最佳利用方式
     */
    private Integer bestUse;

    /**
     * 证载面积
     */
    private BigDecimal floorArea;

    /**
     * 评估面积
     */
    private BigDecimal evaluationArea;

    /**
     * 评估数量
     */
    private BigDecimal evaluationNumber;

    /**
     * 评估数量单位
     */
    private String evaluationNumberUnit;

    /**
     * 委估对象单价
     */
    private BigDecimal price;

    /**
     * 调价因素
     */
    private String factor;

    /**
     * 原单价
     */
    private BigDecimal originalPrice;

    /**
     * 设定容积率
     */
    private BigDecimal setPlotRatio;

    /**
     * 
     */
    private String planPlotRatio;

    /**
     * 实际容积率
     */
    private BigDecimal actualPlotRatio;

    /**
     * 标准估价对象id
     */
    private Integer standardJudgeId;

    /**
     * 设置为标准估价对象的说明
     */
    private String standardJudgeExplain;

    /**
     * 选用的评估方法
     */
    private String judgeFunction;

    /**
     * 评估方法不适用原因
     */
    private String notApplicableReason;

    /**
     * 对象合并说明
     */
    private String mergeExplain;

    /**
     * 拆分说明
     */
    private String splitExplain;

    /**
     * 拆分的源数据id
     */
    private Integer splitFrom;

    /**
     * 宗地外实际开发程度
     */
    private String parcelOuterDevelop;

    /**
     * 宗地内实际开发程度
     */
    private String parcelInnerDevelop;

    /**
     * 宗地内设定开发程度
     */
    private String parcelSettingInnerDevelop;

    /**
     * 宗地现状
     */
    private String currentSituation;

    /**
     * 是否为拆分
     */
    private Boolean bisSplit;

    /**
     * 是否为合并的委估对象
     */
    private Boolean bisMerge;

    /**
     * 是否可用
     */
    private Boolean bisEnable;

    /**
     * 是否已设置评估方法
     */
    private Boolean bisSetFunction;

    /**
     * 排序
     */
    private Integer sorting;

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
     * tb_scheme_judge_object
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
     * 
     * @return pid 
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 
     * @param pid 
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 项目id
     * @return project_id 项目id
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * 项目id
     * @param projectId 项目id
     */
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    /**
     * 区域分组id
     * @return area_group_id 区域分组id
     */
    public Integer getAreaGroupId() {
        return areaGroupId;
    }

    /**
     * 区域分组id
     * @param areaGroupId 区域分组id
     */
    public void setAreaGroupId(Integer areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    /**
     * 原始区域id
     * @return original_area_group_id 原始区域id
     */
    public Integer getOriginalAreaGroupId() {
        return originalAreaGroupId;
    }

    /**
     * 原始区域id
     * @param originalAreaGroupId 原始区域id
     */
    public void setOriginalAreaGroupId(Integer originalAreaGroupId) {
        this.originalAreaGroupId = originalAreaGroupId;
    }

    /**
     * 申报记录id
     * @return declare_record_id 申报记录id
     */
    public Integer getDeclareRecordId() {
        return declareRecordId;
    }

    /**
     * 申报记录id
     * @param declareRecordId 申报记录id
     */
    public void setDeclareRecordId(Integer declareRecordId) {
        this.declareRecordId = declareRecordId;
    }

    /**
     * 建筑状态（已完、在建）
     * @return building_status 建筑状态（已完、在建）
     */
    public Integer getBuildingStatus() {
        return buildingStatus;
    }

    /**
     * 建筑状态（已完、在建）
     * @param buildingStatus 建筑状态（已完、在建）
     */
    public void setBuildingStatus(Integer buildingStatus) {
        this.buildingStatus = buildingStatus;
    }

    /**
     * 
     * @return basic_apply_id 
     */
    public Integer getBasicApplyId() {
        return basicApplyId;
    }

    /**
     * 
     * @param basicApplyId 
     */
    public void setBasicApplyId(Integer basicApplyId) {
        this.basicApplyId = basicApplyId;
    }

    /**
     * 委估对象编号
     * @return number 委估对象编号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 委估对象编号
     * @param number 委估对象编号
     */
    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    /**
     * 原委估对象编号
     * @return original_number 原委估对象编号
     */
    public String getOriginalNumber() {
        return originalNumber;
    }

    /**
     * 原委估对象编号
     * @param originalNumber 原委估对象编号
     */
    public void setOriginalNumber(String originalNumber) {
        this.originalNumber = originalNumber == null ? null : originalNumber.trim();
    }

    /**
     * 拆分编号
     * @return split_number 拆分编号
     */
    public Integer getSplitNumber() {
        return splitNumber;
    }

    /**
     * 拆分编号
     * @param splitNumber 拆分编号
     */
    public void setSplitNumber(Integer splitNumber) {
        this.splitNumber = splitNumber;
    }

    /**
     * 权证名称
     * @return name 权证名称
     */
    public String getName() {
        return name;
    }

    /**
     * 权证名称
     * @param name 权证名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 权证名称
     * @return cert_name 权证名称
     */
    public String getCertName() {
        return certName;
    }

    /**
     * 权证名称
     * @param certName 权证名称
     */
    public void setCertName(String certName) {
        this.certName = certName == null ? null : certName.trim();
    }

    /**
     * 所有权人
     * @return ownership 所有权人
     */
    public String getOwnership() {
        return ownership;
    }

    /**
     * 所有权人
     * @param ownership 所有权人
     */
    public void setOwnership(String ownership) {
        this.ownership = ownership == null ? null : ownership.trim();
    }

    /**
     * 座落
     * @return seat 座落
     */
    public String getSeat() {
        return seat;
    }

    /**
     * 座落
     * @param seat 座落
     */
    public void setSeat(String seat) {
        this.seat = seat == null ? null : seat.trim();
    }

    /**
     * 证载用途
     * @return cert_use 证载用途
     */
    public String getCertUse() {
        return certUse;
    }

    /**
     * 证载用途
     * @param certUse 证载用途
     */
    public void setCertUse(String certUse) {
        this.certUse = certUse == null ? null : certUse.trim();
    }

    /**
     * 实际用途
     * @return practical_use 实际用途
     */
    public String getPracticalUse() {
        return practicalUse;
    }

    /**
     * 实际用途
     * @param practicalUse 实际用途
     */
    public void setPracticalUse(String practicalUse) {
        this.practicalUse = practicalUse == null ? null : practicalUse.trim();
    }

    /**
     * 土地证载用途
     * @return land_cert_use 土地证载用途
     */
    public String getLandCertUse() {
        return landCertUse;
    }

    /**
     * 土地证载用途
     * @param landCertUse 土地证载用途
     */
    public void setLandCertUse(String landCertUse) {
        this.landCertUse = landCertUse == null ? null : landCertUse.trim();
    }

    /**
     * 土地实际用途
     * @return land_practical_use 土地实际用途
     */
    public String getLandPracticalUse() {
        return landPracticalUse;
    }

    /**
     * 土地实际用途
     * @param landPracticalUse 土地实际用途
     */
    public void setLandPracticalUse(String landPracticalUse) {
        this.landPracticalUse = landPracticalUse == null ? null : landPracticalUse.trim();
    }

    /**
     * 土地终止时间
     * @return land_use_end_date 土地终止时间
     */
    public Date getLandUseEndDate() {
        return landUseEndDate;
    }

    /**
     * 土地终止时间
     * @param landUseEndDate 土地终止时间
     */
    public void setLandUseEndDate(Date landUseEndDate) {
        this.landUseEndDate = landUseEndDate;
    }

    /**
     * 土地法定年限
     * @return land_legal_year 土地法定年限
     */
    public BigDecimal getLandLegalYear() {
        return landLegalYear;
    }

    /**
     * 土地法定年限
     * @param landLegalYear 土地法定年限
     */
    public void setLandLegalYear(BigDecimal landLegalYear) {
        this.landLegalYear = landLegalYear;
    }

    /**
     * 土地剩余年限
     * @return land_remaining_year 土地剩余年限
     */
    public BigDecimal getLandRemainingYear() {
        return landRemainingYear;
    }

    /**
     * 土地剩余年限
     * @param landRemainingYear 土地剩余年限
     */
    public void setLandRemainingYear(BigDecimal landRemainingYear) {
        this.landRemainingYear = landRemainingYear;
    }

    /**
     * 设定用途大类
     * @return set_use_classify 设定用途大类
     */
    public Integer getSetUseClassify() {
        return setUseClassify;
    }

    /**
     * 设定用途大类
     * @param setUseClassify 设定用途大类
     */
    public void setSetUseClassify(Integer setUseClassify) {
        this.setUseClassify = setUseClassify;
    }

    /**
     * 设定用途
     * @return set_use 设定用途
     */
    public Integer getSetUse() {
        return setUse;
    }

    /**
     * 设定用途
     * @param setUse 设定用途
     */
    public void setSetUse(Integer setUse) {
        this.setUse = setUse;
    }

    /**
     * 最佳利用方式
     * @return best_use 最佳利用方式
     */
    public Integer getBestUse() {
        return bestUse;
    }

    /**
     * 最佳利用方式
     * @param bestUse 最佳利用方式
     */
    public void setBestUse(Integer bestUse) {
        this.bestUse = bestUse;
    }

    /**
     * 证载面积
     * @return floor_area 证载面积
     */
    public BigDecimal getFloorArea() {
        return floorArea;
    }

    /**
     * 证载面积
     * @param floorArea 证载面积
     */
    public void setFloorArea(BigDecimal floorArea) {
        this.floorArea = floorArea;
    }

    /**
     * 评估面积
     * @return evaluation_area 评估面积
     */
    public BigDecimal getEvaluationArea() {
        return evaluationArea;
    }

    /**
     * 评估面积
     * @param evaluationArea 评估面积
     */
    public void setEvaluationArea(BigDecimal evaluationArea) {
        this.evaluationArea = evaluationArea;
    }

    /**
     * 评估数量
     * @return evaluation_number 评估数量
     */
    public BigDecimal getEvaluationNumber() {
        return evaluationNumber;
    }

    /**
     * 评估数量
     * @param evaluationNumber 评估数量
     */
    public void setEvaluationNumber(BigDecimal evaluationNumber) {
        this.evaluationNumber = evaluationNumber;
    }

    /**
     * 评估数量单位
     * @return evaluation_number_unit 评估数量单位
     */
    public String getEvaluationNumberUnit() {
        return evaluationNumberUnit;
    }

    /**
     * 评估数量单位
     * @param evaluationNumberUnit 评估数量单位
     */
    public void setEvaluationNumberUnit(String evaluationNumberUnit) {
        this.evaluationNumberUnit = evaluationNumberUnit == null ? null : evaluationNumberUnit.trim();
    }

    /**
     * 委估对象单价
     * @return price 委估对象单价
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 委估对象单价
     * @param price 委估对象单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 调价因素
     * @return factor 调价因素
     */
    public String getFactor() {
        return factor;
    }

    /**
     * 调价因素
     * @param factor 调价因素
     */
    public void setFactor(String factor) {
        this.factor = factor == null ? null : factor.trim();
    }

    /**
     * 原单价
     * @return original_price 原单价
     */
    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    /**
     * 原单价
     * @param originalPrice 原单价
     */
    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     * 设定容积率
     * @return set_plot_ratio 设定容积率
     */
    public BigDecimal getSetPlotRatio() {
        return setPlotRatio;
    }

    /**
     * 设定容积率
     * @param setPlotRatio 设定容积率
     */
    public void setSetPlotRatio(BigDecimal setPlotRatio) {
        this.setPlotRatio = setPlotRatio;
    }

    /**
     * 
     * @return plan_plot_ratio 
     */
    public String getPlanPlotRatio() {
        return planPlotRatio;
    }

    /**
     * 
     * @param planPlotRatio 
     */
    public void setPlanPlotRatio(String planPlotRatio) {
        this.planPlotRatio = planPlotRatio == null ? null : planPlotRatio.trim();
    }

    /**
     * 实际容积率
     * @return actual_plot_ratio 实际容积率
     */
    public BigDecimal getActualPlotRatio() {
        return actualPlotRatio;
    }

    /**
     * 实际容积率
     * @param actualPlotRatio 实际容积率
     */
    public void setActualPlotRatio(BigDecimal actualPlotRatio) {
        this.actualPlotRatio = actualPlotRatio;
    }

    /**
     * 标准估价对象id
     * @return standard_judge_id 标准估价对象id
     */
    public Integer getStandardJudgeId() {
        return standardJudgeId;
    }

    /**
     * 标准估价对象id
     * @param standardJudgeId 标准估价对象id
     */
    public void setStandardJudgeId(Integer standardJudgeId) {
        this.standardJudgeId = standardJudgeId;
    }

    /**
     * 设置为标准估价对象的说明
     * @return standard_judge_explain 设置为标准估价对象的说明
     */
    public String getStandardJudgeExplain() {
        return standardJudgeExplain;
    }

    /**
     * 设置为标准估价对象的说明
     * @param standardJudgeExplain 设置为标准估价对象的说明
     */
    public void setStandardJudgeExplain(String standardJudgeExplain) {
        this.standardJudgeExplain = standardJudgeExplain == null ? null : standardJudgeExplain.trim();
    }

    /**
     * 选用的评估方法
     * @return judge_function 选用的评估方法
     */
    public String getJudgeFunction() {
        return judgeFunction;
    }

    /**
     * 选用的评估方法
     * @param judgeFunction 选用的评估方法
     */
    public void setJudgeFunction(String judgeFunction) {
        this.judgeFunction = judgeFunction == null ? null : judgeFunction.trim();
    }

    /**
     * 评估方法不适用原因
     * @return not_applicable_reason 评估方法不适用原因
     */
    public String getNotApplicableReason() {
        return notApplicableReason;
    }

    /**
     * 评估方法不适用原因
     * @param notApplicableReason 评估方法不适用原因
     */
    public void setNotApplicableReason(String notApplicableReason) {
        this.notApplicableReason = notApplicableReason == null ? null : notApplicableReason.trim();
    }

    /**
     * 对象合并说明
     * @return merge_explain 对象合并说明
     */
    public String getMergeExplain() {
        return mergeExplain;
    }

    /**
     * 对象合并说明
     * @param mergeExplain 对象合并说明
     */
    public void setMergeExplain(String mergeExplain) {
        this.mergeExplain = mergeExplain == null ? null : mergeExplain.trim();
    }

    /**
     * 拆分说明
     * @return split_explain 拆分说明
     */
    public String getSplitExplain() {
        return splitExplain;
    }

    /**
     * 拆分说明
     * @param splitExplain 拆分说明
     */
    public void setSplitExplain(String splitExplain) {
        this.splitExplain = splitExplain == null ? null : splitExplain.trim();
    }

    /**
     * 拆分的源数据id
     * @return split_from 拆分的源数据id
     */
    public Integer getSplitFrom() {
        return splitFrom;
    }

    /**
     * 拆分的源数据id
     * @param splitFrom 拆分的源数据id
     */
    public void setSplitFrom(Integer splitFrom) {
        this.splitFrom = splitFrom;
    }

    /**
     * 宗地外实际开发程度
     * @return parcel_outer_develop 宗地外实际开发程度
     */
    public String getParcelOuterDevelop() {
        return parcelOuterDevelop;
    }

    /**
     * 宗地外实际开发程度
     * @param parcelOuterDevelop 宗地外实际开发程度
     */
    public void setParcelOuterDevelop(String parcelOuterDevelop) {
        this.parcelOuterDevelop = parcelOuterDevelop == null ? null : parcelOuterDevelop.trim();
    }

    /**
     * 宗地内实际开发程度
     * @return parcel_inner_develop 宗地内实际开发程度
     */
    public String getParcelInnerDevelop() {
        return parcelInnerDevelop;
    }

    /**
     * 宗地内实际开发程度
     * @param parcelInnerDevelop 宗地内实际开发程度
     */
    public void setParcelInnerDevelop(String parcelInnerDevelop) {
        this.parcelInnerDevelop = parcelInnerDevelop == null ? null : parcelInnerDevelop.trim();
    }

    /**
     * 宗地内设定开发程度
     * @return parcel_setting_inner_develop 宗地内设定开发程度
     */
    public String getParcelSettingInnerDevelop() {
        return parcelSettingInnerDevelop;
    }

    /**
     * 宗地内设定开发程度
     * @param parcelSettingInnerDevelop 宗地内设定开发程度
     */
    public void setParcelSettingInnerDevelop(String parcelSettingInnerDevelop) {
        this.parcelSettingInnerDevelop = parcelSettingInnerDevelop == null ? null : parcelSettingInnerDevelop.trim();
    }

    /**
     * 宗地现状
     * @return current_situation 宗地现状
     */
    public String getCurrentSituation() {
        return currentSituation;
    }

    /**
     * 宗地现状
     * @param currentSituation 宗地现状
     */
    public void setCurrentSituation(String currentSituation) {
        this.currentSituation = currentSituation == null ? null : currentSituation.trim();
    }

    /**
     * 是否为拆分
     * @return bis_split 是否为拆分
     */
    public Boolean getBisSplit() {
        return bisSplit;
    }

    /**
     * 是否为拆分
     * @param bisSplit 是否为拆分
     */
    public void setBisSplit(Boolean bisSplit) {
        this.bisSplit = bisSplit;
    }

    /**
     * 是否为合并的委估对象
     * @return bis_merge 是否为合并的委估对象
     */
    public Boolean getBisMerge() {
        return bisMerge;
    }

    /**
     * 是否为合并的委估对象
     * @param bisMerge 是否为合并的委估对象
     */
    public void setBisMerge(Boolean bisMerge) {
        this.bisMerge = bisMerge;
    }

    /**
     * 是否可用
     * @return bis_enable 是否可用
     */
    public Boolean getBisEnable() {
        return bisEnable;
    }

    /**
     * 是否可用
     * @param bisEnable 是否可用
     */
    public void setBisEnable(Boolean bisEnable) {
        this.bisEnable = bisEnable;
    }

    /**
     * 是否已设置评估方法
     * @return bis_set_function 是否已设置评估方法
     */
    public Boolean getBisSetFunction() {
        return bisSetFunction;
    }

    /**
     * 是否已设置评估方法
     * @param bisSetFunction 是否已设置评估方法
     */
    public void setBisSetFunction(Boolean bisSetFunction) {
        this.bisSetFunction = bisSetFunction;
    }

    /**
     * 排序
     * @return sorting 排序
     */
    public Integer getSorting() {
        return sorting;
    }

    /**
     * 排序
     * @param sorting 排序
     */
    public void setSorting(Integer sorting) {
        this.sorting = sorting;
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

    public static SchemeJudgeObject.Builder builder() {
        return new SchemeJudgeObject.Builder();
    }

    /**
     * tb_scheme_judge_object
     */
    public static class Builder {
        /**
         * tb_scheme_judge_object
         */
        private SchemeJudgeObject obj;

        public Builder() {
            this.obj = new SchemeJudgeObject();
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
         * 
         * @param pid 
         */
        public Builder pid(Integer pid) {
            obj.setPid(pid);
            return this;
        }

        /**
         * 项目id
         * @param projectId 项目id
         */
        public Builder projectId(Integer projectId) {
            obj.setProjectId(projectId);
            return this;
        }

        /**
         * 区域分组id
         * @param areaGroupId 区域分组id
         */
        public Builder areaGroupId(Integer areaGroupId) {
            obj.setAreaGroupId(areaGroupId);
            return this;
        }

        /**
         * 原始区域id
         * @param originalAreaGroupId 原始区域id
         */
        public Builder originalAreaGroupId(Integer originalAreaGroupId) {
            obj.setOriginalAreaGroupId(originalAreaGroupId);
            return this;
        }

        /**
         * 申报记录id
         * @param declareRecordId 申报记录id
         */
        public Builder declareRecordId(Integer declareRecordId) {
            obj.setDeclareRecordId(declareRecordId);
            return this;
        }

        public SchemeJudgeObject build() {
            return this.obj;
        }

        /**
         * 建筑状态（已完、在建）
         * @param buildingStatus 建筑状态（已完、在建）
         */
        public Builder buildingStatus(Integer buildingStatus) {
            obj.setBuildingStatus(buildingStatus);
            return this;
        }

        /**
         * 
         * @param basicApplyId 
         */
        public Builder basicApplyId(Integer basicApplyId) {
            obj.setBasicApplyId(basicApplyId);
            return this;
        }

        /**
         * 委估对象编号
         * @param number 委估对象编号
         */
        public Builder number(String number) {
            obj.setNumber(number);
            return this;
        }

        /**
         * 原委估对象编号
         * @param originalNumber 原委估对象编号
         */
        public Builder originalNumber(String originalNumber) {
            obj.setOriginalNumber(originalNumber);
            return this;
        }

        /**
         * 拆分编号
         * @param splitNumber 拆分编号
         */
        public Builder splitNumber(Integer splitNumber) {
            obj.setSplitNumber(splitNumber);
            return this;
        }

        /**
         * 权证名称
         * @param name 权证名称
         */
        public Builder name(String name) {
            obj.setName(name);
            return this;
        }

        /**
         * 权证名称
         * @param certName 权证名称
         */
        public Builder certName(String certName) {
            obj.setCertName(certName);
            return this;
        }

        /**
         * 所有权人
         * @param ownership 所有权人
         */
        public Builder ownership(String ownership) {
            obj.setOwnership(ownership);
            return this;
        }

        /**
         * 座落
         * @param seat 座落
         */
        public Builder seat(String seat) {
            obj.setSeat(seat);
            return this;
        }

        /**
         * 证载用途
         * @param certUse 证载用途
         */
        public Builder certUse(String certUse) {
            obj.setCertUse(certUse);
            return this;
        }

        /**
         * 实际用途
         * @param practicalUse 实际用途
         */
        public Builder practicalUse(String practicalUse) {
            obj.setPracticalUse(practicalUse);
            return this;
        }

        /**
         * 土地证载用途
         * @param landCertUse 土地证载用途
         */
        public Builder landCertUse(String landCertUse) {
            obj.setLandCertUse(landCertUse);
            return this;
        }

        /**
         * 土地实际用途
         * @param landPracticalUse 土地实际用途
         */
        public Builder landPracticalUse(String landPracticalUse) {
            obj.setLandPracticalUse(landPracticalUse);
            return this;
        }

        /**
         * 土地终止时间
         * @param landUseEndDate 土地终止时间
         */
        public Builder landUseEndDate(Date landUseEndDate) {
            obj.setLandUseEndDate(landUseEndDate);
            return this;
        }

        /**
         * 土地法定年限
         * @param landLegalYear 土地法定年限
         */
        public Builder landLegalYear(BigDecimal landLegalYear) {
            obj.setLandLegalYear(landLegalYear);
            return this;
        }

        /**
         * 土地剩余年限
         * @param landRemainingYear 土地剩余年限
         */
        public Builder landRemainingYear(BigDecimal landRemainingYear) {
            obj.setLandRemainingYear(landRemainingYear);
            return this;
        }

        /**
         * 设定用途
         * @param setUse 设定用途
         */
        public Builder setUse(Integer setUse) {
            obj.setSetUse(setUse);
            return this;
        }

        /**
         * 设定用途大类
         * @param setUseClassify 设定用途大类
         */
        public Builder setUseClassify(Integer setUseClassify) {
            obj.setSetUseClassify(setUseClassify);
            return this;
        }

        /**
         * 最佳利用方式
         * @param bestUse 最佳利用方式
         */
        public Builder bestUse(Integer bestUse) {
            obj.setBestUse(bestUse);
            return this;
        }

        /**
         * 证载面积
         * @param floorArea 证载面积
         */
        public Builder floorArea(BigDecimal floorArea) {
            obj.setFloorArea(floorArea);
            return this;
        }

        /**
         * 评估面积
         * @param evaluationArea 评估面积
         */
        public Builder evaluationArea(BigDecimal evaluationArea) {
            obj.setEvaluationArea(evaluationArea);
            return this;
        }

        /**
         * 评估数量
         * @param evaluationNumber 评估数量
         */
        public Builder evaluationNumber(BigDecimal evaluationNumber) {
            obj.setEvaluationNumber(evaluationNumber);
            return this;
        }

        /**
         * 评估数量单位
         * @param evaluationNumberUnit 评估数量单位
         */
        public Builder evaluationNumberUnit(String evaluationNumberUnit) {
            obj.setEvaluationNumberUnit(evaluationNumberUnit);
            return this;
        }

        /**
         * 委估对象单价
         * @param price 委估对象单价
         */
        public Builder price(BigDecimal price) {
            obj.setPrice(price);
            return this;
        }

        /**
         * 调价因素
         * @param factor 调价因素
         */
        public Builder factor(String factor) {
            obj.setFactor(factor);
            return this;
        }

        /**
         * 原单价
         * @param originalPrice 原单价
         */
        public Builder originalPrice(BigDecimal originalPrice) {
            obj.setOriginalPrice(originalPrice);
            return this;
        }

        /**
         * 设定容积率
         * @param setPlotRatio 设定容积率
         */
        public Builder setPlotRatio(BigDecimal setPlotRatio) {
            obj.setSetPlotRatio(setPlotRatio);
            return this;
        }

        /**
         * 
         * @param planPlotRatio 
         */
        public Builder planPlotRatio(String planPlotRatio) {
            obj.setPlanPlotRatio(planPlotRatio);
            return this;
        }

        /**
         * 实际容积率
         * @param actualPlotRatio 实际容积率
         */
        public Builder actualPlotRatio(BigDecimal actualPlotRatio) {
            obj.setActualPlotRatio(actualPlotRatio);
            return this;
        }

        /**
         * 标准估价对象id
         * @param standardJudgeId 标准估价对象id
         */
        public Builder standardJudgeId(Integer standardJudgeId) {
            obj.setStandardJudgeId(standardJudgeId);
            return this;
        }

        /**
         * 设置为标准估价对象的说明
         * @param standardJudgeExplain 设置为标准估价对象的说明
         */
        public Builder standardJudgeExplain(String standardJudgeExplain) {
            obj.setStandardJudgeExplain(standardJudgeExplain);
            return this;
        }

        /**
         * 选用的评估方法
         * @param judgeFunction 选用的评估方法
         */
        public Builder judgeFunction(String judgeFunction) {
            obj.setJudgeFunction(judgeFunction);
            return this;
        }

        /**
         * 评估方法不适用原因
         * @param notApplicableReason 评估方法不适用原因
         */
        public Builder notApplicableReason(String notApplicableReason) {
            obj.setNotApplicableReason(notApplicableReason);
            return this;
        }

        /**
         * 对象合并说明
         * @param mergeExplain 对象合并说明
         */
        public Builder mergeExplain(String mergeExplain) {
            obj.setMergeExplain(mergeExplain);
            return this;
        }

        /**
         * 拆分说明
         * @param splitExplain 拆分说明
         */
        public Builder splitExplain(String splitExplain) {
            obj.setSplitExplain(splitExplain);
            return this;
        }

        /**
         * 拆分的源数据id
         * @param splitFrom 拆分的源数据id
         */
        public Builder splitFrom(Integer splitFrom) {
            obj.setSplitFrom(splitFrom);
            return this;
        }

        /**
         * 宗地外实际开发程度
         * @param parcelOuterDevelop 宗地外实际开发程度
         */
        public Builder parcelOuterDevelop(String parcelOuterDevelop) {
            obj.setParcelOuterDevelop(parcelOuterDevelop);
            return this;
        }

        /**
         * 宗地内实际开发程度
         * @param parcelInnerDevelop 宗地内实际开发程度
         */
        public Builder parcelInnerDevelop(String parcelInnerDevelop) {
            obj.setParcelInnerDevelop(parcelInnerDevelop);
            return this;
        }

        /**
         * 宗地内设定开发程度
         * @param parcelSettingInnerDevelop 宗地内设定开发程度
         */
        public Builder parcelSettingInnerDevelop(String parcelSettingInnerDevelop) {
            obj.setParcelSettingInnerDevelop(parcelSettingInnerDevelop);
            return this;
        }

        /**
         * 宗地现状
         * @param currentSituation 宗地现状
         */
        public Builder currentSituation(String currentSituation) {
            obj.setCurrentSituation(currentSituation);
            return this;
        }

        /**
         * 是否为拆分
         * @param bisSplit 是否为拆分
         */
        public Builder bisSplit(Boolean bisSplit) {
            obj.setBisSplit(bisSplit);
            return this;
        }

        /**
         * 是否为合并的委估对象
         * @param bisMerge 是否为合并的委估对象
         */
        public Builder bisMerge(Boolean bisMerge) {
            obj.setBisMerge(bisMerge);
            return this;
        }

        /**
         * 是否可用
         * @param bisEnable 是否可用
         */
        public Builder bisEnable(Boolean bisEnable) {
            obj.setBisEnable(bisEnable);
            return this;
        }

        /**
         * 是否已设置评估方法
         * @param bisSetFunction 是否已设置评估方法
         */
        public Builder bisSetFunction(Boolean bisSetFunction) {
            obj.setBisSetFunction(bisSetFunction);
            return this;
        }

        /**
         * 排序
         * @param sorting 排序
         */
        public Builder sorting(Integer sorting) {
            obj.setSorting(sorting);
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
    }

    public enum Column {
        id("id", "id", "INTEGER", false),
        pid("pid", "pid", "INTEGER", false),
        projectId("project_id", "projectId", "INTEGER", false),
        areaGroupId("area_group_id", "areaGroupId", "INTEGER", false),
        originalAreaGroupId("original_area_group_id", "originalAreaGroupId", "INTEGER", false),
        declareRecordId("declare_record_id", "declareRecordId", "INTEGER", false),
        buildingStatus("building_status", "buildingStatus", "INTEGER", false),
        basicApplyId("basic_apply_id", "basicApplyId", "INTEGER", false),
        number("number", "number", "VARCHAR", false),
        originalNumber("original_number", "originalNumber", "VARCHAR", false),
        splitNumber("split_number", "splitNumber", "INTEGER", false),
        name("name", "name", "VARCHAR", false),
        certName("cert_name", "certName", "VARCHAR", false),
        ownership("ownership", "ownership", "VARCHAR", false),
        seat("seat", "seat", "VARCHAR", false),
        certUse("cert_use", "certUse", "VARCHAR", false),
        practicalUse("practical_use", "practicalUse", "VARCHAR", false),
        landCertUse("land_cert_use", "landCertUse", "VARCHAR", false),
        landPracticalUse("land_practical_use", "landPracticalUse", "VARCHAR", false),
        landUseEndDate("land_use_end_date", "landUseEndDate", "TIMESTAMP", false),
        landLegalYear("land_legal_year", "landLegalYear", "DECIMAL", false),
        landRemainingYear("land_remaining_year", "landRemainingYear", "DECIMAL", false),
        setUseClassify("set_use_classify", "setUseClassify", "INTEGER", false),
        setUse("set_use", "setUse", "INTEGER", false),
        bestUse("best_use", "bestUse", "INTEGER", false),
        floorArea("floor_area", "floorArea", "DECIMAL", false),
        evaluationArea("evaluation_area", "evaluationArea", "DECIMAL", false),
        evaluationNumber("evaluation_number", "evaluationNumber", "DECIMAL", false),
        evaluationNumberUnit("evaluation_number_unit", "evaluationNumberUnit", "VARCHAR", false),
        price("price", "price", "DECIMAL", false),
        factor("factor", "factor", "VARCHAR", false),
        originalPrice("original_price", "originalPrice", "DECIMAL", false),
        setPlotRatio("set_plot_ratio", "setPlotRatio", "DECIMAL", false),
        planPlotRatio("plan_plot_ratio", "planPlotRatio", "VARCHAR", false),
        actualPlotRatio("actual_plot_ratio", "actualPlotRatio", "DECIMAL", false),
        standardJudgeId("standard_judge_id", "standardJudgeId", "INTEGER", false),
        standardJudgeExplain("standard_judge_explain", "standardJudgeExplain", "VARCHAR", false),
        judgeFunction("judge_function", "judgeFunction", "VARCHAR", false),
        notApplicableReason("not_applicable_reason", "notApplicableReason", "VARCHAR", false),
        mergeExplain("merge_explain", "mergeExplain", "VARCHAR", false),
        splitExplain("split_explain", "splitExplain", "VARCHAR", false),
        splitFrom("split_from", "splitFrom", "INTEGER", false),
        parcelOuterDevelop("parcel_outer_develop", "parcelOuterDevelop", "VARCHAR", false),
        parcelInnerDevelop("parcel_inner_develop", "parcelInnerDevelop", "VARCHAR", false),
        parcelSettingInnerDevelop("parcel_setting_inner_develop", "parcelSettingInnerDevelop", "VARCHAR", false),
        currentSituation("current_situation", "currentSituation", "VARCHAR", false),
        bisSplit("bis_split", "bisSplit", "BIT", false),
        bisMerge("bis_merge", "bisMerge", "BIT", false),
        bisEnable("bis_enable", "bisEnable", "BIT", false),
        bisSetFunction("bis_set_function", "bisSetFunction", "BIT", false),
        sorting("sorting", "sorting", "INTEGER", false),
        creator("creator", "creator", "VARCHAR", false),
        gmtCreated("gmt_created", "gmtCreated", "TIMESTAMP", false),
        gmtModified("gmt_modified", "gmtModified", "TIMESTAMP", false);

        /**
         * tb_scheme_judge_object
         */
        private static final String BEGINNING_DELIMITER = "\"";

        /**
         * tb_scheme_judge_object
         */
        private static final String ENDING_DELIMITER = "\"";

        /**
         * tb_scheme_judge_object
         */
        private final String column;

        /**
         * tb_scheme_judge_object
         */
        private final boolean isColumnNameDelimited;

        /**
         * tb_scheme_judge_object
         */
        private final String javaProperty;

        /**
         * tb_scheme_judge_object
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