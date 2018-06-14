package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.dao.funi.*;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.lang.annotation.ElementType;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Date;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/12
 * @time: 11:16
 */
@Service
public class FuniWebService {
    @Autowired
    private FuniHousesDao funiHousesDao;
    @Autowired
    private FuniDevelopersDao funiDevelopersDao;
    @Autowired
    private FuniHousesPropertyDao funiHousesPropertyDao;
    @Autowired
    private FuniPropertyManagementDao funiPropertyManagementDao;
    @Autowired
    private FuniHousesTypeDao funiHousesTypeDao;

    public void getFuniHousesType(String urlString, Integer lpbh) {
        Elements elements = getContent(urlString + "/huxing.htm?page=1", ".hxlist", "");
        if (elements.size() <= 0) {
            return;
        }
        getHxt(lpbh, elements);

        Elements pages = elements.get(0).select(".pages a");//分页
        if (pages != null && pages.size() > 1) {
            Element element = pages.get(pages.size() - 2);
            String string = element.childNodes().get(0).toString();
            if (StringUtils.isNotBlank(string)) {
                for (Integer i = 2; i <= Integer.parseInt(string); i++) {
                    elements = getContent(urlString + "/huxing.htm?page=" + i, ".hxlist", "");
                    getHxt(lpbh, elements);
                }
            }
        }

    }

    private void getHxt(Integer lpbh, Elements elements) {
        Elements li = elements.get(0).select("li");//户型图
        for (Element item : li) {
            Elements select = item.select(".area span");
            FuniHousesType funiHousesType = new FuniHousesType();
            funiHousesType.setLpbh(lpbh);

            if (select.get(0).childNodes().size() > 0) {
                funiHousesType.setFx(select.get(0).childNodes().get(0).toString());
            }
            if (select.get(1).childNodes().size() > 0) {
                funiHousesType.setMj(select.get(1).childNodes().get(0).toString());
            }
            //http://img.funi.com/images/upload/144/63cc1d864dd5a0aa3750366f2b99.jpg.240x1000.jpg
            String s = item.select("img").get(0).attributes().get("src").toString().replaceAll(".240x1000.jpg", "");
            funiHousesType.setHxt(s);
            funiHousesTypeDao.addFuniHousesType(funiHousesType);
        }
    }

    private void office(Elements select, Integer lpbh) {
        FuniHousesProperty funiHousesProperty = new FuniHousesProperty();
        funiHousesProperty.setWylx(select.get(0).childNodes().get(0).toString());//物业类型
        funiHousesProperty.setWyf(select.get(1).childNodes().get(0).toString());//物业费
        String string = select.get(2).childNodes().get(0).toString();//物业公司
        if (StringUtils.isNotBlank(string)) {
            FuniPropertyManagement funiPropertyManagement = funiPropertyManagementDao.getFuniPropertyManagement(string);
            if (funiPropertyManagement == null) {
                funiPropertyManagement = new FuniPropertyManagement();
                funiPropertyManagement.setPropertyManagementName(string);
                funiPropertyManagementDao.addFuniPropertyManagement(funiPropertyManagement);
            }
            funiHousesProperty.setWygsbh(funiPropertyManagement.getId());//物业公司
        }
        funiHousesProperty.setYxdl(select.get(3).childNodes().get(0).toString());//营销代理
        funiHousesProperty.setJzlb(select.get(4).childNodes().get(0).toString());//建筑类别
        funiHousesProperty.setZxqk(select.get(5).childNodes().get(0).toString());//装修情况
        //层高
        funiHousesProperty.setTdsynx(select.get(7).childNodes().get(0).toString());//土地使用年限
        funiHousesProperty.setJzmj(select.get(8).childNodes().get(0).toString());//建筑面积
        funiHousesProperty.setZhs(select.get(9).childNodes().get(0).toString());//总户数

        funiHousesProperty.setKpsj(select.get(10).childNodes().get(0).toString());//开盘时间
        funiHousesProperty.setJfsj(select.get(11).childNodes().get(0).toString());//交房时间
        funiHousesProperty.setFxqj(select.get(12).childNodes().get(0).toString());//户型区间
        //客梯数
        //货梯数
        funiHousesProperty.setLpbh(lpbh);
        funiHousesPropertyDao.addFuniHousesProperty(funiHousesProperty);
    }

    private void business(Elements select, Integer lpbh) {
        FuniHousesProperty funiHousesProperty = new FuniHousesProperty();
        funiHousesProperty.setWylx(select.get(0).childNodes().get(0).toString());//物业类型
        funiHousesProperty.setWyf(select.get(1).childNodes().get(0).toString());//物业费
        String string = select.get(2).childNodes().get(0).toString();//物业公司
        if (StringUtils.isNotBlank(string)) {
            FuniPropertyManagement funiPropertyManagement = funiPropertyManagementDao.getFuniPropertyManagement(string);
            if (funiPropertyManagement == null) {
                funiPropertyManagement = new FuniPropertyManagement();
                funiPropertyManagement.setPropertyManagementName(string);
                funiPropertyManagementDao.addFuniPropertyManagement(funiPropertyManagement);
            }
            funiHousesProperty.setWygsbh(funiPropertyManagement.getId());//开发商
        }
        funiHousesProperty.setTdsynx(select.get(3).childNodes().get(0).toString());//土地使用年限
        funiHousesProperty.setZhs(select.get(4).childNodes().get(0).toString());//总户数
        funiHousesProperty.setJzlb(select.get(5).childNodes().get(0).toString());//建筑类别
        funiHousesProperty.setYxdl(select.get(6).childNodes().get(0).toString());//营销代理
        funiHousesProperty.setZxqk(select.get(7).childNodes().get(0).toString());//装修情况
        funiHousesProperty.setJzmj(select.get(8).childNodes().get(0).toString());//建筑面积
        funiHousesProperty.setKpsj(select.get(9).childNodes().get(0).toString());//开盘时间
        funiHousesProperty.setJfsj(select.get(10).childNodes().get(0).toString());//交房时间
        funiHousesProperty.setFxqj(select.get(11).childNodes().get(0).toString());//户型区间
        funiHousesProperty.setLpbh(lpbh);
        funiHousesPropertyDao.addFuniHousesProperty(funiHousesProperty);
    }

    private void house(Elements select, Integer lpbh) {
        FuniHousesProperty funiHousesProperty = new FuniHousesProperty();
        funiHousesProperty.setWylx(select.get(0).childNodes().get(0).toString());//物业类型
        funiHousesProperty.setJzlb(select.get(1).childNodes().get(0).toString());//建筑类别
        funiHousesProperty.setZxqk(select.get(2).childNodes().get(0).toString());//装修情况
        funiHousesProperty.setTdsynx(select.get(3).childNodes().get(0).toString());//土地使用年限
        funiHousesProperty.setZhs(select.get(4).childNodes().get(0).toString());//总户数
        funiHousesProperty.setWyf(select.get(5).childNodes().get(0).toString());//物业费

        String string = select.get(6).childNodes().get(0).toString();//物业公司
        if (StringUtils.isNotBlank(string)) {
            FuniPropertyManagement funiPropertyManagement = funiPropertyManagementDao.getFuniPropertyManagement(string);
            if (funiPropertyManagement == null) {
                funiPropertyManagement = new FuniPropertyManagement();
                funiPropertyManagement.setPropertyManagementName(string);
                funiPropertyManagementDao.addFuniPropertyManagement(funiPropertyManagement);
            }
            funiHousesProperty.setWygsbh(funiPropertyManagement.getId());//开发商
        }

        funiHousesProperty.setYxdl(select.get(7).childNodes().get(0).toString());//营销代理
        funiHousesProperty.setZdmj(select.get(8).childNodes().get(0).toString());//占地面积
        funiHousesProperty.setJzmj(select.get(9).childNodes().get(0).toString());//建筑面积
        funiHousesProperty.setKpsj(select.get(10).childNodes().get(0).toString());//开盘时间
        funiHousesProperty.setJfsj(select.get(11).childNodes().get(0).toString());//交房时间
        funiHousesProperty.setFxqj(select.get(12).childNodes().get(0).toString());//户型区间
        funiHousesProperty.setLpbh(lpbh);
        funiHousesPropertyDao.addFuniHousesProperty(funiHousesProperty);
    }

    private void details(Elements select, Integer lpbh) {
        FuniHouses funiHouses = funiHousesDao.getFuniHouses(lpbh);
        //基本信息=================================================

        if(!select.get(0).childNodes().get(0).toString().equals("暂无")) {
            funiHouses.setJzmj(select.get(0).childNodes().get(0).childNodes().get(0).toString());//建筑面积
        }
        else
        {
            funiHouses.setJzmj("暂无");
        }
        if(!select.get(1).childNodes().get(0).toString().equals("暂无")) {
            funiHouses.setZdmj(select.get(1).childNodes().get(0).childNodes().get(0).toString());//占地面积
        }
        else
        {
            funiHouses.setZdmj("暂无");
        }
        if(!select.get(2).childNodes().get(0).toString().equals("暂无")) {
            funiHouses.setRjl(select.get(2).childNodes().get(0).childNodes().get(0).toString());//容积率
        }
        else
        {
            funiHouses.setRjl("暂无");
        }
        if(!select.get(3).childNodes().get(0).toString().equals("暂无")) {
            funiHouses.setLhl(select.get(3).childNodes().get(0).childNodes().get(0).toString());//绿化率
        }
        else
        {
            funiHouses.setLhl("暂无");//绿化率
        }
        if(!select.get(4).childNodes().get(0).toString().equals("暂无")) {
            funiHouses.setCwxx(select.get(4).childNodes().get(0).toString());//车位信息
        }
        else
        {
            funiHouses.setCwxx("暂无");
        }
        if(!select.get(5).childNodes().get(0).toString().equals("暂无")) {
            funiHouses.setLpdz(select.get(5).childNodes().get(0).childNodes().get(0).toString());//项目地址
        }
        else
        {
            funiHouses.setLpdz("暂无");
        }
        if(!select.get(6).childNodes().get(0).toString().equals("暂无")) {
            funiHouses.setSldz(select.get(6).childNodes().get(0).toString());//售楼地址
        }
        else
        {
            funiHouses.setSldz("暂无");
        }



        String string = select.get(7).childNodes().get(0).toString();
        if (StringUtils.isNotBlank(string)) {
            FuniDevelopers funiDevelopers = funiDevelopersDao.getFuniDevelopers(string);
            if (funiDevelopers == null) {
                funiDevelopers = new FuniDevelopers();
                funiDevelopers.setDevelopersName(string);
                funiDevelopersDao.addFuniDevelopers(funiDevelopers);
            }
            funiHouses.setKfsbh(funiDevelopers.getId());//开发商
        }

        funiHouses.setSsskzh(select.get(8).childNodes().get(0).toString());//销售许可证
        Elements p = select.get(9).select("p");
        if (p.size() > 0) {
            funiHouses.setLpjs(p.get(0).childNodes().get(0).toString());//楼盘介绍
        }
        funiHousesDao.editFuniHouses(funiHouses);
    }

    public void getFuniHousesDetails(String urlString, Integer lpbh) {
        //楼盘信息
        Elements elements = getContent(urlString + "/detail.htm", ".intro dd", "");
        for (Element item : elements) {

            Elements select = item.select("ul li em");
            String string = select.get(0).childNodes().get(0).toString();
            switch (string) {
                case "办公": {
                    office(select, lpbh);
                    break;
                }
                case "住宅": {
                    house(select, lpbh);
                    break;
                }
                case "商业": {
                    business(select, lpbh);
                    break;
                }
                default: {
                    details(select, lpbh);
                    break;
                }
            }
        }

    }

    public void getFuniHousesList(Integer page) {
        try {
            String urlInfo = "http://www.funi.com/loupan/region_0_0_0_0_";
            Elements elements = getContent(urlInfo + "1", ".pages", "");
            int pages = 1;
            if (elements != null) {
                Elements a = elements.select("a");
                Element element = a.get(a.size() - 2);
                String string = element.childNodes().get(0).toString();
                if (StringUtils.isNotBlank(string)) {
                    pages = Integer.parseInt(string);
                }
            }
            if (page > pages)//如果取的页数大于总页数，则退出
            {
                return;
            }
            // pages = 10;//临时测试用的
            for (int i = page; i <= pages; i++) {
                System.out.println("pages:" + i);
                elements = getContent(urlInfo + i, ".pan-con dl", "");
                for (Element item : elements) {

                    Elements h2a = item.select("h2 a");

                    Elements address = item.select(".address");

                    FuniHouses funiHouses = new FuniHouses();
                    funiHouses.setLpmc(h2a.get(0).childNodes().get(0).toString());
                    String s = h2a.get(0).attributes().get("href");
                    funiHouses.setFuniweb("http://www.funi.com" + s.split(";")[0]);
                    funiHouses.setLpdz(address.get(0).attributes().get("title"));
                    funiHouses.setJd(item.attributes().get("lng"));
                    funiHouses.setWd(item.attributes().get("lat"));
                    funiHousesDao.addFuniHouses(funiHouses);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
}
