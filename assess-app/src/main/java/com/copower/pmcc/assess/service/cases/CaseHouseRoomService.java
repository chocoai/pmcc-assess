package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseHouseRoomDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseRoom;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseRoomVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 10:13
 * @Description:
 */
@Service
public class CaseHouseRoomService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseHouseRoomDao caseHouseRoomDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public CaseHouseRoom getCaseHouseRoomById(Integer id) {
        return caseHouseRoomDao.getHouseRoomById(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseHouseRoom
     * @return
     */
    public List<CaseHouseRoom> getCaseHouseRoomList(CaseHouseRoom caseHouseRoom) {
        return caseHouseRoomDao.getHouseRoomList(caseHouseRoom);
    }

    public BootstrapTableVo getCaseHouseRoomLists(CaseHouseRoom caseHouseRoom) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseHouseRoomVo> vos = Lists.newArrayList();
        getCaseHouseRoomList(caseHouseRoom).stream().forEach(oo -> vos.add(getCaseHouseRoomVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseHouseRoomVo>() : vos);
        return vo;
    }

    public CaseHouseRoomVo getCaseHouseRoomVo(CaseHouseRoom caseHouseRoom) {
        CaseHouseRoomVo vo = new CaseHouseRoomVo();
        BeanUtils.copyProperties(caseHouseRoom, vo);
        vo.setRoomTypeName(baseDataDicService.getNameById(caseHouseRoom.getRoomType()));
        return vo;
    }

    /**
     * 新增
     *
     * @param caseHouseRoom
     * @return
     */
    public boolean addCaseHouseRoom(CaseHouseRoom caseHouseRoom) {
        return caseHouseRoomDao.addHouseRoom(caseHouseRoom);
    }

    public Integer saveCaseHouseRoom(CaseHouseRoom caseHouseRoom){
        return caseHouseRoomDao.saveCaseHouseRoom(caseHouseRoom);
    }


    /**
     * 编辑
     *
     * @param caseHouseRoom
     * @return
     */
    public boolean updateCaseHouseRoom(CaseHouseRoom caseHouseRoom) {
        return caseHouseRoomDao.updateHouseRoom(caseHouseRoom);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseHouseRoom(Integer id) {
        return caseHouseRoomDao.deleteHouseRoom(id);
    }

    /**
     * 根据查询条件判断是否有数据
     * @param houseId
     * @return
     */
    public boolean hasHouseRoomData(Integer houseId){
        return caseHouseRoomDao.countByHouseId(houseId)>0;
    }
}
