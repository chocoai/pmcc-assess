package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateSupplyDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateSupply;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateSupplyVo;
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
 * @Date: 2018/11/6 10:54
 * @Description:供应信息
 */
@Service
public class BasicEstateSupplyService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicEstateSupplyDao basicEstateSupplyDao;
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
    public BasicEstateSupply getBasicEstateSupplyById(Integer id) throws Exception {
        return basicEstateSupplyDao.getBasicEstateSupplyById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicEstateSupply
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicEstateSupply(BasicEstateSupply basicEstateSupply, boolean updateNull) throws Exception {
        if (basicEstateSupply.getId() == null || basicEstateSupply.getId().intValue() == 0) {
            basicEstateSupply.setCreator(commonService.thisUserAccount());
            Integer id = basicEstateSupplyDao.saveBasicEstateSupply(basicEstateSupply);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicEstateSupply.class), id);
            return  id ;
        } else {
            if(updateNull){
                BasicEstateSupply estateSupply = basicEstateSupplyDao.getBasicEstateSupplyById(basicEstateSupply.getId());
                if(estateSupply!=null){
                    basicEstateSupply.setCreator(estateSupply.getCreator());
                    basicEstateSupply.setGmtCreated(estateSupply.getGmtCreated());
                }
            }
            basicEstateSupplyDao.updateBasicEstateSupply(basicEstateSupply,updateNull);
            return basicEstateSupply.getId();
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicEstateSupply(Integer id) throws Exception {
        return basicEstateSupplyDao.deleteBasicEstateSupply(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicEstateSupply
     * @return
     * @throws Exception
     */
    public List<BasicEstateSupply> basicEstateSupplyList(BasicEstateSupply basicEstateSupply) throws Exception {
        return basicEstateSupplyDao.basicEstateSupplyList(basicEstateSupply);
    }

    public void removeBasicEstateSupply(BasicEstateSupply basicEstateSupply)throws Exception{
        basicEstateSupplyDao.removeBasicEstateSupply(basicEstateSupply);
    }

    public List<BasicEstateSupply> getBasicEstateSupplyList(Integer estateId){
        BasicEstateSupply where=new BasicEstateSupply();
        where.setEstateId(estateId);
        return basicEstateSupplyDao.basicEstateSupplyList(where);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicEstateSupply basicEstateSupply) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstateSupply> basicEstateSupplyList = basicEstateSupplyDao.basicEstateSupplyList(basicEstateSupply);
        List<BasicEstateSupplyVo> vos = Lists.newArrayList();
        basicEstateSupplyList.forEach(oo -> vos.add(getBasicEstateSupplyVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicEstateSupplyVo>(10) : vos);
        return vo;
    }

    public BasicEstateSupplyVo getBasicEstateSupplyVo(BasicEstateSupply basicEstateSupply){
        if (basicEstateSupply==null){
            return null;
        }
        BasicEstateSupplyVo vo = new BasicEstateSupplyVo();
        BeanUtils.copyProperties(basicEstateSupply,vo);
        vo.setReputationName(baseDataDicService.getNameById(basicEstateSupply.getReputation()));
        vo.setLineGradeName(baseDataDicService.getNameById(basicEstateSupply.getLineGrade()));
        vo.setGradeName(baseDataDicService.getNameById(basicEstateSupply.getGrade()));
        return vo;
    }
    
}
