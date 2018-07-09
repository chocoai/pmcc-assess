<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-7-5
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_building" class="form-horizontal">
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">省<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select name="province" class="form-control search-select select2"
                        required="required">
                    <option value="">-请选择-</option>
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
                <select name="city" class="form-control search-select select2"
                        required="required">

                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">县</label>
            <div class="col-sm-3">
                <select name="district" class="form-control search-select select2">

                </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">版块名称<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="版块名称" required
                       name="name" value=""
                       class="form-control">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">方位<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="方位" required
                       name="name" value=""
                       class="form-control">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">区域性质<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="区域性质" required
                       name="name" value=""
                       class="form-control">
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-4 col-sm-offset-5">
            <button id="saveFrm" type="button" class="btn btn-primary" onclick="saveData()">
                保存版块
            </button>
        </div>
    </div>

    <div class="ln_solid"></div>
</form>

<script type="text/javascript">
    (function ($) {
        //避免方法重复，定义全局变量
        var building = {
            //初始化
            init: function (id) {

            },

            //保存
            save: function () {

            }
        };
        window.Building = building;
    })(jQuery)
</script>
