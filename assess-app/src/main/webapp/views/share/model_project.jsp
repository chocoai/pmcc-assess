<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="div_model_project" class="modal fade bs-example-modal-sm" data-backdrop="static" aria-hidden="true"
     role="dialog" data-keyboard="false" tabindex="1" style="display: none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">项目选择</h4>
            </div>

            <div class="x_content">
                <input id="model_project_tags" type="text" class="tags">
                <input id="model_project_single" type="hidden">
                <div class="x_content">
                    <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                        <table id="model_project_table">
                        </table>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" class="btn btn-primary" id="btn_model_project">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
<script type="application/javascript">

    $(function () {
        $("#model_project_tags").tagsInput({
            width: "auto",
            interactive: false,
            autosize: false,
            height: 0
        });

    });


    //参数表示在哪个公司或部门下选择相关人员,若为全部则传入0
    function loadSelectProject(currVlaue, single, callback) {
//        Loading.progressShow();
        $("#model_project_single").val(single == true ? "1" : "0");
        model_project_tags_firstLoadTag(currVlaue);
        model_project_talbes_load();

        //绑定模态窗口事件
        $('#btn_model_project').unbind("click");
        $("#btn_model_project").click(function () {
            getSelectProject(callback);
        });

    }

    function model_project_tags_addTag(text) {
        $("#model_project_tags").addTag(text);
    }
    function model_project_tags_removeTag(text) {
        $("#model_project_tags").removeTag(text);
    }
    function model_project_tags_firstLoadTag(text) {
        $("#model_project_tags").importTags(text, true);
    }
    function model_project_tags_checkTag(text) {
        if ($("#model_project_tags").tagExist(text)) {
            return "1";
        }
        else {
            return "0";
        }
    }

    function getSelectProject(callback) {
        var tags = $("#model_project_tags").val();
        $('#div_model_project').modal('hide');
        model_project_tags_firstLoadTag('');
        var data = {};
        data["base"] = tags;
        var id = "";
        var name = "";
        var aTags = tags.split(',');
        for (var i = 0; i < aTags.length; i++) {
            id += aTags[i].split('_')[1] + ",";
            name += aTags[i].split('_')[0] + ",";
        }
        data["projectId"] = id.substring(0, id.length - 1);
        data["projectName"] = name.substring(0, name.length - 1);
        callback(data);
    }

    function model_project_talbes_load() {
        var cols = [];
        cols.push({field: "id", title: "总项目号"});
        cols.push({field: "projectId", title: "项目编号"});
        cols.push({field: "projectName", title: "项目名称"});
        var data = {};
        var parms = {
            pageSize: 8,
            pageList: [8],
            showColumns: false,
            onClickRow: function (rows, $element) {
                var tagText = rows.projectName + "_" + rows.id;
                if ($("#model_project_single").val() == "1") {
                    model_project_tags_firstLoadTag(tagText);
                }
                else {
                    if (model_project_tags_checkTag(tagText) == "0")//如果不存在则进行添加书签
                    {
                        model_project_tags_addTag(tagText);
                    }
                    else//如果存在则删除书签
                    {
                        model_project_tags_removeTag(tagText);
                    }
                }
            },
            onLoadSuccess: function () {
                Loading.progressHide();
                $('#div_model_project').modal({backdrop: 'static', keyboard: false});
            }
        };
        $("#model_project_table").bootstrapTable("destroy");//销毁
        TableInit("model_project_table", "${pageContext.request.contextPath}/home/getProjectList", cols, data, parms);
    }

</script>
