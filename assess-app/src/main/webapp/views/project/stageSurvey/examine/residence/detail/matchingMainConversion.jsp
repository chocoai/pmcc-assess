<%--
  主要转换开通桥
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>主要转换互通桥信息</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <table class="table table-bordered" id="MatchingMainConversionList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>


<script>


    var matchingMainConversion;
    (function () {
        matchingMainConversion = function () {

        };
        matchingMainConversion.prototype = {
            config: function () {
                var data = {};
                data.table = "MatchingMainConversionList";
                data.box = "divBoxMatchingMainConversion";
                data.frm = "frmMatchingMainConversion";
                data.type = "mainConversion";//根据ExamineMatchingTrafficTypeEnum 配置
                return data;
            },
            isNotBlank: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            loadDataDicList: function () {
                var cols = commonColumn.matchingMainConversionColumn();
                $("#" + matchingMainConversion.prototype.config().table).bootstrapTable('destroy');
                TableInit(matchingMainConversion.prototype.config().table, "${pageContext.request.contextPath}/basicMatchingTraffic/getBootstrapTableVo", cols, {
                    type: matchingMainConversion.prototype.config().type,
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
        $('#' + matchingMainConversion.prototype.config().table).closest('.x_panel').find('.x_title').bind('click', function () {
            matchingMainConversion.prototype.loadDataDicList();
        })
    })();

</script>
