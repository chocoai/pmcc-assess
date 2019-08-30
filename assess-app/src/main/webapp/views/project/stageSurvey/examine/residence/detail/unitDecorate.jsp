<%--
 楼栋内装
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>楼栋内装情况</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="ExamineUnitDecorateListDetail">
            <!-- cerare document add ajax data-->
        </table>
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
        $('#' + unitDecorateDetail.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            unitDecorateDetail.prototype.loadDataDicList();
        })
    })();
</script>
