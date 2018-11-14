<%--
  原材料及销售条件
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="matchingMaterial.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>原料供应及销售条件信息
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="MatchingMaterialList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var matchingMaterial;
    (function () {
        var flag = true;
        matchingMaterial = function () {

        };
        matchingMaterial.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                matchingMaterial.prototype.loadDataDicList();
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
                var cols = [];
                cols.push({field: 'name', title: '名称'});
                cols.push({field: 'categoryName', title: '类别'});
                cols.push({field: 'scaleName', title: '规模'});
                cols.push({field: 'distanceName', title: '距离'});
                $("#" + matchingMaterial.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingMaterial.prototype.config().table, "${pageContext.request.contextPath}/caseMatchingMaterial/getCaseMatchingMaterialList", cols, {
                    type: matchingMaterial.prototype.config().type,
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

