<%--
  版块
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
                       name="name"
                       class="form-control">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">方位<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <input type="text" data-rule-maxlength="100" placeholder="方位" required
                       name="position" class="form-control">
            </div>
        </div>
        <div class="x-valid">
            <label class="col-sm-1 control-label">区域性质<span class="symbol required"></span></label>
            <div class="col-sm-3">
                <select name="regionalNature" class="form-control" required></select>
            </div>
        </div>
    </div>

    <div class="ln_solid"></div>
</form>

<script type="text/javascript">

    $(function () {
        Block.init(0);
        //版块信息自动补全
        $("#frm_block").find("[name='name']").autocomplete({
            source: function (request, response) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/case/autoCompleteBlock",
                    type: "get",
                    dataType: "json",
                    data: {
                        maxRows: 10,
                        term: request.term
                    },
                    success: function (result) {
                        response($.each(result.data, function (i, item) {
                            return {
                                label: item.value,
                                value: item.key
                            }
                        }));
                    }
                });
            },
            minLength: 2,
            select: function (event, ele) {
                console.log(ele.item);//直接读取案例中对应数据内容
            }
        });
    })
</script>
<script type="text/javascript">
    (function ($) {
        //避免方法重复，定义全局变量
        var block = {
            //初始化
            init: function (id) {
                $("#frm_block").clearAll();
                var blockData = {};
                var block = Block.getBlockById(id)
                if (block) {
                    blockData = block;
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

            //获取版块信息
            getBlockById: function (id) {
                var data = undefined;
                if (id) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/examineBlock/getBlockById",
                        data: {id: id},
                        type: "get",
                        dataType: "json",
                        async: false,
                        success: function (result) {
                            if (result.ret) {
                                data = result.data;
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
                return data;
            },

            //保存
            save: function () {
                if (!$("#frm_block").valid()) {
                    return false;
                }
                var data = formParams("frm_block");
                data.id = ExamineInfo.config.blockId;
                Loading.progressShow();
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBlock/saveBlock",
                    data: data,
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            toastr.success('保存版块成功');
                            ExamineInfo.config.blockId = result.data;
                            ExamineInfo.initTab();//重新加载tab
                        }
                        else {
                            Alert("保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (e) {
                        Alert("调用服务端方法失败，失败原因:" + e);
                    }
                })
            },

            //加载区域性质
            loadRegionalNature: function (value) {
                AssessCommon.loadDataDicByKey(AssessDicKey.examineBlockRegionalNature, value, function (html, data) {
                    $("#frm_block").find("[name='regionalNature']").append(html);
                })
            }
        };

        window.Block = block;
    })(jQuery)
</script>
