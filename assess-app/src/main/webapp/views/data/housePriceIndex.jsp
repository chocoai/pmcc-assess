<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <script src="${pageContext.request.contextPath}/excludes/assets/plugins/laydate/laydate.js" type="text/javascript"></script>
</head>

<body class="nav-md footer_fixed">
<%--<%@include file="share/main_head.jsp" %>--%>
<!-- start: MAIN CONTAINER -->
<div class="container body">
    <div class="main_container">
        <%@include file="/views/share/main_navigation.jsp" %>
        <%@include file="/views/share/main_head.jsp" %>
        <div class="right_col" role="main">
            <div class="x_panel">
                <div class="x_title">
                    <h2><i class="fa ${baseViewDto.currentMenu.icon}"></i>
                       ${baseViewDto.currentMenu.name} <%--这是用来显示标题的，固定格式--%>
                    </h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form id="frmQuery" class="form-horizontal">
                        <div class="form-group ">
                            <div>
                                <label class="col-sm-1 control-label">
                                    开始时间
                                </label>

                                <div class="col-sm-2">
                                    <input type="text"  data-date-format="yyyy-mm" data-date-minView="3" data-date-startView="3"
                                           placeholder="开始时间" id="startTime" name="startTime"
                                           class="form-control dbdate">
                            </div>
                            <div>
                                <label class="col-sm-1 control-label">
                                    结束时间
                                </label>
                                <div class="col-sm-2">
                                    <input type="text"  data-date-format="yyyy-mm" data-date-minView="3" data-date-startView="3"
                                           placeholder="结束时间" id="endTime" name="endTime"
                                           class="form-control dbdate">
                                </div>
                            </div>
                            <div class="col-sm-3">
                                <button type="button" class="btn btn-primary" onclick="loadDataDicList()">
                                    查询
                                </button>

                                <button type="button" class="btn btn-success" onclick="addDataDic()"
                                        data-toggle="modal" href="#divBox"> 新增
                                </button>
                            </div>
                        </div>

                    </form>
                    <table class="table table-bordered" id="tb_List">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>
</body>
<div id="divBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">指数</h4>
            </div>
            <form id="frm" class="form-horizontal">
                <input type="hidden" id="id" name="id" value="0">
                <input type="hidden" id="creator" name="creator">
                <input type="hidden" id="gmtCreated" name="gmtCreated">
                <input type="hidden" id="pid" name="pid">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            指数<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" required data-rule-maxlength="50" placeholder="指数"
                                                   id="indexCalendar" name="indexCalendar" class="form-control">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            年份<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-10">
                                            <%--<input class="form-control dbdate" id="yearMonthCalendar" name="yearMonthCalendar">--%>
                                            <input class="form-control dbdate" id="yearMonthCalendar" name="yearMonthCalendar"
                                                   data-date-format="yyyy-mm" data-date-minView="3" data-date-startView="3">
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="saveSubDataDic()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="/views/share/main_footer.jsp" %>
<script type="application/javascript">
    $(function () {
        loadDataDicList();
    })
    //加载  数据列表
    function loadDataDicList() {
        var cols = [];
        cols.push({field: 'yearMonthString', title: '年份'});
        cols.push({field: 'indexCalendar', title: '指数'});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success" href="javascript:editHrProfessional(' + index + ');" >编辑</i></a>';
                str += '<a class="btn btn-xs btn-warning" href="javascript:removeDataBuildingNewRate(' + row.id + ',\'tb_List\')">删除</a>';
                str += '</div>';
                return str;
            }
        });
        $("#tb_List").bootstrapTable('destroy');
        TableInit("tb_List", "${pageContext.request.contextPath}/housePriceIndex/list", cols, {
            startTime: $("#startTime").val(),
            endTime: $("#endTime").val()
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    }

    //删除 数据
    function removeDataBuildingNewRate(id, tbId) {
        Alert("确认要删除么？", 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: "${pageContext.request.contextPath}/housePriceIndex/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadDataDicList();//重载 (刷新)
                        $('#' + tbId).bootstrapTable("refresh");
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    }

    //对新增 数据处理
    function addDataDic() {
        $("#frmSub").clearAll();
        $("#indexCalendar").val("");
        $("#yearMonthCalendar").val("");
    }
    //yyyy-MM-dd被固定了
    laydate.render({
        elem: '#yearMonthCalendar' //指定元素 id
    });
    laydate.render({
        elem: '#startTime' //指定元素 id
    });
    laydate.render({
        elem: '#endTime' //指定元素 id
    });
    //新增 数据
    function saveSubDataDic() {
        var flag = false;
        var data = {};
        data.id = $("#id").val();
        data.yearMonthCalendar = $("#yearMonthCalendar").val();
        data.indexCalendar = $("#indexCalendar").val();
        //非空校验
        if (isNot(data.yearMonthCalendar) && isNot(data.indexCalendar)){
            flag = true;
        }else {
            alert("存在没有填写的内容");
        }
        if (flag){
            console.log(data);
            $.ajax({
                url: "${pageContext.request.contextPath}/housePriceIndex/save",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        loadDataDicList();
                        $('#divBox').modal('hide');
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
//            document.getElementById("divBox").style.display = "none";
//            window.location.reload();//自动刷新
        }
    }


    function editHrProfessional(index) {
        var yearMonthCalendar = document.getElementById("yearMonthCalendar");
        console.log(yearMonthCalendar);
        var yearMonthCalendarName = $("input[name='yearMonthCalendar']");
        var row = $("#tb_List").bootstrapTable('getData')[index];
        $("#frm").clearAll();
        console.info(yearMonthCalendar.value);
        $("#frm").initForm(row);
        $("#yearMonthCalendar").val(row.yearMonthSource);
        $('#divBox').modal();
    }

    function isNot(val) {
        if (val!=null){
            if (val!=''){
                return true;
            }
        }
        return false;
    }


</script>


</html>
