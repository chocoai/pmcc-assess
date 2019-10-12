
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-3 left_col">
    <div class="left_col scroll-view">
        <div class="navbar nav_title" style="border: 0;">

            <a href="${pageContext.request.contextPath}/projectInfo/projectDetails?projectId=${projectInfo.id}" class="site_title">
                <i style="border: none" class="fa fa-trophy"></i>sdsdjsdj
                <small><span class="label label-info">${projectInfo.projectStatus}</span></small>
            </a>

            <div class="profile clearfix">

                <a onclick="followProject()" style="margin-left: 20px;" data-placement='top' data-original-title='关注' class='btn btn-xs btn-info tooltips'><i class='fa fa-bell-o fa-white'></i></a>
                <a onclick="cancelFollowProject()" style="margin-left: 20px;" data-placement='top' data-original-title='取消关注' class='btn btn-xs btn-info tooltips'><i
                        class='fa fa-bell-slash-o fa-white'></i></a>
                <a href="${pageContext.request.contextPath}/projectClose/closeIndex?projectId=${projectInfo.id}" target="_blank" data-placement='top' data-original-title='终止'
                   class='btn btn-xs btn-danger tooltips'><i class='fa fa-stop fa-white'></i></a>



                <a onclick="copyProjectInfoSure()" data-placement='top' data-original-title='拷贝项目' class='btn btn-xs btn-warning tooltips'><i
                        class='fa fa-copy fa-white'></i></a>
            </div>

        </div>

        <div class="clearfix"></div>
        <br>
        <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
            <div class="menu_section">
                <ul class="nav side-menu">

                    <li><a><i class="fa fa-cogs"></i> 项目管理 <span class="fa fa-chevron-down"></span></a>
                        <ul class="nav child_menu">
                            <li><a href="${pageContext.request.contextPath}/projectInfo/sendDocument/${projectInfo.id}">项目发文</a></li>
                            <%--<li><a href="${pageContext.request.contextPath}/projectInfo/projectDetails?projectId=${projectInfo.id}">保证金</a></li>--%>
                            <%--<li><a href="${pageContext.request.contextPath}/projectInfo/projectWorkLog/${projectInfo.id}">项目日志</a></li>--%>
                            <li><a href="${pageContext.request.contextPath}/projectInfo/projectLegwork/${projectInfo.id}">外勤</a></li>
                            <li><a href="${pageContext.request.contextPath}/projectInfo/projectOvertime/${projectInfo.id}">加班</a></li>
                            <li><a href="${pageContext.request.contextPath}/projectInfo/projectExpense/${projectInfo.id}">报销</a></li>
                        </ul>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/projectInfo/projectLx/${projectInfo.id}"><i class="fa fa-bookmark"></i> 项目信息</a></li>
                    <li><a href="${pageContext.request.contextPath}/projectInfo/projectBsqd/${projectInfo.id}"><i class="fa fa-cloud-download"></i> 标书取得</a></li>
                    <li><a href="${pageContext.request.contextPath}/projectInfo/projectTbfx/${projectInfo.id}"><i class="fa fa-spinner"></i> 投标分析</a></li>
                    <li><a href="${pageContext.request.contextPath}/projectInfo/projectBszz/${projectInfo.id}"><i class="fa fa-magic"></i> 标书制作</a></li>
                    <li><a href="${pageContext.request.contextPath}/projectInfo/projectTb/${projectInfo.id}"><i class="fa fa-flag-checkered"></i> 投标</a></li>
                    <li><a href="${pageContext.request.contextPath}/projectInfo/projectJggz/${projectInfo.id}"><i class="fa fa-coffee"></i> 结果跟踪</a></li>

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
                    <a target="_blank" class="tooltips" href="${pageContext.request.contextPath}/projectInfo/projectDisplayPage?projectId=${projectInfo.id}" data-placement='bottom'
                       data-original-title='单击查看项目详情'>
                       sdhsdsd
                    </a>
                </h3>
            </div>
        </nav>
    </div>
</div>
<div id="div_new_project" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title" id="h3_modeTitle">项目复制</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-body">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            项目名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="项目名称" id="projectNewName" required maxlength="50" value="${projectInfo.projectName}-复制" class="form-control">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="copyProjectInfo()">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    //关注
    function followProject() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectFollow/followProject",
            data: {
                projectId: "${projectInfo.id}",
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success("关注成功");
                    window.location.reload();
                }
                else {
                    Alert("关注失败，失败原因：" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    //取消关注
    function cancelFollowProject() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectFollow/cancelFollowProject",
            data: {
                projectId: "${projectInfo.id}",
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success("取消成功");
                    window.location.reload();
                }
                else {
                    Alert("取消失败，失败原因：" + result.errmsg, 1, null, null);
                }
            },
            error: function (result) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

    function copyProjectInfoSure() {
        $('#div_new_project').modal({backdrop: 'static', keyboard: false});
    }
    function copyProjectInfo() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/projectInfo/copyProjectInfo",
            data: {
                projectId:${projectInfo.id},
                projectName: $("#projectNewName").val()
            },
            type: "post",
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $('#div_new_project').modal('hide');
                    Alert("拷贝项目成功，请到项目列表中查看");
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg, 1, null, null);
            }
        });
    }

</script>
