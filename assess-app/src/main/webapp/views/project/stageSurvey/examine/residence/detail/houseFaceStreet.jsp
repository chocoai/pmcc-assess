<%--
 临街（路）状况
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>临街（路）状况</h4>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="HouseFaceStreetList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>

    var houseFaceStreet;
    (function () {
        houseFaceStreet = function () {

        };
        houseFaceStreet.prototype = {
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            config: function () {
                var data = {};
                data.table = "HouseFaceStreetList";
                data.box = "divBoxHouseFaceStreet";
                data.frm = "frmHouseFaceStreet";
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.houseFaceStreetColumn();
                $("#" + houseFaceStreet.prototype.config().table).bootstrapTable('destroy');
                TableInit(houseFaceStreet.prototype.config().table, "${pageContext.request.contextPath}/basicHouseFaceStreet/getBootstrapTableVo", cols, {
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
        $('#' + houseFaceStreet.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            houseFaceStreet.prototype.loadDataDicList();
        })
    })();


</script>
