/**
 * Created by kings on 2019-5-30.
 */

var estateNetwork;
(function () {
    estateNetwork = function () {

    };
    estateNetwork.prototype = {
        config: function () {
            var data = {};
            data.table = "examineEstateNetworkList";
            data.box = "divBoxExamineEstateNetwork";
            data.frm = "frmExamineEstateNetwork";
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.estateNetworkColumn();
            $("#" + estateNetwork.prototype.config().table).bootstrapTable('destroy');
            TableInit(estateNetwork.prototype.config().table, getContextPath() + "/basicEstateNetwork/getBootstrapTableVo", cols, {
                estateId: estateCommon.getEstateId(),
                approval: true
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        }
    };

    //绑定事件
    $('#' + estateNetwork.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        estateNetwork.prototype.loadDataDicList();
    })
})();

var estateParking;
(function () {
    estateParking = function () {

    };
    estateParking.prototype = {
        config: function () {
            var data = {};
            data.table = "estateParkingList";
            data.box = "divBoxEstateParking";
            data.frm = "frmEstateParking";
            data.fileIDName = "house_estateParking";
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.estateParkingColumn();
            $("#" + estateParking.prototype.config().table).bootstrapTable('destroy');
            TableInit(estateParking.prototype.config().table, getContextPath() + "/basicEstateParking/getBootstrapTableVo", cols, {
                estateId: estateCommon.getEstateId(),
                approval: true
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

    //绑定事件
    $('#' + estateParking.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        estateParking.prototype.loadDataDicList();
    })
})();

var matchingEnvironment;
(function () {
    matchingEnvironment = function () {

    };
    matchingEnvironment.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingEnvironmentList";
            data.box = "divBoxMatchingEnvironment";
            data.frm = "frmMatchingEnvironment";
            data.type = "null";//
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingEnvironmentColumn();
            $("#" + matchingEnvironment.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingEnvironment.prototype.config().table, getContextPath() + "/basicMatchingEnvironment/getBootstrapTableVo", cols, {
                estateId: estateCommon.getEstateId(),
                approval: true
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

    //绑定事件
    $('#' + matchingEnvironment.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingEnvironment.prototype.loadDataDicList();
    })
})();

var matchingFinance;
(function () {
    matchingFinance = function () {

    };
    matchingFinance.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingFinanceList";
            data.box = "divBoxMatchingFinance";
            data.frm = "frmMatchingFinance";
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingFinanceColumn();
            $("#" + matchingFinance.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingFinance.prototype.config().table, getContextPath() + "/basicMatchingFinance/getBootstrapTableVo", cols, {
                estateId: estateCommon.getEstateId(),
                approval: true
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
    }

    //绑定事件
    $('#' + matchingFinance.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingFinance.prototype.loadDataDicList();
    })
})();

var matchingEducation;
(function () {
    matchingEducation = function () {

    };
    matchingEducation.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingEducationList";
            data.box = "divBoxMatchingEducation";
            data.frm = "frmMatchingEducation";
            data.type = "null";//
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingEducationColumn();
            $("#" + matchingEducation.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingEducation.prototype.config().table, getContextPath() + "/basicMatchingEducation/getBootstrapTableVo", cols, {
                estateId: estateCommon.getEstateId(),
                approval: true
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

    //绑定事件
    $('#' + matchingEducation.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingEducation.prototype.loadDataDicList();
    })
})();

var matchingRecreation;
(function () {
    matchingRecreation = function () {

    };
    matchingRecreation.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingRecreationList";
            data.box = "divBoxMatchingRecreation";
            data.frm = "frmMatchingRecreation";
            data.type = "matchingRecreation";// 根据 ExamineMatchingLeisurePlaceTypeEnum 配置
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingRecreationColumn();
            $("#" + matchingRecreation.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingRecreation.prototype.config().table, getContextPath() + "/basicMatchingLeisurePlace/getBootstrapTableVo", cols, {
                type: matchingRecreation.prototype.config().type,
                estateId: estateCommon.getEstateId(),
                approval: true
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
    }

    //绑定事件
    $('#' + matchingRecreation.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingRecreation.prototype.loadDataDicList();
    })
})();

var matchingRestaurant;
(function () {
    matchingRestaurant = function () {

    };
    matchingRestaurant.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingRestaurantList";
            data.box = "divBoxMatchingRestaurant";
            data.frm = "frmMatchingRestaurant";
            data.type = "matchingRestaurant";// 根据 ExamineMatchingLeisurePlaceTypeEnum 配置
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingRestaurantColumn();
            $("#" + matchingRestaurant.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingRestaurant.prototype.config().table, getContextPath() + "/basicMatchingLeisurePlace/getBootstrapTableVo", cols, {
                type: matchingRestaurant.prototype.config().type,
                estateId: estateCommon.getEstateId(),
                approval: true
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

    //绑定事件
    $('#' + matchingRestaurant.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingRestaurant.prototype.loadDataDicList();
    })
})();

var matchingMarket;
(function () {
    matchingMarket = function () {

    };
    matchingMarket.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingMarketList";
            data.box = "divBoxMatchingMarket";
            data.frm = "frmMatchingMarket";
            data.type = "matchingMarket";// 根据 ExamineMatchingLeisurePlaceTypeEnum 配置
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingMarketColumn();
            $("#" + matchingMarket.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingMarket.prototype.config().table, getContextPath() + "/basicMatchingLeisurePlace/getBootstrapTableVo", cols, {
                type: matchingMarket.prototype.config().type,
                estateId: estateCommon.getEstateId(),
                approval: true
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

    //绑定事件
    $('#' + matchingMarket.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingMarket.prototype.loadDataDicList();
    })
})();

var matchingMedical;
(function () {
    matchingMedical = function () {

    };
    matchingMedical.prototype = {
        config: function () {
            var data = {};
            data.table = "MatchingMedicalList";
            data.box = "divBoxMatchingMedical";
            data.frm = "frmMatchingMedical";
            data.type = "null";//
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingMedicalColumn();
            $("#" + matchingMedical.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingMedical.prototype.config().table, getContextPath() + "/basicMatchingMedical/getBootstrapTableVo", cols, {
                estateId: estateCommon.getEstateId(),
                approval: true
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
    }

    //绑定事件
    $('#' + matchingMedical.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingMedical.prototype.loadDataDicList();
    })
})();

var matchingTransit;
(function () {
    matchingTransit = function () {

    };
    matchingTransit.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "TransitList";
            data.box = "divBoxTransit";
            data.frm = "frmTransit";
            data.type = "transit";//根据ExamineMatchingTrafficTypeEnum 配置
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingTransitColumn();
            $("#" + matchingTransit.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingTransit.prototype.config().table, getContextPath() + "/basicMatchingTraffic/getBootstrapTableVo", cols, {
                type: matchingTransit.prototype.config().type,
                estateId: estateCommon.getEstateId(),
                approval: true
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
    }

    //绑定事件
    $('#' + matchingTransit.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingTransit.prototype.loadDataDicList();
    })
})();

var matchingTrafficHub;
(function () {
    matchingTrafficHub = function () {

    };
    matchingTrafficHub.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingTrafficHubList";
            data.box = "divBoxMatchingTrafficHub";
            data.frm = "frmMatchingTrafficHub";
            data.type = "trafficHub";//根据ExamineMatchingTrafficTypeEnum 配置
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingTrafficHubColumn();
            $("#" + matchingTrafficHub.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingTrafficHub.prototype.config().table, getContextPath() + "/basicMatchingTraffic/getBootstrapTableVo", cols, {
                type: matchingTrafficHub.prototype.config().type,
                estateId: estateCommon.getEstateId(),
                approval: true
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
    }

    //绑定事件
    $('#' + matchingTrafficHub.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingTrafficHub.prototype.loadDataDicList();
    })
})();

var matchingMetro;
(function () {
    matchingMetro = function () {

    };
    matchingMetro.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingMetroList";
            data.box = "divBoxMatchingMetro";
            data.frm = "frmMatchingMetro";
            data.type = "metro";//根据 ExamineMatchingTrafficTypeEnum 配置
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingMetroColumn();
            $("#" + matchingMetro.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingMetro.prototype.config().table, getContextPath() + "/basicMatchingTraffic/getBootstrapTableVo", cols, {
                type: matchingMetro.prototype.config().type,
                estateId: estateCommon.getEstateId(),
                approval: true
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
    }

    //绑定事件
    $('#' + matchingMetro.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingMetro.prototype.loadDataDicList();
    })
})();

var matchingMainRoad;
(function () {
    matchingMainRoad = function () {

    };
    matchingMainRoad.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MainRoadList";
            data.box = "divBoxMainRoad";
            data.frm = "frmMainRoad";
            data.type = "mainRoad";//根据ExamineMatchingTrafficTypeEnum 配置
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingMainRoadColumn();
            $("#" + matchingMainRoad.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingMainRoad.prototype.config().table, getContextPath() + "/basicMatchingTraffic/getBootstrapTableVo", cols, {
                type: matchingMainRoad.prototype.config().type,
                estateId: estateCommon.getEstateId(),
                approval: true
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
    }

    //绑定事件
    $('#' + matchingMainRoad.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingMainRoad.prototype.loadDataDicList();
    })
})();

var matchingMainConversion;
(function () {
    matchingMainConversion = function () {

    };
    matchingMainConversion.prototype = {
        config: function () {
            var data = {};
            data.table = "MatchingMainConversionList";
            data.box = "divBoxMatchingMainConversion";
            data.frm = "frmMatchingMainConversion";
            data.type = "mainConversion";//根据ExamineMatchingTrafficTypeEnum 配置
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingMainConversionColumn();
            $("#" + matchingMainConversion.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingMainConversion.prototype.config().table, getContextPath() + "/basicMatchingTraffic/getBootstrapTableVo", cols, {
                type: matchingMainConversion.prototype.config().type,
                estateId: estateCommon.getEstateId(),
                approval: true
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
    }

    //绑定事件
    $('#' + matchingMainConversion.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingMainConversion.prototype.loadDataDicList();
    })
})();

var estateSupplyWater;
(function () {
    estateSupplyWater = function () {

    };
    estateSupplyWater.prototype = {
        config: function () {
            var data = {};
            data.table = "EstateSupplyWaterList";
            data.box = "divBoxEstateSupplyWater";
            data.frm = "frmEstateSupplyWater";
            data.type = "estateSupplyWater";//根据 ExamineEstateSupplyEnumType 配置
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.estateSupplyWaterColumn();
            $("#" + estateSupplyWater.prototype.config().table).bootstrapTable('destroy');
            TableInit(estateSupplyWater.prototype.config().table, getContextPath() + "/basicEstateSupply/getBootstrapTableVo", cols, {
                type: estateSupplyWater.prototype.config().type,
                estateId: estateCommon.getEstateId(),
                approval: true
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

    //绑定事件
    $('#' + estateSupplyWater.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        estateSupplyWater.prototype.loadDataDicList();
    })
})();

var estateDrainWater;
(function () {
    estateDrainWater = function () {

    };
    estateDrainWater.prototype = {
        config: function () {
            var data = {};
            data.table = "EstateDrainWaterList";
            data.box = "divBoxEstateDrainWater";
            data.frm = "frmEstateDrainWater";
            data.type = "estateDrainWater";//根据 ExamineEstateSupplyEnumType 配置
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.estateDrainWaterColumn();
            $("#" + estateDrainWater.prototype.config().table).bootstrapTable('destroy');
            TableInit(estateDrainWater.prototype.config().table, getContextPath() + "/basicEstateSupply/getBootstrapTableVo", cols, {
                type: estateDrainWater.prototype.config().type,
                estateId: estateCommon.getEstateId(),
                approval: true
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

    //绑定事件
    $('#' + estateDrainWater.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        estateDrainWater.prototype.loadDataDicList();
    })
})();

var estateSupplyPower;
(function () {
    estateSupplyPower = function () {

    };
    estateSupplyPower.prototype = {
        config: function () {
            var data = {};
            data.table = "EstateSupplyPowerList";
            data.box = "divBoxEstateSupplyPower";
            data.frm = "frmEstateSupplyPower";
            data.type = "estateSupplyPower";//根据 ExamineEstateSupplyEnumType 配置
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.estateSupplyPowerColumn();
            $("#" + estateSupplyPower.prototype.config().table).bootstrapTable('destroy');
            TableInit(estateSupplyPower.prototype.config().table, getContextPath() + "/basicEstateSupply/getBootstrapTableVo", cols, {
                type: estateSupplyPower.prototype.config().type,
                estateId: estateCommon.getEstateId(),
                approval: true
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

    //绑定事件
    $('#' + estateSupplyPower.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        estateSupplyPower.prototype.loadDataDicList();
    })
})();

var estateSupplyHeating;
(function () {
    estateSupplyHeating = function () {
    };
    estateSupplyHeating.prototype = {
        config: function () {
            var data = {};
            data.table = "EstateSupplyHeatingList";
            data.box = "divBoxEstateSupplyHeating";
            data.frm = "frmEstateSupplyHeating";
            data.type = "estateSupplyHeating";//根据 ExamineEstateSupplyEnumType 配置
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.estateSupplyHeatingColumn();
            $("#" + estateSupplyHeating.prototype.config().table).bootstrapTable('destroy');
            TableInit(estateSupplyHeating.prototype.config().table, getContextPath() + "/basicEstateSupply/getBootstrapTableVo", cols, {
                type: estateSupplyHeating.prototype.config().type,
                estateId: estateCommon.getEstateId(),
                approval: true
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

    //绑定事件
    $('#' + estateSupplyHeating.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        estateSupplyHeating.prototype.loadDataDicList();
    })
})();

var estateSupplyGas;
(function () {
    estateSupplyGas = function () {
    };
    estateSupplyGas.prototype = {
        config: function () {
            var data = {};
            data.table = "EstateSupplyGasList";
            data.box = "divBoxEstateSupplyGas";
            data.frm = "frmEstateSupplyGas";
            data.type = "estateSupplyGas";//根据 ExamineEstateSupplyEnumType 配置
            return data;
        },
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        loadDataDicList: function () {
            var cols = commonColumn.estateSupplyGasColumn();
            $("#" + estateSupplyGas.prototype.config().table).bootstrapTable('destroy');
            TableInit(estateSupplyGas.prototype.config().table, getContextPath() + "/basicEstateSupply/getBootstrapTableVo", cols, {
                estateId: estateCommon.getEstateId(),
                type: estateSupplyGas.prototype.config().type,
                approval: true
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

    //绑定事件
    $('#' + estateSupplyGas.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        estateSupplyGas.prototype.loadDataDicList();
    })
})();

var matchingMaterial;
(function () {
    matchingMaterial = function () {

    };
    matchingMaterial.prototype = {
        isNotBlank: function (item) {
            if (item) {
                return true;
            }
            return false;
        },
        config: function () {
            var data = {};
            data.table = "MatchingMaterialList";
            data.box = "divBoxMatchingMaterial";
            data.frm = "frmMatchingMaterial";
            data.type = "null";//
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.matchingMaterialColumn();
            $("#" + matchingMaterial.prototype.config().table).bootstrapTable('destroy');
            TableInit(matchingMaterial.prototype.config().table, getContextPath() + "/basicMatchingMaterial/getBootstrapTableVo", cols, {
                estateId: estateCommon.getEstateId(),
                approval: true
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
    }

    //绑定事件
    $('#' + matchingMaterial.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        matchingMaterial.prototype.loadDataDicList();
    })
})();

var estateInvestigation;
(function () {
    estateInvestigation = function () {
    };
    estateInvestigation.prototype = {
        config: function () {
            var data = {};
            data.table = "EstateInvestigationList";
            data.box = "divBoxEstateInvestigation";
            data.frm = "frmEstateInvestigation";
            return data;
        },
        loadDataDicList: function () {
            var cols = commonColumn.estateInvestigation();
            $("#" + estateInvestigation.prototype.config().table).bootstrapTable('destroy');
            TableInit(estateInvestigation.prototype.config().table, getContextPath() + "/basicEstateInvestigation/getEstateInvestigationListByEstateId", cols, {
                estateId: estateCommon.getEstateId()
            }, {
                showColumns: false,
                showRefresh: false,
                search: false,
                onLoadSuccess: function () {
                    $('.tooltips').tooltip();
                }
            });
        },
        isNotNull: function (item) {
            if (item) {
                return true;
            }
            return false;
        }
    }

    //绑定事件
    $('#' + estateInvestigation.prototype.config().table).closest('.full-height').find('.card-header').bind('click', function () {
        estateInvestigation.prototype.loadDataDicList();
    })

})();