//金融 高德地图抓取周边金融数据
(function ($) {
    var AssessMatchingFinance = function () {

    };

    /**
     * 获取要查询的参数如:经纬度以及抓取数据后为选择做必要的处理
     * @param that
     */
    AssessMatchingFinance.prototype.select = function (that) {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/getEstateTaggingList',
            data: {
                applyId: basicCommon.getApplyId(),
                type: 'estate'
            },
            success: function (result) {
                if (result.ret) {
                    if (result.data.length >= 1) {
                        var data = result.data[0];
                        if (data) {
                            var id = $("#" + matchingFinance.prototype.config().table).closest("form").find('select.category').val() ;
                            if (id){
                                AssessCommon.getDataDicInfo(id,function (item) {
                                    var options = {
                                        distance: $(that).parent().prev().val(),
                                        lng: data.lng,
                                        lat: data.lat,
                                        type:item.name

                                    };
                                    AssessMatchingFinance.prototype.appendHtml(options);
                                });
                            }else {
                                Alert("类型必须选择!") ;
                            }
                        }
                    } else {
                        Alert("无标记");
                    }
                }
            }
        });
    };

    /**
     * append html
     * @param options
     */
    AssessMatchingFinance.prototype.appendHtml = function (options) {
        var target = $("#select_matchingfinance_modal");
        if (target.length > 0) {
            $("#select_matchingfinance_modal").remove();
        }
        try {
            if (options.type == '信托'){
                Alert("高德地图案例中金融机构类型无信托");
            }else {
                assessSearchMap.localUseTypeSearch(options.type, options.distance, options, function (data) {
                    var html = '<div id="select_matchingfinance_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" ';
                    html += 'role="dialog" data-keyboard="false" tabindex="1" >';
                    html += '<div class="modal-dialog  modal-lg">';
                    html += '<div class="modal-content">';
                    html += '<div class="modal-header">';

                    html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span';
                    html += 'aria-hidden="true">&times;</span></button>';
                    html += '<h3 class="modal-title">金融机构选择 &nbsp;&nbsp;&nbsp;&nbsp;';
                    html += "<span class='label label-primary'>" + '全选或全不选' + "</span>";
                    html += "<input type='checkbox' onclick='assessMatchingFinance.checkedFun(this,true)'>";
                    html += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
                    html += "<input type='checkbox' onclick='assessMatchingFinance.checkedFun(this,false)'>";
                    html += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='badge'>记录max20</span>";
                    html += "&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' class='btn btn-success' value='保存选中选项' onclick='assessMatchingFinance.save(this)'>" ;
                    html += "</h3>";
                    html += '</div>';

                    html += "<form class='form-horizontal'>";
                    html += '<div class="modal-body">';

                    html += "<div class='row'>";
                    html += "<div class='col-md-12'>";
                    html += "<div class='panel-body'>";
                    html += AssessMatchingFinance.prototype.write(data);
                    html += '</div>';
                    html += '</div>';
                    html += '</div>';

                    html += '</div>';
                    html += "</form>";
                    html += '</div>';
                    html += '</div>';

                    $(document.body).append(html);
                    $('#select_matchingfinance_modal').modal('show');
                });
            }
        } catch (e) {
            console.log(e);
        }
    };

    /**
     * 保存选中的
     * @param this_
     */
    AssessMatchingFinance.prototype.onSelected = function (this_) {
        var item = $(this_).parent().parent();
        var data = {
            name: item.find("input[name='name']").val(),
            estateId: estateCommon.getEstateId(),
            category: $("#" + matchingFinance.prototype.config().table).closest("form").find('select.category').val()
        };
        $.ajax({
            url: getContextPath() + "/basicMatchingFinance/saveAndUpdateBasicMatchingFinance",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    matchingFinance.prototype.loadDataDicList();
                    $('#select_matchingfinance_modal').modal('hide');
                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        });
    };

    AssessMatchingFinance.prototype.save = function (that) {
        var form = $(that).parent().parent().next();
        form.find(":checkbox").each(function (i, n) {
            if ($(this).prop("checked")) {
                AssessMatchingFinance.prototype.onSelected(this);
            }
        });
    };

    AssessMatchingFinance.prototype.write = function (data) {
        var retHtml = "";
        $.each(data.poiList.pois, function (i, item) {
            retHtml += "<div class='form-group'>";

            retHtml += "<label class='col-sm-1 control-label'>距离</label>";
            retHtml += "<div class='col-sm-2'>";
            retHtml += "<input type='text' class='form-control' name='distance' readonly='readonly' value='" + item.distance + "'" + ">";
            retHtml += "</div>";

            retHtml += "<label class='col-sm-1 control-label'>地址</label>";
            retHtml += "<div class='col-sm-5'>";
            retHtml += "<label class='form-control theLine'>" + item.address + "</label>";
            retHtml += "</div>";

            retHtml += "<div class='col-sm-3'>";
            retHtml += item.name + "<input type='checkbox' name='name' readonly='readonly' value='" + item.name + "' onclick=''" + ">";
            retHtml += '</div>';

            retHtml += '</div>';
        });
        return retHtml;
    };

    /**
     * 截图字符串中的数字
     */
    AssessMatchingFinance.prototype.getNumber = function (str) {
        var reg = /[1-9][0-9]*/g;
        return str.match(reg)[0];
    };

    /**
     *
     * @param that
     * @param flag true 表示全选或者全不选,否则表示反选
     */
    AssessMatchingFinance.prototype.checkedFun = function (that, flag) {
        var form = $(that).parent().parent().next();
        if (flag) {//全选或者全不选
            var number = 1;
            form.find(":checkbox").each(function (i, n) {
                if ($(this).prop("checked")) {
                    number++;
                }
            });
            if (number == 1) {
                form.find(":checkbox").prop("checked", true);
            } else {
                form.find(":checkbox").prop("checked", false);
            }
        } else {
            //首先让选中的失效
            form.find(":checkbox").each(function (i, n) {
                if ($(this).prop("checked")) {
                    $(this).prop("disabled", true);
                }
            });
            //然后让没有选中的元素设置为选中
            form.find(":checkbox").each(function (i, n) {
                if (!$(this).prop("checked")) {
                    $(this).prop("checked", true);
                }
            });
            //最后是让失效的元素恢复,并且使其不选中
            form.find(":checkbox").each(function (i, n) {
                if ($(this).prop("disabled")) {
                    $(this).prop("disabled", false);
                    $(this).prop("checked", false);
                }
            });
        }
    };


    window.assessMatchingFinance = new AssessMatchingFinance();
})(jQuery);