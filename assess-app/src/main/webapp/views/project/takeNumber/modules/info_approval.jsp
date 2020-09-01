<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/9/3
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    项目拿号
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form id="project_takeNumber_form" class="form-horizontal">
                <input type="hidden" name="id" value="${projectTakeNumber.id}">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                报告类型
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectTakeNumber.reportTypeName}</label>
                            </div>
                            <c:if test="${not empty projectTakeNumber.numberValue}">
                                <label class="col-sm-1 col-form-label">
                                    文号
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control input-full">${projectTakeNumber.numberValue}</label>
                                </div>
                            </c:if>
                            <c:if test="${true eq projectTakeNumber.bisQrcode}">
                                <div class="col-sm-2">
                                    <div class="form-check" style="justify-content:left">
                                        <label class="form-check-label">
                                            <input class="form-check-input" type="checkbox" name="bisQrcode"
                                                   checked="checked" disabled="disabled" value="true">
                                            <span class="form-check-sign">${projectTakeNumber.reportGroupName}</span>
                                        </label>
                                    </div>
                                </div>
                                <c:if test="${not empty projectTakeNumber.qrcode}">
                                    <div class="col-sm-2">
                                        <img style="width: 100px;height: 100px;" src="data:image/png;base64,${projectTakeNumber.qrcode}">
                                    </div>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                说明<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control input-full">${projectTakeNumber.remark}</label>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="application/javascript">
    $(function () {

    });
</script>
