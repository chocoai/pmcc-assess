var FormFieldConfig = {
    selectChange: function (obj) {

    },

    //选择部门
    selectDepartment: function (_this) {
        var selectDom = $(_this);
        var hiddenIdDom = $("#" + selectDom.attr("hiddenid"));
        var currentValue = hiddenIdDom.val();

        loadSelectDept(0, currentValue, false, function (nodes) {
            if (nodes && nodes.length > 0) {
                hiddenIdDom.val(nodes[0].id);
                selectDom.val(nodes[0].text);
            } else {
                hiddenIdDom.val('');
                selectDom.val('');
            }
        });
    },

    //选择人员
    selectUser: function (_this) {
        var selectDom = $(_this);
        var hiddenIdDom = $("#" + selectDom.attr("hiddenid"));
        var currentValue = hiddenIdDom.val();

        loadSelectEmployee(1, selectDom.val(), true, function (data) {
            if (data.base) {
                hiddenIdDom.val(data.account);
                selectDom.importTags(data.base, false);
            } else {
                hiddenIdDom.val("");
                selectDom.importTags("", false);
            }
        });
    },


    //下拉改变事件
    comboBoxChange: function (_this) {
        var combox = $(_this);
        var name = combox.find("option:selected").text();

        var nameDom = $("#" + _this.id + "_name");
        nameDom.val(name);

        //
        FormFieldConfig.selectChange(combox);
    }

};
