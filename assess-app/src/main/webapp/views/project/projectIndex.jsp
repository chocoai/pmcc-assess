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
            <!--填写表单-->
            <div class="x_panel">
                <div class="x_title">
                    <h2>项目信息</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="bid_info_form" class="form-horizontal">
                        <input type="hidden" id="projectId" name="id" value="${projectBidInfo.id}">
                        <div class="form-group">

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    项目类型<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <select name="projectTypeId" id="projectTypeId" class="form-control" required>

                                        <c:forEach items="${BidProjectCategory}" var="item" varStatus="status">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    项目类别<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <select name="projectCategoryId" id="projectCategoryId" class="form-control" required>
                                        <option value="" selected="selected">-请选择-</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">招标项目名称<span class="symbol required"></span></label>
                                <div class="col-sm-11">
                                    <input required placeholder="" id="bidProjectName" name="bidProjectName"
                                           value="${projectBidInfo.bidProjectName}" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="x-valid">
                                <label class="col-sm-1 control-label">招标简介<span class="symbol required"></span></label>
                                <div class="col-sm-11">
                                        <textarea name="bidIntroduction" id="bidIntroduction" class="form-control"
                                                  maxlength="100" required>${projectBidInfo.bidIntroduction}</textarea>
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class='x-valid'><label class='col-sm-1 control-label'>项目编号<span
                                    class="symbol required"></span></label>
                                <div class='col-sm-3'><input placeholder=''
                                                             id='bidProjectCode'
                                                             name='bidProjectCode'
                                                             class='form-control' maxlength='50'
                                                             required value="${projectBidInfo.bidProjectCode}">
                                </div>
                            </div>

                            <div class='x-valid'><label class='col-sm-1 control-label'>标书售价</label>
                                <div class='col-sm-3'>
                        <span class="input-icon">
                            <input placeholder=''
                                   id='bidDocumentPrice'
                                   name='bidDocumentPrice'
                                   class='form-control' data-rule-number="true"
                                   value="${projectBidInfo.bidDocumentPrice}">
                            <i class="fa fa-rmb"></i>
                            </span>
                                </div>
                            </div>

                            <div class='x-valid'><label class='col-sm-1 control-label'>标书提交时间<span
                                    class="symbol required"></span></label>
                                <div class='col-sm-3'>
                                    <input placeholder='选择时间' id='tenderSubmissionDate' name='tenderSubmissionDate'
                                           class='form-control date-picker dbtime'
                                           data-date-format="yyyy-mm-dd hh:ii" required readonly
                                           value="<fmt:formatDate value="${projectBidInfo.tenderSubmissionDate}" pattern="yyyy-MM-dd HH:mm"/>">
                                </div>
                            </div>
                        </div>


                        <div class="form-group">

                            <div class='x-valid'><label class='col-sm-1 control-label'>采购类型<span
                                    class="symbol required"></span></label>
                                <div class='col-sm-3'>
                                    <select id='procurementType' name='procurementType' class='form-control' required>
                                        <option value="公开招标" ${projectBidInfo.procurementType eq "公开招标"?"selected":""}>公开招标</option>
                                        <option value="邀请招标" ${projectBidInfo.procurementType eq "邀请招标"?"selected":""}>邀请招标</option>
                                    </select>
                                </div>
                            </div>

                            <div class='x-valid'><label class='col-sm-1 control-label'>渠道来源<span
                                    class="symbol required"></span></label>
                                <div class='col-sm-3'>
                                    <select id='channelSource' name='channelSource' class="form-control search-select select2" required>
                                        <option value="">请选择</option>
                                        <c:forEach var="item" items="${channelSource}">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class='x-valid'><label class='col-sm-1 control-label'>个人来源</label>
                                <div class='col-sm-3'><input placeholder=''
                                                             id='infoSource' name='infoSource'
                                                             class='form-control' maxlength='200'
                                                             value="${projectBidInfo.infoSource}">
                                </div>
                            </div>


                        </div>

                        <div class="form-group">
                            <div class='x-valid'><label class='col-sm-1 control-label'>报名开始日期<span
                                    class="symbol required"></span></label>
                                <div class='col-sm-3'><input placeholder='选择日期' id='startDate'
                                                             name='startDate'
                                                             class='form-control date-picker dbdate'
                                                             data-date-format="yyyy-mm-dd" required readonly
                                                             value="<fmt:formatDate value="${projectBidInfo.startDate}" pattern="yyyy-MM-dd"/>">
                                </div>
                            </div>
                            <div class='x-valid'><label class='col-sm-1 control-label'>报名结束日期<span
                                    class="symbol required"></span></label>
                                <div class='col-sm-3'><input placeholder='选择日期' id='endDate'
                                                             name='endDate'
                                                             class='form-control date-picker dbtime'
                                                             data-date-format="yyyy-mm-dd hh:ii" required readonly
                                                             value="<fmt:formatDate value="${projectBidInfo.endDate}" pattern="yyyy-MM-dd HH:mm"/>">
                                </div>
                            </div>

                            <div class='x-valid'><label class='col-sm-1 control-label'>报名方式<span
                                    class="symbol required"></span></label>
                                <div class='col-sm-3'>
                                    <select placeholder='' id='applyWay' name='applyWay'
                                            class='form-control' required>
                                        <option value="线上" ${projectBidInfo.applyWay eq "线上"? "selected":""}>线上</option>
                                        <option value="现场" ${projectBidInfo.applyWay eq "现场"? "selected":""}>现场</option>
                                        <option value="线上、现场" ${projectBidInfo.applyWay eq "线上、现场"? "selected":""}>线上、现场</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class='form-group'>

                            <div class='x-valid'><label class='col-sm-1 control-label'>招标单位<span
                                    class="symbol required"></span></label>
                                <div class='col-sm-3'>
                                    <input type="hidden" id='bidOrganization' name='bidOrganization'
                                           class='form-control'>
                                    <input id="selectBidOrganization" name="bidOrganizationName"
                                           class='form-control' placeholder='-请选择-' required readonly
                                           value="${translate["bidOrganizationName"]}">
                                </div>
                            </div>

                            <div class='x-valid'><label class='col-sm-1 control-label'>代理机构<span
                                    class="symbol required"></span></label>
                                <div class='col-sm-3'>
                                    <input type="hidden" id='bidAgency' name='bidAgency' class='form-control'>
                                    <input id='selectBidAgency' name='bidAgencyName'
                                           class='form-control' placeholder='-请选择-' required readonly
                                           value="${translate["bidAgencyName"]}">
                                </div>
                            </div>


                            <div class='x-valid'><label class='col-sm-1 control-label'>评标方式<span
                                    class="symbol required"></span></label>
                                <div class='col-sm-3'>
                                    <select required id="bidEvaluateWay" name="bidEvaluateWay" class="form-control">
                                        <option value="综合评标法" ${projectBidInfo.bidEvaluateWay eq "综合评标法"?"selected":""}>综合评标法</option>
                                        <option value="单项评议法" ${projectBidInfo.bidEvaluateWay eq "单项评议法"?"selected":""}>单项评议法</option>
                                        <option value="合理单价评标法" ${projectBidInfo.bidEvaluateWay eq "合理单价评标法"?"selected":""}>合理单价评标法</option>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class='form-group'>
                            <div class='x-valid'><label class='col-sm-1 control-label'>投标主体<span
                                    class="symbol required"></span></label>
                                <div class='col-sm-3'>

                                    <select id="tenderMain" name="tenderMain" class="form-control" required>
                                        <c:forEach items="${tenderMain}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class='x-valid'><label class='col-sm-1 control-label'>联合投标<span
                                    class="symbol required"></span></label>
                                <div class='col-sm-3'>
                                    <select id='bidUnionAllow' name='bidUnionAllow' class='form-control' required>
                                        <c:if test="${projectBidInfo.bidUnionAllow}">
                                            <option value="0">不允许</option>
                                            <option value="1" selected>允许</option>
                                        </c:if>
                                        <c:if test="${!projectBidInfo.bidUnionAllow}">
                                            <option value="0" selected>不允许</option>
                                            <option value="1">允许</option>
                                        </c:if>

                                    </select>
                                </div>
                            </div>

                            <div class='x-valid'><label class='col-sm-1 control-label'>包数</label>
                                <div class='col-sm-3'><input placeholder=''
                                                             id='packageCount' name='packageCount'
                                                             class='form-control'
                                                             data-rule-digits="true"
                                                             value="${projectBidInfo.packageCount}">
                                </div>
                            </div>
                        </div>

                        <div class='form-group' id="isBidUnionAllow">
                            <div class='x-valid'><label class='col-sm-1 control-label'>联合投标体</label>
                                <div class='col-sm-11'>
                                    <input required placeholder="" id="tenderUnionMain" name="tenderUnionMain"
                                           value="${projectBidInfo.tenderUnionMain}" class="form-control">
                                </div>
                            </div>
                        </div>


                        <div class='form-group'>

                            <div class='x-valid'><label class='col-sm-1 control-label'>文件获取方式<span
                                    class="symbol required"></span></label>
                                <div class='col-sm-3'>
                                    <select id='bidFileAcquireWay' name='bidFileAcquireWay' class='form-control' required>
                                        <option value="现场">现场</option>
                                        <option value="网上">网上</option>
                                    </select>
                                </div>
                            </div>
                            <div class='x-valid'><label class='col-sm-1 control-label'>文件获取地点<span
                                    class="symbol required"></span></label>
                                <div class='col-sm-3'><input placeholder=''
                                                             id='bidFileAcquireSite' name='bidFileAcquireSite'
                                                             class='form-control' required maxlength='100'
                                                             value="${projectBidInfo.bidFileAcquireSite}">
                                </div>
                            </div>

                            <div class='x-valid'><label class='col-sm-1 control-label'>文件获取截止日期<span class="symbol required"></span></label>
                                <div class='col-sm-3'><input placeholder='选择日期'
                                                             id='bidFileAcquireEndTime'
                                                             name='bidFileAcquireEndTime'
                                                             class='form-control date-picker dbtime'
                                                             data-date-format="yyyy-mm-dd hh:ii" required readonly
                                                             value="<fmt:formatDate value="${projectBidInfo.bidFileAcquireEndTime}" pattern="yyyy-MM-dd HH:mm"/>">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div> <!--用于控制界面显示与否-->
                                <div class='x-valid'><label class='col-sm-1 control-label'>开标时间<span class="symbol required"></span></label>
                                    <div class='col-sm-3'><input placeholder='选择时间' id='bidOpenDate'
                                                                 name='bidOpenDate'
                                                                 class='form-control date-picker dbtime'
                                                                 data-date-format="yyyy-mm-dd hh:ii" readonly
                                                                 value="<fmt:formatDate value="${projectBidInfo.bidOpenDate}" pattern="yyyy-MM-dd HH:mm"/>">
                                    </div>
                                </div>

                                <div class='x-valid'><label class='col-sm-1 control-label'>开标地点<span class="symbol required"></span></label>
                                    <div class='col-sm-3'><input placeholder='' id='bidOpenSite'
                                                                 name='bidOpenSite' class='form-control'
                                                                 maxlength='100' value="${projectBidInfo.bidOpenSite}"></div>
                                </div>

                                <div class='x-valid'><label class='col-sm-1 control-label'>结果发布渠道<span class="symbol required"></span></label>
                                    <div class='col-sm-3'>
                                        <input placeholder='' id='bidPublishWay'
                                               name='bidPublishWay' class='form-control'
                                               maxlength='100' value="${projectBidInfo.bidPublishWay}">
                                        <%--<select placeholder='' id='bidPublishWay' name='bidPublishWay' class='form-control search-select'></select>--%>
                                    </div>
                                </div>

                            </div>

                        </div>

                        <div class='form-group'>
                            <div class='x-valid'><label class='col-sm-1 control-label'>特殊要求<span
                                    class="symbol required"></span></label>
                                <div class='col-sm-11'><textarea placeholder=''
                                                                 id='specialRequirements'
                                                                 name='specialRequirements' class='form-control'
                                                                 required
                                                                 maxlength='200'>${projectBidInfo.specialRequirements}</textarea>
                                </div>
                            </div>
                        </div>
                        <div class='form-group'>
                            <div class='x-valid'><label class='col-sm-1 control-label'>处理建议<span
                                    class="symbol required"></span></label>
                                <div class='col-sm-3'>
                                    <select id='bidDoSuggestion' name='bidDoSuggestion' class='form-control'>
                                        <option value="参与" ${projectBidInfo.bidDoSuggestion eq "参与"?"selected":""}>参与</option>
                                        <option value="不参与" ${projectBidInfo.bidDoSuggestion eq "不参与"?"selected":""}>不参与</option>
                                        <option value="获取招标文件确认" ${projectBidInfo.bidDoSuggestion eq "获取招标文件确认"?"selected":""}>获取招标文件确认</option>
                                    </select>
                                </div>
                            </div>

                            <div class="x-valid">
                                <label class="col-sm-1 control-label">
                                    标书评级<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-3">
                                    <select class="form-control search-select select2" id="bidGrade" name="bidGrade" required>
                                        <option value="">请选择</option>
                                        <c:forEach var="item" items="${bidGrade}">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-1 control-label">
                                标书评级说明<span class="symbol required"></span>
                            </label>
                            <div class="x-valid">
                                <div class="col-sm-11">
                                    <textarea required placeholder="" id="bidGradeRemarks" name="bidGradeRemarks" class="form-control">${projectBidInfo.bidGradeRemarks}</textarea>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-5">
                            <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                取消
                            </button>

                            <button id="commit_btn" class="btn btn-success" onclick="commitApply();">
                                提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>


</body>
</html>


<script type="text/javascript">
    $(function () {
        $("#projectTypeId").change(function () {
            loadCategroy($(this).val());
        });
        loadCategroy($("#projectTypeId").val());
        $("#bid_info_form").validate();
    });

    function loadCategroy(typeId) {
        $("#projectCategoryId").html("");
        loadCategoryByPid(typeId, function (html, data) {
            $("#projectCategoryId").html(html);
        });
    }

    function commitApply() {
//        if (!$("#bid_info_form").valid()) {
//            return false;
//        }
        Loading.progressShow();
        $.ajax({
            url: getContextPath() + "/projectInfo/projectSubmit",
            type: "post",
            data: {
                formData: JSON.stringify(formParams("bid_info_form"))
            },
            //async: false, //必须使用同步，否则提交按钮会存在问题
            dataType: "json",
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    //保存完后其他动作
                    Alert("提交数据成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            }

            ,
            error: function (e) {
                Loading.progressHide();
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    }
</script>