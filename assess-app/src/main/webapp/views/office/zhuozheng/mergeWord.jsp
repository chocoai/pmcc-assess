<%@ page language="java"
         import="com.copower.pmcc.erp.common.CommonService,com.copower.pmcc.erp.common.utils.SpringContextUtils,com.zhuozhengsoft.pageoffice.OpenModeType"
         pageEncoding="utf-8" %>
<%@ page import="com.zhuozhengsoft.pageoffice.PageOfficeCtrl" %>
<%@ page import="com.zhuozhengsoft.pageoffice.wordwriter.DataRegion" %>
<%@ page import="com.zhuozhengsoft.pageoffice.wordwriter.WordDocument" %>
<%@ page import="org.apache.commons.collections.CollectionUtils" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%
    PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
    poCtrl.setCaption("标书文件合并");
    poCtrl.setServerPage(request.getContextPath() + "/poserver.zz"); //此行必须
    //添加自定义按钮
    poCtrl.addCustomToolButton("保存", "Save", 1);
    //设置保存页面
    CommonService commonService = SpringContextUtils.getBean(CommonService.class);
    poCtrl.setSaveFilePage(String.format("/%s/zhuozheng/saveFile", commonService.getCurrentSelectAppKey()));
    WordDocument worddoc = new WordDocument();
    List<String> mergeFileList = (List<String>) request.getAttribute("mergeFileList");
    if (CollectionUtils.isNotEmpty(mergeFileList)) {
        for (int i = 0; i < mergeFileList.size(); i++) {
            try {
                DataRegion mydr = worddoc.openDataRegion("PO_" + i);
                mydr.setValue(String.format("[word]/%s/attachment/downloadFileFromServer?id=%s[/word]",commonService.getCurrentSelectAppKey(), mergeFileList.get(i)));
            } catch (Exception e) {
                //
            }
        }
    }
    poCtrl.setWriter(worddoc);
    String url = String.format("/%s/attachment/downloadFileFromServer?id=%s", commonService.getCurrentSelectAppKey(), request.getAttribute("attachmentId"));
    poCtrl.webOpen(url, OpenModeType.docNormalEdit, "admin");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <meta charset="utf-8">
    <title>word文档合并</title>
    <script src="/assets/js/jquery/1.10.2/jquery.min.js"></script>
    <%--<script src='/pageoffice.js' id='po_js_main'></script>--%>
</head>
<body>
<input type="hidden" id="attachmentId" name="attachmentId" value="<%=request.getAttribute("attachmentId")%>"/>
<script type="text/javascript">
    function Save() {
        document.getElementById("PageOfficeCtrl1").WebSave();
        document.getElementById("PageOfficeCtrl1").Close();
    }
</script>
<form id="form1">
    <input id="isKeep" name="isKeep" type="hidden" value="false"/>
    <div style=" width:auto; height:700px;">
        <%=poCtrl.getHtmlCode("PageOfficeCtrl1")%>
    </div>
</form>
</body>
</html>

