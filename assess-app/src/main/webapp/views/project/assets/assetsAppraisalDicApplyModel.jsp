
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/assets/dataAssetsAppraisalDic.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/assets/assetsAppraisalDic.common.js"></script>
<div class="x_panel">
    <div class="x_title collapse-link">
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
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <input name="name" class="form-control" placeholder="名称" readonly="readonly"
                                   value="${item.name}"
                                   onblur="dataAssetsAppraisalDic.inputBlur(this,${item.id})"/>
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
                            commonAssets.showFile2(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", false, true, fileId);
                        });
                    </script>
                    <div class="x-valid">
                                        <span class="input-group-btn"><input class="btn btn-warning" type="button"
                                                                             value="X"
                                                                             onclick="commonAssets.cleanItemHTML(this,'assetsCustomizeDataField_Fixed_file${item.id}',2,'${item.id}');"></span>
                    </div>
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
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                            <input name="name" class="form-control" placeholder="名称" required="required"
                                   value="${item.name}"
                                   onblur="dataAssetsAppraisalDic.inputBlur(this,${item.id})"/>
                        </div>
                    </div>
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            附件
                        </label>
                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                            <input id="assetsCustomizeDataField_Fixed${item.id}"
                                   name="assetsCustomizeDataField_Fixed${item.id}" type="file"
                                   multiple="false">
                            <div id="_assetsCustomizeDataField_Fixed${item.id}"></div>
                        </div>
                    </div>
                    <script type="text/javascript">
                        $(function () {
                            var fileId = 'assetsCustomizeDataField_Fixed${item.id}';
                            commonAssets.showFile(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", true, fileId);
                            commonAssets.fileUpload(fileId, AssessDBKey.AssetsCustomizeDataField, "${item.id}", true, fileId);
                        });
                    </script>
                    <div class="x-valid">
                                        <span class="input-group-btn"><input class="btn btn-warning" type="button"
                                                                             value="X"
                                                                             onclick="commonAssets.cleanItemHTML(this,'assetsCustomizeDataField_Fixed${item.id}',1,'${item.id}');"></span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
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
                if (data.length == 0){
                    commonAssets.declareApplyForm.find(".x_panel").first().hide() ;
                }
            });
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
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    自定义操作
                </label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                    <div class="btn  btn-success" onclick="commonAssets.writeDeclarationHtml(0)"><i
                            class="fa fa-plus"></i>
                    </div>
                </div>
            </div>
        </div>
        <div id="assetsCustomizeDataOther_fieldId">
            <c:forEach items="${customizeDataField}" var="item">
                <div class="form-group other_EnclosureModel${item.id}">
                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            自定义名称<span class="symbol required"></span>
                        </label>
                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                            <input required="required" name="name" class="form-control"
                                   placeholder="自定义名称"
                                   value="${item.name}"
                                   onblur="dataAssetsAppraisalDic.inputBlur(this,${item.id})"/>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            类型<span class="symbol required"></span>
                        </label>
                        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  ">
                            <select required="required" name="typeCustomize" class="form-control"
                                    onchange="dataAssetsAppraisalDic.selectChange(this,'${item.id}')">
                            </select>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            类别
                        </label>
                        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  ">
                            <select name="category" class="form-control search-select select2"
                                    onchange="dataAssetsAppraisalDic.selectChange(this,'${item.id}')">
                            </select>
                        </div>
                    </div>

                    <div class="x-valid">
                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                            附件
                        </label>
                        <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  ">
                            <input id="other_Enclosure${item.id}"
                                   name="other_Enclosure${item.id}" type="file" multiple="false">
                            <div id="_other_Enclosure${item.id}"></div>
                        </div>
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
                    <div class="x-valid">
                        <div class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  ">
                                            <span class="input-group-btn"><input class="btn btn-warning" type="button"
                                                                                 value="X"
                                                                                 onclick="commonAssets.cleanItemHTML(this,'other_Enclosure${item.id}',0,'${item.id}');"></span>
                        </div>
                    </div>
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
                                    <textarea class="form-control" name="remark"
                                              onblur="dataAssetsAppraisalDic.inputBlur(this,${assetsCustomizeDataField.id})">${assetsCustomizeDataField.remark}</textarea>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="./commonFieldHtmlModel.jsp" %>

</html>
