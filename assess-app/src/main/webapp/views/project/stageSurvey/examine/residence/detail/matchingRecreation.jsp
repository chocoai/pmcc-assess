<%--
  休闲娱乐
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>休闲娱乐信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="MatchingRecreationList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script>
    var matchingRecreation;
    (function () {
        matchingRecreation = function () {

        };
        matchingRecreation.prototype = {
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
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
                var cols = commonColumn.matchingRecreationColumn();
                $("#" + matchingRecreation.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingRecreation.prototype.config().table, "${pageContext.request.contextPath}/basicMatchingLeisurePlace/getBootstrapTableVo", cols, {
                    type: matchingRecreation.prototype.config().type,
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
            },
        }

        //绑定事件
        $('#' + matchingRecreation.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            matchingRecreation.prototype.loadDataDicList();
        })
    })();
</script>