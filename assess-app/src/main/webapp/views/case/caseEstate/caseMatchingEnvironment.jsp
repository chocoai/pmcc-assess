<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="matchingEnvironment.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>环境因素信息
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
                    <table class="table table-bordered" id="MatchingEnvironmentList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var matchingEnvironment;
    (function () {
        var flag = true;
        matchingEnvironment = function () {

        };
        matchingEnvironment.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                matchingEnvironment.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "MatchingEnvironmentList";
                data.box = "divBoxMatchingEnvironment";
                data.frm = "frmMatchingEnvironment";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'typeName', title: '环境类型'});
                cols.push({field: 'categoryName', title: '环境类别'});
                cols.push({field: 'influenceDegreeName', title: '影响程度'});
                $("#" + matchingEnvironment.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingEnvironment.prototype.config().table, "${pageContext.request.contextPath}/caseMatchingEnvironment/getCaseMatchingEnvironmentList", cols, {
                    type: matchingEnvironment.prototype.config().type,
                    estateId: ${empty caseEstate.id?0:caseEstate.id},
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
    })();

</script>