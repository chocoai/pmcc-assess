<%--
  Created by IntelliJ IDEA.
  User: huhao
  Date: 2018/9/3
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    归档信息
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form id="project_master_form" class="form-horizontal">
                <input type="hidden" name="id" value="${projectXlxPigeonhole.id}">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                归档日期
                            </label>
                            <div class="col-sm-3">
                                <input placeholder="归档日期"
                                       name="filingDate" data-date-format="yyyy-mm-dd"
                                       class="form-control input-full date-picker dbdate" readonly="readonly"
                                       value="<fmt:formatDate value='${projectXlxPigeonhole.filingDate}' pattern='yyyy-MM-dd'/>">

                            </div>
                            <label class="col-sm-1 col-form-label">
                                归档人员
                            </label>
                            <div class="col-sm-3">
                                <input type="text" placeholder="归档人员" name="filingPerson"
                                       class="form-control input-full"
                                       value="${empty projectXlxPigeonhole.filingPerson?baseViewDto.thisUser.userName:projectXlxPigeonhole.filingPerson}">
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <form id="master_form" class="form-horizontal">
                <div class="row form-group">
                    <div class="col-md-12">
                        <table class="table table-bordered" id="tb_FatherList">
                        </table>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>
<script type="application/javascript">
    var projectXlxPigeonhole = {};

    //申请提交
    projectXlxPigeonhole.commit = function () {
        if (!$("#project_master_form").valid()) {
            return false;
        }
        var data = formParams("project_master_form");
        data.projectId = "${projectInfo.id}";
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectXlxPigeonhole/applyCommit",
            type: "post",
            data: {formData:JSON.stringify(data)},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                }
                else {
                    AlertError("提交数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    //返回修改
    projectXlxPigeonhole.editCommit = function () {
        var data = formParams("project_master_form");

        //返回修改要提交的数据
        var approvalModelDto = formSerializeArray($("#process_variable_form"));
        approvalModelDto.businessDataJson = JSON.stringify(data);
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectXlxPigeonhole/editCommit",
            type: "post",
            data: approvalModelDto,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    AlertSuccess("成功", "提交数据成功", function () {
                        window.close();
                    });
                }
                else {
                    AlertError("提交数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result);
            }
        });
    };
</script>
<script type="text/javascript">
    $(function () {
        projectXlxPigeonholeRecord.prototype.loadDataDicList();
    });
    var projectXlxPigeonholeRecord = function () {};
    projectXlxPigeonholeRecord.prototype = {
        config: function () {
            var data = {};
            data.table = "tb_FatherList";
            data.box = "divPigeonholeRatioBox";
            data.frm = "PigeonholeRatioFrm";
            return data;
        },
        loadDataDicList: function () {
            var cols = [];
            cols.push({field: 'fileName', width: '50%', title: '文件名称'});
            cols.push({
                field: 'hasPaperDatum', width: '15%', title: '有无纸质资料', formatter: function (value,row,index) {
                    var html='<div class="form-check" style="justify-content:left">';
                    html+='<label class="form-check-label">';
                    html+='<input class="form-check-input" type="checkbox" name="hasPaperDatum" value="true" onclick="projectXlxPigeonholeRecord.prototype.saveData2('+index+',this)"';
                    if (value==true) {
                        html += ' checked="checked" ';
                    }
                    html+='><span class="form-check-sign"></span>';
                    html+='</label>';
                    html+='</div>';
                    return html;
                }
            });
            cols.push({
                field: 'hasElectronicDatum', width: '15%', title: '有无电子资料', formatter: function (value,row,index) {
                    var html='<div class="form-check" style="justify-content:left">';
                    html+='<label class="form-check-label">';
                    html+='<input class="form-check-input" type="checkbox" name="hasElectronicDatum" value="true"  onclick="projectXlxPigeonholeRecord.prototype.saveData2('+index+',this)"';
                    if (value==true) {
                        html += ' checked="checked" ';
                    }
                    html+='><span class="form-check-sign"></span>';
                    html+='</label>';
                    html+='</div>';
                    return html;
                }
            });
            $("#" + projectXlxPigeonholeRecord.prototype.config().table).bootstrapTable('destroy');
            TableInit(projectXlxPigeonholeRecord.prototype.config().table, "${pageContext.request.contextPath}/projectXlxPigeonholeRecord/list", cols, {
                projectId: ${projectId},
                pigeonholeId:$('#project_master_form').find('[name=id]').val()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                pageSize: 150,
                pagination: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        saveData2:function(index,_that){
            var row = $("#tb_FatherList").bootstrapTable('getData')[index];
            var tempName = $(_that).attr("name");
            var value = $(_that).prop('checked');;
            row[tempName] = value;
            $.ajax({
                url: "${pageContext.request.contextPath}/projectXlxPigeonholeRecord/save",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(row)},
                success: function (result) {
                    if (result.ret) {
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    }
</script>