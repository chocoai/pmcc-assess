<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/views/share/chks_common.jsp" %>

<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    质量考核
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-up"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body" style="display: none">
            <form id="assessmentPerformanceForm" class="form-horizontal">
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                被考核人
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <div class="input-group">
                                    <input type="hidden" name="byExaminePeople">
                                    <input type="text" class="form-control" readonly="readonly" name="byExaminePeopleName"
                                           onclick="assessmentCommonHandle.selectUserAccountMember(this);">
                                    <div class="input-group-prepend">
                                        <button class="btn btn-warning btn-sm "
                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                type="button" onclick="$(this).closest('.input-group').find('input').val('');">
                                            清空
                                        </button>
                                    </div>
                                    <div class="input-group-prepend">
                                        <button class="btn btn-primary btn-sm "
                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                type="button" onclick="assessmentCommonHandle.selectUserAccountMember(this);">选择
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                考核人
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <div class="input-group">
                                    <input type="hidden" name="examinePeople">
                                    <input type="text" class="form-control" readonly="readonly" name="examinePeopleName"
                                           onclick="assessmentCommonHandle.selectUserAccountMember(this);">
                                    <div class="input-group-prepend">
                                        <button class="btn btn-warning btn-sm "
                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                type="button" onclick="$(this).closest('.input-group').find('input').val('');">
                                            清空
                                        </button>
                                    </div>
                                    <div class="input-group-prepend">
                                        <button class="btn btn-primary btn-sm "
                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                type="button" onclick="assessmentCommonHandle.selectUserAccountMember(this);">选择
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                状态
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <select class="form-control input-full" name="examineStatus">
                                    <option value="">-请选择-</option>
                                    <option value="runing">进行中</option>
                                    <option value="finish">完成</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                        <div class="form-inline x-valid">
                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 col-form-label">
                                名称
                            </label>
                            <div class="col-xs-3  col-sm-3  col-md-3  col-lg-3">
                                <input type="text" placeholder="名称" name="businessKey" class="form-control input-full">
                            </div>
                            <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                <button type="button"
                                        onclick="assessmentCommonHandle.loadAssessmentQualityList($(this).closest('form'));"
                                        class="btn btn-info btn-sm"><i class="fa fa-search"></i>查询</button>
                                <button style="margin-left: 5px" class="btn btn-info  btn-sm" type="button" onclick="$(this).closest('form').clearAll();">
                                    <span class="fa fa-undo-alt" aria-hidden="true"></span>
                                    重置
                                </button>
                                <button type="button" class="btn btn-info btn-sm"  style="margin-left: 5px"
                                        onclick="assessmentCommonHandle.copyData(this);"><i
                                        class="fa fa-copy" aria-hidden="true"></i> 拷贝
                                </button>
                                <button type="button" class="btn btn-warning btn-sm" style="margin-left: 5px;"
                                        onclick="assessmentCommonHandle.pasteAll(this);"><i
                                        class="fa fa-clipboard" aria-hidden="true"></i>粘贴
                                </button>
                                <button type="button"
                                        onclick="assessmentCommonHandle.batchSetFinish();"
                                        class="btn btn-primary btn-sm"><i class="fa fa-tasks"></i>一键完成</button>
                            </div>
                            <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                <div class="input-group ">
                                    <input type="hidden" name="id">
                                    <input type="text" readonly="readonly" name="name"
                                           class="form-control form-control-sm"
                                           placeholder="无拷贝数据">
                                    <div class="input-group-prepend ">
                                        <button class="btn btn-warning btn-sm"
                                                style="border-bottom-right-radius:.25rem;border-top-right-radius:.25rem;"
                                                type="button"
                                                onclick="$(this).closest('.input-group').find('input').val('');">
                                            <i class="fa "></i>
                                            清空拷贝
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <table class="table" id="assessmentPerformanceTableList">
            </table>
        </div>
    </div>
</div>
<div class="col-md-12">
    <div class="card full-height">
        <div class="card-header collapse-link">
            <div class="card-head-row">
                <div class="card-title">
                    工时考核
                </div>
                <div class="card-tools">
                    <button class="btn  btn-link btn-primary btn-xs"><span
                            class="fa fa-angle-up"></span>
                    </button>
                </div>
            </div>
        </div>
        <div class="card-body" style="display: none">
            <table class="table" id="assessmentWorkHoursTableList">
            </table>
        </div>
    </div>
</div>


<script type="text/javascript">

    /*初始化*/
    $(document).ready(function () {
        assessmentCommonHandle.loadAssessmentPerformanceList();
        assessmentCommonHandle.loadAssessmentWorkHoursList();
    });

</script>