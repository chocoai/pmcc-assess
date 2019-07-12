package com.copower.pmcc.assess.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.dao.net.NetAuctionInfoDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetLandTransactionDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetResultAnnouncementDao;
import com.copower.pmcc.assess.dal.basis.entity.NetAuctionInfo;
import com.copower.pmcc.assess.dal.basis.entity.NetLandTransaction;
import com.copower.pmcc.assess.dal.basis.entity.NetResultAnnouncement;
import com.copower.pmcc.assess.dto.input.net.JDSFDto;
import com.copower.pmcc.assess.dto.input.net.JDZCDto;
import com.copower.pmcc.assess.dto.input.net.ZGSFDto;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
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
public class NetAuctionInfoService {
    @Autowired
    private NetAuctionInfoDao netAuctionInfoDao;
    @Autowired
    private NetLandTransactionDao netLandTransactionDao;
    @Autowired
    private NetResultAnnouncementDao netResultAnnouncementDao;

    //来源淘宝网
    public void getNetInfoFromTB() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -2); //得到前2天
            Date date = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formatDate = sdf.format(date);
            String[] needContentType = new String[]{"住宅用房", "商业用房", "工业用房", "其他用房", "股权", "债权", "林权", "矿权", "土地", "资产", "无形资产"};
            List<String> types = Arrays.asList(needContentType);
            Map<String, String> strHrefs = Maps.newHashMap();//用于记录地址
            String urlInfo = "https://sf.taobao.com/item_list.htm?auction_source=0&sorder=2&st_param=-1&auction_start_seg=&" +
                    "auction_start_from=" + formatDate + "&auction_start_to=" + formatDate + "&&spm=a213w.3064813.9001.2";
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
                        NetAuctionInfo netAuctionInfo = JSON.parseObject(sb.toString(), NetAuctionInfo.class);
                        netAuctionInfo.setItemUrl(String.format("%s" + netAuctionInfo.getItemUrl(), "https:"));
                        netAuctionInfo.setType(entry.getValue().substring(0, entry.getValue().indexOf("_")));
                        String provinceAndCity = entry.getValue().substring(entry.getValue().indexOf("_") + 1);
                        netAuctionInfo.setProvincename(provinceAndCity.substring(0, provinceAndCity.indexOf("_")));
                        netAuctionInfo.setCityname(provinceAndCity.substring(provinceAndCity.indexOf("_") + 1));
                        Elements itemContent = getContent(netAuctionInfo.getItemUrl(), "#J_HoverShow", "GBK").select("tr").get(0).select("span");
                        if (itemContent == null) continue;
                        netAuctionInfo.setInitPrice(itemContent.get(2).childNodes().get(0).toString().replace(",", ""));
                        //  String dataFormHerf = getContent(netAuctionInfo.getItemUrl(), "#J_NoticeDetail", "GBK").get(0).attributes().get("data-from");
//                        List<org.jsoup.nodes.Node> nodes = getContent(String.format("%s%s", "https:", dataFormHerf), "body", "GBK").get(0).childNodes();
//                        StringBuilder content = new StringBuilder();
//                        for (int j = 0; j < nodes.size(); j++) {
//                            if (j == 0 || j == nodes.size() - 1) continue;
//                            content.append(delHtmlTags(nodes.get(j).toString()));
//                        }
//                        if(content.length()>500) {
//                            content.substring(0,500);
//                        }
//                        netAuctionInfo.setContent(content.toString());
                        netAuctionInfoDao.addInfo(netAuctionInfo);

                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //来源京东司法
    public void getNetInfoFromJDSF() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1); //得到前1天
            Date date = calendar.getTime();
            String[] needContentType = new String[]{"住宅用房", "商业用房", "工业用房", "其他用房", "股权", "债权", "林权", "矿权", "土地", "无形资产"};
            Map<String, List<String>> strHrefs = Maps.newHashMap();
            List<String> types = Arrays.asList(needContentType);

            String urlInfo = "http://auction.jd.com/sifa_list.html?callback=jQuery8159673&page=1&limit=40&paimaiStatus=2";
            String typeHref = "http://auction.jd.com/getJudicatureList.html?paimaiStatus=2";
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
                        if (jdsfDto.getEndTime().compareTo(date) < 1) break circ;
                        NetAuctionInfo netAuctionInfo = new NetAuctionInfo();
                        netAuctionInfo.setItemUrl(itemHref);
                        netAuctionInfo.setTitle(jdsfDto.getTitle());
                        netAuctionInfo.setStart(jdsfDto.getStartTime());
                        netAuctionInfo.setEnd(jdsfDto.getEndTime());
                        netAuctionInfo.setProvincename(jdsfDto.getProvince());
                        netAuctionInfo.setCityname(jdsfDto.getCity());
                        netAuctionInfo.setConsultPrice(jdsfDto.getAssessmentPriceStr());
                        netAuctionInfo.setCurrentPrice(jdsfDto.getCurrentPriceStr());
                        netAuctionInfo.setType(entry.getKey());
                        netAuctionInfoDao.addInfo(netAuctionInfo);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //来源京东资产
    public void getNetInfoFromJDZC() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1); //得到前1天
            Date date = calendar.getTime();
            Map<String, List<String>> strHrefs = Maps.newHashMap();
            Map<String, String> needContentType = Maps.newHashMap();
            needContentType.put("房产", "12762");
            needContentType.put("土地林权", "12764");
            needContentType.put("股权", "12766");
            needContentType.put("债权", "12767");
            needContentType.put("知识产权", "12768");
            needContentType.put("无形资产", "12769");
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
                circ:
                for (String typeData : pageHref) {
                    Elements pageElements = getContent(typeData, "body", "");
                    String data = pageElements.get(0).childNodes().get(0).toString();
                    JSONObject jsonObject = JSON.parseObject(data);
                    List<String> dataList = JSON.parseArray(jsonObject.getString("ls"), String.class);
                    if (CollectionUtils.isEmpty(dataList)) continue;
                    String itemHrefStr = "https://paimai.jd.com/";
                    for (String dataStr : dataList) {
                        JDZCDto jdzcDto = JSON.parseObject(dataStr, JDZCDto.class);
                        String itemHref = String.format("%s%s", itemHrefStr, jdzcDto.getId());
                        if (jdzcDto.getEndTime().compareTo(date) < 1) break circ;
                        NetAuctionInfo netAuctionInfo = new NetAuctionInfo();
                        netAuctionInfo.setItemUrl(itemHref);
                        netAuctionInfo.setTitle(jdzcDto.getTitle());
                        netAuctionInfo.setStart(jdzcDto.getStartTime());
                        netAuctionInfo.setEnd(jdzcDto.getEndTime());
                        netAuctionInfo.setProvincename(jdzcDto.getProvince());
                        netAuctionInfo.setCityname(jdzcDto.getCity());
                        netAuctionInfo.setCurrentPrice(jdzcDto.getCurrentPrice());
                        netAuctionInfo.setInitPrice(jdzcDto.getStartPrice());
                        netAuctionInfo.setType(entry.getKey());
                        netAuctionInfoDao.addInfo(netAuctionInfo);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //中国拍卖行业协会网-司法
    public void getNetInfoFromZGSF() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1); //得到前1天
            Date date = calendar.getTime();
            Map<String, String> needContentType = Maps.newHashMap();
            needContentType.put("房产", "6");
            needContentType.put("土地使用权", "5");
            needContentType.put("股权", "16");
            needContentType.put("债券", "14");
            needContentType.put("集体土地使用权", "4");
            needContentType.put("森林、林木使用权", "3");
            needContentType.put("知识产权", "17");
            needContentType.put("其他财产", "255");
            Map<String, List<String>> strHrefs = Maps.newHashMap();
            String typeHref = "http://sf.caa123.org.cn/caa-web-ws/ws/0.1/lots?sortname=&sortorder=&count=12&lotStatus=2";

            for (Map.Entry<String, String> entry : needContentType.entrySet()) {
                Integer startPage = 0;
                List<String> pageHref = Lists.newArrayList();
                String dataHref = String.format("%s%s", typeHref, "&standardType=" + entry.getValue());
                Elements pageElements = getContent(String.format("%s%s", dataHref, "&start=" + startPage), "body", "");
                String data = pageElements.get(0).childNodes().get(0).toString().trim();
                JSONObject jsonObject = JSON.parseObject(data);
                Integer totalCount = JSON.parseObject(jsonObject.getString("totalCount"), Integer.class);
                if (totalCount == 0) continue;
                Integer totalPages = JSON.parseObject(jsonObject.getString("totalPages"), Integer.class);
                for (int i = 0; i < totalPages; i++) {
                    String dataPageHref = String.format("%s%s", dataHref, "&start=" + i);
                    pageHref.add(dataPageHref);
                }
                strHrefs.put(entry.getKey(), pageHref);
            }

            for (Map.Entry<String, List<String>> entry : strHrefs.entrySet()) {
                List<String> pageHref = entry.getValue();
                circ:
                for (String typeData : pageHref) {
                    Elements pageElements = getContent(typeData, "body", "");
                    String data = pageElements.get(0).childNodes().get(0).toString().trim();
                    JSONObject jsonObject = JSON.parseObject(data);
                    List<String> dataList = JSON.parseArray(jsonObject.getString("items"), String.class);
                    if (CollectionUtils.isEmpty(dataList)) continue;
                    String itemHrefStr = "http://sf.caa123.org.cn/lot/";
                    for (String dataStr : dataList) {
                        ZGSFDto zgsfDto = JSON.parseObject(dataStr, ZGSFDto.class);
                        String itemHref = String.format("%s%s%s", itemHrefStr, zgsfDto.getId(), ".html");
                        if (zgsfDto.getEndTime().compareTo(date) < 1) break circ;
                        NetAuctionInfo netAuctionInfo = new NetAuctionInfo();
                        netAuctionInfo.setItemUrl(itemHref);
                        netAuctionInfo.setTitle(zgsfDto.getName());
                        netAuctionInfo.setStart(zgsfDto.getStartTime());
                        netAuctionInfo.setEnd(zgsfDto.getEndTime());
                        netAuctionInfo.setType(zgsfDto.getStandardType());
                        netAuctionInfo.setConsultPrice(zgsfDto.getAssessPrice());
                        netAuctionInfo.setCurrentPrice(zgsfDto.getNowPrice());
                        netAuctionInfo.setInitPrice(zgsfDto.getStartPrice());
                        netAuctionInfo.setType(entry.getKey());
                        netAuctionInfoDao.addInfo(netAuctionInfo);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //中国拍卖行业协会网-标的
    public void getNetInfoFromZGBD() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1); //得到前1天
            Date date = calendar.getTime();
            Map<String, String> needContentType = Maps.newHashMap();
            needContentType.put("房产", "6");
            needContentType.put("土地", "7");
            needContentType.put("股权债权", "8");
            needContentType.put("无形资产", "18");
            needContentType.put("其他财产", "255");

            Map<String, List<String>> strHrefs = Maps.newHashMap();
            String typeHref = "https://paimai.caa123.org.cn/wt-web-ws/ws/0.1/lots?sortname=&sortorder=&count=12&status=3";

            for (Map.Entry<String, String> entry : needContentType.entrySet()) {
                Integer startPage = 0;
                List<String> pageHref = Lists.newArrayList();
                String dataHref = String.format("%s%s", typeHref, "&standardType=" + entry.getValue());
                Elements pageElements = getContent(String.format("%s%s", dataHref, "&start=" + startPage), "body", "");
                String data = pageElements.get(0).childNodes().get(0).toString().trim();
                JSONObject jsonObject = JSON.parseObject(data);
                Integer totalCount = JSON.parseObject(jsonObject.getString("totalCount"), Integer.class);
                if (totalCount == 0) continue;
                Integer totalPages = JSON.parseObject(jsonObject.getString("totalPages"), Integer.class);
                for (int i = 0; i < totalPages; i++) {
                    String dataPageHref = String.format("%s%s", dataHref, "&start=" + i);
                    pageHref.add(dataPageHref);
                }
                strHrefs.put(entry.getKey(), pageHref);
            }

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
                        if (zgsfDto.getEndTime().compareTo(date) < 1) break circ;
                        NetAuctionInfo netAuctionInfo = new NetAuctionInfo();
                        netAuctionInfo.setItemUrl(itemHref);
                        netAuctionInfo.setTitle(zgsfDto.getName());
                        netAuctionInfo.setStart(zgsfDto.getStartTime());
                        netAuctionInfo.setEnd(zgsfDto.getEndTime());
                        netAuctionInfo.setType(zgsfDto.getStandardType());
                        netAuctionInfo.setConsultPrice(zgsfDto.getAssessPrice());
                        netAuctionInfo.setCurrentPrice(zgsfDto.getNowPrice());
                        netAuctionInfo.setInitPrice(zgsfDto.getStartPrice());
                        netAuctionInfo.setType(entry.getKey());
                        netAuctionInfoDao.addInfo(netAuctionInfo);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //来源公拍网
    public void getNetInfoFromGPW() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1); //得到前1天
            Date date = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String[] needContentType = new String[]{"房产", "土地", "股权", "无形资产", "林权矿权"};
            List<String> types = Arrays.asList(needContentType);
            Map<String, String> strHrefs = Maps.newHashMap();//用于记录地址
            String urlInfo = "http://s.gpai.net/sf/search.do?restate=3";

            Elements elements = getContent(urlInfo, ".s-tab-con", "");
            Elements a = elements.get(1).select("a");

            for (Element item : a) {
                String type = item.childNodes().get(0).toString().trim();
                String href = item.attributes().get("href").trim();
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
                ;
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
                            if (endTime == null || endTime.compareTo(date) < 1) continue circ;
                            NetAuctionInfo netAuctionInfo = new NetAuctionInfo();
                            netAuctionInfo.setTitle(titleName);
                            netAuctionInfo.setEnd(endTime);
                            netAuctionInfo.setItemUrl(itemHref);
                            String consultPrice = info.get(0).childNodes().get(6).childNodes().get(2).childNodes().get(0).childNodes().get(0).toString();
                            netAuctionInfo.setConsultPrice(new BigDecimal(consultPrice).multiply(new BigDecimal("10000")).toString());
                            Elements itemContent = getContent(itemHref, ".d-m-infos", "");
                            Elements currentPriceElements = itemContent.get(0).select(".price-red");
                            String currentPrice = currentPriceElements.get(0).childNodes().get(0).toString();
                            netAuctionInfo.setCurrentPrice(currentPrice);
                            Elements tbody_tr = itemContent.get(0).select("tbody td");//consult_price
                            String initPrice = tbody_tr.get(6).childNodes().get(1).childNodes().get(0).toString();
                            netAuctionInfo.setInitPrice(initPrice);
                            netAuctionInfo.setType(entry.getValue().substring(0, entry.getValue().indexOf("_")));
                            netAuctionInfo.setProvincename(entry.getValue().substring(entry.getValue().indexOf("_") + 1));
                            netAuctionInfoDao.addInfo(netAuctionInfo);
                        } catch (Exception e) {

                        }


                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //公共资源交易平台-雅安
    public void getNetInfoFromGGZYYA() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -10); //得到前1天
            Date date = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
                    String publishtimeStr = itemContent.get(i).childNodes().get(7).childNodes().get(0).toString();
                    Date publishtime = sdf.parse(publishtimeStr);
                    if (publishtime == null || publishtime.compareTo(date) < 1) break circ;
                    NetLandTransaction netLandTransaction = new NetLandTransaction();
                    netLandTransaction.setPublishtime(publishtime);
                    String title = itemContent.get(i).childNodes().get(3).childNodes().get(0).toString();
                    netLandTransaction.setContent(title);
                    String detailHref = itemContent.get(i).childNodes().get(5).childNodes().get(1).attributes().get("href");
                    netLandTransaction.setDetailLink(String.format("%s%s", "http://www.yaggzy.org.cn", detailHref));
                    netLandTransactionDao.addNetLandTransaction(netLandTransaction);
                    Elements tdElements = getContent(netLandTransaction.getDetailLink(), "tr", "");
                    Integer length = tdElements.get(1).select("td").size();
                    //移除首行字段名
                    tdElements.remove(0);
                    //只适用于9个字段的列表
                    if (length == 9) {
                        for (Element item : tdElements) {
                            Elements select = item.select("td");
                            NetResultAnnouncement netResultAnnouncement = new NetResultAnnouncement();
                            netResultAnnouncement.setBdmc(checkNull(select, 0));//标的名称
                            netResultAnnouncement.setZdwz(checkNull(select, 1));//宗地位置
                            netResultAnnouncement.setJydmj(checkNull(select, 2));//净用地面积
                            netResultAnnouncement.setTdyt(checkNull(select, 3));//土地用途
                            netResultAnnouncement.setCrfs(checkNull(select, 4));//出让方式
                            netResultAnnouncement.setRjl(checkNull(select, 5));//容积率
                            netResultAnnouncement.setQsj(String.format("%s%s", checkNull(select, 6), "万元/亩"));//起始价
                            netResultAnnouncement.setCcj(String.format("%s%s", checkNull(select, 7), "万元/亩"));//成交价
                            netResultAnnouncement.setJdr(checkNull(select, 8));//竞得人
                            netResultAnnouncement.setMainId(netLandTransaction.getId());
                            netResultAnnouncementDao.addNetResultAnnouncement(netResultAnnouncement);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private String checkNull(Elements select, Integer index) {
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
        String string = select.get(index).childNodes().get(0).toString();
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

}
