<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>
<script>
    var objProject = {};

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
        if (!pid) {
            var option = "<option value=''>-请先选择委托目的-</option>";
            $("#" + objProject.config.info.frm).find('select.entrustAimType_p').html(option);
            $("#" + objProject.config.info.frm).find('select.entrustAimType_p').val(['']).trigger('change');
            return false;
        }
        $.ajax({
            url: "${pageContext.request.contextPath}/baseDataDic/getCacheDataDicListByPid",
            type: "get",
            dataType: "json",
            data: {pid: pid},
            success: function (result) {
                if (result.ret) {
                    var data = result.data;
                    if (data.length >= 1) {
                        var option = "<option value=''>-请选择-</option>";
                        for (var i = 0; i < data.length; i++) {
                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                        }
                        $("#" + objProject.config.info.frm).find('select.entrustAimType_p').html(option);
                        if (value) {
                            $("#" + objProject.config.info.frm).find('select.entrustAimType_p').val([value]).trigger('change');
                        } else {
                            $("#" + objProject.config.info.frm).find('select.entrustAimType_p').val(['']).trigger('change');
                        }
                    }

                }
                else {
                    Alert("保存数据失败，失败原因:" + result.errmsg);
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })

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
            $.ajax({
                url: "${pageContext.request.contextPath}/initiateContacts/saveAndUpdate",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        callback();
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        get: function (id, callback) {
            $.ajax({
                url: "${pageContext.request.contextPath}/initiateContacts/get",
                type: "get",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    if (result.ret) {
                        callback(result.data);
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
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
            Alert("确认删除", 2, null, function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/initiateContacts/delete",
                    type: "post",
                    dataType: "json",
                    data: {id: id},
                    success: function (result) {
                        if (result.ret) {
                            callback();
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                })
            });
        },
        clear: function (data, callback) {
            data.creator = '${currUserAccount}';
            $.ajax({
                url: "${pageContext.request.contextPath}/initiateContacts/clear",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        callback();
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        writeCustomerLinkmanInContacts: function (data, callback) {
            $.ajax({
                url: "${pageContext.request.contextPath}/initiateCrmCustomer/writeCustomerLinkmanInContacts",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        callback();
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
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
                Alert("未选择单元");
            }
        },
        findCRMContactShow: function () {
            var id = formParams(objProject.config.unit_information.frm).uUseUnit;
            if (id) {
                $('#divBoxCRMContacts').modal("show");
                objProject.commonContacts.findCRMContacts($("#divBoxCRMContacts").find("input[name='name']")[0]);
            } else {
                Alert("还未选择报告使用单位");
            }
        },
        selectCRMContacts: function () {
            var rows = $("#tb_ListCRMContacts").bootstrapTable('getSelections');
            if (rows.length == 0) {
                toastr.warning("至少选择一个客户!");
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
            $.ajax({
                url: "${pageContext.request.contextPath}/initiateContacts/saveAndUpdateList",
                type: "post",
                dataType: "json",
                data: {formData: JSON.stringify(data)},
                success: function (result) {
                    if (result.ret) {
                        objProject.unit_information.loadContactList();
                        $('#divBoxCRMContacts').modal("hide");
                    }
                },
                error: function (result) {
                    toastr.warning("调用服务端方法失败，失败!");
                    console.log(result);
                }
            })
        },
        copyContacts: function (data, callback) {
            $.ajax({
                url: "${pageContext.request.contextPath}/initiateContacts/copyContacts",
                type: "post",
                dataType: "json",
                data: data,
                success: function (result) {
                    if (result.ret) {
                        callback();
                    }
                },
                error: function (result) {
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
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
                $("#" + objProject.config.info.frm).find("select.valueType").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.serviceComeFrom, item.serviceComeFrom, function (html, data) {
                console.log(html);
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

            $("#" + objProject.config.info.frm).find("select.valueType").change(function () {
                var entrustPurpose = $("#" + objProject.config.info.frm).find("select.entrustPurpose").find("option:selected").val();
                var valueType = $("#" + objProject.config.info.frm).find("select.valueType").find("option:selected").val();
                AssessCommon.getDataDicInfo(valueType, function (data) {
                    if (data) {
                        console.log(objProject.Trim(data.remark));
                        $("#" + objProject.config.info.frm).find("input[name='remarkValueType']").val($.trim(data.remark));
                    }
                });
                if (entrustPurpose && valueType) {
                    entrustPurpose = "," + entrustPurpose + ",";
                    valueType = "," + valueType + ",";
                    $.ajax({
                        url: "${pageContext.request.contextPath}/projectInfo/getValueDefinition",
                        type: "post",
                        dataType: "json",
                        data: {
                            entrustPurpose: entrustPurpose,
                            valueType: valueType
                        },
                        success: function (result) {
                            if (result.ret) {
                                if (result.data) {
                                    $("#" + objProject.config.info.frm).find("select.propertyScope").val([result.data.propertyScope]).trigger('change');
                                    $("#" + objProject.config.info.frm).find("input[name='scopeInclude']").val(result.data.scopeInclude);
                                    $("#" + objProject.config.info.frm).find("input[name='scopeNotInclude']").val(result.data.scopeNotInclude);
                                } else {
                                    $("#" + objProject.config.info.frm).find("select.propertyScope").val(null).trigger("change");
                                    $("#" + objProject.config.info.frm).find("input[name='scopeInclude']").val("");
                                    $("#" + objProject.config.info.frm).find("input[name='scopeNotInclude']").val("");
                                }
                            }
                            else {
                                toastr.warning(result.errmsg);
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })

                }
            });
            $("#" + objProject.config.info.frm).find("select.entrustPurpose").change(function () {
                var entrustPurpose = $("#" + objProject.config.info.frm).find("select.entrustPurpose").find("option:selected").val();
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
                var valueType = $("#" + objProject.config.info.frm).find("select.valueType").find("option:selected").val();

                if (entrustPurpose && valueType) {
                    entrustPurpose = "," + entrustPurpose + ",";
                    valueType = "," + valueType + ",";
                    $.ajax({
                        url: "${pageContext.request.contextPath}/projectInfo/getValueDefinition",
                        type: "post",
                        dataType: "json",
                        data: {
                            entrustPurpose: entrustPurpose,
                            valueType: valueType
                        },
                        success: function (result) {
                            if (result.ret) {
                                if (result.data) {
                                    $("#" + objProject.config.info.frm).find("select.propertyScope").val([result.data.propertyScope]).trigger('change');
                                    $("#" + objProject.config.info.frm).find("input[name='scopeInclude']").val(result.data.scopeInclude);
                                    $("#" + objProject.config.info.frm).find("input[name='scopeNotInclude']").val(result.data.scopeNotInclude);
                                } else {
                                    $("#" + objProject.config.info.frm).find("select.propertyScope").val(null).trigger("change");
                                    $("#" + objProject.config.info.frm).find("input[name='scopeInclude']").val("");
                                    $("#" + objProject.config.info.frm).find("input[name='scopeNotInclude']").val("");

                                }
                            }
                            else {
                                toastr.warning(result.errmsg);
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })

                }
            });

            $("#" + objProject.config.info.frm).find("select.entrustAimType_p").change(function () {
                var remarkEntrustPurpose = $("#" + objProject.config.info.frm).find("input[name='remarkEntrustPurpose']");
                remarkEntrustPurpose.val('');
                var entrustAimType = $("#" + objProject.config.info.frm).find("select.entrustAimType_p").find("option:selected").val();
                if (entrustAimType) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/projectInfo/getRemarkEntrustPurpose",
                        type: "post",
                        dataType: "json",
                        data: {
                            entrustAimType: entrustAimType,
                        },
                        success: function (result) {
                            if (result.ret) {
                                if (result.data) {
                                    remarkEntrustPurpose.val(result.data.remark);
                                }
                            }
                            else {
                                toastr.warning(result.errmsg);
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    })

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
                    $("#consignor_tab_unit").show();
                }
                if ($(this).val() == 0) {
                    $("#" + objProject.config.consignor.frm).find("input[type='radio'][name='csType'][value='1']").removeAttr("checked");
                    $(this).attr("checked", true);
                    $("#consignor_tab").show();
                    $("#consignor_tab_unit").hide();
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
                    $("#consignor_tab_unit").show();
                }
                if (item.csType == 0) {
                    $("#" + objProject.config.consignor.frm).find("input[type='radio'][name='csType'][value='0']").attr("checked", true);
                    $("#consignor_tab").show();
                    $("#consignor_tab_unit").hide();
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
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="objProject.consignor.getContact(' + row.id + ')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="objProject.consignor.deleteContact(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
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
                    $("#possessor_tab_unit").show();
                }
                if ($(this).val() == 0) {
                    $("#" + objProject.config.possessor.frm).find("input[type='radio'][name='pType'][value='1']").removeAttr("checked");
                    $(this).attr("checked", true);
                    $("#possessor_tab").show();
                    $("#possessor_tab_unit").hide();
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
                    $("#possessor_tab_unit").show();
                    $("#" + objProject.config.possessor.frm).find("input[type='radio'][name='pType'][value='1']").attr("checked", true);
                }
                if (item.pType == 0) {
                    $("#possessor_tab").show();
                    $("#possessor_tab_unit").hide();
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
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="objProject.possessor.getContact(' + row.id + ')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="objProject.possessor.deleteContact(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
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
                    //附件拷贝
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
                    var str = '<div class="btn-margin">';
                    str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="objProject.unit_information.getContact(' + row.id + ')"><i class="fa fa-edit fa-white"></i></a>';
                    str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="objProject.unit_information.deleteContact(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                    str += '</div>';
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
                $(this_).closest('.input-group').find("input[name='departmentName']").val(nodes[0].text);
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
            onSelected: function (data) {
                $(this_).closest('.input-group').find("input[name='userAccountManager']").val(data.account);
                $(this_).closest('.input-group').find("input[name='userAccountManagerName']").val(data.name);
            }
        });
    };

    //项目成员
    objProject.selectUserAccountMember = function (this_) {
        erpEmployee.select({
            multi: true,
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
                data.forEach(function (node, i) {
                    if (node.uuid) {
                        uuids.push(node.uuid);
                    }
                    if (node.name) {
                        names.push(node.name);
                    }
                });
                if (uuids.length == 0) {
                    Alert('有效合同为0');
                    return false;
                }
                if (uuids.length >= 1) {
                    $(this_).closest('.input-group').find("input[name='contractId']").val(uuids.join(","));
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
            companyId:${companyId},
            onSelected: function (nodes) {
                if (nodes.length == 0) {
                    return false;
                }
                nodes.forEach(function (node, i) {
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
                            //2019-06-11之后不再写入数据了 , 并且也不再清除了 ,待删除数据
                            if (false) {
                                var query = {
                                    cType: objProject.config.unit_information.contacts.nodeKey,
                                    cPid: objProject.isNotBlank('${projectInfo.unitInformationVo.id}') ? '${projectInfo.unitInformationVo.id}' : '0'
                                };
                                //清除报告使用单位写入的联系人
                                objProject.commonContacts.clear(
                                    query,
                                    function () {
                                        query.customerId = msg.data.id;
                                        //crm数据库获取联系人并且在本地数据库写入联系人
                                        objProject.commonContacts.writeCustomerLinkmanInContacts(query, function () {
                                            //把本地数据库写入的联系人展示出来
                                            objProject.unit_information.loadContactList();
                                        });
                                    }
                                );
                            }
                        }
                    });
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
            Alert('还未填写委托人联系人信息');
            return false;
        }
        if (!this.hasLinkman(this.config.possessor.table)) {
            Alert('还未填写占有人联系人信息');
            return false;
        }
        if (!this.hasLinkman(this.config.unit_information.table)) {
            Alert('还未填写报告使用单位联系人信息');
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
            if (objProject.isNotBlank('${projectInfoVoJson}')) {
                var str = $("#projectInfoVoJson").val();
                if (objProject.isNotBlank(str)) {
                    var data = {};
                    try {
                        data = JSON.parse(str);
                    } catch (e) {
                        console.log(e);
                    }
                    if (objProject.isNotBlankObjectProperty(data)) {
                        objProject.info.loadInit(data);
                        objProject.consignor.loadInit(data.consignorVo);
                        objProject.possessor.loadInit(data.possessorVo);
                        objProject.unit_information.loadInit(data.unitInformationVo);
                    }
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
                                                   class="form-control">
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
</div>

<script type="text/html" id="contactModelHTML">
    <div class="modal-body">
        <input type="hidden" name="id">
        <div class="row">
            <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                <div class="panel-body">
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                姓名<span class="symbol required"></span>
                            </label>
                            <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                <input type="text" name="cName" placeholder="姓名"
                                       class="form-control" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                部门<span class="symbol required"></span>
                            </label>
                            <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                <input type="text" name="cDept" placeholder="部门"
                                       class="form-control" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                电话号码<span class="symbol required"></span>
                            </label>
                            <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                <input type="text" name="cPhone" data-rule-number='true' name="number"
                                       placeholder="号码（请输入数字）"
                                       class="form-control" required="required">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="x-valid">
                            <label class=" col-xs-2  col-sm-2  col-md-2  col-lg-2  control-label">
                                邮箱
                            </label>
                            <div class=" col-xs-10  col-sm-10  col-md-10  col-lg-10 ">
                                <input type="text" name="cEmail" placeholder="邮箱"
                                       class="form-control">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</script>
