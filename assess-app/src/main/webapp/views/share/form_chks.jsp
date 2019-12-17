<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>考核信息</h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <table class="table">
            <thead>
            <tr>
                <th width="3%">序号</th>
                <th width="7%">节点名称</th>
                <th width="60%">考核标准</th>
                <th width="10%">综合得分</th>
                <th width="10%">操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">1</th>
                <td>项目经理</td>
                <td>
                    <table class="table">
                        <tr>
                            <td>
                                每一个产权资产清查信息包括工作内容中的四项，如果有出现需要填写的信息不完整采取扣分方法进行评分，
                                并不是一个产权对应的每一个信息都客观存在；如果为多个产权每增加一个产权增加１０%的得分
                                ，最高增加分不超过本项的标准设定分值。(1.0~10.0)
                            </td>
                            <td width="10%">
                                <label class="form-control">5</label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                每一个产权资产清查信息包括工作内容中的四项，如果有出现需要填写的信息不完整采取扣分方法进行评分，
                                并不是一个产权对应的每一个信息都客观存在；如果为多个产权每增加一个产权增加１０%的得分
                                ，最高增加分不超过本项的标准设定分值。(1.0~10.0)
                            </td>
                            <td>
                                <label class="form-control">5</label>
                            </td>
                        </tr>
                    </table>
                </td>
                <td>
                    <ul class="list-unstyled user_data">
                        <li> 张三（33分）</li>
                    </ul>
                </td>
                <td>
                    <input type="button" class="btn btn-xs btn-primary" value="考核记录">
                </td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>技术总工</td>
                <td>
                    <table class="table">
                        <tr>
                            <td>
                                每一个产权资产清查信息包括工作内容中的四项，如果有出现需要填写的信息不完整采取扣分方法进行评分，
                                并不是一个产权对应的每一个信息都客观存在；如果为多个产权每增加一个产权增加１０%的得分
                                ，最高增加分不超过本项的标准设定分值。(1.0~10.0)
                            </td>
                            <td width="10%">
                                <label class="form-control">5</label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                每一个产权资产清查信息包括工作内容中的四项，如果有出现需要填写的信息不完整采取扣分方法进行评分，
                                并不是一个产权对应的每一个信息都客观存在；如果为多个产权每增加一个产权增加１０%的得分
                                ，最高增加分不超过本项的标准设定分值。(1.0~10.0)
                            </td>
                            <td>
                                <label class="form-control">5</label>
                            </td>
                        </tr>
                    </table>
                </td>
                <td>
                    <ul class="list-unstyled user_data">
                        <li> 张三（33分）</li>
                    </ul>
                </td>
                <td>
                    <input type="button" class="btn btn-xs btn-primary" value="考核记录">
                </td>
            </tr>
            <tr>
                <th scope="row">3</th>
                <td>部门负责人</td>
                <td>
                    <table class="table">
                        <tr>
                            <td>
                                每一个产权资产清查信息包括工作内容中的四项，如果有出现需要填写的信息不完整采取扣分方法进行评分，
                                并不是一个产权对应的每一个信息都客观存在；如果为多个产权每增加一个产权增加１０%的得分
                                ，最高增加分不超过本项的标准设定分值。(1.0~10.0)
                            </td>
                            <td width="10%">
                                <input type="text" placeholder="1.0~10.0" class="form-control" value="">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                每一个产权资产清查信息包括工作内容中的四项，如果有出现需要填写的信息不完整采取扣分方法进行评分，
                                并不是一个产权对应的每一个信息都客观存在；如果为多个产权每增加一个产权增加１０%的得分
                                ，最高增加分不超过本项的标准设定分值。(1.0~10.0)
                            </td>
                            <td width="10%">
                                <input type="text" placeholder="1.0~10.0" class="form-control" value="">
                            </td>
                        </tr>
                    </table>
                </td>
                <td>
                    <ul class="list-unstyled user_data">
                        <li> 张三（33分）</li>
                    </ul>
                </td>
                <td>
                    <input type="button" class="btn btn-xs btn-primary" value="考核记录" data-toggle="modal" href="#divChksRecordModal">
                    <input type="button" class="btn btn-xs btn-primary" value="抽查" data-toggle="modal" href="#divChksRecordModal">
                </td>
            </tr>
            </tbody>
        </table>
        <div class="form-group">
            <label class="col-sm-1 control-label">
                考核说明<span class="symbol required"></span>
            </label>
            <div class="col-sm-11">
                <div class="x-valid">
                           <textarea required placeholder="考核说明" id="chksRemarks" name="chksRemarks"
                                     class="form-control"></textarea>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="form-group">
    <label class="col-sm-1 control-label">
        考核<span class="symbol required"></span>
    </label>
    <div class="col-sm-11">
        <div class="form-group">
            <button type="button" id="addData" class="btn btn-primary"
                    href="#divBox" onclick="historyChksData();"> 历史考核
            </button>
        </div>
        <c:forEach var="item" items="${assessmentItemList}">
            <div class="x-valid">
                <label class="col-sm-12" for="chks_${item.id}">
                        ${item.assessmentDes}(${item.minScore}~${item.maxScore})
                </label>
            </div>
            <div class="col-sm-2">
                <input type="number" required name="chks_${item.id}" id="chks_${item.id}"
                       class="form-control">
            </div>
        </c:forEach>
    </div>
</div>

<div id="divChksRecordModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">考核记录</h3>
            </div>
            <div class="modal-body">
                <input type="button" class="btn btn-primary" value="考核" data-toggle="modal" href="#divChksContentModal">
                <table class="table">
                    <thead>
                    <tr>
                        <th width="10%">被考核人</th>
                        <th width="10%">考核人</th>
                        <th width="60%">考核项</th>
                        <th width="10%">得分</th>
                        <th width="10%">考核时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>张三</td>
                        <td>李四</td>
                        <td>
                            <table class="table">
                                <tr>
                                    <td>
                                        每一个产权资产清查信息包括工作内容中的四项，如果有出现需要填写的信息不完整采取扣分方法进行评分，
                                        并不是一个产权对应的每一个信息都客观存在；如果为多个产权每增加一个产权增加１０%的得分
                                        ，最高增加分不超过本项的标准设定分值。(1.0~10.0)
                                    </td>
                                    <td width="10%">
                                        <label class="form-control">5</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        每一个产权资产清查信息包括工作内容中的四项，如果有出现需要填写的信息不完整采取扣分方法进行评分，
                                        并不是一个产权对应的每一个信息都客观存在；如果为多个产权每增加一个产权增加１０%的得分
                                        ，最高增加分不超过本项的标准设定分值。(1.0~10.0)
                                    </td>
                                    <td>
                                        <label class="form-control">5</label>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            10分
                        </td>
                        <td>
                            2019-1-1
                        </td>
                    </tr>
                    <tr>
                        <td>张三</td>
                        <td>李四</td>
                        <td>
                            <table class="table">
                                <tr>
                                    <td>
                                        每一个产权资产清查信息包括工作内容中的四项，如果有出现需要填写的信息不完整采取扣分方法进行评分，
                                        并不是一个产权对应的每一个信息都客观存在；如果为多个产权每增加一个产权增加１０%的得分
                                        ，最高增加分不超过本项的标准设定分值。(1.0~10.0)
                                    </td>
                                    <td width="10%">
                                        <label class="form-control">5</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        每一个产权资产清查信息包括工作内容中的四项，如果有出现需要填写的信息不完整采取扣分方法进行评分，
                                        并不是一个产权对应的每一个信息都客观存在；如果为多个产权每增加一个产权增加１０%的得分
                                        ，最高增加分不超过本项的标准设定分值。(1.0~10.0)
                                    </td>
                                    <td>
                                        <label class="form-control">5</label>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td>
                            10分
                        </td>
                        <td>
                            2019-1-1
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>

<div id="divChksContentModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">考核</h3>
            </div>
            <div class="modal-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th width="90%">考核项</th>
                        <th width="10%">得分</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            每一个产权资产清查信息包括工作内容中的四项，如果有出现需要填写的信息不完整采取扣分方法进行评分，
                            并不是一个产权对应的每一个信息都客观存在；如果为多个产权每增加一个产权增加１０%的得分
                            ，最高增加分不超过本项的标准设定分值。(1.0~10.0)
                        </td>
                        <td>
                            <input type="text" class="form-control">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            每一个产权资产清查信息包括工作内容中的四项，如果有出现需要填写的信息不完整采取扣分方法进行评分，
                            并不是一个产权对应的每一个信息都客观存在；如果为多个产权每增加一个产权增加１０%的得分
                            ，最高增加分不超过本项的标准设定分值。(1.0~10.0)
                        </td>
                        <td>
                            <input type="text" class="form-control">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" data-dismiss="modal" class="btn btn-primary">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">历史考核</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel-body">
                            <table id="tb_list"
                                   class="table table-striped jambo_table bulk_action table-bordered"></table>
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
    </div>
</div>

<script type="text/javascript">

    function checkChks(data) {
        var json = {};
        <c:forEach var="item" items="${assessmentItemList}">
        var score = Number($("#chks_${item.id}").val());
        var maxScore = Number("${item.maxScore}");
        var minScore = Number("${item.minScore}");
        if (score > maxScore || score < minScore) {
            toastr.warning("请在考核范围内打分");
            return false;
        }
        json["${item.id}"] = $("#chks_${item.id}").val();
        </c:forEach>
        data["chksScore"] = JSON.stringify(json);
        var row = undefined;
        $.each($("#tb_log").bootstrapTable("getData"), function (i, item) {
            //确定申请人
            if (item.bisApply) {
                row = item;
            }
        });
        if (row) {
            data["byExaminePeople"] = row.creator;
        }
    }

    function historyChksData() {
        var cols = [];
        cols.push({field: 'activityName', title: '考核节点'});
        cols.push({field: 'examinePeopleName', title: '考核人'});
        cols.push({field: 'byExaminePeopleName', title: '被考核人'});
        cols.push({
            field: 'examineDate', title: '考核时间', formatter: function (value, row, index) {
                if (value) {
                    return formatDate(value, false);
                }
                return "";
            }
        });
        cols.push({field: 'examineScore', title: '考核分值'});
        cols.push({field: 'validScore', title: '实际分值'});
        $("#tb_list").bootstrapTable('destroy');
        TableInit("tb_list", "${pageContext.request.contextPath}/chksAssessmentProjectPerformance/getHistoryChksData", cols, {
            processInsId: "${processInsId}"
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $("#divBox").modal();
            }
        });
    }
</script>