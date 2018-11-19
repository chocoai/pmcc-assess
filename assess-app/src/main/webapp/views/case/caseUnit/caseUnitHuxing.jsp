<%--
 户型
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="unitHuxing.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>户型信息
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content collapse">
        <form id="frm_unitHuxing" class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="UnitHuxingList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">
    $(function () {
        unitHuxing.prototype.fileUpload();

    });
    var unitHuxing;
    (function () {
        var flag = true;
        var fileID = null;
        unitHuxing = function () {
        };
        unitHuxing.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            setFileID: function (id_) {
                fileID = id_;
            },
            getFileID: function () {
                if (fileID == null || fileID == '') {
                    return 0;
                }
                return fileID;
            },
            viewInit: function () {

                unitHuxing.prototype.loadDataDicList();
            },
            fileUpload: function () {
                FileUtils.uploadFiles({
                    target: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                    disabledTarget: "btn_submit",
                    onUpload: function (file) {
                        var formData = {
                            fieldsName: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                            tableName: AssessDBKey.CaseUnitHuxing,
                            tableId: unitHuxing.prototype.getFileID()
                        };
                        return formData;
                    }, onUploadComplete: function () {
                        unitHuxing.prototype.showFile();
                    },
                    deleteFlag: true
                });
            },
            showFile: function () {
                FileUtils.getFileShows({
                    target: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                    formData: {
                        fieldsName: unitHuxing.prototype.config().unitHuxingFileIDFildName,
                        tableName: AssessDBKey.CaseUnitHuxing,
                        tableId: unitHuxing.prototype.getFileID(),
                        projectId: 0
                    },
                    deleteFlag: true
                })
            },
            config: function () {
                var data = {};
                data.table = "UnitHuxingList";
                data.box = "divBoxUnitHuxing";
                data.frm = "frmUnitHuxing";
                data.type = "null";//
                data.unitHuxingFileIDFildName = "house_latest_family_planV";//根据 ExamineFileUpLoadFieldEnum 配置
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'description', title: '描述'});
                cols.push({field: 'spanLength', title: '跨长'});
                cols.push({field: 'orientation', title: '朝向'});
                cols.push({field: 'spanWidth', title: '跨宽'});
                cols.push({field: 'spanNumber', title: '跨数'});
                cols.push({field: 'fileViewName', title: '户型图'});
                cols.push({
                    field: 'houseCategory', title: '房型', formatter: function (value, row, index) {
                        var str = "";
                        if (unitHuxing.prototype.isNotNull(row.houseCategory)){
                            str = unitHuxing.prototype.rule("formatter",JSON.parse(row.houseCategory));
                        }
                        return str;
                    }
                });

                $("#" + unitHuxing.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitHuxing.prototype.config().table, "${pageContext.request.contextPath}/caseUnitHuxing/getCaseUnitHuxingList", cols, {
                    type: unitHuxing.prototype.config().type,
                    unitId: '${empty caseUnit.id?0:caseUnit.id}'
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },

            /**
             * @author:  zch
             * 描述:户型的类别填写方式【】房【】厅【】厨【】卫【】花园【】阳台
             * @date:
             **/
            rule: function (flag, item) {
                var text = "";
                //格式化
                if (flag == "formatter") {
                    if (unitHuxing.prototype.isNotNull(item.house)) {
                        text += item.house + "房-";
                    }
                    if (unitHuxing.prototype.isNotNull(item.saloon)) {
                        text += item.saloon + "客厅-";
                    }
                    if (unitHuxing.prototype.isNotNull(item.kitchen)) {
                        text += item.kitchen + "厨房-";
                    }
                    if (unitHuxing.prototype.isNotNull(item.toilet)) {
                        text += item.toilet + "卫生间-";
                    }
                    if (unitHuxing.prototype.isNotNull(item.garden)) {
                        text += item.garden + "花园-";
                    }
                    if (unitHuxing.prototype.isNotNull(item.balcony)) {
                        text += item.balcony + "阳台";
                    }
                    return text;
                }
                //转为json存入数据库
                if (flag == "get"){
                    var data = {};
                    if (unitHuxing.prototype.isNotNull(item.house)) {
                        data.house = item.house;
                    }
                    if (unitHuxing.prototype.isNotNull(item.saloon)) {
                        data.saloon = item.saloon;
                    }
                    if (unitHuxing.prototype.isNotNull(item.kitchen)) {
                        data.kitchen = item.kitchen;
                    }
                    if (unitHuxing.prototype.isNotNull(item.toilet)) {
                        data.toilet = item.toilet;
                    }
                    if (unitHuxing.prototype.isNotNull(item.garden)) {
                        data.garden = item.garden;
                    }
                    if (unitHuxing.prototype.isNotNull(item.balcony)) {
                        data.balcony = item.balcony;
                    }
                    return JSON.stringify(data);
                }
                //赋值
                if (flag == "set"){
                    if (unitHuxing.prototype.isNotNull(item.house)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='house']").val(item.house);
                    }
                    if (unitHuxing.prototype.isNotNull(item.saloon)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='saloon']").val(item.saloon);
                    }
                    if (unitHuxing.prototype.isNotNull(item.kitchen)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='kitchen']").val(item.kitchen);
                    }
                    if (unitHuxing.prototype.isNotNull(item.toilet)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='toilet']").val(item.toilet);
                    }
                    if (unitHuxing.prototype.isNotNull(item.garden)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='garden']").val(item.garden);
                    }
                    if (unitHuxing.prototype.isNotNull(item.balcony)) {
                        $("#" + unitHuxing.prototype.config().frm + " input[name='balcony']").val(item.balcony);
                    }
                }
            },
            isNotNull: function (item) {
                if (item) {
                    return true;
                }
                return false;
            }

        }
    })();

</script>



</html>

