<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-12" style="text-align: center;padding-bottom: 1.25rem">
    <button class="btn btn-default " style="margin-left: 10px;" onclick="window.close()">
        取消
    </button>
    <c:choose>
        <c:when test="${projectPhase.bisUseBox eq false}">
            <button  class="btn btn-success" style="margin-left: 10px;" onclick="submit(false);">
                直接提交
            </button>
            <button  class="btn btn-primary" style="margin-left: 10px;" onclick="submit(true);">
                提交审批
            </button>
        </c:when>
        <c:otherwise>
            <button  class="btn btn-primary" style="margin-left: 10px;" onclick="submit();">
                提交
            </button>
        </c:otherwise>
    </c:choose>
</div>