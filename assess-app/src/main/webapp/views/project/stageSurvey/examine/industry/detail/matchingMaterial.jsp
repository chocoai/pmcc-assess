<%--
  原材料及销售条件
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>原料供应及销售条件信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="MatchingMaterialList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>


<script>

    var matchingMaterial;
    (function () {
        matchingMaterial = function () {

        };
        matchingMaterial.prototype = {
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            config: function () {
                var data = {};
                data.table = "MatchingMaterialList";
                data.box = "divBoxMatchingMaterial";
                data.frm = "frmMatchingMaterial";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.matchingMaterialColumn();
                $("#" + matchingMaterial.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingMaterial.prototype.config().table, "${pageContext.request.contextPath}/basicMatchingMaterial/getBootstrapTableVo", cols, {
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
        $('#' + matchingMaterial.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            matchingMaterial.prototype.loadDataDicList();
        })
    })();


</script>
