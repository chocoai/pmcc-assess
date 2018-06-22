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
<button type="button"  class="btn btn-primary"
        onclick='selectDeclareForm()'> 选择申报表
</button>
<div id="dyncmicContent"></div>

<!--动态表单模板-->
<script type="text/html" id="dynamicHtml">
    <div class="x_panel" id="panel_{classifyId}">
        <div class="x_title collapse-link">
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
            </ul>
            <h2>{title}</h2>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <button type="button" data-toggle="modal" href="#modal_detail" class="btn btn-success"
                    onclick='addDynamicData("{tableName}",$(this).find("form"),$(this).find(".modal"))'> 新增
            </button>
            <table class="table table-bordered">
            </table>
            <div class="modal fade bs-example-modal-lg"
                 data-backdrop="static" aria-hidden="true" data-height="600"
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
                            <h4 class='modal-title'>{title}管理</h4>
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
                            <button type='button' class='btn btn-primary save_custom_model'>
                                保存
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>
<input type="hidden" id="jsonValue" value='${jsonValue}'>
<input type="hidden" id="jsonValue" value='${fieldList}'>
<script type="text/javascript">
    var globalContainer = {};//全局容器
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

            }
        };
        //如果为综合资产则可选单项资产的任意申报表
        if ('${bisComprehensiveAssets}' == 'true') {
            param.pid = undefined;
            param.key = AssessProjectClassifyKey.single;
        }
        assessProjectClassify.select(param);
    }

    //初始化动态数据
    function initDynamicData() {
        //如果计划设置了申报表 则自动加载
        if ('${empty baseFormModule}' == 'false') {
            //1.设置参数 2.调用初始化方法
            var options={};
            options.title='${baseProjectClassify.name}';
            options.formModuleId = '${baseFormModule.id}';
            options.foreignKeyName = '${baseFormModule.foreignKeyName}';
            options.foreignKeyValue = '${projectPlanDetails.id}';
            options.tableName = '${baseFormModule.tableName}';
            options.jsonValue = $("#jsonValue").val();//需填写的表单字段
            options.fieldList = $("#fieldList").val();//列表显示的字段
            generateDynamicHtml(options);
        }
    }

    //生成动态html
    function generateDynamicHtml(options) {
        //1.先将html加载到dyncmicContent
        //2.再初始出列表
        var panelElement = $("#panel_" + options.formModuleId);
        if (panelElement.length > 0) {
            return;//申报类型元素已存在
        }
        var html = $("#dynamicHtml").html();
        html = html.replace(/{title}/g, options.title).replace(/{tableName}/g, options.title);
        $("#dyncmicContent").append(html);
        //初始化各后期需要的元素
        options.targetList = panelElement.find('table');
        options.targetForm = panelElement.find('form');
        options.targetModal = panelElement.find('.modal');
        getDynamicHtml(options);
    }
</script>

<script type="text/javascript">
    //添加动态数据
    function addDynamicData(tableName, targetForm, targetModal) {
        var fns = beforeAddFunction[tableName];
        if (fns) {
            $.each(fns, function (i, item) {
                item();
            });
        }
        FormConfigureUtils.addDetailInfo({targetForm: targetForm});
        targetModal.modal();
    }

    //保存动态数据
    function saveDynamicData(options) {
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
                $("#detail_content").append(html);
                //加载数据列表
                loadDynamicList(options);
            }
        });
    }

    //显示动态数据列表
    function loadDynamicList(options) {
        FormConfigureUtils.loadDetailInfoList({
            formModuleId: options.formModuleId,
            fieldList: options.fieldList,
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