//医疗 高德地图抓取周边医疗数据

(function ($) {
    var AssessMedical = function () {

    };

    /**
     * 获取要查询的参数如:经纬度以及抓取数据后为选择做必要的处理
     * @param that
     */
    AssessMedical.prototype.select = function (that) {
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
                            var options = {
                                distance: $(that).closest('form').find('[name=distance]').val(),
                                lng: data.lng,
                                lat: data.lat
                            };
                            AssessMedical.prototype.appendHtml(options);
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
    AssessMedical.prototype.appendHtml = function (options) {
        var target = $("#select_Medical_modal");
        if (target.length > 0) {
            $("#select_Medical_modal").remove();
        }
        try {
            assessSearchMap.localUseTypeSearch('医院', options.distance, options, function (data) {
                var html = '<div id="select_Medical_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" ';
                html += 'role="dialog" data-keyboard="false" tabindex="1" >';
                html += '<div class="modal-dialog  modal-lg">';
                html += '<div class="modal-content">';
                html += '<div class="modal-header">';

                html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span';
                html += 'aria-hidden="true">&times;</span></button>';
                html += '<h3 class="modal-title">医疗选择 &nbsp;&nbsp;&nbsp;&nbsp;';
                html += "<span class='label label-primary'>" + '全选或全不选' + "</span>";
                html += "<input type='checkbox' onclick='assessMatchingMedical.checkedFun(this,true)'>";
                html += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='label label-primary'>" + '反选' + "</span>";
                html += "<input type='checkbox' onclick='assessMatchingMedical.checkedFun(this,false)'>";
                html += "&nbsp;&nbsp;&nbsp;&nbsp;<span class='badge'>记录max20</span>";
                html += "&nbsp;&nbsp;&nbsp;&nbsp;<input type='button' class='btn btn-success' value='保存选中选项' onclick='assessMatchingMedical.save(this)'>" ;
                html += "</h3>";
                html += '</div>';

                html += "<form class='form-horizontal'>";
                html += '<div class="modal-body">';

                html += "<div class='row'>";
                html += "<div class='col-md-12'>";
                html += "<div class='panel-body'>";
                html += AssessMedical.prototype.write(data);
                html += '</div>';
                html += '</div>';
                html += '</div>';

                html += '</div>';
                html += "</form>";
                html += '</div>';
                html += '</div>';

                $(document.body).append(html);
                $('#select_Medical_modal').modal('show');
            });
        } catch (e) {
            console.log(e);
        }
    };

    /**
     * 保存选中的
     * @param this_
     */
    AssessMedical.prototype.onSelected = function (this_) {
        var item = $(this_).closest('.form-group');
        var data = {
            organizationName: item.find("input[name='name']").val(),
            distance: item.find("input[name='distance']").val(),
            theLine: item.find("label.address").text(),
            estateId: estateCommon.getEstateId()
        };
        AssessCommon.loadDataDicByKey(AssessDicKey.estate_distance, null, function (html, n) {
            var a500 = {};
            var a1000 = {};
            var a1500 = {};
            var a2000 = {};
            var a2000Max = {};
            $.each(n, function (i, v) {
                var number = AssessMedical.prototype.getNumber(v.name);
                number = Number(number);
                if (v.name == '小于等于500m') {
                    a500.number = number;
                    a500.id = v.id;
                }
                if (v.name == '小于等于1000m') {
                    a1000.number = number;
                    a1000.id = v.id;
                }
                if (v.name == '小于等于1500m') {
                    a1500.number = number;
                    a1500.id = v.id;
                }
                if (v.name == '小于等于2000m') {
                    a2000.number = number;
                    a2000.id = v.id;
                }
                if (v.name == '大于2000m') {
                    a2000Max.number = number;
                    a2000Max.id = v.id;
                }
            });
            if (data.distance < a500.number) {
                data.distance = a500.id;
            }else if (a500.number > data.distance < a1000.number){
                data.distance = a1000.id;
            }else if (a1000.number > data.distance < a1500.number){
                data.distance = a1500.id;
            }else if (a1500.number > data.distance < a2000.number){
                data.distance = a2000.id;
            }else if(data.distance > a2000Max.number){
                data.distance = a2000Max.id;
            }
            $.ajax({
                url: getContextPath() + "/basicMatchingMedical/saveAndUpdateBasicMatchingMedical",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        toastr.success('保存成功');
                        matchingMedical.prototype.loadDataDicList();
                        $('#select_Medical_modal').modal('hide');
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

    AssessMedical.prototype.save = function (that) {
        var form = $(that).parent().parent().next();
        form.find(":checkbox").each(function (i, n) {
            if ($(this).prop("checked")) {
                AssessMedical.prototype.onSelected(this);
            }
        });
    };

    AssessMedical.prototype.write = function (data) {
        var retHtml = "";
        $.each(data.poiList.pois, function (i, item) {
            retHtml += "<div class='form-group'>";
            retHtml += "<div class='col-sm-3'><span class='checkbox-inline'>";
            retHtml += "<input type='checkbox' id='matchingMedical" + i + "' name='name' readonly='readonly' value='" + item.name + "' onclick=''" + ">";
            retHtml += "<label for='matchingMedical" + i + "'>" + item.name + "</label>";
            retHtml += '</span></div>';
            retHtml += "<label class='col-sm-1 control-label'>距离</label>";
            retHtml += "<div class='col-sm-2'>";
            retHtml += "<input type='text' class='form-control' name='distance' readonly='readonly' value='" + item.distance + "'" + ">";
            retHtml += "</div>";

            retHtml += "<label class='col-sm-1 control-label'>位置</label>";
            retHtml += "<div class='col-sm-5'>";
            retHtml += "<label class='form-control address'>" + item.address + "</label>";
            retHtml += "</div>";

            retHtml += '</div>';
        });
        return retHtml;
    };

    /**
     * 截图字符串中的数字
     */
    AssessMedical.prototype.getNumber = function (str) {
        var reg = /[1-9][0-9]*/g;
        return str.match(reg)[0];
    };

    /**
     *
     * @param that
     * @param flag true 表示全选或者全不选,否则表示反选
     */
    AssessMedical.prototype.checkedFun = function (that, flag) {
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


    window.assessMatchingMedical = new AssessMedical();
})(jQuery);