<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
</head>


<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <%@include file="/views/share/form_head.jsp" %>
            <div class="row">
                <div class="col-md-12 ">
                    <div class="x_panel">
                        <div class="x_title collapse-link">
                            <ul class="nav navbar-right panel_toolbox">
                                <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                            </ul>
                            <h3>案列任务分派申请</h3>
                            <div class="clearfix"></div>
                        </div>
                        <div class="x_content">
                            <form id="master" class="form-horizontal">
                                <input type="hidden" name="id">
                                <div class="form-group ">
                                    <div>
                                        <label class="col-sm-1 control-label">
                                            省
                                        </label>
                                        <div class="col-sm-2">
                                            <select name="province" class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div>
                                        <label class="col-sm-1 control-label">
                                            市
                                        </label>
                                        <div class="col-sm-2">
                                            <select name="city" class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div>
                                        <label class="col-sm-1 control-label">
                                            区
                                        </label>
                                        <div class="col-sm-2">
                                            <select name="district" class="form-control search-select select2">
                                            </select>
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <label class=" col-md-1 col-sm-1 col-xs-12 control-label">
                                            认领人<span class="symbol required"></span>
                                        </label>
                                        <div class="col-md-2 col-sm-2 col-xs-12 ">
                                            <input type="hidden" id="executor" name="executor">
                                            <input class="form-control" id="executorName" name="executorName"
                                                   placeholder="选择认领人..."
                                                   readonly required onclick="selectUser()">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group ">
                                    <label class=" col-md-1 col-sm-1 col-xs-12 control-label">
                                        添加楼盘<span class="symbol required"></span>
                                    </label>
                                    <div class=" col-xs-2  col-sm-2  col-md-2  col-lg-2 ">
                                        <div class="btn btn-xs btn-success"
                                             onclick="appendHTML(this)"><i
                                                class="fa fa-plus"></i></div>
                                    </div>
                                </div>
                                <div class="addHouse">

                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-md-1 col-sm-1 col-xs-12 control-label">
                                            备注
                                        </label>
                                        <div class="col-md-11 col-sm-11 col-xs-12">
                                     <textarea placeholder="备注" name="remark" id="remark"
                                               class="form-control"></textarea>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="x_panel">
                        <div class="x_content">
                            <div style="text-align: center;">
                                <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                                    取消
                                </button>
                                <button id="btn_submit" class="btn btn-success" onclick="submit();">
                                    提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                    <%@include file="/views/share/form_log.jsp" %>
                </div>
            </div>
        </div>

    </div>
    <!-- end: MAIN CONTAINER -->
</div>


</body>
<%@include file="/views/share/main_footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/autocomplete/lpmc.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.css">
<script src='${pageContext.request.contextPath}/assets/jquery-ui/jquery-ui.js'></script>
<script type="application/javascript">
    $(function () {
        AssessCommon.initAreaInfo({
            provinceTarget: $("#master").find("select[name='province']"),
            cityTarget: $("#master").find("select[name='city']"),
            districtTarget: $("#master").find("select[name='district']"),
            provinceValue: '',
            cityValue: '',
            districtValue: ''
        });
        if (!"${master}") {
            $("#executor").val("${thisUser.userAccount}");
            $("#executorName").val("${thisUser.userName}");
        }
    });


    //保存数据
    function submit() {
        if (!$("#master").valid()) {
            return false;
        }
        var data = formParams("master");
        data.lpInfo = [];
        $("#master").find('.form-group').each(function () {
            var lpItem = {};
            var lpbh = $(this).find('[name^=lpbh]').val();
            var name = $(this).find('[name^=lpName]').val();
            if (lpbh || name) {
                lpItem.lpbh = lpbh;
                lpItem.name = name;
                data.lpInfo.push(lpItem);
            }
        });

        $.ajax({
            url: "${pageContext.request.contextPath}/taskCaseAssign/applySubmit",
            type: "post",
            dataType: "json",
            data: {formData: JSON.stringify(data)},
            async: false,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    Alert("数据提交成功!", 1, null, function () {
                        window.close();
                    });
                } else {
                    Alert(result.errmsg);
                }
            }
        });

    }

    function selectUser() {
        erpEmployee.select({
            onSelected: function (data) {
                if (data.account) {
                    $("#executor").val(data.account);
                    $("#executorName").val(data.name);
                }
                else {
                    $("#executor").val("");
                    $("#executorName").val("");
                }
            }
        });
    }

    //自动填充楼盘名称
    function apLpmc(that) {
        var defaults = {
            offset: 0,
            limit: 10,
            onSelect: function (id, name) {

            }
        };
        var params = AssessDefault.autocomplete();
        var province = $("#master").find("select[name='province']").val();
        var city = $("#master").find("select[name='city']").val();
        var district = $("#master").find("select[name='district']").val();
        params.source = function (request, response) {
            $.ajax({
                url: getContextPath() + "/taskCaseAssign/getHousesList",
                type: "get",
                dataType: "json",
                data: {
                    offset: defaults.offset,
                    limit: defaults.limit,
                    name: $(that).val(),
                    province: province,
                    city: city,
                    district: district
                },
                success: function (data) {
                    if (data) {
                        var responseArray = [];
                        $.each(data.rows, function (i, item) {
                            responseArray.push({
                                label: item.lpmc,
                                id: item.id
                            });
                        });
                        response(responseArray);
                    }
                }
            });
        }
        params.select = function (event, ele) {
            $(that).val(ele.item.label);
            $(that).prevAll().val(ele.item.id);
            if (defaults.onSelect) {
                defaults.onSelect(ele.item.id, ele.item.label)
            }
        }
        $(that).autocomplete(params);
        return that;
    }


    function appendHTML(this_) {
        var html = "<div class='form-group' >";
        html += "<div class='x-valid'>";

        html += "<label class=' col-md-1 col-sm-1 col-xs-12 control-label'>" + "楼盘名称" + "</label>";
        html += "<div class=' col-md-2 col-sm-2 col-xs-12 '>";
        html += "<input type='hidden' name='lpbh'>";
        html += "<input type='text' required class='form-control' name='lpName' oninput='apLpmc(this)'>";
        html += "</div>";

        html += "<div class=' col-xs-2  col-sm-2  col-md-2  col-lg-2 '>";
        html += "<div class='btn btn-xs btn-warning' onclick='cleanHTMLData(this)'>";
        html += "<i class='fa fa-minus fa-white'></i>";
        html += "</div>";
        html += "</div>";


        html += "</div>";
        html += "</div>";

        $(".addHouse").append(html);
    }

    function cleanHTMLData(item) {
        $(item).parent().parent().parent().remove();
    }


</script>


</html>
