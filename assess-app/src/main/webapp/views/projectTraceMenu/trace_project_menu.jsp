<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-3 left_col">
    <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0;">

            <span class="site_title">
                <i style="border: none" class="fa fa-trophy"></i> ${projectInfo.projectName}
                <small><span class="label label-info">${projectInfo.projectStatus}</span></small>
            </span>
            <div class="profile clearfix">

                <a onclick="" style="margin-left: 20px;" data-placement='top' data-original-title='关注'
                   class='btn btn-xs btn-info tooltips'>
                    <i class='fa fa-bell-o fa-white'></i></a>

                <a onclick="" style="margin-left: 20px;" data-placement='top' data-original-title='取消关注'
                   class='btn btn-xs btn-info tooltips'>
                    <i class='fa fa-bell-slash-o fa-white'></i></a>

                <a target="_blank" data-placement='top' data-original-title='终止'
                   class='btn btn-xs btn-danger tooltips'><i class='fa fa-stop fa-white'></i></a>

                <a onclick="" data-placement='top' data-original-title='拷贝项目' class='btn btn-xs btn-warning tooltips'><i
                        class='fa fa-copy fa-white'></i></a>

            </div>
        </div>

        <div class="clearfix"></div>
        <br>
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
            <div class="menu_section">
                <ul class="nav side-menu">
                    <li><a> <i class="fa fa-info-circle" aria-hidden="true"></i>项目信息 <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a onclick="treeProjectObj.openProjectInfoLink('tb_documentSendList');">项目发文</a></li>
                            <li><a onclick="treeProjectObj.openProjectInfoLink('tb_documentOpinionList');">项目意见稿</a></li>
                            <li><a onclick="treeProjectObj.openProjectInfoLink('tb_subsequentList');">后续事项</a></li>
                            <li><a onclick="treeProjectObj.openProjectInfoLink('tb_takeNumber');">项目拿号</a></li>
                            <li><a onclick="treeProjectObj.openProjectInfoLink('tb_projectLogList');">项目日志</a></li>
                            <li><a onclick="treeProjectObj.openProjectInfoLink('tb_projectLegWorkList');">外勤信息</a></li>
                            <li><a onclick="treeProjectObj.openProjectInfoLink('tb_projectBillList');">开票信息</a></li>
                        </ul>
                    </li>

                    <li><a onclick=""> <i class="fa fa-database" aria-hidden="true"></i>资料收集与分析 <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a onclick="treeProjectObj.openProjectDeclareLink();">资产申报</a></li>
                        </ul>
                    </li>

                    <li><a> <i class="fa fa-sitemap" aria-hidden="true"></i>现场与案例 <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a onclick="">资产清查</a></li>
                            <li><a onclick="">现场查勘</a></li>
                            <li><a onclick="">交易案例</a></li>
                            <li><a onclick="">他项权利</a></li>
                        </ul>
                    </li>

                    <li><a onclick=""><i class="fa fa-object-group" aria-hidden="true"></i> 评估工作方案</a></li>
                    <li><a onclick=""><i class="fa fa-file-word-o" aria-hidden="true"></i> 报告编写</a></li>
                    <li><a onclick=""><i class="fa fa-eye" aria-hidden="true"></i>报告生成</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="top_nav" id="pmcc_head">

    <div class="nav_menu">
        <nav>
            <div class="nav toggle"><a id="menu_toggle"><i class="fa fa-bars"></i></a></div>
            <div class=" nav toggle">
                <h3>
                    <%--<a target="_blank" class="tooltips" href="${pageContext.request.contextPath}/projectInfo/projectDisplayPage?projectId=${projectInfo.id}" data-placement='bottom'--%>
                    <%--data-original-title='单击查看项目详情'>--%>
                    <%--项目详情--%>
                    <%--</a>--%>
                </h3>
            </div>
        </nav>
    </div>
</div>

<script type="text/javascript">

    var treeProjectObj = {} ;

    treeProjectObj.openProjectInfoLink = function (key) {
        window.open("${pageContext.request.contextPath}/projectPlanDetails/projectTraceProjectInfo?projectId=${projectInfo.id}&key="+key, "_blank");
    };

    treeProjectObj.openProjectDeclareLink = function () {
        window.open("${pageContext.request.contextPath}/projectPlanDetails/projectTraceProjectDeclare?projectId=${projectInfo.id}", "_blank");
    };

</script>

