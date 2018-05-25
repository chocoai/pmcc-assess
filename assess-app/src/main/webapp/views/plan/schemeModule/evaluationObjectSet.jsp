<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-5-25
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach items="${dataList}" var="item">
    <div class="x_panel">
        <div class="x_title">
            <h2>
                <c:if test="${!empty item.provinceCityDistrictStr}">
                    ${item.provinceCityDistrictStr}
                </c:if>
            </h2>
            <ul class="nav navbar-right panel_toolbox">
                <li>
                    <a class="collapse-link" onclick="firstLoadJudgeObjectList(this,${item.id});">
                        <i class="fa fa-chevron-down"></i>
                    </a>
                </li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="x_content" style="display: none;">
            <form id="frmJudgeObject${item.id}" class="form-horizontal">
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            价值时点
                        </label>
                        <div class="col-sm-3">
                            <input type="text" name="valueTimePoint" required="required" placeholder="价值时间点"
                                   data-date-format="yyyy-mm-dd" class="form-control date-picker dbdate"
                                   readonly="readonly" pattern='yyyy-MM-dd'
                                   value="<fmt:formatDate value="${item.valueTimePoint}"  pattern="yyyy-MM-dd"/>">
                        </div>
                    </div>
                </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>估价对象编号</th>
                        <th>权证号</th>
                        <th>所有权人</th>
                        <th>最佳利用设置</th>
                        <th>合并测算序号</th>
                        <th>证载面积</th>
                        <th>评估面积</th>
                        <th>评估方法</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <div class="form-group">
                    <div class="x-valid">
                        <div class="col-sm-4 col-sm-offset-5">
                            <input type="button" class="btn btn-success"
                                   onclick="saveJudgeObject(this,'${item.id}')" value="保存">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</c:forEach>

<div id="divBoxMethodExtend" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true" data-height="260">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">评估方法</h4>
            </div>
            <div class="modal-body">

                <!-- start accordion -->
                <div class="accordion" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class="panel">
                        <a class="panel-heading" role="tab" id="headingOne" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            <h4 class="panel-title">Collapsible Group Items #1</h4>
                        </a>
                        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne" aria-expanded="true" style="">
                            <div class="panel-body">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>Username</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <th scope="row">1</th>
                                        <td>Mark</td>
                                        <td>Otto</td>
                                        <td>@mdo</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">2</th>
                                        <td>Jacob</td>
                                        <td>Thornton</td>
                                        <td>@fat</td>
                                    </tr>
                                    <tr>
                                        <th scope="row">3</th>
                                        <td>Larry</td>
                                        <td>the Bird</td>
                                        <td>@twitter</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="panel">
                        <a class="panel-heading collapsed" role="tab" id="headingTwo" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            <h4 class="panel-title">Collapsible Group Items #2</h4>
                        </a>
                        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo" aria-expanded="false" style="height: 0px;">
                            <div class="panel-body">
                                <p><strong>Collapsible Item 2 data</strong>
                                </p>
                                Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor,
                            </div>
                        </div>
                    </div>
                    <div class="panel">
                        <a class="panel-heading collapsed" role="tab" id="headingThree" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                            <h4 class="panel-title">Collapsible Group Items #3</h4>
                        </a>
                        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree" aria-expanded="false">
                            <div class="panel-body">
                                <p><strong>Collapsible Item 3 data</strong>
                                </p>
                                Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 评估方法 -->
<div id="divBoxMethod" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true" data-height="260">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">评估方法</h4>
            </div>
            <form id="frmMethod" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                                <input type="hidden" name="judgeObjectId" id="judgeObjectIdMethod"
                                       placeholder="估价对象在方法中的id">
                                <input type="hidden" name="methodType" id="methodTypeID">
                                <c:forEach items="${dataEvaluationMethod}" var="item">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-3 control-label">
                                                    ${item.name}
                                            </label>

                                            <label class="col-sm-9 control-label">
                                                适用<input type="radio" name="bisApplicable" value="1"
                                                         onclick="applyMethodA('${item.id}','${item.name}')">
                                                不适用<input type="radio" name="bisApplicable" value="0"
                                                          onclick="applyMethodB('${item.id}','${item.name}')">
                                            </label>
                                        </div>
                                    </div>

                                    <div class="form-group" id="applyTemplateView${item.id}">
                                        <label class="col-sm-3 control-label">
                                            适用原因模板
                                            <span class="input-group-btn">
                                                    <button type="button" id="applyTemplate${item.id}"
                                                            class="btn btn-primary">选择模板</button>
                                                </span>
                                        </label>
                                        <div class="col-sm-9">
                                                <textarea required="required" placeholder="请填写适用原因" class="form-control"
                                                          id="applicableReason${item.id}" name="applicableReason">

                                                </textarea>
                                        </div>
                                    </div>
                                    <div class="form-group" id="applyNoTemplateView${item.id}">
                                        <div class="x-valid">
                                            <label class="col-sm-3 control-label">
                                                适用不原因模板
                                                <span class="input-group-btn">
                                                    <button type="button" id="applyNoTemplate${item.id}"
                                                            class="btn btn-primary">选择模板</button>
                                                </span>
                                            </label>
                                            <div class="col-sm-9">
                                                <textarea required="required" placeholder="请填写不适用原因"
                                                          class="form-control" id="applicableReasonNo${item.id}"
                                                          name="applicableReasonNo">

                                                </textarea>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group" id="thinkView${item.id}">
                                        <div class="x-valid">
                                            <label class="col-sm-3 control-label">
                                                评估思路
                                            </label>
                                            <label class="col-sm-9 control-label">
                                                <label class="btn btn-success" onclick="evaluationthinking(${item.id})">思路选择</label>
                                            </label>
                                        </div>
                                    </div>

                                </c:forEach>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="saveMethod()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 评估思路 -->
<div id="divBoxThink" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true" data-height="190">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">评估思路</h4>
            </div>
            <form id="frmThink" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body" id="evaluationThinkTempleGroupX">
                                <div class="form-group" id="evaluationThinkTempleGroup1">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            评估技术思路
                                        </label>

                                        <div class="col-sm-9">
                                            <select class="form-control" id="EvaluationThinkSelect">
                                                <c:forEach items="${dataEvaluationThink}" var="item" varStatus="status">
                                                    <option value="${item.id}"
                                                            name="evaluationThink">${item.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group" id="evaluationThinkTempleGroup2">
                                    <input type="hidden" name="methodID" id="thinkMethodID">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            模板
                                        </label>
                                        <div class="col-sm-9">
                                            <textarea class="form-control" placeholder="模板显示数据"
                                                      id="evaluationThinkTemple">

                                            </textarea>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>
                        <div class="col-md-12">
                            <div id="evaluationThinkTempleGroup" class="panel-body">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default"
                            onclick="evaluationthinkingClose()">
                        关闭
                    </button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal"
                            onclick="evaluationthinkingSave()">
                        确认
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 方法模板 -->
<div id="divTemplate" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true" data-height="170">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="templateShow"></h4>
                <input type="hidden" id="templateID">
                <input type="hidden" id="methodFlag">
            </div>
            <form id="frmTemplate" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body" id="templatePanel">
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group" id="evaluationMethodTempleGroup">
                                    <div class="x-valid">
                                        <label class="col-sm-3 control-label">
                                            方法数据
                                        </label>
                                        <div class="col-sm-9">
                                            <textarea class="form-control" placeholder="模板显示数据"
                                                      name="evaluationMethodTemple" id="evaluationMethodTemple">

                                            </textarea>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div class="col-md-12">
                            <div id="evaluationMethodTempleGroupFields" class="panel-body">
                            </div>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" onclick="divTemplateClose()" class="btn btn-default">
                        关闭
                    </button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="evaluationmethodSave()">
                        确认
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">

    //初始化
    $(function () {
        <c:forEach items="${dataEvaluationMethod}" var="item">
        $("#applyNoTemplateView" +${item.id}).hide();
        $("#applyTemplateView" +${item.id}).hide();
        $("#thinkView" +${item.id}).hide();
        $("#thinkField" +${item.id}).hide();
        </c:forEach>

        //评估方法 模板 字段 选择
        <c:forEach items="${dataEvaluationMethod}" var="item">
        var applyNoTemplate = "applyNoTemplate" +${item.id};
        document.getElementById(applyNoTemplate).onclick = function () {//不适用
            document.getElementById("templateShow").innerText = "${item.name}";
            var id = "${item.id}";
            $("#frmTemplate").clearAll();
            $("#methodTypeID").val(id);
            evaluationmethodSelect(id, 0);
            $("#divTemplate").modal();//显示
        }
        var applyTemplate = "applyTemplate" +${item.id};//适用
        document.getElementById(applyTemplate).onclick = function () {
            document.getElementById("templateShow").innerText = "${item.name}";
            var id = "${item.id}";
            $("#frmTemplate").clearAll();
            $("#methodTypeID").val(id);
            evaluationmethodSelect(id, 1);
            $("#divTemplate").modal();//显示
        }
        </c:forEach>
    });
    //选项卡
    function applyMethodA(id, name) {
        $("#applyNoTemplateView" + id).hide();
        $("#thinkView" + id).show();
        $("#applyTemplateView" + id).show();
    }
    //选项卡
    function applyMethodB(id, name) {
        $("#applyNoTemplateView" + id).show();
        $("#thinkView" + id).hide();
        $("#applyTemplateView" + id).hide();
    }
    //分组保存
    function evaluationObject(id) {
        var data = formParams("evaluationObject" + id);//数据
        data.projectPlanID = '${projectPlan.id}';
        data.areaGroupId = id;
        var url = "${pageContext.request.contextPath}/projectplanschemeassist/evaluationObjectSave";
        $.ajax({
            url: url,
            data: data,
            type: "post",
            dataType: "json",
            success: function (result) {

                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                    });
                    location.reload();
                } else {
                    alert("保存失败:" + result.errmsg);
                }
            },
            error: function (result) {
                alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }


    //评估方法 save 页面处理数据
    function evaluationmethodSave() {
        var templateID = document.getElementById("templateID").value;
        var methodFlag = document.getElementById("methodFlag").value;
        if (templateID != null && templateID != '') {
            if (methodFlag == 0) {//判断方法是否适用
                $("#applicableReasonNo" + templateID).val($("#evaluationMethodTemple").val());
            } else {
                $("#applicableReason" + templateID).val($("#evaluationMethodTemple").val());
            }
        }
        document.getElementById("divTemplate").style.display = "none";
    }

    //评估方法 确定后保存 相当于总保存
    function saveMethod() {
        var data = formParams("frmMethod");
        var evaluationMethodTemple = $("#evaluationMethodTemple").val();
        //judgeObjectId
        console.log(evaluationMethodTemple);
        console.log(data);
        $.ajax({
            url: "${pageContext.request.contextPath}/projectplanschemeassist/judgeFunctionSave",
            data: data,
            type: "post",
            dataType: "json",
            success: function (result) {
                Alert("提交数据成功!", 1, null, function () {
                    if (result.ret) {
                        $("#divBoxMethod").hide();
                    } else {
                        alert("保存失败:" + result.errmsg);
                    }
                });
            },
            error: function (result) {
                alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    //评估方法选择
    function evaluationmethodSelect(id, type) {
        document.getElementById("templateID").value = id;
        document.getElementById("methodFlag").value = type;
        $.ajax({
            url: "${pageContext.request.contextPath}/projectplanschemeassist/evaluationmethod/getList",
            data: {
                id: id
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                var templatePanel = document.getElementById("templatePanel");
                for (var i = 0; i < result.length; i++) {
                    var data = result[i];
                    var divElement = document.createElement("div");
                    divElement.setAttribute("class", "form-group");

                    var divValidElement = document.createElement("div");
                    divValidElement.setAttribute("class", "x-valid");

                    var labelElement = document.createElement("label");
                    labelElement.setAttribute("class", "col-sm-3 control-label");

                    var divXElement = document.createElement("div");
                    divXElement.setAttribute("class", "col-sm-9");
                    var selectElement = document.createElement("select");
                    selectElement.setAttribute("class", "form-control");
                    selectElement.setAttribute("name", "methodID");
                    if (type == 0) {
                        labelElement.appendChild(document.createTextNode("不适用模板"));
                        var optionElement = document.createElement("option");
                        optionElement.appendChild(document.createTextNode("" + data.methodStr));
                        $("#evaluationMethodTemple").val(data.applicableReason + "");
                        selectElement.appendChild(optionElement);
                        methodFilds(0);
                    } else {
                        labelElement.appendChild(document.createTextNode("适用模板"));
                        var optionElement = document.createElement("option");
                        optionElement.appendChild(document.createTextNode("" + data.methodStr));
                        $("#evaluationMethodTemple").val(data.notApplicableReason + "");
                        selectElement.appendChild(optionElement);
                        methodFilds(1);
                    }

                    divXElement.appendChild(selectElement);
                    divValidElement.appendChild(labelElement);
                    divValidElement.appendChild(divXElement);


                    divElement.appendChild(divValidElement);
                }
                templatePanel.innerHTML = "";
                templatePanel.appendChild(divElement);
                $("#evaluationMethodTempleGroup").show();
            },
            error: function (result) {
                alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    //评估方法字段加载
    function methodFilds(type) {
        $("#evaluationMethodTempleGroupFields div").remove();
        var templateID = document.getElementById("templateID").value;
        //加载方法字段
        $.ajax({
            url: "${pageContext.request.contextPath}/projectplanschemeassist/evaluationmethod/fieldList",
            data: {
                id: templateID,
                type: type
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                var len = result.length;
                var num = Math.round(len / 4);
                console.log(result);
                console.log(num);
                console.log(len);
                var evaluationMethodTempleGroup = document.getElementById("evaluationMethodTempleGroupFields");
                if (len <= 4) {
                    var divElement = document.createElement("div");
                    divElement.setAttribute("class", "form-group");
                    for (var i = 0; i < len; i++) {
                        var data = result[i];
                        var divValid = document.createElement("div");
                        divValid.setAttribute("class", "x-valid");

                        var labelElement = document.createElement("label");
                        labelElement.setAttribute("class", "col-sm-1");
                        labelElement.appendChild(document.createTextNode("    " + data.name));

                        var divCol = document.createElement("div");
                        divCol.setAttribute("class", "col-sm-2");
                        var inputElement = document.createElement("input");
                        inputElement.setAttribute("type", "text");
                        inputElement.setAttribute("name", "methodType");
                        inputElement.setAttribute("id", "methodTypeID" + data.id);
                        inputElement.setAttribute("onblur", "methodFildReplace(evaluationMethodTemple,methodTypeID" + data.id + ",'" + data.name + "')");
                        inputElement.setAttribute("class", "form-control");
                        inputElement.setAttribute("placeholder", "替换字段");
                        divCol.appendChild(inputElement);

                        divValid.appendChild(labelElement);
                        divValid.appendChild(divCol);
                        divElement.appendChild(divValid);
                    }
                    evaluationMethodTempleGroup.appendChild(divElement);

                } else {
                    for (var i = 0; i < num; i++) {
                        var divElement = document.createElement("div");
                        divElement.setAttribute("class", "form-group");
                        for (var j = (1 * num); j < (1 * num) + 4; j++) {
                            var data = result[i];
                            var divValid = document.createElement("div");
                            divValid.setAttribute("class", "x-valid");

                            var labelElement = document.createElement("label");
                            labelElement.setAttribute("class", "col-sm-1");
                            labelElement.appendChild(document.createTextNode("    " + data.name));

                            var divCol = document.createElement("div");
                            divCol.setAttribute("class", "col-sm-2");
                            var inputElement = document.createElement("input");
                            inputElement.setAttribute("type", "text");
                            inputElement.setAttribute("name", "methodType");
                            inputElement.setAttribute("onblur", "methodFildReplace(evaluationMethodTemple,methodTypeID" + data.id + ",'" + data.name + "')");
                            inputElement.setAttribute("class", "form-control");
                            inputElement.setAttribute("placeholder", "替换字段");
                            divCol.appendChild(inputElement);

                            divValid.appendChild(labelElement);
                            divValid.appendChild(divCol);
                            divElement.appendChild(divValid);
                        }
                        evaluationMethodTempleGroup.appendChild(divElement);
                    }
                    var divElement = document.createElement("div");
                    divElement.setAttribute("class", "form-group");
                    for (var i = num * 4; i < len; i++) {//剩余的 取模剩余的
                        var data = result[i];
                        var divValid = document.createElement("div");
                        divValid.setAttribute("class", "x-valid");

                        var labelElement = document.createElement("label");
                        labelElement.setAttribute("class", "col-sm-1");
                        labelElement.appendChild(document.createTextNode("    " + data.name));

                        var divCol = document.createElement("div");
                        divCol.setAttribute("class", "col-sm-2");
                        var inputElement = document.createElement("input");
                        inputElement.setAttribute("type", "text");
                        inputElement.setAttribute("name", "methodType");
                        inputElement.setAttribute("onblur", "methodFildReplace(evaluationMethodTemple,methodTypeID" + data.id + ",'" + data.name + "')");
                        inputElement.setAttribute("class", "form-control");
                        inputElement.setAttribute("placeholder", "替换字段");
                        divCol.appendChild(inputElement);

                        divValid.appendChild(labelElement);
                        divValid.appendChild(divCol);
                        divElement.appendChild(divValid);
                    }
                    evaluationMethodTempleGroup.appendChild(divElement);
                }
            },
            error: function (result) {
                alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    //评估方法字段替换
    function methodFildReplace(id1, id2, name) {
        var value = $(id2).val();
        var regex = '/\{' + name + '\}/g';
        if (value != null && value != '') {
            var x1 = $(id1).val().replace(eval(regex), value);
            $(id1).val(x1);
        }
    }

    //评估方法模板字段 关闭
    function divTemplateClose() {
        $("#divTemplate").hide();
    }

    //评估方法 视图
    function evaluationmethod(id) {
        //估价对象 id
        $("#frmMethod").clearAll();
        $("#judgeObjectIdMethod").val(id);
        $("#divBoxMethod").modal();//显示
    }

    //评估思路 视图
    function evaluationthinking(id) {
        $("#frmThink").clearAll();
        $("#thinkMethodID").val(id);
        $("#divBoxThink").modal();//显示
    }


    //评估思路 保存
    function evaluationthinkingSave() {
        var evaluationThinkTemple = $("#evaluationThinkTemple").val();
        var thinkMethodID = $("#thinkMethodID").val();
        var data = formParams("frmThink");
        data.thinking = evaluationThinkTemple;
        data.methodType = thinkMethodID;
        data.judgeObjectId = $("#judgeObjectIdMethod").val();
        console.log(data);
        $.ajax({
            url: "${pageContext.request.contextPath}/projectplanschemeassist/judgeFunctionSave",
            data: data,
            type: "post",
            dataType: "json",
            success: function (result) {
                Alert("提交数据成功!", 1, null, function () {
                    if (result.ret) {
                        $("#divBoxThink").hide();
                    } else {
                        alert("保存失败:" + result.errmsg);
                    }
                });
            },
            error: function (result) {
                alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    //评估思路 关闭
    function evaluationthinkingClose() {
        $("#divBoxThink").hide();
    }

    //评估思路 字段替换
    function thinkFildReplace(id1, id2, name) {
        var value = $(id2).val();
        var regex = '/\{' + name + '\}/g';
        if (value != null && value != '') {
            var x1 = $(id1).val().replace(eval(regex), value);
            $(id1).val(x1);
        }
    }

    //评估思路  选择
    $("#EvaluationThinkSelect").change(function () {
        var selected = $(this).children('option:selected').val();
        if (selected != "" && selected != '' && selected != null) {
            $.ajax({// get
                url: "${pageContext.request.contextPath}/projectplanschemeassist/evaluationThink/think",
                data: {
                    id: selected,
                    flag: 1
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    var evaluationThinkTemple = document.getElementById("evaluationThinkTemple");
                    var data = result.applicableReason + "";
                    if (data != null && data != '' && data != "") {
                        document.getElementById("evaluationThinkTemple").value = data;
                        writeThinkFieldS(selected);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        }
    });

    //评估思路 字段
    function writeThinkFieldS(id) {
        $.ajax({// list
            url: "${pageContext.request.contextPath}/projectplanschemeassist/evaluationThink/think",
            data: {
                id: id,
                flag: 2,
                type: 0
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                var len = result.length;
                var num = Math.round(len / 4);
                $("#evaluationThinkTempleGroup div").remove();
                var evaluationThinkTempleGroup = document.getElementById("evaluationThinkTempleGroup");
                if (len <= 4) {
                    var divElement = document.createElement("div");
                    divElement.setAttribute("class", "form-group");
                    for (var i = 0; i < len; i++) {
                        var data = result[i];
                        var divValid = document.createElement("div");
                        divValid.setAttribute("class", "x-valid");

                        var labelElement = document.createElement("label");
                        labelElement.setAttribute("class", "col-sm-1");
                        labelElement.appendChild(document.createTextNode("    " + data.name));

                        var divCol = document.createElement("div");
                        divCol.setAttribute("class", "col-sm-2");
                        var inputElement = document.createElement("input");
                        inputElement.setAttribute("type", "text");
                        inputElement.setAttribute("id", "thinkTypeID" + data.id);
                        inputElement.setAttribute("class", "form-control");
                        inputElement.setAttribute("onblur", "thinkFildReplace(evaluationThinkTemple,thinkTypeID" + data.id + ",'" + data.name + "')");
                        inputElement.setAttribute("placeholder", "替换字段");
                        divCol.appendChild(inputElement);

                        divValid.appendChild(labelElement);
                        divValid.appendChild(divCol);
                        divElement.appendChild(divValid);
                    }
                    evaluationThinkTempleGroup.appendChild(divElement);

                } else {
                    for (var i = 0; i < num; i++) {
                        var divElement = document.createElement("div");
                        divElement.setAttribute("class", "form-group");
                        for (var j = (1 * num); j < (1 * num) + 4; j++) {
                            var data = result[i];
                            var divValid = document.createElement("div");
                            divValid.setAttribute("class", "x-valid");

                            var labelElement = document.createElement("label");
                            labelElement.setAttribute("class", "col-sm-1");
                            labelElement.appendChild(document.createTextNode("    " + data.name));

                            var divCol = document.createElement("div");
                            divCol.setAttribute("class", "col-sm-2");
                            var inputElement = document.createElement("input");
                            inputElement.setAttribute("type", "text");
                            inputElement.setAttribute("id", "thinkTypeID" + data.id);
                            inputElement.setAttribute("class", "form-control");
                            inputElement.setAttribute("onblur", "thinkFildReplace(evaluationThinkTemple,thinkTypeID" + data.id + ",'" + data.name + "')");
                            inputElement.setAttribute("placeholder", "替换字段");
                            divCol.appendChild(inputElement);

                            divValid.appendChild(labelElement);
                            divValid.appendChild(divCol);
                            divElement.appendChild(divValid);
                        }
                        evaluationThinkTempleGroup.appendChild(divElement);
                    }
                    var divElement = document.createElement("div");
                    divElement.setAttribute("class", "form-group");
                    for (var i = num * 4; i < len; i++) {//剩余的 取模剩余的
                        var data = result[i];
                        var divValid = document.createElement("div");
                        divValid.setAttribute("class", "x-valid");

                        var labelElement = document.createElement("label");
                        labelElement.setAttribute("class", "col-sm-1");
                        labelElement.appendChild(document.createTextNode("    " + data.name));

                        var divCol = document.createElement("div");
                        divCol.setAttribute("class", "col-sm-2");
                        var inputElement = document.createElement("input");
                        inputElement.setAttribute("type", "text");
                        inputElement.setAttribute("id", "thinkTypeID" + data.id);
                        inputElement.setAttribute("class", "form-control");
                        inputElement.setAttribute("onblur", "thinkFildReplace(evaluationThinkTemple,thinkTypeID" + data.id + ",'" + data.name + "')");
                        inputElement.setAttribute("placeholder", "替换字段");
                        divCol.appendChild(inputElement);

                        divValid.appendChild(labelElement);
                        divValid.appendChild(divCol);
                        divElement.appendChild(divValid);
                    }
                    evaluationThinkTempleGroup.appendChild(divElement);
                }
            },
            error: function (result) {
                alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }


    //-------------------------------------------------------------------------

    //保存估价对象
    function saveJudgeObject(_this, areaGroupId) {
        //先验证数据必填
        var form = $(_this).closest(".x_panel").find("form");
        if (!form.valid()) {
            return false;
        }
        var tbody = $(_this).closest(".x_panel").find(".table").find("tbody");
        //验证评估面积是否合理

        var data = {}; //找出需要保存的数据
        data.valueTimePoint = form.find('[name="valueTimePoint"]').val();
        data.planId = '${projectPlan.id}';
        data.areaGroupId = areaGroupId;
        data.schemeJudgeObjects = [];

        var trs = tbody.find('tr');
        if (trs && trs.length > 0) {
            $.each(trs, function (i, tr) {
                var schemeJudgeObject = {};
                schemeJudgeObject.id = $(tr).find('[data-name="id"]').val();
                schemeJudgeObject.bisSplit = $(tr).find('[data-name="bisSplit"]').val();
                schemeJudgeObject.number = $(tr).find('[data-name="number"]').val();
                schemeJudgeObject.sourceId = $(tr).find('[data-name="sourceId"]').val();
                schemeJudgeObject.splitNumber = $(tr).find('[data-name="splitNumber"]').val();
                schemeJudgeObject.bestUseId = $(tr).find('[data-name="bestUseId"]').val();
                schemeJudgeObject.groupNumber = $(tr).find('[data-name="groupNumber"]').val();
                schemeJudgeObject.evaluationArea = $(tr).find('[data-name="evaluationArea"]').val();
                data.schemeJudgeObjects.push(schemeJudgeObject);
            })
        }

        var url = "${pageContext.request.contextPath}/projectplanschemeassist/saveEvaluationObject";
        $.ajax({
            url: url,
            data: {formData:JSON.stringify(data)},
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    Alert("保存成功!");
                    loadJudgeObjectList(tbody,areaGroupId);
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    //估价对象编号一致的评估面积总和是否与证载面积相等
    function areaIsAgreement(tbody) {
        var trs = tbody.find('tr');
        var agreement = true;
        if (trs && trs.length > 0) {
            var evaluationAreaTotal = 0;
            var floorArea = $(trs[0])
            $.each(trs, function (i, tr) {
                var evaluationArea = tr.find('[data-name="evaluationArea"]').val();
                evaluationAreaTotal += parseFloat(evaluationArea);
            })
            agreement = parseFloat(floorArea) == parseFloat(evaluationAreaTotal);
        }
        return agreement;
    }

    //检测是否可以设置估价方法
    function canSetJudgeFunction(tbody) {
        var trs = tbody.find('tr');
        var canSet = true;
        $.each(trs, function (i, tr) {
            var id = $(tr).find('[data-name="id"]').val();
            var groupNumber = $(tr).find('[data-name="groupNumber"]').val();
            if (!id || id <= 0) {
                canSet = false;
                return false;
            }
            if (groupNumber == "") {
                canSet = false;
                return false;
            }
        })
        return canSet;
    }
    //检查哪些位置可以设置评估方法
    function whereCanSetJudgeFunction(tbody) {
        //先分组 在设置分组第一个可设评估方法
        tbody.find('tr').find('td:eq(-2)').html('');
        var trs = tbody.find('tr');
        var groupArray = [];
        if (trs && trs.length > 0) {
            $.each(trs, function (i, tr) {
                var groupNumber = $(tr).find('[data-name="groupNumber"]').val();
                if (groupNumber) {
                    if ($.inArray(groupNumber, groupArray) < 0) {
                        groupArray.push(groupNumber);
                    }
                }
            })
            $.each(groupArray, function (i, groupNumer) {
                var groupNumberEles = tbody.find('[data-name="groupNumber"]');
                if (groupNumberEles && groupNumberEles.length > 0) {
                    $.each(groupNumberEles, function (j, item) {
                        if ($(item).val() == groupNumer) {
                            $(item).closest('tr').find('td:eq(-2)')
                                .html('<input class="btn btn-success" type="button" value="评估方法" onclick="evaluationmethod(this)">');
                            return false;
                        }
                    })
                }
            })
        }
    }

    //合并测试号移开光标时调用
    function groupNumberBlur(_this) {
        var tr = $(_this).closest("tr");
        var tbody = tr.closest("tbody");
        if (canSetJudgeFunction(tbody)) {
            whereCanSetJudgeFunction(tbody);
        } else {
            tbody.find('tr').find('td:eq(-2)').html('');
        }
    }

    //拆分估价对象
    function splitJudgeObject(_this) {
        var tr = $(_this).closest("tr");
        var tbody = tr.closest("tbody");
        var number = tr.find('[data-name="number"]').val();
        var numberEleLast = tbody.find('[data-name="number"][value=' + number + ']:last');//相同编号的最后一个元素添加拆分对象
        var splitTr = $(numberEleLast).closest("tr").after("<tr>" + tr.html() + "</tr>").next();
        splitTr.find('[data-name="bisSplit"]').val("true");
        splitTr.find('[data-name="id"]').val("0");
        splitTr.find('[data-name="sourceId"]').val(tr.find('[data-name="id"]').val());
        splitTr.find("td:last").html('<input class="btn btn-warning" type="button" value="移除" onclick="removeJudgeObject(this)">');
        computeSplitNumber(tbody, splitTr.find('[data-name="number"]').val());

        if (canSetJudgeFunction(tbody)) {
            whereCanSetJudgeFunction(tbody);
        } else {
            tbody.find('tr').find('td:eq(-2)').html('');
        }
    }

    //移除拆分的估价对象
    function removeJudgeObject(_this) {
        var tr = $(_this).closest("tr");
        var tbody = tr.closest("tbody");
        var number = tr.find('[data-name="number"]').val();
        tr.remove();
        computeSplitNumber(tbody, number);

        if (canSetJudgeFunction(tbody)) {
            whereCanSetJudgeFunction(tbody);
        } else {
            tbody.find('tr').find('td:eq(-2)').html('');
        }
    }

    //换算拆分号
    function computeSplitNumber(tbody, number) {
        var inputs = tbody.find('[data-name="number"][value=' + number + ']');
        if (inputs.length == 1) {
            var tr = $(inputs[0]).closest('tr');
            var mergeNumber = tr.find('[data-name="number"]').val();
            tr.find('[data-name="mergeNumber"]').text(mergeNumber);
            tr.find('[data-name="splitNumber"]').val(0);
            tr.find('[data-name="bestUseId"]').attr('name', 'bestUseId' + mergeNumber);
            tr.find('[data-name="groupNumber"]').attr('name', 'groupNumber' + mergeNumber);
            tr.find('[data-name="evaluationArea"]').attr('name', 'evaluationArea' + mergeNumber);
        } else {
            $.each(inputs, function (i, input) {
                var tr = $(input).closest('tr');
                var splitNumber = i + 1;
                var mergeNumber = tr.find('[data-name="number"]').val() + "-" + splitNumber;
                tr.find('[data-name="mergeNumber"]').text(mergeNumber);
                tr.find('[data-name="splitNumber"]').val(splitNumber);
                tr.find('[data-name="bestUseId"]').attr('name', 'bestUseId' + mergeNumber);
                tr.find('[data-name="groupNumber"]').attr('name', 'groupNumber' + mergeNumber);
                tr.find('[data-name="evaluationArea"]').attr('name', 'evaluationArea' + mergeNumber);
            })
        }
    }

    //初次加载估价对象信息
    function firstLoadJudgeObjectList(_this, areaGroupId) {
        var tbody = $(_this).closest(".x_panel").find(".table").find("tbody");
        var trs = tbody.find("tr");
        if (!trs || trs.length <= 0) {
            loadJudgeObjectList(tbody, areaGroupId);
        }
    }

    //加载估价对象列表
    function loadJudgeObjectList(tbody, areaGroupId) {
        tbody.empty();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectplanschemeassist/schemeAreaGroupVoList",
            data: {
                areaGroupId: areaGroupId
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                if (result) {
                    $.each(result, function (i, item) {
                        var html = $("#judgeObjectHtml").html();
                        html = html.replace("{id}", item.id == undefined ? "" : item.id);
                        html = html.replace("{bisSplit}", item.bisSplit == undefined ? false : item.bisSplit);
                        html = html.replace("{number}", item.number == undefined ? "" : item.number);
                        html = html.replace("{splitNumber}", item.splitNumber == undefined ? "" : item.splitNumber);
                        html = html.replace("{sourceId}", item.sourceId == undefined ? "" : item.sourceId);
                        if (item.splitNumber) {
                            html = html.replace(/{mergeNumber}/g, item.number + "-" + item.splitNumber);
                        } else {
                            html = html.replace(/{mergeNumber}/g, item.number == undefined ? "" : item.number);
                        }
                        html = html.replace("{name}", item.name == undefined ? "" : item.name);
                        html = html.replace("{ownership}", item.ownership == undefined ? "" : item.ownership);
                        html = html.replace("{groupNumber}", item.groupNumber == undefined ? "" : item.groupNumber);
                        html = html.replace("{floorArea}", item.floorArea == undefined ? "" : item.floorArea);
                        html = html.replace("{evaluationArea}", item.evaluationArea == undefined ? "" : item.evaluationArea);
                        tbody.append(html);
                        tbody.find("tr:last").find('[data-name="bestUseId"]').val(item.bestUseId);
                    })

                    if (canSetJudgeFunction(tbody)) {
                        whereCanSetJudgeFunction(tbody);
                    } else {
                        tbody.find('tr').find('td:eq(-2)').html('');
                    }
                }
            },
            error: function (result) {
                alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }
</script>

<!--评估对象-->
<script type="text/html" id="judgeObjectHtml">
    <tr>
        <td>
            <input type="hidden" data-name="id" value="{id}">
            <input type="hidden" data-name="bisSplit" value="{bisSplit}">
            <input type="hidden" data-name="sourceId" value="{sourceId}">
            <input type="hidden" data-name="number" value="{number}">
            <input type="hidden" data-name="splitNumber" value="{splitNumber}">
            <label class="form-control" data-name="mergeNumber">{mergeNumber}</label>
        </td>
        <td><label class="form-control" data-name="name">{name}</label></td>
        <td><label class="form-control" data-name="ownership">{ownership}</label></td>
        <td>
            <div class="x-valid">
                <select class="form-control" required data-name="bestUseId" name="bestUseId{mergeNumber}">
                    <option value="">--请选择--</option>
                    <c:forEach items="${bestusedescriptionList}" var="bestUse">
                        <option value="${bestUse.id}">${bestUse.name}</option>
                    </c:forEach>
                </select>
            </div>
        </td>
        <td>
            <div class="x-valid">
                <input class="form-control" type="text" required data-rule-digits="true"
                       name="groupNumber{mergeNumber}" data-name="groupNumber" onblur="groupNumberBlur(this);"
                       placeholder="合并测算序号" value="{groupNumber}">
            </div>
        </td>
        <td><label class="form-control">{floorArea}</label></td>
        <td>
            <div class="x-valid">
                <input class="form-control" type="text" required data-rule-number="true"
                       name="evaluationArea{mergeNumber}" data-name="evaluationArea"
                       placeholder="评估面积" value="{evaluationArea}">
            </div>

        </td>
        <td>

        </td>
        <td>
            <input class="btn btn-success" type="button" value="拆分" onclick="splitJudgeObject(this)">
        </td>
    </tr>
</script>