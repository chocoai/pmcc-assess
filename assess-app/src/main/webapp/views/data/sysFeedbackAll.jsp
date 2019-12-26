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
                                    所属系统
                                </label>
                                <div class="col-sm-3">
                                    <select class="form-control" id="querySystemType">
                                        <option value="">--请选择--</option>
                                        <c:if test="${not empty systemTypes}">
                                            <c:forEach items="${systemTypes}" var="item">
                                                <option value="${item.id}">${item.name}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    问题
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" id="queryQuestionTitle" class="form-control">
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    反馈人
                                </label>
                                <div class="col-sm-3">
                                    <input type="hidden" id="queryFeedbackPerson">
                                    <input type="text" data-rule-maxlength="50" readonly
                                           placeholder="选择人员" onclick="sysFeedback.prototype.personSelect(this)"
                                           id="queryFeedbackPersonName" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <div>
                                <label class="col-sm-1 control-label">
                                    状态
                                </label>
                                <div class="col-sm-3">
                                    <select class="form-control" id="queryStatus">
                                        <option value="">--请选择--</option>
                                        <option value="0">新添加</option>
                                        <option value="1">待处理</option>
                                        <option value="2">已处理</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary"
                                        onclick="sysFeedback.prototype.loadDataDicList()">
                                    查询
                                </button>
                                <button type="button" class="btn btn-success" onclick="$('#frmQuery').clearAll()">
                                    重置
                                </button>
                                <button type="button" class="btn btn-success"
                                        onclick="sysFeedback.prototype.showModel()"
                                        data-toggle="modal" href="#divBox"> 新增
                                </button>
                            </div>
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
<script type="text/javascript">
    $(function () {
        sysFeedback.prototype.loadDataDicList();
    });
    var ue = UE.getEditor('deatilDescription', {
        toolbars: [
            ['source','autotypeset','bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
        ],
        zIndex: 11009,
        initialFrameHeight: 120,
        elementPathEnabled: false,//是否启用元素路径，默认是true显示
        wordCount: false, //是否开启字数统计
        autoHeightEnabled: false,
        autoFloatEnabled: true
    });
    ue.ready(function() {
        //不可编辑
        ue.setDisabled();
    });
    var ue2 = UE.getEditor('disposeScheme', {
        toolbars: [
            ['source','autotypeset','bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
        ],
        zIndex: 11009,
        initialFrameHeight: 120,
        elementPathEnabled: false,//是否启用元素路径，默认是true显示
        wordCount: false, //是否开启字数统计
        autoHeightEnabled: false,
        autoFloatEnabled: true
    });
    var sysFeedback = function () {

    };
    sysFeedback.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'systemTypeName', title: '所属系统'});
            cols.push({field: 'urgencyLevelName', title: '紧急程度'});
            cols.push({field: 'statusName', title: '状态'});
            cols.push({field: 'questionTitle', title: '存在问题'});
            cols.push({field: 'feedbackPersonName', title: '反馈人'});
            cols.push({field: 'deatilDescription', title: '问题详情'});
            cols.push({field: 'disposeScheme', title: '处理方案'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="sysFeedback.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
/*
                     str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="sysFeedback.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
*/
                    str += '</div>';
                    return str;
                }
            });
            $("#" + sysFeedback.prototype.config().table).bootstrapTable('destroy');
            TableInit(sysFeedback.prototype.config().table, "${pageContext.request.contextPath}/sysFeedback/getAllSysFeedbackList", cols, {
                systemType: $("#querySystemType").val(),
                questionTitle: $("#queryQuestionTitle").val(),
                feedbackPerson: $("#queryFeedbackPerson").val(),
                status: $("#queryStatus").val()
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
                    url: "${pageContext.request.contextPath}/sysFeedback/deleteSysFeedbackById",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('删除成功');
                            sysFeedback.prototype.loadDataDicList();
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
            $("#" + sysFeedback.prototype.config().frm).clearAll();
            $('#' + sysFeedback.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + sysFeedback.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(sysFeedback.prototype.config().frm);
            data.deatilDescription = ue.getContent();
            data.disposeScheme = ue2.getContent();
            $.ajax({
                url: "${pageContext.request.contextPath}/sysFeedback/saveAndUpdateSysFeedback",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + sysFeedback.prototype.config().box).modal('hide');
                        sysFeedback.prototype.loadDataDicList();
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
                url: "${pageContext.request.contextPath}/sysFeedback/getSysFeedbackById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + sysFeedback.prototype.config().frm).clearAll();
                        $("#" + sysFeedback.prototype.config().frm).initForm(result.data);
                        var content = result.data.deatilDescription;
                        var disposeScheme = result.data.disposeScheme;
                        setTimeout(function () {
                            ue.setContent(content, false);
                            ue2.setContent(disposeScheme, false);
                        }, 500);
                        $('#' + sysFeedback.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        //选择人员
        personSelect:function(_this) {
        erpEmployee.select({
            onSelected: function (data) {
                if (data.account) {
                    $(_this).val(data.name);
                    $(_this).prev().val(data.account);
                }
                else {
                    $(_this).val("");
                    $(_this).prev().val("");
                }
            }
        });
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
                <h3 class="modal-title">我的反馈</h3>
            </div>
            <form id="frmFather" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            所属系统
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" name="systemTypeName" readonly class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            紧急程度
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" name="urgencyLevelName" readonly class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            反馈人
                                        </label>
                                        <div class="col-sm-3">
                                            <input type="text" name="feedbackPersonName" readonly class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            存在问题
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" name="questionTitle"  class="form-control" readonly>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            问题详情描述
                                        </label>
                                        <div class="col-sm-10">
                                            <div style="width:99%;height:200px;" id="deatilDescription"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            状态<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-3">
                                            <select class="form-control" name="status" required>
                                                <option value="">--请选择--</option>
                                                <option value="0">新添加</option>
                                                <option value="1">待处理</option>
                                                <option value="2">已处理</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-1 control-label">
                                            处理方案
                                        </label>
                                        <div class="col-sm-10">
                                            <div style="width:99%;height:200px;" id="disposeScheme"></div>
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
                    <button type="button" class="btn btn-primary" onclick="sysFeedback.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
