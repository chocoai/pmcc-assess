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
                <label class="col-sm-1 control-label">房号<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" required placeholder="房号" name="houseNumber"
                           class="form-control" value="${basicHouse.houseNumber}" id="txt_House_search">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">所在楼层<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="所在楼层" name="floor" required
                           class="form-control" value="${basicHouse.floor}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">楼层描述</label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="floorDesc" value="${basicHouse.floorDesc}">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">户型</label>
                <div class="col-sm-3">
                    <div class="input-group">
                        <input type="text" readonly="readonly" onclick="houseCommon.selectHuxing(this);"
                               placeholder="户型" class="form-control" name="huxingName"
                               value="${basicHouse.huxingName}">
                        <span class="input-group-btn">
                        <button type="button" class="btn btn-default docs-tooltip"
                                data-toggle="tooltip"
                                data-original-title="选择"
                                onclick="houseCommon.selectHuxing(this);">
                        <i class="fa fa-search"></i>
                        </button>
                        <button type="button" class="btn btn-default docs-tooltip"
                                onclick="$(this).closest('.input-group').find('input').val('');"
                                data-toggle="tooltip" data-original-title="清除">
                        <i class="fa fa-trash-o"></i>
                        </button>
                        </span>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">户型图</label>
                <div class="col-sm-3">
                    <div id="_house_huxing_plan"></div>
                </div>
            </div>


        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">新户型</label>
                <div class="col-sm-3">
                    <input type="text" placeholder="新户型" name="newHuxingName"
                           class="form-control" value="${basicHouse.newHuxingName}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">新户型图</label>
                <div class="col-sm-3">
                    <input id="house_new_huxing_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_house_new_huxing_plan"></div>
                </div>
            </div>
            <div class="x-valid">
                <div class="col-sm-3 col-sm-offset-1">
                    <div id="container"></div>
                    <div class="btn btn-success" onclick="houseCommon.orientationFun(false,0)">户型图朝向</div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">朝向<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 orientation" name="orientation" required>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">面积<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input type="text" placeholder="面积" name="area" data-rule-number="true" required
                           class="form-control" value="${basicHouse.area}">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">调查方式<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 researchType" name="researchType" required>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">证载用途<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 certUse" name="certUse" required>
                    </select>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">实际用途<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <select class="form-control search-select select2 practicalUse" name="practicalUse" required>
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">权益限制</label>
                <div class="col-sm-3">
                    <input type="text" data-rule-maxlength="100" placeholder="权益限制"
                           name="rightInterestsRestriction"
                           class="form-control" value="${basicHouse.rightInterestsRestriction}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋出租占用情况描述</label>
                <div class="col-sm-11">
                    <textarea class="form-control" name="description">${basicHouse.description}</textarea>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋平面图</label>
                <div class="col-sm-5">
                    <input id="house_img_plan" placeholder="上传附件" class="form-control" type="file">
                    <div id="_house_img_plan"></div>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">房屋装饰图</label>
                <div class="col-sm-5">
                    <input id="house_decorate" placeholder="上传附件" class="form-control" type="file">
                    <div id="_house_decorate"></div>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    附件
                </label>
                <div class="col-sm-3">
                    <input id="house_file" placeholder="上传附件" class="form-control" type="file">
                    <div id="_house_file"></div>
                </div>
            </div>
        </div>
    </form>
</div>

