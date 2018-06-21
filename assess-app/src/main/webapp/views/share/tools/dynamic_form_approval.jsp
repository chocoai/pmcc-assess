<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-5-9
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input type='hidden' id="primaryId" value="${primaryData.get("primaryId")}">
<input type='hidden' id="phaseId" value="${primaryData.get("phaseId")}">
<input type='hidden' id="primaryTableName" value="${primaryData.get("primaryTableName")}">
<script type="text/javascript">
    var beforeAddFunction = {};//定义新增按钮触发前的全局变量
    var beforeEditFunction = {};//定义编辑按钮触发前的全局变量
    var beforeViewFunction = {};//定义编辑按钮触发前的全局变量
</script>
<%--<c:forEach var="item" items="${tableJsonData.entrySet()}">--%>
<%--<label id="lbl_json_table_${item.getKey()}" style="display: none;">${item.getValue().getValue()}</label>--%>
<%--</c:forEach>--%>
<c:forEach var="item" items="${hrProcessForms}">
    <c:if test="${item.boxReActivityName!=null}">
        <c:if test="${item.sorting<=100}">
            <c:choose>
                <%--当前节点需填写的数据--%>
                <c:when test="${item.boxReActivityName=='abc'}">
                    <div id="${item.name}"
                         data-name="${item.bisMultiple eq true?"multipleTable":"singleTable"}"
                         data-table-name="${item.tableName}" data-form-list-id="${item.formModuleId}">
                            <%--判断是配置的字段还是程序固定页面--%>
                        <c:choose>
                            <c:when test="${item.bisConfigure eq true}">
                                <div class="x_panel">
                                    <div class="x_title collapse-link">
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                                        </ul>
                                        <h2> ${item.formModuleName}</h2>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <div class="x_content" id="panel_body_${item.id}">
                                            <c:choose>
                                                <c:when test="${item.bisMultiple eq true}">
                                                    <button type="button" data-toggle="modal"
                                                            href="#modal_detail_${item.id}"
                                                            class="btn btn-success"
                                                            id="btn_add_detail_info_${item.id}"> 新增
                                                    </button>
                                                    <label id="lbl_json_field_${item.id}"
                                                           style="display: none;">${item.fieldListJson}</label>
                                                    <table id="tb_detail_list_${item.id}" class="table table-bordered">
                                                    </table>
                                                    <div id='modal_detail_${item.id}'
                                                         class="modal fade bs-example-modal-sm"
                                                         data-backdrop="static" aria-hidden="true"
                                                         role="dialog" data-keyboard="false"
                                                         tabindex="1"
                                                         style="display: none;">
                                                        <div class="modal-dialog modal-lg">
                                                            <div class="modal-content">
                                                                <div class='modal-header'>
                                                                    <button type='button' class='close'
                                                                            data-dismiss='modal'
                                                                            aria-label='Close'><span
                                                                            aria-hidden='true'>&times;</span>
                                                                    </button>
                                                                    <h4 class='modal-title'>${item.formCnName}</h4>
                                                                </div>
                                                                <form class="form-horizontal"
                                                                      id='frm_detail_${item.id}'
                                                                      class='form-horizontal'>
                                                                    <input type='hidden' name='id'
                                                                           value="0">
                                                                    <div class='modal-body'>
                                                                        <div class='row'>
                                                                            <div class='col-md-12'>
                                                                                <div class='x_content'>
                                                                                    <div id="detail_content_${item.id}">

                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </form>
                                                                <div class='modal-footer'>
                                                                    <button type='button'
                                                                            data-dismiss='modal'
                                                                            class='btn btn-default'>取消
                                                                    </button>
                                                                    <button type='button'
                                                                            id="btn_save_detail_info_${item.id}"
                                                                            class='btn btn-primary save_custom_model'>
                                                                        保存
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <script type="text/javascript">
                                                        $(function () {
                                                            $("#btn_add_detail_info_${item.id}").click(function () {
                                                                FormConfigureUtils.addDetailInfo({targetForm: "frm_detail_${item.id}"});
                                                            })
                                                            $("#btn_save_detail_info_${item.id}").click(function () {
                                                                FormConfigureUtils.saveDetailInfo({
                                                                    targetList: "tb_detail_list_${item.id}",
                                                                    targetForm: "frm_detail_${item.id}",
                                                                    targetModal: "modal_detail_${item.id}",
                                                                    tableName: "${item.tableName}",
                                                                    formModuleId: "${item.formModuleId}",
                                                                    foreignKeyName: "${item.foreignKeyName}",
                                                                    foreignKeyValue: $("#primaryId").val()
                                                                });
                                                            })
                                                            FormConfigureUtils.loadDetailInfoList({
                                                                formModuleId: '${item.formModuleId}',
                                                                fieldList: $("#lbl_json_field_${item.id}").text(),
                                                                targetList: "tb_detail_list_${item.id}",
                                                                targetForm: "frm_detail_${item.id}",
                                                                targetModal: "modal_detail_${item.id}",
                                                                foreignKeyName: "${item.foreignKeyName}",
                                                                foreignKeyValue: $("#primaryId").val(),
                                                                tableName: "${item.tableName}"
                                                            });


                                                            FormConfigureUtils.getDynamicFormHtml({
                                                                formModuleId: '${item.formModuleId}',
                                                                readOnly: false,
                                                                jsonValue: $("#lbl_json_table_${item.tableName}").text(),
                                                                success: function (html) {
                                                                    $("#detail_content_${item.id}").append(html);
                                                                }
                                                            });
                                                        })

                                                    </script>
                                                </c:when>
                                                <c:otherwise>
                                                    <form class="form-horizontal">
                                                        <input type="hidden" name="id"
                                                               value="${empty tableJsonData?"0":tableJsonData.get(item.tableName).getKey()}">
                                                        <div id="detail_content_${item.id}">
                                                        </div>
                                                    </form>
                                                    <script type="text/javascript">
                                                        $(function () {
                                                            FormConfigureUtils.getDynamicFormHtml({
                                                                formModuleId: '${item.formModuleId}',
                                                                readOnly: false,
                                                                jsonValue: $("#lbl_json_table_${item.tableName}").text(),
                                                                success: function (html) {
                                                                    $("#detail_content_${item.id}").append(html);
                                                                }
                                                            });
                                                        })
                                                    </script>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <jsp:include page="${item.customUrl}.jsp"></jsp:include>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:when>
                <%--其它节点显示的书数据--%>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${item.bisConfigure eq true}">
                            <div class="x_panel">
                                <div class="x_title collapse-link">
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                                    </ul>
                                    <h2> ${item.formModuleName}</h2>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <div class="x_content" id="panel_body_${item.id}">
                                        <c:choose>
                                            <c:when test="${item.bisMultiple eq true}">
                                                <label id="lbl_json_field_${item.id}"
                                                       style="display: none;">${item.fieldListJson}</label>
                                                <table id="tb_detail_list_${item.id}" class="table table-bordered">
                                                </table>
                                                <div id='modal_detail_${item.id}'
                                                     class="modal fade bs-example-modal-sm"
                                                     data-backdrop="static" aria-hidden="true"
                                                     role="dialog" data-keyboard="false" tabindex="1"
                                                     style="display: none;">
                                                    <div class="modal-dialog modal-lg">
                                                        <div class="modal-content">
                                                            <div class='modal-header'>
                                                                <button type='button' class='close'
                                                                        data-dismiss='modal'
                                                                        aria-label='Close'><span
                                                                        aria-hidden='true'>&times;</span>
                                                                </button>
                                                                <h4 class='modal-title'>${item.formModuleName}</h4>
                                                            </div>
                                                            <form class="form-horizontal"
                                                                  id='frm_detail_${item.id}'
                                                                  class='form-horizontal'>
                                                                <input type='hidden' name='id' value="0">
                                                                <div class='modal-body'>
                                                                    <div class='row'>
                                                                        <div class='col-md-12'>
                                                                            <div class='x_content'>
                                                                                <div id="detail_content_${item.id}">

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
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <script type="text/javascript">
                                                    $(function () {
                                                        beforeAddFunction["${item.tableName}"] = [];
                                                        beforeEditFunction["${item.tableName}"] = [];
                                                        beforeViewFunction["${item.tableName}"] = [];
                                                        FormConfigureUtils.loadDetailInfoList({
                                                            readOnly: true,
                                                            formModuleId: '${item.formModuleId}',
                                                            fieldList: $("#lbl_json_field_${item.id}").text(),
                                                            targetList: "tb_detail_list_${item.id}",
                                                            targetForm: "frm_detail_${item.id}",
                                                            targetModal: "modal_detail_${item.id}",
                                                            foreignKeyName: "${item.foreignKeyName}",
                                                            foreignKeyValue: $("#primaryId").val(),
                                                            tableName: "${item.tableName}",
                                                            beforeEdit: function (row) {
                                                                console.log(row);
                                                                var fns = beforeEditFunction["${item.tableName}"];
                                                                if (fns) {
                                                                    $.each(fns, function (i, item) {
                                                                        item(row);
                                                                    });
                                                                }
                                                            }
                                                        });

                                                        FormConfigureUtils.getDynamicFormHtml({
                                                            formModuleId: '${item.formModuleId}',
                                                            readOnly: false,
                                                            jsonValue: $("#lbl_json_table_${item.id}").text(),
                                                            success: function (html) {
                                                                $("#detail_content_${item.id}").append(html);
                                                            }
                                                        });
                                                    })
                                                </script>
                                            </c:when>
                                            <c:otherwise>
                                                <form class="form-horizontal">
                                                    <div id="detail_content_${item.id}">
                                                    </div>
                                                </form>
                                                <script type="text/javascript">
                                                    $(function () {
                                                        FormConfigureUtils.getDynamicFormHtml({
                                                            formModuleId: '${item.formModuleId}',
                                                            readOnly: true,
                                                            jsonValue: $("#lbl_json_table_${item.tableName}").text(),
                                                            success: function (html) {
                                                                $("#detail_content_${item.id}").append(html);
                                                            }
                                                        });
                                                    })
                                                </script>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <jsp:include page="${item.customDisplayUrl}.jsp"></jsp:include>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </c:if>
    </c:if>
</c:forEach>
