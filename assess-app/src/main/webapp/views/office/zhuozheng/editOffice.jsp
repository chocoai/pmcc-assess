<%@ page language="java"
         import="com.zhuozhengsoft.pageoffice.OpenModeType,com.zhuozhengsoft.pageoffice.PageOfficeCtrl"
         pageEncoding="utf-8" %>
<%
    PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
    poCtrl.setServerPage(request.getContextPath() + "/poserver.zz"); //此行必须
    //添加自定义按钮
    poCtrl.setCaption("文档编辑");
    poCtrl.addCustomToolButton("保存", "Save", 1);
    //设置保存页面
    poCtrl.setSaveFilePage(String.format("/%s/zhuozheng/saveFile", request.getContextPath()));
    String url = String.format("/%s/attachment/downloadFileFromServer?id=%s", request.getContextPath(), request.getAttribute("attachmentId"));
    OpenModeType openModeType = (OpenModeType)request.getAttribute("openModeType");
    poCtrl.webOpen(url, openModeType, "admin");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>office在线编辑</title>
    <script src="/assets/js/jquery/2.0.3/jquery.min.js"></script>
    <%--<script src='/pageoffice.js' id='po_js_main'></script>--%>
</head>
<body>
<script type="text/javascript">
    function Save() {
        document.getElementById("PageOfficeCtrl1").WebSave();
        updateFileInfo();
        document.getElementById("PageOfficeCtrl1").Close();
    }
    //更新附件信息
    function updateFileInfo() {
        $.ajax({
            url: "${pageContext.request.contextPath}/zhuozheng/updateFileInfo",
            type: "post",
            dataType: "json",
            data: {
                id: $("#attachmentId").val()
            },
            success: function (result) {

            }
        })
    }
</script>
<form id="form1">
    <input id="attachmentId" name="attachmentId" type="hidden" value="<%=request.getAttribute("attachmentId")%>"/>
    <input id="isKeep" name="isKeep" type="hidden" value="true"/>
    <div style=" width:auto; height:700px;">
        <%=poCtrl.getHtmlCode("PageOfficeCtrl1")%>
    </div>
</form>
</body>
</html>

