<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                    <label class=" col-sm-1 control-label">
                        名称<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-2">
                        <label class="form-control input-full">
                                ${item.name}
                        </label>
                    </div>

                    <label class=" col-sm-1 control-label">
                        附件
                    </label>
                    <div class="col-sm-3 ">
                        <div id="_assetsCustomizeDataField_Fixed_file${item.id}"></div>
                    </div>
                </div>
                <script type="text/javascript">
                    $(function () {
                        var fileId = 'assetsCustomizeDataField_Fixed_file${item.id}';
                        commonAssets.showFile2(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", false, true, fileId);
                    });
                </script>
            </div>
        </div>
    </c:forEach>
</div>

<div id="assetsCustomizeDataField_Fixed_fieldId">
    <c:forEach items="${fixedDataField}" var="item">
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class=" col-sm-1 control-label">
                        名称<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-2">
                        <label class="form-control input-full">
                                ${item.name}
                        </label>
                    </div>
                    <label class=" col-sm-1 control-label">
                        附件
                    </label>
                    <div class="col-sm-3 ">
                        <div id="_assetsCustomizeDataField_Fixed${item.id}"></div>
                    </div>
                </div>
                <script type="text/javascript">
                    $(function () {
                        var fileId = 'assetsCustomizeDataField_Fixed${item.id}';
                        commonAssets.showFile2(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", false, true, fileId);
                    });
                </script>
            </div>
        </div>
    </c:forEach>
</div>


<c:if test="${(fixedDataFieldAndFile== null || fn:length(fixedDataFieldAndFile) == 0) && (fixedDataField== null || fn:length(fixedDataField) == 0)}">
    <script type="text/javascript">
        $(document).ready(function () {
            commonAssets.declareApplyForm.find(".x_panel").first().hide();
        });
    </script>
</c:if>

<div id="assetsCustomizeDataOther_fieldId">
    <c:forEach items="${customizeDataField}" var="item">
        <div class="row form-group">
            <div class="col-md-12">
                <div class="form-inline x-valid">
                    <label class=" col-sm-1 control-label">
                        自定义名称<span class="symbol required"></span>
                    </label>
                    <div class="col-sm-2">
                        <label class=" form-control input-full">
                                ${item.name}
                        </label>
                    </div>

                    <div style="display: none;">
                        <label class=" col-sm-1 control-label">
                            类型<span class="symbol required"></span>
                        </label>
                        <div class="col-sm-1">
                            <label class=" form-control input-full">
                                    ${item.typeCustomizeName}
                            </label>
                        </div>
                    </div>
                    <div style="display: none;">
                        <label class=" col-sm-1 control-label">
                            类别
                        </label>
                        <div class="col-sm-1">
                            <label class=" form-control input-full">
                                    ${item.categoryName}
                            </label>
                        </div>
                    </div>

                    <label class=" col-sm-1 control-label">
                        附件
                    </label>
                    <div class="col-sm-3">
                        <div id="_other_Enclosure${item.id}"></div>
                    </div>
                </div>
                <script type="text/javascript">
                    $(function () {
                        var fileId = 'other_Enclosure${item.id}';
                        commonAssets.showFile3(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", false, 'approval' == '${flog}', '${activityCnName}'.indexOf("盖章") > -1, fileId);
                    });
                </script>
            </div>
        </div>
    </c:forEach>
</div>


<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <label class=" col-sm-1 control-label">
                备注
            </label>
            <div class="col-sm-11">
                <label class=" form-control input-full">
                    ${assetsCustomizeDataField.remark}
                </label>
            </div>
        </div>
    </div>
</div>

</html>
