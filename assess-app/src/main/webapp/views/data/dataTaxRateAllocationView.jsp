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
                                    类型
                                </label>
                                <div class="col-sm-2">
                                    <select  name="type"
                                             class="form-control search-select select2 type">
                                    </select>
                                </div>
                            </div>

                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary"
                                        onclick="dataProperty.prototype.findList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success"
                                        onclick="dataProperty.prototype.showModel()"
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
        dataProperty.prototype.loadDataDicList(null);
        dataProperty.prototype.select2Load();
    });
    var dataProperty = function () {

    };
    dataProperty.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divBoxFather";
            data.frm = "frmFather";
            return data;
        },
        isEmpty: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function (type) {
            var cols = [];
            cols.push({
                field: 'id', title: '全国统一', formatter: function (value, row, index) {
                    var str = '<div class="btn-margin">';
                    if (row.nationalUnity == 'yes'){
                        str += "是";
                    }
                    if (row.nationalUnity == 'no'){
                        str += "否";
                    }
                    str += '</div>';
                    return str;
                }
            });
            cols.push({field: 'provinceName', title: '省'});
            cols.push({field: 'cityName', title: '市'});
            cols.push({field: 'districtName', title: '县'});
            cols.push({field: 'typeName', title: '类型'});
            cols.push({field: 'taxRateName', title: '税率'});
            cols.push({field: 'exExplain', title: '说明'});
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
            $("#" + dataProperty.prototype.config().table).bootstrapTable('destroy');
            TableInit(dataProperty.prototype.config().table, "${pageContext.request.contextPath}/dataTaxRateAllocation/getDataTaxRateAllocationList", cols, {
                type: type
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        findList:function () {
            var id = $("#frmQuery" + " .type").eq(1).val();
            dataProperty.prototype.loadDataDicList(id);
        },
        removeData: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataTaxRateAllocation/deleteDataTaxRateAllocationById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        toastr.success('删除成功');
                        dataProperty.prototype.loadDataDicList();
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
        showModel: function () {
            $("#" + dataProperty.prototype.config().frm).clearAll();
            $('#' + dataProperty.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + dataProperty.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(dataProperty.prototype.config().frm);
            $.ajax({
                url: "${pageContext.request.contextPath}/dataTaxRateAllocation/saveAndUpdateDataTaxRateAllocation",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        $('#' + dataProperty.prototype.config().box).modal('hide');
                        dataProperty.prototype.loadDataDicList();
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
        select2Load: function () {
            //使数据校验生效
            $("#" + dataProperty.prototype.config().frm).validate();
            AssessCommon.initAreaInfo({
                provinceTarget: $("#province"),
                cityTarget: $("#city"),
                districtTarget: $("#district"),
                provinceValue: '',
                cityValue: '',
                districtValue: ''
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.dataTaxRateAllocation, "", function (html, data) {
                $("#" + dataProperty.prototype.config().frm).find('select.type').html(html);
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.dataTaxRateAllocation, "", function (html, data) {
                $("#frmQuery").find('select.type').html(html);
            });
            $("#" + dataProperty.prototype.config().frm+" .nationalUnity").change(function () {
                var item = $("#" + dataProperty.prototype.config().frm+" .nationalUnity").eq(1).val();
                if (item == 'yes'){
                    $("#region").hide();
                }
                if (item == 'no'){
                    $("#region").show();
                }
            });
        },
        getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataTaxRateAllocation/getDataTaxRateAllocationById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + dataProperty.prototype.config().frm).clearAll();
                        $("#" + dataProperty.prototype.config().frm).initForm(result.data);
                        dataProperty.prototype.objectWriteSelectData(dataProperty.prototype.config().frm,result.data.city,"city");
                        dataProperty.prototype.objectWriteSelectData(dataProperty.prototype.config().frm,result.data.district,"district");
                        dataProperty.prototype.objectWriteSelectData(dataProperty.prototype.config().frm,result.data.province,"province");
                        $('#' + dataProperty.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        objectWriteSelectData: function (frm, data, name) {
            if (dataProperty.prototype.isEmpty(data)) {
                $("#" + frm + " ." + name).val(data).trigger("change");
            } else {
                $("#" + frm + " ." + name).val(null).trigger("change");
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
                <h3 class="modal-title">税率配置</h3>
            </div>
            <form id="frmFather" class="form-horizontal">
                <input type="hidden" id="id" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">全国统一</label>
                                        <div class="col-sm-10">
                                            <select name="nationalUnity" class="form-control search-select select2 nationalUnity" required="required">
                                                <option value="yes">是</option>
                                                <option value="no">否</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div id="region" style="display: none;">
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-2 control-label">省
                                                <span class="symbol required"></span></label>
                                            <div class="col-sm-10">
                                                <select id="province" name="province"
                                                        class="form-control search-select select2">
                                                    <option value="" name="province">-请选择-</option>
                                                    <c:forEach items="${ProvinceList}" var="item">
                                                        <c:choose>
                                                            <c:when test="${item.areaId == projectInfo.province}">
                                                                <option value="${item.areaId}"
                                                                        selected="selected">${item.name}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${item.areaId}">${item.name}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-2 control-label">市</label>
                                            <div class="col-sm-10">
                                                <select id="city" name="city" class="form-control search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="x-valid">
                                            <label class="col-sm-2 control-label">县</label>
                                            <div class="col-sm-10">
                                                <select id="district" name="district"
                                                        class="form-control search-select select2">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">类型<span class="symbol required"></span></label>
                                        <div class="col-sm-10">
                                            <select  name="type"
                                                    class="form-control search-select select2 type" required="required">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            税率<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" data-rule-number='true' class="form-control" name="taxRate"
                                                   placeholder="税率(数字)" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            说明<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="exExplain"
                                                   placeholder="说明" required="required">
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
                    <button type="button" class="btn btn-primary" onclick="dataProperty.prototype.saveData()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>
