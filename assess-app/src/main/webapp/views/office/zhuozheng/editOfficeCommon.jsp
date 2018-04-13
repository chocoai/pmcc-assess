<%@ page language="java"
         import="java.util.*,com.zhuozhengsoft.pageoffice.*,com.zhuozhengsoft.pageoffice.wordwriter.*,java.awt.*"
         pageEncoding="utf-8" %>
<%@ page import="com.copower.pmcc.erp.common.utils.SpringContextUtils" %>
<%@ page import="com.copower.pmcc.erp.common.CommonService" %>
<%
    PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
    poCtrl.setServerPage(request.getContextPath() + "/poserver.zz"); //此行必须
    //添加自定义按钮
    poCtrl.setCaption("文档编辑");
    poCtrl.addCustomToolButton("保存", "Save", 1);
    //设置保存页面
    poCtrl.setSaveFilePage(String.valueOf(request.getAttribute("saveUrl")));
    String url = String.valueOf(request.getAttribute("openUrl"));
    String userAccount = String.valueOf(request.getAttribute("userAccount"));
    OpenModeType openModeType = (OpenModeType)request.getAttribute("openModeType");
    poCtrl.webOpen(url, openModeType, userAccount);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>office在线编辑</title>
    <%--<script src='/pageoffice.js' id='po_js_main'></script>--%>
</head>
<body>
<script type="text/javascript">
    function Save() {
        document.getElementById("PageOfficeCtrl1").WebSave();
        document.getElementById("PageOfficeCtrl1").Close();
    }
</script>
<form id="form1">
    <div style=" width:auto; height:700px;">
        <%=poCtrl.getHtmlCode("PageOfficeCtrl1")%>
    </div>
</form>
</body>
</html>

