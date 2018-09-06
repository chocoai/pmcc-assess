

function Contacts() {
};
Contacts.prototype.config = function () {
    var Contacts = {};
    /**
     * 根据此处约定设置
     * com.copower.pmcc.assess.common.enums.InitiateContactsEnum
     */
    Contacts.CONSIGNOR = {key: "CONSIGNOR", name: "委托人", nodeKey: 1};
    Contacts.POSSESSOR = {key: "POSSESSOR", name: "占有人", nodeKey: 2};
    Contacts.UNIT_INFORMATION = {key: "UNIT_INFORMATION", name: "报告使用单位", nodeKey: 3};
    return Contacts;
};


var attachmentIdInitiateConsignor = null;
//委托人
function CONSIGNOR() {

}
CONSIGNOR.prototype = {
    //选项卡
    tabControl:function () {
        $("#changeType input[type='radio'][name='csType']").change(function () {
            if ($(this).val() == 1) {
                $("#changeType input[type='radio'][name='csType'][value='0']").removeAttr("checked");
                $(this).attr("checked", true);
                $("#no_legal_person").hide();
                $("#legal_person").show();
            }
            if ($(this).val() == 0) {
                $("#changeType input[type='radio'][name='csType'][value='1']").removeAttr("checked");
                $(this).attr("checked", true);
                $("#no_legal_person").show();
                $("#legal_person").hide();
            }
        });
    },
    //选项卡 修改页面强制执行
    tabControlUpdate:function (type) {
        if (type == 0){
            $("#no_legal_person").show();
            $("#legal_person").hide();
        }
        if (type == 1){
            $("#no_legal_person").hide();
            $("#legal_person").show();
        }
    },
    setAttachmentIdInitiateConsignor:function (item) {
        attachmentIdInitiateConsignor = item;
    },
    getAttachmentIdInitiateConsignor:function () {
        return attachmentIdInitiateConsignor;
    }
};

//带出委托人标识符附件
var possessorTakeFile = true;
//占有人
function POSSESSOR() {

}
POSSESSOR.prototype = {
    //选项卡
    tabControl:function () {
        /**
        * @author:  zch
        * 描述:这里会处理 把委托人的信息数据 带入 占有人中
        * @date: 2018-08-29
        **/
        $("#changeType1 input[type='radio'][name='pType']").change(function () {
            var value = $("#changeType :radio:checked").val();
            if ($(this).val() == 1) {
                $("#changeType1 input[type='radio'][name='pType'][value='0']").removeAttr("checked");
                $(this).attr("checked", true);
                POSSESSOR.prototype.takeOutCONSIGNOR.legal();
            }
            if ($(this).val() == 0) {
                $("#changeType1 input[type='radio'][name='pType'][value='1']").removeAttr("checked");
                $(this).attr("checked", true);
                POSSESSOR.prototype.takeOutCONSIGNOR.noLegal();
            }
            POSSESSOR.prototype.takeOutCONSIGNOR.contacts.init();
            if (POSSESSOR.prototype.takeOutCONSIGNOR.contacts.getPossessorTakeFile()){
                POSSESSOR.prototype.takeOutCONSIGNOR.contacts.files();
            }
        });
    }
    ,
    //选项卡 修改页面强制执行
    tabControlUpdate:function (type) {
        if (type == 0){
            $("#no_legal_person1").show();
            $("#legal_person1").hide();
        }
        if (type == 1){
            $("#no_legal_person1").hide();
            $("#legal_person1").show();
        }
    },
    /**
    * @author:  zch
    * 描述:占有人 把委托人的数据带入
    * @date:2018-08-29
    **/
    takeOutCONSIGNOR:{
        //法人
        legal:function () {
            $("#no_legal_person1").hide();
            $("#legal_person1").show();

            $("#changeType1 input[type='radio'][name='pType'][value='1']").attr("checked", true);
            $("#changeType1 input[type='radio'][name='pType'][value='0']").removeAttr("checked");
            if ($("#pEntrustmentUnitX").length > 0) {
                $("#pEntrustmentUnitX").val($("#csEntrustmentUnitX").val());
            }
            $("#pEntrustmentUnit").val($("#csEntrustmentUnit").val());
            $("#pLegalRepresentative").val($("#csLegalRepresentative").val());
            $("#pSociologyCode").val($("#csSociologyCode").val());
            $("#pScopeOperation").val($("#csScopeOperation").val());
            $("#pAddress").val($("#csAddress").val());
            var selectV = $("#csUnitProperties option:selected").val();
            if (selectV != null && selectV != '') {
                $("#pUnitProperties").val(selectV);
            }
        },
        //非法人(自然人)
        noLegal:function () {
            $("#no_legal_person1").show();
            $("#legal_person1").hide();

            $("#pName").val($("#csName").val());
            $("#pIdcard").val($("#csIdcard").val());
            $("#pAddress2").val($("#csAddress2").val());
        },
        //联系人
        contacts:{
            init:function () {
                var data = POSSESSOR.prototype.takeOutCONSIGNOR.contacts.getContact();
                if (data != null){
                    if (data.length >= 1){
                        POSSESSOR.prototype.takeOutCONSIGNOR.contacts.setContact(data);
                    }
                }
            },
            //把委托人上传的附件给带出来
            files:function () {
                var data = null;
                var attachmentIdInitiate = CONSIGNOR.prototype.getAttachmentIdInitiateConsignor();
                if (attachmentIdInitiate != null){//说明委托人有上传附件
                    $.ajax({
                        type: "POST",
                        url: Contacts.prototype.getUrl()+"/dataDeveloper/getSysAttachmentDto",
                        data: {attachmentId:attachmentIdInitiate},
                        success: function (result) {
                            if (result.ret) {
                                data = result.data ;
                                POSSESSOR.prototype.takeOutCONSIGNOR.contacts.saveFile(data);//copy file
                            } else {
                                Alert("传输数据失败，失败原因:" + result.errmsg);
                            }
                        }
                    });
                }
            },
            /**
            * @author:  zch
            * 描述:新增附件
            * @date:2018-09-04
            **/
            saveFile:function (data) {
                //替换关键数据
                data.tableName = AssessDBKey.InitiatePossessor ;
                data.id = null;
                data.fieldsName = "pAttachmentProjectEnclosureId" ;
                $.ajax({
                    type: "POST",
                    url: Contacts.prototype.getUrl()+"/dataDeveloper/saveAndUpdateSysAttachmentDto",
                    data: data,
                    success: function (result) {
                        if (result.ret) {
                            showFileInitiatePossessor();
                            POSSESSOR.prototype.takeOutCONSIGNOR.contacts.setPossessorTakeFile(false);
                        } else {
                            Alert("传输数据失败，失败原因:" + result.errmsg);
                        }
                    }
                });
            },
            //把联系人信息数据写入到数据库中 (把从委托人获取的联系人写入到占有人中)
            setContact:function (data) {
                var item = new Array();
                $.each(data,function (i,n) {
                    //write dataBase (然后处理必要的信息数据)
                    n.cType = Contacts.prototype.config().POSSESSOR.nodeKey ;
                    n.id = null;
                    item.push(n);
                });
                $.ajax({
                    type: "POST",
                    url: Contacts.prototype.getUrl()+"/projectInfo/Contacts/save",
                    data: {formData:JSON.stringify({contacts:item})},
                    success: function (result) {
                        if (result.ret) {
                            Contacts.prototype.POSSESSOR().loadDataList(null);
                            toastr.success('成功');
                        } else {
                            Alert("传输数据失败，失败原因:" + result.errmsg);
                        }
                    }
                });
            },
            getContact:function () {
                var data = null;
                var item = null;
                item = $("#" + Contacts.prototype.POSSESSOR().getData().table).bootstrapTable("getData");
                if (item != null){
                    if (item.length >= 1){//说明已经存在数据则不再写
                        return data ;
                    }
                }
                data = $("#" + Contacts.prototype.CONSIGNOR().getData().table).bootstrapTable("getData");
                return data;
            },
            getPossessorTakeFile:function () {
                return possessorTakeFile;
            },
            setPossessorTakeFile:function (item) {
                possessorTakeFile = item ;
            }
        }
    }
};

var customerId_UNIT_INFORMATION = 0 ;
//报告使用单位
function UNIT_INFORMATION() {

}
UNIT_INFORMATION.prototype = {
    //选择客户
    selectCustomer:function () {
        crmCustomer.select({
            multi: false,//是否允许多选
            onSelected: function (nodes) {
                if ($("#uUseUnitX").length > 0) {
                    $("#uUseUnitX").val(nodes[0].name);
                }
                if ($("#uUseUnit").length > 0) {
                    $("#uUseUnit").val(nodes[0].id);
                }
                var id = nodes[0].id;
                UNIT_INFORMATION.prototype.setCustomerId_UNIT_INFORMATION(id);
                Contacts.prototype.UNIT_INFORMATION().loadDataList(null,id);
                $.ajax({
                    type: "POST",
                    url: Contacts.prototype.getUrl()+"/projectInfo/getCRMList",
                    data: "crmId=" + id,
                    success: function (msg) {
                        UNIT_INFORMATION.prototype.writeCRM(msg, "uLegalRepresentative", "uAddress", "uScopeOperation", "uCertificateNumber","uUnitProperties");
                    }
                });
            }
        });
    },
    writeCRM:function (msg, id1, id2, id3, id4,id5) {
        var legalRepresentative = msg.legalRepresentative;//法定代表人
        var address = msg.address;//地址
        var businessScope = msg.businessScope;//经营范围
        var certificateNumber = msg.certificateNumber;//社会统一信用代码
        var unitProperties = msg.unitProperties;//单位性质
        $("#" + id1).val(legalRepresentative);
        $("#" + id2).val(address);
        $("#" + id3).val(businessScope);
        $("#" + id4).val(certificateNumber);
        $("#" + id5).val(unitProperties);
    },
    setCustomerId_UNIT_INFORMATION:function (item) {
        customerId_UNIT_INFORMATION = item;
    },
    getCustomerId_UNIT_INFORMATION:function () {
        return customerId_UNIT_INFORMATION ;
    }
}

/*--------------------------------------------------------------联系人 start-------------------------------------------------------- */
//联系人中的报告使用单位
Contacts.prototype.UNIT_INFORMATION = function () {
    var Fun = {};
    //初始化
    Fun.init = function () {

    };
    Fun.getData = function () {
        var data = {};
        data.table = "tb_ListUNIT_INFORMATION";
        data.crmTable = "tb_ListCRMContacts";
        data.frm = "frmUNIT_INFORMATIONContacts";
        data.pid = "0";
        data.box = "divBoxUNIT_INFORMATIONContacts";
        data.crmBox = "divBoxCRMContacts";
        return data;
    };
    Fun.crmContacts = {
        findCRMContacts:function () {
            var crmContactsName = null;
            crmContactsName = $("#"+Contacts.prototype.UNIT_INFORMATION().getData().crmBox+" "+"input[name='" + "name" + "']").val();
            Contacts.prototype.UNIT_INFORMATION().crmContacts.loadDataList(null,null,crmContactsName);
        },
        showModel:function () {
            $('#' + Contacts.prototype.UNIT_INFORMATION().getData().crmBox).modal("show");
            Contacts.prototype.UNIT_INFORMATION().crmContacts.loadDataList(null);
        },
        next:function () {
            var customerId = UNIT_INFORMATION.prototype.getCustomerId_UNIT_INFORMATION();
            var pageIndex = $("#"+Contacts.prototype.UNIT_INFORMATION().getData().crmBox +" .pageIndex").html();
            pageIndex = parseInt(pageIndex);
            if (!Contacts.prototype.UNIT_INFORMATION().crmContacts.isEmpty(customerId)){
                customerId = 0;
            }
            var page = 0;
            $.ajax({
                url: Contacts.prototype.getUrl() + "/projectInfo/findCrmProjectContactsVos",
                type: "get",
                dataType: "json",
                data: {customerId: customerId,total:"total"},
                success: function (result) {
                    if (result.ret) {
                       var total = result.data ;
                       if ((total % 4) > 0){
                           page = total / 4 ;
                           page = parseInt(page) +1 ;
                       }else {
                           page = total / 4;
                       }
                       pageIndex += 1;
                       console.log(page);
                       console.log(pageIndex);
                       if (pageIndex <= page){
                           Contacts.prototype.UNIT_INFORMATION().crmContacts.loadDataList(pageIndex,4,null);
                           $("#"+Contacts.prototype.UNIT_INFORMATION().getData().crmBox +" .pageIndex").html(pageIndex);
                       }
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        },
        previous:function () {
            var pageIndex = $("#"+Contacts.prototype.UNIT_INFORMATION().getData().crmBox +" .pageIndex").html();
            pageIndex = parseInt(pageIndex);
            pageIndex = pageIndex - 1;
            if (pageIndex >= 1){
                Contacts.prototype.UNIT_INFORMATION().crmContacts.loadDataList(pageIndex,4,null);
                $("#"+Contacts.prototype.UNIT_INFORMATION().getData().crmBox +" .pageIndex").html(pageIndex);
            }
        },
        loadDataList:function (pageIndex,pageSize,search) {
            var customerId = UNIT_INFORMATION.prototype.getCustomerId_UNIT_INFORMATION();
            if (!Contacts.prototype.UNIT_INFORMATION().crmContacts.isEmpty(customerId)){
                customerId = 0;
            }
            //默认4条记录
            if (!Contacts.prototype.UNIT_INFORMATION().crmContacts.isEmpty(pageSize)){
                pageSize = 4;
            }
            if (!Contacts.prototype.UNIT_INFORMATION().crmContacts.isEmpty(pageIndex)){
                pageIndex = $("#"+Contacts.prototype.UNIT_INFORMATION().getData().crmBox +" .pageIndex").html();
            }
            var data = {
                customerId:customerId,
                pageSize:pageSize,
                pageIndex:pageIndex,
                search:search
            };
            console.log("test == >");
            console.log(data);
            var cols = [];
            cols.push({field: 'name', title: '姓名',searchable:true});
            cols.push({field: 'department', title: '部门'});
            cols.push({field: 'phoneNumber', title: '电话号码'});
            cols.push({field: 'email', title: '邮箱'});
            cols.push({field: 'id', visible: false, title: "id"});

            $("#" + Contacts.prototype.UNIT_INFORMATION().getData().crmTable).bootstrapTable("destroy");
            TableInit(Contacts.prototype.UNIT_INFORMATION().getData().crmTable, Contacts.prototype.getUrl() + "/projectInfo/getProjectContactsVos", cols, {
                crmContacts: "crmContacts",customerId:data.customerId,pageIndex:data.pageIndex,pageSize:data.pageSize,searchCrm:data.search
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
            });
        },
        isEmpty:function (item) {
            if (item){
                return true;
            }
            return false;
        }
    };
    /**
     * 显示模态框
     */
    Fun.showModel = function () {
        var pid = formParams(Contacts.prototype.UNIT_INFORMATION().getData().frm).pid;
        $("#" + Contacts.prototype.UNIT_INFORMATION().getData().frm).clearAll();
        $('#' + Contacts.prototype.UNIT_INFORMATION().getData().box).modal("show");
        if (!Contacts.prototype.isEmpty(pid)) {
            $("#" + Contacts.prototype.UNIT_INFORMATION().getData().frm).initForm({pid: pid});
        }
    };

    Fun.delete = function (id) {
        var pid = formParams(Contacts.prototype.UNIT_INFORMATION().getData().frm).pid;
        Alert("确认要删除么？", 2, null, function () {
            $.ajax({
                url: Contacts.prototype.getUrl() + "/projectInfo/Contacts/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        if (Contacts.prototype.isEmpty(pid)) {//说明为初始化的时候的删除
                            Contacts.prototype.UNIT_INFORMATION().loadDataList(null,null);
                        } else {//说明为审批未通过的时候的删除 (修改页面)
                            Contacts.prototype.UNIT_INFORMATION().loadDataList(pid,null);
                        }
                        window.onload;
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    };

    Fun.loadDataList = function (pid,crmId) {
        var data = {};
        data.flag = Contacts.prototype.config().UNIT_INFORMATION.nodeKey;
        if (Contacts.prototype.isEmpty(pid)) {//初始化
            pid = Contacts.prototype.UNIT_INFORMATION().getData().pid;
        } else {//修改
            $("#" + Contacts.prototype.UNIT_INFORMATION().getData().frm).initForm({pid: pid});
            console.log($("#" + Contacts.prototype.UNIT_INFORMATION().getData().frm).val());
        }
        data.pid = pid;
        data.crmId = crmId;
        var cols = [];
        cols.push({field: 'cName', title: '姓名',searchable:true});
        cols.push({field: 'cDept', title: '部门'});
        cols.push({field: 'cPhone', title: '电话号码'});
        cols.push({field: 'cEmail', title: '邮箱'});
        cols.push({field: 'id', visible: false, title: "id"});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="Contacts.prototype.UNIT_INFORMATION().get(' + row.id + ')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="Contacts.prototype.UNIT_INFORMATION().delete(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + Contacts.prototype.UNIT_INFORMATION().getData().table).bootstrapTable("destroy");
        TableInit(Contacts.prototype.UNIT_INFORMATION().getData().table, Contacts.prototype.getUrl() + "/projectInfo/getProjectContactsVos", cols, {
            type: data.flag, pid: data.pid,crmId:data.crmId
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    };

    Fun.save = function () {
        var data = formParams(Contacts.prototype.UNIT_INFORMATION().getData().frm);
        var pid = data.pid;
        data.cType = Contacts.prototype.config().UNIT_INFORMATION.nodeKey;
        if (!$("#"+Contacts.prototype.UNIT_INFORMATION().getData().frm).valid()){
            return false;
        }
        if (!Contacts.prototype.isEmpty(pid)) {//修改页面时候的 新增
            data.cPid = pid ;
        }
        $.ajax({
            url: Contacts.prototype.getUrl() + "/projectInfo/Contacts/save",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    if (Contacts.prototype.isEmpty(pid)) {//说明为初始化的时候的 新增
                        toastr.success('保存成功');
                        Contacts.prototype.UNIT_INFORMATION().loadDataList(null,null);
                    } else {//说明为审批未通过的时候的新增 (修改页面)
                        Contacts.prototype.UNIT_INFORMATION().loadDataList(pid,null);
                    }
                    $('#' + Contacts.prototype.UNIT_INFORMATION().getData().box).modal('hide');
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

    Fun.get = function (id) {
        var pid = formParams(Contacts.prototype.UNIT_INFORMATION().getData().frm).pid;
        $("#" + Contacts.prototype.UNIT_INFORMATION().getData().frm).clearAll();
        $.ajax({
            url: Contacts.prototype.getUrl() + "/projectInfo/Contacts/get",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (Contacts.prototype.isEmpty(pid)) {//初始化
                        $("#" + Contacts.prototype.UNIT_INFORMATION().getData().frm).initForm(result.data);
                    } else {//修改页面
                        var data = result.data;
                        data.pid = pid;
                        $("#" + Contacts.prototype.UNIT_INFORMATION().getData().frm).initForm(data);
                    }
                    $('#' + Contacts.prototype.UNIT_INFORMATION().getData().box).modal("show");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };

    return Fun;
};

//联系人中的占有人
Contacts.prototype.POSSESSOR = function () {
    var Fun = {};
    //初始化
    Fun.init = function () {

    };
    Fun.getData = function () {
        var data = {};
        data.table = "tb_ListPossessor";
        data.frm = "frmPossessorContacts";
        data.pid = "0";
        data.box = "divBoxPossessorContacts";
        return data;
    };
    /**
     * 显示模态框
     */
    Fun.showModel = function () {
        var pid = formParams(Contacts.prototype.POSSESSOR().getData().frm).pid;
        $("#" + Contacts.prototype.POSSESSOR().getData().frm).clearAll();
        $('#' + Contacts.prototype.POSSESSOR().getData().box).modal("show");
        if (!Contacts.prototype.isEmpty(pid)) {
            $("#" + Contacts.prototype.POSSESSOR().getData().frm).initForm({pid: pid});
        }
    };

    Fun.delete = function (id) {
        var pid = formParams(Contacts.prototype.POSSESSOR().getData().frm).pid;
        Alert("确认要删除么？", 2, null, function () {
            $.ajax({
                url: Contacts.prototype.getUrl() + "/projectInfo/Contacts/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        if (Contacts.prototype.isEmpty(pid)) {//说明为初始化的时候的删除
                            Contacts.prototype.POSSESSOR().loadDataList(null);
                        } else {//说明为审批未通过的时候的删除 (修改页面)
                            Contacts.prototype.POSSESSOR().loadDataList(pid);
                        }
                        window.onload;
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    };

    Fun.loadDataList = function (pid) {
        var data = {};
        data.flag = Contacts.prototype.config().POSSESSOR.nodeKey;
        if (Contacts.prototype.isEmpty(pid)) {//初始化
            pid = Contacts.prototype.POSSESSOR().getData().pid;
        } else {//修改
            $("#" + Contacts.prototype.POSSESSOR().getData().frm).initForm({pid: pid});
        }
        data.pid = pid;
        var cols = [];
        cols.push({field: 'cName', title: '姓名'});
        cols.push({field: 'cDept', title: '部门'});
        cols.push({field: 'cPhone', title: '电话号码'});
        cols.push({field: 'cEmail', title: '邮箱'});
        cols.push({field: 'id', visible: false, title: "id"});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="Contacts.prototype.POSSESSOR().get(' + row.id + ')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="Contacts.prototype.POSSESSOR().delete(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + Contacts.prototype.POSSESSOR().getData().table).bootstrapTable("destroy");
        TableInit(Contacts.prototype.POSSESSOR().getData().table, Contacts.prototype.getUrl() + "/projectInfo/getProjectContactsVos", cols, {
            type: data.flag, pid: data.pid
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    };

    Fun.save = function () {
        var data = formParams(Contacts.prototype.POSSESSOR().getData().frm);
        var pid = data.pid;
        data.cType = Contacts.prototype.config().POSSESSOR.nodeKey;
        if (!$("#"+Contacts.prototype.POSSESSOR().getData().frm).valid()){
            return false;
        }
        if (!Contacts.prototype.isEmpty(pid)) {//修改页面时候的 新增
            data.cPid = pid ;
        }
        $.ajax({
            url: Contacts.prototype.getUrl() + "/projectInfo/Contacts/save",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    if (Contacts.prototype.isEmpty(pid)) {//说明为初始化的时候的 新增
                        toastr.success('保存成功');
                        Contacts.prototype.POSSESSOR().loadDataList(null);
                    } else {//说明为审批未通过的时候的新增 (修改页面)
                        Contacts.prototype.POSSESSOR().loadDataList(pid);
                    }
                    $('#' + Contacts.prototype.POSSESSOR().getData().box).modal('hide');
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

    Fun.get = function (id) {
        var pid = formParams(Contacts.prototype.POSSESSOR().getData().frm).pid;
        $("#" + Contacts.prototype.POSSESSOR().getData().frm).clearAll();
        $.ajax({
            url: Contacts.prototype.getUrl() + "/projectInfo/Contacts/get",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (Contacts.prototype.isEmpty(pid)) {//初始化
                        $("#" + Contacts.prototype.POSSESSOR().getData().frm).initForm(result.data);
                    } else {//修改页面
                        var data = result.data;
                        data.pid = pid;
                        $("#" + Contacts.prototype.POSSESSOR().getData().frm).initForm(data);
                    }
                    $('#' + Contacts.prototype.POSSESSOR().getData().box).modal("show");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };
    return Fun;
};

//联系人中的委托人
Contacts.prototype.CONSIGNOR = function () {
    var CONSIGNORFun = {};
    //初始化
    CONSIGNORFun.init = function () {

    };
    CONSIGNORFun.getData = function () {
        var data = {};
        data.table = "tb_ListConsignor";
        data.frm = "frmConsignorContacts";
        data.pid = "0";
        data.box = "divBoxConsignorContacts";
        return data;
    };
    /**
     * 显示模态框
     */
    CONSIGNORFun.showModel = function () {
        var pid = formParams(Contacts.prototype.CONSIGNOR().getData().frm).pid;
        $("#" + Contacts.prototype.CONSIGNOR().getData().frm).clearAll();
        $('#' + Contacts.prototype.CONSIGNOR().getData().box).modal("show");
        if (!Contacts.prototype.isEmpty(pid)) {
            $("#" + Contacts.prototype.CONSIGNOR().getData().frm).initForm({pid: pid});
        }
    };

    CONSIGNORFun.delete = function (id) {
        var pid = formParams(Contacts.prototype.CONSIGNOR().getData().frm).pid;
        Alert("确认要删除么？", 2, null, function () {
            $.ajax({
                url: Contacts.prototype.getUrl() + "/projectInfo/Contacts/delete",
                type: "post",
                dataType: "json",
                data: {id: id},
                success: function (result) {
                    Loading.progressHide();
                    if (result.ret) {
                        toastr.success('删除成功');
                        if (Contacts.prototype.isEmpty(pid)) {//说明为初始化的时候的删除
                            Contacts.prototype.CONSIGNOR().loadDataList(null);
                        } else {//说明为审批未通过的时候的删除 (修改页面)
                            Contacts.prototype.CONSIGNOR().loadDataList(pid);
                        }
                        window.onload;
                    }
                    else {
                        Alert("删除数据失败，失败原因:" + result.errmsg);
                    }
                },
                error: function (result) {
                    Loading.progressHide();
                    Alert("调用服务端方法失败，失败原因:" + result);
                }
            })
        })
    };

    CONSIGNORFun.loadDataList = function (pid) {
        var data = {};
        data.flag = Contacts.prototype.config().CONSIGNOR.nodeKey;
        if (Contacts.prototype.isEmpty(pid)) {//初始化
            pid = Contacts.prototype.CONSIGNOR().getData().pid;
        } else {//修改
            $("#" + Contacts.prototype.CONSIGNOR().getData().frm).initForm({pid: pid});
        }
        data.pid = pid;
        var cols = [];
        cols.push({field: 'cName', title: '姓名'});
        cols.push({field: 'cDept', title: '部门'});
        cols.push({field: 'cPhone', title: '电话号码'});
        cols.push({field: 'cEmail', title: '邮箱'});
        cols.push({field: 'id', visible: false, title: "id"});

        cols.push({
            field: 'id', title: '操作', formatter: function (value, row, index) {
                var str = '<div class="btn-margin">';
                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="Contacts.prototype.CONSIGNOR().get(' + row.id + ')"><i class="fa fa-edit fa-white"></i></a>';
                str += '<a class="btn btn-xs btn-warning tooltips"  data-placement="top" data-original-title="删除" onclick="Contacts.prototype.CONSIGNOR().delete(' + row.id + ')"><i class="fa fa-minus fa-white"></i></a>';
                str += '</div>';
                return str;
            }
        });
        $("#" + Contacts.prototype.CONSIGNOR().getData().table).bootstrapTable("destroy");
        TableInit(Contacts.prototype.CONSIGNOR().getData().table, Contacts.prototype.getUrl() + "/projectInfo/getProjectContactsVos", cols, {
            type: data.flag, pid: data.pid
        }, {
            showColumns: false,
            showRefresh: false,
            search: false
        });
    };

    CONSIGNORFun.save = function () {
        var data = formParams(Contacts.prototype.CONSIGNOR().getData().frm);
        var pid = data.pid;
        data.cType = Contacts.prototype.config().CONSIGNOR.nodeKey;
        if (!$("#"+Contacts.prototype.CONSIGNOR().getData().frm).valid()) {
            return false;
        }
        if (!Contacts.prototype.isEmpty(pid)) {//修改页面时候的 新增
            data.cPid = pid ;
        }
        $.ajax({
            url: Contacts.prototype.getUrl() + "/projectInfo/Contacts/save",
            type: "post",
            dataType: "json",
            data: data,
            success: function (result) {
                if (result.ret) {
                    if (Contacts.prototype.isEmpty(pid)) {//说明为初始化的时候的 新增
                        toastr.success('保存成功');
                        Contacts.prototype.CONSIGNOR().loadDataList(null);
                    } else {//说明为审批未通过的时候的新增 (修改页面)
                        Contacts.prototype.CONSIGNOR().loadDataList(pid);
                    }
                    $('#' + Contacts.prototype.CONSIGNOR().getData().box).modal('hide');
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

    CONSIGNORFun.get = function (id) {
        var pid = formParams(Contacts.prototype.CONSIGNOR().getData().frm).pid;
        $("#" + Contacts.prototype.CONSIGNOR().getData().frm).clearAll();
        $.ajax({
            url: Contacts.prototype.getUrl() + "/projectInfo/Contacts/get",
            type: "get",
            dataType: "json",
            data: {id: id},
            success: function (result) {
                if (result.ret) {
                    if (Contacts.prototype.isEmpty(pid)) {//初始化
                        $("#" + Contacts.prototype.CONSIGNOR().getData().frm).initForm(result.data);
                    } else {//修改页面
                        var data = result.data;
                        data.pid = pid;
                        $("#" + Contacts.prototype.CONSIGNOR().getData().frm).initForm(data);
                    }
                    $('#' + Contacts.prototype.CONSIGNOR().getData().box).modal("show");
                }
            },
            error: function (result) {
                Alert("调用服务端方法失败，失败原因:" + result);
            }
        })
    };
    return CONSIGNORFun;
};

Contacts.prototype.isEmpty = function (data) {
    if (data == null) {
        return true;
    }
    if (data == '') {
        return true;
    }

    if (data == "") {
        return true;
    }

    if (data == "0") {
        return true;
    }
};
/*--------------------------------------------------------------联系人 end-------------------------------------------------------- */
