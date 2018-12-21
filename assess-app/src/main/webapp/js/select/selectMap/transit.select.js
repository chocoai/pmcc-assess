(function ($) {
    var AssessTransit = function () {

    };


    AssessTransit.prototype.select = function (that) {
        $.ajax({
            url: getContextPath() + '/basicEstateTagging/getEstateTaggingList',
            data: {
                applyId: basicCommon.getApplyId(),
                type: 'estate'
            },
            success: function (result) {
                if (result.ret && estateCommon.estateMapiframe) {
                    if (result.data.length >= 1) {
                        var data = result.data[0];
                        if (data) {
                            var options = {
                                distance: $(that).parent().prev().val(),
                                lng: data.lng,
                                lat: data.lat
                            };
                            AssessTransit.prototype.appendHtml(options);
                        }
                    } else {
                        Alert("无标记");
                    }
                }
            }
        });
    };

    AssessTransit.prototype.appendHtml = function (options) {
        var target = $("#select_transit_modal");
        if (target.length > 0) {
            $("#select_transit_modal").remove();
        }
        assessSearchMap.transferSearch('公交站', options.distance, options, function (data) {
            var html = '<div id="select_transit_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" ';
            html += 'role="dialog" data-keyboard="false" tabindex="1" >';
            html += '<div class="modal-dialog  modal-lg">';
            html += '<div class="modal-content">';
            html += '<div class="modal-header">';
            html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span';
            html += 'aria-hidden="true">&times;</span></button>';
            html += '<h3 class="modal-title">公交站选择</h3>';
            html += '</div>';
            html += "<form class='form-horizontal'>";
            html += '<div class="modal-body">';

            html += "<div class='row'>";
            html += "<div class='col-md-12'>";
            html += "<div class='panel-body'>";
            html += AssessTransit.prototype.write(data);
            html += '</div>';
            html += '</div>';
            html += '</div>';

            html += '</div>';
            html += "</form>";
            html += '</div>';
            html += '</div>';

            $(document.body).append(html);
            $('#select_transit_modal').modal('show');
        });
    };

    AssessTransit.prototype.onSelected = function (this_) {
        var item = $(this_).parent().parent();
        var theLine = item.find("input[name='theLine']").val();
        var str = theLine.split(";").join(',');
        var data = {
            name: item.find("input[name='name']").val(),
            distance: item.find("input[name='distance']").val(),
            theLine: str,
            estateId: estateCommon.getEstateId(),
            type: "transit"//根据ExamineMatchingTrafficTypeEnum 配置
        };
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_distance, null, function (html, n) {
            $.each(n, function (i, v) {
                var number = AssessTransit.prototype.getNumber(v.name);
                number = Number(number);
            });
        });
        $.ajax({
            url: getContextPath() + "/basicMatchingTraffic/saveAndUpdateBasicMatchingTraffic",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    toastr.success('保存成功');
                    matchingTransit.prototype.loadDataDicList();
                    $('#select_transit_modal').modal('hide');
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

    AssessTransit.prototype.write = function (data) {
        var retHtml = "";
        $.each(data.poiList.pois, function (i, item) {
            retHtml += "<div class='form-group'>";

            retHtml += "<label class='col-sm-1 control-label'>距离</label>";
            retHtml += "<div class='col-sm-2'>";
            retHtml += "<input type='text' class='form-control' name='distance' readonly='readonly' value='" + item.distance + "'" + ">";
            retHtml += "</div>";

            retHtml += "<label class='col-sm-1 control-label'>线路</label>";
            retHtml += "<div class='col-sm-6'>";
            retHtml += "<input type='text' class='form-control' name='theLine' readonly='readonly' value='" + item.address + "'" + ">";
            retHtml += "</div>";

            retHtml += "<div class='col-sm-2'>";
            retHtml += "<input type='checkbox' class='form-control' name='name' readonly='readonly' value='" + item.name + "' onclick='assessTransit.onSelected(this)'" + ">";
            retHtml += '</div>';

            retHtml += '</div>';
        });
        return retHtml;
    };

    /**
     * 截图字符串中的数字
     */
    AssessTransit.prototype.getNumber = function (str) {
        var reg = /[1-9][0-9]*/g;
        return str.match(reg)[0];
    };


    window.assessTransit = new AssessTransit();
})(jQuery);