
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input type="hidden" name="id">
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">省
            <span class="symbol required"></span></label>
        <div class="col-sm-3">
            <select name="province"
                    class="form-control search-select select2 province">
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
        <label class="col-sm-1 control-label">市<span
                class="symbol required"></span></label>
        <div class="col-sm-3">
            <select name="city" class="form-control search-select select2 city">
            </select>
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">县</label>
        <div class="col-sm-3">
            <select name="district" class="form-control search-select select2 district">
            </select>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">楼盘名称<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" data-rule-maxlength="100" placeholder="楼盘名称" required="required"
                   name="name" class="form-control">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">楼盘方位</label>
        <div class="col-sm-3">
            <input type="text" placeholder="楼盘方位"
                   name="position" class="form-control">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">基础版块<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <select name="blockId" class="form-control search-select select2 blockId"
                    required="required">
            </select>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">编号</label>
        <div class="col-sm-3">
            <input type="text" data-rule-maxlength="100" data-rule-number='true'
                   placeholder="编号(请输入数字)" name="number" class="form-control">
        </div>
    </div>
    <%--<div class="x-valid">--%>
        <%--<label class="col-sm-1 control-label">土地级别</label>--%>
        <%--<div class="col-sm-3">--%>
            <%--<select class="form-control search-select select2 landLevel" name="landLevel">--%>
            <%--</select>--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="x-valid">
        <label class="col-sm-1 control-label">街道</label>
        <div class="col-sm-3">
            <input type="text" placeholder="街道"
                   name="street" class="form-control">
        </div>
    </div>

</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">占地面积</label>
        <div class="col-sm-3">
            <input type="text" data-rule-maxlength="100" data-rule-number='true'
                   placeholder="占地面积(请输入数字)" name="coverAnArea" class="form-control">
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">容积率</label>
        <div class="col-sm-3">
            <input type="text" data-rule-maxlength="100" data-rule-number='true'
                   placeholder="容积率(请输入数字)" name="volumetricRate" class="form-control">
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">绿化率</label>
        <div class="col-sm-3">
            <input type="text" data-rule-maxlength="100" data-rule-number='true'
                   placeholder="绿化率(请输入数字)" name="greeningRate" class="form-control">
        </div>
    </div>
</div>


<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">楼盘概况</label>
        <div class="col-sm-11">
                        <textarea class="form-control" name="description"
                                  placeholder="楼盘概况"></textarea>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">楼栋数</label>
        <div class="col-sm-3">
            <input type="text" data-rule-maxlength="100" data-rule-number='true'
                   placeholder="楼栋数(请输入数字)" name="buildingNumber"
                   class="form-control">
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">开发商</label>
        <div class="col-sm-3">
            <select class="form-control search-select select2 developerId" name="developerId">
            </select>
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">建筑面积</label>
        <div class="col-sm-3">
            <input type="text" data-rule-maxlength="100" data-rule-number='true'
                   placeholder="建筑面积(请输入数字)" name="floorArea" class="form-control">
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">均价</label>
        <div class="col-sm-3">
            <input type="text" data-rule-maxlength="100" data-rule-number='true'
                   placeholder="均价(请输入数字)" name="averagePrice" class="form-control">
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">价格区间</label>
        <div class="col-sm-3">
            <input type="text" placeholder="价格区间"
                   name="priceRange" class="form-control">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">附号</label>
        <div class="col-sm-3">
            <input type="text" data-rule-maxlength="100" data-rule-number='true'
                   placeholder="附号(请输入数字)"
                   name="attachNumber" class="form-control">
        </div>
    </div>
</div>

