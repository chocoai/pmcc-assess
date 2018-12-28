<%--
 房间
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_title">
    <h4> 其他完损部分
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
            <table class="table table-bordered" id="OtherPortionList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</form>


<script type="application/javascript">
    $(function () {
        otherPortion.prototype.loadDataDicList();
    })
    var otherPortion;
    (function () {
        otherPortion = function () {
        };
        otherPortion.prototype = {
            config: function () {
                var data = {};
                data.table = "OtherPortionList";
                data.type = 1698;//设备部分
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'categoryName', title: '类型', width: '15%'});
                cols.push({field: 'entityConditionName', title: '实体状况', width: '15%'});
                cols.push({field: 'entityConditionContent', title: '状况内容', width: '60%'});
                $("#" + otherPortion.prototype.config().table).bootstrapTable('destroy');
                TableInit(otherPortion.prototype.config().table, "${pageContext.request.contextPath}/print/getHouseDamagedDegreeList", cols, {
                    type: otherPortion.prototype.config().type,
                    houseId: '${empty caseHouse.id?0:caseHouse.id}'
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




