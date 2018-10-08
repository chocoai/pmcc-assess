<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="landEngineeringModel">
    <jsp:include page="../developmentModule/land/parameter.jsp"></jsp:include>
    <jsp:include page="../developmentModule/land/cost.jsp"></jsp:include>
    <jsp:include page="../developmentModule/land/designParameters.jsp"></jsp:include>
    <jsp:include page="../developmentModule/land/resultView.jsp"></jsp:include>
</div>

<script>
    var landEngineering = new Object();

    landEngineering.config = {
        id: "landEngineeringModel"
    };

    landEngineering.isEmpty = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    landEngineering.loadData = function () {
        // AssessCommon.loadDataDicByKey(AssessDicKey.build_addedvalueadditionaltaxrate, "", function (html, data) {
        //     $("." + landEngineering.config.id).find("select.businessAdditionalTax").html(html);
        // });
    };

    $(function () {
        landEngineering.loadData();
    });
</script>

