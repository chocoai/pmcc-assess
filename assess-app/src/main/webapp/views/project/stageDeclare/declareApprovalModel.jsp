<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<script type="text/javascript">
    var commonDeclareApprovalModel = {};

    commonDeclareApprovalModel.config = {
        house: {
            id: "declareModelHouse",
            handleId: "declareModelHandleHouse",
            name: "房产证信息"
        },
        land: {
            id: "declareModelLand",
            handleId: "declareModelHandleLand",
            name: "土地证信息"
        },
        realEstateCert: {
            id: "declareModelRealEstateCert",
            handleId: "declareModelHandleRealEstateCert",
            name: "不动产证"
        }
    };

    /**
     * 不动产
     * @type {{getHtml: commonDeclareApprovalModel.realEstateCert.getHtml}}
     */
    commonDeclareApprovalModel.realEstateCert = {
        getHtml: function () {
            return $("#" + commonDeclareApprovalModel.config.realEstateCert.id).html();
        }
    } ;

    /**
     * 房产证
     * @type {{getHtml: commonDeclareApprovalModel.house.getHtml}}
     */
    commonDeclareApprovalModel.house = {
        getHtml: function () {
            return $("#" + commonDeclareApprovalModel.config.house.id).html();
        }
    };

    /**
     * 土地证
     * @type {{getHtml: commonDeclareApprovalModel.land.getHtml}}
     */
    commonDeclareApprovalModel.land = {
        getHtml: function () {
            return $("#" + commonDeclareApprovalModel.config.land.id).html();
        }
    };
</script>

<script id="declareModelHouse" type="text/html" data-title="房产证label">
    <div id="declareModelHandleHouse">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">省
                    <span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="provinceName"></label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">市<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="cityName"></label>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">县<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="districtName"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房产权证号<span class="symbol required"></span></label>
                <div class="col-sm-11">
                    <label class="form-control" name="certName"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">所在地<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="location"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">编号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="number"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">类型<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="type"></label>
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋所有权人<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="ownership"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">共有情况<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="publicSituation"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建筑面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="floorArea"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋坐落</label>
                <div class="col-sm-11">
                    <label class="form-control" name="beLocated"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">街道号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="streetNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">附号</label>
                <div class="col-sm-3">
                    <label class="form-control" name="attachedNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">栋号</label>
                <div class="col-sm-3">
                    <label class="form-control" name="buildingNumber"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">单元</label>
                <div class="col-sm-3">
                    <label class="form-control" name="unit"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼层</label>
                <div class="col-sm-3">
                    <label class="form-control" name="floor"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房号</label>
                <div class="col-sm-3">
                    <label class="form-control" name="roomNumber"></label>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    登记时间<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="registrationTime"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋性质</label>
                <div class="col-sm-3">
                    <label class="form-control" name="nature"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">规划用途<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="planningUse"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">总层数<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="floorCount"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">证载面积</label>
                <div class="col-sm-3">
                    <label class="form-control" name="evidenceArea"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">套内面积</label>
                <div class="col-sm-3">
                    <label class="form-control" name="innerArea"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">其它</label>
                <div class="col-sm-3">
                    <label class="form-control" name="other"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地证号<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="landNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地取得方式</label>
                <div class="col-sm-3">
                    <label class="form-control" name="landAcquisition"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限起<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="useStartDate"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限止<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="useEndDate"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">公摊面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="publicArea"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">附记其它</label>
                <div class="col-sm-3">
                    <label class="form-control" name="otherNote"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">登记机关<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="registrationAuthority"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    登记日期<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="registrationDate"></label>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="declareModelLand" type="text/html" data-title="土地证label">
    <div id="declareModelHandleLand">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    省<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="provinceName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    市<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="cityName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    县(区)
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="districtName"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    所在地
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="location"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用权人
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="ownership"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地权证号
                </label>
                <div class="col-sm-11">
                    <label class="form-control" name="landCertName"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    类型
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="type"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    年份
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="year"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    编号
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="number"></label>
                </div>
            </div>
        </div>
        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋坐落<span class="symbol required"></span></label>
                <div class="col-sm-11">
                    <label class="form-control" name="beLocated"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">街道号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="streetNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">附号</label>
                <div class="col-sm-3">
                    <label class="form-control" name="attachedNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">栋号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="buildingNumber"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">单元<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="unit"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼层<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="floor"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="roomNumber"></label>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">地号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="landNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">图号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="graphNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    用途
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="purposeName"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">取得价格<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="acquisitionPrice"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    使用权类型
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="useRightType"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    终止日期<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="terminationDate"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">使用权面积<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="useRightArea"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">独用面积<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="acreage"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">分摊面积<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="apportionmentArea"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">记事<span
                        class="symbol required"></span></label>
                <div class="col-sm-11">
                    <label class="form-control" name="memo"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">登记机关<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="registrationAuthority"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    登记日期<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="registrationDate"></label>
                </div>
            </div>
        </div>
    </div>
</script>

<script id="declareModelRealEstateCert" type="text/html" data-title="不动产证label">
    <div id="declareModelHandleRealEstateCert">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    省<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="provinceName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    市<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="cityName"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    县(区)
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="districtName"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">不动产权证号</label>
                <div class="col-sm-11">
                    <label class="form-control" name="certName"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">所在地<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="location"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">编号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="number"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房产证类型<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="typeName"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋所有权人<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="ownership"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">共有情况<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="publicSituation"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">建筑面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="floorArea"></label>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋坐落</label>
                <div class="col-sm-11">
                    <label class="form-control" name="beLocated"></label>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">街道号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="streetNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">附号</label>
                <div class="col-sm-3">
                    <label class="form-control" name="attachedNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">栋号</label>
                <div class="col-sm-3">
                    <label class="form-control" name="buildingNumber"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">单元</label>
                <div class="col-sm-3">
                    <label class="form-control" name="unit"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼层</label>
                <div class="col-sm-3">
                    <label class="form-control" name="floor"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房号<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="roomNumber"></label>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    登记时间<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="registrationTime"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋性质<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="nature"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">规划用途</label>
                <div class="col-sm-3">
                    <label class="form-control" name="planningUse"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">总层数<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="floorCount"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">证载面积</label>
                <div class="col-sm-3">
                    <label class="form-control" name="evidenceArea"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">套内面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="innerArea"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">其它</label>
                <div class="col-sm-3">
                    <label class="form-control" name="other"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地证号<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="landNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">土地取得方式</label>
                <div class="col-sm-3">
                    <label class="form-control" name="landAcquisition"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限起<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="useStartDate"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限止<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="useEndDate"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">公摊面积</label>
                <div class="col-sm-3">
                    <label class="form-control" name="publicArea"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">附记其它</label>
                <div class="col-sm-3">
                    <label class="form-control" name="otherNote"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">登记机关<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="registrationAuthority"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    登记日期<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="registrationDate"></label>
                </div>
            </div>
        </div>

        <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
            color="#6f5499" size="10"/>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">地号</label>
                <div class="col-sm-3">
                    <label class="form-control" name="landNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">图号</label>
                <div class="col-sm-3">
                    <label class="form-control" name="graphNumber"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    用途
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="purposeName"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">取得价格</label>
                <div class="col-sm-3">
                    <label class="form-control" name="acquisitionPrice"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    使用权类型
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="useRightType"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    终止日期<span class="symbol required"></span>
                </label>
                <div class="col-sm-3">
                    <label class="form-control" name="terminationDate"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">使用权面积<span
                        class="symbol required"></span></label>
                <div class="col-sm-3">
                    <label class="form-control" name="useRightArea"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">独用面积</label>
                <div class="col-sm-3">
                    <label class="form-control" name="acreage"></label>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">分摊面积</label>
                <div class="col-sm-3">
                    <label class="form-control" name="apportionmentArea"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">记事<span
                        class="symbol required"></span></label>
                <div class="col-sm-11">
                    <label class="form-control" name="memo"></label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">不动产单元号<span
                        class="symbol required"></span></label>
                <div class="col-sm-11">
                    <label class="form-control" name="realEstateUnitNumber"></label>
                </div>
            </div>
        </div>
    </div>
</script>
</body>
</html>
