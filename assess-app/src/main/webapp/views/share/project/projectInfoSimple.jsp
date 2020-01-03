<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en" class="no-js">
<div class="x_panel" id="projectInfoSimpleId">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>
            项目信息
            <small>
                <input type="button" id="btnViewProjectInfoMore" class="btn btn-success btn-xs" value="查看更多..."/>
            </small>
            <small>${projectInfo.projectCategoryName}</small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content collapse">
        <div class="form-horizontal">
            <input type="hidden" id="projectId" name="id" value="${projectInfo.id}">
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目名称</label>
                    <div class=" col-xs-7  col-sm-7  col-md-7  col-lg-7 ">
                        <label class="form-control">${projectInfo.projectName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">紧急程度</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.urgencyName}</label>
                    </div>
                </div>

            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">委托目的</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.entrustPurposeName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">委托目的类别</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.entrustAimTypeName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">委托目的描述</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.remarkEntrustPurpose}</label></div>
                </div>
            <%--</div>--%>
            <%--<div class="form-group">--%>
                <%--<div class="x-valid">--%>
                    <%--<label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">价值类型</label>--%>
                    <%--<div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">--%>
                        <%--<label class="form-control">${projectInfo.valueTypeName}</label>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">评估基准日</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control"><fmt:formatDate value='${projectInfo.valuationDate}'
                                                                    pattern='yyyy-MM-dd'/></label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">执业部门</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.departmentName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">评估范围</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.propertyScopeName}</label></div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">评估包括</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.scopeInclude}</label></div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">评估不包括</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.scopeNotInclude}</label></div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目经理</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.projectMemberVo.userAccountManagerName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目成员</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.projectMemberVo.userAccountMemberName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">贷款类型</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.loanTypeName}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">业务来源</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.serviceComeFromName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">业务来源说明</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <label class="form-control">${projectInfo.serviceComeFromExplain}</label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目说明</label>
                    <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                        <label class="form-control">${projectInfo.remarks}</label></div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">合同添加</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <div class="input-group">
                            <input type="text" class="form-control" readonly="readonly" name="contractName"
                                   onclick="contractObj.selectContract(this)"
                                   value="${projectInfo.contractName}">
                            <span class="input-group-btn">
                                <button type="button" class="btn btn-default"
                                        onclick="contractObj.selectContract(this)"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">项目合同</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 " id="contractInfoTarget">

                    </div>
                </div>
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">合同金额</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <input type="text" class="form-control" readonly="readonly" name="contractPrice"
                               value="${projectInfo.contractPrice}">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附件</label>
                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                        <div id="_attachmentProjectInfoId"></div>
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
