<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/6/22
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<!-- start: META -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>${thisTitle}</title>
<meta charset="utf-8"/>
<!--[if IE]>
<meta http-equiv='X-UA-Compatible' content="IE=edge,IE=9,IE=8,chrome=1"/><![endif]-->
<meta name="viewport"
      content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description"/>
<meta content="" name="author"/>

<script src="/assets/js/jquery/2.0.3/jquery.min.js"></script>
<script src='${pageContext.request.contextPath}/js/assess-key.js'></script>
<script src='${pageContext.request.contextPath}/js/common.js'></script>
<script src="/assets/js/comm/pmcc-css.js"></script>
<script type="text/javascript">
    jQuery.ajaxSetup({cache: false});//取消ajax中的缓存
</script>