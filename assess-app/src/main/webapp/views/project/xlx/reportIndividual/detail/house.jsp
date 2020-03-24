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
                <label  class="form-control input-full" name="location">${projectXlxReportIndividual.location}</label>
            </div>

            <label class="col-md-1  control-label">
                估价对象建筑面积(㎡)   
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="judgeObjectFloorArea">
                    <fmt:formatNumber value="${projectXlxReportIndividual.judgeObjectFloorArea}" type="number" maxFractionDigits="2"/>
                </label>
            </div>
            <label class="col-md-1  control-label">
                估价对象土地面积(㎡)  
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="judgeObjectLandArea">
                    <fmt:formatNumber value="${projectXlxReportIndividual.judgeObjectLandArea}" type="number" maxFractionDigits="2"/>
                </label>
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
                <label  class="form-control input-full" name="worth">
                    <fmt:formatNumber value="${projectXlxReportIndividual.worth}" type="number" maxFractionDigits="2"/>
                </label>
            </div>
            <label class="col-md-1  control-label">
                单价(元)  
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="price">
                    <fmt:formatNumber value="${projectXlxReportIndividual.price}" type="number" maxFractionDigits="2"/>
                </label>
            </div>
            <label class="col-md-1  control-label">
                估价时点  
            </label>
            <div class="col-md-3">
                <label class="form-control input-full" name="valueTimePoint"><fmt:formatDate value='${projectXlxReportIndividual.valueTimePoint}' pattern='yyyy-MM-dd'/></label>
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
                <label class="form-control input-full" name="appraisalPeriod"><fmt:formatDate value='${projectXlxReportIndividual.appraisalPeriod}' pattern='yyyy-MM-dd'/></label>
            </div>

            <label class="col-md-1  control-label">
                评估目的  
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="assessPurpose">${projectXlxReportIndividual.assessPurpose}</label>
            </div>

            <label class="col-md-1  control-label">
                估价方法  
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="valuationMethod">${projectXlxReportIndividual.valuationMethod}</label>
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
                <label  class="form-control input-full" name="housingUse">${projectXlxReportIndividual.housingUse}</label>
            </div>

            <label class="col-md-1  control-label">
                报告人  
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="reportPeople">${projectXlxReportIndividual.reportPeople}</label>
            </div>

            <label class="col-md-1  control-label">
                签章人员  
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="signerPeople">${projectXlxReportIndividual.signerPeople}</label>
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
                <label class="form-control input-full" name="reportBindingDate"><fmt:formatDate value='${projectXlxReportIndividual.reportBindingDate}' pattern='yyyy-MM-dd'/></label>
            </div>

            <label class="col-md-1  control-label">
                报告份数  
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="reportScore">${projectXlxReportIndividual.reportScore}</label>
            </div>

            <label class="col-md-1  control-label">
                报告修改日期  
            </label>
            <div class="col-md-3">
                <label class="form-control input-full" name="reportModificationDate"><fmt:formatDate value='${projectXlxReportIndividual.reportModificationDate}' pattern='yyyy-MM-dd'/></label>
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
                <label class="form-control input-full" name="filingDate"><fmt:formatDate value='${projectXlxReportIndividual.filingDate}' pattern='yyyy-MM-dd'/></label>
            </div>
            <label class="col-md-1  control-label">
                合同签订日期  
            </label>
            <div class="col-md-3">
                <label class="form-control input-full" name="contractSigningDate"><fmt:formatDate value='${projectXlxReportIndividual.contractSigningDate}' pattern='yyyy-MM-dd'/></label>
            </div>


            <label class="col-md-1  control-label">
                委托银行  
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="entrustedBank">${projectXlxReportIndividual.entrustedBank}</label>
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
                <label  class="form-control input-full" name="phonePrincipal">${projectXlxReportIndividual.phonePrincipal}</label>
            </div>
            <label class="col-md-1  control-label">
                项目取得方式  
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="projectAcquisitionMethod">${projectXlxReportIndividual.projectAcquisitionMethod}</label>
            </div>
            <label class="col-md-1  control-label">
                收费(元)  
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="assessTotalToll">
                    <fmt:formatNumber value="${projectXlxReportIndividual.assessTotalToll}" type="number" maxFractionDigits="2"/>
                </label>
            </div>
        </div>
    </div>
</div>
