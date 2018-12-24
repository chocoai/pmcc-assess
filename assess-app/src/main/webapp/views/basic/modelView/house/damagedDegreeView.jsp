<%--
  Created by IntelliJ IDEA.
  User: kings
  Date: 2018-12-24
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i
                    class="fa fa-chevron-up"></i></a></li>
        </ul>
        <h4>房屋完损度</h4>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="" role="tabpanel" data-example-id="togglable-tabs">
            <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                <li role="presentation" class="active"><a href="#tab_content1" id="home-tab" role="tab"
                                                          data-toggle="tab" aria-expanded="true">结构部分</a>
                </li>
                <li role="presentation" class=""><a href="#tab_content2" role="tab" id="profile-tab" data-toggle="tab"
                                                    aria-expanded="false">装修部分</a>
                </li>
                <li role="presentation" class=""><a href="#tab_content3" role="tab" id="profile-tab2" data-toggle="tab"
                                                    aria-expanded="false">设备部分</a>
                </li>
                <li role="presentation" class=""><a href="#tab_content3" role="tab" id="profile-tab3" data-toggle="tab"
                                                    aria-expanded="false">其它</a>
                </li>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div role="tabpanel" class="tab-pane fade active in" id="tab_content1" aria-labelledby="home-tab">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th width="10%">名称</th>
                            <th width="5%">标准分</th>
                            <th width="10%">实例状况</th>
                            <th width="60%">状况内容</th>
                            <th width="5%">得分</th>
                            <th width="10%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th>地基基础</th>
                            <th>25</th>
                            <th>
                                <select class="form-control" name="entityCondition">
                                    <option>请选择</option>
                                </select>
                            </th>
                            <th>
                                <textarea class="form-control" name="entityConditionContent"></textarea>
                            </th>
                            <th>
                                <input type="text" name="score" class="form-control">
                            </th>
                            <th>
                                <div class="btn btn-xs btn-primary">明细内容</div>
                            </th>
                        </tr>
                        <tr>
                            <th>承重构件</th>
                            <th>25</th>
                            <th>
                                <select class="form-control" name="entityCondition">
                                    <option>请选择</option>
                                </select>
                            </th>
                            <th>
                                <textarea class="form-control" name="entityConditionContent"></textarea>
                            </th>
                            <th>
                                <input type="text" name="score" class="form-control">
                            </th>
                            <th>
                                <div class="btn btn-xs btn-primary">明细内容</div>
                            </th>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="profile-tab">

                </div>
                <div role="tabpanel" class="tab-pane fade" id="tab_content3" aria-labelledby="profile-tab">

                </div>
            </div>
        </div>

    </div>
</div>


<script type="text/javascript">

</script>
