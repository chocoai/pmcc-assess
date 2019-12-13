<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">

                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                        ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">
                    <div class="row">
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <div class="row">

                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                    <div class="input-group">
                                        <input type="text" name="queryName" id="queryName" class="form-control">
                                        <span class="input-group-btn">
                                        <a href="javascript://" onclick="sysAreaObj.querySysArea()"
                                           class="btn btn-primary">查询</a>
                                         </span>
                                    </div>
                                </div>


                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                    <ul id="treeSysAreaDemo" class="ztree"></ul>
                                </div>

                            </div>
                        </div>

                        <div class=" col-xs-9  col-sm-9  col-md-9  col-lg-9 ">
                            <div class="row">
                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                    <div class="panel-body panel" style="margin-top:100px;">
                                        <form class="form-horizontal" id="formDetailSysArea">
                                            <input type="hidden" name="id">
                                            <input type="hidden" name="parentId">
                                            <input type="hidden" name="areaId">
                                            <input type="hidden" name="dataName">
                                            <!-- 用作树 append -->
                                            <input type="hidden" name="tId">
                                            <div class="form-group">
                                                <div class="x-valid">
                                                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                        名称
                                                    </label>
                                                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                        <label class="form-control" name="name"></label>
                                                    </div>
                                                </div>

                                                <div class="x-valid">
                                                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                        区域编号
                                                    </label>
                                                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                        <label class="form-control" name="areaCode"></label>
                                                    </div>
                                                </div>

                                                <div class="x-valid">
                                                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                        邮编
                                                    </label>
                                                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                        <label class="form-control" name="zipCode"></label>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <div class="x-valid">
                                                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                        上级名称
                                                    </label>
                                                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                        <label class="form-control" name="parentName"></label>
                                                    </div>
                                                </div>
                                                <div class="x-valid">
                                                    <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                        深度
                                                    </label>
                                                    <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                                        <label class="form-control" name="depth"></label>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>

                                </div>
                                <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                                    <div class="row">
                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4  col-xs-offset-5  col-sm-offset-5  col-md-offset-5  col-lg-offset-5">
                                            <button type="button" class="btn btn-success"
                                                    onclick="sysAreaObj.addSysArea();"> 新增
                                            </button>
                                            <a id="editSysAreaCustomer" class="btn btn-primary"
                                               onclick="sysAreaObj.editSysArea()" style="display: none">
                                                编辑
                                            </a>
                                            <a id="deleteSysAreaCustomer" class="btn btn-warning"
                                               onclick="sysAreaObj.deleteSysArea();" style="display: none">
                                                删除
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>

<div id="boxSysAreaView" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">地区 区域</h3>
            </div>
            <form class="form-horizontal">
                <input type="hidden" name="id">
                <input type="hidden" name="parentId">
                <input type="hidden" name="areaId">
                <!-- 用作树 append -->
                <input type="hidden" name="tId">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">名称<span
                                                class="symbol required"></span></label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input type="text" class="form-control" name="name" required="required"
                                                   placeholder="名称">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">区域编号</label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input type="text" class="form-control"
                                                   name="areaCode"
                                                   placeholder="区域编号">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">邮编</label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input type="text" class="form-control" name="zipCode"
                                                   placeholder="邮编">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">深度</label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input type="text" class="form-control"
                                                   name="depth"
                                                   placeholder="深度">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label"></label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            启用<input type="checkbox" name="bisEnable" value="true" checked="checked">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">上级名称</label>
                                        <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4 ">
                                            <input type="text" class="form-control" readonly="readonly"
                                                   name="parentName"
                                                   placeholder="上级名称">
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
                    <button type="button" class="btn btn-primary" onclick="sysAreaObj.saveSysArea();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
<%@include file="/views/share/main_footer.jsp" %>

<script type="text/javascript" defer="defer">

    var sysAreaObj = {};

    sysAreaObj.queryName = $("#queryName");
    sysAreaObj.formDetail = $("#formDetailSysArea");
    sysAreaObj.box = $("#boxSysAreaView");

    sysAreaObj.tree = $("#treeSysAreaDemo");
    sysAreaObj.editBtn = $("#editSysAreaCustomer");
    sysAreaObj.deleteBtn = $("#deleteSysAreaCustomer");

    /**
     * jquery 处理
     * @param obj
     */
    sysAreaObj.jqueryHandle = function (obj) {
        if (obj instanceof jQuery) {
            //
        } else {
            obj = $(obj.selector);
        }
    };

    sysAreaObj.querySysArea = function () {
        this.jqueryHandle(sysAreaObj.queryName);
        this.jqueryHandle(sysAreaObj.tree);
        var name = sysAreaObj.queryName.val();

//        if (name) {
//            //ztree 查询方法
//            var treeObj = $.fn.zTree.getZTreeObj(sysAreaObj.tree.prop("id"));
//            var nodes = treeObj.getNodesByParam("name", name, null);
//            sysAreaObj.loadTreeHandle(nodes);
//        } else {
//            //服务端查询方法
//            sysAreaObj.loadTree();
//        }


        if (name) {
            sysAreaObj.loadTree({name: name, bisEnable: true});
        } else {
            sysAreaObj.loadTree();
        }
        sysAreaObj.hideDetailHandle() ;
    };

    sysAreaObj.initForm = function (data) {
        sysAreaObj.jqueryHandle(sysAreaObj.box);
        var form = sysAreaObj.box.find("form");
        form.clearAll().initForm(data);
    };

    sysAreaObj.addSysArea = function () {
        sysAreaObj.jqueryHandle(sysAreaObj.box);
        sysAreaObj.jqueryHandle(sysAreaObj.formDetail);
        var item = formSerializeArray(sysAreaObj.formDetail);
        var data = {bisEnable: true, parentId: 0};
        if (item.areaId) {
            data.parentId = item.areaId;
            data.parentName = item.dataName;
        }
        sysAreaObj.initForm(data);
        sysAreaObj.box.modal("show");
    };

    sysAreaObj.deleteSysArea = function () {
        this.jqueryHandle(sysAreaObj.tree);
        sysAreaObj.jqueryHandle(sysAreaObj.formDetail);
        var item = formSerializeArray(sysAreaObj.formDetail);
        Alert("确认删除?",2,null,function () {
            $.ajax({
                url: '${pageContext.request.contextPath}/sysArea' + "/deleteSysAreaByIdEnable",
                data: {id: item.id},
                type: "post",
                success: function (result) {
                    if (result.ret) {
                        if (result.data != 'ok') {
                            Alert(result.data);
                            return false;
                        }
                        var treeObj = $.fn.zTree.getZTreeObj(sysAreaObj.tree.prop("id"));
                        if (item.tId) {
                            var node = treeObj.getNodeByTId(item.tId);
                            treeObj.removeNode(node);
                        } else {
                            sysAreaObj.loadTree();
                        }
                        toastr.success('删除成功');
                        sysAreaObj.hideDetailHandle() ;
                    } else {
                        Alert(result.errmsg);
                    }
                },
                error: function (result) {
                    Alert(result.errmsg);
                }
            });
        });

    };

    sysAreaObj.editSysArea = function () {
        sysAreaObj.jqueryHandle(sysAreaObj.formDetail);
        sysAreaObj.jqueryHandle(sysAreaObj.box);
        var item = formSerializeArray(sysAreaObj.formDetail);
        $.ajax({
            url: '${pageContext.request.contextPath}/sysArea' + "/getSysAreaById",
            data: {id: item.id},
            type: "get",
            success: function (result) {
                if (result.ret) {
                    console.log(result.data);
                    sysAreaObj.initForm(result.data);
                    sysAreaObj.box.modal("show");
                } else {
                    Alert(result.errmsg);
                }
            }
        });
    };

    sysAreaObj.saveSysArea = function () {
        sysAreaObj.jqueryHandle(sysAreaObj.box);
        var form = sysAreaObj.box.find("form");
        if (!form.valid()) {
            return false;
        }
        var data = formSerializeArray(form);
        $.ajax({
            url: '${pageContext.request.contextPath}/sysArea' + "/saveAndUpdateSysArea",
            data: {fomData: JSON.stringify(data), updateNull: true},
            type: "post",
            success: function (result) {
                if (result.ret) {
                    sysAreaObj.loadTree();
                    sysAreaObj.hideDetailHandle() ;
                    toastr.success('成功');
                    sysAreaObj.box.modal("hide");
                } else {
                    Alert(result.errmsg);
                }
            }
        });
    };

    sysAreaObj.hideDetailHandle = function () {
        sysAreaObj.jqueryHandle(sysAreaObj.editBtn);
        sysAreaObj.jqueryHandle(sysAreaObj.deleteBtn);
        sysAreaObj.jqueryHandle(sysAreaObj.formDetail);
        sysAreaObj.formDetail.clearAll().initForm({name:'',parentName:'',areaCode:'',zipCode:'',depth:''});
        sysAreaObj.editBtn.hide();
        sysAreaObj.deleteBtn.hide();
    };

    /**
     * tree  点击事件
     * @param event
     * @param treeId
     * @param treeNode
     */
    sysAreaObj.zTreeOnClick = function (event, treeId, treeNode) {
        sysAreaObj.jqueryHandle(sysAreaObj.editBtn);
        sysAreaObj.jqueryHandle(sysAreaObj.deleteBtn);
        sysAreaObj.jqueryHandle(sysAreaObj.tree);
        sysAreaObj.jqueryHandle(sysAreaObj.formDetail);
        var treeObj = $.fn.zTree.getZTreeObj(sysAreaObj.tree.prop("id"));
        sysAreaObj.editBtn.show();
        sysAreaObj.deleteBtn.show();
        var data = treeObj.getNodeByTId(treeNode.tId);
        data.dataName = data.name;
        sysAreaObj.formDetail.clearAll().initForm(data);
    };

    sysAreaObj.loadTree = function (select) {
        if (select == null || select == undefined) {
            select = {bisEnable: true};
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/sysArea/getSysAreaListSelect",
            type: "get",
            dataType: "json",
            data: select,
            success: function (result) {
                if (result.ret) {
                    sysAreaObj.loadTreeHandle(result.data);
                } else {
                    Alert("获取数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    /**
     * tree 配置文件
     * @param data
     */
    sysAreaObj.loadTreeHandle = function (data) {
        this.jqueryHandle(sysAreaObj.tree);
        var zTreeObj = undefined;

        // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
        var setting = {
            //页面上的显示效果
            view: {
                selectedMulti: true,
                expandSpeed: "slow",
                fontCss: {color: "red"}
            },
            check: {
                enable: false
            },
            callback: {
                onClick: sysAreaObj.zTreeOnClick
            },
            data: {
                key: {
                    name: "name"
                },
                simpleData: {//json 数据必须设置
                    enable: true,//true / false 分别表示 使用 / 不使用 简单数据模式
                    idKey: "areaId",
                    pIdKey: "parentId",
                    rootPId: 0
                }
            }
        };
        $.fn.zTree.destroy();
        zTreeObj = $.fn.zTree.init(sysAreaObj.tree, setting, data);
        //不展开
        zTreeObj.expandAll(false);
    };

    $(document).ready(function () {
        sysAreaObj.loadTree();
    });

</script>


</html>
