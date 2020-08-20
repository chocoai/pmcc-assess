package com.copower.pmcc.assess.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.MyX509TrustManager;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordContentDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordContent;
import com.copower.pmcc.assess.dal.cases.dao.CaseNetInfoRecordContentDao;
import com.copower.pmcc.assess.dal.cases.dao.CaseNetInfoRecordDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseNetInfoRecord;
import com.copower.pmcc.assess.dal.cases.entity.CaseNetInfoRecordContent;
import com.copower.pmcc.assess.dto.input.net.JDSFDto;
import com.copower.pmcc.assess.dto.input.net.JDZCDto;
import com.copower.pmcc.assess.dto.input.net.TBSFDto;
import com.copower.pmcc.assess.dto.input.net.ZGSFDto;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordVo;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.*;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/6/12
 * @time: 11:16
 */
@Service
public class NetInfoRecordService {
    private final static Logger logger = LoggerFactory.getLogger(NetInfoRecordService.class);
    @Autowired
    private NetInfoRecordDao netInfoRecordDao;
    @Autowired
    private CaseNetInfoRecordDao caseNetInfoRecordDao;
    @Autowired
    private NetInfoRecordContentDao netInfoRecordContentDao;
    @Autowired
    private CaseNetInfoRecordContentDao caseNetInfoRecordContentDao;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private NetUrlConfigService netUrlConfigService;


    //抓取数据
    public void climbingData() {
        //来源京东司法
        this.getNetInfoFromJDSF(1);
        //来源京东资产
        this.getNetInfoFromJDZC(1);
        //中国拍卖行业协会网-司法
        this.getNetInfoFromZGSF(1);
        //中国拍卖行业协会网-标的
        this.getNetInfoFromZGBD(1);
        //来源公拍网
        this.getNetInfoFromGPW(1);
        //公共资源交易平台-成都(土地矿权)
        this.getNetInfoFromGGZYCD(1);
        //公共资源交易平台-成都（资产资源）
        this.getNetInfoFromGGZYCD2(1);
        ////土流网
        this.getNetInfoFromTDJY(1);
        //农村产权交易中心
        this.getNetInfoFromNCJY(1);
        //来源淘宝网
        this.getNetInfoFromTB(1);

        netUrlConfigService.climbingAll();
        netUrlConfigService.getBaZhongTradingCenter(1);
        netUrlConfigService.getLuZhouTradingCenter(1);
        netUrlConfigService.getZiGongTradingCenter(1);
        netUrlConfigService.getYiBinTradingCenter(1, "land");
        netUrlConfigService.getYiBinTradingCenter(1, "asset");
    }

    //来源淘宝网
    public void getNetInfoFromTB(Integer days) {
        try {
            Date date = getInstanceDate(days + 1);//得到前1天
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = sdf.format(date);//开始时间
            Date endDate = getInstanceDate(0);
            if (days == 1) {
                endDate = date;//截止时间时间
            }
            String endDateStr = sdf.format(endDate);
//            String[] needContentType = new String[]{"住宅用房", "商业用房", "工业用房", "其他用房", "股权", "债权", "林权", "矿权", "土地", "资产", "无形资产"};
            String[] needContentType = new String[]{"住宅用房", "商业用房", "工业用房", "其他用房", "土地"};
            List<String> types = Arrays.asList(needContentType);
            String[] needProvinceNames = new String[]{"四川", "重庆", "贵州", "云南", "海南"};
            List<String> provinceNames = Arrays.asList(needProvinceNames);
            Map<String, String> strHrefs = Maps.newHashMap();//用于记录地址
            String urlInfo = "https://sf.taobao.com/item_list.htm?auction_source=0&sorder=2&st_param=-1&auction_start_seg=&" +
                    "auction_start_from=" + formatDate + "&auction_start_to=" + endDateStr + "&&spm=a213w.3064813.9001.2";
            Elements elements = getContent(urlInfo, ".condition", "GBK");
            Elements a = elements.get(0).select("li a");
            for (Element item : a) {
                String type = item.childNodes().get(0).toString().trim();
                if (types.contains(type)) {
                    //省
                    Elements provinceElements = getContent(String.format("https:%s", item.attributes().get("href").trim()), ".condition", "GBK");
                    Elements provinces = provinceElements.get(1).select("li a");
                    for (Element province : provinces) {
                        String provinceName = province.childNodes().get(0).toString();
                        if (!provinceNames.contains(provinceName)) continue;
                        Elements cityElements = getContent(String.format("https:%s", province.attributes().get("href").trim()), ".J_SubCondition", "GBK");
                        Elements citys = cityElements.get(0).select("li a");
                        for (Element city : citys) {
                            String strHref = String.format("https:%s", city.attributes().get("href").trim());
                            String cityName = city.childNodes().get(0).toString();
                            strHrefs.put(strHref, String.format("%s_%s_%s", type, provinceName, cityName));
                        }
                    }
                }

            }
            for (Map.Entry<String, String> entry : strHrefs.entrySet()) {
                String strHref = entry.getKey();
                Elements pageElements = getContent(strHref, ".J_TPage", "GBK");
                if (pageElements.size() == 0 || pageElements == null) continue;
                Integer value = Integer.valueOf(pageElements.get(0).attributes().get("value"));
                for (int i = 1; i <= value; i++) {
                    String href = String.format("%s%s", strHref, "&page=" + i);
                    Elements elementsItem = getContent(href, "#sf-item-list-data", "GBK");
                    String data = elementsItem.get(0).childNodes().get(0).toString();
                    JSONObject jsonObject = JSON.parseObject(data);

                    List<String> dataList = JSON.parseArray(jsonObject.getString("data"), String.class);
                    for (String dataStr : dataList) {
                        StringBuilder sb = new StringBuilder(dataStr);
                        sb.insert(1, "\"id\":\"\",");
                        TBSFDto tbsfDto = JSON.parseObject(sb.toString(), TBSFDto.class);
                        if (!StringUtils.equals(tbsfDto.getStatus(), "done")) continue;
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        netInfoRecord.setTitle(tbsfDto.getTitle());
                        netInfoRecord.setSourceSiteUrl(String.format("%s" + tbsfDto.getItemUrl(), "https:"));
                        tbsfDto.setType(entry.getValue().substring(0, entry.getValue().indexOf("_")));
                        String provinceAndCity = entry.getValue().substring(entry.getValue().indexOf("_") + 1);
                        netInfoRecord.setProvince(provinceAndCity.substring(0, provinceAndCity.indexOf("_")));
                        netInfoRecord.setCity(provinceAndCity.substring(provinceAndCity.indexOf("_") + 1));
                        Elements itemContent = getContent(netInfoRecord.getSourceSiteUrl(), "#J_HoverShow", "GBK").select("tr").get(0).select("span");
                        String initPrice = "";
                        if (itemContent != null) {
                            initPrice = itemContent.get(2).childNodes().get(0).toString().replace(",", "");
                        }
                        netInfoRecord.setCurrentPrice(getRealMoney(tbsfDto.getCurrentPrice()));
                        netInfoRecord.setConsultPrice(getRealMoney(tbsfDto.getConsultPrice()));
                        netInfoRecord.setInitPrice(getRealMoney(initPrice));
                        netInfoRecord.setLiquidRatios(getLiquidRatios(tbsfDto.getCurrentPrice(), tbsfDto.getConsultPrice()));
                        String content = getContent(tbsfDto.getTitle(), tbsfDto.getType(), tbsfDto.getCurrentPrice(), tbsfDto.getConsultPrice(), initPrice
                                , DateUtils.format(tbsfDto.getEnd(), DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(tbsfDto.getStart(), DateUtils.DATE_CHINESE_PATTERN));
                        netInfoRecord.setContent(content);
                        netInfoRecord.setType(tbsfDto.getType());
                        netInfoRecord.setBeginTime(tbsfDto.getStart());
                        netInfoRecord.setEndTime(tbsfDto.getEnd());
                        netInfoRecord.setSourceSiteName("淘宝司法拍卖网");
                        this.addInfo(netInfoRecord);

                        Elements noticeEles = getContent(String.format("%s" + tbsfDto.getItemUrl(), "https:"), "#J_ItemNotice", "GBK");
                        if (noticeEles.size() == 0) {
                            noticeEles = getContent(String.format("%s" + tbsfDto.getItemUrl(), "https:"), "#J_NoticeDetail", "GBK");
                        }
                        if (noticeEles.size() != 0) {
                            String contentHref = noticeEles.get(0).attributes().get("data-from").trim();
                            Elements body = getContent(String.format("%s" + contentHref, "https:"), "body", "GBK");
                            NetInfoRecordContent netInfoRecordContent = new NetInfoRecordContent();
                            netInfoRecordContent.setRecordId(netInfoRecord.getId());
                            if (body.html().length() > 30000) {
                                netInfoRecordContent.setFullDescription(body.html().substring(0, 30000));
                            } else {
                                netInfoRecordContent.setFullDescription(body.html());
                            }
                            addInfoContent(netInfoRecordContent);
                        }
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //来源京东司法
    public void getNetInfoFromJDSF(Integer days) {
        try {
            Date date = getInstanceDate(days);//得到前1天
            // String[] needContentType = new String[]{"住宅用房", "商业用房", "工业用房", "其他用房", "股权", "债权", "林权", "矿权", "土地", "无形资产"};
            String[] needContentType = new String[]{"住宅用房", "商业用房", "工业用房", "其他用房", "土地"};
            Map<String, List<String>> strHrefs = Maps.newHashMap();
            List<String> types = Arrays.asList(needContentType);

            //首页
            String urlInfo = "https://auction.jd.com/sifa_list.html?callback=jQuery8159673&page=1&limit=40&paimaiStatus=2";
            //获取数据地址
            String typeHref = "https://auction.jd.com/getJudicatureList.html?paimaiStatus=2";
            //获取起始价地址
            String initPriceHref = "https://api.m.jd.com/api?appid=paimai&functionId=getProductBasicInfo&body={%22paimaiId%22:%s}";

            Elements elements = getContent(urlInfo, ".sl-v-list", "");
            Elements a = elements.get(0).select("li a");
            for (Element item : a) {
                String type = item.childNodes().get(0).toString().trim();
                if (types.contains(type)) {
                    List<String> pageHref = Lists.newArrayList();
                    String typeId = item.parentNode().attributes().get("data-childrencateid");
                    String dataHref = String.format("%s%s", typeHref, "&childrenCateId=" + typeId);
                    Elements pageElements = getContent(dataHref, "body", "");
                    String data = pageElements.get(0).childNodes().get(0).toString();
                    JSONObject jsonObject = JSON.parseObject(data);
                    Integer total = JSON.parseObject(jsonObject.getString("total"), Integer.class);
                    if (total == 0) continue;
                    Integer page = total % 40 > 0 ? total / 40 + 1 : total / 40;
                    for (int i = 1; i <= page; i++) {
                        String dataPageHref = String.format("%s%s", dataHref, "&limit=40&page=" + i);
                        pageHref.add(dataPageHref);
                    }
                    strHrefs.put(type, pageHref);
                }

            }
            for (Map.Entry<String, List<String>> entry : strHrefs.entrySet()) {
                List<String> pageHref = entry.getValue();
                circ:
                for (String typeData : pageHref) {
                    Elements pageElements = getContent(typeData, "body", "");
                    String data = pageElements.get(0).childNodes().get(0).toString();
                    JSONObject jsonObject = JSON.parseObject(data);
                    List<String> dataList = JSON.parseArray(jsonObject.getString("ls"), String.class);
                    if (CollectionUtils.isEmpty(dataList)) continue;
                    String itemHrefStr = "https://paimai.jd.com/";
                    for (String dataStr : dataList) {
                        JDSFDto jdsfDto = JSON.parseObject(dataStr, JDSFDto.class);
                        String itemHref = String.format("%s%s", itemHrefStr, jdsfDto.getId());
                        if (jdsfDto.getEndTime().compareTo(date) < 0) break circ;
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        netInfoRecord.setSourceSiteUrl(itemHref);
                        netInfoRecord.setTitle(jdsfDto.getTitle());
                        netInfoRecord.setProvince(jdsfDto.getProvince());
                        netInfoRecord.setCity(jdsfDto.getCity());
                        netInfoRecord.setEndTime(jdsfDto.getEndTime());
                        netInfoRecord.setBeginTime(jdsfDto.getStartTime());
                        netInfoRecord.setType(entry.getKey());
                        String initPrice = "";
                        try {
                            String replace = initPriceHref.replace("%s", jdsfDto.getId().toString());
                            Elements initPriceElements = getContent(replace, "body", "");
                            String initPriceData = initPriceElements.get(0).childNodes().get(0).toString();
                            JSONObject initPriceStr = JSON.parseObject(initPriceData);
                            initPriceStr = JSON.parseObject(initPriceStr.getString("data"));
                            initPrice = JSON.parseObject(initPriceStr.getString("startPrice"), String.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        netInfoRecord.setCurrentPrice(getRealMoney(jdsfDto.getCurrentPriceStr()));
                        netInfoRecord.setConsultPrice(getRealMoney(jdsfDto.getAssessmentPriceStr()));
                        netInfoRecord.setInitPrice(getRealMoney(initPrice));
                        netInfoRecord.setLiquidRatios(getLiquidRatios(jdsfDto.getCurrentPriceStr(), jdsfDto.getAssessmentPriceStr()));
                        String content = getContent(jdsfDto.getTitle(), entry.getKey(), jdsfDto.getCurrentPriceStr(), jdsfDto.getAssessmentPriceStr(), initPrice
                                , DateUtils.format(jdsfDto.getEndTime(), DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(jdsfDto.getStartTime(), DateUtils.DATE_CHINESE_PATTERN));
                        netInfoRecord.setContent(content);
                        netInfoRecord.setSourceSiteName("京东司法拍卖网");
                        this.addInfo(netInfoRecord);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //来源京东资产
    public void getNetInfoFromJDZC(Integer days) {
        try {
            Date date = getInstanceDate(days);//得到前1天
            Map<String, List<String>> strHrefs = Maps.newHashMap();
            Map<String, String> needContentType = Maps.newHashMap();
            needContentType.put("房产", "12762");
            needContentType.put("土地林权", "12764");
            // needContentType.put("股权", "12766");
            // needContentType.put("债权", "12767");
            // needContentType.put("知识产权", "12768");
            // needContentType.put("无形资产", "12769");
            String typeHref = "https://auction.jd.com/getAssetsList.html?paimaiStatus=2";

            for (Map.Entry<String, String> entry : needContentType.entrySet()) {
                List<String> pageHref = Lists.newArrayList();
                String dataHref = String.format("%s%s", typeHref, "&childrenCateId=" + entry.getValue());
                Elements pageElements = getContent(dataHref, "body", "");
                String data = pageElements.get(0).childNodes().get(0).toString();
                JSONObject jsonObject = JSON.parseObject(data);
                Integer total = JSON.parseObject(jsonObject.getString("total"), Integer.class);
                if (total == 0) continue;
                Integer page = total % 40 > 0 ? total / 40 + 1 : total / 40;
                for (int i = 1; i <= page; i++) {
                    String dataPageHref = String.format("%s%s", dataHref, "&limit=40&page=" + i);
                    pageHref.add(dataPageHref);
                }
                strHrefs.put(entry.getKey(), pageHref);
            }

            for (Map.Entry<String, List<String>> entry : strHrefs.entrySet()) {
                List<String> pageHref = entry.getValue();
                for (String typeData : pageHref) {
                    Elements pageElements = getContent(typeData, "body", "");
                    String data = pageElements.get(0).childNodes().get(0).toString();
                    JSONObject jsonObject = JSON.parseObject(data);
                    List<String> dataList = JSON.parseArray(jsonObject.getString("ls"), String.class);
                    if (CollectionUtils.isEmpty(dataList)) continue;
                    String itemHrefStr = "https://paimai.jd.com/";
                    circ:
                    for (String dataStr : dataList) {
                        JDZCDto jdzcDto = JSON.parseObject(dataStr, JDZCDto.class);
                        String itemHref = String.format("%s%s", itemHrefStr, jdzcDto.getId());
                        if (jdzcDto.getEndTime().compareTo(date) < 0) break circ;
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        netInfoRecord.setSourceSiteUrl(itemHref);
                        netInfoRecord.setTitle(jdzcDto.getTitle());
                        netInfoRecord.setProvince(jdzcDto.getProvince());
                        netInfoRecord.setCity(jdzcDto.getCity());
                        netInfoRecord.setBeginTime(jdzcDto.getStartTime());
                        netInfoRecord.setEndTime(jdzcDto.getEndTime());
                        netInfoRecord.setType(entry.getKey());
                        netInfoRecord.setCurrentPrice(getRealMoney(jdzcDto.getCurrentPrice()));
                        netInfoRecord.setInitPrice(getRealMoney(jdzcDto.getStartPrice()));
                        String content = getContent(jdzcDto.getTitle(), entry.getKey(), jdzcDto.getCurrentPrice(), "", jdzcDto.getStartPrice()
                                , DateUtils.format(jdzcDto.getEndTime(), DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(jdzcDto.getStartTime(), DateUtils.DATE_CHINESE_PATTERN));
                        netInfoRecord.setContent(content);
                        netInfoRecord.setSourceSiteName("京东资产拍卖网");
                        this.addInfo(netInfoRecord);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //中国拍卖行业协会网-司法
    public void getNetInfoFromZGSF(Integer days) {
        try {
            Date date = getInstanceDate(days);//得到前1天
            Map<String, String> needContentType = Maps.newHashMap();
            needContentType.put("房产", "6");
            needContentType.put("土地使用权", "5");
            needContentType.put("集体土地使用权", "4");
            // needContentType.put("股权", "16");
            // needContentType.put("债券", "14");
            // needContentType.put("森林、林木使用权", "3");
            // needContentType.put("知识产权", "17");
            // needContentType.put("其他财产", "255");
            String[] needProvinceNames = new String[]{"四川省", "重庆市", "贵州省", "云南省", "海南省"};
            List<String> provinceNames = Arrays.asList(needProvinceNames);
            Map<String, List<String>> strHrefs = Maps.newHashMap();
            String typeHref = "http://sf.caa123.org.cn/caa-web-ws/ws/0.1/lots?sortname=&sortorder=&count=12&lotStatus=2";

            String htmlHref = "http://sf.caa123.org.cn/pages/lots.html?&lotStatus=2&canLoan=&isRestricted=";
            Elements provinceElements = getContent(htmlHref, "#queryloaction", "");
            Elements provinces = provinceElements.get(0).select("li a");

            for (Map.Entry<String, String> entry : needContentType.entrySet()) {
                for (Element item : provinces) {
                    List<String> pageHref = Lists.newArrayList();
                    Integer startPage = 0;
                    String provinceName = item.childNodes().get(0).childNodes().get(0).toString().trim();
                    if (!provinceNames.contains(provinceName)) continue;
                    String dataHref = String.format("%s%s%s", typeHref, "&standardType=" + entry.getValue(), "&province=" + URLEncoder.encode(provinceName));
                    Elements pageElements = getContent(String.format("%s%s", dataHref, "&start=" + startPage), "body", "");
                    if (pageElements == null) continue;
                    String data = pageElements.get(0).childNodes().get(0).toString().trim();
                    JSONObject jsonObject = JSON.parseObject(data);
                    Integer totalCount = JSON.parseObject(jsonObject.getString("totalCount"), Integer.class);
                    if (totalCount == 0) continue;
                    Integer totalPages = JSON.parseObject(jsonObject.getString("totalPages"), Integer.class);
                    for (int i = 0; i < totalPages; i++) {
                        String dataPageHref = String.format("%s%s", dataHref, "&start=" + i);
                        pageHref.add(dataPageHref);
                    }
                    strHrefs.put(String.format("%s_%s", entry.getKey(), provinceName), pageHref);

                }
            }

//            //只取四川的
//            for (Map.Entry<String, String> entry : needContentType.entrySet()) {
//                List<String> pageHref = Lists.newArrayList();
//                Integer startPage = 0;
//                String dataHref = String.format("%s%s%s", typeHref, "&standardType=" + entry.getValue(), "&province=" + URLEncoder.encode("四川省"));
//                Elements pageElements = getContent(String.format("%s%s", dataHref, "&start=" + startPage), "body", "");
//                if (pageElements == null) continue;
//                String data = pageElements.get(0).childNodes().get(0).toString().trim();
//                JSONObject jsonObject = JSON.parseObject(data);
//                Integer totalCount = JSON.parseObject(jsonObject.getString("totalCount"), Integer.class);
//                if (totalCount == 0) continue;
//                Integer totalPages = JSON.parseObject(jsonObject.getString("totalPages"), Integer.class);
//                for (int i = 0; i < totalPages; i++) {
//                    String dataPageHref = String.format("%s%s", dataHref, "&start=" + i);
//                    pageHref.add(dataPageHref);
//                }
//                strHrefs.put(String.format("%s_%s", entry.getKey(), "四川"), pageHref);
//            }


            for (Map.Entry<String, List<String>> entry : strHrefs.entrySet()) {
                List<String> pageHref = entry.getValue();
                circ:
                for (String typeData : pageHref) {
                    Elements pageElements = getContent(typeData, "body", "");
                    if (pageElements == null) continue;
                    String data = pageElements.get(0).childNodes().get(0).toString().trim();
                    JSONObject jsonObject = JSON.parseObject(data);
                    List<String> dataList = JSON.parseArray(jsonObject.getString("items"), String.class);
                    if (CollectionUtils.isEmpty(dataList)) continue;
                    String itemHrefStr = "http://sf.caa123.org.cn/lot/";
                    for (String dataStr : dataList) {
                        ZGSFDto zgsfDto = JSON.parseObject(dataStr, ZGSFDto.class);
                        String itemHref = String.format("%s%s%s", itemHrefStr, zgsfDto.getId(), ".html");
                        if (zgsfDto.getEndTime().compareTo(date) < 0) break circ;
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        netInfoRecord.setSourceSiteUrl(itemHref);
                        netInfoRecord.setTitle(zgsfDto.getName());
                        netInfoRecord.setProvince(entry.getKey().substring(entry.getKey().indexOf("_") + 1));
                        netInfoRecord.setBeginTime(zgsfDto.getStartTime());
                        netInfoRecord.setEndTime(zgsfDto.getEndTime());
                        netInfoRecord.setType(entry.getKey().substring(0, entry.getKey().indexOf("_")));
                        netInfoRecord.setCurrentPrice(getRealMoney(zgsfDto.getNowPrice()));
                        netInfoRecord.setConsultPrice(getRealMoney(zgsfDto.getAssessPrice()));
                        netInfoRecord.setInitPrice(getRealMoney(zgsfDto.getStartPrice()));
                        netInfoRecord.setLiquidRatios(getLiquidRatios(zgsfDto.getNowPrice(), zgsfDto.getAssessPrice()));
                        String content = getContent(zgsfDto.getName(), entry.getKey().substring(0, entry.getKey().indexOf("_")), zgsfDto.getNowPrice(), zgsfDto.getAssessPrice(), zgsfDto.getStartPrice()
                                , DateUtils.format(zgsfDto.getEndTime(), DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(zgsfDto.getStartTime(), DateUtils.DATE_CHINESE_PATTERN));
                        netInfoRecord.setContent(content);
                        netInfoRecord.setSourceSiteName("中国拍卖行业协会网-司法");
                        this.addInfo(netInfoRecord);

                        String contentHref = itemHref.replace("lot", "caa-web-ws/ws/0.1/notice/lot");
                        contentHref = contentHref.replace(".html", "");
                        Elements contentBody = getContent(contentHref, "body", "");
                        if (contentBody.size() != 0 && contentBody != null) {
                            NetInfoRecordContent netInfoRecordContent = new NetInfoRecordContent();
                            netInfoRecordContent.setRecordId(netInfoRecord.getId());
                            if (contentBody.html().length() > 30000) {
                                netInfoRecordContent.setFullDescription(contentBody.html().substring(0, 30000));
                            } else {
                                netInfoRecordContent.setFullDescription(contentBody.html());
                            }
                            addInfoContent(netInfoRecordContent);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //中国拍卖行业协会网-标的
    public void getNetInfoFromZGBD(Integer days) {
        try {
            Date date = getInstanceDate(days);//得到前1天
            Map<String, String> needContentType = Maps.newHashMap();
            needContentType.put("房产", "6");
            needContentType.put("土地", "7");
//            needContentType.put("股权债权", "8");
//            needContentType.put("无形资产", "18");
//            needContentType.put("其他财产", "255");
            String[] needProvinceNames = new String[]{"四川省", "重庆市", "贵州省", "云南省", "海南省"};
            List<String> provinceNames = Arrays.asList(needProvinceNames);
            Map<String, List<String>> strHrefs = Maps.newHashMap();
            String typeHref = "https://paimai.caa123.org.cn/wt-web-ws/ws/0.1/lots?sortname=&sortorder=&count=12&status=3";

            String htmlHref = "https://paimai.caa123.org.cn/pages/lots/list.html?&attribute=&term=&startTimeStamp=&endTimeStamp=&&canLoan=&isRestricted=";
            Elements provinceElements = getContent(htmlHref, "#queryloaction", "");
            Elements provinces = provinceElements.get(0).select("li a");

            Integer princeId = 0;
            for (Map.Entry<String, String> entry : needContentType.entrySet()) {
                for (Element item : provinces) {
                    princeId++;
                    String provinceName = item.childNodes().get(0).childNodes().get(0).toString().trim();
                    if (!provinceNames.contains(provinceName)) continue;
                    Integer startPage = 0;
                    List<String> pageHref = Lists.newArrayList();
                    String dataHref = String.format("%s%s%s", typeHref, "&standardType=" + entry.getValue(), "&province=" + princeId);
                    Elements pageElements = getContent(String.format("%s%s", dataHref, "&start=" + startPage), "body", "");

                    if (pageElements == null) continue;
                    String data = pageElements.get(0).childNodes().get(0).toString().trim();
                    JSONObject jsonObject = JSON.parseObject(data);
                    Integer totalCount = JSON.parseObject(jsonObject.getString("totalCount"), Integer.class);
                    if (totalCount == 0) continue;
                    Integer totalPages = JSON.parseObject(jsonObject.getString("totalPages"), Integer.class);
                    for (int i = 0; i < totalPages; i++) {
                        String dataPageHref = String.format("%s%s", dataHref, "&start=" + i);
                        pageHref.add(dataPageHref);
                    }
                    strHrefs.put(String.format("%s_%s", entry.getKey(), provinceName), pageHref);
                }
            }

            //只取四川的
//            for (Map.Entry<String, String> entry : needContentType.entrySet()) {
//                Integer startPage = 0;
//                List<String> pageHref = Lists.newArrayList();
//                String dataHref = String.format("%s%s%s", typeHref, "&standardType=" + entry.getValue(), "&province=" + 23);
//                Elements pageElements = getContent(String.format("%s%s", dataHref, "&start=" + startPage), "body", "");
//                if (pageElements == null) continue;
//                String data = pageElements.get(0).childNodes().get(0).toString().trim();
//                JSONObject jsonObject = JSON.parseObject(data);
//                Integer totalCount = JSON.parseObject(jsonObject.getString("totalCount"), Integer.class);
//                if (totalCount == 0) continue;
//                Integer totalPages = JSON.parseObject(jsonObject.getString("totalPages"), Integer.class);
//                for (int i = 0; i < totalPages; i++) {
//                    String dataPageHref = String.format("%s%s", dataHref, "&start=" + i);
//                    pageHref.add(dataPageHref);
//                }
//                strHrefs.put(String.format("%s_%s", entry.getKey(), "四川"), pageHref);
//            }

            for (Map.Entry<String, List<String>> entry : strHrefs.entrySet()) {
                List<String> pageHref = entry.getValue();
                circ:
                for (String typeData : pageHref) {
                    Elements pageElements = getContent(typeData, "body", "");
                    String data = pageElements.get(0).childNodes().get(0).toString().trim();
                    JSONObject jsonObject = JSON.parseObject(data);
                    List<String> dataList = JSON.parseArray(jsonObject.getString("items"), String.class);
                    if (CollectionUtils.isEmpty(dataList)) continue;
                    String itemHrefStr = "https://paimai.caa123.org.cn/pages/lots/profession.html?";
                    for (String dataStr : dataList) {
                        ZGSFDto zgsfDto = JSON.parseObject(dataStr, ZGSFDto.class);
                        String itemHref = String.format("%s%s%s", itemHrefStr, "lotId=" + zgsfDto.getId(), "&meetId=" + zgsfDto.getMeetId());
                        if (zgsfDto.getEndTime().compareTo(date) < 0) break circ;
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        netInfoRecord.setSourceSiteUrl(itemHref);
                        netInfoRecord.setTitle(zgsfDto.getName());
                        netInfoRecord.setProvince(entry.getKey().substring(entry.getKey().indexOf("_") + 1));
                        netInfoRecord.setBeginTime(zgsfDto.getStartTime());
                        netInfoRecord.setEndTime(zgsfDto.getEndTime());
                        netInfoRecord.setType(entry.getKey().substring(0, entry.getKey().indexOf("_")));
                        netInfoRecord.setCurrentPrice(getRealMoney(zgsfDto.getNowPrice()));
                        netInfoRecord.setConsultPrice(getRealMoney(zgsfDto.getAssessPrice()));
                        netInfoRecord.setInitPrice(getRealMoney(zgsfDto.getStartPrice()));
                        netInfoRecord.setLiquidRatios(getLiquidRatios(zgsfDto.getNowPrice(), zgsfDto.getAssessPrice()));
                        String content = getContent(zgsfDto.getName(), entry.getKey().substring(0, entry.getKey().indexOf("_")), zgsfDto.getNowPrice(), zgsfDto.getAssessPrice(), zgsfDto.getStartPrice()
                                , DateUtils.format(zgsfDto.getEndTime(), DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(zgsfDto.getStartTime(), DateUtils.DATE_CHINESE_PATTERN));
                        netInfoRecord.setContent(content);
                        netInfoRecord.setSourceSiteName("中国拍卖行业协会网-标的");
                        this.addInfo(netInfoRecord);

                        String contentHref = itemHref.replace("pages/lots/profession.html?lotId=", "wt-web-ws/ws/0.1/lot/");
                        contentHref = contentHref.replace(String.format("%s%s", "&meetId=", zgsfDto.getMeetId()), "/introduction");
                        Elements contentBody = getContent(contentHref, "body", "");
                        if (contentBody.size() != 0 && contentBody != null) {
                            NetInfoRecordContent netInfoRecordContent = new NetInfoRecordContent();
                            netInfoRecordContent.setRecordId(netInfoRecord.getId());
                            if (contentBody.html().length() > 30000) {
                                netInfoRecordContent.setFullDescription(contentBody.html().substring(0, 30000));
                            } else {
                                netInfoRecordContent.setFullDescription(contentBody.html());
                            }
                            addInfoContent(netInfoRecordContent);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //来源公拍网
    public void getNetInfoFromGPW(Integer days) {
        try {
            Date date = getInstanceDate(days);//得到前1天
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //  String[] needContentType = new String[]{"房产", "土地", "股权", "无形资产", "林权矿权"};
            String[] needContentType = new String[]{"房产", "土地"};
            List<String> types = Arrays.asList(needContentType);
            Map<String, String> strHrefs = Maps.newHashMap();//用于记录地址
            String urlInfo = "http://s.gpai.net/sf/search.do?restate=3";

            Elements elements = getContent(urlInfo, ".s-tab-con", "");
            Elements a = elements.get(1).select("a");

            for (Element item : a) {
                String type = item.childNodes().get(0).toString().trim();
                String href = item.attributes().get("href").trim();
                if (StringUtils.equals(type, "不限")) continue;
                if (types.contains(type)) {
                    //省
                    Elements provinceElements = getContent(item.attributes().get("href").trim(), ".condition", "");
                    Elements provinces = provinceElements.get(0).select("li a");
                    for (Element province : provinces) {
                        String provinceName = province.childNodes().get(0).toString();
                        strHrefs.put(province.attributes().get("href").trim(), String.format("%s_%s", type, provinceName));
                    }
                }

            }

            circ:
            for (Map.Entry<String, String> entry : strHrefs.entrySet()) {
                String strHref = entry.getKey();
                Elements pageElements = getContent(strHref, ".filt-result-list", "");
                Elements items = pageElements.get(0).select("ul li");
                if (items.size() == 0 || items == null) continue;
                Elements pages = getContent(strHref, ".page-infos", "");
                Integer page = 1;
                if (pages.size() != 0) {
                    String pageStr = pages.get(0).childNodes().get(0).childNodes().get(0).toString();
                    String regEx = "[^0-9]";
                    Pattern p = Pattern.compile(regEx);
                    Matcher m = p.matcher(pageStr);
                    String pageNume = m.replaceAll("").trim();
                    page = Integer.valueOf(pageNume);
                }
                for (int i = 1; i <= page; i++) {
                    String href = String.format("%s%s", strHref, "&Page=" + i);
                    Elements elementsItem = getContent(href, ".filt-result-list", "");
                    Elements itemDatas = elementsItem.get(0).select("ul li");

                    for (Element itemData : itemDatas) {
                        Elements title = itemData.select(".item-tit");
                        String titleName = title.get(0).childNodes().get(0).childNodes().get(0).toString();
                        String itemHref = title.get(0).childNodes().get(0).attributes().get("href");
                        Elements info = itemData.select(".gpai-infos");
                        Date endTime = null;
                        String endTimeStr = "";
                        try {
                            endTimeStr = info.get(0).childNodes().get(8).childNodes().get(0).childNodes().get(0).toString();
                            if (StringUtil.isNotEmpty(endTimeStr) && endTimeStr.indexOf(":") > 0) {
                                endTime = sdf.parse(endTimeStr.substring(endTimeStr.indexOf("：") + 1, endTimeStr.length()));
                            }
                            if (endTime == null || endTime.compareTo(date) < 0) continue circ;
                            NetInfoRecord netInfoRecord = new NetInfoRecord();
                            netInfoRecord.setTitle(titleName);
                            netInfoRecord.setSourceSiteUrl(itemHref);
                            String consultPrice = info.get(0).childNodes().get(6).childNodes().get(2).childNodes().get(0).childNodes().get(0).toString();
                            //单位可能是元或万元
                            String moneyUnit = info.get(0).childNodes().get(6).childNodes().get(2).childNodes().get(1).toString();
                            if (moneyUnit.contains("万")) {
                                NumberFormat format = NumberFormat.getInstance();
                                BigDecimal bigDecimal = new BigDecimal(format.parse(consultPrice).doubleValue());
                                consultPrice = bigDecimal.multiply(new BigDecimal("10000")).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
                            }
                            Elements itemContent = getContent(itemHref, ".d-m-infos", "");
                            Elements currentPriceElements = itemContent.get(0).select(".price-red");
                            String currentPrice = currentPriceElements.get(0).childNodes().get(0).toString();
                            Elements tbody_tr = itemContent.get(0).select("tbody td");//consult_price
                            //起拍价
                            String initPrice = "";
                            try {
                                initPrice = tbody_tr.get(6).childNodes().get(1).childNodes().get(0).toString();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            netInfoRecord.setProvince(entry.getValue().substring(entry.getValue().indexOf("_") + 1));
                            netInfoRecord.setSourceSiteName("公拍网");
                            netInfoRecord.setEndTime(endTime);
                            netInfoRecord.setBeginTime(endTime);
                            netInfoRecord.setType(entry.getValue().substring(0, entry.getValue().indexOf("_")));
                            netInfoRecord.setCurrentPrice(getRealMoney(currentPrice));
                            netInfoRecord.setConsultPrice(getRealMoney(consultPrice));
                            netInfoRecord.setInitPrice(getRealMoney(initPrice));
                            netInfoRecord.setLiquidRatios(getLiquidRatios(currentPrice, consultPrice));
                            String content = getContent(titleName, netInfoRecord.getType(), currentPrice, consultPrice, initPrice
                                    , DateUtils.format(endTime, DateUtils.DATE_CHINESE_PATTERN), "");
                            netInfoRecord.setContent(content);
                            this.addInfo(netInfoRecord);


                            Elements contentBody = getContent(itemHref, ".d-article", "");
                            if (contentBody.size() != 0 && contentBody != null) {
                                NetInfoRecordContent netInfoRecordContent = new NetInfoRecordContent();
                                netInfoRecordContent.setRecordId(netInfoRecord.getId());
                                if (contentBody.get(1).html().length() > 30000) {
                                    netInfoRecordContent.setFullDescription(contentBody.get(1).html().substring(0, 30000));
                                } else {
                                    netInfoRecordContent.setFullDescription(contentBody.get(1).html());
                                }
                                addInfoContent(netInfoRecordContent);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //公共资源交易平台-成都(土地矿权)
    public void getNetInfoFromGGZYCD(Integer days) {
        try {
            logger.info("----公共资源交易平台-成都(土地矿权), start---------");
            Date date = DateUtils.addDay(DateUtils.now(), -days);//得到前1天
            //取得页数
            String pageHtml = getGGZYCDHtml("1");
            Document pageDoc = Jsoup.parse(pageHtml);
            Elements pageElements = pageDoc.select("#Pager a");
            Integer pageValue = 0;
            for (Element pageElement : pageElements) {
                if ("尾页".equals(pageElement.childNodes().get(0).toString())) {
                    String pageHref = pageElement.attributes().get("href");
                    //包含总页码的字符串
                    String pageTotalStr = pageHref.substring(pageHref.indexOf(","), pageHref.length() - 1);
                    String regEx = "[^0-9]";
                    Pattern p = Pattern.compile(regEx);
                    Matcher m = p.matcher(pageTotalStr);
                    String pageValueStr = m.replaceAll("").trim();
                    if (StringUtils.isNotEmpty(pageValueStr)) {
                        pageValue = Integer.valueOf(pageValueStr);
                    }
                }
            }
            if (pageValue > 1) {
                circ:
                for (int i = 1; i <= pageValue; i++) {
                    //内容信息
                    String html = getGGZYCDHtml(String.valueOf(i));
                    Document itemDoc = Jsoup.parse(html);
                    Elements elements = itemDoc.select(".row.contentitem");
                    for (Element item : elements) {
                        Elements publishtimeElement = item.select(".publishtime");
                        String publishtimeStr = publishtimeElement.get(0).childNodes().get(0).toString().substring(1).trim();
                        Date publishtime = DateUtils.parse(publishtimeStr);
                        if (publishtime == null) continue;
                        if (publishtime.compareTo(date) < 0) return;//一旦无效直接返回
                        Elements addressElement = item.select(".col-xs-1");
                        String address = addressElement.get(0).childNodes().get(0).toString();
                        String titleStr = item.select("a").get(0).childNodes().get(0).toString();
                        String link = item.select("a").get(0).attributes().get("href");
                        String title = String.format("%s%s", address, titleStr).replaceAll("\n", "");
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        netInfoRecord.setProvince("四川");
                        netInfoRecord.setCity("成都");
                        netInfoRecord.setType("土地矿权");
                        netInfoRecord.setSourceSiteUrl(link);
                        netInfoRecord.setBeginTime(publishtime);
                        netInfoRecord.setEndTime(publishtime);
                        netInfoRecord.setTitle(title);
                        netInfoRecord.setContent(netInfoRecord.getTitle());
                        netInfoRecord.setSourceSiteName("公共资源交易平台-成都");
                        netInfoRecord.setCreator("admin");
                        this.addInfo(netInfoRecord);

                    }
                }
            }
        } catch (Exception e) {
            logger.info("----公共资源交易平台-成都(土地矿权), error---------" + e.getMessage(), e);
        }
    }

    public String getGGZYCDHtml(String pageValue) {
        try {
            URL url = new URL("https://www.cdggzy.com/site/LandTrade/LandList.aspx");// 1. 获取访问地址URL

            HttpURLConnection connection = (HttpURLConnection) url // 2. 创建HttpURLConnection对象
                    .openConnection(); /* 3. 设置请求参数等 */
            connection.setRequestMethod("POST");// 请求方式
            connection.setDoOutput(true);// 设置是否输出
            connection.setDoInput(true);// 设置是否读入
            connection.setUseCaches(false);// 设置是否使用缓存
            connection.setInstanceFollowRedirects(true);
            // 设置使用标准编码格式编码参数的名-值对
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            connection.setRequestProperty("Host", "www.cdggzy.com");
            connection.setRequestProperty("Origin", "https://www.cdggzy.com");
            connection.setRequestProperty("Referer", "https://www.cdggzy.com/site/LandTrade/LandList.aspx");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
            // 连接
            connection.connect();
            String params = "ctl00%24ScriptManager1=ctl00%24ContentPlaceHolder1%24UpdatePanel1%7Cctl00%24ContentPlaceHolder1%24Pager&ctl00%24ContentPlaceHolder1%24displaytypeval=2&ctl00%24ContentPlaceHolder1%24displaystateval=0&ctl00%24ContentPlaceHolder1%24dealaddressval=0&ctl00%24ContentPlaceHolder1%24keyword=&__VIEWSTATE=%2FwEPDwULLTIwMzM1MDIzNTkPZBYCZg9kFgICAw9kFgQCAw9kFgICBw8WAh4EVGV4dAWPMTx1bCBjbGFzcz0nbmF2IG5hdi1waWxscyBuYXYtanVzdGlmaWVkJz48bGk%2BPGEgaHJlZj0nL2luZGV4LmFzcHgnPummlumhtTwvYT48c3Bhbj48L3NwYW4%2BPC9saT48bGkgIGNsYXNzPSJ1bF9tZW51Ij48YSAgaGVyZj0nIyc%2B5pS%2F5Yqh5YWs5byAPC9hPjx0YWJsZT4gPHRyPjx0ZD48ZGl2PuS4reW%2Fg%2BamguWGtTwvZGl2PjwvdGQ%2BPHRkICAgY2xhc3M9Im1ldW4taXRlbi1ib2R5Ij48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9HZW5lcmFsL0luZGV4LmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMTAwMDQiIHRhcmdldD0iX2JsYW5rIj7kuK3lv4PnroDku4s8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvR2VuZXJhbC9JbmRleC5hc3B4P2NpZD0wMDAxMDAwMTAwMDEwMDAyIiB0YXJnZXQ9Il9ibGFuayI%2B6aKG5a%2B85YiG5belPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0dlbmVyYWwvSW5kZXguYXNweD9jaWQ9MDAwMTAwMDEwMDAxMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuiBlOezu%2BaWueW8jzwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9HZW5lcmFsL0luZGV4LmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMTAwMDEiIHRhcmdldD0iX2JsYW5rIj7pg6jpl6jorr7nva48L2E%2BPC9kaXY%2BPC90ZD48L3RyPiA8dHI%2BPHRkPjxkaXY%2B5paw6Ze75Yqo5oCBPC9kaXY%2BPC90ZD48dGQgICBjbGFzcz0ibWV1bi1pdGVuLWJvZHkiPjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL09wZW5Hb3Zlcm5tZW50L0xpc3QuYXNweD9jaWQ9MDAwMTAwMDEwMDAyMDAwMSIgdGFyZ2V0PSJfYmxhbmsiPuW3peS9nOWKqOaAgTwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9PcGVuR292ZXJubWVudC9MaXN0LmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMjAwMDMiIHRhcmdldD0iX2JsYW5rIj7kv6HnlKjkv6Hmga88L2E%2BPC9kaXY%2BPC90ZD48L3RyPiA8dHI%2BPHRkPjxkaXY%2B5pS%2F5Yqh5YWs5byAPC9kaXY%2BPC90ZD48dGQgICBjbGFzcz0ibWV1bi1pdGVuLWJvZHkiPjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL09wZW5Hb3Zlcm5tZW50L0xpc3QuYXNweD9jaWQ9MDAwMTAwMDEwMDAzMDAwMSIgdGFyZ2V0PSJfYmxhbmsiPuWFrOW8gOS%2FnemanDwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9PcGVuR292ZXJubWVudC9MaXN0LmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMzAwMDIiIHRhcmdldD0iX2JsYW5rIj7orqHliJLmgLvnu5M8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvT3BlbkdvdmVybm1lbnQvTGlzdC5hc3B4P2NpZD0wMDAxMDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B5Lq65LqL5L%2Bh5oGvPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL09wZW5Hb3Zlcm5tZW50L0xpc3QuYXNweD9jaWQ9MDAwMTAwMDEwMDAzMDAwNCIgdGFyZ2V0PSJfYmxhbmsiPui0ouaUv%2Bi1hOmHkTwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHA6Ly9nay5jaGVuZ2R1Lmdvdi5jbi9vcGVuQXBwbHkvaW5kZXguYWN0aW9uP2NpZD0wMDAxMDAwMTAwMDMwMDA1IiB0YXJnZXQ9Il9ibGFuayI%2B5L6d55Sz6K%2B35YWs5byAPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cDovL2drLmNoZW5nZHUuZ292LmNuL29wZW5TdWdnZXN0aW9uQm94L2luZGV4LmFjdGlvbj9jaWQ9MDAwMTAwMDEwMDAzMDAwNiIgdGFyZ2V0PSJfYmxhbmsiPuWFrOW8gOaEj%2BingeeusTwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHA6Ly9nay5jaGVuZ2R1Lmdvdi5jbi9nb3ZJbmZvUHViL2RlcHQuYWN0aW9uP2NsYXNzSWQ9MDcwMzY2IiB0YXJnZXQ9Il9ibGFuayI%2B5L%2Bh5oGv5YWs5byA5oyH5Y2XPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL1BsdXMvQWNjZXB0RGF0YS5hc3B4P2NpZD0wMDAxMDAwMTAwMDMwMDA5IiB0YXJnZXQ9Il9ibGFuayI%2B5Yqe5LqL57uf6K6hPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cDovL3d3dy5jaGVuZ2R1Lmdvdi5jbi9jaGVuZ2R1L2NkbWRtL3htZG1faW5kZXguc2h0bWw%2FY2lkPTAwMDEwMDAxMDAwMzAwMDgiIHRhcmdldD0iX2JsYW5rIj7lhZrpo47mlL%2Fpo47ng63nur88L2E%2BPC9kaXY%2BPC90ZD48L3RyPjwvdGFibGU%2BPC9saT48bGkgIGNsYXNzPSJ1bF9tZW51Ij48YSAgaGVyZj0nIyc%2B5Lia5Yqh5Yqe55CGPC9hPjx0YWJsZT4gPHRyPjx0ZD48ZGl2PuWPl%2BeQhuS4muWKoTwvZGl2PjwvdGQ%2BPHRkICAgY2xhc3M9Im1ldW4taXRlbi1ib2R5Ij48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vY2VudGVyL2luZGV4LmFzcHg%2FY2lkPTAwMDIwMDAxMDAyMDAwMSIgdGFyZ2V0PSJfYmxhbmsiPumhueebrueZu%2BiusDwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9TaXRlU2VhcmNoL25ld2luZGV4LmFzcHg%2FY2lkPTAwMDIwMDAxMDAyMDAwMiIgdGFyZ2V0PSJfYmxhbmsiPuWcuuWcsOafpeivojwvYT48L2Rpdj48L3RkPjwvdHI%2BIDx0cj48dGQ%2BPGRpdj7kuqTmmJPkv6Hmga88L2Rpdj48L3RkPjx0ZCAgIGNsYXNzPSJtZXVuLWl0ZW4tYm9keSI%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvSlNHQy9MaXN0LmFzcHgiIHRhcmdldD0iX2JsYW5rIj7lt6XnqIvlu7rorr48L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvTm90aWNlL1pGQ0cvTm90aWNlVmVyc2lvbk9uZUxpc3QuYXNweCIgdGFyZ2V0PSJfYmxhbmsiPuaUv%2BW6nOmHh%2Bi0rTwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9MYW5kVHJhZGUvTGFuZExpc3QuYXNweCIgdGFyZ2V0PSJfYmxhbmsiPuWcn%2BWcsOefv%2BadgzwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9Bc3NldFJlc291cmNlL0RlYWxOb3RpY2VMaXN0LmFzcHgiIHRhcmdldD0iX2JsYW5rIj7otYTkuqfotYTmupA8L2E%2BPC9kaXY%2BPC90ZD48L3RyPiA8dHI%2BPHRkPjxkaXY%2B5pu05aSa5Lia5YqhPC9kaXY%2BPC90ZD48dGQgICBjbGFzcz0ibWV1bi1pdGVuLWJvZHkiPjxkaXY%2BPGEgaHJlZj0iaHR0cDovL3d3dy5jZGdnenkuY29tL21hbGwvSW5kZXguYXNweD9jaWQ9MDAwMjAwMDEwMDMwMDAxIiB0YXJnZXQ9Il9ibGFuayI%2B5pS%2F5bqc6YeH6LSt55S15a2Q5ZWG5Z%2BOPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9Mb2dpbi5hc3B4P2NpZD0wMDAyMDAwMTAwMzAwMDIiIHRhcmdldD0iX2JsYW5rIj7otYTkuqfotYTmupDnvZHkuIrnq57ku7c8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvQmFua0JvcnJvdy9JbmRleC5hc3B4P2NpZD0wMDAyMDAwMTAwMzAwMDQiIHRhcmdldD0iX2JsYW5rIj7mlL%2Fph4fkv6HnlKjmi4Xkv53ono3otYQ8L2E%2BPC9kaXY%2BPC90ZD48L3RyPiA8dHI%2BPHRkPjxkaXY%2B5pyN5Yqh5oyH5byVPC9kaXY%2BPC90ZD48dGQgICBjbGFzcz0ibWV1bi1pdGVuLWJvZHkiPjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL1BsdXMvTm90aWNlTGlzdC5hc3B4P2NpZD0wMDAyMDAwMTAwNDAwMDEiIHRhcmdldD0iX2JsYW5rIj7pgJrnn6XlhazlkYo8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvSW5zdHJ1Y3Rpb24vSW5kZXguYXNweD9jaWQ9MDAwMjAwMDEwMDQwMDAyIiB0YXJnZXQ9Il9ibGFuayI%2B5Yqe5LqL5oyH5Y2XPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL1BvbGljaWVzYW5kcmVndWxhdGlvbnMvSW5kZXguYXNweD9jaWQ9MDAwMjAwMDEwMDQwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B5pS%2F562W5rOV6KeEPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0Nhb3p1by9pbmRleC5hc3B4P2NpZD0wMDAyMDAwMTAwNDAwMDQiIHRhcmdldD0iX2JsYW5rIj7mk43kvZzmiYvlhow8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvRG93bkNlbnRlci5hc3B4P2NpZD0wMDAyMDAwMTAwNDAwMDUiIHRhcmdldD0iX2JsYW5rIj7kuIvovb3kuJPljLo8L2E%2BPC9kaXY%2BPC90ZD48L3RyPjwvdGFibGU%2BPC9saT48bGkgY2xhc3M9InVsX21lbnUiPjxhIHRhcmdldD0iX2JsYW5rIj7kupLliqjkuqTmtYE8L2E%2BPHVsPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0ludGVyYWN0aW9uL0ludGVyYWN0aW9uTGlzdE5ldzEuYXNweCIgdGFyZ2V0PSJfYmxhbmsiPuS4u%2BS7u%2BS%2FoeeusTwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cDovLzIwMTMuY2RnZ3p5LmNvbS9hcHAxL3R3by93amRjLmpzcD9jaWQ9MDAwMTAwMDIwMDAyIiB0YXJnZXQ9Il9ibGFuayI%2B6LCD5p%2Bl5b6B6ZuGPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwOi8vd2VpYm8uY29tL3UvMzk3MzMzODgzNiMhL3UvMzk3MzMzODgzNj9pc19ob3Q9MT9jaWQ9MDAwMTAwMDIwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B5paw5rWq5b6u5Y2aPC9hPjwvbGk%2BPC91bD48L2xpPjxsaSBjbGFzcz0idWxfbWVudSI%2BPGEgdGFyZ2V0PSJfYmxhbmsiPuWIhuS4reW%2FgzwvYT48dWw%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL2xvbmdxdWFueWk%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPum%2Bmeaziempv%2BWMujwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9xaW5nYmFpamlhbmc%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPumdkueZveaxn%2BWMujwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS94aW5kdT9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B5paw6YO95Yy6PC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3dlbmppYW5nP2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7muKnmsZ%2FljLo8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2h1YW5nbGl1P2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7lj4zmtYHljLo8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vcGlkdT9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6YOr6YO95Yy6PC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL2ppYW55YW5nP2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7nroDpmLPluII8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vZHVqaWFuZ3lhbj9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6YO95rGf5aCw5biCPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3Blbmd6aG91P2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7lva3lt57luII8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vcWlvbmdsYWk%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPumCm%2BW0g%2BW4gjwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9jaG9uZ3pob3U%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuW0h%2BW3nuW4gjwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9qaW5ndGFuZz9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6YeR5aCC5Y6%2FPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3hpbmppbj9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B5paw5rSl5Y6%2FPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL2RheWk%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuWkp%2BmCkeWOvzwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9wdWppYW5nP2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7okrLmsZ%2Fljr88L2E%2BPC9saT48L3VsPjwvbGk%2BIDwvdWw%2BZAIHD2QWBmYPFgIfAAWKAemhtemdouWKoOi9veaAu%2BaXtumXtO%2B8mjkz5q%2Br56eSPGJyLz7mn6Xor6LliJfooajmgLvml7bpl7TvvJo3OOavq%2Benkjxici8%2B5p%2Bl6K%2Bi5p2h5Lu25oC75pe26Ze077yaMTXmr6vnp5I8YnIvPuacrOacuklQ77yaMTc4LjE4LjEuNzg8YnIvPmQCBQ8WAh4LXyFJdGVtQ291bnQCEBYgZg9kFgJmDxUCBjUxMDEwMQnluILmnKznuqdkAgEPZBYCZg8VAgY1MTAxMTIM6b6Z5rOJ6am%2F5Yy6ZAICD2QWAmYPFQIGNTEwMTEzDOmdkueZveaxn%2BWMumQCAw9kFgJmDxUCBjUxMDExNAnmlrDpg73ljLpkAgQPZBYCZg8VAgY1MTAxMTUJ5rip5rGf5Yy6ZAIFD2QWAmYPFQIGNTEwMTE2CeWPjOa1geWMumQCBg9kFgJmDxUCBjUxMDE4NQnnroDpmLPluIJkAgcPZBYCZg8VAgY1MTAxODEM6YO95rGf5aCw5biCZAIID2QWAmYPFQIGNTEwMTgyCeW9reW3nuW4gmQCCQ9kFgJmDxUCBjUxMDE4MwnpgpvltIPluIJkAgoPZBYCZg8VAgY1MTAxODQJ5bSH5bee5biCZAILD2QWAmYPFQIGNTEwMTI0CemDq%2BmDveWMumQCDA9kFgJmDxUCBjUxMDEyMQnph5HloILljr9kAg0PZBYCZg8VAgY1MTAxMzIJ5paw5rSl5Y6%2FZAIOD2QWAmYPFQIGNTEwMTI5CeWkp%2BmCkeWOv2QCDw9kFgJmDxUCBjUxMDEzMQnokrLmsZ%2Fljr9kAggPZBYCZg9kFgYCAw8PFgIfAAUEMi85NmRkAgkPFgIfAQIKFhZmD2QWBmYPFQEJ5rip5rGf5Yy6ZAIBDw8WAh4LTmF2aWdhdGVVcmwFYGh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9MYW5kVHJhZGUvTGFuZE5vdGljZUNvbnRlbnQuYXNweD9pZD1GN0VDMDdENTEzM0Q0MkY5QjQ4RjVBMEE5NDdGNTQxOWQWAmYPFQE%2F5oyC54mM5Lya57uT5p6c5LiA6KeI6KGoKDIwMjDlubQwMeaciDEz5pel5YiwMjAyMOW5tDAy5pyIMDPml6UpZAICDxUCCjIwMjAtMDItMDMpPGRpdiBjbGFzcz0iIGVudGVyaW5nIj4mbmJzcDsmbmJzcDs8L2Rpdj5kAgEPZBYGZg8VAQzpnZLnmb3msZ%2FljLpkAgEPDxYCHwIFYGh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9MYW5kVHJhZGUvTGFuZE5vdGljZUNvbnRlbnQuYXNweD9pZD0wREE4N0RCNjBERDA0OUYwQUM3MDg1QUVGMkFGMEQ1N2QWAmYPFQE%2F5oyC54mM5Lya57uT5p6c5LiA6KeI6KGoKDIwMjDlubQwMeaciDA55pel5YiwMjAyMOW5tDAx5pyIMjLml6UpZAICDxUCCjIwMjAtMDEtMjIpPGRpdiBjbGFzcz0iIGVudGVyaW5nIj4mbmJzcDsmbmJzcDs8L2Rpdj5kAgIPZBYGZg8VAQnluILmnKznuqdkAgEPDxYCHwIFYGh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9MYW5kVHJhZGUvTGFuZE5vdGljZUNvbnRlbnQuYXNweD9pZD1iZDY5NWIxMWRhMDY0YWQwYTlmN2U1MTgzZjk5ZGNmM2QWAmYPFQEx5ouN5Y2W5Lya5oiQ5Lqk57uT5p6c5LiA6KeI6KGoKDIwMjDlubQwMeaciDIw5pelKWQCAg8VAgoyMDIwLTAxLTIwKTxkaXYgY2xhc3M9IiBlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAIDD2QWBmYPFQEJ5biC5pys57qnZAIBDw8WAh8CBWBodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvTGFuZFRyYWRlL0xhbmROb3RpY2VDb250ZW50LmFzcHg%2FaWQ9ZDcwOGU5MDE4ZDE1NDJhMGEyNzFkYTVhZjFhMDU0N2JkFgJmDxUBP%2BaMgueJjOS8mue7k%2BaenOS4gOiniOihqCgyMDIw5bm0MDHmnIgwN%2BaXpeWIsDIwMjDlubQwMeaciDIw5pelKWQCAg8VAgoyMDIwLTAxLTIwKTxkaXYgY2xhc3M9IiBlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAIED2QWBmYPFQEJ5biC5pys57qnZAIBDw8WAh8CBWBodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvTGFuZFRyYWRlL0xhbmROb3RpY2VDb250ZW50LmFzcHg%2FaWQ9NjdlYTRmYWFkYjgyNDY0OGEwNWNkMDFmN2Y1ZjAwYzlkFgJmDxUBP%2BaMgueJjOS8mue7k%2BaenOS4gOiniOihqCgyMDIw5bm0MDHmnIgwN%2BaXpeWIsDIwMjDlubQwMeaciDIw5pelKWQCAg8VAgoyMDIwLTAxLTIwKTxkaXYgY2xhc3M9IiBlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAIFD2QWBmYPFQEJ5biC5pys57qnZAIBDw8WAh8CBWBodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvTGFuZFRyYWRlL0xhbmROb3RpY2VDb250ZW50LmFzcHg%2FaWQ9ZDM2ZjM1MTQ0ZmEyNGYxYjhjZGI3ODhhZTc1NWM5YjNkFgJmDxUBMeaLjeWNluS8muaIkOS6pOe7k%2BaenOS4gOiniOihqCgyMDIw5bm0MDHmnIgyMOaXpSlkAgIPFQIKMjAyMC0wMS0yMCk8ZGl2IGNsYXNzPSIgZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCBg9kFgZmDxUBCeeugOmYs%2BW4gmQCAQ8PFgIfAgVgaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTm90aWNlQ29udGVudC5hc3B4P2lkPThGQkI4QjVFNjIzQjQ1QURCQjQyQTJCNUIyMzkxRUNEZBYCZg8VAT%2FmjILniYzkvJrnu5PmnpzkuIDop4jooagoMjAyMOW5tDAx5pyIMDfml6XliLAyMDIw5bm0MDHmnIgyMOaXpSlkAgIPFQIKMjAyMC0wMS0yMCk8ZGl2IGNsYXNzPSIgZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCBw9kFgZmDxUBCeeugOmYs%2BW4gmQCAQ8PFgIfAgVgaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTm90aWNlQ29udGVudC5hc3B4P2lkPTNDN0U3RkYzMUZENzRDQUE4QTQyMDZCMzBGQURDOUU3ZBYCZg8VAT%2FmjILniYzkvJrnu5PmnpzkuIDop4jooagoMjAxOeW5tDEy5pyIMzDml6XliLAyMDIw5bm0MDHmnIgxNOaXpSlkAgIPFQIKMjAyMC0wMS0xNyk8ZGl2IGNsYXNzPSIgZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCCA9kFgZmDxUBCeWPjOa1geWMumQCAQ8PFgIfAgVgaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTm90aWNlQ29udGVudC5hc3B4P2lkPTlFRDFGM0YzMTAzMzRFOTc4OTJGOTgzNkUyMzE4MUEzZBYCZg8VAT%2FmjILniYzkvJrnu5PmnpzkuIDop4jooagoMjAyMOW5tDAx5pyIMDLml6XliLAyMDIw5bm0MDHmnIgxNuaXpSlkAgIPFQIKMjAyMC0wMS0xNik8ZGl2IGNsYXNzPSIgZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCCQ9kFgZmDxUBCeW4guacrOe6p2QCAQ8PFgIfAgVgaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTm90aWNlQ29udGVudC5hc3B4P2lkPWZjMGJjOTliOTEwODQwZGY5YjNhZjFlN2I1YzI3MTMwZBYCZg8VATHmi43ljZbkvJrmiJDkuqTnu5PmnpzkuIDop4jooagoMjAyMOW5tDAx5pyIMTbml6UpZAICDxUCCjIwMjAtMDEtMTYpPGRpdiBjbGFzcz0iIGVudGVyaW5nIj4mbmJzcDsmbmJzcDs8L2Rpdj5kAgoPZBYCAgEPDxYCHgdWaXNpYmxlaGRkAgsPDxYGHghQYWdlU2l6ZQIKHhBDdXJyZW50UGFnZUluZGV4AgIeC1JlY29yZGNvdW50Ar8HZGRkhPP9ly9J4XaUUQdL9mqWROm1TAk%3D&__VIEWSTATEGENERATOR=87A20B68&__EVENTTARGET=ctl00%24ContentPlaceHolder1%24Pager&__EVENTARGUMENT=" + pageValue + "&__EVENTVALIDATION=%2FwEdAAjZVw3XtydTzJfC%2BNtk26mOZXbjphD4J6Ci0P2UUXCmZQp8fmeAe3E%2BZ%2BlyrRcok%2FuMO7fmjkJSfq6Zbgk2kTWnZi7H72kkAvPqROrOG28FXID8g%2FxeCzDZJOPgpGV4zViznYSIw3B963y%2FxzaOhgoHdty0mXvz7f%2B9YtBAL8kV3dDtGgtaD%2FGMMc%2BQUEoJCgkEmkNJ&__ASYNCPOST=true&";
            OutputStream out = connection.getOutputStream();
            out.write(params.getBytes());
            out.flush();
            out.close();
            // 从连接中读取响应信息
            StringBuilder sb = new StringBuilder();
            int code = connection.getResponseCode();
            if (code == 200) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
            }
            connection.disconnect(); // 5. 断开连接
            // 处理结果
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //公共资源交易平台-成都（资产资源）
    public void getNetInfoFromGGZYCD2(Integer days) {
        try {
            logger.info("----公共资源交易平台-成都(资产资源), start---------");
            Date date = DateUtils.addDay(DateUtils.now(), -days);//得到前1天
            //取得页数
            String pageHtml = getGGZYCDHtml2("1");
            Document pageDoc = Jsoup.parse(pageHtml);
            Elements pageElements = pageDoc.select("#Pager a");
            Integer pageValue = 0;
            for (Element pageElement : pageElements) {
                if ("尾页".equals(pageElement.childNodes().get(0).toString())) {
                    String pageHref = pageElement.attributes().get("href");
                    //包含总页码的字符串
                    String pageTotalStr = pageHref.substring(pageHref.indexOf(","), pageHref.length() - 1);
                    String regEx = "[^0-9]";
                    Pattern p = Pattern.compile(regEx);
                    Matcher m = p.matcher(pageTotalStr);
                    String pageValueStr = m.replaceAll("").trim();
                    if (StringUtils.isNotEmpty(pageValueStr)) {
                        pageValue = Integer.valueOf(pageValueStr);
                    }
                }
            }
            if (pageValue > 1) {
                circ:
                for (int i = 1; i <= pageValue; i++) {
                    //内容信息
                    String html = getGGZYCDHtml2(String.valueOf(i));
                    Document itemDoc = Jsoup.parse(html);
                    Elements elements = itemDoc.select(".row.contentitem");
                    for (Element item : elements) {
                        Elements publishtimeElement = item.select(".publishtime");
                        String publishtimeStr = publishtimeElement.get(0).childNodes().get(0).toString().substring(1).trim();
                        Date publishtime = DateUtils.parse(publishtimeStr);
                        if (publishtime == null) continue;
                        if (publishtime.compareTo(date) < 0) return;//一旦无效直接返回
                        Elements addressElement = item.select(".col-xs-1");
                        String address = addressElement.get(0).childNodes().get(0).toString();
                        String titleStr = item.select("a").get(0).childNodes().get(0).toString();
                        String link = item.select("a").get(0).attributes().get("href");
                        String title = String.format("%s%s", address, titleStr).replaceAll("\n", "");
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        netInfoRecord.setProvince("四川");
                        netInfoRecord.setCity("成都");
                        netInfoRecord.setType("资产资源");
                        netInfoRecord.setSourceSiteUrl(link);
                        netInfoRecord.setBeginTime(publishtime);
                        netInfoRecord.setEndTime(publishtime);
                        netInfoRecord.setTitle(title);
                        netInfoRecord.setContent(netInfoRecord.getTitle());
                        netInfoRecord.setSourceSiteName("公共资源交易平台-成都");
                        netInfoRecord.setCreator("admin");
                        this.addInfo(netInfoRecord);

                    }
                }
            }
        } catch (Exception e) {
            logger.info("----公共资源交易平台-成都(资产资源), error---------" + e.getMessage(), e);
        }

    }

    public String getGGZYCDHtml2(String pageValue) {
        try {
            URL url = new URL("https://www.cdggzy.com/site/AssetResource/DealNoticeList.aspx");// 1. 获取访问地址URL
            HttpURLConnection connection = (HttpURLConnection) url // 2. 创建HttpURLConnection对象
                    .openConnection(); /* 3. 设置请求参数等 */
            connection.setRequestMethod("POST");// 请求方式
            connection.setDoOutput(true);// 设置是否输出
            connection.setDoInput(true);// 设置是否读入
            connection.setUseCaches(false);// 设置是否使用缓存
            connection.setInstanceFollowRedirects(true);
            // 设置使用标准编码格式编码参数的名-值对
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            connection.setRequestProperty("Host", "www.cdggzy.com");
            connection.setRequestProperty("Origin", "https://www.cdggzy.com");
            connection.setRequestProperty("Referer", "https://www.cdggzy.com/site/LandTrade/LandList.aspx");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
            // 连接
            connection.connect();
            String params = "ctl00%24ScriptManager1=ctl00%24ContentPlaceHolder1%24UpdatePanel1%7Cctl00%24ContentPlaceHolder1%24Pager&ctl00%24ContentPlaceHolder1%24displaytypeval=2&ctl00%24ContentPlaceHolder1%24displaystateval=0&ctl00%24ContentPlaceHolder1%24dealaddressval=0&ctl00%24ContentPlaceHolder1%24keyword=&__VIEWSTATE=%2FwEPDwULLTE3NDkwMTQ2NTQPZBYCZg9kFgICAw9kFgQCAw9kFgICBw8WAh4EVGV4dAWPMTx1bCBjbGFzcz0nbmF2IG5hdi1waWxscyBuYXYtanVzdGlmaWVkJz48bGk%2BPGEgaHJlZj0nL2luZGV4LmFzcHgnPummlumhtTwvYT48c3Bhbj48L3NwYW4%2BPC9saT48bGkgIGNsYXNzPSJ1bF9tZW51Ij48YSAgaGVyZj0nIyc%2B5pS%2F5Yqh5YWs5byAPC9hPjx0YWJsZT4gPHRyPjx0ZD48ZGl2PuS4reW%2Fg%2BamguWGtTwvZGl2PjwvdGQ%2BPHRkICAgY2xhc3M9Im1ldW4taXRlbi1ib2R5Ij48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9HZW5lcmFsL0luZGV4LmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMTAwMDQiIHRhcmdldD0iX2JsYW5rIj7kuK3lv4PnroDku4s8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvR2VuZXJhbC9JbmRleC5hc3B4P2NpZD0wMDAxMDAwMTAwMDEwMDAyIiB0YXJnZXQ9Il9ibGFuayI%2B6aKG5a%2B85YiG5belPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0dlbmVyYWwvSW5kZXguYXNweD9jaWQ9MDAwMTAwMDEwMDAxMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuiBlOezu%2BaWueW8jzwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9HZW5lcmFsL0luZGV4LmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMTAwMDEiIHRhcmdldD0iX2JsYW5rIj7pg6jpl6jorr7nva48L2E%2BPC9kaXY%2BPC90ZD48L3RyPiA8dHI%2BPHRkPjxkaXY%2B5paw6Ze75Yqo5oCBPC9kaXY%2BPC90ZD48dGQgICBjbGFzcz0ibWV1bi1pdGVuLWJvZHkiPjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL09wZW5Hb3Zlcm5tZW50L0xpc3QuYXNweD9jaWQ9MDAwMTAwMDEwMDAyMDAwMSIgdGFyZ2V0PSJfYmxhbmsiPuW3peS9nOWKqOaAgTwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9PcGVuR292ZXJubWVudC9MaXN0LmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMjAwMDMiIHRhcmdldD0iX2JsYW5rIj7kv6HnlKjkv6Hmga88L2E%2BPC9kaXY%2BPC90ZD48L3RyPiA8dHI%2BPHRkPjxkaXY%2B5pS%2F5Yqh5YWs5byAPC9kaXY%2BPC90ZD48dGQgICBjbGFzcz0ibWV1bi1pdGVuLWJvZHkiPjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL09wZW5Hb3Zlcm5tZW50L0xpc3QuYXNweD9jaWQ9MDAwMTAwMDEwMDAzMDAwMSIgdGFyZ2V0PSJfYmxhbmsiPuWFrOW8gOS%2FnemanDwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9PcGVuR292ZXJubWVudC9MaXN0LmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMzAwMDIiIHRhcmdldD0iX2JsYW5rIj7orqHliJLmgLvnu5M8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvT3BlbkdvdmVybm1lbnQvTGlzdC5hc3B4P2NpZD0wMDAxMDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B5Lq65LqL5L%2Bh5oGvPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL09wZW5Hb3Zlcm5tZW50L0xpc3QuYXNweD9jaWQ9MDAwMTAwMDEwMDAzMDAwNCIgdGFyZ2V0PSJfYmxhbmsiPui0ouaUv%2Bi1hOmHkTwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHA6Ly9nay5jaGVuZ2R1Lmdvdi5jbi9vcGVuQXBwbHkvaW5kZXguYWN0aW9uP2NpZD0wMDAxMDAwMTAwMDMwMDA1IiB0YXJnZXQ9Il9ibGFuayI%2B5L6d55Sz6K%2B35YWs5byAPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cDovL2drLmNoZW5nZHUuZ292LmNuL29wZW5TdWdnZXN0aW9uQm94L2luZGV4LmFjdGlvbj9jaWQ9MDAwMTAwMDEwMDAzMDAwNiIgdGFyZ2V0PSJfYmxhbmsiPuWFrOW8gOaEj%2BingeeusTwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHA6Ly9nay5jaGVuZ2R1Lmdvdi5jbi9nb3ZJbmZvUHViL2RlcHQuYWN0aW9uP2NsYXNzSWQ9MDcwMzY2IiB0YXJnZXQ9Il9ibGFuayI%2B5L%2Bh5oGv5YWs5byA5oyH5Y2XPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL1BsdXMvQWNjZXB0RGF0YS5hc3B4P2NpZD0wMDAxMDAwMTAwMDMwMDA5IiB0YXJnZXQ9Il9ibGFuayI%2B5Yqe5LqL57uf6K6hPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cDovL3d3dy5jaGVuZ2R1Lmdvdi5jbi9jaGVuZ2R1L2NkbWRtL3htZG1faW5kZXguc2h0bWw%2FY2lkPTAwMDEwMDAxMDAwMzAwMDgiIHRhcmdldD0iX2JsYW5rIj7lhZrpo47mlL%2Fpo47ng63nur88L2E%2BPC9kaXY%2BPC90ZD48L3RyPjwvdGFibGU%2BPC9saT48bGkgIGNsYXNzPSJ1bF9tZW51Ij48YSAgaGVyZj0nIyc%2B5Lia5Yqh5Yqe55CGPC9hPjx0YWJsZT4gPHRyPjx0ZD48ZGl2PuWPl%2BeQhuS4muWKoTwvZGl2PjwvdGQ%2BPHRkICAgY2xhc3M9Im1ldW4taXRlbi1ib2R5Ij48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vY2VudGVyL2luZGV4LmFzcHg%2FY2lkPTAwMDIwMDAxMDAyMDAwMSIgdGFyZ2V0PSJfYmxhbmsiPumhueebrueZu%2BiusDwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9TaXRlU2VhcmNoL25ld2luZGV4LmFzcHg%2FY2lkPTAwMDIwMDAxMDAyMDAwMiIgdGFyZ2V0PSJfYmxhbmsiPuWcuuWcsOafpeivojwvYT48L2Rpdj48L3RkPjwvdHI%2BIDx0cj48dGQ%2BPGRpdj7kuqTmmJPkv6Hmga88L2Rpdj48L3RkPjx0ZCAgIGNsYXNzPSJtZXVuLWl0ZW4tYm9keSI%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvSlNHQy9MaXN0LmFzcHgiIHRhcmdldD0iX2JsYW5rIj7lt6XnqIvlu7rorr48L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvTm90aWNlL1pGQ0cvTm90aWNlVmVyc2lvbk9uZUxpc3QuYXNweCIgdGFyZ2V0PSJfYmxhbmsiPuaUv%2BW6nOmHh%2Bi0rTwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9MYW5kVHJhZGUvTGFuZExpc3QuYXNweCIgdGFyZ2V0PSJfYmxhbmsiPuWcn%2BWcsOefv%2BadgzwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9Bc3NldFJlc291cmNlL0RlYWxOb3RpY2VMaXN0LmFzcHgiIHRhcmdldD0iX2JsYW5rIj7otYTkuqfotYTmupA8L2E%2BPC9kaXY%2BPC90ZD48L3RyPiA8dHI%2BPHRkPjxkaXY%2B5pu05aSa5Lia5YqhPC9kaXY%2BPC90ZD48dGQgICBjbGFzcz0ibWV1bi1pdGVuLWJvZHkiPjxkaXY%2BPGEgaHJlZj0iaHR0cDovL3d3dy5jZGdnenkuY29tL21hbGwvSW5kZXguYXNweD9jaWQ9MDAwMjAwMDEwMDMwMDAxIiB0YXJnZXQ9Il9ibGFuayI%2B5pS%2F5bqc6YeH6LSt55S15a2Q5ZWG5Z%2BOPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9Mb2dpbi5hc3B4P2NpZD0wMDAyMDAwMTAwMzAwMDIiIHRhcmdldD0iX2JsYW5rIj7otYTkuqfotYTmupDnvZHkuIrnq57ku7c8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvQmFua0JvcnJvdy9JbmRleC5hc3B4P2NpZD0wMDAyMDAwMTAwMzAwMDQiIHRhcmdldD0iX2JsYW5rIj7mlL%2Fph4fkv6HnlKjmi4Xkv53ono3otYQ8L2E%2BPC9kaXY%2BPC90ZD48L3RyPiA8dHI%2BPHRkPjxkaXY%2B5pyN5Yqh5oyH5byVPC9kaXY%2BPC90ZD48dGQgICBjbGFzcz0ibWV1bi1pdGVuLWJvZHkiPjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL1BsdXMvTm90aWNlTGlzdC5hc3B4P2NpZD0wMDAyMDAwMTAwNDAwMDEiIHRhcmdldD0iX2JsYW5rIj7pgJrnn6XlhazlkYo8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvSW5zdHJ1Y3Rpb24vSW5kZXguYXNweD9jaWQ9MDAwMjAwMDEwMDQwMDAyIiB0YXJnZXQ9Il9ibGFuayI%2B5Yqe5LqL5oyH5Y2XPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL1BvbGljaWVzYW5kcmVndWxhdGlvbnMvSW5kZXguYXNweD9jaWQ9MDAwMjAwMDEwMDQwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B5pS%2F562W5rOV6KeEPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0Nhb3p1by9pbmRleC5hc3B4P2NpZD0wMDAyMDAwMTAwNDAwMDQiIHRhcmdldD0iX2JsYW5rIj7mk43kvZzmiYvlhow8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvRG93bkNlbnRlci5hc3B4P2NpZD0wMDAyMDAwMTAwNDAwMDUiIHRhcmdldD0iX2JsYW5rIj7kuIvovb3kuJPljLo8L2E%2BPC9kaXY%2BPC90ZD48L3RyPjwvdGFibGU%2BPC9saT48bGkgY2xhc3M9InVsX21lbnUiPjxhIHRhcmdldD0iX2JsYW5rIj7kupLliqjkuqTmtYE8L2E%2BPHVsPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0ludGVyYWN0aW9uL0ludGVyYWN0aW9uTGlzdE5ldzEuYXNweCIgdGFyZ2V0PSJfYmxhbmsiPuS4u%2BS7u%2BS%2FoeeusTwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cDovLzIwMTMuY2RnZ3p5LmNvbS9hcHAxL3R3by93amRjLmpzcD9jaWQ9MDAwMTAwMDIwMDAyIiB0YXJnZXQ9Il9ibGFuayI%2B6LCD5p%2Bl5b6B6ZuGPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwOi8vd2VpYm8uY29tL3UvMzk3MzMzODgzNiMhL3UvMzk3MzMzODgzNj9pc19ob3Q9MT9jaWQ9MDAwMTAwMDIwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B5paw5rWq5b6u5Y2aPC9hPjwvbGk%2BPC91bD48L2xpPjxsaSBjbGFzcz0idWxfbWVudSI%2BPGEgdGFyZ2V0PSJfYmxhbmsiPuWIhuS4reW%2FgzwvYT48dWw%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL2xvbmdxdWFueWk%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPum%2Bmeaziempv%2BWMujwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9xaW5nYmFpamlhbmc%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPumdkueZveaxn%2BWMujwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS94aW5kdT9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B5paw6YO95Yy6PC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3dlbmppYW5nP2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7muKnmsZ%2FljLo8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2h1YW5nbGl1P2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7lj4zmtYHljLo8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vcGlkdT9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6YOr6YO95Yy6PC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL2ppYW55YW5nP2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7nroDpmLPluII8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vZHVqaWFuZ3lhbj9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6YO95rGf5aCw5biCPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3Blbmd6aG91P2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7lva3lt57luII8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vcWlvbmdsYWk%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPumCm%2BW0g%2BW4gjwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9jaG9uZ3pob3U%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuW0h%2BW3nuW4gjwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9qaW5ndGFuZz9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6YeR5aCC5Y6%2FPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3hpbmppbj9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B5paw5rSl5Y6%2FPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL2RheWk%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuWkp%2BmCkeWOvzwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9wdWppYW5nP2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7okrLmsZ%2Fljr88L2E%2BPC9saT48L3VsPjwvbGk%2BIDwvdWw%2BZAIHD2QWBAIDDxYCHgtfIUl0ZW1Db3VudAIQFiBmD2QWAmYPFQIGNTEwMTAxCeW4guacrOe6p2QCAQ9kFgJmDxUCBjUxMDExMgzpvpnms4npqb%2FljLpkAgIPZBYCZg8VAgY1MTAxMTMM6Z2S55m95rGf5Yy6ZAIDD2QWAmYPFQIGNTEwMTE0CeaWsOmDveWMumQCBA9kFgJmDxUCBjUxMDExNQnmuKnmsZ%2FljLpkAgUPZBYCZg8VAgY1MTAxMTYJ5Y%2BM5rWB5Yy6ZAIGD2QWAmYPFQIGNTEwMTg1CeeugOmYs%2BW4gmQCBw9kFgJmDxUCBjUxMDE4MQzpg73msZ%2FloLDluIJkAggPZBYCZg8VAgY1MTAxODIJ5b2t5bee5biCZAIJD2QWAmYPFQIGNTEwMTgzCemCm%2BW0g%2BW4gmQCCg9kFgJmDxUCBjUxMDE4NAnltIflt57luIJkAgsPZBYCZg8VAgY1MTAxMjQJ6YOr6YO95Yy6ZAIMD2QWAmYPFQIGNTEwMTIxCemHkeWgguWOv2QCDQ9kFgJmDxUCBjUxMDEzMgnmlrDmtKXljr9kAg4PZBYCZg8VAgY1MTAxMjkJ5aSn6YKR5Y6%2FZAIPD2QWAmYPFQIGNTEwMTMxCeiSsuaxn%2BWOv2QCBg9kFgJmD2QWBgIDDw8WAh8ABQY4MC8zNTVkZAIJDxYCHwECChYWZg9kFgZmDxUBCeW0h%2BW3nuW4gmQCAQ8PFgIeC05hdmlnYXRlVXJsBWRodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvQXNzZXRSZXNvdXJjZS9EZWFsTm90aWNlQ29udGVudC5hc3B4P2lkPTJEQzNGNUZBMDEwRDQ3RkFBMEJFOTBBOTM3NjRBOEUzZBYCZg8VAmjltIflt57luILlm73mnInotYTkuqfmipXotYTnu4%2FokKXmnInpmZDotKPku7vlhazlj7jotYTkuqflh7rnp5%2FkuqTmmJPnu5PmnpzlhazlkYrvvIgyMDE55bm0MDTmnIgyNOaXpe%2B8iQBkAgIPFQIKMjAxOS0wNC0yNCg8ZGl2IGNsYXNzPSJlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAIBD2QWBmYPFQEM5aSp5bqc5paw5Yy6ZAIBDw8WAh8CBWRodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvQXNzZXRSZXNvdXJjZS9EZWFsTm90aWNlQ29udGVudC5hc3B4P2lkPTFiNmYzZGM1NTY0OTQ1ZWViY2M1NzZmY2JlMGNjMGYzZBYCZg8VAlnmiJDpg73lpKnmipXkuqfkuJrmipXotYTmnInpmZDlhazlj7jotYTkuqflh7rnp5%2Fnq57ku7fnu5PmnpzlhaznpLrvvIgyMDE55bm0MDTmnIgyM%2BaXpe%2B8iQBkAgIPFQIKMjAxOS0wNC0yMyg8ZGl2IGNsYXNzPSJlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAICD2QWBmYPFQEJ5biC5pys57qnZAIBDw8WAh8CBWRodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvQXNzZXRSZXNvdXJjZS9EZWFsTm90aWNlQ29udGVudC5hc3B4P2lkPTQ5ZDEzOTRlMjJkYTQ2ZjA4NWQwY2MxOGFkODRkMjU4ZBYCZg8VAnHmiJDpg73ln47kuaHllYbotLjnianmtYHlj5HlsZXmipXotYTvvIjpm4blm6LvvInmnInpmZDlhazlj7jotYTkuqflh7rnp5%2Fnq57ku7fnu5PmnpzlhaznpLrvvIgyMDE55bm0MDTmnIgyM%2BaXpe%2B8iQBkAgIPFQIKMjAxOS0wNC0yMyg8ZGl2IGNsYXNzPSJlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAIDD2QWBmYPFQEJ5b2t5bee5biCZAIBDw8WAh8CBWRodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvQXNzZXRSZXNvdXJjZS9EZWFsTm90aWNlQ29udGVudC5hc3B4P2lkPTcwM0IwRjg3OUQzRDQwNEJCNUUzNjhDMDQzMDJGQ0ZDZBYCZg8VAjPlva3lt57luILmiLflpJbljYHkuozlnZflub%2FlkYrkvY3mi43ljZbnu5PmnpzlhaznpLoDKDEpZAICDxUCCjIwMTktMDQtMjIoPGRpdiBjbGFzcz0iZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCBA9kFgZmDxUBCeeugOmYs%2BW4gmQCAQ8PFgIfAgVkaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0Fzc2V0UmVzb3VyY2UvRGVhbE5vdGljZUNvbnRlbnQuYXNweD9pZD02Q0E2M0Y2OUY1ODA0RTgxOTZCOURCMTJCNzRGNEYzMmQWAmYPFQJW566A6Ziz5biC5L6b6ZSA5ZCI5L2c56S%2B6IGU5ZCI56S%2B6LWE5Lqn5Ye656ef5Lqk5piT57uT5p6c5YWs5ZGK77yIMjAxOeW5tDA05pyIMjLml6XvvIkAZAICDxUCCjIwMTktMDQtMjIoPGRpdiBjbGFzcz0iZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCBQ9kFgZmDxUBCemHkeeJm%2BWMumQCAQ8PFgIfAgVkaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0Fzc2V0UmVzb3VyY2UvRGVhbE5vdGljZUNvbnRlbnQuYXNweD9pZD0wNzhlMzU4MmEzODQ0YTc4OGRlNjkwZjIwMmYwZDQ0OGQWAmYPFQJu5oiQ6YO95biC6YeR54mb5Zu95pyJ6LWE5Lqn5oqV6LWE57uP6JCl6ZuG5Zui5pyJ6ZmQ5YWs5Y%2B46LWE5Lqn5Ye656ef56ue5Lu357uT5p6c5YWs56S677yIMjAxOeW5tDA05pyIMjLml6XvvIkAZAICDxUCCjIwMTktMDQtMjIoPGRpdiBjbGFzcz0iZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCBg9kFgZmDxUBCemdkue%2BiuWMumQCAQ8PFgIfAgVkaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0Fzc2V0UmVzb3VyY2UvRGVhbE5vdGljZUNvbnRlbnQuYXNweD9pZD0xMmI5MjgwNGQxZWU0ZDIyOTM4ODZiNTc5MWI1NGM1ZGQWAmYPFQJZ5oiQ6YO95biC5oi%2F5Zyw5Lqn5biC5Zy65pyN5Yqh5Lit5b%2BD6LWE5Lqn5aSE572u56ue5Lu357uT5p6c5YWs56S677yIMjAxOeW5tDA05pyIMjLml6XvvIkAZAICDxUCCjIwMTktMDQtMjIoPGRpdiBjbGFzcz0iZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCBw9kFgZmDxUBCeWPjOa1geWMumQCAQ8PFgIfAgVkaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0Fzc2V0UmVzb3VyY2UvRGVhbE5vdGljZUNvbnRlbnQuYXNweD9pZD1GM0IzMDg1MURBRjU0NEM5OTQ3RjI1NUY4RkU0RTNENWQWAmYPFQJT5Y%2BM5rWB5Yy65YWs5YW06KGX6YGT5Yqe5LqL5aSE6LWE5Lqn5Ye656ef5Lqk5piT57uT5p6c5YWs5ZGK77yIMjAxOeW5tDA05pyIMTnml6XvvIkAZAICDxUCCjIwMTktMDQtMTkoPGRpdiBjbGFzcz0iZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCCA9kFgZmDxUBCea4qeaxn%2BWMumQCAQ8PFgIfAgVkaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0Fzc2V0UmVzb3VyY2UvRGVhbE5vdGljZUNvbnRlbnQuYXNweD9pZD00RDQ5QjVFNzg0QUE0ODJEQjNDQTcxOEVDRkVGMTlEN2QWAmYPFQJT5oiQ6YO95Lmd6IGU5oqV6LWE5pyJ6ZmQ5YWs5Y%2B46LWE5Lqn5aSE572u5Lqk5piT57uT5p6c5YWs5ZGK77yIMjAxOeW5tDA05pyIMTnml6XvvIkAZAICDxUCCjIwMTktMDQtMTkoPGRpdiBjbGFzcz0iZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCCQ9kFgZmDxUBCemdkue%2BiuWMumQCAQ8PFgIfAgVkaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0Fzc2V0UmVzb3VyY2UvRGVhbE5vdGljZUNvbnRlbnQuYXNweD9pZD1jY2E3Nzg1Y2M3NTY0Y2MyYjYyZmEwMjE4NmIxZjFmMGQWAmYPFQJW5oiQ6YO95biC5YWs5a6J5bGA5Lqk6YCa566h55CG5bGA6LWE5Lqn5aSE572u56ue5Lu357uT5p6c5YWs56S677yIMjAxOeW5tDA05pyIMTnml6XvvIkAZAICDxUCCjIwMTktMDQtMTkoPGRpdiBjbGFzcz0iZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCCg9kFgICAQ8PFgIeB1Zpc2libGVoZGQCCw8PFgYeCFBhZ2VTaXplAgoeEEN1cnJlbnRQYWdlSW5kZXgCUB4LUmVjb3JkY291bnQC2xtkZGS8D4BeyxFmqACvgjztSveY0OTbAg%3D%3D&__VIEWSTATEGENERATOR=78551C4F&__EVENTTARGET=ctl00%24ContentPlaceHolder1%24Pager&__EVENTARGUMENT=" + pageValue + "&__EVENTVALIDATION=%2FwEdAAhXJMho3X3OoI4xem2%2BadWdZXbjphD4J6Ci0P2UUXCmZQp8fmeAe3E%2BZ%2BlyrRcok%2FuMO7fmjkJSfq6Zbgk2kTWnZi7H72kkAvPqROrOG28FXID8g%2FxeCzDZJOPgpGV4zViznYSIw3B963y%2FxzaOhgoHdty0mXvz7f%2B9YtBAL8kV3V8D0OXO11wLZLxxxFSg1CcwhJp%2B&__ASYNCPOST=true&";
            OutputStream out = connection.getOutputStream();
            out.write(params.getBytes());
            out.flush();
            out.close();
            // 从连接中读取响应信息
            StringBuilder sb = new StringBuilder();
            int code = connection.getResponseCode();
            if (code == 200) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                reader.close();
            }
            connection.disconnect(); // 5. 断开连接
            // 处理结果
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //土流网
    public void getNetInfoFromTDJY(Integer days) {
        try {
            Date date = getInstanceDate(days);//得到前1天
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            String htmlHref = "https://www.tudinet.com/market-0-0-2-0/";
            Elements pageElements = getContent(htmlHref, ".paging.clearfix a", "");
            Integer pageValue = Integer.valueOf(pageElements.get(pageElements.size() - 3).childNodes().get(0).toString());

            circ:
            for (int i = 1; i <= pageValue; i++) {
                //列表信息
                Elements elements = getContent(String.format("%slist-pg%s.html", htmlHref, i), ".land-l-cont dl", "");
                for (Element item : elements) {
                    Elements contentElements = item.select("dd p");
                    String publishtimeStr = contentElements.get(0).childNodes().get(1).toString();
                    Date publishtime = sdf.parse(publishtimeStr);
                    if (publishtime == null) continue;
                    if (publishtime.compareTo(date) < 0) break circ;
                    Elements titleElements = item.select("dt a");
                    String titleStr = titleElements.get(0).attributes().get("titl").trim();
                    String link = titleElements.get(0).attributes().get("href").trim();
                    String addressStr = contentElements.get(3).childNodes().get(1).toString();
                    String province = addressStr.substring(0, addressStr.indexOf("-"));
                    String city = null;
                    if (addressStr.indexOf("-") == addressStr.lastIndexOf("-")) {
                        city = addressStr.substring(addressStr.indexOf("-") + 1, addressStr.length());
                    } else {
                        city = addressStr.substring(addressStr.indexOf("-") + 1, addressStr.lastIndexOf("-"));
                    }

                    String type = contentElements.get(5).childNodes().get(1).toString();
                    NetInfoRecord netInfoRecord = new NetInfoRecord();
                    netInfoRecord.setProvince(province);
                    netInfoRecord.setCity(city);
                    netInfoRecord.setType(type);
                    netInfoRecord.setSourceSiteUrl(link);
                    netInfoRecord.setTitle(titleStr);
                    netInfoRecord.setBeginTime(publishtime);

                    Elements tableElementHrefs = getContent(link, ".row.hh-sort-text", "");
                    if (tableElementHrefs.size() == 2) {
                        Elements dealInfo = tableElementHrefs.get(1).select("li span");
                        String endTimeStr = dealInfo.get(3).childNodes().get(0).toString().trim();
                        Date endTime = DateUtils.parse(endTimeStr);
                        netInfoRecord.setEndTime(endTime);
                        String currentPriceStr = generateCommonMethod.getNumber(dealInfo.get(7).childNodes().get(0).toString());
                        if (StringUtils.isNotEmpty(currentPriceStr)) {
                            netInfoRecord.setCurrentPrice(new BigDecimal(currentPriceStr).multiply(new BigDecimal("10000")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                        }
                        String initPriceStr = generateCommonMethod.getNumber(dealInfo.get(6).childNodes().get(0).toString());
                        if (StringUtils.isNotEmpty(initPriceStr)) {
                            netInfoRecord.setInitPrice(new BigDecimal(initPriceStr).multiply(new BigDecimal("10000")).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
                        }
                    }
                    String content = getContent(titleStr, type, netInfoRecord.getCurrentPrice(), "", netInfoRecord.getInitPrice()
                            , DateUtils.format(netInfoRecord.getEndTime(), DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(netInfoRecord.getBeginTime(), DateUtils.DATE_CHINESE_PATTERN));
                    netInfoRecord.setSourceSiteName("土流网");
                    netInfoRecord.setContent(content);
                    this.addInfo(netInfoRecord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //农村产权交易中心
    public void getNetInfoFromNCJY(Integer days) {
        try {
            Date date = getInstanceDate(days);//得到前1天
            Elements pageElements = getNCJYHtml(".page", "1");
            String pageStr = pageElements.get(0).childNodes().get(0).toString().trim();
            Integer pageTotal = Integer.valueOf(pageStr.substring(pageStr.indexOf("共") + 1, pageStr.indexOf("页")));

            circ:
            for (int i = 1; i <= pageTotal; i++) {
                //列表信息
                Elements elements = getNCJYHtml("table tr", String.valueOf(i));
                for (Element item : elements) {
                    Elements contentElements = item.select(".td5");
                    if (contentElements.size() == 0) continue;
                    String publishtimeStr = contentElements.get(0).childNodes().get(0).toString().trim();
                    Date publishtime = DateUtils.parse(publishtimeStr);
                    if (publishtime == null) continue;
                    if (publishtime.compareTo(date) < 0) break circ;
                    Elements titleElements = item.select(".td2 a");
                    String titleStr = titleElements.get(0).attributes().get("title").trim();
                    String link = titleElements.get(0).attributes().get("href").trim();
                    String param = link.substring(link.indexOf("?") + 1, link.indexOf("&"));

                    Elements contentTable = getContent(String.format("%s%s", "https://www.cdaee.com/portal/pro/UTRN/td.jsp?", param), "table", "");
                    String content = null;
                    content = publicService.delHtmlTags(contentTable.get(0).text());
                    if (content.length() > 500) {
                        content = content.substring(0, 500);
                    }
                    NetInfoRecord netInfoRecord = new NetInfoRecord();
                    netInfoRecord.setProvince("四川");
                    netInfoRecord.setType("土地经营权");
                    netInfoRecord.setSourceSiteUrl(String.format("%s%s", "https://www.cdaee.com", link));
                    netInfoRecord.setTitle(titleStr);
                    netInfoRecord.setBeginTime(publishtime);
                    netInfoRecord.setEndTime(publishtime);
                    netInfoRecord.setSourceSiteName("农村产权交易中心");
                    netInfoRecord.setContent(String.format("%s%s", content, "..."));
                    this.addInfo(netInfoRecord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public Elements getNCJYHtml(String element, String pageValue) {
        try {
            // 1. 获取访问地址URL
            URL url = new URL("https://www.cdaee.com/portal/comp/listPro.jsp");
            // 2. 创建HttpURLConnection对象
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            /* 3. 设置请求参数等 */
            // 请求方式
            connection.setRequestMethod("POST");
            // 超时时间
            //connection.setConnectTimeout(3000);
            // 设置是否输出
            connection.setDoOutput(true);
            // 设置是否读入
            connection.setDoInput(true);
            // 设置是否使用缓存
            connection.setUseCaches(false);
            // 设置此 HttpURLConnection 实例是否应该自动执行 HTTP 重定向
            connection.setInstanceFollowRedirects(true);
            // 设置使用标准编码格式编码参数的名-值对
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            connection.setRequestProperty("Host", "www.cdaee.com");
            connection.setRequestProperty("Origin", "https://www.cdaee.com");
            connection.setRequestProperty("Referer", "https://www.cdaee.com/portal/pro/index.shtml");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
            // 连接
            connection.connect();
            /* 4. 处理输入输出 */
            // 写入参数到请求中
            if (StringUtils.isEmpty(pageValue))
                pageValue = "1";
            String params = "sysEname=UTRN&template=OrgUTRNSearch&pageIndex=" + pageValue + "&pageSize=15&proType=td";

            OutputStream out = connection.getOutputStream();
            out.write(params.getBytes());
            out.flush();
            out.close();
            // 从连接中读取响应信息
            StringBuilder sb = new StringBuilder();
            int code = connection.getResponseCode();
            if (code == 200) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream(), "utf-8"));
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    ;
                }
                reader.close();
            }
            // 5. 断开连接
            connection.disconnect();

            // 处理结果
            org.jsoup.nodes.Document doc = Jsoup.parse(sb.toString());
            Elements elements = doc.select(element);
            return elements;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private String checkNull(Elements select, Integer index) {
        String string = "";
        try {
            Elements elements = select.get(index).select("p span");
            Integer i = elements.size();
            if (i != 0) {
                String str = "";
                for (int j = 0; j < i; j++) {
                    String s;
                    if (elements.get(j).childNodes().size() == 0) {
                        continue;
                    }
                    if (elements.get(j).childNodes().get(0).hasAttr("style")) {
                        continue;
                    }
                    if (elements.get(j).childNodes().size() > 0) {
                        s = elements.get(j).childNodes().get(0).toString();
                        str += s;
                    }
                }
                if (StringUtils.isBlank(str)) {
                    str = "";
                }
                return str;
            }
            string = select.get(index).childNodes().get(0).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (StringUtils.isBlank(string)) {
            string = "";
        }
        return string;
    }

    public String getContentHtml(String urlInfo, String requestMethod, String encoding) {
        try {
            if (urlInfo.contains("https")) {
                SSLContext sslcontext = SSLContext.getInstance("SSL", "SunJSSE");
                sslcontext.init(null, new TrustManager[]{new MyX509TrustManager()}, new java.security.SecureRandom());
                HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
                    public boolean verify(String s, SSLSession sslsession) {
                        System.out.println("WARNING: Hostname is not matched for cert.");
                        return true;
                    }
                };
                HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
                HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
            }

            URL url = new URL(urlInfo);
            HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
            //设置用户代理
            httpUrl.setRequestProperty("User-agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
            httpUrl.setRequestProperty("Host", url.getHost());
            httpUrl.setConnectTimeout(6000); // 6s
            httpUrl.setReadTimeout(6000);
            httpUrl.setUseCaches(false);

            if (StringUtils.isNotBlank(requestMethod))
                httpUrl.setRequestMethod(requestMethod);
            InputStream is = httpUrl.getInputStream();
            String contentEncoding = httpUrl.getContentEncoding();
            BufferedReader br = null;
            if ("gzip".equals(contentEncoding)) {
                GZIPInputStream gzin = new GZIPInputStream(is);
                br = new BufferedReader(new InputStreamReader(gzin, StringUtils.defaultIfBlank(encoding, "utf-8")));
            } else {
                br = new BufferedReader(new InputStreamReader(is, StringUtils.defaultIfBlank(encoding, "utf-8")));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Elements getContent(String urlInfo, String element, String encoding) {
        org.jsoup.nodes.Document doc = Jsoup.parse(getContentHtml(urlInfo, null, encoding));
        Elements elements = doc.select(element);
        return elements;
    }

    public Elements getContent(String urlInfo, String element, String encoding, String requestMethod) {
        org.jsoup.nodes.Document doc = Jsoup.parse(getContentHtml(urlInfo, requestMethod, encoding));
        Elements elements = doc.select(element);
        return elements;
    }


    /**
     * 拼接内容
     *
     * @param title        标题
     * @param type         类型
     * @param currentPrice 成交价
     * @param consultPrice 估算价
     * @param initPrice    起始价
     * @param endTime      结束时间
     * @param startTime    开始时间
     * @return
     */
    public String getContent(String title, String type, String currentPrice, String consultPrice, String initPrice, String endTime, String startTime) {
        StringBuilder content = new StringBuilder();
        if (StringUtil.isNotEmpty(currentPrice)) {
            content.append("成交价：" + currentPrice + "。");
        }
        if (StringUtil.isNotEmpty(consultPrice)) {
            content.append("估算价：" + consultPrice + "。");
        }
        if (StringUtil.isNotEmpty(initPrice)) {
            content.append("起始价：" + initPrice + "。");
        }
        return content.toString();
    }

    //获取变现率（成交价/评估价）
    public String getLiquidRatios(String currentPriceStr, String consultPriceStr) throws Exception {
        if (StringUtil.isEmpty(currentPriceStr) || StringUtil.isEmpty(consultPriceStr))
            return null;
        BigDecimal verifyValue = new BigDecimal("0");
        NumberFormat format = NumberFormat.getInstance();
        BigDecimal currentPrice = new BigDecimal(format.parse(currentPriceStr).doubleValue());
        BigDecimal consultPrice = new BigDecimal(format.parse(consultPriceStr).doubleValue());
        //起始价，评估价不为0
        if (verifyValue.compareTo(currentPrice) == 0 || verifyValue.compareTo(consultPrice) == 0)
            return null;
        BigDecimal liquidRatiosValue = currentPrice.multiply(new BigDecimal("100")).divide(consultPrice, 2, BigDecimal.ROUND_HALF_UP);
        return String.format("%s%s", liquidRatiosValue, "%");
    }

    //price格式为000.00或者000,000.00统一格式为000.00
    public String getRealMoney(String price) throws Exception {
        NumberFormat format = NumberFormat.getInstance();
        if (StringUtil.isNotEmpty(price)) {
            return new BigDecimal(format.parse(price).doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        } else {
            return "";
        }
    }

    public BootstrapTableVo getInfoRecordList(String queryTitle, String queryWebName, String province, String city, String queryContent, String queryType, String queryStartTime, String queryEndTime, String executor, Integer status) throws Exception {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        String provinceName = erpAreaService.getSysAreaName(province);
        String cityName = erpAreaService.getSysAreaName(city);
        Date startTimeParse = null;
        Date endTimeParse = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtil.isNotEmpty(queryStartTime))
            startTimeParse = sdf.parse(queryStartTime);
        if (StringUtil.isNotEmpty(queryEndTime)) {
            endTimeParse = sdf.parse(queryEndTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endTimeParse);
            calendar.add(Calendar.DAY_OF_MONTH, +1); //得到后1天
            endTimeParse = calendar.getTime();
        }
        List<NetInfoRecord> netInfoRecords = netInfoRecordDao.getNetInfoRecordListByName(queryTitle, queryWebName, provinceName, cityName, queryContent, queryType, startTimeParse, endTimeParse, executor, status);
        List<NetInfoRecordVo> vos = LangUtils.transform(netInfoRecords, o -> getNetInfoRecordVo(o));
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<NetInfoRecord>() : vos);
        return bootstrapTableVo;
    }


    public NetInfoRecordVo getNetInfoRecordVo(NetInfoRecord netInfoRecord) {
        if (netInfoRecord == null) return null;
        NetInfoRecordVo netInfoRecordVo = new NetInfoRecordVo();
        BeanUtils.copyProperties(netInfoRecord, netInfoRecordVo);
        //单价=成交价/面积
        if (StringUtils.isNotEmpty(netInfoRecord.getCurrentPrice()) && netInfoRecord.getArea() != null) {
            BigDecimal currentPrice = new BigDecimal(netInfoRecord.getCurrentPrice());
            BigDecimal unitPrice = currentPrice.divide(netInfoRecord.getArea(), 2, BigDecimal.ROUND_HALF_UP);
            netInfoRecordVo.setUnitPrice(unitPrice.toString());
        }
        //默认使用结束日期作为成交日期,计算周期
        if (netInfoRecord.getEndTime() != null && netInfoRecord.getAssessBaseDate() != null) {
            String value = String.valueOf(DateUtils.diffDate(netInfoRecord.getEndTime(), netInfoRecord.getAssessBaseDate()));
            netInfoRecordVo.setLiquidCycle(String.format("%s%s", value, "天"));
        }
        if (netInfoRecord.getStatus().equals(0)) {
            netInfoRecordVo.setStatusName("未分派");
        }
        if (netInfoRecord.getStatus().equals(1)) {
            netInfoRecordVo.setStatusName("已分派");
        }
        if (netInfoRecord.getStatus().equals(2)) {
            netInfoRecordVo.setStatusName("审批中");
        }
        if (netInfoRecord.getStatus().equals(3)) {
            netInfoRecordVo.setStatusName("审批通过");
        }
        return netInfoRecordVo;
    }


    //获取前几天的日期
    public Date getInstanceDate(Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -days); //得到前几天
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return date;
    }

    //抓取两年前数据
    public void climbingOldData() {
        //来源京东司法
        this.getNetInfoFromJDSF(732);
        //来源京东资产
        this.getNetInfoFromJDZC(732);
        //中国拍卖行业协会网-司法
        this.getNetInfoFromZGSF(732);
        //中国拍卖行业协会网-标的
        this.getNetInfoFromZGBD(732);
        //来源公拍网
        this.getNetInfoFromGPW(732);
        //公共资源交易平台-成都(土地矿权)
        this.getNetInfoFromGGZYCD(732);
        //公共资源交易平台-成都（资产资源）
        this.getNetInfoFromGGZYCD2(732);
        ////土流网
        this.getNetInfoFromTDJY(732);
        //农村产权交易中心
        this.getNetInfoFromNCJY(732);
        //来源淘宝网
        this.getNetInfoFromTB(732);
    }


    public void assignTask(String executor, String ids) throws Exception {
        List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
        List<NetInfoRecord> infoRecords = LangUtils.transform(integers, o -> netInfoRecordDao.getInfoById(o));
        //状态为已分派
        for (NetInfoRecord netInfo : infoRecords) {
            netInfo.setStatus(1);
            netInfo.setExecutor(executor);
            this.updateInfo(netInfo);
        }

    }

    public BootstrapTableVo getInfoRecordListByIds(String ids) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
        List<NetInfoRecord> transform = LangUtils.transform(integers, o -> netInfoRecordDao.getInfoById(o));

        List<NetInfoRecordVo> vos = LangUtils.transform(transform, o -> getNetInfoRecordVo(o));
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<NetInfoRecord>() : vos);
        return bootstrapTableVo;
    }

    public void addInfo(NetInfoRecord netInfo) {
        netInfoRecordDao.addInfo(netInfo);
        //备份数据
        CaseNetInfoRecord caseNetInfoRecord = new CaseNetInfoRecord();
        BeanUtils.copyProperties(netInfo, caseNetInfoRecord);
        caseNetInfoRecordDao.addInfo(caseNetInfoRecord);
    }

    public void updateInfo(NetInfoRecord netInfo) {
        netInfoRecordDao.updateInfo(netInfo);
        //备份数据
        CaseNetInfoRecord caseNetInfoRecord = new CaseNetInfoRecord();
        BeanUtils.copyProperties(netInfo, caseNetInfoRecord);
        caseNetInfoRecordDao.updateInfo(caseNetInfoRecord);
    }

    /**
     * 关闭认领任务
     * @param id
     * @param closeReason
     */
    public void closeItem(String id, String closeReason) {
        if (StringUtils.isBlank(id)) {
            return;
        }
        List<Integer> integerList = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isEmpty(integerList)){
            return;
        }
        NetInfoRecord record = new NetInfoRecord();
        record.setCloseReason(closeReason);
        record.setBisDelete(true);
        netInfoRecordDao.batchNetInfoRecord(record,integerList);
        CaseNetInfoRecord caseNetInfoRecord = new CaseNetInfoRecord();
        caseNetInfoRecord.setCloseReason(closeReason);
        caseNetInfoRecord.setBisDelete(true);
        caseNetInfoRecordDao.batchNetInfoRecord(caseNetInfoRecord,integerList);
    }

    public void addInfoContent(NetInfoRecordContent netInfoContent) {
        netInfoRecordContentDao.addInfo(netInfoContent);
        //备份数据
        CaseNetInfoRecordContent caseNetInfoRecordContent = new CaseNetInfoRecordContent();
        BeanUtils.copyProperties(netInfoContent, caseNetInfoRecordContent);
        caseNetInfoRecordContentDao.addInfo(caseNetInfoRecordContent);
    }
}
