<%--
  车位信息
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="estateParking.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>车位信息</h4>
        <div class="clearfix"></div>
    </div>

    <div class="x_content collapse">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="estateParkingList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script >

    var estateParking = {};

    estateParking.loadDataDicList= function () {
        var cols = commonColumn.estateParkingColumn();
        $("#estateParkingList").bootstrapTable('destroy');
        TableInit("estateParkingList", "${pageContext.request.contextPath}/caseEstateParking/getCaseEstateParkingList", cols, {
            estateId: ${empty caseEstate.id?0:caseEstate.id}
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    };

    estateParking.viewInit = function () {
        this.loadDataDicList();
    };
</script>


