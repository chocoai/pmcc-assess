
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_title">
    <h4> 楼地面明细
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
            <table class="table table-bordered" id="FlooringList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</form>


<script type="application/javascript">
    $(function () {
        flooring.prototype.loadDataDicList();
    })
    var flooring;
    (function () {
        flooring = function () {
        };
        flooring.prototype = {
            config: function () {
                var data = {};
                data.table = "FlooringList";
                data.index = 4;
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'typeName', title: '类型', width: '15%'});
                cols.push({field: 'entityConditionName', title: '实体状况', width: '15%'});
                cols.push({field: 'entityConditionContent', title: '状况内容', width: '60%'});
                $("#" + flooring.prototype.config().table).bootstrapTable('destroy');
                TableInit(flooring.prototype.config().table, "${pageContext.request.contextPath}/print/getHouseDamagedDegreeDetailList", cols, {
                    index: flooring.prototype.config().index,
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




