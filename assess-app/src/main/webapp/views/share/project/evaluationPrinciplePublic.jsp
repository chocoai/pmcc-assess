<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/5/24
  Time: 11:32
  To change this template use File | Settings | File Templates.
  评估原则 公共页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <h2>评估原则</h2>
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <div class="clearfix"></div>
    </div>
    <form id="frm_task_evaluationPrincipleTemple" class="form-horizontal">
        <div class="x_content" id="principleContent">

        </div>
    </form>
</div>
<!--动态字段-->
<script type="text/html" id="dynamicPrincipleFieldHtml">
    <div class="x-valid">
        <label class="col-sm-1 control-label">
            {name}
        </label>
        <div class="col-sm-3">
            <input type="text" class="form-control" data-name="{name}" onkeyup="{functionName}(this);">
        </div>
    </div>
</script>
<script type="text/javascript">
    var principleFun = {};
    principleFun.start = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/evaluationPrinciple/lists",
            type: "GET",
            dataType: "json",
            success: function (result) {
                principleFun.writeList(result);
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };
    principleFun.data = function () {
        var arr = new Array();
        arr[0] = "templatePrinciple";
        arr[1] = "templatePrincipleV";
        return arr;
    };

    //模板内容替换
    principleFun.fieldReplace = function (_this) {

    };
    principleFun.writeList = function (result) {
        var len = result.length;
        var content = $("#principleContent");
        for (var i = 0; i < len; i++) {
            var html = '<div class="well">'
            html += "<div class='form-group'>";
            html += "<div class='x-valid'>";
            html += "<label class='col-sm-1 control-label'>";
            html += result[i].name;
            html += "</label>";
            html += "<div class='col-sm-11'>";
            html += "<input type='hidden' name='dataID'" + "value=" + result[i].id + ">";
            html += "<input type='hidden' value='" + result[i].template + "'id='templatePrincipleV" + result[i].id + "'>";
            html += "<textarea placeholder='原则模板' class='form-control' name='content' required='required'" + "id=templatePrinciple" + result[i].id + ">";
            html += result[i].template;
            html += "</textarea>";
            html += "</div>";
            html += "</div>";
            html += "</div>";
            /*-------------分割一下----------------*/
            html += "<div class='content-field'" + "id=principleField" + result[i].id + ">";
            html += "</div>";
            html += '</div>';
            content.append(html);
        }
        principleFun.writeField(result);
    };
    principleFun.writeField = function (result) {
        var len = result.length;
        for (var i = 0; i < len; i++) {
            var template = $("#templatePrinciple" + result[i].id);
            var fieldArray = AssessCommon.extractField(template.val());
            var field = $("#principleField" + result[i].id);
            if (fieldArray&&fieldArray.length > 0) {
                var resultHtml = "<div class='form-group'>";
                for (var j = 0; j < fieldArray.length; j++) {
                    if (j > 0 && j % 3 == 0) {
                        resultHtml += '</div><div class="form-group">';
                    }
                    var templateHtml = $("#dynamicPrincipleFieldHtml").html();
                    templateHtml = templateHtml.replace(/{name}/g, fieldArray[j]).replace(/{functionName}/, "principleFun.fieldReplace").replace(/{id}/, result[i].id);
                    resultHtml += templateHtml;
                }
                resultHtml += "</div>";
                field.append(resultHtml);
            }


        }
    };
    principleFun.start();


</script>