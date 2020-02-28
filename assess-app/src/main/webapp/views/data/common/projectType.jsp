<%--
  Created by IntelliJ IDEA.
  User: wangpc
  Date: 2020/2/28
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="application/javascript">
    var num = 0;

    //类型
    function getType(number, typeValue) {
        if (!number && number != 0) {
            number = num
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/baseProjectClassify/getProjectClassifyListByFieldName",
            type: "post",
            dataType: "json",
            data: {fieldName: "single"},//字段为固定 请参照BaseProjectClassifyController中....
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (data.length >= 1) {
                        var option = "<option value=''>请选择</option>";
                        for (var i = 0; i < data.length; i++) {
                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                        }
                        $("#frm").find('select.type' + number).html(option);
                        if (typeValue) {
                            $("#frm").find('select.type' + number).val([typeValue]).trigger('change');
                        }
                    }
                } else {
                    notifyWarning("获取类型失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                notifyWarning("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    //类别
    function getCategory(number, categoryValue) {
        if (!number && number != 0) {
            number = num;
        }
        //监听change 事件 并做出......
        $("#frm" + " .type" + number).change(function () {
            var pid = $("#frm" + " .type" + number).eq(1).val();
            if (!pid) {
                return false;
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/baseProjectClassify/getCacheProjectClassifyListByPid",
                type: "post",
                dataType: "json",
                data: {pid: pid},
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (data.length >= 1) {
                            var option = "<option value=''>请选择</option>";
                            for (var i = 0; i < data.length; i++) {
                                option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                            }
                            $("#frm").find('select.category' + number).html(option);
                            if (categoryValue) {
                                $("#frm").find('select.category' + number).val([categoryValue]).trigger('change');
                            }
                        }
                    } else {
                        notifyWarning("获取类别失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    notifyWarning("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
    }

    function appendHTML(typeValue, categoryValue) {
        num++;
        var projectType = "type" + num;
        var projectCategory = "category" + num;
        var html = createHTML(projectType, projectCategory);
        $("#frm").find(".system").append(html);
        $("#frm").find("." + projectType).select2();
        $("#frm").find("." + projectCategory).select2();
        getType(null, typeValue);
        getCategory(null, categoryValue);
    }

    function createHTML(projectType, projectCategory) {
        var html = '<div class="row form-group">';
        html += '<div class="col-md-12">';
        html += '<div class="form-inline x-valid">';

        html += '<label class="col-sm-2 col-form-label">项目类型<span class="symbol required"></span></label>';
        html += '<div class="col-sm-3 ">';
        html += "<select required name='type' id='" + projectType + "' onchange='typeChange(this)' class='form-control input-full search-select select2 " + projectType + "'>";
        html += "<option selected='selected' value=''>请选择</option>";
        html += "</select>";
        html += "</div>";

        html += '<label class="col-sm-2 col-form-label">项目类别<span class="symbol required"></span></label>';
        html += '<div class="col-sm-3 ">';
        html += "<select required name='category' id='" + projectCategory + "'  class='form-control input-full search-select select2 " + projectCategory + "'>";
        html += "<option selected='selected' value=''>请先选择类型</option>";
        html += "</select>";
        html += "</div>";

        html += '<div class="col-sm-1">';
        html += "<button type='button' class='btn btn-warning btn-sm' onclick='cleanHTMLData(this)'><i class=\"fa fa-minus\"></i></button>";
        html += "</div>";

        html += "</div>";
        html += "</div>";
        html += "</div>";
        return html;
    }

    function cleanHTMLData(this_) {
        $(this_).closest('.form-group').remove();
    }

    function typeChange(this_) {
        var str = $(this_).attr("id");
        var number = str.substr(str.length - 1, 1);
        getCategory(number);
    }

    function reload(typeValue, categoryValue) {
        $("#frm").find(".system").empty();
        if(!typeValue) {
            return false;
        }
        var html = '<div class="row form-group">';
        html += '<div class="col-md-12">';
        html += '<div class="form-inline x-valid">';

        html += '<label class="col-sm-2 col-form-label">项目类型<span class="symbol required"></span></label>';
        html += '<div class="col-sm-3">';
        html += "<select required name='type' id='type0' onchange='typeChange(this)' class='form-control input-full search-select select2 type0'>";
        html += "<option selected='selected' value=''>请选择</option>";
        html += "</select>";
        html += "</div>";

        html += '<label class="col-sm-2 col-form-label">项目类别<span class="symbol required"></span></label>';
        html += '<div class="col-sm-3">';
        html += "<select required name='category' class='form-control input-full search-select select2 category0'>";
        html += "<option selected='selected' value=''>请先选择类型</option>";
        html += "</select>";
        html += "</div>";

        html += '<div class="col-sm-1">';
        html += "<button type='button' class='btn btn-warning btn-sm' onclick='cleanHTMLData(this)'><i class=\"fa fa-minus\"></i></button>";
        html += "</div>";

        html += "</div>";
        html += "</div>";
        html += "</div>";
        $("#frm").find(".system").append(html);
        getType(0, typeValue);
        getCategory(0, categoryValue);
        $("#frm").find(".type0").select2();
        $("#frm").find(".category0").select2();
    }
</script>
