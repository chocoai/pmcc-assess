<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="div_model_employee" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">人员选择</h4>
            </div>

            <div class="x_content" style="padding: 0px">
                <input id="model_employee_tags" type="text" class="tags">
                <input id="model_employee_single" type="hidden">
                <div id="model_employee_tree" class="col-md-3" style="height: 360px;overflow: auto;">
                </div>
                <div class="col-md-9">
                    <table id="model_employee_table" class="table table-bordered">
                    </table>
                </div>
            </div>

            <div class="modal-footer" style="margin-top: 0px">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" id="btn_model_employee">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script type="application/javascript">
    var departmentNode = {};

    $(function () {
        $("#model_employee_tags").tagsInput({
            width: "auto",
            interactive: false,
            autosize: false,
            minheight: "50px",
            height: 50
        });

    });


    //参数表示在哪个公司或部门下选择相关人员,若为全部则传入0
    function loadSelectEmployee(currOrgId, currVlaue, single, callback) {

        Loading.progressShow();
        $("#model_employee_single").val(single == true ? "1" : "0");
        model_employee_tags_firstLoadTag(currVlaue);
        initBaseTreeView("model_employee_tree", getContextPath() + "/RpcBpmService/getDepartmentTree", {orgId: currOrgId}, false, function (objs) {
            treeView_setValue("model_employee_tree", currVlaue);
            objs.on('nodeSelected', function (event, node) {
                departmentNode = node;
                TableReload("model_employee_table", getContextPath() + "/RpcErpService/getUserInfoByDepartmentId", {departmentId: node.id});
            });
        });


        model_employee_talbes_load(currOrgId);

        //绑定模态窗口事件
        $('#btn_model_employee').unbind("click");
        $("#btn_model_employee").click(function () {
            getSelectEmployee(callback);
        });

    }

    function model_employee_tags_addTag(text) {
        $("#model_employee_tags").addTag(text);
    }
    function model_employee_tags_removeTag(text) {
        $("#model_employee_tags").removeTag(text);
    }
    function model_employee_tags_firstLoadTag(text) {
        $("#model_employee_tags").importTags(text, true);
    }
    function model_employee_tags_checkTag(text) {
        if ($("#model_employee_tags").tagExist(text)) {
            return "1";
        }
        else {
            return "0";
        }
    }

    function getSelectEmployee(callback) {
        var tags = $("#model_employee_tags").val();
        $('#div_model_employee').modal('hide');
        model_employee_tags_firstLoadTag('');
        var data = {};
        data["base"] = tags;
        var acount = "";
        var name = "";
        var aTags = tags.split(',');
        for (var i = 0; i < aTags.length; i++) {
            acount += aTags[i].split('_')[1] + ",";
            name += aTags[i].split('_')[0] + ",";
        }
        data["account"] = acount.substring(0, acount.length - 1);
        data["name"] = name.substring(0, name.length - 1);
        data["department"] = departmentNode;
        callback(data);
    }

    function model_employee_talbes_load(currOrgId) {
        var cols = [];
        cols.push({field: "userAccount", title: "员工账号"});
        cols.push({field: "userName", title: "员工姓名"});
        var data = {departmentId: currOrgId};
        var parms = {
            pageSize: 5,
            pageList: [5],
            showColumns: false,
            toolbar: '',
            showTotalPages: false,
            onClickRow: function (rows, $element) {
                var tagText = rows.userName + "_" + rows.userAccount;
                if ($("#model_employee_single").val() == "1") {
                    model_employee_tags_firstLoadTag(tagText);
                }
                else {
                    if (model_employee_tags_checkTag(tagText) == "0")//如果不存在则进行添加书签
                    {
                        model_employee_tags_addTag(tagText);
                    }
                    else//如果存在则删除书签
                    {
                        model_employee_tags_removeTag(tagText);
                    }
                }
            },
            onLoadSuccess: function () {
                Loading.progressHide();
                $('#div_model_employee').modal({backdrop: 'static', keyboard: false});
            }
        };
        $("#model_employee_table").bootstrapTable("destroy");//销毁
        TableInit("model_employee_table", getContextPath() + "/RpcErpService/getUserInfoByDepartmentId", cols, data, parms);
    }

</script> 