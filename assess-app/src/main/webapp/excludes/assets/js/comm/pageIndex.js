/**
 * Created by Calvin on 2017/9/7.
 * 处理页面控件
 */

//文本
function fun1(index) {
    var whereObj = $("#where_" + index);
    whereObj.html("");
    var divTxt = $("#div_txt_" + index);
    divTxt.html("");
    //处理条件
    whereObj.append("<option value='like'>包含</option>");
    whereObj.append("<option value='='>等于</option>");
    //处理控件
    var html = "<input type='text' id='txt_" + index + "' class='form-control'>";
    divTxt.append(html);

}
//长文本
function fun2(index) {
    fun1(index);
}
//整型数字
function fun3(index) {
    fun4(index);
}
//小数
function fun4(index) {
    var whereObj = $("#where_" + index);
    whereObj.html("");
    var divTxt = $("#div_txt_" + index);
    divTxt.html("");
    //处理条件
    whereObj.append("<option value='>'>大于</option>");
    whereObj.append("<option value='>='>大于等于</option>");
    whereObj.append("<option value='<'>小于</option>");
    whereObj.append("<option value='<='>小于等于</option>");
    whereObj.append("<option value='='>等于</option>");
    //处理控件
    var html = "<input type='text' data-rule-number='true' id='txt_" + index + "' class='form-control'>";
    divTxt.append(html);
}



//多选
function fun6(index) {
    fun5(index);
}


//日期
function fun7(index) {
    fun8(index);
}
//时间
function fun8(index) {
    var whereObj = $("#where_" + index);
    whereObj.html("");
    var divTxt = $("#div_txt_" + index);
    divTxt.html("");
    //处理条件
    whereObj.append("<option value='>'>大于</option>");
    whereObj.append("<option value='>='>大于等于</option>");
    whereObj.append("<option value='<'>小于</option>");
    whereObj.append("<option value='<='>小于等于</option>");
    whereObj.append("<option value='='>等于</option>");
    //处理控件
    var html = "<input type='text' id='txt_" + index + "' class='form-control' data-date-format='yyyy-mm-dd hh:ii'>";
    divTxt.append(html);
    $("#txt_" + index).datetimepicker({
        autoclose: true,
        todayBtn: "linked",
        language: "zh-CN",
        minView: 0
    });
}


//人员单选
function fun11(index) {
    fun9(index);
}
//人员多选
function fun9(index) {
    var whereObj = $("#where_" + index);
    whereObj.html("");
    var divTxt = $("#div_txt_" + index);
    divTxt.html("");
    //处理条件
    whereObj.append("<option value='like'>包含</option>");
    whereObj.append("<option value='='>等于</option>");
    //处理控件
    var html = "<input type='hidden' id='txt_" + index + "'>";
    html += "<input type='text' id='txt_" + index + "_name' class='form-control' readonly='readonly'>";
    divTxt.append(html);
    $("#txt_" + index + "_name").bind('click', function () {
        loadSelectEmployee(1, "", false, function (data) {
            $("#txt_" + index).val(data.account);
            $("#txt_" + index + "_name").val(data.name);
        });
    });
}
//部门单选
function fun10(index) {
    fun12(index);
}

//部门多选
function fun12(index) {
    var whereObj = $("#where_" + index);
    whereObj.html("");
    var divTxt = $("#div_txt_" + index);
    divTxt.html("");
    //处理条件
    whereObj.append("<option value='like'>包含</option>");
    whereObj.append("<option value='='>等于</option>");
//处理控件
    var html = "<input type='hidden' id='txt_" + index + "'>";
    html += "<input type='text' id='txt_" + index + "_name' class='form-control' readonly='readonly'>";
    divTxt.append(html);
    $("#txt_" + index + "_name").bind('click', function () {
        loadSelectDept(1, null, true, function (node) {
            var nodeId = "";
            var nodeText = "";
            for (var i = 0; i < node.length; i++) {
                nodeId += node[i].id + ",";
                nodeText += node[i].text + ",";
            }

            $("#txt_" + index).val(nodeId.substring(0, nodeId.length - 1));
            $("#txt_" + index + "_name").val(nodeText.substring(0, nodeText.length - 1));
        });
    });
}
//项目单选
function fun13(index) {
    fun14(index);
}
//项目多选
function fun14(index) {
    var whereObj = $("#where_" + index);
    whereObj.html("");
    var divTxt = $("#div_txt_" + index);
    divTxt.html("");
    //处理条件
    whereObj.append("<option value='like'>包含</option>");
    whereObj.append("<option value='='>等于</option>");

}