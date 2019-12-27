<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${not empty projectPlanResponsibility and projectPlanResponsibility.pid==0}">
    <div class="x_panel">
        <div class="x_content">
            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4    col-xs-offset-5 col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
                <button id="cancel_btn" class="btn btn-default" onclick="window.close()">
                    取消
                </button>
                <c:choose>
                    <c:when test="${projectPhase.bisUseBox eq false}">
                        <button  class="btn btn-success" onclick="submit(false);">
                            直接提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                        <button  class="btn btn-primary" onclick="submit(true);">
                            提交审批<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button  class="btn btn-success" onclick="submit();">
                            提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${processInsId ne '0'}">
    <div class="x_panel">
        <div class="x_content">
            <div class=" col-xs-4  col-sm-4  col-md-4  col-lg-4    col-xs-offset-5 col-sm-offset-5 col-md-offset-5 col-lg-offset-5">
                <button  class="btn btn-default" onclick="window.close()">
                    取消
                </button>
                <button  class="btn btn-success" onclick="submit();">
                    提交<i style="margin-left: 10px" class="fa fa-arrow-circle-right"></i>
                </button>
            </div>
        </div>
    </div>
</c:if>