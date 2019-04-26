<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <%@include file="/views/share/project/projectInfoSimple.jsp" %>
            <%@include file="/views/share/project/projectPlanDetails.jsp" %>

            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>${declareRecord.name}</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frm_assess" class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">评估人员</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${thisUserInfo.userName}</label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">核对日期</label>
                                <div class="col-sm-3">
                                    <label class="form-control"><fmt:formatDate
                                            value="${surveyAssetInventory.checkDate}" pattern="yyyy-MM-dd"/></label>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">是否查看原件</label>
                                <div class="col-sm-3">
                                    <label class="form-control"> ${surveyAssetInventory.bisCheckOriginal eq true? '是':'否'}</label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">分割限制</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyAssetInventory.segmentationLimit}</label>
                                </div>
                            </div>
                            <div id="showUse" style="display: none">
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">能否使用</label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${surveyAssetInventory.canUse}</label>
                                    </div>
                                </div>
                                <div class="x-valid">
                                    <label class="col-sm-1 control-label">证载用途</label>
                                    <div class="col-sm-3">
                                        <label class="form-control">${surveyAssetInventory.applicationName}</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group" id="showCertificate" style="display: none;">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">是否办证</label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyAssetInventory.certificateName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">证明文件</label>
                                <div class="col-sm-3">
                                    <div id="_checkOriginalFile"></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">说明</label>
                                <div class="col-sm-11">
                                    <label class="form-control">${surveyAssetInventory.remark}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">证书信息</label>
                                <div class="col-sm-3">
                                    <button type="button" class="btn btn-success" onclick="checkRealEstate()"
                                            data-toggle="modal"> 查看
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>清查内容</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <table class="table" id="tb_List">
                        <thead>
                        <tr>
                            <th style="width: 10%">一致性内容</th>
                            <th style="width: 10%">登记</th>
                            <th style="width: 10%">实际</th>
                            <th style="width: 6%">是否一致</th>
                            <th style="width: 10%">差异原因</th>
                            <th style="width: 10%">证明文件</th>
                            <th style="width: 10%">证明文件附件</th>
                            <th style="width: 5%">证明人</th>
                            <th style="width: 8%">调查时间</th>
                            <th style="width: 6%">确认一致</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${surveyAssetInventoryContentVos}" var="item" varStatus="s">
                            <tr>
                                <td>
                                    <label class="form-control">${item.inventoryContentName}</label>
                                </td>
                                <td>
                                    <label class="form-control">${item.registration}</label>
                                </td>
                                <td>
                                    <label class="form-control">${item.actual}</label>
                                </td>
                                <td>
                                    <label class="form-control">${item.areConsistent}</label>
                                </td>
                                <c:if test="${item.areConsistent eq '不一致'}">
                                    <td>
                                        <label class="form-control">${item.differenceReason}</label>
                                    </td>
                                    <td>
                                        <label class="form-control">${item.credential}</label>
                                    </td>
                                    <td>
                                        <div id="_credentialAccessory${item.id}"></div>
                                        <script type="text/javascript">
                                            $(function () {
                                                //清查内容附件加载
                                                showFileCommon("${item.id}");
                                            })
                                        </script>
                                    </td>
                                    <td>
                                        <label class="form-control">${item.voucher}</label>
                                    </td>
                                    <td>
                                        <label class="form-control">
                                            <fmt:formatDate value="${item.surveyTime}" pattern="yyyy-MM-dd"/>
                                        </label>
                                    </td>
                                    <td>
                                        <label class="form-control">${item.sureConsistent}</label>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>税费缴纳调查</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="taxesPaymentSurvey" class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    缴纳情况
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyAssetInventory.paymentStatus}
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="paymentItem">

                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>损坏调查表</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="damageSurvey" class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    区位是否损坏
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyAssetInventory.rimIsNormal}
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="zoneBit">

                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    实物状况是否损坏
                                </label>
                                <div class="col-sm-3">
                                    <label class="form-control">${surveyAssetInventory.entityIsDamage}
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="entity">

                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>转让限制</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    转让限制
                                </label>
                                <div class="col-sm-11">
                                    <label class="form-control">${surveyAssetInventory.transferLimit}
                                    </label>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
    <%--        <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h3>他项权利</h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <table class="table table-bordered" id="tb_inventory_right_list">
                        <!-- cerare document add ajax data-->
                    </table>
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">特殊情况</label>
                                <div class="col-sm-11">
                                    <label class="form-control">${surveyAssetInventory.specialCase}</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">特殊情况附件</label>
                                <div class="col-sm-3">
                                    <div id="_specialCaseFile"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>--%>
            <%@include file="/views/share/form_approval.jsp" %>
            <%@include file="/views/share/form_log.jsp" %>
        </div>
    </div>
</div>
</body>
<!--查看他项权利信息-->
<%--<div id="viewInventoryRightModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">他项权利信息</h3>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">类型</label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="typeName"></label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">类别</label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="categoryName"></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">他权证编号</label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="number"></label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">登记日期</label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="registerDate"></label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">
                            义务人
                        </label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="obligor"></label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">
                            权利人
                        </label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="obligee"></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">
                            登记金额
                        </label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="registerAmount"></label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">
                            行权金额
                        </label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="actualAmount"></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">
                            登记面积
                        </label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="registerArea"></label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">
                            他权级次
                        </label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="rightRank"></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">开始日期</label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="beginDate"></label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">结束日期</label>
                        <div class="col-sm-4">
                            <label class="form-control" data-name="endDate"></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">备注</label>
                        <div class="col-sm-10">
                            <label class="form-control" data-name="remark"></label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="x-valid">
                        <label class="col-sm-2 control-label">
                            附件
                        </label>
                        <div class="col-sm-10">
                            <div id="_inventoryRightFile"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>--%>
<%@include file="/views/share/main_footer.jsp" %>
<%@include file="/views/project/stageSurvey/certificate.jsp" %>
<script type="application/javascript">
    $(function () {
        loadAssetOtherRightList();

        FileUtils.getFileShows({
            target: "checkOriginalFile",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventory,
                fieldsName: AssessUploadKey.INVENTORY_CHECK_ORIGINAL,
                tableId: '${surveyAssetInventory.id}'
            },
            deleteFlag: false
        })

        FileUtils.getFileShows({
            target: "specialCaseFile",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventory,
                fieldsName: AssessUploadKey.INVENTORY_SPECIAL_CASE,
                tableId: '${surveyAssetInventory.id}'
            },
            deleteFlag: false
        })

        if ("${surveyAssetInventory}") {
            showOther();

            writeHTMLData('zoneProjectName', 'zoneProjectItem', 'zoneBit', ${surveyAssetInventory.zoneDamage});
            writeHTMLData('entityProjectName', 'entityProjectItem', 'entity', ${surveyAssetInventory.entityDamage});
            writePaymentHTMLData(${surveyAssetInventory.paymentContent});
        }
    })

    //获取对应房产证信息
    function checkRealEstate() {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/surveyAssetInventoryRight/getRealEstateId",
            type: "get",
            dataType: "json",
            data: {declareRecordId:"${projectPlanDetails.declareRecordId}"},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    certificate.prototype.getAndInit(result.data.dataTableName, result.data.dataTableId);
                }
                else {
                    Alert("获取数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    }

    //显示附件通用
    function showFileCommon(tableId) {
        FileUtils.getFileShows({
            showMode: 'table',
            target: "credentialAccessory" + tableId,
            formData: {
                tableName: AssessDBKey.SurveyAssetInventoryContent,
                tableId: tableId
            },
            deleteFlag: false
        })
    }

    function saveform() {
        saveApprovalform("");
    }

    //加载他项权利附件
    function loadInventoryRightFile(tableId) {
        FileUtils.getFileShows({
            target: "inventoryRightFile",
            formData: {
                tableName: AssessDBKey.SurveyAssetInventoryRight,
                tableId: tableId
            },
            deleteFlag: true
        });
    }

    function loadAssetOtherRightList() {
        var cols = [];
        cols.push({field: 'typeName', title: '类型'});
        cols.push({field: 'categoryName', title: '类别'});
        cols.push({field: 'number', title: '他权证编号'});
        cols.push({field: 'obligor', title: '义务人'});
        cols.push({field: 'obligee', title: '权利人'});
        cols.push({field: 'registerArea', title: '登记面积'});
        cols.push({field: 'rightRank', title: '他权级次'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top"  onclick="viewInventoryRightInfo(' + index + ')"><i class="fa fa-search fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_inventory_right_list").bootstrapTable('destroy');
        TableInit("tb_inventory_right_list", "${pageContext.request.contextPath}/surveyAssetInventoryRight/getListByPlanDetailsId", cols, {
            planDetailsId: '${projectPlanDetails.id}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
        });
    }

    //查看他项信息
    function viewInventoryRightInfo(index) {
        var row = $("#tb_inventory_right_list").bootstrapTable('getData')[index];
        $("#viewInventoryRightModal").find('[data-name]').each(function () {
            $(this).text('').text(row[$(this).attr('data-name')]);
        })
        loadInventoryRightFile(row.id);
        $("#viewInventoryRightModal").find('[data-name=registerDate]').text(formatDate(row.registerDate, false));
        $("#viewInventoryRightModal").find('[data-name=beginDate]').text(formatDate(row.beginDate, false));
        $("#viewInventoryRightModal").find('[data-name=endDate]').text(formatDate(row.endDate, false));
        $("#viewInventoryRightModal").modal();

    };

    function showOther() {
        if ("${surveyAssetInventory.segmentationLimit}" == "可分") {
            $("#showCertificate").show();
            $("#showUse").show();
        } else {
            $("#showCertificate").hide();
            $("#showUse").hide();
        }
    }

    function writeHTMLData(projectName, projectItem, item, json) {
        $("." + item).empty();
        var jsonarray = eval(json);
        $.each(jsonarray, function (i, n) {
            var html = "<div class='form-group' >";
            html += "<div class='x-valid'>";

            html += "<label class='col-sm-1 control-label'>" + "项目" + "</label>";
            html += "<div class='col-sm-3'>";
            html += "<label class='form-control'>" + n[projectName];
            html += "</label>";
            html += "</div>";

            html += "<label class='col-sm-1 control-label'>" + "明细" + "</label>";
            html += "<div class='col-sm-3'>";
            html += "<label class='form-control'>" + n[projectItem];
            html += "</label>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            $("." + item).append(html);
        })
    }

    function writePaymentHTMLData(json) {
        $(".paymentItem").empty();
        var jsonarray = eval(json);
        $.each(jsonarray, function (i, n) {
            var html = "<div class='form-group' >";
            html += "<div class='x-valid'>";

            html += "<label class='col-sm-1 control-label'>" + "项目" + "</label>";
            html += "<div class='col-sm-3'>";
            html += "<label class='form-control'>" + n["projectName"];
            html += "</label>";
            html += "</div>";

            html += "<label class='col-sm-1 control-label'>" + "说明" + "</label>";
            html += "<div class='col-sm-3'>";
            html += "<label class='form-control'>" + n["remark"];
            html += "</label>";
            html += "</div>";

            html += "<label class='col-sm-1 control-label'>" + "金额" + "</label>";
            html += "<div class='col-sm-2'>";
            html += "<label class='form-control'>" + n["money"];
            html += "</label>";
            html += "</div>";

            html += "</div>";
            html += "</div>";
            $(".paymentItem").append(html);
        })
    }

</script>
</body>
</html>

