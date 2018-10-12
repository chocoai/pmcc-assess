<%--
  Created by IntelliJ IDEA.
  User: 13426
  Date: 2018/8/17
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 假设开发法 -->
<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2>假设开发法</h2>
        <div class="clearfix"></div>
    </div>

    <div class="x_content optionsBuildBox">
        <div>
            <!-- 隐藏框数据 -->
            <input type="hidden" id="mdCostAndDevelopmentOtherHypothesisJSON"
                   name="mdCostAndDevelopmentOtherHypothesisJSON" value='${mdCostAndDevelopmentOtherHypothesisJSON}'>
            <input type="hidden" id="mdCostAndDevelopmentOtherArchitecturalJSON"
                   name="mdCostAndDevelopmentOtherArchitecturalJSON"
                   value='${mdCostAndDevelopmentOtherArchitecturalJSON}'>
            <input type="hidden" id="mdDevelopmentHypothesisJSON" name="mdDevelopmentHypothesisJSON"
                   value='${mdDevelopmentHypothesisJSON}'>
            <input type="hidden" id="mdDevelopmentArchitecturalJSON" name="mdDevelopmentArchitecturalJSON"
                   value='${mdDevelopmentArchitecturalJSON}'>
        </div>
        <div class="col-sm-12 form-group">
            <span class="col-sm-1">
                <label>经营方式</label><span class="symbol required"></span>
            </span>
            <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                 <input type="radio" name="development" value="1" checked="checked">
                <label>土地</label>
            </span>
            <span class="col-sm-2  checkbox-inline">
                <input type="radio" name="development" value="2">
                <label>在建工程</label>
            </span>
        </div>
        <div class="col-sm-12 form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    项目建设期(年)
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="项目建设期(年)"
                           class="form-control" name="projectConstructionPeriod" id="projectConstructionPeriod">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    已开发时间(年)
                </label>
                <div class="col-sm-3">
                    <input type="text"
                           placeholder="已开发时间(年)"
                           class="form-control" name="developedTime" id="developedTime">
                </div>
            </div>
        </div>
    </div>

</div>

<div class="landEngineering">
    <jsp:include page="../method/module/developmentModule/landEngineering.jsp"></jsp:include>
    <!-- 主题内容 -->
</div>
<div class="architecturalEngineering" style="display: none;">
    <jsp:include page="../method/module/developmentModule/underConstruction.jsp"></jsp:include>
    <!-- 主题内容 -->
</div>

<script>
    var AlgorithmsPrototype = function () {

    };
    AlgorithmsPrototype.prototype.isNumber = function (obj) {
        if (obj == 0) {
            return true;
        }
        if (obj == '0') {
            return true;
        }
        if (AlgorithmsPrototype.prototype.isNotNull(obj)) {
            var regPos = /^\d+(\.\d+)?$/; //非负浮点数
            var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
            if (regPos.test(obj) || regNeg.test(obj)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    };

    /**
     * @author:  zch
     * 描述:判断是否为null
     * @date:
     **/
    AlgorithmsPrototype.prototype.isNotNull = function (obj) {
        if (obj == null) {
            return false;
        }
        if (obj == '') {
            return false;
        }
        if (obj == "") {
            return false;
        }
        if (obj == 'undefined') {
            return false;
        }
        return true;
    };



    var optionsBuildBox = new Object();
    optionsBuildBox.showHypothesisDevelopment = function () {
        $(".landEngineering").show();
        $(".architecturalEngineering").hide();
    };
    optionsBuildBox.showArchitecturalEngineering = function () {
        $(".architecturalEngineering").show();
        $(".landEngineering").hide();
    };

    /**
     * @author:  zch
     * 描述:选项卡初始化载入
     * @date:2018-08-17
     **/
    optionsBuildBox.tabControl = function () {
        $(".optionsBuildBox input[type='radio'][name='development']").change(function () {
            var val = $(".optionsBuildBox :radio:checked").val();
            if (val == 1) {
                optionsBuildBox.showHypothesisDevelopment();
            }
            if (val == 2) {
                optionsBuildBox.showArchitecturalEngineering();
            }
        });
    };

    //初始化在建工程
    optionsBuildBox.hypothesisDevelopmentInit = function (data) {
        underConstruction.initForm(data);
    };

    //初始化 土地
    optionsBuildBox.architecturalEngineeringInit = function (data) {
        landEngineering.initForm(data);
    };
    /**
     * @author:  zch
     * 描述:获取 在建工程
     * @date:2018-08-17
     **/
    optionsBuildBox.getHypothesisDevelopment = function () {//getDevelopment
        var jsonParams = underConstruction.formParams();
        jsonParams.id = "${mdDevelopmentHypothesis.id}";//确保修改能成功
        return jsonParams;
    };

    /**
     * @author:  zch
     * 描述:获取 土地
     * @date:2018-08-17
     **/
    optionsBuildBox.getArchitecturalEngineering = function () {
        var jsonParams = landEngineering.formParams();
        jsonParams.id = "${mdDevelopmentArchitectural.id}";//确保修改能成功
        return jsonParams;
    };

    optionsBuildBox.updateInit = function () {
        var mdDevelopmentHypothesis = "${mdDevelopmentHypothesis}";
        var mdDevelopmentArchitectural = "${mdDevelopmentArchitectural}";

        if (AlgorithmsPrototype.prototype.isNotNull(mdDevelopmentArchitectural)) {
            mdDevelopmentArchitectural = $("#mdDevelopmentArchitecturalJSON").val();
            try {
                mdDevelopmentArchitectural = JSON.parse(mdDevelopmentArchitectural);
                optionsBuildBox.architecturalEngineeringInit(mdDevelopmentArchitectural);
                //设置从表数据
                var mdCostAndDevelopmentOtherArchitecturalJSON = "${mdCostAndDevelopmentOtherArchitectural}";
                if (AlgorithmsPrototype.prototype.isNotNull(mdCostAndDevelopmentOtherArchitecturalJSON)) {
                    try {
                        mdCostAndDevelopmentOtherArchitecturalJSON = $("#mdCostAndDevelopmentOtherArchitecturalJSON").val();
                        mdCostAndDevelopmentOtherArchitecturalJSON = JSON.parse(mdCostAndDevelopmentOtherArchitecturalJSON);
                        landEngineeringDevelopment.setServerData(mdCostAndDevelopmentOtherArchitecturalJSON);
                    } catch (e) {
                        console.log("设置从表数据 失败!");
                    }
                }

            } catch (e) {
                console.log("json parse 失败!")
            }
        }

        if (AlgorithmsPrototype.prototype.isNotNull(mdDevelopmentHypothesis)) {
            mdDevelopmentHypothesis = $("#mdDevelopmentHypothesisJSON").val();
            try {
                mdDevelopmentHypothesis = JSON.parse(mdDevelopmentHypothesis);
                optionsBuildBox.hypothesisDevelopmentInit(mdDevelopmentHypothesis);
                //设置从表数据
                var mdCostAndDevelopmentOtherHypothesisJSON = "${mdCostAndDevelopmentOtherHypothesis}";
                if (AlgorithmsPrototype.prototype.isNotNull(mdCostAndDevelopmentOtherHypothesisJSON)) {
                    try {
                        mdCostAndDevelopmentOtherHypothesisJSON = $("#mdCostAndDevelopmentOtherHypothesisJSON").val();
                        mdCostAndDevelopmentOtherHypothesisJSON = JSON.parse(mdCostAndDevelopmentOtherHypothesisJSON);
                        underDevelopment.setServerData(mdCostAndDevelopmentOtherHypothesisJSON);
                    } catch (e) {
                        console.log("设置从表数据 失败!");
                    }
                }
            } catch (e) {
                console.log("json parse 失败!")
            }
        }
    };

    $(function () {
        optionsBuildBox.tabControl();
        optionsBuildBox.updateInit();
    });
</script>

