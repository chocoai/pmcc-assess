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
                <input type="text" data-rule-maxlength="100" placeholder="房号" required="required" readonly="readonly"
                       value="${surveyExamineDataInfoVo.examineHouseVo.houseNumber}" name="houseNumber"
                       class="form-control">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">所在楼层<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" data-rule-number='true' placeholder="所在楼层(请输入数字)"
                       required="required" readonly="readonly"
                       value="${surveyExamineDataInfoVo.examineHouseVo.houseNumber}" name="floor"
                       class="form-control">
            </div>
        </div>

    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">户型选择<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="户型选择" required="required" readonly="readonly"
                       value="${surveyExamineDataInfoVo.examineHouseVo.huxingId}" name="huxingId"
                       class="form-control">
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
                <input type="text" data-rule-maxlength="100" placeholder="最新户型" required="required" readonly="readonly"
                       value="${surveyExamineDataInfoVo.examineHouseVo.newsHuxing}" name="newsHuxing"
                       class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">最新户型图<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <div id="_house_new_latest_family_plan"></div>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">证载用途<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="证载用途" required="required" readonly="readonly"
                       value="${surveyExamineDataInfoVo.examineHouseVo.certUse}" name="certUse"
                       class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">实际用途<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="证载用途" required="required" readonly="readonly"
                       value="${surveyExamineDataInfoVo.examineHouseVo.practicalUse}" name="practicalUse"
                       class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">权益限制<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" readonly="readonly" placeholder="权益限制"
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
                <textarea class="form-control" name="description" readonly="readonly">
                    ${surveyExamineDataInfoVo.examineHouseVo.description}
                </textarea>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">房屋平面图<span class="symbol required"></span></label>
            <div class="col-sm-5">
                <div id="_house_house_plan"></div>
            </div>
        </div>
    </div>
</form>

<script>
    var house_config ;
    (function () {
        var frm = "frm_house" ;
        //下面根据字段 ExamineFileUpLoadFieldEnum 配置而来 (假如修改必须在此中更新)
        var houseNewLatestFamilyPlan = "house_new_latest_family_plan" ;//最新户型图id和字段
        var houseHousePlan = "house_house_plan" ;//房屋平面图id和字段
        var dataBaseName = AssessDBKey.ExamineHouse;
        house_config = {
            getFrm:function () {
                return frm;
            },
            getDataBaseName:function () {
                return dataBaseName;
            },
            getHouseHousePlan:function () {
                return houseHousePlan;
            },
            getHouseNewLatestFamilyPlan:function () {
                return houseNewLatestFamilyPlan;
            }
        }
    })();
    var house = Object.create(house_config);
    house.init = function () {
        house.select2LoadData();
        house.select2InitMethodWrite("${surveyExamineDataInfoVo.examineHouseVo.huxingId}", "huxingId");
        house.select2InitMethodWrite("${surveyExamineDataInfoVo.examineHouseVo.newsHuxing}", "newsHuxing");
        house.select2InitMethodWrite("${surveyExamineDataInfoVo.examineHouseVo.certUse}", "certUse");
        house.select2InitMethodWrite("${surveyExamineDataInfoVo.examineHouseVo.practicalUse}", "practicalUse");
        var id = "${surveyExamineDataInfoVo.examineHouseVo.huxingId}";
        if (house.select2IsNotNull(id)){
            $.ajax({
                url: "${pageContext.request.contextPath}/examineUnitHuxing/getExamineUnitHuxingById",
                dataType: "JSON",
                data: {'id': id},
                type: "GET",
                success: function (result) {
                    if (result.ret) {
                        var data = result.data;
                        if (house.select2IsNotNull(data)){
                            $("#" + house.getFrm() + " .house_latest_family_plan").html(data.fileViewName);
                        }
                    }
                },
                error: function (e) {
                    Alert("调用服务端方法失败，失败原因:" + e);
                }
            });
        }
        house.showFiles();
        $("#" + house.getFrm() + " :input").attr("readonly","readonly");
    };
    house.select2InitMethodWrite = function (data, name) {
        if (house.select2IsNotNull(data)) {
            if (house.select2IsNotNull(name)) {
                $("#" + house.getFrm() + " ." + name).val(data).trigger("change");
            }
        } else {
            $("#" + house.getFrm() + " ." + name).val(null).trigger("change");
        }
    };
    house.select2IsNotNull = function (data) {
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
    };
    house.showFiles = function () {
        //最新户型图
        FileUtils.getFileShows({
            target: house.getHouseNewLatestFamilyPlan(),
            formData: {
                fieldsName: house.getHouseNewLatestFamilyPlan(),
                tableName: house.getDataBaseName(),
                tableId: ${empty surveyExamineDataInfoVo.examineHouseVo?0:surveyExamineDataInfoVo.examineHouseVo.id},
                projectId: 0,
                creater: "${currUserAccount}"
            },
            deleteFlag: false
        });
        //房屋平面图
        FileUtils.getFileShows({
            target: house.getHouseHousePlan(),
            formData: {
                fieldsName: house.getHouseHousePlan(),
                tableName: house.getDataBaseName(),
                tableId: ${empty surveyExamineDataInfoVo.examineHouseVo?0:surveyExamineDataInfoVo.examineHouseVo.id},
                projectId: 0,
                creater: "${currUserAccount}"
            },
            deleteFlag: false
        });
    };
    house.select2ChangeEvent = function () {
        $("#" + house.getFrm() + " .huxingId").change(function () {
            var id = $("#" + houseFun.prototype.config().frm + " .huxingId").eq(1).val();
            // 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
            if (id != null && id!=''){
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
    };
    house.select2LoadData = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/examineHouse/examine_house_practical_use",
            type: "get",
            dataType: "json",
            async:false,
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var gradeNum = data.length;
                    var option = "<option value=''>请选择</option>";
                    if (gradeNum > 0) {
                        for (var i = 0; i < gradeNum; i++) {
                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                        }
                        $("#" + house.getFrm() + " .practicalUse").html(option);
                        $("#" + house.getFrm() + " .practicalUse").select2();//加载样式
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        $.ajax({
            url: "${pageContext.request.contextPath}/examineHouse/examine_house_load_utility",
            type: "get",
            dataType: "json",
            async:false,
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var gradeNum = data.length;
                    var option = "<option value=''>请选择</option>";
                    if (gradeNum > 0) {
                        for (var i = 0; i < gradeNum; i++) {
                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                        }
                        $("#" + house.getFrm() + " .certUse").html(option);
                        $("#" + house.getFrm() + " .certUse").select2();//加载样式
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        $.ajax({
            url: "${pageContext.request.contextPath}/examineHouse/examine_house_newshuxing",
            type: "get",
            async:false,
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
                        $("#" + house.getFrm() + " .newsHuxing").html(option);
                        $("#" + house.getFrm() + " .newsHuxing").select2();//加载样式
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
        $.ajax({
            url: "${pageContext.request.contextPath}/examineHouse/examineunithuxingSelect",
            type: "get",
            dataType: "json",
            async:false,
            data:{
                declareId : $("#declareId").val(),
                examineType : $("#examineType").val()
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
                        if ($("#" + house.getFrm() + " .huxingId").size() > 0) {
                            $("#" + house.getFrm() + " .huxingId").html(option);
                            $("#" + house.getFrm() + " .huxingId").select2();//加载样式
                        }
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    $(function () {
        // house.init();
        house.select2ChangeEvent();
    });
</script>

