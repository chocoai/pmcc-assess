<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/10/19
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="cropperImageUpload" class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1"
     data-width="1600" style="display: none;">
    <div class="modal-body">
        <div class="row eg-main">
            <div class="col-sm-10 ">
                <div class="eg-wrapper" id="cropperWrapper" style="width: 1200px;;">
                    <img id="imgCropper" class="cropper" src="/assets/images/back-error-page.jpg" alt="Picture">
                </div>
            </div>
            <div class="col-sm-2">
                <div class="col-sm-12">
                    <p>
                        <span class="btn btn-primary fileinput-button" onclick="$(this).find(':file').get(0).click();">
                    <span>选择图片</span>
                    <input type="file" name="files[]" onchange="CropperUtils.viewImage(this)" id="fileCropper"
                           style="display: none" accept="image/gif,image/jpeg,image/jpg,image/png">
                     </span>
                    </p>
                    <p>
                        <button type="button" class="btn btn-primary" id="btnCropperReset"><i class="fa fa-plus"></i>重置
                        </button>
                    </p>
                    <p>
                        <button type="button" class="btn btn-primary" id="btnCropperZoomPlus"><i class="fa fa-plus"></i>
                            放大
                        </button>
                    </p>
                    <p>
                        <button type="button" class="btn btn-primary" id="btnCropperZoomMin"><i class="fa fa-minus"></i>
                            缩小
                        </button>
                    </p>
                    <p>
                        <button type="button" class="btn btn-primary" id="btnCropperRotateLeft"><i
                                class="fa fa-reply"></i>
                            左旋
                        </button>
                    </p>
                    <p>
                        <button type="button" class="btn btn-primary" id="btnCropperRotateRight"><i
                                class="fa fa-share"></i>
                            右旋
                        </button>
                    </p>
                    <p>
                        <label>宽：</label><input type="text" id="cropperBoxWidth" style="width: 50px">
                    </p>
                    <p>
                        <label>高：</label><input type="text" id="cropperBoxHeight" style="width: 50px;">
                    </p>
                    <p>
                        <button type="button" class="btn btn-primary" id="btnCropperRefresh"><i
                                class="fa fa-refresh"></i> 刷新
                        </button>
                    </p>
                    <p>
                        <button type="button" class="btn btn-success" id="btnCropperSave"><i class="fa fa-save"></i> 保存
                        </button>
                    </p>
                    <p>
                        <button type="button" class="btn btn-close" id="btnCropperClose"><i class="fa fa-times"></i> 关闭
                        </button>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/html">
    <div class="col-md-3 col-sm-4 gallery-img">
        <div class="wrap-image">
            <a class="group1" href="javascript:showImage('{url}')"
               title="image">
                <img src="{url}" alt="" class="img-responsive">
            </a>
            <div class="tools tools-bottom">
                <a href="javascript:deleteImage('{id}')">
                    <i class="clip-close-2"></i>
                </a>
            </div>
        </div>
    </div>
</script>
<script type="text/javascript">
    $(function () {
        var windowWidth = $(window).width();
        var windowHeight = $(window).height();
        $("#cropperImageUpload").attr("data-height", windowHeight - 50);
        $("#cropperWrapper").css({height: windowHeight - 80});
        $("#btnCropperRefresh").click(function () {
            CropperUtils.setBoxSize(parseInt($("#cropperBoxWidth").val()), parseInt($("#cropperBoxHeight").val()));
        })
        $("#btnCropperClose").click(function () {
            $("#cropperImageUpload").modal("hide");
        })
    });
    (function () {
        var $cropper = undefined;
        var cropperFileName = "default.jpg";
        var cropperOption = {
            aspectRatio: 9 / 5,
            preview: ".preview",
            minContainerWidth: 1200,
            minContainerHeight: ($(window).height() - 100),
            cropmove: function (e) {

            },
            cropend: function (e) {

            },
            crop: function (e) {
                var cropBoxData = $cropper.getCropBoxData();
                $("#cropperBoxWidth").val(Math.round(cropBoxData.width));
                $("#cropperBoxHeight").val(Math.round(cropBoxData.height));
            },
            ready: function () {
                this.cropper.crop();
                this.cropper.setAspectRatio(NaN);
            }
        };

        var cropperUtils = {
            //初始化裁剪控件
            init: function (fn) {
                if (!$cropper) {
                    var image = document.getElementById("imgCropper");
                    $cropper = new Cropper(image, cropperOption)
                    $("#btnCropperSave").click(function () {
                        var cropBoxData = $cropper.getCropBoxData();
                        var width = Math.round(cropBoxData.width);
                        var height = Math.round(cropBoxData.height);
                        var base64 = $cropper.getCroppedCanvas({
                            width: width,
                            height: height
                        }).toDataURL('base64');
                        if (fn) {
                            cropperFileName = "(" + width + "x" + height + ")" + cropperFileName;
                            var options = {
                                cropperFileName: cropperFileName,
                                base64: base64,
                                width: width,
                                height: height
                            };
                            $("#cropperImageUpload").modal("hide");
                            fn(options);
                        }
                    })
                    $("#btnCropperReset").click(function () {
                        $cropper.reset();
                    })
                    $("#btnCropperZoomPlus").click(function () {
                        $cropper.zoom(0.1);
                    })
                    $("#btnCropperZoomMin").click(function () {
                        $cropper.zoom(-0.1);
                    })
                    $("#btnCropperRotateLeft").click(function () {
                        $cropper.rotate(90);
                    })
                    $("#btnCropperRotateRight").click(function () {
                        $cropper.rotate(-90);
                    })
                } else {
                    $cropper.reset();
                    cropperFileName = "default.jpg";
                    $(".eg-wrapper img").attr("src", "/assets/images/back-error-page.jpg");
                }
                $("#cropperImageUpload").modal();
            },
            //base64转Blod
            getBlobBydataURI: function (dataURI, type) {
                var binary = atob(dataURI.split(',')[1]);
                var array = [];
                for (var i = 0; i < binary.length; i++) {
                    array.push(binary.charCodeAt(i));
                }
                return new Blob([new Uint8Array(array)], {type: type});
            },
            //本地预览图片
            showImagePreview: function () {
                var file = source.files[0];
                if (window.FileReader) {
                    var fr = new FileReader();
                    fr.onloadend = function (e) {
                        target.attr("src", e.target.result);
                        target.parent().find("img").attr("src", e.target.result);
                    };
                    fr.readAsDataURL(file);
                }
            },
            //本地预览图片
            viewImage: function (source) {
                var URL = window.URL || window.webkitURL, blobURL;
                if (URL) {
                    var files = source.files, file;
                    if (files && files.length) {
                        file = files[0];
                        console.log(file);
                        cropperFileName = file.name;
                        if (/^image\/\w+$/.test(file.type)) {
                            blobURL = URL.createObjectURL(file);
                            $cropper.reset().replace(blobURL);
                            $(source).val('');
                        } else {
                            showMessage('Please choose an image file.');
                        }
                    }
                } else {
                    alert("该浏览器不支持");
                }
            },
            //保存裁剪后的图片
            saveCutImage: function (formData, fn) {
                $.ajax({
                    url: "${pageContext.request.contextPath}/attachment/uploadFileToServer",
                    type: "post",
                    processData: false,
                    contentType: false,
                    data: formData,
                    success: function (result) {
                        Loading.progressHide();
                        if (result.ret) {
                            toastr.success('保存成功');
                            if (fn) {
                                fn(result.data);
                            }
                        }
                        else {
                            Alert("保存数据失败，失败原因:" + result.errmsg);
                        }
                    },
                    error: function (result) {
                        Loading.progressHide();
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            },
            //设置裁剪框大小
            setBoxSize: function (width, height) {
                $cropper.setCropBoxData({width: width, height: height});
            }
        }
        window.CropperUtils = cropperUtils;
    })(jQuery)
</script>