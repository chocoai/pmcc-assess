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
<div id="pmcc-footer">

</div>
<script src='/assets/js/comm/pmcc.js'></script>
<script src='/assets/js/comm/erp-footer.js'></script>
<script src='${pageContext.request.contextPath}/js/base-dic-utils.js'></script>
<script type="application/javascript">
    var mainObj = {
        systemMessageWs: '<%=systemMessageWs%>',
        websocket: null
    };
    $("#pmcc-footer").html(PMCC_MAIN.footer);
    //message init

    $(function () {
        $(".tooltips").tooltip();
        mainObj.fetchMessage;

    });
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
</script>

