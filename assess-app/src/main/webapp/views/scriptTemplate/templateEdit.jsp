<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>脚本模板编辑</title>

    <%@include file="/views/share/main_css.jsp" %>
    <%@include file="template_css.jsp" %>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/bootstrap-drawer/css/bootstrap-drawer.min.css">
</head>
<body>

<div id="previewBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true" >
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">脚本预览</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input type="hidden" name="id" value="${template.id}">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 col-form-label">
                                            模板名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-11">
                                            <label class="form-control input-full">${template.templateName}</label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 col-form-label">
                                            脚本模板
                                        </label>
                                        <div class="col-sm-11">
                                            <label class="form-control input-full" name="scriptTemplate"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 col-form-label">
                                            脚本参数字符串
                                        </label>
                                        <div class="col-sm-11">
                                            <textarea class="form-control input-full" rows="12" name="paramJsonStr"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 col-form-label">
                                            输出结果
                                        </label>
                                        <div class="col-sm-11">
                                            <label class="form-control input-full" name="result"></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick=" scriptTemplateObj.previewPost()">
                    执行预览
                </button>
            </div>
        </div>
    </div>
</div>

<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 10px;">

            <div class="col-md-12">
                <div class="card">
                    <div class="card-header">
                        <div class="card-head-row">
                            <div class="card-title"><i class="fa fa-info-circle"></i> 脚本模板信息</div>
                            <div class="card-tools">

                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <form id="script_template_form" class="form-horizontal">
                            <input name="id" value="${template.id}" type="hidden">
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">
                                            模板名称<span class="symbol required"></span>
                                        </label>
                                        <div class="col-sm-5">
                                            <label class="form-control input-full">${template.templateName}</label>
                                        </div>

                                        <label class="col-sm-1 control-label">
                                            模板key
                                        </label>
                                        <div class="col-sm-5">
                                            <label class="form-control input-full">${template.templateKey}</label>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">
                                            原始文本
                                        </label>
                                        <div class="col-md-11 col-sm-11 col-xs-12">
                                            <div style="width:99%;height:200px;" id="templateOriginalText"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row form-group">
                                <div class="col-md-12">
                                    <div class="form-inline x-valid">
                                        <label class="col-sm-1 control-label">
                                            脚本内容
                                        </label>
                                        <div class="col-md-11 col-sm-11 col-xs-12">

                                            <a type="button"
                                               onclick="scriptTemplateObj.previewView();" data-toggle="drawer"
                                               class="btn btn-xs btn-primary">预览</a>

                                            <textarea class="form-control input-full" id="scriptTemplate"
                                                      name="scriptTemplate">${template.scriptTemplate}</textarea>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col-md-12" style="text-align: center; margin-bottom: 10px">
                <button id="cancel_btn" class="btn btn-sm btn-default" onclick="window.close();">
                    关闭<i style="margin-left: 10px" class="fa fa-times-circle"></i>
                </button>
                <button id="commit_btn" class="btn btn-sm btn-primary" onclick="scriptTemplateObj.save();"
                        onsubmit="return false;">
                    保存<i style="margin-left: 10px" class="fa fa-check-circle"></i>
                </button>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>

<script type="application/javascript"
        src="${pageContext.request.contextPath}/assets/bootstrap-drawer/js/drawer.min.js"></script>
<script type="text/javascript">

    var ue = UE.getEditor('templateOriginalText', {
        toolbars: [
            ['source', 'autotypeset', 'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
        ],
        zIndex: 2,
        initialFrameHeight: 220,
        elementPathEnabled: false,//是否启用元素路径，默认是true显示
        wordCount: false, //是否开启字数统计
        autoHeightEnabled: false,
        autoFloatEnabled: true
    });

    var scriptTemplateObj = {
        script_template_form: $('#script_template_form'),
        previewBox: $('#previewBox'),
        //代码编辑器
        templateEditor: CodeMirror.fromTextArea(document.getElementById("scriptTemplate"), {
            styleActiveLine: true,
            //Java高亮显示
            mode: "text/x-java",
            //显示行号
            lineNumbers: true,
            //设置主题
            theme: "xq-light",
            //代码折叠
            lineWrapping: true,
            foldGutter: true,
            gutters: ["CodeMirror-linenumbers", "CodeMirror-foldgutter"],
            //括号匹配
            matchBrackets: true,
            //智能提示
            extraKeys: {"Ctrl-Space": "autocomplete"}//ctrl-space唤起智能提示
        })
    };


    //保存脚本
    scriptTemplateObj.save = function () {
        if (!scriptTemplateObj.script_template_form.valid()) {
            return false;
        }
        var data = formSerializeArray(scriptTemplateObj.script_template_form);
        data.templateOriginalText = ue.getContent();
        data.scriptTemplate = scriptTemplateObj.templateEditor.getValue();
        console.log(data);
        AssessCommon.ajaxServerMethod({formData: JSON.stringify(data)}, "/scriptTemplate/saveAndUpdateScriptTemplate", "post", function () {
            AlertSuccess("提交成功", "数据已成功保存到数据库", function () {
                window.close();
            });
        });

    };

    //预览结果请求
    scriptTemplateObj.previewPost = function () {
        var box = scriptTemplateObj.previewBox;
        var frm = box.find("form") ;
        var scriptTemplate = scriptTemplateObj.templateEditor.getValue();
        var paramJsonStr = frm.find("[name=paramJsonStr]").val() ;
        var data = {templateKey:'${template.templateKey}' , id:'${template.id}',scriptTemplate:scriptTemplate,paramJsonStr:paramJsonStr};
        console.log(data);
        AssessCommon.ajaxServerMethod(data, "/scriptTemplate/previewScriptTemplate", "post", function (result) {
            frm.find("[name=result]").html(result) ;
        });
    };

    //代码编辑插入光标位置
    function editorInsertString(editor, str) {
        var selection = editor.getSelection();
        if (selection.length > 0) {
            editor.replaceSelection(str);
        } else {
            var doc = editor.getDoc();
            var cursor = doc.getCursor();
            var pos = {
                line: cursor.line,
                ch: cursor.ch
            };
            doc.replaceRange(str, pos);
        }
    }

    scriptTemplateObj.previewView = function () {
        var box = scriptTemplateObj.previewBox;
        box.modal("show");
        var frm = box.find("form") ;
        var scriptTemplate = scriptTemplateObj.templateEditor.getValue();
        frm.find("[name=scriptTemplate]").html(scriptTemplate) ;
    };



    /*************************************/

    $(function () {
        $('.tooltips').tooltip();


        (function (templateOriginalText) {
            if (!templateOriginalText) {
                templateOriginalText = "未配置原始文本";
            }
            setTimeout(function () {
                try {
                    ue.setContent(templateOriginalText, false);
                } catch (e) {
                }
            }, 400);
        }('${template.templateOriginalText}'));

        //开启编辑器
        // scriptTemplateObj.templateEditor;
    });
</script>

</body>



</html>
