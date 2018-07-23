<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="x_panel">
    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h2>市场比较法</h2>
        <div class="clearfix"></div>
    </div>
    <div class="x_content">
        <div class="table-responsive">
            <table class="table table-striped jambo_table bulk_action">
                <thead>
                <tr class="headings">
                    <th class="column-title" width="10%">项目</th>
                    <th class="column-title" width="10%">委估对象</th>
                    <th class="column-title" width="20%">案例1</th>
                    <th class="column-title" width="20%">案例2</th>
                </tr>
                </thead>
                <tbody>
                <tr class="even pointer">
                    <td class=" ">楼盘名称</td>
                    <td class=" ">彩叠园</td>
                    <td class=" ">长城半岛</td>
                    <td class=" ">新城佳宇</td>
                </tr>
                <tr class="odd pointer">
                    <td>交易情况</td>
                    <td>全款</td>
                    <td>
                        <p class="p_text">
                            <a href="javascript://" data-placeholder="Required" data-type="textarea"
                               data-original-title="交易情况"
                               class="editable editable-click editable-pre-wrapped editable-empty">贷款</a>
                        </p>
                        <p class="p_score">
                            <a href="javascript://" data-original-title="分值"
                               class="editable editable-click editable-pre-wrapped editable-empty">100</a>
                        </p>
                        <p class="p_result red">
                            1.1333
                        </p>
                    </td>
                    <td class=" green">
                        贷款
                        <input type="text" value="92">
                        【0.8333】
                    </td>
                </tr>
                <tr class="even pointer">
                    <td class=" ">交易时间</td>
                    <td class=" ">新城佳宇</td>
                    <td class=" ">
                        <a href="#" data-type="textarea" id="ct_lppt_2852" data-original-title="餐厅"
                           class="editable editable-click editable-pre-wrapped editable-empty">暂无</a>
                    </td>
                    <td class=" ">Mike Smith</td>
                </tr>
                <tr class="odd pointer">
                    <td class=" ">121000037</td>
                    <td class=" ">彩叠园</td>
                    <td class=" ">121000204</td>
                    <td class=" ">Mike Smith</td>
                </tr>
                <tr class="even pointer">
                    <td class=" ">121000040</td>
                    <td class=" ">祥润胡</td>
                    <td class=" ">121000210</td>
                    <td class=" ">John Blank L</td>
                </tr>
                <tr class="odd pointer">
                    <td class=" ">121000039</td>
                    <td class=" ">熙望</td>
                    <td class=" ">121000208 <i class="error fa fa-long-arrow-down"></i></td>
                    <td class=" ">John Blank L</td>
                </tr>
                <tr class="even pointer">
                    <td class=" ">121000038</td>
                    <td class=" ">核能琥珀</td>
                    <td class=" ">121000203</td>
                    <td class=" ">Mike Smith</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        $(".p_text").find('a').editable({
            validate: function (value) { //字段验证
                if (!$.trim(value)) {
                    return '不能为空';
                }
            }
        });

        $(".p_score").find('a').editable({
            validate: function (value) { //字段验证
                if (!$.trim(value)) {
                    return '不能为空';
                }
                if (!/^\+?[1-9][0-9]*$/.test(value)) {
                    return '只能填数字';
                }
                value = parseInt(value);
                if (value < 80 || value > 120) {
                    return '分值只能在80至120之间';
                }
            },
            url: function(params) {
                console.log($(this));
            }
        });
    })
</script>



