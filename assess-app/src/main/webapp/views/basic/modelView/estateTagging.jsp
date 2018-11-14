<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-11-13
  Time: 18:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <title>楼盘信息标注</title>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="page-title" style="margin: 0px">
                <div class="title_left">
                    <h2>
                        ${estateName}
                    </h2>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_content">
                    <div class="col-sm-9">
                        <iframe name="mapFrame" src="${pageContext.request.contextPath}/map/estateTagging"
                                width="100%"
                                height="100%" frameborder="0"></iframe>
                    </div>
                    <div class="col-sm-3">
                        <table class="table" id="estateTaggingList"></table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="tagging_info_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h3 class="modal-title">标注信息</h3>
            </div>
            <form id="tagging_info_form" class="form-horizontal">
                <input type="hidden" name="lng">
                <input type="hidden" name="lat">
                <div class="modal-body">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                类型<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-2">
                                 <span class="radio-inline">
                                    <input type="radio" required name="type" id="typeBuilding" value="building">
                                     <label for="typeBuilding">楼栋</label>
                                </span>
                            </div>
                            <div class="col-sm-2">
                                <span class="radio-inline">
                                    <input type="radio" required name="type" id="typeUnit" value="unit">
                                     <label for="typeUnit">单元</label>
                                </span>
                            </div>
                            <div class="col-sm-2">
                                <span class="radio-inline">
                                    <input type="radio" required name="type" id="typeOther" value="other">
                                     <label for="typeOther">其它</label>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group" id="buildingNumberGroup">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                楼栋编号<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="buildingNumber"
                                       placeholder="楼栋编号" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group" id="unitNumberGroup">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                单元编号<span class="symbol required"></span>
                            </label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="unitNumber"
                                       placeholder="单元编号" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class="col-sm-2 control-label">
                                描述
                            </label>
                            <div class="col-sm-10">
                                <textarea class="form-control" name="remark" placeholder="描述"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="saveTagging()">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
<script type="text/javascript">
    $(function () {
        $("#typeBuilding").click(function () {
            $('#unitNumberGroup').hide();
            $('#buildingNumberGroup').show();
        })
        $("#typeUnit").click(function () {
            $('#buildingNumberGroup,#unitNumberGroup').show();
        })
        $("#typeOther").click(function () {
            $('#buildingNumberGroup,#unitNumberGroup').hide();
        })

        loadTaggingList();
    })

    //加载数据列表
    function loadTaggingList() {
        var cols = [];
        cols.push({field: 'content', title: '名称'});
        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="deleteTagging(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#estateTaggingList").bootstrapTable('destroy');
        TableInit("estateTaggingList", "${pageContext.request.contextPath}/basicEstateTagging/getBootstrapTableVo", cols, {
            estateId: '${estateId}'
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            pagination: false,
            onLoadSuccess: function (data) {
                $('.tooltips').tooltip();
                mapFrame.window.loadMarkerList(data.rows);//显示标注信息
            }
        });
    }

    //添加标注信息
    function addTagging(lng, lat) {
        $('#typeBuilding').trigger('click');
        $('#tagging_info_form').clearAll(['type']);
        $('#tagging_info_form').find('[name=lng]').val(lng);
        $('#tagging_info_form').find('[name=lat]').val(lat);
        $('#tagging_info_modal').modal();
    }

    //保存标注
    function saveTagging() {
        if(!$('#tagging_info_form').valid()){
            return false;
        }
        var data = formSerializeArray($('#tagging_info_form'));
        if(data.type=='other'&&!data.remark){
            toastr.info('请填写描述信息');
            return false;
        }
        data.estateId = '${estateId}';
        Loading.progressShow();
        $.ajax({
            url: '${pageContext.request.contextPath}/basicEstateTagging/saveAndUpdateBasicEstateTagging',
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    toastr.success('保存成功');
                    loadTaggingList();
                    $('#tagging_info_modal').modal('hide');
                } else {
                    Alert(result.msg);
                }
            }
        })
    }

    //删除标注
    function deleteTagging(id) {
        Alert('确认要删除么？', 2, null, function () {
            Loading.progressShow();
            $.ajax({
                url: '${pageContext.request.contextPath}/basicEstateTagging/deleteBasicEstateTagging',
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        loadTaggingList();
                    } else {
                        Alert(result.msg);
                    }
                }
            })
        })
    }


</script>
</body>
</html>
