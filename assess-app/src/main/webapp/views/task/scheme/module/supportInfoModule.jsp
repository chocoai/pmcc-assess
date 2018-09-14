<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="supportInfoContent">

</div>
<script type="text/javascript">
    var supportInfoModule = {};

    //初始化
    supportInfoModule.init = function (options) {
        var defaluts = {
            supportInfo: undefined,//支撑信息
            readonly: false//
        };
        defaluts = $.extend({}, defaluts, options);
        if (defaluts.supportInfo) {
            $.each(defaluts.supportInfo, function (i, item) {
                //检查该类型模板是否创建 未创建先创建类型模板 已创建则将字段信息直接归入到该模板下
                if ($('#frm_support_type_' + item.supportType).length <= 0) {
                    var supportInfoPanelHtml = $('#supportInfoPanel').html();
                    supportInfoPanelHtml = supportInfoPanelHtml.replace(/{supportTypeName}/g, item.supportTypeName).replace(/{supportType}/g, item.supportType);
                    $('.supportInfoContent').append(supportInfoPanelHtml);
                }

                var supportInfoWellHtml = '';
                if (defaluts.readonly) {
                    supportInfoWellHtml = $('#supportInfoWellView').html();
                    supportInfoWellHtml = supportInfoWellHtml.replace(/{name}/g, AssessCommon.toString(item.name)).replace(/{content}/g, AssessCommon.toString(item.content));
                } else {
                    supportInfoWellHtml = $('#supportInfoWell').html();
                    supportInfoWellHtml = supportInfoWellHtml.replace(/{id}/g, item.id).replace(/{name}/g, AssessCommon.toString(item.name));
                    supportInfoWellHtml = supportInfoWellHtml.replace(/{template}/g, item.template).replace(/{content}/g, AssessCommon.toString(item.content));
                    //处理字段
                    var wellFields = '';
                    var fieldArray = JSON.parse(item.jsonContent);
                    if (fieldArray && fieldArray.length > 0) {
                        wellFields = "<div class='form-group'>";
                        for (var j = 0; j < fieldArray.length; j++) {
                            if (j > 0 && j % 3 == 0) {
                                wellFields += '</div><div class="form-group">';
                            }
                            var supportInfoFieldHtml = $("#supportInfoField").html();
                            // console.log(fieldArray[j]);
                            supportInfoFieldHtml = supportInfoFieldHtml.replace(/{key}/g, fieldArray[j].key).replace(/{value}/g, fieldArray[j].value);
                            wellFields += supportInfoFieldHtml;
                        }
                        wellFields += "</div>";
                    }
                    supportInfoWellHtml = supportInfoWellHtml.replace(/{wellFields}/g, wellFields);
                }
                $('#frm_support_type_' + item.supportType).find('.x_content').append(supportInfoWellHtml);
            })
        }
    }

    //内容替换
    supportInfoModule.fieldReplace = function (_this) {
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
    supportInfoModule.valid = function () {
        //先验证数据
        var forms = $('.supportInfoContent').find('form');
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
    supportInfoModule.getData = function () {
        var supportInfoArray = [];
        $('.supportInfoContent').find('.well').each(function () {
            var supportInfo = {};
            supportInfo.id = $(this).find('[name=id]').val();
            supportInfo.content = $(this).find('[data-name=content]').val();
            supportInfo.jsonContent = [];
            $(this).find('.content-field').find(':text').each(function () {
                var keyValue = {};
                keyValue.key = $(this).attr('data-name');
                keyValue.value = $(this).val();
                supportInfo.jsonContent.push(keyValue);
            })
            supportInfoArray.push(supportInfo);
        })
        return supportInfoArray;
    }
</script>

<script type="text/html" id="supportInfoPanel">
    <div class="x_panel">
        <div class="x_title collapse-link">
            <h2>{supportTypeName}</h2>
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <form id="frm_support_type_{supportType}" class="form-horizontal">
            <div class="x_content">

            </div>
        </form>
    </div>
</script>

<script type="text/html" id="supportInfoWell">
    <div class="well">
        <input type="hidden" name="id" value="{id}">
        <div class="form-group">
            <label class="col-sm-1 control-label">模板</label>
            <div class="col-sm-11"><label class="form-control template">{template}</label></div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    {name}<span class="symbol required"></span>
                </label>
                <div class="col-sm-11">
                    <textarea placeholder="内容" class="form-control" name="content_{id}" data-name="content" required>{content}</textarea>
                </div>
            </div>
        </div>
        <div class="content-field">
            {wellFields}
        </div>
    </div>
</script>

<script type="text/html" id="supportInfoWellView">
    <div class="well">
        <div class="form-group">
            <div class="x-valid"><label class="col-sm-1 control-label">{name}</label>
                <div class="col-sm-11">
                    <label class="form-control">{content}</label>
                </div>
            </div>
        </div>
    </div>
</script>

<!--动态字段-->
<script type="text/html" id="supportInfoField">
    <div class="x-valid">
        <label class="col-sm-1 control-label">
            {key}
        </label>
        <div class="col-sm-3">
            <input type="text" class="form-control" data-name="{key}" value="{value}"
                   onkeyup="supportInfoModule.fieldReplace(this);">
        </div>
    </div>
</script>