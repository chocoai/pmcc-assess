/**
 * Created by kings on 2018-6-21.
 */
var treeGridJson = {};
var treeGridJsonData = {};
$(function () {
    getPlanItemList();
    DatepickerUtils.sectionChoose($("#planStartDate"), $("#planEndDate"));

    if ($("#projectPhaseId").hasClass('select2')) {
        $("#projectPhaseId").select2();
        $("#projectPhaseId").on("change", function (e) {
            if($(this).val()){
                $("#projectPhaseName").val($("#projectPhaseId").find('option:selected').text());
            }else{
                $("#projectPhaseName").val('');
            }
        })
    }
});

function nextEmployee() {
    var thisUser = "";
    if ($("#nextApproval").val()) {
        var userName = $("#nextApprovalName").val();
        var userAccount = $("#nextApproval").val();
        thisUser = userName + "_" + userAccount;
    }
    erpEmployee.select({
        value: thisUser,
        onSelected: function (data) {
            if (data.account) {
                $("#nextApproval").val(data.account);
                $("#nextApprovalName").val(data.name);
            }
            else {
                $("#nextApproval").val("");
                $("#nextApprovalName").val("");
            }
        }
    });
}

function selEmployee() {
    var thisUser = "";
    if ($("#executeUserAccount").val()) {
        var userName = $("#executeUserName").val();
        var userAccount = $("#executeUserAccount").val();
        thisUser = userName + "_" + userAccount;
    }
    erpEmployee.select({
        value: thisUser,
        onSelected: function (data) {
            if (data.account) {
                $("#executeUserAccount").val(data.account);
                $("#executeUserName").val(data.name);

                //获取人员部门信息
                $.ajax({
                    url: getContextPath() + "/RpcErpService/getDepartmentByUserAccount",
                    type: "get",
                    data: {userAccount: data.account},
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            $("#executeDepartmentId").val(result.data.id);
                            $("#executeDepartmentName").val(result.data.name);
                        }
                    }
                })
            }
            else {
                $("#executeUserAccount").val("");
                $("#executeUserName").val("");
            }
        }
    });
}

//选部门控件
function selDept() {
    erpDepartment.select({
        value: $("#executeDepartmentId").val(),
        onSelected: function (node) {
            $("#executeDepartmentId").val(node[0].id);
            $("#executeDepartmentName").val(node[0].text);
        }
    });
}

//快速设置选择责任人
function selFastEmployee() {
    erpEmployee.select({
        onSelected: function (data) {
            if (data.account) {
                $("#fast_executeUserAccount").val(data.account);
                $("#fast_executeUserName").val(data.name);

                //获取人员部门信息
                $.ajax({
                    url: getContextPath() + "/RpcErpService/getDepartmentByUserAccount",
                    type: "get",
                    data: {userAccount: data.account},
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            $("#fast_executeDepartmentId").val(result.data.id);
                            $("#fast_executeDepartmentName").val(result.data.name);
                        }
                    }
                })
            }
            else {
                $("#fast_executeUserAccount").val("");
                $("#fast_executeUserName").val("");
            }
        }
    });
}

//选部门控件
function selFastDept() {
    erpDepartment.select({
        value: $("#fast_executeDepartmentId").val(),
        onSelected: function (node) {
            $("#fast_executeDepartmentId").val(node[0].id);
            $("#fast_executeDepartmentName").val(node[0].text);
        }
    });
}

function clearFastValue(obj) {
    $(obj).closest("tr").find(".fast_value").val("");
    var objId = $(obj).attr("id");
    if (objId == "btn_user") {
        $("#fast_executeUserName").val("");
    }
    if (objId == "btn_dept") {
        $("#fast_executeDepartmentName").val("");
    }
}

//保存计划明细数据
function savePlanDtails() {
    if (!$("#frm_planDetails").valid()) {
        return false;
    }
    var data = formParams("frm_planDetails");
    data["planId"] = $("#planId").val();
    data["workStageId"] = $("#workStageId").val();
    data["projectId"] = $("#projectId").val();
    data["projectWorkStageId"] = $("#workStageId").val();
    //将最新的列表顺序存入表中
    var detailsSoring = [];
    $.each(treeGridJsonData.rows, function (i, j) {
        detailsSoring.push({
            key: j.id,
            value: j.sorting,
            explain: j.pid
        });
    });
    data["detailsSoring"] = JSON.stringify(detailsSoring);
    Loading.progressShow();
    $.ajax({
        url: getContextPath() + "/ProjectPlan/saveProjectPlanDetails",
        data: {
            ds: JSON.stringify(data),
            planId: $("#planId").val()
        },
        type: "post",
        dataType: "json",
        success: function (result) {
            Loading.progressHide();

            if (result.ret) {
                //保存完后其他动作
                toastr.success("保存成功");
                result = result.data;
                result.rows = sortObjectArray(result.rows, ["sorting"]);
                treeGridJson = result;
                treeGridJsonData = $.extend(true, {}, result);
                treeGridload();
                $('#div_plan').modal('hide');
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
        }
    });

}

function addfirst() {
    $("#frm_planDetails").clearAll();
    $("#frm_planDetails").validate();
    $("#pid").val(0);
    $("#firstPid").val(0);
    $("#projectPhaseId").find("option").first().attr("selected", true);
    $("#planDetailsId").val(0);
    $('#div_plan').modal({backdrop: 'static', keyboard: false});
}
function addPlan(id) {

    $("#frm_planDetails").clearAll();
    $("#frm_planDetails").validate();
    var row = $('#PlanItemListed').treegrid('find', id);
    $("#pid").val(row["id"]);
    $("#firstPid").val(row["firstPid"]);
    if ($("#projectPhaseId").hasClass('select2')) {
        $("#projectPhaseId").select2('val', row.projectPhaseId)
    } else {
        $("#projectPhaseId").val(row.projectPhaseId);
    }
    $("#planDetailsId").val(0);
    $('#div_plan').modal({backdrop: 'static', keyboard: false});

}

function editPlan(id) {
    var row = $('#PlanItemListed').treegrid('find', id);
    $("#frm_planDetails").clearAll();
    $("#frm_planDetails").validate();
    $("#frm_planDetails").initForm(row);
    $("#planDetailsId").val(row["id"]);
    $("#planStartDate").val(formatDate(row["planStartDate"]));
    $("#planEndDate").val(formatDate(row["planEndDate"]));
    if ($("#projectPhaseId").hasClass('select2')) {
        $("#projectPhaseId").select2('val', row.projectPhaseId)
    } else {
        $("#projectPhaseId").val(row.projectPhaseId);
    }
    $('#div_plan').modal({backdrop: 'static', keyboard: false});
}
function deletePlan(id) {
    Alert("删除后将不可恢复,确认删除？", 2, null, function () {
        Loading.progressShow();
        $.ajax({
            url: getContextPath() + "/ProjectPlan/deletePlan",
            data: {
                planDetailsId: id,
                planId: $("#planId").val()
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();

                if (result.ret) {
                    result.data.rows = sortObjectArray(result.data.rows, ["sorting"]);
                    treeGridJson = result.data;
                    treeGridJsonData = $.extend(true, {}, result.data);
                    treeGridload();
                } else {
                    Alert("删除数据失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    });
}


function getPlanItemList() {
    Loading.progressShow();
    $.ajax({
        url: getContextPath() + "/ProjectPlan/getProjectPlanDetailsByPlanApply",
        data: {
            planId: $("#planId").val()
        },
        type: "get",
        dataType: "json",
        success: function (result) {
            Loading.progressHide();
            result.rows = sortObjectArray(result.rows, ["sorting"]);
            treeGridJson = result;
            treeGridJsonData = $.extend(true, {}, result);
            treeGridload();

        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
        }
    });


}

function keySet() {
//        $("#frm_fastset").clearAll();
    $("#frm_fastset").validate();
    $('#div_fastSet').modal({backdrop: 'static', keyboard: false});

}

function saveFastset() {
    if (!$("#frm_fastset").valid()) {
        return false;
    }

    //将最新的列表顺序存入表中
    var detailsSoring = [];
    $.each(treeGridJsonData.rows, function (i, j) {
        detailsSoring.push({
            key: j.id,
            value: j.sorting,
            explain: j.pid
        });
    });
    var detailsSoring = JSON.stringify(detailsSoring);
    var objArray = [];
    $.each($(".fast_tr"), function (i, j) {
        objArray.push({
            fastFileds: $(j).find(".fast_fileds").val(),
            fastValue: $(j).find(".fast_value").val(),
            fastRange: $(j).find(".fast_range").val()
        });
    });

    Loading.progressShow();
    $.ajax({
        url: getContextPath() + "/ProjectPlan/fastSetPlan",
        data: {
            fields: JSON.stringify(objArray),
            planId: $("#planId").val(),
            detailsSoring: detailsSoring
        },
        type: "post",
        dataType: "json",
        success: function (result) {
            Loading.progressHide();

            if (result.ret) {
                //保存完后其他动作
                toastr.success("保存成功");
                result = result.data;
                result.rows = sortObjectArray(result.rows, ["sorting"]);
                treeGridJson = result;
                treeGridJsonData = $.extend(true, {}, result);
                treeGridload();
                $('#div_fastSet').modal('hide');
            } else {
                Alert("保存失败:" + result.errmsg);
            }

        },
        error: function (result) {
            Loading.progressHide();
            Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
        }
    });


}
function treeGridload() {
    $("#PlanItemListed").treegrid({
            data: treeGridJson,
            idField: 'id',
            treeField: 'projectPhaseName',
            datatype: 'json',
            lines: true,
            width: 'auto',
            toolbar: "#tb",
            rownumbers: true,
            onDblClickRow: function (row) {
                editPlan(row.id);
            },
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            },

            columns: [[
                {field: "projectPhaseName", title: "工作内容", width: "20%", align: "left"},
                {
                    field: "planStartDate",
                    title: "开始时间",
                    width: "10%",
                    align: "center",
                    formatter: function (value, row) {
                        return formatDate(value, false);
                    }
                },
                {
                    field: "planEndDate",
                    title: "结束时间",
                    width: "10%",
                    align: "center",
                    formatter: function (value, row) {
                        return formatDate(value, false);
                    }
                },
                {
                    field: "planHours",
                    title: "计划工时",
                    width: "5%",
                    align: "center"
                },
                {
                    field: "executeUserName",
                    title: "责任人",
                    width: "10%",
                    align: "center"
                },
                {
                    field: "executeDepartmentName",
                    title: "责任部门",
                    width: "10%",
                    align: "center"
                },
                {
                    field: "proportion",
                    title: "权重占比",
                    width: "5%",
                    align: "center"
                },
                {field: "planRemarks", title: "说明", width: "15%", align: "left"},
                {field: "firstPid", title: "firstPid", align: "center", hidden: true},
                {field: "sorting", title: "sorting", align: "center", hidden: true},
                {field: "id", title: "PlanItemId", align: "center", hidden: true},
                {field: "projectPhaseId", title: "projectPhaseId", align: "center", hidden: true},
                {field: "declareFormId", title: "declareFormId", align: "center", hidden: true},
                {field: "declareFormName", title: "declareFormName", align: "center", hidden: true},
                {
                    field: 'workStages', title: '操作', width: '10%', formatter: function (value, row) {
                    if (row.bisEnable && row.status == 'none') {
                        var s = "";
                        if ($("#planDetailsIds").val()) {
                            //如果不为空则说明是子计划，如果为子计划，则只允许新增项或编辑当前项
                            var planDetailsId = $("#planDetailsIds").val();
                            var aPlanDetailsId = planDetailsId.split(",");
                            if ($.inArray(row.id + "", aPlanDetailsId) >= 0) {
                                s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='新增下级' class='btn btn-xs btn-success tooltips' target='_blank' onclick='addPlan(" + row.id + ")'   ><i class='fa fa-plus fa-white'></i></a>";
                                s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='编辑修改' class='btn btn-xs btn-primary tooltips' target='_blank' onclick='editPlan(" + row.id + ")'  ><i class='fa fa-edit fa-white'></i></a>";
                            }
                            else {
                                if ($.inArray(row.firstPid + "", aPlanDetailsId) >= 0) {
                                    s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='新增下级' class='btn btn-xs btn-success tooltips' target='_blank' onclick='addPlan(" + row.id + ")'   ><i class='fa fa-plus fa-white'></i></a>";
                                    s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='编辑修改' class='btn btn-xs btn-primary tooltips' target='_blank' onclick='editPlan(" + row.id + ")'  ><i class='fa fa-edit fa-white'></i></a>";
                                    if (row.bisLastLayer) {
                                        s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='删除' class='btn btn-xs btn-warning tooltips' target='_blank' onclick='deletePlan(" + row.id + ")'   ><i class='fa fa-minus fa-white'></i></a>";
                                    }

                                }
                            }
                        }
                        else {
                            s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='新增下级' class='btn btn-xs btn-success tooltips' target='_blank' onclick='addPlan(" + row.id + ")'   ><i class='fa fa-plus fa-white'></i></a>";
                            s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='编辑修改' class='btn btn-xs btn-primary tooltips' target='_blank' onclick='editPlan(" + row.id + ")'  ><i class='fa fa-edit fa-white'></i></a>";
                            if (row.bisLastLayer) {
                                s += "<a style='margin-left: 5px;' data-placement='top' data-original-title='删除' class='btn btn-xs btn-warning tooltips' target='_blank'   onclick='deletePlan(" + row.id + ")'><i class='fa fa-minus fa-white'></i></a>";
                            }

                        }

                        return s;
                    }

                }
                }
            ]]
        }
    )
    ;
}

function move(o) {//将此方法加入上下移的按钮事件即可
    Loading.progressShow();
    var node = $("#PlanItemListed").treegrid("getSelected");
    $.each(treeGridJsonData.rows, function (i, j) {

        if (j.id == node.id) {
            if (o == "up") {
                if (i - 1 >= 0) {
                    if (treeGridJsonData.rows[i - 1].pid == treeGridJsonData.rows[i].pid) {
                        treeGridJsonData.rows[i - 1].sorting = treeGridJsonData.rows[i - 1].sorting + 1;
                        treeGridJsonData.rows[i].sorting = treeGridJsonData.rows[i].sorting - 1;
                    }
                }
            }
            else {
                if (i + 1 < treeGridJsonData.rows.length) {
                    if (treeGridJsonData.rows[i + 1]._parentId == treeGridJsonData.rows[i]._parentId) {
                        treeGridJsonData.rows[i].sorting = treeGridJsonData.rows[i].sorting + 1;
                        treeGridJsonData.rows[i + 1].sorting = treeGridJsonData.rows[i + 1].sorting - 1;
                    }
                }
            }

        }
    });

    treeGridJsonData.rows = sortObjectArray(treeGridJsonData.rows, ["sorting"]);
    treeGridJson = jQuery.extend(true, {}, treeGridJsonData);
    $('#PlanItemListed').treegrid('loadData', treeGridJson);
    Loading.progressHide();
}

function commitApply() {
    if ($("#chk_isNext").is(':checked')) {
        $("#nextApprovalName").attr("required", true);
        $("#detailsPlan").val(1);
    }
    else {
        $("#nextApprovalName").attr("required", false);
        $("#detailsPlan").val(0);
    }

    if (!$("#frm_plan").valid()) {
        return false;
    }
    //将最新的列表顺序存入表中
    var detailsSoring = [];
    $.each(treeGridJsonData.rows, function (i, j) {
        detailsSoring.push({
            key: j.id,
            value: j.sorting,
            explain: j.pid
        });
    });
    var data = formParams("frm_plan");
    data["detailsSoring"] = JSON.stringify(detailsSoring);
    data["bisChildren"] = $("#bisChildren").val();
    data["projectId"] = $("#projectId").val();
    Loading.progressShow();
    var url = getContextPath() + "/ProjectPlan/saveProjectPlan";
    if ($("#processInsId").length > 0 && $("#processInsId").val() != "0") {
        url = getContextPath() + "/ProjectPlan/submitPlanEdit";
    }
    $.ajax({
        url: url,
        data: {formData: JSON.stringify(data)},
        type: "post",
        dataType: "json",
        success: function (result) {
            Loading.progressHide();

            if (result.ret) {
                Alert("提交数据成功!", 1, null, function () {
                    window.close();
                });
            } else {
                Alert("保存失败:" + result.errmsg);
            }
        },
        error: function (result) {
            Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
        }
    });
}