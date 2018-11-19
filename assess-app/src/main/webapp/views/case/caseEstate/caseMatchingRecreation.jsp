<%--
  休闲娱乐
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="matchingRecreation.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>休闲娱乐信息
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
                    <table class="table table-bordered" id="MatchingRecreationList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var matchingRecreation;
    (function () {
        var flag = true;
        matchingRecreation = function () {

        };
        matchingRecreation.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                matchingRecreation.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "MatchingRecreationList";
                data.box = "divBoxMatchingRecreation";
                data.frm = "frmMatchingRecreation";
                data.type = "matchingRecreation";// 根据 ExamineMatchingLeisurePlaceTypeEnum 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'name', title: '休闲娱乐名称'});
                cols.push({field: 'categoryName', title: '休闲娱乐类别'});
                cols.push({field: 'distanceName', title: '休闲娱乐距离'});
                $("#" + matchingRecreation.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingRecreation.prototype.config().table, "${pageContext.request.contextPath}/caseMatchingLeisurePlace/getCaseMatchingLeisurePlaceList", cols, {
                    type: matchingRecreation.prototype.config().type,
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