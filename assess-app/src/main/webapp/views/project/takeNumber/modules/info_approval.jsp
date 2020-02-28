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
                                报告类型<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectTakeNumber.reportTypeName}</label>
                            </div>
                        </div>
                    </div>
                    <c:if test="${not empty numberValue}">
                        <div class="col-md-4">
                            <div class="form-inline x-valid">
                                <label class="col-sm-2 col-form-label">
                                    文号
                                </label>
                                <div class="col-sm-10">
                                    <label class="form-control input-full">${numberValue}</label>
                                </div>
                            </div>
                        </div>
                    </c:if>
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
