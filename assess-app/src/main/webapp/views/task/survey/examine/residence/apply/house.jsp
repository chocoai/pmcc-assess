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

    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">户型选择<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select class="form-control search-select select2 huxingId" name="huxingId" required="required">
                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">户型图<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <div class="house_latest_family_plan"></div>
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
                <textarea class="form-control" name="description">
                    ${surveyExamineDataInfoVo.examineHouseVo.description}
                </textarea>
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
<script type="text/javascript">
    $(function () {
        ContainerFunForValid.push(ExamineHouse.valid);//数据验证方法写入容器
        ContainerFunForGetData.push(ExamineHouse.getFormData);//获取数据方法写入容器
        ContainerFunForInit.house.push(houseFun.init);//初始化方法写入容器
        ContainerFunForInit.house.push(houseFun.select2Init);//初始化方法写入容器
        ContainerFunForInit.house.push(houseFun.files);//初始化方法写入容器
    });
</script>

<script type="text/javascript">
    (function () {
        var houseFun = function () {
        };
        houseFun.prototype = {
            select2Init: function () {
                //页面保存数据后 展示数据
                houseFun.prototype.select2InitMethodWrite("${surveyExamineDataInfoVo.examineHouseVo.huxingId}", "huxingId");
                houseFun.prototype.select2InitMethodWrite("${surveyExamineDataInfoVo.examineHouseVo.newsHuxing}", "newsHuxing");
                houseFun.prototype.select2InitMethodWrite("${surveyExamineDataInfoVo.examineHouseVo.certUse}", "certUse");
                houseFun.prototype.select2InitMethodWrite("${surveyExamineDataInfoVo.examineHouseVo.practicalUse}", "practicalUse");
                var id = "${surveyExamineDataInfoVo.examineHouseVo.huxingId}";
                if (houseFun.prototype.select2IsNotNull(id)) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/examineUnitHuxing/getExamineUnitHuxingById",
                        dataType: "JSON",
                        data: {'id': id},
                        type: "GET",
                        success: function (result) {
                            if (result.ret) {
                                var data = result.data;
                                if (houseFun.prototype.select2IsNotNull(data)) {
                                    $("#" + houseFun.prototype.config().frm + " .house_latest_family_plan").html(data.fileViewName);
                                }
                            }
                        },
                        error: function (e) {
                            Alert("调用服务端方法失败，失败原因:" + e);
                        }
                    });
                }
            },
            select2InitMethodWrite: function (data, name) {
                if (houseFun.prototype.select2IsNotNull(data)) {
                    if (houseFun.prototype.select2IsNotNull(name)) {
                        $("#" + houseFun.prototype.config().frm + " ." + name).val(data).trigger("change");
                    }
                } else {
                    if (houseFun.prototype.select2IsNotNull(name)) {
                        $("#" + houseFun.prototype.config().frm + " ." + name).val(null).trigger("change");
                    }
                }
            },
            select2IsNotNull: function (data) {
                if (data == null) {
                    return false;
                }
                if (data == '') {
                    return false;
                }
                if (data == "") {
                    return false;
                }
                if (data == 0) {
                    return false;
                }
                return true;
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
            init: function () {
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, "${surveyExamineDataInfoVo.examineHouseVo.certUse}", function (html,data) {
                    $("#" + houseFun.prototype.config().frm + " .certUse").html(html);
                    $("#" + houseFun.prototype.config().frm + " .certUse").select2();//加载样式
                })
                AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePracticalUse, "${surveyExamineDataInfoVo.examineHouseVo.practicalUse}", function (html,data) {
                    $("#" + houseFun.prototype.config().frm + " .practicalUse").html(html);
                    $("#" + houseFun.prototype.config().frm + " .practicalUse").select2();//加载样式
                })

                $.ajax({
                    url: "${pageContext.request.contextPath}/examineHouse/examine_house_newshuxing",
                    type: "get",
                    async: false,
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            var gradeNum = data.length;
                            var option = "<option value=''>请选择</option>";
                            if (gradeNum > 0) {
                                for (var i = 0; i < gradeNum; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#" + houseFun.prototype.config().frm + " .newsHuxing").html(option);
                                // $("#" + houseFun.prototype.config().frm + " .newsHuxing").select2({minimumResultsForSearch: -1});//加载样式
                                $("#" + houseFun.prototype.config().frm + " .newsHuxing").select2();//加载样式
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineHouse/examineunithuxingSelect",
                    type: "get",
                    dataType: "json",
                    async: false,
                    data: {
                        declareId: $("#declareId").val(),
                        examineType: $("#examineType").val()
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
                                    // $("#" + houseFun.prototype.config().frm + " .huxingId").select2({minimumResultsForSearch: -1});//加载样式
                                    $("#" + houseFun.prototype.config().frm + " .huxingId").select2();//加载样式
                                }
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            },
            init2: function () {
                $("#" + houseFun.prototype.config().frm + " .huxingId").change(function () {
                    var id = $("#" + houseFun.prototype.config().frm + " .huxingId").eq(1).val();
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
                                }
                            },
                            error: function (e) {
                                Alert("调用服务端方法失败，失败原因:" + e);
                            }
                        });
                    }
                });
            },
            files: function () {
                //最新户型图
                FileUtils.uploadFiles({
                    target: houseFun.prototype.config().houseNewLatestFamilyPlan,
                    disabledTarget: "btn_submit",
                    formData: {
                        fieldsName: houseFun.prototype.config().houseNewLatestFamilyPlan,
                        tableName: houseFun.prototype.config().database_Table,
                        tableId: ${empty surveyExamineDataInfoVo.examineHouseVo?0:surveyExamineDataInfoVo.examineHouseVo.id},
                        projectId: 0,
                        creater: "${currUserAccount}"
                    },
                    deleteFlag: true
                });
                FileUtils.getFileShows({
                    target: houseFun.prototype.config().houseNewLatestFamilyPlan,
                    formData: {
                        fieldsName: houseFun.prototype.config().houseNewLatestFamilyPlan,
                        tableName: houseFun.prototype.config().database_Table,
                        tableId: ${empty surveyExamineDataInfoVo.examineHouseVo?0:surveyExamineDataInfoVo.examineHouseVo.id},
                        projectId: 0,
                        creater: "${currUserAccount}"
                    },
                    deleteFlag: true
                })

                //房屋平面图
                FileUtils.uploadFiles({
                    target: houseFun.prototype.config().houseHousePlan,
                    disabledTarget: "btn_submit",
                    formData: {
                        fieldsName: houseFun.prototype.config().houseHousePlan,
                        tableName: houseFun.prototype.config().database_Table,
                        tableId: ${empty surveyExamineDataInfoVo.examineHouseVo?0:surveyExamineDataInfoVo.examineHouseVo.id},
                        projectId: 0,
                        creater: "${currUserAccount}"
                    },
                    deleteFlag: true
                });
                FileUtils.getFileShows({
                    target: houseFun.prototype.config().houseHousePlan,
                    formData: {
                        fieldsName: houseFun.prototype.config().houseHousePlan,
                        tableName: houseFun.prototype.config().database_Table,
                        tableId: ${empty surveyExamineDataInfoVo.examineHouseVo?0:surveyExamineDataInfoVo.examineHouseVo.id},
                        projectId: 0,
                        creater: "${currUserAccount}"
                    },
                    deleteFlag: true
                })
            }
        };
        window.houseFun=new houseFun();
    })()
</script>
<script type="text/javascript">
    (function ($) {
        //避免方法重复，定义全局变量
        var examineHouse = {
            //验证
            valid: function () {
                var options={
                    msg:"请检查【房屋】填写的信息",
                    hiddenValid:true
                };
                return $("#" + houseFun.config().frm).valid(options);
            },

            //获取需要保存的数据
            getFormData: function () {
                var data = formParams(houseFun.config().frm);
                data.declareId = $("#declareId").val();
                data.examineType = $("#examineType").val();
                var keyValueDto = {};
                keyValueDto.key = $("#" + houseFun.config().frm).find('[data-name="fieldName"]').val();
                keyValueDto.value = data;
                return keyValueDto;
            }
        };
        window.ExamineHouse = examineHouse;
    })(jQuery)
</script>