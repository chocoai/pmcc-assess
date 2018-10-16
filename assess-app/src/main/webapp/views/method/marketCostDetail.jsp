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
            <input type="hidden" id="mdCostBuildingJSON" name="mdCostBuildingJSON" value='${mdCostBuildingJSON}'>
            <input type="hidden" id="mdCostConstructionJSON" name="mdCostConstructionJSON" value='${mdCostConstructionJSON}'>
            <input type="hidden" name="area" class="mdCost area" value="20"><!-- 委估对象面积 -->
            <input type="hidden" name="price" class="mdCost price" value="22.5"><!-- 委估对象价格 -->
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
    <jsp:include page="module/costModule/buildingDetail.jsp"></jsp:include>
</div>
<div class="construction" style="display: none;">
    <!-- 主题内容 -->
    <jsp:include page="module/costModule/constructionDetail.jsp"></jsp:include>
</div>
<script type="text/javascript">
    var AlgorithmsPrototype = function () {

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
    optionsBuildBox.detailInit = function () {
        var mdCostBuilding = "${mdCostBuilding}";
        var mdCostConstruction = "${mdCostConstruction}";
        if (AlgorithmsPrototype.prototype.isNotNull(mdCostBuilding)) {
            // optionsBuildBox.showBuilding();
            try {
                mdCostBuilding = $("#mdCostBuildingJSON").val();
                mdCostBuilding = JSON.parse(mdCostBuilding);
                optionsBuildBox.mdCostBuildingInit(mdCostBuilding);
            } catch (e) {
                console.log("json parse 失败!")
            }
        }
        if (AlgorithmsPrototype.prototype.isNotNull(mdCostConstruction)) {
            // optionsBuildBox.showConstruction();
            try {
                mdCostConstruction = $("#mdCostConstructionJSON").val();
                mdCostConstruction = JSON.parse(mdCostConstruction);
                optionsBuildBox.mdCostConstructionInit(mdCostConstruction);
            } catch (e) {
                console.log("json parse 失败!")
            }
        }
    }

    $(function () {
        optionsBuildBox.tabControl();
        optionsBuildBox.detailInit();
    });
</script>

