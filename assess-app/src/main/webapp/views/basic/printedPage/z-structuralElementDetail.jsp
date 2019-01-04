<%--
 房间
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_title">
    <h4> 承重构件明细
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
            <table class="table table-bordered" id="StructuralElementList">
                <!-- cerare document add ajax data-->
            </table>
        </div>
    </div>
</form>


<script type="application/javascript">
    $(function () {
        structuralElement.prototype.loadDataDicList();
    })
    var structuralElement;
    (function () {
        structuralElement = function () {
        };
        structuralElement.prototype = {
            config: function () {
                var data = {};
                data.table = "StructuralElementList";
                data.index = 1;
                return data;
            },
            loadDataDicList: function () {
                var cols = [];
                cols.push({field: 'typeName', title: '类型', width: '15%'});
                cols.push({field: 'entityConditionName', title: '实体状况', width: '15%'});
                cols.push({field: 'entityConditionContent', title: '状况内容', width: '60%'});
                $("#" + structuralElement.prototype.config().table).bootstrapTable('destroy');
                TableInit(structuralElement.prototype.config().table, "${pageContext.request.contextPath}/print/getHouseDamagedDegreeDetailList", cols, {
                    index: structuralElement.prototype.config().index,
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




