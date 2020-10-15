package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.aspose.words.ControlChar;
import com.copower.pmcc.assess.common.enums.SchemeSupportTypeEnum;
import com.copower.pmcc.assess.common.enums.report.ReportFieldEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataReportAnalysisDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.data.SurveyDamageDto;
import com.copower.pmcc.assess.dto.input.project.generate.EstateLiquidityAnalysisDto;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyJudgeObjectGroupDto;
import com.copower.pmcc.assess.dto.output.data.DataReportAnalysisVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.assess.service.project.generate.GenerateHouseEntityService;
import com.copower.pmcc.assess.service.project.generate.GenerateLandEntityService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.scheme.SchemeLiquidationAnalysisService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInfoService;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetRightGroupService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service(value = "dataReportAnalysisService")
public class DataReportAnalysisService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataReportAnalysisDao dataReportAnalysisDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private DataReportTemplateItemService dataReportTemplateItemService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private SchemeLiquidationAnalysisService schemeLiquidationAnalysisService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private GenerateLandEntityService generateLandEntityService;
    @Autowired
    private GenerateHouseEntityService generateHouseEntityService;
    @Autowired
    private DataBestUseDescriptionService dataBestUseDescriptionService;
    @Autowired
    private SurveyAssetRightGroupService surveyAssetRightGroupService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private SurveyAssetInfoService surveyAssetInfoService;

    /**
     * 保存数据
     *
     * @param dataReportAnalysis
     */
    public void saveAndUpdate(DataReportAnalysis dataReportAnalysis) {
        if (dataReportAnalysis.getId() != null && dataReportAnalysis.getId() > 0) {
            dataReportAnalysisDao.updateReportAnalysis(dataReportAnalysis);
        } else {
            BaseDataDic cacheDataDicByFieldName = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_LIQUIDITY);
            dataReportAnalysis.setReportAnalysisType(cacheDataDicByFieldName.getId());
            dataReportAnalysis.setCreator(commonService.thisUserAccount());
            dataReportAnalysisDao.addReportAnalysis(dataReportAnalysis);
            //修改子模板
            dataReportTemplateItemService.templateItemToSetMasterId(dataReportAnalysis.getId(), SchemeSupportTypeEnum.REPORT_ANALYSIS_CATEGORY_LIQUIDITY.getKey());
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean removeReportAnalysis(Integer id) {
        return dataReportAnalysisDao.removeReportAnalysis(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public DataReportAnalysis getReportAnalysis(Integer id) {
        return dataReportAnalysisDao.getReportAnalysis(id);
    }


    /**
     * 获取数据列表
     *
     * @param name
     * @return
     */
    public BootstrapTableVo getReportAnalysisList(String name, Integer type, Integer reportAnalysisType) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataReportAnalysis> hypothesisList = dataReportAnalysisDao.getReportAnalysisList(name, type, reportAnalysisType);
        List<DataReportAnalysisVo> vos = LangUtils.transform(hypothesisList, p -> getReportAnalysisVo(p));
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<DataReportAnalysisVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    /**
     * 根据取数据列表
     *
     * @param reportAnalysisType
     * @return
     */
    public List<DataReportAnalysis> getReportAnalysisList(Integer reportAnalysisType) {
        return dataReportAnalysisDao.getReportAnalysisList(reportAnalysisType);
    }


    public DataReportAnalysisVo getReportAnalysisVo(DataReportAnalysis reportAnalysis) {
        if (reportAnalysis == null) return null;
        DataReportAnalysisVo vo = new DataReportAnalysisVo();
        BeanUtils.copyProperties(reportAnalysis, vo);
        vo.setReportAnalysisTypeName(baseDataDicService.getNameById(reportAnalysis.getReportAnalysisType()));
        List<BaseDataDic> purposeDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        if (StringUtils.isNotBlank(reportAnalysis.getEntrustmentPurpose())) {
            vo.setEntrustmentPurposeName(baseDataDicService.getDataDicName(purposeDicList, reportAnalysis.getEntrustmentPurpose()));
        }
        if (reportAnalysis.getMarketBackgroundType() != null) {
            vo.setMarketBackgroundTypeName(baseDataDicService.getNameById(reportAnalysis.getMarketBackgroundType()));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(reportAnalysis.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(reportAnalysis.getProvince()));//省
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(reportAnalysis.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(reportAnalysis.getCity()));//市或者县
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(reportAnalysis.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(reportAnalysis.getDistrict()));//县
        }

        return vo;
    }

    /**
     * 获取报告分析数据
     *
     * @param type
     * @param entrustmentPurpose
     * @return
     */
    public List<DataReportAnalysis> getDataReportAnalysisList(String province, String city, String district, Integer type, Integer entrustmentPurpose) {
        String entrustmentPurposeString = String.format(",%s,", entrustmentPurpose);
        return dataReportAnalysisDao.getReportAnalysisList(province, city, district, type, entrustmentPurposeString);
    }

    /**
     * 一个临时方法 里面数据写死了的用作测试  待配置在报告配置里面
     *
     * @param projectInfo
     * @param areaGroupId
     * @param reportFieldEnum
     * @return
     * @throws Exception
     */
    public String getReportNewLiquidity(ProjectInfo projectInfo, Integer areaGroupId, ReportFieldEnum reportFieldEnum) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        switch (reportFieldEnum) {
            case ENUM_CITY_RESOURCE_STATUS: {
                stringBuilder.append("成都位于四川盆地西部，别称“蓉城”、“锦城”，简称“蓉”、“成”。成都物产丰富，自古享有“天府之国”美誉。成都自古为西南重镇，三国时为蜀汉国都，五十国时为前蜀、后蜀都城，是我国第一批历史文化名城。\n" +
                        "　　【土地资源】成都市土地资源有以下特点，一是土地类型多样。按地貌类型可分为平原、丘陵和山地;按土壤类型可分为水稻土、潮土、紫色土、黄壤、黄棕壤等11类;按土地利用现状类型可分为耕地、园林地、牧草地等8类。二是平原面积比重大，达4971.4平方公里，占全市土地总面积的40.1%，远远高于全国占12%和四川省占2.54%的水平;丘陵面积占27.6%，山地面积占32.3%。三是土地垦殖指数高。土地肥沃，土层深厚，气候温和，灌溉方便，可利用面积的比重可达94.2%，全市平均土地垦殖指数达38.22%，其中平原地区高达60%以上，远远高于全国10.4%和四川省11.5%的水平。\n" +
                        "　　【水资源】成都市降水丰沛，年均水资源总量为304.72亿立方米，其中地下水31.58亿立方米，过境水184.17亿立方米，基本上能满足成都市人民生活和生产建设用水的需要。主要特点:一是河网密度大。成都市有岷江、沱江等12条干流及几十条支流，河流纵横，沟渠交错，河网密度高达1.22公里/平方公里;加上驰名中外的都江堰水利工程，库、塘、堰、渠星罗棋布。2004年有效灌溉面积达34.5万公顷; 全市水能资源理论蕴藏量为161.5万千瓦。二是水质优良。成都地处长江流域上游，河水主要由大气降水、地下潜流和融雪组成，在流入成都平原之前，河道主要在高山峡谷之间，受人为污染极小，因而水质格外优良，绝大部分指标都符合国家地面水二级标准的要求。\n" +
                        "　　【生物资源】成都市地处亚热带湿润地区，地形地貌复杂，自然生态环境多样，生物资源十分丰富。据初步统计，仅动、植物资源就有11纲、200科、764属、3000余种。其中，种子植物2682种，特有和珍稀植物有银杏、珙桐、黄心树、香果树等;主要脊椎动物237种，国家重点保护的珍稀动物有大熊猫、小熊猫、金丝猴、牛羚等;中药材860多种，川芎、川郁金、乌梅、黄连等蜚声中外。\n" +
                        "　　【矿产资源】成都市矿产资源较为丰富。一是种类繁多，目前已探明的有铁、钛、钒、铜、铅、锌、铝、金、银、锶、稀土等金属矿产以及钙芒销、蛇纹石、石膏、方解石、石灰石、大理石、煤、天然气等非金属矿产资源60多种。二是分布相对集中。全市有大小矿产地400余处，多属矿产资源分布相对集中。煤炭探明储量1.46亿吨，主要集中在西部边沿山区的彭州市、都江堰市、崇州市和大邑县;天然气探明储量16.77亿立方米，远景储量为42.21亿立方米，主要集中于蒲江、邛崃、大邑、都江堰和金堂一带;钙芒硝储量全国第一，高达98.62亿吨，主要集中于新津县和双流县;多种金属矿产资源则相对集中于彭州市。三是共生矿多。\n" +
                        "　　【旅游资源】成都市名胜古迹蜚声中外，加上自然风光绮丽多姿，因而旅游资源得天独厚，并具有鲜明的成都特色。一是人文景观多。全市现有人文景观172处，具有类型多、规模大、分布广、价值高的特点。全市19个区(市)县，都有自己特有的人文景观。其中，尤以二王庙、文君井、武侯祠、杜甫草堂、文殊院、宝光寺、王建墓、蜀僖王陵以及古蜀文化——金沙遗址等最具特色;观音寺的壁画、塑像和花置寺的摩岩造像等也有很高的艺术观赏价值;举世闻名的都江堰水利工程，更是具有极高的科学研究价值。二是自然景观全。成都地形地貌复杂多样，山景、洞景、水景、生景、气景俱全。其中山景具有高、险、奇、秀、幽的特色，如有“天下幽”的青城山、雄奇多姿的九峰山、奇峰挺拔的雾中山、景色秀美的玉垒山等;水景中有汹涌湍急的溪流、清澈明亮的水潭、飞珠溅玉的瀑布、秀美如画的湖泊、千姿百态的泉眼等等。生景中，有少见的桂花林、箭竹林、杜鹃林等植物群落和大熊猫、小熊猫、蝴蝶群等珍稀动物。丰富多彩的成都气景中，有壮观的日出、多变的云海、神奇的佛光、奇特的“神灯”和玄幂的“阴阳界”等等。三是旅游资源分布相对集中。现已形成以成都市区为核心的、组合不同、风格各异的都江堰、青城山、宝光寺等8个国家、省、市级风景片区和西岭雪山国家级风景名胜区、龙池国家级森林公园、龙门山国家级地质公园和白水河国家自然保护区等。四是旅游地理位置十分优越。成都正处在由剑门蜀道、九寨沟、成都、峨眉山、长江三峡等旅游胜地组成的四川旅游环和由北京、西安、成都、昆明、桂林、广州等旅游中心组成的全国旅游环的联结点上，还是内地前往西藏的主要通道。");
                break;
            }
            case ENUM_REAL_ESTATE_SYSTEM: {
                stringBuilder.append("成都是中国国务院确定的西南科技、商贸、金融中心和交通、通信枢纽，成都科技实力雄厚。成都的房地产事业发展迅速，如今，房地产业已成为推动经济增长的重要产业之一，成为拉动投资与消费需求的重要动力，启动民间投资的重要方式和带动就业增长的重要途径。");
                break;
            }
            case ENUM_land_MARKET_CONDITION: {
                stringBuilder.append("2018年1-11月份，全国固定资产投资（不含农户）累计增长5.9%，同比下降1.3%，增速比1-10月份提高0.2个百分点；国有及国有控股固定资产投资额累计增长2.3%，同比下降8.7%，增速比1-10月份提高0.5个百分点；房地产开发投资额累计增长9.7%，同比增长2.2%，增速与1-10月份持平；第一产业固定资产投资完成额累计增长12.2%，同比增长0.8%，增速比1-10月份回落1.2个百分点；第二产业固定资产投资完成额累计增长6.2%，同比增长3.6%，增速比1-10月份提高0.4个百分点；第三产业固定资产投资完成额累计增长5.6%，同比下降4.5%，增速比1-10月份提高0.2个百分点。\n" +
                        "\n" +
                        "从按行业分民间固定资产投资情况看，农林牧渔业民间固定资产投资累计增长11.4%，增速比1-10月份回落0.7个百分点；农业固定资产投资额累计增长12.5%，增速比1-10月份提高1.8个百分点；林业固定资产投资额累计增长8.6%，内资企业投资同比增长3.7%，增速比1-10月份回落0.4个百分点；畜牧业固定资产投资额累计增长11.6%，增速比1-10月份回落0.6个百分点；渔业固定资产投资额累计增长19.7%，增速比1-10月份提高1.2个百分点；农、林、牧、渔服务业固定资产投资额累计增长6.6%，增速比1-10月份提高2.4个百分点");
                break;
            }
            case ENUM_HOUSE_MARKET_CONDITION: {
                stringBuilder.append("对于房地产的概念,应该从两个方面来理解:房地产既是一种客观存在的物质形态,同时也是一项法律权利。作为一种客观存在的物质形态,房地产是指房产和地产的总称,包括土地和土地上永久建筑物及其所衍生的权利。房产是指建筑在土地上的各种房屋,包括住宅、厂房、仓库和商业、服务、文化、教育、卫生、体育以及办公用房等。地产是指土地及其上下一定的空间,包括地下的各种基础设施、地面道路等。房地产由于其自己的特点即位置的固定性和不可移动性,在经济学上又被称为不动产。可以有三种存在形态:即土地、建筑物、房地合一。在房地产拍卖中,其拍卖标的也可以有三种存在形态,即土地(或土地使用权)、建筑物和房地合一状态下的物质实体及其权益。随着个人财产所有权的发展,房地产已经成为商业交易的主要组成部分。法律意义上的房地产本质是一种财产权利,这种财产权利是指寓含于房地产实体中的各种经济利益以及由此而形成的各种权利,如所有权、使用权、抵押权、典当权、租赁权等。");
                break;
            }
            case ENUM_INDUSTRIAL_POLICY: {
                stringBuilder.append("“产业新政50条”包括总体目标、创新要素供给、降低企业成本、培育产业生态4个板块、13个部分、50条措施。总体目标包括四个方面：一是强体系，初步形成具有国际竞争力和区域带动力的现代产业体系。二是优生态，初步形成企业集聚、产业集群、要素集约、技术集成、服务集中的产业生态体系。三是重落实，不断完善集成整合、协调配套、精准高效的产业发展政策体系，保障政策红利充分释放。四是提能级，不断提高战略性新兴产业、先进制造业和生产性服务业占比，让新经济成为增长新动能，促成一批国内领先、具有国际影响力的龙头企业和创新型企业持续涌现，使全市产业影响力、带动力和竞争力不断提升。为实现上述目标，成都将从以下三个方面发力：\n" +
                        "\n" +
                        "创新要素供给 着力增强产业核心要素聚集能力\n" +
                        "\n" +
                        "在政策制定中，成都始终秉持改革创新的理念，坚持人才是第一资源、科技是第一生产力，将人才、技术、土地、资本、数据等促进产业发展的核心要素作为重中之重。\n" +
                        "\n" +
                        "关键词1：最高1亿元综合资助\n" +
                        "\n" +
                        "高端人才是城市发展的引领力量。成都将强化高端人才激励，对国际顶尖人才（团队）来蓉创新创业，给予最高1亿元综合资助。对市域实体经济和新经济领域年收入50万元以上的人才，按其贡献给予不超过其年度个人收入5%的奖励。\n" +
                        "\n" +
                        "关键词2：青年人才驿站\n" +
                        "\n" +
                        "青年大学生是城市发展的新生力量。成都将以更开放、更包容的姿态，鼓励青年大学生扎根成都，大力实施“蓉漂”计划，改革人才落户制度，进一步放宽落户限制，提高落户便利性，实行全日制大学本科及以上青年人才凭毕业证落户制度。对外地本科及以上应届毕业生来蓉应聘提供7天以内免费入住的青年人才驿站。把解决“蓉漂”安居问题摆在更加突出位置，加大人才公寓和租赁住房建设力度，对急需紧缺人才提供人才公寓租赁住房保障，确保各类人才“住得上房、安得了家”。");
                break;
            }
            case ENUM_TAX_POLICY: {
                stringBuilder.append("公司企业每年纳税***大的问题就在于增值税跟所得税，每年纳完税利润所剩无几，公司每年的税负压力都很大。成都税收返还_各地税收2020年新政策。\n" +
                        "一般纳税人其增值税以行业来划分，服务行业增值税为6%，运输建设行业增值税为9%，贸易行业增值税***高13%。公司在运营期间多多少少会缺乏或无法取得进项，公司缺乏进项就不能拿来抵扣增值税那就会造成公司缴纳的增值税过高。\n" +
                        "公司经营所得税又根据其利润来划分，利润在100万以下公司所得税为5%，利润在100万--300万公司所得税为10%，利润超过三百万公司所得税为25%。\n" +
                        "一般纳税人除了缴纳增值税所得税，有的还有20%股东分红税。一家公司如果在不做任何税收筹划的情况下，每年缴纳完税利润真的剩不了多少，每年的税负压力都是很大的。");
                break;
            }
            case ENUM_FINANCIAL_POLICY: {
                stringBuilder.append("一、设立目标规模1000亿元的成都发展基金，通过子基金群力争带动5000亿元社会资\n" +
                        "\n" +
                        "本，投资支柱产业、优势产业和未来产业。\n" +
                        "\n" +
                        "　　二、支持内外资银行、保险、投行、证券等金融机构在蓉设立地区总部；支持金融机构在蓉设立结算中心、数据中心、资产管理中心、信用卡中心等功能性总部；对新设立或迁入的境\n" +
                        "\n" +
                        "内外法人金融机构总部，给予最高4000万元的落户、办公用房（含租赁）和项目补助。\n" +
                        "\n" +
                        "　　三、争取金融租赁、互联网金融、互联网保险、汽车金融、消费金融等新型金融牌照；支持大型电子商务企业在蓉设立融资担保等新型金融企业。\n" +
                        "\n" +
                        "　　四、扩大中小微企业贷款风险补偿资金池和民营企业应急周转金规模，放宽资金使用条件和范围，促进中小微企业融资规模实现倍增。\n" +
                        "\n" +
                        "　　五、设立市级再担保公司和小额再贷款公司，为中小微企业融资增信。对银行机构新增客户首贷、重点产业固定资产贷款、支农支小贷款及发行专项金融债给予奖励。\n" +
                        "\n" +
                        "　　六、鼓励银行开展投贷联动、供应链金融、并购贷款、银保互动、知识产权等无形资产质押贷款等融资服务创新；鼓励符合条件企业发起设立民营银行。支持保险公司开展信用保证保险、责任保险等新业务，推进首台（套）重大技术装备保险试点。\n" +
                        "\n" +
                        "　　七、争取境内外主要证券交易所在蓉设立西部服务中心，对实现上市融资企业给予最高\n" +
                        "\n" +
                        "500万元奖励。\n" +
                        "\n" +
                        "　　八、优化天府（四川）联合股权交易中心服务企业功能，建立对接主板、创业板和新三板市场拟上市企业“蓄水池”。\n" +
                        "\n" +
                        "　　九、推动企业在银行间市场和证券交易所市场发债融资，给予最高100万元奖励。\n" +
                        "\n" +
                        "　　十、落实西部大开发税收优惠政策，对符合条件的企业减按15%缴纳企业所得税。\n" +
                        "\n" +
                        "　　十一、建立以“一带一路”沿线区域为重点的跨境金融服务中心。深化跨国总部外汇资金集中运营管理和双向人民币资金池创新，争取外债切块管理改革试点。");
                break;
            }
            case ENUM_URBAN_PLANNING_DEVELOPMENT_GOALS: {
                stringBuilder.append("<div class=\"content\">\n" +
                        "<p>区城市建设工作思路和发展目标计划</p>\n" +
                        "<p>　　<strong>一、指导思想</strong></p><p style=\"text-align: center;\"><img src=\"//www.ruiwen.com/pic/allimg/copyright/img/d1a7cfb05_2.jpg\" alt=\"区城市建设工作思路和发展目标计划\"></p><p>　　以“三个代表”重要思想为指导，贯彻落实科学发展观，坚持以人为本和可持续发展理念，以与时俱进的思想，奋发有为的精神，真抓实干的作风，加快城市建设步伐，加大城市管理力度，使河西城市建设再上新台阶。</p><p>　　<strong>二、工作思路</strong></p><p>　　紧紧围绕改革、发展、稳定大局，依托中心城市，有序推进城市化；逐步将城市建设的重点从扩大规模、拉开框架、整治市容、美化环境，向强化基储完善功能、挖掘内涵、塑造特色转移；稳步推进城市重大基础设施建设，统筹规划新城、新区开发；进一步改善城市形象，提升城市功能；强化城乡规划对经济发展和城乡建设的指导调节作用，加大规划决策透明度；维护公平、规范的市场秩序，加强行业监管，为我区建筑业长期快速发展提供服务保障；加强法制建设，不断提高建设行政效率和管理水平，保持建设事业持续稳定健康发展。</p><p>　　<strong>三、发展目标</strong></p><p>　　1、拉大城市框架，提高城市化水平。到XX年,上顿渡城区建成区面积达到12平方公里，城镇人口12万左右，人均城市建设用地100平方米。我区城市化水平由年末的32.5%提高到42%。</p><p>　　2、按照河西分区规划，积极认真做好城市规划建设工作。在分区规划基础上编制控制性详规，努力塑造街区景观，突出河西文化特色。到XX年,全面完成上顿渡旧城改造；完成河西新城、行政新区和市民公园建设，并逐步完善其功能；完成梦湖建设和周边设施建设；高标准规划建设河西温泉风景名胜区，完成核心区域建设达3平方公里，使之成为全省领先的休闲度假场所；规划建设好河西“白露生态园”，成为市民休闲观光的好去处。</p><p>　　3、加快城市基础设施建设步伐。全面增强道路、排水、供水、绿化、路灯等基础设施的综合服务功能；完成城区燃气工程建设,确保市民正常供气;加强供水管理和建设，完成河西区3万立方米/日给水工程建设，使城市供水普及率达到95%以上。大力建设城区道路，城区道路全部硬化。加大老城区的路灯改造，新城区路灯的投入，使我区路灯亮灯率达到99%以上。</p><p>　　4、以建设生态园林城市为目标，加大城市绿化、美化力度。按照点、线、面、网相结合的手法，抓紧抓好城市街道和单位、居民区绿化，加强城市街路、广尝公园、小区和城郊绿化，积极引进天竺桂特色树种，加大绿化科技投入，使城市绿化格局逐步形成乔、灌、花、草相结的绿化体系。增加城市公共绿地面积15万平方米，使城市绿化覆盖率增至28.3%，城市绿地率达23%，人均公共绿地面积达5.82平方米。</p><p>　　5、抓好市政公用事业发展规划，积极探索市政公用事业市场化运作机制，引导社会投资，促进我区公用事业的快速、健康发展。</p><p>　　6、深入实施经营城市战略。走市场化运作、以城养城、以城建城之路，科学收储土地，合理配置土地资源，使经营城市取得实质性进展。努力形成经营城市上的'投融资多元化、项目化、市场开拓多元化的良性发展格局，盘活城市资产，扩大经营收益，提高服务功能。</p><p>　　7、加大城市管理力度，继续建立健全和完善城市管理规章，强化市容市貌和环境卫生管理，认真开展城市环境综合整治，创造优美、整洁的城市环境。</p><p>　　8、做大做强河西建筑产业。XX年建筑业成为我区经济支柱产业。今后五年，保持我区建筑业产值持续增长，年均递增8%。进一步规范建筑市场管理，重点抓好房地产开发市场和监理市场的整顿。结合建筑企业实际，继续深化企业改革改制。严格招投标管理，规范建筑市场和房地产市场秩序。</p><p>　　9、加快全区小城镇建设。加大小城镇建设的投入，选择具有较好投资环境，能辐射和带动一定区域经济发展的中心镇作为建设重点小城镇。XX年前抓好2个国家级重点镇、3个省级重点镇、5个市级试点镇的建设。全面提高全区小城镇发展质量和建设水平、树立经营理念，创新投融资体制、盘活存量资产、开辟融资渠道，加大小城镇建设的投入力度。同时，加强规划、建设和管理，加大政策扶持力度。已完成总体规划修编的重点镇，XX年前完成编制控制性详细规划，完成50%村庄总体规划。重点镇、试点镇到XX年建设完善城镇基础设施体系，提高集镇硬质路面覆盖率，建设集镇亮化、绿化工程，建设集镇给排水工程，垃圾处理和污水处理工程，建立完备社会公共服务设施系统。</p><script>s(\"content_relate\");</script></div>");
                break;
            }
            case ENUM_URBAN_SOCIAL_ECONOMIC_DEVELOPMENT: {
                stringBuilder.append("　建国60年，我国城市化水平大幅提高，城市个数由建国前的132个增加到2008年的655个，城市化水平由1949年的7.3%提高到2008年的45.68%。城市化进程经历了以下几个阶段：\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "　　（一）城市化起步阶段（1949年—1957年）\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "　　1949年，新中国刚成立时，全国仅有城市132个，城市市区人口3949万人，城市市区人口占全国总人口比重7.3%。在第一个五年计划时期，随着156项重点工程的启动和推进，出现了一批新的工矿城市，如纺织机械工业城市榆次；煤炭新城鸡西、双鸭山、焦作、平顶山、鹤壁等；钢铁新城马鞍山；石油新城玉门等。还完善了一批老城，扩建了武汉、成都、太原、西安、洛阳、兰州等工业占优势城市，发展了鞍山、本溪、齐齐哈尔等中等城市和哈尔滨、长春等大城市。到1957年末，我国城市发展到176个，比1949年增长33.3%，平均每年增长10%；城市市区人口增加到7077.27万人，比1949年增长79.2%，平均每年增长19.9%。城市市区人口占全国人口的比重提高到10.9%，比1949年增加3.3个百分点。\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "　　（二）城市化波动较大阶段（1958年—1965年）\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "　　第二个五年计划时期，城市的发展同国民经济的巨大振动一样，也呈现由扩大到紧缩的变化。在三年“大跃进”后，我国城市数量由1957年176个增加到1961年的208个，增长18.2%；城市人口由7077.27万人增加到10132.47万人，增长43.2%；城市市区人口占全国总人口比重由10.9%提高到15.4%。1962年开始的国民经济调整时期，又被迫撤销了一大批城市，到1965年，全国拥有城市168个，与1961年相比，减少40个，下降20%，主要原因，一是将“一五”时期以来设置的市恢复到县的建制；二是将一部分地级市降为县级市，停缓建大批建设项目，动员2500万左右职工回农村，城市市区人口由1961年的10132.47万人下降到8857.62万人，下降12.6%；城市市区人口的比重由15.4%下降至12.2%。\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "　　（三）城市化停滞发展阶段（1966年—1978年）\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "　　1966年开始的文化大革命，使得我国国民经济长期徘徊不前，相应的城市发展也十分缓慢，城市化进程受阻。1966年到1978年十二年间，全国仅增加城市26个，平均每年只增加2个，1978年城镇人口（居住在城镇地区半年及以上的人口）为17245万人，城市化率（城镇人口占全国总人口的比重）17.92%。\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "　　（四）城市化快速发展阶段（1979年—1991年）\n" +
                        "\n" +
                        " \n" +
                        "\n" +
                        "　　党的十一届三中全会以来，随着对内改革，对外开放的一系列政策措施的实施，我国城市的建设与发展同国民经济一样进入了生机旺盛的时期。从八十年代起，城市经济体制改革陆续展开，特别是进入90年代以后，小城镇发展战略的实施、经济开发区的普遍建立以及乡镇企业的兴起，带动了城市化水平的高速发展。城市经济辐射面增强，城市的中心作用得到进一步发挥，多年来的城乡分割被打破。1979年到1991年的十二年间，全国共新增加城市286个，相当于前三十年增加数的4.7倍，平均每年新增15个城市。到1991年末，城镇人口增加到31203万人，比1978年增长80.9%，平均每年增长5.8%。城市化率达到26.94%，比1978年提高9个百分点。");
                break;
            }
            case ENUM_SUMMARY_GENERAL_FACTORS: {
                stringBuilder.append("1、国家金融政策\n" +
                        "　　(1)银行信贷对金融信贷的管理、对按揭首付比例的规范、对社会保障住房开发的相关优惠等措施对遏制炒房、保障住房和限制开发具有重要影响,从而波及房地产价格走势。比如对于按揭首付的规定,分面积和购房次数来决定首付数额比例,对于大面积住房和二次购房要支付较高的首付款,从而限制投机和盲目消费。\n" +
                        "　　(2)银行利率。存款利率的调整直接影响到房地产投机行为,投机的减少带来成交量的下降,因此有可能导致房价涨幅的回落,这一情况在一定程度上缓解了炒房热,从而推动了房地产价格的理性回归。\n" +
                        "　　 (3)货币政策。由于银行信贷和外资的控制,货币供应量的增加同样是房价上升的重要原因,直接后果就是大量银行信贷及民间资本流入房地产业,而这个过程有其背后的制度原因。首先,在强制结汇制度下,外汇占款导致基础货币大量发行,并且由于人民银行对冲能力有限,从而导致货币供应量快速增长;其次,投资渠道有限以及其他各种原因,导致储蓄率过高,大量货币资产以储蓄的形式存放在银行等金融机构内;最后,中央政府和地方政府在一段时间内偏好性的扶持该行业,加之房地产作为一种投资品本身所具有的特殊性质,促使房地产价格在短时间内迅速攀升。\n" +
                        "　　2、土地调控政策\n" +
                        "　　首先,土地出让价格是房地产价格的重要组成部分,房地产价格与土地价格密不可分。地价指数与房地产价格指数也具有十分密切的关系,两者相互影响、相互作用。一方面地价水平受房地产价格和房地产投资力度的影响;另一方面房地产价格和房地产投资力度也受土地价格的影响。其次,土地使用权出让方式的规范对土地价格产生影响。最后,土地利用用途管制与监督对房地产价格的影响也很大。\n" +
                        "　　3、政府公共住房政策\n" +
                        "　　当前房地产业的症结在于政府未对房地产业进行合理区分,房地产的定价全部依靠不成熟的市场机制。作为消费品的房地产和作为投资品的房地产混同在一起无法区分,居民的居住权在开发商、地方政府以及外资等各方面寡头利益的博弈中无法得到保障,政府必须承担起保证居民基本居住权的公共责任。而在这个过程中政府的政策必然会导致供给和需求的变化,从而成为影响房价的重要的因素。\n" +
                        "　　4、税收政策\n" +
                        "　　税收手段是财政政策的主要手段,它通过税种和税率的变动来调节社会总供给和总需求。一般来说,住房领域涉及的税种主要有房地产开发税、契税、营业税、土地使用税、土地增值税、房地产税、企业所得税、个人所得税、印花税等。这些税种的可能影响到房地产发展的每个环节,从而限制其成本,造成价格变动。\n" +
                        "　　5、城市规划政策\n" +
                        "　　\n" +
                        "　　城市发展规划对房地产价格都有很大的影响,特别是城市规划中的规定用途、容积率、绿化率、建筑高度等指标。\n" +
                        "　　6、国家收入分配政策\n" +
                        "　　城镇居民人均可支配收入的增加反映人民生活水平的提高。居民可支配收入决定了其实际购买能力,进而决定了居民住房消费能力。城镇居民可支配收入的增加将使居民用来购房的资金增加,同时也使居民有了改善居住水平的愿望,这样就刺激了对房地产的需求,从而在一定程度上推动了房价的上涨。因此,房价水平与人均可支配收入也应呈现出正向关系。");
                break;
            }
            case ENUM_MARKET_RISK: {
                stringBuilder.append("本公司对估价对象作出的估价结果是基于国家及估价对象所在地目前的宏观政策和地产市场发展状况作出的客观判断。估价对象作为待开发土地，市场风险相对较高。如果出现较大的经济下滑影响及市场诸多不利因素发生的条件下，将会出现较大市场风险，则建议委托重新评估。");
                break;
            }
            case ENUM_REALIZATION_RISK: {
                stringBuilder.append("本报告结果是估价对象严格按照满足前述地价定义限制条件下的价格，未考虑市场变化风险、宗地分割处置及强制处分等因素对估价对象价值的影响，也未考虑变现费用及其他有关费用，报告使用者以本报告结果设置抵押权确定贷款额度时，应考虑实现抵押权时可能支付税金、变现所需的费用及优先受偿款，注意估价对象可能出现的市场变现风险。\n" +
                        "根据估价对象所在区域的市场交易行情，结合估价对象在估价期日的实际情况，我们预测估价对象在估价期日拍卖或变卖实现变现最可能的时间不低于90天。\n");
                break;
            }
            case ENUM_TAX_RISK: {
                stringBuilder.append("本报告在测算估价对象价格时，未对土地增值税进行预扣除处理，因此报告使用者以本报告价格为依据设置抵押权时，可能面临债权人在抵押标的物中权益减少的风险。");
                break;
            }
            default: {
                stringBuilder.append("未配置").append(RandomStringUtils.random(55)).append("十三点");
                break;
            }
        }

        return stringBuilder.toString();
    }

    /**
     * 获取上报告的变现分析数据
     *
     * @return
     */
    public String getReportLiquidity(ProjectInfo projectInfo, Integer areaGroupId) throws Exception {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_LIQUIDITY);
        if (baseDataDic == null) {
            return "";
        }
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(areaGroupId);
        SchemeLiquidationAnalysis data = schemeLiquidationAnalysisService.getDataByAreaId(areaGroupId);
        String liquidRatios = "";//变现比率
        String liquidTime = "";//变现时间
        if (data != null && StringUtils.isNotBlank(data.getLiquidRatios())) {
            liquidRatios = data.getLiquidRatios();//变现比率
        }
        if (data != null && StringUtils.isNotBlank(data.getLiquidTime())) {
            liquidTime = data.getLiquidTime();//变现时间
        }
        List<DataReportAnalysis> reportAnalysisList = dataReportAnalysisDao.getReportAnalysisList(baseDataDic.getId());
        if (CollectionUtils.isEmpty(reportAnalysisList)) return "";
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaGroupId);//区域下委估对象
        LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> estateGroupMap = generateCommonMethod.getEstateGroupByAreaId(areaGroupId);
        Map<String, EstateLiquidityAnalysisDto> analysisDtoMap = Maps.newHashMap();//用于处理变现能力综述
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : estateGroupMap.entrySet()) {
            EstateLiquidityAnalysisDto estateLiquidityAnalysisDto = new EstateLiquidityAnalysisDto();
            estateLiquidityAnalysisDto.setEstateName(entry.getKey().getName());
            analysisDtoMap.put(entry.getKey().getName(), estateLiquidityAnalysisDto);
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < reportAnalysisList.size(); i++) {
            DataReportAnalysis dataReportAnalysis = reportAnalysisList.get(i);
            stringBuilder.append(generateCommonMethod.getIndentHtml(String.format("%s、%s", i + 1, dataReportAnalysis.getName())));
            stringBuilder.append(generateCommonMethod.getIndentHtml(dataReportAnalysis.getTemplate()));
            //估价对象区位分析与估价区位分析
            if (AssessReportFieldConstant.ZONE_BIT_ANALYSIS.equals(dataReportAnalysis.getFieldName()) || AssessReportFieldConstant.LOCATION_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getLocationAnalysis(estateGroupMap))));
            }
            //估价对象土地实体分析
            if (AssessReportFieldConstant.LAND_ENTITY_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getLandEntityAnalysis(estateGroupMap))));
            }
            //估价对象建筑实体分析
            if (AssessReportFieldConstant.ARCHITECTURAL_ENTITY_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getBuildingEntityAnalysis(estateGroupMap, schemeAreaGroup))));
            }
            //变现能力通用性分析
            if (AssessReportFieldConstant.UNIVERSALITY_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getUniversalityAnalysis(estateGroupMap, projectInfo.getId(), analysisDtoMap))));
            }
            //独立性分析
            if (AssessReportFieldConstant.INDEPENDENCE_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getIndependenceAnalysis(judgeObjectList))));
            }
            //可分割分析
            if (AssessReportFieldConstant.DIVISIBLE_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getDivisibleAnalysis(judgeObjectList))));
            }
            //开发程度分析
            if (AssessReportFieldConstant.DEVELOPMENT_LEVEL.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getDevelopmentLevelAnalysis(estateGroupMap))));
            }
            //价值大小分析
            if (AssessReportFieldConstant.VALUE_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getValueAnalysis(judgeObjectList, areaGroupId))));
            }
            //房地产市场状况
            if (AssessReportFieldConstant.HOUSE_MARKET_CONDITION.equals(dataReportAnalysis.getFieldName())) {

            }
            //其他分析
            if (AssessReportFieldConstant.OTHER_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                stringBuilder.append(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(this.getOtherAnalysis(judgeObjectList))));
            }
            //变现价格与市场价格的差异度
            if (AssessReportFieldConstant.MARKET_VALUE_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                //其他
                DataReportTemplateItem other = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.MARKET_VALUE_ANALYSIS_OTHER);
                stringBuilder.append(generateCommonMethod.getIndentHtml(other.getTemplate()));
                //实现价格
                DataReportTemplateItem realized = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.MARKET_VALUE_ANALYSIS_REALIZED_PRICE);
                stringBuilder.append(generateCommonMethod.getIndentHtml(realized.getTemplate().replace("#{变现比率}", StringUtils.defaultString(liquidRatios))));
            }
            //变现时间费税及清偿
            if (AssessReportFieldConstant.PAY_OFF_ANALYSIS.equals(dataReportAnalysis.getFieldName())) {
                //其他
                DataReportTemplateItem other = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.PAY_OFF_ANALYSIS_OTHER);
                stringBuilder.append(generateCommonMethod.getIndentHtml(other.getTemplate()));
                //政策
                DataReportTemplateItem policy = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.PAY_OFF_ANALYSIS_POLICY);
                stringBuilder.append(generateCommonMethod.getIndentHtml(policy.getTemplate().replace("#{变现时间}", StringUtils.defaultString(liquidTime))));
                //费用一览表
                DataReportTemplateItem schedule = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.PAY_OFF_ANALYSIS_SCHEDULE);
                stringBuilder.append(generateCommonMethod.getIndentHtml(schedule.getTemplate()));
            }
            //变现能力综述
            if (AssessReportFieldConstant.CASHABILITY_SUMMARY.equals(dataReportAnalysis.getFieldName())) {
                DataReportTemplateItem templateItem = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.CASHABILITY_SUMMARY_CONTENT);
                HashSet<String> stringHashSet = Sets.newHashSet();
                for (Map.Entry<String, EstateLiquidityAnalysisDto> entry : analysisDtoMap.entrySet()) {
                    EstateLiquidityAnalysisDto analysisDto = entry.getValue();
                    String s = templateItem.getTemplate()
                            .replace("#{通用性}", StringUtils.isEmpty(analysisDto.getGenerality()) ? "" : String.format(analysisDto.getGenerality()))
                            .replace("#{独立使用性}", StringUtils.isEmpty(analysisDto.getIndependence()) ? "" : String.format(analysisDto.getIndependence()))
                            .replace("#{单个产权面积}", StringUtils.isEmpty(analysisDto.getPropertyRight()) ? "" : String.format(analysisDto.getPropertyRight()));
                    String mobility = "好";
                    if (analysisDto.getGenerality().contains("弱") || analysisDto.getIndependence().contains("差")) {
                        mobility = "较差";
                    }
                    if (analysisDto.getGenerality().contains("一般") || analysisDto.getGenerality().contains("一般") || analysisDto.getGenerality().contains("适中")) {
                        mobility = "较好";
                    }
                    s = s.replace("#{流动性}", mobility);
                    stringHashSet.add(generateCommonMethod.getIndentHtml(generateCommonMethod.trim(s)));
                }
                stringHashSet.forEach(o -> stringBuilder.append(o));
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 获取上报告的变现分析数据 (注意这个方法用在小微快贷的报告,因此和上面得方法是不同得，此方法只有部分)
     *
     * @param projectInfo
     * @param areaGroupId
     * @return
     * @throws Exception
     */
    public String getReportLiquidityLittle(ProjectInfo projectInfo, Integer areaGroupId) throws Exception {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY_LIQUIDITY);
        if (baseDataDic == null) {
            return "";
        }
        List<DataReportAnalysis> reportAnalysisList = dataReportAnalysisDao.getReportAnalysisList(baseDataDic.getId());
        if (CollectionUtils.isEmpty(reportAnalysisList)) {
            return "";
        }
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectDeclareListByAreaId(areaGroupId);//区域下委估对象
        LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> estateGroupMap = generateCommonMethod.getEstateGroupByAreaId(areaGroupId);
        Map<String, EstateLiquidityAnalysisDto> analysisDtoMap = Maps.newHashMap();//用于处理变现能力综述
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : estateGroupMap.entrySet()) {
            EstateLiquidityAnalysisDto estateLiquidityAnalysisDto = new EstateLiquidityAnalysisDto();
            estateLiquidityAnalysisDto.setEstateName(entry.getKey().getName());
            analysisDtoMap.put(entry.getKey().getName(), estateLiquidityAnalysisDto);
        }
        StringBuilder stringBuilder = new StringBuilder(8);
        final int five = 4;
        for (int i = 0; i < reportAnalysisList.size(); i++) {
            DataReportAnalysis dataReportAnalysis = reportAnalysisList.get(i);
            String fieldName = dataReportAnalysis.getFieldName();
            switch (fieldName) {
                case AssessReportFieldConstant.ZONE_BIT_ANALYSIS://估价对象区位分析与估价区位分析
                    stringBuilder.append(StringUtils.repeat(" ", five)).append(StringUtils.trim(dataReportAnalysis.getTemplate())).append(StringUtils.trim(generateCommonMethod.trim(getLocationAnalysis(estateGroupMap)))).append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    break;
                case AssessReportFieldConstant.LOCATION_ANALYSIS://估价对象区位分析与估价区位分析
                    stringBuilder.append(StringUtils.repeat(" ", five)).append(StringUtils.trim(dataReportAnalysis.getTemplate())).append(generateCommonMethod.trim(this.getLocationAnalysis(estateGroupMap))).append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    break;
                case AssessReportFieldConstant.UNIVERSALITY_ANALYSIS://变现能力通用性分析
                    stringBuilder.append(StringUtils.repeat(" ", five)).append(StringUtils.trim(dataReportAnalysis.getTemplate())).append(StringUtils.trimToEmpty(generateCommonMethod.trim(this.getUniversalityAnalysis(estateGroupMap, projectInfo.getId(), analysisDtoMap)))).append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    break;
                case AssessReportFieldConstant.INDEPENDENCE_ANALYSIS://独立性分析
                    stringBuilder.append(StringUtils.repeat(" ", five)).append(StringUtils.trim(dataReportAnalysis.getTemplate())).append(generateCommonMethod.trim(this.getIndependenceAnalysis(judgeObjectList))).append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    break;
                case AssessReportFieldConstant.DIVISIBLE_ANALYSIS://可分割分析
                    stringBuilder.append(StringUtils.repeat(" ", five)).append(StringUtils.trim(dataReportAnalysis.getTemplate())).append(generateCommonMethod.trim(this.getDivisibleAnalysis(judgeObjectList))).append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    break;
                case AssessReportFieldConstant.VALUE_ANALYSIS: //价值大小分析
                    stringBuilder.append(StringUtils.repeat(" ", five)).append(StringUtils.trim(dataReportAnalysis.getTemplate())).append(generateCommonMethod.trim(this.getValueAnalysis(judgeObjectList, areaGroupId))).append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    break;
                case AssessReportFieldConstant.CASHABILITY_SUMMARY://变现能力综述
                {
                    DataReportTemplateItem templateItem = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.CASHABILITY_SUMMARY_CONTENT);
                    HashSet<String> stringHashSet = Sets.newHashSet();
                    if (templateItem == null) {
                        continue;
                    }
                    if (!analysisDtoMap.isEmpty()) {
                        for (Map.Entry<String, EstateLiquidityAnalysisDto> entry : analysisDtoMap.entrySet()) {
                            EstateLiquidityAnalysisDto analysisDto = entry.getValue();
                            String s = templateItem.getTemplate()
                                    .replace("#{通用性}", StringUtils.isEmpty(analysisDto.getGenerality()) ? "" : String.format(analysisDto.getGenerality()))
                                    .replace("#{独立使用性}", StringUtils.isEmpty(analysisDto.getIndependence()) ? "" : String.format(analysisDto.getIndependence()))
                                    .replace("#{单个产权面积}", StringUtils.isEmpty(analysisDto.getPropertyRight()) ? "" : String.format(analysisDto.getPropertyRight()));
                            String mobility = "好";
                            if (analysisDto.getGenerality().contains("弱") || analysisDto.getIndependence().contains("差")) {
                                mobility = "较差";
                            }
                            if (analysisDto.getGenerality().contains("一般") || analysisDto.getGenerality().contains("一般") || analysisDto.getGenerality().contains("适中")) {
                                mobility = "较好";
                            }
                            s = s.replace("#{流动性}", mobility);
                            stringHashSet.add(generateCommonMethod.trim(s));
                        }
                    }
                    if (CollectionUtils.isNotEmpty(stringHashSet)) {
                        stringBuilder.append(StringUtils.repeat(" ", five)).append(StringUtils.trim(StringUtils.join(stringHashSet, ""))).append(StringUtils.repeat(ControlChar.LINE_BREAK, 1));
                    }
                }
                break;
                default:
                    break;
            }
        }
        String value = generateCommonMethod.delHTMLTag(stringBuilder.toString());
        return value;
    }


    /**
     * 获取区位分析
     *
     * @param map
     * @return
     */
    public String getLocationAnalysis(LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> map) {
        if (map.isEmpty()) return "";
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : map.entrySet()) {
            StringBuilder builder = new StringBuilder();
            BasicEstate basicEstate = entry.getKey();
            builder.append(erpAreaService.getSysAreaName(basicEstate.getDistrict())).append(basicEstate.getBlockName());
            if (basicEstate.getBlockId() != null) {
                DataBlock block = dataBlockService.getDataBlockById(basicEstate.getBlockId());
                if (block != null)
                    builder.append(block.getRemark());
            }
            entry.getValue().forEach(o -> resultMap.put(generateCommonMethod.parseIntJudgeNumber(o.getNumber()), builder.toString()));
        }
        return generateCommonMethod.trim(generateCommonMethod.judgeEachDesc(resultMap, "", "。", false));
    }

    /**
     * 获取区位分析
     *
     * @param map
     * @return
     */
    public String getLandEntityAnalysis(LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> map) throws Exception {
        if (map.isEmpty()) return "";
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : map.entrySet()) {
            StringBuilder builder = new StringBuilder();
            BasicEstate basicEstate = entry.getKey();
            builder.append(erpAreaService.getSysAreaName(basicEstate.getDistrict())).append(basicEstate.getName());
            BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchByEstateId(basicEstate.getId());
            builder.append(generateLandEntityService.getContent(basicApplyBatch));
            entry.getValue().forEach(o -> resultMap.put(generateCommonMethod.parseIntJudgeNumber(o.getNumber()), builder.toString()));
        }
        return generateCommonMethod.trim(generateCommonMethod.judgeEachDesc(resultMap, "", "。", false));
    }

    /**
     * 获取建筑实体分析
     *
     * @param map
     * @param schemeAreaGroup
     * @return
     * @throws Exception
     */
    public String getBuildingEntityAnalysis(LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> map, SchemeAreaGroup schemeAreaGroup) throws Exception {
        if (map.isEmpty()) return "";
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : map.entrySet()) {
            String content = generateHouseEntityService.getBuildEntityAnalysis(entry.getValue(), schemeAreaGroup);
            entry.getValue().forEach(o -> resultMap.put(generateCommonMethod.parseIntJudgeNumber(o.getNumber()), content));
        }
        return generateCommonMethod.trim(generateCommonMethod.judgeEachDesc(resultMap, "", "。", false));
    }

    /**
     * 获取通用性分析
     *
     * @param map
     * @return
     */
    public String getUniversalityAnalysis(LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> map, Integer projectId, Map<String, EstateLiquidityAnalysisDto> analysisDtoMap) {
        if (map.isEmpty()) return "";
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : map.entrySet()) {
            StringBuilder content = new StringBuilder("位于");
            List<Integer> judgeNumbers = Lists.newArrayList();
            Map<Integer, String> certUseMap = Maps.newHashMap();
            Map<Integer, String> practicalUseMap = Maps.newHashMap();
            for (SchemeJudgeObject schemeJudgeObject : entry.getValue()) {
                Integer number = generateCommonMethod.parseIntJudgeNumber(schemeJudgeObject.getNumber());
                judgeNumbers.add(number);
                certUseMap.put(number, schemeJudgeObject.getCertUse());
                practicalUseMap.put(number, schemeJudgeObject.getPracticalUse());
            }
            content.append(publicService.fusinString(LangUtils.transform(entry.getValue(), o -> o.getSeat()), true)).append("，");
            content.append(generateCommonMethod.judgeSummaryDesc(certUseMap, "证载用途", false)).append("，");
            content.append(generateCommonMethod.judgeSummaryDesc(practicalUseMap, "实际用途", false)).append("，其用途符合该区域的未来发展方向，");
            List<SurveyJudgeObjectGroupDto> list = surveyAssetRightGroupService.groupJudgeObject(projectId, entry.getValue());
            if (CollectionUtils.isNotEmpty(list)) {
                Map<Integer, String> comprehensiveMap = Maps.newHashMap();
                Map<Integer, String> tempMap = Maps.newHashMap();
                for (SurveyJudgeObjectGroupDto surveyJudgeObjectGroupDto : list) {
                    tempMap.put(surveyJudgeObjectGroupDto.getJudgeObjectId(), surveyJudgeObjectGroupDto.getResult());
                    if (StringUtils.equals(surveyJudgeObjectGroupDto.getResult(), "强"))
                        comprehensiveMap.put(surveyJudgeObjectGroupDto.getJudgeObjectId(), "产权清晰、权利明确、无特定转让限制");
                    if (StringUtils.equals(surveyJudgeObjectGroupDto.getResult(), "一般"))
                        comprehensiveMap.put(surveyJudgeObjectGroupDto.getJudgeObjectId(), "产权清晰、权利明确、转让受特定限制");
                    if (StringUtils.equals(surveyJudgeObjectGroupDto.getResult(), "弱"))
                        comprehensiveMap.put(surveyJudgeObjectGroupDto.getJudgeObjectId(), "产权清晰、权利明确、转让受到限制");
                }
                content.append(generateCommonMethod.judgeSummaryDesc(comprehensiveMap, "", false)).append("，");
                String desc = generateCommonMethod.judgeSummaryDesc(tempMap, "通用性", false);
                EstateLiquidityAnalysisDto analysisDto = analysisDtoMap.get(entry.getKey().getName());
                analysisDto.setGenerality(desc);
                content.append(desc).append("，");
            }
            entry.getValue().forEach(o -> resultMap.put(generateCommonMethod.parseIntJudgeNumber(o.getNumber()), content.toString()));
        }
        return generateCommonMethod.trim(generateCommonMethod.judgeEachDesc(resultMap, "", "。", false));
    }

    /**
     * 获取独立性分析
     *
     * @param judgeObjectList
     * @return
     */
    public String getIndependenceAnalysis(List<SchemeJudgeObject> judgeObjectList) {
        DataReportTemplateItem intactTemplateItem = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.INDEPENDENCE_ANALYSIS_INTACT);
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (SchemeJudgeObject judgeObject : judgeObjectList) {
            Integer number = generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber());
            //对应资产清查内容

            List<SurveyAssetInventory> surveyAssetInventories = surveyAssetInfoService.getSurveyAssetInventoryListByDeclareRecordId(judgeObject.getDeclareRecordId());
            if (CollectionUtils.isEmpty(surveyAssetInventories)) {
                continue;
            }
            //对应资产清查内容
            for (SurveyAssetInventory surveyAssetInventory : surveyAssetInventories) {
                if (!"不正常".equals(surveyAssetInventory.getRimIsNormal()) && !"损坏".equals(surveyAssetInventory.getEntityIsDamage())) {
                    resultMap.put(number, intactTemplateItem.getTemplate());
                } else {
                    StringBuilder damageContent = new StringBuilder();
                    if ("不正常".equals(surveyAssetInventory.getRimIsNormal())) {
                        List<SurveyDamageDto> zoneDamegeList = JSON.parseArray(surveyAssetInventory.getZoneDamage(), SurveyDamageDto.class);
                        if (CollectionUtils.isNotEmpty(zoneDamegeList)) {
                            for (SurveyDamageDto dto : zoneDamegeList) {
                                damageContent.append("项目:").append(dto.getZoneProjectName()).append(",明细").append(dto.getZoneProjectItem()).append(";");
                            }
                        }
                    }
                    if ("损坏".equals(surveyAssetInventory.getEntityIsDamage())) {
                        List<SurveyDamageDto> entityDamegeList = JSON.parseArray(surveyAssetInventory.getEntityDamage(), SurveyDamageDto.class);
                        if (CollectionUtils.isNotEmpty(entityDamegeList)) {
                            for (SurveyDamageDto dto : entityDamegeList) {
                                damageContent.append("项目:").append(dto.getEntityProjectName()).append(",明细").append(dto.getEntityProjectItem()).append(";");
                            }
                        }
                    }
                    if (StringUtils.isNotBlank(damageContent)) {
                        resultMap.put(number, String.format("%s；估价对象不能独立使用。", damageContent));
                    }
                }
            }

        }
        return generateCommonMethod.judgeEachDesc(resultMap, "", "。", false);
    }

    /**
     * 获取可分割分析
     *
     * @param judgeObjectList
     * @return
     */
    public String getDivisibleAnalysis(List<SchemeJudgeObject> judgeObjectList) {
        Integer passId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.CERTIFICATE_HANDLING_TYPE_PASS).getId(); //可办证
        Integer refuseId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.CERTIFICATE_HANDLING_TYPE_REFUSE).getId();//不可办证
        //不可分
        DataReportTemplateItem impartibility = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.DIVISIBLE_ANALYSIS_IMPARTIBILITY);
        //可分可办证
        DataReportTemplateItem detachableCanRush = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.DIVISIBLE_ANALYSIS_DETACHABLE_CAN_RUSH);
        //可分不可办证
        DataReportTemplateItem detachableNotRush = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.DIVISIBLE_ANALYSIS_DETACHABLE_NOT_RUSH);
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (SchemeJudgeObject judgeObject : judgeObjectList) {
            Integer number = generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber());
            if (judgeObject.getDeclareRecordId() == null || judgeObject.getDeclareRecordId() == 0) {
                continue;
            }
            List<SurveyAssetInventory> surveyAssetInventories = surveyAssetInfoService.getSurveyAssetInventoryListByDeclareRecordId(judgeObject.getDeclareRecordId());
            if (CollectionUtils.isEmpty(surveyAssetInventories)) {
                continue;
            }
            //对应资产清查内容
            for (SurveyAssetInventory surveyAssetInventory : surveyAssetInventories) {
                if ("不可分".equals(surveyAssetInventory.getSegmentationLimit())) {
                    resultMap.put(number, impartibility.getTemplate());
                } else if ("可分".equals(surveyAssetInventory.getSegmentationLimit())) {
                    resultMap.put(number, detachableCanRush.getTemplate());
                }
            }
        }
        String s = generateCommonMethod.judgeEachDesc(resultMap, "", "", false);
        return s;
    }

    /**
     * 获取开发程度分析
     *
     * @param map
     * @return
     */
    public String getDevelopmentLevelAnalysis(LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> map) {
        if (map.isEmpty()) return "";
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : map.entrySet()) {
            entry.getValue().forEach(o -> resultMap.put(generateCommonMethod.parseIntJudgeNumber(o.getNumber()), entry.getKey().getLocationDescribe()));
        }
        return generateCommonMethod.judgeEachDesc(resultMap, "", "。", false);
    }

    /**
     * 获取估价对象价值大小
     *
     * @param judgeObjectList
     * @param areaGroupId
     * @return
     */
    public String getValueAnalysis(List<SchemeJudgeObject> judgeObjectList, Integer areaGroupId) {
        Integer num = judgeObjectList.size();
        BigDecimal totalRealEstate = generateCommonMethod.getTotalRealEstate(areaGroupId);
        BigDecimal rank = new BigDecimal("5000000");
        DataReportTemplateItem dataReportTemplateByField = null;
        if (rank.compareTo(totalRealEstate) == 1 && num < 5) {
            dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.VALUE_ANALYSIS_CONDITION1);
        }
        if (rank.compareTo(totalRealEstate) == 1 && num >= 5) {
            dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.VALUE_ANALYSIS_CONDITION2);
        }
        if (rank.compareTo(totalRealEstate) < 1 && num < 5) {
            dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.VALUE_ANALYSIS_CONDITION3);
        }
        if (rank.compareTo(totalRealEstate) < 1 && num >= 5) {
            dataReportTemplateByField = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.VALUE_ANALYSIS_CONDITION4);
        }
        return dataReportTemplateByField.getTemplate();
    }

    /**
     * 获取房地产市场状况
     *
     * @param map
     * @return
     */
    public String getHouseMarketCondition(LinkedHashMap<BasicEstate, List<SchemeJudgeObject>> map) {
        if (map.isEmpty()) return "";
        Map<Integer, String> resultMap = Maps.newHashMap();
        for (Map.Entry<BasicEstate, List<SchemeJudgeObject>> entry : map.entrySet()) {
            BasicEstate basicEstate = entry.getKey();
            for (SchemeJudgeObject schemeJudgeObject : entry.getValue()) {
                Integer number = generateCommonMethod.parseIntJudgeNumber(generateCommonMethod.getNumber(schemeJudgeObject.getNumber()));
                DataBestUseDescription bestUseDescription = dataBestUseDescriptionService.getCacheBestUseDescriptionById(schemeJudgeObject.getBestUse());
                String bestUseName = bestUseDescription == null ? "" : bestUseDescription.getName();
                resultMap.put(number, String.format("%s%s", erpAreaService.getSysAreaName(basicEstate.getDistrict()), bestUseName));
            }
        }
        return generateCommonMethod.judgeEachDesc(resultMap, "", "。", false);
    }

    /**
     * 获取其它分析
     *
     * @param judgeObjectList
     * @return
     */
    public String getOtherAnalysis(List<SchemeJudgeObject> judgeObjectList) {
        Integer pledgeId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.HOUSE_INVENTORY_RIGHT_CATEGORY_PLEDGE).getId();
        Integer otherId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.HOUSE_INVENTORY_RIGHT_CATEGORY_OTHER).getId();
        Integer rentId = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.HOUSE_INVENTORY_RIGHT_CATEGORY_LEASEHOLD).getId();

        DataReportTemplateItem pledgeTemplate = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.OTHER_ANALYSIS_PLEDGE);
        DataReportTemplateItem rentTemplate = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.OTHER_ANALYSIS_RENT);
        DataReportTemplateItem otherTemplate = dataReportTemplateItemService.getDataReportTemplateByField(AssessReportFieldConstant.OTHER_ANALYSIS_OTHER);
        Map<Integer, String> pledgeMap = Maps.newHashMap();
        Map<Integer, String> rentMap = Maps.newHashMap();
        Map<Integer, String> otherMap = Maps.newHashMap();
        for (SchemeJudgeObject judgeObject : judgeObjectList) {
            Integer number = generateCommonMethod.parseIntJudgeNumber(judgeObject.getNumber());
            //对应的他权信息
            List<SurveyAssetRightItem> rightList = Lists.newArrayList();
            List<SurveyAssetRightGroup> surveyAssetInventoryRightRecordList = surveyAssetRightGroupService.getSurveyAssetRightGroupByDeclareRecord(judgeObject.getDeclareRecordId(), judgeObject.getProjectId());
            if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightRecordList)) {
                Iterator<SurveyAssetRightGroup> iterator = surveyAssetInventoryRightRecordList.iterator();
                while (iterator.hasNext()) {
                    rightList.addAll(surveyAssetRightGroupService.getSurveyAssetRightItemListByGroupId(iterator.next().getId()));
                }
            }
            if (CollectionUtils.isNotEmpty(rightList)) {
                for (SurveyAssetRightItem inventoryRight : rightList) {
                    if (pledgeId.equals(inventoryRight.getCategory())) {//抵押
                        pledgeMap.put(number, pledgeTemplate.getTemplate().replace("#{他权描述}", StringUtils.defaultString(inventoryRight.getInfluence())));
                    }
                    if (rentId.equals(inventoryRight.getCategory())) {//出租
                        rentMap.put(number, rentTemplate.getTemplate().replace("#{他权描述}", StringUtils.defaultString(inventoryRight.getInfluence())));
                    }
                    if (otherId.equals(inventoryRight.getCategory())) {//其它
                        otherMap.put(number, otherTemplate.getTemplate().replace("#{他权描述}", StringUtils.defaultString(inventoryRight.getInfluence())));
                    }
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!pledgeMap.isEmpty())
            stringBuilder.append(generateCommonMethod.judgeEachDesc(pledgeMap, "", "。", true));
        if (!rentMap.isEmpty())
            stringBuilder.append(generateCommonMethod.judgeEachDesc(rentMap, "", "。", true));
        if (!otherMap.isEmpty())
            stringBuilder.append(generateCommonMethod.judgeEachDesc(otherMap, "", "。", true));
        if (pledgeMap.isEmpty() && rentMap.isEmpty() && otherMap.isEmpty()) {
            stringBuilder.append("无抵押，无租赁，无典当，无继承，无担保，无查封、诉讼、仲裁、司法强制执行或其他重大争议等禁止转让情形，房地产权属无纠纷。");
        }
        return stringBuilder.toString();
    }
}
