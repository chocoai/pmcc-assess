<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/5/24
  Time: 11:32
  To change this template use File | Settings | File Templates.
  评估假设 公共页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title">
        <h2>评估假设</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <form id="frm_task_evaluationHypothesis" class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    请选择假设
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                        <select name="DataID" class="form-control">
                            <c:forEach items="${hypothesisList}" var="data">
                                <option name="DataID" value="${data.id}">${data.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                        <textarea required placeholder="假设模板" class="form-control" name="Content">

                        </textarea>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>