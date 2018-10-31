package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicBuildingSurfaceDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingSurface;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingSurfaceVo;
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
 * @Date: 2018/10/30 11:52
 * @Description:
 */
@Service
public class BasicBuildingSurfaceService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicBuildingSurfaceDao basicBuildingSurfaceDao;
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
    public BasicBuildingSurface getBasicBuildingSurfaceById(Integer id) throws Exception {
        return basicBuildingSurfaceDao.getBasicBuildingSurfaceById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicBuildingSurface
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicBuildingSurface(BasicBuildingSurface basicBuildingSurface) throws Exception {
        if (basicBuildingSurface.getId() == null || basicBuildingSurface.getId().intValue() == 0) {
            basicBuildingSurface.setCreator(commonService.thisUserAccount());
            Integer id = basicBuildingSurfaceDao.saveBasicBuildingSurface(basicBuildingSurface);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicBuildingSurface.class), id);
            return  id ;
        } else {
            BasicBuildingSurface oo = basicBuildingSurfaceDao.getBasicBuildingSurfaceById(basicBuildingSurface.getId());
            basicBuildingSurfaceDao.updateBasicBuildingSurface(basicBuildingSurface);
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
    public boolean deleteBasicBuildingSurface(Integer id) throws Exception {
        return basicBuildingSurfaceDao.deleteBasicBuildingSurface(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicBuildingSurface
     * @return
     * @throws Exception
     */
    public List<BasicBuildingSurface> basicBuildingSurfaceList(BasicBuildingSurface basicBuildingSurface) throws Exception {
        return basicBuildingSurfaceDao.basicBuildingSurfaceList(basicBuildingSurface);
    }

    public void removeBasicBuildingSurface(BasicBuildingSurface basicBuildingSurface)throws Exception{
        basicBuildingSurfaceDao.removeBasicBuildingSurface(basicBuildingSurface);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicBuildingSurface basicBuildingSurface) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicBuildingSurface> basicBuildingSurfaceList = basicBuildingSurfaceDao.basicBuildingSurfaceList(basicBuildingSurface);
        List<BasicBuildingSurfaceVo> vos = Lists.newArrayList();
        basicBuildingSurfaceList.forEach(oo -> vos.add(getBasicBuildingSurfaceVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicBuildingSurfaceVo>(10) : vos);
        return vo;
    }

    public BasicBuildingSurfaceVo getBasicBuildingSurfaceVo(BasicBuildingSurface basicBuildingSurface){
        if (basicBuildingSurface==null){
            return null;
        }
        BasicBuildingSurfaceVo vo = new BasicBuildingSurfaceVo();
        BeanUtils.copyProperties(basicBuildingSurface,vo);
        BaseDataDic dataDic = null;
        if (basicBuildingSurface.getStructure() != null){
            dataDic = baseDataDicService.getDataDicById(basicBuildingSurface.getStructure());
            if (dataDic != null){
                vo.setStructureName(dataDic.getName());
                dataDic = null;
            }
        }
        return vo;
    }
    
}
