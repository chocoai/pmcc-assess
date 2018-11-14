<%--
  车位信息
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="estateParking.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>车位信息
        </h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="x-valid">
                </div>
            </div>
            <div class="form-group">
                <div class="x-valid">
                    <table class="table table-bordered" id="estateParkingList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    $(function () {
        estateParking.prototype.fileUpload();
    });

    var estateParking;
    (function () {
        var flag = true;
        var fileID = null;
        estateParking = function () {

        };
        estateParking.prototype = {
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            setFileID:function (id_) {
                fileID = id_;
            },
            getFileID:function () {
                if (fileID == null || fileID == ''){
                    return 0;
                }
                return fileID;
            },
            viewInit: function () {
                estateParking.prototype.loadDataDicList();
            },
            fileUpload:function () {
                FileUtils.uploadFiles({
                    target:estateParking.prototype.config().fileIDName,
                    disabledTarget: "btn_submit",
                    onUpload: function (file) {
                        var formData={
                            fieldsName:estateParking.prototype.config().fileIDName,
                            tableName: AssessDBKey.CaseEstateParking,
                            tableId: estateParking.prototype.getFileID()
                        };
                        return formData;
                    },onUploadComplete:function () {
                        estateParking.prototype.showFile();
                    },
                    deleteFlag: true
                });
            },
            showFile:function () {
                FileUtils.getFileShows({
                    target: estateParking.prototype.config().fileIDName,
                    formData: {
                        fieldsName:estateParking.prototype.config().fileIDName,
                        tableName: AssessDBKey.CaseEstateParking,
                        tableId: estateParking.prototype.getFileID(),
                        projectId: 0
                    },
                    deleteFlag: true
                })
            },
            config: function () {
                var data = {};
                data.table = "estateParkingList";
                data.box = "divBoxEstateParking";
                data.frm = "frmEstateParking";
                data.fileIDName = "house_estateParking" ;//ExamineFileUpLoadFieldEnum
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'parkingTypeName', title: '车位类型'});
                cols.push({field: 'location', title: '车辆位置'});
                cols.push({field: 'fileViewName', title: '上传的附件'});
                $("#" + estateParking.prototype.config().table).bootstrapTable('destroy');
                TableInit(estateParking.prototype.config().table, "${pageContext.request.contextPath}/caseEstateParking/getCaseEstateParkingList", cols, {
                    estateId: ${empty caseEstate.id?0:caseEstate.id},
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            },

        }
    })();

</script>



</html>


