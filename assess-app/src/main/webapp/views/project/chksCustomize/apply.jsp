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

            <script type="text/javascript" src="${pageContext.request.contextPath}/js/declare/declare.common.js?v=${assessVersion}"></script>
            <!-- 申报各种类型的html视图 -->
            <%@include file="/views/project/stageDeclare/declareApprovalModel.jsp" %>

            <div class="x_panel area_panel">

                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
                    </ul>
                    <h3>${chksCustomerAssessmentPlanDetail.typeName}</h3>
                    <div class="clearfix"></div>
                </div>

                <div class="x_content">

                    <div id="chksCustomerAssessmentPlanDetailSelector">
                        <form class="form-horizontal">
                        </form>
                    </div>

                </div>

            </div>

            <div class="x_panel">
                <div class="x_content">

                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <form>
                            <table class="table" id="chksTableList">
                                <thead>
                                <tr>
                                    <th width="3%">序号</th>
                                    <th width="7%">节点名称</th>
                                    <th width="50%">考核标准</th>
                                    <th width="10%">打分</th>
                                    <th width="10%">说明</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </form>
                    </div>

                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <table class="table" id="boxReActivityDtoTableView">
                        </table>
                    </div>

                    <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 col-xs-offset-6 col-sm-offset-6 col-md-offset-6 col-lg-offset-6">
                        <button class="btn btn-success" onclick="chksCustomer.saveAssessmentItem();">
                            保存考核记录
                        </button>
                    </div>
                </div>
            </div>


            <div class="x_panel">
                <div class="x_content">
                    <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 col-xs-offset-6 col-sm-offset-6 col-md-offset-6 col-lg-offset-6">
                        <button  class="btn btn-default" onclick="window.close()">
                            关闭
                        </button>
                    </div>
                </div>
            </div>
            <%@include file="/views/method/module/economicIndicators.jsp" %>
        </div>
    </div>
</div>
</body>
<%@include file="/views/share/main_footer.jsp" %>
<%@include file="/views/share/chksCommon.jsp" %>
<input type="file" id="ajaxFileUpload" name="file" style="display: none;">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script>

var chksCustomer = {} ;

chksCustomer.config = {
    selector:"#chksCustomerAssessmentPlanDetailSelector"
} ;

chksCustomer.houseConfig = {
    fileId: declareCommon.config.house.fileId
} ;

chksCustomer.landConfig = {
    fileId: declareCommon.config.land.fileId
} ;

chksCustomer.declareRealtyRealEstateCertConfig = {
    newFileId: declareCommon.config.declareRealty.newFileId,
} ;

chksCustomer.saveAssessmentItem = function () {
    var target = $("#chksTableList").find("tbody");
    if (!vaildChksData(target)){
        return false;
    }
    var filterData = [];
    var data = [];
    var remarks = target.find("textarea[name=remarks]").val();
    assessmentCommonHandle.getChksSonData(target, data);
    for (var i = 0; i < data.length; i++) {
        if (data[i].actualScore) {
            filterData.push(data[i]);
        }
    }
    if (filterData.length == 0){
        toastr.warning("详情页考核需要填写全部数据!");
        return false;
    }
    var parentData = {
        spotActivityId: '0',
        projectId: '${chksCustomerAssessmentPlanDetail.projectId}',
        processInsId: '${chksCustomerAssessmentPlanDetail.processInsId}',
        boxId: '${boxReDto.id}',
        activityId: '${chksCustomerAssessmentPlanDetail.activityId}',
        activityName: '${chksCustomerAssessmentPlanDetail.activityName}',
        remarks: remarks,
        tableId:'${chksCustomerAssessmentPlanDetail.id}'
    };
    assessmentCommonHandle.saveAssessmentServer({chksScore: JSON.stringify(filterData), fomData: JSON.stringify(parentData)}, function (data) {
        toastr.warning("考核成功!");
        chksCustomer.loadChksServerViewTable() ;
        chksCustomer.loadChksServerNew() ;
    });
};

chksCustomer.loadChksServerNew = function () {
    var target = $("#chksTableList").find("tbody");
    var option = {
        boxId: '${boxReDto.id}',
        activityId: '${chksCustomerAssessmentPlanDetail.activityId}',
        projectId: '${chksCustomerAssessmentPlanDetail.projectId}',
        processInsId: '${chksCustomerAssessmentPlanDetail.processInsId}',
        tableId:'${chksCustomerAssessmentPlanDetail.id}',//必须要
        isEffective: true //数据必须有效
    };
    assessmentCommonHandle.loadChksServerBase(option, {
        boxId: option.boxId,
        boxReActivitiId: option.activityId
    }, target);
};

chksCustomer.loadChksServerViewTable = function () {
    var target = $("#boxReActivityDtoTableView");
    var options = {
        boxId: '${boxReDto.id}',
        projectId: '${chksCustomerAssessmentPlanDetail.projectId}',
        processInsId: '${chksCustomerAssessmentPlanDetail.processInsId}',
        spotActivityId: 0,
        isEffective:true
    };
    if ('${activityDtoList}') {
        var activityIds = [];
        var data = null;
        try {
            data = JSON.parse('${el:toJsonString(activityDtoList)}');
        } catch (e) {
            console.log(e);
        }
        if (data) {
            $.each(data, function (i, item) {
                activityIds.push(item.id);
            });
        }
        if (activityIds.length != 0) {
            options.activityIdList = activityIds.join(",");
        }
    }
    assessmentCommonHandle.loadChksServerViewBaseTable(target,options) ;
};

chksCustomer.init = function () {
    var selector = $(chksCustomer.config.selector) ;

    var data = JSON.parse('${el:toJsonString(targetObjectInfo)}') ;
    var obj = JSON.parse('${el:toJsonString(chksCustomerAssessmentPlanDetail)}') ;

    if (obj.tableName == AssessDBKey.DeclareRealtyHouseCert){
        selector.find("#" + commonDeclareApprovalModel.config.house.handleId).remove();
        selector.find("form").append(commonDeclareApprovalModel.house.getHtml());
        declareCommon.showHtmlMastInit(selector.find("form"), function (area) {
            declareCommon.initHouse(data, selector.find("form"), [chksCustomer.houseConfig.fileId], null, false);
        });
    }


    if (obj.tableName == AssessDBKey.DeclareRealtyLandCert){
        selector.find("#" + commonDeclareApprovalModel.config.land.handleId).remove();
        selector.find("form").append(commonDeclareApprovalModel.land.getHtml());
        declareCommon.showHtmlMastInit(selector.find("form"), function (area) {
            declareCommon.initLand(data, selector.find("form"), [chksCustomer.landConfig.fileId], null, false);
        });
    }

    if (obj.tableName == AssessDBKey.DeclareRealtyRealEstateCert){
        selector.find("#" + commonDeclareApprovalModel.config.realEstateCert.handleId).remove();
        selector.find("form").append(commonDeclareApprovalModel.realEstateCert.getHtml());
        declareCommon.showHtmlMastInit(selector.find("form"), function (area) {
            declareCommon.initDeclareRealty(data, selector.find("form"), [chksCustomer.declareRealtyRealEstateCertConfig.newFileId], null, false);
        });
    }

    console.log(data) ;
    console.log(obj) ;
};

$(document).ready(function () {

    chksCustomer.init() ;

    chksCustomer.loadChksServerViewTable() ;

    chksCustomer.loadChksServerNew() ;
}) ;

</script>

<script type="application/javascript">




</script>

</html>

