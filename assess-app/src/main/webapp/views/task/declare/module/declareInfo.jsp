<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-5-9
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    var beforeAddFunction = {};//定义新增按钮触发前的全局变量
    var beforeEditFunction = {};//定义编辑按钮触发前的全局变量
</script>
<button type="button" class="btn btn-primary"
        onclick='selectDeclareForm()'> 选择申报表
</button>
<div id="dyncmicContent"></div>

<!--动态表单模板-->
<script type="text/html" id="dynamicHtml">
    <div class="x_panel" id="panel_{classifyId}">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li>
                    <a class="close-link" onclick="delDeclareUserClassify(this,'{classifyId}');"><i
                            class="fa fa-close"></i></a>
                </li>
            </ul>
            <h2>{title}</h2>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <input type="hidden" name="tableName" value="{tableName}">
            <input type="hidden" name="formModuleId" value="{formModuleId}">
            <input type="hidden" name="foreignKeyName" value="{foreignKeyName}">
            <input type="hidden" name="foreignKeyValue" value="{foreignKeyValue}">
            <button type="button" data-toggle="modal" class="btn btn-success"
                    onclick='addDynamicData(this,"{tableName}")'> 新增
            </button>
            <table class="table table-bordered">
            </table>
            <div class="modal fade bs-example-modal-lg"
                 data-backdrop="static" aria-hidden="true"
                 role="dialog" data-keyboard="false" tabindex="1"
                 style="display: none;">
                <div class="modal-dialog modal-lg" style="width: 1200px;">
                    <div class="modal-content">
                        <div class='modal-header'>
                            <button type='button' class='close'
                                    data-dismiss='modal'
                                    aria-label='Close'><span
                                    aria-hidden='true'>&times;</span>
                            </button>
                            <h3 class='modal-title'>{title}管理</h3>
                        </div>
                        <form class="form-horizontal">
                            <input type='hidden' name='id' value="0">
                            <div class='modal-body'>
                                <div class='row'>
                                    <div class='col-md-12'>
                                        <div class='x_content'>
                                            <div class="detail-content">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class='modal-footer'>
                            <button type='button' data-dismiss='modal'
                                    class='btn btn-default'>取消
                            </button>
                            <button type='button' class='btn btn-primary' data-classifyId="{classifyId}"
                                    onclick="saveDynamicData(this);">
                                保存
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>
<script type="text/javascript">
    $(function () {
        initDynamicData();
    })

    //选择申报表
    function selectDeclareForm() {
        var param = {
            modalName: "申报表选择",
            pid: "${empty projectInfo.projectCategoryId?projectInfo.projectTypeId:projectInfo.projectCategoryId}",
            filterKey: [AssessProjectClassifyKey.explore, AssessProjectClassifyKey.case],
            onSelected: function (nodes) {
                //选中之后，如果该申报表单已存在不做处理，否则添加申报表单
                var node = nodes[0];
                var panelElement = $("#panel_" + node.id);
                if (panelElement.length > 0) {
                    return;//申报类型元素已存在
                }
                var options = {};
                options.classifyId = node.id;
                options.title = node.name;
                //需从后台获取
                $.ajax({
                    url: "${pageContext.request.contextPath}/formConfigure/getProjectClassifyFormInfo",
                    type: "post",
                    dataType: "json",
                    data: {id: node.id},
                    success: function (result) {
                        if (result.ret) {
                            options.formModuleId = result.data.id;
                            options.foreignKeyName = result.data.foreignKeyName;
                            options.foreignKeyValue = '${projectPlanDetails.id}';
                            options.tableName = result.data.tableName;
                            options.jsonValue = '';//需填写的表单字段
                            options.listShowFields = JSON.stringify(result.data.listShowFields);//列表显示的字段
                            generateDynamicHtml(options);
                            addDeclareUserClassify(node.id);
                        }
                    }
                })
            }
        };
        //如果为综合资产则可选单项资产的任意申报表
        if ('${bisComprehensiveAssets}' == 'true') {
            param.pid = undefined;
            param.key = AssessProjectClassifyKey.single;
        }
        assessProjectClassify.select(param);


    }

    //添加申报项目分类
    function addDeclareUserClassify(projectClassifyId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/declare/addDeclareUserClassify",
            type: "post",
            dataType: "json",
            data: {
                projectId: '${projectPlanDetails.projectId}',
                planDetailsId: '${projectPlanDetails.id}',
                projectClassifyId: projectClassifyId
            },
            success: function (result) {
                if (!result.ret) {
                    Alert("添加申报项目分类异常");
                }
            }
        })
    }

    //删除申报项目分类
    function delDeclareUserClassify(_this, projectClassifyId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/declare/delDeclareUserClassify",
            type: "post",
            dataType: "json",
            data: {
                projectId: '${projectPlanDetails.projectId}',
                planDetailsId: '${projectPlanDetails.id}',
                projectClassifyId: projectClassifyId
            },
            success: function (result) {
                if (result.ret) {
                    $(_this).closest('.x_panel').remove();
                } else {
                    Alert("删除申报项目分类异常");
                }
            }
        })
    }

    //初始化动态数据
    function initDynamicData() {
        //如果计划设置了申报表 则自动加载
        $.ajax({
            url: "${pageContext.request.contextPath}/declare/getBaseFormModuleVos",
            type: "post",
            dataType: "json",
            data: {
                projectId: '${projectPlanDetails.projectId}',
                planDetailsId: '${projectPlanDetails.id}'
            },
            success: function (result) {
                if (result.ret) {
                    $.each(result.data, function (i, item) {
                        var options = {};
                        options.classifyId = item.classifyId;
                        options.title = item.title;
                        options.formModuleId = item.id;
                        options.foreignKeyName = item.foreignKeyName;
                        options.foreignKeyValue = '${projectPlanDetails.id}';
                        options.tableName = item.tableName;
                        options.jsonValue = '';//需填写的表单字段
                        options.listShowFields = JSON.stringify(item.listShowFields);//列表显示的字段

                        var panelElement = $("#panel_" + options.formModuleId);
                        if (panelElement.length > 0) {
                            return;//申报类型元素已存在
                        }
                        generateDynamicHtml(options);
                    })
                } else {
                    Alert("删除申报项目分类异常");
                }
            }
        })
    }

    //生成动态html
    function generateDynamicHtml(options) {
        //1.先将html加载到dyncmicContent
        //2.再初始出列表
        beforeAddFunction[options.tableName] = [];
        beforeEditFunction[options.tableName] = [];
        var html = $("#dynamicHtml").html();
        html = html.replace(/{classifyId}/g, options.classifyId).replace(/{title}/g, options.title).replace(/{tableName}/g, options.tableName);
        html = html.replace(/{formModuleId}/g, options.formModuleId).replace(/{foreignKeyName}/g, options.foreignKeyName).replace(/{foreignKeyValue}/g, options.foreignKeyValue);
        $("#dyncmicContent").append(html);
        panelElement = $("#panel_" + options.classifyId);
        //初始化各后期需要的元素
        options.targetList = panelElement.find('table');
        options.targetForm = panelElement.find('form');
        options.targetModal = panelElement.find('.modal');
        getDynamicHtml(options);
    }
</script>

<script type="text/javascript">
    //添加动态数据
    function addDynamicData(_this, tableName) {
        var fns = beforeAddFunction[tableName];
        if (fns) {
            $.each(fns, function (i, item) {
                item();
            });
        }
        targetForm = $(_this).closest('.x_panel').find("form");
        targetModal = $(_this).closest('.x_panel').find(".modal");
        targetForm.clearAll();
        targetModal.modal();
    }

    //保存动态数据
    function saveDynamicData(_this) {
        var classifyId = $(_this).attr("data-classifyId");
        var panelElement = $("#panel_" + classifyId);
        var options = {};
        options.targetList = panelElement.find('table');
        options.targetForm = $(_this).closest('.modal-content').find('form');
        options.targetModal = $(_this).closest('.modal');
        options.tableName = panelElement.find('[name="tableName"]').val();
        options.formModuleId = panelElement.find('[name="formModuleId"]').val();
        options.foreignKeyName = panelElement.find('[name="foreignKeyName"]').val();
        options.foreignKeyValue = panelElement.find('[name="foreignKeyValue"]').val();
        FormConfigureUtils.saveDetailInfo({
            targetList: options.targetList,
            targetForm: options.targetForm,
            targetModal: options.targetModal,
            tableName: options.tableName,
            formModuleId: options.formModuleId,
            foreignKeyName: options.foreignKeyName,
            foreignKeyValue: options.foreignKeyValue
        });
    }

    //获取动态html
    function getDynamicHtml(options) {
        FormConfigureUtils.getDynamicFormHtml({
            formModuleId: options.formModuleId,
            readOnly: false,
            jsonValue: options.jsonValue,
            success: function (html) {
                options.targetForm.find(".detail-content").append(html);
                //加载数据列表
                loadDynamicList(options);
            }
        });
    }

    //显示动态数据列表
    function loadDynamicList(options) {
        FormConfigureUtils.loadDetailInfoList({
            formModuleId: options.formModuleId,
            listShowFields: options.listShowFields,
            targetList: options.targetList,
            targetForm: options.targetForm,
            targetModal: options.targetModal,
            foreignKeyName: options.foreignKeyName,
            foreignKeyValue: options.foreignKeyValue,
            tableName: options.tableName,
            beforeEdit: function (row) {
                var fns = beforeEditFunction[options.tableName];
                if (fns) {
                    $.each(fns, function (i, item) {
                        item(row);
                    });
                }
            }
        });
    }
</script>