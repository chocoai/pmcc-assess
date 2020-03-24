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
                <label  class="form-control input-full" name="assessTotalPrice">
                    <fmt:formatNumber value="${projectXlxReportIndividual.assessTotalPrice}" type="number" maxFractionDigits="2"/>
                </label>
            </div>

            <label class="col-md-1  control-label">
                评估单价(元)    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="assessPrice">
                    <fmt:formatNumber value="${projectXlxReportIndividual.assessPrice}" type="number" maxFractionDigits="2"/>
                </label>
            </div>
            <label class="col-md-1  control-label">
                土地面积(㎡)    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="landArea">
                    <fmt:formatNumber value="${projectXlxReportIndividual.landArea}" type="number" maxFractionDigits="2"/>
                </label>
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
                <label  class="form-control input-full" name="floorArea">
                    <fmt:formatNumber value="${projectXlxReportIndividual.floorArea}" type="number" maxFractionDigits="2"/>
                </label>
            </div>
            <label class="col-md-1  control-label">
                估价目的    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="valuationPurpose">${projectXlxReportIndividual.valuationPurpose}</label>
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
                估价时点    
            </label>
            <div class="col-md-3">
                <label class="form-control input-full" name="valueTimePoint"><fmt:formatDate value='${projectXlxReportIndividual.valueTimePoint}' pattern='yyyy-MM-dd'/></label>
            </div>
            <label class="col-md-1  control-label">
                估价作业期    
            </label>
            <div class="col-md-3">
                <label class="form-control input-full" name="appraisalPeriod"><fmt:formatDate value='${projectXlxReportIndividual.appraisalPeriod}' pattern='yyyy-MM-dd'/></label>
            </div>

            <label class="col-md-1  control-label">
                收费(万元)    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="assessTotalToll">
                    <fmt:formatNumber value="${projectXlxReportIndividual.assessTotalToll}" type="number" maxFractionDigits="2"/>
                </label>
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
                <label  class="form-control input-full" name="landUse">${projectXlxReportIndividual.landUse}</label>
            </div>

            <label class="col-md-1  control-label">
                项目负责人    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="projectLeader">${projectXlxReportIndividual.projectLeader}</label>
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
                <label  class="form-control input-full" name="reportScore">
                    <fmt:formatNumber value="${projectXlxReportIndividual.reportScore}" type="number" maxFractionDigits="2"/>
                </label>
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
                委托银行    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="entrustedBank">${projectXlxReportIndividual.entrustedBank}</label>
            </div>
            <label class="col-md-1  control-label">
                委托人地址    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="addressPrincipal">${projectXlxReportIndividual.addressPrincipal}</label>
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
                <label  class="form-control input-full" name="phonePrincipal">${projectXlxReportIndividual.phonePrincipal}</label>
            </div>
            <label class="col-md-1  control-label">
                宗地位置    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="landPlotsLocation">${projectXlxReportIndividual.landPlotsLocation}</label>
            </div>
            <label class="col-md-1  control-label">
                宗地数    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="landPlotsNumber">
                    <fmt:formatNumber value="${projectXlxReportIndividual.landPlotsNumber}" type="number" maxFractionDigits="2"/>
                </label>
            </div>
        </div>
    </div>
</div>
