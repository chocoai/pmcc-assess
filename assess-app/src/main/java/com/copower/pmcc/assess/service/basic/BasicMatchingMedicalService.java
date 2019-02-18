package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicMatchingMedicalDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingMedical;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingMedicalVo;
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
 * @Date: 2018/11/6 11:14
 * @Description:医养条件
 */
@Service
public class BasicMatchingMedicalService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicMatchingMedicalDao basicMatchingMedicalDao;
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
    public BasicMatchingMedical getBasicMatchingMedicalById(Integer id) throws Exception {
        return basicMatchingMedicalDao.getBasicMatchingMedicalById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicMatchingMedical
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicMatchingMedical(BasicMatchingMedical basicMatchingMedical) throws Exception {
        if (basicMatchingMedical.getId() == null || basicMatchingMedical.getId().intValue() == 0) {
            basicMatchingMedical.setCreator(commonService.thisUserAccount());
            Integer id = basicMatchingMedicalDao.saveBasicMatchingMedical(basicMatchingMedical);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicMatchingMedical.class), id);
            return  id ;
        } else {
            BasicMatchingMedical oo = basicMatchingMedicalDao.getBasicMatchingMedicalById(basicMatchingMedical.getId());
            basicMatchingMedicalDao.updateBasicMatchingMedical(basicMatchingMedical);
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
    public boolean deleteBasicMatchingMedical(Integer id) throws Exception {
        return basicMatchingMedicalDao.deleteBasicMatchingMedical(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicMatchingMedical
     * @return
     * @throws Exception
     */
    public List<BasicMatchingMedical> basicMatchingMedicalList(BasicMatchingMedical basicMatchingMedical) throws Exception {
        return basicMatchingMedicalDao.basicMatchingMedicalList(basicMatchingMedical);
    }

    public void removeBasicMatchingMedical(BasicMatchingMedical basicMatchingMedical)throws Exception{
        basicMatchingMedicalDao.removeBasicMatchingMedical(basicMatchingMedical);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicMatchingMedical basicMatchingMedical) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicMatchingMedical> basicMatchingMedicalList = basicMatchingMedicalDao.basicMatchingMedicalList(basicMatchingMedical);
        List<BasicMatchingMedicalVo> vos = Lists.newArrayList();
        basicMatchingMedicalList.forEach(oo -> vos.add(getBasicMatchingMedicalVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicMatchingMedicalVo>(10) : vos);
        return vo;
    }

    public List<BasicMatchingMedical> getBasicMatchingMedicalList(Integer estateId){
        BasicMatchingMedical where=new BasicMatchingMedical();
        where.setEstateId(estateId);
        List<BasicMatchingMedical> basicMatchingMedicalList = basicMatchingMedicalDao.basicMatchingMedicalList(where);
        return basicMatchingMedicalList;
    }

    public BasicMatchingMedicalVo getBasicMatchingMedicalVo(BasicMatchingMedical basicMatchingMedical){
        if (basicMatchingMedical==null){
            return null;
        }
        BasicMatchingMedicalVo vo = new BasicMatchingMedicalVo();
        BeanUtils.copyProperties(basicMatchingMedical,vo);
        vo.setOrganizationLevelName(baseDataDicService.getNameById(basicMatchingMedical.getOrganizationLevel()));
        vo.setBedNumberName(baseDataDicService.getNameById(basicMatchingMedical.getBedNumber()));
        vo.setDistanceName(baseDataDicService.getNameById(basicMatchingMedical.getDistance()));
        return vo;
    }

    public void removeIds(String str){
        if (StringUtils.isNotBlank(str)){
            List<Integer> ids = new ArrayList<>(10) ;
            for (String id:str.split(",")){
                if (NumberUtils.isNumber(id)){
                    ids.add(Integer.parseInt(id));
                }
            }
            basicMatchingMedicalDao.removeIds(ids);
        }
    }
    
}
