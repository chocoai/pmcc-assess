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
    <div class="x_title">
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
            <input type="text" class="form-control" data-name="{name}" data-value="{id}" onkeyup="{functionName}(this);">
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
        arr[0] = "templatePrinciple" ;
        arr[1] = "templatePrincipleV" ;
        return arr;
    };
    principleFun.fieldReplace = function (dd) {
        var arr = principleFun.data();
        var name = $(dd).attr("data-name");
        var id = $(dd).attr("data-value");
        var template = $("#"+arr[0]+id).val();
        var regex = '/\{' + name + '\}/g';
        if (template != ''){
            var template = template.replace(eval(regex), $(dd).val());
            $("#"+arr[1]+id).val(template);
        }
    };
    principleFun.writeList = function (result) {
        var len = result.length;
        var content = $("#principleContent");
        for (var i = 0;i< len ;i++){
            var groupA = "<div class='form-group'>" ;
            groupA += "<div class='x-valid'>" ;

            groupA += "<label class='col-sm-1 control-label'>" ;
            groupA += "原则名称";
            groupA += "</label>" ;
            groupA += "<div class='col-sm-11 control-label'>" ;
            groupA += result[i].name;
            groupA += "</div>";

            groupA += "</div>" ;
            groupA += "</div>";
            /*-------------分割一下----------------*/
            var groupB = "<div class='form-group'>" ;
            groupB += "<div class='x-valid'>" ;

            groupB += "<label class='col-sm-1 control-label'>" ;
            groupB += "模板数据" ;
            groupB += "</label>" ;
            groupB += "<div class='col-sm-11'>" ;
            groupB += "<input type='hidden' value='"+result[i].template +"'id='templatePrinciple"+result[i].id +"'>";
            groupB += "<textarea placeholder='原则模板' class='form-control' name='content' required='required'"+ "id=templatePrincipleV"+result[i].id +">" ;
            groupB += result[i].template;
            groupB += "</textarea>" ;
            groupB += "</div>" ;

            groupB += "</div>" ;
            groupB += "</div>" ;
            /*-------------分割一下----------------*/
            var groupC = "<div class='content-field'" + "id=principleField"+result[i].id +">";
            groupC += "</div>" ;
            content.append(groupA);
            content.append(groupB);
            content.append(groupC);
        }
        principleFun.writeField(result);
    };
    principleFun.writeField = function (result) {
        var len = result.length;
        for (var i = 0;i< len ;i++){
            var template = $("#templatePrinciple"+result[i].id);
            var fieldArray = AssessCommon.extractField(template.val());
            var field = $("#principleField"+result[i].id);
            if (fieldArray.length > 0 && fieldArray!=null) {
                var resultHtml = "<div class='form-group'>" ;
                for (var j = 0;j<fieldArray.length ;j ++){
                    if (j > 0 && j % 3 == 0) {
                        resultHtml += '</div><div class="form-group">';
                    }
                    var templateHtml = $("#dynamicPrincipleFieldHtml").html();
                    templateHtml = templateHtml.replace(/{name}/g, fieldArray[j]).replace(/{functionName}/, "principleFun.fieldReplace").replace(/{id}/, result[i].id);
                    resultHtml += templateHtml;
                }
                resultHtml += "</div>" ;
                field.append(resultHtml);
            }


        }
    };
    principleFun.start();


</script>