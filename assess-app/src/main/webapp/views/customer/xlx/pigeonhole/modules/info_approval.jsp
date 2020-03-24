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
                    项目归档
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
                                <label class="form-control input-full">
                                    <fmt:formatDate value="${projectXlxPigeonhole.filingDate}"
                                                    pattern="yyyy-MM-dd"/>
                                </label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                归档人员
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectXlxPigeonhole.filingPerson}</label>
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
                field: 'hasPaperDatum', width: '15%', title: '纸质资料', formatter: function (value,row,index) {
                    var html='<div class="form-check" style="justify-content:left">';
                    html+='<label class="form-check-label">';
                    html+='<input class="form-check-input" disabled="disabled" type="checkbox" name="hasPaperDatum" value="true"';
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
                field: 'hasElectronicDatum', width: '15%', title: '电子资料', formatter: function (value,row,index) {
                    var html='<div class="form-check" style="justify-content:left">';
                    html+='<label class="form-check-label">';
                    html+='<input class="form-check-input"  disabled="disabled" type="checkbox" name="hasElectronicDatum" value="true" ';
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
                projectId: ${projectInfo.id},
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
        }
    }
</script>