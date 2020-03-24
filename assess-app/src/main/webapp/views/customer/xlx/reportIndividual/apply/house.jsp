<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-3-20
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-md-1  control-label">
                所在地方   
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"
                       placeholder="所在地方"    name="location"
                       value="${projectXlxReportIndividual.location}">
            </div>

            <label class="col-md-1  control-label">
                估价对象建筑面积(㎡)    
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"
                       data-rule-number="true"
                       placeholder="估价对象建筑面积(㎡)"    name="judgeObjectFloorArea"
                       value="${projectXlxReportIndividual.judgeObjectFloorArea}">
            </div>
            <label class="col-md-1  control-label">
                估价对象土地面积(㎡)   
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"   
                       placeholder="估价对象土地面积(㎡)" data-rule-number="true"
                       name="judgeObjectLandArea"
                       value="${projectXlxReportIndividual.judgeObjectLandArea}">
            </div>
        </div>
    </div>
</div>
<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">

            <label class="col-md-1  control-label">
                价值（万元）   
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"
                       placeholder="价值（万元）"    name="worth"
                       data-rule-number="true"
                       value="${projectXlxReportIndividual.worth}">
            </div>
            <label class="col-md-1  control-label">
                单价(元)   
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"
                       data-rule-number="true"
                       placeholder="单价(元)"    name="price"
                       value="${projectXlxReportIndividual.price}">
            </div>
            <label class="col-md-1  control-label">
                估价时点   
            </label>
            <div class="col-md-3">
                <input type="text"    name="valueTimePoint"
                       placeholder="估价时点"
                       class="form-control input-full date-picker dbdate"
                       data-date-format="yyyy-mm-dd"
                       pattern='yyyy-MM-dd'
                       value="<fmt:formatDate value='${projectXlxReportIndividual.valueTimePoint}' pattern='yyyy-MM-dd'/>">
            </div>

        </div>
    </div>
</div>
<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">


            <label class="col-md-1  control-label">
                估价作业期   
            </label>
            <div class="col-md-3">
                <input type="text"    name="appraisalPeriod"
                       placeholder="估价作业期"
                       class="form-control input-full date-picker dbdate"
                       data-date-format="yyyy-mm-dd"
                       pattern='yyyy-MM-dd'
                       value="<fmt:formatDate value='${projectXlxReportIndividual.appraisalPeriod}' pattern='yyyy-MM-dd'/>">
            </div>

            <label class="col-md-1  control-label">
                评估目的   
            </label>
            <div class="col-md-3">
                <input type="text" placeholder="评估目的" class="form-control input-full"   
                       name="assessPurpose" value="${projectXlxReportIndividual.assessPurpose}">
            </div>

            <label class="col-md-1  control-label">
                估价方法   
            </label>
            <div class="col-md-3">
                <input type="text" placeholder="估价方法" class="form-control input-full"   
                       name="valuationMethod" value="${projectXlxReportIndividual.valuationMethod}">
            </div>

        </div>
    </div>
</div>
<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">

            <label class="col-md-1  control-label">
                房屋用途   
            </label>
            <div class="col-md-3">
                <input type="text" placeholder="房屋用途" class="form-control input-full"   
                       name="housingUse" value="${projectXlxReportIndividual.housingUse}">
            </div>

            <label class="col-md-1  control-label">
                报告人   
            </label>
            <div class="col-md-3">
                <input type="text" placeholder="报告人" class="form-control input-full"   
                       name="reportPeople" value="${projectXlxReportIndividual.reportPeople}">
            </div>

            <label class="col-md-1  control-label">
                签章人员   
            </label>
            <div class="col-md-3">
                <input type="text" placeholder="签章人员" class="form-control input-full"   
                       name="signerPeople" value="${projectXlxReportIndividual.signerPeople}">
            </div>

        </div>
    </div>
</div>

<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-md-1  control-label">
                报告装订日期   
            </label>
            <div class="col-md-3">
                <input type="text"    name="reportBindingDate"
                       placeholder="报告装订日期"
                       class="form-control input-full date-picker dbdate"
                       data-date-format="yyyy-mm-dd"
                       pattern='yyyy-MM-dd'
                       value="<fmt:formatDate value='${projectXlxReportIndividual.reportBindingDate}' pattern='yyyy-MM-dd'/>">
            </div>

            <label class="col-md-1  control-label">
                报告份数   
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"    data-rule-number="true" placeholder="报告份数"
                       name="reportScore" value="${projectXlxReportIndividual.reportScore}">
            </div>

            <label class="col-md-1  control-label">
                报告修改日期   
            </label>
            <div class="col-md-3">
                <input type="text"    name="reportModificationDate"
                       placeholder="报告修改日期"
                       class="form-control input-full date-picker dbdate"
                       data-date-format="yyyy-mm-dd"
                       pattern='yyyy-MM-dd'
                       value="<fmt:formatDate value='${projectXlxReportIndividual.reportModificationDate}' pattern='yyyy-MM-dd'/>">
            </div>
        </div>
    </div>
</div>


<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">

            <label class="col-md-1  control-label">
                归档日期   
            </label>
            <div class="col-md-3">
                <input type="text"    name="filingDate"
                       placeholder="归档日期"
                       class="form-control input-full date-picker dbdate"
                       data-date-format="yyyy-mm-dd"
                       pattern='yyyy-MM-dd'
                       value="<fmt:formatDate value='${empty projectXlxReportIndividual.filingDate?pigeonholeDate:projectXlxReportIndividual.filingDate}' pattern='yyyy-MM-dd'/>">
            </div>
            <label class="col-md-1  control-label">
                合同签订日期   
            </label>
            <div class="col-md-3">
                <input type="text"    name="contractSigningDate"
                       placeholder="合同签订日期"
                       class="form-control input-full date-picker dbdate"
                       data-date-format="yyyy-mm-dd"
                       pattern='yyyy-MM-dd'
                       value="<fmt:formatDate value='${projectXlxReportIndividual.contractSigningDate}' pattern='yyyy-MM-dd'/>">
            </div>


            <label class="col-md-1  control-label">
                委托银行   
            </label>
            <div class="col-md-3">
                <input type="text" placeholder="委托银行" class="form-control input-full"   
                       name="entrustedBank" value="${projectXlxReportIndividual.entrustedBank}">
            </div>


        </div>
    </div>
</div>


<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-md-1  control-label">
                委托人电话   
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"
                       placeholder="委托人电话"    name="phonePrincipal"
                       value="${projectXlxReportIndividual.phonePrincipal}">
            </div>
            <label class="col-md-1  control-label">
                项目取得方式   
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"
                       placeholder="项目取得方式"    name="projectAcquisitionMethod"
                       value="${projectXlxReportIndividual.projectAcquisitionMethod}">
            </div>
            <label class="col-md-1  control-label">
                收费(元)   
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full" data-rule-number="true"
                       placeholder="收费(元)"    name="assessTotalToll"
                       value="${projectXlxReportIndividual.assessTotalToll}">
            </div>
        </div>
    </div>
</div>
