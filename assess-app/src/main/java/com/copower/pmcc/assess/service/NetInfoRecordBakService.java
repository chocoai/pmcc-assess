package com.copower.pmcc.assess.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordContentDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordContent;
import com.copower.pmcc.assess.dto.input.net.JDSFDto;
import com.copower.pmcc.assess.dto.input.net.JDZCDto;
import com.copower.pmcc.assess.dto.input.net.TBSFDto;
import com.copower.pmcc.assess.dto.input.net.ZGSFDto;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordVo;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
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
public class NetInfoRecordBakService {
    private final static Logger logger = LoggerFactory.getLogger(NetInfoRecordBakService.class);
    @Autowired
    private NetInfoRecordDao netInfoRecordDao;
    @Autowired
    private NetInfoRecordContentDao netInfoRecordContentDao;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private CommonService commonService;


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
        //公共资源交易平台-雅安
        this.getNetInfoFromGGZYYA(1);
        //公共资源交易平台-成都(土地矿权)
        this.getNetInfoFromGGZYCD(1);
        //公共资源交易平台-成都（资产资源）
        this.getNetInfoFromGGZYCD2(1);
        ////公共资源交易平台-凉山州（交易公告）
        this.getNetInfoFromGGZYLSZ(1);
        ////公共资源交易平台-攀枝花（交易信息）
        this.getNetInfoFromGGZYPZH(1);
        ////土流网
        this.getNetInfoFromTDJY(1);
        //农村产权交易中心
        this.getNetInfoFromNCJY(1);
        //来源淘宝网
        this.getNetInfoFromTB(1);
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
                        netInfoRecordDao.addInfo(netInfoRecord);

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
                            netInfoRecordContentDao.addInfo(netInfoRecordContent);
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
                        netInfoRecordDao.addInfo(netInfoRecord);
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
                        netInfoRecordDao.addInfo(netInfoRecord);
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
                        netInfoRecordDao.addInfo(netInfoRecord);

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
                            netInfoRecordContentDao.addInfo(netInfoRecordContent);
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
                        netInfoRecordDao.addInfo(netInfoRecord);

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
                            netInfoRecordContentDao.addInfo(netInfoRecordContent);
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
                            netInfoRecordDao.addInfo(netInfoRecord);

                            Elements contentBody = getContent(itemHref, ".d-article", "");
                            if (contentBody.size() != 0 && contentBody != null) {
                                NetInfoRecordContent netInfoRecordContent = new NetInfoRecordContent();
                                netInfoRecordContent.setRecordId(netInfoRecord.getId());
                                if (contentBody.get(1).html().length() > 30000) {
                                    netInfoRecordContent.setFullDescription(contentBody.get(1).html().substring(0, 30000));
                                } else {
                                    netInfoRecordContent.setFullDescription(contentBody.get(1).html());
                                }
                                netInfoRecordContentDao.addInfo(netInfoRecordContent);
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

    //公共资源交易平台-雅安
    public void getNetInfoFromGGZYYA(Integer days) {
        try {
            Date date = getInstanceDate(days);//得到前1天

            String urlInfo = "http://www.yaggzy.org.cn/jyxx/tdsyq/cjqr";

            Elements pageElements = getContent(urlInfo, ".mmggxlh", "");
            Elements a = pageElements.get(0).select("a");
            Integer page = a.size() - 2;
            List<String> pageHrefs = Lists.newArrayList();
            for (int i = 1; i <= page; i++) {
                pageHrefs.add(String.format("%s%s", urlInfo, "?currentPage=" + i));
            }

            circ:
            for (String pageHref : pageHrefs) {
                Elements itemContent = getContent(pageHref, "tbody tr", "");
                for (int i = 1; i < itemContent.size(); i++) {
                    String publishtimeStr = itemContent.get(i).childNodes().get(7).childNodes().get(0).toString().trim();
                    Date publishtime = DateUtils.parse(publishtimeStr);
                    if (publishtime == null) continue;
                    if (publishtime.compareTo(date) < 0) break circ;
                    String detailHref = itemContent.get(i).childNodes().get(5).childNodes().get(1).attributes().get("href");
                    Elements tdElements = getContent(String.format("%s%s", "http://www.yaggzy.org.cn", detailHref), "tr", "");
                    Integer length = tdElements.get(1).select("td").size();
                    //获取字段名称
                    List<String> fieldNames = Lists.newArrayList();
                    for (int k = 0; k < tdElements.size(); k++) {
                        Elements select = tdElements.get(k).select("td");
                        if (select.size() != tdElements.get(0).select("td").size()) continue;
                        if (k == 0) {
                            for (int f = 0; f < length; f++) {
                                String fieldName = checkNull(select, f);
                                fieldNames.add(fieldName);
                            }
                            continue;
                        }
                        List<String> fieldValues = Lists.newArrayList();
                        for (int j = 0; j < length; j++) {
                            String fieldValue = checkNull(select, j);
                            fieldValues.add(publicService.delHtmlTags(fieldValue));
                        }
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        String title = itemContent.get(i).childNodes().get(3).childNodes().get(0).toString();
                        netInfoRecord.setTitle(title);
                        netInfoRecord.setSourceSiteUrl(String.format("%s%s", "http://www.yaggzy.org.cn", detailHref));
                        netInfoRecord.setProvince("四川");
                        netInfoRecord.setCity("雅安");
                        netInfoRecord.setSourceSiteName("公共资源交易平台-雅安");
                        netInfoRecord.setBeginTime(publishtime);
                        netInfoRecord.setEndTime(publishtime);
                        StringBuilder content = new StringBuilder();
                        for (int m = 0; m < fieldNames.size(); m++) {
                            content.append(fieldNames.get(m) + "：" + fieldValues.get(m) + "；");
                        }
                        netInfoRecord.setContent(content.toString());
                        netInfoRecordDao.addInfo(netInfoRecord);
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
            Date date = getInstanceDate(days);//得到前1天
            //  String[] needContentType = new String[]{"拍卖公告", "结果公告"};
            String[] needContentType = new String[]{"结果公告"};
            List<String> needTypes = Arrays.asList(needContentType);

            String urlInfo = "https://www.cdggzy.com/site/LandTrade/LandList.aspx";
            //类型
            Elements typeEelements = getContent(urlInfo, ".optionlist", "");
            Elements typeDivs = typeEelements.get(0).select("div");
            for (Element typeDiv : typeDivs) {
                String typeName = typeDiv.childNodes().get(0).toString().trim();
                if (needTypes.contains(typeName)) {
                    String typeValue = typeDiv.attributes().get("data-value");
                    //取得页数
                    Elements pageElements = getGGZYCDHtml("#Pager a", "", typeValue);
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
                            Elements elements = getGGZYCDHtml(".row.contentitem", String.valueOf(i), typeValue);
                            for (Element item : elements) {
                                Elements publishtimeElement = item.select(".publishtime");
                                String publishtimeStr = publishtimeElement.get(0).childNodes().get(0).toString().substring(1).trim();
                                Date publishtime = DateUtils.parse(publishtimeStr);
                                if (publishtime == null) continue;
                                if (publishtime.compareTo(date) < 0) break circ;

                                Elements addressElement = item.select(".col-xs-1");
                                String address = addressElement.get(0).childNodes().get(0).toString();
                                String titleStr = item.select("a").get(0).childNodes().get(0).toString();
                                String link = item.select("a").get(0).attributes().get("href");

                                Elements tableElementHrefs = getContent(link, "iframe", "");
                                if (tableElementHrefs.size() <= 0) {
                                    continue;
                                }
                                String s = link.substring(0, link.lastIndexOf("/") + 1);

                                String iframeUrl = s + tableElementHrefs.get(0).attributes().get("src");//表格地址
                                Elements tableElements = getContent(iframeUrl, "table", "");
                                Elements tdElements = tableElements.select("tr");

                                Integer length = tdElements.get(tdElements.size() - 1).select("td").size();
                                //获取字段名称
                                List<String> fieldNames = Lists.newArrayList();
                                for (int k = 0; k < tdElements.size(); k++) {
                                    Elements select = tdElements.get(k).select("td");
                                    Elements one = tdElements.get(0).select("td");
                                    if (one.size() == 0) {
                                        one = tdElements.get(0).select("th");
                                    }
                                    if (select == null || select.size() == 0) {
                                        select = tdElements.get(k).select("th");
                                    }
                                    Elements tow = tdElements.get(1).select("td");
                                    if (one.size() == tow.size()) {
                                        if (k == 0) {
                                            for (int f = 0; f < length; f++) {
                                                String fieldName = checkNull(select, f);
                                                fieldNames.add(fieldName);
                                            }
                                            continue;
                                        }
                                    } else {
                                        if (k == 0 || k == 1) {
                                            continue;
                                        }
                                    }

                                    List<String> fieldValues = Lists.newArrayList();
                                    String name = "";
                                    for (int j = 0; j < length; j++) {
                                        name = checkNull(select, 1);
                                        String fieldValue = checkNull(select, j);
                                        fieldValues.add(publicService.delHtmlTags(fieldValue));
                                    }
                                    NetInfoRecord netInfoRecord = new NetInfoRecord();
                                    netInfoRecord.setProvince("四川");
                                    netInfoRecord.setCity("成都");
                                    netInfoRecord.setType(typeName);
                                    netInfoRecord.setSourceSiteUrl(link);
                                    netInfoRecord.setBeginTime(publishtime);
                                    netInfoRecord.setEndTime(publishtime);
                                    netInfoRecord.setTitle(titleStr.replaceAll("\n", ""));
                                    netInfoRecord.setSourceSiteName("公共资源交易平台-成都");
                                    StringBuilder content = new StringBuilder();
                                    if (CollectionUtils.isNotEmpty(fieldNames)) {
                                        for (int m = 0; m < fieldNames.size(); m++) {
                                            content.append(fieldNames.get(m) + "：" + fieldValues.get(m) + "；");
                                        }
                                    } else {
                                        for (int m = 0; m < fieldValues.size(); m++) {
                                            content.append(fieldValues.get(m) + "；");
                                        }
                                    }

                                    //关联拍卖信息或交易信息
                                    Elements otherInfoDiv = getContent(link, "#timershaft", "");
                                    if (otherInfoDiv != null && otherInfoDiv.size() > 0) {
                                        Elements a = otherInfoDiv.get(0).select("a");
                                        String otherInfoHref = a.get(0).attributes().get("href");
                                        Elements otherTableElementHrefs = getContent(String.format("%s%s", s, otherInfoHref), "iframe", "");
                                        if (otherTableElementHrefs.size() > 0) {
                                            String otherIframeUrl = s + otherTableElementHrefs.get(0).attributes().get("src");//表格地址
                                            Elements otherTableElements = getContent(otherIframeUrl, "table", "");
                                            Elements otherTdElements = otherTableElements.select("tr");
                                            if (otherTdElements != null && otherTdElements.size() > 0) {

                                                Integer ontherFieldsLength = otherTdElements.get(otherTdElements.size() - 1).select("td").size();
                                                Elements otherSelectedTbs = null;
                                                //获取字段名称
                                                List<String> fieldTitleNames = Lists.newArrayList();
                                                for (int k1 = 0; k1 < otherTdElements.size(); k1++) {
                                                    Element tempTr = otherTdElements.get(k1);
                                                    Elements tbs = tempTr.select("td");
                                                    String otherTitle = checkNull(tbs, 1);
                                                    if (StringUtils.equals(name, otherTitle)) {
                                                        otherSelectedTbs = tbs;
                                                    }


                                                }
                                                //表头字段
                                                Elements otherFirst = otherTdElements.get(0).select("td");
                                                Elements otherSecond = otherTdElements.get(1).select("td");
                                                if (otherFirst.size() > 0) {
                                                    if (otherFirst.size() != otherSecond.size()) {
                                                        for (int f2 = 0; f2 < otherFirst.size(); f2++) {
                                                            Element element = otherFirst.get(f2);
                                                            String colspan = element.attributes().get("colspan");
                                                            if (StringUtils.isEmpty(colspan)) {
                                                                String fieldTitleName = checkNull(otherFirst, f2);
                                                                fieldTitleName = publicService.delHtmlTags(fieldTitleName);
                                                                if (StringUtils.isNotEmpty(fieldTitleName)) {
                                                                    fieldTitleNames.add(fieldTitleName);
                                                                }
                                                            } else {
                                                                for (int f3 = 0; f3 < otherSecond.size(); f3++) {
                                                                    fieldTitleNames.add(checkNull(otherSecond, f3));
                                                                }
                                                            }

                                                        }
                                                    }
                                                }


                                                List<String> otherFieldValues = Lists.newArrayList();
                                                for (int j = 0; j < ontherFieldsLength; j++) {
                                                    String otherFieldValue = checkNull(otherSelectedTbs, j);
                                                    otherFieldValues.add(publicService.delHtmlTags(otherFieldValue));
                                                }


                                                if (CollectionUtils.isNotEmpty(fieldTitleNames)) {
                                                    Integer indexLength = fieldTitleNames.size();
                                                    if (fieldTitleNames.size() >= otherFieldValues.size()) {
                                                        indexLength = otherFieldValues.size();
                                                    }
                                                    for (int m = 0; m < indexLength; m++) {
                                                        content.append(fieldTitleNames.get(m) + "：" + otherFieldValues.get(m) + "；");
                                                    }
                                                } else {
                                                    for (int m = 0; m < otherFieldValues.size(); m++) {
                                                        content.append(otherFieldValues.get(m) + "；");
                                                    }
                                                }
                                            }

                                        }


                                    }


                                    content.append("发布时间：" + publishtimeStr + "；");
                                    content.append("地址：" + address.replaceAll("\n", "").substring(1, address.length() - 2) + "；");
                                    netInfoRecord.setContent(content.toString());
                                    netInfoRecordDao.addInfo(netInfoRecord);
                                }

                            }
                        }
                    }
                }
            }

            logger.info("----公共资源交易平台-成都(土地矿权), end---------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public Elements getGGZYCDHtml(String element, String pageValue, String typeValue) {
        try {
            // 1. 获取访问地址URL
            URL url = new URL("https://www.cdggzy.com/site/LandTrade/LandList.aspx");
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
            connection.setRequestProperty("Host", "www.cdggzy.com");
            connection.setRequestProperty("Origin", "https://www.cdggzy.com");
            connection.setRequestProperty("Referer", "https://www.cdggzy.com/site/LandTrade/LandList.aspx");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
            // 连接
            connection.connect();
            /* 4. 处理输入输出 */
            // 写入参数到请求中
            if (StringUtils.isEmpty(pageValue))
                pageValue = "1";
            if (StringUtils.isEmpty(typeValue))
                typeValue = "";
            String params = "ctl00%24ScriptManager1=ctl00%24ContentPlaceHolder1%24UpdatePanel1%7Cctl00%24ContentPlaceHolder1%24Pager&__EVENTTARGET=ctl00%24ContentPlaceHolder1%24Pager&__EVENTARGUMENT=" + pageValue + "&__VIEWSTATE=%2FwEPDwUKMTU3NDc4MTQ1Nw9kFgJmD2QWAgIDD2QWBAIDD2QWAgIHDxYCHgRUZXh0BY8xPHVsIGNsYXNzPSduYXYgbmF2LXBpbGxzIG5hdi1qdXN0aWZpZWQnPjxsaT48YSBocmVmPScvaW5kZXguYXNweCc%2B6aaW6aG1PC9hPjxzcGFuPjwvc3Bhbj48L2xpPjxsaSAgY2xhc3M9InVsX21lbnUiPjxhICBoZXJmPScjJz7mlL%2FliqHlhazlvIA8L2E%2BPHRhYmxlPiA8dHI%2BPHRkPjxkaXY%2B5Lit5b%2BD5qaC5Ya1PC9kaXY%2BPC90ZD48dGQgICBjbGFzcz0ibWV1bi1pdGVuLWJvZHkiPjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0dlbmVyYWwvSW5kZXguYXNweD9jaWQ9MDAwMTAwMDEwMDAxMDAwNCIgdGFyZ2V0PSJfYmxhbmsiPuS4reW%2Fg%2BeugOS7izwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9HZW5lcmFsL0luZGV4LmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMTAwMDIiIHRhcmdldD0iX2JsYW5rIj7pooblr7zliIblt6U8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvR2VuZXJhbC9JbmRleC5hc3B4P2NpZD0wMDAxMDAwMTAwMDEwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6IGU57O75pa55byPPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0dlbmVyYWwvSW5kZXguYXNweD9jaWQ9MDAwMTAwMDEwMDAxMDAwMSIgdGFyZ2V0PSJfYmxhbmsiPumDqOmXqOiuvue9rjwvYT48L2Rpdj48L3RkPjwvdHI%2BIDx0cj48dGQ%2BPGRpdj7mlrDpl7vliqjmgIE8L2Rpdj48L3RkPjx0ZCAgIGNsYXNzPSJtZXVuLWl0ZW4tYm9keSI%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvT3BlbkdvdmVybm1lbnQvTGlzdC5hc3B4P2NpZD0wMDAxMDAwMTAwMDIwMDAxIiB0YXJnZXQ9Il9ibGFuayI%2B5bel5L2c5Yqo5oCBPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL09wZW5Hb3Zlcm5tZW50L0xpc3QuYXNweD9jaWQ9MDAwMTAwMDEwMDAyMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuS%2FoeeUqOS%2FoeaBrzwvYT48L2Rpdj48L3RkPjwvdHI%2BIDx0cj48dGQ%2BPGRpdj7mlL%2FliqHlhazlvIA8L2Rpdj48L3RkPjx0ZCAgIGNsYXNzPSJtZXVuLWl0ZW4tYm9keSI%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvT3BlbkdvdmVybm1lbnQvTGlzdC5hc3B4P2NpZD0wMDAxMDAwMTAwMDMwMDAxIiB0YXJnZXQ9Il9ibGFuayI%2B5YWs5byA5L%2Bd6ZqcPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL09wZW5Hb3Zlcm5tZW50L0xpc3QuYXNweD9jaWQ9MDAwMTAwMDEwMDAzMDAwMiIgdGFyZ2V0PSJfYmxhbmsiPuiuoeWIkuaAu%2Be7kzwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9PcGVuR292ZXJubWVudC9MaXN0LmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7kurrkuovkv6Hmga88L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvT3BlbkdvdmVybm1lbnQvTGlzdC5hc3B4P2NpZD0wMDAxMDAwMTAwMDMwMDA0IiB0YXJnZXQ9Il9ibGFuayI%2B6LSi5pS%2F6LWE6YeRPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cDovL2drLmNoZW5nZHUuZ292LmNuL29wZW5BcHBseS9pbmRleC5hY3Rpb24%2FY2lkPTAwMDEwMDAxMDAwMzAwMDUiIHRhcmdldD0iX2JsYW5rIj7kvp3nlLPor7flhazlvIA8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwOi8vZ2suY2hlbmdkdS5nb3YuY24vb3BlblN1Z2dlc3Rpb25Cb3gvaW5kZXguYWN0aW9uP2NpZD0wMDAxMDAwMTAwMDMwMDA2IiB0YXJnZXQ9Il9ibGFuayI%2B5YWs5byA5oSP6KeB566xPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cDovL2drLmNoZW5nZHUuZ292LmNuL2dvdkluZm9QdWIvZGVwdC5hY3Rpb24%2FY2xhc3NJZD0wNzAzNjYiIHRhcmdldD0iX2JsYW5rIj7kv6Hmga%2FlhazlvIDmjIfljZc8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvUGx1cy9BY2NlcHREYXRhLmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMzAwMDkiIHRhcmdldD0iX2JsYW5rIj7lip7kuovnu5%2ForqE8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwOi8vd3d3LmNoZW5nZHUuZ292LmNuL2NoZW5nZHUvY2RtZG0veG1kbV9pbmRleC5zaHRtbD9jaWQ9MDAwMTAwMDEwMDAzMDAwOCIgdGFyZ2V0PSJfYmxhbmsiPuWFmumjjuaUv%2BmjjueDree6vzwvYT48L2Rpdj48L3RkPjwvdHI%2BPC90YWJsZT48L2xpPjxsaSAgY2xhc3M9InVsX21lbnUiPjxhICBoZXJmPScjJz7kuJrliqHlip7nkIY8L2E%2BPHRhYmxlPiA8dHI%2BPHRkPjxkaXY%2B5Y%2BX55CG5Lia5YqhPC9kaXY%2BPC90ZD48dGQgICBjbGFzcz0ibWV1bi1pdGVuLWJvZHkiPjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9jZW50ZXIvaW5kZXguYXNweD9jaWQ9MDAwMjAwMDEwMDIwMDAxIiB0YXJnZXQ9Il9ibGFuayI%2B6aG555uu55m76K6wPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL1NpdGVTZWFyY2gvbmV3aW5kZXguYXNweD9jaWQ9MDAwMjAwMDEwMDIwMDAyIiB0YXJnZXQ9Il9ibGFuayI%2B5Zy65Zyw5p%2Bl6K%2BiPC9hPjwvZGl2PjwvdGQ%2BPC90cj4gPHRyPjx0ZD48ZGl2PuS6pOaYk%2BS%2FoeaBrzwvZGl2PjwvdGQ%2BPHRkICAgY2xhc3M9Im1ldW4taXRlbi1ib2R5Ij48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9KU0dDL0xpc3QuYXNweCIgdGFyZ2V0PSJfYmxhbmsiPuW3peeoi%2BW7uuiuvjwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9Ob3RpY2UvWkZDRy9Ob3RpY2VWZXJzaW9uT25lTGlzdC5hc3B4IiB0YXJnZXQ9Il9ibGFuayI%2B5pS%2F5bqc6YeH6LStPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTGlzdC5hc3B4IiB0YXJnZXQ9Il9ibGFuayI%2B5Zyf5Zyw55%2B%2F5p2DPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0Fzc2V0UmVzb3VyY2UvRGVhbE5vdGljZUxpc3QuYXNweCIgdGFyZ2V0PSJfYmxhbmsiPui1hOS6p%2Bi1hOa6kDwvYT48L2Rpdj48L3RkPjwvdHI%2BIDx0cj48dGQ%2BPGRpdj7mm7TlpJrkuJrliqE8L2Rpdj48L3RkPjx0ZCAgIGNsYXNzPSJtZXVuLWl0ZW4tYm9keSI%2BPGRpdj48YSBocmVmPSJodHRwOi8vd3d3LmNkZ2d6eS5jb20vbWFsbC9JbmRleC5hc3B4P2NpZD0wMDAyMDAwMTAwMzAwMDEiIHRhcmdldD0iX2JsYW5rIj7mlL%2Flupzph4fotK3nlLXlrZDllYbln448L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL0xvZ2luLmFzcHg%2FY2lkPTAwMDIwMDAxMDAzMDAwMiIgdGFyZ2V0PSJfYmxhbmsiPui1hOS6p%2Bi1hOa6kOe9keS4iuernuS7tzwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9CYW5rQm9ycm93L0luZGV4LmFzcHg%2FY2lkPTAwMDIwMDAxMDAzMDAwNCIgdGFyZ2V0PSJfYmxhbmsiPuaUv%2BmHh%2BS%2FoeeUqOaLheS%2Fneiejei1hDwvYT48L2Rpdj48L3RkPjwvdHI%2BIDx0cj48dGQ%2BPGRpdj7mnI3liqHmjIflvJU8L2Rpdj48L3RkPjx0ZCAgIGNsYXNzPSJtZXVuLWl0ZW4tYm9keSI%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvUGx1cy9Ob3RpY2VMaXN0LmFzcHg%2FY2lkPTAwMDIwMDAxMDA0MDAwMSIgdGFyZ2V0PSJfYmxhbmsiPumAmuefpeWFrOWRijwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9JbnN0cnVjdGlvbi9JbmRleC5hc3B4P2NpZD0wMDAyMDAwMTAwNDAwMDIiIHRhcmdldD0iX2JsYW5rIj7lip7kuovmjIfljZc8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvUG9saWNpZXNhbmRyZWd1bGF0aW9ucy9JbmRleC5hc3B4P2NpZD0wMDAyMDAwMTAwNDAwMDMiIHRhcmdldD0iX2JsYW5rIj7mlL%2FnrZbms5Xop4Q8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvQ2FvenVvL2luZGV4LmFzcHg%2FY2lkPTAwMDIwMDAxMDA0MDAwNCIgdGFyZ2V0PSJfYmxhbmsiPuaTjeS9nOaJi%2BWGjDwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9Eb3duQ2VudGVyLmFzcHg%2FY2lkPTAwMDIwMDAxMDA0MDAwNSIgdGFyZ2V0PSJfYmxhbmsiPuS4i%2Bi9veS4k%2BWMujwvYT48L2Rpdj48L3RkPjwvdHI%2BPC90YWJsZT48L2xpPjxsaSBjbGFzcz0idWxfbWVudSI%2BPGEgdGFyZ2V0PSJfYmxhbmsiPuS6kuWKqOS6pOa1gTwvYT48dWw%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvSW50ZXJhY3Rpb24vSW50ZXJhY3Rpb25MaXN0TmV3MS5hc3B4IiB0YXJnZXQ9Il9ibGFuayI%2B5Li75Lu75L%2Bh566xPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwOi8vMjAxMy5jZGdnenkuY29tL2FwcDEvdHdvL3dqZGMuanNwP2NpZD0wMDAxMDAwMjAwMDIiIHRhcmdldD0iX2JsYW5rIj7osIPmn6XlvoHpm4Y8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHA6Ly93ZWliby5jb20vdS8zOTczMzM4ODM2IyEvdS8zOTczMzM4ODM2P2lzX2hvdD0xP2NpZD0wMDAxMDAwMjAwMDMiIHRhcmdldD0iX2JsYW5rIj7mlrDmtarlvq7ljZo8L2E%2BPC9saT48L3VsPjwvbGk%2BPGxpIGNsYXNzPSJ1bF9tZW51Ij48YSB0YXJnZXQ9Il9ibGFuayI%2B5YiG5Lit5b%2BDPC9hPjx1bD4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vbG9uZ3F1YW55aT9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6b6Z5rOJ6am%2F5Yy6PC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3FpbmdiYWlqaWFuZz9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6Z2S55m95rGf5Yy6PC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3hpbmR1P2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7mlrDpg73ljLo8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vd2Vuamlhbmc%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPua4qeaxn%2BWMujwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaHVhbmdsaXU%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuWPjOa1geWMujwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9waWR1P2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7pg6vpg73ljLo8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vamlhbnlhbmc%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPueugOmYs%2BW4gjwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9kdWppYW5neWFuP2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7pg73msZ%2FloLDluII8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vcGVuZ3pob3U%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuW9reW3nuW4gjwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9xaW9uZ2xhaT9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6YKb5bSD5biCPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL2Nob25nemhvdT9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B5bSH5bee5biCPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL2ppbmd0YW5nP2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7ph5HloILljr88L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20veGluamluP2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7mlrDmtKXljr88L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vZGF5aT9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B5aSn6YKR5Y6%2FPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3B1amlhbmc%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuiSsuaxn%2BWOvzwvYT48L2xpPjwvdWw%2BPC9saT4gPC91bD5kAgcPZBYGAgEPFgIfAAWKAemhtemdouWKoOi9veaAu%2BaXtumXtO%2B8mjkz5q%2Br56eSPGJyLz7mn6Xor6LliJfooajmgLvml7bpl7TvvJo2Muavq%2Benkjxici8%2B5p%2Bl6K%2Bi5p2h5Lu25oC75pe26Ze077yaMzHmr6vnp5I8YnIvPuacrOacuklQ77yaMTc4LjE4LjEuNzY8YnIvPmQCCw8WAh4LXyFJdGVtQ291bnQCEBYgZg9kFgJmDxUCBjUxMDEwMQnluILmnKznuqdkAgEPZBYCZg8VAgY1MTAxMTIM6b6Z5rOJ6am%2F5Yy6ZAICD2QWAmYPFQIGNTEwMTEzDOmdkueZveaxn%2BWMumQCAw9kFgJmDxUCBjUxMDExNAnmlrDpg73ljLpkAgQPZBYCZg8VAgY1MTAxMTUJ5rip5rGf5Yy6ZAIFD2QWAmYPFQIGNTEwMTE2CeWPjOa1geWMumQCBg9kFgJmDxUCBjUxMDE4NQnnroDpmLPluIJkAgcPZBYCZg8VAgY1MTAxODEM6YO95rGf5aCw5biCZAIID2QWAmYPFQIGNTEwMTgyCeW9reW3nuW4gmQCCQ9kFgJmDxUCBjUxMDE4MwnpgpvltIPluIJkAgoPZBYCZg8VAgY1MTAxODQJ5bSH5bee5biCZAILD2QWAmYPFQIGNTEwMTI0CemDq%2BmDveWMumQCDA9kFgJmDxUCBjUxMDEyMQnph5HloILljr9kAg0PZBYCZg8VAgY1MTAxMzIJ5paw5rSl5Y6%2FZAIOD2QWAmYPFQIGNTEwMTI5CeWkp%2BmCkeWOv2QCDw9kFgJmDxUCBjUxMDEzMQnokrLmsZ%2Fljr9kAhEPZBYCZg9kFgYCAw8PFgIfAAUFMS8xOTlkZAIJDxYCHwECChYUZg9kFgZmDxUBCeW4guacrOe6p2QCAQ8PFgIeC05hdmlnYXRlVXJsBWBodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvTGFuZFRyYWRlL0xhbmROb3RpY2VDb250ZW50LmFzcHg%2FaWQ9MzE2ZWM3NWJiZTEyNDNkZTg4NjkwNjgyOTFjZTYwOGNkFgJmDxUBMeaLjeWNluS8muaIkOS6pOe7k%2BaenOS4gOiniOihqCgyMDE55bm0MDjmnIgyOeaXpSlkAgIPFQIKMjAxOS0wOC0yOSk8ZGl2IGNsYXNzPSIgZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCAQ9kFgZmDxUBDOmdkueZveaxn%2BWMumQCAQ8PFgIfAgVgaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTm90aWNlQ29udGVudC5hc3B4P2lkPUEzOThCRkIzNUE5MTRDNkM5QjZDNUQwNzNDRURCRjEzZBYCZg8VAT%2FmjILniYzkvJrnu5PmnpzkuIDop4jooagoMjAxOeW5tDA45pyIMTXml6XliLAyMDE55bm0MDjmnIgyOeaXpSlkAgIPFQIKMjAxOS0wOC0yOSk8ZGl2IGNsYXNzPSIgZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCAg9kFgZmDxUBCemDq%2BmDveWMumQCAQ8PFgIfAgVgaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTm90aWNlQ29udGVudC5hc3B4P2lkPUQxMDBCMkYxRDJFQjQ3QUJCRTdFNTRCMjYwNUFBNDIxZBYCZg8VAV7pg6vpg73ljLrmjILniYzlh7rorqnlm73mnInlu7rorr7nlKjlnLDkvb%2FnlKjmnYPlhazlkYoo6YOr6YO95oiQ5YWs6LWE5Zyf572R5oyC5ZGKKDIwMTkpMDXlj7cpZAICDxUCCjIwMTktMDgtMjkiPGRpdiBjbGFzcz0iICAiPuWNs%2BWwhuaKpeWQjTwvZGl2PmQCAw9kFgZmDxUBCeW4guacrOe6p2QCAQ8PFgIfAgVgaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTm90aWNlQ29udGVudC5hc3B4P2lkPTU4NjhhODU1MzA4NDRkNDlhMWVkZWEzZjkyMGI1NmI2ZBYCZg8VAT%2FmjILniYzkvJrnu5PmnpzkuIDop4jooagoMjAxOeW5tDA45pyIMTTml6XliLAyMDE55bm0MDjmnIgyOOaXpSlkAgIPFQIKMjAxOS0wOC0yOCk8ZGl2IGNsYXNzPSIgZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCBA9kFgZmDxUBCeW4guacrOe6p2QCAQ8PFgIfAgVgaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTm90aWNlQ29udGVudC5hc3B4P2lkPTgxOTRlMTY1M2Q5NzRjODk5MjExYmFkMTcyZGFiY2JiZBYCZg8VAT%2FmjILniYzkvJrnu5PmnpzkuIDop4jooagoMjAxOeW5tDA45pyIMTTml6XliLAyMDE55bm0MDjmnIgyOOaXpSlkAgIPFQIKMjAxOS0wOC0yOCk8ZGl2IGNsYXNzPSIgZW50ZXJpbmciPiZuYnNwOyZuYnNwOzwvZGl2PmQCBQ9kFgZmDxUBCeW4guacrOe6p2QCAQ8PFgIfAgVgaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTm90aWNlQ29udGVudC5hc3B4P2lkPTA2MTIxOTRiODM4ODRmNmY5Y2ViZDcyZjQ1OTkzZWFjZBYCZg8VATHmi43ljZbkvJrmiJDkuqTnu5PmnpzkuIDop4jooagoMjAxOeW5tDA45pyIMjjml6UpZAICDxUCCjIwMTktMDgtMjgpPGRpdiBjbGFzcz0iIGVudGVyaW5nIj4mbmJzcDsmbmJzcDs8L2Rpdj5kAgYPZBYGZg8VAQzpnZLnmb3msZ%2FljLpkAgEPDxYCHwIFYGh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9MYW5kVHJhZGUvTGFuZE5vdGljZUNvbnRlbnQuYXNweD9pZD03MjJGNUY1ODU4OUU0QkJEQkYwNDhDM0U5REI4RTU1MmQWAmYPFQFq5oiQ6YO95biC6Z2S55m95rGf5Yy65oyC54mM5Ye66K6p5Zu95pyJ5bu66K6%2B55So5Zyw5L2%2F55So5p2D5YWs5ZGKKOmdkueZveaIkOWFrOi1hOWcn%2Be9keaMguWRiigyMDE5KTA55Y%2B3KWQCAg8VAgoyMDE5LTA4LTI4IjxkaXYgY2xhc3M9IiAgIj7ljbPlsIbmiqXlkI08L2Rpdj5kAgcPZBYGZg8VAQnltIflt57luIJkAgEPDxYCHwIFYGh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9MYW5kVHJhZGUvTGFuZE5vdGljZUNvbnRlbnQuYXNweD9pZD0yQTAzNjVEM0JBNEY0QThCQTlBQ0MxNzBGRTk2NkM2MmQWAmYPFQFb5oyC54mM5Ye66K6p5Zu95pyJ5bu66K6%2B55So5Zyw5L2%2F55So5p2D5pu05q2j5YWs5ZGKKOW0h%2BW3nuaIkOWFrOi1hOWcn%2Be9keaMguWRiigyMDE5KTAz5Y%2B3KWQCAg8VAgoyMDE5LTA4LTI2KTxkaXYgY2xhc3M9IiBlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAIID2QWBmYPFQEJ6JKy5rGf5Y6%2FZAIBDw8WAh8CBWBodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvTGFuZFRyYWRlL0xhbmROb3RpY2VDb250ZW50LmFzcHg%2FaWQ9ODNGNjVEN0ZCMjc3NDkwRUFCRkEwNTkzMTA2ODZCODlkFgJmDxUBP%2BaMgueJjOS8mue7k%2BaenOS4gOiniOihqCgyMDE55bm0MDjmnIgxMuaXpeWIsDIwMTnlubQwOOaciDI25pelKWQCAg8VAgoyMDE5LTA4LTI2KTxkaXYgY2xhc3M9IiBlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAIJD2QWBmYPFQEJ6YKb5bSD5biCZAIBDw8WAh8CBWBodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvTGFuZFRyYWRlL0xhbmROb3RpY2VDb250ZW50LmFzcHg%2FaWQ9RkVCMDRBNkU5Nzg2NEI1ODhCNjlERUUyRDREQkMwMTBkFgJmDxUBP%2BaMgueJjOS8mue7k%2BaenOS4gOiniOihqCgyMDE55bm0MDjmnIgxMuaXpeWIsDIwMTnlubQwOOaciDI25pelKWQCAg8VAgoyMDE5LTA4LTI2KTxkaXYgY2xhc3M9IiBlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAILDw8WBB4IUGFnZVNpemUCCh4LUmVjb3JkY291bnQCxA9kZGTv1CBLQbXMRSGjAJECk9TSeNfqzg%3D%3D&__VIEWSTATEGENERATOR=87A20B68&__EVENTVALIDATION=%2FwEdAAhZEJ%2BEThIyeDEYVfC4x1M%2BZi7H72kkAvPqROrOG28FXID8g%2FxeCzDZJOPgpGV4zViznYSIw3B963y%2FxzaOhgoHdty0mXvz7f%2B9YtBAL8kV3WV246YQ%2BCegotD9lFFwpmUKfH5ngHtxPmfpcq0XKJP7jDu35o5CUn6umW4JNpE1pw6EfP%2FV%2BB%2F5nifQQj5cjCIHDeZu&ctl00%24ContentPlaceHolder1%24displaytypeval=" + typeValue + "&ctl00%24ContentPlaceHolder1%24displaystateval=0&ctl00%24ContentPlaceHolder1%24dealaddressval=0&ctl00%24ContentPlaceHolder1%24keyword=&__ASYNCPOST=true&";

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

    //公共资源交易平台-成都（资产资源）
    public void getNetInfoFromGGZYCD2(Integer days) {
        logger.info("----公共资源交易平台-成都(资产资源), start---------");
        try {
            Date date = getInstanceDate(days);//得到前1天

            String[] needContentType = new String[]{"结果公告"};
            List<String> needTypes = Arrays.asList(needContentType);

            String urlInfo = "https://www.cdggzy.com/site/AssetResource/DealNoticeList.aspx";
            //类型
            Elements typeEelements = getContent(urlInfo, ".optionlist", "");
            Elements typeDivs = typeEelements.get(0).select("div");
            for (Element typeDiv : typeDivs) {
                String typeName = typeDiv.childNodes().get(0).toString().trim();
                if (needTypes.contains(typeName)) {
                    String typeValue = typeDiv.attributes().get("data-value");
                    //取得页数
                    Elements pageElements = getGGZYCDHtml2("#Pager a", "", typeValue);
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
                            Elements elements = getGGZYCDHtml2(".row.contentitem", String.valueOf(i), typeValue);
                            for (Element item : elements) {
                                Elements publishtimeElement = item.select(".publishtime");
                                String publishtimeStr = publishtimeElement.get(0).childNodes().get(0).toString().substring(1).trim();
                                Date publishtime = DateUtils.parse(publishtimeStr);
                                if (publishtime == null) continue;
                                if (publishtime.compareTo(date) < 0) break circ;

                                Elements addressElement = item.select(".col-xs-1");
                                String address = addressElement.get(0).childNodes().get(0).toString();
                                String titleStr = item.select("a").get(0).childNodes().get(0).toString();
                                String link = item.select("a").get(0).attributes().get("href");

                                Elements tableElementHrefs = getContent(link, ".noticecontent", "");
                                if (tableElementHrefs.size() <= 0) {
                                    continue;
                                }
                                Elements tableElements = tableElementHrefs.select("table");
                                if (tableElements == null || tableElements.size() == 0) continue;
                                Elements tdElements = null;
                                if (tableElements.size() > 1) {
                                    tdElements = tableElements.get(1).select("tr");
                                } else {
                                    tdElements = tableElements.get(0).select("tr");
                                }
                                Integer length = tdElements.get(0).select("th").size() != 0 ? tdElements.get(0).select("th").size() : tdElements.get(0).select("tb").size();
                                //获取字段名称
                                for (int k = 0; k < tdElements.size(); k++) {
                                    Elements select = tdElements.get(k).select("td");
                                    if (select.size() == 0) continue;
                                    List<String> fieldValues = Lists.newArrayList();
                                    for (int j = 0; j < length; j++) {
                                        try {
                                            String fieldValue = checkNull(select, j);
                                            fieldValues.add(publicService.delHtmlTags(fieldValue));
                                        } catch (Exception e) {
                                        }
                                    }
                                    NetInfoRecord netInfoRecord = new NetInfoRecord();
                                    netInfoRecord.setProvince("四川");
                                    netInfoRecord.setCity("成都");
                                    netInfoRecord.setType(typeName);
                                    netInfoRecord.setSourceSiteUrl(link);
                                    netInfoRecord.setBeginTime(publishtime);
                                    netInfoRecord.setEndTime(publishtime);
                                    netInfoRecord.setTitle(titleStr.replaceAll("\n", ""));
                                    netInfoRecord.setSourceSiteName("公共资源交易平台-成都");
                                    StringBuilder content = new StringBuilder();
                                    for (int m = 0; m < fieldValues.size(); m++) {
                                        content.append(fieldValues.get(m) + "；");
                                    }

                                    content.append("发布时间：" + publishtimeStr + "；");
                                    content.append("地址：" + address.replaceAll("\n", "").substring(1, address.length() - 2) + "；");
                                    netInfoRecord.setContent(content.toString());
                                    netInfoRecordDao.addInfo(netInfoRecord);
                                }

                            }
                        }
                    }
                }
            }
            logger.info("----公共资源交易平台-成都(资产资源), end---------");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Elements getGGZYCDHtml2(String element, String pageValue, String typeValue) {
        try {
            // 1. 获取访问地址URL
            URL url = new URL("https://www.cdggzy.com/site/AssetResource/DealNoticeList.aspx");
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
            connection.setRequestProperty("Host", "www.cdggzy.com");
            connection.setRequestProperty("Origin", "https://www.cdggzy.com");
            connection.setRequestProperty("Referer", "https://www.cdggzy.com/site/LandTrade/LandList.aspx");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36");
            // 连接
            connection.connect();
            /* 4. 处理输入输出 */
            // 写入参数到请求中
            if (StringUtils.isEmpty(pageValue))
                pageValue = "1";
            if (StringUtils.isEmpty(typeValue))
                typeValue = "";
            String params = "ctl00%24ScriptManager1=ctl00%24ContentPlaceHolder1%24UpdatePanel1%7Cctl00%24ContentPlaceHolder1%24Pager&ctl00%24ContentPlaceHolder1%24displaytypeval=" + typeValue + "&ctl00%24ContentPlaceHolder1%24displaystateval=0&ctl00%24ContentPlaceHolder1%24dealaddressval=0&ctl00%24ContentPlaceHolder1%24keyword=&__VIEWSTATE=%2FwEPDwUKMTE2OTUxMzc2Nw9kFgJmD2QWAgIDD2QWBAIDD2QWAgIHDxYCHgRUZXh0BY8xPHVsIGNsYXNzPSduYXYgbmF2LXBpbGxzIG5hdi1qdXN0aWZpZWQnPjxsaT48YSBocmVmPScvaW5kZXguYXNweCc%2B6aaW6aG1PC9hPjxzcGFuPjwvc3Bhbj48L2xpPjxsaSAgY2xhc3M9InVsX21lbnUiPjxhICBoZXJmPScjJz7mlL%2FliqHlhazlvIA8L2E%2BPHRhYmxlPiA8dHI%2BPHRkPjxkaXY%2B5Lit5b%2BD5qaC5Ya1PC9kaXY%2BPC90ZD48dGQgICBjbGFzcz0ibWV1bi1pdGVuLWJvZHkiPjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0dlbmVyYWwvSW5kZXguYXNweD9jaWQ9MDAwMTAwMDEwMDAxMDAwNCIgdGFyZ2V0PSJfYmxhbmsiPuS4reW%2Fg%2BeugOS7izwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9HZW5lcmFsL0luZGV4LmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMTAwMDIiIHRhcmdldD0iX2JsYW5rIj7pooblr7zliIblt6U8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvR2VuZXJhbC9JbmRleC5hc3B4P2NpZD0wMDAxMDAwMTAwMDEwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6IGU57O75pa55byPPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0dlbmVyYWwvSW5kZXguYXNweD9jaWQ9MDAwMTAwMDEwMDAxMDAwMSIgdGFyZ2V0PSJfYmxhbmsiPumDqOmXqOiuvue9rjwvYT48L2Rpdj48L3RkPjwvdHI%2BIDx0cj48dGQ%2BPGRpdj7mlrDpl7vliqjmgIE8L2Rpdj48L3RkPjx0ZCAgIGNsYXNzPSJtZXVuLWl0ZW4tYm9keSI%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvT3BlbkdvdmVybm1lbnQvTGlzdC5hc3B4P2NpZD0wMDAxMDAwMTAwMDIwMDAxIiB0YXJnZXQ9Il9ibGFuayI%2B5bel5L2c5Yqo5oCBPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL09wZW5Hb3Zlcm5tZW50L0xpc3QuYXNweD9jaWQ9MDAwMTAwMDEwMDAyMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuS%2FoeeUqOS%2FoeaBrzwvYT48L2Rpdj48L3RkPjwvdHI%2BIDx0cj48dGQ%2BPGRpdj7mlL%2FliqHlhazlvIA8L2Rpdj48L3RkPjx0ZCAgIGNsYXNzPSJtZXVuLWl0ZW4tYm9keSI%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvT3BlbkdvdmVybm1lbnQvTGlzdC5hc3B4P2NpZD0wMDAxMDAwMTAwMDMwMDAxIiB0YXJnZXQ9Il9ibGFuayI%2B5YWs5byA5L%2Bd6ZqcPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL09wZW5Hb3Zlcm5tZW50L0xpc3QuYXNweD9jaWQ9MDAwMTAwMDEwMDAzMDAwMiIgdGFyZ2V0PSJfYmxhbmsiPuiuoeWIkuaAu%2Be7kzwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9PcGVuR292ZXJubWVudC9MaXN0LmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7kurrkuovkv6Hmga88L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvT3BlbkdvdmVybm1lbnQvTGlzdC5hc3B4P2NpZD0wMDAxMDAwMTAwMDMwMDA0IiB0YXJnZXQ9Il9ibGFuayI%2B6LSi5pS%2F6LWE6YeRPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cDovL2drLmNoZW5nZHUuZ292LmNuL29wZW5BcHBseS9pbmRleC5hY3Rpb24%2FY2lkPTAwMDEwMDAxMDAwMzAwMDUiIHRhcmdldD0iX2JsYW5rIj7kvp3nlLPor7flhazlvIA8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwOi8vZ2suY2hlbmdkdS5nb3YuY24vb3BlblN1Z2dlc3Rpb25Cb3gvaW5kZXguYWN0aW9uP2NpZD0wMDAxMDAwMTAwMDMwMDA2IiB0YXJnZXQ9Il9ibGFuayI%2B5YWs5byA5oSP6KeB566xPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cDovL2drLmNoZW5nZHUuZ292LmNuL2dvdkluZm9QdWIvZGVwdC5hY3Rpb24%2FY2xhc3NJZD0wNzAzNjYiIHRhcmdldD0iX2JsYW5rIj7kv6Hmga%2FlhazlvIDmjIfljZc8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvUGx1cy9BY2NlcHREYXRhLmFzcHg%2FY2lkPTAwMDEwMDAxMDAwMzAwMDkiIHRhcmdldD0iX2JsYW5rIj7lip7kuovnu5%2ForqE8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwOi8vd3d3LmNoZW5nZHUuZ292LmNuL2NoZW5nZHUvY2RtZG0veG1kbV9pbmRleC5zaHRtbD9jaWQ9MDAwMTAwMDEwMDAzMDAwOCIgdGFyZ2V0PSJfYmxhbmsiPuWFmumjjuaUv%2BmjjueDree6vzwvYT48L2Rpdj48L3RkPjwvdHI%2BPC90YWJsZT48L2xpPjxsaSAgY2xhc3M9InVsX21lbnUiPjxhICBoZXJmPScjJz7kuJrliqHlip7nkIY8L2E%2BPHRhYmxlPiA8dHI%2BPHRkPjxkaXY%2B5Y%2BX55CG5Lia5YqhPC9kaXY%2BPC90ZD48dGQgICBjbGFzcz0ibWV1bi1pdGVuLWJvZHkiPjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9jZW50ZXIvaW5kZXguYXNweD9jaWQ9MDAwMjAwMDEwMDIwMDAxIiB0YXJnZXQ9Il9ibGFuayI%2B6aG555uu55m76K6wPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL1NpdGVTZWFyY2gvbmV3aW5kZXguYXNweD9jaWQ9MDAwMjAwMDEwMDIwMDAyIiB0YXJnZXQ9Il9ibGFuayI%2B5Zy65Zyw5p%2Bl6K%2BiPC9hPjwvZGl2PjwvdGQ%2BPC90cj4gPHRyPjx0ZD48ZGl2PuS6pOaYk%2BS%2FoeaBrzwvZGl2PjwvdGQ%2BPHRkICAgY2xhc3M9Im1ldW4taXRlbi1ib2R5Ij48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9KU0dDL0xpc3QuYXNweCIgdGFyZ2V0PSJfYmxhbmsiPuW3peeoi%2BW7uuiuvjwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9Ob3RpY2UvWkZDRy9Ob3RpY2VWZXJzaW9uT25lTGlzdC5hc3B4IiB0YXJnZXQ9Il9ibGFuayI%2B5pS%2F5bqc6YeH6LStPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0xhbmRUcmFkZS9MYW5kTGlzdC5hc3B4IiB0YXJnZXQ9Il9ibGFuayI%2B5Zyf5Zyw55%2B%2F5p2DPC9hPjwvZGl2PjxkaXY%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0Fzc2V0UmVzb3VyY2UvRGVhbE5vdGljZUxpc3QuYXNweCIgdGFyZ2V0PSJfYmxhbmsiPui1hOS6p%2Bi1hOa6kDwvYT48L2Rpdj48L3RkPjwvdHI%2BIDx0cj48dGQ%2BPGRpdj7mm7TlpJrkuJrliqE8L2Rpdj48L3RkPjx0ZCAgIGNsYXNzPSJtZXVuLWl0ZW4tYm9keSI%2BPGRpdj48YSBocmVmPSJodHRwOi8vd3d3LmNkZ2d6eS5jb20vbWFsbC9JbmRleC5hc3B4P2NpZD0wMDAyMDAwMTAwMzAwMDEiIHRhcmdldD0iX2JsYW5rIj7mlL%2Flupzph4fotK3nlLXlrZDllYbln448L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL0xvZ2luLmFzcHg%2FY2lkPTAwMDIwMDAxMDAzMDAwMiIgdGFyZ2V0PSJfYmxhbmsiPui1hOS6p%2Bi1hOa6kOe9keS4iuernuS7tzwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9CYW5rQm9ycm93L0luZGV4LmFzcHg%2FY2lkPTAwMDIwMDAxMDAzMDAwNCIgdGFyZ2V0PSJfYmxhbmsiPuaUv%2BmHh%2BS%2FoeeUqOaLheS%2Fneiejei1hDwvYT48L2Rpdj48L3RkPjwvdHI%2BIDx0cj48dGQ%2BPGRpdj7mnI3liqHmjIflvJU8L2Rpdj48L3RkPjx0ZCAgIGNsYXNzPSJtZXVuLWl0ZW4tYm9keSI%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvUGx1cy9Ob3RpY2VMaXN0LmFzcHg%2FY2lkPTAwMDIwMDAxMDA0MDAwMSIgdGFyZ2V0PSJfYmxhbmsiPumAmuefpeWFrOWRijwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9JbnN0cnVjdGlvbi9JbmRleC5hc3B4P2NpZD0wMDAyMDAwMTAwNDAwMDIiIHRhcmdldD0iX2JsYW5rIj7lip7kuovmjIfljZc8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvUG9saWNpZXNhbmRyZWd1bGF0aW9ucy9JbmRleC5hc3B4P2NpZD0wMDAyMDAwMTAwNDAwMDMiIHRhcmdldD0iX2JsYW5rIj7mlL%2FnrZbms5Xop4Q8L2E%2BPC9kaXY%2BPGRpdj48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvQ2FvenVvL2luZGV4LmFzcHg%2FY2lkPTAwMDIwMDAxMDA0MDAwNCIgdGFyZ2V0PSJfYmxhbmsiPuaTjeS9nOaJi%2BWGjDwvYT48L2Rpdj48ZGl2PjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9Eb3duQ2VudGVyLmFzcHg%2FY2lkPTAwMDIwMDAxMDA0MDAwNSIgdGFyZ2V0PSJfYmxhbmsiPuS4i%2Bi9veS4k%2BWMujwvYT48L2Rpdj48L3RkPjwvdHI%2BPC90YWJsZT48L2xpPjxsaSBjbGFzcz0idWxfbWVudSI%2BPGEgdGFyZ2V0PSJfYmxhbmsiPuS6kuWKqOS6pOa1gTwvYT48dWw%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvSW50ZXJhY3Rpb24vSW50ZXJhY3Rpb25MaXN0TmV3MS5hc3B4IiB0YXJnZXQ9Il9ibGFuayI%2B5Li75Lu75L%2Bh566xPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwOi8vMjAxMy5jZGdnenkuY29tL2FwcDEvdHdvL3dqZGMuanNwP2NpZD0wMDAxMDAwMjAwMDIiIHRhcmdldD0iX2JsYW5rIj7osIPmn6XlvoHpm4Y8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHA6Ly93ZWliby5jb20vdS8zOTczMzM4ODM2IyEvdS8zOTczMzM4ODM2P2lzX2hvdD0xP2NpZD0wMDAxMDAwMjAwMDMiIHRhcmdldD0iX2JsYW5rIj7mlrDmtarlvq7ljZo8L2E%2BPC9saT48L3VsPjwvbGk%2BPGxpIGNsYXNzPSJ1bF9tZW51Ij48YSB0YXJnZXQ9Il9ibGFuayI%2B5YiG5Lit5b%2BDPC9hPjx1bD4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vbG9uZ3F1YW55aT9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6b6Z5rOJ6am%2F5Yy6PC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3FpbmdiYWlqaWFuZz9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6Z2S55m95rGf5Yy6PC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3hpbmR1P2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7mlrDpg73ljLo8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vd2Vuamlhbmc%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPua4qeaxn%2BWMujwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaHVhbmdsaXU%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuWPjOa1geWMujwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9waWR1P2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7pg6vpg73ljLo8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vamlhbnlhbmc%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPueugOmYs%2BW4gjwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9kdWppYW5neWFuP2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7pg73msZ%2FloLDluII8L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vcGVuZ3pob3U%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuW9reW3nuW4gjwvYT48L2xpPiA8bGk%2BPGEgaHJlZj0iaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9xaW9uZ2xhaT9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B6YKb5bSD5biCPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL2Nob25nemhvdT9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B5bSH5bee5biCPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL2ppbmd0YW5nP2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7ph5HloILljr88L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20veGluamluP2NpZD0wMDAxMDAwMzAwMDMiIHRhcmdldD0iX2JsYW5rIj7mlrDmtKXljr88L2E%2BPC9saT4gPGxpPjxhIGhyZWY9Imh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vZGF5aT9jaWQ9MDAwMTAwMDMwMDAzIiB0YXJnZXQ9Il9ibGFuayI%2B5aSn6YKR5Y6%2FPC9hPjwvbGk%2BIDxsaT48YSBocmVmPSJodHRwczovL3d3dy5jZGdnenkuY29tL3B1amlhbmc%2FY2lkPTAwMDEwMDAzMDAwMyIgdGFyZ2V0PSJfYmxhbmsiPuiSsuaxn%2BWOvzwvYT48L2xpPjwvdWw%2BPC9saT4gPC91bD5kAgcPZBYEAgcPFgIeC18hSXRlbUNvdW50AhAWIGYPZBYCZg8VAgY1MTAxMDEJ5biC5pys57qnZAIBD2QWAmYPFQIGNTEwMTEyDOm%2Bmeaziempv%2BWMumQCAg9kFgJmDxUCBjUxMDExMwzpnZLnmb3msZ%2FljLpkAgMPZBYCZg8VAgY1MTAxMTQJ5paw6YO95Yy6ZAIED2QWAmYPFQIGNTEwMTE1Cea4qeaxn%2BWMumQCBQ9kFgJmDxUCBjUxMDExNgnlj4zmtYHljLpkAgYPZBYCZg8VAgY1MTAxODUJ566A6Ziz5biCZAIHD2QWAmYPFQIGNTEwMTgxDOmDveaxn%2BWgsOW4gmQCCA9kFgJmDxUCBjUxMDE4Mgnlva3lt57luIJkAgkPZBYCZg8VAgY1MTAxODMJ6YKb5bSD5biCZAIKD2QWAmYPFQIGNTEwMTg0CeW0h%2BW3nuW4gmQCCw9kFgJmDxUCBjUxMDEyNAnpg6vpg73ljLpkAgwPZBYCZg8VAgY1MTAxMjEJ6YeR5aCC5Y6%2FZAIND2QWAmYPFQIGNTEwMTMyCeaWsOa0peWOv2QCDg9kFgJmDxUCBjUxMDEyOQnlpKfpgpHljr9kAg8PZBYCZg8VAgY1MTAxMzEJ6JKy5rGf5Y6%2FZAIND2QWAmYPZBYGAgMPDxYCHwAFBTEvMzExZGQCCQ8WAh8BAgoWFGYPZBYGZg8VAQnph5HniZvljLpkAgEPDxYCHgtOYXZpZ2F0ZVVybAVkaHR0cHM6Ly93d3cuY2RnZ3p5LmNvbS9zaXRlL0Fzc2V0UmVzb3VyY2UvRGVhbE5vdGljZUNvbnRlbnQuYXNweD9pZD1lMGQ0NzcwNWQxZDg0NTUxOWQ0OWU1ZDVkNmU2YWFiNWQWAmYPFQKDAeaIkOmDveW4gumHkeeJm%2BWMuuW4guWcuuebkeedo%2BeuoeeQhuWxgO%2B8iOaIkOmDveW4gumHkeeJm%2BWMuuefpeivhuS6p%2Badg%2BWxgO%2B8iei1hOS6p%2BWkhOe9ruernuS7t%2Be7k%2BaenOWFrOekuu%2B8iDIwMTnlubQwOeaciDA05pel77yJAGQCAg8VAgoyMDE5LTA5LTA0KDxkaXYgY2xhc3M9ImVudGVyaW5nIj4mbmJzcDsmbmJzcDs8L2Rpdj5kAgEPZBYGZg8VAQnluILmnKznuqdkAgEPDxYCHwIFZGh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9Bc3NldFJlc291cmNlL0RlYWxOb3RpY2VDb250ZW50LmFzcHg%2FaWQ9OWIwMDhlNmY5OWQxNGU4Y2IxYWY3MjYzZmIyNjRiZjFkFgJmDxUCVuaIkOmDvea2pumUpuWfjuWunuS4muaciemZkOWFrOWPuOi1hOS6p%2BWHuuenn%2BernuS7t%2Be7k%2BaenOWFrOekuu%2B8iDIwMTnlubQwOeaciDA05pel77yJAGQCAg8VAgoyMDE5LTA5LTA0KDxkaXYgY2xhc3M9ImVudGVyaW5nIj4mbmJzcDsmbmJzcDs8L2Rpdj5kAgIPZBYGZg8VAQnluILmnKznuqdkAgEPDxYCHwIFZGh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9Bc3NldFJlc291cmNlL0RlYWxOb3RpY2VDb250ZW50LmFzcHg%2FaWQ9NDc1OWNhNDYzYmEwNGNlOTk2MjdjNzNlNTJhYWJkMGZkFgJmDxUCWeaIkOmDveW3peaKlei1hOS6p%2Be7j%2BiQpeaciemZkOWFrOWPuOi1hOS6p%2BWHuuenn%2BernuS7t%2Be7k%2BaenOWFrOekuu%2B8iDIwMTnlubQwOeaciDA05pel77yJAGQCAg8VAgoyMDE5LTA5LTA0KDxkaXYgY2xhc3M9ImVudGVyaW5nIj4mbmJzcDsmbmJzcDs8L2Rpdj5kAgMPZBYGZg8VAQzpvpnms4npqb%2FljLpkAgEPDxYCHwIFZGh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9Bc3NldFJlc291cmNlL0RlYWxOb3RpY2VDb250ZW50LmFzcHg%2FaWQ9REVDQzNFNjQ3QkM2NEM1NkJEMzIwNzU3OUU1NzBDNDNkFgJmDxUCaOaIkOmDvee7j%2Ba1juaKgOacr%2BW8gOWPkeWMuuW7uuiuvuWPkeWxleaciemZkOWFrOWPuOi1hOS6p%2BWHuuenn%2BS6pOaYk%2Be7k%2BaenOWFrOWRiu%2B8iDIwMTnlubQwOeaciDA05pel77yJAGQCAg8VAgoyMDE5LTA5LTA0KDxkaXYgY2xhc3M9ImVudGVyaW5nIj4mbmJzcDsmbmJzcDs8L2Rpdj5kAgQPZBYGZg8VAQzpnZLnmb3msZ%2FljLpkAgEPDxYCHwIFZGh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9Bc3NldFJlc291cmNlL0RlYWxOb3RpY2VDb250ZW50LmFzcHg%2FaWQ9MzYwMUNEQ0NGNzc3NDdGOTgxRjRBQ0FEMTcyRDI2QUZkFgJmDxUCceaIkOmDveW4gumdkueZveaxn%2BWMuuaWh%2BWMluS9k%2BiCsuWSjOaXhea4uOWxgOi1hOS6p%2BWkhOe9ru%2B8iOesrOS6jOasoe%2B8ieS6pOaYk%2Be7k%2BaenOWFrOWRiu%2B8iDIwMTnlubQwOeaciDA05pel77yJAGQCAg8VAgoyMDE5LTA5LTA0KDxkaXYgY2xhc3M9ImVudGVyaW5nIj4mbmJzcDsmbmJzcDs8L2Rpdj5kAgUPZBYGZg8VAQzpg73msZ%2FloLDluIJkAgEPDxYCHwIFZGh0dHBzOi8vd3d3LmNkZ2d6eS5jb20vc2l0ZS9Bc3NldFJlc291cmNlL0RlYWxOb3RpY2VDb250ZW50LmFzcHg%2FaWQ9MzA0RDAwNENBRDhBNDEwQkI1RkUxRkMxNTQ0REFEOUFkFgJmDxUCcumDveaxn%2BWgsOW4guWcn%2BWcsOWCqOWkh%2BS4reW%2Fg%2BmdkuWfjuWbvemZhemFkuW6l%2BmFjeWll%2BiuvuaWveWPiumFjeWll%2BeUqOaIv%2Bi1hOS6p%2Bi9rOiuqeWFrOW8gOaLjeWNlueahOe7k%2BaenOWFrOWRiioo6YO95rGf5aCw5biC5YWs6LWE5ouN5ZGK44CUMjAxOeOAlTAwMuWPtylkAgIPFQIKMjAxOS0wOS0wMyg8ZGl2IGNsYXNzPSJlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAIGD2QWBmYPFQEJ6auY5paw5Yy6ZAIBDw8WAh8CBWRodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvQXNzZXRSZXNvdXJjZS9EZWFsTm90aWNlQ29udGVudC5hc3B4P2lkPWRmNmIzOGU0MWY2YzQ4OWViMGRlNjMxMzk1ZTFlNTJlZBYCZg8VAmvmiJDpg73pq5jmlrDmioDmnK%2FkuqfkuJrlvIDlj5HljLrmoYLmuqrooZfpgZPlip7kuovlpITotYTkuqflh7rnp5%2Fnq57ku7fnu5PmnpzlhaznpLrvvIgyMDE55bm0MDnmnIgwM%2BaXpe%2B8iQBkAgIPFQIKMjAxOS0wOS0wMyg8ZGl2IGNsYXNzPSJlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAIHD2QWBmYPFQEJ6ZSm5rGf5Yy6ZAIBDw8WAh8CBWRodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvQXNzZXRSZXNvdXJjZS9EZWFsTm90aWNlQ29udGVudC5hc3B4P2lkPTE0Njg5YTM3MGI4NDQwNjc5MjNjOGU4MGM2MWQ5M2QzZBYCZg8VAmvmiJDpg73luILplKbmsZ%2FljLrkurrmsJHmlL%2FlupznnaPpmaLooZfooZfpgZPlip7kuovlpITotYTkuqflh7rnp5%2Fnq57ku7fnu5PmnpzlhaznpLrvvIgyMDE55bm0MDnmnIgwM%2BaXpe%2B8iQBkAgIPFQIKMjAxOS0wOS0wMyg8ZGl2IGNsYXNzPSJlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAIID2QWBmYPFQEM6Z2S55m95rGf5Yy6ZAIBDw8WAh8CBWRodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvQXNzZXRSZXNvdXJjZS9EZWFsTm90aWNlQ29udGVudC5hc3B4P2lkPTIwQkQwRTlCQ0NDMTQ2OTVBQUY5MkUxQ0VDQjgxQzdFZBYCZg8VAnTmiJDpg73luILpnZLnmb3msZ%2FljLrop4TliJLlkozoh6rnhLbotYTmupDlsYDotYTkuqfvvIjmiqXlup%2FnlLXmoq%2FvvInlpITnva7kuqTmmJPnu5PmnpzlhazlkYrvvIgyMDE55bm0MDnmnIgwM%2BaXpe%2B8iQBkAgIPFQIKMjAxOS0wOS0wMyg8ZGl2IGNsYXNzPSJlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAIJD2QWBmYPFQEJ5oiQ5Y2O5Yy6ZAIBDw8WAh8CBWRodHRwczovL3d3dy5jZGdnenkuY29tL3NpdGUvQXNzZXRSZXNvdXJjZS9EZWFsTm90aWNlQ29udGVudC5hc3B4P2lkPTA3OWY4ZWU0MGIwZjQwYjM4MTUzYWExMmUxMzAxNDFlZBYCZg8VAl%2FmiJDpg73plKbln47ljY7liJvnva7kuJrmnInpmZDotKPku7vlhazlj7jotYTkuqflh7rnp5%2Fnq57ku7fnu5PmnpzlhaznpLrvvIgyMDE55bm0MDnmnIgwMuaXpe%2B8iQBkAgIPFQIKMjAxOS0wOS0wMig8ZGl2IGNsYXNzPSJlbnRlcmluZyI%2BJm5ic3A7Jm5ic3A7PC9kaXY%2BZAILDw8WBB4IUGFnZVNpemUCCh4LUmVjb3JkY291bnQCoxhkZGToQIM0jGXbRunG4oQvUHqnRvBRwA%3D%3D&__VIEWSTATEGENERATOR=78551C4F&__EVENTTARGET=ctl00%24ContentPlaceHolder1%24Pager&__EVENTARGUMENT=" + pageValue + "&__EVENTVALIDATION=%2FwEdAAhVqX2pcQlTUmDpEA4oDqT2ZXbjphD4J6Ci0P2UUXCmZQp8fmeAe3E%2BZ%2BlyrRcok%2FuMO7fmjkJSfq6Zbgk2kTWnZi7H72kkAvPqROrOG28FXID8g%2FxeCzDZJOPgpGV4zViznYSIw3B963y%2FxzaOhgoHdty0mXvz7f%2B9YtBAL8kV3R34bUsJ2ZRENSCc%2Ba1xvhmnz5L6&__ASYNCPOST=true&";
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


    //公共资源交易平台-凉山州（交易公告）
    public void getNetInfoFromGGZYLSZ(Integer days) {
        try {
            Date date = getInstanceDate(days);//得到前1天
            String urlInfo = "http://ggzyjy.lsz.gov.cn/TPFront/jyxx/005004/005004001/";
            //取得页数
            Elements pageElements = getContent(urlInfo, ".huifont", "");
            String pageStr = pageElements.get(0).childNodes().get(0).toString();
            List<String> strings = Arrays.asList(pageStr.split("/"));
            Integer pageValue = Integer.valueOf(strings.get(1));

            circ:
            for (int i = 1; i <= pageValue; i++) {
                //列表信息
                Elements elements = getContent("http://ggzyjy.lsz.gov.cn/TPFront/jyxx/005004/005004001/?Paging=" + i, ".morecontent li", "GBK");
                for (Element item : elements) {
                    Elements publishtimeElement = item.select(".ewb-date");
                    String publishtimeStr = publishtimeElement.get(0).childNodes().get(0).toString().trim();
                    Date publishtime = DateUtils.parse(publishtimeStr);
                    if (publishtime == null) continue;
                    if (publishtime.compareTo(date) < 0) break circ;

                    String link = item.select("a").get(0).attributes().get("href");
                    String titleStr = item.select("a").get(0).childNodes().get(0).toString();

                    Elements tableElementHrefs = getContent(String.format("%s%s", "http://ggzyjy.lsz.gov.cn", link), "#mainContent", "GBK");
                    if (tableElementHrefs.size() <= 0) {
                        continue;
                    }
                    String content = publicService.delHtmlTags(tableElementHrefs.get(0).text());
                    if (content.length() > 500) {
                        content = content.substring(0, 500);
                    }
                    NetInfoRecord netInfoRecord = new NetInfoRecord();
                    netInfoRecord.setProvince("四川");
                    netInfoRecord.setCity("凉山");
                    netInfoRecord.setType("交易公告");
                    netInfoRecord.setSourceSiteUrl(String.format("%s%s", "http://ggzyjy.lsz.gov.cn", link));
                    netInfoRecord.setBeginTime(publishtime);
                    netInfoRecord.setEndTime(publishtime);
                    netInfoRecord.setTitle(titleStr.replaceAll("\n", ""));
                    netInfoRecord.setSourceSiteName("公共资源交易平台-凉山州");
                    netInfoRecord.setContent(String.format("%s%s", content, "..."));
                    netInfoRecordDao.addInfo(netInfoRecord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //公共资源交易平台-攀枝花（交易信息）
    public void getNetInfoFromGGZYPZH(Integer days) {
        try {
            Date date = getInstanceDate(days);//得到前1天
            String urlInfo = "http://ggzy.panzhihua.gov.cn/jyxx/tdsyq/cjqr";
            //取得页数
            Elements pageElements = getContent(urlInfo, ".mmggxlh a", "");
            String pageStr = pageElements.get(pageElements.size() - 2).childNodes().get(0).toString();
            Integer pageValue = Integer.valueOf(pageStr);

            circ:
            for (int i = 1; i <= pageValue; i++) {
                //列表信息
                Elements elements = getGGZYPZHHtml("#p2 tr", String.valueOf(i));
                for (Element item : elements) {
                    Elements tbElements = item.select("td");
                    if (tbElements.size() == 0 || tbElements == null) continue;
                    String publishTimeStr = tbElements.get(3).childNodes().get(0).toString().trim();
                    Date publishTime = DateUtils.parse(publishTimeStr);
                    if (publishTime == null) continue;
                    if (publishTime.compareTo(date) < 0) break circ;
                    String titleStr = tbElements.get(1).childNodes().get(0).toString();
                    String link = item.select("a").get(0).attributes().get("href");

                    Elements tableElementHrefs = getContent(String.format("%s%s", "http://ggzy.panzhihua.gov.cn", link), ".content_all_nr.editor_content", "");
                    if (tableElementHrefs.size() <= 0) {
                        continue;
                    }
                    String content = null;
                    content = publicService.delHtmlTags(tableElementHrefs.get(0).text());
                    if (content.length() > 500) {
                        content = content.substring(0, 500);
                    }
                    NetInfoRecord netInfoRecord = new NetInfoRecord();
                    netInfoRecord.setProvince("四川");
                    netInfoRecord.setCity("攀枝花");
                    netInfoRecord.setType("交易信息");
                    netInfoRecord.setSourceSiteUrl(String.format("%s%s", "http://ggzy.panzhihua.gov.cn", link));
                    netInfoRecord.setBeginTime(publishTime);
                    netInfoRecord.setEndTime(publishTime);
                    netInfoRecord.setTitle(titleStr.replaceAll("\n", ""));
                    netInfoRecord.setSourceSiteName("公共资源交易平台-攀枝花");
                    netInfoRecord.setContent(String.format("%s%s", content, "..."));
                    netInfoRecordDao.addInfo(netInfoRecord);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Elements getGGZYPZHHtml(String element, String pageValue) {
        try {
            // 1. 获取访问地址URL
            URL url = new URL("http://ggzy.panzhihua.gov.cn/jyxx/tdsyq/cjqr");
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
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Host", "ggzy.panzhihua.gov.cn");
            connection.setRequestProperty("Origin", "http://ggzy.panzhihua.gov.cn");
            connection.setRequestProperty("Referer", "http://ggzy.panzhihua.gov.cn/jyxx/tdsyq/crgg");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
            // 连接
            connection.connect();
            /* 4. 处理输入输出 */
            // 写入参数到请求中
            String params = "currentPage=" + pageValue + "&area=004&secondArea=000";

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
                    netInfoRecordDao.addInfo(netInfoRecord);
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
                    netInfoRecordDao.addInfo(netInfoRecord);
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

    private Elements getContent(String urlInfo, String element, String encoding) {
        try {
            URL url = new URL(urlInfo);
            HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
            //设置用户代理
            httpUrl.setRequestProperty("User-agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
            httpUrl.setRequestProperty("Host", url.getHost());
            httpUrl.setConnectTimeout(6000); // 6s
            httpUrl.setReadTimeout(6000);
            httpUrl.setUseCaches(false);

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
            org.jsoup.nodes.Document doc = Jsoup.parse(sb.toString());
            Elements elements = doc.select(element);
            return elements;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        //公共资源交易平台-雅安
        this.getNetInfoFromGGZYYA(732);
        //公共资源交易平台-成都(土地矿权)
        this.getNetInfoFromGGZYCD(732);
        //公共资源交易平台-成都（资产资源）
        this.getNetInfoFromGGZYCD2(732);
        ////公共资源交易平台-凉山州（交易公告）
        this.getNetInfoFromGGZYLSZ(732);
        ////公共资源交易平台-攀枝花（交易信息）
        this.getNetInfoFromGGZYPZH(732);
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
            netInfoRecordDao.updateInfo(netInfo);
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
}
