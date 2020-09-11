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

<div class="sidebar sidebar-style-2" id="sub_navigation_div">
    <div class="sidebar-wrapper scrollbar scrollbar-inner">
        <div id="sub_navigation" class="sidebar-content">

        </div>
    </div>
</div>

<script src='/assets/js/comm/url.param.js'></script>
<script src='/assets/js/atlantis/business-sub-menu.js'></script>
<script src='/assets/js/atlantis/erp-navigation.js'></script>

<script type="text/javascript">
    var bisBusinessMenu = "${baseViewDto.currentMenu.bisSub}";

    if (bisBusinessMenu === "true") {
        //需要细分菜单模式
        $("#sub_navigation").html(BusinessSubMenu.navigation("${baseViewDto.currentMenu.id}"));
        $("#pmcc_navigation").remove();
    } else {
        //正常菜单显示模式
        $("#pmcc_navigation").html(PMCC_MAIN.navigation(${baseViewDtoJson}));
        $("#sub_navigation_div").remove();
    }

</script>
