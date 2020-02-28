
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    屋面结构
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-down"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body" style="display: none">
            <form class="form-horizontal">
                <table class="table table-bordered" id="ExamineBuildingSurfaceList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>



<script>
    function examineBuildingSurfaceList() {
        var cols = commonColumn.buildingSurfaceColumn();
        $("#ExamineBuildingSurfaceList").bootstrapTable('destroy');
        TableInit('ExamineBuildingSurfaceList', "${pageContext.request.contextPath}/basicBuildingSurface/getBootstrapTableVo", cols, {
            buildingId: buildingCommon.getBuildingId(),
            approval:true
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $('.tooltips').tooltip();
            }
        });
    }

    $(function () {

        //绑定事件
        $('#ExamineBuildingSurfaceList').closest('.full-height').find('.card-header').bind('click', function () {
            examineBuildingSurfaceList();
        })
    });

</script>
