<%--
  房屋基本新信息
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <div class="x_title">
        <h3>
            房屋基本信息
        </h3>
        <div class="clearfix"></div>
    </div>
    <form class="form-horizontal" id="basicHouseFrm">
        <input type="hidden" name="id" value="${basicHouse.id}">
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房号</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.houseNumber}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">所在楼层</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.floor}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼层描述</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.floorDesc}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">户型</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.huxingName}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">户型图</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div id="_house_huxing_plan"></div>
                </div>
            </div>


        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">新户型</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.newHuxingName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">新户型图</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div id="_house_new_huxing_plan"></div>
                </div>
            </div>
            <div class="x-valid">
                <div class=" col-xs-31  col-sm-31  col-md-31  col-lg-31  col-sm-offset-1">
                    <div class="btn btn-success" onclick="houseCommon.orientationFun(true);">户型地图朝向</div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">空间布局</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.spatialDistributionName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">空间布局描述</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.spatialDistributionDesc}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">朝向</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.orientationName}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
           
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">面积</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.area}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">面积描述</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.areaDesc}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">调查方式</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.researchTypeName}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证载用途</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.certUseName}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">实际用途</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.practicalUseName}</label>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">权益限制</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.rightInterestsRestriction}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地块位置</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.landLocation}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">使用年限</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.useYear}</label>
                </div>
            </div>

            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼面地价</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <label class="form-control">${basicHouse.floorPrice}</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋出租占用情况描述</label>
                <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 1">
                    <label class="form-control">${basicHouse.description}</label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋平面图</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div id="_house_img_plan"></div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋装饰图</label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div id="_house_decorate"></div>
                </div>
            </div>
            <div class="x-valid">
                <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                    附件
                </label>
                <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                    <div id="_house_file"></div>
                </div>
            </div>
        </div>
    </form>
</div>
