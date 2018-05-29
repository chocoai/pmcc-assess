<%@ page language="java"
         import="com.copower.pmcc.erp.common.CommonService,com.copower.pmcc.erp.common.utils.SpringContextUtils,com.zhuozhengsoft.pageoffice.OpenModeType"
         pageEncoding="utf-8" %>
<%@ page import="com.zhuozhengsoft.pageoffice.PageOfficeCtrl" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%
    PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
    poCtrl.setServerPage(request.getContextPath() + "/poserver.zz"); //此行必须
    //添加自定义按钮
    poCtrl.setCaption("文档在线查看");
    poCtrl.setCustomToolbar(false);
    CommonService commonService = SpringContextUtils.getBean(CommonService.class);
    String url = String.format("/%s/attachment/downloadFileFromServer?id=%s", commonService.getCurrentSelectAppKey(), request.getAttribute("attachmentId"));
    OpenModeType openModeType = (OpenModeType)request.getAttribute("openModeType");
    poCtrl.webOpen(url, openModeType, "admin");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>文档在线查看</title>
    <%--<script src='/pageoffice.js' id='po_js_main'></script>--%>
</head>
<body>
<form id="form1">
    <div style=" width:auto; height:700px;">
        <%=poCtrl.getHtmlCode("PageOfficeCtrl1")%>
    </div>
</form>
</body>
</html>

