<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-5-14
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/views/project/tool/selectSchemeJudgeTool.jsp" %>

<script type="text/html" id="generateReportGroupToolView">

    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <input type="hidden" data-name="id" value="{id}">
                <label class="col-sm-1 control-label">分组名称</label>

                <div class="col-sm-2" >
                    <label class="form-control input-full">{name}</label>
                </div>

                <label class="col-sm-1 control-label">估价对象名称</label>

                <div class="col-sm-2" >
                    <label class="form-control input-full">{fullName}</label>
                </div>

                <label class="col-sm-1 control-label">报告类型</label>

                <div class="col-sm-2" >
                    <label class="form-control input-full">{reportTypeName}</label>
                </div>
                <label class="col-sm-1 control-label">操作</label>

                <div class="col-sm-2" >
                    <button class="btn-primary btn btn-sm" type="button" onclick="schemeJudgeObj.init({callback:reportGroupObj.selectJudgeObj,this_:this ,areaGroupId: '{reportInfoId}'});">
                        选择估价对象
                    </button>
                    <span class="input-group-btn">
                <input class="btn btn-warning btn-sm" type="button" value="X"
                       onclick="reportGroupObj.cleanHTMLData(this ,'{id}')"></span>
                </div>
            </div>
        </div>
    </div>
</script>

<div>
    <input type="hidden" id="reportGroupObjJson" value='${el:toJsonString(generationVos)}'>
</div>


<script type="text/javascript">

    var reportGroupObj = {};

    reportGroupObj.run = function (data, url, type, callback, funParams, errorCallback) {
        Loading.progressShow();
        $.ajax({
            type: type,
            url: '${pageContext.request.contextPath}' + url,
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    if (funParams) {
                        if (funParams == 'save') {
                            notifySuccess("成功", "保存数据成功!");
                        }
                        if (funParams == 'add') {
                            notifySuccess("成功", "添加数据成功!");
                        }
                        if (funParams == 'update') {
                            notifySuccess("成功", "修改数据成功!");
                        }
                        if (funParams == 'query') {
                            notifySuccess("成功", "查询数据成功!");
                        }
                        if (funParams == 'delete') {
                            notifySuccess("成功", "删除数据成功!");
                        }
                    }
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    if (errorCallback) {
                        errorCallback(result.errmsg);
                    } else {
                        if (result.errmsg) {
                            AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                        } else {
                            AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                        }
                    }
                }
            },
            error: function (result) {
                Loading.progressHide();
                if (errorCallback) {
                    errorCallback(result.errmsg);
                } else {
                    if (result.errmsg) {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                    }
                }
            }
        });
    };
    reportGroupObj.ajaxServerFun = function (data, url, type, callback, funParams, errorCallback) {
        var deleteParams = false;
        if (funParams) {
            if (funParams == 'delete') {
                deleteParams = true;
            }
        }
        if (deleteParams) {
            AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
                reportGroupObj.run(data, url, type, callback, funParams, errorCallback);
            });
        } else {
            reportGroupObj.run(data, url, type, callback, funParams, errorCallback);
        }
    };

    reportGroupObj.replaceHtml = function (data) {
        var html = $("#generateReportGroupToolView").html();
        html = html.replace(/{id}/g, data.id);
        html = html.replace(/{reportInfoId}/g, data.reportInfoId);
        if (data.fullName) {
            html = html.replace(/{fullName}/g, data.fullName);
        } else {
            html = html.replace(/{fullName}/g, '');
        }
        if (data.name) {
            html = html.replace(/{name}/g, data.name);
        } else {
            html = html.replace(/{name}/g, '');
        }
        if (data.reportTypeName) {
            html = html.replace(/{reportTypeName}/g, data.reportTypeName);
        } else {
            html = html.replace(/{reportTypeName}/g, '');
        }
        return html;
    };

    reportGroupObj.getGenerateReportGroupList = function(reportInfoId ,callback){
        reportGroupObj.ajaxServerFun({reportInfoId:reportInfoId ,projectId:'${projectPlan.projectId}'} ,"/generateReportGroup/getGenerateReportGroupList" ,"get" ,callback,null,null) ;
    } ;

    reportGroupObj.saveAndUpdateGenerateReportGroup = function(data ,callback){
        data.projectId ='${projectPlan.projectId}' ;
        reportGroupObj.ajaxServerFun({formData:JSON.stringify(data) ,updateNull:true} ,"/generateReportGroup/saveAndUpdateGenerateReportGroup" ,"post" ,callback,null,null) ;
    } ;

    reportGroupObj.deleteGenerateReportGroup = function(id ,callback){
        reportGroupObj.ajaxServerFun({id:id} ,"/generateReportGroup/deleteGenerateReportGroup" ,"post" ,callback,'delete',null) ;
    } ;

    reportGroupObj.cleanHTMLData = function(_this,id){

    } ;

    reportGroupObj.initData = function (arr) {
        if (! arr){
            return false ;
        }
        if (! $.isArray(arr)){
            return false ;
        }
        if (arr.length == 0){
            return false ;
        }

        $.each(arr , function (i,item) {
            reportGroupObj.init(item) ;
        }) ;
    };

    reportGroupObj.selectJudgeObj = function(_this, id){
        console.log(id) ;
        console.log(_this) ;
    } ;

    reportGroupObj.init = function (generationVo) {
        var reportInfoId = generationVo.areaGroupId ;
        // console.log(generationVo) ;

        (function (target) {
            reportGroupObj.getGenerateReportGroupList(reportInfoId ,function (data) {
                // console.log(data) ;
                if (data.length >= 1){
                    $.each(data, function (i, item) {
                        target.append(reportGroupObj.replaceHtml(item));
                    });
                    setTimeout(function () {

                    }, 100);
                }else {
                   var item = {reportInfoId:reportInfoId,name:"组1"} ;
                    reportGroupObj.saveAndUpdateGenerateReportGroup(item ,function () {
                        reportGroupObj.init(generationVo) ;
                    }) ;
                }
            }) ;
        }($("#generateReportGroupTool"+reportInfoId))) ;
    };


    $(document).ready(function () {

        var json = JSON.parse($("#reportGroupObjJson").val());

        reportGroupObj.initData(json) ;


    });


</script>