<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js?v=1.0"></script>
<script>
    var objProject = {};


    objProject.run = function (data, url, type, callback, funParams, errorCallback) {
        Loading.progressShow();
        $.ajax({
            type: type,
            url: '${pageContext.request.contextPath}' + url,
            data: data,
            success: function (result) {
                Loading.progressHide();
                if (result.ret) {
                    if (funParams) {
                        if (funParams == 'save') {
                            notifySuccess("成功", "保存数据成功!");
                        }
                        if (funParams == 'add') {
                            notifySuccess("成功", "添加数据成功!");
                        }
                        if (funParams == 'update') {
                            notifySuccess("成功", "修改数据成功!");
                        }
                        if (funParams == 'query') {
                            notifySuccess("成功", "查询数据成功!");
                        }
                        if (funParams == 'delete') {
                            notifySuccess("成功", "删除数据成功!");
                        }
                    }
                    if (callback) {
                        callback(result.data);
                    }
                } else {
                    if (result.errmsg) {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result.errmsg);
                    } else {
                        AlertError("错误", "调用服务端方法失败，失败原因:" + result);
                    }
                    if (errorCallback) {
                        errorCallback();
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
    objProject.ajaxServerMethod = function (data, url, type, callback, funParams, errorCallback) {
        var deleteParams = false;
        if (funParams) {
            if (funParams == 'delete') {
                deleteParams = true;
            }
        }
        if (deleteParams) {
            AlertConfirm("是否确认删除当前数据", "删除相应的数据后将不可恢复", function (flag) {
                objProject.run(data, url, type, callback, funParams, errorCallback);
            });
        } else {
            objProject.run(data, url, type, callback, funParams, errorCallback);
        }
    };

    /**
     * 判断字符串以及null等
     */
    objProject.isNotBlank = function (item) {
        if (item) {
            if (item == 'undefined') {
                return false;
            }
            return true;
        }
        return false;
    };
    //去掉所有空白换行
    objProject.Trim = function (str) {
        if (str) {
            return str.replace(/(^\s*)|(\s*$)/g, "").replace(/[\r\n]/g, "");
        }
        return str;
    }

    /**
     * 判断对象 属性
     */
    objProject.isNotBlankObjectProperty = function (obj) {
        for (var key in obj) {
            if (this.isNotBlank(obj[key])) {
                return true;
            }
        }
        return false
    };

    /**
     * 判断对象
     */
    objProject.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    objProject.select2Assignment = function (frm, data, name) {
        if (this.isNotBlank(data)) {
            $("#" + frm).find("select." + name).val(data).trigger("change");
            $("#" + frm).find("select[name='" + name + "']").val(data).trigger("change");
            $("#" + frm).find("select[name='" + name + "']").val(data);
        } else {
            $("#" + frm).find("select." + name).val(null).trigger("change");
        }
    };

    objProject.uploadFile = function (fieldsName, table, id) {
        FileUtils.uploadFiles({
            target: fieldsName,
            disabledTarget: "btn_submit",
            formData: {
                fieldsName: fieldsName,
                tableName: table,
                tableId: objProject.isNotBlank(id) ? id : "0",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        });
    };

    objProject.showFile = function (fieldsName, table, id) {
        FileUtils.getFileShows({
            target: fieldsName,
            formData: {
                fieldsName: fieldsName,
                tableName: table,
                tableId: objProject.isNotBlank(id) ? id : "0",
                creater: "${currUserAccount}"
            },
            deleteFlag: true
        })
    };

    objProject.getCategory = function (pid, value) {
        AssessCommon.loadDataDicByPid(pid, value, function (html, data) {
            $("#" + objProject.config.info.frm).find("select[name='entrustAimType']").empty().html(html).trigger('change');
        });
    };

    objProject.config = {
        info: {
            frm: "frm_project_info",
            files: {
                attachmentProjectInfoId: "attachmentProjectInfoId"
            }
        },
        consignor: {
            frm: "frm_consignor",
            table: "tb_ListConsignor",
            boxDiv: "divBoxConsignorContacts",
            boxFrm: "frmConsignorContacts",
            files: {
                csAttachmentProjectEnclosureId: "csAttachmentProjectEnclosureId"
            },
            contacts: {key: "CONSIGNOR", name: "委托人", nodeKey: 1}
        },
        possessor: {
            frm: "frm_possessor",
            table: "tb_ListPossessor",
            boxDiv: "divBoxPossessorContacts",
            boxFrm: "frmPossessorContacts",
            files: {
                pAttachmentProjectEnclosureId: "pAttachmentProjectEnclosureId"
            },
            contacts: {key: "POSSESSOR", name: "占有人", nodeKey: 2}
        },
        unit_information: {
            frm: "frm_unitinformation",
            table: "tb_ListUNIT_INFORMATION",
            boxDiv: "divBoxUNIT_INFORMATIONContacts",
            boxFrm: "frmUNIT_INFORMATIONContacts",
            contacts: {key: "UNIT_INFORMATION", name: "报告使用单位", nodeKey: 3}
        }
    };

    /**
     * 联系人公共方法
     */
    objProject.commonContacts = {
        getCols: function () {
            var cols = [];
            cols.push({field: 'cName', title: '姓名'});
            cols.push({field: 'cDept', title: '部门'});
            cols.push({field: 'cPhone', title: '电话号码'});
            cols.push({field: 'cEmail', title: '邮箱'});
            cols.push({field: 'id', visible: false, title: "id"});
            return cols;
        },
        save: function (data, callback) {
            objProject.ajaxServerMethod(data, '/initiateContacts/saveAndUpdate', "post", callback, null);
        },
        get: function (id, callback) {
            objProject.ajaxServerMethod({id: id}, '/initiateContacts/get', "get", callback, null);
        },
        loadList: function (col, data) {
            var cols = objProject.commonContacts.getCols();
            cols.push(col);
            $("#" + data.table).bootstrapTable("destroy");
            TableInit(data.table, "${pageContext.request.contextPath}/initiateContacts/getBootstrapTableVo", cols, {
                cType: data.cType, cPid: data.cPid
            }, {
                showColumns: false,
                showRefresh: false,
                search: false
            });
        },
        delete: function (id, callback) {
            objProject.ajaxServerMethod({id: id}, "/initiateContacts/delete", "post", callback, 'delete', null);
        },
        clear: function (data, callback) {
            data.creator = '${currUserAccount}';
            objProject.ajaxServerMethod(data, '/initiateContacts/clear', "post", callback, null, null);
        },
        writeCustomerLinkmanInContacts: function (data, callback) {
            objProject.ajaxServerMethod(data, '/initiateCrmCustomer/writeCustomerLinkmanInContacts', "post", callback, null, null);
        },
        findCRMContacts: function (that) {
            var text = $(that).parent().parent().prev().find("input[name='name']").val();
            var id = formParams(objProject.config.unit_information.frm).uUseUnit;
            var data = {customerId: id, searchCrm: text};
            if (id) {
                var cols = [];
                cols.push({field: 'ckeckbox', checkbox: true, title: '客户经理勾选'});//多选
                cols.push({field: 'name', title: '姓名', searchable: true});
                cols.push({field: 'department', title: '部门'});
                cols.push({field: 'phoneNumber', title: '电话号码'});
                cols.push({field: 'email', title: '邮箱'});
                cols.push({field: 'id', visible: false, title: "id"});
                $("#tb_ListCRMContacts").bootstrapTable("destroy");
                TableInit('tb_ListCRMContacts', "${pageContext.request.contextPath}/initiateCrmCustomer/getCustomerLinkmanPageList", cols, data, {
                    showColumns: false,
                    showRefresh: false,
                    search: false
                });
            } else {
                AlertError("未选择单元");
            }
        },
        findCRMContactShow: function () {
            var id = formParams(objProject.config.unit_information.frm).uUseUnit;
            if (id) {
                $('#divBoxCRMContacts').modal("show");
                objProject.commonContacts.findCRMContacts($("#divBoxCRMContacts").find("input[name='name']")[0]);
            } else {
                AlertError("还未选择报告使用单位");
            }
        },
        selectCRMContacts: function () {
            var rows = $("#tb_ListCRMContacts").bootstrapTable('getSelections');
            if (rows.length == 0) {
                notifyWaring('警告', "至少选择一个客户!");
                return false;
            }
            var data = [];
            rows.forEach(function (item, i) {
                data.push({
                    cType: objProject.config.unit_information.contacts.nodeKey,
                    cPid: objProject.isNotBlank('${projectInfo.unitInformationVo.id}') ? '${projectInfo.unitInformationVo.id}' : '0',
                    crmId: item.id,
                    customerId: item.customerId,
                    cName: item.name,
                    cEmail: item.email,
                    cDept: item.department,
                    cPhone: item.phoneNumber
                });
            });
            objProject.ajaxServerMethod({formData: JSON.stringify(data)}, "/initiateContacts/saveAndUpdateList", "post", function () {
                objProject.unit_information.loadContactList();
                $('#divBoxCRMContacts').modal("hide");
            });
        },
        copyContacts: function (data, callback) {
            objProject.ajaxServerMethod(data, "/initiateContacts/copyContacts", "post", callback);
        }
    };

    /**
     * 项目基础信息 加载数据
     * @type {{loadInit: objProject.info.loadInit}}
     */
    objProject.info = {
        loadInit: function (item) {
            $("#" + objProject.config.info.frm).initForm(item);
            $.each(objProject.config.info.files, function (i, n) {
                objProject.showFile(n, AssessDBKey.ProjectInfo, objProject.isNotBlank(item.id) ? item.id : "0");
                objProject.uploadFile(n, AssessDBKey.ProjectInfo, objProject.isNotBlank(item.id) ? item.id : "0");
            });
            AssessCommon.initAreaInfo({
                provinceTarget: $("#province"),
                cityTarget: $("#city"),
                districtTarget: $("#district"),
                provinceValue: item.province,
                cityValue: item.city,
                districtValue: item.district
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.value_type, item.valueType, function (html, data) {
                $("#" + objProject.config.info.frm).find("select[name='valueType']").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.serviceComeFrom, item.serviceComeFrom, function (html, data) {
                $("#" + objProject.config.info.frm).find("select[name='serviceComeFrom']").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.property_scope, item.propertyScope, function (html, data) {
                $("#" + objProject.config.info.frm).find("select.propertyScope").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.dataEntrustmentPurpose, item.entrustPurpose, function (html, data) {
                $("#" + objProject.config.info.frm).find("select.entrustPurpose").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.project_initiate_urgency, item.urgency, function (html, data) {
                $("#" + objProject.config.info.frm).find("select.urgency").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByPid(item.entrustPurpose, item.entrustAimType, function (html, data) {
                $("#" + objProject.config.info.frm).find("select.entrustAimType_p").empty().html(html).trigger('change');

            });
            AssessCommon.loadDataDicByKey(AssessDicKey.dataLoanType, item.loanType, function (html, data) {
                $("#" + objProject.config.info.frm).find("select.loanType").empty().html(html).trigger('change');
            });

            $("#" + objProject.config.info.frm).find("select[name='valueType']").change(function () {
                AssessCommon.getDataDicInfo($(this).val(), function (data) {
                    if (data) {
                        $("#" + objProject.config.info.frm).find("input[name='remarkValueType']").val($.trim(data.remark));
                    }
                });
                var data = formSerializeArray($("#" + objProject.config.info.frm));
                objProject.ajaxServerMethod(data, '/projectInfo/getValueDefinition', "post", function (data) {
                    if (data) {
                        if (data.propertyScope) {
                            $("#" + objProject.config.info.frm).find("select.propertyScope").val(data.propertyScope).trigger('change');
                        }
                        if (data.scopeInclude) {
                            $("#" + objProject.config.info.frm).find("input[name='scopeInclude']").val(data.scopeInclude);
                        }
                        if (data.scopeNotInclude) {
                            $("#" + objProject.config.info.frm).find("input[name='scopeNotInclude']").val(data.scopeNotInclude);
                        }
                    }
                }, null, function () {
                    $("#" + objProject.config.info.frm).find("select[name='propertyScope']").val(null).trigger("change");
                    $("#" + objProject.config.info.frm).find("input[name='scopeInclude']").val("");
                    $("#" + objProject.config.info.frm).find("input[name='scopeNotInclude']").val("");
                });
            });
            $("#" + objProject.config.info.frm).find("select[name='entrustPurpose']").change(function () {
                var entrustPurpose = $("#" + objProject.config.info.frm).find("select[name='entrustPurpose']").find("option:selected").val();
                var strArr = ["抵押评估"];//来自于实体描述1(1).docx中的规则
                if (entrustPurpose) {
                    AssessCommon.getDataDicInfo(entrustPurpose, function (entrustPurposeData) {
                        var str = strArr.join(",");
                        //当属于数组中的任意一项时显示
                        if (str.indexOf(entrustPurposeData.name) != -1) {
                            $("#" + objProject.config.info.frm).find("select.loanType").attr("required", "required");
                        } else {
                            $("#" + objProject.config.info.frm).find("select.loanType").removeAttr("required");

                        }
                    });
                }

                if (item.entrustAimType) {
                    objProject.getCategory(entrustPurpose, item.entrustAimType);
                    item.entrustAimType = "";
                } else {
                    objProject.getCategory(entrustPurpose, false);
                }
                var data = formSerializeArray($("#" + objProject.config.info.frm));
                objProject.ajaxServerMethod(data, '/projectInfo/getValueDefinition', "post", function (data) {
                    if (data) {
                        if (data.propertyScope) {
                            $("#" + objProject.config.info.frm).find("select.propertyScope").val(data.propertyScope).trigger('change');
                        }
                        if (data.scopeInclude) {
                            $("#" + objProject.config.info.frm).find("input[name='scopeInclude']").val(data.scopeInclude);
                        }
                        if (data.scopeNotInclude) {
                            $("#" + objProject.config.info.frm).find("input[name='scopeNotInclude']").val(data.scopeNotInclude);
                        }
                    }
                }, null, function () {
                    $("#" + objProject.config.info.frm).find("select[name='propertyScope']").val(null).trigger("change");
                    $("#" + objProject.config.info.frm).find("input[name='scopeInclude']").val("");
                    $("#" + objProject.config.info.frm).find("input[name='scopeNotInclude']").val("");
                });
            });

            $("#" + objProject.config.info.frm).find("select[name='entrustAimType']").change(function () {
                var remarkEntrustPurpose = $("#" + objProject.config.info.frm).find("input[name='remarkEntrustPurpose']");
                remarkEntrustPurpose.val('');
                var entrustAimType = $("#" + objProject.config.info.frm).find("select[name='entrustAimType']").find("option:selected").val();
                if (entrustAimType) {
                    objProject.ajaxServerMethod({
                        entrustAimType: entrustAimType,
                        projectCategoryId: '${projectInfo.projectCategoryId}'
                    }, '/projectInfo/getRemarkEntrustPurpose', "post", function (data) {
                        remarkEntrustPurpose.val(data.remark);
                    });
                }
            });

            if (objProject.isNotBlank(item.entrustPurpose)) {
                var strArr = ["抵押评估"];//来自于实体描述1(1).docx中的规则
                var entrustPurposeId = item.entrustPurpose;
                if (entrustPurposeId) {
                    AssessCommon.getDataDicInfo(entrustPurposeId, function (entrustPurposeData) {
                        var str = strArr.join(",");
                        //当属于数组中的任意一项时显示
                        if (str.indexOf(entrustPurposeData.name) != -1) {
                            $("#" + objProject.config.info.frm).find("select.loanType").attr("required", "required");
                        } else {
                            $("#" + objProject.config.info.frm).find("select.loanType").removeAttr("required");

                        }
                    });
                }
            }
        }
    };


    /**
     * 委托人
     * @type {{loadInit: objProject.consignor.loadInit, contactsShow: objProject.consignor.contactsShow}}
     */
    objProject.consignor = {
        loadInit: function (item) {
            $("#" + objProject.config.consignor.frm).find("input[type='radio'][name='csType']").change(function () {
                if ($(this).val() == 1) {
                    $("#" + objProject.config.consignor.frm).find("input[type='radio'][name='csType'][value='0']").removeAttr("checked");
                    $(this).attr("checked", true);
                    $("#consignor_tab").hide();
                    $("#consignor_tab").find("input").attr("disabled", true);
                    $("#consignor_tab_unit").show();
                    $("#consignor_tab_unit").find("input").attr("disabled", false);
                }
                if ($(this).val() == 0) {
                    $("#" + objProject.config.consignor.frm).find("input[type='radio'][name='csType'][value='1']").removeAttr("checked");
                    $(this).attr("checked", true);
                    $("#consignor_tab").show();
                    $("#consignor_tab").find("input").attr("disabled", false);

                    $("#consignor_tab_unit").hide();
                    $("#consignor_tab_unit").find("input").attr("disabled", true);
                }
            });
            $.each(objProject.config.consignor.files, function (i, n) {
                objProject.showFile(n, AssessDBKey.InitiateConsignor, objProject.isNotBlank(item.id) ? item.id : "0");
                objProject.uploadFile(n, AssessDBKey.InitiateConsignor, objProject.isNotBlank(item.id) ? item.id : "0");
            });
            if (item.id) {
                if (item.csType == 1) {
                    $("#" + objProject.config.consignor.frm).find("input[type='radio'][name='csType'][value='1']").attr("checked", true);
                    $("#consignor_tab").hide();
                    $("#consignor_tab").find("input").attr("disabled", true);
                    $("#consignor_tab_unit").show();
                    $("#consignor_tab_unit").find("input").attr("disabled", false);
                }
                if (item.csType == 0) {
                    $("#" + objProject.config.consignor.frm).find("input[type='radio'][name='csType'][value='0']").attr("checked", true);
                    $("#consignor_tab").show();
                    $("#consignor_tab").find("input").attr("disabled", false);
                    $("#consignor_tab_unit").hide();
                    $("#consignor_tab_unit").find("input").attr("disabled", true);
                }
            }
            objProject.consignor.loadContactList();
        },
        //使添加联系人按钮显示
        contactsShow: function () {
            $("#" + objProject.config.consignor.table).parent().parent().show();
        },
        //添加联系人模态框
        showContactModel: function () {
            $("#" + objProject.config.consignor.boxFrm).clearAll();
            $('#' + objProject.config.consignor.boxDiv).modal("show");
            if ($("#" + objProject.config.consignor.boxFrm).find(".modal-body").size() > 0) {
                $("#" + objProject.config.consignor.boxFrm).find(".modal-body").remove();
            }
            $("#" + objProject.config.consignor.boxFrm).prepend($("#contactModelHTML").html());
        },
        //添加联系人
        saveContact: function () {
            if (!$("#" + objProject.config.consignor.boxFrm).valid()) {
                return false;
            }
            var data = formParams(objProject.config.consignor.boxFrm);
            data.cType = objProject.config.consignor.contacts.nodeKey;
            data.cPid = objProject.isNotBlank('${projectInfo.consignorVo.id}') ? '${projectInfo.consignorVo.id}' : '0';
            objProject.commonContacts.save(data, function () {
                objProject.consignor.loadContactList();
                $('#' + objProject.config.consignor.boxDiv).modal("hide");
            });
        },
        //获取联系人
        getContact: function (id) {
            objProject.commonContacts.get(id, function (data) {
                objProject.consignor.showContactModel();
                $("#" + objProject.config.consignor.boxFrm).initForm(data);
            });
        },
        //删除 联系人
        deleteContact: function (id) {
            objProject.commonContacts.delete(id, function () {
                objProject.consignor.loadContactList();
            });
        },
        //联系人列表
        loadContactList: function () {
            var data = {};
            data.table = objProject.config.consignor.table;
            data.cType = objProject.config.consignor.contacts.nodeKey;
            data.cPid = objProject.isNotBlank('${projectInfo.consignorVo.id}') ? '${projectInfo.consignorVo.id}' : '0';
            var col = "";
            col = {
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<button onclick="objProject.consignor.getContact(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button onclick="objProject.consignor.deleteContact(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    return str;
                }
            };
            objProject.commonContacts.loadList(col, data);
        }
    };

    /**
     * 占有人
     * @type {{loadInit: objProject.possessor.loadInit, contactsShow: objProject.possessor.contactsShow}}
     */
    objProject.possessor = {
        loadInit: function (item) {
            $("#" + objProject.config.possessor.frm).find("input[type='radio'][name='pType']").change(function () {
                if ($(this).val() == 1) {
                    $("#" + objProject.config.possessor.frm).find("input[type='radio'][name='pType'][value='0']").removeAttr("checked");
                    $(this).attr("checked", true);
                    $("#possessor_tab").hide();
                    $("#possessor_tab").find("input").attr("disabled", true);
                    $("#possessor_tab_unit").show();
                    $("#possessor_tab_unit").find("input").attr("disabled", false);
                }
                if ($(this).val() == 0) {
                    $("#" + objProject.config.possessor.frm).find("input[type='radio'][name='pType'][value='1']").removeAttr("checked");
                    $(this).attr("checked", true);
                    $("#possessor_tab").show();
                    $("#possessor_tab").find("input").attr("disabled", false);
                    $("#possessor_tab_unit").hide();
                    $("#possessor_tab_unit").find("input").attr("disabled", true);
                }
                //拷贝数据
                objProject.possessor.copyConsignor($(this).val());
            });
            $.each(objProject.config.possessor.files, function (i, n) {
                objProject.showFile(n, AssessDBKey.InitiatePossessor, objProject.isNotBlank(item.id) ? item.id : "0");
                objProject.uploadFile(n, AssessDBKey.InitiatePossessor, objProject.isNotBlank(item.id) ? item.id : "0");
            });
            if (item.id) {
                if (item.pType == 1) {
                    $("#possessor_tab").hide();
                    $("#possessor_tab").find("input").attr("disabled", true);
                    $("#possessor_tab_unit").show();
                    $("#possessor_tab_unit").find("input").attr("disabled", false);
                    $("#" + objProject.config.possessor.frm).find("input[type='radio'][name='pType'][value='1']").attr("checked", true);
                }
                if (item.pType == 0) {
                    $("#possessor_tab").show();
                    $("#possessor_tab").find("input").attr("disabled", false);
                    $("#possessor_tab_unit").hide();
                    $("#possessor_tab_unit").find("input").attr("disabled", true);
                    $("#" + objProject.config.possessor.frm).find("input[type='radio'][name='pType'][value='0']").attr("checked", true);
                }
            }
            objProject.possessor.loadContactList();
        },
        showContactModel: function () {
            $("#" + objProject.config.possessor.boxFrm).clearAll();
            $('#' + objProject.config.possessor.boxDiv).modal("show");
            if ($("#" + objProject.config.possessor.boxFrm).find(".modal-body").size() > 0) {
                $("#" + objProject.config.possessor.boxFrm).find(".modal-body").remove();
            }
            $("#" + objProject.config.possessor.boxFrm).prepend($("#contactModelHTML").html());
        },
        saveContact: function () {
            if (!$("#" + objProject.config.possessor.boxFrm).valid()) {
                return false;
            }
            var data = formParams(objProject.config.possessor.boxFrm);
            data.cType = objProject.config.possessor.contacts.nodeKey;
            data.cPid = objProject.isNotBlank('${projectInfo.possessorVo.id}') ? '${projectInfo.possessorVo.id}' : '0';
            objProject.commonContacts.save(data, function () {
                objProject.possessor.loadContactList();
                $('#' + objProject.config.possessor.boxDiv).modal("hide");
            });
        },
        getContact: function (id) {
            objProject.commonContacts.get(id, function (data) {
                objProject.possessor.showContactModel();
                $("#" + objProject.config.possessor.boxFrm).initForm(data);
            });
        },
        deleteContact: function (id) {
            objProject.commonContacts.delete(id, function () {
                objProject.possessor.loadContactList();
            });
        },
        loadContactList: function () {
            var data = {};
            data.table = objProject.config.possessor.table;
            data.cType = objProject.config.possessor.contacts.nodeKey;
            data.cPid = objProject.isNotBlank('${projectInfo.possessorVo.id}') ? '${projectInfo.possessorVo.id}' : '0';
            var col = "";
            col = {
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<button onclick="objProject.possessor.getContact(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button onclick="objProject.possessor.deleteContact(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    return str;
                }
            };
            objProject.commonContacts.loadList(col, data);
        },
        contactsShow: function () {
            $("#" + objProject.config.possessor.table).parent().parent().show();
        },
        //copy 委托人信息
        copyConsignor: function (pType) {
            var select = $('#' + objProject.config.possessor.table).bootstrapTable('getData');
            //当委托人已经有联系人则默认为拷贝过占有人数据了
            if (select.length >= 1) {
                return false;
            }
            var consignor = $("#" + objProject.config.consignor.frm); //委托人信息
            if (pType == 1) {
                var data = {
                    pEntrustmentUnit: consignor.find("#consignor_tab_unit").find("input[name='csEntrustmentUnit']").val(),
                    pLegalRepresentative: consignor.find("#consignor_tab_unit").find("input[name='csLegalRepresentative']").val(),
                    pSociologyCode: consignor.find("#consignor_tab_unit").find("input[name='csSociologyCode']").val(),
                    pAddress: consignor.find("#consignor_tab_unit").find("input[name='csAddress']").val(),
                    pScopeOperation: consignor.find("#consignor_tab_unit").find("input[name='csScopeOperation']").val(),
                    pUnitProperties: consignor.find("#consignor_tab_unit").find("select[name='csUnitProperties']").find(":selected").val()
                };
                $("#" + objProject.config.possessor.frm).find("#possessor_tab_unit").initForm(data);
            }
            if (pType == 0) {
                var data = {
                    pName: consignor.find("#consignor_tab").find("input[name='csName']").val(),
                    pIdcard: consignor.find("#consignor_tab").find("input[name='csIdcard']").val(),
                    pAddress: consignor.find("#consignor_tab").find("input[name='csAddress']").val()
                };
                $("#" + objProject.config.possessor.frm).find("#possessor_tab").initForm(data);
            }
            //检测
            var item = $('#' + objProject.config.consignor.table).bootstrapTable('getData');
            if (item) {
                if (item.length >= 1) {
                    var ids = "";
                    $.each(item, function (i, n) {
                        if (i == item.length - 1) {
                            ids += n.id;
                        } else {
                            ids += n.id + ",";
                        }
                    });
                    var replaceData = {};
                    replaceData.ids = ids;
                    replaceData.cType = objProject.config.possessor.contacts.nodeKey;
                    replaceData.cPid = objProject.isNotBlank('${projectInfo.possessorVo.id}') ? '${projectInfo.possessorVo.id}' : '0';
                    //联系人拷贝
                    objProject.commonContacts.copyContacts(replaceData, function () {
                        objProject.possessor.loadContactList();
                    });
                    $.ajax({
                        url: getContextPath() + "/public/getSysAttachmentDtoList",
                        type: "get",
                        dataType: "json",
                        async: false,
                        data: {
                            tableId: objProject.isNotBlank('${projectInfo.consignorVo.id}') ? '${projectInfo.consignorVo.id}' : '0',
                            tableName: AssessDBKey.InitiateConsignor,
                            creater: "${currUserAccount}"
                        },
                        success: function (result) {
                            if (result.ret && result.data) {
                                if (result.data.length >= 1) {
                                    var oldFile = result.data[0];
                                    oldFile.fieldsName = objProject.config.possessor.files.pAttachmentProjectEnclosureId;
                                    oldFile.tableName = AssessDBKey.InitiatePossessor;
                                    oldFile.id = null;
                                    AssessCommon.saveAndUpdateSysAttachmentDto(oldFile, function () {
                                        $.each(objProject.config.possessor.files, function (i, n) {
                                            objProject.showFile(n, AssessDBKey.InitiatePossessor, objProject.isNotBlank('${projectInfo.possessorVo.id}') ? '${projectInfo.possessorVo.id}' : '0');
                                        });
                                    })
                                }
                            }
                        }
                    });
                }
            }
        }
    };

    /**
     * 报告使用单位
     * @type {{loadInit: objProject.unit_information.loadInit, contactsShow: objProject.unit_information.contactsShow}}
     */
    objProject.unit_information = {
        loadInit: function (item) {
            objProject.unit_information.loadContactList();
        },
        showContactModel: function () {
            $("#" + objProject.config.unit_information.boxFrm).clearAll();
            $('#' + objProject.config.unit_information.boxDiv).modal("show");
            if ($("#" + objProject.config.unit_information.boxFrm).find(".modal-body").size() > 0) {
                $("#" + objProject.config.unit_information.boxFrm).find(".modal-body").remove();
            }
            $("#" + objProject.config.unit_information.boxFrm).prepend($("#contactModelHTML").html());
        },
        saveContact: function () {
            if (!$("#" + objProject.config.unit_information.boxFrm).valid()) {
                return false;
            }
            var data = formParams(objProject.config.unit_information.boxFrm);
            data.cType = objProject.config.unit_information.contacts.nodeKey;
            data.cPid = objProject.isNotBlank('${projectInfo.unitInformationVo.id}') ? '${projectInfo.unitInformationVo.id}' : '0';
            objProject.commonContacts.save(data, function () {
                objProject.unit_information.loadContactList();
                $('#' + objProject.config.unit_information.boxDiv).modal("hide");
            });
        },
        getContact: function (id) {
            objProject.commonContacts.get(id, function (data) {
                objProject.unit_information.showContactModel();
                $("#" + objProject.config.unit_information.boxFrm).initForm(data);
            });
        },
        deleteContact: function (id) {
            objProject.commonContacts.delete(id, function () {
                objProject.unit_information.loadContactList();
            });
        },
        loadContactList: function () {
            var data = {};
            data.table = objProject.config.unit_information.table;
            data.cType = objProject.config.unit_information.contacts.nodeKey;
            data.cPid = objProject.isNotBlank('${projectInfo.unitInformationVo.id}') ? '${projectInfo.unitInformationVo.id}' : '0';
            var col = "";
            col = {
                field: 'id', title: '操作', formatter: function (value, row, index) {
                    var str = '<button onclick="objProject.unit_information.getContact(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-primary  btn-xs tooltips"  data-placement="bottom" data-original-title="编辑">';
                    str += '<i class="fa fa-pen"></i>';
                    str += '</button>';
                    str += '<button onclick="objProject.unit_information.deleteContact(' + row.id + ')"  style="margin-left: 5px;"  class="btn  btn-warning  btn-xs tooltips"  data-placement="bottom" data-original-title="删除">';
                    str += '<i class="fa fa-minus"></i>';
                    str += '</button>';
                    return str;
                }
            };
            objProject.commonContacts.loadList(col, data);
        },
        contactsShow: function () {
            $("#" + objProject.config.unit_information.table).parent().parent().show();
        }
    };

    //执业部门
    objProject.selectDepartment = function (this_) {
        var options = {
            onSelected: function (nodes) {
                $(this_).closest('.input-group').find("input[name='departmentId']").val(nodes[0].id);
                if (nodes[0].text) {
                    $(this_).closest('.input-group').find("input[name='departmentName']").val(nodes[0].text);
                }
                if (nodes[0].name) {
                    $(this_).closest('.input-group').find("input[name='departmentName']").val(nodes[0].name);
                }
            }
        };
        if ("${departmentAssess.id}".length > 0) {
            options.currOrgId = "${departmentAssess.id}";
        }
        erpDepartment.select(options);
    };

    //项目经理
    objProject.selectUserAccountManager = function (this_) {
        erpEmployee.select({
            currOrgId: '${baseViewDto.thisUser.companyId}',
            onSelected: function (data) {
                $(this_).closest('.input-group').find("input[name='userAccountManager']").val(data.account);
                $(this_).closest('.input-group').find("input[name='userAccountManagerName']").val(data.name);
                objProject.getProjectNumber(data.account);
            }
        });
    };

    objProject.getProjectNumber = function (account) {
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/projectInfo/getProjectNumber",
            data: {account: account},
            success: function (result) {
                if (result.ret) {
                    $("#lab_total").text(result.data + "个");
                }
            }
        });
    };

    objProject.checkProjectByAccount = function (_this) {
        var account = $(_this).closest('form').find("input[name='userAccountManager']").val();
        objProject.getProjectByAccount(account);
        $("#checkProjectsBox").modal("show");
    };

    objProject.getProjectByAccount = function (account) {
        var cols = [];
        cols.push({
            field: 'projectName', title: '项目名称', width: '30%', formatter: function (value, row, index) {
                var str = value;
                str += '<span style="margin-left: 2px;background-color: #868b9e;" class="label label-default">' + row.useUnitName + '</span>';
                str += '<span style="margin-left: 2px;background-color: #9ed2a0;" class="label label-success">' + row.departmentName + '</span>';
                str += '<span style="margin-left: 2px;background-color: #b3b0e2;" class="label label-secondary">' + row.entrustPurposeName + '</span>';
                str += '<span style="margin-left: 2px;background-color: #accfea;" class="label label-info">' + row.loanTypeName + '</span>';
                return str;
            }
        });
        cols.push({
            field: 'serviceEnd', title: '项目成员', width: '15%', formatter: function (value, row, index) {
                var s = "";
                if (row.userAccountManagerName) {
                    s += "<span class='label label-primary'>" + row.userAccountManagerName.split("_")[0] + "</span>"
                }
                if (row.userAccountMemberName) {
                    s += " " + row.userAccountMemberName.split("_")[0];
                }
                return s;
            }
        });
        cols.push({
            field: 'projectClassName', title: '类型', width: '8%', formatter: function (value, row, index) {
                return row.projectCategoryName;
            }
        });
        cols.push({field: 'projectStatus', title: '状态', width: '10%'});
        cols.push({
            field: 'finishPre', title: '项目进度', width: '15%', formatter: function (value, row, index) {
                var s = "<div class='progress progress-sm' style='margin-bottom: 0px;'>";
                s += "<div class='progress-bar bg-success' role='progressbar'  style='width: " + value + "%;'></div>";
                s += "</div>";
                s += "<small>完成" + value + "%</small>";
                return s;
            }
        });
        cols.push({
            field: 'gmtCreated', title: '立项时间', width: '14%', formatter: function (value, row, index) {
                return formatDate(row.gmtCreated, true);
            }
        });

        $("#getProjectByAccountList").bootstrapTable('destroy');
        TableInit("getProjectByAccountList", "${pageContext.request.contextPath}/projectInfo/getProjectByAccount", cols, {
            account: account
        }, {
            showColumns: false,
            showRefresh: false,
            search: false,
            onLoadSuccess: function () {
                $(".tooltips").tooltip();
            }
        });
    }

    //项目成员
    objProject.selectUserAccountMember = function (this_) {
        erpEmployee.select({
            multi: true,
            currOrgId: '${baseViewDto.thisUser.companyId}',
            onSelected: function (data) {
                $(this_).closest('.input-group').find("input[name='userAccountMember']").val(data.account);
                $(this_).closest('.input-group').find("input[name='userAccountMemberName']").val(data.name);
            }
        });
    };

    /*选择合同*/
    objProject.selectContract = function (this_) {
        cmsContract.select({
            multi: true,//是否允许多选
            appkey: "pmcc-assess",
            onSelected: function (data) {
                var uuids = [];
                var names = [];
                var viewArray = [];
                data.forEach(function (node, i) {
                    if (node.uuid) {
                        uuids.push(node.uuid);
                    }
                    if (node.name) {
                        names.push(node.name);
                    }
                    if (node.uuid && node.name) {
                        var url = "<a target='_blank' href='${sysUrl}/pmcc-contract/contractCurrency/details/" + node.uuid + "'" + ">" + node.name + "</a>";
                        viewArray.push(url);
                    }
                });
                if (uuids.length == 0) {
                    AlertError("提示", '有效合同为0');
                    return false;
                }
                if (uuids.length >= 1) {
                    $(this_).closest('.input-group').find("input[name='contractId']").val(uuids.join(","));
                }
                if (viewArray.length >= 1) {
                    $(this_).closest('.input-group').find("label[name='contractNameView']").html(viewArray.join("/ "));
                }
                if (names.length >= 1) {
                    $(this_).closest('.input-group').find("input[name='contractName']").val(names.join(","));
                }
            }
        });
    };

    objProject.selectCustomer = function (this_) {
        //选择客户
        crmCustomer.select({
            multi: false,//是否允许多选
            companyId:${baseViewDto.thisUser.companyId},
            onSelected: function (data) {
                if (!data) {
                    return false;
                }
                var node = Array.isArray(data) ? data[0] : data;
                $(this_).closest('.input-group').find("input[name='uUseUnit']").val(node.id);
                $(this_).closest('.input-group').find("input[name='uUseUnitName']").val(node.name);
                $.ajax({
                    type: "get",
                    url: "${pageContext.request.contextPath}/initiateCrmCustomer/getCrmCustomerDto",
                    data: {customerId: node.id},
                    success: function (msg) {
                        var item = {
                            uLegalRepresentative: msg.data.legalRepresentative,
                            uAddress: msg.data.address,
                            uScopeOperation: msg.data.businessScope,
                            uCertificateNumber: msg.data.certificateNumber,
                            uUnitProperties: msg.data.unitProperties
                        };
                        $("#" + objProject.config.unit_information.frm).initForm(item);
                        $("#" + objProject.config.unit_information.frm).find("input[name='uUseUnitName']").val(msg.data.fullName);
                        $("#" + objProject.config.unit_information.frm).find("select[name='uUnitProperties']").val(msg.data.unitProperties).trigger("selected");
                        objProject.loadCustomerFieldList(node.id, node.name);
                    }
                });
            }
        });
    };

    objProject.loadCustomerFieldList = function (id, name) {
        $("#businessType,#assessType").hide();
        $.ajax({
            type: "get",
            url: "${pageContext.request.contextPath}/dataCustomerField/getCustomerFieldList",
            data: {customerId: id, customerName: name},
            success: function (result) {
                $("#" + objProject.config.unit_information.frm).find("select.assessType").empty();
                $("#" + objProject.config.unit_information.frm).find("select.businessType").empty();
                if (result.ret && result.data) {
                    if (result.data.length >= 1) {
                        var businessType = result.data[0].businessType;
                        var assessType = result.data[0].assessType;
                        var retHtml = '';
                        if (businessType) {
                            businessType = businessType.split(",");
                            retHtml += '<option value="" selected>-请选择-</option>';
                            $.each(businessType, function (i, item) {
                                retHtml += '<option key="' + item + '" title="' + item + '" value="' + item + '"';
                                retHtml += '>' + item + '</option>';
                            });
                            $("#" + objProject.config.unit_information.frm).find("select.businessType").empty().html(retHtml).trigger('change');
                            $("#businessType").show();
                        }

                        if (assessType) {
                            assessType = assessType.split(",");
                            retHtml = '<option value="" selected>-请选择-</option>';
                            $.each(assessType, function (i, item) {
                                retHtml += '<option key="' + item + '" title="' + item + '" value="' + item + '"';
                                retHtml += '>' + item + '</option>';
                            });
                            $("#" + objProject.config.unit_information.frm).find("select.assessType").empty().html(retHtml).trigger('change');
                            $("#assessType").show();
                        }
                    } else {
                        $("#businessType,#assessType").hide();
                    }
                }
            }
        });
    };

    /**
     * 收集数据
     * @returns {{}}
     */
    objProject.getFormData = function () {
        var data = {};
        var projectInfo = formParams(this.config.info.frm);//项目信息
        projectInfo.projectTypeId = '${projectInfo.projectTypeId}';
        projectInfo.projectClassId = '${projectInfo.projectClassId}';
        projectInfo.projectCategoryId = '${projectInfo.projectCategoryId}';
        var consignor = formParams(this.config.consignor.frm); //委托人信息
        var possessor = formParams(this.config.possessor.frm); //占有人信息
        var unitinformation = formParams(this.config.unit_information.frm); //报告使用单位信息
        data.projectInfo = projectInfo;
        data.consignor = consignor;
        data.possessor = possessor;
        data.unitinformation = unitinformation;
        return data;
    };

    /**
     * js校验
     * @returns {boolean}
     */
    objProject.valid = function () {
        //表单校验
        if (!$("#" + this.config.info.frm).valid()) {
            return false;
        }
        if (!$("#" + this.config.consignor.frm).valid()) {
            return false;
        }
        if (!$("#" + this.config.possessor.frm).valid()) {
            return false;
        }
        if (!$("#" + this.config.unit_information.frm).valid()) {
            return false;
        }
        //联系人校验
        if (!this.hasLinkman(this.config.consignor.table)) {
            notifyInfo("提示", '还未填写委托人联系人信息');
            return false;
        }
        if (!this.hasLinkman(this.config.possessor.table)) {
            notifyInfo("提示", '还未填写占有人联系人信息');
            return false;
        }
        if (!this.hasLinkman(this.config.unit_information.table)) {
            notifyInfo("提示", '还未填写报告使用单位联系人信息');
            return false;
        }
        return true;
    };

    objProject.baseValid = function () {
        //表单校验
        if (!$("#" + this.config.info.frm).valid()) {
            return false;
        }
        if (!$("#" + this.config.consignor.frm).valid()) {
            return false;
        }
        if (!$("#" + this.config.possessor.frm).valid()) {
            return false;
        }
        if (!$("#" + this.config.unit_information.frm).valid()) {
            return false;
        }
        return true;
    };

    objProject.hasLinkman = function (tbListId) {
        var rows = $("#" + tbListId).bootstrapTable('getData');
        if (rows == null || rows.length <= 0) return false;
        return true;
    };


    objProject.loadInit = function () {
        $(function () {
            objProject.consignor.contactsShow();
            objProject.possessor.contactsShow();
            objProject.unit_information.contactsShow();
            var str = $("#projectInfoVoJson").val();
            if (objProject.isNotBlank(str)) {
                var data = {};
                try {
                    data = JSON.parse(str);
                } catch (e) {
                    console.log(str);
                    console.log(e);
                }

                if (objProject.isNotBlankObjectProperty(data)) {
                    objProject.info.loadInit(data);
                    setTimeout(function () {
                        //一些数据重新赋值
                        $("#" + objProject.config.info.frm).find("input[name='scopeInclude']").val(data.scopeInclude);
                        $("#" + objProject.config.info.frm).find("input[name='scopeNotInclude']").val(data.scopeNotInclude);
                        $("#" + objProject.config.info.frm).find("input[name='remarkEntrustPurpose']").val(data.remarkEntrustPurpose);
                    }, 2000);
                } else {
                    var item = formParams(objProject.config.info.frm);
                    objProject.info.loadInit({id: item.id});
                }

                if (objProject.isNotBlank(data.consignorVo)) {
                    objProject.consignor.loadInit(data.consignorVo);
                } else {
                    var item = formParams(objProject.config.consignor.frm);
                    objProject.consignor.loadInit({id: item.id});
                }

                if (objProject.isNotBlank(data.possessorVo)) {
                    objProject.possessor.loadInit(data.possessorVo);
                } else {
                    var item = formParams(objProject.config.possessor.frm);
                    objProject.possessor.loadInit({id: item.id});
                }

                if (objProject.isNotBlank(data.unitInformationVo)) {
                    objProject.unit_information.loadInit(data.unitInformationVo);
                } else {
                    var item = formParams(objProject.config.unit_information.frm);
                    objProject.unit_information.loadInit({id: item.id});
                }
            }
        });
    };


</script>
<div id="divBoxCRMContacts" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">客户经理</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>

            <div class="modal-body">
                <form id="frmFather" class="form-horizontal">
                    <input type="hidden" id="id" name="id">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-md-6">
                                        <div class="form-inline x-valid">
                                            <label class="col-sm-2 col-form-label">
                                            </label>
                                            <div class="col-sm-10">
                                                <input type="text" name="name" placeholder="客户经理名字、电话"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <div>
                                            <button style="margin-left: 10px" class="btn btn-info  btn-sm" type="button"
                                                    onclick="objProject.commonContacts.findCRMContacts(this)">
											<span class="btn-label">
												<i class="fa fa-search"></i>
											</span>
                                                查询
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <table class="table table-bordered" id="tb_ListCRMContacts">
                                    <!-- cerare document add ajax data-->
                                </table>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm"
                        onclick="objProject.commonContacts.selectCRMContacts(this)">
                    确定
                </button>
            </div>

        </div>
    </div>
</div>

<%--<div id="divBoxCRMContacts" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">客户经理</h3>
            </div>
            <form class="form-horizontal">
                <div class="modal-body">
                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 ">
                                            <input type="text" name="name" placeholder="客户经理名字、电话"
                                                   class="form-control input-full">
                                        </div>
                                    </div>
                                    <div class="x-valid">
                                        <div class=" col-xs-6  col-sm-6  col-md-6  col-lg-6 ">
                                            <input type="button"
                                                   onclick="objProject.commonContacts.findCRMContacts(this)"
                                                   class="btn btn-success" value="查询">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <table class="table table-bordered" id="tb_ListCRMContacts">
                                        <!-- cerare document add ajax data-->
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" data-dismiss="modal" class="btn btn-primary"
                            onclick="objProject.commonContacts.selectCRMContacts(this)">
                        确定
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>--%>


<script type="text/html" id="contactModelHTML">
    <div class="modal-body">
        <input type="hidden" name="id">
        <div class="row">
            <div class="col-md-12">
                <div class="card-body">
                    <div class="row form-group">
                        <div class="col-md-6">
                            <div class="form-inline x-valid">
                                <label class="col-sm-2 col-form-label">
                                    姓名<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-10">
                                    <input type="text" name="cName" placeholder="姓名"
                                           class="form-control input-full" required="required">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-inline x-valid">
                                <label class="col-sm-2 col-form-label">
                                    部门<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-10">
                                    <input type="text" name="cDept" placeholder="部门"
                                           class="form-control input-full" required="required">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-md-6">
                            <div class="form-inline x-valid">
                                <label class="col-sm-2 col-form-label">
                                    电话号码<span class="symbol required"></span>
                                </label>
                                <div class="col-sm-10">
                                    <input type="text" name="cPhone" data-rule-number='true' name="number"
                                           placeholder="号码（请输入数字）"
                                           class="form-control input-full" required="required">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-inline x-valid">
                                <label class="col-sm-2 col-form-label">
                                    邮箱
                                </label>
                                <div class="col-sm-10">
                                    <input type="text" name="cEmail" placeholder="邮箱"
                                           class="form-control input-full">
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</script>
