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
                <input type="text" data-rule-maxlength="100" placeholder="房号"
                       value="${surveyExamineDataInfoVo.examineHouseVo.houseNumber}" name="houseNumber"
                       class="form-control">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">所在楼层<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" data-rule-number='true' placeholder="所在楼层(请输入数字)"
                       value="${surveyExamineDataInfoVo.examineHouseVo.houseNumber}" name="floor"
                       class="form-control">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">朝向<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" placeholder="朝向"
                       value="${surveyExamineDataInfoVo.examineHouseVo.orientation}" name="orientation"
                       class="form-control">
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">户型选择<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <div class="input-group">
                    <select class="form-control huxingId" name="huxingId" required="required">
                    </select>
                    <label class="input-group-addon btn" onclick="houseFun.huXinSelectRefresh();">刷新户型<i
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
                <input type="text" data-rule-maxlength="100" placeholder="权益限制"
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
                <textarea class="form-control" name="description" required="required">
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
    });
</script>

<script type="text/javascript">
    var HouseFun = function () {

    };
    HouseFun.prototype.config = {
        getFrm: function () {
            return "frm_house";
        },
        //下面根据字段 ExamineFileUpLoadFieldEnum 配置而来 (假如修改必须在此中更新)
        getDatabase_Table: function () {
            return AssessDBKey.ExamineHouse;
        },
        getHouseNewLatestFamilyPlan: function () {
            return "house_new_latest_family_plan";//最新户型图id和字段
        },
        //房屋平面图id和字段
        getHouseHousePlan:function () {
            return "house_house_plan" ; //房屋平面图id和字段
        },
        isEmpty:function (item) {
            if (item){
                return true;
            }
            return false;
        },
        select2InitMethodWrite:function (data, name) {
            if (houseFun.config.isEmpty(data)){
                if (houseFun.config.isEmpty(name)){
                    $("#"+houseFun.config.getFrm()+" ."+name).val(data).trigger("change");
                }
            }else {
                if (houseFun.config.isEmpty(name)){
                    $("#"+houseFun.config.getFrm()+" ."+name).val(null).trigger("change");
                }
            }
        },
        rule:function (item) {
            var text = "";
            if (houseFun.config.isEmpty(item.house)) {
                text += item.house + "房-";
            }
            if (houseFun.config.isEmpty(item.saloon)) {
                text += item.saloon + "客厅-";
            }
            if (houseFun.config.isEmpty(item.kitchen)) {
                text += item.kitchen + "厨房-";
            }
            if (houseFun.config.isEmpty(item.toilet)) {
                text += item.toilet + "卫生间-";
            }
            if (houseFun.config.isEmpty(item.garden)) {
                text += item.garden + "花园-";
            }
            if (houseFun.config.isEmpty(item.balcony)) {
                text += item.balcony + "阳台";
            }
            return text;
        }
    }
    var houseFun = new HouseFun();
    houseFun.init = function () {
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, null, function (html, data) {
            $("#" + houseFun.config.getFrm()).find("select.certUse").html(html);
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHousePracticalUse, null, function (html, data) {
            $("#" + houseFun.config.getFrm()).find("select.practicalUse").html(html);
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseNewsHuxing, null, function (html, data) {
            $("#" + houseFun.config.getFrm()).find("select.newsHuxing").html(html);
        });
        houseFun.files.init();
        houseFun.huXinSelect();
        houseFun.changeEvent();
    };
    houseFun.huXinSelect = function () {
        $.ajax({
            url: "${pageContext.request.contextPath}/examineUnitHuxing/examineunithuxingSelect",
            type: "get",
            dataType: "json",
            async: false,
            data: {
                declareId: $("#declareId").val(),
                examineType: $("#examineType").val(),
                planDetailsId : $("#planDetailsId").val()
            },
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var gradeNum = data.length;
                    var option = "<option value=''>请选择</option>";
                    if (gradeNum > 0) {
                        for (var i = 0; i < gradeNum; i++) {
                            option += "<option value='" + data[i].id + "'>" + houseFun.config.rule(JSON.parse(data[i].houseCategory)) + "</option>";
                        }
                        if ($("#" + houseFun.config.getFrm() + " .huxingId").size() > 0) {
                            $("#" + houseFun.config.getFrm() + " .huxingId").html(option);
                        }
                    }
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };
    houseFun.huXinSelectRefresh = function () {
        $("#" + houseFun.config.getFrm() + " .huxingId").empty();
        houseFun.huXinSelect();
    };
    houseFun.changeEvent = function () {
        $("#" + houseFun.config.getFrm() + " .huxingId").change(function () {
            var id = $("#" + houseFun.config.getFrm() + " .huxingId option:selected").val();
            if (houseFun.config.isEmpty(id)) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineUnitHuxing/getExamineUnitHuxingById",
                    dataType: "JSON",
                    data: {'id': id},
                    type: "GET",
                    success: function (result) {
                        if (result.ret) {
                            var data = result.data;
                            $("#" + houseFun.config.getFrm() + " .house_latest_family_plan").html(data.fileViewName);
                            $("#" + houseFun.config.getFrm() + " input[name='orientation']").val(data.orientation) ;
                        }
                    },
                    error: function (e) {
                        Alert("调用服务端方法失败，失败原因:" + e);
                    }
                });
            }
        });
    };
    houseFun.files  = {
        init:function () {
            //最新户型图
            houseFun.files.uploadFilesModel(houseFun.config.getHouseNewLatestFamilyPlan());
            houseFun.files.getFileShowsModel(houseFun.config.getHouseNewLatestFamilyPlan());


            //房屋平面图
            houseFun.files.uploadFilesModel(houseFun.config.getHouseHousePlan());
            houseFun.files.getFileShowsModel(houseFun.config.getHouseHousePlan());
        },
        uploadFilesModel:function (target) {
            FileUtils.uploadFiles({
                target: target,
                disabledTarget: "btn_submit",
                formData: {
                    fieldsName: target,
                    tableName: houseFun.config.getDatabase_Table(),
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
                    tableName: houseFun.config.getDatabase_Table(),
                    tableId: ${empty surveyExamineDataInfoVo.examineHouseVo?0:surveyExamineDataInfoVo.examineHouseVo.id},
                    projectId: 0,
                    creater: "${currUserAccount}"
                },
                deleteFlag: true
            })
        }
    }

</script>
<script type="text/javascript">
    (function ($) {
        //避免方法重复，定义全局变量
        var examineHouse = {
            //验证
            valid: function () {
                var options = {
                    msg: "请检查【房屋】填写的信息",
                    hiddenValid: true
                };
                return $("#" + houseFun.config.getFrm()).valid(options);
            },

            //获取需要保存的数据
            getFormData: function () {
                var data = formParams(houseFun.config.getFrm());
                data.declareId = $("#declareId").val();
                data.planDetailsId = $("#planDetailsId").val();
                data.examineType = $("#examineType").val();
                var keyValueDto = {};
                keyValueDto.key = $("#" + houseFun.config.getFrm()).find('[data-name="fieldName"]').val();
                keyValueDto.value = data;
                return keyValueDto;
            }
        };
        window.ExamineHouse = examineHouse;
    })(jQuery)
</script>