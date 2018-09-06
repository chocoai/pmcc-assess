<%--
  房屋基本新信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="frm_house" class="form-horizontal">
    <input type="hidden" data-name="fieldName" value="<%=request.getParameter("fieldName")%>">
    <input type="hidden" name="id" value="${surveyExamineDataInfoVo.examineHouseVo.id}">

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">房号<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="房号" required="required"
                       value="${surveyExamineDataInfoVo.examineHouseVo.houseNumber}" name="houseNumber"
                       class="form-control">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">所在楼层<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" data-rule-number='true' placeholder="所在楼层(请输入数字)"
                       required="required"
                       value="${surveyExamineDataInfoVo.examineHouseVo.houseNumber}" name="floor"
                       class="form-control">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">使用环境<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select class="form-control search-select select2 useEnvironment" name="useEnvironment"
                        required="required">
                </select>
            </div>
        </div>

    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">房型选择<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <div class="input-group">
                    <select class="form-control huxingId" name="huxingId" required="required">
                    </select>
                    <label class="input-group-addon btn" onclick="houseFun.prototype.huXinSelectRefresh();">刷新户型<i
                            class="fa fa-refresh"></i></label>
                </div>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">户型图<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <div class="house_latest_family_plan"></div>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">朝向<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" placeholder="朝向" readonly="readonly"
                       value="${surveyExamineDataInfoVo.examineHouseVo.orientation}" name="orientation"
                       class="form-control">
            </div>
        </div>
    </div>


    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">证载用途<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select class="form-control search-select select2 certUse" name="certUse" required="required">
                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">实际用途<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select class="form-control search-select select2 practicalUse" name="practicalUse" required="required">
                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">权益限制<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="权益限制" required="required"
                       value="${surveyExamineDataInfoVo.examineHouseVo.rightInterestsRestriction}"
                       name="rightInterestsRestriction"
                       class="form-control">
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">房屋出租占用情况途描述<span class="symbol required"></span></label>
            <div class="col-sm-11">
                <textarea class="form-control"
                          name="description">${surveyExamineDataInfoVo.examineHouseVo.description}</textarea>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">最新户型<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select class="form-control search-select select2 newsHuxing" name="newsHuxing" required="required">
                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">最新户型图<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <input id="house_new_latest_family_plan"
                       required="required" placeholder="上传附件" class="form-control" type="file">
                <div id="_house_new_latest_family_plan"></div>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">房屋平面图<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <input id="house_house_plan"
                       required="required" placeholder="上传附件" class="form-control" type="file">
                <div id="_house_house_plan"></div>
            </div>
        </div>
    </div>
</form>
<script>
    $(function () {
        ContainerFunForValid.push(ExamineHouse.valid);//数据验证方法写入容器
        ContainerFunForGetData.push(ExamineHouse.getFormData);//获取数据方法写入容器
        ContainerFunForInit.house.push(houseFun.prototype.init);//初始化方法写入容器
    });
</script>

<script type="text/javascript">
    var houseFun;
    (function () {
        houseFun = function () {

        };
        houseFun.prototype = {
            select2InitMethodWrite: function (data, name) {
                if (houseFun.prototype.isEmpty(data)) {
                    if (houseFun.prototype.isEmpty(name)) {
                        $("#" + houseFun.prototype.config().frm + " ." + name).val(data).trigger("change");
                    }
                } else {
                    if (houseFun.prototype.isEmpty(name)) {
                        $("#" + houseFun.prototype.config().frm + " ." + name).val(null).trigger("change");
                    }
                }
            },
            isEmpty: function (data) {
                if (data) {
                    return true;
                }
                return false;
            },
            config: function () {
                return {
                    frm: "frm_house",
                    database_Table: AssessDBKey.ExamineHouse,
                    //下面根据字段 ExamineFileUpLoadFieldEnum 配置而来 (假如修改必须在此中更新)
                    houseNewLatestFamilyPlan: "house_new_latest_family_plan",//最新户型图id和字段
                    houseHousePlan: "house_house_plan" //房屋平面图id和字段
                };
            },
            huXinSelectRefresh: function () {
                $("#" + houseFun.prototype.config().frm + " .huxingId").empty();
                houseFun.prototype.examineunithuxingSelect();
            },
            examineunithuxingSelect: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineUnitHuxing/examineunithuxingSelect",
                    type: "get",
                    dataType: "json",
                    async: false,
                    data: {
                        declareId: $("#declareId").val(),
                        examineType: $("#examineType").val(),
                        planDetailsId: $("#planDetailsId").val()
                    },
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if (gradeNum > 0) {
                                for (var i = 0; i < gradeNum; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].houseLayoutName + "</option>";
                                }
                                if ($("#" + houseFun.prototype.config().frm + " .huxingId").size() > 0) {
                                    $("#" + houseFun.prototype.config().frm + " .huxingId").html(option);
                                }
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            },
            init: function () {
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, "", function (html, data) {
                    $("#" + houseFun.prototype.config().frm + " .certUse").html(html);
                    $("#" + houseFun.prototype.config().frm + " .certUse").select2();//加载样式
                })
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePracticalUse, "", function (html, data) {
                    $("#" + houseFun.prototype.config().frm + " .practicalUse").html(html);
                    $("#" + houseFun.prototype.config().frm + " .practicalUse").select2();//加载样式
                })
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseEnvironmentUse, "", function (html, data) {
                    $("#" + houseFun.prototype.config().frm + " .useEnvironment").html(html);
                    $("#" + houseFun.prototype.config().frm + " .useEnvironment").select2();//加载样式
                })
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseNewsHuxing, "", function (html, data) {
                    $("#" + houseFun.prototype.config().frm + " .newsHuxing").html(html);
                    $("#" + houseFun.prototype.config().frm + " .newsHuxing").select2();//加载样式
                })
                houseFun.prototype.examineunithuxingSelect();
                houseFun.prototype.changeEvent();
                houseFun.prototype.files.init();
            },
            changeEvent: function () {
                $("#" + houseFun.prototype.config().frm + " .huxingId").change(function () {
                    var id = $("#" + houseFun.prototype.config().frm + " .huxingId option:selected").val();
                    // 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                    if (id != null && id != '') {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineUnitHuxing/getExamineUnitHuxingById",
                            dataType: "JSON",
                            data: {'id': id},
                            type: "GET",
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    $("#" + houseFun.prototype.config().frm + " .house_latest_family_plan").html(data.fileViewName);
                                    $("#" + houseFun.prototype.config().frm + " input[name='orientation']").val(data.orientation);
                                }
                            },
                            error: function (e) {
                                Alert("调用服务端方法失败，失败原因:" + e);
                            }
                        });
                    }
                });
            },
            files: {
                init:function () {
                    houseFun.prototype.files.uploadFilesModel(houseFun.prototype.config().houseHousePlan);
                    houseFun.prototype.files.getFileShowsModel(houseFun.prototype.config().houseHousePlan);

                    houseFun.prototype.files.uploadFilesModel(houseFun.prototype.config().houseNewLatestFamilyPlan);
                    houseFun.prototype.files.getFileShowsModel(houseFun.prototype.config().houseNewLatestFamilyPlan);
                },
                uploadFilesModel:function (target) {
                    FileUtils.uploadFiles({
                        target: target,
                        disabledTarget: "btn_submit",
                        formData: {
                            fieldsName: target,
                            tableName: houseFun.prototype.config().database_Table,
                            tableId: ${empty surveyExamineDataInfoVo.examineHouseVo?0:surveyExamineDataInfoVo.examineHouseVo.id},
                            projectId: 0,
                            creater: "${currUserAccount}"
                        },
                        deleteFlag: true
                    });
                },
                getFileShowsModel:function (target) {
                    FileUtils.getFileShows({
                        target: target,
                        formData: {
                            fieldsName: target,
                            tableName: houseFun.prototype.config().database_Table,
                            tableId: ${empty surveyExamineDataInfoVo.examineHouseVo?0:surveyExamineDataInfoVo.examineHouseVo.id},
                            projectId: 0,
                            creater: "${currUserAccount}"
                        },
                        deleteFlag: true
                    })
                }
            }
        };
    })();

    (function ($) {
        //避免方法重复，定义全局变量
        var examineHouse = {
            //验证
            valid: function () {
                return $("#" + houseFun.prototype.config().frm).valid();
            },

            //获取需要保存的数据
            getFormData: function () {
                var data = formParams(houseFun.prototype.config().frm);
                data.declareId = $("#declareId").val();
                data.planDetailsId = $("#planDetailsId").val();
                data.examineType = $("#examineType").val();
                var keyValueDto = {};
                keyValueDto.key = $("#" + houseFun.prototype.config().frm).find('[data-name="fieldName"]').val();
                keyValueDto.value = data;
                return keyValueDto;
            },
            //文件上传处理
            files: function () {
                //已经移除
            }
        };
        window.ExamineHouse = examineHouse;
    })(jQuery)
</script>