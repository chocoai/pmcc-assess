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
<div id="pmcc-footer"></div>
<script src='/assets/js/atlantis/pmcc-js.js'></script>
<script src='/assets/js/atlantis/erp-footer.js?v=${assessVersion}'></script>
<script type="application/javascript">
    var mainObj = {
        systemMessageWs: '<%=systemMessageWs%>',
        websocket: null,
        currDate: new Date()
    };

    mainObj.storeAccess = function () {
        window.sessionStorage.setItem("${requestAccessKey}", "${baseViewDto.thisUser.sessionId}");
        //设置全局ajax头
        $.ajaxSetup({
            headers: {
                accessToken: window.sessionStorage.getItem("${requestAccessKey}")
            }
        });
    };

    //获取打开页面与当前时间小时差
    mainObj.getDiffHours = function () {
        var ms = new Date().getTime() - mainObj.currDate.getTime();
        if (ms < 0) return 0;
        return (ms/1000/60/60).toFixed(4);
    }


    //页面初始化后
    $(function () {
        $(".tooltips").tooltip();
        $("#pmcc-footer").html(PMCC_MAIN.footer());

        //页面按钮权限
        ErpPermUtil.btnAuth('${el:toJsonString(noPermissions)}', '${el:toJsonString(havePermissions)}');

        WebFont.load({
            google: {"families": ["Lato:300,400,700,900"]},
            custom: {
                "families": ["Flaticon", "Font Awesome 5 Solid", "Font Awesome 5 Regular", "Font Awesome 5 Brands", "simple-line-icons"],
                urls: ['/assets/atlantis/css/fonts.min.css']
            },
            active: function () {
                sessionStorage.fonts = true;
            }
        });
    });
</script>

