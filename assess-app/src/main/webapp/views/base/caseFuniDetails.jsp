<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <title>${caseFuniHouses.lpmc}</title>
    <%@include file="/views/share/main_css.jsp" %>
</head>
<body class="nav-md footer_fixed">
<div class="container body">
    <div class="main_container">
        <div class="right_col" role="main" style="margin-left: 0">
            <div class="x_content">
                <div class="row">
                    <div class="col-xs-12 invoice-header">
                        <h1>
                            <i class="fa fa-globe"></i> ${caseFuniHouses.lpmc}
                            <small class="pull-right"><i class="fa fa-cny"></i>
                                <a href='#' data-type='text' name="lpjj_lpxx_${caseFuniHouses.id}" data-original-title='楼盘均价' class='editable editable-click'>${caseFuniHouses.lpjj}</a>
                            </small>
                        </h1>
                    </div>
                    <div class="col-sm-4 invoice-col">
                        <address>
                            <strong>项目地址:${caseFuniHouses.xmdz}</strong>
                        </address>
                    </div>
                    <div class="col-sm-4 invoice-col">
                        <address>
                            <strong>区域:${caseFuniHouses.lpdz}</strong>
                        </address>
                    </div>
                    <div class="col-sm-4 invoice-col">
                        <address>
                            <strong>开发商:${caseFuniHouses.kfs}</strong>
                        </address>
                    </div>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>楼盘信息</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <div class="table-responsive">
                        <table class="table">
                            <tbody>
                            <tr>
                                <th style="width:7%">建筑面积:</th>
                                <td style="width:18%"><a href='#' data-type='text' name="jzmj_lpxx_${caseFuniHouses.id}" data-original-title='建筑面积' class='editable editable-click'>${caseFuniHouses.jzmj}</a>
                                </td>
                                <th style="width:7%">占地面积:</th>
                                <td style="width:18%"><a href='#' data-type='text' name="jzmj_lpxx_${caseFuniHouses.id}" data-original-title='占地面积' class='editable editable-click'>${caseFuniHouses.jzmj}</a>
                                </td>
                                <th style="width:7%">容积率:</th>
                                <td style="width:18%"><a href='#' data-type='text' name="rjl_lpxx_${caseFuniHouses.id}" data-original-title='容积率' class='editable editable-click'>${caseFuniHouses.rjl}</a></td>
                                <th style="width:7%">绿化率:</th>
                                <td style="width:18%"><a href='#' data-type='text' name="lhl_lpxx_${caseFuniHouses.id}" data-original-title='绿化率' class='editable editable-click'>${caseFuniHouses.lhl}</a></td>
                            </tr>
                            <tr>
                                <th>车位信息:</th>
                                <td><a href='#' data-type='text' name="cwxx_lpxx_${caseFuniHouses.id}" data-original-title='车位信息' class='editable editable-click'>${caseFuniHouses.cwxx}</a></td>
                                <th>销售许可证:</th>
                                <td colspan="5">${caseFuniHouses.xsxkz}</td>
                            </tr>
                            <tr>
                                <th>公交:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="gj_lppt_${caseFuniHouses.id}" data-original-title='公交' class='editable editable-click'>${caseFuniHousesMating.gj}</a>
                                </td>
                                <th>地铁:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="dt_lppt_${caseFuniHouses.id}" data-original-title='地铁' class='editable editable-click'>${caseFuniHousesMating.dt}</a>
                                </td>
                            </tr>
                            <tr>
                                <th>幼儿园:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="yey_lppt_${caseFuniHouses.id}" data-original-title='幼儿园' class='editable editable-click'>${caseFuniHousesMating.yey}</a>
                                </td>
                                <th>小学:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="xx_lppt_${caseFuniHouses.id}" data-original-title='小学' class='editable editable-click'>${caseFuniHousesMating.xx}</a>
                                </td>
                            </tr>
                            <tr>
                                <th>中学:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="zx_lppt_${caseFuniHouses.id}" data-original-title='中学' class='editable editable-click'>${caseFuniHousesMating.zx}</a>
                                </td>
                                <th>大学:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="dx_lppt_${caseFuniHouses.id}" data-original-title='大学' class='editable editable-click'>${caseFuniHousesMating.dx}</a>
                                </td>
                            </tr>
                            <tr>
                                <th>医院:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="yy_lppt_${caseFuniHouses.id}" data-original-title='医院' class='editable editable-click'>${caseFuniHousesMating.yy}</a>
                                </td>
                                <th>药店:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="yd_lppt_${caseFuniHouses.id}" data-original-title='药店' class='editable editable-click'>${caseFuniHousesMating.yd}</a>
                                </td>
                            </tr>
                            <tr>
                                <th>商场:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="sc_lppt_${caseFuniHouses.id}" data-original-title='商场' class='editable editable-click'>${caseFuniHousesMating.sc}</a>
                                </td>
                                <th>超市:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="cs_lppt_${caseFuniHouses.id}" data-original-title='超市' class='editable editable-click'>${caseFuniHousesMating.cs}</a>
                                </td>
                            </tr>
                            <tr>
                                <th>市场:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="csc_lppt_${caseFuniHouses.id}" data-original-title='市场' class='editable editable-click'>${caseFuniHousesMating.csc}</a>
                                </td>
                                <th>银行:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="yh_lppt_${caseFuniHouses.id}" data-original-title='银行' class='editable editable-click'>${caseFuniHousesMating.yh}</a>
                                </td>
                            </tr>
                            <tr>
                                <th>餐厅:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="ct_lppt_${caseFuniHouses.id}" data-original-title='餐厅' class='editable editable-click'>${caseFuniHousesMating.ct}</a>
                                </td>
                                <th>咖啡馆:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="kfg_lppt_${caseFuniHouses.id}" data-original-title='咖啡馆' class='editable editable-click'>${caseFuniHousesMating.kfg}</a>
                                </td>
                            </tr>
                            <tr>
                                <th>电影院:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="dyy_lppt_${caseFuniHouses.id}" data-original-title='电影院' class='editable editable-click'>${caseFuniHousesMating.dyy}</a>
                                </td>
                                <th>公园:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="gy_lppt_${caseFuniHouses.id}" data-original-title='公园' class='editable editable-click'>${caseFuniHousesMating.gy}</a>
                                </td>
                            </tr>
                            <tr>
                                <th>健身房:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="jsf_lppt_${caseFuniHouses.id}" data-original-title='健身房' class='editable editable-click'>${caseFuniHousesMating.jsf}</a>
                                </td>
                                <th>体育馆:</th>
                                <td colspan="3"><a href='#' data-type='textarea' name="tyg_lppt_${caseFuniHouses.id}" data-original-title='体育馆' class='editable editable-click'>${caseFuniHousesMating.tyg}</a>
                                </td>
                            </tr>
                            <tr>
                                <th>楼盘介绍:</th>
                                <td colspan="7"><a href='#' data-type='textarea' name="lpjs_wyxx_${caseFuniHouses.id}" data-original-title='楼盘介绍' class='editable editable-click'>${caseFuniHouses.lpjs}</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>物业信息</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <a class="btn btn-success" onclick="addWyxx()">
                        <i class="fa fa-plus"></i>
                        新增物业
                    </a>
                    <div class="table-responsive">
                        <table class="table">
                            <tbody>
                            <c:forEach var="item" items="${caseFuniHousesPropertyList}">
                                <tr>
                                    <th colspan="8"><h3><label class="label label-info">${item.wylx}</label></h3></th>
                                </tr>
                                <tr>
                                    <th style="width:7%">建筑类别:</th>
                                    <td style="width:18%"><a href='#' data-type='text' name="jzlb_wyxx_${item.id}" data-original-title='建筑类别' class='editable editable-click'>${item.jzlb}</a></td>
                                    <th style="width:7%">土地使用年限:</th>
                                    <td style="width:18%"><a href='#' data-type='text' name="tdsynx_wyxx_${item.id}" data-original-title='土地使用年限' class='editable editable-click'>${item.tdsynx}</a>
                                    </td>
                                    <th style="width:7%">总户数:</th>
                                    <td style="width:18%">${item.zhs}</td>
                                    <th style="width:7%">物业费:</th>
                                    <td style="width:18%"><a href='#' data-type='text' name="wyf_wyxx_${item.id}" data-original-title='物业费' class='editable editable-click'>${item.wyf}</a></td>
                                </tr>
                                <tr>
                                    <th>物业公司:</th>
                                    <td><a href='#' data-type='text' name="wygs_wyxx_${item.id}" data-original-title='物业公司' class='editable editable-click'>${item.wygs}</a></td>
                                    <th>营销代理:</th>
                                    <td>${item.yxdl}</td>
                                    <th>占地面积:</th>
                                    <td>${item.zdmj}</td>
                                    <th>建筑面积:</th>
                                    <td>${item.jzmj}</td>
                                </tr>
                                <tr>
                                    <th>供暖方式:</th>
                                    <td colspan="3"><a href='#' data-type='textarea' name="glfs_wyxx_${item.id}" data-original-title='供暖方式' class='editable editable-click'>${item.glfs}</a></td>
                                    <th>供水方式:</th>
                                    <td colspan="3"><a href='#' data-type='textarea' name="gsfs_wyxx_${item.id}" data-original-title='供水方式' class='editable editable-click'>${item.gsfs}</a></td>
                                </tr>
                                <tr>
                                    <th colspan="3">通讯:</th>
                                    <td><a href='#' data-type='textarea' name="tx_wyxx_${item.id}" data-original-title='通讯' class='editable editable-click'>${item.tx}</a></td>
                                    <th colspan="3">网络:</th>
                                    <td><a href='#' data-type='textarea' name="wl_wyxx_${item.id}" data-original-title='网络' class='editable editable-click'>${item.wl}</a></td>
                                </tr>
                                <tr>
                                    <th>层高:</th>
                                    <td><a href='#' data-type='text' name="cg_wyxx_${item.id}" data-original-title='层高' class='editable editable-click'>${item.cg}</a></td>
                                    <th>开盘时间:</th>
                                    <td>${item.kpsj}</td>
                                    <th>交房时间:</th>
                                    <td>${item.jfsj}</td>
                                    <th>户型区间:</th>
                                    <td>${item.hxqj}</td>
                                </tr>
                                <tr>
                                    <th>客梯数:</th>
                                    <td><a href='#' data-type='text' name="kts_wyxx_${item.id}" data-original-title='客梯数' class='editable editable-click'>${item.kts}</a></td>
                                    <th>货梯数:</th>
                                    <td><a href='#' data-type='text' name="hts_wyxx_${item.id}" data-original-title='货梯数' class='editable editable-click'>${item.hts}</a></td>
                                    <th>装修情况:</th>
                                    <td colspan="3"><a href='#' data-type='textarea' name="zxqk_wyxx_${item.id}" data-original-title='装修情况' class='editable editable-click'>${item.zxqk}</a></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="x_panel">
                <div class="x_title collapse-link">
                    <ul class="nav navbar-right panel_toolbox">
                        <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
                    </ul>
                    <h2>户型</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <a class="btn btn-success" onclick="addHxxx()">
                        <i class="fa fa-plus"></i>
                        新增户型
                    </a>
                    <div class="table-responsive" id="tab_content2">
                    </div>
                </div>
            </div>
            <div class="x_panel col-md-">
                <%@include file="/views/share/form_details.jsp" %>
            </div>
        </div>

    </div>

</div>
</body>


<div id="model_wyxx" class="modal fade bs-example-modal-sm" data-backdrop="static" aria-hidden="true"
     role="dialog" data-keyboard="false" tabindex="1" style="display: none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">物业信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="x_content">
                            <form id="frm_wyxx" class="form-horizontal" onsubmit="return false;">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            物业类型
                                        </label>
                                        <div class="col-sm-9">
                                            <select name="wylb" class="form-control">
                                                <option value="住宅">住宅</option>
                                                <option value="商业">商业</option>
                                                <option value="办公">办公</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" onclick="saveWyxx()" class="btn btn-primary">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>


<div id="model_hxxx" class="modal fade bs-example-modal-sm" data-backdrop="static" aria-hidden="true"
     role="dialog" data-keyboard="false" tabindex="1" style="display: none;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">户型信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="x_content">
                            <form id="frm_hxxx" class="form-horizontal" onsubmit="return false;">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            户型
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" placeholder="户型" name="hx" class="form-control"
                                                   maxlength="200">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            面积
                                        </label>
                                        <div class="col-sm-9">
                                            <input type="text" placeholder="面积" name="mj" class="form-control"
                                                   maxlength="200">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">
                    取消
                </button>
                <button type="button" onclick="saveHxxx()" class="btn btn-primary">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>
<%@include file="/views/share/main_footer.jsp" %>
<link href="${pageContext.request.contextPath}/assets/x-editable/css/bootstrap-editable.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/assets/x-editable/js/bootstrap-editable.min.js?v=${assessVersion}"></script>
</html>

<script type="text/javascript">
    $(function () {
        loadHousesTypeList(${caseFuniHouses.id});
        xEditChange();
    })

    function addHxxx() {
        $("#frm_hxxx").clearAll();
        $('#model_hxxx').modal({backdrop: 'static', keyboard: false});
    }

    function saveHxxx() {
        Loading.progressShow();
        var data = formParams("frm_hxxx");
        data["lpbh"] =${caseFuniHouses.id};
        $.ajax({
            url: "${pageContext.request.contextPath}/caseFuniViewer/newHxxx",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $('#model_hxxx').modal('hide');
                    notifySuccess('成功','保存成功');
                    loadHousesTypeList(${caseFuniHouses.id});
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }
    function addWyxx() {
        $("#frm_wyxx").clearAll();
        $('#model_wyxx').modal({backdrop: 'static', keyboard: false});
    }

    function saveWyxx() {
        Loading.progressShow();
        var data = formParams("frm_wyxx");
        data["lpbh"] =${caseFuniHouses.id};
        $.ajax({
            url: "${pageContext.request.contextPath}/caseFuniViewer/newWyxx",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    $('#model_wyxx').modal('hide');
                    notifySuccess('成功','保存成功');
                    window.reload();
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    function xEditChange() {
        $(".editable").editable({
            emptytext: '暂无',
            placement: "bottom",
            url: function (params) {
                var aName = $(this).attr("name").split('_');
                updateFuniData(aName[0], params.value, aName[1], aName[2]);
            }
        });

    }
    function updateFuniData(sName, paramsValue, xxType, id) {
        Loading.progressShow();
        $.ajax({
            url: "${pageContext.request.contextPath}/caseFuniViewer/updateHousesData",
            type: "post",
            dataType: "json",
            data: {
                id: id,
                xxType: xxType,
                keys: sName,
                values: paramsValue
            },
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    notifySuccess('成功','保存成功');
                    reloadFuniList();
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }
    function loadHousesTypeList(lpbh) {
        $.ajax({
            url: "${pageContext.request.contextPath}/caseFuniViewer/getHousesType",
            type: "get",
            dataType: "json",
            data: {
                lpbh: lpbh
            },
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    var html = "";
                    $.each(data, function (i, j) {
                        html += "<div class='col-md-2'>";
                        html += "<div class='thumbnail'>";
                        html += "<div class='image view view-first'>";
                        html += "<img style='width: 100%; display: block;' onclick='showImage(this)' src='" + j.hxt + ".240x1000.jpg' alt='image'>";
                        html += "</div>";
                        html += "<div class='caption'>";
                        html += "<p>" + j.hx + "</p>";
                        html += "<p>" + j.mj + "</p>";
                        html += "</div>";
                        html += "</div>";
                        html += "</div>";
                    });
                    $("#tab_content2").html(html);
                }
                else {
                    AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Loading.progressHide();
                AlertError("失败","调用服务端方法失败，失败原因:" + result.errmsg);
            }
        })
    }

    function showImage(obj) {
        var url = $(obj).attr("src");
        url = url.replace(".240x1000.jpg", "");
        window.open(url);
    }
</script>