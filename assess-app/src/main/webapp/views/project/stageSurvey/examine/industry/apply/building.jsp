<%--
 楼栋基础信息
--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="x_content">
    <form class="form-horizontal" id="navButtonBuild">
        <input type="hidden" data-name="fieldName" value="<%=request.getParameter("fieldName")%>">
        <div class="form-group">
            <div class="x-valid">
                <div class="col-sm-12">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    <div class="btn-group" data-toggle="buttons">
                        <button class="btn btn-default"
                                onclick="examineBuilding_.prototype.navButtonBuild.btnWrite(this,1);">
                            楼栋基础
                        </button>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    <div class="btn-group" data-toggle="buttons">
                        <button class="btn btn-default"
                                onclick="examineBuilding_.prototype.navButtonBuild.copyData(this,2)">
                            复制楼栋基础
                        </button>
                        <button class="btn btn-default"
                                onclick="examineBuilding_.prototype.navButtonBuild.btnWrite(this,2);">
                            第二部分
                        </button>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    <div class="btn-group" data-toggle="buttons">
                        <button class="btn btn-default"
                                onclick="examineBuilding_.prototype.navButtonBuild.copyData(this,3)">
                            复制二部分
                        </button>
                        <button class="btn btn-default"
                                onclick="examineBuilding_.prototype.navButtonBuild.btnWrite(this,3);">
                            第三部分
                        </button>
                    </div>
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                </label>
                <div class="col-sm-2">
                    <div class="btn-group" data-toggle="buttons">
                        <button class="btn btn-default"
                                onclick="examineBuilding_.prototype.navButtonBuild.copyData(this,4)">
                            复制三部分
                        </button>
                        <button class="btn btn-default"
                                onclick="examineBuilding_.prototype.navButtonBuild.btnWrite(this,4);">
                            第四部分
                        </button>
                    </div>
                </div>
            </div>
        </div>

    </form>

    <form class="form-horizontal" id="frmExamineBuilding_">
        <input type="hidden" name="id">
        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼栋号
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼栋号" name="buildingNumber"
                           class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    户型区间
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="户型区间" name="unitInterval"
                           class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业费
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="物业费(数字)" name="propertyFee"
                           data-rule-number='true' class="form-control" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    公共设施使用费
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="公共设施使用费(数字)" name="facilitiesUseFee"
                           data-rule-number='true' class="form-control" required="required">
                </div>
            </div>

            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼层起
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼层起(数字)" name="floorStart"
                           data-rule-number='true' class="form-control">
                </div>
            </div>
            <%--<input type="hidden" placeholder="编号" name="identifier"--%>
            <%--readonly="readonly" class="form-control">--%>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    编号
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="编号" name="identifier"
                           readonly="readonly" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    楼层止
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="楼层止(数字)" name="floorEnd"
                           data-rule-number='true' class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    总层数
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="总层数(数字)" data-rule-number='true'
                           name="floorCount" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑高度
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑高度(数字)" data-rule-number='true'
                           name="buildingHeight" class="form-control" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑面积
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="建筑面积(数字)" data-rule-number='true'
                           name="buildingArea" class="form-control" required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    占地面积
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="占地面积(数字)" data-rule-number='true'
                           name="coverAnArea" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    层高
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="层高(数字)" data-rule-number='true'
                           name="floorHeight" class="form-control">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    径深
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="径深(数字)" data-rule-number='true'
                           name="diameterDepth" class="form-control">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    土地使用年限
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="土地使用年限(数字)" data-rule-number='true'
                           name="landUseYear" class="form-control" required="required">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    所在位置
                </label>
                <div class="col-sm-3">
                    <input type="text" placeholder="所在位置" name="location" class="form-control"
                           required="required">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    开盘时间
                </label>
                <div class="col-sm-3">
                    <input placeholder="开盘时间"
                           name="openTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate openTime">
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    交房时间
                </label>
                <div class="col-sm-3">
                    <input placeholder="交房时间"
                           name="roomTime" data-date-format="yyyy-mm-dd"
                           class="form-control date-picker dbdate roomTime">
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业类型
                </label>
                <div class="col-sm-3">
                    <select name="propertyType"
                            class="form-control search-select select2 propertyType">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑结构上级
                </label>
                <div class="col-sm-3">
                    <select name="buildingStructure"
                            class="form-control search-select select2 buildingStructure">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑结构(下级)
                </label>
                <div class="col-sm-3">
                    <select id="frmExamineBuilding_buildingStructure"
                            name="buildingStructurePid"
                            class="form-control search-select select2 buildingstructurePid">
                        <option>请先选择建筑结构上级</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑类别
                </label>
                <div class="col-sm-3">
                    <select name="buildingCategory"
                            class="form-control search-select select2 buildingCategory">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    建筑公司
                </label>
                <div class="col-sm-3">
                    <select name="builderId"
                            class="form-control search-select select2 builderId">
                    </select>
                </div>
            </div>
            <div class="x-valid">
                <label class="col-sm-1 control-label">
                    物业公司
                </label>
                <div class="col-sm-3">
                    <select name="propertyId"
                            class="form-control search-select select2 propertyId">
                    </select>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="x-valid">
                <label class="col-sm-1 control-label">平面图<span class="symbol required"></span></label>
                <div class="col-sm-3">
                    <input id="building_floor_plan" name="frm_estate_floor_total_plan"
                           required="required" placeholder="上传附件" class="form-control" type="file">
                    <div id="_building_floor_plan"></div>
                </div>
            </div>
        </div>

    </form>
</div>
<div class="x_content">
    <div class="x_title">
        <h3>
            楼栋外装 <label class="control-label ExamineBuildingOutfitList"></label>
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick="examineBuilding_.prototype.sonModelMethod.buildingOutfit.showModel();"> 新增
            </button>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div>
        <table class="table table-bordered" id="ExamineBuildingOutfitList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>
<div class="x_content">
    <div class="x_title">
        <h3>
            屋面结构 <label class="control-label ExamineBuildingSurfaceList"></label>
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick="examineBuilding_.prototype.sonModelMethod.buildingSurface.showModel()"> 新增
            </button>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div>
        <table class="table table-bordered" id="ExamineBuildingSurfaceList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>
<div class="x_content">
    <div class="x_title">
        <h3>
            维护结构 <label class="control-label ExamineBuildingMaintenanceList"></label>
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick="examineBuilding_.prototype.sonModelMethod.buildingMaintenance.showModel();"> 新增
            </button>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div>
        <table class="table table-bordered" id="ExamineBuildingMaintenanceList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<div class="x_content">
    <div class="x_title">
        <h3>
            建筑功能 <label class="control-label examineBuildingFunctionList"></label>
            <button type="button" class="btn btn-success" data-toggle="modal"
                    onclick="examineBuilding_.prototype.sonModelMethod.buildingFunction.showModel();"> 新增
            </button>
        </h3>
        <div class="clearfix"></div>
    </div>
    <div>
        <table class="table table-bordered" id="examineBuildingFunctionList">
            <!-- cerare document add ajax data-->
        </table>
    </div>
</div>

<script type="application/javascript">
    $(function () {
        //两个方法 都可以假如选项卡载入时 初始化
        ContainerFunForInit.building.push(examineBuilding_.prototype.viewInit);//初始化方法写入容器
        ContainerFunForGetData.push(examineBuilding_.prototype.getFormData);//获取数据方法写入容器
    });
</script>
<script type="application/javascript">

    var examineBuilding_;
    (function () {
        //配置的局部变量用做 对象属性 (初始化标识符)
        var flag = true;
        var sonFlag = true; //子类标识符
        var examineBuildingMaintenanceFlag = true;//子类 标识符
        var examineBuildingSurfaceFlag = true;//子类 标识符
        var examineBuildingFunctionFlag = true;//子类 标识符
        var navButtonBuildFlag = false;//触发校验机制
        var attachmentId = null;//临时存储的附件id
        var objArray = new Array();
        examineBuilding_ = function () {

        };
        examineBuilding_.prototype = {
            /**
             * @author:  zch
             * 描述:获取所有临时数据
             * @date:2018-09-03
             **/
            getFormData: function () {
                //需要检验
                if (examineBuilding_.prototype.getNavButtonBuildFlag()) {//校验机制被触发 (页面被编辑或者被复制)
                    //因为这是 任务提交不再校验数据了
                    // if (!$("#" + examineBuilding_.prototype.config().frm).valid()) {
                    //     return false;
                    // }
                    //处理需要保存临时数据
                    examineBuilding_.prototype.navButtonBuild.especiallyDataObj();
                }
                var keyValueDto = {};
                keyValueDto.key = $("#navButtonBuild").find('[data-name="fieldName"]').val();
                keyValueDto.value = objArray;
                return keyValueDto;
            },
            //获取临时存储的附件id
            getAttachmentId: function () {
                return attachmentId;
            },
            //设置临时存储的附件id
            setAttachmentId: function (target) {
                attachmentId = target;
            },
            /**
             * @author:  zch
             * 描述:根据索引获取数据
             * @date:2018-09-03
             **/
            getObjArray: function (index) {
                var data = null;
                if (examineBuilding_.prototype.isEmpty(index)) {
                    data = objArray[index];
                    return data;
                }
            },
            /**
             * @author:  zch
             * 描述:设置数据
             * @date:2018-09-03
             **/
            setObjArrayElement: function (index, data) {
                objArray[index] = data;
            },
            setNavButtonBuildFlag: function (flag_) {
                navButtonBuildFlag = flag_;
            },
            getNavButtonBuildFlag: function () {
                return navButtonBuildFlag;
            },
            setExamineBuildingSurfaceFlag: function (flag_) {
                examineBuildingSurfaceFlag = flag_;
            },
            getExamineBuildingSurfaceFlag: function () {
                return examineBuildingSurfaceFlag;
            },
            setExamineBuildingMaintenanceFlag: function (flag_) {
                examineBuildingMaintenanceFlag = flag_;
            },
            getExamineBuildingMaintenanceFlag: function () {
                return examineBuildingMaintenanceFlag;
            },
            setFlag: function (flag_) {
                flag = flag_;
            },
            getFlag: function () {
                return flag;
            },
            setSonFlag: function (sonFlag_) {
                sonFlag = sonFlag_;
            },
            getSonFlag: function () {
                return sonFlag;
            },
            getExamineBuildingFunctionFlag: function () {
                return examineBuildingFunctionFlag;
            },
            setExamineBuildingFunctionFlag: function (target) {
                examineBuildingFunctionFlag = target;
            },
            getIdentifier: function () {
                var data = formParams(examineBuilding_.prototype.config().frm);
                var identifier = data.identifier;
                if (examineBuilding_.prototype.isEmpty(identifier)) {
                    return identifier;
                }
                return "0";
            },
            getBuildId: function () {
                var data = formParams(examineBuilding_.prototype.config().frm);
                var id = data.id;
                if (id == 0) {
                    return 0;
                }
                if (id == '') {
                    return 0;
                }
                if (id == null) {
                    return 0;
                }
                return id;
            },
            isEmpty: function (item) {
                if (item) {
                    return true;
                }
                return false;
            },
            objectWriteSelectData: function (frm, data, name) {
                if (examineBuilding_.prototype.isEmpty(data)) {
                    $("#" + frm + " ." + name).val(data).trigger("change");
                } else {
                    $("#" + frm + " ." + name).val(null).trigger("change");
                }
            },
            /**
             * @author:  zch
             * 描述:
             * @date: 页面 初始化
             **/
            viewInit: function () {
                if (examineBuilding_.prototype.getFlag()) {
                    examineBuilding_.prototype.init();
                    examineBuilding_.prototype.initSonMainOutfitSurface();
                    examineBuilding_.prototype.setFlag(false);
                }
                examineBuilding_.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
                examineBuilding_.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
                examineBuilding_.prototype.sonModelMethod.buildingSurface.loadDataDicList();
                examineBuilding_.prototype.sonModelMethod.buildingFunction.loadDataDicList();
            },
            /**
             * @author:  zch
             * 描述:配置文件
             * @date:
             **/
            config: function () {
                var data = {};
                data.table = "ExamineBuilding_List";
                data.box = "divBoxExamineBuilding_";
                data.frm = "frmExamineBuilding_";

                data.sonBox = "divBoxExamineBuildingOutfit";
                data.sonTable = "ExamineBuildingOutfitList";
                data.sonFrm = "frmExamineBuildingOutfit";

                data.examineBuildingSurfaceBox = "divBoxExamineBuildingSurface";
                data.examineBuildingSurfaceTable = "ExamineBuildingSurfaceList";
                data.examineBuildingSurfaceFrm = "ExamineBuildingSurfaceFrm";

                data.examineBuildingMaintenanceBox = "divBoxExamineBuildingMaintenance";
                data.examineBuildingMaintenanceTable = "ExamineBuildingMaintenanceList";
                data.examineBuildingMaintenanceFrm = "ExamineBuildingMaintenanceFrm";

                data.examineBuildingFunctionBox = "examineBuildingFunction";
                data.examineBuildingFunctionTable = "examineBuildingFunctionList";
                data.examineBuildingFunctionFrm = "examineBuildingFunctionFrm";

                data.type = "null";//
                data.database_Table = AssessDBKey.ExamineBuilding;//
                data.building_floor_plan = "building_floor_plan";//平面图id和字段 (楼栋) 根据 ExamineFileUpLoadFieldEnum 配置
                data.building_figure_outside = "building_figure_outside";//外装图id和字段
                data.building_floor_Appearance_figure = "building_floor_Appearance_figure";//外观图id和字段
                return data;
            },
            /**
             * @author:  zch
             * 描述:楼栋写数据
             * @date:
             **/
            dataNumberWrite: function (result) {
                var data = result.data;
                if (examineBuilding_.prototype.isEmpty(data)) {
                    $("#" + examineBuilding_.prototype.config().frm).initForm(data);
                    if (examineBuilding_.prototype.isEmpty(data.openTime)) {
                        $("#" + examineBuilding_.prototype.config().frm + " .openTime").val(formatDate(data.openTime));
                    }
                    if (examineBuilding_.prototype.isEmpty(data.roomTime)) {
                        $("#" + examineBuilding_.prototype.config().frm + " .roomTime").val(formatDate(data.roomTime));
                    }
                    var frm = examineBuilding_.prototype.config().frm;
                    examineBuilding_.prototype.objectWriteSelectData(frm, data.buildingCategory, "buildingCategory");
                    examineBuilding_.prototype.objectWriteSelectData(frm, data.buildingStructure, "buildingStructure");
                    examineBuilding_.prototype.objectWriteSelectData(frm, data.buildingStructurePid, "buildingStructurePid");
                    examineBuilding_.prototype.objectWriteSelectData(frm, data.propertyType, "propertyType");
                    examineBuilding_.prototype.objectWriteSelectData(frm, data.builderId, "builderId");
                    examineBuilding_.prototype.objectWriteSelectData(frm, data.propertyId, "propertyId");
                }
            },
            navButtonBuild: {
                //按钮被点击
                btnWrite: function (target, identifierNumber) {
                    if (examineBuilding_.prototype.getNavButtonBuildFlag()) {//校验机制被触发 (页面被编辑或者被复制)
                        if (!$("#" + examineBuilding_.prototype.config().frm).valid()) {
                            return false;
                        }
                        //处理需要保存临时数据
                        examineBuilding_.prototype.navButtonBuild.especiallyDataObj();
                    }
                    //-----------------||---------------
                    //清除数据
                    examineBuilding_.prototype.navButtonBuild.clearAll();
                    //改变按钮颜色
                    examineBuilding_.prototype.navButtonBuild.dataButtonWrite(target);
                    //赋值
                    examineBuilding_.prototype.navButtonBuild.initData(identifierNumber);
                },
                //复制数据
                copyData: function (target, identifierNumber) {
                    if (examineBuilding_.prototype.getNavButtonBuildFlag()) {//校验机制被触发 (页面被编辑或者被复制)
                        if (!$("#" + examineBuilding_.prototype.config().frm).valid()) {
                            return false;
                        }
                        //处理需要保存临时数据
                        examineBuilding_.prototype.navButtonBuild.especiallyDataObj();
                    }
                    var data = examineBuilding_.prototype.getObjArray(identifierNumber - 1);//获取上部分的数据
                    //对象copy (原因是对象之间直接赋值复制的是引用 修改一个对象会对另一个对象造成影响)
                    var newObj = {};
                    for (var attr in data) {
                        newObj[attr] = data[attr];
                    }
                    if ("buildingNumber" in newObj) {//判断期望对象是否存在 (newObj很可能存在但是期望值不存在就认为不存在 buildingNumber属于楼栋号)
                        //清除数据
                        examineBuilding_.prototype.navButtonBuild.clearAll();
                        var item = examineBuilding_.prototype.getObjArray(identifierNumber);//获取当前部分数据
                        if (examineBuilding_.prototype.isEmpty(item)) {
                            newObj.identifier = item.identifier;//把上部分复制来的数据的编号改为当前编号
                            newObj.attachmentId = null;//把上部分复制的附件id设为null
                        }
                        //处理复制的子类 (直接在服务端处理)
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuilding/copySonMainOutfitSurfaceFunction",
                            type: "post",
                            data: {
                                newBuildNumber: newObj.identifier,
                                oldBuildNumber:data.identifier,
                                declareId: $("#declareId").val(),
                                examineType: $("#examineType").val(),
                                planDetailsId: $("#planDetailsId").val()
                            },
                            dataType: "json",
                            success: function (result) {
                                if (result.ret) {
                                    console.log("子类复制成功!");
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                        //处理复制的附件信息
                        var fileId = data.attachmentId;
                        if (examineBuilding_.prototype.isEmpty(fileId)) {
                            //说明copy 的数据中曾经上传过附件
                            AssessCommon.getSysAttachmentDto(fileId, function (fileData) {
                                fileData.fieldsName = examineBuilding_.prototype.config().building_floor_plan + newObj.identifier;
                                fileData.id = null;
                                //copy file
                                AssessCommon.saveAndUpdateSysAttachmentDto(fileData, function (returnItem) {
                                    if (returnItem != null) {
                                        newObj.attachmentId = returnItem.id;
                                        console.log("test:");
                                        console.log(newObj);
                                        console.log(returnItem);
                                        examineBuilding_.prototype.setAttachmentId(null);
                                        //把合成好的数据 传入数组相应的位置
                                        examineBuilding_.prototype.setObjArrayElement(identifierNumber, newObj);
                                        //赋值
                                        examineBuilding_.prototype.navButtonBuild.initData(identifierNumber);
                                        //使触发机制生效!
                                        examineBuilding_.prototype.setNavButtonBuildFlag(true);
                                        //改变按钮颜色
                                        examineBuilding_.prototype.navButtonBuild.dataButtonWrite(target);
                                    }
                                })
                            });
                        } else {
                            //把合成好的数据 传入数组相应的位置
                            examineBuilding_.prototype.setObjArrayElement(identifierNumber, newObj);
                            //赋值
                            examineBuilding_.prototype.navButtonBuild.initData(identifierNumber);
                            //使触发机制生效!
                            examineBuilding_.prototype.setNavButtonBuildFlag(true);
                            //改变按钮颜色
                            examineBuilding_.prototype.navButtonBuild.dataButtonWrite(target);
                        }
                    } else {
                        toastr.success('没有能复制的部分!');
                        return false;
                    }
                },
                /**特别处理数据!**/
                especiallyDataObj: function () {
                    var number = "";
                    $.each($("#navButtonBuild button"), function (i, n) {
                        if ($(n).attr("class") == "btn btn-primary") {
                            number = i;
                        }
                    });
                    //保存临时数据
                    examineBuilding_.prototype.navButtonBuild.tempSaveData(number);
                    //使触发机制失效!
                    examineBuilding_.prototype.setNavButtonBuildFlag(false);
                },
                /**临时保存处理数据!**/
                tempSaveData: function (number) {
                    var data = formParams(examineBuilding_.prototype.config().frm);
                    if ($("#declareId").size() > 0) {
                        data.declareId = $("#declareId").val();
                    }
                    if ($("#examineType").size() > 0) {
                        data.examineType = $("#examineType").val();
                    }
                    if ($("#planDetailsId").size() > 0) {
                        data.planDetailsId = $("#planDetailsId").val();
                    }
                    var temp = examineBuilding_.prototype.navButtonBuild.especiallyNumber(number);
                    var item = examineBuilding_.prototype.getObjArray(temp);
                    data.identifier = item.identifier;
                    console.log("temp:" + temp + " number:" + number);
                    var fileId = examineBuilding_.prototype.getAttachmentId();
                    if (examineBuilding_.prototype.isEmpty(fileId)) {
                        data.attachmentId = fileId;
                        AssessCommon.getSysAttachmentDto(fileId, function (fileData) {
                            //这里需要注意:假如数据里面已经存储了附件id 那么不再更新
                            fileData.fieldsName = examineBuilding_.prototype.config().building_floor_plan + data.identifier;
                            AssessCommon.saveAndUpdateSysAttachmentDto(fileData, function (item) {
                                if (item != null) {
                                    toastr.success('附件更新成功!');
                                    examineBuilding_.prototype.setAttachmentId(null);
                                }
                            })
                        });
                    }
                    examineBuilding_.prototype.setObjArrayElement(temp, data);
                    //更新子类
                    $.ajax({
                        url: "${pageContext.request.contextPath}/examineBuilding/updateSonMainOutfitSurface",
                        type: "post",
                        data: {buildNumber: data.identifier},
                        dataType: "json",
                        success: function (result) {
                            if (result.ret) {
                                toastr.success('子类更新成功!');
                            }
                        },
                        error: function (result) {
                            Alert("调用服务端方法失败，失败原因:" + result);
                        }
                    });
                },
                //清除数据
                clearAll: function () {
                    var frm = examineBuilding_.prototype.config().frm;
                    $("#" + frm).clearAll();
                    examineBuilding_.prototype.objectWriteSelectData(frm, null, "buildingCategory");
                    examineBuilding_.prototype.objectWriteSelectData(frm, null, "buildingStructure");
                    examineBuilding_.prototype.objectWriteSelectData(frm, null, "buildingstructurePid");
                    examineBuilding_.prototype.objectWriteSelectData(frm, null, "propertyType");
                    examineBuilding_.prototype.objectWriteSelectData(frm, null, "builderId");
                    examineBuilding_.prototype.objectWriteSelectData(frm, null, "propertyId");
                },
                //赋值
                initData: function (identifierNumber) {
                    $("." + examineBuilding_.prototype.config().sonTable).html(identifierNumber + "部分");
                    $("." + examineBuilding_.prototype.config().examineBuildingSurfaceTable).html(identifierNumber + "部分");
                    $("." + examineBuilding_.prototype.config().examineBuildingMaintenanceTable).html(identifierNumber + "部分");
                    $("." + examineBuilding_.prototype.config().examineBuildingFunctionTable).html(identifierNumber + "部分");
                    var data = examineBuilding_.prototype.getObjArray(identifierNumber);
                    if (examineBuilding_.prototype.isEmpty(data)) {
                        //显示基本数据!
                        examineBuilding_.prototype.dataNumberWrite({data: data});
                        //显示附件
                        var target = examineBuilding_.prototype.config().building_floor_plan + data.identifier;
                        examineBuilding_.prototype.file.newFileShows(examineBuilding_.prototype.config().building_floor_plan, target);
                    }
                    examineBuilding_.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
                    examineBuilding_.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
                    examineBuilding_.prototype.sonModelMethod.buildingFunction.loadDataDicList();
                    examineBuilding_.prototype.sonModelMethod.buildingSurface.loadDataDicList();
                    console.log(objArray);
                },
                //编号 规则
                rule: function (identifierNumber) {
                    var date = new Date();
                    //利用了时间轴 和 随机数 的积 来生成 唯一编号
                    var str = Date.now().toString();
                    str = str.substring(str.length - 6, str.length);
                    var identifier = "";
                    identifier = examineBuilding_.prototype.navButtonBuild.randomNum(100, 90000);
                    identifier = parseInt(identifier) * parseInt(str);
                    identifier += ":" + identifierNumber;
                    return identifier;
                },
                //改变按钮颜色
                dataButtonWrite: function (target) {
                    $.each($("#navButtonBuild button"), function (i, n) {
                        $(n).removeClass();
                        $(n).addClass("btn btn-default");
                    });
                    //改变按钮颜色
                    $(target).removeClass();
                    $(target).addClass("btn btn-primary");
                },
                /*编号 特别处理(处理的是button所以从0开始)*/
                especiallyNumber: function (number) {
                    //0-2-4-6  1-3-5  (1,2是第二部分) (3,4是第三部分)  (5,6是第四部分)
                    if (examineBuilding_.prototype.isEmpty(number)) {
                        if (number == 1) {
                            return 2;
                        }
                        if (number == 2) {
                            return 2;
                        }
                        if (number == 3) {
                            return 3;
                        }
                        if (number == 4) {
                            return 3;
                        }
                        if (number == 5) {
                            return 4;
                        }
                        if (number == 6) {
                            return 4;
                        }
                    }
                    //由于js中0 也属于false,所以再次判断
                    var regPos = /0/gi; //0 判断
                    if (regPos.test(number)) {
                        return 1;
                    }
                    return 1;
                },
                //生成从minNum到maxNum的随机数 (请尽量设置大一些 以免重复)
                randomNum: function (minNum, maxNum) {
                    switch (arguments.length) {
                        case 1:
                            return parseInt(Math.random() * minNum + 1, 10);
                            break;
                        case 2:
                            return parseInt(Math.random() * (maxNum - minNum + 1) + minNum, 10);
                            break;
                        default:
                            return 0;
                            break;
                    }
                }
            },
            loadDataDicList: function () {

            },
            removeData: function (id) {

            },
            showModel: function () {

            },
            saveData: function () {

            },
            getAndInit: function (id) {

            },
            initSonMainOutfitSurface: function () {
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuilding/initSonMainOutfitSurface",
                    type: "post",
                    dataType: "json",
                    success: function (result) {
                        if (result.ret) {
                            toastr.success('初始化成功');
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
            },
            init: function () {
                AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_category, null, function (html, data) {
                    $("#" + examineBuilding_.prototype.config().frm ).find("select.buildingCategory").html(html);
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_structure, null, function (html, data) {
                    $("#" + examineBuilding_.prototype.config().frm ).find("select.buildingStructure").html(html);
                });
                $("#" + examineBuilding_.prototype.config().frm + "buildingStructure").select2({minimumResultsForSearch: -1});//加载样式
                $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").change(function () {
                    /**
                     * 这 因为select2 自动创建 属性名相同的两个class 所以需要要手动取值
                     **/
                    var id = $("#" + examineBuilding_.prototype.config().frm + " .buildingStructure").eq(1).val();
                    if (id != null && id != '' && id != 0) {
                        AssessCommon.loadDataDicByPid(id, null, function (html, data) {
                            $("#" + examineBuilding_.prototype.config().frm + "buildingStructure").html(html);
                        });
                    }
                });
                AssessCommon.loadDataDicByKey(AssessDicKey.examine_building_property_type, null, function (html, data) {
                    $("#" + examineBuilding_.prototype.config().frm ).find("select.propertyType").html(html);
                });
                $.ajax({
                    url: "${pageContext.request.contextPath}/examineBuilding/getBuildAndProperty",
                    type: "get",
                    dataType: "json",
                    data: {type: "type"},
                    success: function (result) {
                        if (result.ret) {
                            var item = result.data;
                            var option = "";
                            if (item.DataBuilder.length > 0) {
                                option = "<option value=''>请选择</option>";
                                var data = item.DataBuilder;
                                for (var i = 0; i < item.DataBuilder.length; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#" + examineBuilding_.prototype.config().frm ).find("select.builderId").html(html);
                                option = "";
                            }
                            if (item.DataProperty.length > 0) {
                                option = "<option value=''>请选择</option>";
                                var data = item.DataProperty;
                                for (var i = 0; i < item.DataProperty.length; i++) {
                                    option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                }
                                $("#" + examineBuilding_.prototype.config().frm ).find("select.propertyId").html(html);
                            }
                        }
                    },
                    error: function (result) {
                        Alert("调用服务端方法失败，失败原因:" + result);
                    }
                });
                //自动添加编号
                for (var i = 1; i <= 4; i++) {
                    objArray[i] = {identifier: examineBuilding_.prototype.navButtonBuild.rule(i)};
                }
                console.log(objArray);
                //附件
                examineBuilding_.prototype.file.init();
                //对所有的input添加事件
                var input = $("#"+examineBuilding_.prototype.config().frm+" :input");
                $.each(input,function (i,n) {
                    $(n).blur(function () {
                        examineBuilding_.prototype.setNavButtonBuildFlag(true);
                    });
                });
            },
            sonModelMethod: {
                /**楼栋外装情况**/
                buildingOutfit: {
                    getAndInit: function (id) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingOutfit/getExamineBuildingOutfitById",
                            type: "get",
                            dataType: "json",
                            data: {id: id},
                            success: function (result) {
                                if (result.ret) {
                                    if (examineBuilding_.prototype.getSonFlag()) {
                                        examineBuilding_.prototype.sonModelMethod.buildingOutfit.init();
                                        examineBuilding_.prototype.setSonFlag(false);
                                    }
                                    $("#" + examineBuilding_.prototype.config().sonFrm).clearAll();
                                    var data = result.data;
                                    if (examineBuilding_.prototype.isEmpty(data)) {
                                        $("#" + examineBuilding_.prototype.config().sonFrm).initForm(data);
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().sonFrm,
                                            data.decoratingMaterial, "decoratingMaterial");
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().sonFrm,
                                            data.materialPrice, "materialPrice");
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().sonFrm,
                                            data.constructionTechnology, "constructionTechnology");
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().sonFrm,
                                            data.decorationPart, "decorationPart");
                                    }
                                    $('#' + examineBuilding_.prototype.config().sonBox).modal("show");
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        })
                    },
                    saveData: function () {
                        if (!$("#" + examineBuilding_.prototype.config().sonFrm).valid()) {
                            return false;
                        }
                        var data = formParams(examineBuilding_.prototype.config().sonFrm);
                        // data.buildingId = examineBuilding_.prototype.getBuildId();
                        data.buildNumber = examineBuilding_.prototype.getIdentifier();
                        if ($("#declareId").size() > 0) {
                            data.declareId = $("#declareId").val();
                        }
                        if ($("#examineType").size() > 0) {
                            data.examineType = $("#examineType").val();
                        }
                        if ($("#planDetailsId").size() > 0) {
                            data.planDetailsId = $("#planDetailsId").val();
                        }
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingOutfit/saveAndUpdateExamineBuildingOutfit",
                            type: "post",
                            dataType: "json",
                            data: data,
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('保存成功');
                                    $('#' + examineBuilding_.prototype.config().sonBox).modal('hide');
                                    examineBuilding_.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
                                }
                                else {
                                    Alert("保存数据失败，失败原因:" + result.errmsg);
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        })
                    },
                    showModel: function () {
                        $("#" + examineBuilding_.prototype.config().sonFrm).clearAll();
                        if (examineBuilding_.prototype.getSonFlag()) {
                            examineBuilding_.prototype.sonModelMethod.buildingOutfit.init();
                            examineBuilding_.prototype.setSonFlag(false);
                        }
                        $('#' + examineBuilding_.prototype.config().sonBox).modal("show");
                    },
                    removeData: function (id) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingOutfit/deleteExamineBuildingOutfitById",
                            type: "post",
                            dataType: "json",
                            data: {id: id},
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('删除成功');
                                    examineBuilding_.prototype.sonModelMethod.buildingOutfit.loadDataDicList();
                                }
                                else {
                                    Alert("保存数据失败，失败原因:" + result.errmsg);
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        })
                    },
                    init: function () {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_decorating_material",
                            type: "get",
                            dataType: "json",
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    var gradeNum = data.length;
                                    var option = "<option value=''>请选择</option>";
                                    if (gradeNum > 0) {
                                        for (var i = 0; i < gradeNum; i++) {
                                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                        }
                                        $("#" + examineBuilding_.prototype.config().sonFrm ).find("select.decoratingMaterial").html(option);
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_material_price",
                            type: "get",
                            dataType: "json",
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    var gradeNum = data.length;
                                    var option = "<option value=''>请选择</option>";
                                    if (gradeNum > 0) {
                                        for (var i = 0; i < gradeNum; i++) {
                                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                        }
                                        $("#" + examineBuilding_.prototype.config().sonFrm ).find("select.materialPrice").html(option);
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_construction_technology",
                            type: "get",
                            dataType: "json",
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    var gradeNum = data.length;
                                    var option = "<option value=''>请选择</option>";
                                    if (gradeNum > 0) {
                                        for (var i = 0; i < gradeNum; i++) {
                                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                        }
                                        $("#" + examineBuilding_.prototype.config().sonFrm ).find("select.constructionTechnology").html(option);
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_decoration_part",
                            type: "get",
                            dataType: "json",
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    var gradeNum = data.length;
                                    var option = "<option value=''>请选择</option>";
                                    if (gradeNum > 0) {
                                        for (var i = 0; i < gradeNum; i++) {
                                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                        }
                                        $("#" + examineBuilding_.prototype.config().sonFrm ).find("select.decorationPart").html(option);
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        })
                    },
                    loadDataDicList: function () {
                        var cols = [];
                        cols.push({field: 'decorationPartName', title: '装修部位'});
                        cols.push({field: 'decoratingMaterialName', title: '装修材料'});
                        cols.push({field: 'materialPriceName', title: '材料价格区间'});
                        cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
                        cols.push({
                            field: 'id', title: '操作', formatter: function (value, row, index) {
                                var str = '<div class="btn-margin">';
                                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="examineBuilding_.prototype.sonModelMethod.buildingOutfit.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="examineBuilding_.prototype.sonModelMethod.buildingOutfit.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                                str += '</div>';
                                return str;
                            }
                        });
                        $("#" + examineBuilding_.prototype.config().sonTable).bootstrapTable('destroy');
                        TableInit(examineBuilding_.prototype.config().sonTable, "${pageContext.request.contextPath}/examineBuildingOutfit/getExamineBuildingOutfitList", cols, {
                            declareId: $("#declareId").val(),
                            examineType: $("#examineType").val(),
                            planDetailsId: $("#planDetailsId").val(),
                            buildNumber: examineBuilding_.prototype.getIdentifier()
                        }, {
                            showColumns: false,
                            showRefresh: false,
                            search: false,
                            onLoadSuccess: function () {
                                $('.tooltips').tooltip();
                            }
                        });
                    }

                },
                /**屋面结构**/
                buildingSurface: {
                    getAndInit: function (id) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingSurface/getExamineBuildingSurfaceById",
                            type: "get",
                            dataType: "json",
                            data: {id: id},
                            success: function (result) {
                                if (result.ret) {
                                    if (examineBuilding_.prototype.getExamineBuildingSurfaceFlag()) {
                                        examineBuilding_.prototype.sonModelMethod.buildingSurface.init();
                                        examineBuilding_.prototype.setExamineBuildingSurfaceFlag(false);
                                    }
                                    $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm).clearAll();
                                    var data = result.data;
                                    if (examineBuilding_.prototype.isEmpty(data)) {
                                        $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm).initForm(data);
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().examineBuildingSurfaceFrm,
                                            data.structure, "structure");
                                    }
                                    $('#' + examineBuilding_.prototype.config().examineBuildingSurfaceBox).modal("show");
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        })
                    },
                    saveData: function () {
                        if (!$("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm).valid()) {
                            return false;
                        }
                        var data = formParams(examineBuilding_.prototype.config().examineBuildingSurfaceFrm);
                        // data.buildingId = examineBuilding_.prototype.getBuildId();
                        data.buildNumber = examineBuilding_.prototype.getIdentifier();
                        if ($("#declareId").size() > 0) {
                            data.declareId = $("#declareId").val();
                        }
                        if ($("#examineType").size() > 0) {
                            data.examineType = $("#examineType").val();
                        }
                        if ($("#planDetailsId").size() > 0) {
                            data.planDetailsId = $("#planDetailsId").val();
                        }
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingSurface/saveAndUpdateExamineBuildingSurface",
                            type: "post",
                            dataType: "json",
                            data: data,
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('保存成功');
                                    $('#' + examineBuilding_.prototype.config().examineBuildingSurfaceBox).modal('hide');
                                    examineBuilding_.prototype.sonModelMethod.buildingSurface.loadDataDicList();
                                }
                                else {
                                    Alert("保存数据失败，失败原因:" + result.errmsg);
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        })
                    },
                    showModel: function () {
                        $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm).clearAll();
                        if (examineBuilding_.prototype.getExamineBuildingSurfaceFlag()) {
                            examineBuilding_.prototype.sonModelMethod.buildingSurface.init();
                            examineBuilding_.prototype.setExamineBuildingSurfaceFlag(false);
                        }
                        $('#' + examineBuilding_.prototype.config().examineBuildingSurfaceBox).modal("show");
                    },
                    removeData: function (id) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingSurface/deleteExamineBuildingSurfaceById",
                            type: "post",
                            dataType: "json",
                            data: {id: id},
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('删除成功');
                                    examineBuilding_.prototype.sonModelMethod.buildingSurface.loadDataDicList();
                                }
                                else {
                                    Alert("保存数据失败，失败原因:" + result.errmsg);
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    },
                    init: function () {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingSurface/examine_building_structure",
                            type: "get",
                            async: false,
                            dataType: "json",
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    var gradeNum = data.length;
                                    var option = "<option value=''>请选择</option>";
                                    if (gradeNum > 0) {
                                        for (var i = 0; i < gradeNum; i++) {
                                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                        }
                                        $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceFrm ).find("select.structure").html(option);
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    },
                    loadDataDicList: function () {
                        var cols = [];
                        cols.push({field: 'structureName', title: '屋面结构'});
                        cols.push({field: 'description', title: '描述'});
                        cols.push({
                            field: 'id', title: '操作', formatter: function (value, row, index) {
                                var str = '<div class="btn-margin">';
                                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="examineBuilding_.prototype.sonModelMethod.buildingSurface.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="examineBuilding_.prototype.sonModelMethod.buildingSurface.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                                str += '</div>';
                                return str;
                            }
                        });
                        $("#" + examineBuilding_.prototype.config().examineBuildingSurfaceTable).bootstrapTable('destroy');
                        TableInit(examineBuilding_.prototype.config().examineBuildingSurfaceTable, "${pageContext.request.contextPath}/examineBuildingSurface/getExamineBuildingSurfaceList", cols, {
                            declareId: $("#declareId").val(),
                            examineType: $("#examineType").val(),
                            planDetailsId: $("#planDetailsId").val(),
                            buildNumber: examineBuilding_.prototype.getIdentifier()
                        }, {
                            showColumns: false,
                            showRefresh: false,
                            search: false,
                            onLoadSuccess: function () {
                                $('.tooltips').tooltip();
                            }
                        });
                    }
                },
                /**维护结构**/
                buildingMaintenance: {
                    getAndInit: function (id) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingMaintenance/getExamineBuildingMaintenanceById",
                            type: "get",
                            dataType: "json",
                            data: {id: id},
                            success: function (result) {
                                if (result.ret) {
                                    if (examineBuilding_.prototype.getExamineBuildingMaintenanceFlag()) {
                                        examineBuilding_.prototype.sonModelMethod.buildingMaintenance.init();
                                        examineBuilding_.prototype.setExamineBuildingMaintenanceFlag(false);
                                    }
                                    $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm).clearAll();
                                    var data = result.data;
                                    if (examineBuilding_.prototype.isEmpty(data)) {
                                        $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm).initForm(data);
                                        examineBuilding_.prototype.objectWriteSelectData(
                                            examineBuilding_.prototype.config().examineBuildingMaintenanceFrm, data.category, "category");
                                        examineBuilding_.prototype.objectWriteSelectData(
                                            examineBuilding_.prototype.config().examineBuildingMaintenanceFrm, data.materialQuality, "materialQuality");
                                    }
                                    $('#' + examineBuilding_.prototype.config().examineBuildingMaintenanceBox).modal("show");
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        })
                    },
                    saveData: function () {
                        if (!$("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm).valid()) {
                            return false;
                        }
                        var data = formParams(examineBuilding_.prototype.config().examineBuildingMaintenanceFrm);
                        // data.buildingId = examineBuilding_.prototype.getBuildId();
                        data.buildNumber = examineBuilding_.prototype.getIdentifier();
                        if ($("#declareId").size() > 0) {
                            data.declareId = $("#declareId").val();
                        }
                        if ($("#examineType").size() > 0) {
                            data.examineType = $("#examineType").val();
                        }
                        if ($("#planDetailsId").size() > 0) {
                            data.planDetailsId = $("#planDetailsId").val();
                        }
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingMaintenance/saveAndUpdateExamineBuildingMaintenance",
                            type: "post",
                            dataType: "json",
                            data: data,
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('保存成功');
                                    $('#' + examineBuilding_.prototype.config().examineBuildingMaintenanceBox).modal('hide');
                                    examineBuilding_.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
                                }
                                else {
                                    Alert("保存数据失败，失败原因:" + result.errmsg);
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        })
                    },
                    showModel: function () {
                        $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm).clearAll();
                        if (examineBuilding_.prototype.getExamineBuildingMaintenanceFlag()) {
                            examineBuilding_.prototype.sonModelMethod.buildingMaintenance.init();
                            examineBuilding_.prototype.setExamineBuildingMaintenanceFlag(false);
                        }
                        $('#' + examineBuilding_.prototype.config().examineBuildingMaintenanceBox).modal("show");
                    },
                    removeData: function (id) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingMaintenance/deleteExamineBuildingMaintenanceById",
                            type: "post",
                            dataType: "json",
                            data: {id: id},
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('删除成功');
                                    examineBuilding_.prototype.sonModelMethod.buildingMaintenance.loadDataDicList();
                                }
                                else {
                                    Alert("保存数据失败，失败原因:" + result.errmsg);
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    },
                    init: function () {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingMaintenance/examine_building_maintenance_category",
                            type: "get",
                            dataType: "json",
                            async: false,
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    var gradeNum = data.length;
                                    var option = "<option value=''>请选择</option>";
                                    if (gradeNum > 0) {
                                        for (var i = 0; i < gradeNum; i++) {
                                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                        }
                                        $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm ).find("select.category").html(option);
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingMaintenance/examine_building_materialquality",
                            type: "get",
                            async: false,
                            dataType: "json",
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    var gradeNum = data.length;
                                    var option = "<option value=''>请选择</option>";
                                    if (gradeNum > 0) {
                                        for (var i = 0; i < gradeNum; i++) {
                                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                        }
                                        $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceFrm ).find("select.materialQuality").html(option);
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    },
                    loadDataDicList: function () {
                        var cols = [];
                        cols.push({field: 'categoryName', title: '类别'});
                        cols.push({field: 'materialQualityName', title: '材质'});
                        cols.push({field: 'name', title: '名称'});
                        cols.push({
                            field: 'id', title: '操作', formatter: function (value, row, index) {
                                var str = '<div class="btn-margin">';
                                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="examineBuilding_.prototype.sonModelMethod.buildingMaintenance.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="examineBuilding_.prototype.sonModelMethod.buildingMaintenance.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                                str += '</div>';
                                return str;
                            }
                        });
                        $("#" + examineBuilding_.prototype.config().examineBuildingMaintenanceTable).bootstrapTable('destroy');
                        TableInit(examineBuilding_.prototype.config().examineBuildingMaintenanceTable, "${pageContext.request.contextPath}/examineBuildingMaintenance/getExamineBuildingMaintenanceList", cols, {
                            declareId: $("#declareId").val(),
                            examineType: $("#examineType").val(),
                            planDetailsId: $("#planDetailsId").val(),
                            buildNumber: examineBuilding_.prototype.getIdentifier()
                        }, {
                            showColumns: false,
                            showRefresh: false,
                            search: false,
                            onLoadSuccess: function () {
                                $('.tooltips').tooltip();
                            }
                        });
                    }
                },
                /**楼栋建筑功能**/
                buildingFunction: {
                    getAndInit: function (id) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingFunction/getExamineBuildingFunctionById",
                            type: "get",
                            dataType: "json",
                            data: {id: id},
                            success: function (result) {
                                if (result.ret) {
                                    if (examineBuilding_.prototype.getExamineBuildingFunctionFlag()) {
                                        examineBuilding_.prototype.sonModelMethod.buildingFunction.init();
                                        examineBuilding_.prototype.setExamineBuildingFunctionFlag(false);
                                    }
                                    $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm).clearAll();
                                    var data = result.data;
                                    if (examineBuilding_.prototype.isEmpty(data)) {
                                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm).initForm(data);
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().examineBuildingFunctionFrm,
                                            data.decoratingMaterial, "decoratingMaterial");
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().examineBuildingFunctionFrm,
                                            data.materialPrice, "materialPrice");
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().examineBuildingFunctionFrm,
                                            data.constructionTechnology, "constructionTechnology");
                                        examineBuilding_.prototype.objectWriteSelectData(examineBuilding_.prototype.config().examineBuildingFunctionFrm,
                                            data.decorationPart, "decorationPart");
                                    }
                                    $('#' + examineBuilding_.prototype.config().examineBuildingFunctionBox).modal("show");
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        })
                    },
                    saveData: function () {
                        if (!$("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm).valid()) {
                            return false;
                        }
                        var data = formParams(examineBuilding_.prototype.config().examineBuildingFunctionFrm);
                        // data.buildingId = examineBuilding_.prototype.getBuildId();
                        data.buildNumber = examineBuilding_.prototype.getIdentifier();
                        if ($("#declareId").size() > 0) {
                            data.declareId = $("#declareId").val();
                        }
                        if ($("#examineType").size() > 0) {
                            data.examineType = $("#examineType").val();
                        }
                        if ($("#planDetailsId").size() > 0) {
                            data.planDetailsId = $("#planDetailsId").val();
                        }
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingFunction/saveAndUpdateExamineBuildingFunction",
                            type: "post",
                            dataType: "json",
                            data: data,
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('保存成功');
                                    $('#' + examineBuilding_.prototype.config().examineBuildingFunctionBox).modal('hide');
                                    examineBuilding_.prototype.sonModelMethod.buildingFunction.loadDataDicList();
                                }
                                else {
                                    Alert("保存数据失败，失败原因:" + result.errmsg);
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        })
                    },
                    showModel: function () {
                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm).clearAll();
                        if (examineBuilding_.prototype.getExamineBuildingFunctionFlag()) {
                            examineBuilding_.prototype.sonModelMethod.buildingFunction.init();
                            examineBuilding_.prototype.setExamineBuildingFunctionFlag(false);
                        }
                        $('#' + examineBuilding_.prototype.config().examineBuildingFunctionBox).modal("show");
                    },
                    removeData: function (id) {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingFunction/deleteExamineBuildingFunctionById",
                            type: "post",
                            dataType: "json",
                            data: {id: id},
                            success: function (result) {
                                if (result.ret) {
                                    toastr.success('删除成功');
                                    examineBuilding_.prototype.sonModelMethod.buildingFunction.loadDataDicList();
                                }
                                else {
                                    Alert("保存数据失败，失败原因:" + result.errmsg);
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                    },
                    init: function () {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_decorating_material",
                            type: "get",
                            dataType: "json",
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    var gradeNum = data.length;
                                    var option = "<option value=''>请选择</option>";
                                    if (gradeNum > 0) {
                                        for (var i = 0; i < gradeNum; i++) {
                                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                        }
                                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm ).find("select.decoratingMaterial").html(option);
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_material_price",
                            type: "get",
                            dataType: "json",
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    var gradeNum = data.length;
                                    var option = "<option value=''>请选择</option>";
                                    if (gradeNum > 0) {
                                        for (var i = 0; i < gradeNum; i++) {
                                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                        }
                                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm ).find("select.materialPrice").html(option);
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_construction_technology",
                            type: "get",
                            dataType: "json",
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    var gradeNum = data.length;
                                    var option = "<option value=''>请选择</option>";
                                    if (gradeNum > 0) {
                                        for (var i = 0; i < gradeNum; i++) {
                                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                        }
                                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm ).find("select.constructionTechnology").html(option);
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        });
                        $.ajax({
                            url: "${pageContext.request.contextPath}/examineBuildingOutfit/examine_building_decoration_part",
                            type: "get",
                            dataType: "json",
                            success: function (result) {
                                if (result.ret) {
                                    var data = result.data;
                                    var gradeNum = data.length;
                                    var option = "<option value=''>请选择</option>";
                                    if (gradeNum > 0) {
                                        for (var i = 0; i < gradeNum; i++) {
                                            option += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
                                        }
                                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionFrm ).find("select.decorationPart").html(option);
                                    }
                                }
                            },
                            error: function (result) {
                                Alert("调用服务端方法失败，失败原因:" + result);
                            }
                        })
                    },
                    loadDataDicList: function () {
                        var cols = [];
                        cols.push({field: 'waterProof', title: '防水'});
                        cols.push({field: 'heatPreservation', title: '保温'});
                        cols.push({field: 'heatInsulation', title: '隔热'});
                        cols.push({field: 'decorationPartName', title: '装修部位'});
                        cols.push({field: 'decoratingMaterialName', title: '装修材料'});
                        cols.push({field: 'materialPriceName', title: '材料价格区间'});
                        cols.push({field: 'constructionTechnologyName', title: '施工工艺'});
                        cols.push({
                            field: 'id', title: '操作', formatter: function (value, row, index) {
                                var str = '<div class="btn-margin">';
                                str += '<a class="btn btn-xs btn-success tooltips"  data-placement="top" data-original-title="编辑" onclick="examineBuilding_.prototype.sonModelMethod.buildingFunction.getAndInit(' + row.id + ',\'tb_List\')"><i class="fa fa-edit fa-white"></i></a>';
                                str += '<a class="btn btn-xs btn-warning tooltips" data-placement="top" data-original-title="删除" onclick="examineBuilding_.prototype.sonModelMethod.buildingFunction.removeData(' + row.id + ',\'tb_List\')"><i class="fa fa-minus fa-white"></i></a>';
                                str += '</div>';
                                return str;
                            }
                        });
                        $("#" + examineBuilding_.prototype.config().examineBuildingFunctionTable).bootstrapTable('destroy');
                        TableInit(examineBuilding_.prototype.config().examineBuildingFunctionTable, "${pageContext.request.contextPath}/examineBuildingFunction/getExamineBuildingFunctionList", cols, {
                            declareId: $("#declareId").val(),
                            examineType: $("#examineType").val(),
                            planDetailsId: $("#planDetailsId").val(),
                            buildNumber: examineBuilding_.prototype.getIdentifier()
                        }, {
                            showColumns: false,
                            showRefresh: false,
                            search: false,
                            onLoadSuccess: function () {
                                $('.tooltips').tooltip();
                            }
                        });
                    }
                }
            },
            file: {
                //附件初始化方法
                init: function () {
                    examineBuilding_.prototype.file.uploadFilesModel(examineBuilding_.prototype.config().building_floor_plan);
                },
                //默认上传附件方法
                uploadFilesModel: function (target) {
                    FileUtils.uploadFiles({
                        target: target,
                        disabledTarget: "btn_submit",
                        onUpload: function () {
                            var formData = {
                                fieldsName: target,
                                tableName: examineBuilding_.prototype.config().database_Table,
                                tableId: examineBuilding_.prototype.getBuildId(),
                                projectId: 0,
                                creater: "${currUserAccount}"
                            };
                            return formData;
                        },
                        onUploadComplete: function (result, file) {
                            //附件id
                            if (target == examineBuilding_.prototype.config().building_floor_plan){
                                examineBuilding_.prototype.setAttachmentId(result);
                            }
                            examineBuilding_.prototype.file.getFileShowsModel(target);
                        },
                        deleteFlag: true
                    });
                },
                //默认显示附件方法
                getFileShowsModel: function (target) {
                    FileUtils.getFileShows({
                        target: target,
                        formData: {
                            fieldsName: target,
                            tableName: examineBuilding_.prototype.config().database_Table,
                            tableId: examineBuilding_.prototype.getBuildId(),
                            projectId: 0,
                            creater: "${currUserAccount}"
                        },
                        deleteFlag: true
                    });
                },
                //根据字段来显示附件
                newFileShows: function (target, fieldsName) {
                    FileUtils.getFileShows({
                        target: target,
                        formData: {
                            fieldsName: fieldsName,
                            tableName: examineBuilding_.prototype.config().database_Table,
                            tableId: examineBuilding_.prototype.getBuildId(),
                            projectId: 0,
                            creater: "${currUserAccount}"
                        },
                        deleteFlag: true
                    });
                }
            }
        }
    })();

</script>

<div id="divBoxExamineBuildingOutfit" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">楼栋外装情况</h3>
            </div>
            <form id="frmExamineBuildingOutfit" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修部位
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decorationPart"
                                                    class="form-control search-select select2 decorationPart">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            施工工艺
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="constructionTechnology"
                                                    class="form-control search-select select2 constructionTechnology">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            材料价格区间
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="materialPrice"
                                                    class="form-control search-select select2 materialPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修材料
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decoratingMaterial"
                                                    class="form-control search-select select2 decoratingMaterial">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="examineBuilding_.prototype.sonModelMethod.buildingOutfit.saveData();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxExamineBuildingMaintenance" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">维护结构</h3>
            </div>
            <form id="ExamineBuildingMaintenanceFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            名称
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="名称"
                                                   name="name" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            分类
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="category"
                                                    class="form-control search-select select2 category">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            材质
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="materialQuality"
                                                    class="form-control search-select select2 materialQuality">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="examineBuilding_.prototype.sonModelMethod.buildingMaintenance.saveData();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="divBoxExamineBuildingSurface" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">屋面结构</h3>
            </div>
            <form id="ExamineBuildingSurfaceFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            屋面结构
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="structure"
                                                    class="form-control search-select select2 structure">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            描述
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="描述"
                                                   name="description" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="examineBuilding_.prototype.sonModelMethod.buildingSurface.saveData();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="examineBuildingFunction" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1"
     role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">建筑功能</h3>
            </div>
            <form id="examineBuildingFunctionFrm" class="form-horizontal">
                <input type="hidden" name="id">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel-body">
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            保温
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="保温"
                                                   name="heatPreservation" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            防水
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="防水"
                                                   name="waterProof" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            隔热
                                        </label>
                                        <div class="col-sm-10">
                                            <input type="text" placeholder="隔热"
                                                   name="heatInsulation" class="form-control" required="required">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修部位
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decorationPart"
                                                    class="form-control search-select select2 decorationPart">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            施工工艺
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="constructionTechnology"
                                                    class="form-control search-select select2 constructionTechnology">
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            材料价格区间
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="materialPrice"
                                                    class="form-control search-select select2 materialPrice">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="x-valid">
                                        <label class="col-sm-2 control-label">
                                            装修材料
                                        </label>
                                        <div class="col-sm-10">
                                            <select required="required" name="decoratingMaterial"
                                                    class="form-control search-select select2 decoratingMaterial">
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" data-dismiss="modal" class="btn btn-default">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            onclick="examineBuilding_.prototype.sonModelMethod.buildingFunction.saveData();">
                        保存
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</html>

