package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basic.dao.BasicHouseIntelligentDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseIntelligent;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseIntelligentVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:25
 * @Description:电力通讯网络
 */
@Service
public class BasicHouseIntelligentService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicHouseIntelligentDao basicHouseIntelligentDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicHouseIntelligent getBasicHouseIntelligentById(Integer id) throws Exception {
        return basicHouseIntelligentDao.getBasicHouseIntelligentById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouseIntelligent
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouseIntelligent(BasicHouseIntelligent basicHouseIntelligent) throws Exception {
        if (basicHouseIntelligent.getId() == null || basicHouseIntelligent.getId().intValue() == 0) {
            basicHouseIntelligent.setCreator(commonService.thisUserAccount());
            Integer id = basicHouseIntelligentDao.saveBasicHouseIntelligent(basicHouseIntelligent);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class), id);
            return id;
        } else {
            BasicHouseIntelligent oo = basicHouseIntelligentDao.getBasicHouseIntelligentById(basicHouseIntelligent.getId());
            basicHouseIntelligentDao.updateBasicHouseIntelligent(basicHouseIntelligent);
            return null;
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicHouseIntelligent(Integer id) throws Exception {
        return basicHouseIntelligentDao.deleteBasicHouseIntelligent(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicHouseIntelligent
     * @return
     * @throws Exception
     */
    public List<BasicHouseIntelligent> basicHouseIntelligentList(BasicHouseIntelligent basicHouseIntelligent) throws Exception {
        return basicHouseIntelligentDao.basicHouseIntelligentList(basicHouseIntelligent);
    }

    public void removeBasicHouseIntelligent(BasicHouseIntelligent basicHouseIntelligent) throws Exception {
        basicHouseIntelligentDao.removeBasicHouseIntelligent(basicHouseIntelligent);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicHouseIntelligent basicHouseIntelligent) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseIntelligent> basicHouseIntelligentList = basicHouseIntelligentDao.basicHouseIntelligentList(basicHouseIntelligent);
        List<BasicHouseIntelligentVo> vos = Lists.newArrayList();
        basicHouseIntelligentList.forEach(oo -> vos.add(getBasicHouseIntelligentVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicHouseIntelligentVo>(10) : vos);
        return vo;
    }

    public BasicHouseIntelligentVo getBasicHouseIntelligentVo(BasicHouseIntelligent basicHouseIntelligent) {
        if (basicHouseIntelligent == null) {
            return null;
        }
        BasicHouseIntelligentVo vo = new BasicHouseIntelligentVo();
        BeanUtils.copyProperties(basicHouseIntelligent, vo);
        vo.setSwitchCircuitName(baseDataDicService.getNameById(basicHouseIntelligent.getSwitchCircuit()));
        vo.setLayingMethodName(baseDataDicService.getNameById(basicHouseIntelligent.getLayingMethod()));
        if (StringUtils.isNotBlank(basicHouseIntelligent.getLampsLanterns())) {
            String[] strings = basicHouseIntelligent.getLampsLanterns().split(",");
            if (strings.length >= 1) {
                StringBuilder builder = new StringBuilder(128);
                for (int i = 0; i < strings.length; i++) {
                    if (NumberUtils.isNumber(strings[i])) {
                        if (i == strings.length - 1) {
                            builder.append(baseDataDicService.getNameById(Integer.parseInt(strings[i])));
                        } else {
                            builder.append(baseDataDicService.getNameById(Integer.parseInt(strings[i]))).append(",");
                        }
                    }
                }
                vo.setLampsLanternsName(builder.toString());
            }
        }
        if (StringUtils.isNotBlank(basicHouseIntelligent.getIntelligentSystem())) {
            try {
                List<String> stringList = JSONObject.parseArray(basicHouseIntelligent.getIntelligentSystem(), String.class);
                StringBuilder builder = new StringBuilder(128);
                if (!ObjectUtils.isEmpty(stringList)) {
                    for (int i = 0; i < stringList.size(); i++) {
                        if (i == stringList.size() - 1) {
                            JSONObject jsonObject = JSON.parseObject(stringList.get(i));
                            JSONObject intelligentSystem = JSON.parseObject(jsonObject.getString("intelligentSystem"));
                            JSONObject layingMethod = JSON.parseObject(jsonObject.getString("layingMethod"));
                            String intelligentValue = intelligentSystem.getString("value");
                            String layingMethodValue = layingMethod.getString("value");
                            if (NumberUtils.isNumber(intelligentValue) && NumberUtils.isNumber(layingMethodValue)) {
                                builder.append("(").append(baseDataDicService.getNameById(Integer.parseInt(intelligentValue))).append("");
                                builder.append("-");
                                builder.append("").append(baseDataDicService.getNameById(Integer.parseInt(layingMethodValue))).append(")");
                            }
                        } else {
                            JSONObject jsonObject = JSON.parseObject(stringList.get(i));
                            JSONObject intelligentSystem = JSON.parseObject(jsonObject.getString("intelligentSystem"));
                            JSONObject layingMethod = JSON.parseObject(jsonObject.getString("layingMethod"));
                            String intelligentValue = intelligentSystem.getString("value");
                            String layingMethodValue = layingMethod.getString("value");
                            if (NumberUtils.isNumber(intelligentValue) && NumberUtils.isNumber(layingMethodValue)) {
                                builder.append("(").append(baseDataDicService.getNameById(Integer.parseInt(intelligentValue))).append("");
                                builder.append("-");
                                builder.append("").append(baseDataDicService.getNameById(Integer.parseInt(layingMethodValue))).append(")");
                                builder.append(",");
                            }
                        }
                    }
                }
                vo.setIntelligentSystemName(builder.toString());
            } catch (Exception e1) {
                logger.error("json异常!", e1);
            }
        }
        return vo;
    }

}
