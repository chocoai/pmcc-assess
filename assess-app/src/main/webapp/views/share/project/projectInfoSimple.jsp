<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en" class="no-js">

<div class="col-md-12" id="projectInfoSimpleId">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    <input type="hidden" name="projectInfoVoJson" id="projectInfoVoJson"
                           value='${projectInfoVoJson}'>
                    项目信息
                    <small><input type="button" id="btnViewProjectInfoMore" class="btn btn-success btn-sm" value="查看更多..."/>
                    </small>
                    <small>${projectInfo.projectCategoryName}</small>
                </div>
                <div class="card-tools">
                    <button class="btn btn-icon btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <div class="form-horizontal">
                <input type="hidden" id="projectId" name="id" value="${projectInfo.id}">
                <div class="row form-group">
                    <div class="col-md-8">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">项目名称</label>
                            <div class="col-sm-10">
                                <label class="form-control input-full">${projectInfo.projectName}</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">紧急程度</label>
                            <div class="col-sm-10">
                                <label class="form-control input-full">${projectInfo.urgencyName}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-4">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">委托目的</label>
                            <div class="col-sm-10">
                                <label class="form-control input-full">${projectInfo.entrustPurposeName}</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">评估基准日</label>
                            <div class="col-sm-10">
                                <label class="form-control input-full"><fmt:formatDate value='${projectInfo.valuationDate}'
                                                                                       pattern='yyyy-MM-dd'/></label>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">执业部门</label>
                            <div class="col-sm-10">
                                <label class="form-control input-full">${projectInfo.departmentName}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-4">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">评估范围</label>
                            <div class="col-sm-10">
                                <label class="form-control input-full">${projectInfo.propertyScopeName}</label></div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">评估包括</label>
                            <div class="col-sm-10">
                                <label class="form-control input-full">${projectInfo.scopeInclude}</label></div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">评估不包括</label>
                            <div class="col-sm-10">
                                <label class="form-control input-full">${projectInfo.scopeNotInclude}</label></div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-4">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">项目经理</label>
                            <div class="col-sm-10">
                                <label class="form-control input-full">${projectInfo.projectMemberVo.userAccountManagerName}</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">项目成员</label>
                            <div class="col-sm-10">
                                <label class="form-control input-full">${projectInfo.projectMemberVo.userAccountMemberName}</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">贷款类型</label>
                            <div class="col-sm-10">
                                <label class="form-control input-full">${projectInfo.loanTypeName}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-4">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">项目合同</label>

                            <c:if test="${!empty projectInfo.contractId}">
                                <c:forEach var="item" items="${projectInfo.contractList}">
                                    <div class="col-sm-10">
                                        <label class="form-control input-full">
                                            <a href="${sysUrl}/pmcc-contract/contractCurrency/details/${item.key}"
                                               target="_blank">${item.value}</a>
                                        </label>
                                    </div>
                                </c:forEach>
                            </c:if>
                            <c:if test="${empty projectInfo.contractId}">
                                <div class="col-sm-10">
                                    <div class="input-group">
                                        <input type="hidden" name="contractId" value="${projectInfo.contractId}">
                                        <input type="text" class="form-control" readonly="readonly" name="contractName"
                                               onclick="selectContract(this);"
                                               value="${projectInfo.contractName}">
                                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="selectContract(this);">
                        <i class="fa fa-search"></i>
                        </button>
                        <button type="button" class="btn btn-default docs-tooltip"
                                onclick="$(this).closest('.input-group').find('input').val('');"
                                data-toggle="tooltip" data-original-title="清除">
                        <i class="fa fa-trash-o"></i>
                        </button>
                        </span>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">合同金额</label>
                            <div class="col-sm-10">
                                <label class="form-control input-full">${projectInfo.contractPrice}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-4">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">业务来源</label>
                            <div class="col-sm-10">
                                <label class="form-control input-full">${projectInfo.serviceComeFromName}</label>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">业务来源说明</label>
                            <div class="col-sm-10">
                                <label class="form-control input-full">${projectInfo.serviceComeFromExplain}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline x-valid">
                            <label class="col-sm-1 col-form-label">项目说明</label>
                            <div class="col-md-11">
                                <label class="form-control input-full">${projectInfo.remarks}</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-4">
                        <div class="form-inline x-valid">
                            <label class="col-sm-2 col-form-label">附件</label>
                            <div class="col-md-10">
                                <div id="_attachmentProjectInfoId"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/html" id="contractInfoModelHtml">
    <div class="panel panel-info">
        <i class="fa fa-close close" title="删除" onclick="contractObj.delete(this,'{uuid}')"
           style="margin-right: 10px;font-size: 15px;cursor: pointer;"></i>
        <i class="fa fa-search" onclick="contractObj.details('{uuid}')" title="查看"
           style="margin-right: 10px;font-size: 15px;cursor: pointer;"></i>
        <a href="#" style="cursor: pointer;" onclick="contractObj.details('{uuid}')">{name}</a>
    </div>
</script>
<script src="/pmcc-contract/js/cms_contract_utils.js?v=${assessVersion}"></script>
<script type="text/javascript">

    var contractObj = {};
    contractObj.projectInfoSimpleId = $("#projectInfoSimpleId");
    contractObj.target = $("#contractInfoTarget");
    contractObj.modelTarget = $("#contractInfoModelHtml");

    contractObj.contractId = '${projectInfo.contractId}';

    /*
     合同详情
     */
    contractObj.details = function (uuid) {
        window.open('${sysUrl}/pmcc-contract/contractCurrency/details/' + uuid);
    };

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

    /**
     * 合同删除
     * @param _this
     * @param uuid
     */
    contractObj.delete = function (_this, uuid) {
        contractObj.getProjectById('${projectInfo.id}', function (data) {
            if (!data) {
                return false;
            }
            if (!data.contractList) {
                return false;
            }
            if (data.contractList.length < 1) {
                return false;
            }
            var contractId = [];
            var contractName = [];
            $.each(data.contractList, function (i, item) {
                if (item.key != uuid) {
                    contractId.push(item.key);
                    contractName.push(item.value);
                }
            });
            contractObj.projectUpdate({
                id: '${projectInfo.id}',
                contractId: contractId.join(","),
                contractName: contractName.join(",")
            }, function () {
                contractObj.loadHtml();
            });
        });
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
                Alert("调用服务端方法失败，失败原因:" + result);
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
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };

    /**
     * 合同加载
     */
    contractObj.loadHtml = function () {
        contractObj.getProjectById('${projectInfo.id}', function (data) {
            if (!data) {
                return false;
            }
            contractObj.target.empty();
            var html = "";
            if (data.contractList.length >= 1) {
                $.each(data.contractList, function (i, item) {
                    var resetHtml = contractObj.modelTarget.html();
                    resetHtml = resetHtml.replace(/{uuid}/g, item.key);
                    resetHtml = resetHtml.replace(/{name}/g, item.value);
                    html += resetHtml;
                });
            }
            contractObj.target.append(html);
            contractObj.projectInfoSimpleId.find("input[name='contractName']").val(data.contractName);
        });
    };

    /**
     * 合同选择
     */
    contractObj.selectContract = function () {
        cmsContract.select({
            multi: true,//是否允许多选
            appkey: "pmcc-assess",
            onSelected: function (data) {
                var uuids = [];
                var names = [];
                data.forEach(function (node, i) {
                    if (node.uuid) {
                        uuids.push(node.uuid);
                    }
                    if (node.name) {
                        names.push(node.name);
                    }
                });
                if (uuids.length == 0) {
                    Alert('有效合同为0');
                    return false;
                }
                contractObj.getProjectById('${projectInfo.id}', function (data) {
                    if (data.contractList) {
                        if (data.contractList.length >= 1) {
                            //把之前的累加上
                            $.each(data.contractList, function (i, item) {
                                uuids.push(item.key);
                                names.push(item.value);
                            });
                        }
                    }
                    //去重
                    uuids = contractObj.unique(uuids);
                    names = contractObj.unique(names);
                    contractObj.projectUpdate({
                        id: '${projectInfo.id}',
                        contractId: uuids.join(","),
                        contractName: names.join(",")
                    }, function () {
                        contractObj.loadHtml();
                    });
                });
            }
        });
    };
    $(function () {
        contractObj.loadHtml();
        //显示附件
        FileUtils.getFileShows({
            target: "attachmentProjectInfoId",
            formData: {
                tableName: AssessDBKey.ProjectInfo,
                fieldsName: "attachmentProjectInfoId",
                tableId: '${empty projectInfo.id?0:projectInfo.id}'
            },
            deleteFlag: false
        })
        //查看更多
        $("#btnViewProjectInfoMore").click(function (e) {
            window.open('${pageContext.request.contextPath}/projectInfo/projectInfoDetails?projectId=${projectInfo.id}');
            e.preventDefault();
            e.stopPropagation();
        })
    });

</script>
