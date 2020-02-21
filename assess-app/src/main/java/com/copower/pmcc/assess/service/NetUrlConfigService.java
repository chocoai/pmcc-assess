package com.copower.pmcc.assess.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.MyX509TrustManager;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetUrlConfigDao;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetUrlConfig;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by wangpc on 2020/2/19.
 */
@Service
public class NetUrlConfigService {
    @Autowired
    private NetUrlConfigDao netUrlConfigDao;
    @Autowired
    private NetInfoRecordDao netInfoRecordDao;
    @Autowired
    private NetInfoRecordService netInfoRecordService;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public void climbingAll() {
        List<NetUrlConfig> configList = netUrlConfigDao.getEnableNetUrlConfigList();
        if (CollectionUtils.isNotEmpty(configList))
            configList.forEach(o -> climbingDataByConfig(o));
    }

    public void climbingDataById(Integer id) {
        NetUrlConfig netUrlConfig = netUrlConfigDao.getNetUrlConfigById(id);
        climbingDataByConfig(netUrlConfig);
    }

    /**
     * 根据配置爬取网络数据
     */
    private void climbingDataByConfig(NetUrlConfig config) {
        if (config == null) return;
        try {
            int pages = config.getPageCount();
            // 根据取得页码，分页进行每页数据的提取
            for (int i = config.getStartPageIndex(); i <= pages; i++) {
                try {
                    Elements elements = null;
                    Integer pageIndex = i;
                    if (config.getPageSize() != null && config.getPageSize() > 0) {
                        pageIndex = i * config.getPageSize();
                    }
                    String urlInfo = config.getUrl().replace("{pages}", String.valueOf(pageIndex));
                    if (i == config.getStartPageIndex() && StringUtils.isNotBlank(config.getIndexUrl()))
                        urlInfo = config.getIndexUrl();
                    elements = netInfoRecordService.getContent(urlInfo, config.getItemList(), config.getEncoding(), config.getRequestMethod());
                    for (Element element : elements) {
                        try {
                            // 标题
                            String title = "";
                            if (StringUtils.isNotBlank(config.getItemTitle())) {
                                Elements titleElement = element.select(config.getItemTitle());
                                if (StringUtils.isBlank(title)) {
                                    title = titleElement.get(0).attributes().get("title");
                                }
                                title = StringUtils.isBlank(title) ? titleElement.text() : title;
                            } else {
                                title = element.attributes().get("title");
                                title = StringUtils.isBlank(title) ? element.text() : title;
                            }

                            String href = null;
                            if (StringUtils.isNotBlank(config.getItemUrl())) {
                                Elements urlElement = element.select(config.getItemTitle());
                                href = urlElement.get(0).attributes().get("href");
                            } else {
                                href = element.attributes().get("href");
                            }
                            if (StringUtils.isNotBlank(href) && !href.contains("http"))
                                href = config.getDomainName() + href.replaceAll("^\\.+?", "");

                            Date publishtime = null;
                            String dtString = null;
                            if (StringUtils.isNotBlank(config.getItemDate())) {
                                Elements dateElement = element.select(config.getItemDate());
                                dtString = dateElement.get(0).text();
                            } else {
                                dtString = element.text();
                            }
                            if (StringUtils.isNotBlank(dtString))
                                publishtime = DateUtils.convertDate(dtString, DateUtils.DATE_PATTERN);

                            NetInfoRecord netInfoRecord = new NetInfoRecord();
                            netInfoRecord.setProvince(config.getProvince());
                            netInfoRecord.setCity(config.getCity());
                            netInfoRecord.setSourceSiteName(config.getName());
                            netInfoRecord.setType(config.getRemark());
                            netInfoRecord.setSourceSiteUrl(StringUtils.isBlank(href) ? urlInfo : href);
                            netInfoRecord.setTitle(StringUtils.trim(title));
                            netInfoRecord.setContent(netInfoRecord.getTitle());
                            netInfoRecord.setBeginTime(publishtime);
                            netInfoRecord.setCreator("admin");
                            netInfoRecordDao.addInfo(netInfoRecord);

                        } catch (Exception e) {
                            logger.error(MessageFormat.format("抓举具体内容异常；网址{0}，页码{1},异常原因：{2}", config.getUrl(), String.valueOf(i), e.getMessage()));
                        }
                    }
                } catch (Exception e) {
                    logger.error(MessageFormat.format("分页抓取异常；网址{0}，页码{1},异常原因：{2}", config.getUrl(), String.valueOf(i), e.getMessage()));
                }
            }
        } catch (Exception e) {
            logger.error("读取数据失败:" + e.getMessage(), e);
        }
    }

    /**
     * 获取巴中资源交易中心数据
     *
     * @param pageCount
     */
    public void getBaZhongTradingCenter(Integer pageCount) {
        String baseUrl = "http://117.172.156.43:82/pub/showMcontent?mcode=JYTD_JGGG&clicktype=1&pageNum={pages}&keyname=&areacode=";
        for (Integer i = 1; i <= pageCount; i++) {
            String currUrl = baseUrl.replace("{pages}", String.valueOf(i));
            String json = netInfoRecordService.getContentHtml(currUrl, "POST", "UTF-8");
            if (StringUtils.isBlank(json)) continue;
            JSONObject jsonObject = JSON.parseObject(json);
            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("content");
            for (Object o : jsonArray) {
                JSONObject object = (JSONObject) o;
                String url = "http://117.172.156.43:82/pub/BZ_indexContent_" + object.getString("id") + ".html";
                String date = object.getString("mckeys");
                String title = object.getString("mctype");
                NetInfoRecord netInfoRecord = new NetInfoRecord();
                netInfoRecord.setProvince("四川");
                netInfoRecord.setCity("巴中");
                netInfoRecord.setSourceSiteName("巴中公共资源交易中心");
                netInfoRecord.setType("土地矿权");
                netInfoRecord.setSourceSiteUrl(url);
                netInfoRecord.setTitle(StringUtils.trim(title));
                netInfoRecord.setContent(netInfoRecord.getTitle());
                netInfoRecord.setBeginTime(DateUtils.convertDate(date));
                netInfoRecord.setCreator("admin");
                netInfoRecordDao.addInfo(netInfoRecord);
            }
        }
    }

    /**
     * 获取泸州资源交易中心数据
     *
     * @param pageCount
     */
    public void getLuZhouTradingCenter(Integer pageCount) {
        String baseUrl = "https://www.lzsggzy.com/EpointWebBuilder/ggSearchAction.action?cmd=getJyxxList&luztitle=&siteGuid=7eb5f7f1-9041-43ad-8e13-8fcb82ea831a&ggcategorynum=006001004%3B&jyzt=&jylx=&citycode=&pageIndex={pages}&pageSize=10";
        for (Integer i = 0; i < pageCount; i++) {
            String currUrl = baseUrl.replace("{pages}", String.valueOf(i));
            String json = netInfoRecordService.getContentHtml(currUrl, "POST", "UTF-8");
            if (StringUtils.isBlank(json)) continue;
            JSONObject jsonObject = JSON.parseObject(json);
            JSONObject parseObject = JSON.parseObject(jsonObject.getString("custom"));
            JSONArray jsonArray = parseObject.getJSONArray("Table");
            for (Object o : jsonArray) {
                JSONObject object = (JSONObject) o;
                String date = object.getString("infodate");
                String title = object.getString("title");
                Date publishDate = DateUtils.convertDate(date);
                String url = String.format("https://www.lzsggzy.com/tdkq/006001/006001004/%s/%s.html", DateUtils.format(publishDate, DateUtils.DATE_SHORT_PATTERN), object.getString("infoid"));
                NetInfoRecord netInfoRecord = new NetInfoRecord();
                netInfoRecord.setProvince("四川");
                netInfoRecord.setCity("泸州");
                netInfoRecord.setSourceSiteName("泸州公共资源交易中心");
                netInfoRecord.setType("土地矿权");
                netInfoRecord.setSourceSiteUrl(url);
                netInfoRecord.setTitle(StringUtils.trim(title));
                netInfoRecord.setContent(netInfoRecord.getTitle());
                netInfoRecord.setBeginTime(publishDate);
                netInfoRecord.setCreator("admin");
                netInfoRecordDao.addInfo(netInfoRecord);
            }
        }
    }

    /**
     * 获取自贡资源交易中心数据
     *
     * @param pageCount
     */
    public void getZiGongTradingCenter(Integer pageCount) {
        String baseUrl = "http://jyxxw.zg.gov.cn/EpointWebBuilder_zgzfcg/ggSearchAction.action?cmd=getList&xiaqucode=&siteGuid=7eb5f7f1-9041-43ad-8e13-8fcb82ea831a&categorynum=003003&datestart=&dateend=&pageIndex={pages}&pageSize=6";
        for (Integer i = 0; i < pageCount; i++) {
            String currUrl = baseUrl.replace("{pages}", String.valueOf(i));
            String json = netInfoRecordService.getContentHtml(currUrl, "POST", "UTF-8");
            if (StringUtils.isBlank(json)) continue;
            JSONObject jsonObject = JSON.parseObject(json);
            JSONObject parseObject = JSON.parseObject(jsonObject.getString("custom"));
            JSONArray jsonArray = parseObject.getJSONArray("Table");
            for (Object o : jsonArray) {
                JSONObject object = (JSONObject) o;
                String date = object.getString("infodate");
                String title = object.getString("title");
                Date publishDate = DateUtils.convertDate(date);
                String url = String.format("http://jyxxw.zg.gov.cn//tiaozhuan.html?infoid=%s&amp;categorynum=%s", object.getString("infoid"), object.getString("categorynum"));
                NetInfoRecord netInfoRecord = new NetInfoRecord();
                netInfoRecord.setProvince("四川");
                netInfoRecord.setCity("自贡");
                netInfoRecord.setSourceSiteName("自贡公共资源交易中心");
                netInfoRecord.setType("土地矿权");
                netInfoRecord.setSourceSiteUrl(url);
                netInfoRecord.setTitle(StringUtils.trim(title));
                netInfoRecord.setContent(netInfoRecord.getTitle());
                netInfoRecord.setBeginTime(publishDate);
                netInfoRecord.setCreator("admin");
                netInfoRecordDao.addInfo(netInfoRecord);
            }
        }
    }

    /**
     * 获取宜宾资源交易中心数据
     *
     * @param pageCount
     */
    public void getYiBinTradingCenter(Integer pageCount, String type) {
        String baseUrl = "https://ggzy.yibin.gov.cn/TrueLoreAjax/TrueLore.Web.WebUI.AjaxHelper,TrueLore.Web.WebUI.ashx";
        String postContent = "[\"TrueLore.Web.WebUI.WebAjaxService\",\"GetPageTDJYXTXXFB\",[{pages},15,\"-1\",\"3\",\"400\",\"XMMC\",\"\",\"Fbqssj DESC\"],null,null]1582195024720";
        if ("asset".equals(type)) {
            postContent = "[\"TrueLore.Web.WebUI.WebAjaxService\",\"GetPageGYJYXTXXFB\",[{pages},15,\"-1\",\"4\",\"450\",\"XMMC\",\"\",\"Fbqssj DESC\"],null,null]1582204091607";
        }
        for (Integer i = 0; i < pageCount; i++) {

            String currPostBody = postContent.replace("{pages}", String.valueOf(i * 15));
            try {
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

                URL url = new URL(baseUrl);
                HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
                //设置用户代理
                httpUrl.setRequestProperty("User-agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
                httpUrl.setRequestProperty("Host", url.getHost());
                httpUrl.setConnectTimeout(6000); // 6s
                httpUrl.setReadTimeout(6000);
                httpUrl.setUseCaches(false);
                httpUrl.setDoOutput(true);
                httpUrl.addRequestProperty("Content-Type", "text/plain; charset=UTF-8");
                httpUrl.addRequestProperty("Ajax-method", "AjaxMethodFactory");
                httpUrl.setRequestMethod("POST");
                DataOutputStream out = new DataOutputStream(httpUrl.getOutputStream());
                out.writeBytes(currPostBody);
                out.flush();
                out.close();
                InputStream is = httpUrl.getInputStream();
                BufferedReader br = null;
                br = new BufferedReader(new InputStreamReader(is, "utf-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                is.close();
                br.close();
                String json = sb.toString();
                if (StringUtils.isBlank(json)) continue;
                JSONArray jsonArray = JSON.parseArray(json);
                for (Object o : jsonArray) {
                    JSONObject object = (JSONObject) o;
                    String date = object.getString("Fbqssj");
                    String title = object.getString("GCMC");
                    Date publishDate = DateUtils.convertDate(date);
                    String siteUrl = String.format("https://ggzy.yibin.gov.cn/TD/WSJM/TDJYJGGSView.aspx?type=510&subtype=%s&ID=%s", object.getString("XXLB"), object.getString("ID"));
                    NetInfoRecord netInfoRecord = new NetInfoRecord();
                    netInfoRecord.setProvince("四川");
                    netInfoRecord.setCity("宜宾");
                    netInfoRecord.setSourceSiteName("宜宾公共资源交易中心");
                    netInfoRecord.setType("asset".equals(type) ? "国有资产" : "土地矿权");
                    netInfoRecord.setSourceSiteUrl(siteUrl);
                    netInfoRecord.setTitle(StringUtils.trim(title));
                    netInfoRecord.setContent(netInfoRecord.getTitle());
                    netInfoRecord.setBeginTime(publishDate);
                    netInfoRecord.setCreator("admin");
                    netInfoRecordDao.addInfo(netInfoRecord);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
