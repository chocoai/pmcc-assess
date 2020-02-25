<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12 compileInfoContent">

</div>
<script type="text/javascript">
    var compileInfoModule = {};

    //初始化
    compileInfoModule.init = function (options) {
        var defaluts = {
            compileInfo: undefined,//支撑信息
            readonly: false//
        };
        defaluts = $.extend({}, defaluts, options);
        if (defaluts.compileInfo) {
            $.each(defaluts.compileInfo, function (i, item) {
                //检查该类型模板是否创建 未创建先创建类型模板 已创建则将字段信息直接归入到该模板下
                if ($('#frm_compile_type_' + item.reportAnalysisType).length <= 0) {
                    var compileInfoPanelHtml = $('#compileInfoPanel').html();
                    compileInfoPanelHtml = compileInfoPanelHtml.replace(/{reportAnalysisTypeName}/g, item.reportAnalysisName).replace(/{reportAnalysisType}/g, item.reportAnalysisType);
                    $('.compileInfoContent').append(compileInfoPanelHtml);
                }
                var compileInfoWellHtml = '';
                if (defaluts.readonly || !item.bisModifiable) {
                    compileInfoWellHtml = $('#compileInfoWellView').html();
                    compileInfoWellHtml = compileInfoWellHtml.replace(/{name}/g, AssessCommon.toString(item.name)).replace(/{content}/g, AssessCommon.toString(item.content));
                } else {
                    compileInfoWellHtml = $('#compileInfoWell').html();
                    compileInfoWellHtml = compileInfoWellHtml.replace(/{id}/g, item.id).replace(/{name}/g, AssessCommon.toString(item.name));
                    compileInfoWellHtml = compileInfoWellHtml.replace(/{content}/g, AssessCommon.toString(item.content));
                    if(item.template){
                        compileInfoWellHtml = compileInfoWellHtml.replace(/{template}/g, item.template);
                    }else{
                        compileInfoWellHtml = compileInfoWellHtml.replace(/{templateDisplay}/g, 'style="display:none"');
                    }
                    //处理字段
                    var wellFields = '';
                    if(item.jsonContent){
                        var fieldArray = JSON.parse(item.jsonContent);
                        if (fieldArray && fieldArray.length > 0) {
                            wellFields = "<div class='row form-group'>";
                            wellFields += "<div class='col-md-12'>";
                            wellFields += '<div class="form-inline x-valid">';

                            for (var j = 0; j < fieldArray.length; j++) {
                                if (j > 0 && j % 3 == 0) {
                                    wellFields += '</div></div></div><div class="row form-group"><div class="col-md-12"><div class="form-inline x-valid">';
                                }
                                var compileInfoFieldHtml = $("#compileInfoField").html();
                                compileInfoFieldHtml = compileInfoFieldHtml.replace(/{key}/g, fieldArray[j].key).replace(/{value}/g, fieldArray[j].value);
                                wellFields += compileInfoFieldHtml;
                            }
                            wellFields += "</div>";
                            wellFields += "</div>";
                            wellFields += "</div>";
                        }
                    }
                    compileInfoWellHtml = compileInfoWellHtml.replace(/{wellFields}/g, wellFields);
                }
                $('#frm_compile_type_' + item.reportAnalysisType).find('.x_content').append(compileInfoWellHtml);
            })
        }
    }

    //内容替换
    compileInfoModule.fieldReplace = function (_this) {
        var well = $(_this).closest(".well");
        var template = well.find('.template').text();
        well.find('.content-field').find('input:text').each(function () {
            if ($(this).val()) {
                template = AssessCommon.replaceTemplate(template, $(this).attr('data-name'), $(this).val());
            }
        })
        well.find('[data-name="content"]').val(template);
    };

    //验证
    compileInfoModule.valid = function () {
        //先验证数据
        var forms = $('.compileInfoContent').find('form');
        for (var i = 0; i < forms.length; i++) {
            var form = $(forms[i]);
            var title = form.closest('.x_panel').find('.x_title').find('h2').text();
            if (!form.valid(title + '未填写完整！')) {
                return false;
            }
        }
        return true;
    }

    //获取需要保存的数据
    compileInfoModule.getData = function () {
        var compileInfoArray = [];
        $('.compileInfoContent').find('.well').each(function () {
            var compileInfo = {};
            compileInfo.id = $(this).find('[name=id]').val();
            compileInfo.content = $(this).find('[data-name=content]').val();
            if (compileInfo.content) {
                compileInfo.jsonContent = [];
                $(this).find('.content-field').find(':text').each(function () {
                    var keyValue = {};
                    keyValue.key = $(this).attr('data-name');
                    keyValue.value = $(this).val();
                    compileInfo.jsonContent.push(keyValue);
                })
                compileInfoArray.push(compileInfo);
            }
        })
        return compileInfoArray;
    }
</script>


<script type="text/html" id="compileInfoPanel">

    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    {reportAnalysisTypeName}
                </div>
                <div class="card-tools">
                    <button class="btn btn-icon btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form id="frm_compile_type_{reportAnalysisType}" class="form-horizontal">
                <div class="x_content">

                </div>
            </form>
        </div>
    </div>

</script>


<script type="text/html" id="compileInfoWell">
    <div class="well">
        <input type="hidden" name="id" value="{id}">
        <div class="row form-group" {templateDisplay}>
            <label class="col-sm-2 control-label">模板</label>
            <div class="col-sm-10 "><label class="form-control input-full template">{template}</label></div>
        </div>
        <div class="row form-group">
            <div class="col-md-12">
            <div class="form-inline x-valid">
                <label class="col-sm-2 control-label">
                    {name}<span class="symbol required"></span>
                </label>
                <div class="col-sm-10 ">
                    <textarea placeholder="内容" class="form-control input-full" name="content_{id}" data-name="content" required>{content}</textarea>
                </div>
            </div>
            </div>
        </div>
        <div class="content-field">
            {wellFields}
        </div>
    </div>
</script>

<script type="text/html" id="compileInfoWellView">
    <div class="well">
        <div class="row form-group">
            <div class="col-md-12">
            <div class="form-inline x-valid"><label class="col-sm-2 control-label">{name}</label>
                <div class="col-sm-10 ">
                    <label class="form-control input-full">{content}</label>
                </div>
            </div>
            </div>
        </div>
    </div>
</script>

<!--动态字段-->
<script type="text/html" id="compileInfoField">

        <label class="col-sm-2 control-label">
            {key}
        </label>
        <div class="col-sm-3">
            <input type="text" class="form-control input-full" data-name="{key}" value="{value}"
                   onkeyup="compileInfoModule.fieldReplace(this);">
        </div>

</script>