
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
                                <button type="button" class="btn btn-primary" onclick="dataProperty.prototype.loadDataDicList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success" onclick="dataProperty.prototype.showModel()"
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
<%@include file="/views/data/dataPropertyServiceItem.jsp" %>
<%@include file="/views/data/dataPropertyModelQuote.jsp" %>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        dataProperty.prototype.loadDataDicList();
    });

    function showItemable(_this) {
        var frm = $(_this).closest("form") ;
        var data = formSerializeArray(frm);
        dataPropertyServiceItem.prototype.showStartModel(data.id);
    }

    var dataProperty = function () {

    };
    dataProperty.prototype = {
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
            cols.push({field: 'companyNatureName', title: '公司性质'});
            cols.push({field: 'socialPrestigeName', title: '社会信誉'});
            cols.push({
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    <!-- 这的tb_List不作为数据显示的table以config配置的为主 -->
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="dataProperty.prototype.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="dataProperty.prototype.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
                    return str;
                }
            });
            $("#"+dataProperty.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataProperty.prototype.config().table, "${pageContext.request.contextPath}/dataProperty/getDataPropertyList", cols, {
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
            dataPropertyModelQuote.deleteDataProperty(id,function () {
                toastr.success('删除成功');
                dataProperty.prototype.loadDataDicList();
            }) ;
        },
        showModel:function () {
            var target = $('#'+dataProperty.prototype.config().box) ;
            target.find(".panel-body").empty() ;
            target.find(".panel-body").append(dataPropertyModelQuote.getFatherHtml()) ;
            target.find("form").clearAll();
            target.modal("show");
        },
        saveData:function () {
            if (!$("#"+dataProperty.prototype.config().frm).valid()){
                return false;
            }
            var data = formParams(dataProperty.prototype.config().frm);
            dataPropertyModelQuote.saveDataProperty(data,function () {
                toastr.success('保存成功');
                $('#'+dataProperty.prototype.config().box).modal('hide');
                dataProperty.prototype.loadDataDicList();
            }) ;
        },
        getAndInit:function (id) {
            var data = $("#"+dataProperty.prototype.config().table).bootstrapTable('getRowByUniqueId',id);
            var target = $('#'+dataProperty.prototype.config().box) ;
            target.find(".panel-body").empty() ;
            target.find(".panel-body").append(dataPropertyModelQuote.getFatherHtml()) ;
            target.find("form").clearAll();
            target.find("form").find(".form-group").show();
            target.find("form").initForm(data);
            target.modal("show");
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
                <h3 class="modal-title">物业公司</h3>
            </div>
            <form id="frmFather" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="dataProperty.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
