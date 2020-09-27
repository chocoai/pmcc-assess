<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
</head>
<body>
<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row mt--2">
                    <!-- 清查内容 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        清查内容
                                        <button type="button" class="btn btn-primary btn-sm" onclick="survey.reinitializeContentList();">重新初始化</button>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form id="frm_asset_inventory_content" class="form-horizontal">
                                    <table id="tb_surveyList">
                                        <thead>
                                        <tr>
                                            <th style="width: 6%">是否一致</th>
                                            <th style="width: 10%">一致性内容</th>
                                            <th style="width: 10%">登记<span
                                                    style="color:red;font-size:10px;">(没有登记信息则填无)</span></th>
                                            <th style="width: 10%">实际</th>
                                            <th style="width: 10%">差异原因</th>
                                            <th style="width: 10%">证明文件</th>
                                            <th style="width: 7%">证明文件附件</th>
                                            <th style="width: 7%">证明人</th>
                                            <th style="width: 8%">调查时间</th>
                                            <th style="width: 8%">确认一致</th>
                                            <th style="width: 6%">操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${surveyAssetInventoryContentVos}" var="item" varStatus="s">
                                            <tr>
                                                <td>
                                                    <div class="x-valid ">
                                                        <div class="form-check" style="justify-content:left">
                                                            <label class="form-check-label">
                                                                <input class="form-check-input" type="checkbox"
                                                                       id="areConsistent${item.id}"
                                                                       onclick="survey.areConsistentEvent(this) ;"
                                                                       name="areConsistent${item.id}"
                                                                       value="一致">
                                                                <span class="form-check-sign">一致</span>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </td>
                                                <input type="hidden" name="id" value="${item.id}">
                                                <input type="hidden" name="infoItemId" value="${item.infoItemId}">
                                                <td>${item.inventoryContentName}</td>
                                                <td>
                                                    <div class="x-valid">
                                                        <input type="text" data-rule-maxlength="50" placeholder="登记"
                                                               required
                                                               id="registration${item.id}"
                                                               onblur="survey.isAgreement(this);"
                                                               name="registration${item.id}" data-name="registration"
                                                               class="form-control input-full "
                                                               value="${item.registration}">
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="x-valid">
                                                        <input type="text" data-rule-maxlength="50" placeholder="实际"
                                                               required
                                                               id="actual${item.id}"
                                                               onblur="survey.isAgreement(this);"
                                                               name="actual${item.id}" data-name="actual"
                                                               class="form-control input-full "
                                                               value="${item.actual}">
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="x-valid show-hide"
                                                         style="display: ${item.areConsistent eq '一致'?'none':'block'}">
                                                        <input type="text" data-rule-maxlength="50" placeholder="差异原因"
                                                               required
                                                               id="differenceReason${item.id}"
                                                               name="differenceReason${item.id}"
                                                               class="form-control input-full"
                                                               value="${item.differenceReason}">
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="x-valid show-hide"
                                                         style="display: ${item.areConsistent eq '一致'?'none':'block'}">
                                                        <input type="text" data-rule-maxlength="50" placeholder="证明文件"
                                                               required
                                                               id="credential${item.id}" name="credential"
                                                               class="form-control input-full"
                                                               value="${item.credential}">
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="show-hide"
                                                         style="display: ${item.areConsistent eq '一致'?'none':'block'}">
                                                        <input id="credentialAccessory${item.id}"
                                                               name="credentialAccessory${item.id}" type="file"
                                                               multiple="false">
                                                        <div id="_credentialAccessory${item.id}"></div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="x-valid show-hide"
                                                         style="display: ${item.areConsistent eq '一致'?'none':'block'}">
                                                        <input type="text" data-rule-maxlength="50" placeholder="证明人"
                                                               required
                                                               id="voucher${item.id}" name="voucher${item.id}"
                                                               class="form-control input-full" value="${item.voucher}">
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="x-valid show-hide"
                                                         style="display: ${item.areConsistent eq '一致'?'none':'block'}">
                                                        <input placeholder="调查时间" id="surveyTime${item.id}"
                                                               name="surveyTime${item.id}" required
                                                               data-date-format="yyyy-mm-dd"
                                                               class="form-control input-full date-picker dbdate"
                                                               readonly="readonly"
                                                               value='<fmt:formatDate value="${item.surveyTime}" pattern="yyyy-MM-dd"/>'>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="x-valid show-hide"
                                                         style="display: ${item.areConsistent eq '一致'?'none':'block'}">
                                                        <select class="form-control input-full"
                                                                id="sureConsistent${item.id}"
                                                                name="sureConsistent${item.id}" required>
                                                            <option value=""></option>
                                                            <option value="一致" ${item.sureConsistent eq '一致'?'selected':''}>
                                                                一致
                                                            </option>
                                                            <option value="不一致" ${item.sureConsistent eq '不一致'?'selected':''}>
                                                                不一致
                                                            </option>
                                                        </select>
                                                    </div>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-xs btn-danger"
                                                            onclick="$(this).closest('tr').find('input').val('') ;">清空
                                                    </button>
                                                </td>
                                            </tr>
                                            <script type="text/javascript">
                                                $(function () {
                                                    //清查内容附件上传和加载
                                                    survey.uploadFileCommon("${item.id}");
                                                    survey.showFileCommon("${item.id}");
                                                    survey.initAgreement('#areConsistent${item.id}', '${item.areConsistent}');
                                                })
                                            </script>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="/views/share/main_footer.jsp" %>
    </div>
</div>
</body>


<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/ajaxfileupload.js?v=${assessVersion}"></script>
<script type="application/javascript">

    var survey = {};

    survey.frm = $("#frm_asset");

    survey.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };

    survey.fileUpload = function (fieldsName, tableName, id, deleteFlag) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                tableName: tableName,
                tableId: id,
                fieldsName: fieldsName,
                // projectId: id
            },
            deleteFlag: deleteFlag
        });
    };

    survey.showFile = function (fieldsName, tableName, id, deleteFlag) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                tableName: tableName,
                tableId: id,
                fieldsName: fieldsName,
                // projectId: id
            },
            deleteFlag: deleteFlag
        })
    };

    survey.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    //公共  赋值 方法
    survey.initFormData = function (form, item, fileArr, bisDetail, tableName, inputArr) {
        var frm = $(form.selector);
        frm.clearAll(['bisCheckOriginal']);
        frm.initForm(item);
        frm.validate();
        if (fileArr) {
            $.each(fileArr, function (i, n) {
                if (bisDetail == false) {
                    survey.showFile(n, tableName, survey.isNotBlank(item.id) ? item.id : '0', false);
                    survey.fileUpload(n, tableName, survey.isNotBlank(item.id) ? item.id : '0', false);
                } else {
                    survey.showFile(n, tableName, survey.isNotBlank(item.id) ? item.id : '0', true);
                    survey.fileUpload(n, tableName, survey.isNotBlank(item.id) ? item.id : '0', true);
                }
            });
        }
        if (inputArr) {
            $.each(inputArr, function (i, n) {
                frm.find("input[name='" + n + "']").val(formatDate(item[n]));
                frm.find("label[name='" + n + "']").html(formatDate(item[n]));
            });
        }
    };

    survey.initSurveyAssetInventoryForm = function (data) {
        var frm = survey.handleJquery(survey.frm);
        //附件
        var arr = ["checkOriginalFile", "paymentStatusFile", "networkFindFile", AssessUploadKey.INVENTORY_PAYMENT_STATUS, AssessUploadKey.INVENTORY_CHECK_ORIGINAL];
        //日期
        var inputArr = ["checkDate"];
        survey.initFormData(frm, data, arr, false, AssessDBKey.SurveyAssetInventory, inputArr);
        if (data.transferLimit) {
            $("#bisLimit").val("是");
        } else {
            $("#bisLimit").val("否");
        }
        AssessCommon.loadDataDicByKey(AssessDicKey.projectSurveyInventoryContentDefaultCheckOriginal, data.findOriginal, function (html, item) {
            frm.find("select[name='findOriginal']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.examineHouseLoadUtility, data.application, function (html, item) {
            frm.find("select[name='application']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.CERTIFICATE_HANDLING_TYPE, data.certificate, function (html, item) {
            frm.find("select[name='certificate']").empty().html(html).trigger('change');
        });
        AssessCommon.loadDataDicByKey(AssessDicKey.projectSurveyInventoryContentDefaultFindMethod, data.findMethod, function (html, item) {
            frm.find("select[name='findMethod']").empty().html(html).trigger('change');
        });
        frm.find("select[name='findMethod']").change(function () {
            var id = $(this).val();
            AssessCommon.getDataDicInfo(id, function (item) {
                if (item.fieldName == AssessDicKey.projectSurveyInventoryContentDefaultFindMethodNetwork) {
                    frm.find("div[data-name=findMethod]").show();
                } else {
                    frm.find("div[data-name=findMethod]").hide();
                }
            });

        });
        AssessCommon.loadNewAsyncDataDicByKey(AssessDicKey.projectSurveyInventoryContentDefaultAffected, data.affected, function (html, item) {
            frm.find("select[name='affected']").empty().html(html).trigger('change');
            if (data.affected) {
                frm.find("select[name='affected']").val(data.affected.split(",")).trigger('change');
            }
        }, true, false);
        AssessCommon.loadNewAsyncDataDicByKey(AssessDicKey.projectSurveyInventoryContentDefaultInfluenceFactor, data.influenceFactor, function (html, item) {
            frm.find("select[name='influenceFactor']").empty().html(html);
            if (data.influenceFactor) {
                frm.find("select[name='influenceFactor']").val(data.influenceFactor.split(",")).trigger('change');
            }
        }, true, false);
        frm.find("select[name='influenceFactor']").change(function () {
            var ids = $(this).val();
            if (!ids) {
                return false;
            }
            var target = frm.find("select[name='influenceFactor']").closest(".form-group");
            var arr = $.grep(ids, function (n, i) {
                if (n) {
                    return true;
                } else {
                    return false;
                }
            });
            $.each(arr, function (i, id) {
                var name = "influenceFactorRemarkText" + id;
                var size = frm.find("[name=" + name + "]").size();
                var html = $("#influenceFactorRemarkTextHtml").html();
                if (size == 0) {
                    AssessCommon.getDataDicInfo(id, function (item) {
                        html = html.replace(/{id}/g, id);
                        html = html.replace(/{name}/g, item.name);
                        target.after(html);
                        var influenceFactorRemarkText = frm.find("input[name=influenceFactorRemarkText]").val();
                        //进行动态赋值对象
                        var obj = {};
                        if (influenceFactorRemarkText) {
                            var testData = influenceFactorRemarkText.split(",");
                            $.each(testData, function (i, str) {
                                var rD = str.split(":");
                                obj[rD[0]] = rD[1];
                            });
                        }
                        if (obj) {
                            var value = obj[name];
                            if (value) {
                                frm.find("textarea[name=" + name + "]").val(value);
                            }
                        }
                    });
                }
            })
        });

        if (data.id) {
            if (data.transferLimit) {
                $("#transferLimit").val(data.transferLimit).trigger('change');
            }
            if (data.entityIsDamage) {
                $("#entityIsDamage").val(data.entityIsDamage).trigger('change');
            }
            if (data.rimIsNormal) {
                $("#rimIsNormal").val(data.rimIsNormal).trigger('change');
            }
            if (data.paymentStatus) {
                $("#paymentStatus").val(data.paymentStatus).trigger('change');
            }
            if (data.zoneDamage) {
                writeHTMLData('zoneProjectName', 'zoneProjectItem', 'zoneBit', data.zoneDamage);
            }
            if (data.entityDamage) {
                writeHTMLData('entityProjectName', 'entityProjectItem', 'entity', data.entityDamage);
            }
            if (data.otherProject) {
                writeHTMLData('otherProjectName', 'otherProjectItem', 'otherProject', data.otherProject);
            }
            writePaymentHTMLData(${surveyAssetInventory.paymentContent});
        }
        try {
            if (data.bisCheckOriginal != null && data.bisCheckOriginal != undefined) {
                if (data.bisCheckOriginal) {
                    frm.find("[name=bisCheckOriginal][value=0]").attr("checked", false);
                    frm.find("[name=bisCheckOriginal][value=1]").attr("checked", true);
                } else {
                    frm.find("[name=bisCheckOriginal][value=0]").attr("checked", true);
                    frm.find("[name=bisCheckOriginal][value=1]").attr("checked", false);
                }
            }
        } catch (e) {
        }
        frm.find("[name=bisCheckOriginal]").change(function () {
            var value = $(this).val();
            if (value == '1') {
                frm.find(".bisCheckOriginalOne").show();
                frm.find(".bisCheckOriginalZero").hide();
            } else {
                frm.find(".bisCheckOriginalOne").hide();
                frm.find(".bisCheckOriginalZero").show();
            }
        });
    };

    survey.getSurveyAssetInventoryById = function (id, callback) {
        Loading.progressShow();
        $.ajax({
            type: "get",
            url: '${pageContext.request.contextPath}' + "/surveyAssetInventory/getSurveyAssetInventoryById",
            data: {id: id},
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    if (result.errmsg) {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                    }
                }
            },
            error: function (result) {
                Loading.progressHide();
                if (result.errmsg) {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                } else {
                    AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                }
            }
        });
    };

    survey.areConsistentEvent = function (_this) {
        var value = $(_this).prop("checked");
        var tr = $(_this).closest('tr');
        if (value) {
            tr.find('.show-hide').hide();
        } else {
            tr.find('.show-hide').show();
        }
    };

    /**
     * 初始化
     */
    survey.initAgreement = function (selector, areConsistent) {
        var tr = $(selector).closest('tr');
        if (areConsistent == "一致") {
            tr.find('.show-hide').hide();
            tr.find("input[type=checkbox]").prop('checked', true);
        } else {
            tr.find('.show-hide').show();
            tr.find("input[type=checkbox]").prop('checked', false);
        }
    };

    /**
     * 验证登记与实际是否一致，如果不一致需填写相关内容
     * @param _this
     */
    survey.isAgreement = function (_this) {
        var value = $(_this).val();
        if (!value) {
            return false;
        }
        var tr = $(_this).closest('tr');
        var registration = $.trim(tr.find('[name^=registration]').val());//登记
        var actual = $.trim(tr.find('[name^=actual]').val());//实际
        if (actual && registration) {
            if (actual == registration) {
                tr.find('.show-hide').hide();
                tr.find("input[type=checkbox]").prop('checked', true);
            } else {
                tr.find('.show-hide').show();
                tr.find("input[type=checkbox]").prop('checked', false);
            }
        }
    };

    //select2 change事件的处理
    survey.influenceFactorEvent = function (_this, id) {
        var frm = $(_this).closest("form");
        var data = formSerializeArray(frm);
        $(_this).closest(".form-group").remove();
        if (data.influenceFactor) {
            var arr = data.influenceFactor.split(",");
            arr = $.grep(arr, function (n, i) {
                return n != id;
            });
            frm.find("select[name='influenceFactor']").val(arr).trigger('change');
        }
    };


    //上传附件通用
    survey.uploadFileCommon = function (tableId) {
        survey.fileUpload("credentialAccessory" + tableId, AssessDBKey.SurveyAssetInventoryContent, tableId, true);
    };

    //显示附件
    survey.showFileCommon = function (tableId) {
        survey.showFile("credentialAccessory" + tableId, AssessDBKey.SurveyAssetInventoryContent, tableId, true);
    };

    //重新初始化
    survey.reinitializeContentList = function () {
        $.post('${pageContext.request.contextPath}/surveyAssetInfoItem/reinitializeContentList', {
            itemId:${assetInfoItem.id}
        }, function (result) {
            if (result.ret) {
                AlertSuccess("成功", "操作成功!", function () {
                    window.location.href = window.location.href;
                });
            } else {
                AlertError("操作失败，失败原因:" + result.errmsg);
            }
        })
    }

    //获取需要保存的数据
    function getFormData() {
        var trs = $("#tb_surveyList").find('tbody tr');
        var dataItem = [];
        $.each(trs, function (i, tr) {
            var item = {};
            item.registration = $(tr).find('[name^="registration"]').val();    //登记面积
            item.actual = $(tr).find('[name^="actual"]').val();//实际面积
            var areConsistentEle = $(tr).find('[name^="areConsistent"]'); //是否一致
            if (areConsistentEle.prop("checked")) {
                item.areConsistent = areConsistentEle.val();
            } else {
                item.areConsistent = '不一致';
            }
            item.differenceReason = $(tr).find('[name^="differenceReason"]').val(); //差异原因
            item.credential = $(tr).find('[name^="credential"]').val(); //证明文件
            item.voucher = $(tr).find('[name^="voucher"]').val(); //证明人
            item.surveyTime = $(tr).find('[name^="surveyTime"]').val(); //查勘时间
            item.sureConsistent = $(tr).find('[name^="sureConsistent"]').val();   //确认一致
            item.infoItemId = $(tr).find('[name="infoItemId"]').val();    //infoItemId
            item.id = $(tr).find('[name="id"]').val();    //id
            dataItem.push(item);
        });
        return dataItem;
    }

    function newGetFormData(callback) {
        if (!$("#frm_asset_inventory_content").valid()) {
            return false;
        }
        if (callback) {
            callback(getFormData());
        }
    }
</script>

</html>

