<%--
  Created by IntelliJ IDEA.
  User: wangpc
  Date: 2020/2/28
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    var projectTypeObj = {};

    projectTypeObj.getTemplateHtml = function () {
        var html = '<div class="row form-group">';
        html += '<div class="col-md-12">';
        html += '<div class="form-inline x-valid">';

        html += '<label class="col-sm-2 col-form-label">项目类型<span class="symbol required"></span></label>';
        html += '<div class="col-sm-3 ">';
        html += "<select required name='type' onchange='projectTypeObj.typeChange(this)' class='form-control input-full'>";
        html += "</select>";
        html += "</div>";

        html += '<label class="col-sm-2 col-form-label">项目类别<span class="symbol required"></span></label>';
        html += '<div class="col-sm-3 ">';
        html += "<select required name='category' class='form-control input-full'>";
        html += "</select>";
        html += "</div>";

        html += '<div class="col-sm-1">';
        html += "<button type='button' class='btn btn-warning btn-sm' onclick='$(this).closest(\".form-group\").remove();'><i class=\"fa fa-minus\"></i></button>";
        html += "</div>";

        html += "</div>";
        html += "</div>";
        html += "</div>";
        return html;
    }

    projectTypeObj.addTypeHtml = function (type, category) {
        var systemObj = $("#frm").find('.system');
        systemObj.append(projectTypeObj.getTemplateHtml());
        var typeObj = systemObj.find('.form-group').last().find('[name=\'type\']');
        projectTypeObj.createTypeOptionHtml(typeObj, type, category);
    }

    projectTypeObj.editTypeHtml = function (strType, strCategory) {
        $("#frm").find('.system').empty();
        if (strType && strCategory) {
            var types = strType.substring(1, strType.length - 1).split(',');
            var categorys = strCategory.substring(1, strCategory.length - 1).split(',');
            $.each(types, function (i, item) {
                projectTypeObj.addTypeHtml(item,categorys[i]);
            })
        }
    }

    //创建类型html
    projectTypeObj.createTypeOptionHtml = function (_this, type, category) {
        $.ajax({
            url: "${pageContext.request.contextPath}/baseProjectClassify/getProjectClassifyListByFieldName",
            type: "post",
            dataType: "json",
            data: {fieldName: "single"},//字段为固定
            success: function (result) {
                if (result.ret && result.data) {
                    var html = '';
                    if (result.data.length >= 1) {
                        $.each(result.data, function (i, item) {
                            if (type && type == item.id) {
                                html += "<option selected='selected' value='" + item.id + "'>" + item.name + "</option>";
                            } else {
                                html += "<option value='" + item.id + "'>" + item.name + "</option>";
                            }
                        })
                        $(_this).append(html);
                        projectTypeObj.typeChange(_this, category);
                    }
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    }

    //类型切换
    projectTypeObj.typeChange = function (_this, category) {
        $.ajax({
            url: "${pageContext.request.contextPath}/baseProjectClassify/getCacheProjectClassifyListByPid",
            type: "post",
            dataType: "json",
            data: {pid: $(_this).val()},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (data.length >= 1) {
                        var option = "";
                        for (var i = 0; i < data.length; i++) {
                            if (category && category == data[i].id) {
                                option += "<option selected='selected' value='" + data[i].id + "'>" + data[i].name + "</option>";
                            } else {
                                option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                            }
                        }
                        $(_this).closest('.form-group').find('[name=category]').append(option);
                    }
                } else {
                    AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }
</script>
