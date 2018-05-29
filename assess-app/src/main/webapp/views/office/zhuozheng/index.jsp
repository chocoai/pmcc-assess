<%@ page language="java"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.pageoffice.cn" prefix="po" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <meta charset="utf-8">
    <title>卓正首页</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <i class="fa fa-external-link-square"></i>
        卓正注册信息
        <div class="panel-tools">
            <a class="btn btn-xs btn-link panel-collapse collapses" href="#">
            </a>
            <a class="btn btn-xs btn-link panel-config" href="#panel-config" data-toggle="modal">
                <i class="fa fa-wrench"></i>
            </a>
            <a class="btn btn-xs btn-link panel-refresh" href="#">
                <i class="fa fa-refresh"></i>
            </a>
            <a class="btn btn-xs btn-link panel-expand" href="#">
                <i class="fa fa-resize-full"></i>
            </a>
            <a class="btn btn-xs btn-link panel-close" href="#">
                <i class="fa fa-times"></i>
            </a>
        </div>
    </div>
    <div class="panel-body">
        <table class="table table-condensed table-hover table-bordered" id="sample-table-3">
            <thead>
            <tr>
                <th class="center hidden-xs">
                    单位名称
                </th>
                <th>联系人</th>
                <th class="hidden-xs">联系电话</th>
                <th><i class="fa fa-time"></i> 序列号</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="center hidden-xs">
                   四川协合魔方
                </td>
                <td>
                    王先生
                   </td>
                <td class="hidden-xs">10000</td>
                <td>V925K-G5RS-4AC8-H3VHC</td>
                <td class="hidden-xs"></td>
            </tr>
            </tbody>
        </table>

    </div>
</div>
</body>
</html>

