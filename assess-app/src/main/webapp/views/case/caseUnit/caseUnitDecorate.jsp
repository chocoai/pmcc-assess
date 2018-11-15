<%--
 楼栋内装
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_panel">
    <div class="x_title collapse-link" onclick="unitDecorate.prototype.viewInit()">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link" ><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h3>楼栋内装信息
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
                    <table class="table table-bordered" id="ExamineUnitDecorateList">
                        <!-- cerare document add ajax data-->
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>

<script type="application/javascript">

    var unitDecorate;
    (function () {
        var flag = true;
        unitDecorate= function () {

        };
        unitDecorate.prototype = {
            setFlag:function (flag_) {
                flag = flag_;
            },
            getFlag:function () {
                return flag;
            },
            viewInit: function () {
                unitDecorate.prototype.loadDataDicList();
            },
            config: function () {
                var data = {};
                data.table = "ExamineUnitDecorateList";
                data.box = "divBoxExamineUnitDecorate";
                data.frm = "frmExamineUnitDecorate";
                data.type = "null";//
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'decorationPartName', title: '装修部位'});
                cols.push({field: 'decoratingMaterialName', title: '装修材料'});
                cols.push({field: 'materialPriceName', title: '材料价格区间'});
                cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
                $("#" + unitDecorate.prototype.config().table).bootstrapTable('destroy');
                TableInit(unitDecorate.prototype.config().table, "${pageContext.request.contextPath}/caseUnitDecorate/getCaseUnitDecorateList", cols, {
                    type: unitDecorate.prototype.config().type,
                    unitId: '${empty caseUnit.id?0:caseUnit.id}'
                }, {
                    showColumns: false,
                    showRefresh: false,
                    search: false,
                    onLoadSuccess: function () {
                        $('.tooltips').tooltip();
                    }
                });
            }

        }
    })();

</script>



</html>

