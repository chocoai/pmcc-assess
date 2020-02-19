package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetUrlConfigDao;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetUrlConfig;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;

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
            for (int i = 0; i < pages; i++) {
                try {
                    Elements elements = null;
                    Integer pageIndex = i + 1;
                    if(config.getPageSize()!=null&&config.getPageSize()>0){
                        pageIndex=pageIndex*config.getPageSize();
                    }
                    String urlInfo = config.getUrl().replace("{pages}", String.valueOf(pageIndex));
                    if (i == 0 && StringUtils.isNotBlank(config.getIndexUrl()))
                        urlInfo = config.getIndexUrl();
                    elements = netInfoRecordService.getContent(urlInfo, config.getItemList(), config.getEncoding());
                    for (Element element : elements) {
                        try {
                            // 标题
                            String title = "";
                            if (StringUtils.isNotBlank(config.getItemTitle())) {
                                Elements titleElement = element.select(config.getItemTitle());
                                if (StringUtils.isBlank(title)) {
                                    title = titleElement.get(0).attributes().get("title");
                                }
                                title = StringUtils.isBlank(title) ? element.text() : title;
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
                                href = config.getDomainName() + href;

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
                            netInfoRecord.setSourceSiteUrl(href);
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


}
