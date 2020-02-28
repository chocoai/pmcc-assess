
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>

<body>
<div class="wrapper">
    <%@include file="/views/share/main_navigation.jsp" %>
    <%@include file="/views/share/main_head.jsp" %>
    <div class="main-panel">
        <div class="content">
            <div class="panel-header bg-primary-gradient">
                <div class="page-inner py-5">
                </div>
            </div>
            <div class="page-inner mt--5">
                <div class="row mt--2">

                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">${baseViewDto.currentMenu.name}</div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frmQuery" class="form-horizontal">
                                    <div class="form-group form-inline">
                                        <label for="queryName" class="col-md-1 col-form-label">名称</label>
                                        <div class="col-md-3 p-0">
                                            <input type="text" data-rule-maxlength="50"
                                                   placeholder="名称" id="queryName" name="queryName"
                                                   class="form-control">
                                        </div>

                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="dataProperty.prototype.loadDataDicList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="dataProperty.prototype.showModel()"
                                                href="#divBoxFather">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                            新增
                                        </button>
                                    </div>


                                </form>
                                <table class="table table-bordered" id="tb_FatherList">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>

</div>
<%@include file="/views/data/dataPropertyModelQuote.jsp" %>
<%@include file="/views/data/dataPropertyServiceItem.jsp" %>
</body>

<script type="text/javascript">
    $(function () {
        dataProperty.prototype.loadDataDicList();
    });

    function showItemable(_this) {
        var frm = $(_this).closest("form") ;
        var data = formSerializeArray(frm);
        $('#'+dataProperty.prototype.config().box).modal('hide');
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
                    var str = '<button onclick="dataProperty.prototype.getAndInit(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button onclick="dataProperty.prototype.removeData(' + row.id + ',\'tb_List\')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
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
                notifySuccess("成功", "删除数据成功");
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
                AlertSuccess("成功", "数据已成功保存到数据库");
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
                <h4 class="modal-title">物业公司</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmFather" class="form-horizontal">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="panel-body">

                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="dataProperty.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>



</html>
