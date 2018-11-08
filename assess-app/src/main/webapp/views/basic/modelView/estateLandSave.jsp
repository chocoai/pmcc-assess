
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input type="hidden" name="id">
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">土地名称</label>
        <div class="col-sm-11">
            <input type="text" class="form-control" name="name"
                   placeholder="名称">
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">土地用途类型</label>
        <div class="col-sm-3">
            <select class="form-control search-select select2 landUseType" name="landUseType">
            </select>
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">土地用途类别</label>
        <div class="col-sm-3">
            <select class="form-control search-select select2 landUseCategory"
                    name="landUseCategory">
                <option>请先选择土地用途类型</option>
            </select>
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">土地级别</label>
        <div class="col-sm-3">
            <select class="form-control search-select select2 landLevel" name="landLevel">
            </select>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">东至</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="东至"
                   name="eastTo">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">南至</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="南至"
                   name="southTo">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">西至</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="西至"
                   name="westTo">
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">北至</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="北至"
                   name="northTo">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">土地形状状况</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="土地形状状况"
                   name="shapeState">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">土地平整度</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="土地平整度"
                   name="planeness">
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">土地开发程度</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="土地开发程度"
                   name="developmentDegree">
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">开发限制条件</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="开发限制条件"
                   name="restrictiveCondition">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">土壤</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="土壤"
                   name="soil">
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">地形地势</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" placeholder="地形地势" name="topographicTerrain">
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">土地面积</label>
        <div class="col-sm-3">
            <input type="text" class="form-control" data-rule-number='true'
                   placeholder="土地面积(请输入数字)" name="landArea">
        </div>
    </div>
</div>