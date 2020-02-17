<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/6/22
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-3 left_col" id="pmcc_navigation">

</div>

<script src='/assets/js/atlantis/erp-navigation.js?v=${assessVersion}'></script>
<script type="text/javascript">
    $("#pmcc_navigation").html(PMCC_MAIN.navigation(${baseViewDtoJson}));
</script>
