<div class="x-valid">
    <label class="col-sm-1 control-label">
        #{curr_labelName}<span class="symbol required"></span>
    </label>
    <div class="col-sm-3">
        <select id="#{curr_fieldId}" name="#{curr_fieldName}" class="form-control  search-select select2" required="required">
            <option selected="selected" value="">-请选择-</option>
        </select>
    </div>
</div>
<div class="x-valid">
    <label class="col-sm-1 control-label">
        市<span class="symbol required"></span>
    </label>
    <div class="col-sm-3">
        <select id="city" name="city" class="form-control  search-select select2" required="required">
            <option selected="selected" value="">请选择</option>
        </select>
    </div>
</div>
<div class="x-valid">
    <label class="col-sm-1 control-label">
        区县
    </label>
    <div class="col-sm-3">
        <select id="district" name="district" class="form-control  search-select select2">
            <option selected="selected" value="">请选择</option>
        </select>
    </div>
</div>
<script type="text/javascript">
    var areaSelect = {
        provinceId: "#{curr_fieldId}",
        provinceName: "#{curr_fieldName}",
        loadProvinceEdit: function (row) {
            $("#" + areaSelect.provinceId).empty();
            $("#city,#district").empty();
            $.ajax({
                url: getContextPath() + "/projectInfo/getAreaList",
                type: "post",
                dataType: "json",
                data: {
                    pid: "0"
                },
                success: function (result) {
                    if (result) {
                        $("#" + areaSelect.provinceId).append("<option value=''>-请选择-</option>");
                        $.each(result, function (i, item) {
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
                url: getContextPath() + "/projectInfo/getAreaList",
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
                url: getContextPath() + "/projectInfo/getAreaList",
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
        beforeAddFunction["#{curr_tableName}"].push(areaSelect.loadProvinceEdit);
        beforeEditFunction["#{curr_tableName}"].push(areaSelect["loadProvinceEdit"]);
        beforeEditFunction["#{curr_tableName}"].push(areaSelect["loadCityEdit"]);
        beforeEditFunction["#{curr_tableName}"].push(areaSelect["loadDistrictEdit"]);

        $("#" + areaSelect.provinceId).select2();
        $("#city").select2();
        $("#district").select2();

        $("#" + areaSelect.provinceId).change(function () {
            $("#city").val(null).trigger("change").empty();
            var that = $(this);
            $.ajax({
                url: getContextPath() + "/projectInfo/getAreaList",
                type: "post",
                dataType: "json",
                data: {
                    pid: that.val()
                },
                success: function (result) {
                    if (result) {
                        $("#city").append("<option value=''>-请选择-</option>");
                        $.each(result, function (i, item) {
                            $("#city").append("<option value='" + item.areaId + "'>" + item.name + "</option>");
                        })
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        });

        $("#city").change(function () {
            $("#district").val(null).trigger("change").empty();
            var that = $(this);
            $.ajax({
                url: getContextPath() + "/projectInfo/getAreaList",
                type: "post",
                dataType: "json",
                data: {
                    pid: that.val()
                },
                success: function (result) {
                    if (result) {
                        $("#district").append("<option value=''>-请选择-</option>");
                        $.each(result, function (i, item) {
                            $("#district").append("<option value='" + item.areaId + "'>" + item.name + "</option>");
                        })
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        });
    });


</script>