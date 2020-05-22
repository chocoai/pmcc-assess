<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en" class="no-js">

<div class="col-md-12" >
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    <input type="hidden" name="projectInfoVoJson" id="projectInfoVoJson"
                           value='${projectInfoVoJson}'>
                    项目信息
                    <small><input type="button" id="btnViewProjectInfoMore" class="btn btn-success btn-sm"
                                  value="查看更多..."/>
                    </small>
                    <small><a href="${pageContext.request.contextPath}/projectCenter/projectInfo?projectId=${projectInfo.id}" target="_blank" style="color: white;" class="btn btn-success btn-sm">项目中心</a>
                    </small>
                    <small>${projectInfo.projectCategoryName}</small>
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-up"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body" style="display: none">
            <div class="form-horizontal">
                <input type="hidden" id="projectId" name="id" value="${projectInfo.id}">
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">项目名称</label>
                            <div class="col-sm-7">
                                <label class="form-control input-full">${projectInfo.projectName}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">紧急程度</label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.urgencyName}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">委托目的</label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.entrustPurposeName}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                委托目的类别
                            </label>
                            <div class="col-sm-3 x-valid">
                                <label class="form-control input-full">${projectInfo.entrustAimTypeName}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">价值类型</label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.valueTypeName}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                委托目的描述
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control input-full">${projectInfo.remarkEntrustPurpose}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                评估范围
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.propertyScopeName}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                评估包括
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.scopeInclude}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                评估不包括
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.scopeNotInclude}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">项目经理</label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.projectMemberVo.userAccountManagerName}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">项目成员</label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.projectMemberVo.userAccountMemberName}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                执业部门
                            </label>
                            <div class="col-sm-3 x-valid">
                                <label class="form-control input-full">${projectInfo.departmentName}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                项目合同
                            </label>
                            <div class="col-sm-11">
                                <div class="input-group">
                                    <label class="form-control" name="contractName">
                                        <c:if test="${!empty projectInfo.contractId}">
                                            <c:forEach var="item" items="${projectInfo.contractList}">
                                                <a href="${sysUrl}/pmcc-contract/contractCurrency/details/${item.key}"
                                                   target="_blank">${item.value} /    </a>
                                            </c:forEach>
                                        </c:if>
                                    </label>

                                    <div class="input-group-prepend">
                                        <button class="btn btn-warning btn-sm "
                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                type="button" onclick="$(this).closest('.input-group').find('label[name=contractName]').html('');">
                                            清空
                                        </button>
                                    </div>
                                    <div class="input-group-prepend">
                                        <button class="btn btn-primary btn-sm "
                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                type="button" onclick="contractObj.selectContract(this);">选择
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">
                                合同金额（元）
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.contractPrice}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                评估基准日
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full"><fmt:formatDate value='${projectInfo.valuationDate}' pattern='yyyy-MM-dd'/></label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                贷款类型
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.loanTypeName}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline">
                            <label class="col-sm-1 col-form-label">
                                业务来源
                            </label>
                            <div class="col-sm-3 x-valid">
                                <label class="form-control input-full">${projectInfo.serviceComeFrom}</label>
                            </div>
                            <label class="col-sm-1 col-form-label">
                                业务来源说明
                            </label>
                            <div class="col-sm-3">
                                <label class="form-control input-full">${projectInfo.serviceComeFromExplain}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 control-label">
                                项目说明
                            </label>
                            <div class="col-sm-11">
                                <label class="form-control input-full">${projectInfo.remarks}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">附件</label>
                            <div class="col-md-3">
                                <div id="_attachmentProjectInfoId"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/pmcc-contract/js/cms_contract_utils.js?v=${assessVersion}"></script>
<script type="text/javascript">

    var contractObj = {};

    /**
     * 数组去重
     * @param arr
     * @returns {Array}
     */
    contractObj.unique = function (arr) {
        var hash = [];
        for (var i = 0; i < arr.length; i++) {
            if (hash.indexOf(arr[i]) == -1) {
                hash.push(arr[i]);
            }
        }
        return hash;
    };



    contractObj.getProjectById = function (id, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/projectInfo/getProjectById",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    };

    contractObj.projectUpdate = function (data, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/projectInfo/projectDataUpdate",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                }
            },
            error: function (result) {
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    };



    /**
     * 合同选择
     */
    contractObj.selectContract = function (_this) {
        cmsContract.select({
            multi: true,//是否允许多选
            appkey: "pmcc-assess",
            onSelected: function (data) {
                var uuids = [];
                var names = [];
                var viewArray = [];
                data.forEach(function (node, i) {
                    if (node.uuid) {
                        uuids.push(node.uuid);
                    }
                    if (node.name) {
                        names.push(node.name);
                    }
                    if (node.uuid && node.name){
                        var url = "<a target='_blank' href='${sysUrl}/pmcc-contract/contractCurrency/details/" + node.uuid +"'"+ ">" +node.name+ "</a>";
                        viewArray.push(url);
                    }
                });
                if (uuids.length == 0) {
                    notifyInfo('提示','有效合同为0');
                    return false;
                }
                <%--contractObj.getProjectById('${projectInfo.id}', function (data) {--%>
                    <%--if (data.contractList) {--%>
                        <%--if (data.contractList.length >= 1) {--%>
                            <%--//把之前的累加上--%>
                            <%--$.each(data.contractList, function (i, item) {--%>
                                <%--uuids.push(item.key);--%>
                                <%--names.push(item.value);--%>
                            <%--});--%>
                        <%--}--%>
                    <%--}--%>
                    <%--//去重--%>
                    <%--uuids = contractObj.unique(uuids);--%>
                    <%--names = contractObj.unique(names);--%>
                    <%--contractObj.projectUpdate({--%>
                        <%--id: '${projectInfo.id}',--%>
                        <%--contractId: uuids.join(","),--%>
                        <%--contractName: names.join(",")--%>
                    <%--}, function () {--%>
                        <%--$(_this).closest('.input-group').find("label[name='contractName']").html(viewArray.join("/ "));--%>
                        <%--settingContract() ;--%>
                    <%--});--%>
                <%--});--%>

                contractObj.projectUpdate({
                    id: '${projectInfo.id}',
                    contractId: uuids.join(","),
                    contractName: names.join(",")
                }, function () {
                    $(_this).closest('.input-group').find("label[name='contractName']").html(viewArray.join("/ "));
                    settingContract() ;
                });
            }
        });
    };

    /**
     * 文字溢出 情况 超过规定的就执行
     */
    function settingContract() {
        var textMax = 75;
        var projectId = $("#projectId");
        var form = projectId.closest(".form-horizontal") ;
        var contractName = form.find("label[name='contractName']") ;
        var len = 0;
        var attribute = {'overflow':'scroll','-webkit-box-orient':'vertical',display:'-webkit-box'} ;//'text-overflow':'ellipsis'
        contractName.find("a").each(function (i,a) {
            var text = $.trim($(a).text()) ;
            len += text.length ;
            if (len > textMax){
                $(a).hide() ;
            }
        });
        if (len > textMax){
            contractName.html(contractName.html()+"...") ;
//            contractName.css(attribute) ;
        }

    }

    $(function () {
        settingContract() ;
        //显示附件
        FileUtils.getFileShows({
            target: "attachmentProjectInfoId",
            formData: {
                tableName: AssessDBKey.ProjectInfo,
                fieldsName: "attachmentProjectInfoId",
                tableId: '${empty projectInfo.id?0:projectInfo.id}'
            },
            deleteFlag: false
        });
        //查看更多
        $("#btnViewProjectInfoMore").click(function (e) {
            window.open('${pageContext.request.contextPath}/projectInfo/projectInfoDetails?projectId=${projectInfo.id}');
            e.preventDefault();
            e.stopPropagation();
        })
    });

</script>
