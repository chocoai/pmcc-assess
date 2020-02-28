//中文提示信息
jQuery.extend(jQuery.validator.messages, {
	required: "该字段为必填项",
	remote: "请修正该字段",
	email: "请输入正确格式的电子邮件",
	url: "请输入合法的网址",
	date: "请输入合法的日期",
	dateISO: "请输入合法的日期 (ISO).",
	number: "请输入合法的数字",
    money: "请输入合法金额(0.00)",
    digits: "只能输入整数",
	creditcard: "请输入合法的信用卡号",
	equalTo: "请再次输入相同的值",
	accept: "请输入拥有合法后缀名的字符串",
	maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
	minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
	rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
	range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
	max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
	min: jQuery.validator.format("请输入一个最小为 {0} 的值")
});
//设置默认值
$.validator.setDefaults({
	errorElement: "span",
	errorClass: 'help-block',
	success: function (label, element) {
		label.addClass('help-block valid');
		// mark the current input as valid and display OK icon
		$(element).closest('.x-valid').removeClass('has-error').addClass('has-success').find('.symbol').removeClass('required').addClass('ok');
	},
	errorPlacement: function (error, element) { // render error placement for each input type
		if (element.attr("type") == "radio" || element.attr("type") == "checkbox") { // for chosen elements, need to insert the error after the chosen container
			error.insertAfter($(element).closest('.x-valid').children('div').children().last());
		} else {
			if(element.parent().hasClass("input-group")){
                error.insertAfter(element.parent());
			}else{
                error.insertAfter(element);
			}
			// for other inputs, just perform default behavior
		}
	},
	highlight: function (element) {
		$(element).closest('.help-block').removeClass('valid');
		// display OK icon
		$(element).closest('.x-valid').removeClass('has-success').addClass('has-error').find('.symbol').removeClass('ok').addClass('required');
	}
});

// function cleanValid(formId) {
//     jQuery("#"+formId).find(".x-valid").removeClass('has-error').removeClass('has-success')
// 		.find('.symbol').removeClass('required').removeClass('ok');
//
//     jQuery("#"+formId).find(".x-valid").find('.help-block').remove();
// }
// function cleanAll(formId) {
//     $('#'+formId)[0].reset();
//     cleanValid(formId);
// }

(function ($) {
    $.fn.extend({
        ///<summary>
        /// 清空验证消息
        ///</summary>
        "clearValid": function () {
            this.find(".x-valid").removeClass('has-error').removeClass('has-success')
                .find('.symbol').each(function () {
				if($(this).hasClass('ok')){
                    $(this).removeClass('ok').addClass('required');
				}
            })
				//.removeClass('required').removeClass('ok');

            this.find(".x-valid").find('.help-block').remove();

            return this;
        },
        ///<summary>
        /// 清空验证消息
        ///</summary>
        "clearAll": function (excludeArray) {
            this.clearValid();
            var excludeString='';
			if(excludeArray){
				$.each(excludeArray,function (i,item) {
                    excludeString+='[name="'+item+'"]';
                })
			}
            this.find(':input')
                .not(':button,:submit,:reset,:checkbox')   //将myform表单中input元素type为button、submit、reset、hidden排除
				.not(excludeString)
                .val('') ; //将input元素的value设为空值

            this.find(':checkbox') .removeAttr('checked');
            return this;
        }
    });

    $.extend($.fn, {
        valid: function () {
            var valid = $(this[0]).validBase();
            if (valid === false && typeof toastr != "undefined") {//验证余
                notifyInfo('提示',"请检查表单内容是否完整及符合要求！");
            }
            return valid;
        }
    });
})(jQuery);