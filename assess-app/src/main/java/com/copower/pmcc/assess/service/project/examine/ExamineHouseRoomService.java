package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineHouseRoomDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseRoom;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineHouseRoomVo;
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
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/24 18:22
 * @Description:房间
 */
@Service
public class ExamineHouseRoomService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineHouseRoomDao examineHouseRoomDao;
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
    public ExamineHouseRoom getExamineHouseRoomById(Integer id) {
        return examineHouseRoomDao.getHouseRoomById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineHouseRoom
     * @return
     */
    public List<ExamineHouseRoom> getExamineHouseRoomList(ExamineHouseRoom examineHouseRoom) {
        return examineHouseRoomDao.getHouseRoomList(examineHouseRoom);
    }

    public BootstrapTableVo getExamineHouseRoomLists(ExamineHouseRoom examineHouseRoom) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineHouseRoomVo> vos = Lists.newArrayList();
        getExamineHouseRoomList(examineHouseRoom).stream().forEach(oo -> vos.add(getExamineHouseRoomVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineHouseRoomVo>() : vos);
        return vo;
    }

    public ExamineHouseRoomVo getExamineHouseRoomVo(ExamineHouseRoom examineHouseRoom) {
        ExamineHouseRoomVo vo = new ExamineHouseRoomVo();
        BeanUtils.copyProperties(examineHouseRoom, vo);
        if (examineHouseRoom.getRoomType() != null) {
            vo.setRoomTypeName(getValue(AssessExamineTaskConstant.EXAMINE_UNIT_HOUSE_TYPE, examineHouseRoom.getRoomType()));
        }
        return vo;
    }

    private String getValue(String key, Integer v) {
        StringBuilder builder = new StringBuilder(1024);
        List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(key);
        if (baseDataDic.size() >= 1) {
            if (v != null) {
                for (BaseDataDic base : baseDataDic) {
                    if (base.getId().intValue() == v.intValue()) {
                        builder.append(base.getName());
                    }
                }
            }
        }
        return builder.toString();
    }

    /**
     * 新增
     *
     * @param examineHouseRoom
     * @return
     */
    public boolean addExamineHouseRoom(ExamineHouseRoom examineHouseRoom) {
        examineHouseRoom.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineHouseRoom.getDeclareId())) {
            examineHouseRoom.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineHouseRoom.getExamineType())) {
            examineHouseRoom.setExamineType(0);
        }
        return examineHouseRoomDao.addHouseRoom(examineHouseRoom);
    }

    /**
     * 编辑
     *
     * @param examineHouseRoom
     * @return
     */
    public boolean updateExamineHouseRoom(ExamineHouseRoom examineHouseRoom) {
        return examineHouseRoomDao.updateHouseRoom(examineHouseRoom);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineHouseRoom(Integer id) {
        return examineHouseRoomDao.deleteHouseRoom(id);
    }


}
