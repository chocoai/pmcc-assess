<%--
  Created by IntelliJ IDEA.
  User: zch
  Date: 2020-4-21
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en" class="no-js">
<head>
    <%@include file="/views/share/main_css.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">

    <script type="text/javascript"
            src="//webapi.amap.com/maps?v=1.4.15&key=ac9fb0371e0405ef74cb1ca003fd0eef&plugin=AMap.Autocomplete,AMap.ToolBar,AMap.PlaceSearch,AMap.MouseTool,AMap.RectangleEditor"></script>
    <script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>
    <link rel='stylesheet' type='text/css' href='https://a.amap.com/jsapi_demos/static/demo-center/css/prety-json.css'>
    <script type='text/javascript'
            src='https://a.amap.com/jsapi_demos/static/demo-center/js/underscore-min.js'></script>
    <script type='text/javascript' src='https://a.amap.com/jsapi_demos/static/demo-center/js/backbone-min.js'></script>
    <script type='text/javascript' src='https://a.amap.com/jsapi_demos/static/demo-center/js/prety-json.js'></script>
    <script type='text/javascript' src='https://a.amap.com/jsapi_demos/static/demo-center/js/demoutils.js'></script>
    <script type='text/javascript' src='https://a.amap.com/jsapi_demos/static/demo-center/js/prety-json.js'></script>


    <script src="${pageContext.request.contextPath}/assets/html2canvas/html2canvas.js?v=${assessVersion}"></script>

</head>
<body>


<div id="divPolygonColorBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">区块背景色填充选择</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">

                    <input type="hidden" name="pid">

                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                颜色参考
                                            </label>
                                            <div class="col-xs-11  col-sm-11  col-md-11  col-lg-11">
                                                <table width="550" cellspacing="3">
                                                    <tbody>
                                                    <tr>
                                                        <td bgcolor="#ffffff" height="30" width="30"></td>
                                                        <td>#FFFFFF</td>
                                                        <td bgcolor="#fffff0" height="30" width="30"></td>
                                                        <td>#FFFFF0</td>
                                                        <td bgcolor="#ffffe0" height="30" width="30"></td>
                                                        <td>#FFFFE0</td>
                                                        <td bgcolor="#ffff00" height="30" width="30"></td>
                                                        <td>#FFFF00</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#fffafa" height="30" width="30"></td>
                                                        <td>#FFFAFA</td>
                                                        <td bgcolor="#fffaf0" height="30" width="30"></td>
                                                        <td>#FFFAF0</td>
                                                        <td bgcolor="#fffacd" height="30" width="30"></td>
                                                        <td>#FFFACD</td>
                                                        <td bgcolor="#fff8dc" height="30" width="30"></td>
                                                        <td>#FFF8DC</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#fff68f" height="30" width="30"></td>
                                                        <td>#FFF68F</td>
                                                        <td bgcolor="#fff5ee" height="30" width="30"></td>
                                                        <td>#FFF5EE</td>
                                                        <td bgcolor="#fff0f5" height="30" width="30"></td>
                                                        <td>#FFF0F5</td>
                                                        <td bgcolor="#ffefdb" height="30" width="30"></td>
                                                        <td>#FFEFDB</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ffefd5" height="30" width="30"></td>
                                                        <td>#FFEFD5</td>
                                                        <td bgcolor="#ffec8b" height="30" width="30"></td>
                                                        <td>#FFEC8B</td>
                                                        <td bgcolor="#ffebcd" height="30" width="30"></td>
                                                        <td>#FFEBCD</td>
                                                        <td bgcolor="#ffe7ba" height="30" width="30"></td>
                                                        <td>#FFE7BA</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ffe4e1" height="30" width="30"></td>
                                                        <td>#FFE4E1</td>
                                                        <td bgcolor="#ffe4c4" height="30" width="30"></td>
                                                        <td>#FFE4C4</td>
                                                        <td bgcolor="#ffe4b5" height="30" width="30"></td>
                                                        <td>#FFE4B5</td>
                                                        <td bgcolor="#ffe1ff" height="30" width="30"></td>
                                                        <td>#FFE1FF</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ffdead" height="30" width="30"></td>
                                                        <td>#FFDEAD</td>
                                                        <td bgcolor="#ffdab9" height="30" width="30"></td>
                                                        <td>#FFDAB9</td>
                                                        <td bgcolor="#ffd700" height="30" width="30"></td>
                                                        <td>#FFD700</td>
                                                        <td bgcolor="#ffd39b" height="30" width="30"></td>
                                                        <td>#FFD39B</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ffc1c1" height="30" width="30"></td>
                                                        <td>#FFC1C1</td>
                                                        <td bgcolor="#ffc125" height="30" width="30"></td>
                                                        <td>#FFC125</td>
                                                        <td bgcolor="#ffc0cb" height="30" width="30"></td>
                                                        <td>#FFC0CB</td>
                                                        <td bgcolor="#ffbbff" height="30" width="30"></td>
                                                        <td>#FFBBFF</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ffb90f" height="30" width="30"></td>
                                                        <td>#FFB90F</td>
                                                        <td bgcolor="#ffb6c1" height="30" width="30"></td>
                                                        <td>#FFB6C1</td>
                                                        <td bgcolor="#ffb5c5" height="30" width="30"></td>
                                                        <td>#FFB5C5</td>
                                                        <td bgcolor="#ffaeb9" height="30" width="30"></td>
                                                        <td>#FFAEB9</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ffa54f" height="30" width="30"></td>
                                                        <td>#FFA54F</td>
                                                        <td bgcolor="#ffa500" height="30" width="30"></td>
                                                        <td>#FFA500</td>
                                                        <td bgcolor="#ffa07a" height="30" width="30"></td>
                                                        <td>#FFA07A</td>
                                                        <td bgcolor="#ff8c69" height="30" width="30"></td>
                                                        <td>#FF8C69</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ff8c00" height="30" width="30"></td>
                                                        <td>#FF8C00</td>
                                                        <td bgcolor="#ff83fa" height="30" width="30"></td>
                                                        <td>#FF83FA</td>
                                                        <td bgcolor="#ff82ab" height="30" width="30"></td>
                                                        <td>#FF82AB</td>
                                                        <td bgcolor="#ff8247" height="30" width="30"></td>
                                                        <td>#FF8247</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ff7f50" height="30" width="30"></td>
                                                        <td>#FF7F50</td>
                                                        <td bgcolor="#ff7f24" height="30" width="30"></td>
                                                        <td>#FF7F24</td>
                                                        <td bgcolor="#ff7f00" height="30" width="30"></td>
                                                        <td>#FF7F00</td>
                                                        <td bgcolor="#ff7256" height="30" width="30"></td>
                                                        <td>#FF7256</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ff6eb4" height="30" width="30"></td>
                                                        <td>#FF6EB4</td>
                                                        <td bgcolor="#ff6a6a" height="30" width="30"></td>
                                                        <td>#FF6A6A</td>
                                                        <td bgcolor="#ff69b4" height="30" width="30"></td>
                                                        <td>#FF69B4</td>
                                                        <td bgcolor="#ff6347" height="30" width="30"></td>
                                                        <td>#FF6347</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ff4500" height="30" width="30"></td>
                                                        <td>#FF4500</td>
                                                        <td bgcolor="#ff4040" height="30" width="30"></td>
                                                        <td>#FF4040</td>
                                                        <td bgcolor="#ff3e96" height="30" width="30"></td>
                                                        <td>#FF3E96</td>
                                                        <td bgcolor="#ff34b3" height="30" width="30"></td>
                                                        <td>#FF34B3</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ff3030" height="30" width="30"></td>
                                                        <td>#FF3030</td>
                                                        <td bgcolor="#ff1493" height="30" width="30"></td>
                                                        <td>#FF1493</td>
                                                        <td bgcolor="#ff00ff" height="30" width="30"></td>
                                                        <td>#FF00FF</td>
                                                        <td bgcolor="#ff0000" height="30" width="30"></td>
                                                        <td>#FF0000</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#fdf5e6" height="30" width="30"></td>
                                                        <td>#FDF5E6</td>
                                                        <td bgcolor="#fcfcfc" height="30" width="30"></td>
                                                        <td>#FCFCFC</td>
                                                        <td bgcolor="#fafafa" height="30" width="30"></td>
                                                        <td>#FAFAFA</td>
                                                        <td bgcolor="#fafad2" height="30" width="30"></td>
                                                        <td>#FAFAD2</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#faf0e6" height="30" width="30"></td>
                                                        <td>#FAF0E6</td>
                                                        <td bgcolor="#faebd7" height="30" width="30"></td>
                                                        <td>#FAEBD7</td>
                                                        <td bgcolor="#fa8072" height="30" width="30"></td>
                                                        <td>#FA8072</td>
                                                        <td bgcolor="#f8f8ff" height="30" width="30"></td>
                                                        <td>#F8F8FF</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#f7f7f7" height="30" width="30"></td>
                                                        <td>#F7F7F7</td>
                                                        <td bgcolor="#f5fffa" height="30" width="30"></td>
                                                        <td>#F5FFFA</td>
                                                        <td bgcolor="#f5f5f5" height="30" width="30"></td>
                                                        <td>#F5F5F5</td>
                                                        <td bgcolor="#f5f5dc" height="30" width="30"></td>
                                                        <td>#F5F5DC</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#f5deb3" height="30" width="30"></td>
                                                        <td>#F5DEB3</td>
                                                        <td bgcolor="#f4f4f4" height="30" width="30"></td>
                                                        <td>#F4F4F4</td>
                                                        <td bgcolor="#f4a460" height="30" width="30"></td>
                                                        <td>#F4A460</td>
                                                        <td bgcolor="#f2f2f2" height="30" width="30"></td>
                                                        <td>#F2F2F2</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#f0ffff" height="30" width="30"></td>
                                                        <td>#F0FFFF</td>
                                                        <td bgcolor="#f0fff0" height="30" width="30"></td>
                                                        <td>#F0FFF0</td>
                                                        <td bgcolor="#f0f8ff" height="30" width="30"></td>
                                                        <td>#F0F8FF</td>
                                                        <td bgcolor="#f0f0f0" height="30" width="30"></td>
                                                        <td>#F0F0F0</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#f0e68c" height="30" width="30"></td>
                                                        <td>#F0E68C</td>
                                                        <td bgcolor="#f08080" height="30" width="30"></td>
                                                        <td>#F08080</td>
                                                        <td bgcolor="#eeeee0" height="30" width="30"></td>
                                                        <td>#EEEEE0</td>
                                                        <td bgcolor="#eeeed1" height="30" width="30"></td>
                                                        <td>#EEEED1</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#eeee00" height="30" width="30"></td>
                                                        <td>#EEEE00</td>
                                                        <td bgcolor="#eee9e9" height="30" width="30"></td>
                                                        <td>#EEE9E9</td>
                                                        <td bgcolor="#eee9bf" height="30" width="30"></td>
                                                        <td>#EEE9BF</td>
                                                        <td bgcolor="#eee8cd" height="30" width="30"></td>
                                                        <td>#EEE8CD</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#eee8aa" height="30" width="30"></td>
                                                        <td>#EEE8AA</td>
                                                        <td bgcolor="#eee685" height="30" width="30"></td>
                                                        <td>#EEE685</td>
                                                        <td bgcolor="#eee5de" height="30" width="30"></td>
                                                        <td>#EEE5DE</td>
                                                        <td bgcolor="#eee0e5" height="30" width="30"></td>
                                                        <td>#EEE0E5</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#eedfcc" height="30" width="30"></td>
                                                        <td>#EEDFCC</td>
                                                        <td bgcolor="#eedc82" height="30" width="30"></td>
                                                        <td>#EEDC82</td>
                                                        <td bgcolor="#eed8ae" height="30" width="30"></td>
                                                        <td>#EED8AE</td>
                                                        <td bgcolor="#eed5d2" height="30" width="30"></td>
                                                        <td>#EED5D2</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#eed5b7" height="30" width="30"></td>
                                                        <td>#EED5B7</td>
                                                        <td bgcolor="#eed2ee" height="30" width="30"></td>
                                                        <td>#EED2EE</td>
                                                        <td bgcolor="#eecfa1" height="30" width="30"></td>
                                                        <td>#EECFA1</td>
                                                        <td bgcolor="#eecbad" height="30" width="30"></td>
                                                        <td>#EECBAD</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#eec900" height="30" width="30"></td>
                                                        <td>#EEC900</td>
                                                        <td bgcolor="#eec591" height="30" width="30"></td>
                                                        <td>#EEC591</td>
                                                        <td bgcolor="#eeb4b4" height="30" width="30"></td>
                                                        <td>#EEB4B4</td>
                                                        <td bgcolor="#eeb422" height="30" width="30"></td>
                                                        <td>#EEB422</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#eeaeee" height="30" width="30"></td>
                                                        <td>#EEAEEE</td>
                                                        <td bgcolor="#eead0e" height="30" width="30"></td>
                                                        <td>#EEAD0E</td>
                                                        <td bgcolor="#eea9b8" height="30" width="30"></td>
                                                        <td>#EEA9B8</td>
                                                        <td bgcolor="#eea2ad" height="30" width="30"></td>
                                                        <td>#EEA2AD</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ee9a49" height="30" width="30"></td>
                                                        <td>#EE9A49</td>
                                                        <td bgcolor="#ee9a00" height="30" width="30"></td>
                                                        <td>#EE9A00</td>
                                                        <td bgcolor="#ee9572" height="30" width="30"></td>
                                                        <td>#EE9572</td>
                                                        <td bgcolor="#ee82ee" height="30" width="30"></td>
                                                        <td>#EE82EE</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ee8262" height="30" width="30"></td>
                                                        <td>#EE8262</td>
                                                        <td bgcolor="#ee7ae9" height="30" width="30"></td>
                                                        <td>#EE7AE9</td>
                                                        <td bgcolor="#ee799f" height="30" width="30"></td>
                                                        <td>#EE799F</td>
                                                        <td bgcolor="#ee7942" height="30" width="30"></td>
                                                        <td>#EE7942</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ee7621" height="30" width="30"></td>
                                                        <td>#EE7621</td>
                                                        <td bgcolor="#ee7600" height="30" width="30"></td>
                                                        <td>#EE7600</td>
                                                        <td bgcolor="#ee6aa7" height="30" width="30"></td>
                                                        <td>#EE6AA7</td>
                                                        <td bgcolor="#ee6a50" height="30" width="30"></td>
                                                        <td>#EE6A50</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ee6363" height="30" width="30"></td>
                                                        <td>#EE6363</td>
                                                        <td bgcolor="#ee5c42" height="30" width="30"></td>
                                                        <td>#EE5C42</td>
                                                        <td bgcolor="#ee4000" height="30" width="30"></td>
                                                        <td>#EE4000</td>
                                                        <td bgcolor="#ee3b3b" height="30" width="30"></td>
                                                        <td>#EE3B3B</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ee3a8c" height="30" width="30"></td>
                                                        <td>#EE3A8C</td>
                                                        <td bgcolor="#ee30a7" height="30" width="30"></td>
                                                        <td>#EE30A7</td>
                                                        <td bgcolor="#ee2c2c" height="30" width="30"></td>
                                                        <td>#EE2C2C</td>
                                                        <td bgcolor="#ee1289" height="30" width="30"></td>
                                                        <td>#EE1289</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#ee00ee" height="30" width="30"></td>
                                                        <td>#EE00EE</td>
                                                        <td bgcolor="#ee0000" height="30" width="30"></td>
                                                        <td>#EE0000</td>
                                                        <td bgcolor="#ededed" height="30" width="30"></td>
                                                        <td>#EDEDED</td>
                                                        <td bgcolor="#ebebeb" height="30" width="30"></td>
                                                        <td>#EBEBEB</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#eaeaea" height="30" width="30"></td>
                                                        <td>#EAEAEA</td>
                                                        <td bgcolor="#e9967a" height="30" width="30"></td>
                                                        <td>#E9967A</td>
                                                        <td bgcolor="#e8e8e8" height="30" width="30"></td>
                                                        <td>#E8E8E8</td>
                                                        <td bgcolor="#e6e6fa" height="30" width="30"></td>
                                                        <td>#E6E6FA</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#e5e5e5" height="30" width="30"></td>
                                                        <td>#E5E5E5</td>
                                                        <td bgcolor="#e3e3e3" height="30" width="30"></td>
                                                        <td>#E3E3E3</td>
                                                        <td bgcolor="#e0ffff" height="30" width="30"></td>
                                                        <td>#E0FFFF</td>
                                                        <td bgcolor="#e0eeee" height="30" width="30"></td>
                                                        <td>#E0EEEE</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#e0eee0" height="30" width="30"></td>
                                                        <td>#E0EEE0</td>
                                                        <td bgcolor="#e0e0e0" height="30" width="30"></td>
                                                        <td>#E0E0E0</td>
                                                        <td bgcolor="#e066ff" height="30" width="30"></td>
                                                        <td>#E066FF</td>
                                                        <td bgcolor="#dedede" height="30" width="30"></td>
                                                        <td>#DEDEDE</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#deb887" height="30" width="30"></td>
                                                        <td>#DEB887</td>
                                                        <td bgcolor="#dda0dd" height="30" width="30"></td>
                                                        <td>#DDA0DD</td>
                                                        <td bgcolor="#dcdcdc" height="30" width="30"></td>
                                                        <td>#DCDCDC</td>
                                                        <td bgcolor="#dc143c" height="30" width="30"></td>
                                                        <td>#DC143C</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#dbdbdb" height="30" width="30"></td>
                                                        <td>#DBDBDB</td>
                                                        <td bgcolor="#db7093" height="30" width="30"></td>
                                                        <td>#DB7093</td>
                                                        <td bgcolor="#daa520" height="30" width="30"></td>
                                                        <td>#DAA520</td>
                                                        <td bgcolor="#da70d6" height="30" width="30"></td>
                                                        <td>#DA70D6</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#d9d9d9" height="30" width="30"></td>
                                                        <td>#D9D9D9</td>
                                                        <td bgcolor="#d8bfd8" height="30" width="30"></td>
                                                        <td>#D8BFD8</td>
                                                        <td bgcolor="#d6d6d6" height="30" width="30"></td>
                                                        <td>#D6D6D6</td>
                                                        <td bgcolor="#d4d4d4" height="30" width="30"></td>
                                                        <td>#D4D4D4</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#d3d3d3" height="30" width="30"></td>
                                                        <td>#D3D3D3</td>
                                                        <td bgcolor="#d2b48c" height="30" width="30"></td>
                                                        <td>#D2B48C</td>
                                                        <td bgcolor="#d2691e" height="30" width="30"></td>
                                                        <td>#D2691E</td>
                                                        <td bgcolor="#d1eeee" height="30" width="30"></td>
                                                        <td>#D1EEEE</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#d1d1d1" height="30" width="30"></td>
                                                        <td>#D1D1D1</td>
                                                        <td bgcolor="#d15fee" height="30" width="30"></td>
                                                        <td>#D15FEE</td>
                                                        <td bgcolor="#d02090" height="30" width="30"></td>
                                                        <td>#D02090</td>
                                                        <td bgcolor="#cfcfcf" height="30" width="30"></td>
                                                        <td>#CFCFCF</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#cdcdc1" height="30" width="30"></td>
                                                        <td>#CDCDC1</td>
                                                        <td bgcolor="#cdcdb4" height="30" width="30"></td>
                                                        <td>#CDCDB4</td>
                                                        <td bgcolor="#cdcd00" height="30" width="30"></td>
                                                        <td>#CDCD00</td>
                                                        <td bgcolor="#cdc9c9" height="30" width="30"></td>
                                                        <td>#CDC9C9</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#cdc9a5" height="30" width="30"></td>
                                                        <td>#CDC9A5</td>
                                                        <td bgcolor="#cdc8b1" height="30" width="30"></td>
                                                        <td>#CDC8B1</td>
                                                        <td bgcolor="#cdc673" height="30" width="30"></td>
                                                        <td>#CDC673</td>
                                                        <td bgcolor="#cdc5bf" height="30" width="30"></td>
                                                        <td>#CDC5BF</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#cdc1c5" height="30" width="30"></td>
                                                        <td>#CDC1C5</td>
                                                        <td bgcolor="#cdc0b0" height="30" width="30"></td>
                                                        <td>#CDC0B0</td>
                                                        <td bgcolor="#cdbe70" height="30" width="30"></td>
                                                        <td>#CDBE70</td>
                                                        <td bgcolor="#cdba96" height="30" width="30"></td>
                                                        <td>#CDBA96</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#cdb7b5" height="30" width="30"></td>
                                                        <td>#CDB7B5</td>
                                                        <td bgcolor="#cdb79e" height="30" width="30"></td>
                                                        <td>#CDB79E</td>
                                                        <td bgcolor="#cdb5cd" height="30" width="30"></td>
                                                        <td>#CDB5CD</td>
                                                        <td bgcolor="#cdb38b" height="30" width="30"></td>
                                                        <td>#CDB38B</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#cdaf95" height="30" width="30"></td>
                                                        <td>#CDAF95</td>
                                                        <td bgcolor="#cdad00" height="30" width="30"></td>
                                                        <td>#CDAD00</td>
                                                        <td bgcolor="#cdaa7d" height="30" width="30"></td>
                                                        <td>#CDAA7D</td>
                                                        <td bgcolor="#cd9b9b" height="30" width="30"></td>
                                                        <td>#CD9B9B</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#cd9b1d" height="30" width="30"></td>
                                                        <td>#CD9B1D</td>
                                                        <td bgcolor="#cd96cd" height="30" width="30"></td>
                                                        <td>#CD96CD</td>
                                                        <td bgcolor="#cd950c" height="30" width="30"></td>
                                                        <td>#CD950C</td>
                                                        <td bgcolor="#cd919e" height="30" width="30"></td>
                                                        <td>#CD919E</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#cd8c95" height="30" width="30"></td>
                                                        <td>#CD8C95</td>
                                                        <td bgcolor="#cd853f" height="30" width="30"></td>
                                                        <td>#CD853F</td>
                                                        <td bgcolor="#cd8500" height="30" width="30"></td>
                                                        <td>#CD8500</td>
                                                        <td bgcolor="#cd8162" height="30" width="30"></td>
                                                        <td>#CD8162</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#cd7054" height="30" width="30"></td>
                                                        <td>#CD7054</td>
                                                        <td bgcolor="#cd69c9" height="30" width="30"></td>
                                                        <td>#CD69C9</td>
                                                        <td bgcolor="#cd6889" height="30" width="30"></td>
                                                        <td>#CD6889</td>
                                                        <td bgcolor="#cd6839" height="30" width="30"></td>
                                                        <td>#CD6839</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#cd661d" height="30" width="30"></td>
                                                        <td>#CD661D</td>
                                                        <td bgcolor="#cd6600" height="30" width="30"></td>
                                                        <td>#CD6600</td>
                                                        <td bgcolor="#cd6090" height="30" width="30"></td>
                                                        <td>#CD6090</td>
                                                        <td bgcolor="#cd5c5c" height="30" width="30"></td>
                                                        <td>#CD5C5C</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#cd5b45" height="30" width="30"></td>
                                                        <td>#CD5B45</td>
                                                        <td bgcolor="#cd5555" height="30" width="30"></td>
                                                        <td>#CD5555</td>
                                                        <td bgcolor="#cd4f39" height="30" width="30"></td>
                                                        <td>#CD4F39</td>
                                                        <td bgcolor="#cd3700" height="30" width="30"></td>
                                                        <td>#CD3700</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#cd3333" height="30" width="30"></td>
                                                        <td>#CD3333</td>
                                                        <td bgcolor="#cd3278" height="30" width="30"></td>
                                                        <td>#CD3278</td>
                                                        <td bgcolor="#cd2990" height="30" width="30"></td>
                                                        <td>#CD2990</td>
                                                        <td bgcolor="#cd2626" height="30" width="30"></td>
                                                        <td>#CD2626</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#cd1076" height="30" width="30"></td>
                                                        <td>#CD1076</td>
                                                        <td bgcolor="#cd00cd" height="30" width="30"></td>
                                                        <td>#CD00CD</td>
                                                        <td bgcolor="#cd0000" height="30" width="30"></td>
                                                        <td>#CD0000</td>
                                                        <td bgcolor="#cccccc" height="30" width="30"></td>
                                                        <td>#CCCCCC</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#caff70" height="30" width="30"></td>
                                                        <td>#CAFF70</td>
                                                        <td bgcolor="#cae1ff" height="30" width="30"></td>
                                                        <td>#CAE1FF</td>
                                                        <td bgcolor="#c9c9c9" height="30" width="30"></td>
                                                        <td>#C9C9C9</td>
                                                        <td bgcolor="#c7c7c7" height="30" width="30"></td>
                                                        <td>#C7C7C7</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#c71585" height="30" width="30"></td>
                                                        <td>#C71585</td>
                                                        <td bgcolor="#c6e2ff" height="30" width="30"></td>
                                                        <td>#C6E2FF</td>
                                                        <td bgcolor="#c67171" height="30" width="30"></td>
                                                        <td>#C67171</td>
                                                        <td bgcolor="#c5c1aa" height="30" width="30"></td>
                                                        <td>#C5C1AA</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#c4c4c4" height="30" width="30"></td>
                                                        <td>#C4C4C4</td>
                                                        <td bgcolor="#c2c2c2" height="30" width="30"></td>
                                                        <td>#C2C2C2</td>
                                                        <td bgcolor="#c1ffc1" height="30" width="30"></td>
                                                        <td>#C1FFC1</td>
                                                        <td bgcolor="#c1cdcd" height="30" width="30"></td>
                                                        <td>#C1CDCD</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#c1cdc1" height="30" width="30"></td>
                                                        <td>#C1CDC1</td>
                                                        <td bgcolor="#c1c1c1" height="30" width="30"></td>
                                                        <td>#C1C1C1</td>
                                                        <td bgcolor="#c0ff3e" height="30" width="30"></td>
                                                        <td>#C0FF3E</td>
                                                        <td bgcolor="#bfefff" height="30" width="30"></td>
                                                        <td>#BFEFFF</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#bfbfbf" height="30" width="30"></td>
                                                        <td>#BFBFBF</td>
                                                        <td bgcolor="#bf3eff" height="30" width="30"></td>
                                                        <td>#BF3EFF</td>
                                                        <td bgcolor="#bebebe" height="30" width="30"></td>
                                                        <td>#BEBEBE</td>
                                                        <td bgcolor="#bdbdbd" height="30" width="30"></td>
                                                        <td>#BDBDBD</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#bdb76b" height="30" width="30"></td>
                                                        <td>#BDB76B</td>
                                                        <td bgcolor="#bcee68" height="30" width="30"></td>
                                                        <td>#BCEE68</td>
                                                        <td bgcolor="#bcd2ee" height="30" width="30"></td>
                                                        <td>#BCD2EE</td>
                                                        <td bgcolor="#bc8f8f" height="30" width="30"></td>
                                                        <td>#BC8F8F</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#bbffff" height="30" width="30"></td>
                                                        <td>#BBFFFF</td>
                                                        <td bgcolor="#bababa" height="30" width="30"></td>
                                                        <td>#BABABA</td>
                                                        <td bgcolor="#ba55d3" height="30" width="30"></td>
                                                        <td>#BA55D3</td>
                                                        <td bgcolor="#b9d3ee" height="30" width="30"></td>
                                                        <td>#B9D3EE</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#b8b8b8" height="30" width="30"></td>
                                                        <td>#B8B8B8</td>
                                                        <td bgcolor="#b8860b" height="30" width="30"></td>
                                                        <td>#B8860B</td>
                                                        <td bgcolor="#b7b7b7" height="30" width="30"></td>
                                                        <td>#B7B7B7</td>
                                                        <td bgcolor="#b5b5b5" height="30" width="30"></td>
                                                        <td>#B5B5B5</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#b4eeb4" height="30" width="30"></td>
                                                        <td>#B4EEB4</td>
                                                        <td bgcolor="#b4cdcd" height="30" width="30"></td>
                                                        <td>#B4CDCD</td>
                                                        <td bgcolor="#b452cd" height="30" width="30"></td>
                                                        <td>#B452CD</td>
                                                        <td bgcolor="#b3ee3a" height="30" width="30"></td>
                                                        <td>#B3EE3A</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#b3b3b3" height="30" width="30"></td>
                                                        <td>#B3B3B3</td>
                                                        <td bgcolor="#b2dfee" height="30" width="30"></td>
                                                        <td>#B2DFEE</td>
                                                        <td bgcolor="#b23aee" height="30" width="30"></td>
                                                        <td>#B23AEE</td>
                                                        <td bgcolor="#b22222" height="30" width="30"></td>
                                                        <td>#B22222</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#b0e2ff" height="30" width="30"></td>
                                                        <td>#B0E2FF</td>
                                                        <td bgcolor="#b0e0e6" height="30" width="30"></td>
                                                        <td>#B0E0E6</td>
                                                        <td bgcolor="#b0c4de" height="30" width="30"></td>
                                                        <td>#B0C4DE</td>
                                                        <td bgcolor="#b0b0b0" height="30" width="30"></td>
                                                        <td>#B0B0B0</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#b03060" height="30" width="30"></td>
                                                        <td>#B03060</td>
                                                        <td bgcolor="#aeeeee" height="30" width="30"></td>
                                                        <td>#AEEEEE</td>
                                                        <td bgcolor="#adff2f" height="30" width="30"></td>
                                                        <td>#ADFF2F</td>
                                                        <td bgcolor="#add8e6" height="30" width="30"></td>
                                                        <td>#ADD8E6</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#adadad" height="30" width="30"></td>
                                                        <td>#ADADAD</td>
                                                        <td bgcolor="#ababab" height="30" width="30"></td>
                                                        <td>#ABABAB</td>
                                                        <td bgcolor="#ab82ff" height="30" width="30"></td>
                                                        <td>#AB82FF</td>
                                                        <td bgcolor="#aaaaaa" height="30" width="30"></td>
                                                        <td>#AAAAAA</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#a9a9a9" height="30" width="30"></td>
                                                        <td>#A9A9A9</td>
                                                        <td bgcolor="#a8a8a8" height="30" width="30"></td>
                                                        <td>#A8A8A8</td>
                                                        <td bgcolor="#a6a6a6" height="30" width="30"></td>
                                                        <td>#A6A6A6</td>
                                                        <td bgcolor="#a52a2a" height="30" width="30"></td>
                                                        <td>#A52A2A</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#a4d3ee" height="30" width="30"></td>
                                                        <td>#A4D3EE</td>
                                                        <td bgcolor="#a3a3a3" height="30" width="30"></td>
                                                        <td>#A3A3A3</td>
                                                        <td bgcolor="#a2cd5a" height="30" width="30"></td>
                                                        <td>#A2CD5A</td>
                                                        <td bgcolor="#a2b5cd" height="30" width="30"></td>
                                                        <td>#A2B5CD</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#a1a1a1" height="30" width="30"></td>
                                                        <td>#A1A1A1</td>
                                                        <td bgcolor="#a0522d" height="30" width="30"></td>
                                                        <td>#A0522D</td>
                                                        <td bgcolor="#a020f0" height="30" width="30"></td>
                                                        <td>#A020F0</td>
                                                        <td bgcolor="#9fb6cd" height="30" width="30"></td>
                                                        <td>#9FB6CD</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#9f79ee" height="30" width="30"></td>
                                                        <td>#9F79EE</td>
                                                        <td bgcolor="#9e9e9e" height="30" width="30"></td>
                                                        <td>#9E9E9E</td>
                                                        <td bgcolor="#9c9c9c" height="30" width="30"></td>
                                                        <td>#9C9C9C</td>
                                                        <td bgcolor="#9bcd9b" height="30" width="30"></td>
                                                        <td>#9BCD9B</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#9b30ff" height="30" width="30"></td>
                                                        <td>#9B30FF</td>
                                                        <td bgcolor="#9aff9a" height="30" width="30"></td>
                                                        <td>#9AFF9A</td>
                                                        <td bgcolor="#9acd32" height="30" width="30"></td>
                                                        <td>#9ACD32</td>
                                                        <td bgcolor="#9ac0cd" height="30" width="30"></td>
                                                        <td>#9AC0CD</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#9a32cd" height="30" width="30"></td>
                                                        <td>#9A32CD</td>
                                                        <td bgcolor="#999999" height="30" width="30"></td>
                                                        <td>#999999</td>
                                                        <td bgcolor="#9932cc" height="30" width="30"></td>
                                                        <td>#9932CC</td>
                                                        <td bgcolor="#98fb98" height="30" width="30"></td>
                                                        <td>#98FB98</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#98f5ff" height="30" width="30"></td>
                                                        <td>#98F5FF</td>
                                                        <td bgcolor="#97ffff" height="30" width="30"></td>
                                                        <td>#97FFFF</td>
                                                        <td bgcolor="#96cdcd" height="30" width="30"></td>
                                                        <td>#96CDCD</td>
                                                        <td bgcolor="#969696" height="30" width="30"></td>
                                                        <td>#969696</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#949494" height="30" width="30"></td>
                                                        <td>#949494</td>
                                                        <td bgcolor="#9400d3" height="30" width="30"></td>
                                                        <td>#9400D3</td>
                                                        <td bgcolor="#9370db" height="30" width="30"></td>
                                                        <td>#9370DB</td>
                                                        <td bgcolor="#919191" height="30" width="30"></td>
                                                        <td>#919191</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#912cee" height="30" width="30"></td>
                                                        <td>#912CEE</td>
                                                        <td bgcolor="#90ee90" height="30" width="30"></td>
                                                        <td>#90EE90</td>
                                                        <td bgcolor="#8fbc8f" height="30" width="30"></td>
                                                        <td>#8FBC8F</td>
                                                        <td bgcolor="#8f8f8f" height="30" width="30"></td>
                                                        <td>#8F8F8F</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#8ee5ee" height="30" width="30"></td>
                                                        <td>#8EE5EE</td>
                                                        <td bgcolor="#8e8e8e" height="30" width="30"></td>
                                                        <td>#8E8E8E</td>
                                                        <td bgcolor="#8e8e38" height="30" width="30"></td>
                                                        <td>#8E8E38</td>
                                                        <td bgcolor="#8e388e" height="30" width="30"></td>
                                                        <td>#8E388E</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#8deeee" height="30" width="30"></td>
                                                        <td>#8DEEEE</td>
                                                        <td bgcolor="#8db6cd" height="30" width="30"></td>
                                                        <td>#8DB6CD</td>
                                                        <td bgcolor="#8c8c8c" height="30" width="30"></td>
                                                        <td>#8C8C8C</td>
                                                        <td bgcolor="#8b8b83" height="30" width="30"></td>
                                                        <td>#8B8B83</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#8b8b7a" height="30" width="30"></td>
                                                        <td>#8B8B7A</td>
                                                        <td bgcolor="#8b8b00" height="30" width="30"></td>
                                                        <td>#8B8B00</td>
                                                        <td bgcolor="#8b8989" height="30" width="30"></td>
                                                        <td>#8B8989</td>
                                                        <td bgcolor="#8b8970" height="30" width="30"></td>
                                                        <td>#8B8970</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#8b8878" height="30" width="30"></td>
                                                        <td>#8B8878</td>
                                                        <td bgcolor="#8b8682" height="30" width="30"></td>
                                                        <td>#8B8682</td>
                                                        <td bgcolor="#8b864e" height="30" width="30"></td>
                                                        <td>#8B864E</td>
                                                        <td bgcolor="#8b8386" height="30" width="30"></td>
                                                        <td>#8B8386</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#8b8378" height="30" width="30"></td>
                                                        <td>#8B8378</td>
                                                        <td bgcolor="#8b814c" height="30" width="30"></td>
                                                        <td>#8B814C</td>
                                                        <td bgcolor="#8b7e66" height="30" width="30"></td>
                                                        <td>#8B7E66</td>
                                                        <td bgcolor="#8b7d7b" height="30" width="30"></td>
                                                        <td>#8B7D7B</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#8b7d6b" height="30" width="30"></td>
                                                        <td>#8B7D6B</td>
                                                        <td bgcolor="#8b7b8b" height="30" width="30"></td>
                                                        <td>#8B7B8B</td>
                                                        <td bgcolor="#8b795e" height="30" width="30"></td>
                                                        <td>#8B795E</td>
                                                        <td bgcolor="#8b7765" height="30" width="30"></td>
                                                        <td>#8B7765</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#8b7500" height="30" width="30"></td>
                                                        <td>#8B7500</td>
                                                        <td bgcolor="#8b7355" height="30" width="30"></td>
                                                        <td>#8B7355</td>
                                                        <td bgcolor="#8b6969" height="30" width="30"></td>
                                                        <td>#8B6969</td>
                                                        <td bgcolor="#8b6914" height="30" width="30"></td>
                                                        <td>#8B6914</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#8b668b" height="30" width="30"></td>
                                                        <td>#8B668B</td>
                                                        <td bgcolor="#8b6508" height="30" width="30"></td>
                                                        <td>#8B6508</td>
                                                        <td bgcolor="#8b636c" height="30" width="30"></td>
                                                        <td>#8B636C</td>
                                                        <td bgcolor="#8b5f65" height="30" width="30"></td>
                                                        <td>#8B5F65</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#8b5a2b" height="30" width="30"></td>
                                                        <td>#8B5A2B</td>
                                                        <td bgcolor="#8b5a00" height="30" width="30"></td>
                                                        <td>#8B5A00</td>
                                                        <td bgcolor="#8b5742" height="30" width="30"></td>
                                                        <td>#8B5742</td>
                                                        <td bgcolor="#8b4c39" height="30" width="30"></td>
                                                        <td>#8B4C39</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#8b4789" height="30" width="30"></td>
                                                        <td>#8B4789</td>
                                                        <td bgcolor="#8b475d" height="30" width="30"></td>
                                                        <td>#8B475D</td>
                                                        <td bgcolor="#8b4726" height="30" width="30"></td>
                                                        <td>#8B4726</td>
                                                        <td bgcolor="#8b4513" height="30" width="30"></td>
                                                        <td>#8B4513</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#8b4500" height="30" width="30"></td>
                                                        <td>#8B4500</td>
                                                        <td bgcolor="#8b3e2f" height="30" width="30"></td>
                                                        <td>#8B3E2F</td>
                                                        <td bgcolor="#8b3a62" height="30" width="30"></td>
                                                        <td>#8B3A62</td>
                                                        <td bgcolor="#8b3a3a" height="30" width="30"></td>
                                                        <td>#8B3A3A</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#8b3626" height="30" width="30"></td>
                                                        <td>#8B3626</td>
                                                        <td bgcolor="#8b2500" height="30" width="30"></td>
                                                        <td>#8B2500</td>
                                                        <td bgcolor="#8b2323" height="30" width="30"></td>
                                                        <td>#8B2323</td>
                                                        <td bgcolor="#8b2252" height="30" width="30"></td>
                                                        <td>#8B2252</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#8b1c62" height="30" width="30"></td>
                                                        <td>#8B1C62</td>
                                                        <td bgcolor="#8b1a1a" height="30" width="30"></td>
                                                        <td>#8B1A1A</td>
                                                        <td bgcolor="#8b0a50" height="30" width="30"></td>
                                                        <td>#8B0A50</td>
                                                        <td bgcolor="#8b008b" height="30" width="30"></td>
                                                        <td>#8B008B</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#8b0000" height="30" width="30"></td>
                                                        <td>#8B0000</td>
                                                        <td bgcolor="#8a8a8a" height="30" width="30"></td>
                                                        <td>#8A8A8A</td>
                                                        <td bgcolor="#8a2be2" height="30" width="30"></td>
                                                        <td>#8A2BE2</td>
                                                        <td bgcolor="#8968cd" height="30" width="30"></td>
                                                        <td>#8968CD</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#87ceff" height="30" width="30"></td>
                                                        <td>#87CEFF</td>
                                                        <td bgcolor="#87cefa" height="30" width="30"></td>
                                                        <td>#87CEFA</td>
                                                        <td bgcolor="#87ceeb" height="30" width="30"></td>
                                                        <td>#87CEEB</td>
                                                        <td bgcolor="#878787" height="30" width="30"></td>
                                                        <td>#878787</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#858585" height="30" width="30"></td>
                                                        <td>#858585</td>
                                                        <td bgcolor="#848484" height="30" width="30"></td>
                                                        <td>#848484</td>
                                                        <td bgcolor="#8470ff" height="30" width="30"></td>
                                                        <td>#8470FF</td>
                                                        <td bgcolor="#838b8b" height="30" width="30"></td>
                                                        <td>#838B8B</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#838b83" height="30" width="30"></td>
                                                        <td>#838B83</td>
                                                        <td bgcolor="#836fff" height="30" width="30"></td>
                                                        <td>#836FFF</td>
                                                        <td bgcolor="#828282" height="30" width="30"></td>
                                                        <td>#828282</td>
                                                        <td bgcolor="#7fffd4" height="30" width="30"></td>
                                                        <td>#7FFFD4</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#7fff00" height="30" width="30"></td>
                                                        <td>#7FFF00</td>
                                                        <td bgcolor="#7f7f7f" height="30" width="30"></td>
                                                        <td>#7F7F7F</td>
                                                        <td bgcolor="#7ec0ee" height="30" width="30"></td>
                                                        <td>#7EC0EE</td>
                                                        <td bgcolor="#7d9ec0" height="30" width="30"></td>
                                                        <td>#7D9EC0</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#7d7d7d" height="30" width="30"></td>
                                                        <td>#7D7D7D</td>
                                                        <td bgcolor="#7d26cd" height="30" width="30"></td>
                                                        <td>#7D26CD</td>
                                                        <td bgcolor="#7cfc00" height="30" width="30"></td>
                                                        <td>#7CFC00</td>
                                                        <td bgcolor="#7ccd7c" height="30" width="30"></td>
                                                        <td>#7CCD7C</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#7b68ee" height="30" width="30"></td>
                                                        <td>#7B68EE</td>
                                                        <td bgcolor="#7ac5cd" height="30" width="30"></td>
                                                        <td>#7AC5CD</td>
                                                        <td bgcolor="#7a8b8b" height="30" width="30"></td>
                                                        <td>#7A8B8B</td>
                                                        <td bgcolor="#7a7a7a" height="30" width="30"></td>
                                                        <td>#7A7A7A</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#7a67ee" height="30" width="30"></td>
                                                        <td>#7A67EE</td>
                                                        <td bgcolor="#7a378b" height="30" width="30"></td>
                                                        <td>#7A378B</td>
                                                        <td bgcolor="#79cdcd" height="30" width="30"></td>
                                                        <td>#79CDCD</td>
                                                        <td bgcolor="#787878" height="30" width="30"></td>
                                                        <td>#787878</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#778899" height="30" width="30"></td>
                                                        <td>#778899</td>
                                                        <td bgcolor="#76eec6" height="30" width="30"></td>
                                                        <td>#76EEC6</td>
                                                        <td bgcolor="#76ee00" height="30" width="30"></td>
                                                        <td>#76EE00</td>
                                                        <td bgcolor="#757575" height="30" width="30"></td>
                                                        <td>#757575</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#737373" height="30" width="30"></td>
                                                        <td>#737373</td>
                                                        <td bgcolor="#71c671" height="30" width="30"></td>
                                                        <td>#71C671</td>
                                                        <td bgcolor="#7171c6" height="30" width="30"></td>
                                                        <td>#7171C6</td>
                                                        <td bgcolor="#708090" height="30" width="30"></td>
                                                        <td>#708090</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#707070" height="30" width="30"></td>
                                                        <td>#707070</td>
                                                        <td bgcolor="#6e8b3d" height="30" width="30"></td>
                                                        <td>#6E8B3D</td>
                                                        <td bgcolor="#6e7b8b" height="30" width="30"></td>
                                                        <td>#6E7B8B</td>
                                                        <td bgcolor="#6e6e6e" height="30" width="30"></td>
                                                        <td>#6E6E6E</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#6ca6cd" height="30" width="30"></td>
                                                        <td>#6CA6CD</td>
                                                        <td bgcolor="#6c7b8b" height="30" width="30"></td>
                                                        <td>#6C7B8B</td>
                                                        <td bgcolor="#6b8e23" height="30" width="30"></td>
                                                        <td>#6B8E23</td>
                                                        <td bgcolor="#6b6b6b" height="30" width="30"></td>
                                                        <td>#6B6B6B</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#6a5acd" height="30" width="30"></td>
                                                        <td>#6A5ACD</td>
                                                        <td bgcolor="#698b69" height="30" width="30"></td>
                                                        <td>#698B69</td>
                                                        <td bgcolor="#698b22" height="30" width="30"></td>
                                                        <td>#698B22</td>
                                                        <td bgcolor="#696969" height="30" width="30"></td>
                                                        <td>#696969</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#6959cd" height="30" width="30"></td>
                                                        <td>#6959CD</td>
                                                        <td bgcolor="#68838b" height="30" width="30"></td>
                                                        <td>#68838B</td>
                                                        <td bgcolor="#68228b" height="30" width="30"></td>
                                                        <td>#68228B</td>
                                                        <td bgcolor="#66cdaa" height="30" width="30"></td>
                                                        <td>#66CDAA</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#66cd00" height="30" width="30"></td>
                                                        <td>#66CD00</td>
                                                        <td bgcolor="#668b8b" height="30" width="30"></td>
                                                        <td>#668B8B</td>
                                                        <td bgcolor="#666666" height="30" width="30"></td>
                                                        <td>#666666</td>
                                                        <td bgcolor="#6495ed" height="30" width="30"></td>
                                                        <td>#6495ED</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#63b8ff" height="30" width="30"></td>
                                                        <td>#63B8FF</td>
                                                        <td bgcolor="#636363" height="30" width="30"></td>
                                                        <td>#636363</td>
                                                        <td bgcolor="#616161" height="30" width="30"></td>
                                                        <td>#616161</td>
                                                        <td bgcolor="#607b8b" height="30" width="30"></td>
                                                        <td>#607B8B</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#5f9ea0" height="30" width="30"></td>
                                                        <td>#5F9EA0</td>
                                                        <td bgcolor="#5e5e5e" height="30" width="30"></td>
                                                        <td>#5E5E5E</td>
                                                        <td bgcolor="#5d478b" height="30" width="30"></td>
                                                        <td>#5D478B</td>
                                                        <td bgcolor="#5cacee" height="30" width="30"></td>
                                                        <td>#5CACEE</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#5c5c5c" height="30" width="30"></td>
                                                        <td>#5C5C5C</td>
                                                        <td bgcolor="#5b5b5b" height="30" width="30"></td>
                                                        <td>#5B5B5B</td>
                                                        <td bgcolor="#595959" height="30" width="30"></td>
                                                        <td>#595959</td>
                                                        <td bgcolor="#575757" height="30" width="30"></td>
                                                        <td>#575757</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#556b2f" height="30" width="30"></td>
                                                        <td>#556B2F</td>
                                                        <td bgcolor="#555555" height="30" width="30"></td>
                                                        <td>#555555</td>
                                                        <td bgcolor="#551a8b" height="30" width="30"></td>
                                                        <td>#551A8B</td>
                                                        <td bgcolor="#54ff9f" height="30" width="30"></td>
                                                        <td>#54FF9F</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#548b54" height="30" width="30"></td>
                                                        <td>#548B54</td>
                                                        <td bgcolor="#545454" height="30" width="30"></td>
                                                        <td>#545454</td>
                                                        <td bgcolor="#53868b" height="30" width="30"></td>
                                                        <td>#53868B</td>
                                                        <td bgcolor="#528b8b" height="30" width="30"></td>
                                                        <td>#528B8B</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#525252" height="30" width="30"></td>
                                                        <td>#525252</td>
                                                        <td bgcolor="#515151" height="30" width="30"></td>
                                                        <td>#515151</td>
                                                        <td bgcolor="#4f94cd" height="30" width="30"></td>
                                                        <td>#4F94CD</td>
                                                        <td bgcolor="#4f4f4f" height="30" width="30"></td>
                                                        <td>#4F4F4F</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#4eee94" height="30" width="30"></td>
                                                        <td>#4EEE94</td>
                                                        <td bgcolor="#4d4d4d" height="30" width="30"></td>
                                                        <td>#4D4D4D</td>
                                                        <td bgcolor="#4b0082" height="30" width="30"></td>
                                                        <td>#4B0082</td>
                                                        <td bgcolor="#4a708b" height="30" width="30"></td>
                                                        <td>#4A708B</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#4a4a4a" height="30" width="30"></td>
                                                        <td>#4A4A4A</td>
                                                        <td bgcolor="#48d1cc" height="30" width="30"></td>
                                                        <td>#48D1CC</td>
                                                        <td bgcolor="#4876ff" height="30" width="30"></td>
                                                        <td>#4876FF</td>
                                                        <td bgcolor="#483d8b" height="30" width="30"></td>
                                                        <td>#483D8B</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#474747" height="30" width="30"></td>
                                                        <td>#474747</td>
                                                        <td bgcolor="#473c8b" height="30" width="30"></td>
                                                        <td>#473C8B</td>
                                                        <td bgcolor="#4682b4" height="30" width="30"></td>
                                                        <td>#4682B4</td>
                                                        <td bgcolor="#458b74" height="30" width="30"></td>
                                                        <td>#458B74</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#458b00" height="30" width="30"></td>
                                                        <td>#458B00</td>
                                                        <td bgcolor="#454545" height="30" width="30"></td>
                                                        <td>#454545</td>
                                                        <td bgcolor="#43cd80" height="30" width="30"></td>
                                                        <td>#43CD80</td>
                                                        <td bgcolor="#436eee" height="30" width="30"></td>
                                                        <td>#436EEE</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#424242" height="30" width="30"></td>
                                                        <td>#424242</td>
                                                        <td bgcolor="#4169e1" height="30" width="30"></td>
                                                        <td>#4169E1</td>
                                                        <td bgcolor="#40e0d0" height="30" width="30"></td>
                                                        <td>#40E0D0</td>
                                                        <td bgcolor="#404040" height="30" width="30"></td>
                                                        <td>#404040</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#3d3d3d" height="30" width="30"></td>
                                                        <td>#3D3D3D</td>
                                                        <td bgcolor="#3cb371" height="30" width="30"></td>
                                                        <td>#3CB371</td>
                                                        <td bgcolor="#3b3b3b" height="30" width="30"></td>
                                                        <td>#3B3B3B</td>
                                                        <td bgcolor="#3a5fcd" height="30" width="30"></td>
                                                        <td>#3A5FCD</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#388e8e" height="30" width="30"></td>
                                                        <td>#388E8E</td>
                                                        <td bgcolor="#383838" height="30" width="30"></td>
                                                        <td>#383838</td>
                                                        <td bgcolor="#36648b" height="30" width="30"></td>
                                                        <td>#36648B</td>
                                                        <td bgcolor="#363636" height="30" width="30"></td>
                                                        <td>#363636</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#333333" height="30" width="30"></td>
                                                        <td>#333333</td>
                                                        <td bgcolor="#32cd32" height="30" width="30"></td>
                                                        <td>#32CD32</td>
                                                        <td bgcolor="#303030" height="30" width="30"></td>
                                                        <td>#303030</td>
                                                        <td bgcolor="#2f4f4f" height="30" width="30"></td>
                                                        <td>#2F4F4F</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#2e8b57" height="30" width="30"></td>
                                                        <td>#2E8B57</td>
                                                        <td bgcolor="#2e2e2e" height="30" width="30"></td>
                                                        <td>#2E2E2E</td>
                                                        <td bgcolor="#2b2b2b" height="30" width="30"></td>
                                                        <td>#2B2B2B</td>
                                                        <td bgcolor="#292929" height="30" width="30"></td>
                                                        <td>#292929</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#282828" height="30" width="30"></td>
                                                        <td>#282828</td>
                                                        <td bgcolor="#27408b" height="30" width="30"></td>
                                                        <td>#27408B</td>
                                                        <td bgcolor="#262626" height="30" width="30"></td>
                                                        <td>#262626</td>
                                                        <td bgcolor="#242424" height="30" width="30"></td>
                                                        <td>#242424</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#228b22" height="30" width="30"></td>
                                                        <td>#228B22</td>
                                                        <td bgcolor="#218868" height="30" width="30"></td>
                                                        <td>#218868</td>
                                                        <td bgcolor="#212121" height="30" width="30"></td>
                                                        <td>#212121</td>
                                                        <td bgcolor="#20b2aa" height="30" width="30"></td>
                                                        <td>#20B2AA</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#1f1f1f" height="30" width="30"></td>
                                                        <td>#1F1F1F</td>
                                                        <td bgcolor="#1e90ff" height="30" width="30"></td>
                                                        <td>#1E90FF</td>
                                                        <td bgcolor="#1e1e1e" height="30" width="30"></td>
                                                        <td>#1E1E1E</td>
                                                        <td bgcolor="#1c86ee" height="30" width="30"></td>
                                                        <td>#1C86EE</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#1c1c1c" height="30" width="30"></td>
                                                        <td>#1C1C1C</td>
                                                        <td bgcolor="#1a1a1a" height="30" width="30"></td>
                                                        <td>#1A1A1A</td>
                                                        <td bgcolor="#191970" height="30" width="30"></td>
                                                        <td>#191970</td>
                                                        <td bgcolor="#1874cd" height="30" width="30"></td>
                                                        <td>#1874CD</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#171717" height="30" width="30"></td>
                                                        <td>#171717</td>
                                                        <td bgcolor="#141414" height="30" width="30"></td>
                                                        <td>#141414</td>
                                                        <td bgcolor="#121212" height="30" width="30"></td>
                                                        <td>#121212</td>
                                                        <td bgcolor="#104e8b" height="30" width="30"></td>
                                                        <td>#104E8B</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#0f0f0f" height="30" width="30"></td>
                                                        <td>#0F0F0F</td>
                                                        <td bgcolor="#0d0d0d" height="30" width="30"></td>
                                                        <td>#0D0D0D</td>
                                                        <td bgcolor="#0a0a0a" height="30" width="30"></td>
                                                        <td>#0A0A0A</td>
                                                        <td bgcolor="#080808" height="30" width="30"></td>
                                                        <td>#080808</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#050505" height="30" width="30"></td>
                                                        <td>#050505</td>
                                                        <td bgcolor="#030303" height="30" width="30"></td>
                                                        <td>#030303</td>
                                                        <td bgcolor="#00ffff" height="30" width="30"></td>
                                                        <td>#00FFFF</td>
                                                        <td bgcolor="#00ff7f" height="30" width="30"></td>
                                                        <td>#00FF7F</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#00ff00" height="30" width="30"></td>
                                                        <td>#00FF00</td>
                                                        <td bgcolor="#00fa9a" height="30" width="30"></td>
                                                        <td>#00FA9A</td>
                                                        <td bgcolor="#00f5ff" height="30" width="30"></td>
                                                        <td>#00F5FF</td>
                                                        <td bgcolor="#00eeee" height="30" width="30"></td>
                                                        <td>#00EEEE</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#00ee76" height="30" width="30"></td>
                                                        <td>#00EE76</td>
                                                        <td bgcolor="#00ee00" height="30" width="30"></td>
                                                        <td>#00EE00</td>
                                                        <td bgcolor="#00e5ee" height="30" width="30"></td>
                                                        <td>#00E5EE</td>
                                                        <td bgcolor="#00ced1" height="30" width="30"></td>
                                                        <td>#00CED1</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#00cdcd" height="30" width="30"></td>
                                                        <td>#00CDCD</td>
                                                        <td bgcolor="#00cd66" height="30" width="30"></td>
                                                        <td>#00CD66</td>
                                                        <td bgcolor="#00cd00" height="30" width="30"></td>
                                                        <td>#00CD00</td>
                                                        <td bgcolor="#00c5cd" height="30" width="30"></td>
                                                        <td>#00C5CD</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#00bfff" height="30" width="30"></td>
                                                        <td>#00BFFF</td>
                                                        <td bgcolor="#00b2ee" height="30" width="30"></td>
                                                        <td>#00B2EE</td>
                                                        <td bgcolor="#009acd" height="30" width="30"></td>
                                                        <td>#009ACD</td>
                                                        <td bgcolor="#008b8b" height="30" width="30"></td>
                                                        <td>#008B8B</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#008b45" height="30" width="30"></td>
                                                        <td>#008B45</td>
                                                        <td bgcolor="#008b00" height="30" width="30"></td>
                                                        <td>#008B00</td>
                                                        <td bgcolor="#00868b" height="30" width="30"></td>
                                                        <td>#00868B</td>
                                                        <td bgcolor="#00688b" height="30" width="30"></td>
                                                        <td>#00688B</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#006400" height="30" width="30"></td>
                                                        <td>#006400</td>
                                                        <td bgcolor="#0000ff" height="30" width="30"></td>
                                                        <td>#0000FF</td>
                                                        <td bgcolor="#0000ee" height="30" width="30"></td>
                                                        <td>#0000EE</td>
                                                        <td bgcolor="#0000cd" height="30" width="30"></td>
                                                        <td>#0000CD</td>
                                                    </tr>
                                                    <tr>
                                                        <td bgcolor="#0000aa" height="30" width="30"></td>
                                                        <td>#0000AA</td>
                                                        <td bgcolor="#00008b" height="30" width="30"></td>
                                                        <td>#00008B</td>
                                                        <td bgcolor="#000080" height="30" width="30"></td>
                                                        <td>#000080</td>
                                                        <td bgcolor="#000000" height="30" width="30"></td>
                                                        <td>#000000</td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                颜色<span class="symbol required"></span>
                                            </label>
                                            <div class="col-xs-9  col-sm-9  col-md-9  col-lg-9">
                                                <input type="text" placeholder="颜色"
                                                       name="color" required="required"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm"
                        onclick="">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="drawPolygon.selectColor();">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="divPolygonBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">区块标题</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">

                    <input type="hidden" name="id">
                    <input type="hidden" name="pid">
                    <input type="hidden" name="lng">
                    <input type="hidden" name="lat">

                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                标题<span class="symbol required"></span>
                                            </label>
                                            <div class="col-xs-9  col-sm-9  col-md-9  col-lg-9">
                                                <input type="text" placeholder="标题"
                                                       name="name" required="required"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm"
                        onclick="drawPolygon.cancelSaveTitle();">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="drawPolygon.saveTitleData() ;">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div id="divTextBox" class="modal fade bs-example-modal-lg" data-backdrop="static" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">区块标题</h4>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">

                    <input type="hidden" name="id">
                    <input type="hidden" name="pid">
                    <input type="hidden" name="lng">
                    <input type="hidden" name="lat">

                    <div class="row">
                        <div class=" col-xs-12  col-sm-12  col-md-12  col-lg-12 ">
                            <div class="card-body">
                                <div class="row form-group">
                                    <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                        <div class="form-inline x-valid">
                                            <label class="col-xs-1  col-sm-1  col-md-1  col-lg-1 control-label">
                                                标题<span class="symbol required"></span>
                                            </label>
                                            <div class="col-xs-9  col-sm-9  col-md-9  col-lg-9">
                                                <input type="text" placeholder="标题"
                                                       name="name" required="required"
                                                       class="form-control input-full">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default btn-sm"
                        onclick="drawPolygon.cancelSaveTitle();">
                    关闭
                </button>
                <button type="button" class="btn btn-primary btn-sm" onclick="drawPolygon.saveTextData() ;">
                    保存
                </button>
            </div>
        </div>
    </div>
</div>

<div class="wrapper">
    <div class="main-panel" style="width: 100%">
        <div class="content" style="margin-top: 0px;">
            <div class="page-inner">
                <div class="row mt--2">
                    <!-- 填写表单 start -->
                    <div class="col-md-12">
                        <div class="card full-height">
                            <div class="card-header ">
                                <div class="card-head-row">
                                    <div class="card-title">
                                        高德地图
                                        <c:if test="${empty masterName}">
                                            区块
                                        </c:if>
                                        <c:if test="${! empty masterName}">
                                            ${masterName}
                                        </c:if>
                                        操作
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal">

                                    <div class="row form-group">
                                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                            <button style="margin-left: 5px" class="btn btn-primary btn-sm"
                                                    type="button"
                                                    onclick="drawPolygon.clearMap() ;">
											<span class="btn-label">
												<i class="fa "></i>
											</span>
                                                清除所有标记
                                            </button>
                                        </div>
                                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                            <button style="margin-left: 5px" class="btn btn-primary btn-sm"
                                                    type="button" onclick="drawPolygon.html2canvas(this) ;">
											<span class="btn-label">
												<i class="fa fa-mouse-pointer"></i>
											</span>
                                                地图截取
                                            </button>
                                            <%--<span class="label label-warning">请使用360极速浏览器或者谷歌浏览器或者火狐浏览器请不要使用遨游浏览器和IE浏览器</span>--%>
                                        </div>
                                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">
                                            <button style="margin-left: 5px" class="btn btn-primary btn-sm"
                                                    data-value="2D"
                                                    type="button" onclick="drawPolygon.switch3DMap(this) ;">
											<span class="btn-label">
												<i class="fas fa-recycle"></i>
											</span>
                                                3D或2D切换
                                            </button>
                                        </div>
                                        <div class="col-xs-2  col-sm-2  col-md-2  col-lg-2">

                                            <button style="margin-left: 5px" class="btn btn-primary btn-sm"
                                                    type="button"
                                                    onclick="drawPolygon.handleMouseTool(true);">
											<span class="btn-label">
                                                <%--<i class="far fa-stop-circle"></i>--%>
                                            </span>
                                                关闭绘图
                                            </button>
                                            <button style="margin-left: 5px" class="btn btn-primary btn-sm"
                                                    type="button"
                                                    onclick="drawPolygon.handleMouseTool(false);">
											<span class="btn-label">
                                                <%--<i class="far fa-square"></i>--%>
                                            </span>
                                                开启绘图
                                            </button>
                                        </div>

                                        <div class="col-xs-4  col-sm-4  col-md-4  col-lg-4">
                                            <input type="text" class="form-control input-full" value=""
                                                   placeholder="搜索...." name="mapSearchName" id="tipinput">
                                        </div>
                                    </div>

                                    <div class="row form-group">
                                        <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                            <div class="form-inline x-valid">
                                                <div class="col-xs-12  col-sm-12  col-md-12  col-lg-12">
                                                    <div id="toolMapHandleContainer" style="height:650px;">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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

<script>

    var drawPolygon = {};

    drawPolygon.map = null;
    drawPolygon.mouseTool = null;
    drawPolygon.placeSearch = null;
    drawPolygon.defaultObj = null;
    drawPolygon.box = $("#divPolygonBox");
    drawPolygon.textBox = $("#divTextBox");
    drawPolygon.colorBox = $("#divPolygonColorBox");


    drawPolygon.jsonData = [];
    drawPolygon.overlays = [];

    drawPolygon.handleJquery = function (obj) {
        if (obj instanceof jQuery) {
            return obj;
        } else {
            return $(obj.selector);
        }
    };

    drawPolygon.loadMap = function (callback) {
        //初始化地图对象，加载地图
        drawPolygon.map = new AMap.Map('toolMapHandleContainer', {
            resizeEnable: true,
            rotateEnable: true,
            zoom: 24,
            viewMode: "2D",
            center: [116.403322, 39.920255]
        });
        // 地图 加载完成 load
        drawPolygon.map.on("complete", function () {
            //地图加载完成后要做的事
            drawPolygon.completeEvent();

            //输入提示
            var autoOptions = {
                input: "tipinput"
            };
            var auto = new AMap.Autocomplete(autoOptions);
            //注册监听，当选中某条记录时会触发
            AMap.event.addListener(auto, "select", function (e) {
                drawPolygon.autoCompleteSearch(e.poi.name);
            });

            (function (tt) {
                if (!tt) {
                    return false;
                }
                var item = [];
                try {
                    item = JSON.parse(tt);
                } catch (e) {
                }
                drawPolygon.createOverlay(item);
            }('${formData}'));

            if (callback) {
                callback();
            }

            (function (estateName) {
                if (estateName){
                    drawPolygon.autoCompleteSearch(estateName) ;
                }
            }('${estateName}')) ;

        });
    };

    /**
     * 3d 和 2d 切换
     */
    drawPolygon.switch3DMap = function (_this) {
        var value = $(_this).attr("data-value");
        if (value == '2D') {
            var layers = [
                // 高德默认标准图层
                new AMap.TileLayer.Satellite(),
                // 楼块图层
                new AMap.Buildings({
                    zooms: [16, 18],
                    zIndex: 10,
                    heightFactor: 2//2倍于默认高度，3D下有效
                })
            ];
            drawPolygon.map.setLayers(layers);
            drawPolygon.map.setPitch(75, false, 1);
            $(_this).attr("data-value", "3D");
        } else {
            var overlays = drawPolygon.map.getAllOverlays();
            drawPolygon.map.destroy();
            drawPolygon.loadMap(function () {
                drawPolygon.map.add(overlays);
            });
            $(_this).attr("data-value", "2D");
        }
    };

    drawPolygon.autoCompleteSearch = function (name) {
        var placeSearch = new AMap.PlaceSearch({});
        placeSearch.search(name, function (status, result) {
            if (result.info == 'OK') {
                if (result.poiList.pois.length > 0) {
                    var poi = result.poiList.pois[0];
                    drawPolygon.map.setCenter([poi.location.lng, poi.location.lat]); //设置地图中心点
                }
            }
        })
    };

    /**
     * 鼠标工具开启和关闭
     */
    drawPolygon.handleMouseTool = function (param) {
        if (param) {
            var polygon = drawPolygon.map.getAllOverlays('polygon');
            if (drawPolygon.mouseTool) {
                drawPolygon.mouseTool.close(true);//关闭
                drawPolygon.mouseTool = undefined;
            }
            drawPolygon.showOverlay(polygon);
        } else {
            if (!drawPolygon.mouseTool) {
                drawPolygon.completeEvent();
            }
        }
    };

    drawPolygon.completeEvent = function () {
        drawPolygon.mouseTool = new AMap.MouseTool(drawPolygon.map);
        drawPolygon.mouseTool.polygon({
            fillColor: '#80d8ff',
            borderWeight: 2, // 线条宽度，默认为 1
            strokeColor: '#0000FF'
        });
        //添加事件
        AMap.event.addListener(drawPolygon.mouseTool, 'draw', function (e) {
        });
    };

    /**
     * 创建文本标记
     */
    drawPolygon.createLabelMarker = function (lnglat, item, map) {
        // 创建一个 LabelMarker 实例
        var Text = new AMap.Text({
            position: lnglat,
            anchor: 'bottom-center',//位置
            text: item.name,
            extData: item,
            draggable: true,//是否可以拖动
//            style: {'background-color': 'red'}
        });
        map.add(Text);
        item.id = Text._amap_id;
        Text.setExtData(item);
        Text.on('click', function (e) {
            var box = drawPolygon.handleJquery(drawPolygon.textBox);
            box.modal("show");
            var target = e.target;
            var data = target.getExtData();//target.getText();
            var frm = box.find("form");
            frm.clearAll().initForm(data);
            drawPolygon.tempData = target;
        });
        drawPolygon.updateExtData(item);
    };

    /**
     * 保存标题
     */
    drawPolygon.saveTextData = function () {
        var box = drawPolygon.handleJquery(drawPolygon.textBox);
        var frm = box.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        var target = drawPolygon.tempData;
        target.setText(data.name);
        target.setExtData(data);
        box.modal("hide");
        drawPolygon.tempData = null;
        drawPolygon.updateExtData(data);
    };

    /**
     * 清除覆盖物
     */
    drawPolygon.clearMap = function () {
        drawPolygon.map.clearMap(); // 使用clearMap方法删除所有覆盖物
        drawPolygon.overlays.length = 0;
        drawPolygon.jsonData.length = 0;
    };

    /**
     * 显示覆盖物
     */
    drawPolygon.showOverlay = function (overlays) {
        overlays = drawPolygon.unique(overlays);
        if (overlays.length != 0) {
            $.each(overlays, function (i, overlay) {
                overlay.on('click', function (e) {
                    drawPolygon.eventOverlay(e);
                });
                overlay.on('rightclick', function (e) {
                    drawPolygon.setOverlayFillColor(e);
                });
                drawPolygon.map.add(overlay);
            });
        }
    };

    drawPolygon.selectColor = function () {
        var box = drawPolygon.handleJquery(drawPolygon.colorBox);
        var frm = box.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        box.modal("hide");
        var overlays = drawPolygon.map.getAllOverlays();
        $.each(overlays, function (i, overlay) {
            if (overlay._amap_id == data.pid) {
                var attributeData = overlay.getOptions();
                attributeData.fillColor = data.color;
                overlay.setOptions(attributeData);
//                var path = [];
//                $.each(overlay.getPath(), function (i, item) {
//                    path.push([item.lng, item.lat]);
//                });
//                var obj = {path: path, extData: overlay.getExtData()};
//                obj.fillColor = data.color;
//                drawPolygon.map.remove(overlay);
//                drawPolygon.createOverlay([obj]);
            }
        });
    };

    /**
     * 覆盖物 背景颜色选择填充
     */
    drawPolygon.setOverlayFillColor = function (e) {
        var box = drawPolygon.handleJquery(drawPolygon.colorBox);
        box.modal("show");
        var frm = box.find("form");
        frm.clearAll().initForm({pid: e.target._amap_id});
    };

    /**
     * 创建覆盖物
     */
    drawPolygon.createOverlay = function (data) {
        if (!$.isArray(data)) {
            return false;
        }
        $.each(data, function (i, item) {
            var fillColor = '#80d8ff';
            if (item.fillColor) {
                fillColor = item.fillColor;
            }
            var polygon = new AMap.Polygon({
                path: item.path,
                fillColor: fillColor,
                borderWeight: 2, // 线条宽度，默认为 1
                strokeColor: '#0000FF',
                map: drawPolygon.map
            });
            polygon.on('click', function (e) {
                drawPolygon.eventOverlay(e);
            });
            polygon.on('rightclick', function (e) {
                drawPolygon.setOverlayFillColor(e);
            });
            var title = item.extData.title;
            if ($.isArray(title)) {
                $.each(title, function (j, n) {
                    n.pid = polygon._amap_id;
                    n.id = null;
                    drawPolygon.createLabelMarker(new AMap.LngLat(n.lng, n.lat), n, drawPolygon.map);
                });
            }
        });
    };

    /**
     * 覆盖物事件
     * @param e
     */
    drawPolygon.eventOverlay = function (e) {
        var box = drawPolygon.handleJquery(drawPolygon.box);
        box.modal("show");
        var frm = box.find("form");
        var lnglat = e.lnglat;
        frm.clearAll().initForm({pid: e.target._amap_id, lng: lnglat.lng, lat: lnglat.lat});
        drawPolygon.tempData = lnglat;
    };

    /**
     * 更新数据
     */
    drawPolygon.updateExtData = function (data) {
        var overlays = drawPolygon.map.getAllOverlays();
        $.each(overlays, function (i, overlay) {
            if (overlay._amap_id == data.pid) {
                var item = overlay.getExtData();
                if (item.title) {
                    $.each(item.title, function (j, n) {
                        if (n.id == data.id) {
                            n.name = data.name;
                        }
                    });
                } else {
                    item.title = [data];
                }
                overlay.setExtData(item);
            }
        });
    };

    /**
     * 收集数据
     */
    drawPolygon.getFormData = function () {
        var polygon = drawPolygon.map.getAllOverlays('polygon');
        var jsonData = [];
        if (polygon.length != 0) {
            $.each(polygon, function (i, overlay) {
                var path = [];
                $.each(overlay.getPath(), function (i, item) {
                    path.push([item.lng, item.lat]);
                });
                var attributeData = overlay.getOptions();
                var obj = {path: path, extData: overlay.getExtData(), fillColor: attributeData.fillColor};
                jsonData.push(obj);
            });
        }
        drawPolygon.jsonData = jsonData;
        return jsonData;
    };

    //layui 无法直接调用 drawPolygon.getFormData
    function getFormDrawPolygonData(){
        return drawPolygon.getFormData() ;
    }



    drawPolygon.saveTitleData = function () {
        var box = drawPolygon.handleJquery(drawPolygon.box);
        var frm = box.find("form");
        if (!frm.valid()) {
            return false;
        }
        var data = formSerializeArray(frm);
        box.modal("hide");
        drawPolygon.createLabelMarker(drawPolygon.tempData, data, drawPolygon.map);
        drawPolygon.tempData = null;
    };

    drawPolygon.cancelSaveTitle = function () {
        drawPolygon.tempData = null;
    };

    /**
     * 去除重复
     * @param arr
     * @returns {*}
     */
    drawPolygon.unique = function (arr) {
        for (var i = 0; i < arr.length; i++) {
            for (var j = i + 1; j < arr.length; j++) {
                if (arr[i]._amap_id == arr[j]._amap_id) {
                    //如果第一个等于第二个，splice方法删除第二个
                    arr.splice(j, 1);
                    j--;
                }
            }
        }
        return arr;
    };

    drawPolygon.html2canvas = function () {
        var target = $("#toolMapHandleContainer");
        $(document).ready(function () {
            Loading.progressShow();
            html2canvas(target[0]).then(function (canvas) {
                var dataUrl = canvas.toDataURL();
                var type = 'png';
                var imgData = canvas.toDataURL(type);//canvas转换为图片
                // 加工image data，替换mime type，方便以后唤起浏览器下载
                imgData = imgData.replace(drawPolygon.onFixType(type), 'image/octet-stream');
                drawPolygon.fileDownload(imgData);
                if('${callback}'){
                    var excuteString = 'if (parent && parent.${callback}) {';
                    excuteString += 'parent.${callback}(' + "'" +  canvas.toDataURL('image/jpeg') + "'" + ')' + ';';
                    excuteString += '}';
                    try {
                        eval(excuteString);
                    } catch (e) {
                        console.log(excuteString) ;
                        console.log(e) ;
                    }
                }
                Loading.progressHide();
            });
        });
    };

    /**
     * 唤起浏览器下载
     * @param downloadUrl
     */
    drawPolygon.fileDownload = function (downloadUrl) {
        var date = new Date();
        var title = "图片截取时间:";
        title += date.getFullYear().toString() + "年" + "_";
        title += (date.getMonth() + 1).toString() + "月" + "_";
        title += date.getDate().toString() + "日" + "_";
        title += date.getHours().toString() + "时" + "_";
        title += date.getMinutes().toString() + "分" + "_";
        title += date.getSeconds().toString() + "秒" + "";
        var aLink = document.createElement('a');
        aLink.style.display = 'none';
        aLink.href = downloadUrl;
        aLink.download = title + ".png";
        // 触发点击-然后移除
        document.body.appendChild(aLink);
        aLink.click();
        document.body.removeChild(aLink);
    };

    /**
     * 图片类型转换
     * @param type
     * @returns {string}
     */
    drawPolygon.onFixType = function (type) {
        type = type.toLowerCase().replace(/jpg/i, 'jpeg');
        var r = type.match(/png|jpeg|bmp|gif/)[0];
        return 'image/' + r;
    };


    $(document).ready(function () {
        drawPolygon.loadMap();
        var tt = '[{"path":[[116.40259,39.921129],[116.403571,39.92094],[116.403711,39.92117],[116.403679,39.921293]],"extData":{"title":[{"id":64,"pid":"56","lng":"116.403486","lat":"39.921162","name":"rjadjad"}]}},{"path":[[116.404408,39.921026],[116.404639,39.920347],[116.405728,39.921104]],"extData":{"title":[{"id":67,"pid":"61","lng":"116.404837","lat":"39.920845","name":"sdjsdjdjs"}]}}]';
//        setInterval(function () {
//            var str = JSON.stringify(drawPolygon.getFormData());
//            console.log(str);
////            drawPolygon.createOverlay(JSON.parse(tt));
//        }, 8000);
    });


</script>

<script type="application/javascript">


</script>

</html>
