package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basic.dao.BasicEstateLandStateDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateLandState;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicEstateLandStateService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicEstateLandStateDao basicEstateLandStateDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     * @param id
     * @return
     * @throws Exception
     */
    public BasicEstateLandState getBasicEstateLandStateById(Integer id)throws Exception{
        return basicEstateLandStateDao.getBasicEstateLandStateById(id);
    }

    /**
     * 新增或者修改
     * @param basicEstateLandState
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicEstateLandState(BasicEstateLandState basicEstateLandState)throws Exception{
        if (basicEstateLandState.getId()== null || basicEstateLandState.getId().intValue()==0){
            basicEstateLandState.setCreator(commonService.thisUserAccount());
            if (basicEstateLandState.getVersion() == null){
                basicEstateLandState.setVersion(0);
            }
            return basicEstateLandStateDao.saveBasicEstateLandState(basicEstateLandState);
        }else {
            BasicEstateLandState oo = basicEstateLandStateDao.getBasicEstateLandStateById(basicEstateLandState.getId());
            basicEstateLandState.setVersion(oo.getVersion()+1);
            basicEstateLandStateDao.updateBasicEstateLandState(basicEstateLandState);
            return  null;
        }
    }

    /**
     * 删除数据
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicEstateLandState(Integer id)throws Exception{
        return basicEstateLandStateDao.deleteBasicEstateLandState(id);
    }

    /**
     * 获取数据列表
     * @param basicEstateLandState
     * @return
     * @throws Exception
     */
    public List<BasicEstateLandState> basicEstateLandStateList(BasicEstateLandState basicEstateLandState)throws Exception{
        return basicEstateLandStateDao.basicEstateLandStateList(basicEstateLandState);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicEstateLandState basicEstateLandState)throws Exception{
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstateLandState> basicEstateLandStateList = basicEstateLandStateDao.basicEstateLandStateList(basicEstateLandState);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicEstateLandStateList)?new ArrayList<BasicEstateLandState>(10):basicEstateLandStateList);
        return vo;
    }

}
