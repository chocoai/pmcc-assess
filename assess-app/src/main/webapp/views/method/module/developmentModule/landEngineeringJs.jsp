
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>

    var landEngineering = {};




    landEngineering.constructionInstallationEngineeringFeeEvent = {
        show:function () {
            var target = $("#boxLandEngineering") ;
            if (target.find(".panel-body").size() != 0){
                target.find(".panel-body").empty() ;
            }
            target.find(".panel-body").append(developmentCommon.architecturalA.getHtml()) ;
            developmentCommon.architecturalA.treeGirdParse(target) ;
            target.modal("show") ;
        },
        save:function () {
            var target = $("#boxLandEngineering") ;
            target.modal("hide") ;
        }
    } ;

</script>