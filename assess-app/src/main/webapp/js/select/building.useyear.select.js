/**
 * 楼栋 房屋使用年限
 */
(function ($) {
    var assessBuildingUseYear = {};


    assessBuildingUseYear.config = {
        notIndustry: {key: 0, name: "非工业与仓储"},
        industry: {key: 1, name: "工业与仓储"},
        defaultKey: "0"
    };

    //默认为非工业仓储
    assessBuildingUseYear.key = assessBuildingUseYear.config.defaultKey;

    assessBuildingUseYear.setKey = function (key) {
        this.key = key;
    };

    assessBuildingUseYear.getKey = function () {
        return this.key;
    };

    assessBuildingUseYear.onSelected = function (key,that) {
        var target = $("#select_BuildingUseYear_modal");
        if (target.length > 0) {
            $("#select_BuildingUseYear_modal").remove();
        }
        var html = "" ;
        html += '<div id="select_BuildingUseYear_modal" class="modal fade bs-example-modal-lg" data-backdrop="static" ';
        html += 'role="dialog" data-keyboard="false" tabindex="1" >';
        html += '<div class="modal-dialog  modal-lg">';
        html += '<div class="modal-content">';
        html += '<div class="modal-header">';

        html += '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span';
        html += 'aria-hidden="true">&times;</span></button>';
        html += '<h3 class="modal-title">建筑使用年限 &nbsp;&nbsp;&nbsp;&nbsp;';
        html += "</h3>";
        html += '</div>';

        html += "<form class='form-horizontal'>";
        html += '<div class="modal-body">';
        html += "<div class='row'>";
        html += "<div class='col-xs-12 col-sm-12 col-md-12 col-lg-12'>";
        html += "<div class='panel-body'>";
        //直接传入key的情况
        if (key) {
            if (key == assessBuildingUseYear.config.industry.key) {
                html += assessBuildingUseYear.getIndustryHTML();
            }
            if (key == assessBuildingUseYear.config.notIndustry.key) {
                html += assessBuildingUseYear.getNoIndustryHTML();
            }
            assessBuildingUseYear.setKey(key);
        } else {
            //用setter 方法设置的key
            var key_ = assessBuildingUseYear.getKey();
            if (key_){
                if (key_ == assessBuildingUseYear.config.industry.key) {
                    html += assessBuildingUseYear.getIndustryHTML();
                }
                if (key_ == assessBuildingUseYear.config.notIndustry.key) {
                    html += assessBuildingUseYear.getNoIndustryHTML();
                }
                assessBuildingUseYear.setKey(key_);
            }else {
                html += assessBuildingUseYear.getIndustryHTML();
            }
        }
        html += '</div>';
        html += '</div>';
        html += '</div>';
        html += '</div>';
        html += "</form>";
        html += '</div>';
        html += '</div>';
        $(document.body).append(html);
        $('#select_BuildingUseYear_modal').modal('show');
    };

    /**
     * 获取工业与仓储
     * @returns {string}
     */
    assessBuildingUseYear.getIndustryHTML = function () {
        var retHtml = "" ;
        return retHtml;
    };

    /**
     * 获取非工业与仓储
     * @returns {string}
     */
    assessBuildingUseYear.getNoIndustryHTML = function () {
        var retHtml = "" ;
        AssessCommon.loadAsyncDataDicByKey(AssessDicKey.examine_building_residence_data, null, function (html, data) {
           $.each(data,function (i,n) {
               retHtml += "<div class='form-group'>";

               retHtml += "<div class='col-xs-1 col-sm-1 col-md-1 col-lg-1'><span class='checkbox-inline'>";
               retHtml += "<input type='radio' name='name' readonly='readonly' value='" + n.id + "' onclick='assessBuildingUseYear.save(" + n.id +")'>";
               retHtml += '</span></div>';

               retHtml += "<div class='col-xs-11 col-sm-11 col-md-11 col-lg-11'>";
               retHtml += "<label class='form-control'>" + n.name + "</label>";
               retHtml += "</div>";

               retHtml += '</div>';
           });
        }, false);
        return retHtml;
    };

    assessBuildingUseYear.save = function (that) {
        console.log(that);
        $('#select_BuildingUseYear_modal').modal('hide');
    };

    window.assessBuildingUseYear = assessBuildingUseYear;
})(jQuery);