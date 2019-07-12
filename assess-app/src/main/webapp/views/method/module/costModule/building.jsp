<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="x_panel">

    <div class="x_title collapse-link">
        <ul class="nav navbar-right panel_toolbox">
            <li><a class="collapse-link"><i class="fa fa-chevron-down"></i></a></li>
        </ul>
        <h3>建设成本</h3>
        <div class="clearfix"></div>
    </div>

    <div class="x_content">

        <div class="form-group">
            <label class="col-sm-1 control-label">
                勘察设计和前期工程费率<span class="symbol required"></span>
            </label>
            <div class="x-valid">
                <div class="col-sm-11">
                    <input type="text"
                           placeholder="勘察设计和前期工程费率" class="form-control x-percent"  required="required"
                           name="reconnaissanceDesignTax" onblur="cost.checkParams(this);">
                </div>
            </div>
        </div>
    </div>


</div>








