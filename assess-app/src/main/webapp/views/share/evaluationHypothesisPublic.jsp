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
        <c:forEach items="${hypothesisList}" var="data">
            <div class="x_content">
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            请选择假设
                        </label>
                        <div class="col-sm-11">
                            <select required="required" name="DataID" class="form-control" id="hypothesisSelectID${data.id}">
                                <option name="DataID" value="${data.id}">${data.name}</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-1 control-label">
                            模板数据
                        </label>
                        <div class="col-sm-11">
                            <textarea required="required" id="hypothesisTemple${data.id}" placeholder="假设模板" class="form-control" name="Content">

                            </textarea>
                        </div>
                    </div>
                </div>

                <div id="viewH${data.id}" class="form-group">

                </div>
            </div>

        </c:forEach>


    </form>
</div>
<script type="text/javascript">
    (function () {
        var ids = "";
        var itemsX = new Array();
        var j = 0;
        <c:forEach items="${hypothesisList}" var="data">
        (function () {
            var selectID = "#hypothesisSelectID" + '${data.id}';
            var selected = $(selectID+" option:selected").val();
            var data = {};
            data.id = selected;
            $.ajax({
                url: "${pageContext.request.contextPath}/evaluationHypothesis/get",
                type: "GET",
                dataType: "json",
                data: data,
                async:false,
                success: function (result) {
                    $("#hypothesisTemple"+"${data.id}").val(result.template);
                    ids += selected +",";
                    itemsX[j++] = "${data.id}";
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }());
        </c:forEach>
        ids = ids.substring(0,ids.length-1);
        var dataX = {};
        dataX.id = ids;
        $.ajax({
            url: "${pageContext.request.contextPath}/evaluationHypothesisNG/listFieldsS",
            type: "POST",
            dataType: "json",
            data: dataX,
            async:true,
            success: function (result) {
                for (var k = 0;k < result.length;k++){
                    writeHypothesisField(result[k],itemsX[k]);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }());


    //字段写入
    function writeHypothesisField(result,id) {
        var viewH = document.getElementById("viewH"+id);
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
                labelElement.setAttribute("class", "col-sm-1 control-label");
                labelElement.appendChild(document.createTextNode("" + data.name));

                var divCol = document.createElement("div");
                divCol.setAttribute("class", "col-sm-2");
                var inputElement = document.createElement("input");
                inputElement.setAttribute("type", "text");
                inputElement.setAttribute("id", "XTypeID" + data.id+""+id);
                inputElement.setAttribute("onblur", "hypothesisFildReplace('hypothesisTemple"+id+"','XTypeID" + data.id +""+id+ "','" + data.name + "')");
                inputElement.setAttribute("class", "form-control");
                inputElement.setAttribute("placeholder", "替换字段");
                divCol.appendChild(inputElement);

                divValid.appendChild(labelElement);
                divValid.appendChild(divCol);
                divElement.appendChild(divValid);
            }
            viewH.parentNode.insertBefore(divElement,viewH);
        }else {
            for (var j = 0; j < num * 4; j++){
                if (j % 4 == 0){
                    var data = result[j];
                    var divElement = document.createElement("div");
                    divElement.setAttribute("class", "form-group");

                    var divValid = document.createElement("div");
                    divValid.setAttribute("class", "x-valid");

                    var labelElement = document.createElement("label");
                    labelElement.setAttribute("class", "col-sm-1 control-label");
                    labelElement.appendChild(document.createTextNode("" + data.name));

                    var divCol = document.createElement("div");
                    divCol.setAttribute("class", "col-sm-2");
                    var inputElement = document.createElement("input");
                    inputElement.setAttribute("type", "text");
                    inputElement.setAttribute("id", "XTypeID" + data.id+""+id);
                    inputElement.setAttribute("onblur", "hypothesisFildReplace('hypothesisTemple"+id+"','XTypeID" + data.id +""+id+ "','" + data.name + "')");
                    inputElement.setAttribute("class", "form-control");
                    inputElement.setAttribute("placeholder", "替换字段");
                    divCol.appendChild(inputElement);

                    divValid.appendChild(labelElement);
                    divValid.appendChild(divCol);
                    divElement.appendChild(divValid);

                    viewH.parentNode.insertBefore(divElement,viewH);
                }
            }
            for (var i = num * 4; i < len; i++){//剩余的 取模剩余的
                var data = result[i];
                console.log(data);
                var divElement = document.createElement("div");
                divElement.setAttribute("class", "form-group");

                var divValid = document.createElement("div");
                divValid.setAttribute("class", "x-valid");

                var labelElement = document.createElement("label");
                labelElement.setAttribute("class", "col-sm-1 control-label");
                labelElement.appendChild(document.createTextNode("" + data.name));

                var divCol = document.createElement("div");
                divCol.setAttribute("class", "col-sm-2");
                var inputElement = document.createElement("input");
                inputElement.setAttribute("type", "text");
                inputElement.setAttribute("id", "XTypeID" + data.id+""+id);
                inputElement.setAttribute("onblur", "hypothesisFildReplace('hypothesisTemple"+id+"','XTypeID" + data.id +""+id+ "','" + data.name + "')");
                inputElement.setAttribute("class", "form-control");
                inputElement.setAttribute("placeholder", "替换字段");
                divCol.appendChild(inputElement);

                divValid.appendChild(labelElement);
                divValid.appendChild(divCol);
                divElement.appendChild(divValid);

                viewH.parentNode.insertBefore(divElement,viewH);
            }
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