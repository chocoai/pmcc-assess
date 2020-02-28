<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="realEstateModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">不动产证</h3>
            </div>
            <form id="realEstateFrm" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            省
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="provinceName"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            市
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="cityName"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            县(区)
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="districtName"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">不动产权证号</label>
                                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                            <label class="form-control" name="certName"></label>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">所在地</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="location"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">编号</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="number"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">年份</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label name="year" class="form-control"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">不动产单元号</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label name="realEstateUnitNumber" class="form-control"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋所有权人</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="ownership"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共有情况</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="publicSituationName"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            登记日期</span>
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <%--<label class="form-control" name="registrationTime"></label>--%>
                                            <input type="text" name="registrationTimeFmt" class="form-control"
                                                   readonly="readonly">
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <%--<div class="form-group">--%>
                                <%--<div class="x-valid">--%>
                                <%--<label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号</label>--%>
                                <%--<div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">--%>
                                <%--<label class="form-control" name="streetNumber"></label>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="x-valid">--%>
                                <%--<label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附号</label>--%>
                                <%--<div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">--%>
                                <%--<label class="form-control" name="attachedNumber"></label>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="x-valid">--%>
                                <%--<label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">栋号</label>--%>
                                <%--<div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">--%>
                                <%--<label class="form-control" name="buildingNumber"></label>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--</div>--%>

                                <%--<div class="form-group">--%>
                                <%--<div class="x-valid">--%>
                                <%--<label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单元</label>--%>
                                <%--<div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">--%>
                                <%--<label class="form-control" name="unit"></label>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="x-valid">--%>
                                <%--<label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼层</label>--%>
                                <%--<div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">--%>
                                <%--<label class="form-control" name="floor"></label>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="x-valid">--%>
                                <%--<label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房号</label>--%>
                                <%--<div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">--%>
                                <%--<label class="form-control" name="roomNumber"></label>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">坐落</label>
                                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                            <label class="form-control" name="beLocated"></label>
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋用途类型</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="houseCertUse"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋用途类别</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="houseCertUseCategory"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋结构</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="housingStructure"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋性质</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="natureName"></label>
                                        </div>
                                    </div>
                                    <%--<div class="x-valid">--%>
                                    <%--<label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑面积</label>--%>
                                    <%--<div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">--%>
                                    <%--<label class="form-control" name="floorArea"></label>--%>
                                    <%--</div>--%>
                                    <%--</div>--%>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证载面积</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="evidenceArea"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">套内面积</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="innerArea"></label>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">分摊面积</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="apportionmentArea"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">登记机关</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="registrationAuthority"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">其它(房屋)</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="other"></label>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">

                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附记(其他)</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="otherNote"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总层数</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="floorCount"></label>
                                        </div>
                                    </div>
                                </div>


                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地取得方式</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="acquisitionType"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地取得价格</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="acquisitionPrice"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地用途类型</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="purposeName"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            土地用途类别
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="landCertUseCategoryName"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地权利性质</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="useRightTypeName"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共用宗地面积</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="useRightArea"></label>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">权利类型</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="typeName"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地分摊面积</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="landApportionArea"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            土地使用年限起
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <%--<label class="form-control" name="useStartDate"></label>--%>
                                            <input type="text" name="useStartDateFmt" class="form-control"
                                                   readonly="readonly">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            土地使用年限止
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <%--<label class="form-control" name="useEndDate"></label>--%>
                                            <input type="text" name="useEndDateFmt" id="useEndDateFmt_d"
                                                   class="form-control"
                                                   readonly="readonly">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">记事</label>
                                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                            <label class="form-control" name="memo"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        不动产证
                                    </label>
                                    <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                        <div id="_tb_declare_realty_real_estate_cert"></div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="realLandModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">土地证</h3>
            </div>
            <form id="realLandFrm" class="form-horizontal">
                <input type="hidden" id="id" name="id" value="0">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            省
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="provinceName"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            市
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="cityName"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            县(区)
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="districtName"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            所在地
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="location"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            类型
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="landRightTypeName"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            年份
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="year"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            编号
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="number"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            土地使用权人
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="ownership"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共有情况</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="publicSituationName"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            土地权证号
                                        </label>
                                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                            <label class="form-control" name="landCertName"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        登记日期
                                    </label>
                                    <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                        <label class="form-control" name="registrationDate"></label>
                                    </div>
                                </div>
                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <%--<div class="form-group">--%>
                                <%--<div class="x-valid">--%>
                                <%--<label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号</label>--%>
                                <%--<div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">--%>
                                <%--<label class="form-control" name="streetNumber"></label>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="x-valid">--%>
                                <%--<label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附号</label>--%>
                                <%--<div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">--%>
                                <%--<label class="form-control" name="attachedNumber"></label>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="x-valid">--%>
                                <%--<label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">栋号</label>--%>
                                <%--<div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">--%>
                                <%--<label class="form-control" name="buildingNumber"></label>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--</div>--%>

                                <%--<div class="form-group">--%>
                                <%--<div class="x-valid">--%>
                                <%--<label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单元</label>--%>
                                <%--<div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">--%>
                                <%--<label class="form-control" name="unit"></label>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="x-valid">--%>
                                <%--<label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼层</label>--%>
                                <%--<div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">--%>
                                <%--<label class="form-control" name="floor"></label>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--<div class="x-valid">--%>
                                <%--<label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房号</label>--%>
                                <%--<div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">--%>
                                <%--<label class="form-control" name="roomNumber"></label>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">坐落</label>
                                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                            <label class="form-control" name="beLocated"></label>
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">地号</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="landNumber"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">图号</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="graphNumber"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            土地用途类型
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="purposeName"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            土地用途类别
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="certUseCategoryName"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">取得价格</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="acquisitionPrice"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            权利性质
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="landRightNatureName"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">使用权面积</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="useRightArea"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">分摊面积</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="apportionmentArea"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">登记机关</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="registrationAuthority"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">记事</label>
                                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                            <label class="form-control" name="memo"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        土地证
                                    </label>
                                    <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                        <div id="_tb_declare_realty_land_cert"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="realHouseModal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">房产证</h3>
            </div>
            <form id="realHouseFrm" class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">省
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="provinceName"></label>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">市</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="cityName"></label>
                                        </div>
                                    </div>

                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">县</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="districtName"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房产权证号</label>
                                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                            <label class="form-control" name="certName"></label>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">所在地</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="location"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">编号</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="number"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">类型</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="typeName"></label>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋所有权人</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="ownership"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">建筑面积</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="floorArea"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">共有情况</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="publicSituationName"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            登记日期
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="registrationDate"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">丘地号</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="groundNum"></label>
                                        </div>
                                    </div>
                                </div>
                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">街道号</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="streetNumber"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">附号</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="attachedNumber"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">栋号</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="buildingNumber"></label>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">单元</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="unit"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">楼层</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="floor"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房号</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="roomNumber"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">坐落</label>
                                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                            <label class="form-control" name="beLocated"></label>
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋结构</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="housingStructure"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋用途类型</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="certUse"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋用途类别</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="certUseCategory"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">房屋性质</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="natureName"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">证载面积</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="evidenceArea"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">套内面积</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="innerArea"></label>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">公摊面积</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="publicArea"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">总层数</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="floorCount"></label>
                                        </div>
                                    </div>
                                </div>

                                <hr style="filter: alpha(opacity=100,finishopacity=0,style=2)" width="100%"
                                    color="#6f5499" size="10"/>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">土地取得方式</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="landAcquisitionName"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">登记机关</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="registrationAuthority"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">分摊面积</label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="apportionmentArea"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            土地使用年限起
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="useStartDate"></label>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                            土地使用年限止
                                        </label>
                                        <div class=" col-xs-3  col-sm-3  col-md-3  col-lg-3 ">
                                            <label class="form-control" name="useEndDate" id="useEndDate_d"></label>
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">其它</label>
                                        <div class=" col-xs-11  col-sm-11  col-md-11  col-lg-11 ">
                                            <label class="form-control" name="other"></label>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class=" col-xs-1  col-sm-1  col-md-1  col-lg-1  control-label">
                                        房产证
                                    </label>
                                    <div class=" col-xs-5  col-sm-5  col-md-5  col-lg-5 ">
                                        <div id="_tb_declare_realty_house_cert"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="application/javascript">
    var certificate = function () {

    };
    certificate.prototype = {
        config: function () {
            var data = {};
            data.realEstateBox = "realEstateModal";
            data.realEstateFrm = "realEstateFrm";
            data.realLandBox = "realLandModal";
            data.realLandFrm = "realLandFrm";
            data.realHouseBox = "realHouseModal";
            data.realHouseFrm = "realHouseFrm";
            return data;
        },
        getAndInit: function (tableName, id) {
            switch (tableName) {
                //不动产证
                case "tb_declare_realty_real_estate_cert": {
                    certificate.prototype.initRealEstate(tableName, id);
                    break;
                }
                //土地证
                case "tb_declare_realty_land_cert": {
                    certificate.prototype.initRealLand(tableName, id);
                    break;
                }
                //房产证
                case "tb_declare_realty_house_cert": {
                    certificate.prototype.initRealHouse(tableName, id);
                    break;
                }
            }
        },
        initRealEstate: function (tableName, id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/declareRealtyRealEstateCert/getDeclareRealtyRealEstateCertById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + certificate.prototype.config().realEstateFrm).clearAll();
                        $("#" + certificate.prototype.config().realEstateFrm).initForm(result.data);
                        certificate.prototype.showFiles(result.data.id, tableName);
                        $('#' + certificate.prototype.config().realEstateBox).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        initRealLand: function (tableName, id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/declareRealtyLandCert/getDeclareRealtyLandCertById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        console.log(result.data)
                        $("#" + certificate.prototype.config().realLandFrm).clearAll();
                        $("#" + certificate.prototype.config().realLandFrm).initForm(result.data);
                        certificate.prototype.showFiles(result.data.id, tableName);
                        $("#" + certificate.prototype.config().realLandFrm).find("label[name='terminationDate']").html(formatDate(result.data.terminationDate));
                        $("#" + certificate.prototype.config().realLandFrm).find("label[name='registrationDate']").html(formatDate(result.data.registrationDate));

                        $('#' + certificate.prototype.config().realLandBox).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        initRealHouse: function (tableName, id) {
            $.ajax({
                url: "${pageContext.request.contextPath}/declareRealtyHouseCert/getDeclareRealtyHouseCertById",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        $("#" + certificate.prototype.config().realHouseFrm).clearAll();
                        $("#" + certificate.prototype.config().realHouseFrm).initForm(result.data);
                        $("#" + certificate.prototype.config().realHouseFrm).find("label[name='registrationDate']").html(formatDate(result.data.registrationDate));
                        $("#" + certificate.prototype.config().realHouseFrm).find("label[name='useEndDate']").html(formatDate(result.data.useEndDate));
                        $("#" + certificate.prototype.config().realHouseFrm).find("label[name='useStartDate']").html(formatDate(result.data.useStartDate));
                        certificate.prototype.showFiles(result.data.id, tableName);

                        $('#' + certificate.prototype.config().realHouseBox).modal("show");
                    }
                },
                error: function (result) {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        showFiles: function (tableId, tableName) {
            FileUtils.getFileShows({
                target: tableName,
                formData: {
                    tableName: tableName,
                    tableId: tableId
                },
                deleteFlag: false
            });
        }
    }
</script>
