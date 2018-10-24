<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.css">
    <script src='${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.js'></script>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main" id="basicApplyId">
            <div class="page-title">
                <div class="title_left">
                    <h2><i class="fa "></i>
                        案例基础数据维护
                    </h2>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i
                                class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>
                        申请信息
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                                <input type="radio" id="industry_no" name="industry" value="1" checked="checked">
                                <label for="industry_no">非工业与仓储</label>
                            </span>

                            <span class="col-sm-2  checkbox-inline">
                                <input type="radio" id="industry" name="industry" value="2">
                                <label for="industry">工业与仓储</label>
                            </span>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼盘名称
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="basicEstate" placeholder="楼盘名称" onkeydown="objectData.autocompleteEstate()">
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-2">
                                    <button class="btn btn-success">详细信息</button>
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-2">
                                    <button class="btn btn-success">修改</button>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    楼栋编号
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="basicBuilding" placeholder="楼栋编号" onkeydown="objectData.autocompleteBuilding()">
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-2">
                                    <button class="btn btn-success">详细信息</button>
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-2">
                                    <button class="btn btn-success">修改</button>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    单元编号
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="" placeholder="单元编号">
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-2">
                                    <button class="btn btn-success">详细信息</button>
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-2">
                                    <button class="btn btn-success">修改</button>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    房屋编号
                                </label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="" placeholder="房屋编号">
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-2">
                                    <button class="btn btn-success">详细信息</button>
                                </div>
                            </div>
                            <div class="x-valid">
                                <div class="col-sm-2">
                                    <button class="btn btn-success">修改</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_title">
                    <h2>
                        <small>详细信息</small>
                    </h2>
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                        </li>
                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                        </li>
                    </ul>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div role="tabpanel" data-example-id="togglable-tabs">
                        <ul class="nav nav-tabs bar_tabs" role="tablist">
                            <li role="presentation" class="active"><a href="#caseEstate" id="home-tab" role="tab"
                                                                      data-toggle="tab" aria-expanded="true">楼盘</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseBuild" role="tab" id="profile-tab"
                                                                data-toggle="tab" aria-expanded="false">楼栋</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseUnit" role="tab" id="profile-tab2"
                                                                data-toggle="tab" aria-expanded="false">单元</a>
                            </li>
                            <li role="presentation" class=""><a href="#caseHouse" role="tab" id="profile-tab3"
                                                                data-toggle="tab" aria-expanded="false">房屋</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane fade" id="caseEstate" aria-labelledby="home-tab">
                                <p>Raw denim you probably haven't heard of them jean shorts Austin. Nesciunt tofu
                                    stumptown aliqua, retro synth master cleanse. Mustache cliche tempor, williamsburg
                                    carles vegan helvetica. Reprehenderit butcher retro keffiyeh dreamcatcher
                                    synth. Cosby sweater eu banh mi, qui irure terr.</p>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseBuild" aria-labelledby="profile-tab">
                                <p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee
                                    squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes
                                    anderson artisan four loko farm-to-table craft beer twee. Qui photo
                                    booth letterpress, commodo enim craft beer mlkshk aliquip</p>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseUnit" aria-labelledby="profile-tab2">
                                <p>xxFood truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee
                                    squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes
                                    anderson artisan four loko farm-to-table craft beer twee. Qui photo
                                    booth letterpress, commodo enim craft beer mlkshk </p>
                            </div>
                            <div role="tabpanel" class="tab-pane fade" id="caseHouse" aria-labelledby="profile-tab3">
                                <p>
                                    hjsdjsjsd </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-4 col-sm-offset-5">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
</html>
<script type="text/javascript">

    var objectData = new Object();

    objectData.config = {
        id: "basicApplyId",
        option: {},
        basicEstate:{
            key:"basicEstate",
            name:"楼盘"
        },
        basicBuilding:{
            key:"basicBuilding",
            name:"楼栋"
        }
    };

    objectData.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    objectData.select2Assignment = function (frm, data, name) {
        if (objectData.isNotBlank(data)) {
            $("#" + frm).find("select." + name).val(data).trigger("change");
        } else {
            $("#" + frm).find("select." + name).val(null).trigger("change");
        }
    };

    objectData.formParams = function () {
        var item = {};
        var forms = $("#" + objectData.config.id).find("form");
        $.each(forms, function (i, n) {
            // if (!$(n).valid()) {
            //     return false;
            // }
        });
        $.each(forms, function (i, n) {
            try {
                /*Object.assign() 方法用于将所有可枚举属性的值从一个或多个源对象复制到目标对象。它将返回目标对象。 ECMAScript6 (可能不兼容) */
                item = Object.assign(item, formSerializeArray($(n)));
            } catch (e) {
                item = $.extend(item, formSerializeArray($(n)));
            }
        });
    };

    /**
     * 楼盘 信息自动补全
     */
    objectData.autocompleteEstate = function () {
        $("#"+objectData.config.id).find("input[name='"+objectData.config.basicEstate.key+"']").autocomplete(
            {
                source: function (request, response) {
                    var itemVal = $("#"+objectData.config.id).find("input[name='"+objectData.config.basicEstate.key+"']").val();
                    $.ajax({
                        url: "${pageContext.request.contextPath}/basicEstate/autoComplete",
                        type: "get",
                        dataType: "json",
                        data: {
                            maxRows: 10,
                            name: itemVal
                        },
                        success: function (result) {
                            if (result.ret){
                                response($.each(result.data, function (i, item) {
                                    return {
                                        label: item.value,
                                        value: item.key
                                    }
                                }));
                            }else {
                                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
                            }
                        }
                    });
                },
                minLength: 1,
                /*当从菜单中选择条目时触发。默认的动作是把文本域中的值替换为被选中的条目的值。取消该事件会阻止值被更新，但不会阻止菜单关闭。*/
                select: function (event, ele) {
                    $("#"+objectData.config.id).find("input[name='"+objectData.config.basicEstate.key+"']").attr("data-id",ele.item.key);
                },
                /*当焦点移动到一个条目上（未选择）时触发。默认的动作是把文本域中的值替换为获得焦点的条目的值，即使该事件是通过键盘交互触发的。取消该事件会阻止值被更新，但不会阻止菜单项获得焦点。*/
                focus:function (event, ui) {
                }
            }
        );
    };

    /**
     * 楼栋 信息自动补全
     */
    objectData.autocompleteBuilding = function () {
        var estateId = $("#"+objectData.config.id).find("input[name='"+objectData.config.basicEstate.key+"']").attr("data-id");
        if (!objectData.isNotBlank(estateId)){
            Alert("请先选择楼盘然后在选择楼栋!");
            return false;
        }
        $("#"+objectData.config.id).find("input[name='"+objectData.config.basicBuilding.key+"']").autocomplete(
            {
                source: function (request, response) {
                    var itemVal = $("#"+objectData.config.id).find("input[name='"+objectData.config.basicBuilding.key+"']").val();
                    $.ajax({
                        url: "${pageContext.request.contextPath}/basicBuilding/autoComplete",
                        type: "get",
                        dataType: "json",
                        data: {
                            maxRows: 10,
                            identifier: itemVal,
                            estateId:estateId
                        },
                        success: function (result) {
                            if (result.ret){
                                response($.each(result.data, function (i, item) {
                                    return {
                                        label: item.value,
                                        value: item.key
                                    }
                                }));
                            }else {
                                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
                            }
                        }
                    });
                },
                minLength: 1,
                /*当从菜单中选择条目时触发。默认的动作是把文本域中的值替换为被选中的条目的值。取消该事件会阻止值被更新，但不会阻止菜单关闭。*/
                select: function (event, ele) {
                    $("#"+objectData.config.id).find("input[name='"+objectData.config.basicBuilding.key+"']").attr("data-id",ele.item.key);
                },
                focus:function (event, ui) {
                }
            }
        );
    };

</script>

<script>
    //提交
    function submit() {
        if ("${processInsId}" != "0") {
            submitEditToServer();
        }
        else {
            submitToServer();
        }
    }
</script>