/**
 * Created by Calvin on 2017/7/18.
 */
function TableInit(table, url, cols, data, btParams, isCheck, isOrder) {
    var tableObj;
    if (table instanceof jQuery) /**我只想传一个table对象，不想传id(id太麻烦，一改到处需要改)**/{
        tableObj = table;
    } else {
        tableObj = $("#" + table);
    }
    data = (data === undefined) ? {} : data;
    if (cols) {
        if (isOrder != false) {
            cols.unshift({
                field: 'Number',
                title: '序号',
                width: '50px',
                formatter: function (value, row, index) {
                    var page = tableObj.bootstrapTable("getPage");
                    return page.pageSize * (page.pageNumber - 1) + index + 1;
                    // return index + 1;
                }
            });
        }

        if (isCheck) {
            cols.unshift({
                field: 'ckeckbox',
                checkbox: true
            });
        }
    }
    var defaluts = {
        url: url,         //请求后台的URL（*）
        method: 'get',                      //请求方式（*）
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                       //初始化加载第一页，默认第一页
        showColumns: false,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        search: true,
        uniqueId: "id",
        contentType: "application/json;charset=UTF-8",
        toolbar: '#toolbar', //工具按钮用哪个容器
        searchOnEnterKey: true, //回车再出发搜索
        icons: {
            refresh: 'glyphicon-refresh clip-search-3'
        },
        queryParams: function (params) {
            data["limit"] = params.limit;
            data["offset"] = (params.offset + params.limit) / params.limit;
            data["search"] = params.search;
            return data;
        },//传递参数（*）
        columns: [cols]
    };

    defaluts = $.extend({}, defaluts, btParams);
    tableObj.bootstrapTable(defaluts);


}

function TableReload(table) {
    if (table instanceof jQuery) /**我只想传一个table对象，不想传id(id太麻烦，一改到处需要改)**/{
        $(table).bootstrapTable('refresh');
    } else {
        $("#" + table).bootstrapTable('refresh');
    }

}
function TableReload(table, url, data, fn) {
    var opt = {
        url: url,
        pageNumber: 1,
        query: data
    };

    if (table instanceof jQuery) /**我只想传一个table对象，不想传id(id太麻烦，一改到处需要改)**/{
        $(table).bootstrapTable('refresh', opt);
    } else {
        $("#" + table).bootstrapTable('refresh', opt);
    }
}

function getTableDataByIndex(tableId, index) {
    var row = $("#" + tableId).bootstrapTable('getData')[index];
    return row;
}

/**
 * 客户端分页
 * @param table table的domID 或者 table的jQuery对象
 * @param cols 列
 * @param rows 数据Array[]
 * @param config 配置
 * @param isCheck 复选框boolean
 * @constructor
 */
function TableClient(table, cols, rows, config, isCheck) {
    config.showNumber = config.showNumber || true;
    rows = (rows === undefined) ? [] : rows;

    if (cols) {
        if (config.showNumber) {
            cols.unshift({
                field: 'Number',
                title: '序号',
                formatter: function (value, row, index) {
                    return index + 1;
                }
            });
        }

        if (isCheck) {
            cols.unshift({
                field: 'ckeckbox',
                checkbox: true
            });
        }
    }
    var defaluts = {
        columns: [cols],
        pageSize: 5, //可供选择的每页的行数（*）
        pageList: [5, 10, 25, 50, 100],
        pagination: true,
        sidePagination: "client",
        search: true,
        data: rows,
        formatLoadingMessage: function () {
            return ""; //客户端分页不显示提示
        }
    };
    defaluts = $.extend({}, defaluts, config);

    if (table instanceof jQuery) {
        table.bootstrapTable(defaluts);
    } else {
        $("#" + table).bootstrapTable(defaluts);
    }
}

