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
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2>动态表单</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content" id="panel_body">
        <button type="button" data-toggle="modal" style="display: none;"
                href="#modal_detail"
                class="btn btn-success"
                id="btn_add_detail_info"> 新增
        </button>
        <table id="tb_detail_list" class="table table-bordered">
        </table>
        <div id='modal_detail'
             class="modal fade bs-example-modal-lg"
             data-backdrop="static" aria-hidden="true"
             role="dialog" data-keyboard="false" tabindex="1"
             style="display: none;">
            <div class="modal-dialog modal-lg" style="height: 800px;width: 1200px;">
                <div class="modal-content">
                    <div class='modal-header'>
                        <button type='button' class='close'
                                data-dismiss='modal'
                                aria-label='Close'><span
                                aria-hidden='true'>&times;</span>
                        </button>
                        <h4 class='modal-title'>{formModuleName}</h4>
                    </div>
                    <form class="form-horizontal"
                          id='frm_detail'
                          class='form-horizontal'>
                        <input type='hidden' name='id' value="0">
                        <div class='modal-body'>
                            <div class='row'>
                                <div class='col-md-12'>
                                    <div class='x_content'>
                                        <div id="detail_content">

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
                        <button type='button'
                                id="btn_save_detail_info"
                                class='btn btn-primary save_custom_model'>
                            保存
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(function () {
                beforeAddFunction["${item.tableName}"] = [];
                beforeEditFunction["${item.tableName}"] = [];

                //新增按钮触发事件
                $("#btn_add_detail_info").click(function () {
                    var fns = beforeAddFunction["${item.tableName}"];
                    if (fns) {
                        $.each(fns, function (i, item) {
                            item();
                        });
                    }
                    FormConfigureUtils.addDetailInfo({targetForm: "frm_detail"});
                })

                //保存按钮触发事件
                $("#btn_save_detail_info").click(function () {
                    FormConfigureUtils.saveDetailInfo({
                        targetList: "tb_detail_list",
                        targetForm: "frm_detail",
                        targetModal: "modal_detail",
                        tableName: "{item.tableName}",
                        formModuleId: "{item.formModuleId}",
                        foreignKeyName: "{item.foreignKeyName}",
                        foreignKeyValue: $("#primaryId").val()
                    });
                })


                //获取动态的html
                FormConfigureUtils.getDynamicFormHtml({
                    formModuleId: '{item.formModuleId}',
                    readOnly: false,
                    jsonValue: $("#lbl_json_table").text(),
                    success: function (html) {
                        $("#detail_content").append(html);
                        $("#btn_add_detail_info").show();//显示对应的新增按钮
                        //加载数据列表
                        FormConfigureUtils.loadDetailInfoList({
                            formModuleId: '{item.formModuleId}',
                            fieldList: $("#lbl_json_field").text(),
                            targetList: "tb_detail_list",
                            targetForm: "frm_detail",
                            targetModal: "modal_detail",
                            foreignKeyName: "{item.foreignKeyName}",
                            foreignKeyValue: $("#primaryId").val(),
                            tableName: "{item.tableName}",
                            beforeEdit: function (row) {
                                var fns = beforeEditFunction["{item.tableName}"];
                                if (fns) {
                                    $.each(fns, function (i, item) {
                                        item(row);
                                    });
                                }
                            }
                        });
                    }
                });
            })

        </script>
    </div>
</div>
