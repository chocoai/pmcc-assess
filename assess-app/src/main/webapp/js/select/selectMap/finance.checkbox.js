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
            url: getContextPath() + '/basicEstateTagging/getApplyBatchEstateTaggingsByTableId',
            data: {
                tableId: estateCommon.getEstateId(),
                type: 'estate'
            },
            success: function (result) {
                if (result.ret) {
                    if (result.data.length >= 1) {
                        var data = result.data[0];
                        if (data) {
                            var id = $("#" + matchingFinance.prototype.config().table).closest("form").find('select.category').val();
                            if (id) {
                                AssessCommon.getDataDicInfo(id, function (item) {
                                    var options = {
                                        distance: $(that).closest('form').find('[name=distance]').val(),
                                        lng: data.lng,
                                        lat: data.lat,
                                        type: item.name

                                    };
                                    AssessMatchingFinance.prototype.appendHtml(options);
                                });
                            } else {
                                AlertError("提示","类型必须选择!");
                            }
                        }
                    } else {
                        AlertError("提示","无标记");
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

            assessSearchMap.otherSearch(options.type, options.distance, options, function (data) {
                var html = '<div id="select_matchingfinance_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"aria-hidden="true">';
                html += ' <div class="modal-dialog modal-lg">';
                html += ' <div class="modal-content">';
                
                html += '<div class="modal-header">';
                html += '<h4 class="modal-title">金融机构选择</h4>';


                html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close">';
                html += '<span ria-hidden="true">&times;</span></button>';
                html += '</div>';

                html += '<div class="modal-body">';
                html += '<form class="form-horizontal">';
                html += '<div class="row">';
                html += '<div class="col-md-12">';
                html += '<div class="card-body">';

                html += "<div class='row form-group'>";
                html += "<div class='col-md-12'>";
                html += '<div class="form-inline x-valid">';
                html += "<span class='label label-primary'>" + '全选或全不选' + "</span>";
                html += "<input  name='radio' type='radio'  onclick='assessMatchingFinance.checkedFun(this,true)'>";
                html += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
                html += "<input   name='radio' type='radio'   onclick='assessMatchingFinance.checkedFun(this,false)'>";
                html += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='badge'>记录max20</span>";
                html += '</div>';
                html += '</div>';
                html += '</div>';


                html += AssessMatchingFinance.prototype.write(data);

                html += '</div>';
                html += '</div>';
                html += '</div>';
                html += '</form>';
                html += '</div>';

                html += '<div class="modal-footer">';
                html += '<button type="button" data-dismiss="modal" class="btn btn-default btn-sm">关闭';
                html += '</button>';
                html += '<button type="button" class="btn btn-primary btn-sm" onclick="assessMatchingFinance.save(this)">';
                html += '保存</button>';
                html += '</div>';
                
                html += '</div>';
                html += '</div>';
                html += '</div>';


                
                $(document.body).append(html);
                $('#select_matchingfinance_modal').modal('show');
            });
        } catch (e) {
            console.log(e);
        }
    };

    /**
     * 保存选中的
     * @param this_
     */
    AssessMatchingFinance.prototype.onSelected = function (this_) {
        var item = $(this_).closest('.form-group');
        var data = {
            name: item.find("input[name='name']").val(),
            estateId: estateCommon.getEstateId(),
            distance: item.find("input[name='distance']").val(),
            category: $("#" + matchingFinance.prototype.config().table).closest("form").find('select.category').val()
        };
        distanceGetFun.get(data.distance,function (distance) {
            data.distance = distance ;
            $.ajax({
                url: getContextPath() + "/basicMatchingFinance/saveAndUpdateBasicMatchingFinance",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        notifySuccess('成功','保存成功');
                        matchingFinance.prototype.loadDataDicList();
                        $('#select_matchingfinance_modal').modal('hide');
                    }
                    else {
                        AlertError("保存数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    AlertError("调用服务端方法失败，失败原因:" + result);
                }
            });
        });
    };

    AssessMatchingFinance.prototype.save = function (that) {
        var form = $(that).parent().parent().find('.form-horizontal');
        form.find(":checkbox").each(function (i, n) {
            if ($(this).prop("checked")) {
                AssessMatchingFinance.prototype.onSelected(this);
            }
        });
    };

    AssessMatchingFinance.prototype.write = function (data) {
        var retHtml = "";
        $.each(data.poiList.pois, function (i, item) {
            retHtml += "<div class='row form-group'>";
            retHtml += "<div class='col-md-12'>";
            retHtml += ' <div class="form-inline x-valid">';
            
            
            retHtml += "<div class='col-sm-3'><span class='checkbox-inline'>";
            retHtml += "<input type='checkbox' id='matchingFinance" + i + "' name='name' readonly='readonly' value='" + item.name + "' onclick=''" + ">";
            retHtml += "<label for='matchingFinance" + i + "'>" + item.name + "</label>";
            retHtml += '</span></div>';
            
            retHtml += "<label class='col-sm-1 control-label'>距离</label>";
            retHtml += "<div class='col-sm-2'>";
            retHtml += "<input type='text' class='form-control input-full' name='distance' readonly='readonly' value='" + item.distance + "'" + ">";
            retHtml += "</div>";

            retHtml += "<label class='col-sm-1 control-label'>地址</label>";
            retHtml += "<div class='col-sm-5'>";
            retHtml += "<label class='form-control input-full theLine'>" + item.address + "</label>";
            retHtml += "</div>";

            retHtml += '</div>';
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
        var form = $(that).closest('.form-horizontal');
        if (flag) {//全选或者全不选
            var number = 2;
            form.find(":checkbox").each(function (i, n) {
                if ($(this).prop("checked")) {
                    number++;
                }
            });
            if (number == 2) {
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