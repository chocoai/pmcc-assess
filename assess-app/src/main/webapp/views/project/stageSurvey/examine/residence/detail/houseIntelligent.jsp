<%--
 电力通讯网络
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="x_panel" id="industryIntelligent">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>电力通讯网络</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseIntelligentList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>


    var houseIntelligent;
    (function () {
        houseIntelligent = function () {

        };
        houseIntelligent.prototype = {
            config: function () {
                var data = {};
                data.table = "HouseIntelligentList";
                data.box = "divBoxHouseIntelligent";
                data.frm = "frmHouseIntelligent";
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
                var cols = commonColumn.houseIntelligentColumn();
                $("#" + houseIntelligent.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseIntelligent.prototype.config().table, "${pageContext.request.contextPath}/basicHouseIntelligent/getBootstrapTableVo", cols, {
                    houseId: '${empty basicHouse.id?0:basicHouse.id}',
                    approval: true
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
        $('#' + houseIntelligent.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            houseIntelligent.prototype.loadDataDicList();
        })
    })();

</script>