package com.copower.pmcc.assess.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dto.input.net.JDSFDto;
import com.copower.pmcc.assess.dto.input.net.JDZCDto;
import com.copower.pmcc.assess.dto.input.net.TBSFDto;
import com.copower.pmcc.assess.dto.input.net.ZGSFDto;
import com.copower.pmcc.erp.common.utils.DateUtils;
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
public class NetInfoRecordService {
    @Autowired
    private NetInfoRecordDao netInfoRecordDao;

    //抓取数据
    public void climbingData() {
        //来源淘宝网
        this.getNetInfoFromTB();
        //来源京东司法
        this.getNetInfoFromJDSF();
        //来源京东资产
        this.getNetInfoFromJDZC();
        //中国拍卖行业协会网-司法
        this.getNetInfoFromZGSF();
        //中国拍卖行业协会网-标的
        this.getNetInfoFromZGBD();
        //来源公拍网
        this.getNetInfoFromGPW();
        //公共资源交易平台-雅安
        this.getNetInfoFromGGZYYA();
        //公共资源交易平台-成都
        this.getNetInfoFromGGZYCD();
    }

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
                        TBSFDto tbsfDto = JSON.parseObject(sb.toString(), TBSFDto.class);
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
                        String content = getContent(tbsfDto.getTitle(), tbsfDto.getType(), tbsfDto.getCurrentPrice(), tbsfDto.getConsultPrice(), initPrice
                                , DateUtils.format(tbsfDto.getEnd(), DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(tbsfDto.getStart(), DateUtils.DATE_CHINESE_PATTERN));
                        netInfoRecord.setContent(content);
                        netInfoRecord.setSourceSiteName("淘宝司法拍卖网");
                        netInfoRecordDao.addInfo(netInfoRecord);

                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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
        if (StringUtil.isNotEmpty(title)) {
            content.append("标题：" + title + "。");
        }
        if (StringUtil.isNotEmpty(type)) {
            content.append("类型：" + type + "。");
        }
        if (StringUtil.isNotEmpty(currentPrice)) {
            content.append("成交价：" + currentPrice + "。");
        }
        if (StringUtil.isNotEmpty(consultPrice)) {
            content.append("估算价：" + consultPrice + "。");
        }
        if (StringUtil.isNotEmpty(initPrice)) {
            content.append("起始价：" + initPrice + "。");
        }
        if (StringUtil.isNotEmpty(startTime)) {
            content.append("开始时间：" + startTime + "。");
        }
        if (StringUtil.isNotEmpty(endTime)) {
            content.append("结束时间：" + endTime + "。");
        }


        return content.toString();
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
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        netInfoRecord.setSourceSiteUrl(itemHref);
                        netInfoRecord.setTitle(jdsfDto.getTitle());
                        //netInfoRecord.setStart(jdsfDto.getStartTime());
                        //netInfoRecord.setEnd(jdsfDto.getEndTime());
                        netInfoRecord.setProvince(jdsfDto.getProvince());
                        netInfoRecord.setCity(jdsfDto.getCity());
                        // netInfoRecord.setConsultPrice(jdsfDto.getAssessmentPriceStr());
                        //netInfoRecord.setCurrentPrice(jdsfDto.getCurrentPriceStr());
                        //netInfoRecord.setType(entry.getKey());
                        String content = getContent(jdsfDto.getTitle(), entry.getKey(), jdsfDto.getCurrentPriceStr(), jdsfDto.getAssessmentPriceStr(), ""
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
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        netInfoRecord.setSourceSiteUrl(itemHref);
                        netInfoRecord.setTitle(jdzcDto.getTitle());
                        //netInfoRecord.setStart(jdzcDto.getStartTime());
                        //netInfoRecord.setEnd(jdzcDto.getEndTime());
                        netInfoRecord.setProvince(jdzcDto.getProvince());
                        netInfoRecord.setCity(jdzcDto.getCity());
                        // netInfoRecord.setCurrentPrice(jdzcDto.getCurrentPrice());
                        //netInfoRecord.setInitPrice(jdzcDto.getStartPrice());
                        //netInfoRecord.setType(entry.getKey());
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
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        netInfoRecord.setSourceSiteUrl(itemHref);
                        netInfoRecord.setTitle(zgsfDto.getName());
                        //netInfoRecord.setStart(zgsfDto.getStartTime());
                        //netInfoRecord.setEnd(zgsfDto.getEndTime());
                        //netInfoRecord.setType(zgsfDto.getStandardType());
                        //netInfoRecord.setConsultPrice(zgsfDto.getAssessPrice());
                        //netInfoRecord.setCurrentPrice(zgsfDto.getNowPrice());
                        //netInfoRecord.setInitPrice(zgsfDto.getStartPrice());
                        //netInfoRecord.setType(entry.getKey());
                        String content = getContent(zgsfDto.getName(), entry.getKey(), zgsfDto.getNowPrice(), zgsfDto.getAssessPrice(), zgsfDto.getStartPrice()
                                , DateUtils.format(zgsfDto.getEndTime(), DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(zgsfDto.getStartTime(), DateUtils.DATE_CHINESE_PATTERN));
                        netInfoRecord.setContent(content);
                        netInfoRecord.setSourceSiteName("中国拍卖行业协会网-司法");
                        netInfoRecordDao.addInfo(netInfoRecord);
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
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        netInfoRecord.setSourceSiteUrl(itemHref);
                        netInfoRecord.setTitle(zgsfDto.getName());
                        //netInfoRecord.setStart(zgsfDto.getStartTime());
                        //netInfoRecord.setEnd(zgsfDto.getEndTime());
                        //netInfoRecord.setType(zgsfDto.getStandardType());
                        //netInfoRecord.setConsultPrice(zgsfDto.getAssessPrice());
                        //netInfoRecord.setCurrentPrice(zgsfDto.getNowPrice());
                        //netInfoRecord.setInitPrice(zgsfDto.getStartPrice());
                        //netInfoRecord.setType(entry.getKey());
                        String content = getContent(zgsfDto.getName(), entry.getKey(), zgsfDto.getNowPrice(), zgsfDto.getAssessPrice(), zgsfDto.getStartPrice()
                                , DateUtils.format(zgsfDto.getEndTime(), DateUtils.DATE_CHINESE_PATTERN), DateUtils.format(zgsfDto.getStartTime(), DateUtils.DATE_CHINESE_PATTERN));
                        netInfoRecord.setContent(content);
                        netInfoRecord.setSourceSiteName("中国拍卖行业协会网-标的");
                        netInfoRecordDao.addInfo(netInfoRecord);
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
                            NetInfoRecord netInfoRecord = new NetInfoRecord();
                            netInfoRecord.setTitle(titleName);
                            netInfoRecord.setSourceSiteUrl(itemHref);
                            String consultPrice = info.get(0).childNodes().get(6).childNodes().get(2).childNodes().get(0).childNodes().get(0).toString();
                            Elements itemContent = getContent(itemHref, ".d-m-infos", "");
                            Elements currentPriceElements = itemContent.get(0).select(".price-red");
                            String currentPrice = currentPriceElements.get(0).childNodes().get(0).toString();
                            Elements tbody_tr = itemContent.get(0).select("tbody td");//consult_price
                            String initPrice = tbody_tr.get(6).childNodes().get(1).childNodes().get(0).toString();
                            netInfoRecord.setProvince(entry.getValue().substring(entry.getValue().indexOf("_") + 1));
                            netInfoRecord.setSourceSiteName("公拍网");
                            String content = getContent(titleName, entry.getKey(), currentPrice, consultPrice, initPrice
                                    , DateUtils.format(endTime, DateUtils.DATE_CHINESE_PATTERN), "");
                            netInfoRecord.setContent(content);
                            netInfoRecordDao.addInfo(netInfoRecord);
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
            calendar.add(Calendar.DATE, -1); //得到前1天
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
                    String detailHref = itemContent.get(i).childNodes().get(5).childNodes().get(1).attributes().get("href");
                    Elements tdElements = getContent(String.format("%s%s", "http://www.yaggzy.org.cn", detailHref), "tr", "");
                    Integer length = tdElements.get(1).select("td").size();
                    //获取字段名称
                    List<String> fieldNames = Lists.newArrayList();
                    for (int k = 0; k < tdElements.size(); k++) {
                        Elements select = tdElements.get(k).select("td");
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
                            fieldValues.add(delHtmlTags(fieldValue));
                        }
                        NetInfoRecord netInfoRecord = new NetInfoRecord();
                        String title = itemContent.get(i).childNodes().get(3).childNodes().get(0).toString();
                        netInfoRecord.setTitle(title);
                        netInfoRecord.setSourceSiteUrl(String.format("%s%s", "http://www.yaggzy.org.cn", detailHref));
                        netInfoRecord.setProvince("四川");
                        netInfoRecord.setCity("雅安");
                        netInfoRecord.setSourceSiteName("公共资源交易平台-雅安");
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


    //公共资源交易平台-成都
    public void getNetInfoFromGGZYCD() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -2); //得到前1天
            Date date = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String urlInfo = "https://www.cdggzy.com/site/LandTrade/LandList.aspx";
            Elements elements = getContent(urlInfo, ".row.contentitem", "");
            for (Element item : elements) {

                Elements publishtimeElement = item.select(".publishtime");
                String publishtimeStr = publishtimeElement.get(0).childNodes().get(0).toString().substring(1);
                Date publishtime = sdf.parse(publishtimeStr);
                if (publishtime == null || publishtime.compareTo(date) < 1) break;

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
                    for (int j = 0; j < length; j++) {
                        String fieldValue = checkNull(select, j);
                        fieldValues.add(delHtmlTags(fieldValue));
                    }
                    NetInfoRecord netInfoRecord = new NetInfoRecord();
                    netInfoRecord.setProvince("四川");
                    netInfoRecord.setCity("成都");
                    netInfoRecord.setSourceSiteUrl(link);
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

                    content.append("发布时间：" + publishtimeStr + "；");
                    content.append("地址：" + address.replaceAll("\n", "").substring(1, address.length() - 2) + "；");
                    netInfoRecord.setContent(content.toString());
                    netInfoRecordDao.addInfo(netInfoRecord);
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


    //去掉标签
    public static String delHtmlTags(String htmlStr) {
        //定义script的正则表达式，去除js可以防止注入
        String scriptRegex = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        //定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
        String styleRegex = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        //定义HTML标签的正则表达式，去除标签，只提取文字内容
        String htmlRegex = "<[^>]+>";
        //定义空格,回车,换行符,制表符
        String spaceRegex = "\\s*|\t|\r|\n";

        // 过滤script标签
        htmlStr = htmlStr.replaceAll(scriptRegex, "");
        // 过滤style标签
        htmlStr = htmlStr.replaceAll(styleRegex, "");
        // 过滤html标签
        htmlStr = htmlStr.replaceAll(htmlRegex, "");
        // 过滤空格等
        htmlStr = htmlStr.replaceAll(spaceRegex, "");

        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        return htmlStr.trim(); // 返回文本字符串
    }
}
