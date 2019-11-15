<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2019-10-30
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<input type="hidden" id="id" name="id">
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省
            <span class="symbol required"></span></label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <select name="province"
                    class="form-control search-select select2"
                    required="required">
            </select>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市<span
                class="symbol required"></span></label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <select name="city" class="form-control search-select select2"
                    required="required">

            </select>
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <select name="district"
                    class="form-control search-select select2">
            </select>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">乡镇/街道</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input placeholder="乡镇/街道" class="form-control" name="townShipName"
                   type="text">
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">权利类型</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <select name="landRightType"
                    class="form-control search-select select2">
            </select>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">估价期日</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input type="text" readonly="readonly"
                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                   name="valuationDate" placeholder="估价期日">
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">发布日期</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input type="text" readonly="readonly"
                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                   name="releaseDate" placeholder="发布日期">
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">执行时间</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input type="text" readonly="readonly"
                   class="form-control date-picker dbdate" data-date-format="yyyy-mm-dd"
                   name="executionTime" placeholder="执行时间">
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">标题</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input placeholder="标题" class="form-control" name="title" type="text">
        </div>
    </div>
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">文号</label>
        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
            <input placeholder="文号" class="form-control" name="wordSymbol" type="text">
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
            基准地价定义
        </label>
        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
            <div style="width:99%;height:200px;" id="landDefinition"></div>
        </div>
    </div>
</div>
<div class="form-group">
    <div class="x-valid">
        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附件</label>
        <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
            <input id="uploadFile" placeholder="上传附件" class="form-control" type="file">
            <div id="_uploadFile"></div>
        </div>
    </div>
</div>
