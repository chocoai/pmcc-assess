<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/12/1
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="x_panel">
    <div class="x_title">
        <i class="fa fa-external-link-square"></i> 填表人信息
    </div>
    <div class="x_content">
        <div class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        姓名
                    </label>
                    <div class="col-sm-2">
                        <label class="form-control">${currUserInfo.userNickname}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        部门
                    </label>
                    <div class="col-sm-2">
                        <label class="form-control">${currUserInfo.departmentIdName}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        电话
                    </label>
                    <div class="col-sm-2">
                        <label class="form-control">${currUserInfo.mobile}</label>
                    </div>
                </div>
                <div class="x-valid">
                    <label class="col-sm-1 control-label">
                        邮箱
                    </label>
                    <div class="col-sm-2">
                        <label class="form-control">${currUserInfo.companyEmail}</label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>