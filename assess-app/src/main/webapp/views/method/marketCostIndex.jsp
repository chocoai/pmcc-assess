<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>成本法</h3>
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
            <input type="hidden" id="mdCostConstructionJSON" name="mdCostConstructionJSON" value='${mdCostConstructionJSON}'>


        </div>
        <div class="col-sm-12 form-group">
            <span class="col-sm-1">
                <label>建筑形态</label>
            </span>
            <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                 <input type="radio" id="building1" name="building" value="1" checked="checked">
                <label for="building1">建筑物</label>
            </span>
            <span class="col-sm-2  checkbox-inline">
                <input type="radio" id="building2" name="building" value="2">
                <label for="building2">在建工程</label>
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
    var optionsBuildBox = new Object();

    optionsBuildBox.isNotNull = function (item) {
        if (item){
            return true;
        }
        return false;
    };


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
        build.dataObject = JSON.parse(data);
    };
    optionsBuildBox.mdCostConstructionInit = function (data) {
        construction.initForm(data);
        construction.dataObject = JSON.parse(data);
    };
    /**
     * @author:  zch
     * 描述:处理页面更新时
     * @date:
     **/
    optionsBuildBox.updateInit = function () {
        var mdCostBuilding = "${mdCostBuilding}";
        var mdCostConstruction = "${mdCostConstruction}";
        var mdCost = "${mdCost.type}";
        if (mdCost == "tb_md_cost_construction"){
            optionsBuildBox.showConstruction();
            $(".optionsBuildBox :radio").each(function (i,n) {
                if ($(n).val() == "2"){
                    $(n).attr("checked",'checked');
                }
            });
        }
        if (mdCost == "tb_md_cost_building"){
            optionsBuildBox.showBuilding();
            $(".optionsBuildBox :radio").each(function (i,n) {
                if ($(n).val() == "1"){
                    $(n).attr("checked",'checked');
                }
            });
        }
        if (optionsBuildBox.isNotNull(mdCostBuilding)) {
            try {
                mdCostBuilding = $("#mdCostBuildingJSON").val();
                mdCostBuilding = JSON.parse(mdCostBuilding);
                //初始化数据
                optionsBuildBox.mdCostBuildingInit(mdCostBuilding);
            } catch (e) {
                console.log("json parse 失败!")
            }
        }
        if (optionsBuildBox.isNotNull(mdCostConstruction)) {
            try {
                mdCostConstruction = $("#mdCostConstructionJSON").val();
                mdCostConstruction = JSON.parse(mdCostConstruction);
                //初始化数据
                optionsBuildBox.mdCostConstructionInit(mdCostConstruction);
            } catch (e) {
                console.log("json parse 失败!")
            }
        }
    };

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

