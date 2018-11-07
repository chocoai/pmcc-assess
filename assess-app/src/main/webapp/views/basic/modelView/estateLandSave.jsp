
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input type="hidden" name="id">
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">土地名称<span class="symbol required"></span></label>
        <div class="col-sm-11">
            <input type="text" class="form-control" required="required" name="name"
                   placeholder="名称">
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">土地用途类型<span
                class="symbol required"></span></label>
        <div class="col-sm-3">
            <select class="form-control search-select select2 landUseType" name="landUseType"
                    required="required">
            </select>
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">土地用途类别<span
                class="symbol required"></span></label>
        <div class="col-sm-3">
            <select class="form-control search-select select2 landUseCategory"
                    name="landUseCategory" required="required">
                <option>请先选择土地用途类型</option>
            </select>
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">土地级别<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <select class="form-control search-select select2 landLevel" name="landLevel"
                    required="required">
            </select>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">东至<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="东至" required="required"
                   name="eastTo">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">南至<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="南至" required="required"
                   name="southTo">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">西至<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="西至" required="required"
                   name="westTo">
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">北至<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="北至" required="required"
                   name="northTo">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">土地形状状况<span
                class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="土地形状状况" required="required"
                   name="shapeState">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">土地平整度<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="土地平整度" required="required"
                   name="planeness">
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">土地开发程度<span
                class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="土地开发程度" required="required"
                   name="developmentDegree">
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">开发限制条件<span
                class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="开发限制条件" required="required"
                   name="restrictiveCondition">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">土壤<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="土壤" required="required"
                   name="soil">
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">地形地势<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="地形地势"
                   required="required" name="topographicTerrain">
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">土地面积<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" class="form-control" data-rule-number='true'
                   placeholder="土地面积(请输入数字)" required="required" name="landArea">
        </div>
    </div>
</div>