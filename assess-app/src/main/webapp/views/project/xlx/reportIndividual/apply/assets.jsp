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
                价值（万元）<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"
                       placeholder="价值（万元）" required name="worth"
                       data-rule-number="true"
                       value="${projectXlxReportIndividual.worth}">
            </div>

            <label class="col-md-1  control-label">
                单价(元)<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"
                       data-rule-number="true"
                       placeholder="单价(元)" required name="price"
                       value="${projectXlxReportIndividual.price}">
            </div>
            <label class="col-md-1  control-label">
                估价对象土地面积(㎡)<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full" required
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
                估价对象建筑面积(㎡)<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full" required
                       placeholder="估价对象建筑面积(㎡)" data-rule-number="true"
                       name="judgeObjectFloorArea"
                       value="${projectXlxReportIndividual.judgeObjectFloorArea}">
            </div>
            <label class="col-md-1  control-label">
                账面原值(万元)<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full" required
                       placeholder="账面原值(万元)" data-rule-number="true"
                       name="accountValueOriginal"
                       value="${projectXlxReportIndividual.accountValueOriginal}">
            </div>
            <label class="col-md-1  control-label">
                拟稿人<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" placeholder="拟稿人" class="form-control input-full" required
                       name="draftAuthor" value="${projectXlxReportIndividual.draftAuthor}">
            </div>
        </div>
    </div>
</div>
<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">

            <label class="col-md-1  control-label">
                签章人员<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" placeholder="签章人员" class="form-control input-full" required
                       name="signerPeople" value="${projectXlxReportIndividual.signerPeople}">
            </div>
            <label class="col-md-1  control-label">
                网上报备签章人<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" placeholder="网上报备签章人" class="form-control input-full" required
                       name="signedBy" value="${projectXlxReportIndividual.signedBy}">
            </div>
            <label class="col-md-1  control-label">
                评估目的<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" placeholder="评估目的" class="form-control input-full" required
                       name="assessPurpose" value="${projectXlxReportIndividual.assessPurpose}">
            </div>
        </div>
    </div>
</div>
<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">

            <label class="col-md-1  control-label">
                评估方法<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" placeholder="评估方法" class="form-control input-full" required
                       name="assessMethod" value="${projectXlxReportIndividual.assessMethod}">
            </div>

            <label class="col-md-1  control-label">
                评估基准日<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" required name="valueTimePoint"
                       placeholder="评估基准日"
                       class="form-control input-full date-picker dbdate"
                       data-date-format="yyyy-mm-dd"
                       pattern='yyyy-MM-dd'
                       value="<fmt:formatDate value='${projectXlxReportIndividual.valueTimePoint}' pattern='yyyy-MM-dd'/>">
            </div>
            <label class="col-md-1  control-label">
                报告装订日期<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" required name="reportBindingDate"
                       placeholder="报告装订日期"
                       class="form-control input-full date-picker dbdate"
                       data-date-format="yyyy-mm-dd"
                       pattern='yyyy-MM-dd'
                       value="<fmt:formatDate value='${projectXlxReportIndividual.reportBindingDate}' pattern='yyyy-MM-dd'/>">
            </div>
        </div>
    </div>
</div>


<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">


            <label class="col-md-1  control-label">
                报告份数<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full" required data-rule-number="true" placeholder="报告份数"
                       name="reportScore" value="${projectXlxReportIndividual.reportScore}">
            </div>

            <label class="col-md-1  control-label">
                报告修改日期<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" required name="reportModificationDate"
                       placeholder="报告修改日期"
                       class="form-control input-full date-picker dbdate"
                       data-date-format="yyyy-mm-dd"
                       pattern='yyyy-MM-dd'
                       value="<fmt:formatDate value='${projectXlxReportIndividual.reportModificationDate}' pattern='yyyy-MM-dd'/>">
            </div>
            <label class="col-md-1  control-label">
                归档日期<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" required name="filingDate"
                       placeholder="归档日期"
                       class="form-control input-full date-picker dbdate"
                       data-date-format="yyyy-mm-dd"
                       pattern='yyyy-MM-dd'
                       value="<fmt:formatDate value='${projectXlxReportIndividual.filingDate}' pattern='yyyy-MM-dd'/>">
            </div>


        </div>
    </div>
</div>
<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-md-1  control-label">
                委托人电话<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"
                       placeholder="委托人电话" required name="phonePrincipal"
                       value="${projectXlxReportIndividual.phonePrincipal}">
            </div>
            <label class="col-md-1  control-label">
                标准收费<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"
                       placeholder="标准收费" required name="standardCharge"
                       value="${projectXlxReportIndividual.standardCharge}">
            </div>
            <label class="col-md-1  control-label">
                实际收费<span class="symbol required"></span>
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"
                       placeholder="实际收费" required name="actualCharges"
                       value="${projectXlxReportIndividual.actualCharges}">
            </div>
        </div>
    </div>
</div>
