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
                                    开始日期
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="开始日期" id="startTime"
                                           name="startTime" class="form-control date-month">
                                </div>
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    结束日期
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" placeholder="结束日期" id="endTime"
                                           name="endTime" class="form-control date-month">
                                </div>
                            </div>

                            <div class="col-sm-4">
                                <button type="button" class="btn btn-primary"
                                        onclick="dataProperty.prototype.loadDataDicList()">
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
        dataProperty.prototype.loadDataDicList();
        dataProperty.prototype.select2Load();

        DatepickerUtils.initDate($('.date-month'), {
            autoclose: true,
            todayBtn: "linked",
            language: "zh-CN",
            clearBtn: true,
            format: 'yyyy-mm',
            startView: 4,
            minView: 3
        });
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
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'provinceName', title: '区域',formatter: function (value, row, index) {
                return AssessCommon.getAreaFullName(row.provinceName,row.cityName,row.districtName);
            }});
            cols.push({
                field: 'yearMonthCalendar', title: '日期', formatter: function (value, row, index) {
                    return dataProperty.prototype.formatDate(row.yearMonthCalendar);
                }
            });
            cols.push({field: 'indexCalendar', title: '指数'});
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
            TableInit(dataProperty.prototype.config().table, "${pageContext.request.contextPath}/housePriceIndex/list", cols, {
                startTime: $("#startTime").val() + "-01", endTime: $("#endTime").val() + "-01"
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
            $.ajax({
                url: "${pageContext.request.contextPath}/housePriceIndex/delete",
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
            AssessCommon.initAreaInfo({
                provinceTarget: $("#province"),
                cityTarget: $("#city"),
                districtTarget: $("#district"),
                provinceValue: '',
                cityValue: '',
                districtValue: ''
            })
            $('#' + dataProperty.prototype.config().box).modal("show");
        },
        saveData: function () {
            if (!$("#" + dataProperty.prototype.config().frm).valid()) {
                return false;
            }
            var data = formParams(dataProperty.prototype.config().frm);
            console.log(data);
            data.yearMonthCalendar = data.yearMonthCalendar + "-01";
            console.log(data);
            console.log(data.yearMonthCalendar + "------");
            $.ajax({
                url: "${pageContext.request.contextPath}/housePriceIndex/saveAndUpdate",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
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
        },
        getAndInit: function (id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/housePriceIndex/get",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + dataProperty.prototype.config().frm).clearAll();
                        $("#" + dataProperty.prototype.config().frm).initForm(result.data);
                        AssessCommon.initAreaInfo({
                            provinceTarget: $("#province"),
                            cityTarget: $("#city"),
                            districtTarget: $("#district"),
                            provinceValue: result.data.province,
                            cityValue: result.data.city,
                            districtValue: result.data.district
                        })
                        $("#" + dataProperty.prototype.config().frm).find('[name=yearMonthCalendar]').val(formatDate(result.data.yearMonthCalendar));
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
        },
        //日期格式化
        formatDate: function (v) {
            if (!v) {
                return "";
            }
            if (/^(-)?\d{1,10}$/.test(v)) {
                v = v * 1000;
            } else if (/^(-)?\d{1,13}$/.test(v)) {
                v = v * 1;
            }
            var dateObj = new Date(v);
            var month = dateObj.getMonth() + 1;
            if (month < 10) {
                month = "0" + month;
            }
            var UnixTimeToDate = dateObj.getFullYear() + '-' + month;
            return UnixTimeToDate;
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
                <h3 class="modal-title">房价指数</h3>
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
                                            日期<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="月份" id="yearMonthCalendar"
                                                   required="required"
                                                   name="yearMonthCalendar" class="form-control date-month"
                                                   data-date-format="yyyy-mm">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">省
                                            <span class="symbol required"></span></label>
                                        <div class="col-sm-10">
                                            <select id="province" name="province"
                                                    class="form-control search-select select2"
                                                    required="required">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">市<span
                                                class="symbol required"></span></label>
                                        <div class="col-sm-10">
                                            <select id="city" name="city" class="form-control search-select select2"
                                                    required="required">

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
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            指数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" data-rule-number='true'
                                                   name="indexCalendar"
                                                   placeholder="指数(数字)" required="required">
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
