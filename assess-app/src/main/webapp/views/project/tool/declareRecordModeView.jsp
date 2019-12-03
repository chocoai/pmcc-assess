<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2019-12-3
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="boxDeclareRecordModeObj" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">权证选择控件</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="panel-body">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                        <table class="table table-bordered" id="boxDeclareRecordModeObjList">

                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    var declareRecordModeObj = {} ;

    declareRecordModeObj.targetTable = $("#boxDeclareRecordModeObjList");
    declareRecordModeObj.targetBox = $("#boxDeclareRecordModeObj");

    declareRecordModeObj.loadDeclareRecordTable = function () {
        var data = {projectId:'${projectPlanDetails.projectId}'} ;
        var table = declareRecordModeObj.targetTable ;
        if (table instanceof jQuery){
        } else {
            table = $("#" + table.attr("id"));
        }
        var cols = [];
        cols.push({checkbox: true, width: "5%"});
        cols.push({field: 'name', title: '权证名称', width: "12%"});
        cols.push({field: 'buildingNumber', title: '楼栋号', width: "6%"});
        cols.push({field: 'unit', title: '单元号', width: "6%"});
        cols.push({field: 'ownership', title: '所有权人', width: "6%"});
        cols.push({field: 'seat', title: '坐落', width: "9%"});
        cols.push({field: 'floorArea', title: '证载面积', width: "9%"});
        cols.push({field: 'practicalArea', title: '实际面积', width: "9%"});
        var method = {
            showColumns: true,
            showRefresh: true,
            search: false,
            onLoadSuccess: function () {//加载成功时执行

            },
            onLoadError: function () {

            }
        };
        table.bootstrapTable('destroy');
        TableInit(table, "${pageContext.request.contextPath}/declareRecord/getDeclareRecordList", cols, data, method);
    };

</script>