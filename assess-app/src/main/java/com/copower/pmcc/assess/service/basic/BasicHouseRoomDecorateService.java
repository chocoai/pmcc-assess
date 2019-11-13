package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseRoomDecorateDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseRoomDecorate;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseRoomDecorateVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/2 10:12
 * @Description:房间装修
 */
@Service
public class BasicHouseRoomDecorateService {
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicHouseRoomDecorateDao basicHouseRoomDecorateDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
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
    public BasicHouseRoomDecorate getBasicHouseRoomDecorateById(Integer id) throws Exception {
        return basicHouseRoomDecorateDao.getBasicHouseRoomDecorateById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouseRoomDecorate
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouseRoomDecorate(BasicHouseRoomDecorate basicHouseRoomDecorate, boolean updateNull) throws Exception {
        if (basicHouseRoomDecorate.getId() == null || basicHouseRoomDecorate.getId().intValue() == 0) {
            basicHouseRoomDecorate.setCreator(commonService.thisUserAccount());
            Integer id = basicHouseRoomDecorateDao.addBasicHouseRoomDecorate(basicHouseRoomDecorate);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicHouseRoomDecorate.class), id);
            return id;
        } else {
            if(updateNull){
                BasicHouseRoomDecorate houseRoomDecorate = basicHouseRoomDecorateDao.getBasicHouseRoomDecorateById(basicHouseRoomDecorate.getId());
                if(houseRoomDecorate!=null){
                    basicHouseRoomDecorate.setCreator(houseRoomDecorate.getCreator());
                    basicHouseRoomDecorate.setGmtCreated(houseRoomDecorate.getGmtCreated());
                }
            }
            basicHouseRoomDecorateDao.updateBasicHouseRoomDecorate(basicHouseRoomDecorate,updateNull);
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
    public boolean deleteBasicHouseRoomDecorate(Integer id) throws Exception {
        return basicHouseRoomDecorateDao.deleteBasicHouseRoomDecorate(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicHouseRoomDecorate
     * @return
     * @throws Exception
     */
    public List<BasicHouseRoomDecorate> basicHouseRoomDecorateList(BasicHouseRoomDecorate basicHouseRoomDecorate) throws Exception {
        return basicHouseRoomDecorateDao.basicHouseRoomDecorateList(basicHouseRoomDecorate);
    }

    public List<BasicHouseRoomDecorateVo> getHouseRoomDecorateList(Integer roomId)  {
        BasicHouseRoomDecorate basicHouseRoomDecorate =new BasicHouseRoomDecorate();
        basicHouseRoomDecorate.setRoomId(roomId);
        List<BasicHouseRoomDecorate> roomDecorates = basicHouseRoomDecorateDao.basicHouseRoomDecorateList(basicHouseRoomDecorate);
        if(CollectionUtils.isEmpty(roomDecorates)) return null;
        return LangUtils.transform(roomDecorates, o -> getBasicHouseRoomDecorateVo(o));
    }


    public BootstrapTableVo getBootstrapTableVo(BasicHouseRoomDecorate basicHouseRoomDecorate) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseRoomDecorate> basicHouseRoomDecorateList = basicHouseRoomDecorateDao.basicHouseRoomDecorateList(basicHouseRoomDecorate);
        List<BasicHouseRoomDecorateVo> vos = Lists.newArrayList();
        basicHouseRoomDecorateList.forEach(oo -> vos.add(getBasicHouseRoomDecorateVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicHouseRoomDecorateVo>(10) : vos);
        return vo;
    }

    public BasicHouseRoomDecorateVo getBasicHouseRoomDecorateVo(BasicHouseRoomDecorate basicHouseRoomDecorate) {
        if (basicHouseRoomDecorate == null) {
            return null;
        }
        BasicHouseRoomDecorateVo vo = new BasicHouseRoomDecorateVo();
        BeanUtils.copyProperties(basicHouseRoomDecorate, vo);
        vo.setPartName(baseDataDicService.getNameById(basicHouseRoomDecorate.getPart()));
        vo.setMaterialName(baseDataDicService.getNameById(basicHouseRoomDecorate.getMaterial()));
        vo.setConstructionTechnologyName(baseDataDicService.getNameById(basicHouseRoomDecorate.getConstructionTechnology()));
        vo.setMaterialPriceName(baseDataDicService.getNameById(basicHouseRoomDecorate.getMaterialPrice()));
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(basicHouseRoomDecorate.getId(), "house_room_file", "tb_basic_house_room_decorate");
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
        vo.setCreatorName(publicService.getUserNameByAccount(basicHouseRoomDecorate.getCreator()));
        return vo;
    }
}
