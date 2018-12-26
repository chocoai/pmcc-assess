package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.basis.dao.net.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
import java.util.Date;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/6/12
 * @time: 11:16
 */
@Service
public class NetLandTransactionService {
    @Autowired
    private NetLandTransactionDao netLandTransactionDao;
    @Autowired
    private NetHangTagAnnouncementDao netHangTagAnnouncementDao;
    @Autowired
    private NetResultAnnouncementDao netResultAnnouncementDao;
    @Autowired
    private NetAuctionAnnouncementDao netAuctionAnnouncementDao;
    @Autowired
    private NetBeforehandAnnouncementDao netBeforehandAnnouncementDao;

    private String pmcrsj;

    public void getNetLandTransactionDetail(String content, String urlString, Integer mainId) {
        Elements elements = getContent(urlString, "iframe", "");
        if (elements.size() <= 0) {
            return;
        }
        String s = urlString.substring(0, urlString.lastIndexOf("/") + 1);

        String iframeUrl = s + elements.get(0).attributes().get("src");//表格地址
        Elements tableElements = getContent(iframeUrl, "table", "");
        Elements trs = tableElements.select("tr");
        if (content.contains("挂告")) {
            hangTag(trs, mainId);
        }
        if (content.contains("结果一览表")) {
            result(trs, mainId);
        }
        if (content.contains("拍告") && !content.contains("结果一览表")) {
            auction(trs, mainId);
        }
        if (content.contains("预公告")) {
            beforehand(trs, mainId);
        }

    }


    private Elements getContent(String urlInfo, String element, String encoding) {
        try {
            URL url = new URL(urlInfo);
            HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
            //设置用户代理
            httpUrl.setRequestProperty("User-agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
            httpUrl.setRequestProperty("Host", url.getHost());
            InputStream is = httpUrl.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StringUtils.defaultIfBlank(encoding, "utf-8")));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            br.close();
            Document doc = Jsoup.parse(sb.toString());
            Elements elements = doc.select(element);
            return elements;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getNetLandTransactionList(Date date) {
        try {
            String urlInfo = "https://www.cdggzy.com/site/LandTrade/LandList.aspx";

            Elements elements = getContent(urlInfo, ".row.contentitem", "");
            for (Element item : elements) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Elements publishtimeElement = item.select(".publishtime");
                String publishtime = publishtimeElement.get(0).childNodes().get(0).toString().substring(1);
                String format = sdf.format(date);
                if (format.equals(publishtime)) {
                    NetLandTransaction netLandTransaction = new NetLandTransaction();
                    netLandTransaction.setPublishtime(sdf.parse(publishtime));
                    Elements addressElement = item.select(".col-xs-1");
                    String address = addressElement.get(0).childNodes().get(0).toString();
                    netLandTransaction.setAddress(address.replaceAll("\n", "").substring(1, address.length() - 2));
                    Elements statusElement = item.select(".item-right");
                    String status = statusElement.get(0).childNodes().get(3).childNodes().toString();
                    netLandTransaction.setStatus(status.replaceAll("\n", "").substring(1, status.length() - 2));
                    String link = item.select("a").get(0).attributes().get("href");
                    netLandTransaction.setDetailLink(link);
                    String content = item.select("a").get(0).childNodes().get(0).toString();
                    netLandTransaction.setContent(content.replaceAll("\n", ""));
                    List<NetLandTransaction> list = netLandTransactionDao.getNetLandTransactionList(netLandTransaction);
                    if (CollectionUtils.isNotEmpty(list)) {
                        continue;
                    }
                    netLandTransaction.setId(0);
                    netLandTransaction.setBisEdit(false);
                    netLandTransactionDao.addNetLandTransaction(netLandTransaction);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //土地预公告
    private void beforehand(Elements trs, Integer mainId) {
        //移除首行字段名
        trs.remove(0);
        for (Element item : trs) {
            Elements select = item.select("td");
            NetBeforehandAnnouncement netBeforehandAnnouncement = new NetBeforehandAnnouncement();
            netBeforehandAnnouncement.setDkwz(checkNull(select, 1));//地块位置
            netBeforehandAnnouncement.setJydmj(checkNull(select, 2));//净用地面积(亩)
            netBeforehandAnnouncement.setRjl(checkNull(select, 3));//计入容积率总建筑面积/容积率
            netBeforehandAnnouncement.setJzmd(checkNull(select, 4));//建筑密度
            netBeforehandAnnouncement.setJzgd(checkNull(select, 5));//建筑高度
            netBeforehandAnnouncement.setLdl(checkNull(select, 6));//绿地率
            netBeforehandAnnouncement.setYdxz(checkNull(select, 7));//用地性质
            netBeforehandAnnouncement.setYjsssj(checkNull(select, 8));//预计上市时间
            netBeforehandAnnouncement.setMainId(mainId);
            netBeforehandAnnouncementDao.addNetBeforehandAnnouncement(netBeforehandAnnouncement);
        }


    }

    //结果公告
    private void result(Elements trs, Integer mainId) {
        Integer a = trs.get(0).select("th").size();
        //移除首行字段名
        trs.remove(0);
        if (a == 7) {
            for (Element item : trs) {
                Elements select = item.select("td");
                NetResultAnnouncement netResultAnnouncement = new NetResultAnnouncement();
                netResultAnnouncement.setZdbh(checkNull(select, 1));//宗地编号
                netResultAnnouncement.setZdwz(checkNull(select, 2));//宗地位置
                netResultAnnouncement.setJydmj(checkNull(select, 3));//净用地面积
                netResultAnnouncement.setQsj(checkNull(select, 4));//起始价
                netResultAnnouncement.setCcj(checkNull(select, 5));//成交价
                netResultAnnouncement.setJdr(checkNull(select, 6));//竞得人
                netResultAnnouncement.setMainId(mainId);
                netResultAnnouncementDao.addNetResultAnnouncement(netResultAnnouncement);
            }
        }
        if (a == 8) {
            for (Element item : trs) {
                Elements select = item.select("td");
                NetResultAnnouncement netResultAnnouncement = new NetResultAnnouncement();
                netResultAnnouncement.setZdbh(checkNull(select, 1));//宗地编号
                netResultAnnouncement.setZdwz(checkNull(select, 2));//宗地位置
                netResultAnnouncement.setJydmj(checkNull(select, 3));//净用地面积
                netResultAnnouncement.setTdyt(checkNull(select, 4));//土地用途
                netResultAnnouncement.setCjsj(checkNull(select, 5));//成交时间
                netResultAnnouncement.setCcj(checkNull(select, 6));//成交价
                netResultAnnouncement.setJdr(checkNull(select, 7));//竞得人
                netResultAnnouncement.setMainId(mainId);
                netResultAnnouncementDao.addNetResultAnnouncement(netResultAnnouncement);
            }
        }
    }

    //挂牌公告
    private void hangTag(Elements trs, Integer mainId) {
        Integer a = trs.get(0).select("td").size();
        //移除前两行字段名
        trs.remove(0);
        trs.remove(0);
        if (a == 12) {
            hangtagOne(trs, mainId);
        }
        if (a == 11) {
            hangtagTow(trs, mainId);
        }
    }

    //挂告类型一（14个字段）
    private void hangtagOne(Elements trs, Integer mainId) {
        for (Element item : trs) {
            Elements select = item.select("td");

            NetHangTagAnnouncement netHangTagAnnouncement = new NetHangTagAnnouncement();
            netHangTagAnnouncement.setZdbh(checkNull(select, 1));//宗地编号
            netHangTagAnnouncement.setZdwz(checkNull(select, 2));//宗地位置
            netHangTagAnnouncement.setJydmj(checkNull(select, 3));//净用地面积
            netHangTagAnnouncement.setTdytjsyqx(checkNull(select, 4));//土地用途及使用年限
            netHangTagAnnouncement.setQpjj(checkNull(select, 5));//拍卖起叫价
            netHangTagAnnouncement.setJmbxj(checkNull(select, 6));//竞买保证金
            if (select.size() == 15) {
                pmcrsj = checkNull(select, 7);
                netHangTagAnnouncement.setRjl(checkNull(select, 8));//容积率
                netHangTagAnnouncement.setJzmd(checkNull(select, 9));//建筑密度
                netHangTagAnnouncement.setJzgd(checkNull(select, 10));//建筑高度
                netHangTagAnnouncement.setGhydsyxz(checkNull(select, 11));//规划用地使用性质
                netHangTagAnnouncement.setTzqdyq(checkNull(select, 12));//投资强度要求
                netHangTagAnnouncement.setXmcyyq(checkNull(select, 13));//项目产业要求
                netHangTagAnnouncement.setCrr(checkNull(select, 14));//出让人
            } else {
                netHangTagAnnouncement.setRjl(checkNull(select, 7));//容积率
                netHangTagAnnouncement.setJzmd(checkNull(select, 8));//建筑密度
                netHangTagAnnouncement.setJzgd(checkNull(select, 9));//建筑高度
                netHangTagAnnouncement.setGhydsyxz(checkNull(select, 10));//规划用地使用性质
                netHangTagAnnouncement.setTzqdyq(checkNull(select, 11));//投资强度要求
                netHangTagAnnouncement.setXmcyyq(checkNull(select, 12));//项目产业要求
                netHangTagAnnouncement.setCrr(checkNull(select, 13));//出让人
            }
            netHangTagAnnouncement.setPmcrsj(pmcrsj);//拍卖出让时间
            netHangTagAnnouncement.setMainId(mainId);
            netHangTagAnnouncementDao.addNetHangTagAnnouncement(netHangTagAnnouncement);
        }
    }


    //挂告类型二（13个字段）
    private void hangtagTow(Elements trs, Integer mainId) {
        for (Element item : trs) {
            Elements select = item.select("td");

            NetHangTagAnnouncement netHangTagAnnouncement = new NetHangTagAnnouncement();
            netHangTagAnnouncement.setZdbh(checkNull(select, 1));//宗地编号
            netHangTagAnnouncement.setZdwz(checkNull(select, 2));//宗地位置
            netHangTagAnnouncement.setJydmj(checkNull(select, 3));//净用地面积
            netHangTagAnnouncement.setTdytjsyqx(checkNull(select, 4));//土地用途及使用年限
            netHangTagAnnouncement.setQpjj(checkNull(select, 5));//拍卖起叫价
            netHangTagAnnouncement.setJmbxj(checkNull(select, 6));//竞买保证金
            if (select.size() == 14) {
                pmcrsj = checkNull(select, 7);
                netHangTagAnnouncement.setRjl(checkNull(select, 8));//容积率
                netHangTagAnnouncement.setJzmd(checkNull(select, 9));//建筑密度
                netHangTagAnnouncement.setJzgd(checkNull(select, 10));//建筑高度
                netHangTagAnnouncement.setGhydsyxz(checkNull(select, 11));//规划用地使用性质
                netHangTagAnnouncement.setCzzymjjfs(checkNull(select, 12));//持证准用面积(亩)及方式
                netHangTagAnnouncement.setCrr(checkNull(select, 13));//出让人
            } else {
                netHangTagAnnouncement.setRjl(checkNull(select, 7));//容积率
                netHangTagAnnouncement.setJzmd(checkNull(select, 8));//建筑密度
                netHangTagAnnouncement.setJzgd(checkNull(select, 9));//建筑高度
                netHangTagAnnouncement.setGhydsyxz(checkNull(select, 10));//规划用地使用性质
                netHangTagAnnouncement.setCzzymjjfs(checkNull(select, 11));//持证准用面积(亩)及方式
                netHangTagAnnouncement.setCrr(checkNull(select, 12));//出让人
            }
            netHangTagAnnouncement.setPmcrsj(pmcrsj);//拍卖出让时间
            netHangTagAnnouncement.setMainId(mainId);
            netHangTagAnnouncementDao.addNetHangTagAnnouncement(netHangTagAnnouncement);
        }
    }

    //拍告
    private void auction(Elements trs, Integer mainId) {
        //移除前两行字段名
        trs.remove(0);
        trs.remove(0);
        for (Element item : trs) {
            Elements select = item.select("td");

            NetAuctionAnnouncement netAuctionAnnouncement = new NetAuctionAnnouncement();
            netAuctionAnnouncement.setZdbh(checkNull(select, 1));//宗地编号
            netAuctionAnnouncement.setZdwz(checkNull(select, 2));//宗地位置
            netAuctionAnnouncement.setJydmj(checkNull(select, 3));//净用地面积
            netAuctionAnnouncement.setTdytjsyqx(checkNull(select, 4));//土地用途及使用年限
            netAuctionAnnouncement.setQpjj(checkNull(select, 5));//拍卖起叫价
            netAuctionAnnouncement.setJmbxj(checkNull(select, 6));//竞买保证金
            if (select.size() == 14) {
                pmcrsj = checkNull(select, 7);
                netAuctionAnnouncement.setRjl(checkNull(select, 8));//容积率
                netAuctionAnnouncement.setJzmd(checkNull(select, 9));//建筑密度
                netAuctionAnnouncement.setJzgd(checkNull(select, 10));//建筑高度
                netAuctionAnnouncement.setGhydsyxz(checkNull(select, 11));//规划用地使用性质
                netAuctionAnnouncement.setCzzymjjfs(checkNull(select, 12));//持证准用面积(亩)及方式
                netAuctionAnnouncement.setCrr(checkNull(select, 13));//出让人
            } else {
                netAuctionAnnouncement.setRjl(checkNull(select, 7));//容积率
                netAuctionAnnouncement.setJzmd(checkNull(select, 8));//建筑密度
                netAuctionAnnouncement.setJzgd(checkNull(select, 9));//建筑高度
                netAuctionAnnouncement.setGhydsyxz(checkNull(select, 10));//规划用地使用性质
                netAuctionAnnouncement.setCzzymjjfs(checkNull(select, 11));//持证准用面积(亩)及方式
                netAuctionAnnouncement.setCrr(checkNull(select, 12));//出让人
            }
            netAuctionAnnouncement.setPmcrsj(pmcrsj);//拍卖出让时间
            netAuctionAnnouncement.setMainId(mainId);
            netAuctionAnnouncementDao.addNetAuctionAnnouncement(netAuctionAnnouncement);
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
}
