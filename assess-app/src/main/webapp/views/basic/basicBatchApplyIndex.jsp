<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" id="basicApplyId" role="main" style="margin-left: 0">
            <div class="page-title">
                <div class="title_left">
                    <h2><i class="fa "></i>
                        案例批量申请
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
                        楼盘信息
                    </h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="basicBatchApplyFrm" class="form-horizontal">
                        <input type="hidden" name="id" value="${applyBatch.id}">
                        <div class="form-group">
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    省
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <select name="province" class="form-control search-select select2" required>
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    市
                                </label>
                                <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                    <select name="city" class="form-control search-select select2" required>
                                    </select>
                                </div>
                            </div>
                            <div class="x-valid">
                                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                    楼盘名称
                                </label>
                                <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                    <input type="hidden" id="estateId" name="estateId" value="${applyBatch.estateId}">
                                    <input type="text" class="form-control" name="estateName" placeholder="楼盘名称"
                                           required value="${applyBatch.estateName}">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <span class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-xs-offset-1 col-sm-offset-1 col-md-offset-1 col-lg-offset-1 checkbox-inline">
                                <input type="radio" id="applyFormType0" name="type" value="0">
                                <label for="applyFormType0">非工业交通仓储</label>
                            </span>

                            <span class=" col-xs-2  col-sm-2  col-md-2  col-lg-2   checkbox-inline">
                                <input type="radio" id="applyFormType1" name="type" value="1">
                                <label for="applyFormType1">工业交通仓储</label>
                            </span>
                            <span class=" col-xs-2  col-sm-2  col-md-2  col-lg-2   checkbox-inline">
                                <input type="radio" id="applyFormType2" name="type" value="2">
                                <label for="applyFormType2">构筑物</label>
                            </span>
                            <a id="saveApplyInfoBtn" class="btn btn-warning" onclick="saveApplyInfo(this);">
                                <c:if test="${empty applyBatch}">
                                    添加楼栋单元房屋
                                </c:if>
                                <c:if test="${!empty applyBatch}">
                                    查看楼栋单元房屋
                                </c:if>
                            </a>
                        </div>
                    </form>
                    <div id="showTree" style="display: none">
                    <%@include file="/views/basic/basicBatchTool/batchTreeTool.jsp" %>
                    </div>
                </div>
            </div>

            <div class="x_panel">
                <div class="x_content">
                    <div style="text-align: center;">
                        <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                            取消
                        </button>
                        <button class="btn btn-warning" onclick="saveDraft()">
                            保存<i style="margin-left: 10px" class="fa fa-save"></i>
                        </button>
                        <button id="btn_submit" class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </div>
                </div>
            </div>
            <c:if test="${processInsId ne '0'}">
                <%@include file="/views/share/form_log.jsp" %>
                <form id="frm_approval">
                    <%@include file="/views/share/ApprovalVariable.jsp" %>
                </form>
            </c:if>
        </div>
    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/map.position.js"></script>
<%@include file="/views/share/main_footer.jsp" %>
</html>

<script type="text/javascript">
    $(function () {
        if (${!empty applyBatch}) {
            AssessCommon.initAreaInfo({
                provinceTarget: $("#basicBatchApplyFrm").find('[name=province]'),
                cityTarget: $("#basicBatchApplyFrm").find('[name=city]'),
                provinceValue: '${applyBatch.province}',
                cityValue: '${applyBatch.city}'
            });

            $("#basicBatchApplyFrm").find("input[type='radio'][name='type'][value='${applyBatch.type}']").trigger('click');
            batchTreeTool.ztreeInit(JSON.parse('${el:toJsonString(applyBatch)}'));
        } else {
            //定位成功回调方法
            mapPosition.getCurrentCity(function (province, city) {
                AssessCommon.initAreaInfo({
                    provinceTarget: $("#basicBatchApplyFrm").find('[name=province]'),
                    cityTarget: $("#basicBatchApplyFrm").find('[name=city]'),
                    provinceDefaultText: province,
                    cityDefaultText: city
                });
            });
        }
    });

    //申请提交
    function submit() {
        if ("${processInsId}" != "0") {
            editSubmit();
        } else {
            if (!$("#basicBatchApplyFrm").valid()) {
                return false;
            }
            var id = $("#basicBatchApplyFrm").find("input[name='id']").val();
            if (!id) {
                Alert("请先添加楼盘的信息");
                return false;
            }
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/basicApplyBatch/basicApplyBatchSubmit",
                type: "post",
                dataType: "json",
                async: false,
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        Alert("提交数据成功!", 1, null, function () {
                            window.close();
                        });

                    } else {
                        Alert(result.errmsg);
                    }
                }
            });
        }
    }

    //返回修改提交
    function editSubmit() {
        var data = {};
        var approvalData = formParams('frm_approval');
        data = $.extend({}, approvalData, data);
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/editSubmit",
            type: "post",
            dataType: "json",
            async: false,
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert(result.errmsg);
                }
            }
        });
    }


    //保存草稿
    function saveDraft() {
        if (!$("#basicBatchApplyFrm").valid()) {
            return false;
        }
        var radioValue = $("#basicBatchApplyFrm").find("input[type='radio']:checked").val();
        if(!radioValue){
            Alert("请选择类型");
            return false;
        }
        Loading.progressShow();
        var formData = formParams("basicBatchApplyFrm");
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/saveApplyDraftInfo",
            type: "post",
            dataType: "json",
            data: formData,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("保存草稿成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert(result.errmsg);
                }
            }
        });
    }
</script>

<script type="text/javascript">

    //添加楼栋等信息
    function saveApplyInfo(_this) {
        if (!$("#basicBatchApplyFrm").valid()) {
            return false;
        }
        var radioValue = $("#basicBatchApplyFrm").find("input[type='radio']:checked").val();
        if (!radioValue) {
            Alert("请选择类型");
            return false;
        }
        var formData = formParams("basicBatchApplyFrm");
        $.ajax({
            url: "${pageContext.request.contextPath}/basicApplyBatch/saveApplyInfo",
            type: "post",
            dataType: "json",
            data: formData,
            success: function (result) {
                console.log(result.data)
                if (result.ret) {
                    $(_this).hide();
                    $("#basicBatchApplyFrm").find("input[name='id']").val(result.data.id);
                    $("#estateId").val(result.data.estateId);
                    $("#basicBatchApplyFrm").find("input").attr("readonly", "readonly");
                    $("#basicBatchApplyFrm").find("select").attr("disabled", "disabled");
                    $("#basicBatchApplyFrm").find("input[type='radio']").on('click', function () {
                        return false;
                    });
                    $("#showTree").show();
                    batchTreeTool.ztreeInit(result.data);
                } else {
                    Alert(result.errmsg);
                }
            }
        });
    }


</script>