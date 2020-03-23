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
                评估总价(万元)   
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"
                       placeholder="评估总价(万元)"    name="assessTotalPrice"
                       data-rule-number="true"
                       value="${projectXlxReportIndividual.assessTotalPrice}">
            </div>

            <label class="col-md-1  control-label">
                评估单价(元)   
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"
                       data-rule-number="true"
                       placeholder="评估单价(元)"    name="assessPrice"
                       value="${projectXlxReportIndividual.assessPrice}">
            </div>
            <label class="col-md-1  control-label">
                土地面积(㎡)   
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"   
                       placeholder="土地面积(㎡)" data-rule-number="true"
                       name="landArea"
                       value="${projectXlxReportIndividual.landArea}">
            </div>
        </div>
    </div>
</div>

<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-md-1  control-label">
                建筑面积(㎡)    
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"
                       data-rule-number="true"
                       placeholder="建筑面积(㎡)"    name="floorArea"
                       value="${projectXlxReportIndividual.floorArea}">
            </div>
            <label class="col-md-1  control-label">
                估价目的   
            </label>
            <div class="col-md-3">
                <input type="text" placeholder="估价目的" class="form-control input-full"   
                       name="valuationPurpose" value="${projectXlxReportIndividual.valuationPurpose}">
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
                收费(万元)   
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full" data-rule-number="true"
                       placeholder="收费(万元)"    name="assessTotalToll"
                       value="${projectXlxReportIndividual.assessTotalToll}">
            </div>

        </div>
    </div>
</div>


<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">

            <label class="col-md-1  control-label">
                土地用途   
            </label>
            <div class="col-md-3">
                <input type="text" placeholder="土地用途" class="form-control input-full"   
                       name="landUse" value="${projectXlxReportIndividual.landUse}">
            </div>

            <label class="col-md-1  control-label">
                项目负责人   
            </label>
            <div class="col-md-3">
                <input type="text" placeholder="项目负责人" class="form-control input-full"   
                       name="projectLeader" value="${projectXlxReportIndividual.projectLeader}">
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
                       value="<fmt:formatDate value='${projectXlxReportIndividual.filingDate}' pattern='yyyy-MM-dd'/>">
            </div>


            <label class="col-md-1  control-label">
                委托银行   
            </label>
            <div class="col-md-3">
                <input type="text" placeholder="委托银行" class="form-control input-full"   
                       name="entrustedBank" value="${projectXlxReportIndividual.entrustedBank}">
            </div>
            <label class="col-md-1  control-label">
                委托人地址   
            </label>
            <div class="col-md-3">
                <input type="text" placeholder="委托人地址" class="form-control input-full"   
                       name="addressPrincipal" value="${projectXlxReportIndividual.addressPrincipal}">
            </div>
        </div>
    </div>
</div>

<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-md-1  control-label">
                委托人及电话   
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"
                       placeholder="委托人及电话"    name="phonePrincipal"
                       value="${projectXlxReportIndividual.phonePrincipal}">
            </div>
            <label class="col-md-1  control-label">
                宗地位置   
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"
                       placeholder="宗地位置"    name="landPlotsLocation"
                       value="${projectXlxReportIndividual.landPlotsLocation}">
            </div>
            <label class="col-md-1  control-label">
                宗地数   
            </label>
            <div class="col-md-3">
                <input type="text" class="form-control input-full"    data-rule-number="true" placeholder="宗地数目"
                       name="landPlotsNumber" value="${projectXlxReportIndividual.landPlotsNumber}">
            </div>
        </div>
    </div>
</div>
