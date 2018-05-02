<%--
  Created by IntelliJ IDEA.
  User: Calvin
  Date: 2017/7/26
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-title" style="margin: 0px">
    <div class="title_left">
        <h3>
            <i class="fa ${boxprocessIcon}" style="margin-right: 20px;"></i>
            ${boxCnName}
            <small>
                <label>${boxdescription}</label>
                <label class="label label-success"><i class="fa fa-flag"
                                                      style="margin-right: 8px"></i>${currentStepName}</label>
                <label class="label label-primary"><i class="fa fa-user"
                                                      style="margin-right: 8px"></i>${currUserName}</label>
            </small>
        </h3>
    </div>
</div>
<div class="clearfix"></div>
