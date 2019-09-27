
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<script type="text/html" id="assetsCustomizeDataField_Fixed_fileModel">
    <div class="form-group">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                {name}<span class="symbol required"></span>
            </label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <input name="name" class="form-control" placeholder="{name}" value="{name}" readonly="readonly"  onblur="dataAssetsAppraisalDic.inputBlur(this,'{id}')"/>
            </div>
        </div>
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                附件
            </label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                <div id="_assetsCustomizeDataField_Fixed_file{number}"></div>
            </div>
        </div>
        <div class="x-valid">
            <span class="input-group-btn"><input class="btn btn-warning" type="button" value="X"
                                                 onclick="{method}.cleanItemHTML(this,'assetsCustomizeDataField_Fixed_file{number}','{value}','{id}')"></span>
        </div>
    </div>
</script>


<script type="text/html" id="assetsCustomizeDataField_Fixed_fieldModel">
    <div class="form-group">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                名称<span class="symbol required"></span>
            </label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <input name="name" required="required" class="form-control" placeholder="{name}" value="{name}"  onblur="dataAssetsAppraisalDic.inputBlur(this,'{id}')"/>
            </div>
        </div>
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                附件
            </label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                <input id="assetsCustomizeDataField_Fixed{number}"  name="assetsCustomizeDataField_Fixed{number}" type="file" multiple="false">
                <div id="_assetsCustomizeDataField_Fixed{number}"></div>
            </div>
        </div>
        <div class="x-valid">
            <span class="input-group-btn"><input class="btn btn-warning" type="button" value="X"
                                                 onclick="{method}.cleanItemHTML(this,'assetsCustomizeDataField_Fixed{number}','{value}','{id}')"></span>
        </div>
    </div>
</script>

<script type="text/html" id="other_EnclosureModel">
    <div class="form-group other_EnclosureModel{number}">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                自定义名称<span class="symbol required"></span>
            </label>
            <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                <input name="name" required="required" class="form-control" placeholder="自定义名称" onblur="dataAssetsAppraisalDic.inputBlur(this,'{id}')"/>
            </div>
        </div>
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                类型<span class="symbol required"></span>
            </label>
            <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  ">
                <select required="required" name="typeCustomize" class="form-control"  onchange="dataAssetsAppraisalDic.selectChange(this,'{id}')">
                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                类别
            </label>
            <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  ">
                <select name="category" class="form-control search-select select2" onchange="dataAssetsAppraisalDic.selectChange(this,'{id}')">
                    <option value="">请先选择类型</option>
                </select>
            </div>
        </div>
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                附件
            </label>
            <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  ">
                <input id="other_Enclosure{number}" name="other_Enclosure{number}" type="file" multiple="false">
                <div id="_other_Enclosure{number}"></div>
            </div>
        </div>
        <div class="x-valid">
            <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  ">
                <span class="input-group-btn"><input class="btn btn-warning" type="button" value="X"
                                                     onclick="{method}.cleanItemHTML(this,'other_Enclosure{number}','{value}','{id}')"></span>
            </div>
        </div>
    </div>
</script>


</html>
