<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    .CodeMirror {border: 1px solid #ebedf2;}
</style>

<!--下面两个是使用Code Mirror必须引入的-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/codemirror/lib/codemirror.css">
<script src="${pageContext.request.contextPath}/assets/codemirror/lib/codemirror.js"></script>
<!--Java代码高亮必须引入-->
<script src="${pageContext.request.contextPath}/assets/codemirror/lib/clike.js"></script>
<!--引入css文件，用以支持主题-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/codemirror/theme/eclipse.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/codemirror/theme/dracula.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/codemirror/theme/xq-light.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/codemirror/theme/seti.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/codemirror/theme/3024-day.css">
<!--引入js，绑定Vim-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/codemirror/addon/dialog/dialog.css">
<script src="${pageContext.request.contextPath}/assets/codemirror/keymap/vim.js"></script>
<script src="${pageContext.request.contextPath}/assets/codemirror/addon/search/searchcursor.js"></script>
<!--改善vim输入命令时的样式-->
<script src="${pageContext.request.contextPath}/assets/codemirror/addon/dialog/dialog.js"></script>
<!--支持代码折叠-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/codemirror/addon/fold/foldgutter.css"/>
<script src="${pageContext.request.contextPath}/assets/codemirror/addon/fold/foldcode.js"></script>
<script src="${pageContext.request.contextPath}/assets/codemirror/addon/fold/foldgutter.js"></script>
<script src="${pageContext.request.contextPath}/assets/codemirror/addon/fold/brace-fold.js"></script>
<script src="${pageContext.request.contextPath}/assets/codemirror/addon/fold/comment-fold.js"></script>
<!--全屏模式-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/codemirror/addon/display/fullscreen.css">
<script src="${pageContext.request.contextPath}/assets/codemirror/addon/display/fullscreen.js"></script>
<!--括号匹配-->
<script src="${pageContext.request.contextPath}/assets/codemirror/addon/edit/matchbrackets.js"></script>
<!--自动补全-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/codemirror/addon/hint/show-hint.css">
<script src="${pageContext.request.contextPath}/assets/codemirror/addon/hint/show-hint.js"></script>
<script src="${pageContext.request.contextPath}/assets/codemirror/addon/hint/anyword-hint.js"></script>

<script src="${pageContext.request.contextPath}/assets/codemirror/lib/javascript.js"></script>
<script src="${pageContext.request.contextPath}/assets/codemirror/addon/selection/active-line.js"></script>
