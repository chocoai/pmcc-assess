package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseRoomDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoom;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoomDecorate;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseRoomVo;
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
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicHouseRoom getBasicHouseRoomById(Integer id) throws Exception {
        return basicHouseRoomDao.getBasicHouseRoomById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouseRoom
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouseRoom(BasicHouseRoom basicHouseRoom) throws Exception {
        if (basicHouseRoom.getId() == null || basicHouseRoom.getId().intValue() == 0) {
            basicHouseRoom.setCreator(commonService.thisUserAccount());
            Integer id = basicHouseRoomDao.saveBasicHouseRoom(basicHouseRoom);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicHouseRoom.class), id);
            basicHouseRoom.setId(id);
            return id;
        } else {
            BasicHouseRoom oo = basicHouseRoomDao.getBasicHouseRoomById(basicHouseRoom.getId());
            basicHouseRoomDao.updateBasicHouseRoom(basicHouseRoom);
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
        vo.setRoomTypeName(baseDataDicService.getNameById(basicHouseRoom.getRoomType()));
        vo.setOrientationName(baseDataDicService.getNameById(basicHouseRoom.getOrientation()));
        return vo;
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


}
