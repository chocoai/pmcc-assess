<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2>成本法</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content optionsBuildBox">
        <div>
            <!-- 隐藏框数据 -->
            <input type="hidden" id="mdCostAndDevelopmentOtherConstructionJSON"
                   name="mdCostAndDevelopmentOtherConstructionJSON"
                   value='${mdCostAndDevelopmentOtherConstructionJSON}'>
            <input type="hidden" id="mdCostAndDevelopmentOtherBuildingJSON" name="mdCostAndDevelopmentOtherBuildingJSON"
                   value='${mdCostAndDevelopmentOtherBuildingJSON}'>
            <input type="hidden" id="mdCostBuildingJSON" name="mdCostBuildingJSON" value='${mdCostBuildingJSON}'>
            <input type="hidden" id="mdCostConstructionJSON" name="mdCostConstructionJSON"
                   value='${mdCostConstructionJSON}'>
        </div>
        <div class="col-sm-12 form-group">
            <span class="col-sm-1">
                <label>建筑形态</label>
            </span>
            <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                 <input type="radio" name="building" value="1" checked="checked">
                <label>建筑物</label>
            </span>
            <span class="col-sm-2  checkbox-inline">
                <input type="radio" name="building" value="2">
                <label>在建工程</label>
            </span>
        </div>
    </div>
</div>
<div class="building">
    <!-- 主题内容 -->
    <jsp:include page="module/costModule/building.jsp"></jsp:include>
</div>
<div class="construction" style="display: none;">
    <!-- 主题内容 -->
    <jsp:include page="module/costModule/construction.jsp"></jsp:include>
</div>
<script type="text/javascript">
    var AlgorithmsPrototype = function () {

    };

    /**
     * @author:  zch
     * 描述:判断是否为数字
     * @date:
     **/
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
    optionsBuildBox.showBuilding = function () {
        $(".building").show();
        $(".construction").hide();
    };
    optionsBuildBox.showConstruction = function () {
        $(".construction").show();
        $(".building").hide();
    };
    /**
     * @author:  zch
     * 描述:选项卡初始化载入
     * @date:
     **/
    optionsBuildBox.tabControl = function () {
        $(".optionsBuildBox input[type='radio'][name='building']").change(function () {
            var val = $(".optionsBuildBox :radio:checked").val();
            if (val == 1) {
                optionsBuildBox.showBuilding();
            }
            if (val == 2) {
                optionsBuildBox.showConstruction();
            }
        });
    };

    optionsBuildBox.mdCostBuildingInit = function (data) {
        build.initForm(data);
    };
    optionsBuildBox.mdCostConstructionInit = function (data) {
        construction.initForm(data);
    };
    /**
     * @author:  zch
     * 描述:处理页面更新时
     * @date:
     **/
    optionsBuildBox.updateInit = function () {
        var mdCostBuilding = "${mdCostBuilding}";
        var mdCostConstruction = "${mdCostConstruction}";
        if (AlgorithmsPrototype.prototype.isNotNull(mdCostBuilding)) {
            try {
                mdCostBuilding = $("#mdCostBuildingJSON").val();
                mdCostBuilding = JSON.parse(mdCostBuilding);
                console.log(mdCostBuilding);
                //初始化数据
                optionsBuildBox.mdCostBuildingInit(mdCostBuilding);
                var mdCostAndDevelopmentOtherBuildingJSON = "${mdCostAndDevelopmentOtherBuilding}";
                if (AlgorithmsPrototype.prototype.isNotNull(mdCostAndDevelopmentOtherBuildingJSON)) {
                    mdCostAndDevelopmentOtherBuildingJSON = $("#mdCostAndDevelopmentOtherBuildingJSON").val();
                    mdCostAndDevelopmentOtherBuildingJSON = JSON.parse(mdCostAndDevelopmentOtherBuildingJSON);
                    constructEngineeringObject.setServerData(mdCostAndDevelopmentOtherBuildingJSON);
                }
            } catch (e) {
                console.log("json parse 失败!")
            }
        }
        if (AlgorithmsPrototype.prototype.isNotNull(mdCostConstruction)) {
            try {
                mdCostConstruction = $("#mdCostConstructionJSON").val();
                mdCostConstruction = JSON.parse(mdCostConstruction);
                console.log(mdCostConstruction);
                //初始化数据
                optionsBuildBox.mdCostConstructionInit(mdCostConstruction);
                var mdCostAndDevelopmentOtherConstructionJSON = "${mdCostAndDevelopmentOtherConstruction}";
                if (AlgorithmsPrototype.prototype.isNotNull(mdCostAndDevelopmentOtherConstructionJSON)) {
                    mdCostAndDevelopmentOtherConstructionJSON = $("#mdCostAndDevelopmentOtherConstructionJSON").val();
                    mdCostAndDevelopmentOtherConstructionJSON = JSON.parse(mdCostAndDevelopmentOtherConstructionJSON);
                    constructEngineeringObjectA.setServerData(mdCostAndDevelopmentOtherConstructionJSON);
                }
            } catch (e) {
                console.log("json parse 失败!")
            }
        }
    }

    optionsBuildBox.getMdCostBuilding = function () {
        var jsonParams = build.formParams();
        jsonParams.id = "${mdCostBuilding.id}";//确保修改能成功
        return jsonParams;
    };
    optionsBuildBox.getMdCostConstruction = function () {
        var jsonParams = construction.formParams();
        jsonParams.id = "${mdCostConstruction.id}";//确保修改能成功
        return jsonParams;
    };
    optionsBuildBox.getBuildKey = function () {
        var val = $(".optionsBuildBox :radio:checked").val();
        if (val == 1) {
            return "mdCostBuilding";
        }
        if (val == 2) {
            return "mdCostConstruction";
        }
    };

    $(function () {
        optionsBuildBox.tabControl();
        optionsBuildBox.updateInit();
    });
</script>

