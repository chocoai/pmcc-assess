<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>

            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>${parentProject.projectPhaseName}-${projectPlanDetails.projectPhaseName}</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_asset" class="form-horizontal">
                        <input type="hidden" name="id" value="${surveyAssetInventory.id}">
                        <input type="hidden" id="defaultLocaltion" name="defaultLocaltion"
                               value="${surveyAssetInventory.defaultLocaltion}">
                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">评估人员<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <div class="input-group">
                                        <input type="hidden" id="evaluatorID">
                                        <input type="text" class="form-control" readonly="readonly"
                                               value="${thisUserInfo.userName}" required="required"
                                               id="evaluator" name="evaluator" maxlength="200">
                                        <span class="input-group-btn">
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    data-toggle="tooltip"
                                                    data-original-title="选择" onclick="selectEvaluator()">
                                            <i class="fa fa-search"></i>
                                            </button>
                                            <button type="button" class="btn btn-default docs-tooltip"
                                                    onclick="$(this).closest('.input-group').find('input').val('');"
                                                    data-toggle="tooltip" data-original-title="清除">
                                            <i class="fa fa-trash-o"></i>
                                            </button>
                                        </span>
                                    </div>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">核对日期<span class="symbol required"></span></label>
                                <div class="col-sm-2">
                                    <input type="text" required placeholder="核对日期" id="checkDate" name="checkDate"
                                           data-date-format="yyyy-mm-dd" class="form-control date-picker dbdate"
                                           readonly="readonly"
                                           value="<fmt:formatDate value='${surveyAssetInventory.checkDate}' pattern='yyyy-MM-dd'/>">
                                </div>
                            </div>
                        </div>
                    </form>
                    <form id="frm_asset_inventory" class="form-horizontal">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h2>
                                <small><i class="fa fa-bars"></i>清查内容</small>
                            </h2>
                            <div class="clearfix"></div>
                        </div>
                        <table class="table" id="tb_surveyList">
                            <thead>
                            <tr>
                                <th style="width: 10%">清查内容</th>
                                <th style="width: 6%">是否一致</th>
                                <th style="width: 10%">登记</th>
                                <th style="width: 10%">实际</th>
                                <th style="width: 10%">差异原因</th>
                                <th style="width: 10%">证明文件</th>
                                <th style="width: 10%">证明文件附件</th>
                                <th style="width: 5%">证明人</th>
                                <th style="width: 6%">调查时间</th>
                                <th style="width: 10%">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${surveyAssetInventoryContentVos}" var="items" varStatus="s">
                                <tr>
                                    <input type="hidden" id="id" name="id" value="${items.id}">
                                    <td name="inventoryContent"
                                        dic-id="${items.inventoryContent}">${items.inventoryContentName}</td>
                                    <td>
                                        <input id="areConsistent${items.id}" name="areConsistent" type="checkbox"
                                               value="一致" style="vertical-align:middle;"
                                               onclick="showHiddenCheck(this,${items.id})"/>
                                        <label style="vertical-align:middle;font-weight: normal;margin-bottom: 0px;"
                                               for="areConsistent${items.id}">一致</label>
                                    </td>
                                    <td>
                                        <div class="x-valid">
                                            <input type="text" data-rule-maxlength="50" placeholder="登记" required
                                                   id="registrationAddress${items.id}"
                                                   name="registrationAddress${items.id}"
                                                   class="form-control showHidden" value="${items.registrationAddress}">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="x-valid">
                                            <input type="text" data-rule-maxlength="50" placeholder="实际" required
                                                   id="actualAddress${items.id}" name="actualAddress${items.id}"
                                                   class="form-control showHidden" value="${items.actualAddress}">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="x-valid">
                                            <input type="text" data-rule-maxlength="50" placeholder="差异原因" required
                                                   id="differenceReason${items.id}" name="differenceReason${items.id}"
                                                   class="form-control showHidden" value="${items.differenceReason}">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="x-valid">
                                            <input type="text" data-rule-maxlength="50" placeholder="证明文件" required
                                                   id="credential${items.id}" name="credential${items.id}"
                                                   class="form-control showHidden" value="${items.credential}">
                                        </div>
                                    </td>
                                    <td>
                                        <input id="credentialAccessory${items.id}" name="credentialAccessory"
                                               type="file" multiple="false" class="showHidden">
                                        <div id="_credentialAccessory${items.id}" class="showHidden"></div>
                                    </td>
                                    <td>
                                        <div class="x-valid">
                                            <input type="text" data-rule-maxlength="50" placeholder="证明人" required
                                                   id="voucher${items.id}" name="voucher${items.id}"
                                                   class="form-control showHidden" value="${items.voucher}">
                                        </div>
                                    </td>
                                    <td>
                                        <div class="x-valid">
                                            <input placeholder="调查时间" id="surveyTime${items.id}"
                                                   name="surveyTime${items.id}" required
                                                   data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate showHidden"
                                                   readonly="readonly"
                                                   value='<fmt:formatDate value="${items.surveyTime}" pattern="yyyy-MM-dd"/>'>
                                        </div>
                                    </td>
                                    <td>
                                        <a class="btn btn-xs btn-danger" onclick="emptyRefill(this)">清空重填</a>
                                    </td>
                                </tr>
                                <script type="text/javascript">
                                    $(function () {
                                        //清查内容附件上传和加载
                                        uploadFileCommon("${items.id}");
                                        showFileCommon("${items.id}");
                                    })
                                </script>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                    <div class="x_title ">
                        <ul class="nav navbar-right panel_toolbox">
                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                        </ul>
                        <h2>
                            <small class="col-sm-1"><i class="fa fa-bars"></i>他项权利</small>
                        </h2>
                        <button type="button" class="btn btn-success" onclick="addData()"
                                data-toggle="modal" href="#divBox"> 新增
                        </button>
                        <div class="clearfix"></div>
                    </div>
                    <table class="table table-bordered" id="tb_List">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>

                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>

<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">他项权利</h3>
            </div>
            <form id="frm" class="form-horizontal">
                <input type="hidden" name="id" value="0">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类型<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select class="form-control" required id="type" name="type">
                                                <option value="">-请选择-</option>
                                                <c:forEach var="items" items="${otherRightTypeList}">
                                                    <option value="${items.id}">${items.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            类别<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <select class="form-control" required id="category" name="category">
                                                <option value="">-请选择-</option>
                                                <c:forEach var="items" items="${otherRightTypeList}">
                                                    <option value="${items.id}">${items.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            他权证编号<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="他权证编号" required
                                                   id="number" name="number"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">登记日期<span class="symbol required"></span></label>
                                        <div class="col-sm-4">
                                            <input placeholder="登记日期" id="registerDate" name="registerDate"
                                                   data-date-format="yyyy-mm-dd" required
                                                   class="form-control date-picker dbdate" readonly="readonly">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            义务人<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="义务人" required
                                                   id="obligor" name="obligor"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            权利人<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="权利人" required
                                                   id="obligee" name="obligee" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            登记金额
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="登记金额"
                                                   id="registerAmount" name="registerAmount" class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            行权金额
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="行权金额"
                                                   id="actualAmount" name="actualAmount" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            登记面积
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="登记面积"
                                                   id="registerArea" name="registerArea" class="form-control">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            他权级次
                                        </label>
                                        <div class="col-sm-4">
                                            <input type="text" placeholder="他权级次"
                                                   id="rightRank" name="rightRank" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">开始日期</label>
                                        <div class="col-sm-4">
                                            <input placeholder="开始日期" id="beginDate"
                                                   name="beginDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate" readonly="readonly">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">结束日期</label>
                                        <div class="col-sm-4">
                                            <input placeholder="结束日期" id="endDate"
                                                   name="endDate" data-date-format="yyyy-mm-dd"
                                                   class="form-control date-picker dbdate" readonly="readonly">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </form>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="saveData()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">

    $(function () {
        loadAssetOtherList();
    });

    //上传附件通用
    function uploadFileCommon(tableId) {
        FileUtils.uploadFiles({
            showMode: 'table',
            target: "credentialAccessory" + tableId,
            disabledTarget: "btn_submit",
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: tableId
            },
            deleteFlag: true
        });
    }

    //显示附件通用
    function showFileCommon(tableId) {
        FileUtils.getFileShows({
            showMode: 'table',
            target: "credentialAccessory" + tableId,
            formData: {
                tableName: "tb_survey_asset_template",
                tableId: tableId
            },
            deleteFlag: true
        })
    }

    //加载 他项权利列表
    function loadAssetOtherList() {
        var cols = [];
        cols.push({field: 'typeName', title: '类型'});
        cols.push({field: 'categoryName', title: '类型'});
        cols.push({field: 'number', title: '他权证编号'});
        cols.push({field: 'obligor', title: '义务人'});
        cols.push({field: 'obligee', title: '权利人'});
        cols.push({field: 'registerArea', title: '登记面积'});
        cols.push({field: 'rightRank', title: '他权级次'});
        cols.push({field: 'registerAmount', title: '登记金额'});
        cols.push({field: 'actualAmount', title: '行权金额'});
        cols.push({
            field: 'registerDate', title: '登记日期', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'beginDate', title: '实际行权人行权日期', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });
        cols.push({
            field: 'endDate', title: '预计到期日', formatter: function (value, row, index) {
                return formatDate(value, false);
            }
        });

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips" data-placement="top" data-original-title="编辑" onclick="editData(' + index + ');" ><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="delData(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/surveyAssetInventoryRight/list", cols, {
            planDetailsId: '${projectPlanDetails.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();   //提示
            }
        });
    }


    //获取需要保存的数据
    function getFormData() {
        var trs = $("#tb_surveyList").find('tbody tr');
        var dataItem = [];
        $.each(trs, function (i, tr) {
            var item = {};
            var temp = $(tr).find('[name="areConsistent"]:checked').prop("checked");    //是否一致
            if (temp) {
                item.areConsistent = "一致";    //是否一致
            } else {
                item.areConsistent = "不一致";
            }
            item.inventoryContent = $(tr).find('[name^="inventoryContent"]').attr("dic-id");    //清查内容
            item.registrationAddress = $(tr).find('[name^="registrationAddress"]').val();    //登记面积
            item.actualAddress = $(tr).find('[name^="actualAddress"]').val();                //实际面积
            item.differenceReason = $(tr).find('[name^="differenceReason"]').val();          //差异原因
            item.credential = $(tr).find('[name^="credential"]').val();                      //证明文件
            item.voucher = $(tr).find('[name^="voucher"]').val();                            //证明人
            item.surveyTime = $(tr).find('[name^="surveyTime"]').val();                      //查勘时间
            item.projectId = ${projectPlanDetails.projectId};
            item.planDetailId = ${projectPlanDetails.id};
            item.id = $(tr).find('[name="id"]').val();    //id
            dataItem.push(item);
        });
        var data = {};
        data.surveyAssetInventory = formParams("frm_asset");//评估人员 核对时间
        data.assetInventoryContentList = dataItem;
        return data;
    }

    function submit() {
        if (!$("#frm_asset").valid()) {
            return false;
        }
        if (!$("#frm_asset_inventory").valid()) {
            return false;
        }
        var formData = JSON.stringify(getFormData());
        if ("${processInsId}" != "0") {
            submitEditToServer(formData, "1", "1");
        }
        else {
            submitToServer(formData, "1", "1");
        }
    }

    //他权一致显示隐藏切换
    $('#agreementBox').click(function () {
        if ($('#agreementBox').prop("checked")) {
            $('#frm_survey').css('display', 'none');
            $('#frm_survey').clearAll();
        } else {
            $('#frm_survey').css('display', 'block');
        }
    });


    //表格一致显示隐藏切换
    function showHiddenCheck(_this, id) {
        if ($('#areConsistent' + id).prop("checked")) {
            $(_this).closest("tr").find(".showHidden,div").css('display', 'none');
            $(_this).closest("tr").find("input:text").val("");
        } else {
            $(_this).closest("tr").find(".showHidden,div").css('display', 'block');
        }
    }

    //清空重填
    function emptyRefill(_this) {
        $(_this).closest("tr").find("input").val("");
    }

    //评估人员
    function selectEvaluator() {
        erpEmployee.select({
            onSelected: function (data) {
                $("#evaluator").val(data.name);
                $("#evaluatorID").val(data.account);
            }
        });
    }

    //他权
    function addData() {
        $("#frm").clearAll();
    }
    //他权保存
    function saveData() {
        var data = formParams("frm");
        data.planDetailsId = ${projectPlanDetails.id};
        if ($("#frm").valid()) {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyAssetInventoryRight/save",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadAssetOtherList();
                        $('#divBox').modal('hide');
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }
    //他权修改
    function editData(index) {
        var row = $("#tb_List").bootstrapTable('getData')[index];
        $("#frm").clearAll();
        $("#frm").initForm(row);
        $("#registerDate").val(formatDate(row.registerDate, false));
        $("#beginDate").val(formatDate(row.beginDate, false));
        $("#endDate").val(formatDate(row.endDate, false));
        $('#divBox').modal();
    }
    //他权删除
    function delData(id) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/surveyAssetOtherTemplate/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadAssetOtherList();//重载 (刷新)
                        $('#' + tbId).bootstrapTable("refresh");
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

</script>

</html>

