<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-4-24
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%--二维码问题处理--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row form-group">
    <div class="col-md-12">
        <div class="form-inline x-valid">
            <div class="col-sm-11">
                <div>
                    <script src="${pageContext.request.contextPath}/assets/qrcodejs/qrcode.js?v=${assessVersion}"></script>
                    <div id="canvasQRcodeModel"></div>
                </div>
            </div>
        </div>
    </div>
</div>


<script>

    var canvasQRcode = {};
    canvasQRcode.targetId = "canvasQRcodeModel";


    $(document).ready(function () {
        var url = window.location.href ;
        url = url.replace(/informationEdit/g, 'informationPhoneEdit');



        var qrcode = new QRCode(document.getElementById(canvasQRcode.targetId), {
            width: 150,
            height: 150
        });
        try {
            //二维码 canvas 生成
            qrcode.makeCode(url);
            console.log(url) ;
        } catch (ex) {
            console.log(ex) ;
        }
    });
</script>