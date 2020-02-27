<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<script type="text/html" id="assetsCustomizeDataField_Fixed_fileModel">
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">
                    {name}<span class="symbol required"></span>
                </label>
                <div class="col-sm-2">
                    <input name="name" class="form-control input-full" placeholder="{name}" value="{name}"
                           readonly="readonly" onblur="dataAssetsAppraisalDic.inputBlur(this,'{id}')"/>
                </div>

                <label class="col-sm-1 control-label">
                    附件
                </label>
                <div class="col-sm-3">
                    <div id="_assetsCustomizeDataField_Fixed_file{number}"></div>
                </div>
                <span class="input-group-btn"><input class="btn btn-warning btn-sm" type="button" value="X"
                                                     onclick="{method}.cleanItemHTML(this,'assetsCustomizeDataField_Fixed_file{number}','{value}','{id}')"></span>
            </div>
        </div>
    </div>
</script>


<script type="text/html" id="assetsCustomizeDataField_Fixed_fieldModel">
    <div class="row form-group">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">
                    名称<span class="symbol required"></span>
                </label>
                <div class="col-sm-2">
                    <input name="name" required="required" class="form-control input-full" placeholder="{name}"
                           value="{name}" onblur="dataAssetsAppraisalDic.inputBlur(this,'{id}')"/>
                </div>

                <label class="col-sm-1 control-label">
                    附件
                </label>
                <div class="col-sm-3">
                    <input id="assetsCustomizeDataField_Fixed{number}" name="assetsCustomizeDataField_Fixed{number}"
                           type="file" multiple="false">
                    <div id="_assetsCustomizeDataField_Fixed{number}"></div>
                </div>

                <span class="input-group-btn"><input class="btn btn-warning btn-sm" type="button" value="X"
                                                     onclick="{method}.cleanItemHTML(this,'assetsCustomizeDataField_Fixed{number}','{value}','{id}')"></span>
            </div>
        </div>
    </div>
</script>

<script type="text/html" id="other_EnclosureModel">
    <div class="row form-group other_EnclosureModel{number}">
        <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-1 control-label">
                    自定义名称<span class="symbol required"></span>
                </label>
                <div class="col-sm-2">
                    <input name="name" required="required" class="form-control input-full" placeholder="自定义名称"
                           onblur="dataAssetsAppraisalDic.inputBlur(this,'{id}')"/>
                </div>

                <div style="display: none;">
                    <label class="col-sm-1 control-label">
                        类型<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-1 ">
                        <select required="required" name="typeCustomize" class="form-control input-full"
                                onchange="dataAssetsAppraisalDic.selectChange(this,'{id}')">
                        </select>
                    </div>
                </div>

                <div style="display: none;">
                    <label class="col-sm-1 control-label">
                        类别
                    </label>
                    <div class="col-sm-1 ">
                        <select name="category" class="form-control input-full search-select select2"
                                onchange="dataAssetsAppraisalDic.selectChange(this,'{id}')">
                            <option value="">请先选择类型</option>
                        </select>
                    </div>
                </div>

                <label class="col-sm-1 control-label">
                    附件
                </label>
                <div class="col-sm-3">
                    <input id="other_Enclosure{number}" name="other_Enclosure{number}" type="file" multiple="false">
                    <div id="_other_Enclosure{number}"></div>
                </div>

                <span class="input-group-btn"><input class="btn btn-warning btn-sm" type="button" value="X"
                                                     onclick="{method}.cleanItemHTML(this,'other_Enclosure{number}','{value}','{id}')"></span>
            </div>
        </div>
    </div>
</script>


</html>
