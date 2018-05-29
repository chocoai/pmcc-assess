<%@ page language="java"
         import="com.copower.pmcc.erp.common.utils.SysUtils,com.zhuozhengsoft.pageoffice.PDFCtrl"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>
<%
    PDFCtrl poCtrl1 = new PDFCtrl(request);
    poCtrl1.setServerPage(request.getContextPath() + "/poserver.zz"); //此行必须
    //隐藏自定义工具栏
    poCtrl1.setCaption("pdf查看");
    poCtrl1.addCustomToolButton("打印", "Print()", 6);
    poCtrl1.addCustomToolButton("隐藏/显示书签", "SetBookmarks()", 0);
    poCtrl1.addCustomToolButton("-", "", 0);
    poCtrl1.addCustomToolButton("实际大小", "SetPageReal()", 16);
    poCtrl1.addCustomToolButton("适合页面", "SetPageFit()", 17);
    poCtrl1.addCustomToolButton("适合宽度", "SetPageWidth()", 18);
    poCtrl1.addCustomToolButton("-", "", 0);
    poCtrl1.addCustomToolButton("首页", "FirstPage()", 8);
    poCtrl1.addCustomToolButton("上一页", "PreviousPage()", 9);
    poCtrl1.addCustomToolButton("下一页", "NextPage()", 10);
    poCtrl1.addCustomToolButton("尾页", "LastPage()", 11);
    poCtrl1.addCustomToolButton("-", "", 0);
    String fileResultPath = (String) request.getAttribute("fileResultPath");
    String prefix = "";
    if (SysUtils.isLinux())
        prefix = "file://";
    poCtrl1.webOpen(prefix + fileResultPath);
    poCtrl1.setTagId("PDFCtrl1"); //此行必须
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <meta charset="utf-8">
    <title>pdf在线查看</title>
    <script src="/assets/js/jquery/1.10.2/jquery.min.js"></script>
    <script src='/pageoffice.js' id='po_js_main'></script>
</head>
<body>

<script type="text/javascript">
    function AfterDocumentOpened() {
        //alert(document.getElementById("PDFCtrl1").Caption);
    }
    function SetBookmarks() {
        document.getElementById("PDFCtrl1").BookmarksVisible = !document.getElementById("PDFCtrl1").BookmarksVisible;
    }

    function Print() {
        document.getElementById("PDFCtrl1").ShowDialog(4);
    }
    function SwitchFullScreen() {
        document.getElementById("PDFCtrl1").FullScreen = !document.getElementById("PDFCtrl1").FullScreen;
    }
    function SetPageReal() {
        document.getElementById("PDFCtrl1").SetPageFit(1);
    }
    function SetPageFit() {
        document.getElementById("PDFCtrl1").SetPageFit(2);
    }
    function SetPageWidth() {
        document.getElementById("PDFCtrl1").SetPageFit(3);
    }
    function ZoomIn() {
        document.getElementById("PDFCtrl1").ZoomIn();
    }
    function ZoomOut() {
        document.getElementById("PDFCtrl1").ZoomOut();
    }
    function FirstPage() {
        document.getElementById("PDFCtrl1").GoToFirstPage();
    }
    function PreviousPage() {
        document.getElementById("PDFCtrl1").GoToPreviousPage();
    }
    function NextPage() {
        document.getElementById("PDFCtrl1").GoToNextPage();
    }
    function LastPage() {
        document.getElementById("PDFCtrl1").GoToLastPage();
    }
    function RotateRight() {
        document.getElementById("PDFCtrl1").RotateRight();
    }
    function RotateLeft() {
        document.getElementById("PDFCtrl1").RotateLeft();
    }
</script>
<form id="form1">
    <div style=" width:auto; height:700px;">
        <po:PDFCtrl id="PDFCtrl1">
        </po:PDFCtrl>
    </div>
</form>
</body>
</html>

