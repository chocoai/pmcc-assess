<%--
  主要转换开通桥
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="matchingMainConversion.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>主要转换信息
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <form class="form-horizontal">

            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="MatchingMainConversionList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var matchingMainConversion;
    (function () {
        var flag = true;
        matchingMainConversion = function () {

        };
        matchingMainConversion.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                matchingMainConversion.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "MatchingMainConversionList";
                data.box = "divBoxMatchingMainConversion";
                data.frm = "frmMatchingMainConversion";
                data.type = "mainConversion";//根据ExamineMatchingTrafficTypeEnum 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'name', title: '名称'});
                cols.push({field: 'distanceName', title: '距离'});
                cols.push({field: 'lineName', title: '线路名称'});
                cols.push({field: 'theLine', title: '所在线路'});
                $("#" + matchingMainConversion.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingMainConversion.prototype.config().table, "${pageContext.request.contextPath}/caseMatchingTraffic/getCaseMatchingTrafficList", cols, {
                    type: matchingMainConversion.prototype.config().type,
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

