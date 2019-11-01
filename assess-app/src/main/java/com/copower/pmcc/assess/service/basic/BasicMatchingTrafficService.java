package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicMatchingTrafficDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingTraffic;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingTrafficVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
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
 * @Date: 2018/11/6 11:17
 * @Description:交通条件
 */
@Service
public class BasicMatchingTrafficService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicMatchingTrafficDao basicMatchingTrafficDao;
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
    public BasicMatchingTraffic getBasicMatchingTrafficById(Integer id) throws Exception {
        return basicMatchingTrafficDao.getBasicMatchingTrafficById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicMatchingTraffic
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicMatchingTraffic(BasicMatchingTraffic basicMatchingTraffic, boolean updateNull) throws Exception {
        if (basicMatchingTraffic.getId() == null || basicMatchingTraffic.getId().intValue() == 0) {
            basicMatchingTraffic.setCreator(commonService.thisUserAccount());
            Integer id = basicMatchingTrafficDao.addBasicMatchingTraffic(basicMatchingTraffic);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicMatchingTraffic.class), id);
            return  id ;
        } else {
            if(updateNull){
                BasicMatchingTraffic matchingTraffic = basicMatchingTrafficDao.getBasicMatchingTrafficById(basicMatchingTraffic.getId());
                if(matchingTraffic!=null){
                    basicMatchingTraffic.setCreator(matchingTraffic.getCreator());
                    basicMatchingTraffic.setGmtCreated(matchingTraffic.getGmtCreated());
                }
            }
            basicMatchingTrafficDao.updateBasicMatchingTraffic(basicMatchingTraffic,updateNull);
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
    public boolean deleteBasicMatchingTraffic(Integer id) throws Exception {
        return basicMatchingTrafficDao.deleteBasicMatchingTraffic(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicMatchingTraffic
     * @return
     * @throws Exception
     */
    public List<BasicMatchingTraffic> basicMatchingTrafficList(BasicMatchingTraffic basicMatchingTraffic) throws Exception {
        return basicMatchingTrafficDao.basicMatchingTrafficList(basicMatchingTraffic);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicMatchingTraffic basicMatchingTraffic) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicMatchingTraffic> basicMatchingTrafficList = basicMatchingTrafficDao.basicMatchingTrafficList(basicMatchingTraffic);
        List<BasicMatchingTrafficVo> vos = Lists.newArrayList();
        basicMatchingTrafficList.forEach(oo -> vos.add(getBasicMatchingTrafficVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicMatchingTrafficVo>(10) : vos);
        return vo;
    }

    public List<BasicMatchingTrafficVo> getBasicMatchingTrafficVos(Integer estateId){
        BasicMatchingTraffic where=new BasicMatchingTraffic();
        where.setEstateId(estateId);
        return LangUtils.transform(basicMatchingTrafficDao.basicMatchingTrafficList(where),o->getBasicMatchingTrafficVo(o));
    }

    public BasicMatchingTrafficVo getBasicMatchingTrafficVo(BasicMatchingTraffic basicMatchingTraffic){
        if (basicMatchingTraffic==null){
            return null;
        }
        BasicMatchingTrafficVo vo = new BasicMatchingTrafficVo();
        BeanUtils.copyProperties(basicMatchingTraffic,vo);
        vo.setDistanceName(baseDataDicService.getNameById(basicMatchingTraffic.getDistance()));
        vo.setNatureName(baseDataDicService.getNameById(basicMatchingTraffic.getNature()));
        vo.setLimitSpeialName(baseDataDicService.getNameById(basicMatchingTraffic.getLimitSpeial()));
        vo.setTrafficFlowName(baseDataDicService.getNameById(basicMatchingTraffic.getTrafficFlow()));
        vo.setPositionName(baseDataDicService.getNameById(basicMatchingTraffic.getPosition()));
        vo.setVisitorsFlowrateName(baseDataDicService.getNameById(basicMatchingTraffic.getVisitorsFlowrate()));
        if (basicMatchingTraffic.getFlag() != null){
            if (basicMatchingTraffic.getFlag()){
                vo.setFlagName("是");
            }else {
                vo.setFlagName("否");
            }
        }else {
            vo.setFlagName("无");
        }
        return vo;
    }

    public void remove(String id){
        List<Integer> ids = new ArrayList<Integer>(10) ;
        if (StringUtils.isNotBlank(id)){
            for (String str :id.split(",")){
                if (org.apache.commons.lang3.math.NumberUtils.isNumber(str)){
                    ids.add(Integer.parseInt(str));
                }
            }
            basicMatchingTrafficDao.remove(ids);
        }
    }
    
}
