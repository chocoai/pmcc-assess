<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/html" id="categoryDiv">
    <div class="col-md-12">
        <div class="card full-height">
            <div class="card-header">
                <div class="card-head-row">
                    <div class="card-title">
                        土地类型类别（_number）
                        <small>
                        </small>
                    </div>
                    <div class="card-tools">
                        <button class="btn  btn-link btn-primary btn-xs"><span
                                class="fa fa-angle-down"></span>
                        </button>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <form class="form-horizontal" id="categoryFrm_number">
                    <input type="hidden" name="id">
                    <input type="hidden" name="landUseTypeId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">土地用途类型</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="landUseType"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 control-label">土地用途类别</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="landUseCategory"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 control-label">土地级别</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="landLevelName"
                                                       class="form-control input-full"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">土地取得时间</label>
                                            <div class="col-sm-3">
                                                <label name="acquisitionTime" data-date-format="yyyy-mm-dd"
                                                       class="form-control input-full date-picker dbdate"></label>
                                            </div>
                                            <label class="col-sm-1 control-label">土地使用年限</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="landUseYear"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 control-label">土地形状</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="landShape"
                                                       class="form-control input-full"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">开发时间</label>
                                            <div class="col-sm-3">
                                                <label name="developTime" data-date-format="yyyy-mm-dd"
                                                       class="form-control input-full date-picker dbdate"></label>
                                            </div>
                                            <label class="col-sm-1 control-label">容积率</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="plotRatio"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 control-label">建筑密度</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="buildingDensity"
                                                       class="form-control input-full"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">绿地率</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="greeningRate"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 control-label">兼容类型</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="compatibilityType"
                                                       class="form-control input-full"></label>
                                            </div>
                                            <label class="col-sm-1 control-label">兼容比例</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="compatibilityRate"
                                                       class="form-control input-full"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-1 control-label">建筑限高</label>
                                            <div class="col-sm-3">
                                                <label type="text" name="heightPermitted"
                                                       class="form-control input-full"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>


                </form>
            </div>
        </div>

    </div>
</script>



<script type="text/javascript">
    $(function () {
        landUseType.loadHtml();
    });

    var LandUseType = function () {

    };
    LandUseType.prototype.config = {
        father: {
            frm: function () {
                return "frmFather";
            },
            table: function () {
                return "tb_LandUseTypeList";
            },
            box: function () {
                return "divBoxUseTypeFather";
            }
        },
        son: {
            frm: function () {
                return "frmSon";
            },
            table: function () {
                return "tb_LandUseCategoryList";
            },
            box: function () {
                return "divBoxUseCategorySon";
            },
            tableBox: function () {
                return "LandUseCategoryListBox";
            },
            tableFrm: function () {
                return "LandUseCategoryListBoxFrm";
            },
        }
    }
    LandUseType.prototype.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    }

    var landUseType = new LandUseType();

    var num = 1;

    landUseType.loadSonDataListHtml = function (landUseTypeId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/basicEstateLandUseCategory/basicEstateLandUseCategoryList",
            type: "get",
            dataType: "json",
            data: {landUseTypeId: landUseTypeId},
            success: function (result) {
                if (result.ret) {
                    if (result.ret) {
                        if (result.data.length >= 1) {
                            $.each(result.data, function (i, item) {
                                var html = $("#categoryDiv").html();
                                var number = num;
                                html = html.replace(/_number/g, number);
                                $("#LandUseTypeContent").append(html);
                                $("#categoryFrm" + number).initForm(item);
                                //百分字段
                                $("#categoryFrm" + number).find('[name=greeningRate]').text(AssessCommon.pointToPercent(item.greeningRate));
                                $("#categoryFrm" + number).find('[name=compatibilityRate]').text(AssessCommon.pointToPercent(item.compatibilityRate));
                               
                                num++;
                            });
                        }
                    }
                }
                else {
                    ("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    landUseType.loadHtml = function () {
        //找到所有主表数据，一条一个table
        $.ajax({
            url: "${pageContext.request.contextPath}/basicEstateLandUseType/basicEstateLandUseTypeList",
            type: "get",
            dataType: "json",
            data: {estateId: estateCommon.getEstateId()},
            success: function (result) {
                if (result.ret) {
                    if (result.data) {
                        $.each(result.data, function (i, item) {
                            landUseType.loadSonDataListHtml(item.id);
                        })
                    }
                }
            },
            error: function (result) {
                AlertError("失败", "调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })

    }

</script>


