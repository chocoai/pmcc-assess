
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/html" id="constructionInstallationEngineeringFeeInfoModelHtml">
    <div class="panel panel-info">
        <i class="fa fa-close close" title="删除" onclick="{method}.deleteConstructionInstallation('{uuid}')"
           style="margin-right: 10px;font-size: 15px;cursor: pointer;"></i>
        <i class="fa fa-search" onclick="{method}.detailsConstructionInstallation('{uuid}')" title="查看"
           style="margin-right: 10px;font-size: 15px;cursor: pointer;"></i>
        <a  style="cursor: pointer;" onclick="{method}.detailsConstructionInstallation('{uuid}')">第{index}个</a>
    </div>
</script>

<script type="text/html" id="architecturalA" data-title="简单树">
    <table class="table tree" id="architecturalAHandle">
        <thead>
        <tr>
            <th>工程名称</th>
            <th>估价时点完工程度(元/㎡)</th>
            <th>描述</th>
        </tr>
        </thead>

        <tbody>

        <tr class="treegrid-1" data-key="architecturalEngineering" data-role="parent">
            <td>建筑工程</td>
            <td></td>
            <td></td>
        </tr>
        <tr class="treegrid-1-1 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
            <td> 地下基础</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-1-2 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
            <td> 地下室</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-1-3 treegrid-parent-1" data-key="architecturalEngineering" data-role="child">
            <td> 地上主体</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>

        <tr class="treegrid-2" data-key="installationWorks" data-role="parent">
            <td>安装工程</td>
            <td></td>
            <td></td>
        </tr>
        <tr class="treegrid-2-1 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 电气工程</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-2 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 给排水工程</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-3 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 燃气工程</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-4 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 消防工程含消防报警</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-5 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 通风空调工程</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-6 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 智能化系统工程</td>
            <td><input type="text" placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-7 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 电梯工程</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-2-8 treegrid-parent-2" data-key="installationWorks" data-role="child">
            <td> 其它工程</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>

        <tr class="treegrid-3" data-key="decorationEngineering" data-role="parent">
            <td>装饰工程</td>
            <td></td>
            <td></td>
        </tr>
        <tr class="treegrid-3-1 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 楼地面工程</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-2 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 外墙墙柱面工程</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-3 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 内墙墙柱面工程</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-4 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 天棚工程</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-5 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 门窗工程</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-6 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 外墙（油漆、涂料、裱糊）工程</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-7 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 内墙（油漆、涂料、裱糊）工程</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-3-8 treegrid-parent-3" data-key="decorationEngineering" data-role="child">
            <td> 其它工程</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>

        <tr class="treegrid-4" data-key="ancillaryWorks" data-role="parent">
            <td>附属工程</td>
            <td></td>
            <td></td>
        </tr>
        <tr class="treegrid-4-1 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 道路</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-4-2 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 围墙</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-4-3 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 大门</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-4-4 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 绿化</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-4-5 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 园林</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        <tr class="treegrid-4-6 treegrid-parent-4" data-key="ancillaryWorks" data-role="child">
            <td> 景观</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>

        <tr class="treegrid-5" data-key="secondInstallationProject" data-role="parent">
            <td>二装工程</td>
            <td></td>
            <td></td>
        </tr>
        <tr class="treegrid-5-1 treegrid-parent-5" data-key="secondInstallationProject" data-role="child">
            <td> 二装工程</td>
            <td><input type="text"  placeholder="请输入(数字)"
                       name="valuationDateDegreeCompletion" class="x-percent" data-rule-number="true" style="width: 100px;"></td>
            <td><input type="text" name="remark"  style="width: 100px;"></td>
        </tr>
        </tbody>
    </table>
</script>

<!--  建筑安装工程费 -->
<div id="boxLandEngineering" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建筑安装工程费</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <div class="panel-body">

                        </div>
                    </div>
                </div>
            </div>
            <input type="hidden" name="constructionInstallationEngineeringFeeId">
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary"
                        onclick="architecturalA.save('{method}')">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<script>

    var architecturalA = {} ;

    architecturalA.getHtml = function () {
        return $("#architecturalA").html() ;
    };

    architecturalA.save = function (method) {
        if (method){
            eval(method) ;
        }
    };

    architecturalA.getFomData = function (table) {
        var tbody = table.find('tbody');
        var data = [];
        tbody.find("tr").each(function (i, tr) {
            var dataKey = $(tr).attr('data-key');
            var role = $(tr).attr('data-role');
            var name = $(tr).find("td").first().text();
            var remark = $(tr).find("td").last().find("input").val();
            var valuationDateDegreeCompletion = $(tr).find("input[name='valuationDateDegreeCompletion']").first().val();
            data.push({dataKey: dataKey, role: role, name: name, value: '',valuationDateDegreeCompletion:valuationDateDegreeCompletion,remark:remark});
        });
        return data;
    };

    architecturalA.saveMdArchitecturalObj = function (data,obj,callback) {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/mdArchitecturalObj/saveMdArchitecturalObj",
            data: {jsonString:JSON.stringify(data),forData:JSON.stringify(obj)},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    Alert("保存失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    architecturalA.getMdArchitecturalObjList = function (databaseName,pid,callback) {
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/mdArchitecturalObj/getMdArchitecturalObjList",
            data: {databaseName: databaseName, pid: pid},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    Alert("失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    architecturalA.getMdArchitecturalObjById = function (id , callback) {
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/mdArchitecturalObj/getMdArchitecturalObjById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    Alert("失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    architecturalA.deleteMdArchitecturalObjById = function (id , callback) {
        $.ajax({
            type: "post",
            url: "${pageContext.request.contextPath}/mdArchitecturalObj/deleteMdArchitecturalObjById",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    Alert("失败:" + result.errmsg);
                }
            },
            error: function (e) {
                Alert("调用服务端方法失败，失败原因:" + e);
            }
        });
    };

    architecturalA.initData = function (table, data) {
        var tbody = table.find('tbody');
        tbody.find("tr").each(function (i, tr) {
            var dataKey = $(tr).attr('data-key');
            var role = $(tr).attr('data-role');
            var name = $(tr).find("td").first().text();
            $.each(data, function (i, item) {
                if (item.role == role) {
                    if (dataKey == item.dataKey) {
                        if (name == item.name) {
                            if ($(tr).find("input[name='valuationDateDegreeCompletion']").first().size() != 0) {
                                $(tr).find("input[name='valuationDateDegreeCompletion']").first().val(item.valuationDateDegreeCompletion);
                            }
                            if ($(tr).find("input[name='remark']").first().size() != 0) {
                                $(tr).find("input[name='remark']").first().val(item.remark);
                            }
                        }
                    }
                }
            });
        });
    };

    architecturalA.treeGirdParse = function (target) {
        target.find("table").treegrid();
    };

</script>
