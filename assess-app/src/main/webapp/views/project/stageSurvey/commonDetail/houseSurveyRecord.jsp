<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-8-10
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
 查勘记录表
--%>
<!DOCTYPE html>
<div class="col-md-12"  tab-role="method">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    查勘记录表
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
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                上传查勘记录
                            </label>
                            <div class="col-xs-6  col-sm-6  col-md-6  col-lg-6">
                                <div id="_estateSurveyRecordFileId"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <%--<table class="table table-bordered" id="HouseHuxingPriceList">--%>
                    <%--<!-- cerare document add ajax data-->--%>
                <%--</table>--%>
            </form>
        </div>
    </div>
</div>


</html>
<script>
    $(document).ready(function () {
        (function (fieldsName) {
            FileUtils.getFileShows({
                target: fieldsName,
                formData: {
                    fieldsName: fieldsName,
                    tableName: AssessDBKey.BasicEstateSurveyRecord,
                    tableId: "${basicEstateSurveyRecord == null?'0':basicEstateSurveyRecord.id}"
                },
                deleteFlag: false
            }) ;
        }('estateSurveyRecordFileId')) ;
    }) ;
</script>

