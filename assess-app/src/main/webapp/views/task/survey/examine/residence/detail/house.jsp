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
                       value="${surveyExamineDataInfoVo.examineHouseVo.huxingName}" name="huxingName"
                       class="form-control">
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
                <input type="text" value="${surveyExamineDataInfoVo.examineHouseVo.orientation}" name="orientation" class="form-control" placeholder="朝向" readonly="readonly">
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">最新户型<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="最新户型" required="required" readonly="readonly"
                       value="${surveyExamineDataInfoVo.examineHouseVo.newsHuxingName}" name="newsHuxing"
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
                       value="${surveyExamineDataInfoVo.examineHouseVo.certUseName}" name="certUse"
                       class="form-control">
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">实际用途<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="实际用途" required="required" readonly="readonly"
                       value="${surveyExamineDataInfoVo.examineHouseVo.practicalUseName}" name="practicalUse"
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
    $(function () {
        house.init();
    });
</script>

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
        house.showFiles();
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
    };
    house.select2LoadData = function () {

    };
</script>

