<%--
教育条件
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="matchingEducation.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>教育条件信息</h4>
        <div class="clearfix"></div>
    </div>

    <div class="x_content collapse">
        <div>
        </div>
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="MatchingEducationList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var matchingEducation;
    (function () {
        matchingEducation = function () {

        };
        matchingEducation.prototype = {
            viewInit: function () {
                matchingEducation.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "MatchingEducationList";
                data.box = "divBoxMatchingEducation";
                data.frm = "frmMatchingEducation";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = commonColumn.matchingEducationColumn();
                $("#" + matchingEducation.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingEducation.prototype.config().table, "${pageContext.request.contextPath}/caseMatchingEducation/getCaseMatchingEducationList", cols, {
                    type: matchingEducation.prototype.config().type,
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