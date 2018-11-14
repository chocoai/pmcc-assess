<%--
  医养条件
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="matchingMedical.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>医养条件信息
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
                    <table class="table table-bordered" id="MatchingMedicalList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var matchingMedical;
    (function () {
        var flag = true;
        matchingMedical = function () {

        };
        matchingMedical.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            viewInit: function () {
                matchingMedical.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "MatchingMedicalList";
                data.box = "divBoxMatchingMedical";
                data.frm = "frmMatchingMedical";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'bedNumber', title: '床位数'});
                cols.push({field: 'distanceName', title: '医养条件距离'});
                cols.push({field: 'organizationLevelName', title: '医养条件机构等级'});
                $("#" + matchingMedical.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingMedical.prototype.config().table, "${pageContext.request.contextPath}/caseMatchingMedical/getCaseMatchingMedicalList", cols, {
                    type: matchingMedical.prototype.config().type,
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

