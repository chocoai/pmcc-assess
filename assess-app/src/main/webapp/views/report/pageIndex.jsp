<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>



<body class="nav-md footer_fixed">
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="row">
                <div class="x_panel">
                    <div class="x_title">
                        <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                            ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                        </h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <%@include file="/views/share/main_pageHead.jsp" %>
                        <div class="row">
                            <div class="col-md-12 page-header">
                                <!-- start: DEFAULT TREE PANEL -->
                                <input type="hidden" id="currOrgId" value="${currOrgId}">
                                <input type="hidden" id="tableId" value="${tableId}">
                                <%--${tableColumns}--%>
                                <div class="panel panel-default">
                                    ${html}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/model_employee.jsp" %>
<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    var selNumber = [];
    var index = 1;
    var editInfo = undefined;


    //单选 由于要动态取相关数据，所以拿出来执行
    function fun5(index) {
        var whereObj = $("#where_" + index);
        whereObj.html("");
        var divTxt = $("#div_txt_" + index);
        divTxt.html("");
        //处理条件
        whereObj.append("<option value='='>等于</option>");
        //处理控件
        $.ajax({
            url: "${pageContext.request.contextPath}/reportPage/getSelectSource",
            data: {
                columnsName: $("#sel_" + index).val(),
                tableId: $("#tableId").val()
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    var html = "<select id='txt_" + index + "' class='form-control'>";
                    html += "<option value=''>全部</option>";
                    $.each(result.data, function (i, j) {
                        html += "<option value='" + j.key + "'>" + j.value + "</option>";
                    });
                    html += "</select>";
                    divTxt.append(html);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });

    }


    $(function () {
        selNumber.push(0);
        $.ajax({
            url: "${pageContext.request.contextPath}/reportPage/getTableInfoColumns",
            type: "get",
            data: {
                tableName: "${tableName}",
                tableId: "${tableId}"
            },
            dataType: "json",
            success: function (result) {
                if (result.ret) {
                    var cols = [];
                    $.each(result.data, function (j, item) {
                        cols.push({field: item.key, title: item.value});
                    });
                    if ("${isBaseData}" == "1") {
                        cols.push({
                            field: 'opt', title: '操作', formatter: function (value, row, index) {
                                var str = '<div class="btn-margin">';
                                str += '<a class="btn btn-xs btn-success btn-edit-object" href="javascript://;" onclick="editData(' + row.id + ');"><i class="fa fa-edit">编辑</i></a>';
                                str += '<a class="btn btn-xs btn-warning btn-delete-object" href="javascript://;" onclick="deleteDate(' + row.id + ');"><i class="fa fa-trash-o"></i>删除</a>';
                                str += '</div>';
                                return str;
                            }
                        });
                    }
                    TableInit("tb_formList",
                        "${pageContext.request.contextPath}/reportPage/getTableData", cols,
                        {
                            tableName: "${tableName}",
                            tableId: "${tableId}",
                            pageSearch: JSON.stringify(loadGridData())
                        }, {
                            showRefresh: false,                  //是否显示刷新按钮
                            search: false
                        });
                }
            },
            error: function (e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
        if ("${isBaseData}" == "1") {
            loadSel();
            loadOption();
        }
        changeTxt(0);
    });

    function loadOption() {

        $(".selcustomText").each(function () {
            var id = $(this).attr("id");
            $.ajax({
                url: "${pageContext.request.contextPath}/reportPage/getSelectSource",
                data: {
                    columnsName: id,
                    tableId: $("#tableId").val(),
                    typeSel: "input"
                },
                type: "get",
                dataType: "json",
                success: function (result) {
                    if (result.ret) {
                      var  html = "";
                        $.each(result.data, function (i, j) {
                            html += "<option value='" + j.key + "'>" + j.value + "</option>";
                        });
                        $("#"+id).html(html);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        });
    }

    function addSel() {
        var html = "<div class='form-group' id='div_" + index + "'>";
        html += "<div class='col-sm-2'>";
        html += "<select id='sel_" + index + "' class='form-control' onchange='changeTxt(" + index + ")'>";
        html += "${opionHtml}";
        html += "</select>";
        html += "</div>";
        html += "<div class='col-sm-1'>";
        html += "<select id='where_" + index + "' class='form-control'>";
        html += "<option value='like'>包含</option>";
        html += "<option value='='> 等于</option>";
        html += "</select>";
        html += "</div>";
        html += "<div class='col-sm-3'  id='div_txt_" + index + "'>";
        html += "<input type='text' id='txt_" + index + "' class='form-control'>";
        html += "</div>";
        html += "<div class='col-sm-1'>";
        html += "<a href='javascript:;'  class='btn btn-primary' onclick='reduceSel(" + index + ")' >-</a>";
        html += "</div>";
        html += "</div>";
        $("#div_" + selNumber[selNumber.length - 1]).after(html);
        selNumber.push(index);
        index++;
    }

    function reduceSel(index) {
        $("#div_" + index).remove();
        selNumber.splice($.inArray(index, selNumber), 1);
    }
    function changeTxt(i) {
        var columnsType = $("#sel_" + i).find("option:selected").attr("columnsType");
        var func = eval("fun" + columnsType);
        new func(i);
    }

    function loadGridData() {
        var selData = [];
        for (var i = 0; i < selNumber.length; i++) {
            var data = {
                search: $("#sel_" + selNumber[i]).val(),
                columnsType: $("#sel_" + i).find("option:selected").attr("columnsType"),
                where: $("#where_" + selNumber[i]).val(),
                searchValue: $("#txt_" + selNumber[i]).val(),
            }
            selData.push(data);
        }
        return selData
    }

    function searchGrid() {
        TableReload("tb_formList", "${pageContext.request.contextPath}/reportPage/getTableData",
            {
                tableName: "${tableName}",
                tableId: "${tableId}",
                pageSearch: JSON.stringify(loadGridData())
            }
            , null);
    }


    function editData(id) {
        $("#frm_model").clearAll();
        $("#frm_model").validate();
        editInfo = undefined;
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/reportPage/getTableDataById",
            data: {
                id: id,
                tableId:${tableId},
                tableName: "${tableName}"
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                $("#id").val(id);
                if (result.ret) {
                    editInfo = result.data;
                    $("#frm_model .customAttachment").each(function () {
                        var objIds = $(this).attr("id");
                        GetFileShows(objIds,
                            {
                                tableName: "${tableName}",
                                fieldsName: objIds,
                                tableId: id
                            }, 1);
                    });
                    $.each(result.data, function (i, item) {
                        var obj = $("#" + item.columnsName);
                        var className = obj.attr("class");
                        if (className) {
                            if (className.indexOf("chk_custom_form") >= 0) {
                                if (item.columnsValue) {
                                    obj.prop('checked', true);
                                }
                                else {
                                    obj.prop('checked', false);
                                }
                            }
                            else {
                                obj.val(item.columnsValue);
                            }
                        }
                        else {
                            obj.val(item.columnsValue);
                        }
                    });
                    $("#div_table_model").modal({backdrop: 'static', keyboard: false});
                }
                else {
                    Alert("导入表结构失败，失败原因：" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }
    function deleteDate(id) {
        Alert("删除后将不能恢复，是否确认删除" + "?", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/reportPage/deleteTableData",
                data: {
                    id: id,
                    tableName: "${tableName}"
                },
                type: "post",
                dataType: "json",
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success("删除成功");
                        searchGrid();
                    }
                    else {
                        Alert("删除数据失败，失败原因：" + result.errmsg, 1, null, null);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
                }
            });
        });
    }

    function addForm() {
        $("#div_table_model").modal({backdrop: 'static', keyboard: false});
        $("#frm_model").clearAll();
        $("#frm_model").validate();
        $("#id").val(0);
    }
    function save_custom_model() {
        if (!$("#frm_model").valid()) {
            return false;
        }
        Loading.progressShow();
        var data = formParams("frm_model");
        data["tableName"] = "${tableName}";
        data["tableId"] = "${tableId}";
        //保存数据
        $.ajax({
            type: "Post",
            url: "${pageContext.request.contextPath}/reportPage/saveTableData?_=" + Math.random(),
            data: {ds: JSON.stringify(data)},
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success("数据保存成功");
                    $("#div_table_model").modal('hide');
                    searchGrid();
                }
                else {
                    Alert("读取文件列表失败，失败原因：" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }


    function loadSel() {
        $(".selcustom").each(function () {
            $(this).bind('click', function () {
                var objIds = $(this).attr("id");
                //custom_radioproject_13_0_project_28
                //对应 自定义标识_数据字段名称_数据库字段名称序号_单选还是多选0为单选_类型（项目、部门、人员）_自定义表的编号
                var aId = objIds.split("_");
                var radio = aId[aId.length - 3] == "0" ? true : false;//是否单选
                var type = aId[aId.length - 2];

                var pre = $(this).prev();
                var inputId = $(pre).attr("id");
                /*var newAid = aId.slice(1, aId.length - 3);
                 var inputId =$("#"+objIds input) newAid.join("_");*/
                switch (type)//类型
                {
                    case "user": {
                        var thisSuperior = "";
                        if ($("#" + inputId).val() != "") {

                            var userName = $("#" + inputId + "Name").val();
                            var userAccount = $("#" + inputId).val();
                            var AuserName = userName.split(",");
                            var AuserAccount = userAccount.split(",");
                            for (var i = 0; i < AuserAccount.length; i++) {
                                thisSuperior += AuserName[i] + "_" + AuserAccount[i] + ",";
                            }
                            thisSuperior = thisSuperior.substring(0, thisSuperior.length - 1);
                        }
                        loadSelectEmployee(1, thisSuperior, radio, function (data) {
                            $("#" + inputId).val(data.account);
                            $("#" + inputId + "Name").val(data.name);
                        });
                        break;
                    }
                    case "dept": {
                        loadSelectDept(1, $("#" + inputId).val(), !radio, function (node) {
                            var nodeId = "";
                            var nodeText = "";
                            for (var i = 0; i < node.length; i++) {
                                nodeId += node[i].id + ",";
                                nodeText += node[i].text + ",";
                            }
                            $("#" + inputId).val(nodeId.substring(0, nodeId.length - 1));
                            $("#" + inputId + "Name").val(nodeText.substring(0, nodeText.length - 1));
                        })
                        break;
                    }

                }
            });
        });
    }

</script>

</html>