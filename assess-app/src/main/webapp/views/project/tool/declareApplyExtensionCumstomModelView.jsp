<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/html" id="other_EnclosureModel">
    <div class="row form-group">
        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
            <div class="form-inline x-valid">
                <label class="col-xs-2  col-sm-2  col-md-2  col-lg-2 col-form-label">
                    申报名称<span class="symbol required"></span>
                </label>
                <div class="col-xs-10  col-sm-10  col-md-10  col-lg-10">
                    <input name="name" class="form-control input-full" placeholder="申报名称" value="${itemData.name}"
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
            <button type="button" class="btn btn-warning btn-sm" onclick="declareApplyExtensionCumstom.cleanItemHTML(this)"><i class="fa fa-minus"></i></button>
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
            notifyInfo('提示','自定义字段必须填写完整!       (假如不需要那么 多自定义字段那么请点击清除按钮)');
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
        declareCommon.ajaxServerFun({declareId: declareId},"/declareApplyExtension/getDeclareApplyExtensionListByExample","get",callback,null) ;
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
        declareCommon.ajaxServerFun({id: id},"/declareApplyExtension/deleteDeclareApplyExtensionById","post",callback,"delete") ;
    };


    declareApplyExtensionCumstom.saveTempData = function (data, callback) {
        declareCommon.ajaxServerFun({fomData: JSON.stringify(data), updateNull: false},"/declareApplyExtension/saveAndUpdateDeclareApplyExtensionAll","post",callback,null,null) ;
    };

</script>