<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/6/22
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
    String server = request.getServerName();
    int port = request.getServerPort();
    String basePath = String.format("%s://%s:%s%s", request.getScheme(), server, port, contextPath);

    //日志请求路径
    String systemMessageWs = String.format("ws://%s:%s/sysMessage", server, port);

%>
<footer>
    <div class="pull-right">
        技术支持 <a href="https://www.copowercpa.com">四川协合魔方科技有限公司</a>
    </div>
    <div class="clearfix"></div>
</footer>
<!-- loading modal -->
<div id="loading-progress" class="modal fade bs-example-modal-sm" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="progress progress-striped active">
            <div class="progress-bar progress-bar-success" style="width: 100%;"><span id="loading-progress-info-text">正在加载...</span></div>
        </div>
    </div>
</div>
<!-- lading modal end -->

<div class="footer clearfix">
    <div class="footer-inner">
    </div>
</div>

<div id="select_dept_tree_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">部门选择</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">

                        <div id="mode_dept_tree"></div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" id="model_dept_tree_ok">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<!--附件编辑历史记录-->
<div id="attachmentKeepModal" class="modal fade bs-example-modal-sm" data-backdrop="static" aria-hidden="true"
     role="dialog" data-keyboard="false" tabindex="1" style="display: none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">存档列表</h4>
            </div>
            <div class="x_content">
                <table id="attachmentKeepList">
                </table>
            </div>
        </div>
    </div>
</div>


<script src='/assets/js/comm/pmcc.js'></script>
<script type="application/javascript">
    var mainObj = {
        systemMessageWs: '<%=systemMessageWs%>',
        websocket: null
    };

    var datepickerOptions = {
        date: {
            autoclose: true,
            minView: "month",
            todayBtn: "linked",
            language: "zh-CN",
            clearBtn: true
        },
        dateTime: {
            autoclose: true,
            todayBtn: "linked",
            language: "zh-CN",
            minView: 0,
            clearBtn: true
        }
    };

    mainObj.fetchMessage = function () {
        //先关闭之前的websocket
        if (mainObj.websocket) {
            mainObj.websocket.close();
        }
        //新实例化一个websocket
        mainObj.websocket = new WebSocket(mainObj.systemMessageWs);
        mainObj.websocket.onmessage = function (event) {
            // 接收到消息
            var message = event.data;
            toastr.options = {
                closeButton: true,
                positionClass: "toast-bottom-right",
                showDuration: "300",
                hideDuration: "1000",
                timeOut: "10000",
                extendedTimeOut: "1000"
            };
            toastr.info("系统消息:" + message);
        };
    };


    $(function () {
        //message init
        mainObj.fetchMessage();

        Main.init();
        //日期选择
        var objs = $('.dbdate');
        $.each(objs, function (i, obj) {
            initDatetimepickerDate(obj);
        });
        var objsS = $('.dbtime');
        $.each(objsS, function (i, obj) {
            initDatetimepickerTime(obj);
        });

        //模态框加拖动,此拖到影响到模态窗口
        $(document).on("show.bs.modal", ".modal", function () {
            if ($(this).attr("id") != "loading-progress") {
                $(this).draggable({
                    handle: ".modal-header"   // 只能点击头部拖动
                });
                $(this).css({"overflow": "hidden", "left": "", "top": ""}); // 防止出现滚动条，出现的话，你会把滚动条一起拖着走的
                $(this).find(".modal-header").css({"cursor": "move"});
            }
        });
        $(".tooltips").tooltip();
    });

    function initDatetimepickerDate(obj, param) {
        var defaluts = datepickerOptions.date;
        if (param) {
            defaluts = $.extend({}, defaluts, param);
        }
        $(obj).datetimepicker(defaluts);
        $(obj).attr("readonly", "readonly");
    }

    function initDatetimepickerTime(obj, param) {
        var defaluts = datepickerOptions.dateTime;
        if (param) {
            defaluts = $.extend({}, defaluts, param);
        }
        $(obj).datetimepicker(defaluts);
        $(obj).attr("readonly", "readonly");
    }

    //时间区间选择
    function dateSectionChoose(objStart, objEnd) {
        $(objStart).click(function () {
            $(objStart).datetimepicker('setEndDate', formatDate($(objEnd).val(), true));
        })
        $(objEnd).click(function () {
            $(objEnd).datetimepicker('setStartDate', formatDate($(objStart).val(), true));
        })
    }

    function model_dept_onNodeChecked(data) {
        if (data.nodes != null) {
            var arrayInfo = data.nodes;
            for (var i = 0; i < arrayInfo.length; i++) {
                $('#mode_dept_tree').treeview('checkNode', [arrayInfo[i], {silent: true}]);
                $('#mode_dept_tree').treeview('selectNode', [arrayInfo[i], {silent: true}]);
                model_dept_onNodeChecked(arrayInfo[i]);
            }
        }
    }
    function model_dept_onNodeUnchecked(data) {
        if (data.nodes != null) {
            var arrayInfo = data.nodes;
            for (var i = 0; i < arrayInfo.length; i++) {
                $('#mode_dept_tree').treeview('uncheckNode', [arrayInfo[i], {silent: true}]);
                $('#mode_dept_tree').treeview('unselectNode', [arrayInfo[i], {silent: true}]);
                model_dept_onNodeUnchecked(arrayInfo[i]);
            }
        }
    }


    //参数表示在哪个公司或部门下选择相关人员,若为全部则传入0
    function loadSelectDept(currOrgId, currVlaue, single, callback) {
        Loading.progressShow();
        initBaseTreeView("mode_dept_tree", "/department/getDepartmentTree", {pid: currOrgId}, false, function (objs) {
            if (currVlaue) {
                treeView_setValue("mode_dept_tree", currVlaue)
            }
            $('#model_dept_tree_ok').unbind("click");
            $("#model_dept_tree_ok").click(function () {
                model_dept_tree_getVlaue(callback);
            });
            Loading.progressHide();
            $('#select_dept_tree_modal').modal({backdrop: 'static', keyboard: false});
        })
    }

    function model_dept_tree_getVlaue(fn)//取当前选中节点
    {
        var nodes = $('#mode_dept_tree').treeview('getSelected');
        $('#select_dept_tree_modal').modal('hide');
        fn(nodes);
    }


</script>