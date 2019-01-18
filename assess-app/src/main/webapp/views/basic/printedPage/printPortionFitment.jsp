<%--
 房间
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_title">
    <h4> 装修完损部分
    </h4>
    <div class="clearfix"></div>
</div>
<form class="form-horizontal">
    <div class="form-group">
        <div class="x-valid">
        </div>
    </div>
    <div class="form-group">
        <div class="x-valid">
            <table class="table table-bordered" id="FitmentPortionList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</form>


<script type="application/javascript">
    $(function () {
        fitmentPortion.prototype.loadDataDicList();
    })
    var fitmentPortion;
    (function () {
        fitmentPortion = function () {
        };
        fitmentPortion.prototype = {
            config: function () {
                var data = {};
                data.table = "FitmentPortionList";
                data.type = AssessDicKey.damaged_degree_decoration_part;//装修部分
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'categoryName', title: '类型', width: '15%'});
                cols.push({field: 'entityConditionName', title: '实体状况', width: '15%'});
                cols.push({field: 'entityConditionContent', title: '状况内容', width: '60%'});
                $("#" + fitmentPortion.prototype.config().table).bootstrapTable('destroy');
                TableInit(fitmentPortion.prototype.config().table, "${pageContext.request.contextPath}/print/getHouseDamagedDegreeList", cols, {
                    type: fitmentPortion.prototype.config().type,
                    houseId: '${empty basicHouse.id?0:basicHouse.id}'
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




