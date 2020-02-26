<%--
 楼栋内装
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    楼栋内装情况
                </div>
                <div class="card-tools">
                    <button class="btn btn-icon btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body" style="display: none">
            <form class="form-horizontal">
                <table class="table table-bordered" id="ExamineUnitDecorateListDetail">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>


<script>
    var unitDecorateDetail;
    (function () {
        unitDecorateDetail = function () {

        };
        unitDecorateDetail.prototype = {
            config: function () {
                var data = {};
                data.table = "ExamineUnitDecorateListDetail";
                data.box = "divBoxExamineUnitDecorate";
                data.frm = "frmExamineUnitDecorate";
                return data;
            },
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            loadDataDicList: function () {
                var cols = commonColumn.unitDecorateColumn();
                $("#" + unitDecorateDetail.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitDecorateDetail.prototype.config().table, "${pageContext.request.contextPath}/basicUnitDecorate/getBootstrapTableVo", cols, {
                    unitId: ${empty basicUnit.id?0:basicUnit.id},
                    approval:true
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            }
        }

        //绑定事件
        $('#' + unitDecorateDetail.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
            unitDecorateDetail.prototype.loadDataDicList();
        })
    })();
</script>
