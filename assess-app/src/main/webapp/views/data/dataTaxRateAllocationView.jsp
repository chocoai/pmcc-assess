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
                                        <label for="type" class="col-md-1 col-form-label"> 类型</label>
                                        <div class="col-md-3 p-0">
                                            <select name="type" id="type"
                                                    class="form-control input-full search-select select2 type">
                                            </select>
                                        </div>

                                        <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                onclick="dataProperty.prototype.findList()">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                            查询
                                        </button>
                                        <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                                data-toggle="modal" onclick="dataProperty.prototype.showModel()"
                                                href="#divBox">
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

</body>


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
                field: 'id', title: '区域', formatter: function (value, row, index) {
                    var str = '全国';
                    if (row.bisNationalUnity == false) {
                        if (row.provinceName) {
                            str = row.provinceName;
                        }
                        if (row.cityName) {
                            str += row.cityName;
                        }
                        if (row.districtName) {
                            str += row.districtName;
                        }
                    }
                    return str;
                }
            });
            cols.push({field: 'typeName', title: '类型'});
            cols.push({
                field: 'taxRate', title: '税率', formatter: function (value, row, index) {
                    if (value) {
                        return AssessCommon.pointToPercent(value);
                    }
                }
            });
            cols.push({field: 'amount', title: '金额'});
            cols.push({field: 'calculateBase', title: '计算基数', width: '20%'});
            cols.push({field: 'calculationFormula', title: '计算公式'});
            cols.push({field: 'taxesBurdenName', title: '税费负担方'});
            cols.push({field: 'exExplain', title: '说明'});
            cols.push({field: 'sorting', title: '排序'});
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
        findList: function () {
            var id = $("#frmQuery" + " .type").eq(1).val();
            dataProperty.prototype.loadDataDicList(id);
        },
        removeData: function (id) {
            AlertConfirm("是否确认删除", "删除相应的数据后将不可恢复", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/dataTaxRateAllocation/deleteDataTaxRateAllocationById",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        notifySuccess("成功", "删除数据成功");
                        dataProperty.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
            })
        },
        showModel: function () {
            $("#" + dataProperty.prototype.config().frm).clearAll();
            AssessCommon.initAreaInfo({
                provinceTarget: $("#province"),
                cityTarget: $("#city"),
                districtTarget: $("#district"),
                provinceValue: '',
                cityValue: '',
                districtValue: ''
            });
            $("#region").hide();
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
                        AlertSuccess("成功", "数据已成功保存到数据库");
                        $('#' + dataProperty.prototype.config().box).modal('hide');
                        dataProperty.prototype.loadDataDicList();
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        select2Load: function () {
            //使数据校验生效
            $("#" + dataProperty.prototype.config().frm).validate();
            AssessCommon.loadDataDicByKey(AssessDicKey.dataTaxRateAllocation, "", function (html, data) {
                $("#" + dataProperty.prototype.config().frm).find('select.type').html(html);
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.dataTaxRateAllocation, "", function (html, data) {
                $("#frmQuery").find('select.type').html(html);
            });
            $("#" + dataProperty.prototype.config().frm + " .bisNationalUnity").change(function () {
                var item = $("#" + dataProperty.prototype.config().frm).find('select.bisNationalUnity').val();
                if (item == 'true') {
                    $("#region").hide();
                }
                if (item == 'false') {
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
                        $("#" + dataProperty.prototype.config().frm).find('[name=bisNationalUnity]').select2('val', result.data.bisNationalUnity + '').trigger('change');
                        if (!result.data.bisNationalUnity) {
                            AssessCommon.initAreaInfo({
                                provinceTarget: $("#province"),
                                cityTarget: $("#city"),
                                districtTarget: $("#district"),
                                provinceValue: result.data.province,
                                cityValue: result.data.city,
                                districtValue: result.data.district
                            })
                        }
                        $("#" + dataProperty.prototype.config().frm).find('.x-percent').each(function () {
                            $(this).attr('data-value', result.data[$(this).attr('name')]);
                            AssessCommon.elementParsePercent($(this));
                        })
                        $('#' + dataProperty.prototype.config().box).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
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
                <h4 class="modal-title">税率配置</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmFather" class="form-horizontal">
                    <input type="hidden" id="id" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                                全国统一
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="bisNationalUnity"
                                                        class="form-control input-full  search-select select2 bisNationalUnity"
                                                        required="required">
                                                    <option value="">-请选择-</option>
                                                    <option value="true">是</option>
                                                    <option value="false">否</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="region" style="display: none;">
                                    <div class="row form-group">
                                        <div class="col-md-6">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2 control-label">
                                                    省<span class="symbol required"></span></label>
                                                </label>
                                                <div class="col-sm-10">
                                                    <select id="province" name="province"
                                                            class="form-control input-full  search-select select2">
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
                                        <div class="col-md-6">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2 control-label">
                                                    市
                                                </label>
                                                <div class="col-sm-10">
                                                    <select id="city" name="city"
                                                            class="form-control input-full  search-select select2">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-6">
                                            <div class="form-inline x-valid">
                                                <label class="col-sm-2 control-label">
                                                    县
                                                </label>
                                                <div class="col-sm-10">
                                                    <select id="district" name="district"
                                                            class="form-control input-full  search-select select2">
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                类型<span class="symbol required"></span></label>
                                            </label>
                                            <div class="col-sm-10">
                                                <select name="type"
                                                        class="form-control input-full  search-select select2 type"
                                                        required="required">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                税率
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full  x-percent"
                                                       name="taxRate" placeholder="税率(数字)">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                金额
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" data-rule-number='true' class="form-control input-full"
                                                       name="amount" placeholder="金额(数字)">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                计算基数<span class="symbol required"></span>
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full" name="calculateBase"
                                                       placeholder="计算基数">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">

                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                计算公式
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="calculationFormula" placeholder="计算公式">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                税费负担方
                                            </label>
                                            <div class="col-sm-10">
                                                <select name='taxesBurden'
                                                        class='form-control input-full   search-select select2'>
                                                    <option value="0">-请选择-</option>
                                                    <c:forEach var="item" items="${taxesBurdenList}">
                                                        <option value="${item.id}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                字段名称
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full"
                                                       name="fieldName" placeholder="字段名称">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                排序
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control input-full" data-rule-number='true'
                                                       name="sorting" placeholder="排序">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">
                                                说明
                                            </label>
                                            <div class="col-sm-11">
                                            <textarea placeholder="说明" id="exExplain" name="exExplain"
                                                      class="form-control input-full"></textarea>
                                            </div>
                                        </div>
                                    </div>
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
