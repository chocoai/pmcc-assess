<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/7/19
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frmQuery" class="form-horizontal">
                        <div class="form-group ">
                            <div>
                                <label class="col-sm-1 control-label">
                                    资质类型
                                </label>
                                <div class="col-sm-3">
                                    <select class="form-control" required id="queryType">
                                        <option value="">--请选择--</option>
                                        <c:if test="${not empty qualificationTypes}">
                                            <c:forEach items="${qualificationTypes}" var="item">
                                                <option value="${item.key}">${item.value}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary"
                                        onclick="dataQualification.prototype.loadDataDicList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success"
                                        onclick="dataQualification.prototype.showModel()"
                                        data-toggle="modal" href="#divBox"> 新增
                                </button>
                            </div>

                            <%--<div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">--%>
                                <%--<button type="button" class="btn btn-default"--%>
                                        <%--onclick="toolMapHandleFun.loadMap({drawState:'rectangle',callback:function(item) {--%>
                                          <%--console.info(item) ;--%>
                                        <%--}});"><i--%>
                                        <%--class="fa fa-map-marker"></i>map    --%>
                                <%--</button>--%>
                            <%--</div>--%>

                        </div>

                    </form>
                    <table class="table table-bordered" id="tb_FatherList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<%@include file="/views/share/main_footer.jsp" %>
<%@include file="/views/project/tool/toolMapHandleView.jsp" %>
<script type="text/javascript">

    /**
     * 获取资质
     * @param userAccount
     * @param qualificationType
     * @param callback
     * @author zch
     */
    function getAdPersonalIdentityDto(userAccount, qualificationType, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/dataQualification/getAdPersonalIdentityDto",
            data: {
                userAccount: userAccount,
                qualificationType: qualificationType
            },
            type: "get",
            dataType: "json",
            success: function (result) {
                if (result.ret && result.data) {
                    callback(result.data);
                }
            },
            error: function (result) {
                alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    }

    $(function () {
        dataQualification.prototype.loadDataDicList();
    });
    var dataQualification = function () {

    };
    dataQualification.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'qualificationTypeName', title: '资质类型'});
            cols.push({field: 'userAccountName', title: '人员'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="dataQualification.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataQualification.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#" + dataQualification.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataQualification.prototype.config().table, "${pageContext.request.contextPath}/dataQualification/getDataQualificationList", cols, {
                type: $("#queryType").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData: function (id) {
            Alert("确认删除!", 2, null, function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/dataQualification/deleteDataQualificationById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            dataQualification.prototype.loadDataDicList();
                        }
                        else {
                            Alert("保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        },
        showModel: function () {
            $("#" + dataQualification.prototype.config().frm).clearAll();
            $('#' + dataQualification.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + dataQualification.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(dataQualification.prototype.config().frm);
            $.ajax({
                url: "${pageContext.request.contextPath}/dataQualification/saveAndUpdateDataQualification",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + dataQualification.prototype.config().box).modal('hide');
                        dataQualification.prototype.loadDataDicList();
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataQualification/getDataQualificationById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + dataQualification.prototype.config().frm).clearAll();
                        $("#" + dataQualification.prototype.config().frm).initForm(result.data);
                        $('#' + dataQualification.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        //选择人员
        personSelect: function (this_) {
            var frm = $(this_).closest("form");
            var item = formParams(frm.attr("id"));
            if (item.qualificationType) {
                erpEmployee.select({
                    multi: false,
                    currOrgId: 1,
                    userName: $("#userAccountName").val(),
                    userAccount: $("#userAccount").val(),
                    onSelected: function (data) {
                        getAdPersonalIdentityDto(data.account, item.qualificationType, function (item) {
                            if (item.length >= 1) {
                                $("#userAccount").val(data.account);
                                $("#userAccountName").val(data.name);
                            } else {
                                $("#userAccount").val("");
                                $("#userAccountName").val("");
                                Alert("该人员未有评估师资格!");
                            }
                        });
                    }
                });
            } else {
                Alert("资格类型必须先选择!");
            }
        }

    }
</script>
<div id="divBoxFather" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">资质管理</h3>
            </div>
            <form id="frmFather" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            资质类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <select class="form-control" required name="qualificationType">
                                                <option value="">--请选择--</option>
                                                <c:if test="${not empty qualificationTypes}">
                                                    <c:forEach items="${qualificationTypes}" var="item">
                                                        <option value="${item.key}">${item.value}</option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            选择人员
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="hidden" id="userAccount" name="userAccount">
                                            <input type="text" data-rule-maxlength="50" readonly
                                                   placeholder="选择人员"
                                                   onclick="dataQualification.prototype.personSelect(this)"
                                                   id="userAccountName" name="userAccountName" class="form-control">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="dataQualification.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
