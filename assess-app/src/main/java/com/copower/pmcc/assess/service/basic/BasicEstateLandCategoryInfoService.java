package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateLandCategoryInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandCategoryInfo;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandState;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/30 11:52
 * @Description:土地 土地实体类型信息
 */
@Service
public class BasicEstateLandCategoryInfoService {
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicEstateLandCategoryInfoDao basicEstateLandCategoryInfoDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicApplyDao basicApplyDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void copy(Integer oldId, Integer newId)throws Exception {
        if (oldId == null) {
            return;
        }
        if (newId == null) {
            return;
        }
        BasicEstateLandCategoryInfo query = new BasicEstateLandCategoryInfo();
        query.setLandId(oldId);
        List<BasicEstateLandCategoryInfo> basicEstateLandCategoryInfoList = basicEstateLandCategoryInfoList(query);
        if (CollectionUtils.isEmpty(basicEstateLandCategoryInfoList)){
            return;
        }
        Iterator<BasicEstateLandCategoryInfo> iterator = basicEstateLandCategoryInfoList.iterator();
        while (iterator.hasNext()){
            BasicEstateLandCategoryInfo categoryInfo = iterator.next();
            BasicEstateLandCategoryInfo obj = new BasicEstateLandCategoryInfo();
            BeanUtils.copyProperties(categoryInfo,obj);
            obj.setId(null);
            obj.setCreator(commonService.thisUserAccount());
            obj.setGmtCreated(null);
            obj.setGmtModified(null);
            obj.setLandId(newId);
            saveAndUpdateBasicEstateLandCategoryInfo(obj,true) ;
        }
    }

    public List<BasicEstateLandCategoryInfo> getListByEstateId(Integer estateId){
        BasicEstateLandState basicEstateLandStateById = basicEstateLandStateService.getLandStateByEstateId(estateId);
        if (basicEstateLandStateById != null){
            BasicEstateLandCategoryInfo query = new BasicEstateLandCategoryInfo();
            query.setLandId(basicEstateLandStateById.getId());
            List<BasicEstateLandCategoryInfo> basicEstateLandCategoryInfoList = basicEstateLandCategoryInfoList(query);
            return basicEstateLandCategoryInfoList ;
        }
        return new ArrayList<>();
    }

    public BasicEstateLandCategoryInfo getBasicEstateLandCategoryInfoByHouseId(Integer houseId){
        BasicEstateLandCategoryInfo query = new BasicEstateLandCategoryInfo();
        query.setHouseId(houseId);
        List<BasicEstateLandCategoryInfo> basicEstateLandCategoryInfoList = basicEstateLandCategoryInfoList(query);
        if(CollectionUtils.isNotEmpty(basicEstateLandCategoryInfoList)){
            return basicEstateLandCategoryInfoList.get(0);
        }
        return null ;
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicEstateLandCategoryInfo getBasicEstateLandCategoryInfoById(Integer id) throws Exception {
        return basicEstateLandCategoryInfoDao.getBasicEstateLandCategoryInfoById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicEstateLandCategoryInfo
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicEstateLandCategoryInfo(BasicEstateLandCategoryInfo basicEstateLandCategoryInfo, boolean updateNull) throws Exception {
        if(!StringUtils.isNotEmpty(basicEstateLandCategoryInfo.getLandLevelContentResult())){
            basicEstateLandCategoryInfo.setLandLevelContentResult(null);
        }
        if (basicEstateLandCategoryInfo.getId() == null || basicEstateLandCategoryInfo.getId().intValue() == 0) {
            basicEstateLandCategoryInfo.setCreator(commonService.thisUserAccount());
            Integer id = basicEstateLandCategoryInfoDao.saveBasicEstateLandCategoryInfo(basicEstateLandCategoryInfo);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicEstateLandCategoryInfo.class), id);
            return id;
        } else {

            //json串不允许修改,因为有时会爆出  json串过长的错误  ,所以json串只能第一次保存后要么新增要么删除,直到这个异常排除
            BasicEstateLandCategoryInfo landCategoryInfo = getBasicEstateLandCategoryInfoById(basicEstateLandCategoryInfo.getId());
            if (StringUtils.isNotBlank(landCategoryInfo.getLandLevelContentResult())) {
                basicEstateLandCategoryInfo.setLandLevelContentResult(landCategoryInfo.getLandLevelContentResult());
                updateNull = true;
            }

            if (updateNull) {
                basicEstateLandCategoryInfo.setBisDelete(landCategoryInfo.getBisDelete());
                basicEstateLandCategoryInfo.setCreator(landCategoryInfo.getCreator());
                basicEstateLandCategoryInfo.setGmtCreated(landCategoryInfo.getGmtCreated());
                basicEstateLandCategoryInfo.setGmtModified(DateUtils.now());
                basicEstateLandCategoryInfo.setLandId(landCategoryInfo.getLandId());
            }
            basicEstateLandCategoryInfoDao.updateBasicEstateLandCategoryInfo(basicEstateLandCategoryInfo, updateNull);
            return basicEstateLandCategoryInfo.getId();
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicEstateLandCategoryInfo(Integer id) throws Exception {
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(id);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateLandCategoryInfo.class));
        boolean flag = basicEstateLandCategoryInfoDao.deleteBasicEstateLandCategoryInfo(id);
        baseAttachmentService.deleteAttachmentByDto(sysAttachmentDto);
        return flag;
    }

    /**
     * 获取数据列表
     *
     * @param basicEstateLandCategoryInfo
     * @return
     * @throws Exception
     */
    public List<BasicEstateLandCategoryInfo> basicEstateLandCategoryInfoList(BasicEstateLandCategoryInfo basicEstateLandCategoryInfo) {
        return basicEstateLandCategoryInfoDao.basicEstateLandCategoryInfoList(basicEstateLandCategoryInfo);
    }


    public BootstrapTableVo getBootstrapTableVo(BasicEstateLandCategoryInfo basicEstateLandCategoryInfo) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstateLandCategoryInfo> basicEstateLandCategoryInfoList = basicEstateLandCategoryInfoDao.basicEstateLandCategoryInfoList(basicEstateLandCategoryInfo);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicEstateLandCategoryInfoList) ? new ArrayList<BasicEstateLandCategoryInfo>(10) : basicEstateLandCategoryInfoList);
        return vo;
    }


}
