<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-7-5
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form id="frm_block" class="form-horizontal">

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">省<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select id="province" name="province" class="form-control search-select select2"
                        required="required">
                    <option value="" name="province">-请选择-</option>
                    <c:forEach items="${ProvinceList}" var="item">
                        <c:choose>
                            <c:when test="${item.areaId == projectInfo.province}">
                                <option value="${item.areaId}"
                                        selected="selected">${item.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${item.areaId}">${item.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">市<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select id="city" name="city" class="form-control search-select select2"
                        required="required">

                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">县</label>
            <div class="col-sm-3">
                <select id="district" name="district" class="form-control search-select select2">

                </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">版块名称</label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="50" placeholder="版块名称" required
                       name="ledLuminousPeople" value=""
                       class="form-control">
            </div>
        </div>
    </div>
</form>

<script type="text/javascript">
    $(function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#province"),
            cityTarget: $("#city"),
            districtTarget: $("#district")
        })
    })

    //避免方法重复，定义全局变量
    var block={
        //初始化
        init:function (id) {

        },

        //保存 将需要保存的数据统一传递到后台
        save:function () {

        }
    };
</script>
