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
    <form id="frm_task_evaluationHypothesis" class="form-horizontal">
        <div class="x_content">
            <div class="form-group">
                <label class="col-sm-1 control-label">
                    请选择假设
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                        <select name="DataID" class="form-control" id="hypothesisSelectID">
                            <option value="">请选择</option>
                            <c:forEach items="${hypothesisList}" var="data">
                                <option name="DataID" value="${data.id}">${data.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-1 control-label">
                    模板数据
                </label>
                <div class="x-valid">
                    <div class="col-sm-11">
                        <textarea required="required" id="hypothesisTemple" placeholder="假设模板" class="form-control"
                                  name="Content">

                        </textarea>
                    </div>
                </div>
            </div>

            <div id="viewH">

            </div>
        </div>

    </form>
</div>
<script type="text/javascript">
    $("#hypothesisSelectID").change(function () {
        var selected = $(this).children('option:selected').val();
        var data = {};
        data.id = selected;
        $.ajax({
            url: "${pageContext.request.contextPath}/evaluationHypothesis/get",
            type: "GET",
            dataType: "json",
            data: data,
            success: function (result) {
                $("#hypothesisTemple").val(result.template);
                getHypothesisField(selected);
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    });

    function getHypothesisField(hypothesisId) {
        var data = {};
        data.hypothesisId = hypothesisId;
        $.ajax({
            url: "${pageContext.request.contextPath}/evaluationHypothesisNG/listFields",
            type: "POST",
            dataType: "json",
            data: data,
            success: function (result) {
                writeHypothesisField(result);
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //字段写入
    function writeHypothesisField(result) {
        var viewH = document.getElementById("viewH");
        var len = result.length;
        var num = Math.round(len / 4);
        if (len <= 4) {
            var divElement = document.createElement("div");
            divElement.setAttribute("class", "form-group");
            for (var i = 0; i < len; i++) {
                var data = result[i];
                var divValid = document.createElement("div");
                divValid.setAttribute("class", "x-valid");

                var labelElement = document.createElement("label");
                labelElement.setAttribute("class", "col-sm-1");
                labelElement.appendChild(document.createTextNode("" + data.name));

                var divCol = document.createElement("div");
                divCol.setAttribute("class", "col-sm-2");
                var inputElement = document.createElement("input");
                inputElement.setAttribute("type", "text");
                inputElement.setAttribute("name", "hypothesisType");
                inputElement.setAttribute("id", "XTypeID" + data.id);
                inputElement.setAttribute("onblur", "hypothesisFildReplace(hypothesisTemple,XTypeID" + data.id + ",'" + data.name + "')");
                inputElement.setAttribute("class", "form-control");
                inputElement.setAttribute("placeholder", "替换字段");
                divCol.appendChild(inputElement);

                divValid.appendChild(labelElement);
                divValid.appendChild(divCol);
                divElement.appendChild(divValid);
            }
            $("#viewH div").remove();
            viewH.appendChild(divElement);
        } else {
            $("#viewH div").remove();
            var divElement = document.createElement("div");
            divElement.setAttribute("class", "form-group");
            for (var j = 0; j < num * 4; j++) {
                if (j % 4==0){
                    console.log("取模"+j);
                }
                var data = result[j];
                var divValid = document.createElement("div");
                divValid.setAttribute("class", "x-valid");

                var labelElement = document.createElement("label");
                labelElement.setAttribute("class", "col-sm-1");
                labelElement.appendChild(document.createTextNode("" + data.name));

                var divCol = document.createElement("div");
                divCol.setAttribute("class", "col-sm-2");
                var inputElement = document.createElement("input");
                inputElement.setAttribute("type", "text");
                inputElement.setAttribute("name", "hypothesisType");
                inputElement.setAttribute("id", "XTypeID" + data.id);
                inputElement.setAttribute("onblur", "hypothesisFildReplace(hypothesisTemple,XTypeID" + data.id + ",'" + data.name + "')");
                inputElement.setAttribute("class", "form-control");
                inputElement.setAttribute("placeholder", "替换字段");
                divCol.appendChild(inputElement);

                divValid.appendChild(labelElement);
                divValid.appendChild(divCol);
                divElement.appendChild(divValid);
            }
            viewH.appendChild(divElement);
            var divElement2 = document.createElement("div");
            divElement2.setAttribute("class", "form-group");
            for (var i = num * 4; i < len; i++) {//剩余的 取模剩余的
                var data = result[i];
                var divValid = document.createElement("div");
                divValid.setAttribute("class", "x-valid");

                var labelElement = document.createElement("label");
                labelElement.setAttribute("class", "col-sm-1");
                labelElement.appendChild(document.createTextNode("" + data.name));

                var divCol = document.createElement("div");
                divCol.setAttribute("class", "col-sm-2");
                var inputElement = document.createElement("input");
                inputElement.setAttribute("type", "text");
                inputElement.setAttribute("name", "hypothesisType");
                inputElement.setAttribute("id", "XTypeID" + data.id);
                inputElement.setAttribute("onblur", "hypothesisFildReplace(hypothesisTemple,XTypeID" + data.id + ",'" + data.name + "')");
                inputElement.setAttribute("class", "form-control");
                inputElement.setAttribute("placeholder", "替换字段");
                divCol.appendChild(inputElement);

                divValid.appendChild(labelElement);
                divValid.appendChild(divCol);
                divElement.appendChild(divValid);
            }
            viewH.appendChild(divElement2);
        }
    }

    //字段替换
    function hypothesisFildReplace(id1, id2, name) {
        var value = $(id2).val();
        var regex = '/\{' + name + '\}/g';
        if (value != null && value != '') {
            var x1 = $(id1).val().replace(eval(regex), value);
            $(id1).val(x1);
        }
    }
</script>