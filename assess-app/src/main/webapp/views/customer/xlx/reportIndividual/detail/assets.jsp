<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-3-20
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
                估价对象建筑面积(㎡)    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="judgeObjectFloorArea">
                    <fmt:formatNumber value="${projectXlxReportIndividual.judgeObjectFloorArea}" type="number" maxFractionDigits="2"/>
                </label>
            </div>
            <label class="col-md-1  control-label">
                账面原值(万元)    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="accountValueOriginal">
                    <fmt:formatNumber value="${projectXlxReportIndividual.accountValueOriginal}" type="number" maxFractionDigits="2"/>
                </label>
            </div>
            <label class="col-md-1  control-label">
                拟稿人    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="draftAuthor">${projectXlxReportIndividual.draftAuthor}</label>
            </div>
        </div>
    </div>
</div>
<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">

            <label class="col-md-1  control-label">
                签章人员    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="signerPeople">${projectXlxReportIndividual.signerPeople}</label>
            </div>
            <label class="col-md-1  control-label">
                网上报备签章人    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="signedBy">${projectXlxReportIndividual.signedBy}</label>
            </div>
            <label class="col-md-1  control-label">
                评估目的    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="assessPurpose">${projectXlxReportIndividual.assessPurpose}</label>
            </div>
        </div>
    </div>
</div>
<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">

            <label class="col-md-1  control-label">
                评估方法    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="assessMethod">${projectXlxReportIndividual.assessMethod}</label>
            </div>

            <label class="col-md-1  control-label">
                评估基准日    
            </label>
            <div class="col-md-3">
                <label class="form-control input-full" name="valueTimePoint"><fmt:formatDate value='${projectXlxReportIndividual.valueTimePoint}' pattern='yyyy-MM-dd'/></label>
            </div>
            <label class="col-md-1  control-label">
                报告装订日期    
            </label>
            <div class="col-md-3">
                <label class="form-control input-full" name="reportBindingDate"><fmt:formatDate value='${projectXlxReportIndividual.reportBindingDate}' pattern='yyyy-MM-dd'/></label>
            </div>
        </div>
    </div>
</div>


<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">


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
            <label class="col-md-1  control-label">
                归档日期    
            </label>
            <div class="col-md-3">
                <label class="form-control input-full" name="filingDate"><fmt:formatDate value='${projectXlxReportIndividual.filingDate}' pattern='yyyy-MM-dd'/></label>
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
                标准收费    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="standardCharge">${projectXlxReportIndividual.standardCharge}</label>
            </div>
            <label class="col-md-1  control-label">
                实际收费    
            </label>
            <div class="col-md-3">
                <label  class="form-control input-full" name="actualCharges">${projectXlxReportIndividual.actualCharges}</label>
            </div>
        </div>
    </div>
</div>
