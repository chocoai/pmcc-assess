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
                            <input type="hidden" id="hypothesisTempleV${data.id}">
                            <textarea required="required" id="hypothesisTemple${data.id}" placeholder="假设模板" class="form-control" name="Content">

                            </textarea>
                        </div>
                    </div>
                </div>

                <c:choose>
                    <c:when test="${data.size <= 4}">
                        <div class="form-group">
                            <c:forEach items="${data.fieldVos}" var="item" varStatus="sta">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label"> ${item.name} </label>
                                    <div class="col-sm-2">
                                        <input class="form-control" type="text" placeholder="替换字段"
                                               id="hypothesisFieldID${data.id}${item.id}" onblur="hypothesisFildReplace('hypothesisTemple${data.id}','hypothesisTempleV${data.id}','hypothesisFieldID${data.id}${item.id}','${item.name}')">
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${data.fieldVos}" var="item"  varStatus="status">
                            <c:if test="${status.index % 4 == 0}">
                                <div class="form-group">
                                    <c:forEach items="${data.fieldVos}" var="item" begin="${status.index}" end="${status.index+3}">
                                        <div class="x-valid">
                                            <label class="col-sm-1 control-label">${status.index} ${item.name} </label>
                                            <div class="col-sm-2">
                                                <input class="form-control" type="text" placeholder="替换字段"
                                                       id="hypothesisFieldID${data.id}${item.id}" onblur="hypothesisFildReplace('hypothesisTemple${data.id}','hypothesisTempleV${data.id}','hypothesisFieldID${data.id}${item.id}','${item.name}')">
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>

        </c:forEach>


    </form>
</div>
<script type="text/javascript">
    (function () {
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
                async:true,
                success: function (result) {
                    $("#hypothesisTemple"+"${data.id}").val(result.template);
                    $("#hypothesisTempleV"+"${data.id}").val(result.template);
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }());
        </c:forEach>
    }());

    //字段替换
    function hypothesisFildReplace(id,id1, id2, name) {
        var value = $("#"+id2).val();
        var regex = '/\{' + name + '\}/g';
        if (value != null && value != '') {
            var x1 = $("#"+id1).val().replace(eval(regex), value);
            $("#"+id).val(x1);
        }
    }
</script>