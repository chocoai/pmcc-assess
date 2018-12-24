<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/7/19
  Time: 14:18
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
                                    名称
                                </label>
                                <div class="col-sm-2">
                                    <input type="text" data-rule-maxlength="50"
                                           placeholder="名称" id="queryName" name="queryName"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary" onclick="dataDeveloper.prototype.loadDataDicList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success" onclick="dataDeveloper.prototype.showModel()"
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
        dataDeveloper.prototype.loadDataDicList();
    });



    var dataDeveloper = function () {

    };
    dataDeveloper.prototype = {
        config:function () {
            var data = {};
            data.table = "tb_FatherList" ;
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        loadDataDicList:function () {
            var cols = [];
            cols.push({field: 'name', title: '名称'});
            cols.push({field: 'companyNature', title: '公司性质'});
            cols.push({field: 'socialPrestige', title: '社会信誉'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="dataDeveloper.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataDeveloper.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#"+dataDeveloper.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataDeveloper.prototype.config().table, "${pageContext.request.contextPath}/dataDeveloper/getDataDeveloperList", cols, {
                name:$("#queryName").val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        removeData:function (id) {
           Alert("确认删除!",2,null,function () {
               $.ajax({
                   url:"${pageContext.request.contextPath}/dataDeveloper/deleteDataDeveloperById",
                   type: "post",
                   dataType: "json",
                   data: {id:id},
                   success: function (result) {
                       if (result.ret) {
                           toastr.success('删除成功');
                           dataDeveloper.prototype.loadDataDicList();
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
        showModel:function () {
            $("#"+dataDeveloper.prototype.config().frm).clearAll();
            $('#'+dataDeveloper.prototype.config().box).modal("show");
        },
        saveData:function () {
            if (!$("#"+dataDeveloper.prototype.config().frm).valid()){
                return false;
            }
            var data = formParams(dataDeveloper.prototype.config().frm);
            $.ajax({
                url:"${pageContext.request.contextPath}/dataDeveloper/saveAndUpdateDataDeveloper",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#'+dataDeveloper.prototype.config().box).modal('hide');
                        dataDeveloper.prototype.loadDataDicList();
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
        getAndInit:function (id) {
            $.ajax({
                url:"${pageContext.request.contextPath}/dataDeveloper/getDataDeveloperById",
                type: "get",
                dataType: "json",
                data: {id:id},
                success: function (result) {
                    if (result.ret) {
                        $("#"+dataDeveloper.prototype.config().frm).clearAll();
                        $("#" + dataDeveloper.prototype.config().frm).initForm(result.data);
                        $('#'+dataDeveloper.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
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
                <h3 class="modal-title">开发商</h3>
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
                                            名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="名称" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            公司性质
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="companyNature"
                                                   placeholder="公司性质" >
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            社会信誉
                                        </label>
                                        <div class="col-sm-10">
                                            <textarea class="form-control" name="socialPrestige" placeholder="社会信誉" >
                                            </textarea>
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
                    <button type="button" class="btn btn-primary" onclick="dataDeveloper.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
