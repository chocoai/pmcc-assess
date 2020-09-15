package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.base.BaseDataDicDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseRoomDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoom;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoomDecorate;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseRoomVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/2 09:59
 * @Description:房间
 */
@Service
public class BasicHouseRoomService {
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicHouseRoomDao basicHouseRoomDao;
    @Autowired
    private BaseDataDicDao cmsBaseDataDicDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    @Autowired
    private PublicService publicService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicHouseRoom getBasicHouseRoomById(Integer id) throws Exception {
        return getBasicHouseRoomVo(basicHouseRoomDao.getBasicHouseRoomById(id));
    }

    /**
     * 新增或者修改
     *
     * @param basicHouseRoom
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouseRoom(BasicHouseRoom basicHouseRoom, boolean updateNull) throws Exception {
        if (basicHouseRoom.getId() == null || basicHouseRoom.getId().intValue() == 0) {
            basicHouseRoom.setCreator(commonService.thisUserAccount());
            Integer id = basicHouseRoomDao.addBasicHouseRoom(basicHouseRoom);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicHouseRoom.class), id);
            basicHouseRoom.setId(id);
            return id;
        } else {
            if (updateNull) {
                BasicHouseRoom houseRoom = basicHouseRoomDao.getBasicHouseRoomById(basicHouseRoom.getId());
                if (houseRoom != null) {
                    basicHouseRoom.setBisDelete(houseRoom.getBisDelete());
                    basicHouseRoom.setCreator(houseRoom.getCreator());
                    basicHouseRoom.setGmtCreated(houseRoom.getGmtCreated());
                    basicHouseRoom.setGmtModified(DateUtils.now());
                }
            }
            basicHouseRoomDao.updateBasicHouseRoom(basicHouseRoom, updateNull);
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
    public boolean deleteBasicHouseRoom(Integer id) throws Exception {
        List<BasicHouseRoomDecorate> basicHouseRoomDecorateList = null;
        BasicHouseRoomDecorate query = new BasicHouseRoomDecorate();
        query.setRoomId(id);
        basicHouseRoomDecorateList = basicHouseRoomDecorateService.basicHouseRoomDecorateList(query);
        if (!ObjectUtils.isEmpty(basicHouseRoomDecorateList)) {
            for (BasicHouseRoomDecorate houseRoomDecorate : basicHouseRoomDecorateList) {
                basicHouseRoomDecorateService.deleteBasicHouseRoomDecorate(houseRoomDecorate.getId());
            }
        }
        return basicHouseRoomDao.deleteBasicHouseRoom(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicHouseRoom
     * @return
     * @throws Exception
     */
    public List<BasicHouseRoom> basicHouseRoomList(BasicHouseRoom basicHouseRoom) throws Exception {
        return basicHouseRoomDao.basicHouseRoomList(basicHouseRoom);
    }


    public BootstrapTableVo getBootstrapTableVo(BasicHouseRoom basicHouseRoom) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseRoom> basicHouseRoomList = basicHouseRoomDao.basicHouseRoomList(basicHouseRoom);
        List<BasicHouseRoomVo> vos = Lists.newArrayList();
        basicHouseRoomList.forEach(oo -> vos.add(getBasicHouseRoomVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicHouseRoomVo>(10) : vos);
        return vo;
    }

    public List<BasicHouseRoom> getBasicHouseRoomList(Integer houseId) {
        BasicHouseRoom basicHouseRoom = new BasicHouseRoom();
        basicHouseRoom.setHouseId(houseId);
        List<BasicHouseRoom> basicHouseRoomList = basicHouseRoomDao.basicHouseRoomList(basicHouseRoom);
        return basicHouseRoomList;
    }

    public List<BasicHouseRoomVo> getBasicHouseRoomVoList(Integer houseId) {
        return LangUtils.transform(getBasicHouseRoomList(houseId), o -> getBasicHouseRoomVo(o));
    }

    public BasicHouseRoomVo getBasicHouseRoomVo(BasicHouseRoom basicHouseRoom) {
        if (basicHouseRoom == null) {
            return null;
        }
        BasicHouseRoomVo vo = new BasicHouseRoomVo();
        BeanUtils.copyProperties(basicHouseRoom, vo);
        vo.setRoomTypeName(basicHouseRoom.getRoomType());
        vo.setOrientationName(baseDataDicService.getNameById(basicHouseRoom.getOrientation()));
        vo.setStandardMeasureName(baseDataDicService.getNameById(basicHouseRoom.getStandardMeasure()));
        vo.setStorageRequestName(baseDataDicService.getNameById(basicHouseRoom.getStorageRequest()));
        vo.setAdjacentPositionName(baseDataDicService.getNameById(basicHouseRoom.getAdjacentPosition()));
        if(!StringUtils.isEmpty(basicHouseRoom.getAdjacentPosition())&&!StringUtils.isEmpty(basicHouseRoom.getDistance())){
            String[] adjacentPositions = basicHouseRoom.getAdjacentPosition().split(",");
            String[] distances = basicHouseRoom.getDistance().split(",");
            StringBuilder s = new StringBuilder();
            if(adjacentPositions.length>0){
                try {
                    for (int i = 0; i < adjacentPositions.length; i++) {
                        if(!StringUtils.isEmpty(adjacentPositions[i])){
                            s.append("距离").append(baseDataDicService.getNameById(adjacentPositions[i])).append(distances[i]).append("m").append(";");
                        }
                    }
                }catch (Exception ex){
                    logger.error(ex.getMessage(),ex);
                }
            }
            vo.setAdjacentPositionDescribe(s.toString());
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(basicHouseRoom.getId(), null, FormatUtils.entityNameConvertToTableName(BasicHouseRoom.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            if (sysAttachmentDtos.size() >= 1) {
                for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                    if (sysAttachmentDto != null) {
                        builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto));
                        builder.append(" ");
                    }
                }
            }
            vo.setFileViewName(builder.toString());
        }
        vo.setCreatorName(publicService.getUserNameByAccount(basicHouseRoom.getCreator()));
        return vo;
    }

    public void autoGenerate(String huxingData,Integer houseId)throws Exception{
        if(!StringUtils.isEmpty(huxingData)){
            List<KeyValueDto> baseDtos = JSON.parseArray(huxingData, KeyValueDto.class);
            if(CollectionUtils.isNotEmpty(baseDtos)){
                for (KeyValueDto item:baseDtos) {
                    for (int i = 0; i < Integer.valueOf(item.getValue()); i++) {
                        BasicHouseRoom basicHouseRoom = new BasicHouseRoom();
                        basicHouseRoom.setHouseId(houseId);
                        if(Integer.valueOf(item.getValue())>1){
                            basicHouseRoom.setName(String.format("%s%s",item.getKey(),i+1));
                        }else {
                            basicHouseRoom.setName(item.getKey());
                        }
                        saveAndUpdateBasicHouseRoom(basicHouseRoom,true);
                    }
                }
            }
        }
    }

    /**
     * 根据查询条件判断是否有数据
     *
     * @param houseId
     * @return
     */
    public boolean hasHouseRoomData(Integer houseId) {
        return basicHouseRoomDao.countByHouseId(houseId) > 0;
    }


    /**
     * 获取设定用途字段列表
     *
     * @return
     */
    public BootstrapTableVo getRoomTypeList(String name, Integer pid) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        //List<DataSetUseField> list = dataSetUseFieldDao.getListObject(name, 0);
        List<BaseDataDic> data = cmsBaseDataDicDao.getListByPid(pid, name);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(data) ? new ArrayList<BaseDataDic>() : data);
        return bootstrapTableVo;
    }
}
