<%--
 户型
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>户型</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="UnitHuxingList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>


    var unitHuxing;
    (function () {
        unitHuxing = function () {
        };
        unitHuxing.prototype = {
            config: function () {
                var data = {};
                data.table = "UnitHuxingList";
                data.box = "divBoxUnitHuxing";
                data.frm = "frmUnitHuxing";
                data.unitHuxingFileIDFildName = "house_latest_family_planV";
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.unitHuxingColumn();
                $("#" + unitHuxing.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitHuxing.prototype.config().table, "${pageContext.request.contextPath}/basicUnitHuxing/getBootstrapTableVo", cols, {
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
            },
            isNotNull: function (item) {
                if (item) {
                    return true;
                }
                return false;
            }
        }

        //绑定事件
        $('#' + unitHuxing.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            unitHuxing.prototype.loadDataDicList();
        })
    })();


</script>