
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<input type="hidden" name="id">
<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">房号<span class="symbol required"></span></label>
        <div class="col-sm-3">
            <input type="text" data-rule-maxlength="100" placeholder="房号" name="houseNumber"
                   class="form-control" required="required">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">所在楼层</label>
        <div class="col-sm-3">
            <input type="text" data-rule-maxlength="100" data-rule-number='true'
                   placeholder="所在楼层(请输入数字)" name="floor"
                   class="form-control">
        </div>
    </div>
    <div class="x-valid">
        <label class="col-sm-1 control-label">使用环境</label>
        <div class="col-sm-3">
            <select class="form-control search-select select2 useEnvironment"
                    name="useEnvironment">
            </select>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">户型选择</label>
        <div class="col-sm-3">
            <div class="input-group">
                <select class="form-control huxingId" name="huxingId">
                </select>
                <label class="input-group-addon btn" onclick="houseModelFun.unitHuxingSelectLoad(this)">刷新户型<i
                        class="fa fa-refresh"></i></label>
            </div>
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">户型图</label>
        <div class="col-sm-3">
            <div class="house_latest_family_plan"></div>
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">朝向</label>
        <div class="col-sm-3">
            <input type="text" placeholder="朝向" name="orientation"
                   class="form-control">
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">证载用途</label>
        <div class="col-sm-3">
            <select class="form-control search-select select2 certUse" name="certUse">
            </select>
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">实际用途</label>
        <div class="col-sm-3">
            <select class="form-control search-select select2 practicalUse" name="practicalUse">
            </select>
        </div>
    </div>

    <div class="x-valid">
        <label class="col-sm-1 control-label">权益限制</label>
        <div class="col-sm-3">
            <input type="text" data-rule-maxlength="100" placeholder="权益限制"
                   name="rightInterestsRestriction"
                   class="form-control">
        </div>
    </div>
</div>

<div class="form-group">
    <div class="x-valid">
        <label class="col-sm-1 control-label">房屋出租占用情况途描述</label>
        <div class="col-sm-11">
            <textarea class="form-control" name="description"></textarea>
        </div>
    </div>
</div>