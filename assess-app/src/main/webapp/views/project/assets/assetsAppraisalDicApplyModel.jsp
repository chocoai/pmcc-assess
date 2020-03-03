<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/assets/dataAssetsAppraisalDic.js?v=${assessVersion}"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/assets/assetsAppraisalDic.common.js?v=${assessVersion}"></script>

<div id="assetsCustomizeDataField_Fixed_FileId">
    <c:forEach items="${fixedDataFieldAndFile}" var="item">
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 control-label">
                        名称<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-2">
                        <input name="name" class="form-control input-full" placeholder="名称" readonly="readonly"
                               value="${item.name}"
                               onblur="dataAssetsAppraisalDic.inputBlur(this,${item.id})"/>
                    </div>
                    <label class="col-sm-1 control-label">
                        附件
                    </label>
                    <div class="col-sm-3 ">
                        <div id="_assetsCustomizeDataField_Fixed_file${item.id}"></div>
                    </div>
                    <span class="input-group-btn"><input class="btn btn-warning btn-sm" type="button"
                                                         value="X"
                                                         onclick="commonAssets.cleanItemHTML(this,'assetsCustomizeDataField_Fixed_file${item.id}',2,'${item.id}');"></span>


                </div>
            </div>
            <script type="text/javascript">
                $(function () {
                    var fileId = 'assetsCustomizeDataField_Fixed_file${item.id}';
                    commonAssets.showFile2(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", false, true, fileId);
                });
            </script>
        </div>
    </c:forEach>
</div>

<div id="assetsCustomizeDataField_Fixed_fieldId">
    <c:forEach items="${fixedDataField}" var="item">
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 control-label">
                        名称<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-2">
                        <input name="name" class="form-control input-full" placeholder="名称" required="required"
                               value="${item.name}"
                               onblur="dataAssetsAppraisalDic.inputBlur(this,${item.id})"/>
                    </div>
                    <label class="col-sm-1 control-label">
                        附件
                    </label>
                    <div class="col-sm-3 ">
                        <input id="assetsCustomizeDataField_Fixed${item.id}"
                               name="assetsCustomizeDataField_Fixed${item.id}" type="file"
                               multiple="false">
                        <div id="_assetsCustomizeDataField_Fixed${item.id}"></div>
                    </div>
                    <span class="input-group-btn"><input class="btn btn-warning btn-sm" type="button"
                                                         value="X"
                                                         onclick="commonAssets.cleanItemHTML(this,'assetsCustomizeDataField_Fixed${item.id}',1,'${item.id}');"></span>

                </div>
            </div>
            <script type="text/javascript">
                $(function () {
                    var fileId = 'assetsCustomizeDataField_Fixed${item.id}';
                    commonAssets.showFile(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", true, fileId);
                    commonAssets.fileUpload(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", true, fileId);
                });
            </script>

        </div>
    </c:forEach>
</div>


<c:if test="${fixedDataField== null || fn:length(fixedDataField) == 0}">
    <script type="text/javascript">
        $(document).ready(function () {
            commonAssets.writeDeclarationHtml(1);
        });
    </script>
</c:if>
<c:if test="${fixedDataFieldAndFile== null || fn:length(fixedDataFieldAndFile) == 0}">
    <script type="text/javascript">
        $(document).ready(function () {
            commonAssets.writeDeclarationHtml(2);
        });
    </script>
</c:if>
<c:if test="${(fixedDataFieldAndFile== null || fn:length(fixedDataFieldAndFile) == 0) && (fixedDataField== null || fn:length(fixedDataField) == 0)}">
    <script type="text/javascript">
        $(document).ready(function () {
            commonAssets.getServerDeclaration2(function (data) {
                if (data.length == 0) {
                    commonAssets.declareApplyForm.find(".x_panel").first().hide();
                }
            });
        });
    </script>
</c:if>
<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-sm-1 control-label"></label>
            <div class="col-sm-11"><button type="button" class="btn btn-success btn-sm" onclick="commonAssets.writeDeclarationHtml(0)">
                <span class="btn-label"><i class="fa fa-plus"></i></span>添加附件
            </button></div>
        </div>
    </div>
</div>
<div id="assetsCustomizeDataOther_fieldId">
    <c:forEach items="${customizeDataField}" var="item">
        <div class="row form-group other_EnclosureModel${item.id}">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class="col-sm-1 control-label">
                        自定义名称<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-2">
                        <input required="required" name="name" class="form-control input-full"
                               placeholder="自定义名称"
                               value="${item.name}"
                               onblur="dataAssetsAppraisalDic.inputBlur(this,${item.id})"/>
                    </div>


                    <div style="display: none;">
                        <label class="col-sm-1 control-label">
                            类型<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-1 ">
                            <select required="required" name="typeCustomize" class="form-control input-full"
                                    onchange="dataAssetsAppraisalDic.selectChange(this,'${item.id}')">
                            </select>
                        </div>
                    </div>

                    <div style="display: none;">
                        <label class="col-sm-1 control-label">
                            类别
                        </label>
                        <div class="col-sm-1 ">
                            <select name="category" class="form-control input-full search-select select2"
                                    onchange="dataAssetsAppraisalDic.selectChange(this,'${item.id}')">
                            </select>
                        </div>
                    </div>


                    <label class="col-sm-1 control-label">
                        附件
                    </label>
                    <div class="col-sm-3">
                        <input id="other_Enclosure${item.id}"
                               name="other_Enclosure${item.id}" type="file" multiple="false">
                        <div id="_other_Enclosure${item.id}"></div>
                    </div>

                    <span class="input-group-btn"><input class="btn btn-warning btn-sm" type="button"
                                                                                 value="X"
                                                                                 onclick="commonAssets.cleanItemHTML(this,'other_Enclosure${item.id}',0,'${item.id}');"></span>

                </div>
                <script type="text/javascript">
                    $(function () {
                        var fileId = 'other_Enclosure${item.id}';
                        commonAssets.showFile(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", true, fileId);
                        commonAssets.fileUpload(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", true, fileId);
                        var group = $(".other_EnclosureModel${item.id}");
                        dataAssetsAppraisalDic.initForm(${item.jsonString}, group);

                    });
                </script>

            </div>
        </div>
    </c:forEach>
</div>
<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class="col-sm-1 control-label">
                备注
            </label>
            <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11  ">
                                    <textarea class="form-control input-full" name="remark"
                                              onblur="dataAssetsAppraisalDic.inputBlur(this,${assetsCustomizeDataField.id})">${assetsCustomizeDataField.remark}</textarea>
            </div>
        </div>
    </div>
</div>

<%@include file="./commonFieldHtmlModel.jsp" %>

</html>
