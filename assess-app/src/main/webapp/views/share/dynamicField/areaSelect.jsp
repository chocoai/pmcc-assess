<div class="x-valid">
    <label class="col-sm-1 control-label">
        #{curr_labelName}<span class="symbol required"></span>
    </label>
    <div class="col-sm-3">
        <select id="#{curr_fieldId}" name="#{curr_fieldName}" class="form-control  search-select select2" required="required">
        </select>
    </div>
</div>
<div class="x-valid">
    <label class="col-sm-1 control-label">
        市<span class="symbol required"></span>
    </label>
    <div class="col-sm-3">
        <select id="#{curr_tableName}-city" name="city" class="form-control  search-select select2" required="required">
        </select>
    </div>
</div>
<div class="x-valid">
    <label class="col-sm-1 control-label">
        区县
    </label>
    <div class="col-sm-3">
        <select id="#{curr_tableName}-district" name="district" class="form-control  search-select select2">
        </select>
    </div>
</div>
<script type="text/javascript">

    var areaSelect = {
        provinceId: "#{curr_fieldId}",
        cityId: "#{curr_tableName}-city",
        districtId: "#{curr_tableName}-district",
        loadAreaAdd:function () {
            AssessCommon.initAreaInfo({
                provinceTarget: $("#"+areaSelect.provinceId),
                cityTarget: $("#"+areaSelect.cityId),
                districtTarget:$("#"+areaSelect.districtId)
            })
        },
        loadAreaEdit:function (row) {
            AssessCommon.initAreaInfo({
                useDefaultText: false,
                provinceTarget: $("#"+areaSelect.provinceId),
                cityTarget: $("#"+areaSelect.cityId),
                districtTarget:$("#"+areaSelect.districtId),
                provinceValue: row.province,
                cityValue: row.city,
                districtValue: row.district
            })
        },

        loadProvinceEdit: function (row) {
            $("#" + areaSelect.provinceId).empty();
            $("#city,#district").empty();
            $.ajax({
                url: getContextPath() + "/area/getProvinceList",
                type: "post",
                dataType: "json",
                data: {
                    pid: "0"
                },
                success: function (result) {
                    if (result.ret) {
                        $("#" + areaSelect.provinceId).append("<option value=''>-请选择-</option>");
                        $.each(result.data, function (i, item) {
                            $("#" + areaSelect.provinceId).append("<option value='" + item.areaId + "'>" + item.name + "</option>");
                        });
                        if (row) {
                            $("#" + areaSelect.provinceId).val(row[areaSelect.provinceName]);
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        loadCityEdit: function (row) {
            $("#city").empty();
            $.ajax({
                url: getContextPath() + "/area/getAreaList",
                type: "post",
                dataType: "json",
                data: {
                    pid: row[areaSelect.provinceName]
                },
                success: function (result) {
                    if (result) {
                        $("#city").append("<option value=''>-请选择-</option>");
                        $.each(result, function (i, item) {
                            $("#city").append("<option value='" + item.areaId + "'>" + item.name + "</option>");
                        });
                        if (row) {
                            $("#city").val(row.city);
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        loadDistrictEdit: function (row) {
            $("#district").empty();
            $.ajax({
                url: getContextPath() + "/area/getAreaList",
                type: "post",
                dataType: "json",
                data: {
                    pid: row.city
                },
                success: function (result) {
                    if (result) {
                        $("#district").append("<option value=''>-请选择-</option>");
                        $.each(result, function (i, item) {
                            $("#district").append("<option value='" + item.areaId + "'>" + item.name + "</option>");
                        });
                        if (row) {
                            $("#district").val(row.district);
                        }
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        }
    };

    $(function () {
        beforeAddFunction["#{curr_tableName}"].push(areaSelect["loadAreaAdd"]);
        beforeEditFunction["#{curr_tableName}"].push(areaSelect["loadAreaEdit"]);


    });


</script>