<%--
  医养条件
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>医养条件信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="MatchingMedicalList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
    var matchingMedical;
    (function () {
        matchingMedical = function () {

        };
        matchingMedical.prototype = {
            config: function () {
                var data = {};
                data.table = "MatchingMedicalList";
                data.box = "divBoxMatchingMedical";
                data.frm = "frmMatchingMedical";
                data.type = "null";//
                return data;
            },
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            loadDataDicList: function () {
                var cols =commonColumn.matchingMedicalColumn();
                $("#" + matchingMedical.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingMedical.prototype.config().table, "${pageContext.request.contextPath}/basicMatchingMedical/getBootstrapTableVo", cols, {
                    estateId: ${empty basicEstate.id?0:basicEstate.id},
                    approval: true
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },
        }

        //绑定事件
        $('#' + matchingMedical.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            matchingMedical.prototype.loadDataDicList();
        })
    })();
</script>