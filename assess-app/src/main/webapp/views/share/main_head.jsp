<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/6/22
  Time: 11:52
  对头部文件进行分离，并对样式进行调整，清除不需要的样式信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="top_nav" id="pmcc_head">

</div>

<script src='/assets/js/comm/erp-head.js'></script>
<script type="text/javascript">
    var baseViewDtoJson = '${baseViewDtoJson}';
    var sysRemindUnReadCount =${sysRemindUnReadCount};
    var sysRemindUnRead =${sysRemindUnRead};
    $("#pmcc_head").html(PMCC_MAIN.head(baseViewDtoJson, sysRemindUnReadCount, sysRemindUnRead));
    $("#mainHeadProcessMap").click(function (e) {
        e.stopPropagation();
    });
</script>
