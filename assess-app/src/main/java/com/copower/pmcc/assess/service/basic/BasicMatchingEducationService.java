package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicMatchingEducationDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingEducation;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingEducationVo;
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
 * @Date: 2018/11/6 10:58
 * @Description:教育条件
 */
@Service
public class BasicMatchingEducationService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicMatchingEducationDao basicMatchingEducationDao;
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
    public BasicMatchingEducation getBasicMatchingEducationById(Integer id) throws Exception {
        return basicMatchingEducationDao.getBasicMatchingEducationById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicMatchingEducation
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicMatchingEducation(BasicMatchingEducation basicMatchingEducation) throws Exception {
        if (basicMatchingEducation.getId() == null || basicMatchingEducation.getId().intValue() == 0) {
            basicMatchingEducation.setCreator(commonService.thisUserAccount());
            Integer id = basicMatchingEducationDao.saveBasicMatchingEducation(basicMatchingEducation);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicMatchingEducation.class), id);
            return  id ;
        } else {
            BasicMatchingEducation oo = basicMatchingEducationDao.getBasicMatchingEducationById(basicMatchingEducation.getId());
            basicMatchingEducationDao.updateBasicMatchingEducation(basicMatchingEducation);
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
    public boolean deleteBasicMatchingEducation(Integer id) throws Exception {
        return basicMatchingEducationDao.deleteBasicMatchingEducation(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicMatchingEducation
     * @return
     * @throws Exception
     */
    public List<BasicMatchingEducation> basicMatchingEducationList(BasicMatchingEducation basicMatchingEducation) throws Exception {
        return basicMatchingEducationDao.basicMatchingEducationList(basicMatchingEducation);
    }

    public void removeBasicMatchingEducation(BasicMatchingEducation basicMatchingEducation)throws Exception{
        basicMatchingEducationDao.removeBasicMatchingEducation(basicMatchingEducation);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicMatchingEducation basicMatchingEducation) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicMatchingEducation> basicMatchingEducationList = basicMatchingEducationDao.basicMatchingEducationList(basicMatchingEducation);
        List<BasicMatchingEducationVo> vos = Lists.newArrayList();
        basicMatchingEducationList.forEach(oo -> vos.add(getBasicMatchingEducationVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicMatchingEducationVo>(10) : vos);
        return vo;
    }

    public List<BasicMatchingEducation> getBasicMatchingEducationList(Integer estateId){
        BasicMatchingEducation where=new BasicMatchingEducation();
        where.setEstateId(estateId);
        return basicMatchingEducationDao.basicMatchingEducationList(where);
    }

    public BasicMatchingEducationVo getBasicMatchingEducationVo(BasicMatchingEducation basicMatchingEducation){
        if (basicMatchingEducation==null){
            return null;
        }
        BasicMatchingEducationVo vo = new BasicMatchingEducationVo();
        BeanUtils.copyProperties(basicMatchingEducation,vo);
        vo.setDistanceName(baseDataDicService.getNameById(basicMatchingEducation.getDistance()));
        vo.setSchoolNatureName(baseDataDicService.getNameById(basicMatchingEducation.getSchoolNature()));
        vo.setSchoolGradationName(baseDataDicService.getNameById(basicMatchingEducation.getSchoolGradation()));
        vo.setSchoolLevelName(baseDataDicService.getNameById(NumberUtils.isNumber(basicMatchingEducation.getSchoolLevel())?Integer.parseInt(basicMatchingEducation.getSchoolLevel()):null));
        return vo;
    }

    public void removeIds(String str){
        if (StringUtils.isNotBlank(str)){
            List<Integer> ids = new ArrayList<>(10);
            for (String id:str.split(",")){
                if (NumberUtils.isNumber(id)){
                    ids.add(Integer.parseInt(id)) ;
                }
            }
            basicMatchingEducationDao.removeIds(ids);
        }
    }
    
}
