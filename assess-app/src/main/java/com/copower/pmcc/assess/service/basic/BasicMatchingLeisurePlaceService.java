package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicMatchingLeisurePlaceDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicMatchingLeisurePlace;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingLeisurePlaceVo;
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
 * @Date: 2018/11/6 11:08
 * @Description:
 */
@Service
public class BasicMatchingLeisurePlaceService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicMatchingLeisurePlaceDao basicMatchingLeisurePlaceDao;
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
    public BasicMatchingLeisurePlace getBasicMatchingLeisurePlaceById(Integer id) throws Exception {
        return basicMatchingLeisurePlaceDao.getBasicMatchingLeisurePlaceById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicMatchingLeisurePlace
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicMatchingLeisurePlace(BasicMatchingLeisurePlace basicMatchingLeisurePlace) throws Exception {
        if (basicMatchingLeisurePlace.getId() == null || basicMatchingLeisurePlace.getId().intValue() == 0) {
            basicMatchingLeisurePlace.setCreator(commonService.thisUserAccount());
            Integer id = basicMatchingLeisurePlaceDao.saveBasicMatchingLeisurePlace(basicMatchingLeisurePlace);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicMatchingLeisurePlace.class), id);
            return  id ;
        } else {
            BasicMatchingLeisurePlace oo = basicMatchingLeisurePlaceDao.getBasicMatchingLeisurePlaceById(basicMatchingLeisurePlace.getId());
            basicMatchingLeisurePlaceDao.updateBasicMatchingLeisurePlace(basicMatchingLeisurePlace);
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
    public boolean deleteBasicMatchingLeisurePlace(Integer id) throws Exception {
        return basicMatchingLeisurePlaceDao.deleteBasicMatchingLeisurePlace(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicMatchingLeisurePlace
     * @return
     * @throws Exception
     */
    public List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList(BasicMatchingLeisurePlace basicMatchingLeisurePlace) throws Exception {
        return basicMatchingLeisurePlaceDao.basicMatchingLeisurePlaceList(basicMatchingLeisurePlace);
    }

    public void removeBasicMatchingLeisurePlace(BasicMatchingLeisurePlace basicMatchingLeisurePlace)throws Exception{
        basicMatchingLeisurePlaceDao.removeBasicMatchingLeisurePlace(basicMatchingLeisurePlace);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicMatchingLeisurePlace basicMatchingLeisurePlace) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicMatchingLeisurePlace> basicMatchingLeisurePlaceList = basicMatchingLeisurePlaceDao.basicMatchingLeisurePlaceList(basicMatchingLeisurePlace);
        List<BasicMatchingLeisurePlaceVo> vos = Lists.newArrayList();
        basicMatchingLeisurePlaceList.forEach(oo -> vos.add(getBasicMatchingLeisurePlaceVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicMatchingLeisurePlaceVo>(10) : vos);
        return vo;
    }

    public BasicMatchingLeisurePlaceVo getBasicMatchingLeisurePlaceVo(BasicMatchingLeisurePlace basicMatchingLeisurePlace){
        if (basicMatchingLeisurePlace==null){
            return null;
        }
        BasicMatchingLeisurePlaceVo vo = new BasicMatchingLeisurePlaceVo();
        BeanUtils.copyProperties(basicMatchingLeisurePlace,vo);
        BaseDataDic dataDic = null;

        return vo;
    }
    
}
