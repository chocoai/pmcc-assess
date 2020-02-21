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
<%@ taglib prefix="el" uri="assess.pmcc.com/el-common" %>
<title>${thisTitle}</title>
<meta charset="utf-8"/>
<meta name="viewport"
      content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description"/>
<meta content="" name="author"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport'/>
<script src="/assets/js/atlantis/pmcc-css.js?v=1.1"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.css">
<script src='${pageContext.request.contextPath}/js/assess.key.js?v=${assessVersion}'></script>
<script src='${pageContext.request.contextPath}/js/assess.upload.key.js?v=${assessVersion}'></script>
<script src='${pageContext.request.contextPath}/js/assess.default.js?v=${assessVersion}'></script>
<script src='${pageContext.request.contextPath}/js/common.js?v=${assessVersion}'></script>
<script src='${pageContext.request.contextPath}/js/common.column.js?v=${assessVersion}'></script>
<script src='${pageContext.request.contextPath}/js/ajaxhook.min.js?v=${assessVersion}'></script>
<script src="${pageContext.request.contextPath}/assets/layer/layer.js?v=${assessVersion}"></script>

<script type="text/javascript">
    jQuery.ajaxSetup({
        cache: false,
        type: 'post',
        dataType: 'json'
    });//取消ajax中的缓存

    hookAjax({
        //拦截回调
        onreadystatechange:function(xhr){
            //console.log("onreadystatechange called: %O",xhr)
        },
        onload:function(xhr){
            //console.log("onload called: %O",xhr)
        },
        //拦截函数
        open:function(arg){
            //console.log("open called: method:%s,url:%s,async:%s",arg[0],arg[1],arg[2])
        }
    })
</script>