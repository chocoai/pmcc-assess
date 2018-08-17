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
        </div>
        <div class="col-sm-12 form-group">
            <span class="col-sm-1">
                <label>假设开发法:</label>
            </span>
            <span class="col-sm-2 col-sm-offset-1 checkbox-inline">
                 <input type="radio" name="development" value="1" checked="checked">
                <label>假设开发法</label>
            </span>
            <span class="col-sm-2  checkbox-inline">
                <input type="radio" name="development" value="2">
                <label>在建工程建设开发法</label>
            </span>
        </div>
        <div class="hypothesisDevelopment">
            <jsp:include page="./developmentModule/hypothesisDevelopment.jsp"></jsp:include>
            <!-- 主题内容 -->
        </div>
        <div class="architecturalEngineering" style="display: none;">
            <jsp:include page="./developmentModule/architecturalEngineering.jsp"></jsp:include>
            <!-- 主题内容 -->
        </div>
    </div>
</div>
<script>
    var optionsBuildBox = new Object();
    optionsBuildBox.showHypothesisDevelopment = function () {
        $(".optionsBuildBox .hypothesisDevelopment").show();
        $(".optionsBuildBox .architecturalEngineering").hide();
    };
    optionsBuildBox.showArchitecturalEngineering = function () {
        $(".optionsBuildBox .architecturalEngineering").show();
        $(".optionsBuildBox .hypothesisDevelopment").hide();
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

    /**
     * @author:  zch
     * 描述:获取假设开发法数据
     * @date:2018-08-17
     **/
    optionsBuildBox.getDevelopment = function () {

    };

    /**
     * @author:  zch
     * 描述:在建工程建设开发法数据
     * @date:2018-08-17
     **/
    optionsBuildBox.getArchitecturalEngineering = function () {

    };

    $(function () {
        optionsBuildBox.tabControl();
    });
</script>

