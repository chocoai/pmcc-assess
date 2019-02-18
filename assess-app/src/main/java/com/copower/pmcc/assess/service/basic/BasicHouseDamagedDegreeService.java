package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.DataDamagedDegreeEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseDamagedDegreeDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseDamagedDegreeDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseDamagedDegree;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseDamagedDegreeDetail;
import com.copower.pmcc.assess.dal.basis.entity.DataDamagedDegree;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseDamagedDegreeDetailVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseDamagedDegreeVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataDamagedDegreeService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/2 09:59
 * @Description:房间
 */
@Service
public class BasicHouseDamagedDegreeService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataDamagedDegreeService dataDamagedDegreeService;
    @Autowired
    private BasicHouseDamagedDegreeDao basicHouseDamagedDegreeDao;
    @Autowired
    private BasicHouseDamagedDegreeDetailDao basicHouseDamagedDegreeDetailDao;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicHouseDamagedDegree getDamagedDegreeById(Integer id) throws Exception {
        return basicHouseDamagedDegreeDao.getBasicHouseDamagedDegreeById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouseDamagedDegree
     * @return
     * @throws Exception
     */
    public void saveAndUpdateDamagedDegree(BasicHouseDamagedDegree basicHouseDamagedDegree) throws Exception {
        if (basicHouseDamagedDegree.getId() == null || basicHouseDamagedDegree.getId().intValue() == 0) {
            basicHouseDamagedDegree.setCreator(commonService.thisUserAccount());
            basicHouseDamagedDegreeDao.saveBasicHouseDamagedDegree(basicHouseDamagedDegree);
        } else {
            basicHouseDamagedDegreeDao.updateBasicHouseDamagedDegree(basicHouseDamagedDegree);
        }
    }

    public List<BasicHouseDamagedDegree> getDamagedDegreeList(Integer houseId) {
        BasicHouseDamagedDegree basicHouseDamagedDegree = new BasicHouseDamagedDegree();
        basicHouseDamagedDegree.setHouseId(houseId);
        List<BasicHouseDamagedDegree> list = basicHouseDamagedDegreeDao.getDamagedDegreeList(basicHouseDamagedDegree);
        return list;
    }

    public List<BasicHouseDamagedDegree> getDamagedDegreeList(Integer houseId, Integer type) {
        BasicHouseDamagedDegree basicHouseDamagedDegree = new BasicHouseDamagedDegree();
        basicHouseDamagedDegree.setHouseId(houseId);
        basicHouseDamagedDegree.setType(type);
        List<BasicHouseDamagedDegree> list = basicHouseDamagedDegreeDao.getValueDamagedDegreeList(houseId, type);
        return list;
    }

    public List<BasicHouseDamagedDegreeVo> getDamagedDegreeVoList(Integer houseId, Integer type) {
        List<BasicHouseDamagedDegree> list = getDamagedDegreeList(houseId, type);
        List<BasicHouseDamagedDegreeVo> vos = LangUtils.transform(list, o -> getBasicHouseDamagedDegreeVo(o));
        return vos;
    }

    /**
     * @param houseId
     * @return
     */
    public List<BasicHouseDamagedDegreeVo> getDamagedDegreeVoList(Integer houseId) {
        List<BasicHouseDamagedDegree> list = getDamagedDegreeList(houseId);
        List<BasicHouseDamagedDegreeVo> vos = LangUtils.transform(list, o -> getBasicHouseDamagedDegreeVo(o));
        return vos;
    }

    /**
     * 删除数据
     *
     * @param basicHouseDamagedDegree
     * @return
     */
    public Boolean deleteDamagedDegree(BasicHouseDamagedDegree basicHouseDamagedDegree) {
        return basicHouseDamagedDegreeDao.deleteBasicHouseDamagedDegree(basicHouseDamagedDegree);
    }

    public BasicHouseDamagedDegreeVo getBasicHouseDamagedDegreeVo(BasicHouseDamagedDegree basicHouseDamagedDegree) {
        DataDamagedDegree degree = dataDamagedDegreeService.getDamagedDegreeById(basicHouseDamagedDegree.getType());
        BasicHouseDamagedDegreeVo basicHouseDamagedDegreeVo = new BasicHouseDamagedDegreeVo();
        BeanUtils.copyProperties(basicHouseDamagedDegree, basicHouseDamagedDegreeVo);
        basicHouseDamagedDegreeVo.setTypeName(dataDamagedDegreeService.getNameById(basicHouseDamagedDegree.getType()));
        DataDamagedDegree dataDamagedDegree = dataDamagedDegreeService.getCacheDamagedDegreeById(basicHouseDamagedDegree.getCategory());
        if (dataDamagedDegree != null) {
            basicHouseDamagedDegreeVo.setCategoryName(dataDamagedDegree.getName());
            basicHouseDamagedDegreeVo.setStandardScore(dataDamagedDegree.getStandardScore());
            basicHouseDamagedDegreeVo.setWeight(degree.getWeight());
            basicHouseDamagedDegreeVo.setIntact(dataDamagedDegree.getIntact());
            basicHouseDamagedDegreeVo.setBasicallyIntact(dataDamagedDegree.getBasicallyIntact());
            basicHouseDamagedDegreeVo.setGeneralDamage(dataDamagedDegree.getGeneralDamage());
            basicHouseDamagedDegreeVo.setSeriousDamage(dataDamagedDegree.getSeriousDamage());
            basicHouseDamagedDegreeVo.setHasChildren(!CollectionUtils.isEmpty(dataDamagedDegreeService.getCacheDamagedDegreeListByPid(dataDamagedDegree.getId())));
        }
        if (!StringUtils.isEmpty(basicHouseDamagedDegree.getEntityCondition()))
            basicHouseDamagedDegreeVo.setEntityConditionName(DataDamagedDegreeEnum.getEnumByKey(basicHouseDamagedDegree.getEntityCondition()).getValue());
        return basicHouseDamagedDegreeVo;
    }

    //明细项数据处理-----------------------------------------------------------------------------------------------------

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicHouseDamagedDegreeDetail getDamagedDegreeDetailById(Integer id) throws Exception {
        return basicHouseDamagedDegreeDetailDao.getBasicHouseDamagedDegreeDetailById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouseDamagedDegreeDetail
     * @return
     * @throws Exception
     */
    public void saveAndUpdateDamagedDegreeDetail(BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail) throws Exception {
        if (basicHouseDamagedDegreeDetail.getId() == null || basicHouseDamagedDegreeDetail.getId().intValue() == 0) {
            basicHouseDamagedDegreeDetail.setCreator(commonService.thisUserAccount());
            basicHouseDamagedDegreeDetailDao.saveBasicHouseDamagedDegreeDetail(basicHouseDamagedDegreeDetail);
        } else {
            basicHouseDamagedDegreeDetailDao.updateBasicHouseDamagedDegreeDetail(basicHouseDamagedDegreeDetail);
        }
    }

    /**
     * 删除数据
     *
     * @param basicHouseDamagedDegreeDetail
     * @return
     */
    public Boolean deleteDamagedDegreeDetail(BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail) {
        return basicHouseDamagedDegreeDetailDao.deleteBasicHouseDamagedDegreeDetail(basicHouseDamagedDegreeDetail);
    }

    public Boolean deleteDamagedDegreeDetail(Integer id) {
        return basicHouseDamagedDegreeDetailDao.deleteBasicHouseDamagedDegreeDetail(id);
    }

    public BasicHouseDamagedDegreeDetailVo getBasicHouseDamagedDegreeDetailVo(BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail) {
        BasicHouseDamagedDegreeDetailVo basicHouseDamagedDegreeDetailVo = new BasicHouseDamagedDegreeDetailVo();
        BeanUtils.copyProperties(basicHouseDamagedDegreeDetail, basicHouseDamagedDegreeDetailVo);
        DataDamagedDegree dataDamagedDegree = dataDamagedDegreeService.getCacheDamagedDegreeById(basicHouseDamagedDegreeDetail.getType());
        if (dataDamagedDegree != null) {
            basicHouseDamagedDegreeDetailVo.setTypeName(dataDamagedDegree.getName());
            basicHouseDamagedDegreeDetailVo.setStandardScore(dataDamagedDegree.getStandardScore());
            basicHouseDamagedDegreeDetailVo.setIntact(dataDamagedDegree.getIntact());
            basicHouseDamagedDegreeDetailVo.setBasicallyIntact(dataDamagedDegree.getBasicallyIntact());
            basicHouseDamagedDegreeDetailVo.setGeneralDamage(dataDamagedDegree.getGeneralDamage());
            basicHouseDamagedDegreeDetailVo.setSeriousDamage(dataDamagedDegree.getSeriousDamage());
        }
        if (!StringUtils.isEmpty(basicHouseDamagedDegreeDetail.getEntityCondition()))
            basicHouseDamagedDegreeDetailVo.setEntityConditionName(DataDamagedDegreeEnum.getEnumByKey(basicHouseDamagedDegreeDetail.getEntityCondition()).getValue());
        return basicHouseDamagedDegreeDetailVo;
    }

    public List<BasicHouseDamagedDegreeDetail> getDamagedDegreeDetails(Integer damagedDegreeId) {
        BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail = new BasicHouseDamagedDegreeDetail();
        basicHouseDamagedDegreeDetail.setDamagedDegreeId(damagedDegreeId);
        List<BasicHouseDamagedDegreeDetail> degreeDetailList = basicHouseDamagedDegreeDetailDao.getDamagedDegreeDetailList(basicHouseDamagedDegreeDetail);
        return degreeDetailList;
    }

    public BootstrapTableVo getDamagedDegreeDetailList(Integer damagedDegreeId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail = new BasicHouseDamagedDegreeDetail();
        basicHouseDamagedDegreeDetail.setDamagedDegreeId(damagedDegreeId);
        List<BasicHouseDamagedDegreeDetail> degreeDetailList = basicHouseDamagedDegreeDetailDao.getDamagedDegreeDetailList(basicHouseDamagedDegreeDetail);
        List<BasicHouseDamagedDegreeDetailVo> vos = LangUtils.transform(degreeDetailList, o -> getBasicHouseDamagedDegreeDetailVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? Lists.newArrayList() : vos);
        return vo;
    }

    /**
     * 根据查询条件判断是否有数据
     *
     * @param houseId
     * @return
     */
    public boolean hasHouseDamagedDegreeData(Integer houseId, String type) {
        DataDamagedDegree degree = dataDamagedDegreeService.getCacheDamagedDegreeByFieldName(type);
        return basicHouseDamagedDegreeDao.countByHouseId(houseId, degree.getId()) > 0;
    }


    public boolean hasOppositeDetail(Integer houseId, Integer index) {
        List<BasicHouseDamagedDegree> list = getDamagedDegreeList(houseId);
        if (StringUtils.isEmpty(list.get(index).getEntityCondition())) {
            return false;
        }
        return true;
    }
}
