/**
 * 获取周边的配套设施
 * 需要传入搜索类型,搜索中心的经纬度以及搜索半径
 */
(function ($) {
    document.write('<script type="text/javascript" src="//webapi.amap.com/maps?v=1.4.6&key=ac9fb0371e0405ef74cb1ca003fd0eef&plugin=AMap.ToolBar&plugin=AMap.PlaceSearch&plugin=AMap.Transfer"></script>');
    document.write('<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>');
    document.write('<div id="containerSearchMap" style="display: none;" tabindex="0"></div>');
    document.write("<link rel='stylesheet' type='text/css' href='https://a.amap.com/jsapi_demos/static/demo-center/css/prety-json.css'> </link>");
    document.write("<script type='text/javascript' src='https://a.amap.com/jsapi_demos/static/demo-center/js/underscore-min.js'> </script>");
    document.write("<script type='text/javascript' src='https://a.amap.com/jsapi_demos/static/demo-center/js/backbone-min.js'> </script>");
    document.write("<script type='text/javascript' src='https://a.amap.com/jsapi_demos/static/demo-center/js/prety-json.js'> </script>");
    var mapSearch = null;

    var searchMap = {};

    /**
     * 空字符串检测
     * @param item
     * @returns {boolean}
     */
    searchMap.isNotBlank = function (item) {
        if (item) {
            return true;
        }
        return false;
    };

    searchMap.isNotBlankObject = function (obj) {
        for (var key in obj) {
            return true;
        }
        return false
    };

    searchMap.config = {
        id: "containerSearchMap",
        classification: {
            museum: {
                name: "博物馆",
                poiId: null,
                typeName: "博物馆",
                describe: "poiId来源为高德地图,不能够随便更改,typeName为标准行业名称"
            },
            catering: {
                name: "餐饮",
                poiId: null,
                typeName: "餐饮服务",
                describe: "poiId来源为高德地图,不能够随便更改,typeName为标准行业名称"
            }
        }
    };

    /**
     * 地图创建
     * @param lng
     * @param lat
     * @param zoom
     */
    searchMap.createMap = function (lng, lat, zoom) {
        mapSearch = new AMap.Map(searchMap.config.id, {
            resizeEnable: true, //是否监控地图容器尺寸变化
            center: [lng, lat], //初始地图中心点
            zoom: zoom,// //初始地图级别
            showIndoorMap: false,//关闭室内地图
            lang: "zh_cn" //可选值：en，zh_en, zh_cn
        });
    };

    /**
     * 获取搜索信息 (关键字搜索)
     * @param city 搜索区域的城市名称
     * @param word 关键字 多个('新华之星 | 五冶大厦') 单个('五冶大厦')
     * @param callback 回调函数
     */
    searchMap.keywordSearch = function (city, word, callback) {
        var options = {
            city: searchMap.isNotBlank(city) ? city : "四川",
            citylimit: true,  //是否强制限制在设置的城市内搜索
            pageSize: 10,// 单页显示结果条数
            map: mapSearch // 展现结果的地图实例
        };
        AMap.plugin('AMap.PlaceSearch', function () {
            var placeSearch = new AMap.PlaceSearch(options);
            placeSearch.search(word, function (status, result) {
                // 搜索成功时，result即是对应的匹配数据
                var node = new PrettyJSON.view.Node({
                    data: result
                });
                callback(result);
            })
        })
    };


    /**
     * 行业分类检索
     * @param word 关键字
     * @param type 行业名称 多个('餐饮服务 | 博物馆') 单个('博物馆')
     * @param city 检索行业所在的城市
     * @param distance 距离
     * @param position 位置 (经纬度)
     * @param callback 回调函数
     */
    searchMap.classificationSearch = function (word, type, city, distance, position, callback) {
        /*高德地图api函数
        * searchNearBy(keyword:String,center:LngLat,radius:Number,
        * callback:function(status:String,result:info/SearchResult))
        **/
        var options = {
            type: type,   // 类别
            pageSize: 20, // 单页显示结果条数 (默认为10)
            pageIndex: 1, // 页码
            city: searchMap.isNotBlank(city) ? city : "四川", // 兴趣点城市
            citylimit: true,  //是否强制限制在设置的城市内搜索
            map: mapSearch // 展现结果的地图实例
        };
        console.log(options);
        try {
            AMap.service(["AMap.PlaceSearch"], function () {
                //构造地点查询类
                var placeSearch = new AMap.PlaceSearch(options);
                placeSearch.searchNearBy(word, [position.lng, position.lat], searchMap.isNotBlank(distance) ? distance : "200", function (status, result) {
                    callback(result);
                    console.log(status) ;
                });
            });
        } catch (e) {
            console.log(e);
        }
    };

    /**
     *
     * @param name example(公交站,地铁站)
     * @param distance
     * @param position
     * @param callback
     */
    searchMap.transferSearch = function (name, distance, position, callback) {
        console.log("transferSearch") ;
        if (this.isNotBlank(name) && this.isNotBlank(distance) && this.isNotBlankObject(position)) {
            this.classificationSearch(name, '交通设施服务', null, distance, position, callback);
        } else {
            try {
                Alert("参数必须传入!");
            } catch (e) {
            }
            return false;
        }
    };

    /**
     * 其它查询
     * @param name
     * @param distance
     * @param position
     * @param callback
     * @returns {boolean}
     */
    searchMap.otherSearch = function (name, distance, position, callback) {
        if (this.isNotBlank(name) && this.isNotBlank(distance) && this.isNotBlankObject(position)) {
            this.classificationSearch(name, '', null, distance, position, callback);
        } else {
            try {
                Alert("参数必须传入!");
            } catch (e) {
            }
            return false;
        }
    };

    /**
     * 四川行业检索
     * @param type 行业名称 行业名称 多个('餐饮服务 | 博物馆') 单个('博物馆')
     * @param distance 距离
     * @param position 位置 (经纬度)
     * @param callback 回调函数
     */
    searchMap.localUseTypeSearch = function (type, distance, position, callback) {
        if (this.isNotBlank(type) && this.isNotBlank(distance) && this.isNotBlankObject(position)) {
            this.classificationSearch('', type, null, distance, position, callback);
        } else {
            try {
                Alert("参数必须传入!");
            } catch (e) {
            }
            return false;
        }
    };

    /**
     *
     * @param poiId example:中国国家博物馆对应的POI ID(B000A83U0P)
     * @param callback
     */
    searchMap.getPoiIdDetails = function (poiId, callback) {
        AMap.plugin('AMap.PlaceSearch', function () {
            var placeSearch = new AMap.PlaceSearch();
            placeSearch.getDetails(poiId, function (status, result) {
                // 查询成功时，result即为对应的POI详情
                callback(result);
            })
        })
    };

    /**
     * 测试方法
     */
    searchMap.test = function () {
        this.createMap(104.086965, 30.587458, 17);


        $.each(['餐饮服务', '交通设施服务', '停车场', '医院', '银行', '学校'], function (i, name) {
            searchMap.localUseTypeSearch(name, 3000, {lng: 104.086965, lat: 30.587458}, function (result) {
                // console.log(result);
            });
        });

        searchMap.otherSearch('金融机构', 8000, {lng: 104.086965, lat: 30.587458}, function (result) {
            console.log(result);
        });

        this.transferSearch('主干道', 1000, {lng: 104.086965, lat: 30.587458}, function (result) {
            // console.log(result);
        })
    };
    window.onload = function (ev) { assessSearchMap.createMap(104.086965, 30.587458, 17); };
    window.assessSearchMap = searchMap;
})(jQuery);



