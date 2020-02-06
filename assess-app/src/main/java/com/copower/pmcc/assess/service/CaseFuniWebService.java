package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.basis.dao.funi.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.erp.common.exception.BusinessException;
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
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/6/12
 * @time: 11:16
 */
@Service
public class CaseFuniWebService {
    @Autowired
    private CaseFuniHousesDao caseFuniHousesDao;
    @Autowired
    private CaseFuniDevelopersDao caseFuniDevelopersDao;
    @Autowired
    private CaseFuniHousesPropertyDao caseFuniHousesPropertyDao;
    @Autowired
    private CaseFuniPropertyManagementDao caseFuniPropertyManagementDao;
    @Autowired
    private CaseFuniHousesTypeDao caseFuniHousesTypeDao;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;

    public void getCaseFuniHousesType(String urlString, Integer lpbh) {
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
            CaseFuniHousesType caseFuniHousesType = new CaseFuniHousesType();
            caseFuniHousesType.setLpbh(lpbh);

            if (select.get(0).childNodes().size() > 0) {
                caseFuniHousesType.setHx(select.get(0).childNodes().get(0).toString());
            }
            if (select.get(1).childNodes().size() > 0) {
                caseFuniHousesType.setMj(select.get(1).childNodes().get(0).toString());
            }
            //http://img.funi.com/images/upload/144/63cc1d864dd5a0aa3750366f2b99.jpg.240x1000.jpg
            String s = item.select("img").get(0).attributes().get("src").toString().replaceAll(".240x1000.jpg", "");
            caseFuniHousesType.setHxt(s);
            caseFuniHousesTypeDao.addCaseFuniHousesType(caseFuniHousesType);
        }
    }

    private void office(Elements select, Integer lpbh) {
        CaseFuniHousesProperty caseFuniHousesProperty = new CaseFuniHousesProperty();
        caseFuniHousesProperty.setWylx(checkNull(select, 0));//物业类型
        caseFuniHousesProperty.setWyf(checkNull(select, 1));//物业费
        String string = checkNull(select, 2);//物业公司
        if (StringUtils.isNotBlank(string)) {
            CaseFuniPropertyManagement caseFuniPropertyManagement = caseFuniPropertyManagementDao.getCaseFuniPropertyManagement(string);
            if (caseFuniPropertyManagement == null) {
                caseFuniPropertyManagement = new CaseFuniPropertyManagement();
                caseFuniPropertyManagement.setPropertyManagementName(string);
                caseFuniPropertyManagementDao.addCaseFuniPropertyManagement(caseFuniPropertyManagement);
            }
            caseFuniHousesProperty.setWygsbh(caseFuniPropertyManagement.getId());//物业公司
            caseFuniHousesProperty.setWygs(string);
        }
        caseFuniHousesProperty.setYxdl(checkNull(select, 3));//营销代理
        caseFuniHousesProperty.setJzlb(checkNull(select, 4));//建筑类别
        caseFuniHousesProperty.setZxqk(checkNull(select, 5));//装修情况
        caseFuniHousesProperty.setCg(checkNull(select, 6));//层高
        caseFuniHousesProperty.setTdsynx(checkNull(select, 7));//土地使用年限
        caseFuniHousesProperty.setJzmj(checkNull(select, 8));//建筑面积
        caseFuniHousesProperty.setZhs(checkNull(select, 9));//总户数

        caseFuniHousesProperty.setKpsj(checkNull(select, 10));//开盘时间
        caseFuniHousesProperty.setJfsj(checkNull(select, 11));//交房时间
        caseFuniHousesProperty.setHxqj(checkNull(select, 12));//户型区间
        caseFuniHousesProperty.setKts(checkNull(select, 13));//客梯数
        caseFuniHousesProperty.setHts(checkNull(select, 14));//货梯数
        //客梯数
        //货梯数
        caseFuniHousesProperty.setLpbh(lpbh);
        caseFuniHousesPropertyDao.addCaseFuniHousesProperty(caseFuniHousesProperty);
    }

    private String checkNull(Elements select, Integer index) {
        String string = select.get(index).childNodes().get(0).toString();
        if (StringUtils.isBlank(string)) {
            string = "";
        }
        return string;
    }

    private void business(Elements select, Integer lpbh) {
        CaseFuniHousesProperty caseFuniHousesProperty = new CaseFuniHousesProperty();
        caseFuniHousesProperty.setWylx(checkNull(select, 0));//物业类型
        caseFuniHousesProperty.setWyf(checkNull(select, 1));//物业费
        String string = checkNull(select, 2);//物业公司
        if (StringUtils.isNotBlank(string)) {
            CaseFuniPropertyManagement caseFuniPropertyManagement = caseFuniPropertyManagementDao.getCaseFuniPropertyManagement(string);
            if (caseFuniPropertyManagement == null) {
                caseFuniPropertyManagement = new CaseFuniPropertyManagement();
                caseFuniPropertyManagement.setPropertyManagementName(string);
                caseFuniPropertyManagementDao.addCaseFuniPropertyManagement(caseFuniPropertyManagement);
            }
            caseFuniHousesProperty.setWygs(string);
            caseFuniHousesProperty.setWygsbh(caseFuniPropertyManagement.getId());//开发商
        }
        caseFuniHousesProperty.setTdsynx(checkNull(select, 3));//土地使用年限
        caseFuniHousesProperty.setZhs(checkNull(select, 4));//总户数
        caseFuniHousesProperty.setJzlb(checkNull(select, 5));//建筑类别
        caseFuniHousesProperty.setYxdl(checkNull(select, 6));//营销代理
        caseFuniHousesProperty.setZxqk(checkNull(select, 7));//装修情况
        caseFuniHousesProperty.setJzmj(checkNull(select, 8));//建筑面积
        caseFuniHousesProperty.setKpsj(checkNull(select, 9));//开盘时间
        caseFuniHousesProperty.setJfsj(checkNull(select, 10));//交房时间
        caseFuniHousesProperty.setHxqj(checkNull(select, 11));//户型区间
        caseFuniHousesProperty.setLpbh(lpbh);
        caseFuniHousesPropertyDao.addCaseFuniHousesProperty(caseFuniHousesProperty);
    }

    private void house(Elements select, Integer lpbh) {
        CaseFuniHousesProperty caseFuniHousesProperty = new CaseFuniHousesProperty();
        caseFuniHousesProperty.setWylx(checkNull(select, 0));//物业类型
        caseFuniHousesProperty.setJzlb(checkNull(select, 1));//建筑类别
        caseFuniHousesProperty.setZxqk(checkNull(select, 2));//装修情况
        caseFuniHousesProperty.setTdsynx(checkNull(select, 3));//土地使用年限
        caseFuniHousesProperty.setZhs(checkNull(select, 4));//总户数
        caseFuniHousesProperty.setWyf(checkNull(select, 5));//物业费

        String string = checkNull(select, 6);//物业公司
        if (StringUtils.isNotBlank(string)) {
            CaseFuniPropertyManagement caseFuniPropertyManagement = caseFuniPropertyManagementDao.getCaseFuniPropertyManagement(string);
            if (caseFuniPropertyManagement == null) {
                caseFuniPropertyManagement = new CaseFuniPropertyManagement();
                caseFuniPropertyManagement.setPropertyManagementName(string);
                caseFuniPropertyManagementDao.addCaseFuniPropertyManagement(caseFuniPropertyManagement);
            }
            caseFuniHousesProperty.setWygs(string);
            caseFuniHousesProperty.setWygsbh(caseFuniPropertyManagement.getId());//开发商
        }

        caseFuniHousesProperty.setYxdl(checkNull(select, 7));//营销代理
        caseFuniHousesProperty.setZdmj(checkNull(select, 8));//占地面积
        caseFuniHousesProperty.setJzmj(checkNull(select, 9));//建筑面积
        caseFuniHousesProperty.setKpsj(checkNull(select, 10));//开盘时间
        caseFuniHousesProperty.setJfsj(checkNull(select, 11));//交房时间
        caseFuniHousesProperty.setHxqj(checkNull(select, 12));//户型区间
        caseFuniHousesProperty.setLpbh(lpbh);
        caseFuniHousesPropertyDao.addCaseFuniHousesProperty(caseFuniHousesProperty);
    }

    private void details(Elements select, Integer lpbh) {
        CaseFuniHouses caseFuniHouses = caseFuniHousesDao.getCaseFuniHouses(lpbh);
        //基本信息=================================================

        if (!select.get(0).childNodes().get(0).toString().equals("暂无")) {
            caseFuniHouses.setJzmj(select.get(0).childNodes().get(0).childNodes().get(0).toString());//建筑面积
        }
        if (!select.get(1).childNodes().get(0).toString().equals("暂无")) {
            caseFuniHouses.setZdmj(select.get(1).childNodes().get(0).childNodes().get(0).toString());//占地面积
        }
        if (!select.get(2).childNodes().get(0).toString().equals("暂无")) {
            caseFuniHouses.setRjl(select.get(2).childNodes().get(0).childNodes().get(0).toString());//容积率
        }
        if (!select.get(3).childNodes().get(0).toString().equals("暂无")) {
            caseFuniHouses.setLhl(select.get(3).childNodes().get(0).childNodes().get(0).toString());//绿化率
        }
        if (!select.get(4).childNodes().get(0).toString().equals("暂无")) {
            caseFuniHouses.setCwxx(select.get(4).childNodes().get(0).toString());//车位信息
        }
        if (!select.get(5).childNodes().get(0).toString().equals("暂无")) {
            caseFuniHouses.setXmdz(select.get(5).childNodes().get(0).childNodes().get(0).toString());//项目地址
        }
        if (!select.get(6).childNodes().get(0).toString().equals("暂无")) {
            caseFuniHouses.setSldz(select.get(6).childNodes().get(0).toString());//售楼地址
        }
        String string = select.get(7).childNodes().get(0).toString();
        if (StringUtils.isNotBlank(string)) {
            CaseFuniDevelopers caseFuniDevelopers = caseFuniDevelopersDao.getCaseFuniDevelopers(string);
            if (caseFuniDevelopers == null) {
                caseFuniDevelopers = new CaseFuniDevelopers();
                caseFuniDevelopers.setDevelopersName(string);
                caseFuniDevelopersDao.addCaseFuniDevelopers(caseFuniDevelopers);
            }
            caseFuniHouses.setKfsbh(caseFuniDevelopers.getId());//开发商
            caseFuniHouses.setKfs(string);
        }

        caseFuniHouses.setXsxkz(select.get(8).childNodes().get(0).toString());//销售许可证
        Elements p = select.get(9).select(".showDetail");
        if (p.size() > 0) {
            String string1 = p.toString();

            String s = StringUtils.replacePattern(string1, "<.*?>", "");
            caseFuniHouses.setLpjs(s);//楼盘介绍
        }
        caseFuniHousesDao.editCaseFuniHouses(caseFuniHouses);
    }

    public void getCaseFuniHousesDetails(String urlString, Integer lpbh) {
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

    public void getCaseFuniHousesList(Integer page) {
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
                    String string = h2a.get(0).childNodes().get(0).toString();

                    CaseFuniHouses caseFuniHouses = new CaseFuniHouses();
                    caseFuniHouses.setLpmc(string);
                    List<CaseFuniHouses> caseFuniHousesList = caseFuniHousesDao.getCaseFuniHousesList(caseFuniHouses, "");
                    if (CollectionUtils.isNotEmpty(caseFuniHousesList)) {
                        continue;
                        // caseFuniHouses = caseFuniHousesList.get(0);
                    } else {
                        caseFuniHouses = new CaseFuniHouses();
                        caseFuniHouses.setId(0);
                    }
                    caseFuniHouses.setLpmc(h2a.get(0).childNodes().get(0).toString());
                    String s = h2a.get(0).attributes().get("href");
                    caseFuniHouses.setFuniweb("http://www.funi.com" + s.split(";")[0]);
                    String s2 = address.get(0).attributes().get("title");
                    caseFuniHouses.setXmdz(s2);
                    caseFuniHouses.setLpdz(s2.substring(1, s2.indexOf(']')));
                    caseFuniHouses.setJd(item.attributes().get("lng"));
                    caseFuniHouses.setWd(item.attributes().get("lat"));
                    String s1 = item.select("img").get(0).attributes().get("src").toString().replaceAll(".160x120.jpg", "");
                    caseFuniHouses.setLptp(s1);
                    if (caseFuniHouses.getId() > 0) {
                        caseFuniHousesDao.editCaseFuniHouses(caseFuniHouses);
                    } else {
                        caseFuniHousesDao.addCaseFuniHouses(caseFuniHouses);
                    }
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

    public void updateHousesData(Integer id, String xxType, String keys, String values) throws BusinessException {
        switch (xxType) {
            case "wyxx"://物业信息
            {
                String updateSql = String.format("update pmcc_assess_case.tb_case_funi_houses_property set %s='%s' where id=%s", keys, values, id);
                ddlMySqlAssist.customTableDdlInsert(updateSql);
                break;
            }
            case "lpxx"://楼盘信息
            {
                String updateSql = String.format("update pmcc_assess_case.tb_case_funi_houses set %s='%s' where id=%s", keys, values, id);
                ddlMySqlAssist.customTableDdlInsert(updateSql);
                break;
            }
            case "lppt"://楼盘配套
            {
                String sqlString = String.format("select * from pmcc_assess_case.tb_case_funi_houses_mating  where lpbh=%s", id);
                List<Map> maps = ddlMySqlAssist.customTableSelect(sqlString);
                if (maps.size() > 0) {
                    sqlString = String.format("update pmcc_assess_case.tb_case_funi_houses_mating set %s='%s' where lpbh=%s", keys, values, id);
                    ddlMySqlAssist.customTableDdlInsert(sqlString);
                } else {
                    sqlString = String.format("insert into pmcc_assess_case.tb_case_funi_houses_mating (lpbh,%s) value(%s,'%s')", keys, id, values);
                    ddlMySqlAssist.customTableDdlInsert(sqlString);
                }
                break;
            }
        }

    }
}
