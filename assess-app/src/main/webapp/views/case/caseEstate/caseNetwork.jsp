<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="x_panel collapse">
    <div class="x_title collapse-link" onclick="estateNetwork.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>通讯网络信息
        </h3>
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
                    <table class="table table-bordered" id="examineEstateNetworkList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var estateNetwork;
    (function () {
        var flag = true;
        estateNetwork = function () {

        };
        estateNetwork.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {

                estateNetwork.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "examineEstateNetworkList";
                data.box = "divBoxExamineEstateNetwork";
                data.frm = "frmExamineEstateNetwork";
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'name', title: '通讯名称'});
                cols.push({field: 'serviceContent', title: '服务内容'});
                cols.push({field: 'indexParameter', title: '通信网络指标参数'});
                $("#" + estateNetwork.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateNetwork.prototype.config().table, "${pageContext.request.contextPath}/caseEstateNetwork/getCaseEstateNetworkList", cols, {
                    estateId: ${empty caseEstate.id?0:caseEstate.id},
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
    })();

</script>



</html>
