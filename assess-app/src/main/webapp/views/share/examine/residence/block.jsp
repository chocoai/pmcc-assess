<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-7-5
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form id="frm_block" class="form-horizontal">
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">省<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select name="province" class="form-control search-select select2"
                        required="required">
                    <option value="">-请选择-</option>
                    <c:forEach items="${ProvinceList}" var="item">
                        <c:choose>
                            <c:when test="${item.areaId == projectInfo.province}">
                                <option value="${item.areaId}"
                                        selected="selected">${item.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${item.areaId}">${item.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">市<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select name="city" class="form-control search-select select2"
                        required="required">

                </select>
            </div>
        </div>

        <div class="x-valid">
            <label class="col-sm-1 control-label">县</label>
            <div class="col-sm-3">
                <select name="district" class="form-control search-select select2">

                </select>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <label class="col-sm-1 control-label">版块名称<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="版块名称" required
                       name="name" value=""
                       class="form-control">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">方位<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="方位" required
                       name="name" value=""
                       class="form-control">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">区域性质<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="区域性质" required
                       name="name" value=""
                       class="form-control">
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-4 col-sm-offset-5">
            <button id="saveFrm" type="button" class="btn btn-primary" onclick="saveData()">
                保存版块
            </button>
        </div>
    </div>

    <div class="ln_solid"></div>
</form>

<script type="text/javascript">
    $(function () {


        //版块信息自动补全
        $("#frm_block").find("[name='name']").autocomplete({
            source: function (request, response) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/case/autoCompleteBlock",
                    dataType: "json",
                    data: {
                        featureClass: "P",
                        style: "full",
                        maxRows: 12,
                        name_startsWith: request.term
                    },
                    success: function (data) {
                        response($.map(data.geonames, function (item) {
                            return {
                                label: item.name + (item.adminName1 ? ", " + item.adminName1 : "") + ", " + item.countryName,
                                value: item.name
                            }
                        }));
                    }
                });
            },
            minLength: 2,
            select: function (event, ui) {
                log(ui.item ?
                    "Selected: " + ui.item.label :
                    "Nothing selected, input was " + this.value);
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        });
    })

    (function ($) {
        //避免方法重复，定义全局变量
        var block = {
            //初始化
            init: function (id) {
                var blockData = {};
                if (id) {//获取版块数据
                    $.ajax({
                        url: "${pageContext.request.contextPath}/case/autoCompleteBlock",
                        data: {id: id},
                        type: "get",
                        dataType: "json",
                        success: function (result) {
                            if (result.ret) {
                                blockData = result.data;
                            }
                            else {
                                Alert("保存数据失败，失败原因:" + result.errmsg);
                            }
                        },
                        error: function (e) {
                            Alert("调用服务端方法失败，失败原因:" + e);
                        }
                    })
                }

                $("#frm_block").initForm(blockData);
                Block.loadRegionalNature(blockData.regionalNature);

                //初始化区域信息
                AssessCommon.initAreaInfo({
                    provinceTarget: $("#frm_block").find("[name='province']"),
                    cityTarget: $("#frm_block").find("[name='city']"),
                    districtTarget: $("#frm_block").find("[name='district']"),
                    provinceValue: blockData.province,
                    cityValue: blockData.city,
                    districtValue: blockData.district
                })
            },

            //保存 将需要保存的数据统一传递到后台
            save: function () {

                var data = formParams("frm_block");
                data.id = ExamineInfo.config.blockId;

                $.ajax({})
            },

            //加载区域性质
            loadRegionalNature: function (value) {
                AssessCommon.loadDataDicByKey("", value, function (html, data) {
                    $("#frm_block").find("[name='province']").append(html);
                })
            }
        };

        window.Block = block;
    })(jQuery)
</script>
