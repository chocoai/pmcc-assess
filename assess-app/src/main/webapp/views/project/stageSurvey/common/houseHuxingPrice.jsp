<%--
 单价调查
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    单价调查
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
                <div class="row form-group">
                    <div class="col-md-12">
                        <div class="form-inline form-inline x-valid">
                            <button class="btn btn-success btn-sm" type="button"
                                    data-toggle="modal" onclick="houseHuxingPrice.prototype.showModel()"
                                    href="#divBoxHouseHuxingPrice">
											<span class="btn-label">
												<i class="fa fa-plus"></i>
											</span>
                                新增
                            </button>
                            <button style="margin-left: 5px" class="btn btn-success btn-sm" type="button"
                                    onclick="exportData();">
											<span class="btn-label">
												<i class="fa fa-cloud-download-alt"></i>
											</span>
                                下载模板
                            </button>
                            <button style="margin-left: 5px" class="btn btn-info btn-sm" type="button"
                                    onclick="$('#ajaxFileUpload').val('').trigger('click')">
											<span class="btn-label">
												<i class="fa fa-cloud-upload-alt"></i>
											</span>
                                导入数据
                            </button>
                        </div>
                    </div>
                </div>
                <table class="table table-bordered" id="HouseHuxingPriceList">
                    <!-- cerare document add ajax data-->
                </table>
            </form>
        </div>
    </div>
</div>

<div id="divBoxHouseHuxingPrice" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">单价调查</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmHouseHuxingPrice" class="form-horizontal">
                    <input type="hidden" name="id">
                    <input type="hidden" name="declareName">
                    <input type="hidden" name="declareId">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div style="display: none" class="row form-group common">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                房号
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="房号" name="houseNumber"
                                                       class="form-control input-full">
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                面积
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="面积" name="area" data-rule-number='true'
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group common">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                楼层
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="楼层" name="floor"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--住宅，办公--%>
                                <div style="display: none" class="row form-group residence">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                通风
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="通风" name="aeration"
                                                       class="form-control input-full" required>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                采光
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="采光" name="lighting"
                                                       class="form-control input-full" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group residence">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                日照
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="日照" name="sunshine"
                                                       class="form-control input-full" required>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                隔音
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="隔音" name="soundInsulation"
                                                       class="form-control input-full" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group residence">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                长度
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="长度" name="length"
                                                       class="form-control input-full" required>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                宽度
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="宽度" name="width"
                                                       class="form-control input-full" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%-- 餐饮酒店--%>
                                <div style="display: none;" class="row form-group hotel">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                通风
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="通风" name="aeration"
                                                       class="form-control input-full" required>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                采光
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="采光" name="lighting"
                                                       class="form-control input-full" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group hotel">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                长度
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="长度" name="length"
                                                       class="form-control input-full" required>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                宽度
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="宽度" name="width"
                                                       class="form-control input-full" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%-- 商铺、商场--%>
                                <div style="display: none" class="row form-group store">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                开间
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="开间" name="opening"
                                                       class="form-control input-full" required>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                进深
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="进深" name="depth"
                                                       class="form-control input-full" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group store">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                相邻位置
                                            </label>
                                            <div class="col-sm-4">
                                                <select class="form-control input-full adjacentPosition"
                                                        name="adjacentPosition">
                                                </select>
                                            </div>

                                            <label class="col-sm-2 control-label">
                                                距离(m)
                                            </label>
                                            <div class="col-sm-3">
                                                <input type="text" placeholder="距离" name="distance"
                                                       class="form-control input-full">
                                            </div>
                                            <div class="col-sm-1">
                                                <button class="btn btn-sm btn-success" type="button"
                                                        onclick="houseHuxingPrice.prototype.appendHTML()"><i
                                                        class="fa fa-plus"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="streetNumbers store">

                                </div>
                                <div style="display: none" class="row form-group store">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                方位
                                            </label>
                                            <div class="col-sm-4">
                                                <select class="form-control input-full search-select select2 orientation2"
                                                        name="orientation">
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--生产--%>
                                <div style="display: none" class="row form-group production">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                跨长
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="跨长" name="spanLength"
                                                       class="form-control input-full" required>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                跨数
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="跨数" name="spanNum"
                                                       class="form-control input-full" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group production">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                通风
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="通风" name="aeration"
                                                       class="form-control input-full" required>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                采光
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="采光" name="lighting"
                                                       class="form-control input-full" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group production">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                最大跨距
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="最大跨距" name="maxSpan"
                                                       class="form-control input-full" required>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                最小跨距
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="最小跨距" name="minSpan"
                                                       class="form-control input-full" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="display: none" class="row form-group production">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                标准跨距
                                            </label>
                                            <div class="col-sm-4">
                                                <input type="text" placeholder="标准跨距" name="standardSpan"
                                                       class="form-control input-full" required>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%-- 仓储--%>
                                <div style="display: none" class="row form-group storage">
                                    <div class="col-md-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 control-label">
                                                计量标准
                                            </label>
                                            <div class="col-sm-4">
                                                <select class="form-control input-full search-select select2 standardMeasure2"
                                                        name="standardMeasure">
                                                </select>
                                            </div>
                                            <label class="col-sm-2 control-label">
                                                仓储要求
                                            </label>
                                            <div class="col-sm-4">
                                                <select class="form-control input-full search-select select2 storageRequest2"
                                                        name="storageRequest">
                                                </select>
                                            </div>
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
                <button type="button" class="btn btn-primary btn-sm" onclick="houseHuxingPrice.prototype.saveData()">
                    保存
                </button>
            </div>

        </div>
    </div>
</div>

<input type="file" id="ajaxFileUpload" name="file" style="display: none;"
       onchange="houseHuxingPrice.prototype.importData(${planDetailsId});">
</html>
<script>
    //生成并下载模板
    function exportData() {
        var columns = houseCommon.houseHuxingForm.find("input[name='priceExportColumns']").val();
        var href = "${pageContext.request.contextPath}/basicHouseHuxingPrice/generateAndExport";
        href += "?columns=" + encodeURIComponent(columns);
        window.open(href, "");

    }
</script>
