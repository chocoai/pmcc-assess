<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--tab模板-->
<script type="text/html" id="tabTemp">
    <li class="{active}">
        <a href="#panel_tab{i}" data-toggle="tab" onclick="toggleFirstArrow({i});">
            {groupNameCn}
        </a>
    </li>
</script>

<!--tab内容-->
<script type="text/html" id="tabContentTemp">
    <div class="tab-pane in {active}" id="panel_tab{i}">
        <div class="panel-group accordion-custom accordion-teal"
             id="accordion{i}">
        </div>
    </div>
</script>

<!--流程信息模板-->
<script type="text/html" id="tabProcessTemp">
    <div class="x_panel">
        <div class="x_title">
            <h4 class="panel-title">
                <a class="accordion-toggle collapsed" data-parent="#accordion{i}"
                   data-toggle="collapse" id="arrow{i}{k}"
                   href="#collapse{i}{k}">
                    <i class="icon-arrow"></i>
                    {processNameCn}&nbsp;{total}
                </a>
            </h4>
            <div class="panel-tools">
                <a class="btn btn-xs btn-link" href="javascript:{refreshFunc};">
                    <i class="fa fa-refresh">刷新</i>
                </a>
            </div>
        </div>

        <div id="collapse{i}{k}"
             class="panel-collapse collapse">
            <div class="x_content">
                <table id="processList{i}{k}">
                </table>
            </div>
        </div>
    </div>

</script>
<script type="text/javascript">
    //根据数据创建标签内容
    function generateLabel(opt) {
        var defaults = {
            "contentId": null,
            "data": {},
            "success": function () {

            },
            refreshFunc: "",
            "tabIndex": opt.tabIndex || 0,
            "accIndex": opt.accIndex || 0
        };
        var options = $.extend({}, defaults, opt);
        $("#" + options.contentId).empty();
        var tabHtml = '<ul class="nav nav-tabs tab-bricky">';
        var tabContentHtml = '<div class="tab-content">';

        $.each(options.data, function (i, item) {
            var tabTemp = $("#tabTemp").html();
            var tabContentTemp = $("#tabContentTemp").html();
            if (i == options.tabIndex) {
                tabTemp = tabTemp.replace("{active}", "active");
                tabContentTemp = tabContentTemp.replace("{active}", "active");
            } else {
                tabTemp = tabTemp.replace("{active}", "");
                tabContentTemp = tabContentTemp.replace("{active}", "");
            }
            tabContentTemp = tabContentTemp.replace(/\{i\}/g, i);
            tabTemp = tabTemp.replace(/\{i\}/g, i).replace("{groupNameCn}", item.groupNameCn);
            tabHtml += tabTemp;
            tabContentHtml += tabContentTemp;

        })
        tabHtml += '</ul>';
        tabContentHtml += '</div>';
        $("#" + options.contentId).append(tabHtml).append(tabContentHtml);
        $.each(options.data, function (i, item) {
            $.each(item.processSimpleDtoList, function (k, processItem) {
                var tabProcessTemp = $("#tabProcessTemp").html();
                tabProcessTemp = tabProcessTemp.replace(/\{i\}/g, i).replace(/\{k\}/g, k)
                    .replace("{processNameCn}", processItem.processNameCn);
                if (processItem.total) {
                    tabProcessTemp = tabProcessTemp.replace("{total}", '<span class="badge badge-danger"' +
                        ' id="badge' + processItem.boxId + '">' + processItem.total + '</span>');

                } else {
                    tabProcessTemp = tabProcessTemp.replace("{total}", "");
                }
                tabProcessTemp = tabProcessTemp.replace("{refreshFunc}", options.refreshFunc);
                $("#accordion" + i).append(tabProcessTemp);
                $("#" + options.contentId).off("show.bs.collapse", "#collapse" + i + k);//移除绑定事件
                $("#" + options.contentId).on('show.bs.collapse', "#collapse" + i + k, function () {
                    options.success(processItem, i, k);
                })
            })
        })
        //开启一个accordion
        //$("#collapse" + options.tabIndex + options.accIndex).collapse('toggle');
        $("#arrow" + options.tabIndex + options.accIndex).get(0).click();
    }
    //刷新
    function bootTbRefresh() {
        TableReload(currProcess.currTableId);
    }

    //展开第一个流程
    function toggleFirstArrow(i) {
        var arrow=$("#arrow" + i + 0);
        if(arrow&&arrow.hasClass("collapsed")){
            arrow.get(0).click();
        }
    }
</script>