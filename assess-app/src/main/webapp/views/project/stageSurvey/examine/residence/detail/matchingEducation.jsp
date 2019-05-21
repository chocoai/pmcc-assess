<%--
教育条件
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>教育条件信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="MatchingEducationList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
    var matchingEducation;
    (function () {
        matchingEducation = function () {

        };
        matchingEducation.prototype = {
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
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
                TableInit(matchingEducation.prototype.config().table, "${pageContext.request.contextPath}/basicMatchingEducation/getBootstrapTableVo", cols, {
                    estateId: ${empty basicEstate.id?0:basicEstate.id},
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
        $('#' + matchingEducation.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            matchingEducation.prototype.loadDataDicList();
        })
    })();
</script>
