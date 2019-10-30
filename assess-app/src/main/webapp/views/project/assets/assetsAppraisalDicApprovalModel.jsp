
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/assets/dataAssetsAppraisalDic.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/assets/assetsAppraisalDic.common.js"></script>

<div class="x_panel">
    <div class="x_title">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>申报编辑文件
            <small>
            </small>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div id="assetsCustomizeDataField_Fixed_FileId">
            <c:forEach items="${fixedDataFieldAndFile}" var="item">
                <div class="form-group">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            名称<span class="symbol required"></span>
                        </label>
                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                            <label class="form-control">
                                    ${item.name}
                            </label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            附件
                        </label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                            <div id="_assetsCustomizeDataField_Fixed_file${item.id}"></div>
                        </div>
                    </div>
                    <script type="text/javascript">
                        $(function () {
                            var fileId = 'assetsCustomizeDataField_Fixed_file${item.id}';
                            commonAssets.showFile2(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", false,false, fileId);
                        });
                    </script>
                </div>
            </c:forEach>
        </div>

        <div id="assetsCustomizeDataField_Fixed_fieldId">
            <c:forEach items="${fixedDataField}" var="item">
                <div class="form-group">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            名称<span class="symbol required"></span>
                        </label>
                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                            <label class="form-control">
                                    ${item.name}
                            </label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            附件
                        </label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                            <div id="_assetsCustomizeDataField_Fixed${item.id}"></div>
                        </div>
                    </div>
                    <script type="text/javascript">
                        $(function () {
                            var fileId = 'assetsCustomizeDataField_Fixed${item.id}';
                            commonAssets.showFile2(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", false,false, fileId);
                        });
                    </script>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<c:if test="${(fixedDataFieldAndFile== null || fn:length(fixedDataFieldAndFile) == 0) && (fixedDataField== null || fn:length(fixedDataField) == 0)}">
    <script type="text/javascript">
        $(document).ready(function () {
            commonAssets.declareApplyForm.find(".x_panel").first().hide() ;
        });
    </script>
</c:if>


<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>自定义附件</h3>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div id="assetsCustomizeDataOther_fieldId">
            <c:forEach items="${customizeDataField}" var="item">
                <div class="form-group">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            自定义名称<span class="symbol required"></span>
                        </label>
                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                            <label class=" form-control">
                                    ${item.name}
                            </label>
                        </div>
                    </div>
                    <div class="x-valid" style="display: none;">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            类型<span class="symbol required"></span>
                        </label>
                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                            <label class=" form-control">
                                    ${item.typeCustomizeName}
                            </label>
                        </div>
                    </div>
                    <div class="x-valid" style="display: none;">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            类别
                        </label>
                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                            <label class=" form-control">
                                    ${item.categoryName}
                            </label>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            附件
                        </label>
                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  ">
                            <div id="_other_Enclosure${item.id}"></div>
                        </div>
                    </div>
                    <script type="text/javascript">
                        $(function () {
                            var fileId = 'other_Enclosure${item.id}';
                            commonAssets.showFile3(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", false,false,'${activityCnName}'.indexOf("盖章") > -1, fileId);
                        });
                    </script>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<div class="x_panel">
    <div class="x_content">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    备注
                </label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11  ">
                        <label class=" form-control">
                            ${assetsCustomizeDataField.remark}
                        </label>
                </div>
            </div>
        </div>
    </div>
</div>
</html>
