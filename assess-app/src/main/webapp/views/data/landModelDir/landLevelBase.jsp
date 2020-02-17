<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2019-10-30
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<input type="hidden" id="id" name="id">

<div class="row form-group">
    <div class="col-md-6">
        <div class="form-inline x-valid">
            <label class="col-sm-2 col-form-label">
                省<span class="symbol required"></span>
            </label>
            <div class="col-sm-10">
                <select name="province"
                        class="form-control input-full search-select select2"
                        required="required">
                </select>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="form-inline x-valid">
            <label class="col-sm-2 col-form-label">
                市<span class="symbol required"></span>
            </label>
            <div class="col-sm-10">
                <select name="city" class="form-control input-full search-select select2"
                        required="required">

                </select>
            </div>
        </div>
    </div>
</div>

<div class="row form-group">
    <div class="col-md-6">
        <div class="form-inline x-valid">
            <label class="col-sm-2 col-form-label">
                县
            </label>
            <div class="col-sm-10">
                <select name="district"
                        class="form-control input-full search-select select2">
                </select>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="form-inline x-valid">
            <label class="col-sm-2 col-form-label">
                乡镇/街道
            </label>
            <div class="col-sm-10">
                <input placeholder="乡镇/街道" class="form-control input-full" name="townShipName"
                       type="text">
            </div>
        </div>
    </div>
</div>

<div class="row form-group">
    <div class="col-md-6">
        <div class="form-inline x-valid">
            <label class="col-sm-2 col-form-label">
                权利类型
            </label>
            <div class="col-sm-10">
                <select name="landRightType"
                        class="form-control input-full search-select select2">
                </select>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="form-inline x-valid">
            <label class="col-sm-2 col-form-label">
                估价期日
            </label>
            <div class="col-sm-10">
                <input type="text" readonly="readonly"
                       class="form-control input-full date-picker dbdate" data-date-format="yyyy-mm-dd"
                       name="valuationDate" placeholder="估价期日">
            </div>
        </div>
    </div>
</div>

<div class="row form-group">
    <div class="col-md-6">
        <div class="form-inline x-valid">
            <label class="col-sm-2 col-form-label">
                发布日期
            </label>
            <div class="col-sm-10">
                <input type="text" readonly="readonly"
                       class="form-control input-full date-picker dbdate" data-date-format="yyyy-mm-dd"
                       name="releaseDate" placeholder="发布日期">
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="form-inline x-valid">
            <label class="col-sm-2 col-form-label">
                执行时间
            </label>
            <div class="col-sm-10">
                <input type="text" readonly="readonly"
                       class="form-control input-full date-picker dbdate" data-date-format="yyyy-mm-dd"
                       name="executionTime" placeholder="执行时间">
            </div>
        </div>
    </div>
</div>

<div class="row form-group">
    <div class="col-md-6">
        <div class="form-inline x-valid">
            <label class="col-sm-2 col-form-label">
                标题
            </label>
            <div class="col-sm-10">
                <input placeholder="标题" class="form-control input-full" name="title" type="text">
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="form-inline x-valid">
            <label class="col-sm-2 col-form-label">
                文号
            </label>
            <div class="col-sm-10">
                <input placeholder="文号" class="form-control input-full" name="wordSymbol" type="text">
            </div>
        </div>
    </div>
</div>

<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-sm-1 col-form-label">
                基准地价定义
            </label>
            <div class="col-sm-11">
                <div style="width:99%;height:200px;" id="landDefinition"></div>
            </div>
        </div>
    </div>
</div>

<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-sm-1 col-form-label">
                附件
            </label>
            <div class="col-sm-11">
                <input id="uploadFile" placeholder="上传附件" class="form-control input-full" type="file">
                <div id="_uploadFile"></div>
            </div>
        </div>
    </div>
</div>
