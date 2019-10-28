//购物商场 高德地图抓取周边购物商场数据

(function ($) {
    var AssessMatchingMarket = function () {

    };

    /**
     * 获取要查询的参数如:经纬度以及抓取数据后为选择做必要的处理
     * @param that
     */
    AssessMatchingMarket.prototype.select = function (that) {
        $.ajax({
            url: getContextPath() + '/toolMapHandle/getToolMapHandleListByExample',
            data: {
                tableId: estateCommon.getEstateId(),
                type: 'estate'
            },
            method:"get",
            success: function (result) {
                if (result.ret) {
                    if (result.data.length >= 1) {
                        var data = result.data[0];
                        if (data) {
                            var id = $("#" + matchingMarket.prototype.config().table).closest("form").find('select.category').val() ;
                            if (id){
                                AssessCommon.getDataDicInfo(id,function (item) {
                                    var options = {
                                        distance: $(that).closest('form').find('[name=distance]').val(),
                                        lng: data.lng,
                                        lat: data.lat,
                                        type:item.name

                                    };
                                    AssessMatchingMarket.prototype.appendHtml(options);
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
    AssessMatchingMarket.prototype.appendHtml = function (options) {
        var target = $("#select_matchingMarket_modal");
        if (target.length > 0) {
            $("#select_matchingMarket_modal").remove();
        }
        try {
            assessSearchMap.otherSearch(options.type, options.distance, options, function (data) {
                var html = '<div id="select_matchingMarket_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" ';
                html += 'role="dialog" data-keyboard="false" tabindex="1" >';
                html += '<div class="modal-dialog  modal-lg">';
                html += '<div class="modal-content">';
                html += '<div class="modal-header">';

                html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span';
                html += 'aria-hidden="true">&times;</span></button>';
                html += '<h3 class="modal-title">商场选择 &nbsp;&nbsp;&nbsp;&nbsp;';
                html += "<span class='label label-primary'>" + '全选或全不选' + "</span>";
                html += "<input type='checkbox' onclick='assessMatchingMarket.checkedFun(this,true)'>";
                html += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
                html += "<input type='checkbox' onclick='assessMatchingMarket.checkedFun(this,false)'>";
                html += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='badge'>记录max20</span>";
                html += "&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' class='btn btn-success' value='保存选中选项' onclick='assessMatchingMarket.save(this)'>" ;
                html += "</h3>";
                html += '</div>';

                html += "<form class='form-horizontal'>";
                html += '<div class="modal-body">';

                html += "<div class='row'>";
                html += "<div class='col-xs-12 col-sm-12 col-md-12 col-lg-12'>";
                html += "<div class='panel-body'>";
                html += AssessMatchingMarket.prototype.write(data);
                html += '</div>';
                html += '</div>';
                html += '</div>';

                html += '</div>';
                html += "</form>";
                html += '</div>';
                html += '</div>';

                $(document.body).append(html);
                $('#select_matchingMarket_modal').modal('show');
            });
        } catch (e) {
            console.log(e);
        }
    };

    /**
     * 保存选中的
     * @param this_
     */
    AssessMatchingMarket.prototype.onSelected = function (this_) {
        var item = $(this_).closest('.form-group');
        var data = {
            name: item.find("input[name='name']").val(),
            estateId: estateCommon.getEstateId(),
            distance: item.find("input[name='distance']").val(),
            category: $("#" + matchingMarket.prototype.config().table).closest("form").find('select.category').val(),
            type:'matchingMarket' //根据 ExamineMatchingLeisurePlaceTypeEnum 配置
        };
        distanceGetFun.get(data.distance,function (distance) {
            data.distance = distance ;
            $.ajax({
                url: getContextPath() + "/basicMatchingLeisurePlace/saveAndUpdateBasicMatchingLeisurePlace",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        matchingMarket.prototype.loadDataDicList();
                        $('#select_matchingMarket_modal').modal('hide');
                    }
                    else {
                        Alert("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            });
        });
    };

    AssessMatchingMarket.prototype.save = function (that) {
        var form = $(that).parent().parent().next();
        form.find(":checkbox").each(function (i, n) {
            if ($(this).prop("checked")) {
                AssessMatchingMarket.prototype.onSelected(this);
            }
        });
    };

    AssessMatchingMarket.prototype.write = function (data) {
        var retHtml = "";
        $.each(data.poiList.pois, function (i, item) {
            retHtml += "<div class='form-group'>";
            retHtml += "<div class='col-xs-3 col-sm-3 col-md-3 col-lg-3'><span class='checkbox-inline'>";
            retHtml += "<input type='checkbox' id='matchingMarket" + i + "' name='name' readonly='readonly' value='" + item.name + "' onclick=''" + ">";
            retHtml += "<label for='matchingMarket" + i + "'>" + item.name + "</label>";
            retHtml += '</span></div>';
            retHtml += "<label class='col-xs-1 col-sm-1 col-md-1 col-lg-1 control-label'>距离</label>";
            retHtml += "<div class='col-xs-2 col-sm-2 col-md-2 col-lg-2'>";
            retHtml += "<input type='text' class='form-control' name='distance' readonly='readonly' value='" + item.distance + "'" + ">";
            retHtml += "</div>";

            retHtml += "<label class='col-xs-1 col-sm-1 col-md-1 col-lg-1 control-label'>地址</label>";
            retHtml += "<div class='col-xs-5 col-sm-5 col-md-5 col-lg-5'>";
            retHtml += "<label class='form-control theLine'>" + item.address + "</label>";
            retHtml += "</div>";
            retHtml += '</div>';
        });
        return retHtml;
    };

    /**
     * 截图字符串中的数字
     */
    AssessMatchingMarket.prototype.getNumber = function (str) {
        var reg = /[1-9][0-9]*/g;
        return str.match(reg)[0];
    };

    /**
     *
     * @param that
     * @param flag true 表示全选或者全不选,否则表示反选
     */
    AssessMatchingMarket.prototype.checkedFun = function (that, flag) {
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


    window.assessMatchingMarket = new AssessMatchingMarket();
})(jQuery);