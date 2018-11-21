<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/pmcc-crm/js/crm-customer-utils.js"></script>
<script>
    var objProject = new Object();

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
        unit_information:{
            frm: "frm_unitinformation",
            table: "tb_ListUNIT_INFORMATION",
            boxDiv: "divBoxUNIT_INFORMATIONContacts",
            boxFrm: "frmUNIT_INFORMATIONContacts",
            contacts: {key: "UNIT_INFORMATION", name: "报告使用单位", nodeKey: 3}
        }
    };

    /**
     * 项目基础信息 加载数据
     * @type {{loadInit: objProject.info.loadInit}}
     */
    objProject.info = {
        loadInit: function (item) {
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
            AssessCommon.loadDataDicByKey(AssessDicKey.dataEntrustmentPurpose, item.entrustPurpose, function (html, data) {
                $("#" + objProject.config.info.frm).find("select.entrustPurpose").empty().html(html).trigger('change');
            });
            AssessCommon.loadDataDicByKey(AssessDicKey.project_initiate_urgency, item.urgency, function (html, data) {
                $("#" + objProject.config.info.frm).find("select.urgency").empty().html(html).trigger('change');
            });
            $("#" + objProject.config.info.frm).find("select.valueType").change(function () {
                var valueType = $("#" + objProject.config.info.frm).find("select.valueType").val();
                if (objProject.isNotBlank(valueType)) {
                    AssessCommon.getDataDicInfo(valueType, function (data) {
                        $("#" + objProject.config.info.frm).find("input[name='remarkValueType']").val(data.remark);
                    });
                }
            });
            $("#" + objProject.config.info.frm).find("select.entrustPurpose").change(function () {
                var entrustPurpose = $("#" + objProject.config.info.frm).find("select.entrustPurpose").val();
                if (objProject.isNotBlank(entrustPurpose)) {
                    AssessCommon.getDataDicInfo(entrustPurpose, function (data) {
                        $("#" + objProject.config.info.frm).find("input[name='remarkEntrustPurpose']").val(data.remark);
                    });
                }
            });
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
                    $("#no_legal_person").hide();
                    $("#legal_person").show();
                }
                if ($(this).val() == 0) {
                    $("#" + objProject.config.consignor.frm).find("input[type='radio'][name='csType'][value='1']").removeAttr("checked");
                    $(this).attr("checked", true);
                    $("#no_legal_person").show();
                    $("#legal_person").hide();
                }
            });
            $.each(objProject.config.consignor.files, function (i, n) {
                objProject.showFile(n, AssessDBKey.InitiateConsignor, objProject.isNotBlank(item.id) ? item.id : "0");
                objProject.uploadFile(n, AssessDBKey.InitiateConsignor, objProject.isNotBlank(item.id) ? item.id : "0");
            });
        },
        contactsShow: function () {
            $("#" + objProject.config.consignor.table).parent().parent().show();
        }
    };

    /**
     * 占有人
     * @type {{loadInit: objProject.possessor.loadInit, contactsShow: objProject.possessor.contactsShow}}
     */
    objProject.possessor = {
        loadInit: function (item) {
            $.each(objProject.config.possessor.files, function (i, n) {
                objProject.showFile(n, AssessDBKey.InitiatePossessor, objProject.isNotBlank(item.id) ? item.id : "0");
                objProject.uploadFile(n, AssessDBKey.InitiatePossessor, objProject.isNotBlank(item.id) ? item.id : "0");
            });
        },
        contactsShow: function () {
            $("#" + objProject.config.possessor.table).parent().parent().show();
        }
    };

    /**
     * 报告使用单位
     * @type {{loadInit: objProject.unit_information.loadInit, contactsShow: objProject.unit_information.contactsShow}}
     */
    objProject.unit_information = {
        loadInit:function (item) {

        },
        contactsShow:function () {

        }
    };

    //执业部门
    objProject.selectDepartment = function (this_) {
        var options = {
            onSelected: function (nodes) {
                $(this_).parent().prev().val(nodes[0].text);
                $(this_).parent().prev().prev().val(nodes[0].id);
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
                $(this_).parent().prev().val(data.name);
                $(this_).parent().prev().prev().val(data.account);
            }
        });
    };

    //项目成员
    objProject.selectUserAccountMember = function (this_) {
        var info = formParams(this.config.info.frm);
        if (!this.isNotBlank(info.userAccountManager)) {
            return false;
        }
        erpEmployee.select({
            multi: true,
            value: info.userAccountManager,
            onSelected: function (data) {
                $(this_).parent().prev().val(data.name);
                $(this_).parent().prev().prev().val(data.account);
            }
        });
    };

    objProject.selectCustomer = function (this_) {
        //选择客户
        crmCustomer.select({
            multi: false,//是否允许多选
            onSelected: function (nodes) {
                $(this_).parent().prev().val(nodes[0].name);
                $(this_).parent().prev().prev().val(nodes[0].id);
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/projectInfo/getCRMList",
                    data: "crmId=" + nodes[0].id,
                    success: function (msg) {
                        $("#" + objProject.config.unit_information.frm).initForm({
                            uLegalRepresentative:msg.legalRepresentative,
                            uAddress:msg.address,
                            uScopeOperation:msg.businessScope,
                            uCertificateNumber:msg.certificateNumber,
                            uUnitProperties:msg.unitProperties
                        });
                        $("#" + objProject.config.unit_information.frm).find("select[name='uUnitProperties']").val(msg.unitProperties).trigger("selected");
                    }
                });
            }
        });
    };

    objProject.loadInit = function () {
        $(function () {
            var info = {
                province: objProject.isNotBlank("${projectInfo.province}") ? "${projectInfo.province}" : null,
                city: objProject.isNotBlank("${projectInfo.city}") ? "${projectInfo.city}" : null,
                district: objProject.isNotBlank("${projectInfo.district}") ? "${projectInfo.district}" : null,
                valueType: objProject.isNotBlank("${projectInfo.valueType}") ? "${projectInfo.valueType}" : null,
                entrustPurpose: objProject.isNotBlank("${projectInfo.entrustPurpose}") ? "${projectInfo.entrustPurpose}" : null,
                urgency: objProject.isNotBlank("${projectInfo.urgency}") ? "${projectInfo.urgency}" : null,
            };
            objProject.info.loadInit(info);
            objProject.consignor.loadInit({});
            objProject.possessor.loadInit({});
        });
    };


</script>