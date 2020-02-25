<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<script type="text/html" id="other_EnclosureModel">
    <div class="form-group">
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                自定义名称<span class="symbol required"></span>
            </label>
            <input type="hidden" name="id" value="{id}">
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                <input name="name" class="form-control" placeholder="自定义名称"
                       onblur="declareApplyExtensionCumstom.targetSave(this);"/>
            </div>
        </div>
        <div class="x-valid">
            <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                附件
            </label>
            <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3  ">
                <input id="other_Enclosure{id}" name="other_Enclosure{id}" type="file" multiple="false">
                <div id="_other_Enclosure{id}"></div>
            </div>
        </div>
        <div class="x-valid">
            <span class="input-group-btn"><input class="btn btn-warning" type="button" value="X"
                                                 onclick="declareApplyExtensionCumstom.cleanItemHTML(this)"></span>
        </div>
    </div>
</script>--%>
<script type="text/html" id="other_EnclosureModel">
    <div class="row form-group">
        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
            <div class="form-inline x-valid">
                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                    自定义名称<span class="symbol required"></span>
                </label>
                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                    <input name="name" class="form-control input-full" placeholder="自定义名称" value="${itemData.name}"
                           onblur="declareApplyExtensionCumstom.targetSave(this);"/>
                </div>
            </div>
        </div>
        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
            <div class="form-inline x-valid">
                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                    附件
                </label>
                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                    <input id="other_Enclosure{id}" name="other_Enclosure{id}" type="file" multiple="false">
                    <div id="_other_Enclosure{id}"></div>
                </div>
            </div>
        </div>
        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
            <span class="input-group-btn"><input class="btn btn-warning btn-sm" type="button" value="X"
                                                 onclick="declareApplyExtensionCumstom.cleanItemHTML(this)"></span>
        </div>
    </div>
</script>


<script>

    var declareApplyExtensionCumstom = {};

    /**
     * 清除数据
     * @param _this
     */
    declareApplyExtensionCumstom.cleanItemHTML = function (_this) {
        var target = $(_this).closest(".form-group");
        declareApplyExtensionCumstom.deleteById(target.find("[name='id']").val(), function () {
            target.remove();
            notifyInfo("清除","清除本条数据成功!");
        });
    };

    /**
     * 添加数据
     * @param declareId
     * @param projectId
     * @param _this
     */
    declareApplyExtensionCumstom.appendHTML = function (declareId, projectId, _this) {
        var html = $("#other_EnclosureModel").html();
        var target = $(_this).closest("form");
        declareApplyExtensionCumstom.saveTempData([{declareId: declareId, projectId: projectId}], function (data) {
            var item = data[0];
            var fileId = "other_Enclosure" + item.id;
            html = html.replace(/{id}/g, item.id);
            target.append(html);
            declareCommon.showFile(fileId, AssessDBKey.DeclareApplyExtension, item.id, true, fileId);
            declareCommon.fileUpload(fileId, AssessDBKey.DeclareApplyExtension, item.id, true, fileId);
            notifySuccess("添加","添加本条数据成功!");
        });

    };

    /**
     * 校验
     * @param form
     * @returns {boolean}
     */
    declareApplyExtensionCumstom.valid = function (form) {
        var formData = formSerializeArray(form);
        var number = formData.id.split(",").length;
        var count = 0;
        $.each(formData.name.split(","), function (i, item) {
            if (declareApplyExtensionCumstom.isNotEmpty(item)) {
                count++;
            }
        });
        if (count != number) {
            notifyInfo('自定义字段必须填写完整!       (假如不需要那么 多自定义字段那么请点击清除按钮)');
            return false;
        }
        return true;
    };

    declareApplyExtensionCumstom.isNotEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    declareApplyExtensionCumstom.getDeclareApplyExtensionList = function (declareId, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/declareApplyExtension/getDeclareApplyExtensionListByExample",
            type: "get",
            dataType: "json",
            data: {declareId: declareId},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    AlertError("获取数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

    declareApplyExtensionCumstom.targetSave = function (_this) {
        var target = $(_this).closest(".form-group");
        (function (name, id) {
            if (!name) {
                return false;
            }
            if (!id) {
                return false;
            }
            declareApplyExtensionCumstom.saveTempData([{
                id: id,
                name: name
            }], null);
        }(target.find("[name='name']").val(), target.find("[name='id']").val()));
    };

    declareApplyExtensionCumstom.deleteById = function (id, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/declareApplyExtension/deleteDeclareApplyExtensionById",
            type: "post",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    AlertError("获取数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };


    declareApplyExtensionCumstom.saveTempData = function (data, callback) {
        $.ajax({
            url: "${pageContext.request.contextPath}/declareApplyExtension/saveAndUpdateDeclareApplyExtensionAll",
            type: "post",
            dataType: "json",
            data: {fomData: JSON.stringify(data), updateNull: false},
            success: function (result) {
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    AlertError("获取数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("调用服务端方法失败，失败原因:" + result.errmsg);
            }
        });
    };

</script>